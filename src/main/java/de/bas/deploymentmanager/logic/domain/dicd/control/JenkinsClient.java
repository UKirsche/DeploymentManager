package de.bas.deploymentmanager.logic.domain.dicd.control;

import de.bas.deploymentmanager.logic.domain.dicd.entity.JenkinsParameter;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@RegisterRestClient(baseUri = "http://localhost:8090")
@Path("/job/{jobName}")
@ClientHeaderParam(name = "Authorization", value = "Basic bWFuYWdlcjptYW5hZ2Vy")
public interface JenkinsClient {

    @POST
    @Path("/buildWithParameters")
    Response build(@PathParam("jobName") String jobName, JenkinsParameter parameter);
}
