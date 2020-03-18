package de.bas.deploymentmanager.logic.domain.dicd.control;

import de.bas.deploymentmanager.logic.domain.dicd.boundary.CiCdService;
import de.bas.deploymentmanager.logic.domain.dicd.entity.JenkinsParameter;
import de.bas.deploymentmanager.logic.domain.stage.entity.StageEnum;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

@Stateless
public class CiCdServiceImpl implements CiCdService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final JenkinsClient jenkinsClient;

    @Inject
    @RestClient
    public CiCdServiceImpl(JenkinsClient jenkinsClient) {
        this.jenkinsClient = jenkinsClient;
    }

    @Override
    public void deployImage(String jobName, String imageTag, StageEnum stageEnum) {
        JenkinsParameter parameter = new JenkinsParameter();
        parameter.put("image", imageTag);
        parameter.put("stage", stageEnum.name());
        jenkinsClient.deploy(jobName, imageTag, true);

    }

    @Override
    public void buildImage(String jobName) {

        JenkinsParameter parameter = new JenkinsParameter();
        parameter.put("PUSH", "true");
        Response build = jenkinsClient.build(jobName, true);
    }
}
