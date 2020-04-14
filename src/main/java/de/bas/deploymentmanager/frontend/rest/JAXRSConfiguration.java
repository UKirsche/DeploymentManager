package de.bas.deploymentmanager.frontend.rest;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.servers.Server;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Configures a JAX-RS endpoint. Delete this class, if you are not exposing
 * JAX-RS resources in your application.
 *
 * @author airhacks.com
 */
@ApplicationPath("api")
@OpenAPIDefinition(info = @Info(
        title = "Deployment Manager",
        version = "1.0"),
        servers = {
                @Server(url = "/deployment-manager", description = "localhost")
        }
)
public class JAXRSConfiguration extends Application {

}
