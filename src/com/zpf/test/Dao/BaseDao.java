package com.zpf.test.Dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Component;

import com.zpf.test.Entity.IdEntity;

@Component
public interface BaseDao<E extends IdEntity> {

	public <PK extends Serializable> E get(PK id);

	/*
	 * 保存实体(non-Javadoc)
	 * 
	 * @see com.zpf.test.Dao.BaseDao#save(com.zpf.test.Entity.IdEntity)
	 */
	public boolean save(E entity);

	/*
	 * 删除实体
	 */
	public void delete(E entity);

	/*
	 * 更新实体
	 */
	public void update(E entity);

	/*
	 * 通过id删除实体
	 */
	public <PK extends Serializable> void delById(PK id);

	/*
	 * 获取列表-带参
	 */
	public List<E> findByCriteria(DetachedCriteria dc);

	/*
	 * 返回列表-所有
	 */
	public List<E> listAll();

	/*
	 * 返回列表-分页
	 */
	public List<E> pageQuery(DetachedCriteria dc, int pageIndex, int pageSize);

	/*
	 * 总条数
	 */
	public int count();

}
