package com.java.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.java.pojo.CarInformation;
import com.java.pojo.Role;
import com.java.pojo.Tree;
import com.java.service.CarInformationService;
import com.java.service.PublicService;
import com.java.service.RoleService;
import com.java.service.TreeService;
import com.java.service.UserService;
import com.java.util.MyUtil;

@Controller
@RequestMapping("/houtai/police")
public class HouTai_Police_Controller {
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
	/*
	 * /houtai/police/car/add
	 *   
	 */
	@RequestMapping("/car/manage")
	public ModelAndView manage() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("pageTitle", "涉案车辆信息管理");
		mav.addObject("title", "涉案车辆信息管理");
		mav.setViewName("/admin/page/police/car_manage");
		return mav;
	}
	/*
	 * /houtai/police/car/manage/add
	 *
	 */
	@RequestMapping("/car/manage/add")
	public ModelAndView add() throws Exception {
		ModelAndView mav = new ModelAndView();
		String fileId=MyUtil.getTimeId();
		mav.addObject("fileId", fileId);
		mav.addObject("btn_text", "保存");
		mav.addObject("save_url", "admin/police/car/add");
		
		mav.setViewName("/admin/page/police/car_add_or_update");
		return mav;
	}
	
	@RequestMapping("/car/manage/edit")
	public ModelAndView edit(@RequestParam(value="id",required=false)String id
			,HttpServletResponse response
			,HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		CarInformation carInformation = carInformationService.findById(Integer.parseInt(id));
		String fileId=carInformation.getFileId();
		mav.addObject("fileId",fileId);
		mav.addObject("carInformation", carInformation);
		mav.addObject("btn_text", "保存");
		mav.addObject("save_url", "admin/police/car/update?id="+id);
		
		mav.setViewName("/admin/page/police/car_add_or_update");
		return mav;
	}
	
	/*
	 * /houtai/police/car/manage/look
	 *    
	 */
	@RequestMapping("/car/manage/look")
	public ModelAndView look(@RequestParam(value="id",required=false)String id,
			@RequestParam(value="fileId",required=false)String fileId
			,HttpServletResponse response
			,HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		if(fileId==null) {
		
			CarInformation carInformation = carInformationService.findById(Integer.parseInt(id));
			Date date=carInformation.getStorageTime();
			if(date!=null) {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			carInformation.setDate(sdf.format(date));
			}
			mav.addObject("carInformation",carInformation);
			mav.setViewName("/admin/page/police/look");
		}else {
			CarInformation carInformation = carInformationService.findByFileId(fileId);
			Date date=carInformation.getStorageTime();
			if(date!=null) {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			carInformation.setDate(sdf.format(date));
			}
			mav.addObject("carInformation",carInformation);
			mav.setViewName("/admin/page/police/look");
		}
		
		return mav;
	}

	@RequestMapping("/car/sub")
	public ModelAndView sub1() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("pageTitle", "已提交的入场申请");
		mav.addObject("title", "已提交的入场申请");
		mav.setViewName("/admin/page/police/sub_manage");
		return mav;
	}
}
