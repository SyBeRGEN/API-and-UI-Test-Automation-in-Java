package com.apiTests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ApiTests {

    private static RequestSpecification requestSpec;
    private static ResponseSpecification responseSpec;

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://reqres.in";

        requestSpec = given()
                .contentType("application/json")
                .baseUri(RestAssured.baseURI);

        responseSpec = expect()
                .statusCode(200);
    }

    // Кейс 1.1: Успешная регистрация
    @Test
    public void successRegistrationTest() {
        String requestBody = "{\"email\": \"eve.holt@reqres.in\", \"password\": \"pistol\"}";

        given()
                .spec(requestSpec)
                .body(requestBody)
                .when()
                .post("/api/register")
                .then()
                .spec(responseSpec)
                .body("token", notNullValue());
    }

    // Кейс 1.2: Ошибка регистрации из-за отсутствия пароля
    @Test
    public void failedRegistrationTest() {
        String requestBody = "{\"email\": \"eve.holt@reqres.in\"}";

        given()
                .spec(requestSpec)
                .body(requestBody)
                .when()
                .post("/api/register")
                .then()
                .statusCode(400)
                .body("error", equalTo("Missing password"));
    }

    // Кейс 2: Получение списка пользователей страницы 2 и проверка на @reqres.in
    @Test
    public void getUserListTest() {
        given()
                .spec(requestSpec)
                .when()
                .get("/api/users?page=2")
                .then()
                .spec(responseSpec)
                .body("data.email", everyItem(endsWith("@reqres.in")));
    }

    // Кейс 3: Удаление второго пользователя и проверка статус-кода 204
    @Test
    public void deleteUserTest() {
        given()
                .spec(requestSpec)
                .when()
                .delete("/api/users/2")
                .then()
                .statusCode(204);
    }

    // Кейс 4: Обновление информации о пользователе
    @Test
    public void patchUserTest() {
        String requestBody = "{\"name\": \"morpheus\", \"job\": \"zion resident\"}";

        Response response = given()
                .spec(requestSpec)
                .body(requestBody)
                .when()
                .patch("/api/users/2");

        response.then()
                .spec(responseSpec)
                .body("name", equalTo("morpheus"))
                .body("job", equalTo("zion resident"));

        String updatedAt = response.jsonPath().getString("updatedAt");
        String currentDate = LocalDate.now().toString();

        assert updatedAt.startsWith(currentDate);

    }
}
