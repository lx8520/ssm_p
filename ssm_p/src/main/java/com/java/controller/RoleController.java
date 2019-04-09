package com.java.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.java.pojo.Role;
import com.java.pojo.Tree;
import com.java.pojo.User;
import com.java.service.RoleService;
import com.java.service.TreeService;
import com.java.util.MyUtil;
import com.java.util.ResponseUtil;

@Controller
@RequestMapping("/role")
public class RoleController {
	@Resource
	private RoleService roleService;
	
	@RequestMapping("/getRole")
	public String getCheckedTreeMenu(@RequestParam(value = "userId", required = false) String userId,
			HttpServletRequest requset, HttpServletResponse response) throws Exception {
		List<Role> role=roleService.findByUserId(Integer.parseInt(userId));
		Map<String, Object> map = new HashMap<String, Object>();
		List<Role> list=roleService.findByAll(map);
		List<Integer> ids=new ArrayList<Integer>();
		for (Role role2 : role) {
			ids.add(role2.getId());
		}
		for (Role role3 : list) {
			if (MyUtil.existStrArr2(role3.getId() + "", ids)) {// �ж�id
				// ��û����ids֮�ڣ��������true
				// ����false
				role3.setChecked(true);
			}
			
		}
		Gson g = new Gson();
		ResponseUtil.write(response, g.toJson(list));
		return null;
	}
	

}
