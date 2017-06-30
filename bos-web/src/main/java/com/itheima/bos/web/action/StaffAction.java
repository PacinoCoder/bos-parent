package com.itheima.bos.web.action;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.itheima.bos.domain.Staff;
import com.itheima.bos.service.IStaffService;
import com.itheima.bos.service.impl.StaffServiceImpl;
import com.itheima.bos.utils.BOSUtils;
import com.itheima.bos.utils.PageBean;
import com.itheima.bos.web.action.base.BaseAction;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
@SuppressWarnings("all")
@Controller
@Scope("prototype")
public class StaffAction extends BaseAction<Staff> {
	@Autowired
	private IStaffService staffService;
	
	// 属性驱动获得分页参数
	private Integer page;
	private Integer rows;
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	
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
		PageBean pageBean = new PageBean();
		pageBean.setCurrentPage(page);
		pageBean.setPageSize(rows);
		//创建离线查询对象
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Staff.class);
		pageBean.setDetachedCriteria(detachedCriteria);
		//传递至业务层处理
		staffService.pageQuery(pageBean);
		/*
		 * 把pageBean转化为json
		 * 	1.创建jsonlib的配置对象,用于指定哪些属性不用转化为json
		 * 	2.把对象转为json,用配置对象作为第二个参数
		 * 	3.把json写到浏览器
		 */
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[]{"currentPage","pageSize","detachedCriteria"});
		String json = JSONObject.fromObject(pageBean, jsonConfig).toString();
		BOSUtils.printJson(json);
		return NONE;
	}
	/**
	 * 批量删除
	 */
	public String deleteBatch(){
		staffService.deleteBatch(ids);
		return LIST;
	}
}
