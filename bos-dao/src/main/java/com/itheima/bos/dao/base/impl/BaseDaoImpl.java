package com.itheima.bos.dao.base.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import com.itheima.bos.dao.base.IBaseDao;
import com.itheima.bos.utils.PageBean;

@SuppressWarnings("all")
public class BaseDaoImpl<T> extends HibernateDaoSupport implements IBaseDao<T> {
	// 定义成员变量保存运行时期的泛型类型
	private Class<T> entityClass;
	// 通过构造方法获取运行其实的泛型类型
	public BaseDaoImpl() {
		// 获取有泛型的父类
		ParameterizedType superclass = (ParameterizedType) this.getClass().getGenericSuperclass();
		// 获取父类运行其实的泛型类型数组
		Type[] arguments = superclass.getActualTypeArguments();
		entityClass = (Class<T>) arguments[0];
	}
	
	// 采用注解开发,不在spring配置文件中管理bean对象及其属性注入
	// 根据类型注入spring工厂中的会话工厂对象sessionFactory
	@Resource
	public void setMySessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
//=========================================方法区==========================>>>>>>>>>>
	public void save(T entity) {
		this.getHibernateTemplate().save(entity);
	}
	
	public void saveOrUpdate(T entity) {
		this.getHibernateTemplate().saveOrUpdate(entity);
	}

	public void delete(T entity) {
		this.getHibernateTemplate().delete(entity);
	}

	public void update(T entity) {
		this.getHibernateTemplate().update(entity);
	}
	
	public T getById(Serializable id) {
		return this.getHibernateTemplate().get(entityClass, id);
	}
	
	public List<T> findAll() {
		String hql = "FROM "+entityClass.getSimpleName();
		return (List<T>) this.getHibernateTemplate().find(hql);
	}

	public void executeUpdate(String queryName, Object... objects) {
		// 获取与当前线程绑定的session
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.getNamedQuery(queryName);
		// 循环为?赋值
		int i = 0;
		for (Object object : objects) {
			query.setParameter(i++, object);
		}
		query.executeUpdate();
	}

	public void pageQuery(PageBean pageBean) {
		//pageBean共有5个属性,其中3个属性在web层已经完成赋值,分别是:
		Integer currentPage = pageBean.getCurrentPage();
		Integer pageSize = pageBean.getPageSize();
		DetachedCriteria detachedCriteria = pageBean.getDetachedCriteria();
		
		//为total赋值,设置聚合函数
		detachedCriteria.setProjection(Projections.rowCount());
		List<Long> findByCriteria = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
		pageBean.setTotal(findByCriteria.get(0).intValue());
		
		//为rows赋值
		detachedCriteria.setProjection(null);
		int firstResult = (currentPage - 1) * pageSize;
		int maxResults = pageSize;
		List rows = this.getHibernateTemplate().findByCriteria(detachedCriteria, firstResult, maxResults);
		pageBean.setRows(rows);
	}

	
	

	


}
