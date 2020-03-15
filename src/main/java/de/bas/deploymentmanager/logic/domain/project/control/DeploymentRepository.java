package de.bas.deploymentmanager.logic.domain.project.control;

import de.bas.deploymentmanager.logic.domain.project.entity.Deployment;

public interface DeploymentRepository {
    Deployment save(Deployment deployment);
}
