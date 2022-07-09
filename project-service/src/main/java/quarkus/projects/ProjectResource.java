package quarkus.projects;

import quarkus.projects.beans.Project;
import quarkus.projects.enums.ProjectStatus;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/projects")
public class ProjectResource {

    List<Project> projects = new ArrayList<>();

    @PostConstruct
    public void setup() {
        projects.add(new Project(2366278L, "Sample Project 1", 5, 10, "NEW" ));
        projects.add(new Project(4895443L, "Sample Project 2", 2, 8, "IN_PROGRESS" ));
        projects.add(new Project(8943547L, "Sample Project 3", 8, 6, "IN_PROGRESS" ));
        projects.add(new Project(3247839L, "Sample Project 4", 12, 5, "DELAYED" ));
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Project> getAllProjects() {
        return Project.listAll();
    }

    @GET
    @Path("/{projectId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Project getProjectById(@PathParam("projectId") Long projectId) {
        return Project.findByProjectId(projectId);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response createProject(Project project) {
        project.persist();
        return Response.status(201).entity(project).build();
    }

    @PUT
    @Path("{projectId}/changeStatus")
    public Project changeStatus(@PathParam("projectId") Long projectId, String status) {
        Project project = Project.findByProjectId(projectId);
        project.setProjectStatus(status);
        return project;
    }

    @DELETE
    @Path("{projectId}")
    public Response deleteProject(@PathParam("projectId") Long projectId) {
        Project projectToClose = Project.findByProjectId(projectId);
        projectToClose.setProjectStatus(ProjectStatus.CLOSED.toString());
        return Response.noContent().build();
    }

}