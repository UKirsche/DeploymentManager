package de.bas.deploymentmanager.logic.domain.application.boundary;

import de.bas.deploymentmanager.logic.domain.application.entity.Application;
import de.bas.deploymentmanager.logic.domain.application.entity.Image;
import de.bas.deploymentmanager.logic.domain.application.entity.NewTagModel;

import java.util.List;

public interface ApplicationService {

    /**
     * Holt alle Applications
     *
     * @return .
     */
    List<Application> getAllApplications();

    /**
     * Lädt eine Application inkl. Images
     *
     * @param identifier der Application
     * @return .
     */
    Application getApplication(String identifier);

    /**
     * Erstellt ein neues Image und gibt dieses wieder zurück
     *
     * @param identifier  für die Application
     * @param newTagModel Informationen des Image
     * @return {image}:{tag}
     */
    String generateNewImage(String identifier, NewTagModel newTagModel);

    Application createNewApplication(Application model);

    List<Image> getImages(String identifier);
}
