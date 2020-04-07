package de.bas.deploymentmanager.frontend.jsf.stage;

import de.bas.deploymentmanager.logic.business.loadpipeline.LoadPipelineFlow;
import de.bas.deploymentmanager.logic.business.loadpipeline.PipelineFormModel;
import de.bas.deploymentmanager.logic.business.loadpipeline.ProjectStageModel;
import de.bas.deploymentmanager.logic.domain.project.entity.Image;
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
import java.util.List;
import java.util.Map;

@ViewScoped
@Named
public class StageDiagramBean implements Serializable {
    private final Logger log = LoggerFactory.getLogger(this.getClass());


    @Getter
    private List<Image> images;
    @Getter
    private Project project;
    @Getter
    private List<ProjectStageModel> appsDeployed;

    @Inject
    private LoadPipelineFlow loadProjectFlow;

    private PipelineFormModel model;


    /**
     * Initialisert ebenfalls über den {@link LoadPipelineFlow} alle relevanten Infos für ein Projekt
     */
    @PostConstruct
    public void init() {
        Map<String, String> params = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap();
        String id = params.get("id");
        model = loadProjectFlow.load(Long.valueOf(id));
        extractFieldsFromModel();
    }

    /**
     * Lädt die alle Informationen für die Grafik in die entsprechenden Fields
     */
    private void extractFieldsFromModel() {
        images = model.getProject().getImages();
        project = model.getProject();
        appsDeployed = model.getDeployedOn();
    }

}
