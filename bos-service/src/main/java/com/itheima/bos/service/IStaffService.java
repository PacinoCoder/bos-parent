package com.itheima.bos.service;

import java.util.List;

import com.itheima.bos.domain.Staff;
import com.itheima.bos.utils.PageBean;

public interface IStaffService {
	/**
	 * 添加取派员
	 * @param model
	 */
	void save(Staff model);
	/**
	 * 分页查询
	 * @param pageBean
	 */
	void pageQuery(PageBean pageBean);
	/**
	 * 批量删除
	 * @param ids
	 */
	void deleteBatch(String ids);
	/**
	 * 修改
	 * @param model
	 */
	void update(Staff model);
	/**
	 * 查询没有删除的取派员
	 * @return
	 */
	List<Staff> findStaffNotDelete();

}
