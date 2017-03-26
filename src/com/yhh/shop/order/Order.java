package com.yhh.shop.order;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.yhh.shop.user.User;

/**
 * 订单实体
 * @author 浩瀚
 *	CREATE TABLE `orders` (
  	`oid` int(11) NOT NULL AUTO_INCREMENT,
  	`total` double DEFAULT NULL,
  	`ordertime` datetime DEFAULT NULL,
  	`state` int(11) DEFAULT NULL,
  	`phone` vachar(50) DEFAULT NULL,
  	`addr` vachar(11) DEFAULT NULL,
  	`uid` int(11) DEFAULT NULL,
  	PRIMARY KEY (`oid`),
  	KEY `FKC3DF62E5AA3D9C7` (`uid`),
  	CONSTRAINT `FKC3DF62E5AA3D9C7` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`)
	) ENGINE=MyISAM DEFAULT CHARSET=utf8;
 */
public class Order {
	
	private Integer oid;
	private Double total;
	private Date ordertime;
	private Integer state;
	private String phone;
	private String addr;
	private String name;
	//订单所属的用户
	private User user;
	
	//订单项的集合
	private Set<OrderItem> orderList = new HashSet<OrderItem>();
	
	public Integer getOid() {
		return oid;
	}
	public void setOid(Integer oid) {
		this.oid = oid;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Set<OrderItem> getOrderList() {
		return orderList;
	}
	public void setOrderList(Set<OrderItem> orderList) {
		this.orderList = orderList;
	}
	public Date getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Order [oid=" + oid + ", total=" + total + ", ordertime=" + ordertime + ", state=" + state + ", phone="
				+ phone + ", addr=" + addr + ", user=" + user + ", orderList=" + orderList + "]";
	}
	
	
	
	
}
