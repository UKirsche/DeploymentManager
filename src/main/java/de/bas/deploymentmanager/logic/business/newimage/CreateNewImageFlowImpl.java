package de.bas.deploymentmanager.logic.business.newimage;

import de.bas.deploymentmanager.logic.domain.project.boundary.ProjectService;
import de.bas.deploymentmanager.logic.domain.project.entity.NewImageModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class CreateNewImageFlowImpl implements CreateNewImageFlow {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final ProjectService projectService;

    @Inject
    public CreateNewImageFlowImpl(ProjectService projectService) {
        this.projectService = projectService;
    }

    @Override
    public String createNewImage(String projectIdentifier, NewImageModel newImageModel) {
        log.info("Es wird ein neues Image für das Project {} angelegt.", projectIdentifier);
        return projectService.generateNewImage(projectIdentifier, newImageModel);
    }
}
