package com.neu.jianfu.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.neu.jianfu.entity.Admin;

public class LoginInterceptor implements HandlerInterceptor {

	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {

	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
		Admin admin = (Admin)request.getSession().getAttribute("admin");
		if(admin==null){
			//不能反问
			response.sendRedirect(request.getContextPath()+"/login/toLogin.do");
			return false;
			
		}else{
			
			return true;
		}
		
	}

}
