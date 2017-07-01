package com.itheima.bos.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.itheima.bos.domain.Subarea;
import com.itheima.bos.service.ISubareaService;
import com.itheima.bos.web.action.base.BaseAction;
@Controller
@Scope("prototype")
public class SubareaAction extends BaseAction<Subarea> {
	//注入service
	@Autowired
	private ISubareaService subareaSerivce;
	//=============================方法区=================>>>>>>>>>>
	/**
	 * 保存分区
	 */
	public String save() throws Exception {
		subareaSerivce.save(model);
		return LIST;
	}
}
