package de.bas.deploymentmanager.data;

import de.bas.deploymentmanager.logic.domain.project.boundary.ProjectService;
import de.bas.deploymentmanager.logic.domain.project.control.ProjectServiceImpl;
import de.bas.deploymentmanager.logic.domain.project.entity.exception.ImageDeleteException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProjectServiceIT extends AbstarctRepositoryIT {

    ProjectService projectService;

    @BeforeEach
    void setUp() {
        ImageRepositoryImpl imageRepository = new ImageRepositoryImpl();
        imageRepository.entityManager = em;
        projectService = new ProjectServiceImpl(null, imageRepository, null);
    }

    @Test
    void aktuellenBuildLoeschen() {
        Assertions.assertThrows(ImageDeleteException.class, () -> projectService.deleteImage(1L));
    }
}
