package tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class EchoTest extends TestBase {
    // Сервер слушает порт 8080, адрес / (т.е. урл для запроса http://localhost:8080/), запросы типа POST
    /*
    Требования к серверу
1. Сервер отвечает на POST запросы телом запроса с cохранением Content-Type
2. Сервер поддерживает тело до 4мб
3. Время ответа сервера не должно превышать 500мс

     */

    @Test
    void echoTestType() {

                given()
                        .contentType("text/plain; charset=UTF-8")
                        .cookie("")
                        .body("Lorem ipsum dolor sit amet, " +
                                "consectetur adipiscing elit, " +
                                "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                                "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat." +
                                " Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. " +
                                "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.")
                        .when()
                        .post()
                        .then()
                        .statusCode(200)
                        .log().body()
                        .extract().response();

    }
}


