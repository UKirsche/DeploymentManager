package de.bas.deploymentmanager.data;

import de.bas.deploymentmanager.logic.domain.stage.control.HostRepository;
import de.bas.deploymentmanager.logic.domain.stage.entity.Host;

import javax.persistence.TypedQuery;

public class HostRepositoryImpl extends AbstractRepository implements HostRepository {


    @Override
    public Host getHostByUrl(String hostUrl) {
        TypedQuery<Host> selectAll = entityManager.createQuery("SELECT host FROM Host host WHERE host.url = :hostUrl", Host.class);
        selectAll.setParameter("hostUrl", hostUrl);
        return selectAll.getSingleResult();
    }

    @Override
    public Host getHostByName(String hostName) {
        TypedQuery<Host> selectAll = entityManager.createQuery("SELECT host FROM Host host WHERE host.name = :hostName", Host.class);
        selectAll.setParameter("hostName", hostName);
        return selectAll.getSingleResult();
    }

    @Override
    public Host getHost(Long hostId) {
        TypedQuery<Host> selectAll = entityManager.createQuery("SELECT host FROM Host host WHERE host.id = :hostId", Host.class);
        selectAll.setParameter("hostId", hostId);
        return selectAll.getSingleResult();
    }
}
