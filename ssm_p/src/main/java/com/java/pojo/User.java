package com.java.pojo;

import java.util.Date;
import java.util.List;


public class User {
	private Integer id;
	private String name;//帐号
	private String password;//密码
	private Date createDateTime;
	private String ip;//创建用户的ip位置
	private String remark;//备注
	private String trueName;//真实姓名 
	private String unit;//权限单位
	private String state;
	
	private List<Tree> menus;//存储用户权限菜单
	
	
	public List<Tree> getMenus() {
		return menus;
	}
	public void setMenus(List<Tree> menus) {
		this.menus = menus;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getTrueName() {
		return trueName;
	}
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public Date getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

 
	
	
	
	
	
	
}
