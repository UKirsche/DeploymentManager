package de.bas.deploymentmanager.logic.business.loadstage;

import de.bas.deploymentmanager.logic.domain.project.boundary.ProjectService;
import de.bas.deploymentmanager.logic.domain.stage.boundary.StageService;
import de.bas.deploymentmanager.logic.domain.stage.entity.App;
import de.bas.deploymentmanager.logic.domain.stage.entity.Host;
import de.bas.deploymentmanager.logic.domain.stage.entity.Stage;
import de.bas.deploymentmanager.logic.domain.stage.entity.StageEnum;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Befüllt die StageModels zur Darstellung der Stages-Gesamtübersicht
 */
@Stateless
public class LoadStageFlowImpl implements LoadStageFlow {

    private final ProjectService projectService;
    private final StageService stageService;
    private HashMap<String, StageModel> stageDiagramModel;

    @Inject
    public LoadStageFlowImpl(ProjectService projectService, StageService stageService) {
        this.projectService = projectService;
        this.stageService = stageService;
    }


    @Override
    public StageDiagramModel load() {
        stageDiagramModel = new HashMap<>();
        fillStageDiagramModel();
        return StageDiagramModel.builder().stageModels(stageDiagramModel).build();
    }


    private List<HostModel> fillHostModel(List<Host> hosts){
        List<HostModel> hostModels = new ArrayList<>();
        for(Host host:hosts){
            List<AppModel> appModelsForHost = fillAppModel(host.getApplications());
            hostModels.add(HostModel.builder().host(host).appModels(appModelsForHost).build());
        }

        return hostModels;
    }

    private List<AppModel> fillAppModel(List<App> apps){
        List<AppModel> appModels = new ArrayList<>();
        for(App app: apps){
            String projektName = stageService.getProjectForApp(app).getName();
            appModels.add(AppModel.builder().app(app).projektName(projektName).build());
        }

        return appModels;
    }

    private StageModel fillStageModel(StageEnum stageEnum) {
        Stage stage = stageService.getStage(stageEnum);
        List<HostModel> hostModels = fillHostModel(stage.getHosts());
        return StageModel.builder().stage(stage).hostModels(hostModels).build();
    }

    private void fillStageDiagramModel() {
        stageDiagramModel.put(StageEnum.ETW.name(), fillStageModel(StageEnum.ETW));
        stageDiagramModel.put(StageEnum.INT.name(), fillStageModel(StageEnum.INT));
        stageDiagramModel.put(StageEnum.PRD.name(), fillStageModel(StageEnum.PRD));
    }




}
