package com.zpf.test.Dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.zpf.test.Entity.User;

public interface UserDao extends BaseDao<User>{
	public <PK extends Serializable> User getById(PK id);
	
	public boolean save(User entity);
	
	public void delete(User entity);

	public void update(User entity);

	public <PK extends Serializable> void delById(PK id);

	public List<User> findByCriteria(DetachedCriteria dc);

	public List<User> listAll();

	public List<User> pageQuery(DetachedCriteria dc, int pageIndex, int pageSize);

	public int count();
}
