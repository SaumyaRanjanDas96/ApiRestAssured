package RestAssured;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC_006_GETAllValuesInResponseBody {
	
	@Test
	void getUserDetails() {
		//specify base uri
		RestAssured.baseURI="https://reqres.in/api/users";
		//request object
        RequestSpecification httpRequest = RestAssured.given();	
        //response object
       Response response = httpRequest.request(Method.GET,"2");
       //it will capture the responsebody in jsonpath format
       
       JsonPath jsonpath = response.jsonPath();
       System.out.println(jsonpath.get("id"));
       
       System.out.println(jsonpath.get("email"));
       
       System.out.println(jsonpath.get("first_name"));
       
       System.out.println(jsonpath.get("last_name"));
       
       System.out.println(jsonpath.get("avatar"));
       Assert.assertEquals(jsonpath.get("first_name"), "Janet");
       
       
		
	}

}
