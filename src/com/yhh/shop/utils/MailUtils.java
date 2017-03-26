package com.yhh.shop.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtils {
	public static void sendMail(String to,String code) throws Exception{
		Properties props = new Properties();
		props.setProperty("mail.smtp", "localhost");
		//session对象 与邮箱服务器连接
		Session session = Session.getInstance(props, new Authenticator(){
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("service@y-h-h.com", "123456");
			}
		});
		
		//构建邮箱信息
		Message message = new MimeMessage(session);
		//设置发件人
		message.setFrom(new InternetAddress("service@y-h-h.com"));
		//设置收件人
		message.setRecipient(RecipientType.TO, new InternetAddress(to));
		//设置主题
		message.setSubject("来自Shop的一封激活邮件");
		//设置邮件正文
		message.setContent("<h1>来自SHOP的官网激活邮件</h1><h3><a href='http://192.168.1.109:8080/ssh_shop/user_active.action?code="+code+"'>http://192.168.1.109:8080/ssh_shop/user_active.action?code="+code+"</a></h3>", "text/html;charset=UTF-8");
		
		//发送对象
		Transport.send(message);
	}
}
