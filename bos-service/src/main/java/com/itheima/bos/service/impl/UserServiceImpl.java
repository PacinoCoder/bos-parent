package com.itheima.bos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bos.dao.IUserDao;
import com.itheima.bos.domain.User;
import com.itheima.bos.service.IUserService;
import com.itheima.bos.utils.MD5Utils;
@Service
@Transactional
public class UserServiceImpl implements IUserService {
	@Autowired
	private IUserDao userDao;
	/**
	 * 用户登录:根据用户名密码查找用户
	 */
	public User findUserByUsernameAndPassword(User model) {
		String username = model.getUsername();
		// 用md5进行加密
		String password = MD5Utils.md5(model.getPassword());
		return userDao.findUserByUsernameAndPassword(username,password);
	}
	/**
	 * 修改当前用户的密码
	 */
	public void editPassword(String id, String password) {
		String queryName = "user.editPassword";
		password= MD5Utils.md5(password);
		userDao.executeUpdate(queryName, password,id);
	}
	

}
