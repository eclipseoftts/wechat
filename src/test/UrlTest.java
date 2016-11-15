package test;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class UrlTest {

	public static void main(String[] args) throws Exception {
		URL url = new URL("http://apis.juhe.cn/idcard/index");
		
		URLConnection open = url.openConnection();
		HttpURLConnection http = (HttpURLConnection)open;
		
		http.setRequestMethod("GET");
		
		

	}

}


class Respons{
	private int resultcode;	          //int	返回码
	private String reason;	          //	string	返回说明
	private String result;	          //	-	返回结果集
 	private String area;	          //	string	地区
 	private String sex;	          //	string	性别
 	private String birthday;	          //	string	出生日期
	public int getResultcode() {
		return resultcode;
	}
	public void setResultcode(int resultcode) {
		this.resultcode = resultcode;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
 	
}


class Messge{
	private String cardno;   //	string	Y	身份证号码
 	private String dtype;   //		string	Y	返回数据格式：json或xml,默认json
 	private String key;   //		string	Y	你申请的key
 	
	public String getCardno() {
		return this.cardno==null?"":this.cardno;
	}
	public void setCardno(String cardno) {
		this.cardno = cardno;
	}
	public String getDtype() {
		return dtype==null?"":dtype;
	}
	public void setDtype(String dtype) {
		this.dtype = dtype;
	}
	public String getKey() {
		return key==null?"":key;
	}
	public void setKey(String key) {
		this.key = key;
	}
 	
}
