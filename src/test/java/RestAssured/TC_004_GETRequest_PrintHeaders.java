package RestAssured;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC_004_GETRequest_PrintHeaders {
	
	@Test
	void googleMapHeaders( ) {
		//base uri
		RestAssured.baseURI="https://maps.googleapis.com";
		//request object
		RequestSpecification httpRequest = RestAssured.given();
		//response object
	Response response = httpRequest.request(Method.GET,"/maps/api/place/nearbysearch/xml?location=-33.8670522,151.1957362&radius=1500&type=supermarket&key=AIzaSyBjGCE3VpLU4lgTqSTDmHmJ2HoELb4Jy1s");
	//rrsponsebody(Print response in console window)
	String responseBody = response.getBody().asString();
	System.out.println(responseBody);
	//capture all the headers from response
	Headers allheaders = response.headers();
	
	
	for(Header header:allheaders) {
		//header will capture the keys and value present
		//to separate key and value
		System.out.println(header.getName()+" : "+header.getValue());
		//getName() will get the key and getValue() will get the value
	}
	
		
	}

}
