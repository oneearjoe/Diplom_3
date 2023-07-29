package config;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class UserAPI {

    private static final String REGISTER = "/api/auth/register";
    private static final String AUTH_USER = "api/auth/user";

    @Step("Регистрация нового пользователя")
    public ValidatableResponse registerUser(User user) {
        return given()
                .spec(AppConfig.getSpecification())
                .and()
                .body(user)
                .when()
                .post(REGISTER)
                .then()
                .log().all();
    }

    @Step("Удаление пользователя")
    public void deleteUser(String accessToken) {
        if (accessToken == null) {
            return;
        }
        given()
                .header("Authorization", accessToken)
                .spec(AppConfig.getSpecification())
                .when()
                .delete(AUTH_USER)
                .then()
                .statusCode(202)
                .log().all();
    }
}
