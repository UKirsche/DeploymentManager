package de.bas.deploymentmanager.frontend.jsf.application;

import de.bas.deploymentmanager.logic.domain.application.boundary.ApplicationService;
import de.bas.deploymentmanager.logic.domain.application.entity.Application;
import de.bas.deploymentmanager.logic.domain.dicd.boundary.CiCdService;
import lombok.Getter;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequestScoped
@Named
public class ApplicationTableBean {

    @Inject
    private ApplicationService applicationService;

    @Inject
    private CiCdService ciCdService;

    @Getter
    private List<Application> list;


    @PostConstruct
    public void init() {
        this.list = applicationService.getAllApplications();
    }

    public void build(String buildJob) {
        ciCdService.buildApplication(buildJob);
    }


}
