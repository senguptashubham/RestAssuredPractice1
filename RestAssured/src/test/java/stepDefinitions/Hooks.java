package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	@Before("@DeletePlace")
	public void generatePlaceId() throws IOException {
		
		StepDefinitions sd = new StepDefinitions();
		sd.add_place_payload_with(34.667, 72.132, "India", "English", "Data by Hooks", "129");
		sd.user_calls_with_http_request("AddPlaceAPI", "POST");
		sd.user_verifies_added_place_has_the_same("name", "Data by Hooks", "GetPlaceAPI");
		
	}

}
