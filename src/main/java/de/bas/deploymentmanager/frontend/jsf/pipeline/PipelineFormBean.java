package de.bas.deploymentmanager.frontend.jsf.pipeline;

import de.bas.deploymentmanager.logic.business.build.BuildFlow;
import de.bas.deploymentmanager.logic.business.deploy.DeployFlow;
import de.bas.deploymentmanager.logic.business.loadproject.LoadProjectFlow;
import de.bas.deploymentmanager.logic.business.loadproject.ProjectFormModel;
import de.bas.deploymentmanager.logic.domain.project.entity.Image;
import de.bas.deploymentmanager.logic.domain.project.entity.Project;
import de.bas.deploymentmanager.logic.domain.stage.entity.StageEnum;
import lombok.Getter;

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
public class PipelineFormBean implements Serializable {

    @Getter
    private List<Image> images;
    private Project project;

    @Inject
    private LoadProjectFlow loadProjectFlow;

    @Inject
    private DeployFlow deployFlow;

    @Inject
    private BuildFlow buildFlow;

    @PostConstruct
    public void init() {
        Map<String, String> params = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap();
        String id = params.get("id");
        ProjectFormModel model = loadProjectFlow.load(Long.valueOf(id));
        images = model.getProject().getImages();
        project = model.getProject();
    }

    public void deploy(Image image, StageEnum stage) {
        deployFlow.deployImage(project.getIdentifier(), stage, image.getTag() );
    }

    public void build() {
        buildFlow.build(project.getId());
    }


}
