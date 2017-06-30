package com.itheima.bos.web.interceptor;

import com.itheima.bos.domain.User;
import com.itheima.bos.utils.BOSUtils;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class BOSLoginInterceptor extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		// 获取session中的用户
		User loginUser = BOSUtils.getLoginUser();
		// 判断
		if (loginUser == null) {
			// 没有登录,跳转到登录页面
			return "login";
		}else{
			// 用户已经登录,直接放行
			return invocation.invoke();
		}
	}

}
