package RestAssured;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC_005_GETJSOnResponseBody {
	
	@Test
	void getUserDetails() {
		//specify base uri
		RestAssured.baseURI="https://reqres.in/api/users";
		//request object
        RequestSpecification httpRequest = RestAssured.given();	
        //response object
       Response response = httpRequest.request(Method.GET,"2");
       //getting the response in json format then convert it to java (String)
       String responseBody = response.getBody().asString();
       System.out.println("responsebody is:"  +responseBody);
       //to check wheather element is present or not
       Assert.assertEquals(responseBody.contains("Janet"), true);
       Assert.assertEquals(responseBody.contains("janet.weaver@reqres.in"), true);
       
       
		
	}

}
