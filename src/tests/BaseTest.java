package tests;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;



public class BaseTest {

	static RequestSpecification reqSpecification;
	
	@BeforeTest
	public static void init() throws IOException {
		
		Properties prop = new Properties();
		
		FileInputStream fis = new FileInputStream("../google-place-api/src/resources/env.properties");
		
		prop.load(fis);
		
		RestAssured.baseURI=prop.getProperty("HOST");
		
	    reqSpecification = RestAssured.given().queryParam("key",prop.getProperty("KEY"));
		
	}

}
