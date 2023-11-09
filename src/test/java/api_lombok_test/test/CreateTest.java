package api_lombok_test.test;

import api_lombok_test.models.CreatUserBodyModel;
import api_lombok_test.models.CreateUserResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static specs.BaseSpec.*;


public class CreateTest extends TestBase {
    @Test
    @DisplayName("Successful create user test")
    void successCreateUserTest() {

        CreatUserBodyModel createData = new CreatUserBodyModel();
        createData.setName("morpheus");
        createData.setJob("leader");

        CreateUserResponseModel response = step("Create user ", () ->
                given(baseRequestSpec)
                        .body(createData)
                        .when()
                        .post("/users")
                        .then()
                        .spec(createResponseSpec)
                        .extract().as(CreateUserResponseModel.class));
        step("Сhecking the answer", () -> {
            assertEquals("morpheus", response.getName());
            assertEquals("leader", response.getJob());
            assertNotNull(response.getId());
            assertNotNull(response.getCreatedAt());
        });


    }

    @Test
    @DisplayName("Create user with empty job")
    void createUserWithEmptyJobTest() {

        CreatUserBodyModel createData = new CreatUserBodyModel();
                createData.setJob("leader");

        CreateUserResponseModel response = step("A user has been created with an empty name ", () ->
                given(baseRequestSpec)
                        .body(createData)
                        .when()
                        .post("/users")
                        .then()
                        .spec(createResponseSpec)
                        .extract().as(CreateUserResponseModel.class));

        step("Сhecking the answer", () -> {
                       assertEquals("leader", response.getJob());
            assertNotNull(response.getId());
            assertNotNull(response.getCreatedAt());
        });

    }
    @Test
    @DisplayName("Create user with empty name")
    void createUserWithEmptyNameTest() {

        CreatUserBodyModel createData = new CreatUserBodyModel();
        createData.setName("morpheus");

        CreateUserResponseModel response = step("A user has been created with an empty name ", () ->
                given(baseRequestSpec)
                        .body(createData)
                        .when()
                        .post("/users")
                        .then()
                        .spec(createResponseSpec)
                        .extract().as(CreateUserResponseModel.class));

        step("Сhecking the answer", () -> {
            assertEquals("morpheus", response.getName());
            assertNotNull(response.getId());
            assertNotNull(response.getCreatedAt());
        });

    }
    @Test
    @DisplayName("Create user with empty name")
    void uncreateUserTest() {

        CreatUserBodyModel createData = new CreatUserBodyModel();


        CreateUserResponseModel response = step("A user has been created with an empty name ", () ->
                given(baseRequestSpec)
                        .body(createData)
                        .when()
                        .post("/users")
                        .then()
                        .spec(createResponseSpec)
                        .extract().as(CreateUserResponseModel.class));

        step("Сhecking the answer", () -> {

            assertNotNull(response.getId());
            assertNotNull(response.getCreatedAt());
        });

    }

}
