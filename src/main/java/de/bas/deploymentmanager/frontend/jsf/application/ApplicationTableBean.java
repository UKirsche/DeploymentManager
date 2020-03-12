package de.bas.deploymentmanager.frontend.jsf.application;

import de.bas.deploymentmanager.logic.domain.application.boundary.ApplicationService;
import de.bas.deploymentmanager.logic.domain.application.entity.Application;
import lombok.Getter;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequestScoped
@Named
public class ApplicationTableBean {

    @Inject
    private ApplicationService applicationService;

    @Getter
    private List<Application> list;


    @PostConstruct
    public void init() {
        List<Application> applications = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            Application application = new Application();
            application.setName(UUID.randomUUID().toString());
            application.setId((long) i);
            application.setBuildJob("pipe_" + i);
            applications.add(application);
        }
        this.list = applications;
    }


}
