package quarkus.projects;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class ProjecctResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/projects")
          .then()
             .statusCode(200)
             .body(is("Hello RESTEasy"));
    }

}