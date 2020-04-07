package de.bas.deploymentmanager.frontend.jsf.stage;

import de.bas.deploymentmanager.logic.domain.project.boundary.ProjectService;
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
public class StageTableBean implements Serializable {
    private final Logger log = LoggerFactory.getLogger(this.getClass());


    @Inject
    private ProjectService projectService;


    @Getter
    private List<Project> list;


    @PostConstruct
    public void init() {
        this.list = projectService.getAllProjects();
    }


    public String edit(Project project) {
        return "diagram.xhtml?faces-redirect=true&id=" + project.getId();
    }


}

