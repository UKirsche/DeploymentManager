package de.bas.deploymentmanager.data;

import de.bas.deploymentmanager.logic.domain.stage.control.StageRepository;
import de.bas.deploymentmanager.logic.domain.stage.entity.Host;
import de.bas.deploymentmanager.logic.domain.stage.entity.Stage;
import de.bas.deploymentmanager.logic.domain.stage.entity.StageEnum;

import javax.persistence.TypedQuery;

public class StageRepositoryImpl extends AbstractRepository implements StageRepository {
    @Override
    public Stage getStage(StageEnum stage) {
        TypedQuery<Stage> selectAll = entityManager.createQuery("SELECT stage FROM Stage stage WHERE stage.name = :name", Stage.class);
        selectAll.setParameter("name", stage);
        return selectAll.getSingleResult();
    }

    @Override
    public Host getHost(String host) {
        TypedQuery<Host> selectAll = entityManager.createQuery("SELECT host FROM Host host WHERE host.url = :host", Host.class);
        selectAll.setParameter("host", host);
        return selectAll.getSingleResult();
    }

    @Override
    public Host getHostByName(String hostName) {
        TypedQuery<Host> selectAll = entityManager.createQuery("SELECT host FROM Host host WHERE host.name = :hostName", Host.class);
        selectAll.setParameter("hostName", hostName);
        return selectAll.getSingleResult();
    }
}
