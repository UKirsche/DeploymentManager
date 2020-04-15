package de.bas.deploymentmanager.logic.business.loadstage;

import de.bas.deploymentmanager.logic.domain.stage.entity.App;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AppModel {
    private App app;
    private String projektName;

}
