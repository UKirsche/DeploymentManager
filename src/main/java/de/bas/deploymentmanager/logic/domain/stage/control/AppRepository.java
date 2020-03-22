package de.bas.deploymentmanager.logic.domain.stage.control;

import de.bas.deploymentmanager.logic.domain.stage.entity.App;

import java.util.List;

public interface AppRepository {
    App save(App app);

    void delete(App app);

    List<App> getByProjectIdentifier(String identifier);
}
