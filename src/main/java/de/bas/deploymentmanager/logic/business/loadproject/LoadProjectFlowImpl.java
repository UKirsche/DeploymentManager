package de.bas.deploymentmanager.logic.business.loadproject;

import de.bas.deploymentmanager.logic.domain.project.boundary.ProjectService;
import de.bas.deploymentmanager.logic.domain.project.entity.Project;
import de.bas.deploymentmanager.logic.domain.stage.boundary.StageService;
import de.bas.deploymentmanager.logic.domain.stage.entity.App;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class LoadProjectFlowImpl implements LoadProjectFlow {

    private final ProjectService projectService;
    private final StageService stageService;

    @Inject
    public LoadProjectFlowImpl(ProjectService projectService, StageService stageService) {
        this.projectService = projectService;
        this.stageService = stageService;
    }

    @Override
    public ProjectFormModel load(Long id) {
        Project project = projectService.getProject(id);
        project.setImages(projectService.getImages(project.getIdentifier()));
        List<ProjectStageModel> deployedOn = getDeployInfo(project.getIdentifier());

        return ProjectFormModel.builder().project(project).deployedOn(deployedOn).build();
    }

    private List<ProjectStageModel> getDeployInfo(String identifier) {
        List<App> deployedApps = stageService.getAppsForProject(identifier);
        List<ProjectStageModel> projectStageModels = deployedApps.stream()
                .map(app -> ProjectStageModel.builder()
                        .image(app.getImage())
                        .stage(app.getStage())
                        .build())
                .collect(Collectors.toList());
        return projectStageModels;
    }

    @Override
    public ProjectFormModel save(ProjectFormModel model) {
        Project project = projectService.save(model.getProject());
        return load(project.getId());
    }
}
