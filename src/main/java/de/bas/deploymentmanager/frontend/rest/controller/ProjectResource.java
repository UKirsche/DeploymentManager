package de.bas.deploymentmanager.frontend.rest.controller;

import de.bas.deploymentmanager.logic.domain.dicd.boundary.CiCdService;
import de.bas.deploymentmanager.logic.domain.project.boundary.ProjectService;
import de.bas.deploymentmanager.logic.domain.project.entity.Project;
import de.bas.deploymentmanager.logic.domain.stage.entity.StageEnum;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/projects")
public class ProjectResource {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Inject
    private ProjectService projectService;

    @Inject
    private CiCdService ciCdService;

    @GET
    public List<Project> getAllProjects() {
        log.debug("Lade alle Projects");
        ciCdService.deployImage("pipe_zustaendigestelle", "imageName", StageEnum.ETW);
        return projectService.getAllProjects();
    }

    @POST
    public Project createNewProject(Project model) {
        return projectService.createNewProject(model);
    }

    @GET
    @Path("/{application}")
    @Operation(summary = "test")
    public Project getApplication(@PathParam("application") String application) {
        log.debug("Lade Application {}", application);
        return projectService.getProject(application);
    }

}
