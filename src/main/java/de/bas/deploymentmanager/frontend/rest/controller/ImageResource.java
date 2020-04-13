package de.bas.deploymentmanager.frontend.rest.controller;

import de.bas.deploymentmanager.logic.business.createnewimage.CreateNewImageFlow;
import de.bas.deploymentmanager.logic.domain.project.boundary.ProjectService;
import de.bas.deploymentmanager.logic.domain.project.entity.NewImageModel;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/projects/{project}/images")
public class ImageResource {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Inject
    private ProjectService projectService;

    @Inject
    private CreateNewImageFlow createNewImageFlow;


    /**
     * Erzeugt ein neues Image und gibt das Tag wieder zurück.
     *
     * @param newImageModel Informationen zur Version
     * @param identifier    ProjectIdentifier
     * @return Tag des neuen Image
     */
    @POST
    @Operation(summary = "Erzeugt ein neues Image und gibt das Tag wieder zurueck")
    public String generateNewImage(NewImageModel newImageModel, @PathParam("project") String identifier) {
        return createNewImageFlow.createNewImage(identifier, newImageModel);
    }

    /**
     * Erzeugt ein neues Image und gibt das Tag wieder zurück.
     * Es wird kein neues Image angelegt.
     *
     * @param version    Aktuellle Version
     * @param identifier ProjectIdentifier
     * @return Tag des neuen Image
     */
    @GET
    @Operation(summary = "Gibt das naechste Tag einer Version zurueck")
    public String getNextTag(@PathParam("project") String identifier, @QueryParam("version") String version) {
        log.info("Generate Next Tag für Project {} und Version {}", identifier, version);
        return projectService.gererateNextTag(identifier, version);
    }
}
