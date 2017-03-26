package com.yhh.shop.user;

import javax.annotation.Resource;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

/**
 * 用户的Action
 * 
 * @author 浩瀚
 *
 */
@SuppressWarnings("serial")
public class UserAction extends ActionSupport implements ModelDriven<User> {

	private User user = new User();

	@Resource(name = "userService")
	private UserService userService;
	
	//接收验证码的属性
	private String checkCode;
	
	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

	@Override
	public User getModel() {
		return user;
	}

	// 跳转到注册页面的方法
	public String registPage() {
		return "registPageSuccess";
	}

	/**
	 * 注册用户的方法
	 * 
	 * @return
	 */
	@InputConfig(resultName = "registInput")
	public String regist() {
		//从Session中获取，与属性比对
		String sessionCheckCode = (String) ServletActionContext.getRequest().getSession().getAttribute("checkcode");
		if(checkCode == null || !sessionCheckCode.equalsIgnoreCase(checkCode)){
			this.addActionError("验证码有误，请重新输入");
			return "registInput";
		}
		userService.regist(user);
		this.addActionMessage("注册成功，请通过邮箱激活");
		return "registSuccess";
	}

	/**
	 * 前台：激活用户的方法
	 */
	public String active() {
		// 根据激活码查询用户
		User codeuser = userService.findByCode(user.getCode());
		if (codeuser != null) {
			codeuser.setState(1);
			userService.update(codeuser);
			this.addActionMessage("激活成功，请前往登录页面登录");
			return "activeSuccess";
		} else {
			this.addActionMessage("激活失败，请前往重新注册");
			return "activeInput";
		}
	}

	/**
	 * 前台：跳转到登录页面
	 * 
	 * @return loginPageSuccess
	 */
	public String loginPage() {
		System.out.println("登录页面--跳转成功");
		return "loginPageSuccess";
	}

	/**
	 * 前台：用户登录操作
	 */
	@InputConfig(resultName = "loginInput")
	public String login() {
		User loginUser = userService.login(user);
		if (loginUser != null) {
			ServletActionContext.getRequest().getSession().setAttribute("loginUser", loginUser);
			return "loginSuccess";
		} else {
			this.addActionError("用户名或者密码不正确");
			return "loginInput";
		}
	}
	
	/**
	 * 前台：用户退出操作
	 */
	public String exit(){
		//获取用户Session，并将其销毁
		ServletActionContext.getRequest().getSession().invalidate();
		return "exitSuccess";
	}
	
	public String msg(){
		return "msg";
	}
}
