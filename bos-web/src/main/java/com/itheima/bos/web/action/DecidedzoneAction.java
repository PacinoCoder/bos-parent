package com.itheima.bos.web.action;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.itheima.bos.domain.Decidedzone;
import com.itheima.bos.service.IDecidedzoneSerivce;
import com.itheima.bos.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class DecidedzoneAction extends BaseAction<Decidedzone> {
	//注入service
	@Autowired
	private IDecidedzoneSerivce decidedzoneService;
	//属性驱动,接收多个分区
	private String[] subareaId;
	public void setSubareaId(String[] subareaId) {
		this.subareaId = subareaId;
	}
	
//====================================方法区===================>>>>>>>>>>>>>>>>>>>
	/**
	 * 添加定区
	 * @return
	 */
	public String save(){
		decidedzoneService.save(model,subareaId);
		return LIST;
	}
	/**
	 * 定区的分页显示
	 */
	public String pageQuery(){
		decidedzoneService.pageQuery(pageBean);
		this.Bean2Json(pageBean, new String[]{"currentPage","pageSize","detachedCriteria","subareas","decidedzones"});
		return NONE;
	}
	
}
