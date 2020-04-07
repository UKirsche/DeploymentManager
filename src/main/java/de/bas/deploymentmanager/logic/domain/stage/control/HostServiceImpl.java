package de.bas.deploymentmanager.logic.domain.stage.control;

import de.bas.deploymentmanager.logic.domain.stage.boundary.HostService;
import de.bas.deploymentmanager.logic.domain.stage.entity.Host;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

public class HostServiceImpl implements HostService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final HostRepository hostRepository;

    @Inject
    public HostServiceImpl(HostRepository hostRepository) {
        this.hostRepository = hostRepository;
    }

    @Override
    public Host getHost(Long id) {
        return hostRepository.getHost(id);
    }
}
