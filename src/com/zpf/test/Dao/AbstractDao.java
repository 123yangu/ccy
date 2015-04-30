package com.zpf.test.Dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.zpf.test.Entity.IdEntity;

/**
 * 基础数据dao层
 * @author zpf
 *
 * @param <E>
 */
@Component
@SuppressWarnings("unchecked")
public abstract class AbstractDao<E extends IdEntity> implements BaseDao<E> {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	// @Resource(name = "sessionFactory")
	// public void setSessionFactory(SessionFactory sessionFactory) {
	// this.sessionFactory = sessionFactory;
	// }

	public Session getSession() {
		// 事务必须是开启的(Required)，否则获取不到
		return sessionFactory.getCurrentSession();
	}

	/*
	 * 定义实体
	 */
	protected Class<E> entityClass;

	/*
	 * 构造函数
	 */
	public AbstractDao(Class<E> entityClass) {
		this.entityClass = entityClass;
	}

	/*
	 * 根据id取得实体
	 */
	public <PK extends Serializable> E get(PK id) {
		E entity = (E) getSession().get(entityClass, id);
		return entity;
	}

	/*
	 * 保存实体(non-Javadoc)
	 * 
	 * @see com.zpf.test.Dao.BaseDao#save(com.zpf.test.Entity.IdEntity)
	 */
	public boolean save(E entity) {
		boolean flag = false;
		if (getSession().save(entity) != null) {
			flag = true;
		}
		return flag;
	}

	/*
	 * 删除实体
	 */
	public void delete(E entity) {
		getSession().delete(entity);
	}

	/*
	 * 更新实体
	 */
	public void update(E entity) {
		getSession().update(entity);
	}

	/*
	 * 通过id删除实体
	 */
	public <PK extends Serializable> void delById(PK id) {
		E entity = this.get(id);
		getSession().delete(entity);
	}

	/*
	 * 获取列表-带参
	 */
	public List<E> findByCriteria(DetachedCriteria dc) {
		Criteria c = dc.getExecutableCriteria(getSession());
		return c.list();
	}

	/*
	 * 返回列表-所有
	 */
	public List<E> listAll() {
		return getSession().createCriteria(entityClass).list();
	}
	
	/*
	 * 返回列表-分页
	 */
	public List<E> pageQuery(DetachedCriteria dc, int pageIndex, int pageSize) {  
        Criteria criteria = dc.getExecutableCriteria(getSession());  
        criteria.setFirstResult(pageIndex);  
        criteria.setMaxResults(pageSize);  
        return criteria.list();  
    } 
	
	/*
	 * 总条数
	 */
	public int count(){
		DetachedCriteria dc = DetachedCriteria.forClass(entityClass);
		Criteria c = dc.getExecutableCriteria(getSession());
		int count = c.list().size();
		return count;
	}
}
