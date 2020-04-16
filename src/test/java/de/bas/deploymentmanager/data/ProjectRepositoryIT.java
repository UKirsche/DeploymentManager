package de.bas.deploymentmanager.data;

import de.bas.deploymentmanager.logic.domain.project.entity.Project;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.NoResultException;
import java.util.List;


class ProjectRepositoryIT extends AbstarctRepositoryIT {

    ProjectRepositoryImpl repository;

    @BeforeEach
    void setUp() {
        repository = new ProjectRepositoryImpl();
        repository.entityManager = em;
    }

    @Test
    void projekteOhenImagesLaden() {
        List<Project> allProjects = repository.getAllProjects();
        Assertions.assertEquals(1, allProjects.size());
        Assertions.assertNull(allProjects.get(0).getImages());
    }

    @Test
    void projektOhneImageLaden() {
        Project manager = repository.getByIfentifier("manager");
        Assertions.assertNull(manager.getImages());
    }

    @Test
    void sucheAlleProjekte() {
        List<Project> allProjects = repository.getAllProjects();
        Assertions.assertEquals(1, allProjects.size());

        Project project = new Project();
        project.setIdentifier("test");
        tx.begin();
        repository.save(project);
        tx.commit();

        allProjects = repository.getAllProjects();
        Assertions.assertEquals(2, allProjects.size());
    }

    @Test
    void sucheProjekteNachIdentifier() {
        Project manager = repository.getByIfentifier("manager");
        Assertions.assertEquals("manager", manager.getIdentifier());
    }

    @Test
    void sucheOhneErgebnis() {
        Assertions.assertThrows(NoResultException.class, () -> repository.getByIfentifier("notFound"));
    }

    @Test
    void findByIdOhneErgebnis() {
        Assertions.assertThrows(NoResultException.class, () -> repository.getById(30L));
    }

    @Test
    void neuesProjektAnlegen() {
        Project project = new Project();
        project.setIdentifier("test");
        tx.begin();
        repository.save(project);
        tx.commit();

        Project manager = repository.getByIfentifier("test");
        Assertions.assertEquals("test", manager.getIdentifier());
    }
}