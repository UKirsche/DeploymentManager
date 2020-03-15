package de.bas.deploymentmanager.logic.domain.project.control;

import de.bas.deploymentmanager.logic.domain.project.boundary.ProjectService;
import de.bas.deploymentmanager.logic.domain.project.entity.Deployment;
import de.bas.deploymentmanager.logic.domain.project.entity.Image;
import de.bas.deploymentmanager.logic.domain.project.entity.NewImageModel;
import de.bas.deploymentmanager.logic.domain.project.entity.Project;
import de.bas.deploymentmanager.logic.domain.stage.entity.Stage;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Stateless
public class ProjectServiceImpl implements ProjectService {


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
        project.setImages(imageRepository.getImagesForApplication(project.getId()));
        return project;
    }

    @Override
    public String generateNewImage(String identifier, NewImageModel newImageModel) {
        Project project = projectRepository.getByIfentifier(identifier);

        int majorVersion = getMajorVersion(newImageModel);
        int minorVersion = getMinorVersion(newImageModel);

        Optional<Image> optionalImage = imageRepository.getLastImageOfVersion(project.getId(), majorVersion, minorVersion);

        Integer buildNumber = optionalImage.map(image -> image.getBuildNumber() + 1).orElse(1);
        Image image = createNewImage(project.getId(), majorVersion, minorVersion, newImageModel, buildNumber);
        Image save = imageRepository.save(image);

        return save.getTag();
    }

    @Override
    public Image markImageAsDeployed(String identifier, String tag, Stage stage) {
        Image image = imageRepository.getImageByIdentifierTag(identifier, tag);
        addDeployment(image, stage.getId());
        return image;
    }

    private void addDeployment(Image image, Long satgeId) {
        Deployment deployment = new Deployment();
        deployment.setCreateTime(LocalDateTime.now());
        deployment.setStageId(satgeId);
        deploymentRepository.save(deployment);
    }

    private Image createNewImage(Long applicationId, int majorVersion, int minorVersion, NewImageModel newImageModel, Integer buildNumber) {
        Image image = new Image();
        image.setProjectId(applicationId);
        image.setMajorVersion(majorVersion);
        image.setMinorVersion(minorVersion);
        image.setBuildNumber(0);
        image.setCreateDate(LocalDateTime.now());
        image.setUser(newImageModel.getUser());
        image.setImage(newImageModel.getImage());
        image.setBuildNumber(buildNumber);
        image.setTag(String.format("%s.%s-%s", majorVersion, minorVersion, buildNumber));
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
                return Integer.parseInt(versionArray[1]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
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
            return Integer.parseInt(versionArray[0]);
        } catch (NumberFormatException e) {
            e.printStackTrace();
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
        return imageRepository.getImagesForApplication(project.getId());
    }

    @Override
    public Image getImage(Long applicationId, String tag) {
        return imageRepository.getImageByApplicationIdTag(applicationId, tag);
    }
}
