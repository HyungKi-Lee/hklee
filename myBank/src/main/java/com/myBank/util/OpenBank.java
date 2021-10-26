package com.myBank.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class OpenBank {
	
	public static String getOAuth(Properties prop) throws IOException {
		//OkHttpClient client = new OkHttpClient();
		HttpUrl urlWithParameters = makeHttpUrlforOauth(prop);
		System.out.println("요청한 url : "+urlWithParameters);
		return urlWithParameters.toString();
		
	}
	/*
	public static String getAccessToken(Properties prop, String code, String grantType) throws IOException {
		HttpUrl urlWithParameters = makeHttpUrlforAccessToken(prop ,code, grantType);
		System.out.println("요청한 url : "+urlWithParameters);
		return urlWithParameters.toString();
	}
	*/
	
	private static HttpUrl makeHttpUrlforOauth(Properties prop) throws MalformedURLException {
		HttpUrl.Builder httpBuilder = HttpUrl.get(new URL(prop.getProperty("uri"))).newBuilder();
		
		httpBuilder.addQueryParameter("response_type", prop.getProperty("response_type"));
		httpBuilder.addQueryParameter("client_id", prop.getProperty("client_id"));
		httpBuilder.addQueryParameter("redirect_uri", prop.getProperty("redirect_uri"));
		httpBuilder.addQueryParameter("scope", prop.getProperty("scope"));
		httpBuilder.addQueryParameter("state", prop.getProperty("state"));
		httpBuilder.addQueryParameter("auth_type", prop.getProperty("auth_type"));
		
		return httpBuilder.build();
		
	}
	
	/*
	public static HttpUrl makeHttpUrlforAccessToken(Properties prop, String code, String grantType) throws MalformedURLException {
		HttpUrl.Builder httpBuilder = HttpUrl.get(new URL(prop.getProperty("uri"))).newBuilder();
		
		
		httpBuilder.addQueryParameter("code", code);
		httpBuilder.addQueryParameter("client_id", prop.getProperty("client_id"));
		httpBuilder.addQueryParameter("client_secret", prop.getProperty("client_secret"));
		httpBuilder.addQueryParameter("redirect_uri", prop.getProperty("redirect_uri"));
		httpBuilder.addQueryParameter("grant_type", grantType);
		
		return httpBuilder.build();
		
	}
	*/
}
