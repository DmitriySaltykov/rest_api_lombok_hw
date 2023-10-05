package api_lombok_test.test;

import api_lombok_test.models.UsersDataResponseModel;
import api_lombok_test.models.UsersResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.UserSpec.UserRequestSpec;
import static specs.UserSpec.UserResponseSpec;

public class SingleUserTest {


    @Test
    @DisplayName("Get a list of all users")
    void getListUser() {
        UsersResponseModel response = step("Get a list of all users request", () ->
                given(UserRequestSpec)
                        .when()
                        .get("/users?page=2")
                        .then()
                        .spec(UserResponseSpec)
                        .extract().as(UsersResponseModel.class));

        step("Verify response", () -> {
            List<UsersDataResponseModel> data = response.getData();
            assertEquals(6, response.getPerPage());
            assertEquals("Byron", data.get(3).getFirstName());
            assertEquals("Lawson", data.get(0).getLastName());
            assertEquals(8, response.getData().get(1).getId());
            assertEquals("https://reqres.in/#support-heading", response.getSupport().getUrl());
            assertEquals("To keep ReqRes free, contributions towards server costs are appreciated!", response.getSupport().getText());
        });

    }
}
