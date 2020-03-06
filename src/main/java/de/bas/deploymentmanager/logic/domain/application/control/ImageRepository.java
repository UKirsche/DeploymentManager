package de.bas.deploymentmanager.logic.domain.application.control;

import de.bas.deploymentmanager.logic.domain.application.entity.Image;

import java.util.List;

public interface ImageRepository {
    List<Image> getImagesForApplication(Long id);
}
