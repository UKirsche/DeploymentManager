package de.bas.deploymentmanager.logic.domain.application.boundary;

import de.bas.deploymentmanager.logic.domain.application.entity.Application;
import de.bas.deploymentmanager.logic.domain.application.entity.Image;

import java.util.List;

public interface ApplicationService {

    List<Application> getAllApplications();

    Application getApplication(String identifier);

    String generateNewImage(String identifier, String version, String minorVersion);

    Application createNewApplication(Application model);

    List<Image> getImages(String identifier);
}
