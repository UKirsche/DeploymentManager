package de.bas.deploymentmanager.logic.business.deleteimage;

import de.bas.deploymentmanager.logic.domain.project.boundary.ProjectService;
import de.bas.deploymentmanager.logic.domain.project.entity.exception.ImageDeleteException;
import de.bas.deploymentmanager.logic.domain.stage.boundary.StageService;

import javax.inject.Inject;
import java.io.Serializable;

/**
 * Löscht ein vorhandenes Image.
 * Ein Image kann nur gelöscht werden, wenn es nicht aktuell deployed ist
 * Wenn es nicht der neuste build in einer Version ist
 */
public class DeleteImageFlow implements Serializable {

    private final ProjectService projectService;
    private final StageService stageService;

    @Inject
    public DeleteImageFlow(ProjectService projectService, StageService stageService) {
        this.projectService = projectService;
        this.stageService = stageService;
    }

    public void deleteImage(Long imageId) throws ImageDeleteException {
        if (stageService.isImageDeployed(imageId).isPresent()) {
            throw new ImageDeleteException("Image ist noch deployed");
        }
        projectService.deleteImage(imageId);
    }
}
