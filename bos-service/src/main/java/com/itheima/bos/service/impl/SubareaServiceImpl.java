package com.itheima.bos.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bos.dao.ISubareaDao;
import com.itheima.bos.domain.Subarea;
import com.itheima.bos.service.ISubareaService;
import com.itheima.bos.utils.PageBean;
@Service
@Transactional
public class SubareaServiceImpl implements ISubareaService {
	//注入dao
	@Autowired
	private ISubareaDao subareaDao;
	/**
	 * 保存分区
	 */
	public void save(Subarea model) {
		subareaDao.save(model);
	}
	/**
	 * 分页查询分区
	 */
	public void pageQuery(PageBean pageBean) {
		subareaDao.pageQuery(pageBean);
	}
	/**
	 * 查询所有分区
	 */
	public List<Subarea> findAll() {
		return subareaDao.findAll();
	}
	/**
	 * 查询所有没有关联定区的分区
	 */
	public List<Subarea> findSubareaNotAssociate() {
		DetachedCriteria criteria = DetachedCriteria.forClass(Subarea.class);
		criteria.add(Restrictions.isNull("decidedzone"));
		return subareaDao.findByCriteria(criteria);
	}

}
