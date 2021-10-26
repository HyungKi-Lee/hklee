package com.myBank.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

import com.fasterxml.jackson.databind.util.JSONPObject;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class KakaoLogin {
	private static final String BASE_URL = "https://kauth.kakao.com";
	private static final String APP_KEY =  "6417a8d5306dd02e7e0977db06db4689";
	private static final String REDIRECT_URI = "http://localhost:8080/controller/";
	
	public KakaoLogin() {
		// TODO Auto-generated constructor stub
	}
	
	public static void getAccessToken(String authorizationCode) throws IOException {
		/*
		OkHttpClient client = new OkHttpClient();
		HttpUrl urlWithParameters = makeHttpUrlWithParameters(authorizationCode);
		Request request = makeRequest(urlWithParameters);
		*/
		
		
		//System.out.println("성곡적으로 받아온 토큰 : " + client.newCall(request).execute().body().toString());
		Map<String,String> params = new HashMap<String,String>();
        //params.put("user_id",idToCheck);
        
        JSONObject jsonObject = new JSONObject(params);

        OkHttpClient client = new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        // put your json here
        RequestBody body = RequestBody.create(JSON, jsonObject.toString());

        HttpUrl urlWithParameters = makeHttpUrlWithParameters(authorizationCode);
		Request request = makeRequest(urlWithParameters);

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ResponseBody responseBody = response.body();
                //Log.v("요청 응답 ------ --",responseBody.string());
                System.out.println("요청 응답 ------ --"+responseBody.string());
            }
        });
    }

	private static Request makeRequest(HttpUrl url) {
		return new Request.Builder().url(url).build();
	}
	
	private static HttpUrl makeHttpUrlWithParameters(String authorizationCode) throws MalformedURLException {
		HttpUrl.Builder httpBuilder = HttpUrl.get(new URL(BASE_URL+"/oauth/token")).newBuilder();
		
		httpBuilder.addQueryParameter("grant_type", "authorization_code");
		httpBuilder.addQueryParameter("client_id", APP_KEY);
		httpBuilder.addQueryParameter("redirect_uri", REDIRECT_URI);
		httpBuilder.addQueryParameter("code", authorizationCode);
		
		return httpBuilder.build();
		
	}
}
