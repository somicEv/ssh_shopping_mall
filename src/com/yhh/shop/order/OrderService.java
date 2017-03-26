package com.yhh.shop.order;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import com.yhh.shop.user.User;
import com.yhh.shop.utils.PageUtils;

@Transactional(readOnly = false)
public class OrderService {

	@Resource(name = "orderDao")
	private OrderDao orderDao;

	public Integer saveOrder(Order order) {
		return orderDao.saveOrder(order);
	}

	public Order findByOid(Integer oid) {

		return orderDao.findByOid(oid);
	}

	public void update(Order currOrder) {
		// TODO Auto-generated method stub
		orderDao.update(currOrder);
	}

	public List<Order> findByUid(User existUser) {
		return orderDao.findByUid(existUser);
	}

	// 按状态查询订单
	public PageUtils<Order> findByPage(Integer page, Integer state) {

		return null;
	}

	// 查询所有订单
	public PageUtils<Order> findByPage(Integer page) {
		
		PageUtils<Order> pageBean = new PageUtils<Order>();
		// 封装分页数据
		Integer limit = 5;
		pageBean.setPage(page);
		pageBean.setLimit(limit);
		
		// 计算总记录数
		Integer totalCount = 0;
		totalCount = orderDao.findCount();
		pageBean.setTotalCount(totalCount);
		
		// 计算总页数
		Integer totalPage = 0;
		if(totalCount % limit == 0){
			totalPage = totalCount/limit;
		}else{
			totalPage = totalCount/limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		
		// 封装订单集合
		Integer begin = (page - 1 )* limit;
		List<Order> orderList = orderDao.findAll(begin,limit);
		pageBean.setList(orderList);
		
		return pageBean;
	}

}
