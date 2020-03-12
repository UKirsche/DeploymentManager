package de.bas.deploymentmanager.frontend.jsf.application;

import de.bas.deploymentmanager.logic.business.deploy.DeployFlow;
import de.bas.deploymentmanager.logic.domain.application.boundary.ApplicationService;
import de.bas.deploymentmanager.logic.domain.application.entity.Application;
import de.bas.deploymentmanager.logic.domain.dicd.boundary.CiCdService;
import de.bas.deploymentmanager.logic.domain.stage.entity.StageEnum;
import lombok.Getter;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@RequestScoped
@Named
public class ApplicationTableBean {

    @Inject
    private ApplicationService applicationService;

    @Inject
    private CiCdService ciCdService;

    @Inject
    private DeployFlow deployFlow;

    @Getter
    private List<Application> list;


    @PostConstruct
    public void init() {
        this.list = applicationService.getAllApplications();
    }

    public void build(String buildJob) {
        deployFlow.imageDeployed("zus", StageEnum.ETW, "1.0-1", "http://etw-docker-01.bvaetw.de", "8080");
        ciCdService.buildApplication(buildJob);
    }


}
