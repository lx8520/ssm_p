package com.java.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.java.pojo.CarInformation;
import com.java.pojo.Role;
import com.java.pojo.Sub;
import com.java.pojo.Tree;
import com.java.pojo.User;
import com.java.service.CarInformationService;
import com.java.service.PublicService;
import com.java.service.RoleService;
import com.java.service.SubService;
import com.java.service.TreeService;
import com.java.service.UserService;
import com.java.util.MyUtil;

@Controller
@RequestMapping("/houtai/apply")
public class HouTai_Apply_Controller {
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
	@Resource
	private SubService subService;
	/*
	 * /houtai/police/car/add
	 *   
	 */
	@RequestMapping("/car/manage")
	public ModelAndView manage() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("pageTitle", "涉案车辆信息管理");
		mav.addObject("title", "涉案车辆信息管理");
		mav.setViewName("/admin/page/apply/car_manage");
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
		mav.addObject("btn_text", "保存");
		mav.addObject("save_url", "admin/apply/car/add?id="+id);
		
		mav.setViewName("/admin/page/apply/apply_submit");
		return mav;
	}
	
	@RequestMapping("/sub/manage")
	public ModelAndView submanage() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("pageTitle", "已提交的申请");
		mav.addObject("title", "已提交的申请");
		mav.setViewName("/admin/page/apply/sub_apply");
		return mav;
	}

	@RequestMapping("/sub/manage/look")
	public ModelAndView sublook(@RequestParam(value="id",required=false)String id
			,HttpServletResponse response
			,HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		List<Sub> sub=subService.findByCarId(Integer.parseInt(id));
		if(sub==null)return null;
		for (Sub sub2 : sub) {
			if(sub2.getSub().equals("0")) {
				sub2.setSub("已提交申请，待审批");
			}else if(sub2.getSub().equals("1")) {
				sub2.setSub("申请被驳回，请重新申请");
			}else {
				sub2.setSub("申请通过");
			}
			String name=userService.findById(sub2.getSubid()).getTrueName();
			sub2.setTrueName(name);
			if(sub2.getState().equals("0")) {
				sub2.setState("申请部门");
			}else if(sub2.getSub().equals("1")) {
				sub2.setState("承办部门");
			}else {
				sub2.setState("xxx部门");
			}
		}
		mav.addObject("sub", sub);
		mav.addObject("pageTitle", "查看详情");
		mav.addObject("title", "查看详情");
		mav.setViewName("/admin/page/apply/look");
		return mav;
	}
	
}
