package tests;
import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import resources.ReusableMethods;


public class AddPlaceTestInXML {

	Properties prop = new Properties();

	@Test
	public void postData() throws IOException {

		FileInputStream fis = new FileInputStream("../google-place-api/src/resources/env.properties");

		prop.load(fis);

		String postData = GenerateStringFromResource("../google-place-api/src/resources/PostData.xml");

		RestAssured.baseURI = prop.getProperty("HOST");

		Response resp = given().queryParam("key", prop.getProperty("KEY")).body(postData).when()
				.post("/maps/api/place/add/xml").then().assertThat().statusCode(200).and().contentType(ContentType.XML).

				extract().response();

		// using reusable method to convert the raw to string and string to xml
		XmlPath x = ReusableMethods.rawToXml(resp);

		System.out.println(x.get("PlaceAddResponse.place_id"));
	}

	public static String GenerateStringFromResource(String path) throws IOException {
		return new String(Files.readAllBytes(Paths.get(path)));
	}
}
