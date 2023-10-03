package api_lombok_test.test;


import api_lombok_test.models.LoginBodyModel;
import api_lombok_test.models.LoginResponseModel;
import api_lombok_test.models.LoginErrorModel;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class LoginTest extends TestBase {
    @Test
    void successfulLoginTest() {

        LoginBodyModel authData = new LoginBodyModel();
        authData.setEmail("eve.holt@reqres.in");
        authData.setPassword("cityslicka");

        LoginResponseModel response = given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .body(authData)
                .when()
                .post("/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(LoginResponseModel.class);

        assertEquals("QpwL5tke4Pnpja7X4", response.getToken());


    }


    @Test
    void unsuccessfulTest() {

        LoginBodyModel authData = new LoginBodyModel();
        authData.setEmail("eve.holt@reqres.in");
        authData.setPassword("cityslicka");

        LoginErrorModel response = given()
                .log().uri()
                .log().method()
                .log().body()
                .body(authData)
                .when()
                .post("/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .extract().as(LoginErrorModel.class);
        assertEquals("Missing email or username", response.getError());

    }



    @Test
    void emptyPasswordTest() {
        LoginBodyModel authData = new LoginBodyModel();
        authData.setEmail("eve.holt@reqres.in");
        authData.setPassword("");

        LoginErrorModel response = given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .body(authData)
                .when()
                .post("/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .extract().as(LoginErrorModel.class);
        assertEquals("Missing password", response.getError());
    }

    @Test
    void emptyEmailTest() {
        LoginBodyModel authData = new LoginBodyModel();
        authData.setEmail("");
        authData.setPassword("cityslicka");

        LoginErrorModel response = given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .body(authData)
                .when()
                .post("/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .extract().as(LoginErrorModel.class);
        assertEquals("Missing email or username", response.getError());
    }
}
