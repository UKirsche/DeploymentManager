package de.bas.deploymentmanager.frontend.rest.controller;


import de.bas.deploymentmanager.logic.business.deploy.DeployFlow;
import de.bas.deploymentmanager.logic.domain.stage.entity.StageEnum;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/projects/{project}/images/{tag}/deployed")
public class DeployResource {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Inject
    private DeployFlow deployFlow;

    @POST
    @Operation(summary = "Markiert ein Image als deployed")
    public Response imageDeployed(@PathParam("project") String identifier
            , @PathParam("tag") String tag
            , @QueryParam("stage") StageEnum stage
            , @QueryParam("host") String host
            , @QueryParam("port") String port) {
        log.info("Image ist deployed: TAG: {}, Stage: {}, Host: {}, Port:{}", tag, stage, host, port);
        deployFlow.imageDeployed(identifier, stage, tag, host, port);
        return Response.created(null).build();
    }
}
