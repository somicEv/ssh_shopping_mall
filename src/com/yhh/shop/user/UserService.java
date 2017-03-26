package com.yhh.shop.user;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import com.yhh.shop.utils.MailUtils;
import com.yhh.shop.utils.UUIDUtils;

@Transactional(readOnly=false)
public class UserService {
	
	@Resource(name="userDao")
	private UserDao userDao;
	
	/**
	 * 业务层：注册用户代码
	 * @param user
	 */
	public void regist(User user) {
		//保存用户
		user.setState(0); //未激活：0  激活： 1
		//生成激活码
		String code = UUIDUtils.getUUID();
		user.setCode(code);
		userDao.save(user);
		//发送邮件
		try {
			MailUtils.sendMail(user.getEmail(), code);
			System.out.println("发送成功。。");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("发送失败。。请检查网络问题");
			e.printStackTrace();
		} 
		
	}
	
	/**
	 * 业务层：根据激活码查找用户
	 * @param code
	 * @return
	 */
	public User findByCode(String code) {
	 	User user = userDao.findByCode(code);
		return user;
	}
	
	/**
	 * 业务层：更新用户
	 * @param codeuser
	 */
	public void update(User codeuser) {
		userDao.updateUser(codeuser);
	}

	public User login(User user) {
		
		return userDao.login(user);
	}

	
	
	
}
