package com.datadrivedntesting.restapi;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DataDrivenUsingExcel2 { 
	
	@Test(dataProvider="empdataprovider")
	void addNewEmployee(String name,String email,String password){

		//base uri
		RestAssured.baseURI="https://reqres.in/api";
		//request object
		RequestSpecification httpRequest = RestAssured.given();
		
		//we need to send request along with request body
		JSONObject requestparams=new JSONObject();
		
		requestparams.put("first_name", name);
		requestparams.put("email", email);
		requestparams.put("password",password );
		//along with request we have to send header also
		httpRequest.header("Content-Type","application/json");
		//as the body we are sending is in json format
		
		/**
		 * as the params aree in hashmap format with key value pair
		 * we need to send it in json format
		 */
		httpRequest.body(requestparams.toJSONString());
		//response object
		Response response = httpRequest.request(Method.POST,"/login");
		//capture response body to capture validation(string format)
		String responseBody = response.getBody().asString();
		Assert.assertEquals(responseBody.contains(name), true);
		Assert.assertEquals(responseBody.contains(email), true);
		Assert.assertEquals(responseBody.contains(password), true);
		int statuscode = response.getStatusCode();
		System.out.println(statuscode);
		
	}
	@DataProvider(name="empdataprovider")
	 String[][] getEmpData() throws EncryptedDocumentException, IOException{
		
		String path = System.getProperty("user.dir")+"/data/empdata2.xlsx";
		int rows = XlUtil.getRowCount(path, "Sheet1");
		int columns = XlUtil.getCellCount(path, "Sheet1", 1);
		String empdata[][]=new String[rows][columns];
		for(int i=0;i<=rows;i++) {
			for(int j=0;j<columns;j++) {
				empdata[i-1][j]=XlUtil.getCellData(path, "Sheet1", i, j);
			}
		}
		return (empdata);
	}

}
