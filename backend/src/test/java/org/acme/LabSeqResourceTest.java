package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class LabSeqResourceTest {
    @Test
    void testLabSeqEndpoint() {
        given()
          .when().get("/labseq")
          .then()
             .statusCode(200)
             .body(is("Hello from Quarkus REST"));
    }

}