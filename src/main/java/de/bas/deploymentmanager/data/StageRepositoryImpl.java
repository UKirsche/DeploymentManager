package de.bas.deploymentmanager.data;

import de.bas.deploymentmanager.logic.domain.stage.control.StageRepository;
import de.bas.deploymentmanager.logic.domain.stage.entity.Stage;
import de.bas.deploymentmanager.logic.domain.stage.entity.StageEnum;

import javax.persistence.TypedQuery;

public class StageRepositoryImpl extends AbstractRepository implements StageRepository {
    @Override
    public Stage getStage(StageEnum stage) {
        TypedQuery<Stage> selectAll = entityManager.createQuery("SELECT stage FROM Stage stage WHERE stage.name = :name", Stage.class);
        selectAll.setParameter("name", stage);
        return selectAll.getSingleResult();
    }



    @Override
    public Stage getStageByHostId(Long hostId) {
        TypedQuery<Stage> selectAll = entityManager.createQuery("SELECT stage FROM Stage stage, Host host WHERE " +
                "stage.id = host.stageId " +
                "AND host.id = :hostId", Stage.class);
        selectAll.setParameter("hostId", hostId);
        return selectAll.getSingleResult();


    }
}
