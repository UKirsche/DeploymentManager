package de.bas.deploymentmanager.logic.domain.stage.boundary;

import de.bas.deploymentmanager.logic.domain.stage.entity.DeployModel;

public interface StageService {

    /**
     * Markiert eine App als Deployed.
     * Markiert
     *
     * @param identifier
     * @param tag
     * @param deployModel
     */
    void imageDeployed(String identifier, String tag, DeployModel deployModel);
}
