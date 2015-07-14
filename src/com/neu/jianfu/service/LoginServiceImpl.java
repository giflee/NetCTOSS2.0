package com.neu.jianfu.service;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.neu.jianfu.dao.AdminMapper;
import com.neu.jianfu.entity.Admin;
import com.neu.jianfu.exception.CodeException;
import com.neu.jianfu.exception.PasswordException;
@Service
public class LoginServiceImpl implements Serializable, LoginService {
	
	@Resource
	private AdminMapper adminDao;
	
	public Admin checkCodeAndPwd(String adminCode, String password)
			throws CodeException, PasswordException {
		if(adminCode == null||adminCode.equals(""))
			throw new CodeException("账号不能为空");
		if(password == null||password.equals(""))
			throw new PasswordException("密码不能为空");
		Admin a = adminDao.findByCode(adminCode);
		if(a == null){
			//账号不存在
			throw new CodeException("账号不存在");
			
		}else if(!a.getPassword().equals(password)){
			//密码错误
			throw new PasswordException("密码错误");
		}else{
			//验证通过
			return a;
		}
	}
	
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		LoginService ser = ctx.getBean("loginServiceImpl",LoginService.class);
		Admin a = ser.checkCodeAndPwd("caocao","");
		System.out.println(a.getName());
	}

}
