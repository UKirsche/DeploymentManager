package de.bas.deploymentmanager.logic.business.createnewimage;

import de.bas.deploymentmanager.logic.domain.project.entity.NewImageModel;

public interface CreateNewImageFlow {

    /**
     * Erstellt ein neues Image und gibt das Tag wieder zurück.
     * Das neue Tag wird anhand der übergebenen Version generiert.
     *
     * @param projectIdentifier für das Project
     * @param newImageModel     Informationen des Image
     * @return {tag}
     */
    String createNewImage(String projectIdentifier, NewImageModel newImageModel);
}
