package de.bas.deploymentmanager.logic.domain.project.boundary;

import de.bas.deploymentmanager.logic.domain.project.control.DeploymentRepository;
import de.bas.deploymentmanager.logic.domain.project.control.ImageRepository;
import de.bas.deploymentmanager.logic.domain.project.control.ProjectRepository;
import de.bas.deploymentmanager.logic.domain.project.control.ProjectServiceImpl;
import de.bas.deploymentmanager.logic.domain.project.entity.Image;
import de.bas.deploymentmanager.logic.domain.project.entity.Tag;
import de.bas.deploymentmanager.logic.domain.project.entity.Version;
import de.bas.deploymentmanager.logic.domain.project.entity.exception.ImageDeleteException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProjectServiceDeleteImageTest {

    ProjectService projectService;

    @Mock
    private ProjectRepository projectRepository;
    @Mock
    private ImageRepository imageRepository;
    @Mock
    private DeploymentRepository deploymentRepository;

    @BeforeEach
    void setUp() {
        projectService = new ProjectServiceImpl(projectRepository, imageRepository, deploymentRepository);
    }

    @Test
    void loeschenNeusteVersionnichtMoeglich() {
        //GIVEN
        when(imageRepository.getById(eq(1L))).thenReturn(getProject());
        when(imageRepository.getLastImageOfVersion(anyLong(), any())).thenReturn(Optional.of(getProject()));

        //WHEN
        Assertions.assertThrows(ImageDeleteException.class, () -> projectService.deleteImage(1L));
    }

    @Test
    void loeschenMoeglich() throws ImageDeleteException {
        //GIVEN
        when(imageRepository.getById(eq(1L))).thenReturn(getProject());
        when(imageRepository.getLastImageOfVersion(anyLong(), any())).thenReturn(Optional.of(getAnotherProject()));

        //WHEN
        projectService.deleteImage(1L);

        //THEN
        verify(imageRepository).delete(eq(1L));
    }

    private Image getAnotherProject() {
        Image image = new Image();
        image.setProjectId(1L);
        image.setTag(new Tag(new Version("1.1.1"), 2));
        return image;
    }

    private Image getProject() {
        Image image = new Image();
        image.setId(1L);
        image.setProjectId(1L);
        image.setTag(new Tag(new Version("1.1.1"), 1));
        return image;
    }
}