package de.bas.deploymentmanager.logic.domain.application.control;

import de.bas.deploymentmanager.logic.domain.application.entity.Image;

import java.util.List;
import java.util.Optional;

public interface ImageRepository {
    List<Image> getImagesForApplication(Long applicationId);

    Optional<Image> getLastImageOfVersion(Long applicationId, Integer majorVersion, Integer minorVersion);

    Image save(Image image);

    Image getImageByIdentifierTag(String identifier, String tag);

    Image getImageByApplicationIdTag(Long applicationId, String tag);
}
