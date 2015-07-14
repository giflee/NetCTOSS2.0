package com.neu.jianfu.service;

import com.neu.jianfu.entity.Admin;
import com.neu.jianfu.exception.CodeException;
import com.neu.jianfu.exception.PasswordException;

public interface LoginService {
	
	
	//校验登陆，若登陆成功，返回返回Admin，
	//若登陆失败，返回错误信息
	Admin checkCodeAndPwd(String adminCode,String password) throws CodeException,PasswordException;
}
