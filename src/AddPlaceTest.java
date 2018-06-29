import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import files.Payload;
import files.resources;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class AddPlaceTest  {

	@Test
	public void postData() {
		
	RestAssured.baseURI="https://maps.googleapis.com";
	
	given().
	queryParam("key", "AIzaSyAQeFVvqS76WaAWkwGkBv8SpHF5G_qUko4").
	given().body(Payload.getPostData()).
	when().
	post(resources.addPostData()).
	then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
	body("status", equalTo("OK"));
  
	
	}
}
