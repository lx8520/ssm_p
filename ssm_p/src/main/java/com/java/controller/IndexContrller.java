package com.java.controller;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;
import javax.annotation.Resource;
import javax.naming.spi.ObjectFactoryBuilder;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.FileUtils;
import org.apache.shiro.SecurityUtils;
import org.aspectj.apache.bcel.classfile.Constant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.java.service.PublicService;
import com.java.service.TreeService;
import com.java.util.MyUtil;
 

/**
 * ��ҳContrller
 * �汾 2.10 ���� ��ȥ
 */

@Controller
@RequestMapping("/")
public class IndexContrller {

	@Resource
	private TreeService treeService;
	@Resource
	private PublicService publicService;
	
	
	/**
	 * /wap/laws/index
	 * ������ҳ
	 */
	@RequestMapping("/index")
	public ModelAndView index(HttpServletResponse  res,HttpServletRequest req) throws Exception {
		ModelAndView mav = new ModelAndView();
			mav.setViewName("/pc/login/login");
		return mav;
	}
	
	@RequestMapping("/inde")
	public ModelAndView inde(HttpServletResponse  res,HttpServletRequest req) throws Exception {
		ModelAndView mav = new ModelAndView();
			mav.setViewName("/pc/inde");
		return mav;
	}
	
	@RequestMapping("/fail")
	public ModelAndView fail(HttpServletResponse  res,HttpServletRequest req) throws Exception {
		ModelAndView mav = new ModelAndView();
			mav.setViewName("/pc/fail");
		return mav;
	}
	
	/**
	 * ���Ե�½
	 */
	@RequestMapping("/login")
	public ModelAndView login(HttpServletResponse  res,HttpServletRequest req) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		
			mav.setViewName("/pc/login/login");
		
 
		return mav;
	}
	
	
	/**
	 * ��̨��ҳ
	 */
	@RequestMapping("/admin/main")
	public ModelAndView admin_main(HttpServletResponse  res,HttpServletRequest req) throws Exception {
		ModelAndView mav = new ModelAndView();
		publicService.addLeftMenu(mav);
		
		mav.setViewName("/admin/main");
	
		return mav;
	}
	
	
}
