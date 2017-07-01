package com.itheima.bos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bos.dao.IStaffDao;
import com.itheima.bos.domain.Staff;
import com.itheima.bos.service.IStaffService;
import com.itheima.bos.utils.PageBean;

/**
 * 取派员管理
 * @author Administrator
 *
 */
@Service
@Transactional
public class StaffServiceImpl implements IStaffService {
	@Autowired
	private IStaffDao staffDao;
//=======================================方法区======================================>>>>
	/**
	 * 添加取派员
	 */
	public void save(Staff model) {
		staffDao.save(model);
	}
	/**
	 * 分页查询取派员
	 */
	public void pageQuery(PageBean pageBean) {
		staffDao.pageQuery(pageBean);
	}
	/**
	 * 批量删除
	 */
	public void deleteBatch(String ids) {
		String[] idArray = ids.split(",");
		for (String id : idArray) {
			//调用dao"删除"---实际是修改deltag为1
			staffDao.executeUpdate("staff.delete", id);
		}
	}
	/**
	 * 保存或修改
	 */
	public void update(Staff model) {
		staffDao.update(model);
		
	}

}
