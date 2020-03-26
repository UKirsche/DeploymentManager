package de.bas.deploymentmanager.data;

import de.bas.deploymentmanager.logic.domain.project.control.ProjectRepository;
import de.bas.deploymentmanager.logic.domain.project.entity.Project;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class ProjectRepositoryImpl extends AbstractRepository implements ProjectRepository {

    @Override
    public List<Project> getAllProjects() {
        TypedQuery<Project> selectAll = entityManager.createQuery("SELECT a FROM Project a", Project.class);
        return selectAll.getResultList();
    }

    @Override
    public Project getByIfentifier(String identifier) throws NoResultException {
        TypedQuery<Project> selectAll = entityManager.createQuery("SELECT a FROM Project a WHERE a.identifier =:identifier", Project.class);
        return selectAll.setParameter("identifier", identifier).getSingleResult();
    }

    @Override
    public Project save(Project project) {
        Project saved = entityManager.merge(project);
        entityManager.flush();
        return saved;

    }

    @Override
    public Project getById(Long id) {
        return entityManager.find(Project.class, id);
    }
}
