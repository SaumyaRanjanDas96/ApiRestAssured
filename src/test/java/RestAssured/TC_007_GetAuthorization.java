package RestAssured;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC_007_GetAuthorization {
	
	
	@Test
	void authorization() {
		RestAssured.baseURI="https://the-internet.herokuapp.com/basic_auth";
		PreemptiveBasicAuthScheme authscheme=new PreemptiveBasicAuthScheme();
        authscheme.setUserName("admin");
        authscheme.setPassword("admin");
        RestAssured.authentication=authscheme;
        RequestSpecification httprequest = RestAssured.given();
        Response response = httprequest.request(Method.GET,"/");
        String responsebody = response.getBody().asString();
        System.out.println(responsebody);
        int statuscode = response.getStatusCode();
        System.out.println(statuscode);
        
		
	}

}
