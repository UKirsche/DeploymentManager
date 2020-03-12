package de.bas.deploymentmanager.logic.domain.dicd.control;

import de.bas.deploymentmanager.logic.domain.dicd.entity.JenkinsParameter;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@RegisterRestClient(baseUri = "http://jenkins.bvaetw.de:8080")
@Path("/job/{jobName}")
@ClientHeaderParam(name = "Authorization", value = "Basic bHViaXR6OjAwMjI0MTM1")
public interface JenkinsClient {

    @POST
    @Path("/buildWithParameters")
    Response build(@PathParam("jobName") String jobName, JenkinsParameter parameter);
}
