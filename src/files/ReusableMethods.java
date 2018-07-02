package files;

import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ReusableMethods {

	// convert raw to string and string to xml
	public static XmlPath rawToXml(Response r) {
		String resp = r.asString();
		XmlPath x = new XmlPath(resp);
		return x;
	}

	// convert raw to string and string to json
	public static JsonPath rawToJson(Response r) {
		String resp = r.asString();
		JsonPath j = new JsonPath(resp);
		return j;
	}

}
