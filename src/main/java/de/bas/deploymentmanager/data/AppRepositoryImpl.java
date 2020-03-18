package de.bas.deploymentmanager.data;

import de.bas.deploymentmanager.logic.domain.stage.control.AppRepository;
import de.bas.deploymentmanager.logic.domain.stage.entity.App;

public class AppRepositoryImpl extends AbstractRepository implements AppRepository {
    @Override
    public App save(App app) {
        App saved = entityManager.merge(app);
        entityManager.flush();
        return saved;
    }

    @Override
    public void delete(App app) {
        entityManager.remove(app);
    }
}
