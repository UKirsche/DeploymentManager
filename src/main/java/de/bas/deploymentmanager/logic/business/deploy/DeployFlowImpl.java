package de.bas.deploymentmanager.logic.business.deploy;

import de.bas.deploymentmanager.logic.domain.application.boundary.ApplicationService;
import de.bas.deploymentmanager.logic.domain.application.entity.Application;
import de.bas.deploymentmanager.logic.domain.application.entity.Image;
import de.bas.deploymentmanager.logic.domain.dicd.boundary.CiCdService;
import de.bas.deploymentmanager.logic.domain.stage.boundary.StageService;
import de.bas.deploymentmanager.logic.domain.stage.entity.Stage;
import de.bas.deploymentmanager.logic.domain.stage.entity.StageEnum;

import javax.inject.Inject;

public class DeployFlowImpl implements DeployFlow {

    private final ApplicationService applicationService;
    private final StageService stageService;
    private final CiCdService ciCdService;


    @Inject
    public DeployFlowImpl(ApplicationService applicationService, StageService stageService, CiCdService ciCdService) {
        this.applicationService = applicationService;
        this.stageService = stageService;
        this.ciCdService = ciCdService;
    }

    @Override
    public void imageDeployed(String identifier, StageEnum stageName, String tag, String host) {
        Stage stage = stageService.getStage(stageName);
        applicationService.markImageAsDeployed(identifier, tag, stage);

    }

    @Override
    public void deployImgae(String identifier, StageEnum stage, String tag, String host) {
        Application application = applicationService.getApplication(identifier);
        Image imageToDeploy = applicationService.getImage(application.getId(), tag);
        ciCdService.deployImage(application.getDeployJob(), imageToDeploy.getImageWithTag(), stage);
    }
}
