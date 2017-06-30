package com.itheima.bos.service;

import com.itheima.bos.domain.User;

public interface IUserService {
	/**
	 * 按照用户名密码查询用户
	 * @param model
	 * @return
	 */
	User findUserByUsernameAndPassword(User model);
	/**
	 * 修改当前用户的用户名
	 * @param id
	 * @param password
	 */
	void editPassword(String id, String password);

}
