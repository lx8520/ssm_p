package com.java.service;

import java.util.Map;

import org.springframework.web.servlet.ModelAndView;


 

public interface PublicService {
	
	/**
	 * 
	 * @param ���mav.addObject("leftPage", "/admin/common/left_menu.jsp");
	 * @param �Լ����leftmenu ������
	 * @param mav
	 */
	public void addLeftMenu(ModelAndView mav);
		
		
	 
}
