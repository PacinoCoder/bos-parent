package com.itheima.bos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bos.dao.ISubareaDao;
import com.itheima.bos.domain.Subarea;
import com.itheima.bos.service.ISubareaService;
@Service
@Transactional
public class SubareaServiceImpl implements ISubareaService {
	//注入dao
	@Autowired
	private ISubareaDao subareaDao;
	@Override
	public void save(Subarea model) {
		subareaDao.save(model);
	}

}
