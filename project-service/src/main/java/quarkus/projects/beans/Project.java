package quarkus.projects.beans;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class Project extends PanacheEntity {

    public Long projectId;
    public String projectName;
    public int durationInMonths;
    public int numResourcesAllocated;
    public String projectStatus;

    public Project(Long projectId, String projectName, int durationInMonths, int numResourcesAllocated, String projectStatus) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.durationInMonths = durationInMonths;
        this.numResourcesAllocated = numResourcesAllocated;
        this.projectStatus = projectStatus;
    }

    public Project() {
    }

    public static Project findByProjectId(Long projectId) {
        return find("projectId", projectId).firstResult();
    }

}
