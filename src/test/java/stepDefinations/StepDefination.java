package stepDefinations;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

import POJO.AddPlace;
import POJO.Location;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIresource;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefination extends Utils {

	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	JsonPath jsonPath;
	String place_id;
	TestDataBuild data = new TestDataBuild();

	@Given("Add Place Payload")
	public void add_place_payload() throws Exception {

		res = given().spec(requestSpecification()).body(data.addPlacePayLoad());

		// response= given().log().all().queryParam("key",
		// "qaclick123").header("Content-Type", "application/json")
		// .body(p).when().post("/maps/api/place/add/json").then().log().all().assertThat().statusCode(200)
		// .extract().response();

	}

	@Given("Add Place Payload with {string}  {string}  {string}")
	public void add_place_Payload_With(String name, String language, String address) throws Exception {

		res = given().spec(requestSpecification()).body(data.addPlacePayLoad(name, language, address));

	}

	@When("user calls {string} with post http request")
	public void user_calls_with_post_http_request(String resource) {

		APIresource resourceAPI = APIresource.valueOf(resource);

		System.out.println(resourceAPI.getResource());

		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		response = res.when().post(resourceAPI.getResource()).then().spec(resspec).extract().response();

		String responseString = response.asString();
		System.out.println("Response : " + responseString);

	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_post_http_request1(String resource, String httpMothod) {

		APIresource resourceAPI = APIresource.valueOf(resource);

		System.out.println(resourceAPI.getResource().toString());

		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		response = res.when().post(resourceAPI.getResource()).then().spec(resspec).extract().response();

		String responseString = response.asString();
		System.out.println("Response : " + responseString);

	}

	@Then("API call got sucess with status code {int}")
	public void api_call_got_sucess_with_status_code(Integer int1) {

		assertEquals(response.getStatusCode(), 200);

	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedValue) {

		assertEquals(getJsonValue(response, keyValue), expectedValue);

	}

	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_Id_created_maps_to_using(String expName, String resource) throws Throwable {

		String place_id = getJsonValue(response, "place_id");
		res = given().spec(requestSpecification()).queryParam("place_id", place_id);
		user_calls_with_post_http_request1(resource, "GET");
		String actName=getJsonValue(response, "name");
		assertEquals(actName, expName);

	}
	
	

}
