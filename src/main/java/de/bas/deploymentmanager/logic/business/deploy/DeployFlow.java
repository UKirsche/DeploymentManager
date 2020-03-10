package de.bas.deploymentmanager.logic.business.deploy;

import de.bas.deploymentmanager.logic.domain.stage.entity.StageEnum;

public interface DeployFlow {
    void imageDeployed(String identifier, StageEnum stage, String tag, String host);
}
