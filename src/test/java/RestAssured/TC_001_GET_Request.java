package RestAssured;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC_001_GET_Request {
	
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
       System.out.println("responsebody is:"+responseBody);
       //statuscode
      int statuscode = response.getStatusCode();
      System.out.println("status code is:"+statuscode);
      //status line
      String statusline = response.getStatusLine();
      System.out.println("statusline is:"+statusline);
		
	}

}
