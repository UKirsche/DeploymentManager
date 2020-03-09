package de.bas.deploymentmanager.data;

import de.bas.deploymentmanager.logic.domain.application.control.ApplicationRepository;
import de.bas.deploymentmanager.logic.domain.application.entity.Application;

import javax.persistence.TypedQuery;
import java.util.List;

public class ApplicationRepositoryImpl extends AbstractRepository implements ApplicationRepository {

    @Override
    public List<Application> getAllApplications() {
        TypedQuery<Application> selectAll = entityManager.createQuery("SELECT a FROM Application a", Application.class);
        return selectAll.getResultList();
    }

    @Override
    public Application getByIfentifier(String identifier) {
        TypedQuery<Application> selectAll = entityManager.createQuery("SELECT a FROM Application a WHERE a.identifier =:identifier", Application.class);
        return selectAll.setParameter("identifier", identifier).getSingleResult();
    }

    @Override
    public Application save(Application application) {
        Application saved = entityManager.merge(application);
        entityManager.flush();
        return saved;

    }
}
