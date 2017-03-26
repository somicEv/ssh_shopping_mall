package com.yhh.shop.category;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class CateGoryDao extends HibernateDaoSupport{

	public List<CateGory> findAll() {
		List<CateGory> list = this.getHibernateTemplate().find("from CateGory");
		return list;
	}

	public void save(CateGory cateGory) {
		this.getHibernateTemplate().save(cateGory);
		
	}

	public void delete(CateGory cateGory) {
		cateGory = this.getHibernateTemplate().get(CateGory.class, cateGory.getCid());
		this.getHibernateTemplate().delete(cateGory);
	}

	public CateGory findByCid(Integer cid) {
		CateGory cateGory = this.getHibernateTemplate().get(CateGory.class, cid);
		return cateGory;
	}

	public void update(CateGory cateGory) {
		this.getHibernateTemplate().update(cateGory);
	}
	
}
