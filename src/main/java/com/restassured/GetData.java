package com.restassured;

import java.io.File;

import org.json.simple.JSONObject;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * Hello world!
 *
 */
public class GetData {
	public static void main(String[] args) {
		/*
		 * JSONObject jsonObj = new JSONObject(); jsonObj.put("name", "morpheus");
		 * jsonObj.put("job", "leader");
		 */
		String jsondata="{\r\n"
				+ "    \"name\": \"morpheus\",\r\n"
				+ "    \"job\": \"leader\"\r\n"
				+ "}";
		String filePath= System.getProperty("user.dir");
		System.out.println(filePath);
		RestAssured.baseURI = "https://reqres.in";
		RequestSpecification httpRequest = RestAssured.given();
		httpRequest.header("Content-Type", "application/json");
		httpRequest.header("Accept", "applicationjson");
		httpRequest.body(new File(filePath + File.separator+ "src"+ File.separator+"main"+File.separator+"java"+File.separator+"com"+File.separator+"restassured"+File.separator+"data.json"));
		//httpRequest.body(jsondata);

		// Response after post method Response response =
		Response response = httpRequest.post("/api/users");
		System.out.println(response.getStatusCode() + "-->" + response.getBody().asString());

	}

}
