package com.myBank.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class OpenBankOauth {
	
	public OpenBankOauth() {
		// TODO Auto-generated constructor stub
	}
	
	public static void getOAuth(Properties prop) throws IOException {
		OkHttpClient client = new OkHttpClient();
		HttpUrl urlWithParameters = makeHttpUrlWithParameters(prop);
		System.out.println("요청한 url : "+urlWithParameters);
		Request request = makeRequest(urlWithParameters);
		
		System.out.println("결과? : " + client.newCall(request).execute().body().toString());
		
	}
	
	private static Request makeRequest(HttpUrl url) {
		return new Request.Builder().url(url).build();
	}
	
	private static HttpUrl makeHttpUrlWithParameters(Properties prop) throws MalformedURLException {
		HttpUrl.Builder httpBuilder = HttpUrl.get(new URL(prop.getProperty("uri"))).newBuilder();
		
		httpBuilder.addQueryParameter("response_type", prop.getProperty("response_type"));
		httpBuilder.addQueryParameter("client_id", prop.getProperty("client_id"));
		httpBuilder.addQueryParameter("redirect_uri", prop.getProperty("redirect_uri"));
		httpBuilder.addQueryParameter("scope", prop.getProperty("scope"));
		httpBuilder.addQueryParameter("state", prop.getProperty("state"));
		httpBuilder.addQueryParameter("auth_type", prop.getProperty("auth_type"));
		
		return httpBuilder.build();
		
	}
}
