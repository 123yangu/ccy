package com.zpf.test.Dao.Impl;

import java.io.Serializable;
import org.springframework.stereotype.Component;

import com.zpf.test.Dao.AbstractDao;
import com.zpf.test.Dao.UserDao;
import com.zpf.test.Entity.User;

@Component
public class UserDaoImpl extends AbstractDao<User> implements UserDao {

	public UserDaoImpl() {
		super(User.class);
	}

	@Override
	public <PK extends Serializable> User getById(PK id) {
		return super.get(id);
	}

}
