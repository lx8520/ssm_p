package com.java.controller;


import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.google.gson.Gson;
import com.java.pojo.Result;
import com.java.pojo.User;
import com.java.service.PublicService;
import com.java.service.UserService;
import com.java.util.CryptographyUtil;
import com.java.util.ResponseUtil;
 



@Controller
@RequestMapping("/user")
public class UserController {
	
	
	@Resource
	private UserService  userService;
	@Resource
	private PublicService  publicService;
 
	/*
	 * 通过shiro进行登录验证，判断是否有异地登录
	 */
	@RequestMapping("/login")
	public String login(User user,HttpServletResponse response,HttpServletRequest request
			,RedirectAttributes attr)throws Exception{
		
		Result result = new  Result();
		Gson gson = new Gson();
		
		Subject subject=SecurityUtils.getSubject();
		UsernamePasswordToken token=new UsernamePasswordToken(user.getName(),user.getPassword()); //CryptographyUtil.md5(user.getPassword(), user.getName()));
		try{
			//通过shiro进行账号密码验证 
			subject.login(token);
			
			HttpSession session=request.getSession();
			ServletContext application =session.getServletContext();
			@SuppressWarnings("unchecked")
			//在application中取出所有在线用户
			Map<String,String> loginMap=(Map<String,String>) application.getAttribute("loginMap");
			if(loginMap==null) {
				loginMap= new HashMap<>();
			}
			for(String username : loginMap.keySet()) {
				if(user.getName().equals(username)) {
					if(session.getId().equals(loginMap.get(username))) {
						result.setSuccess(true);
						result.setMsg(user.getName()+"在同一地点多次登录");
						ResponseUtil.write(response, gson.toJson(result));
					}else {
						result.setSuccess(false);
						result.setMsg(user.getName()+"异地登录被拒绝,该用户已经异地登录");
						ResponseUtil.write(response, gson.toJson(result));
						return "redirect:/login";
					}
					
				}
			}
			loginMap.put(user.getName(), session.getId());
			application.setAttribute("loginMap", loginMap);
			user = userService.findByName(user.getName());
			session.setAttribute("currentUser", user);
			result.setSuccess(true);
			result.setMsg("登录成功");
			ResponseUtil.write(response, gson.toJson(result));
		}catch(Exception e){
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("账号密码错误");
			ResponseUtil.write(response, gson.toJson(result));
		}
		return null;
	}
	
	/**
	 * /user/logout
	 * @throws Exception
	 */
	@RequestMapping("/logout")
	public String logout(HttpServletResponse response,HttpServletRequest request)throws Exception{
		SecurityUtils.getSubject().logout(); //shiro退出
		return "redirect:/login";
		
	}
	
	
}
