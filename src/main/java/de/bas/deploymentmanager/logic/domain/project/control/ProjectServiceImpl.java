package de.bas.deploymentmanager.logic.domain.project.control;

import de.bas.deploymentmanager.logic.domain.project.boundary.ProjectService;
import de.bas.deploymentmanager.logic.domain.project.entity.*;
import de.bas.deploymentmanager.logic.domain.project.entity.exception.ImageDeleteException;
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


    private final ProjectRepository projectRepository;
    private final ImageRepository imageRepository;
    private final DeploymentRepository deploymentRepository;

    @Inject
    public ProjectServiceImpl(ProjectRepository projectRepository, ImageRepository imageRepository, DeploymentRepository deploymentRepository) {
        this.projectRepository = projectRepository;
        this.imageRepository = imageRepository;
        this.deploymentRepository = deploymentRepository;
    }

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
    public Tag generateNewImage(String identifier, NewImageModel newImageModel) {
        Project project = null;
        try {
            project = projectRepository.getByIfentifier(identifier);
        } catch (NoResultException e) {
            log.info("Projekt mit den identifier {} wurde nicht gefunden", identifier);
            project = generateNewProject(identifier);
        }

        Tag newTag = generateNewTag(newImageModel.getVersion(), project);

        log.info("Neue Buildnumber ist: {}.{}.{}-{}"
                , newTag.getMajorVersion()
                , newTag.getMinorVersion()
                , newTag.getIncrementalVersion()
                , newTag.getBuildNumber());

        Image image = createNewImage(project.getId()
                , newTag
                , newImageModel);
        Image save = imageRepository.save(image);
        return newTag;
    }

    @Override
    public String gererateNextTag(String projectIdintifier, String version) {
        Project project = null;
        try {
            project = projectRepository.getByIfentifier(projectIdintifier);
        } catch (NoResultException e) {
            log.info("Projekt mit den identifier {} wurde nicht gefunden", projectIdintifier);
            project = generateNewProject(projectIdintifier);
        }
        return generateNewTag(version, project).toString();
    }

    /**
     * Gibt die nächste Buildnummer zurück
     *
     * @param version
     * @param project
     * @return
     */
    private Tag generateNewTag(String version, Project project) {
        int majorVersion = getMajorVersion(version);
        int minorVersion = getMinorVersion(version);
        int incrementalVersion = getIncrementalVersion(version);

        log.debug("Übergebene Version ist: {}.{}.{}", majorVersion, majorVersion, incrementalVersion);

        Optional<Image> optionalImage = imageRepository.getLastImageOfVersion(project.getId(), majorVersion, minorVersion, incrementalVersion);

        int buildNumber = optionalImage.map(image -> image.getBuildNumber() + 1).orElse(1);
        return new Tag(majorVersion, minorVersion, incrementalVersion, buildNumber);
    }

    private Project generateNewProject(String identifier) {
        log.info("Neues Projekt wird generiert für Identifier {}", identifier);
        Project project = new Project();
        project.setIdentifier(identifier);
        return projectRepository.save(project);
    }

    private int getIncrementalVersion(String version) {
        if (version == null) {
            return 0;
        }
        String[] versionArray = version.split("\\.");
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

    private Image createNewImage(Long projectId, Tag tag, NewImageModel newImageModel) {
        Image image = new Image();
        image.setProjectId(projectId);
        image.setTag(tag);
        image.setCreateDate(LocalDateTime.now());
        image.setUser(newImageModel.getUser());
        image.setImage(newImageModel.getImage());
        image.setCommit(newImageModel.getCommit());
        return image;
    }

    private int getMinorVersion(String version) {
        if (version == null) {
            return 0;
        }
        String[] versionArray = version.split("\\.");
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

    private int getMajorVersion(String version) {
        if (version == null) {
            return 0;
        }
        String[] versionArray = version.split("\\.");
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
        if (save.getImageSync().isAktiv()) {
            log.info("ImageSync ist aktiviert für {}", save.getImageSync().getPersistedValue());
            saveImageSync(save.getImageSync());
        }
        log.info("Projekt {} wurde mit ID {} gespeichert", save.getIdentifier(), save.getId());
        return save;
    }

    @Override
    public void syncImages(String projectIdentifier, NewImageModel newImageModel, Tag tagToSync) {
        Project project = projectRepository.getByIfentifier(projectIdentifier);
        if (project.getImageSync().isAktiv()) {
            project.getImageSync().getProjekctIdentifiers().stream()
                    .filter(ident -> !ident.equals(projectIdentifier))
                    .forEach(s -> {
                        Project syncProject = projectRepository.getByIfentifier(s);
                        Image newImage = createNewImage(syncProject.getId(), tagToSync, newImageModel);
                        log.info("Speicher neues Imgage durch Sync {}", newImage.getImageWithTag());
                        imageRepository.save(newImage);
                    });

        }
    }

    @Override
    public void deleteImage(Long imageId) throws ImageDeleteException {
        log.info("Lösche Image mit ID: {}", imageId);
        isImageLastBuildnumber(imageId);
        imageRepository.delete(imageId);
    }

    /**
     * Prüft ob das Image der letzte Build einer Version ist.
     * Wenn ja dann kann das Image nicht gelöscht werden
     *
     * @param imageId
     * @throws ImageDeleteException
     */
    private void isImageLastBuildnumber(Long imageId) throws ImageDeleteException {
        Image image = imageRepository.getById(imageId);
        Optional<Image> lastImageOfVersion = imageRepository.getLastImageOfVersion(image.getProjectId()
                , image.getMajorVersion()
                , image.getMinorVersion()
                , image.getIncrementalVersion());
        if (lastImageOfVersion.isPresent()) {
            if (image.equals(lastImageOfVersion.get())) {
                throw new ImageDeleteException("Image ist der aktuelle Build");
            }
        }
    }

    private void saveImageSync(ImageSync imageSync) {
        imageSync.getProjekctIdentifiers().forEach(identifier -> {
            Project project = projectRepository.getByIfentifier(identifier);
            project.setImageSync(imageSync);
            projectRepository.save(project);
        });
    }
}
