package test;

import api_lombok_test.models.CreatUserBodyModel;
import api_lombok_test.models.CreateUserResponseModel;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PatchTest extends TestBase {
    @Test
    void UpdateInfoTest() {
        CreatUserBodyModel authData = new CreatUserBodyModel();
        authData.setName("morpheus");
        authData.setJob("leader");

        CreateUserResponseModel response =given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .body(authData)
                .when()
                .patch("/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(CreateUserResponseModel.class);


        assertEquals("morpheus", response.getName());
        assertEquals("leader", response.getJob());


    }
}
