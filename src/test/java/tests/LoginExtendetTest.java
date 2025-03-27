package tests;

import models.lombok.LoginBodyLombokModel;
import models.lombok.LoginResponseLombokModel;
import models.pogo.LoginBodyModel;
import models.pogo.LoginResponseModel;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginExtendetTest {

     /*
    1. Make request (POST) to https://reqres.in/api/login
        with body { "email": "eve.holt@reqres.in", "password": "cityslicka" }
    2. Get response { "token": "QpwL5tke4Pnpja7X4" }
    3. Check "token" is "QpwL5tke4Pnpja7X4" and status code 200
  */


    @Test
    void successFullLoginBadPracticeTest() {
        String authData = "{\"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\"}";
        given() // Дано
                        .body(authData)
                        .contentType(JSON)
                        .log().uri()

                .when() // Действие

                        .post("https://reqres.in/api/login")

                .then()    // Что мы делаем, когда проверяем

                        .log().status()
                        .log().body()
                        .statusCode(200)
                        .body("token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    void successFullLoginPogoTest() {
        LoginBodyModel authData = new LoginBodyModel();
        authData.setEmail("eve.holt@reqres.in");
        authData.setPassword("cityslicka");

        LoginResponseModel response = given() // Дано
                .body(authData)
                .contentType(JSON)
                .log().uri()

                .when() // Действие

                .post("https://reqres.in/api/login")

                .then()    // Что мы делаем, когда проверяем

                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(LoginResponseModel.class);

        assertEquals("QpwL5tke4Pnpja7X4", response.getToken());
    }

    @Test
    void successFullLoginLombokTest() {
        LoginBodyLombokModel authData = new LoginBodyLombokModel();
        authData.setEmail("eve.holt@reqres.in");
        authData.setPassword("cityslicka");

        LoginResponseLombokModel response = given() // Дано
                .body(authData)
                .contentType(JSON)
                .log().uri()

                .when() // Действие

                .post("https://reqres.in/api/login")

                .then()    // Что мы делаем, когда проверяем

                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(LoginResponseLombokModel.class);

        assertEquals("QpwL5tke4Pnpja7X4", response.getToken());
    }

}
