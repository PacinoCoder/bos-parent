package com.itheima.bos.web.action;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.itheima.bos.domain.User;
import com.itheima.bos.service.IUserService;
import com.itheima.bos.utils.BOSUtils;
import com.itheima.bos.web.action.base.BaseAction;
import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("all")
@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {
	// 属性封装接收验证码
	private String checkcode;
	public String getCheckcode() {
		return checkcode;
	}
	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}
	
	// 声明并注入service
	@Autowired
	private IUserService userService;
	
	//=======================================方法====================================>>>>>>
	/**
	 * 用户登录
	 * @return
	 * @throws Exception
	 */
	public String login() throws Exception{
		//验证码校验
		String validatecode = (String) ActionContext.getContext().getSession().get("key");
		if (StringUtils.isNotBlank(checkcode) && checkcode.equals(validatecode)) {
			//验证码正确,进行用户名密码校验
			User user = userService.findUserByUsernameAndPassword(model);
			if (user!=null) {
				//用户名密码正确,保存用户到session,页面跳转
				BOSUtils.getSession().setAttribute("loginUser", user);
				return HOME;
			}else{
				//用户名或者密码错误,给出提示,返回登录
				this.addActionError("用户名或者密码错误");
				return LOGIN;
			}
		}else{
			//提示错误信息,返回登录
			this.addActionError("验证码错误!");
			return LOGIN;
		}
	}
	/**
	 * 用户注销
	 */
	public String logout() throws Exception {
		// 清除session
		BOSUtils.getSession().invalidate();
		return LOGIN;
	}
	/**
	 * 修改当前用户密码
	 */
	public String editPassword() throws Exception {
		//调用service
		try {
			userService.editPassword(BOSUtils.getLoginUser().getId(),model.getPassword());
			BOSUtils.printText("1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return NONE;
	}
}
