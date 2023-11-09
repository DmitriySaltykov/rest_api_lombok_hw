package api_lombok_test.test;


import api_lombok_test.models.LoginBodyModel;
import api_lombok_test.models.LoginResponseModel;
import api_lombok_test.models.LoginErrorModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.BaseSpec.*;



public class LoginTest extends TestBase {
    @Test
    @DisplayName("Successful login test")
    void successfulLoginTest() {

        LoginBodyModel authData = new LoginBodyModel();
        authData.setEmail("eve.holt@reqres.in");
        authData.setPassword("cityslicka");

        LoginResponseModel response = step("Login user", () ->
                given(baseRequestSpec)
                        .body(authData)
                        .when()
                        .post("/login")
                        .then()
                        .spec(loginResponseSpec)
                        .extract().as(LoginResponseModel.class));

        step("Checking the answer", () ->
                assertEquals("QpwL5tke4Pnpja7X4", response.getToken())
        );


    }


    @Test
    @DisplayName("Unsuccessful login test with empty login")
    void unsuccessfulEmptyLoginTest() {

        LoginBodyModel authData = new LoginBodyModel();

        authData.setPassword("cityslicka");

        LoginErrorModel response = step("Login user", () ->
                given(baseRequestSpec)
                        .body(authData)
                        .when()
                        .post("/login")
                        .then()
                        .spec(loginResponse400ErrorSpec)
                        .extract().as(LoginErrorModel.class));

        step("Checking the answer", () ->
                assertEquals("Missing email or username", response.getError())
        );


    }
    @Test
    @DisplayName("Unsuccessful login test with empty password")
    void unsuccessfulEmptyPasswordTest() {

        LoginBodyModel authData = new LoginBodyModel();

        authData.setEmail("eve.holt@reqres.in");

        LoginErrorModel response = step("Login user", () ->
                given(baseRequestSpec)
                        .body(authData)
                        .when()
                        .post("/login")
                        .then()
                        .spec(loginResponse400ErrorSpec)
                        .extract().as(LoginErrorModel.class));

        step("Checking the answer", () ->
                assertEquals("Missing password", response.getError())
        );


    }
    @Test
    @DisplayName("Unsuccessful login test with invalid values")
    void unsuccessfulTest() {

        LoginBodyModel authData = new LoginBodyModel();

        authData.setEmail("dim@vit.in");
        authData.setPassword("ratata");

        LoginErrorModel response = step("Login user", () ->
                given(baseRequestSpec)
                        .body(authData)
                        .when()
                        .post("/login")
                        .then()
                        .spec(loginResponse400ErrorSpec)
                        .extract().as(LoginErrorModel.class));

        step("Checking the answer", () ->
                assertEquals("user not found", response.getError())
        );


    }


}
