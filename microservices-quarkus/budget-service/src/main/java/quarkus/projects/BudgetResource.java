package quarkus.projects;

import quarkus.projects.beans.Budget;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.DecimalFormat;
import java.util.Random;

@Path("/budgets")
@Produces(MediaType.APPLICATION_JSON)
public class BudgetResource {

    private String calculateStatus(double utilization, double allocatedBudget) {
        double utilizationPercent = utilization * 100 / allocatedBudget;

        if(utilizationPercent < 80 )
            return "GREEN";
        else if(utilizationPercent < 90 )
            return "AMBER";
        else if(utilizationPercent < 95 )
            return "ORANGE";
        else
            return "RED";
    }

    private double getRandomNumber() {
        return Double.valueOf(new DecimalFormat("0.00").format(new Random().nextDouble() * 10000));
    }

    @GET
    @Path("/{projectId}")
    public Budget getBudgetForProject(@PathParam("projectId") Long projectId) {

        Budget budget = new Budget();
        budget.setProjectId(projectId);
        budget.setAllocatedBudget(getRandomNumber());
        budget.setCurrentUtilization(getRandomNumber());
        budget.setStatus(calculateStatus(budget.getCurrentUtilization(), budget.getAllocatedBudget()));

        return budget;

    }

    @GET
    @Path("/{projectId}/status")
    public String getBudgetStatusForProject(@PathParam("projectId") Long projectId) {
        return getBudgetForProject(projectId).getStatus();

    }
}