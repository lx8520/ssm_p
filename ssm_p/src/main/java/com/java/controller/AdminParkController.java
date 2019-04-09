package com.java.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.java.pojo.CarInformation;
import com.java.pojo.PageBean;
import com.java.pojo.Result;
import com.java.pojo.User;
import com.java.service.CarInformationService;
import com.java.service.PublicService;
import com.java.service.RoleService;
import com.java.service.SubService;

import com.java.service.TreeService;
import com.java.service.UserService;
import com.java.util.ResponseUtil;
import com.java.util.StringUtil;

@Controller
@RequestMapping("/admin/park")
public class AdminParkController {
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
	
	@RequestMapping("/car/confirm")
	public String list(@RequestParam(value = "id", required = false) String id, 
			HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		    CarInformation ci=carInformationService.findById(Integer.parseInt(id));
		    User user=(User) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
		    if(user==null)return null;
		    Integer t=user.getId();
		    ci.setCarState("1");
		    ci.setStorageTime(new Date());
		    ci.setStorageName(t+"");
		    int total=carInformationService.update(ci);
		    Result result = new Result();
			Gson gson = new Gson();
			if (total > 0) {
				result.setSuccess(true);
				result.setMsg("车辆确认入场成功");
			} else {
				result.setSuccess(false);
				result.setMsg("操作失败，请重试");
			}
			ResponseUtil.write(response, gson.toJson(result));
			return null;
		

	}
	/*
	 * 
	 */
	@RequestMapping("/car/leave")
	public String leave(@RequestParam(value = "id", required = false) String id, 
			HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		    CarInformation ci=carInformationService.findById(Integer.parseInt(id));
		    User user=(User) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
		    if(user==null)return null;
		    Integer t=user.getId();
		    ci.setCarState("3");
		    ci.setLeaveTime(new Date());
		    ci.setLeaveName(t+"");
		    int total=carInformationService.update(ci);
		    Result result = new Result();
			Gson gson = new Gson();
			if (total > 0) {
				result.setSuccess(true);
				result.setMsg("车辆确认离场");
			} else {
				result.setSuccess(false);
				result.setMsg("操作失败，请重试");
			}
			ResponseUtil.write(response, gson.toJson(result));
			return null;
		

	}
	
	/*
	 * 获取停车场的所有入场申请
	 */
	@RequestMapping("/sub/list")
	public String submitList(@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "limit", required = false) String rows,
			@RequestParam(value = "q", required = false) String q, 
			@RequestParam(value = "carState", required = false) String carState,
			HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		map.put("carState", carState);
		
		List<CarInformation> list = carInformationService.list(map);
		for (CarInformation carInformation : list) {
			String TrueName=userService.findById(Integer.parseInt(carInformation.getRegistrationName())).getTrueName();
			carInformation.setRegistrationName(TrueName);
		}
		Integer total = carInformationService.getTotal(map);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
		
		map.clear();
		map.put("data", list);
		map.put("count", total);
		map.put("code", 0);
		map.put("msg", "");
		ResponseUtil.write(response, gson.toJson(map));
		return null;
	}

	/*
	 *
	 */
	@RequestMapping("/car/list")
	public String list(@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "limit", required = false) String rows,
			@RequestParam(value = "q", required = false) String q,
			@RequestParam(value = "carState", required = false) String carState,  
			HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		map.put("carState", carState);
		
		
		List<CarInformation> list = carInformationService.list(map);
		for (CarInformation carInformation : list) {
			String TrueName=userService.findById(Integer.parseInt(carInformation.getRegistrationName())).getTrueName();
			carInformation.setRegistrationName(TrueName);
			String TrueName1=userService.findById(Integer.parseInt(carInformation.getStorageName())).getTrueName();
			carInformation.setStorageName(TrueName1);
		}
		Integer total = carInformationService.getTotal(map);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
		
		map.clear();
		map.put("data", list);
		map.put("count", total);
		map.put("code", 0);
		map.put("msg", "");
		ResponseUtil.write(response, gson.toJson(map));
		return null;
	}
	
	
}
