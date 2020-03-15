package de.bas.deploymentmanager.logic.domain.stage.boundary;

import de.bas.deploymentmanager.logic.domain.project.entity.Image;
import de.bas.deploymentmanager.logic.domain.stage.entity.Stage;
import de.bas.deploymentmanager.logic.domain.stage.entity.StageEnum;

public interface StageService {

    /**
     * Markiert eine App als Deployed.
     * Markiert
     *
     * @param identifier der Application die deployed wurde
     * @param image      das deployed wurde
     * @param host       auf dem deployed wurde
     * @param port       auf dem Host
     */
    void imageDeployed(String identifier, Image image, String host, String port);


    Stage getStage(StageEnum stage);
}
