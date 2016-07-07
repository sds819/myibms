package com.cesecsh.util;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

public class ResponseJsonUtil {

	
	
	public static void responseToJson(HttpServletResponse response,Object object){
		response.setContentType("application/json;charset=utf-8");
		try {
			response.getWriter().write(JSON.toJSONString(object).toString());
			response.getWriter().flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void responseToText(HttpServletResponse response,Object object){
		response.setContentType("application/text;charset=utf-8");
		try {
			response.getWriter().write(JSON.toJSONString(object).toString());
			response.getWriter().flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
