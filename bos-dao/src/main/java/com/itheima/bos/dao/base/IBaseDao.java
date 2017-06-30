package com.itheima.bos.dao.base;

import java.io.Serializable;
import java.util.List;

import com.itheima.bos.utils.PageBean;

/**
 * 持久层通用接口
 * @author Administrator
 *
 * @param <T>
 */
public interface IBaseDao<T> {
	/**
	 * 保存(添加)
	 * @param entity
	 */
	void save(T entity);
	/**
	 * 删除
	 * @param entity
	 */
	void delete(T entity);
	/**
	 * 修改
	 * @param entity
	 */
	void update(T entity);
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	T getById(Serializable id);
	/**
	 * 查询所有
	 * @return
	 */
	List<T> getALl();
	/**
	 * 执行查询
	 * @param queryName:从映射配置文件中获取
	 * @param objects:可变参数,用于给?赋值
	 */
	void executeUpdate(String queryName,Object...objects);
	/**
	 * 查询分页数据
	 */
	void pageQuery(PageBean pageBean);
}
