package de.bas.deploymentmanager.logic.domain.dicd.control;

import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RegisterRestClient()
@Path("/job/{jobName}")
@Produces(MediaType.APPLICATION_FORM_URLENCODED)
@RegisterClientHeaders(value = BasicAuthHeaderFactory.class)
public interface JenkinsClient {

    @POST
    @Path("/buildWithParameters")
    Response build(@PathParam("jobName") String jobName, @QueryParam("ETW_DEPLOY") boolean push);

    @POST
    @Path("/buildWithParameters")
    Response deploy(@PathParam("jobName") String jobName, @QueryParam("TAG") String tag
            , @QueryParam("ETW_DEPLOY") boolean etwDeploy
            , @QueryParam("INT_DEPLOY") boolean intDeploy
            , @QueryParam("PRD_DEPLOY") boolean prdDeploy);
}
