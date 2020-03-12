package de.bas.deploymentmanager.logic.domain.stage.control;

import de.bas.deploymentmanager.logic.domain.application.entity.Image;
import de.bas.deploymentmanager.logic.domain.stage.boundary.StageService;
import de.bas.deploymentmanager.logic.domain.stage.entity.App;
import de.bas.deploymentmanager.logic.domain.stage.entity.Host;
import de.bas.deploymentmanager.logic.domain.stage.entity.Stage;
import de.bas.deploymentmanager.logic.domain.stage.entity.StageEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

@Stateless
public class StageServiceImpl implements StageService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final StageRepository stageRepository;

    @Inject
    public StageServiceImpl(StageRepository stageRepository) {
        this.stageRepository = stageRepository;
    }

    @Override
    public void imageDeployed(String identifier, Image image, String hostUrl, String port) {
        Host host = stageRepository.getHost(hostUrl);
        deleteOldVersion(host, identifier);
        addNewVersion(identifier, image, port, host);

    }

    private void deleteOldVersion(Host host, String identifier) {
        if (host.getApplications() == null || host.getApplications().isEmpty()) {
            return;
        }
        Optional<App> oldVersion = host.getApplications().stream().filter(app -> app.getApplicationIdentifier().equals(identifier)).findFirst();
        oldVersion.ifPresent(app -> {
            host.getApplications().remove(app);
            log.info("Alte Version wurde vom Host {} entfernt", host.getUrl());
        });
    }

    private void addNewVersion(String identifier, Image image, String port, Host host) {
        App neuesDeployment = new App();
        neuesDeployment.setCreateTime(LocalDateTime.now());
        neuesDeployment.setPort(port);
        neuesDeployment.setImage(image.getImageWithTag());
        neuesDeployment.setImageId(image.getId());
        neuesDeployment.setName(identifier);
        neuesDeployment.setApplicationIdentifier(identifier);
        if (host.getApplications() == null) {
            host.setApplications(new ArrayList<>());
        }
        host.getApplications().add(neuesDeployment);
        log.info("Neue Version der Application {} wurde auf {} deployed", identifier, host.getUrl());
    }

    @Override
    public Stage getStage(StageEnum stage) {
        return stageRepository.getStage(stage);

    }
}
