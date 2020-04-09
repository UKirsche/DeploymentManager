package de.bas.deploymentmanager.logic.business.createnewimage;

import de.bas.deploymentmanager.logic.domain.project.boundary.ProjectService;
import de.bas.deploymentmanager.logic.domain.project.entity.NewImageModel;
import de.bas.deploymentmanager.logic.domain.project.entity.Tag;
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
        Tag imageTag = projectService.generateNewImage(projectIdentifier, newImageModel);
        projectService.syncImages(projectIdentifier, newImageModel, imageTag);
        log.info("{} wurde erfolgreich für Projekt {} angelegt.", imageTag, projectIdentifier);
        return imageTag.toString();
    }
}
