package com.java.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.java.service.CarInformationService;
import com.java.service.PublicService;
import com.java.service.RoleService;
import com.java.service.TreeService;
import com.java.service.UserService;

@Controller
@RequestMapping("/houtai/park")
public class HouTai_Park_Controller {

	@Resource
	private UserService  userService;
	@Resource
	private PublicService  publicService;
	@Resource
	private RoleService  roleService;
	@Resource
	private TreeService treeService;
	@Resource
	private CarInformationService carInformationService;
	
	
	@RequestMapping("/car/manage")
	public ModelAndView manage() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("pageTitle", "待入场车辆列表");
		mav.addObject("title", "待入场车辆列表");
		mav.setViewName("/admin/page/park/park_manage");
		return mav;
	}
	
	@RequestMapping("/car/list")
	public ModelAndView list() throws Exception{
		ModelAndView mav=new ModelAndView();
		mav.addObject("pageTitle", "停车场车辆列表");
		mav.addObject("title", "停车场车辆列表");
		mav.setViewName("/admin/page/park/car_manage");
		return mav;
		
	}
	@RequestMapping("/car/leave")
	public ModelAndView leave() throws Exception{
		ModelAndView mav=new ModelAndView();
		mav.addObject("pageTitle", "待离场车辆列表");
		mav.addObject("title", "待离场车辆列表");
		mav.setViewName("/admin/page/park/leave_manage");
		return mav;
	}
	@RequestMapping("/car/leave_history")
	public ModelAndView leavehistory() throws Exception{
		ModelAndView mav=new ModelAndView();
		mav.addObject("pageTitle", "已离场车辆列表");
		mav.addObject("title", "已离场车辆列表");
		mav.setViewName("/admin/page/park/leave_history_manage");
		return mav;
	}
	
}
