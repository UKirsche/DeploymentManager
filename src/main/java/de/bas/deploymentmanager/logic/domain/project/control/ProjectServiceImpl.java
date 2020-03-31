package de.bas.deploymentmanager.logic.domain.project.control;

import de.bas.deploymentmanager.logic.domain.project.boundary.ProjectService;
import de.bas.deploymentmanager.logic.domain.project.entity.Deployment;
import de.bas.deploymentmanager.logic.domain.project.entity.Image;
import de.bas.deploymentmanager.logic.domain.project.entity.NewImageModel;
import de.bas.deploymentmanager.logic.domain.project.entity.Project;
import de.bas.deploymentmanager.logic.domain.stage.entity.Stage;
import de.bas.deploymentmanager.logic.domain.stage.entity.StageEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Stateless
public class ProjectServiceImpl implements ProjectService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());


    @Inject
    private ProjectRepository projectRepository;
    @Inject
    private ImageRepository imageRepository;

    @Inject
    private DeploymentRepository deploymentRepository;

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.getAllProjects();
    }

    @Override
    public Project getProject(String identifier) {
        Project project = projectRepository.getByIfentifier(identifier);
        project.setImages(imageRepository.getImagesForProject(project.getId()));
        return project;
    }

    @Override
    public String generateNewImage(String identifier, NewImageModel newImageModel) {
        Project project = null;
        try {
            project = projectRepository.getByIfentifier(identifier);
        } catch (NoResultException e) {
            log.info("Projekt mit den identifier {} wurde nicht gefunden", identifier);
            project = generateNewProject(identifier);
        }

        int majorVersion = getMajorVersion(newImageModel);
        int minorVersion = getMinorVersion(newImageModel);
        int incrementalVersion = getIncrementalVersion(newImageModel);

        log.debug("Übergebene Version ist: {}.{}.{}", majorVersion, majorVersion, incrementalVersion);

        Optional<Image> optionalImage = imageRepository.getLastImageOfVersion(project.getId(), majorVersion, minorVersion, incrementalVersion);

        Integer buildNumber = optionalImage.map(image -> image.getBuildNumber() + 1).orElse(1);

        log.info("Neue Buildnumber ist: {}.{}.{}-{}", majorVersion, minorVersion, incrementalVersion, buildNumber);

        Image image = createNewImage(project.getId(), majorVersion, minorVersion, incrementalVersion, newImageModel, buildNumber);
        Image save = imageRepository.save(image);

        return save.getTag();
    }

    private Project generateNewProject(String identifier) {
        log.info("Neues Projekt wird generiert für Identifier {}", identifier);
        Project project = new Project();
        project.setIdentifier(identifier);
        return projectRepository.save(project);
    }

    private int getIncrementalVersion(NewImageModel newImageModel) {
        if (newImageModel.getVersion() == null) {
            return 0;
        }
        String[] versionArray = newImageModel.getVersion().split("\\.");
        if (versionArray.length <= 2) {
            return 0;
        } else {
            try {
                String number = versionArray[2];
                number = number.replace("-SNAPSHOT", "");
                return Integer.parseInt(number);
            } catch (NumberFormatException e) {
                log.warn("IncrementalVersion konnte nicht ermittelt werden");
                return 0;
            }
        }

    }

    @Override
    public Image markImageAsDeployed(String identifier, String tag, Stage stage) {
        Image image = imageRepository.getImageByIdentifierTag(identifier, tag);
        addDeployment(image, stage.getName());
        return image;
    }

    private void addDeployment(Image image, StageEnum stage) {
        if (image.getDeployments() != null) {
            Optional<Deployment> deploymentExists = image.getDeployments().stream().filter(deployment -> deployment.getStage().equals(stage)).findAny();
            if (deploymentExists.isPresent()) {
                deploymentExists.get().setCreateTime(LocalDateTime.now());
                deploymentRepository.save(deploymentExists.get());
                return;
            }
        }
        Deployment deployment = new Deployment();
        deployment.setCreateTime(LocalDateTime.now());
        deployment.setStage(stage);
        deployment.setImageId(image.getId());
        deploymentRepository.save(deployment);
    }

    private Image createNewImage(Long applicationId, int majorVersion, int minorVersion, int incrementalVersion, NewImageModel newImageModel, Integer buildNumber) {
        Image image = new Image();
        image.setProjectId(applicationId);
        image.setMajorVersion(majorVersion);
        image.setMinorVersion(minorVersion);
        image.setIncrementalVersion(incrementalVersion);
        image.setCreateDate(LocalDateTime.now());
        image.setUser(newImageModel.getUser());
        image.setImage(newImageModel.getImage());
        image.setBuildNumber(buildNumber);
        image.setCommit(newImageModel.getCommit());
        image.setTag(String.format("%s.%s.%s-%s", majorVersion, minorVersion, incrementalVersion, buildNumber));
        return image;
    }

    private int getMinorVersion(NewImageModel newImageModel) {
        if (newImageModel.getVersion() == null) {
            return 0;
        }
        String[] versionArray = newImageModel.getVersion().split("\\.");
        if (versionArray.length == 1) {
            return 0;
        } else {
            try {
                String number = versionArray[1];
                number = number.replace("-SNAPSHOT", "");
                return Integer.parseInt(number);
            } catch (NumberFormatException e) {
                log.warn("MinorVersion konnte nicht ermittelt werden");
                return 0;
            }
        }
    }

    private int getMajorVersion(NewImageModel newImageModel) {
        if (newImageModel.getVersion() == null) {
            return 0;
        }
        String[] versionArray = newImageModel.getVersion().split("\\.");
        try {
            String number = versionArray[0];
            number = number.replace("-SNAPSHOT", "");
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            log.warn("MajorVersion konnte nicht ermittelt werden");
            return 0;
        }
    }

    @Override
    public Project createNewProject(Project model) {
        return projectRepository.save(model);
    }

    @Override
    public List<Image> getImages(String identifier) {
        Project project = projectRepository.getByIfentifier(identifier);
        return imageRepository.getImagesForProject(project.getId());
    }

    @Override
    public List<Image> getImages(Long projectId) {
        return imageRepository.getImagesForProject(projectId);

    }

    @Override
    public Image getImage(Long applicationId, String tag) {
        return imageRepository.getImageByProjectIdTag(applicationId, tag);
    }

    @Override
    public Project getProject(Long id) {
        return projectRepository.getById(id);
    }

    @Override
    public Project save(Project project) {
        Project save = projectRepository.save(project);
        log.info("Projekt {} wurde mit ID {} gespeichert", save.getIdentifier(), save.getId());
        return save;
    }
}
