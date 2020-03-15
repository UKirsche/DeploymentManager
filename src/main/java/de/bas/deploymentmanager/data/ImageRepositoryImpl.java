package de.bas.deploymentmanager.data;

import de.bas.deploymentmanager.logic.domain.project.control.ImageRepository;
import de.bas.deploymentmanager.logic.domain.project.entity.Image;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class ImageRepositoryImpl extends AbstractRepository implements ImageRepository {


    @Override
    public List<Image> getImagesForApplication(Long applicationId) {
        TypedQuery<Image> selectAll = entityManager.createQuery("SELECT image FROM Image image WHERE image.projectId = :applicationId", Image.class);
        selectAll.setParameter("applicationId", applicationId);
        return selectAll.getResultList();
    }

    @Override
    public Optional<Image> getLastImageOfVersion(Long applicationId, Integer majorVersion, Integer minorVersion) {
        TypedQuery<Image> selectAll = entityManager.createQuery("SELECT image FROM Image image " +
                "WHERE image.projectId = :applicationId " +
                "AND image.majorVersion = :majorVersion " +
                "AND image.minorVersion = :minorVersion " +
                "ORDER BY image.buildNumber DESC", Image.class);
        selectAll.setParameter("applicationId", applicationId);
        selectAll.setParameter("majorVersion", majorVersion);
        selectAll.setParameter("minorVersion", minorVersion);
        List<Image> resultList = selectAll.getResultList();
        if (resultList.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(resultList.get(0));
    }

    @Override
    public Image save(Image image) {
        Image saved = entityManager.merge(image);
        entityManager.flush();
        return saved;
    }

    @Override
    public Image getImageByIdentifierTag(String identifier, String tag) {
        TypedQuery<Image> selectAll = entityManager.createQuery("SELECT image FROM Image image, Project app " +
                "WHERE app.id = image.projectId " +
                "AND app.identifier =:identifier " +
                "AND image.tag =:tag", Image.class);
        selectAll.setParameter("identifier", identifier);
        selectAll.setParameter("tag", tag);
        return selectAll.getSingleResult();
    }

    @Override
    public Image getImageByApplicationIdTag(Long applicationId, String tag) {
        TypedQuery<Image> selectAll = entityManager.createQuery("SELECT image FROM Image image " +
                "WHERE image.projectId =:applicationId " +
                "AND image.tag =:tag", Image.class);
        selectAll.setParameter("applicationId", applicationId);
        selectAll.setParameter("tag", tag);
        return selectAll.getSingleResult();


    }

}
