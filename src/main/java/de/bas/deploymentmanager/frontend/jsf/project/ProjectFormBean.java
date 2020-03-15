package de.bas.deploymentmanager.frontend.jsf.project;

import de.bas.deploymentmanager.logic.business.loadproject.LoadProjectFlow;
import de.bas.deploymentmanager.logic.business.loadproject.ProjectFormModel;
import de.bas.deploymentmanager.logic.business.newimage.CreateNewImageFlow;
import de.bas.deploymentmanager.logic.domain.project.entity.NewImageModel;
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
public class ProjectFormBean implements Serializable {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final LoadProjectFlow loadProjectFlow;
    private final CreateNewImageFlow createNewImageFlow;

    @Getter
    private ProjectFormModel model;

    public Project getProject() {
        return this.model.getProject();
    }

    @Inject
    public ProjectFormBean(LoadProjectFlow loadProjectFlow, CreateNewImageFlow createNewImageFlow) {
        this.loadProjectFlow = loadProjectFlow;
        this.createNewImageFlow = createNewImageFlow;
    }

    public void createNewImage() {
        NewImageModel model = new NewImageModel();
        model.setImage("etw-docker-03.bvaetw.de/zus/zus");
        model.setVersion("1.0");
        model.setUser("Tester");
        String newImage = createNewImageFlow.createNewImage("zus", model);
        log.info(newImage);
        this.model = loadProjectFlow.load(getProject().getId());
    }


    @PostConstruct
    public void init() {
        Map<String, String> params = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap();
        String id = params.get("id");
        this.model = loadProjectFlow.load(Long.valueOf(id));
    }
}
