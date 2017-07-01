package com.itheima.bos.web.action.base;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.itheima.bos.utils.BOSUtils;
import com.itheima.bos.utils.PageBean;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
/**
 * 表现层通用实现
 * @author Administrator
 *
 * @param <T>
 */
@SuppressWarnings("all")
public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {
	// 定义常量
	public static final String HOME = "home";
	public static final String LIST = "list";
	// 模型对象
	protected T model;
	public T getModel() {
		return model;
	}
	//属性驱动获得分页参数
	protected PageBean pageBean =new PageBean();
	DetachedCriteria detachedCriteria = null;
	public void setRows(Integer rows) {
		pageBean.setPageSize(rows);
	}
	public void setPage(Integer page) {
		pageBean.setCurrentPage(page);
	}
	
	//通过---构造---方法获取泛型的实例对象
	public BaseAction() {
		ParameterizedType superclass = (ParameterizedType) this.getClass().getGenericSuperclass();
		Type[] types = superclass.getActualTypeArguments();
		Class<T> entityClass = (Class<T>) types[0];
		//完成离线查询对象的封装
		detachedCriteria=DetachedCriteria.forClass(entityClass);
		pageBean.setDetachedCriteria(detachedCriteria);
		//通过反射获得实例对象
		try {
			model = entityClass.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	//======================================方法区========================>>>>>>>>>>>
	public void Bean2Json(Object javaBean,String[] excludes){
		/*
		 * 把javaBean转化为json
		 * 	1.创建jsonlib的配置对象,用于指定哪些属性不用转化为json
		 * 	2.把对象转为json,用配置对象作为第二个参数
		 * 	3.把json写到浏览器
		 */
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(excludes);
		String json = JSONObject.fromObject(javaBean, jsonConfig).toString();
		BOSUtils.printJson(json);
	}
	public void List2Json(List list,String[] excludes){
		/*
		 * 把List转化为json
		 * 	1.创建jsonlib的配置对象,用于指定哪些属性不用转化为json
		 * 	2.把对象转为json,用配置对象作为第二个参数
		 * 	3.把json写到浏览器
		 */
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(excludes);
		String json = JSONArray.fromObject(list, jsonConfig).toString();
		BOSUtils.printJson(json);
	}

}
