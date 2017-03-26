package com.yhh.shop.user;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class UserDao extends HibernateDaoSupport {

	/**
	 * Dao层：保存用户的代码
	 * 
	 * @param user
	 */
	public void save(User user) {
		this.getHibernateTemplate().save(user);
	}

	/**
	 * Dao层：根据激活码查找用户
	 * 
	 * @param code
	 */
	@SuppressWarnings("unchecked")
	public User findByCode(String code) {
		List<User> list = this.getHibernateTemplate().find("from User where code=?", code);
		if(list.size()!=0){
			return list.get(0);
		}else{
			return null;
		}
		
	}

	/**
	 * Dao层：更新用户状态
	 * 
	 * @param codeuser
	 */
	public void updateUser(User codeuser) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(codeuser);
	}

	/**
	 * Dao层：用户登录方法
	 * 
	 * @param user
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public User login(User user) {
		List<User> list = this.getHibernateTemplate().find("from User where username=? and password=? and state=?", user.getUsername(),
				user.getPassword(), 1);
		if(list.size() != 0){
			return list.get(0);
		}else{
			return null;
		}
	}

}
