package com.yhh.shop.product;

import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.yhh.shop.utils.PageHibernateCallback;

public class ProductDao extends HibernateDaoSupport {

	/**
	 * 查询热门商品
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Product> findHot() {
		// 使用分页查询，查询热门商品的前十个
		/*
		 * DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		 * criteria.add(Restrictions.eq("is_hot", 1)); List<Product> list =
		 * this.getHibernateTemplate().findByCriteria(criteria, 0, 10);
		 */
		List<Product> list = this.getHibernateTemplate().executeFind(
				new PageHibernateCallback<Product>("from Product where is_hot = ?", new Object[] { 1 }, 1, 10));
		return list;
	}

	/**
	 * 查询最新商品
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Product> findNew() {
		// 使用分页查询，查询最新商品前十个
		List<Product> list = this.getHibernateTemplate()
				.executeFind(new PageHibernateCallback<Product>("from Product order by pdate desc", null, 0, 10));
		return list;
	}

	/**
	 * 统计某个分类下的商品数量
	 * 
	 * @param cid
	 *            一级分类的ID
	 * @return 查询结果的数量
	 */
	@SuppressWarnings("unchecked")
	public Integer findCount(Integer cid) {
		String hql = "select count(*) from Product p join p.secondCateGory cs join cs.cateGory c where c.cid = ?";
		List<Long> list = this.getHibernateTemplate().find(hql, cid);
		// System.out.println("list:============="+list.get(0).intValue());
		return list.get(0).intValue();
	}

	/**
	 * 查询每一页显示的商品
	 * 
	 * @param cid
	 *            一级分类的ID
	 * @param begin
	 *            每一页起始项
	 * @param limit
	 *            每一页最多显示的条数
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Product> findByPage(Integer cid, int begin, int limit) {
		String hql = "select p from Product p join p.secondCateGory cs join cs.cateGory c where c.cid = ?";
		List<Product> list = this.getHibernateTemplate()
				.executeFind(new PageHibernateCallback<Product>(hql, new Object[] { cid }, begin, limit));
		return list;
	}

	public Product findByPid(Integer pid) {
		return this.getHibernateTemplate().get(Product.class, pid);
	}

	public Integer findCountByCsid(Integer csid) {
		String hql = "select count(*) from Product p join p.secondCateGory cs where cs.csid = ?";
		List<Long> list = this.getHibernateTemplate().find(hql, csid);
		return list.get(0).intValue();
	}

	public List<Product> findByCsPage(Integer csid, int begin, int limit) {
		String hql = "select p from Product p join p.secondCateGory cs where cs.csid = ?";
		List<Product> list = this.getHibernateTemplate()
				.executeFind(new PageHibernateCallback<Product>(hql, new Object[] { csid }, begin, limit));
		return list;
	}

	public Integer findByCount() {
		String hql = "select count(*) from Product";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if(list.size() > 0){
			return list.get(0).intValue();
		}
		return null;
	}

	public List<Product> findPage(int begin, Integer limit) {
		String hql = "from Product";
		List<Product> list = this.getHibernateTemplate().executeFind(new PageHibernateCallback<Product>(hql, null, begin, limit));
		return list;
	}
	
	public void save(Product product) {
		this.getHibernateTemplate().save(product);
	}
}
