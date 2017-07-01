package com.itheima.bos.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bos.dao.IRegionDao;
import com.itheima.bos.domain.Region;
import com.itheima.bos.service.IRegionService;
import com.itheima.bos.utils.PageBean;

@Service
@Transactional
public class RegionServiceImpl implements IRegionService {
	//注入dao
	@Autowired
	private IRegionDao regionDao;
	/**
	 * 批量保存区域
	 */
	public void saveBatch(ArrayList<Region> arrayList) {
		for (Region region : arrayList) {
			regionDao.saveOrUpdate(region);
		}
	}
	/**
	 * 分页查询所有区域
	 */
	public void pageQuery(PageBean pageBean) {
		regionDao.pageQuery(pageBean);
	}
	/**
	 * 查询所有区域
	 */
	public List<Region> findAll() {
		return regionDao.findAll();
	}
	/**
	 * 按照过滤条件查询区域
	 */
	public List<Region> findListByQ(String q) {
		return regionDao.findListByQ(q);
	}

}
