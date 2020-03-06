package de.bas.deploymentmanager.logic.domain.application.control;

import de.bas.deploymentmanager.logic.domain.application.boundary.ApplicationService;
import de.bas.deploymentmanager.logic.domain.application.entity.Application;

import java.util.List;

public class ApplicationServiceImpl implements ApplicationService {

    private ApplicationRepository applicationRepository;
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
    public String generateNewImage(String identifier, Integer majorVersion, Integer minorVersion) {
        return null;
    }
}
