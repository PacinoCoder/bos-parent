package com.itheima.bos.utils;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.junit.Test;

import com.itheima.bos.domain.User;

/**
 * BOS项目的小工具类
 * @author Administrator
 *
 */
public class BOSUtils {
	// uuid
	@Test
	public void getUUID(){
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		System.out.println(uuid);
	}
	// 获取session
	public static  HttpSession getSession(){
		return ServletActionContext.getRequest().getSession();
	}
	// 获取session中的用户
	public static User getLoginUser(){
		return (User) getSession().getAttribute("loginUser");
	}
	// 服务器往页面写text/html
	public static void printText(String str){
		try {
			ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
			ServletActionContext.getResponse().getWriter().print(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	// 服务器往页面写text/json
	public static void printJson(String str){
		try {
			ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
			ServletActionContext.getResponse().getWriter().print(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
