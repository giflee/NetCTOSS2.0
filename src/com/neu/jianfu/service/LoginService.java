package com.neu.jianfu.service;

import com.neu.jianfu.entity.Admin;
import com.neu.jianfu.exception.CodeException;
import com.neu.jianfu.exception.PasswordException;

public interface LoginService {
	
	
	//У���½������½�ɹ������ط���Admin��
	//����½ʧ�ܣ����ش�����Ϣ
	Admin checkCodeAndPwd(String adminCode,String password) throws CodeException,PasswordException;
}
