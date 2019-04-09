package com.java.controller;

import java.util.ArrayList;
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
import com.java.pojo.Role;
import com.java.pojo.Tree;
import com.java.pojo.User;
import com.java.service.RoleService;
import com.java.service.TreeService;
import com.java.service.UserService;
import com.java.util.MyUtil;
import com.java.util.ResponseUtil;
 

@Controller
@RequestMapping("/tree")
public class TreeController {
	
	@Resource
	private TreeService treeService;
	@Resource
	private UserService userService;
	@Resource
	private RoleService roleService;
	
	//
	@RequestMapping("/getCheckedTreeMenu")
	public String getCheckedTreeMenu(@RequestParam(value = "roleId", required = false) String roleId,
			HttpServletRequest requset, HttpServletResponse response) throws Exception {
		Role role = roleService.findById(Integer.parseInt(roleId));
		List<Integer> ids=new ArrayList<Integer>();
		List<Tree> ro=treeService.findByRoleId(role.getId());
		if(ro.size()>0) {
			for (Tree tr1 : ro) {
				 ids.add(tr1.getId());
					}
				}
		
		List<Tree> list = getCheckTreesByParentId(-1, ids);
		Gson g = new Gson();
		ResponseUtil.write(response, g.toJson(list));
		return null;
	}
	
	/**
	 * �������������Ȩ���õ�   ���˵����check ѡ��
	 */
	public List<Tree> getCheckTreesByParentId(Integer father, List<Integer> treeIds) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("father", father+"");
		List<Tree> list = treeService.getTreesByFatherOrIds(map);//
		
		for (Tree tree : list) {
			// ��� �Ǹ�ѡ�� �����������ж�
			// tree.setChecked(true);
			if (MyUtil.existStrArr2(tree.getId() + "", treeIds)) {// �ж�id
																			// ��û����ids֮�ڣ��������true
																			// ����false
				tree.setChecked(true);
			}
			if ("open".equals(tree.getState())) {
				continue;
			} else {
				tree.setChildren(getCheckTreesByParentId(tree.getId(), treeIds));
			}
		}
		return list;
	}
	
	
	
	/**
	 *  �ò˵�
	 *  
	 *  
	 */
	@RequestMapping("/getMenu")
	public String getMenu(HttpServletResponse response)throws Exception {
		
		User currentUser = (User) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
		currentUser = userService.findById(currentUser.getId());
		
		List<Integer> ids=new ArrayList<Integer>();
		List<Role> ro=roleService.findByUserId(currentUser.getId());
		if(ro.size()>0) {
		for (Role role : ro) {
			List<Tree> tr=treeService.findByRoleId(role.getId());
			if(tr.size()>0) {
			for (Tree tr1 : tr) {
				 ids.add(tr1.getId());
					}
				}
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();  
		map.put("father", -1);
		map.put("ids", ids);
		List<Tree> treeList = getTreesByParentId(map);
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
		ResponseUtil.write(response, gson.toJson(treeList));
		return null;
	}
	
	
	/**
	 * �ò˵�
	 */
	public List<Tree> getTreesByParentId(Map<String,Object> map) throws Exception {
		//String parentId,String ids  = map
		List<Tree> list = treeService.getTreesByFatherOrIds(map);
		for(Tree tree : list){
			//��� �Ǹ�ѡ��  �����������ж�   
			//tree.setChecked(true);
			if("open".equals(tree.getState())){
				continue;
			}else{
				map.put("father", tree.getId()+"");//����id����ids������
				tree.setChildren(getTreesByParentId(map));
			}
		}
		return list;
	}
	
	
}
