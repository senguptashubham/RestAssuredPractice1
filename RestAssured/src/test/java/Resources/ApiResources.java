package Resources;

public enum ApiResources {
	
	AddPlaceAPI("/maps/api/place/add/json"),
	DeletePlaceAPI("/maps/api/place/delete/json"),
	GetPlaceAPI("/maps/api/place/get/json"),
	PutPlaceAPI("/maps/api/place/update/json");
	
	String resource;
	
	ApiResources(String resource){
		
		this.resource = resource;
		
	}
	
	public String getResource() {
		return resource;
	}

}
