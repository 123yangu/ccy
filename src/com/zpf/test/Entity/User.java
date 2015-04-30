package com.zpf.test.Entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 用户实体
 * @author zpf
 *
 */
@Entity
@Table(name="tb_user")
public class User extends IdEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	private String name;
	
	private String sex;
	
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	
}
