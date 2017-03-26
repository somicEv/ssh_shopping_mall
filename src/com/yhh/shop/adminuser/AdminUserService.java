package com.yhh.shop.adminuser;

import javax.annotation.Resource;

public class AdminUserService {
	
	@Resource(name="adminUserDao")
	private AdminUserDao adminUserDao;
	
	public AdminUser login(AdminUser adminUser) {
		AdminUser user = adminUserDao.login(adminUser);
		return user;
	}

}
