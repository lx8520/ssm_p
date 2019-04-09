package com.java.service;

import java.util.List;
import java.util.Map;

import com.java.pojo.Sub;


public interface SubService {
	public Integer add(Sub sub);
	public Integer update(Sub sub);
	public Sub findById(Integer id);
	public Integer delete(Integer id);
	public List<Sub> findByCarId(Integer CarId);
	public List<Sub> list(Map<String,Object> map);
	public Integer getTotal(Map<String,Object> map);
}
