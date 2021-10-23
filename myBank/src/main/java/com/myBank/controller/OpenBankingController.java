package com.myBank.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myBank.service.UsersService;
import com.myBank.util.Pager;

@Controller
public class OpenBankingController {

	@Autowired
	UsersService userService;
	
	@RequestMapping("/Oauth")
	public String Oauth() {
		
		String path = Pager.class.getResource("").getPath();
		
		String propFile = path+"oauth.properties";
		Properties properties = new Properties();
		
		try {
			FileInputStream in = new FileInputStream(propFile);
			properties.load(new BufferedInputStream(in));
			userService.getOauth(properties);
			
			/*
			for(Object key : properties.keySet()) {
				String msg = properties.getProperty((String)key);
				System.out.println(msg);
				
			}
			*/
		} catch (IOException e) {
			e.printStackTrace();
		}
		/*
		String oAuthUri = "https://openapi.openbanking.or.kr/oauth/2.0/authorize?";
		String responseType = "response_type=code&";
		String clientId = "client_id=a3e010c4-db5e-469b-aa64-c35a5a4726ea&";
		String redirectUri = "redirect_uri=http://localhost:8080/controller/&";
		String scope = "scope=login inquiry transfer&";
		String state = "state=b80BLsfigm9OokPTjy03elbJqRHOfGSY&";
		String authType = "auth_type=0";
		String uri = oAuthUri+responseType+clientId+redirectUri+scope+state+authType;
		*/
		return "bank/oauth";
		
	}
}
