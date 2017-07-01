package com.itheima.bos.service;

import java.util.ArrayList;
import java.util.List;

import com.itheima.bos.domain.Region;
import com.itheima.bos.utils.PageBean;

public interface IRegionService {
	void saveBatch(ArrayList<Region> arrayList);
	
	void pageQuery(PageBean pageBean);
	
	List<Region> findAll();
	
	List<Region> findListByQ(String q);

}
