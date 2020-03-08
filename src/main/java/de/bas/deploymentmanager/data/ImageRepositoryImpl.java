package de.bas.deploymentmanager.data;

import de.bas.deploymentmanager.logic.domain.application.control.ImageRepository;
import de.bas.deploymentmanager.logic.domain.application.entity.Image;

import javax.persistence.TypedQuery;
import java.util.List;

public class ImageRepositoryImpl extends AbstractRepository implements ImageRepository {


    @Override
    public List<Image> getImagesForApplication(Long applicationId) {
        TypedQuery<Image> selectAll = entityManager.createQuery("SELECT image FROM Image image WHERE image.applicationId = :applicationId", Image.class);
        selectAll.setParameter("applicationId", applicationId);
        return selectAll.getResultList();
    }
}
