package quarkus.projects;

import quarkus.projects.beans.Project;
import quarkus.projects.enums.ProjectStatus;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Path("/projects")
public class ProjectResource {

    List<Project> projects = new ArrayList<>();

    @Inject
    EntityManager entityManager;

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
        return entityManager.createNamedQuery("Projects.findAll", Project.class).getResultList();
    }

    @GET
    @Path("/{projectId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Project getProjectById(@PathParam("projectId") Long projectId) {

        try {
            return entityManager.createNamedQuery("Projects.findByProjectId", Project.class).setParameter("projectId", projectId).getSingleResult();
        } catch (NoResultException nre) {
            throw new WebApplicationException("Project with id: " + projectId + " does not exist.", 404);
        }

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response createProject(Project project) {
        if (project.getProjectId() == null) {
            throw new WebApplicationException("A project cannot be created without Project Id", 400);
        }

        entityManager.persist(project);
        return Response.status(201).entity(project).build();
    }

    @PUT
    @Path("{projectId}/changeStatus")
    public Project withdrawal(@PathParam("projectId") Long projectId, String status) {
        Project project;
        try {
            project = getProjectById(projectId);
            project.setProjectStatus(status);
        } catch (NoResultException nre) {
            throw new WebApplicationException("Project with id: " + projectId + " does not exist.", 404);
        }
        return project;
    }

    @DELETE
    @Path("{projectId}")
    public Response deleteProject(@PathParam("projectId") Long projectId) {
        Project projectToClose;
        try {
        projectToClose = getProjectById(projectId);
        } catch (NoResultException nre) {
            throw new WebApplicationException("Project with id: " + projectId + " does not exist.", 404);
        }
        projectToClose.setProjectStatus(ProjectStatus.CLOSED.toString());
        return Response.noContent().build();
    }

}