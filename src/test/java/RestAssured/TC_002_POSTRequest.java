package RestAssured;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC_002_POSTRequest {
	
	@Test
	void registrationSuccessful() {
	//specify baseuri
    //request 
    RestAssured.baseURI="http://restapi.demoqa.com/customer";

    RequestSpecification httpRequest = RestAssured.given();
    //in post requeast we have to send some parameters(request payload)
    JSONObject requestparams=new JSONObject();
    requestparams.put("FirstName", "johncu");
    requestparams.put("LastName", "scottcu");
    requestparams.put("Email", "srd@gmail.com");
    requestparams.put("Password", "srd3429");
    requestparams.put("UserName", "johncuxy");
    //as the request payload we are sending is in json format we should mention application/json
    
    httpRequest.header("Content-Type","application/json");
    //whatever value we specified above will convert into json format and send along with body
    httpRequest.body(requestparams.toJSONString());
    //response object
    Response response = httpRequest.request(Method.POST,"/register");
    //it will get the response body and store that in styring format 
    
String responseBody = response.getBody().asString();
System.out.println("response body is"+responseBody);
//statuscode
int statuscode = response.getStatusCode();
System.out.println("status code is"+statuscode);
//successcode(go to the response and get the successcode value which is in json format)
String successcode = response.jsonPath().get("SuccessCode");
System.out.println(successcode);

    

    
    
	}

}
