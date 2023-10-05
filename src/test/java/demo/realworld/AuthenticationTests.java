package demo.realworld;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
class AuthenticationTests {

	@Value("${realworld.auth.email}")
	private String userEmail;

	@Value("${realworld.auth.password}")
	private String userPassword;

	@BeforeAll
	public static void setup() {
		RestAssuredConfig.setup();
	}
	@Test
	void givenValidUser_whenLogin_thenSuccess() throws JSONException {
		JSONObject user = new JSONObject();
		user.put("email", userEmail);
		user.put("password", userPassword);

		JSONObject requestBody = new JSONObject();
		requestBody.put("user", user);

		given()
				.contentType(ContentType.JSON)
				.body(requestBody.toString())
		.when()
				.post("/users/login")
		.then()
				.body("user.token", notNullValue())
				.statusCode(HttpStatus.SC_OK)
		;
	}

}
