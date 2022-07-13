package quarkus.projects.beans;

import lombok.Data;

@Data
public class Budget {

    private Long projectId;
    private Double allocatedBudget;
    private Double currentUtilization;
    private String status;

}
