package com.yhh.shop.order;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.yhh.shop.user.User;
import com.yhh.shop.utils.PageHibernateCallback;

public class OrderDao extends HibernateDaoSupport {

	public Integer saveOrder(Order order) {
		Integer oid = (Integer) this.getHibernateTemplate().save(order);
		return oid;
	}

	public Order findByOid(Integer oid) {
		Order order = (Order) this.getHibernateTemplate().get(Order.class, oid);
		return order;
	}

	public void update(Order currOrder) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(currOrder);
	}

	@SuppressWarnings("unchecked")
	public List<Order> findByUid(User existUser) {
		List<Order> list = this.getHibernateTemplate().find("from Order o where o.user.uid=?", existUser.getUid());
		return list;
	}

	public Integer findCount() {
		String hql = "select count(*) from Order";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if (list.size() > 0) {
			return list.get(0).intValue();
		}
		return null;
	}

	public List<Order> findAll(Integer begin, Integer limit) {
		String hql = "from Order order by ordertime desc";
		List<Order> list = this.getHibernateTemplate()
				.executeFind(new PageHibernateCallback<Order>(hql, null, begin, limit));
		return list;
	}

}
