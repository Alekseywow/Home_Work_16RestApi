package tests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class LoginTest {

     /*
    1. Make request (POST) to https://reqres.in/api/login
        with body { "email": "eve.holt@reqres.in", "password": "cityslicka" }
    2. Get response { "token": "QpwL5tke4Pnpja7X4" }
    3. Check "token" is "QpwL5tke4Pnpja7X4" and status code 200
  */


    @Test
    void successFullLoginTest() {
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
    void missingPaswordLoginTest() {
        String authData = "{\"password\": \"cityslicka\"}";
        given() // Дано
                .body(authData)
                .contentType(JSON)
                .log().uri()

                .when() // Действие

                .post("https://reqres.in/api/login")

                .then()    // Что мы делаем, когда проверяем

                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing email or username"));
    }

    @Test
    void unsuccessFullLoginMissingPasswordTest() {
        String authData = "{\"email\": \"eve.holt@reqres.in\"}";
        given() // Дано
                .body(authData)
                .contentType(JSON)
                .log().uri()

                .when() // Действие

                .post("https://reqres.in/api/login")

                .then()    // Что мы делаем, когда проверяем

                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing password"));
    }

    @Test
    void unsuccessFullLogin400UserTest() {
        String authData = "{\"email\": \"eve23424.holt@reqres.in\", \"password\": \"cityslicka\"}";
        given() // Дано
                .body(authData)
                .contentType(JSON)
                .log().uri()

                .when() // Действие

                .post("https://reqres.in/api/login")

                .then()    // Что мы делаем, когда проверяем

                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("user not found"));
    }

    @Test
    void unsuccessFullLogin400Test() {
        String authData = "{\"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\"}";
        given() // Дано
                .body(authData)
                .log().uri()

                .when() // Действие

                .post("https://reqres.in/api/login")

                .then()    // Что мы делаем, когда проверяем

                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing email or username"));
    }




    @Test
    void unsuccessFullLogin415Test() {
        given()
                .log().uri()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(415);
    }

}
