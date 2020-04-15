package de.bas.deploymentmanager.logic.business.loadstage;

import de.bas.deploymentmanager.logic.domain.stage.entity.Stage;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
/**
 * Das Stagemodel lädt alle relevanten Informationen zur Darstellung der Stages
 */
public class StageModel {
    private Stage stage;
    private List<HostModel> hostModels;
}
