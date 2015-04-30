package com.zpf.test.Service.Impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zpf.test.Dao.UserDao;
import com.zpf.test.Entity.User;
import com.zpf.test.Service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao dao;

	@Override
	public boolean save(User entity) {
		return dao.save(entity);
	}

	@Override
	public <PK extends Serializable> User getById(PK id) {
		return  dao.getById(id);
	}

	@Override
	public void delete(User entity) {
		dao.delete(entity);
	}

	@Override
	public void update(User entity) {
		dao.update(entity);
	}

	@Override
	public <PK extends Serializable> void delById(PK id) {
		dao.delById(id);
	}

	@Override
	public List<User> findByCriteria(DetachedCriteria dc) {
		return dao.findByCriteria(dc);
	}

	@Override
	public List<User> listAll() {
		return dao.listAll();
	}

	@Override
	public List<User> pageQuery(DetachedCriteria dc, int pageIndex, int pageSize) {
		return dao.pageQuery(dc, pageIndex, pageSize);
	}

	@Override
	public int count() {
		return dao.count();
	}

}
