package de.bas.deploymentmanager.logic.domain.application.control;

import de.bas.deploymentmanager.logic.domain.application.entity.Application;

import java.util.List;

public interface ApplicationRepository {
    List<Application> getAllApplications();

    Application getByIfentifier(String identifier);

    Application save(Application application);

}
