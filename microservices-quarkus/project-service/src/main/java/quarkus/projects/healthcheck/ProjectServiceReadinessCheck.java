package quarkus.projects.healthcheck;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import quarkus.projects.services.BudgetService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import java.util.Date;

@Readiness
@ApplicationScoped
public class ProjectServiceReadinessCheck implements HealthCheck {

    @Inject
    @RestClient
    BudgetService budgetService;


    @Override
    public HealthCheckResponse call() {

        String status;

        try {
            status = budgetService.getBudgetStatusForProject(000000L);
        } catch(WebApplicationException exception) {
            if(exception.getResponse().getStatus() >= 500) {
                return HealthCheckResponse
                        .named("readiness-check-name")
                        .withData("exception", exception.toString())
                        .withData("time", (new Date()).toString())
                        .down().build();
            }
        }
        return HealthCheckResponse
                .named("readiness-check-name")
                .withData("time", (new Date()).toString())
                .up().build();
    }
}
