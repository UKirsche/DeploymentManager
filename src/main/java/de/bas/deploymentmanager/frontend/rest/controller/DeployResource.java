package de.bas.deploymentmanager.frontend.rest.controller;


import de.bas.deploymentmanager.logic.domain.stage.boundary.StageService;
import de.bas.deploymentmanager.logic.domain.stage.entity.DeployModel;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/application/{application}/images/{tag}/deploy")
public class DeployResource {

    @Inject
    private StageService stageService;

    @POST
    public Response imageDeployed(@PathParam("application") String identifier, @PathParam("tag") String tag, DeployModel deployModel) {
        stageService.imageDeployed(identifier, tag, deployModel);
        return Response.created(null).build();
    }
}
