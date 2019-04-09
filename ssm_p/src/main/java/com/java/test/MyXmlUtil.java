package com.java.test;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;

public class MyXmlUtil {
	//将中文字符转换为utf-8格式
	public static String encodeUTF8(String xmlDoc) {
		String str="";
		try {
			str= URLEncoder.encode(xmlDoc,"utf-8");
			return str;
		}catch(Exception ex){
			str=ex.toString();
		}
		return str;
	}
	//将utf-8格式字符转换为中文字符
	public static String decodeUTF8(String str) throws Exception{
		String xmlDoc="";
		try {
			xmlDoc=URLDecoder.decode(str,"utf-8");
		}catch(Exception ex) {
			xmlDoc=ex.toString();
		}
		return xmlDoc;
	}
	
	//xml文档格式封装
	public static String getXMLFileHead() {
		return "<?xml version=\"1.0\" encoding=\"GBK\"?>\n<root>\n";
	}
	public static String getXmlFileFoot() {
		return "</root>";
	}
	
	public static String bran2XMLUTF8(Object bean,String itemName,String itemId) throws Exception{
		StringBuffer buffer=null;
		Map p=BeanUtils.describe(bean);
		Set s=p.keySet();
		Iterator i=s.iterator();
		buffer.append("<");
		buffer.append(itemName);
		if(itemId==null||itemId.equals("")){
		buffer.append(">");
		}else{
		buffer.append("id=\"");
		buffer.append("\">");
		}
		while(i.hasNext()){
		Object key=i.next();
		if(key.equals("class")){}
		else{
		Object value=p.get(key);
		buffer.append("\n <");
		buffer.append(key);
		buffer.append(">");
		if(value==null||value.toString().equals("")) {
			buffer.append("");
		}else {
			value= encodeUTF8(value.toString());
			buffer.append(value);
		}
	buffer.append("</");
	buffer.append(key);
	buffer.append(">");
		}
	}
		buffer.append("\n</");
		buffer.append(itemName);
		buffer.append(">");
	
		return null;
}
}
