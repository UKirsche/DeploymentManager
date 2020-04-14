package de.bas.deploymentmanager.frontend.jsf.project;

import de.bas.deploymentmanager.logic.business.loadproject.LoadProjectFlow;
import de.bas.deploymentmanager.logic.business.loadproject.ProjectFormModel;
import de.bas.deploymentmanager.logic.domain.project.entity.Image;
import de.bas.deploymentmanager.logic.domain.project.entity.ImageSync;
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
import java.util.stream.Collectors;

@ViewScoped
@Named
public class ProjectFormBean implements Serializable {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final LoadProjectFlow loadProjectFlow;

    @Getter
    private ProjectFormModel model;


    public Project getProject() {
        return this.model.getProject();
    }

    @Inject
    public ProjectFormBean(LoadProjectFlow loadProjectFlow) {
        this.loadProjectFlow = loadProjectFlow;
    }


    @PostConstruct
    public void init() {
        Map<String, String> params = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap();
        String id = params.get("id");
        if (id != null) {
            this.model = loadProjectFlow.load(Long.valueOf(id));
        } else {
            this.model = ProjectFormModel.builder().project(new Project()).selectetSync(new ImageSync(null)).build();
        }
    }

    public void save() {
        model = loadProjectFlow.save(model);
    }

    public String getDeployments(Image image) {
        if (image.getDeployments() != null) {
            return image.getDeployments().stream().map(deployment -> deployment.getStage().name()).collect(Collectors.joining(" : "));
        }
        return null;
    }
}
