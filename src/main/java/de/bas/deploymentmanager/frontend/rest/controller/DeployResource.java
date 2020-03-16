package de.bas.deploymentmanager.frontend.rest.controller;


import de.bas.deploymentmanager.logic.business.deploy.DeployFlow;
import de.bas.deploymentmanager.logic.domain.stage.entity.StageEnum;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/projects/{project}/images/{tag}/deployed")
public class DeployResource {

    @Inject
    private DeployFlow deployFlow;

    @POST
    public Response imageDeployed(@PathParam("project") String identifier
            , @PathParam("tag") String tag
            , @QueryParam("stage") StageEnum stage
            , @QueryParam("host") String host
            , @QueryParam("port") String port) {
        deployFlow.imageDeployed(identifier, stage, tag, host, port);
        return Response.created(null).build();
    }
}
