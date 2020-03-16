package de.bas.deploymentmanager.frontend.jsf.pipeline;

import de.bas.deploymentmanager.logic.domain.pipeline.entity.Pipeline;
import lombok.Getter;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@ViewScoped
@Named
public class PipelineFormBean implements Serializable {

    @Getter
    private List<Pipeline> pipelines;

    @PostConstruct
    public void init() {
        Pipeline pipeline = new Pipeline();
        pipeline.setName("ZUS");
        pipelines = Arrays.asList(pipeline);

    }


}
