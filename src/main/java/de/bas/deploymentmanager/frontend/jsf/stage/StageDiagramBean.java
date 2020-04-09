package de.bas.deploymentmanager.frontend.jsf.stage;

import de.bas.deploymentmanager.logic.business.loadpipeline.LoadPipelineFlow;
import de.bas.deploymentmanager.logic.business.loadstage.LoadStageFlow;
import de.bas.deploymentmanager.logic.business.loadstage.StageDiagramModel;
import de.bas.deploymentmanager.logic.domain.stage.entity.App;
import de.bas.deploymentmanager.logic.domain.stage.entity.Host;
import de.bas.deploymentmanager.logic.domain.stage.entity.StageEnum;
import lombok.Getter;
import org.primefaces.model.DefaultOrganigramNode;
import org.primefaces.model.OrganigramNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@ViewScoped
@Named
public class StageDiagramBean implements Serializable {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Getter
    private DefaultOrganigramNode rootNode;

    @Inject
    private LoadStageFlow loadStageFlow;
    private StageDiagramModel model;


    /**
     * Initialisert ebenfalls über den {@link LoadPipelineFlow} alle relevanten Infos für ein Projekt
     */
    @PostConstruct
    public void init() {
        log.info("Lade StageModels");
        model = loadStageFlow.load();

        rootNode = new DefaultOrganigramNode("root", "Stages", null);
        rootNode.setDroppable(false);
        rootNode.setCollapsible(false);

        addETW();
        addINT();
        addPRD();

    }

    /**
     * Fügt ETW-Hosts mit Apps hinzu
     * @return
     */
    private OrganigramNode addETW(){
        return addStage(StageEnum.ETW);
    }

    /**
     * Fügt INT-Hosts mit Apps hinzu
     * @return
     */
    private OrganigramNode addINT(){
        return addStage(StageEnum.INT);
    }

    /**
     * Fügt PRD-Hosts mit Apps hinzu
     */
    private OrganigramNode addPRD(){
        return addStage(StageEnum.PRD);

    }

    private OrganigramNode addStage(StageEnum etw) {
        OrganigramNode stageNodeETW = new DefaultOrganigramNode("stage", etw.name(), rootNode);
        List<Host> hostsETW = model.getStageModels().get(etw.name()).getHosts();
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
