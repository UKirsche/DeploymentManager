package de.bas.deploymentmanager.logic.domain.stage.control;

import de.bas.deploymentmanager.logic.domain.stage.entity.App;

public interface AppRepository {
    App save(App app);

    void delete(App app);
}
