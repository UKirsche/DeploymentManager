package de.bas.deploymentmanager.logic.domain.dicd.control;

import de.bas.deploymentmanager.logic.domain.dicd.boundary.CiCdService;
import de.bas.deploymentmanager.logic.domain.dicd.entity.JenkinsParameter;
import de.bas.deploymentmanager.logic.domain.stage.entity.StageEnum;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

@Stateless
public class CiCdServiceImpl implements CiCdService {
    private final JenkinsClient jenkinsClient;

    @Inject
    @RestClient
    public CiCdServiceImpl(JenkinsClient jenkinsClient) {
        this.jenkinsClient = jenkinsClient;
    }

    @Override
    public void deployImage(String jobName, String image, StageEnum stageEnum) {
        JenkinsParameter parameter = new JenkinsParameter();
        parameter.put("image", image);
        parameter.put("stage", stageEnum.name());
        Response build = jenkinsClient.build(jobName, parameter);

    }
}
