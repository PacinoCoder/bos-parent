package com.itheima.bos.service;

import java.util.List;

import com.itheima.bos.domain.Decidedzone;
import com.itheima.bos.utils.PageBean;

public interface IDecidedzoneSerivce {

	void save(Decidedzone model, String[] subareaId);

	void pageQuery(PageBean pageBean);

	
}
