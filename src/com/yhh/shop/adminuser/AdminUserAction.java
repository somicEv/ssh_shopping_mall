package com.yhh.shop.adminuser;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminUserAction extends ActionSupport implements ModelDriven<AdminUser>{

	private AdminUser adminUser = new AdminUser();
	
	@Resource(name="adminUserService")
	private AdminUserService adminUserService;
	
	@Override
	public AdminUser getModel() {
		// TODO Auto-generated method stub
		return adminUser;
	}
	
	/**
	 * 后台登录的方法
	 */
	public String login(){
		//查询是否存在已经登录的用户
		AdminUser existUser = adminUserService.login(adminUser);
		if(existUser == null){
			this.addActionError("登录失败，请检查用户名或者密码是否有误。");
			return "loginInput";
		}
		//将用户存入Session
		ServletActionContext.getRequest().getSession().setAttribute("existUser", existUser);
		return "loginSuccess";
	}

}
