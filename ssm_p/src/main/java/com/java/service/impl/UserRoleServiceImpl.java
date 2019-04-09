package com.java.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.java.dao.UserRoleDao;

import com.java.pojo.UserRole;
import com.java.service.UserRoleService;
@Service("userRoleService")
public class UserRoleServiceImpl implements UserRoleService {
	@Resource
	private UserRoleDao userRoleDao;
	public Integer add(UserRole userRole) {
		// TODO Auto-generated method stub
		return userRoleDao.add(userRole);
	}

	public Integer delete(Integer id) {
		// TODO Auto-generated method stub
		return userRoleDao.delete(id);
	}

	

}
