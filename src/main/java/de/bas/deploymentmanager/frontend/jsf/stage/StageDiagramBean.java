package de.bas.deploymentmanager.frontend.jsf.stage;

import de.bas.deploymentmanager.logic.business.loadpipeline.LoadPipelineFlow;
import de.bas.deploymentmanager.logic.business.loadstage.LoadStageFlow;
import de.bas.deploymentmanager.logic.business.loadstage.StageDiagramModel;
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

@ViewScoped
@Named
public class StageDiagramBean implements Serializable {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Getter
    private DefaultOrganigramNode rootNode;

    @Inject
    private StageNodeFiller stageNodeFiller;

    @Inject
    private LoadStageFlow loadStageFlow;

    /**
     * Initialisert ebenfalls über den {@link LoadPipelineFlow} alle relevanten Infos für ein Projekt
     */
    @PostConstruct
    public void init() {
        log.info("Lade StageModels");
        StageDiagramModel model = loadStageFlow.load();
        DefaultOrganigramNode startNode = initializeDiagramRootNode();
        fillStageNodes(startNode, model );
        rootNode=startNode;
    }

    /**
     * Initialisiert den Startknoten für das Diagram
     * @return
     */
    private DefaultOrganigramNode initializeDiagramRootNode() {
        DefaultOrganigramNode startNode = new DefaultOrganigramNode("root", "Stages", null);
        startNode.setDroppable(false);
        startNode.setCollapsible(false);

        return startNode;
    }


    /**
     *
     *
     * @param startNode
     * @param model
     */
    private void fillStageNodes(DefaultOrganigramNode startNode, StageDiagramModel model) {
        addETW(startNode, model );
        addINT(startNode, model );
        addPRD(startNode, model );
    }

    /**
     * Fügt ETW-Hosts mit Apps hinzu
     * @return
     * @param startNode
     * @param model
     */
    private OrganigramNode addETW(DefaultOrganigramNode startNode, StageDiagramModel model){
        return stageNodeFiller.addStage(StageEnum.ETW, startNode, model);
    }

    /**
     * Fügt INT-Hosts mit Apps hinzu
     * @return
     * @param startNode
     * @param model
     */
    private OrganigramNode addINT(DefaultOrganigramNode startNode, StageDiagramModel model){
        return stageNodeFiller.addStage(StageEnum.INT, startNode, model);
    }

    /**
     * Fügt PRD-Hosts mit Apps hinzu
     */
    private OrganigramNode addPRD(DefaultOrganigramNode startNode, StageDiagramModel model){
        return stageNodeFiller.addStage(StageEnum.PRD, startNode, model);

    }


}
