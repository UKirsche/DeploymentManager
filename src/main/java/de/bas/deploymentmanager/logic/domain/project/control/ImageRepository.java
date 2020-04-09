package de.bas.deploymentmanager.logic.domain.project.control;

import de.bas.deploymentmanager.logic.domain.project.entity.Image;
import de.bas.deploymentmanager.logic.domain.project.entity.exception.ImageDeleteException;

import java.util.List;
import java.util.Optional;

public interface ImageRepository {
    List<Image> getImagesForProject(Long projectId);

    Optional<Image> getLastImageOfVersion(Long applicationId, Integer majorVersion, Integer minorVersion, int incrementalVersion);

    Image save(Image image);

    Image getImageByIdentifierTag(String identifier, String tag);

    Image getImageByProjectIdTag(Long applicationId, String tag);

    void delete(Long id) throws ImageDeleteException;
}
