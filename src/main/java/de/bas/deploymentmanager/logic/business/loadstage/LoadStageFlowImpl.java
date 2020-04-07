package de.bas.deploymentmanager.logic.business.loadstage;

import de.bas.deploymentmanager.logic.domain.project.boundary.ProjectService;
import de.bas.deploymentmanager.logic.domain.project.entity.Project;
import de.bas.deploymentmanager.logic.domain.stage.boundary.StageService;
import de.bas.deploymentmanager.logic.domain.stage.entity.App;
import de.bas.deploymentmanager.logic.domain.stage.entity.StageEnum;
import org.apache.commons.lang.ArrayUtils;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class LoadStageFlowImpl implements LoadStageFlow {

    private final ProjectService projectService;
    private final StageService stageService;
    private List<StageModel> stageModels;

    @Inject
    public LoadStageFlowImpl(ProjectService projectService, StageService stageService) {
        this.projectService = projectService;
        this.stageService = stageService;
    }


    @Override
    public StageDiagramModel load(Long id) {
        Project project = projectService.getProject(id);
        String projectIdentifier = project.getIdentifier();
        projectIdentifier="test";
        stageModels = new ArrayList<>();
        fillStageModels();
        //filterStageModels(projectIdentifier);
        return StageDiagramModel.builder().project(project).stageModels(stageModels).build();
    }

    /**
     * Füllt das StageModel mit allen relevanten Infos zu einer Stage
     */
    private void fillStageModels() {
        stageModels.add(StageModel.builder().stage(stageService.getStage(StageEnum.ETW)).build());
        stageModels.add(StageModel.builder().stage(stageService.getStage(StageEnum.INT)).build());
        stageModels.add(StageModel.builder().stage(stageService.getStage(StageEnum.PRD)).build());
    }

    private void filterStageModels(String projectIdentifier) {
        stageModels
                .forEach(stageModel -> stageModel.getStage().getHosts()
                        .forEach(host -> {
                                    App[] arrayApps = host.getApplications().stream().toArray(App[]::new);
                                    for (App app : arrayApps) {
                                        if (!app.getProjectIdentifier().equals(projectIdentifier)) {
                                            ArrayUtils.removeElement(arrayApps, app);
                                        }
                                    }
                                }

                        ));
    }

}
