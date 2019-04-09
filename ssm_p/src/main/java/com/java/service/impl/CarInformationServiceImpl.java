package com.java.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.java.dao.CarInformationDao;
import com.java.pojo.CarInformation;
import com.java.service.CarInformationService;
@Service("carInformationService")
public class CarInformationServiceImpl implements CarInformationService {
	@Resource
	private CarInformationDao carInformationDao;
	public Integer add(CarInformation ci) {
		// TODO Auto-generated method stub
		return carInformationDao.add(ci);
	}

	public CarInformation findById(Integer id) {
		// TODO Auto-generated method stub
		return carInformationDao.findById(id);
	}

	

	public Integer delete(Integer id) {
		// TODO Auto-generated method stub
		return carInformationDao.delete(id);
	}

	public List<CarInformation> list(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return carInformationDao.list(map);
	}

	public Integer update(CarInformation ci) {
		// TODO Auto-generated method stub
		return carInformationDao.update(ci);
	}

	public Integer getTotal(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return carInformationDao.getTotal(map);
	}

	public CarInformation findByFileId(String fileId) {
		// TODO Auto-generated method stub
		return carInformationDao.findByFileId(fileId);
	}

	@Override
	public CarInformation findByuserId(String userId) {
		// TODO Auto-generated method stub
		return carInformationDao.findByuserId(userId);
	}

	

}
