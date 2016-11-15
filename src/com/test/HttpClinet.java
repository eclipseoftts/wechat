package com.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

public class HttpClinet {

	private static HttpClient httpClient = new DefaultHttpClient();
	
	public static void main(String[] args) {
		Map<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("qqCode", "37324224387");  
		//requestParams.put("theCityCode", "…Ó€⁄");  
		doGet("http://ws.webxml.com.cn/webservices/qqOnlineWebService.asmx/qqCheckOnline",requestParams);
	}
	
	public static void get(String url,Map<String, String> map){
			String uri = "";
		try {
			if(map!=null){
				StringBuffer sb = new StringBuffer(url);
				sb.append("?");
				for(Map.Entry<String, String> entry : map.entrySet()){
					sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
				}
				String str = sb.toString();
				uri = str.substring(0,str.length()-1);
				System.out.println(uri);
			}else{
				uri =url;
			}
			
			URL urls = new URL(uri);
			URLConnection openConnection = urls.openConnection();
			HttpURLConnection httpurl = (HttpURLConnection) openConnection;
			
			httpurl.setRequestMethod("GET");
			httpurl.connect();
			InputStream inputStream = httpurl.getInputStream();
			byte[] b = new byte[inputStream.available()];
			int read = inputStream.read(b);
			
			System.out.println(new String(b,0,read,"utf-8"));
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void doGet(String url,Map<String, String> map){
		
		StringBuffer sb = new StringBuffer(url);
		sb.append("?");
		for(Map.Entry<String, String> entry : map.entrySet()){
			sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
		}
		String str = sb.toString();
		String uri = str.substring(0,str.length()-1);
		System.out.println(uri);
		
		HttpGet get = new HttpGet(uri);
		try {
			HttpResponse execute = httpClient.execute(get);
			
			HttpEntity entity = execute.getEntity();
			InputStream content = entity.getContent();
			byte[] b = new byte[content.available()];
			int len = content.read(b);
			System.out.println(new String(b,0,len,"utf-8"));
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static String doPost(String url,Map<String, String> map){
		
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		for(Map.Entry<String, String> entry : map.entrySet()){
			params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		
		HttpPost post = new HttpPost(url);
		post.setHeader("Content-Type", "application/x-www-form-urlencoded");
		try {
			post.setEntity(new UrlEncodedFormEntity(params,"utf-8"));
			
			HttpResponse execute = httpClient.execute(post);
			
			HttpEntity entity = execute.getEntity();
			
			InputStream content = entity.getContent();
			
			byte[] b = new byte[content.available()];
			int len = content.read(b);
			
			System.out.println(new String(b,0,len,"utf-8"));
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return "";
	}
}
