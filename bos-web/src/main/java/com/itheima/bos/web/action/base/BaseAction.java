package com.itheima.bos.web.action.base;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
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
	// 通过构造方法获取泛型的实例对象
	public BaseAction() {
		ParameterizedType superclass = (ParameterizedType) this.getClass().getGenericSuperclass();
		Type[] types = superclass.getActualTypeArguments();
		Class<T> entityClass = (Class<T>) types[0];
		// 通过反射获得实例对象
		try {
			model = entityClass.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	

}
