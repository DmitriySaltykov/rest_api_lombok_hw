package test;

import api_lombok_test.models.CreatUserBodyModel;
import api_lombok_test.models.CreateUserResponseModel;
import api_lombok_test.models.LoginBodyModel;
import api_lombok_test.models.LoginResponseModel;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class CreateTest extends TestBase {
    @Test
    void successCreateUserTest() {


        CreatUserBodyModel createData = new CreatUserBodyModel();
        createData.setName("morpheus");
        createData.setJob("leader");

        CreateUserResponseModel response = given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .body(createData)
                .when()
                .post("/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .extract().as(CreateUserResponseModel.class);


        assertEquals("morpheus", response.getName());
        assertEquals("leader", response.getJob());
        assertNotNull(response.getId());
        assertNotNull(response.getCreatedAt());


    }

    @Test
    void createUserWithEmptyNameTest() {

        CreatUserBodyModel createData = new CreatUserBodyModel();
        createData.setName("");
        createData.setJob("leader");

        CreateUserResponseModel response = given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .body(createData)
                .when()
                .post("/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .extract().as(CreateUserResponseModel.class);

        assertEquals("leader", response.getJob());
        assertNotNull(response.getId());
        assertNotNull(response.getCreatedAt());

    }

    @Test
    void createUserWithEmptyJobTest() {

        CreatUserBodyModel createData = new CreatUserBodyModel();
        createData.setName("morpheus");
        createData.setJob("leader");

        CreateUserResponseModel response = given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .body(createData)
                .when()
                .post("/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .extract().as(CreateUserResponseModel.class);


        assertEquals("morpheus", response.getName());
        assertNotNull(response.getId());
        assertNotNull(response.getCreatedAt());

    }

    @Test
    void successUpdateUserTest() {
        String createData = "{\"name\": \"morpheus\",\"job\": \"zion resident\"}";

        given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .body(createData)
                .when()
                .post("/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("morpheus"))
                .body("job", is("zion resident"))
                .body("createdAt", is(not(empty())));
    }


}
