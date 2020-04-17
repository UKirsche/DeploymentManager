package de.bas.deploymentmanager.data.image;

import de.bas.deploymentmanager.data.AbstarctRepositoryIT;
import de.bas.deploymentmanager.data.ImageRepositoryImpl;
import de.bas.deploymentmanager.logic.domain.project.control.ImageRepository;
import de.bas.deploymentmanager.logic.domain.project.entity.Image;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ImgageRepositoryIT extends AbstarctRepositoryIT {

    private static final String insertImage = "INSERT INTO IMAGE VALUES (DEFAULT, %s, '%s', '%s', null, '%s', '%s', %s, %s, %s, %s)";

    ImageRepository repository;

    @BeforeEach
    void setUp() {
        repository = injectEntityManager(new ImageRepositoryImpl());
    }

    @Test
    void getImagesForProject() {
        List<Image> imagesForProject = repository.getImagesForProject(1L);
        Assertions.assertNotNull(imagesForProject);
        Assertions.assertEquals(1, imagesForProject.size());
    }

    @Test
    void sucheAlleImagesEinesProjektes() {
        insterImage(1L, "1.1.0", "test", "test", 1, 2, 0, 1);
        List<Image> imagesForProject = repository.getImagesForProject(1L);
        Assertions.assertNotNull(imagesForProject);
        Assertions.assertEquals(2, imagesForProject.size());
    }

    private void insterImage(Long projectId, String tag, String user, String image, int major, int minor, int increment, int build) {
        String sql = String.format(insertImage, projectId, tag, user, image, "commit", major, minor, increment, build);
        executeNativQuery(sql);
    }
}
