package com.java.dao;

import java.util.List;
import java.util.Map;

import com.java.pojo.CarInformation;

public interface CarInformationDao {
	public Integer add(CarInformation ci);
	public CarInformation findById(Integer id);
	public List<CarInformation> list(Map<String ,Object> map);
	public Integer update(CarInformation ci);
	public Integer delete(Integer id);
	public Integer getTotal(Map<String ,Object> map);
	
	public CarInformation findByFileId(String fileId);
	public CarInformation findByuserId(String userId);

}
