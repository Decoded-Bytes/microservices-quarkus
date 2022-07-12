package quarkus.projects.beans;

import lombok.Data;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import quarkus.projects.enums.ProjectStatus;

import javax.persistence.*;

@Data
@Entity
@NamedQuery(name = "Projects.findAll", query = "SELECT p FROM Project p ORDER BY p.projectId")
@NamedQuery(name = "Projects.findByProjectId", query = "SELECT p FROM Project p WHERE p.projectId = :projectId ORDER BY p.projectId")
public class Project {

    private Long projectId;
    private String projectName;
    private int durationInMonths;
    private int numResourcesAllocated;
    private String projectStatus;
    private Long id;
    private String clientName;

    public Project(Long projectId, String projectName, int durationInMonths, int numResourcesAllocated, String projectStatus, String clientName) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.durationInMonths = durationInMonths;
        this.numResourcesAllocated = numResourcesAllocated;
        this.projectStatus = projectStatus;
        this.clientName = clientName;
    }

    public Project() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @SequenceGenerator(name = "projectsSequence", sequenceName = "projects_id_seq", allocationSize = 1, initialValue = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "projectsSequence")
    public Long getId() {
        return id;
    }
}
