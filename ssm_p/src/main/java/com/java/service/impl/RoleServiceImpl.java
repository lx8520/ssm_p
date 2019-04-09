package com.java.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.java.dao.RoleDao;
import com.java.pojo.Role;
import com.java.service.RoleService;
@Service("roleService")
public class RoleServiceImpl implements RoleService {
	@Resource
	private RoleDao roleDao;

	public Integer add(Role role) {
		// TODO Auto-generated method stub
		return roleDao.add(role);
	}

	public Role findById(Integer id) {
		// TODO Auto-generated method stub
		return roleDao.findById(id);
	}

	public List<Role> findByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return roleDao.findByUserId(userId);
	}

	public Integer delete(Integer id) {
		// TODO Auto-generated method stub
		return roleDao.delete(id);
	}



	public Integer update(Role role) {
		// TODO Auto-generated method stub
		return roleDao.update(role);
	}

	@Override
	public List<Role> findByAll(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return roleDao.findByAll(map);
	}

	@Override
	public Integer findTotal(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return roleDao.findTotal(map);
	}

}
