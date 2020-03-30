package de.bas.deploymentmanager.logic.domain.dicd.control;

import de.bas.deploymentmanager.logic.domain.dicd.boundary.CiCdService;
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
        log.info("Deploy Image {} on Stage {}", imageTag, stageEnum);
        Response response = null;
        switch (stageEnum) {
            case ETW:
                response = jenkinsClient.deploy(jobName, imageTag, true, false, false);
                break;
            case INT:
                response = jenkinsClient.deploy(jobName, imageTag, false, true, false);
                break;
            case PRD:
                response = jenkinsClient.deploy(jobName, imageTag, false, false, true);
                break;
        }
        int statusCode = response.getStatus();
        log.info("Jobname {} wurde mit Tag {} in Stage {} ausgeführt. Statuscode: {}", jobName, imageTag, stageEnum, statusCode);

    }

    @Override
    public void buildImage(String jobName) {
        Response build = jenkinsClient.build(jobName, true);
        log.info("");
    }
}
