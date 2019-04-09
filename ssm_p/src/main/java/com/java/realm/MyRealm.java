package com.java.realm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.java.pojo.Role;
import com.java.pojo.Tree;
import com.java.pojo.User;
import com.java.service.RoleService;
import com.java.service.TreeService;
import com.java.service.UserService;
import com.java.util.MyUtil;
import com.java.util.StringUtil;
 

 



/**
 * 自定义Realm
 * @author Administrator
 *
 */
public class MyRealm extends AuthorizingRealm{

	@Resource
	private UserService  userService;
 
	@Resource
	private TreeService  treeService;
	@Resource
	private RoleService roleService;
 
	
	
	/**
	 * 为当前的登录的用户角色和权限
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// 通过用户取得他应该拥有的权限
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		// 设置角色 集合 这个目前用不到。
		// authorizationInfo.setRoles(roles);
		User user = (User) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
		if(user==null)return null;
		
		user = userService.findById(user.getId());
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<Integer> ids=new ArrayList<Integer>();
		List<Role> ro=roleService.findByUserId(user.getId());
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
		map.put("ids", ids);
		if(ids.size()>0){
		}else{
			return authorizationInfo;
		}
		
		List<Tree> treeList = treeService.getTreesByFatherOrIds(map);
		// 权限集合
		Set<String> stringPermissions = new HashSet<String>();
		for (Tree tree : treeList) {
			if (StringUtil.isNotEmpty(tree.getPermissions())) {
				stringPermissions.add(tree.getPermissions());
			}
		}
		authorizationInfo.setStringPermissions(stringPermissions);
		return authorizationInfo;
		
	}
	
	
	/**
	 * 验证当前登录的用户
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		
			String name=(String) token.getPrincipal();//我上面使用了name
			User user=userService.findByName(name);
			if(user!=null){
				//SecurityUtils.getSubject().getSession().setAttribute("currentUser", user); //把当前用户信息存到session中
				AuthenticationInfo authcInfo=new SimpleAuthenticationInfo(user.getName(), user.getPassword(), "xxx");
				return authcInfo;
		}
		return null;
	}

}
