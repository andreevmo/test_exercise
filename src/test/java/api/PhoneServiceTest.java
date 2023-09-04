package api;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class PhoneServiceTest {

    private final String phoneNumber = "[" + System.getenv("number") + "]";
    private final RequestSpecification request = given().baseUri("https://cleaner.dadata.ru/");

    @Test
    void testPhone() {
        Response response = request
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .headers(
                        "Authorization", "Token " + System.getenv("token"),
                        "X-Secret", System.getenv("secret")
                )
                .body(phoneNumber)
                .post("api/v1/clean/phone")
                .andReturn();

        JsonPath json = response.jsonPath();

        assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);
        assertThat(json.get("source").toString()).isEqualTo(phoneNumber);
    }
}
