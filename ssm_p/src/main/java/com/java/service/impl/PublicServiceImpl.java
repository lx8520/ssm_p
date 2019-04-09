package com.java.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.java.pojo.Role;
import com.java.pojo.Tree;
import com.java.pojo.User;
import com.java.service.PublicService;
import com.java.service.RoleService;
import com.java.service.TreeService;
import com.java.service.UserService;
import com.java.util.MyUtil;

 



@Service("publicService")
public class PublicServiceImpl implements PublicService {
	
	@Resource
	private TreeService treeService;
	@Resource
	private UserService userService;
	@Resource
	private RoleService roleService;
 
	
	public void addLeftMenu(ModelAndView mav) {
		
		mav.addObject("leftPage", "/admin/common/left_menu.jsp");
		
		User currentUser = (User) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
		if(currentUser==null)return;
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
		
		if(ids.size()>0){
		}else{
			mav.addObject("treeList", null);
		}
		try {
			List<Tree> treeList = getTreesByParentId(map);
			mav.addObject("treeList", treeList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * �ò˵�
	 */
	public List<Tree> getTreesByParentId(Map<String,Object> map) throws Exception {
		//String parentId,String ids  = map
		List<Tree> list = treeService.getTreesByFatherOrIds(map);
		Iterator<Tree> it = list.iterator();
		while(it.hasNext()){
		    Tree x = it.next();
		    if(9==x.getId()){
		        it.remove();
		    }
		}
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
