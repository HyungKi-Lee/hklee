package com.myBank.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myBank.service.UsersService;
import com.myBank.util.Pager;

@Controller
public class OpenBankingController {

	@Autowired
	UsersService userService;
	
	/*
	@RequestMapping("/Oauth")
	public String Oauth(Model model) {
		String url = null;
		String path = Pager.class.getResource("").getPath();
		
		String propFile = path+"oauth.properties";
		Properties properties = new Properties();
		
		try {
			FileInputStream in = new FileInputStream(propFile);
			properties.load(new BufferedInputStream(in));
			url = userService.getOauth(properties);
			//model.addAttribute("url", url);
			
			for(Object key : properties.keySet()) {
				String msg = properties.getProperty((String)key);
				System.out.println(msg);
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "redirect:"+url;
		
	}
	*/
	
	// *** 오픈뱅킹 code 요청(인증)을 위한 값들을 받아오는 메소드 *** //
	// com.myBank.util 패키지 안의 프로퍼티스 파일 정보를 읽고 url 생성하여 json형식으로 반환 //
	@RequestMapping("/Oauth")
	@ResponseBody
	public Map<String, String> oAuth() {
		String url = null;
		String path = Pager.class.getResource("").getPath();
		
		String propFile = path+"oauth.properties";
		Properties properties = new Properties();
		try {
			FileInputStream in = new FileInputStream(propFile);
			properties.load(new BufferedInputStream(in));
			
			// 프로퍼티스 파일을 매개변수로하여 url을 만들어내는 메소드 호출
			url = userService.getOauth(properties);
			
			Map<String, String> result = new HashMap<String, String>();
			result.put("url", url);
			return result;
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	// *** 오픈뱅킹 인증 성공 후 code값을 받아오는 임시 메소드 ***
	@RequestMapping("/OauthSuccess")
	public String oAuthSuccess(@RequestParam String code, Model model) {
		model.addAttribute("code",code);
		return "bank/Oauth";
	}
	
	// *** Access 토큰을 얻어오기 위한 값들을 반환하는 메소드 ***
	@RequestMapping("/getToken")
	@ResponseBody
	public Map<String, String> getToken(@RequestParam String code, @RequestParam(defaultValue = "authorization_code") String grant_type) {
		String path = Pager.class.getResource("").getPath();
		String propFile = path+"oauth.properties";
		Properties properties = new Properties();
		try {
			FileInputStream in = new FileInputStream(propFile);
			properties.load(new BufferedInputStream(in));
			
			Map<String, String> result = new HashMap<String, String>();
			result.put("code", code);
			result.put("client_id", properties.getProperty("client_id"));
			result.put("client_secret", properties.getProperty("client_secret"));
			result.put("redirect_uri", "http://localhost:8080/controller/getTokenSuccess");
			result.put("grant_type", grant_type);
			
			return result;
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
}
