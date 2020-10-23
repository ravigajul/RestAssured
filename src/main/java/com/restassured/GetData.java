package com.restassured;

import io.restassured.RestAssured;
import io.restassured.response.Response;

/**
 * Hello world!
 *
 */
public class  GetData
{
   public static void main(String[] args) {
    	
    	Response resp= RestAssured.get("https://samples.openweathermap.org/agro/1.0/weather?polyid=5aaa8052cbbbb5000b73ff66&appid=b1b15e88fa797225412429c1c50c122a1");
        System.out.println(resp.getStatusCode());
        System.out.println(resp.asString());
        
    }
}
