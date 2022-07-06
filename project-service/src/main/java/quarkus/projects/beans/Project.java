package quarkus.projects.beans;

import lombok.Data;
import quarkus.projects.enums.ProjectStatus;

@Data
public class Project {

    private Long projectId;
    private String projectName;
    private int durationInMonths;
    private int numResourcesAllocated;
    private ProjectStatus projectStatus = ProjectStatus.NEW;

    public Project(Long projectId, String projectName, int durationInMonths, int numResourcesAllocated, ProjectStatus projectStatus) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.durationInMonths = durationInMonths;
        this.numResourcesAllocated = numResourcesAllocated;
        this.projectStatus = projectStatus;
    }

    public Project() {
    }
}
