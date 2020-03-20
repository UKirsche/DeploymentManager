package de.bas.deploymentmanager.logic.domain.dicd.control;

import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RegisterRestClient(baseUri = "http://jenkins.bvaetw.de:8080")
//@RegisterRestClient(baseUri = "http://etw-docker-01.bvaetw.de:8880")
@Path("/job/{jobName}")
@ClientHeaderParam(name = "Authorization", value = "Basic bHViaXR6OjE3YTJhYzhhZTQ3MTA1MTdiZmM1N2Q1YjM4MTIzNmI2")
//ETW TOKEN
@Produces(MediaType.APPLICATION_FORM_URLENCODED)
public interface JenkinsClient {

    @POST
    @Path("/buildWithParameters")
    Response build(@PathParam("jobName") String jobName, @QueryParam("PUSH") boolean push);

    @POST
    @Path("/buildWithParameters")
    Response deploy(@PathParam("jobName") String jobName, @QueryParam("TAG") String tag
            , @QueryParam("ETW_DEPLOY") boolean etwDeploy
            , @QueryParam("INT_DEPLOY") boolean intDeploy
            , @QueryParam("PRD_DEPLOY") boolean prdDeploy);
}
