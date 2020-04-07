package de.bas.deploymentmanager.frontend.jsf.stage;

import de.bas.deploymentmanager.logic.business.loadpipeline.LoadPipelineFlow;
import de.bas.deploymentmanager.logic.business.loadstage.LoadStageFlow;
import de.bas.deploymentmanager.logic.business.loadstage.StageDiagramModel;
import de.bas.deploymentmanager.logic.domain.project.entity.Project;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Map;

@ViewScoped
@Named
public class StageDiagramBean implements Serializable {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Getter
    private Project project;

    @Inject
    private LoadStageFlow loadStageFlow;

    private StageDiagramModel model;


    /**
     * Initialisert ebenfalls über den {@link LoadPipelineFlow} alle relevanten Infos für ein Projekt
     */
    @PostConstruct
    public void init() {
        Map<String, String> params = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap();
        String projectId = params.get("id");
        model = loadStageFlow.load(Long.valueOf(projectId));
    }

}
