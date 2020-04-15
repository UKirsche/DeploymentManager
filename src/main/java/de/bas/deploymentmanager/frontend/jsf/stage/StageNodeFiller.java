package de.bas.deploymentmanager.frontend.jsf.stage;

import de.bas.deploymentmanager.logic.business.loadstage.AppModel;
import de.bas.deploymentmanager.logic.business.loadstage.HostModel;
import de.bas.deploymentmanager.logic.business.loadstage.StageDiagramModel;
import de.bas.deploymentmanager.logic.domain.stage.entity.App;
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
    private static final String ANZAHL_APPS = "#Apps: ";
    public static final int NAME_MAX_LENGTH = 18;
    public static final String NAME_APPENDIX = "...";
    private final String appTemplate=
            "<!-- Tag -->" +
            "<span class=\"badge\">%s</span><br>" +
            "<!-- Datum  -->" +
            "<small>%s</small><br>" +
            "<!-- Port  -->" +
            "Port: %s <br>" +
            "<span class=\"label label-default\" data-toggle=\"tooltip\" data-placement=\"top\" title=\"%s\">%s</span>";
    private final String stageTemplate =
            "<!-- Stage/Host -->" +
            "<b>%s</b><br>" +
            "<span class=\"label label-default\">"+ANZAHL_APPS+"%s</span>";

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
        List<HostModel> hostsPerStage = model.getStageModels().get(stageEnum.name()).getHostModels();
        OrganigramNode stageNode = new DefaultOrganigramNode(NODE_TYPE_STAGE, stageEnum.name(), startNode);
        addHosts(stageNode, hostsPerStage);
        String stageInfo=String.format(stageTemplate,stageEnum.name(),anzahlAppsOnStage);
        stageNode.setData(stageInfo);
        stageNode.setExpanded(false);
        return stageNode;
    }

    /**
     * Füllt die Host-Infos in die nächste Knotenebende
     * @param parent Stage ETW, INT,PRD
     * @param hostModels
     */
    private void addHosts(OrganigramNode parent, List<HostModel> hostModels){
        anzahlAppsOnStage = 0;
        if(hostModels !=null){
            for(HostModel hostModel: hostModels){
                int numberAppsPerHost = getNumberInList(hostModel.getHost().getApplications());
                anzahlAppsOnStage+=numberAppsPerHost;
                String hostInfo=String.format(stageTemplate,hostModel.getHost().getName(),numberAppsPerHost);
                OrganigramNode hostNode = new DefaultOrganigramNode(NODE_TYPE_HOST, hostInfo, parent);
                hostNode.setExpanded(false);
                addApps(hostNode, hostModel.getAppModels());
            }
        }
    }

    private int getNumberInList(List<?> liste) {
        return liste !=null?liste.size():0;
    }

    /**
     * Füllt die Appinfos in die Knoten
     * @param parent Host
     * @param appModels
     */
    private void addApps(OrganigramNode parent, List<AppModel> appModels){
        if(appModels !=null){
            for(AppModel appModel: appModels){
                String appInfo = String.format(appTemplate, getTagFromImage(appModel.getApp().getImage()),
                        formatCreatedTime(appModel.getApp().getCreateTime()),
                        appModel.getApp().getPort(),
                        appModel.getApp().getName(),
                        formatMaxLength(appModel.getApp().getName()));
                new DefaultOrganigramNode(NODE_TYPE_APP, appInfo, parent);
            }
        }
    }

    private String formatMaxLength(String name){
        if(name.length()> NAME_MAX_LENGTH){
            name=name.substring(0,NAME_MAX_LENGTH-1);
            name+= NAME_APPENDIX;
        }

        return name;
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
    private String getTagFromImage(String appImage){
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
     * Kürzt die Datumsanzeige für dd.MM.yyyy
     * @param createdTime
     * @return
     */
    private String formatCreatedTime(LocalDateTime createdTime){
        return createdTime.format(shortDateTime);
    }


}