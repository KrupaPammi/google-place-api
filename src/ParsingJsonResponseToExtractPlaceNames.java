import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ParsingJsonResponseToExtractPlaceNames {

	@Test
	public void test() {
		
		RestAssured.baseURI = "https://maps.googleapis.com";
		
		Response res = given().
		param("location", "-33.8670522,151.1957362").
		param("radius", "1500").
		param("key", "AIzaSyAQeFVvqS76WaAWkwGkBv8SpHF5G_qUko4").
		when().
		get("/maps/api/place/nearbysearch/json").
		then().
		assertThat().statusCode(200).and().contentType(ContentType.JSON).
		and().body("results[0].name",equalTo("Sydney")).
		and().body("results[0].place_id", equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM")).
		and().header("server", "scaffolding on HTTPServer2").and().
		//extract the response
		extract().response();
		//convert the raw to string and string to json
		JsonPath js=ReusableMethods.rawToJson(res);
		int count=js.get("results.size()");//here as size is a number so the return type should be "int count"
		//print all place names
		for(int i=0; i<count; i++)
		{
			System.out.println(js.get("results["+i+"].name"));
		}
		
		System.out.println(count);
	}

}