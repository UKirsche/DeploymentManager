package de.bas.deploymentmanager.frontend.rest.controller;

import de.bas.deploymentmanager.logic.domain.project.boundary.ProjectService;
import de.bas.deploymentmanager.logic.domain.project.entity.Image;
import de.bas.deploymentmanager.logic.domain.project.entity.NewImageModel;
import de.bas.deploymentmanager.logic.domain.project.entity.Project;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/projects/{project}/images")
public class ImageResource {

    @Inject
    private ProjectService projectService;

    @GET
    public List<Image> getAllImages(@PathParam("project") String application) {
        return projectService.getImages(application);
    }

    @POST
    public String generateNewImage(NewImageModel newImageModel, @PathParam("project") String identifier) {
        return projectService.generateNewImage(identifier, newImageModel);
    }

    @GET
    @Path("/{tag}")
    public Project getImage(@PathParam("tag") String tag) {
        return null;
    }
}
