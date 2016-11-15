package com.test;

import java.io.IOException;  
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
import org.apache.http.impl.client.DefaultHttpClient;  
import org.apache.http.message.BasicNameValuePair;  
import org.apache.http.util.EntityUtils;

public class Test {

	 private static HttpClient httpClient = new DefaultHttpClient();  
	  
	    public static void main(String[] args) {  
	  
	        // 设置代理  
	        // HttpHost proxy = new HttpHost("XXX.XXX.XXX.XXX", 8080);  
	        // httpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,  
	        // proxy);  
	  
	  
	        Map<String, String> requestParams = new HashMap<String, String>(); 
	        //theCityCode=string&theUserID=string
	        requestParams.put("theUserID", "");  
	        requestParams.put("theCityCode", "香港");  
	  
	        httpPost("http://ws.webxml.com.cn/WebServices/WeatherWS.asmx/getWeather",requestParams,"utf-8");  
	        
	        httpClient.getConnectionManager().shutdown();  
	    }  
	  
	    public static void httpGet(String url, Map<String, String> requestParams) {  
	  
	        HttpGet httpGet = null;  
	  
	        try {  
	            // 参数设置  
	            StringBuilder builder = new StringBuilder(url); 
	            builder.append("?");  
	            for (Map.Entry<String, String> entry : requestParams.entrySet()) {  
	            		builder.append((String) entry.getKey());  
	 	                builder.append("=");  
	 	                builder.append((String) entry.getValue());  
	 	                builder.append("&"); 
	            }  
	            String tmpUrl = builder.toString();  
	            tmpUrl = tmpUrl.substring(0, tmpUrl.length() - 1);  
	  
	            httpGet = new HttpGet(tmpUrl);  
	  
	            System.out.println("executing request " + httpGet.getURI());  
	  
	            HttpResponse response = httpClient.execute(httpGet);  
	  
	            // reponse header  
	            System.out.println(response.getStatusLine().getStatusCode());  
	  
	            Header[] headers = response.getAllHeaders();  
	            for (Header header : headers) {  
	                System.out.println(header.getName() + ": " + header.getValue());  
	            }  
	  
	            System.out.println();  
	  
	            // 网页内容  
	            HttpEntity httpEntity = response.getEntity();  
	            System.out.println(EntityUtils.toString(httpEntity));  
	  
	        } catch (ClientProtocolException e) {  
	            e.printStackTrace();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        } finally {  
	            if (httpGet != null) {  
	                httpGet.abort();  
	            }  
	        }  
	    }  
	  
	    public static void httpPost(String url, Map<String, String> requestParams, String urlEncode) {  
	  
	        HttpPost httpPost = null;  
	  
	        try {  
	            // 参数设置  
	            List<NameValuePair> params = new ArrayList<NameValuePair>();  
	            for (Map.Entry<String, String> entry : requestParams.entrySet()) {  
	                params.add(new BasicNameValuePair((String) entry.getKey(),  
	                        (String) entry.getValue()));  
	            }  
	  
	            httpPost = new HttpPost(url);  
	            httpPost.setEntity(new UrlEncodedFormEntity(params, urlEncode));  
	  
	            System.out.println("executing request " + httpPost.getURI());  
	            System.out.println("-------------------------------------");  
	  
	            // reponse header  
	            HttpResponse response = httpClient.execute(httpPost);  
	            System.out.println(response.getStatusLine().getStatusCode());  
	  
	            Header[] headers = response.getAllHeaders();  
	            for (Header header : headers) {  
	                System.out.println(header.getName() + ": " + header.getValue());  
	            }  
	  
	            System.out.println();  
	  
	            // 网页内容  
	            HttpEntity httpEntity = response.getEntity();  
	            System.out.println(EntityUtils.toString(httpEntity));  
	  
	        } catch (UnsupportedEncodingException e) {  
	            e.printStackTrace();  
	        } catch (ClientProtocolException e) {  
	            e.printStackTrace();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        } finally {  
	            if (httpPost != null) {  
	                httpPost.abort();  
	            }  
	        }  
	    }  
	}  
    



