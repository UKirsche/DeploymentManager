package de.bas.deploymentmanager.logic.business.loadproject;

import de.bas.deploymentmanager.logic.domain.project.boundary.ProjectService;
import de.bas.deploymentmanager.logic.domain.project.entity.Project;

import javax.ejb.Stateless;
import javax.inject.Inject;

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
        return ProjectFormModel.builder().project(project).build();
    }

    @Override
    public ProjectFormModel save(ProjectFormModel model) {
        Project project = projectService.save(model.getProject());
        return load(project.getId());
    }
}
