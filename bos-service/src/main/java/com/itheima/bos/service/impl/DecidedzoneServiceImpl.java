package com.itheima.bos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bos.dao.IDecidedzoneDao;
import com.itheima.bos.dao.ISubareaDao;
import com.itheima.bos.domain.Decidedzone;
import com.itheima.bos.domain.Subarea;
import com.itheima.bos.service.IDecidedzoneSerivce;
import com.itheima.bos.utils.PageBean;

@Service
@Transactional
public class DecidedzoneServiceImpl implements IDecidedzoneSerivce {
	//注入dao
	@Autowired
	private IDecidedzoneDao decidedzoneDao;
	@Autowired
	private ISubareaDao subareaDao;

	/**
	 * 添加定区
	 */
	public void save(Decidedzone model, String[] subareaId) {
		decidedzoneDao.save(model);
		for (String id : subareaId) {
			Subarea subarea = subareaDao.getById(id);
			//定区(一方)已经放弃对外键的维护,由分区(多方)来维护外键
			subarea.setDecidedzone(model);
		}
	}

	/**
	 *  定区分页显示
	 */
	public void pageQuery(PageBean pageBean) {
		
		decidedzoneDao.pageQuery(pageBean);
	}
	
	
}
