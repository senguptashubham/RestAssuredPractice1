package Resources;

import pojo.AddPlacePayload;
import pojo.PlaceLocation;

public class TestData {
	
	public AddPlacePayload addPlaceDataBuild(Double lat, Double lng, String address, String language, String name, String phoneNumber) {
		
		PlaceLocation loc = new PlaceLocation();
		loc.setLat(lat);
		loc.setLng(lng);
		
		String[] types = {"home", "residency"};
		
		AddPlacePayload payload = new AddPlacePayload();
		payload.setAccuracy(50);
		payload.setAddress(address);
		payload.setLanguage(language);
		payload.setLocation(loc);
		payload.setName(name);
		payload.setPhone_number(phoneNumber);
		payload.setTypes(types);
		payload.setWebsite("qatesting.com");
		
		return payload;
	}
	
	public String deletePlaceDataBuild(String placeId) {
		
		return "{\r\n"
				+ "    \"place_id\":\""+placeId+"\"\r\n"
				+ "}";
		
	}

}
