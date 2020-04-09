package de.bas.deploymentmanager.frontend.jsf.stage;

import de.bas.deploymentmanager.logic.business.loadstage.StageDiagramModel;
import de.bas.deploymentmanager.logic.domain.stage.entity.App;
import de.bas.deploymentmanager.logic.domain.stage.entity.Host;
import de.bas.deploymentmanager.logic.domain.stage.entity.StageEnum;
import org.primefaces.model.DefaultOrganigramNode;
import org.primefaces.model.OrganigramNode;

import javax.ejb.LocalBean;
import java.io.Serializable;
import java.util.List;

@LocalBean
public class StageNodeFiller implements Serializable {

    public StageNodeFiller(){
    }

    OrganigramNode addStage(StageEnum stageEnum, DefaultOrganigramNode startNode, StageDiagramModel model) {
        OrganigramNode stageNodeETW = new DefaultOrganigramNode("stage", stageEnum.name(), startNode);
        List<Host> hostsETW = model.getStageModels().get(stageEnum.name()).getHosts();
        addHosts(stageNodeETW, hostsETW);
        return stageNodeETW;
    }

    private void addHosts(OrganigramNode parent, List<Host> hosts){
        if(hosts!=null){
            for(Host host: hosts){
                OrganigramNode hostETW = new DefaultOrganigramNode("host", host.getName(), parent);
                addApps(hostETW, host.getApplications());
            }
        }
    }

    private void addApps(OrganigramNode parent, List<App> apps){
        if(apps!=null){
            for(App app: apps){
                new DefaultOrganigramNode("app", app.getImage(), parent);
            }
        }
    }
}