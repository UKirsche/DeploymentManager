package de.bas.deploymentmanager.logic.domain.application.boundary;

import de.bas.deploymentmanager.logic.domain.application.entity.Application;

import java.util.List;

public interface ApplicationService {

    List<Application> getAllApplications();

    Application getApplication(String identifier);

    String generateNewImage(String identifier, Integer majorVersion, Integer minorVersion);
}
