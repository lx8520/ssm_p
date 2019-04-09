package com.java.controller;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.springframework.web.multipart.MultipartFile;

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
import com.java.util.CryptographyUtil;
import com.java.util.MyUtil;
import com.java.util.ResponseUtil;
import com.java.util.StringUtil;



@Controller
@RequestMapping("/admin/police")
public class AdminPoliceController {
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
	 * 通过设置登记人为当前登录用户，使其只能看到自己提交的记录
	 */
	@RequestMapping("/car/list")
	public String list(@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "limit", required = false) String rows,
			@RequestParam(value = "q", required = false) String q,
			@RequestParam(value = "carState", required = false) String carState,  
			@RequestParam(value = "carState1", required = false) String carState1,  
			HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		User user1 = (User) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
		if(user1==null)return null;
		q=user1.getId()+"";
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		map.put("q", StringUtil.formatLike(q));
		map.put("carState", carState);
		map.put("carState1", carState1);
		
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
	 * /admin/police/car/add
	 * 
	 */
	@RequestMapping("/car/add")
	public String caradd(CarInformation carInformation, HttpServletResponse response, HttpServletRequest request) throws Exception {
		User user1 = (User) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
		Integer user1id=user1.getId();
		carInformation.setRegistrationName(user1id+"");
		carInformation.setCreatDateTime(new Date());
		int resultTotal=carInformationService.add(carInformation);
		System.out.println(carInformation.getPhoto1());
		
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
	}
	
	@RequestMapping("/car/update")
	public String update(CarInformation car, HttpServletResponse response, HttpServletRequest request) throws Exception {
		int resultTotal = carInformationService.update(car);
		Result result = new Result();
		Gson gson = new Gson();
		if (resultTotal > 0) {
			result.setSuccess(true);
			result.setMsg("修改成功");
		} else {
			result.setSuccess(false);
			result.setMsg("修改失败");
		}
		ResponseUtil.write(response, gson.toJson(result));
		return null;
	}
	
	/*
	 * admin/police/car/delete
	 */
	@RequestMapping("/car/delete")
	public String permissionsdelete(@RequestParam(value = "ids", required = false) String ids, HttpServletResponse response)
			throws Exception {
		String[] idsStr = ids.split(",");
		Gson gson = new Gson();
		Result result = new Result();
		for (int i = 0; i < idsStr.length; i++) {
			carInformationService.delete(Integer.parseInt(idsStr[i]));
		}
		result.setSuccess(true);
		ResponseUtil.write(response, gson.toJson(result));
		return null;
	}
	/*
	 * /admin/police/car/addimage
	*/
		
		@RequestMapping("/car/addimage")
		public Map<String, Object> caraddimage(@RequestParam(value = "file", required = false) MultipartFile file,
				@RequestParam(value = "fileId", required = false) String fileId,
				HttpServletResponse response, HttpServletRequest request) throws Exception {
			Gson gson = new Gson();
			Result result = new Result();  
			Map<String, Object> res = new HashMap<>();
			String importPath="C:\\Users\\acer\\Desktop\\photos";
			try{
				//获取上传文件的名称
				String fileName = file.getOriginalFilename();
			//截取参数之后剩余的字符串并返回（返回文件名中“.”的索引值），获取上传图片的后缀名
				String ext = fileName.substring(fileName.indexOf("."));
				//根据指定存储路径新建file对象
				File flist = new File(importPath);
				String filename=MyUtil.getTimeId();
				if(!importPath.isEmpty()){
					//检查指定路径下是否有文件夹，没有创建相应文件夹
					flist.mkdir();
				}
				//查看指定路径下的文件夹内的文件
				for(File f : flist.listFiles()){
					if(f.getName().contains(filename)){
						//将指定的文件删除
						filename=MyUtil.getTimeId();
					}
				}
				//图片上传的路径和上传后的名称+原始后缀名
				File file2 = new File(importPath,filename+ext);
				file.transferTo(file2);	
				//上传成功发送给前台的提示信息
				res.put("url","../images/"+ filename+ext );
			    result.setUrl("../images/" + filename+ext);
			     result.setSuccess(true);
			    ResponseUtil.write(response, gson.toJson(result));
			    ResponseUtil.write(response, gson.toJson(res));
			      } catch(Exception e) { 
			  result.setSuccess(false);
			ResponseUtil.write(response, gson.toJson(result));
			     		 
			 }
				return null;
		}
		/*登记部门。
		 * 判断是否已提交，若提交则更新，未提交则增加
		 
		@RequestMapping("/car/submit")
		public String submit(@RequestParam(value = "id", required = false) String id, HttpServletResponse response, HttpServletRequest request)
				throws Exception {
			Gson gson = new Gson();
			Result result = new Result();
			Submit tota=submitService.findByCarId(Integer.parseInt(id));
			User user1 = (User) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
			String userName=user1.getTrueName();
			if(tota==null) {
				
				Submit submit=new Submit();
				submit.setCarId(Integer.parseInt(id));
				submit.setSubName1(userName);
				submit.setSub1(0);
			    int total=submitService.add(submit);
			    if(total>0) {
					result.setSuccess(true);
					result.setMsg("提交成功");
					ResponseUtil.write(response, gson.toJson(result));
				}else {
					result.setSuccess(false);
					result.setMsg("提交失败请重试");
					ResponseUtil.write(response, gson.toJson(result));
				}
			}else {
				int sub1=tota.getSub1();
				if(sub1==0||sub1==2) {
					result.setSuccess(false);
					result.setMsg("无效的提交");
					ResponseUtil.write(response, gson.toJson(result));
				
				}else {
				
				tota.setCarId(Integer.parseInt(id));
				tota.setSubName1(userName);
				tota.setSub1(0);
				int total=submitService.update(tota);
				if(total>0) {
					result.setSuccess(true);
					result.setMsg("提交成功");
					ResponseUtil.write(response, gson.toJson(result));
				}else {
					result.setSuccess(false);
					result.setMsg("提交失败请重试");
					ResponseUtil.write(response, gson.toJson(result));
				}
				}
			}
			
			
			return null;
		}
		*/
		/*
		 * 登记部门。
		 * 查询由个人已提交的信息
		 
		@RequestMapping("/submit/list")
		public String submitList(@RequestParam(value = "page", required = false) String page,
				@RequestParam(value = "limit", required = false) String rows,
				@RequestParam(value = "q", required = false) String q, 
				@RequestParam(value = "sub1", required = false) String sub1,
				HttpServletResponse response,
				HttpServletRequest request) throws Exception {
			User user1 = (User) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
			String subName1=user1.getTrueName();
			PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("start", pageBean.getStart());
			map.put("size", pageBean.getPageSize());
			map.put("q", StringUtil.formatLike(q));
			map.put("subName1", subName1);
			map.put("sub1", sub1);
			
			List<Submit> list = submitService.list(map);
			Integer total = submitService.getTotal(map);
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
			
			map.clear();
			map.put("data", list);
			map.put("count", total);
			map.put("code", 0);
			map.put("msg", "");
			ResponseUtil.write(response, gson.toJson(map));
			return null;
		}
		*/
		/*
		 * 用于民警登记案件后提交入场申请
		 */
		@RequestMapping("/car/submit")
		public String submit(@RequestParam(value = "id", required = false) String id, HttpServletResponse response, HttpServletRequest request)
				throws Exception {
			Gson gson = new Gson();
			Result result = new Result();
			CarInformation carInformation=carInformationService.findById(Integer.parseInt(id));
			carInformation.setCarState("0");
			int total=carInformationService.update(carInformation);
			 if(total>0) {
					result.setSuccess(true);
					result.setMsg("提交成功");
					ResponseUtil.write(response, gson.toJson(result));
				}else {
					result.setSuccess(false);
					result.setMsg("提交失败请重试");
					ResponseUtil.write(response, gson.toJson(result));
				}
			return null;
		}
		/*
		 * 获取当前民警提交的入场申请
		 */
		@RequestMapping("/sub/list")
		public String submitList(@RequestParam(value = "page", required = false) String page,
				@RequestParam(value = "limit", required = false) String rows,
				@RequestParam(value = "q", required = false) String q, 
				@RequestParam(value = "carState", required = false) String carState,
				@RequestParam(value = "carState1", required = false) String carState1,
				HttpServletResponse response,
				HttpServletRequest request) throws Exception {
			User user1 = (User) request.getSession().getAttribute("currentUser");
			if(user1==null)return null;
			q=user1.getId()+"";
			PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("start", pageBean.getStart());
			map.put("size", pageBean.getPageSize());
			map.put("q", StringUtil.formatLike(q));
			map.put("carState1", carState1);
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

}

		
	
