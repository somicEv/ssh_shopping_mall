
package com.yhh.shop.cart;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.yhh.shop.product.Product;
import com.yhh.shop.product.ProductService;

@SuppressWarnings("serial")
public class CartAction extends ActionSupport  {
	
	//接收pid
	private Integer pid;
	
	//接收Count
	private Integer count;
	
	//通过注解将ProductService注入
	@Resource(name="productService")
	private ProductService productService;

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
	/**
	 *  从Session范围内获取购物车对象
	 */
	public Cart getCart(HttpServletRequest request){
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		// 判断是否在Session中存在
		if(cart == null){
			// 添加一个新的Cart到Session中
			cart = new Cart();
			request.getSession().setAttribute("cart", cart);
		}
		return cart;
	}
	
	/**
	 * 访问Cart.jsp
	 */
	public String myCart(){
		return "myCartSuccess";
	}
	
	/**
	 * 添加到购物车的方法
	 * @return
	 */
	public String addCart(){
		//查询到要添加的商品
		Product product = productService.findByPid(pid);
		//创建一个CartItem对象
		CartItem cartItem = new CartItem();
		// 添加相关属性
		cartItem.setProduct(product);
		cartItem.setCount(count);
		//获得购物车并将购物项添加到购物车
		HttpServletRequest request = ServletActionContext.getRequest();
		Cart cart = this.getCart(request);
		cart.addCart(cartItem);
		
		return "addItemSuccess";
	}
	
	/**
	 * 清空购物车方法
	 * 
	 */
	public String clearCart(){
		//获得Cart对象
		HttpServletRequest request = ServletActionContext.getRequest();
		Cart cart = this.getCart(request);
		//清空cart
		cart.clear();
		return "clearCartSuccess";
	}
	
	/**
	 * 删除购物项
	 */
	public String removeCart(){
		//获得Cart对象
		HttpServletRequest request = ServletActionContext.getRequest();
		Cart cart = this.getCart(request);
		//移除购物车
		cart.deleteCart(pid);
		return "removeCartSuccess";
	}
}
