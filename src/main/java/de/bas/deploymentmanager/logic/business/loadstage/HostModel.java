package de.bas.deploymentmanager.logic.business.loadstage;

import de.bas.deploymentmanager.logic.domain.stage.entity.Host;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class HostModel {
    private Host host;
    private List<AppModel> appModels;
}
