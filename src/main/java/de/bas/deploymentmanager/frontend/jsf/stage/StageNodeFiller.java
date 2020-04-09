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

    private static final String NODE_TYPE_STAGE="stage";
    private static final String NODE_TYPE_HOST="host";
    private static final String NODE_TYPE_APP="app";
    private static final String LINE_BREAK = "<br>";
    private static final String CREATED = "Erstellt: ";
    private static final String ANZAHL_APPS = "#Apps: ";
    private int anzahlAppsOnStage;

    public StageNodeFiller(){
    }

    /**
     * Startet das Befüllen der Stage-Knoten
     * @param stageEnum ETW, INT, PRD
     * @param startNode oberster Knoten
     * @param model Model mit Stage-Infos
     * @return
     */
    public OrganigramNode addStage(StageEnum stageEnum, DefaultOrganigramNode startNode, StageDiagramModel model) {
        List<Host> hostsPerStage = model.getStageModels().get(stageEnum.name()).getHosts();
        OrganigramNode stageNode = new DefaultOrganigramNode(NODE_TYPE_STAGE, stageEnum.name(), startNode);
        addHosts(stageNode, hostsPerStage);
        String stageInfo=stageEnum.name()+LINE_BREAK+ANZAHL_APPS+anzahlAppsOnStage;
        stageNode.setData(stageInfo);
        stageNode.setExpanded(false);
        return stageNode;
    }

    /**
     * Füllt die Host-Infos in die nächste Knotenebende
     * @param parent Stage ETW, INT,PRD
     * @param hosts
     */
    private void addHosts(OrganigramNode parent, List<Host> hosts){
        anzahlAppsOnStage = 0;
        if(hosts!=null){
            for(Host host: hosts){
                int numberAppsPerHost = getNumberInList(host.getApplications());
                anzahlAppsOnStage+=numberAppsPerHost;
                String hostInfo = host.getName() + LINE_BREAK + ANZAHL_APPS + numberAppsPerHost;
                OrganigramNode hostNode = new DefaultOrganigramNode(NODE_TYPE_HOST, hostInfo, parent);
                hostNode.setExpanded(false);
                addApps(hostNode, host.getApplications());
            }
        }
    }

    private int getNumberInList(List<?> liste) {
        return liste !=null?liste.size():0;
    }

    /**
     * Füllt die Appinfos in die Konsten
     * @param parent Host
     * @param apps
     */
    private void addApps(OrganigramNode parent, List<App> apps){
        if(apps!=null){
            for(App app: apps){
                String appInfo = app.getImage() + LINE_BREAK + CREATED + app.getCreateTime();
                new DefaultOrganigramNode(NODE_TYPE_APP, appInfo, parent);
            }
        }
    }
}