package quarkus.projects.healthcheck;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

import javax.enterprise.context.ApplicationScoped;
import java.time.Instant;
import java.util.Date;

@ApplicationScoped
@Liveness
public class ProjectServiceLivenessCheck implements HealthCheck {


    @Override
    public HealthCheckResponse call() {
        return HealthCheckResponse
                .named("health-check-name")
                .up()
                .withData("time", (new Date()).toString())
                .build();
    }
}
