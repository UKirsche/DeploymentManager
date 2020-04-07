package de.bas.deploymentmanager.logic.business.loadstage;

import de.bas.deploymentmanager.logic.domain.project.boundary.ProjectService;
import de.bas.deploymentmanager.logic.domain.project.entity.Project;
import de.bas.deploymentmanager.logic.domain.stage.boundary.HostService;
import de.bas.deploymentmanager.logic.domain.stage.boundary.StageService;
import de.bas.deploymentmanager.logic.domain.stage.entity.App;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class LoadStageFlowImpl implements LoadStageFlow {

    private final ProjectService projectService;
    private final StageService stageService;
    private final HostService hostService;

    @Inject
    public LoadStageFlowImpl(ProjectService projectService, StageService stageService, HostService hostService) {
        this.projectService = projectService;
        this.stageService = stageService;
        this.hostService = hostService;
    }


    @Override
    public StageDiagramModel load(Long id) {
        Project project = projectService.getProject(id);
        String projectIdentifier = project.getIdentifier();
        List<App> deployedApps = stageService.getAppsForProject(projectIdentifier);
        List<AppStageModel> appStageModels = getAppStageModels(deployedApps);
        fillAppStageModelsWithHost(appStageModels);

        return StageDiagramModel.builder().project(project).appStageModels(appStageModels).build();

    }

    private List<AppStageModel> getAppStageModels(List<App> deployedApps) {
        List<AppStageModel> deployedAppStageModels = deployedApps.stream()
                    .map(app -> AppStageModel.builder()
                            .app(app)
                            .stage(stageService.getStage(app.getStage()))
                            .build())
                    .collect(Collectors.toList());

        return  deployedAppStageModels;

    }

    private void fillAppStageModelsWithHost(List<AppStageModel> appStageModels){
        appStageModels.forEach(appStageModel -> appStageModel.setHost(hostService.getHost(appStageModel.getApp().getHostId())));
    }
}
