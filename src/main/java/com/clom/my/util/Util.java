package com.clom.my.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public class Util {
	
	
	 /*
     * 判断一个字串是否全是数字
     */
	public static boolean isNum(String str){
		return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
	}
	
	
	   //截去非数字  
	 public static  String getNumbers(String content) {  
	       Pattern pattern = Pattern.compile("\\d+");  
	       Matcher matcher = pattern.matcher(content);  
	       while (matcher.find()) {  
	           return matcher.group(0);  
	       }  
	       return "";  
	   }  
	 
	 //根据年龄计算出生年
	 public static String getBornData(int age){
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		 int year=Integer.parseInt(sdf.format(new Date()))-age;
		 return year+"";
	 }
	  
		//获取ip地址
	 public static String getIpAddr(HttpServletRequest request) {
		 String ip = request.getHeader("x-forwarded-for");  
		 if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
		     ip = request.getHeader("Proxy-Client-IP");  
		 }  
		 if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
		     ip = request.getHeader("WL-Proxy-Client-IP");  
		 }  
		 if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
		     ip = request.getRemoteAddr();  
		 }  
		 return ip;  
		} 
}
