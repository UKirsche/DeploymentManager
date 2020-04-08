package de.bas.deploymentmanager.logic.business.loadstage;

import de.bas.deploymentmanager.logic.domain.project.boundary.ProjectService;
import de.bas.deploymentmanager.logic.domain.stage.boundary.StageService;
import de.bas.deploymentmanager.logic.domain.stage.entity.Stage;
import de.bas.deploymentmanager.logic.domain.stage.entity.StageEnum;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashMap;

/**
 * Befüllt die StageModels zur Darstellung der Stages-Gesamtübersicht
 */
@Stateless
public class LoadStageFlowImpl implements LoadStageFlow {

    private final ProjectService projectService;
    private final StageService stageService;
    private HashMap<String, Stage> stageModels;

    @Inject
    public LoadStageFlowImpl(ProjectService projectService, StageService stageService) {
        this.projectService = projectService;
        this.stageService = stageService;
    }


    @Override
    public StageDiagramModel load() {
        stageModels = new HashMap<>();
        fillStageModel();
        return StageDiagramModel.builder().stageModels(stageModels).build();
    }


    private void fillStageModel() {
        stageModels.put(StageEnum.ETW.name(), stageService.getStage(StageEnum.ETW));
        stageModels.put(StageEnum.INT.name(), stageService.getStage(StageEnum.INT));
        stageModels.put(StageEnum.PRD.name(), stageService.getStage(StageEnum.PRD));
    }


}
