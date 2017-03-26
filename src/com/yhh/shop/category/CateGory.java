package com.yhh.shop.category;

import java.util.HashSet;
import java.util.Set;

import com.yhh.shop.secondCateGory.SecondCateGory;

/**
 * 一级分类实体类
 * @author 浩瀚
 *
 */
public class CateGory {
	private Integer cid;
	
	private String cname;
	
	//二级分类的集合
	private Set<SecondCateGory> secondCateGory = new HashSet<SecondCateGory>();
	
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public Set<SecondCateGory> getSecondCateGory() {
		return secondCateGory;
	}
	public void setSecondCateGory(Set<SecondCateGory> secondCateGory) {
		this.secondCateGory = secondCateGory;
	}
	@Override
	public String toString() {
		return "CateGory [cid=" + cid + ", cname=" + cname + ", secondCateGory=" + secondCateGory + "]";
	}
	
	
}
