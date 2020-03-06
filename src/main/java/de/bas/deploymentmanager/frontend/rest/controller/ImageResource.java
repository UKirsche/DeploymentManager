package de.bas.deploymentmanager.frontend.rest.controller;

import de.bas.deploymentmanager.frontend.rest.domain.ImageInfo;
import de.bas.deploymentmanager.logic.domain.application.entity.Application;
import de.bas.deploymentmanager.logic.domain.application.entity.Image;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/application/{application}/images")
public class ImageResource {

    @GET
    public List<Image> getAllImages() {
        return null;
    }

    @POST
    public String generateNewImage(ImageInfo imageInfo) {
        return null;
    }

    @GET
    @Path("/{tag}")
    public Application getImage(@PathParam("tag") String tag) {
        return null;
    }
}
