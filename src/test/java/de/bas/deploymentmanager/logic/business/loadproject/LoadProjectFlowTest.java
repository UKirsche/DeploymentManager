package de.bas.deploymentmanager.logic.business.loadproject;

import de.bas.deploymentmanager.logic.domain.project.boundary.ProjectService;
import de.bas.deploymentmanager.logic.domain.project.entity.Project;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoadProjectFlowTest {

    private static final String IDENTIFIER = "identifier";
    LoadProjectFlow loadProjectFlow;

    @Mock
    ProjectService projectService;

    @BeforeEach
    void setUp() {
        loadProjectFlow = new LoadProjectFlowImpl(projectService);
    }

    @Test
    void load() {
        //GIVEN
        when(projectService.getProject(anyLong())).thenReturn(generateProject());
        when(projectService.getImages(eq(IDENTIFIER))).thenReturn(new ArrayList<>());
        when(projectService.getAllProjects()).thenReturn(allProjects());

        //WHEN
        ProjectFormModel model = loadProjectFlow.load(1L);

        //THEN
        Assertions.assertNotNull(model.getProject());
        Assertions.assertNotNull(model.getProject().getImages());
        Assertions.assertFalse(model.getSelectetSync().isAktiv());
        Assertions.assertFalse(model.getProjects().isEmpty());
    }

    private List<Project> allProjects() {
        Project p1 = new Project();
        p1.setIdentifier("Project1");

        Project p2 = new Project();
        p1.setIdentifier("Project2");
        return Arrays.asList(p1, p2);

    }

    private Project generateProject() {
        Project project = new Project();
        project.setIdentifier(IDENTIFIER);
        return project;
    }
}