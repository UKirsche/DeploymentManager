package de.bas.deploymentmanager.logic.business.deleteimage;

import de.bas.deploymentmanager.logic.domain.project.entity.exception.ImageDeleteException;

/**
 * Löscht ein vorhandenes Image.
 * Ein Image kann nur gelöscht werden, wenn es nicht aktuell deployed ist
 * Wenn es nicht der neuste build in einer Version ist
 */
public interface DeleteImageFlow {
    void deleteImage(Long imageId) throws ImageDeleteException;
}
