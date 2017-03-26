package com.yhh.shop.cart;

import com.yhh.shop.product.Product;
/**
 * 商品项
 * @author 浩瀚
 *
 */
public class CartItem {
	
	//商品对象
	private Product product;
	
	//数量
	private Integer count;
	
	//小计
	private Double subTotal;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Double getSubTotal() {
		return count * product.getShop_price();
	}

}
