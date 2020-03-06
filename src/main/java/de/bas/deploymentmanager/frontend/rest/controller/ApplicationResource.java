package de.bas.deploymentmanager.frontend.rest.controller;

import de.bas.deploymentmanager.logic.domain.application.entity.Application;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/application")
public class ApplicationResource {

    @GET
    public List<Application> getAllApplications() {
        return null;
    }

    @GET
    @Path("/{application}")
    public Application getApplication(@PathParam("application") String application) {
        return null;
    }

}
