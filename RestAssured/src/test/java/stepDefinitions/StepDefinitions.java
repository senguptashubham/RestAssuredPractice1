package stepDefinitions;

import io.cucumber.java.en.Given;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import Resources.ApiResources;
import Resources.TestData;
import Resources.Utils;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class StepDefinitions extends Utils{
	RequestSpecification apiReq;
	Response apiRes;
	TestData data = new TestData();
	Response response;
	static String placeId;
	
	@Given("Add Place Payload with {double} {double} {string} {string} {string} {string}")
	public void add_place_payload_with(Double lat, Double lng, String address, String language, String name, String phoneNumber) throws IOException {
		
		apiReq = given().spec(requestSpecBuild()).body(data.addPlaceDataBuild(lat,lng,address,language,name,phoneNumber));
	    
	}
	@When("User calls {string} with {string} http request")
	public void user_calls_with_http_request(String api, String httpMethod) {
		
		ApiResources apiResource = ApiResources.valueOf(api);
		
		if(httpMethod.equalsIgnoreCase("POST"))
			apiRes = apiReq.when().post(apiResource.getResource());
		else if(httpMethod.equalsIgnoreCase("DELETE"))
			apiRes = apiReq.when().delete(apiResource.getResource());
		else if(httpMethod.equalsIgnoreCase("GET"))
			apiRes = apiReq.when().get(apiResource.getResource());
		else if(httpMethod.equalsIgnoreCase("PUT"))
			apiRes = apiReq.when().put(apiResource.getResource());
	    
	}
	@Then("API call is successful with status code {int}")
	public void api_call_is_successful_with_status_code(Integer statusCode) {
		
	    assertEquals((int)statusCode, apiRes.getStatusCode());
		
	}
	@Then("{string} in Response body is {string}")
	public void in_response_body_is(String key, String value) {
		
		assertEquals(value,getKeyValue(apiRes,key));
	  
	}
	
	@Then("User verifies place_id created has the {string} as {string} using {string}")
	public void user_verifies_added_place_has_the_same(String key, String value, String apiMethod) throws IOException {
	    
		//build requestSpec, call get place api, validate response with key
		placeId = getKeyValue(apiRes,"place_id");
		apiReq = given().spec(requestSpecBuild()).queryParam("place_id", placeId);
		user_calls_with_http_request(apiMethod, "GET");
		assertEquals(value,getKeyValue(apiRes,key));
		
	}
	
	@Given("Delete place payload")
	public void delete_place_payload() throws IOException {
	    
		apiReq = given().spec(requestSpecBuild()).body(data.deletePlaceDataBuild(placeId));
		
	}


}
