package de.bas.deploymentmanager.logic.domain.project.boundary;

import de.bas.deploymentmanager.logic.domain.project.entity.Image;
import de.bas.deploymentmanager.logic.domain.project.entity.NewImageModel;
import de.bas.deploymentmanager.logic.domain.project.entity.Project;
import de.bas.deploymentmanager.logic.domain.stage.entity.Stage;

import java.util.List;

public interface ProjectService {

    /**
     * Holt alle Applications
     *
     * @return .
     */
    List<Project> getAllProjects();

    /**
     * Lädt eine Application inkl. Images
     *
     * @param identifier der Application
     * @return .
     */
    Project getProject(String identifier);

    /**
     * Erstellt ein neues Image und gibt dieses wieder zurück
     *
     * @param identifier    für die Application
     * @param newImageModel Informationen des Image
     * @return {image}:{tag}
     */
    String generateNewImage(String identifier, NewImageModel newImageModel);

    Image markImageAsDeployed(String identifier, String tag, Stage stage);

    Project createNewProject(Project model);

    List<Image> getImages(String identifier);

    Image getImage(Long applicationId, String tag);
}
