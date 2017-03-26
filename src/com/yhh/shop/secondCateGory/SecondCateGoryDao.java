package com.yhh.shop.secondCateGory;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.yhh.shop.utils.PageHibernateCallback;

public class SecondCateGoryDao extends HibernateDaoSupport{
	
	// 查询二级分类总记录数
	public Integer findCount() {
		List<Long> list = this.getHibernateTemplate().find("select count(*) from SecondCateGory");
		if(list.size() > 0){
			return list.get(0).intValue();
		}
		return null;
	}
	
	// 分页查询
	public List<SecondCateGory> findAll(Integer begin, Integer limit) {
		String hql = "from SecondCateGory";
		List<SecondCateGory> list = this.getHibernateTemplate().executeFind(new PageHibernateCallback<SecondCateGory>(hql,null,begin,limit));
		return list;
	}

	public void save(SecondCateGory secondCateGory) {
		this.getHibernateTemplate().save(secondCateGory);
	}

	public SecondCateGory findByCsid(SecondCateGory secondCateGory) {
		return this.getHibernateTemplate().get(SecondCateGory.class, secondCateGory.getCsid());
	}

	public void update(SecondCateGory secondCateGory) {
		this.getHibernateTemplate().update(secondCateGory);
	}

	public List<SecondCateGory> findAll() {
		return this.getHibernateTemplate().find("from SecondCateGory");
	}
	
}
