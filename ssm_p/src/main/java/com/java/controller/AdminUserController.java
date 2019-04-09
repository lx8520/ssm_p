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
import com.java.pojo.PageBean;
import com.java.pojo.Result;
import com.java.pojo.Role;
import com.java.pojo.RoleMenu;
import com.java.pojo.Tree;
import com.java.pojo.User;
import com.java.pojo.UserRole;
import com.java.service.RoleMenuService;
import com.java.service.RoleService;
import com.java.service.TreeService;
import com.java.service.UserRoleService;
import com.java.service.UserService;
import com.java.util.CryptographyUtil;
import com.java.util.MyUtil;
import com.java.util.ResponseUtil;
import com.java.util.StringUtil;
 


@Controller
@RequestMapping("/admin/user")
public class AdminUserController {
	
	@Resource
	private UserService userService;
	@Resource
	private UserRoleService userRoleService;
	@Resource
	private RoleService roleService;
	@Resource
	private TreeService treeService;
	
	@Resource
	private RoleMenuService roleMenuService;
	
	@RequestMapping("/add")
	public String add(User user, HttpServletResponse response, HttpServletRequest request) throws Exception {
		user.setIp(MyUtil.getRemoteAddress(request));
		user.setPassword(
				//CryptographyUtil.md5(
						user.getPassword()
					//	, user.getName())
				);
		user.setCreateDateTime(new Date());
		
		int resultTotal = userService.add(user);
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
	@RequestMapping("/role/add")
	public String roleadd(@RequestParam(value = "text", required = false) String text,
			HttpServletResponse response, HttpServletRequest request) throws Exception {
		Role role=new Role();
		role.setText(text);
		int resultTotal = roleService.add(role);
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
	
	
	/**
	 * /admin/user/update
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/update")
	public String update(User user, HttpServletResponse response, HttpServletRequest request) throws Exception {
		if(StringUtil.isNotEmpty(user.getPassword())){
			user.setPassword(CryptographyUtil.md5(user.getPassword(),user.getName()));
		}
		int resultTotal = userService.update(user);
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
	
	@RequestMapping("/role/edit")
	public String roleupdate(Role role, HttpServletResponse response, HttpServletRequest request) throws Exception {
		
		int resultTotal = roleService.update(role);
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
	
	@RequestMapping("/permissions/add")
	public String permissionsadd(Tree tree, HttpServletResponse response, HttpServletRequest request) throws Exception {
		int resultTotal = treeService.add(tree);
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
	@RequestMapping("/permissions/edit")
	public String permissionsupdate(Role role, HttpServletResponse response, HttpServletRequest request) throws Exception {
		
		int resultTotal = roleService.update(role);
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
	
	@RequestMapping("/permissions/tree/edit")
	public String permissionTreesupdate(Tree tree, HttpServletResponse response, HttpServletRequest request) throws Exception {
		
		int resultTotal = treeService.update(tree);
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
	
	/**
	 *修改 密码 在不退出登陆的情况下。可以多次修改密码。
	 */
	@RequestMapping("/modifyPassword")
	public String modifyPassword(String newPassword, HttpServletResponse response) throws Exception {
		User user = (User) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
		user.setPassword(CryptographyUtil.md5(newPassword, "chenhao"));
		int resultTotal = userService.update(user);
		Result result = new Result();
		Gson gson = new Gson();
		if (resultTotal > 0) {
			result.setSuccess(true);
		} else {
			result.setSuccess(false);
			result.setMsg("数据库,严重错误!!!!!!!");
		}
		ResponseUtil.write(response, gson.toJson(result));
		return null;
	}
	
	
	@RequestMapping("/list")
	public String list(@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "limit", required = false) String rows,
			@RequestParam(value = "q", required = false) String q, 
			@RequestParam(value = "date1", required = false) String date1, 
			@RequestParam(value = "date2", required = false) String date2, 
			HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		map.put("q", StringUtil.formatLike(q));
		map.put("date1", date1);
		map.put("date2", date2);
		
		List<User> list = userService.list(map);
		Integer total = userService.getTotal(map);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
		
		map.clear();
		map.put("data", list);
		map.put("count", total);
		map.put("code", 0);
		map.put("msg", "");
		ResponseUtil.write(response, gson.toJson(map));
		return null;
	}
	
	@RequestMapping("/permissions")
	public String permissionslist(@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "limit", required = false) String rows,
			@RequestParam(value = "q", required = false) String q, 
			HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		map.put("q", StringUtil.formatLike(q));
		List<Tree> list = treeService.list(map);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
		Integer total= treeService.findTotal(map);
		map.put("data", list);
		map.put("count", total);
		map.put("code", 0);
		map.put("msg", "");
		ResponseUtil.write(response, gson.toJson(map));
		
		return null;
	}
	@RequestMapping("/permissions/delete")
	public String permissionsdelete(@RequestParam(value = "ids", required = false) String ids, HttpServletResponse response)
			throws Exception {
		String[] idsStr = ids.split(",");
		Gson gson = new Gson();
		Result result = new Result();
		for (int i = 0; i < idsStr.length; i++) {
			treeService.delete(Integer.parseInt(idsStr[i]));
		}
		result.setSuccess(true);
		ResponseUtil.write(response, gson.toJson(result));
		return null;
	}

	@RequestMapping("/role")
	public String role(@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "limit", required = false) String rows,
			@RequestParam(value = "q", required = false) String q, 
			HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		List<Role> list = roleService.findByAll(map);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
		Integer total= roleService.findTotal(map);
		map.put("data", list);
		map.put("count", total);
		map.put("code", 0);
		map.put("msg", "");
		ResponseUtil.write(response, gson.toJson(map));
		
		return null;
		
	}
	

	@RequestMapping("/delete")
	public String delete(@RequestParam(value = "ids", required = false) String ids, HttpServletResponse response)
			throws Exception {
		String[] idsStr = ids.split(",");
		Gson gson = new Gson();
		Result result = new Result();
		for (int i = 0; i < idsStr.length; i++) {
			userService.delete(Integer.parseInt(idsStr[i]));
		}
		result.setSuccess(true);
		ResponseUtil.write(response, gson.toJson(result));
		return null;
	}
	@RequestMapping("/role/delete")
	public String roledelete(@RequestParam(value = "ids", required = false) String ids, HttpServletResponse response)
			throws Exception {
		String[] idsStr = ids.split(",");
		Gson gson = new Gson();
		Result result = new Result();
		for (int i = 0; i < idsStr.length; i++) {
			roleService.delete(Integer.parseInt(idsStr[i]));
		}
		result.setSuccess(true);
		ResponseUtil.write(response, gson.toJson(result));
		return null;
	}
	@RequestMapping("/role/update")
	public String roleupdate(@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "menuIds", required = false) String menuIds,HttpServletResponse response, HttpServletRequest request) throws Exception {
		List<Integer> ids=MyUtil.Str_ids_To_ListInteger_ids(menuIds);
		for (Integer integer : ids) {
			System.out.println(integer);
		}
		if(ids.size()==0) {
			int resultTotal=userRoleService.delete(Integer.parseInt(id));
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
		}else {
			UserRole userRole=new UserRole();
			userRoleService.delete(Integer.parseInt(id));
			for (Integer integer : ids) {
				userRole.setUserid(id);
				userRole.setRoleid(integer+"");
				int resultTotal = userRoleService.add(userRole);
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
			}
			
		}
		
		
		return null;
	}

	
	@RequestMapping("/role/tree/update")
	public String roletreeupdate(@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "menuIds", required = false) String menuIds,HttpServletResponse response, HttpServletRequest request) throws Exception {
		List<Integer> ids=MyUtil.Str_ids_To_ListInteger_ids(menuIds);
		for (Integer integer : ids) {
			System.out.println(integer);
		}
		if(ids.size()==0) {
			int resultTotal=roleMenuService.delete(Integer.parseInt(id));
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
		}else {
			RoleMenu RoleMenu=new RoleMenu();
			roleMenuService.delete(Integer.parseInt(id));
			for (Integer integer : ids) {
				RoleMenu.setRoleid(id);
				RoleMenu.setTreeid(integer+"");
				int resultTotal = roleMenuService.add(RoleMenu);
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
			}
			
		}
		
		
		return null;
	}
}
