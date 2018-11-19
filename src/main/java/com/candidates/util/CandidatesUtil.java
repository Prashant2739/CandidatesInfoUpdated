package com.candidates.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;


public class CandidatesUtil {
	
	 @Autowired
	 Logger logger;
	 
	 @Value("${authenticationUrl}")
	 private String authenticationUrl;

	public String authenticateAccessToken(String acessToken){
		
		HttpClient httpclient = new HttpClient();
		int  responseCode=0;
		logger.info("Authentication URL - "+authenticationUrl);
		GetMethod gm = new GetMethod(authenticationUrl);
		gm.setRequestHeader(CandidatesConstants.AUTHORIZATION,acessToken);
		String emailId = "";
		try {
			responseCode = httpclient.executeMethod(gm);
			
			logger.info("response - "+responseCode);
			Map<String, String> oauthLoginResponse = new HashMap<String, String>();
			if(responseCode==200){
				 oauthLoginResponse = (Map<String, String>)new JSONParser().parse(gm.getResponseBodyAsString());
				 emailId= oauthLoginResponse.get("email");
			}

		} catch (HttpException e) {
			logger.info("Http Exception occured - "+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.info("IOException occured file authenticating via access code- "+e.getMessage());
			e.printStackTrace();
		} catch (ParseException e) {
			logger.info("ParseException occured file authenticating via access code- "+e.getMessage());
			e.printStackTrace();
		}
		
		return emailId;
	}
}
