package RestAssured;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC_003_GET_Request {
	
	
	@Test
void googleMapTest() {
		//specify base uri
RestAssured.baseURI="https://maps.googleapis.com";
//request object
RequestSpecification httpRequest = RestAssured.given();
//response object
Response response=httpRequest.request(Method.GET,"/maps/api/place/nearbysearch/xml?location=-33.8670522,151.1957362&radius=1500&type=supermarket&key=AIzaSyBjGCE3VpLU4lgTqSTDmHmJ2HoELb4Jy1s");
//responsebody
String responseBody = response.getBody().asString();
System.out.println(responseBody);
//capture details of header from response
String contentType = response.header("Content-Type");
System.out.println(contentType);

}
}
