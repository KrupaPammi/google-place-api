import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;

import files.resources;


public class BaseTest {

	
	@BeforeTest
	public void Test() throws IOException {
		
		Properties prop = new Properties();
		
		FileInputStream fis = new FileInputStream("../google-place-api/src/files/env.properties");
		
		prop.load(fis);
		
		RestAssured.baseURI=prop.getProperty("HOST");
		
		given().
		        param("location","-33.8670522,151.1957362").
		        param("radius","1500").
		        queryParam("key",prop.getProperty("KEY")).
		when().
				get(resources.getNearbySearchPlace()).
		then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
		//1st validation - checking status code
		//2nd validation - checking if the ContentType is in Json format or not		
		//3rd validation - checking the body
		body("results[0].name",equalTo("Sydney")).and().body("results[0].place_id", equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM"))
		//4th validation - checking the header
		.and().header("server","scaffolding on HTTPServer2");
	}

}
