package de.bas.deploymentmanager.frontend.rest.controller;

import de.bas.deploymentmanager.logic.domain.project.boundary.ProjectService;
import de.bas.deploymentmanager.logic.domain.project.entity.Image;
import de.bas.deploymentmanager.logic.domain.project.entity.NewImageModel;
import de.bas.deploymentmanager.logic.domain.project.entity.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/projects/{project}/images")
public class ImageResource {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Inject
    private ProjectService projectService;

    @GET
    public List<Image> getAllImages(@PathParam("project") String application) {
        return projectService.getImages(application);
    }

    /**
     * Erzeugt ein neues Image und gibt das Tag wieder zurück.
     *
     * @param newImageModel Informationen zur Version
     * @param identifier    ProjectIdentifier
     * @return Tag des neuen Image
     */
    @POST
    public String generateNewImage(NewImageModel newImageModel, @PathParam("project") String identifier) {
        return projectService.generateNewImage(identifier, newImageModel);
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
    public String getNextTag(@PathParam("project") String identifier, @QueryParam("version") String version) {
        return projectService.gererateNextTag(identifier, version);
    }

    @GET
    @Path("/{tag}")
    public Project getImage(@PathParam("tag") String tag) {
        return null;
    }
}
