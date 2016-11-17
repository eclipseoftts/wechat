package rml;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
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
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;

public class utils {
	private HttpClient httpClient = new DefaultHttpClient();
	/**
	 * 
	 * @param url  配置路径
	 * @param entrys   提交的数据           没有可写null
	 * @param method		提交的方式
	 * @param header		头部信息
	 * @param cahset		设置解码方式
	 * @return			返回接收的文本的字符串
	 */
	public String getUrlToNewpath(String url,Map<String, String> header,String method,Map<String,String> entrys,String cahset){
		StringBuffer path = new StringBuffer(url);
		path.append("?");
		if(method.toUpperCase() .equals("GET")){
			return reponseGet(url,path,header,entrys,cahset);
			
		}
		if(method.toUpperCase() .equals("POST")){
			String uri = bufferTostr(path);
			return reponsePost(uri, header, entrys,cahset);
		}
		return null;
	}
	
	/**
	 * 	get   方式提交回调     
	 * 
	 * @param uri    应经组装好的路径以及参数
	 * @return
	 */
	public String reponseGet(String uri,StringBuffer path,Map<String, String> headers,Map<String,String> entrys,String cahset){
		try {
			if(entrys!=null){
				for(Map.Entry<String, String> entry:entrys.entrySet()){
					path.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
				}
			}
			HttpGet httpGet = new HttpGet(bufferTostr(path));
			if(headers!=null){
				for(Map.Entry<String, String> head:headers.entrySet()){
					httpGet.setHeader(head.getKey(), head.getValue());
				}
			}
			HttpResponse execute = httpClient.execute(httpGet);
			HttpEntity entity = execute.getEntity();
			InputStream input = entity.getContent();
			BufferedInputStream content = new BufferedInputStream(input);
			return ioToStr(content,cahset);
			
		} catch (ClientProtocolException e) {
			return "创建客户端异常";
		} catch (IOException e) {
			return "读取数据异常";
		}
	}
	
	/***
	 * 	post   方式提交
	 * @param url     提交的路径
	 * @param heads			头部信息
	 * @param entrys		提交的数据
	 * @return
	 */
	public String reponsePost(String url,Map<String, String> heads,Map<String,String> entrys,String charset){
		HttpPost httpPost = new HttpPost(url);
		if(heads!=null){
			for(Map.Entry<String, String> head:heads.entrySet()){
				Header header = new BasicHeader(head.getKey(), head.getValue());
				httpPost.setHeader(header);
			}
		}
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		if(null!=entrys){
			for(Map.Entry<String, String> entry:entrys.entrySet()){
				params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
		}
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(params,charset));
			HttpResponse response = httpClient.execute(httpPost);
			
			HttpEntity entity = response.getEntity();
			InputStream input = entity.getContent();
			BufferedInputStream content = new BufferedInputStream(input);
			return ioToStr(content,charset);
			
		} catch (ClientProtocolException e) {
			return "创建客户端异常";
		} catch (IOException e) {
			return "读取数据异常";
		}
	}
	
	/***
	 * 	将读取的字节流文件转成String类型的数据
	 * @param content    接收字节输入流
	 * @return
	 */
	
	public String ioToStr(BufferedInputStream content,String cahset){
		try {
			byte[] b = new byte[content.available()];
			int len = content.read(b);
			return new String(b,0,len,cahset);
		} catch (IOException e) {
			return "读取失败";
		}
	}
	/**
	 * 将StringBuffer 转成String 字符串
	 * @param path
	 * @return
	 */
	public String bufferTostr(StringBuffer path){
		String len = path.toString();
		return path.toString().substring(0,len.length()-1);
	}
	
}
