package com.yhh.shop.secondCateGory;

import java.util.HashSet;
import java.util.Set;

import com.yhh.shop.category.CateGory;
import com.yhh.shop.product.Product;

public class SecondCateGory {
	
	private Integer csid;
	
	private String csname;
	
	//配置商品的集合
	private Set<Product> productList = new HashSet<Product>();
	
	//配置一级分类的映射
	private CateGory cateGory;
	
	public Integer getCsid() {
		return csid;
	}
	public void setCsid(Integer csid) {
		this.csid = csid;
	}
	public String getCsname() {
		return csname;
	}
	public void setCsname(String csname) {
		this.csname = csname;
	}
	public CateGory getCateGory() {
		return cateGory;
	}
	public void setCateGory(CateGory cateGory) {
		this.cateGory = cateGory;
	}
	
	public Set<Product> getProductList() {
		return productList;
	}
	public void setProductList(Set<Product> productList) {
		this.productList = productList;
	}
	
	
}
