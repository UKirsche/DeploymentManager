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
}
