package com.tts.test;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class HttpClint {
	
	public String getUrl(String url,Map<String, String> map){
		StringBuffer stb = new StringBuffer(url);
		stb.append("?");
		if(map!=null){
			for(Map.Entry<String, String> set:map.entrySet()){
				stb.append(set.getKey()).append("=").append(set.getValue()).append("&");
			}
		}
		return stb.toString().substring(0,stb.length()-1);
		
	}

	public String doGet(HttpClient httpClient,String url,Map<String, String> headerMap,String charset){
		HttpGet httpGet = new HttpGet(url);
		if(null!=headerMap){
			for(Map.Entry<String, String> set:headerMap.entrySet()){
				httpGet.setHeader(set.getKey(), set.getValue());
			}
		}
		String xml = "";
		try {
			HttpResponse response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			BufferedInputStream buffer = new BufferedInputStream(entity.getContent());
			byte[] b = new byte[buffer.available()];
			int len = buffer.read(b);
			xml = new String(b,0,len,charset);
		} catch (ClientProtocolException e) {
			xml = "客户端异常";
		} catch (IOException e) {
			xml = "读取失败！";
		}
		return xml;
	}
	public String doPost(HttpClient httpClient,String pathUrl,Map<String, String> headerMap,Map<String, String> entityMap,String charset){
		HttpPost httpPost = new HttpPost(pathUrl);
		if(null!=headerMap){
			for(Map.Entry<String, String> set:headerMap.entrySet()){
				httpPost.setHeader(set.getKey(), set.getValue());
			}
		}
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		if(null!=entityMap){
			for(Map.Entry<String, String> set:entityMap.entrySet()){
				params.add(new BasicNameValuePair(set.getKey(), set.getValue()));
			}
		}
		String xml = "";
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(params,charset));
			HttpResponse response = httpClient.execute(httpPost);
			HttpEntity httpEntity = response.getEntity();
			
			BufferedInputStream bufferedInputStream = new BufferedInputStream(httpEntity.getContent());
			byte[] b = new byte[bufferedInputStream.available()];
			int len = bufferedInputStream.read(b);
			xml = new String(b,0,len,charset);
		} catch (UnsupportedEncodingException e) {
			xml = "异常";
		} catch (ClientProtocolException e) {
			xml = "异常";
		} catch (IOException e) {
			xml = "异常";
		}
		
		return xml;
	}
}








