package com.java.listener;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

import com.java.pojo.User;

public class SessionListener implements HttpSessionListener,ServletContextListener {
	private int count;
	private ServletContext servletContext = null;
	
	public SessionListener() {
		count=0;
	}
	private Logger logger=Logger.getLogger(this.getClass());
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		count++;
		setContext(se);
		logger.info("*****the http session is created...****");
	}

	private void setContext(HttpSessionEvent httpSessionEvent) {
		httpSessionEvent.getSession().getServletContext().setAttribute("online", count);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		//在session销毁的时候 把loginMap中保留 的键值对清除
		User user=(User) se.getSession().getAttribute("currentUser");
		if(user!=null) {
			@SuppressWarnings("unchecked")
			Map<String,String> loginMap=(Map<String,String>)se.getSession().getServletContext().getAttribute("loginMap");
			loginMap.remove(user.getName());
			se.getSession().getServletContext().setAttribute("loginMap", loginMap);
			System.out.println(user.getName()+"用户注销！");
		}
		count--;
		setContext(se);
		logger.info("***the http session is destroyed...***");
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		this.servletContext= sce.getServletContext();
		logger.info("***the servlet context is initialized...***");
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		this.servletContext = null;
		logger.info("***the servlet context is destroyed...***");	
	}
	
	
}
