package com.java.dao;

import java.util.List;
import java.util.Map;

import com.java.pojo.Role;


public interface RoleDao {
	public Integer add(Role role);
	public Role findById(Integer id);
	public List<Role> findByUserId(Integer userId);
	public Integer delete(Integer id);
	public Integer findTotal(Map<String, Object> map);
	public Integer update(Role role);
	public List<Role> findByAll(Map<String, Object> map);
	
	public List<Role> getTreesByFatherOrIds(Map<String,Object> map);
}
