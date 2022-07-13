package quarkus.projects;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import quarkus.projects.beans.Project;

import java.util.List;

import static io.restassured.RestAssured.given;


import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProjectResourceTest {

    @Test
    void testGetAllProjectsEndpoint() {
        Response result = given()
          .when().get("/projects")
          .then()
             .statusCode(200)
             .body(
                 containsString("Sample Project 1"),
                 containsString("Sample Project 2"),
                 containsString("Sample Project 3")
             )
            .extract().response();

        List<Project> projectList = result.jsonPath().getList("$");
        assertThat(projectList, not(empty()));
        assertThat(projectList, hasSize(4));
    }

    @Test
    void testGetProjectByIdEndpoint() {
        Project retrievedProject = given()
                .when().get("/projects/{projectId}", 8943547)
                .then()
                .statusCode(200)
                .extract().as(Project.class);

        assertThat(retrievedProject.getProjectId(), equalTo(8943547L));
        assertThat(retrievedProject.getProjectName(), equalTo("Sample Project 3"));
    }

}