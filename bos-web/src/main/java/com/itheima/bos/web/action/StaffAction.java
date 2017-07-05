package com.itheima.bos.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.itheima.bos.domain.Staff;
import com.itheima.bos.service.IStaffService;
import com.itheima.bos.web.action.base.BaseAction;
@SuppressWarnings("all")
@Controller
@Scope("prototype")
public class StaffAction extends BaseAction<Staff> {
	@Autowired
	private IStaffService staffService;
	
	// 属性驱动获取批量删除的ids
	private String ids;
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
//======================================方法区===============================>>>>>>>>>>
	/**
	 * 添加取派员
	 */
	public String save() throws Exception {
		staffService.save(model);
		return NONE;
	}
	/**
	 * 修改取派员
	 */
	public String update() throws Exception {
		staffService.update(model);
		return NONE;
	}
	
	
	/**
	 * 查询分页列表
	 * @return
	 */
	public String pageQuery(){
		staffService.pageQuery(pageBean);
		Bean2Json(pageBean, new String[]{"currentPage","pageSize","detachedCriteria"});
		return NONE;
	}
	/**
	 * 批量删除
	 */
	public String deleteBatch(){
		staffService.deleteBatch(ids);
		return LIST;
	}
	/**
	 * 查询所有未删除的取派员
	 */
	public String listajax(){
		//得到数据集合
		List<Staff> list = staffService.findStaffNotDelete();
		//转化为json
		this.List2Json(list, new String[]{"decidedzones"});
		return NONE;
	}
}
