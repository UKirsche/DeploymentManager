package de.bas.deploymentmanager.logic.business.deleteimage;

import de.bas.deploymentmanager.logic.domain.project.boundary.ProjectService;
import de.bas.deploymentmanager.logic.domain.project.entity.exception.ImageDeleteException;
import de.bas.deploymentmanager.logic.domain.stage.boundary.StageService;

import javax.ejb.Stateless;
import javax.inject.Inject;


@Stateless
public class DeleteImageFlowImpl implements DeleteImageFlow {

    private final ProjectService projectService;
    private final StageService stageService;

    @Inject
    public DeleteImageFlowImpl(ProjectService projectService, StageService stageService) {
        this.projectService = projectService;
        this.stageService = stageService;
    }

    @Override
    public void deleteImage(Long imageId) throws ImageDeleteException {
        if (stageService.isImageDeployed(imageId).isPresent()) {
            throw new ImageDeleteException("Image ist noch deployed");
        }
        projectService.deleteImage(imageId);
    }
}
