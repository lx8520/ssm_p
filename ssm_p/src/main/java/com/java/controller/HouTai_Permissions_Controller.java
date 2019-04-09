package com.java.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.google.gson.Gson;
import com.java.pojo.Role;
import com.java.pojo.Tree;
import com.java.pojo.User;
import com.java.service.PublicService;
import com.java.service.RoleService;
import com.java.service.TreeService;
import com.java.service.UserService;
 


@Controller
@RequestMapping("/houtai/permissions")
public class HouTai_Permissions_Controller {
	
	
	@Resource
	private UserService  userService;
	@Resource
	private PublicService  publicService;
	@Resource
	private RoleService  roleService;
	@Resource
	private TreeService treeService;

	/**
	 * 
	 * @param t  ��null ������ȫ��
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/manage")
	public ModelAndView manage() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("pageTitle", "菜单管理");
		mav.addObject("title", "菜单管理");
		mav.setViewName("/admin/page/user/permissions_manage");
		return mav;
	}
	@RequestMapping("/add")
	public ModelAndView add() throws Exception {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("btn_text", "增加");
		mav.addObject("save_url", "admin/user/permissions/add");
		
		mav.setViewName("/admin/page/user/permissions_add_or_update");
		return mav;
	}
	
	
	
	
	@RequestMapping("/edit")
	public ModelAndView edit(@RequestParam(value="id",required=false)String id
			,HttpServletResponse response
			,HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		Tree tree = treeService.findById(Integer.parseInt(id));
		
		mav.addObject("tree", tree);
		mav.addObject("btn_text", "修改");
		mav.addObject("save_url", "admin/user/permissions/tree/edit?id="+id);
		
		mav.setViewName("/admin/page/user/permissions_add_or_update");
		return mav;
	}
	
	@RequestMapping("/role/edit")
	public ModelAndView roleedit(@RequestParam(value="id",required=false)String id
			,HttpServletResponse response
			,HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		Role role = roleService.findById(Integer.parseInt(id));
		
		mav.addObject("role", role);
		mav.addObject("btn_text", "保存");
		mav.addObject("save_url", "admin/user/role/edit?id="+id);
		
		mav.setViewName("/admin/page/user/role_add_or_update");
		return mav;
	}
	
	@RequestMapping("/role/add")
	public ModelAndView roleadd() throws Exception {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("btn_text", "增加");
		mav.addObject("save_url", "admin/user/role/add");
		
		mav.setViewName("/admin/page/user/role_add_or_update");
		return mav;
	}
	
	@RequestMapping("/role/setpersm")
	public ModelAndView rolesetPersm(@RequestParam(value = "id", required = false) String id,
			HttpServletResponse response, HttpServletRequest request) throws Exception {
		
		//���id��ֵ���� ���� ���û��ֵ  �������
		ModelAndView mav = new ModelAndView();
		mav.addObject("roleId", id);
		mav.setViewName("admin/page/user/set_persm");
		return mav;
	}
	
	@RequestMapping("/setRole")
	public ModelAndView setRole(@RequestParam(value = "id", required = false) String id,
			HttpServletResponse response, HttpServletRequest request) throws Exception {
		
		//���id��ֵ���� ���� ���û��ֵ  �������
		ModelAndView mav = new ModelAndView();
		mav.addObject("userId", id);
		mav.setViewName("admin/page/user/set_role");
		return mav;
	}
	
	@RequestMapping("/setPassword")
	public ModelAndView setPassword(@RequestParam(value = "id", required = false) String id,
			HttpServletResponse response, HttpServletRequest request) throws Exception {
		//���id��ֵ���� ���� ���û��ֵ  �������
		ModelAndView mav = new ModelAndView();
		mav.addObject("userId", id);
		mav.addObject("save_url", "admin/user/update?id="+id);
		mav.setViewName("admin/page/user/set_password");
		return mav;
	}
	
	
	
}


