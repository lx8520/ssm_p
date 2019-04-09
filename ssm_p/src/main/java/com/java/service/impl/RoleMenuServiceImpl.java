package com.java.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.java.dao.RoleMenuDao;
import com.java.pojo.RoleMenu;
import com.java.service.RoleMenuService;
@Service("roleMenuService")
public class RoleMenuServiceImpl implements RoleMenuService {

	@Resource
	private RoleMenuDao  roleMenuDao;
	
	public Integer add(RoleMenu rm) {
		// TODO Auto-generated method stub
		return roleMenuDao.add(rm);
	}

	public Integer delete(Integer id) {
		// TODO Auto-generated method stub
		return roleMenuDao.delete(id);
	}

}
