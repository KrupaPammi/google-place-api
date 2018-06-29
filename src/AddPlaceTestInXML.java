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


public class AddPlaceTestInXML {

	Properties prop = new Properties();
	
	@Test
	public void postData() throws IOException {
	
		FileInputStream fis = new FileInputStream("../RestAPISampleProject/src/files/env.properties");
		
		prop.load(fis);
		
	String postData = GenerateStringFromResource("../RestAPISampleProject/src/files/PostData.xml");
		
	RestAssured.baseURI=prop.getProperty("HOST");
	
	Response resp = given().
	queryParam("key", prop.getProperty("KEY")).
	body(postData).
	when().
	post("/maps/api/place/add/xml").
	then().assertThat().statusCode(200).and().contentType(ContentType.XML).
	
	extract().response();
  
	String res=resp.asString();
	System.out.println(res);
	
	XmlPath x = new XmlPath(res);
	System.out.println(x.get("PlaceAddResponse.place_id"));
	}
	
	
	public static String GenerateStringFromResource(String path) throws IOException
	{
		return new String(Files.readAllBytes(Paths.get(path)));
	}
}
