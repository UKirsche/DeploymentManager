package de.bas.deploymentmanager.frontend.jsf.project;

import de.bas.deploymentmanager.logic.business.newimage.CreateNewImageFlow;
import de.bas.deploymentmanager.logic.domain.dicd.boundary.CiCdService;
import de.bas.deploymentmanager.logic.domain.project.boundary.ProjectService;
import de.bas.deploymentmanager.logic.domain.project.entity.NewImageModel;
import de.bas.deploymentmanager.logic.domain.project.entity.Project;
import lombok.Getter;
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
public class ProjectTableBean implements Serializable {
    private final Logger log = LoggerFactory.getLogger(this.getClass());


    @Inject
    private ProjectService projectService;

    @Inject
    private CiCdService ciCdService;

    @Inject
    private CreateNewImageFlow createNewImageFlow;

    @Getter
    private List<Project> list;


    @PostConstruct
    public void init() {
        this.list = projectService.getAllProjects();
    }

    public void build(String buildJob) {
        NewImageModel model = new NewImageModel();
        model.setImage("etw-docker-03.bvaetw.de/zus/zus");
        model.setVersion("1.0");
        model.setUser("Tester");
        String newImage = createNewImageFlow.createNewImage("zus", model);
        log.info(newImage);
    }

    public String edit(Project project) {
        return "form.xhtml?faces-redirect=true&id=" + project.getId();
    }


}
