package de.bas.deploymentmanager.logic.domain.dicd.boundary;

import de.bas.deploymentmanager.logic.domain.stage.entity.StageEnum;

public interface CiCdService {

    void deployImage(String jobName, String image, StageEnum stageEnum);

    void buildImage(String jobName);
}
