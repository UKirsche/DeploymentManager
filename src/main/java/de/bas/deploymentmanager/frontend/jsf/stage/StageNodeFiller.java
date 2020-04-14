package de.bas.deploymentmanager.frontend.jsf.stage;

import de.bas.deploymentmanager.logic.business.loadstage.StageDiagramModel;
import de.bas.deploymentmanager.logic.domain.stage.entity.App;
import de.bas.deploymentmanager.logic.domain.stage.entity.Host;
import de.bas.deploymentmanager.logic.domain.stage.entity.StageEnum;
import org.primefaces.model.DefaultOrganigramNode;
import org.primefaces.model.OrganigramNode;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@LocalBean
public class StageNodeFiller implements Serializable {

    private static final String NODE_TYPE_STAGE="stage";
    private static final String NODE_TYPE_HOST="host";
    private static final String NODE_TYPE_APP="app";
    private static final String LINE_BREAK = "<br>";
    private static final String PORT = "Port: ";
    private static final String ANZAHL_APPS = "#Apps: ";
    private int anzahlAppsOnStage;
    private DateTimeFormatter shortDateTime;

    public StageNodeFiller(){
    }

    @PostConstruct
    public void init(){
        shortDateTime = DateTimeFormatter.ofPattern("dd.MM.yyyy");
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
                String appInfo = shortenAppImageName(app.getImage()) + LINE_BREAK + formatCreatedTime(app.getCreateTime()) + LINE_BREAK + PORT + app.getPort();
                new DefaultOrganigramNode(NODE_TYPE_APP, appInfo, parent);
            }
        }
    }

    /**
     * Der Fully Qualified Name einer App, der unter Image in der {@link App}-Entity gespeichert ist, hat folgendes Aussehen:
     * <ul>
     *     <li>etw-docker-03.bvaetw.de/zustaendigestelle/zustaendigestelle:1.7.5-36</li>
     *     <li>etw-docker-03.bvaetw.de/dmsservice/dmsservice:1.1.0-3</li>
     * </ul>
     *
     * Von diesem Namen wird nur der letzte Teil zurück gegeben.<br>
     * Beispiel:<br>
     * <b>dmsservice:1.1.0-3</b>
     *
     * @param appImage FullyQualified ImageName
     * @return shortened ImageName
     */
    private String shortenAppImageName(String appImage){
        String returnImage="";
        String[] addressSplitted = appImage.split("/");
        if(addressSplitted.length>0){
            returnImage = addressSplitted[addressSplitted.length-1]; //Letztes Element
            String[] tagSplitted =  returnImage.split(":");
            if(tagSplitted.length>0){
                returnImage= tagSplitted[0] + LINE_BREAK + tagSplitted[1];
            }
        }

        return returnImage;
    }


    /**
     * Kürzt die Datumsanzeige
     * @param createdTime
     * @return
     */
    private String formatCreatedTime(LocalDateTime createdTime){
        return createdTime.format(shortDateTime);
    }


}