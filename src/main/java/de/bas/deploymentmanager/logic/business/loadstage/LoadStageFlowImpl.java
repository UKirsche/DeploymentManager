package de.bas.deploymentmanager.logic.business.loadstage;

import de.bas.deploymentmanager.logic.domain.stage.entity.StageEnum;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashMap;

/**
 * Befüllt die StageModels zur Darstellung der Stages-Gesamtübersicht
 */
@Stateless
public class LoadStageFlowImpl implements LoadStageFlow {

    private final StageModelFiller stageModelFiller;
    private HashMap<String, StageModel> stageDiagramModel;

    @Inject
    public LoadStageFlowImpl(StageModelFiller stageModelFiller) {
        this.stageModelFiller=stageModelFiller;
    }


    @Override
    public StageDiagramModel load() {
        stageDiagramModel = new HashMap<>();
        fillStageDiagramModel();
        return StageDiagramModel.builder().stageModels(stageDiagramModel).build();
    }

    private void fillStageDiagramModel() {
        stageDiagramModel.put(StageEnum.ETW.name(), stageModelFiller.fillStageModel(StageEnum.ETW));
        stageDiagramModel.put(StageEnum.INT.name(), stageModelFiller.fillStageModel(StageEnum.INT));
        stageDiagramModel.put(StageEnum.PRD.name(), stageModelFiller.fillStageModel(StageEnum.PRD));
    }




}
