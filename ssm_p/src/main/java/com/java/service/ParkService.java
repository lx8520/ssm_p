package com.java.service;

import com.java.pojo.Park;

public interface ParkService {
	public Integer add(Park park);
	public Integer update(Park park);
	public Park findById(Integer id);
	public Integer delete(Integer id);
}
