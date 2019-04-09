package com.java.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.java.dao.SubDao;
import com.java.pojo.Sub;
import com.java.service.SubService;
@Service("subService")
public class SubServiceImpl implements SubService {
	@Resource
	private SubDao subDao;
	@Override
	public Integer add(Sub sub) {
		// TODO Auto-generated method stub
		return subDao.add(sub);
	}

	@Override
	public Integer update(Sub sub) {
		// TODO Auto-generated method stub
		return subDao.update(sub);
	}

	@Override
	public Sub findById(Integer id) {
		// TODO Auto-generated method stub
		return subDao.findById(id);
	}

	@Override
	public Integer delete(Integer id) {
		// TODO Auto-generated method stub
		return subDao.delete(id);
	}

	@Override
	public List<Sub> findByCarId(Integer CarId) {
		// TODO Auto-generated method stub
		return subDao.findByCarId(CarId);
	}

	@Override
	public List<Sub> list(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return subDao.list(map);
	}

	@Override
	public Integer getTotal(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return subDao.getTotal(map);
	}

}
