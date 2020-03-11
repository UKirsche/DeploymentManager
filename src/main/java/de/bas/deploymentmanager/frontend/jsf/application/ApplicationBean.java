package de.bas.deploymentmanager.frontend.jsf.application;

import de.bas.deploymentmanager.logic.domain.application.boundary.ApplicationService;
import de.bas.deploymentmanager.logic.domain.application.entity.Application;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.stream.Collectors;

@RequestScoped
@Named
public class ApplicationBean {

    @Inject
    private ApplicationService applicationService;

    public String getApplications() {
        return applicationService.getAllApplications().stream().map(Application::getIdentifier).collect(Collectors.joining(". "));
    }
}
