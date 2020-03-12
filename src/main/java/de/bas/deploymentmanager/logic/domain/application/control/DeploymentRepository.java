package de.bas.deploymentmanager.logic.domain.application.control;

import de.bas.deploymentmanager.logic.domain.application.entity.Deployment;

public interface DeploymentRepository {
    Deployment save(Deployment deployment);
}
