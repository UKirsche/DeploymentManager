package de.bas.deploymentmanager.logic.domain.stage.control;

import de.bas.deploymentmanager.logic.domain.stage.entity.Host;
import de.bas.deploymentmanager.logic.domain.stage.entity.Stage;
import de.bas.deploymentmanager.logic.domain.stage.entity.StageEnum;

public interface StageRepository {
    Stage getStage(StageEnum stage);

    Host getHost(String host);
}
