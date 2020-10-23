package com.restutils;

import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import org.json.simple.JSONObject;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredUtil {

	public static void main(String[] args) {
		//getOOBTxnStatus();
		JSONObject requestParamsUpdateTxn;
		JSONObject requestParamsTxnStatus = new JSONObject();
		requestParamsUpdateTxn=new JSONObject();
		requestParamsUpdateTxn.put("transaction_id", "iq9xu+iWT52cz/wmjtRgFAQEBgQ=");
		requestParamsUpdateTxn.put("transaction_type", "OOB");
		requestParamsUpdateTxn.put("transaction_status","COMPLETE");
		
		requestParamsTxnStatus.put("transaction_id", "iq9xu+iWT52cz/wmjtRgFAQEBgQ=");
		requestParamsTxnStatus.put("transaction_type", "OOB");
		
		getOOBTxnStatus("https://prv7.arcot.com/callback/api/v1",requestParamsTxnStatus);
		try {
			int i=updateOOBTxn("https://prv7api.arcot.com/callback/api/v1",requestParamsUpdateTxn,"src/main/java/com/resources/certs/prv7_keyAndCertBundle.jks");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CertificateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void getOOBTxnStatus(String baseURI,JSONObject requestParamsTxnStatus) {
		JSONObject requestParams=requestParamsTxnStatus;
		//Request
		RestAssured.baseURI=baseURI;
		RequestSpecification httpRequest = RestAssured.given();
		httpRequest.header("Content-Type", "application/json");
		httpRequest.body(requestParams.toJSONString());
		
		//Response after post method
		Response response = httpRequest.post("/txn-status");
		System.out.println(response.getStatusCode() + "-->" +response.getBody().asString());
	}
	
	public static int updateOOBTxn(String baseURI,JSONObject requestParams,String jksPath) throws NoSuchAlgorithmException, CertificateException, IOException, KeyStoreException {
		
		JSONObject requestParameters=requestParams;
		
		//Request 
		RestAssured.baseURI=baseURI;
		
		/*this is an important step to include the cert and key in the form of .jks file generated by using 
		the cert.pem and key.pem files
			//generate .p12 file
			openssl pkcs12 -export -in prv7api.arcot.com.cer.pem -inkey prv7api.arcot.com.key.pem -certfile prv7api.arcot.com.cer.pem -out prv7_keyAndCertBundle.p12
			
			//generate .jks out of .p12 file
			keytool -importkeystore -srckeystore prv7_keyAndCertBundle.p12 -srcstoretype PKCS12 -destkeystore C:\Users\rg030672\Documents\PaySec-2-KT\CallBackServices\certs\certs\prv7_keyAndCertBundle.jks
		*/
		RestAssured.config = RestAssured.config().sslConfig(CertHelper.getSslConfig(jksPath));
		RequestSpecification httpRequest = RestAssured.given();
		httpRequest.header("Content-Type", "application/json");
		httpRequest.body(requestParameters.toJSONString());		
		System.out.println("Request :" + requestParameters.toJSONString());
		
		//Response after post method 
		Response response = httpRequest.post("/update-txn");
		
		System.out.println(response.getStatusCode() + "-->" +response.getBody().asString());
		return response.getStatusCode();
	}
	
	
}