package api_lombok_test.test;

import api_lombok_test.models.UsersDataResponseModel;
import api_lombok_test.models.UsersResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.BaseSpec.*;


public class SingleUserTest extends TestBase {


    @Test
    @DisplayName("Get a list of all users")
    void getListUser() {


        UsersResponseModel response = step("Login user", () ->
                given(baseRequestSpec)
                        .when()
                        .get("/users?page=2")
                        .then()
                        .spec(userResponseSpec)
                        .extract().as(UsersResponseModel.class));

        step("Verify response", () -> {
            List<UsersDataResponseModel> data = response.getData();
            assertEquals(6, response.getPerPage());
            assertEquals("Byron", data.get(3).getFirstName());
            assertEquals("Edwards", data.get(4).getLastName());
            assertEquals(8, response.getData().get(1).getId());
            assertEquals("https://reqres.in/#support-heading", response.getSupport().getUrl());
            assertEquals("To keep ReqRes free, contributions towards server costs are appreciated!", response.getSupport().getText());
        });

    }
}
