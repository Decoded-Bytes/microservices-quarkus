package quarkus.projects.services;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/budgets")
@RegisterRestClient(configKey = "budget-service")
@Produces(MediaType.APPLICATION_JSON)
public interface BudgetService {


    @GET
    @Path("/{projectId}/status")
    String getBudgetStatusForProject(@PathParam("projectId") Long projectId);

}
