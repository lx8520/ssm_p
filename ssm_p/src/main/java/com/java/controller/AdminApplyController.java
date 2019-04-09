package com.java.controller;

import java.text.SimpleDateFormat;
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
import com.java.pojo.Sub;
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
@RequestMapping("/admin/apply")
public class AdminApplyController {
	
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
	 * 提交申请，未提交保存，已提交则提示
	 */
	@RequestMapping("/car/add")
	public String caradd(Sub sub,@RequestParam(value="id",required=false)String id
			,HttpServletResponse response, HttpServletRequest request) throws Exception {
		User user = (User) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
		if(user==null)return null;
		Integer useId=user.getId();
		sub.setSubid(useId);
		sub.setSub("0");
		sub.setState("0");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sub.setSubDateTime(sdf.format(new Date()));
		sub.setCarId(Integer.parseInt(id));
		List<Sub> su=subService.findByCarId(Integer.parseInt(id));
		if(su.size()<=0) {
			int resultTotal=subService.add(sub);
			Result result = new Result();
			Gson gson = new Gson();
			if (resultTotal > 0) {
				result.setSuccess(true);
				result.setMsg("添加成功");
				} else {
					result.setSuccess(false);
					result.setMsg("添加失败");
				}
				ResponseUtil.write(response, gson.toJson(result));
				return null;
			}else {
					Result result = new Result();
					Gson gson = new Gson();
					result.setSuccess(false);
					result.setMsg("此数据已提交过了");
					ResponseUtil.write(response, gson.toJson(result));
					return null;
			}	
	}
	
	@RequestMapping("/car/list")
	public String list(@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "limit", required = false) String rows,
			@RequestParam(value = "q", required = false) String q,
			@RequestParam(value = "carState", required = false) String carState,  
			@RequestParam(value = "state", required = false) String state,
			@RequestParam(value = "sub", required = false) String sub,
			HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		map.put("q", StringUtil.formatLike(q));
		map.put("carState", carState);
		map.put("state", state);
		map.put("sub", sub);
		List<CarInformation> list = carInformationService.list(map);
		for (CarInformation carInformation : list) {
			String TrueName=userService.findById(Integer.parseInt(carInformation.getRegistrationName())).getTrueName();
			carInformation.setRegistrationName(TrueName);
			if(carInformation.getStorageName()!=null) {
			String TrueName1=userService.findById(Integer.parseInt(carInformation.getStorageName())).getTrueName();
			carInformation.setStorageName(TrueName1);
			}
			if(carInformation.getLeaveName()!=null) {
			String TrueName2=userService.findById(Integer.parseInt(carInformation.getLeaveName())).getTrueName();
			carInformation.setLeaveName(TrueName2);
			}
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
	 * 得到已提交申请的具体信息
	 */
	@RequestMapping("/sub/list")
	public String sublist(@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "limit", required = false) String rows,
			@RequestParam(value = "q", required = false) String q,
			@RequestParam(value = "carState", required = false) String carState,  
			@RequestParam(value = "state", required = false) String state,
			@RequestParam(value = "sub", required = false) String sub,
			HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		User user = (User) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
		if(user==null)return null;
		String subid=user.getId()+"";
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		map.put("q", StringUtil.formatLike(q));
		map.put("subid", subid);
		map.put("carState", carState);
		map.put("state", state);
		map.put("sub", sub);
		List<CarInformation> list = carInformationService.list(map);
		
		for (CarInformation carInformation : list) {
			//通过数据库中保存的id找到具体的用户
			String TrueName=userService.findById(Integer.parseInt(carInformation.getRegistrationName())).getTrueName();
			carInformation.setRegistrationName(TrueName);
			if(carInformation.getStorageName()!=null) {
				String TrueName1=userService.findById(Integer.parseInt(carInformation.getStorageName())).getTrueName();
				carInformation.setStorageName(TrueName1);
			}
			if(carInformation.getLeaveName()!=null) {
				String TrueName2=userService.findById(Integer.parseInt(carInformation.getLeaveName())).getTrueName();
				carInformation.setLeaveName(TrueName2);
			}
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
