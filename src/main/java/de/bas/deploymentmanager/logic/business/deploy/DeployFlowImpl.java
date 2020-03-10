package de.bas.deploymentmanager.logic.business.deploy;

import de.bas.deploymentmanager.logic.domain.application.boundary.ApplicationService;
import de.bas.deploymentmanager.logic.domain.stage.boundary.StageService;
import de.bas.deploymentmanager.logic.domain.stage.entity.Stage;
import de.bas.deploymentmanager.logic.domain.stage.entity.StageEnum;

import javax.inject.Inject;

public class DeployFlowImpl implements DeployFlow {

    private final ApplicationService applicationService;
    private final StageService stageService;

    @Inject
    public DeployFlowImpl(ApplicationService applicationService, StageService stageService) {
        this.applicationService = applicationService;
        this.stageService = stageService;
    }

    @Override
    public void imageDeployed(String identifier, StageEnum stageName, String tag, String host) {
        Stage stage = stageService.getStage(stageName);
        applicationService.markImageAsDeployed(identifier, tag, stage);

    }
}
