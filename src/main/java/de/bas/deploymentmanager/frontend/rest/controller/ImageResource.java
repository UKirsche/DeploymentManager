package de.bas.deploymentmanager.frontend.rest.controller;

import de.bas.deploymentmanager.logic.domain.application.boundary.ApplicationService;
import de.bas.deploymentmanager.logic.domain.application.entity.Application;
import de.bas.deploymentmanager.logic.domain.application.entity.Image;
import de.bas.deploymentmanager.logic.domain.application.entity.NewTagModel;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/application/{application}/images")
public class ImageResource {

    @Inject
    private ApplicationService applicationService;

    @GET
    public List<Image> getAllImages(@PathParam("application") String application) {
        return applicationService.getImages(application);
    }

    @POST
    public String generateNewImage(NewTagModel newTagModel, @PathParam("application") String identifier) {
        return applicationService.generateNewImage(identifier, newTagModel);
    }

    @GET
    @Path("/{tag}")
    public Application getImage(@PathParam("tag") String tag) {
        return null;
    }
}
