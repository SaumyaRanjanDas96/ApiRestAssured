package com.datadrivedntesting.restapi;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DataDrivenUsingHardCodedValue { 
	
	@Test(dataProvider="empdataprovider")
	void addNewEmployee(String ename,String esal,String eage){

		//base uri
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		//request object
		RequestSpecification httpRequest = RestAssured.given();
		
		//we need to send request along with request body
		JSONObject requestparams=new JSONObject();
		
		requestparams.put("name", ename);
		requestparams.put("salary", esal);
		requestparams.put("age", eage);
		//along with request we have to send header also
		httpRequest.header("Content-Type","application/json");
		//as the body we are sending is in json format
		
		/**
		 * as the params aree in hashmap format with key value pair
		 * we need to send it in json format
		 */
		httpRequest.body(requestparams.toJSONString());
		//response object
		Response response = httpRequest.request(Method.GET,"/create");
		//capture response body to capture validation(string format)
		String responseBody = response.getBody().asString();
		Assert.assertEquals(responseBody.contains(ename), true);
		Assert.assertEquals(responseBody.contains(esal), true);
		Assert.assertEquals(responseBody.contains(esal), true);
		int statuscode = response.getStatusCode();
		System.out.println(statuscode);
		
	}
	@DataProvider(name="empdataprovider")
	String[][] getEmpData(){
		
		String empdata[][]= {{"abc456","50000","45"},{"ghi345","60000","43"},{"pqr678","59000","29"}};
		return(empdata);
	}

}
