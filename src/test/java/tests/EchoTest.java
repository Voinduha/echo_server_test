package tests;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static filters.CustomLogFilter.customLogFilter;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;

public class EchoTest extends TestBase {
    // Сервер слушает порт 8080, адрес / (т.е. урл для запроса http://localhost:8080/), запросы типа POST
    /*
    Требования к серверу
1. Сервер отвечает на POST запросы телом запроса с cохранением Content-Type
2. Сервер поддерживает тело до 4мб
3. Время ответа сервера не должно превышать 500мс
     */

    @Test
    void echoTestTypeWithAllConditions() {

        given()
                .filter(customLogFilter().withCustomTemplates())
                .contentType("text/plain; charset=UTF-8")
                .basePath("/src/test/resources/")
                .multiPart("avatar", avatarFile, "application/octet-stream")
        .when()
                .post()
        .then()
                .statusCode(200)
                .log().body()
                .and().time(lessThan(5000L));

    }

    @Test
    void withAllLogTestWithContentType() {
        given()
                .filter(customLogFilter().withCustomTemplates())
                .contentType("text/plain; charset=UTF-8")
                .body("")
        .when()
                .post()
        .then()
                .statusCode(200)
                .log().body()
                .and().time(lessThan(5000L));
    }

    @Test
    void withFileMoreThenFourMb() {
        given()
                .filter(customLogFilter().withCustomTemplates())
                .contentType("text/plain; charset=UTF-8"))
                .basePath("/src/test/resources/")
                .multiPart("avatar4001", avatarFile, "application/octet-stream")
        .when()
                .post()
        .then()
                .statusCode(200)
                .log().body()
                .and().time(lessThan(5000L));
    }
    
     @Test
    void withFileLessThenFourMb() {
        given()
                .filter(customLogFilter().withCustomTemplates())
                .contentType("text/plain; charset=UTF-8"))
                .basePath("/src/test/resources/")
                .multiPart("avatar3097", avatarFile, "application/octet-stream")
        .when()
                .post()
        .then()
                .statusCode(200)
                .log().body()
                .and().time(lessThan(5000L));
    }
    
     @Test
    void withTimeMoreThenFiveMc() {
        given()
                .filter(customLogFilter().withCustomTemplates())
                .contentType("text/plain; charset=UTF-8"))
        .when()
                .post()
        .then()
                .statusCode(200)
                .log().body()
                .and().time(lessThan(6000L));
    }
}



