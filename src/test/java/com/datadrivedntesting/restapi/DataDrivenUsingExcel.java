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

public class DataDrivenUsingExcel { 
	
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
		Response response = httpRequest.request(Method.POST,"/create");
		//capture response body to capture validation(string format)
		String responseBody = response.getBody().asString();
		System.out.println(responseBody);
		Assert.assertEquals(responseBody.contains(ename), true);
		Assert.assertEquals(responseBody.contains(esal), true);
		Assert.assertEquals(responseBody.contains(eage), true);
		int statuscode = response.getStatusCode();
		System.out.println(statuscode);
		
	}
	@DataProvider(name="empdataprovider")
	 String[][] getEmpData() throws EncryptedDocumentException, IOException{
		
		String path = System.getProperty("user.dir")+"/data/empdata.xlsx";
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
