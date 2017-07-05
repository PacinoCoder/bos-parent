package com.itheima.bos.dao.base;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

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
	 * 保存或修改
	 */
	void saveOrUpdate(T entity);
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
	 * 按条件查询
	 * @param criteria
	 * @return
	 */
	List<T> findByCriteria(DetachedCriteria criteria);
	/**
	 * 查询所有
	 * @return
	 */
	List<T> findAll();
	/**
	 * 执行修改
	 * @param queryName:从映射配置文件中获取
	 * @param objects:可变参数,用于给?赋值
	 */
	void executeUpdate(String queryName,Object...objects);
	/**
	 * 查询分页数据
	 */
	void pageQuery(PageBean pageBean);
}
