package de.bas.deploymentmanager.data;

import de.bas.deploymentmanager.logic.domain.application.control.DeploymentRepository;
import de.bas.deploymentmanager.logic.domain.application.entity.Deployment;

public class DeploymentRepositoryImpl extends AbstractRepository implements DeploymentRepository {
    @Override
    public Deployment save(Deployment deployment) {
        Deployment saved = entityManager.merge(deployment);
        entityManager.flush();
        return saved;
    }
}
