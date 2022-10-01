package quarkus.projects.config;

import io.smallrye.config.ConfigMapping;

import javax.validation.constraints.Size;

@ConfigMapping(prefix = "project-service-config")
public interface ProjectServiceConfigMapping {

    @Size(min=5)
    String clientName();

}