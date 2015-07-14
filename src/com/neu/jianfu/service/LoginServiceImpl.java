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
			throw new CodeException("�˺Ų���Ϊ��");
		if(password == null||password.equals(""))
			throw new PasswordException("���벻��Ϊ��");
		Admin a = adminDao.findByCode(adminCode);
		if(a == null){
			//�˺Ų�����
			throw new CodeException("�˺Ų�����");
			
		}else if(!a.getPassword().equals(password)){
			//�������
			throw new PasswordException("�������");
		}else{
			//��֤ͨ��
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
