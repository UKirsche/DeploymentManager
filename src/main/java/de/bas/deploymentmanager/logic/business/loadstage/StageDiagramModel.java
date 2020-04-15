package de.bas.deploymentmanager.logic.business.loadstage;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
@Builder
public class StageDiagramModel {
    HashMap<String, StageModel> stageModels;
}
