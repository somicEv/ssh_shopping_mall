package com.yhh.shop.index;

import java.util.List;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.yhh.shop.category.CateGory;
import com.yhh.shop.category.CateGoryService;
import com.yhh.shop.product.Product;
import com.yhh.shop.product.ProductService;

public class IndexAction extends ActionSupport{
	
	//通过注解的方式，注入service
	@Resource(name="cateGoryService")
	private CateGoryService cateGoryService;
	
	//通过注解的方式，注入service
	@Resource(name="productService")
	private ProductService productService;
	
	//热门商品的集合
	private List<Product> hotList;
	
	//最新商品的集合
	private List<Product> newList;
	
	public ProductService getProductService() {
		return productService;
	}
	
	public List<Product> getHotList() {
		return hotList;
	}
	
	public List<Product> getNewList() {
		return newList;
	}
	/**
	 * 执行访问首页的方法
	 */
	public String execute() {
		//查询一级分类集合
		List<CateGory> cateList = cateGoryService.findAll();
		/**
		 * 将集合存入Session
		 * 使用ServletActionContext方式将数据存储到Session需要在刷新后才能显示
		 * 而使用ActionContext方式获取并储存后可以直接显示
		 */
		ActionContext.getContext().getSession().put("cateList", cateList);
		//查询热门商品集合
		hotList = productService.findHot();
		//查询最新商品集合
		newList = productService.findNew();
		return "indexSuccess";
	}

	

	



	
	
}
