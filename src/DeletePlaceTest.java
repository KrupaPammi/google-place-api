import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import files.Payload;
import files.resources;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class DeletePlaceTest {

	Properties prop = new Properties();

	@BeforeTest
	public void getData() throws IOException {
		FileInputStream fis = new FileInputStream("../google-place-api/src/files/env.properties");

		prop.load(fis);
	}

	@Test
	public void addAndDeletePlace() {

		RestAssured.baseURI = prop.getProperty("HOST");

		Response res = given().queryParam("key", prop.getProperty("KEY")).

				body(Payload.getPostBody()).when().post(resources.addPostRequest()).// resources
				then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and()
				.body("status", equalTo("OK")).

				extract().response();

		String responseString = res.asString();

		System.out.println(responseString);

		JsonPath js = new JsonPath(responseString);

		String placeId = js.get("place_id");

		System.out.println(placeId);

		// delete place
		given().queryParam("key", prop.getProperty("KEY")).

				body("{" + "\"place_id\": \"" + placeId + "\"" + "}").when().post(resources.deletePlace()).then()
				.assertThat().statusCode(200).and().contentType(ContentType.JSON).and().body("status", equalTo("OK"));

	}
}
