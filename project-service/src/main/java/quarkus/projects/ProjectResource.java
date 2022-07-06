package quarkus.projects;

import quarkus.projects.beans.Project;
import quarkus.projects.enums.ProjectStatus;

import javax.annotation.PostConstruct;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Path("/projects")
public class ProjectResource {

    List<Project> projects = new ArrayList<>();

    @PostConstruct
    public void setup() {
        projects.add(new Project(2366278L, "Sample Project 1", 5, 10, ProjectStatus.NEW ));
        projects.add(new Project(4895443L, "Sample Project 2", 2, 8, ProjectStatus.IN_PROGRESS ));
        projects.add(new Project(8943547L, "Sample Project 3", 8, 6, ProjectStatus.IN_PROGRESS ));
        projects.add(new Project(3247839L, "Sample Project 4", 12, 5, ProjectStatus.DELAYED ));
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Project> getAllProjects() {
        return projects;
    }

    @GET
    @Path("/{projectId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Project getProjectById(@PathParam("projectId") Long projectId) {
        Optional<Project> retrievedProject = projects.stream().filter(project -> project.getProjectId().equals(projectId)).findFirst();
        return retrievedProject.orElseThrow(() -> new NotFoundException("project with id: " + projectId + " not found!"));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createProject(Project project) {
        if (project.getProjectId() == null) {
            throw new WebApplicationException("A project cannot be created without Project Id", 400);
        }

        projects.add(project);
        return Response.status(201).entity(project).build();
    }

    @PUT
    @Path("{projectId}/changeStatus")
    public Project withdrawal(@PathParam("projectId") Long projectId, String status) {
        Project project = getProjectById(projectId);
        project.setProjectStatus(ProjectStatus.valueOf(status));
        return project;
    }

    @DELETE
    @Path("{projectId}")
    public Response deleteProject(@PathParam("projectId") Long projectId) {
        Project projectToRemove = getProjectById(projectId);
        projects.remove(projectToRemove);
        return Response.noContent().build();
    }

}