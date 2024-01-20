package Resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	public static RequestSpecification request;
	public JsonPath jp;
	
	public RequestSpecification requestSpecBuild() throws IOException {
		if(request==null) {
		PrintStream log = new PrintStream(new FileOutputStream("logs.txt"));
		request = new RequestSpecBuilder().
				setBaseUri(getProperty("baseUri")).
				addFilter(RequestLoggingFilter.logRequestTo(log)).
				addFilter(ResponseLoggingFilter.logResponseTo(log)).
				setContentType(ContentType.JSON).
				addQueryParam(getProperty("queryParam1k"), getProperty("queryParam1v")).build();
		return request;
		}
		return request;
		
	}
	
	public String getProperty(String key) throws IOException {
		
		Properties prop = new Properties();
		FileInputStream fin = new FileInputStream("D:\\Eclipse\\Cucumber Workspace\\RestAssured\\src\\test\\java\\global.properties");
		prop.load(fin);
		return prop.get(key).toString();
		
	}
	
	public String getKeyValue(Response apiRes, String key) {
		
		String response = apiRes.then().extract().response().asString();
		jp = new JsonPath(response);
		return jp.get(key).toString();
		
	}

}
