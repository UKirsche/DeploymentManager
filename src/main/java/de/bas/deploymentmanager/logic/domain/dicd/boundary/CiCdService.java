package de.bas.deploymentmanager.logic.domain.dicd.boundary;

import de.bas.deploymentmanager.logic.domain.stage.entity.StageEnum;

public interface CiCdService {

    /**
     * Führt einen DeployJob aus.
     *
     * @param jobName   JobName
     * @param imageTag  Image des Deployed wird {imgae}:{tag}
     * @param stageEnum Stage ETW INT PRD
     */
    void deployImage(String jobName, String imageTag, StageEnum stageEnum);

    /**
     * Fürht den übergebenen Job aus.
     * Parameter PUSH = true
     *
     * @param jobName buildJob
     */
    void buildImage(String jobName);
}
