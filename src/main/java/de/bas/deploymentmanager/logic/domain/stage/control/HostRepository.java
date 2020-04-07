package de.bas.deploymentmanager.logic.domain.stage.control;

import de.bas.deploymentmanager.logic.domain.stage.entity.Host;

public interface HostRepository {
    Host getHostByUrl(String host);
    Host getHostByName(String hostName);
    Host getHost(Long id);
}
