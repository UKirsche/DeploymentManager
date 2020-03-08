package de.bas.deploymentmanager.frontend.rest.controller;

import de.bas.deploymentmanager.frontend.rest.domain.ImageModel;
import de.bas.deploymentmanager.logic.domain.application.boundary.ApplicationService;
import de.bas.deploymentmanager.logic.domain.application.entity.Application;
import de.bas.deploymentmanager.logic.domain.application.entity.Image;

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
    public String generateNewImage(ImageModel imageModel, @PathParam("application") String application) {
        String version = imageModel.getVersion();
        String image = imageModel.getImage();
        String user = imageModel.getUser();
        return null;
    }

    @GET
    @Path("/{tag}")
    public Application getImage(@PathParam("tag") String tag) {
        return null;
    }
}
