package de.bas.deploymentmanager.frontend.rest.controller;

import de.bas.deploymentmanager.logic.domain.application.boundary.ApplicationService;
import de.bas.deploymentmanager.logic.domain.application.entity.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/application")
public class ApplicationResource {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Inject
    private ApplicationService applicationService;

    @GET
    public List<Application> getAllApplications() {
        log.debug("Lade alle Applications");
        return applicationService.getAllApplications();
    }

    @PUT
    public Application neueApplicationAnlegen(Application model) {
        return applicationService.createNewApplication(model);
    }

    @GET
    @Path("/{application}")
    public Application getApplication(@PathParam("application") String application) {
        log.debug("Lade Application {}", application);
        return applicationService.getApplication(application);
    }

}
