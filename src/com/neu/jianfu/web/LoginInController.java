package com.neu.jianfu.web;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import com.neu.jianfu.entity.Admin;
import com.neu.jianfu.exception.CodeException;
import com.neu.jianfu.exception.PasswordException;
import com.neu.jianfu.service.LoginService;
import com.neu.jianfu.util.ImageUtil;

@Controller
@RequestMapping("/login")
public class LoginInController {
	@Resource
	private LoginService loginservice;
	@RequestMapping("/toLogin.do")
	public String toLogin(){
		return "main/login";
	}
	@RequestMapping("/toIndex.do")
	public String toIndex(){
		return "main/index";
	}
	/*
	 * 登陆成功，跳转到index.jsp
	 * 登陆失败跳转到login.jsp,给错误提示
	 */
	@RequestMapping("/validate.do")
	public String validate(String adminCode,String password,HttpSession session,Model model,String imageCode){
		
			String code = (String)session.getAttribute("code");
			if(imageCode == null||!imageCode.equalsIgnoreCase(code)){
				model.addAttribute("errorMsg","验证码错误");
				model.addAttribute("adminCode",adminCode);
				model.addAttribute("password",password);
				return "main/login";
			}
		Admin a = loginservice.checkCodeAndPwd(adminCode, password);
		session.setAttribute("admin", a);
		return "redirect:toIndex.do";
		
	}
	
	@ExceptionHandler
	public String doException(Exception e,HttpServletRequest request) throws Exception{
		if(e instanceof CodeException || e instanceof PasswordException){
			request.setAttribute("adminCode", request.getParameter("adminCode"));
			request.setAttribute("password",request.getParameter("password"));
			request.setAttribute("errorMsg",e.getMessage());
			return "main/login";
		}else{
			throw e;
		}
	}
	@RequestMapping("/createImage.do")
	public void createImage(HttpServletResponse response,HttpSession session) throws IOException{
		Map<String,BufferedImage> map = ImageUtil.createImage();
		String code = map.keySet().iterator().next();
		session.setAttribute("code", code);
		BufferedImage image = map.get(code);
		response.setContentType("image/jpeg");
		OutputStream os = response.getOutputStream();
		ImageIO.write(image, "jpeg", os);
	}
}
