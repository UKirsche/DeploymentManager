package de.bas.deploymentmanager.logic.business.loadproject;

import de.bas.deploymentmanager.logic.domain.project.boundary.ProjectService;
import de.bas.deploymentmanager.logic.domain.project.entity.Project;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class LoadProjectFlowImpl implements LoadProjectFlow {

    private final ProjectService projectService;

    @Inject
    public LoadProjectFlowImpl(ProjectService projectService) {
        this.projectService = projectService;
    }

    @Override
    public ProjectFormModel load(Long id) {
        Project project = projectService.getProject(id);
        project.setImages(projectService.getImages(project.getIdentifier()));

        List<String> allProjects = getAllProjects();

        return ProjectFormModel.builder()
                .project(project)
                .projects(allProjects)
                .selectetSync(project.getImageSync())
                .build();
    }

    /**
     * Holt eine Liste aller Identifier
     *
     * @return Liste
     */
    private List<String> getAllProjects() {
        List<Project> allProjects = projectService.getAllProjects();
        return allProjects.stream().map(Project::getIdentifier).collect(Collectors.toList());
    }

    @Override
    public ProjectFormModel save(ProjectFormModel model) {
        Project project = model.getProject();
        project.setImageSync(model.getSelectetSync());
        project = projectService.save(model.getProject());
        return load(project.getId());
    }
}
