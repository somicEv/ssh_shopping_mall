package com.yhh.shop.product;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;
import com.yhh.shop.utils.PageUtils;

@Transactional(readOnly = false)
public class ProductService {

	@Resource(name = "productDao")
	private ProductDao productDao;

	public List<Product> findHot() {
		return productDao.findHot();
	}

	public List<Product> findNew() {
		return productDao.findNew();
	}

	/**
	 * 显示商品的类
	 * 
	 * @param cid
	 *            一级分类的ID
	 * @param page
	 *            当前页数
	 * @return
	 */
	public PageUtils<Product> findByPage(Integer cid, Integer page) {
		// 每页显示的条数
		int limit = 12;
		// 总页数
		int totalPage = 0;
		PageUtils<Product> pageUtils = new PageUtils<Product>();
		pageUtils.setPage(page);
		pageUtils.setLimit(limit);

		// 查询商品总条数
		Integer totalCount = productDao.findCount(cid);
		pageUtils.setTotalCount(totalCount);

		// 计算总页数
		if (totalCount % limit == 0) {
			totalPage = totalCount % limit;
		} else {
			totalPage = totalCount % limit + 1;
		}
		pageUtils.setTotalPage(totalPage);

		// 商品数据集合
		int begin = (page - 1) * limit;// 计算每页从哪一项开始
		List<Product> productList = productDao.findByPage(cid,begin,limit);
		pageUtils.setList(productList);
		
		return pageUtils;
	}
	
	/**
	 * 查询商品详情
	 * @param pid 商品ID
	 * @return 查询出的商品对象
	 */
	public Product findByPid(Integer pid) {
		return productDao.findByPid(pid);
	}

	public PageUtils findByCsid(Integer csid, Integer page) {
		// 每页显示的条数
		int limit = 8;
		// 总页数
		int totalPage = 0;
		PageUtils<Product> pageUtils = new PageUtils<Product>();
		pageUtils.setPage(page);
		pageUtils.setLimit(limit);
		// 计算总记录数
		Integer totalCount = productDao.findCountByCsid(csid);
		pageUtils.setTotalCount(totalCount);
		// 计算总页数
		if (totalCount % limit == 0) {
			totalPage = totalCount % limit;
		} else {
			totalPage = totalCount % limit + 1;
		}
		pageUtils.setTotalPage(totalPage);

		// 商品数据集合
		int begin = (page - 1) * limit;// 计算每页从哪一项开始
		List<Product> productList = productDao.findByCsPage(csid,begin,limit);
		pageUtils.setList(productList);
		return pageUtils;
	}
	
	
	public PageUtils<Product> findByPage(Integer page) {
		
		PageUtils<Product> pageUtils = new PageUtils<Product>();
		// 总页数
		Integer totalPage = 0;
		
		// 每页显示的个数
		Integer limit = 10;
		pageUtils.setLimit(limit);
		pageUtils.setPage(page);
		
		// 计算总记录数
		Integer totalCount = productDao.findByCount();
		pageUtils.setTotalCount(totalCount);
		
		// 计算总页数
		if(totalCount % limit == 0){
			totalPage = totalCount/limit;
		}else{
			totalPage = totalCount/limit + 1;
		}
		pageUtils.setTotalPage(totalPage);
		
		// 查找出所有商品的集合
		int begin = (page - 1)*limit;
		List<Product> list = productDao.findPage(begin,limit);
		pageUtils.setList(list);
		
		return pageUtils;
	}
	
	// 业务层保存商品
	public void save(Product product) {
		productDao.save(product);
	}
}
