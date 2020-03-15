package de.bas.deploymentmanager.logic.business.newimage;

import de.bas.deploymentmanager.logic.domain.project.entity.NewImageModel;

public interface CreateNewImageFlow {

    /**
     * Erstellt ein neues Image und gibt dieses wieder zurück
     *
     * @param projectIdentifier für das Project
     * @param newImageModel     Informationen des Image
     * @return {image}:{tag}
     */
    String createNewImage(String projectIdentifier, NewImageModel newImageModel);
}
