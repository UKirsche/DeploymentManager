package de.bas.deploymentmanager.logic.domain.stage.boundary;

import de.bas.deploymentmanager.logic.domain.stage.entity.Stage;
import de.bas.deploymentmanager.logic.domain.stage.entity.StageEnum;

public interface StageService {

    /**
     * Markiert eine App als Deployed.
     * Markiert
     *
     * @param identifier
     * @param tag
     * @param host
     */
    void imageDeployed(String identifier, String tag, String host);


    Stage getStage(StageEnum stage);
}
