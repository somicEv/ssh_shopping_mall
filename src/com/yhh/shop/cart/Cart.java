package com.yhh.shop.cart;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 购物车对象
 * @author 浩瀚
 *
 */
public class Cart {
	//购物车对象
	private Map<Integer,CartItem> map = new HashMap<Integer,CartItem>();
	
	//获得Map中value的值
	public Collection<CartItem> getCartItems(){
		return map.values();
	}
	
	//总计：
	private Double total = 0d;
	
	public Double getTotal() {
		return total;
	}
	
	//提供三个方法:
	//将购物项添加到购物车
	public void addCart(CartItem cartItem){
		// 获得商品的标识
		Integer id = cartItem.getProduct().getPid();
		// 判断这个商品是否在购物车里
		if(map.containsKey(id)){
			// 如果存在
			// 获得已经存在的购物项
			CartItem _cartItem = map.get(id);
			// 增加小计的数量
			_cartItem.setCount(_cartItem.getCount() + cartItem.getCount());
		}else{
			// 如果不存在,将购物项加入购物车
			map.put(id, cartItem);
		}
		// 增加总计价格
		total += cartItem.getSubTotal();
	}
	
	//将购物项删除出购物车
	public void deleteCart(Integer pid){
		// 将购物项移除出Map
		CartItem item = map.remove(pid);
		// 将总计减少
		total -= item.getSubTotal();
	}
	
	//清空购物车
	public void clear(){
		// 清空Map
		map.clear();
		// 将总价设置为0
		total = 0d;
	}

	

	
}
