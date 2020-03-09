package de.bas.deploymentmanager.logic.domain.application.control;

import de.bas.deploymentmanager.logic.domain.application.boundary.ApplicationService;
import de.bas.deploymentmanager.logic.domain.application.entity.Application;
import de.bas.deploymentmanager.logic.domain.application.entity.Image;
import de.bas.deploymentmanager.logic.domain.application.entity.NewTagModel;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Stateless
public class ApplicationServiceImpl implements ApplicationService {


    @Inject
    private ApplicationRepository applicationRepository;
    @Inject
    private ImageRepository imageRepository;

    @Override
    public List<Application> getAllApplications() {
        return applicationRepository.getAllApplications();
    }

    @Override
    public Application getApplication(String identifier) {
        Application application = applicationRepository.getByIfentifier(identifier);
        application.setImages(imageRepository.getImagesForApplication(application.getId()));
        return application;
    }

    @Override
    public String generateNewImage(String identifier, NewTagModel newTagModel) {
        Application application = applicationRepository.getByIfentifier(identifier);

        int majorVersion = getMajorVersion(newTagModel);
        int minorVersion = getMinorVersion(newTagModel);

        Optional<Image> optionalImage = imageRepository.getLastImageOfVersion(application.getId(), majorVersion, minorVersion);

        Integer buildNumber = optionalImage.map(image -> image.getBuildNumber() + 1).orElse(1);
        Image image = createNewImage(application.getId(), majorVersion, minorVersion, newTagModel, buildNumber);
        Image save = imageRepository.save(image);

        return save.getTag();
    }

    private Image createNewImage(Long applicationId, int majorVersion, int minorVersion, NewTagModel newTagModel, Integer buildNumber) {
        Image image = new Image();
        image.setApplicationId(applicationId);
        image.setMajorVersion(majorVersion);
        image.setMinorVersion(minorVersion);
        image.setBuildNumber(0);
        image.setCreateDate(LocalDateTime.now());
        image.setUser(newTagModel.getUser());
        image.setImage(newTagModel.getImage());
        image.setBuildNumber(buildNumber);
        image.setTag(String.format("%s.%s-%s", majorVersion, minorVersion, buildNumber));
        return image;
    }

    private int getMinorVersion(NewTagModel newTagModel) {
        if (newTagModel.getVersion() == null) {
            return 0;
        }
        String[] versionArray = newTagModel.getVersion().split("\\.");
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

    private int getMajorVersion(NewTagModel newTagModel) {
        if (newTagModel.getVersion() == null) {
            return 0;
        }
        String[] versionArray = newTagModel.getVersion().split("\\.");
        try {
            return Integer.parseInt(versionArray[0]);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public Application createNewApplication(Application model) {
        return applicationRepository.save(model);
    }

    @Override
    public List<Image> getImages(String identifier) {
        Application application = applicationRepository.getByIfentifier(identifier);
        return imageRepository.getImagesForApplication(application.getId());
    }
}
