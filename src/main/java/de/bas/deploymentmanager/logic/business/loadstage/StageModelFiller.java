package de.bas.deploymentmanager.logic.business.loadstage;

import de.bas.deploymentmanager.logic.domain.stage.boundary.StageService;
import de.bas.deploymentmanager.logic.domain.stage.entity.App;
import de.bas.deploymentmanager.logic.domain.stage.entity.Host;
import de.bas.deploymentmanager.logic.domain.stage.entity.Stage;
import de.bas.deploymentmanager.logic.domain.stage.entity.StageEnum;

import javax.ejb.LocalBean;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@LocalBean
public class StageModelFiller {
    private final StageService stageService;

    @Inject
    public StageModelFiller(StageService stageService) {
        this.stageService = stageService;
    }

    StageModel fillStageModel(StageEnum stageEnum) {
        Stage stage = stageService.getStage(stageEnum);
        List<HostModel> hostModels = fillHostModel(stage.getHosts());
        return StageModel.builder().stage(stage).hostModels(hostModels).build();
    }

    List<AppModel> fillAppModel(List<App> apps){
        List<AppModel> appModels = new ArrayList<>();
        for(App app: apps){
            String projektName = stageService.getProjectForApp(app).getName();
            appModels.add(AppModel.builder().app(app).projektName(projektName).build());
        }

        return appModels;
    }

    private List<HostModel> fillHostModel(List<Host> hosts){
        List<HostModel> hostModels = new ArrayList<>();
        for(Host host:hosts){
            List<AppModel> appModelsForHost = fillAppModel(host.getApplications());
            hostModels.add(HostModel.builder().host(host).appModels(appModelsForHost).build());
        }

        return hostModels;
    }
}