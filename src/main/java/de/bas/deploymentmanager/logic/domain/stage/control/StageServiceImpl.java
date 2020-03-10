package de.bas.deploymentmanager.logic.domain.stage.control;

import de.bas.deploymentmanager.logic.domain.stage.boundary.StageService;
import de.bas.deploymentmanager.logic.domain.stage.entity.Stage;
import de.bas.deploymentmanager.logic.domain.stage.entity.StageEnum;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class StageServiceImpl implements StageService {
    private final StageRepository stageRepository;

    @Inject
    public StageServiceImpl(StageRepository stageRepository) {
        this.stageRepository = stageRepository;
    }

    @Override
    public void imageDeployed(String identifier, String tag, String host) {

    }

    @Override
    public Stage getStage(StageEnum stage) {
        return stageRepository.getStage(stage);

    }
}
