package tests;
import static org.hamcrest.Matchers.equalTo;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import resources.Payload;
import resources.endpointResources;
import static io.restassured.RestAssured.given;

public class AddPlaceTest extends BaseTest
{

	@Test
	public void postRequest() 
	{

		RestAssured.baseURI = "https://maps.googleapis.com";

		given().queryParam("key", "AIzaSyAQeFVvqS76WaAWkwGkBv8SpHF5G_qUko4").given().body(Payload.getPostBody()).when()
				.post(endpointResources.addPostRequest()).then().assertThat().statusCode(200).and().contentType(ContentType.JSON)
				.and().body("status", equalTo("OK"));


	}
}
