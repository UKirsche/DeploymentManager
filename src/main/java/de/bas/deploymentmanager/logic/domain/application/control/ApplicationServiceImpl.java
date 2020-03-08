package de.bas.deploymentmanager.logic.domain.application.control;

import de.bas.deploymentmanager.logic.domain.application.boundary.ApplicationService;
import de.bas.deploymentmanager.logic.domain.application.entity.Application;
import de.bas.deploymentmanager.logic.domain.application.entity.Image;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

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
    public String generateNewImage(String identifier, String version, String minorVersion) {
        return null;
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
