package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {

	RequestSpecification req;

	public RequestSpecification requestSpecification() throws Exception {

		if (req == null) {

			RestAssured.baseURI = "https://rahulshettyacademy.com";

			PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));

			req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl")).addQueryParam("key", "qaclick123")
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log)).setContentType(ContentType.JSON).build();

			return req;

		} else {
			return req;
		}
	}

	public static String getGlobalValue(String key) throws Exception {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\sonu.kumar1\\eclipse-workspace\\API_FRAMEWORK\\src\\test\\java\\resources\\global.properties");
		prop.load(fis);

		return prop.get(key).toString();

	}

	public String getJsonValue(Response response, String key) {

		String res = response.asString();

		JsonPath js = new JsonPath(res);

		return js.get(key).toString();

	}

}
