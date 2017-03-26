package com.yhh.shop.product;

import java.util.Date;

import com.yhh.shop.secondCateGory.SecondCateGory;

/**
 * Product产品类
 * @author 浩瀚
 *
 */

public class Product {
	
	private Integer pid;
	
	private String pname;
	
	private Double market_price;
	
	private Double shop_price;
	
	private String image;
	
	private String pdesc;
	
	private Integer is_hot;
	
	private Date pdate;
	
	//配置二级分类与商品的映射
	private SecondCateGory secondCateGory;
	
	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public Double getMarket_price() {
		return market_price;
	}

	public void setMarket_price(Double market_price) {
		this.market_price = market_price;
	}

	public Double getShop_price() {
		return shop_price;
	}

	public void setShop_price(Double shop_price) {
		this.shop_price = shop_price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getPdesc() {
		return pdesc;
	}

	public void setPdesc(String pdesc) {
		this.pdesc = pdesc;
	}

	public Integer getIs_hot() {
		return is_hot;
	}

	public void setIs_hot(Integer is_hot) {
		this.is_hot = is_hot;
	}

	public Date getPdate() {
		return pdate;
	}

	public void setPdate(Date pdate) {
		this.pdate = pdate;
	}

	public SecondCateGory getSecondCateGory() {
		return secondCateGory;
	}

	public void setSecondCateGory(SecondCateGory secondCateGory) {
		this.secondCateGory = secondCateGory;
	}

}
