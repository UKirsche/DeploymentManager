package de.bas.deploymentmanager.logic.business.deploy;

import de.bas.deploymentmanager.logic.domain.dicd.boundary.CiCdService;
import de.bas.deploymentmanager.logic.domain.project.boundary.ProjectService;
import de.bas.deploymentmanager.logic.domain.project.entity.Image;
import de.bas.deploymentmanager.logic.domain.project.entity.Project;
import de.bas.deploymentmanager.logic.domain.stage.boundary.StageService;
import de.bas.deploymentmanager.logic.domain.stage.entity.Stage;
import de.bas.deploymentmanager.logic.domain.stage.entity.StageEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class DeployFlowImpl implements DeployFlow {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final ProjectService projectService;
    private final StageService stageService;
    private final CiCdService ciCdService;


    @Inject
    public DeployFlowImpl(ProjectService projectService, StageService stageService, CiCdService ciCdService) {
        this.projectService = projectService;
        this.stageService = stageService;
        this.ciCdService = ciCdService;
    }

    @Override
    public void imageDeployed(String identifier, StageEnum stageName, String tag, String host, String port) {
        Stage stage = stageService.getStage(stageName);
        Image image = projectService.markImageAsDeployed(identifier, tag, stage);
        stageService.imageDeployed(identifier, image, host, port);

    }

    @Override
    public void deployImage(String identifier, StageEnum stage, String tag) {
        log.info("Projekt {} wird auf Stage {} mit Tag {} deployed", identifier, stage, tag);
        Project project = projectService.getProject(identifier);
        Image imageToDeploy = projectService.getImage(project.getId(), tag);
        ciCdService.deployImage(project.getDeployJob(), imageToDeploy.getTag(), stage);
    }
}
