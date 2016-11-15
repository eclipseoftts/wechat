package com.tts.test;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

public class UrlTest {

	public static void main(String[] args) {
		
		HttpClient httpClient = new DefaultHttpClient();
		String url = "http://www.webxml.com.cn/WebServices/MobileCodeWS.asmx/getDatabaseInfo?";
		Map<String, String> headerMap = new HashMap<String,String>();
		//headerMap.put("Content-Type", "application/x-www-form-urlencoded");
		
		Map<String, String> entityMap = new HashMap<String,String>();
		/*entityMap.put("theCityCode", "œ„∏€");
		entityMap.put("theUserID", "");*/
		String charset = "utf-8";
		
		HttpClint h = new HttpClint();
		
		//String doPost = h.doPost(httpClient, url, headerMap, entityMap, charset);
		String doGet = h.doGet(httpClient, url, headerMap, charset);
		
		System.out.println(doGet);

	}

}
