package com.java.pojo;

public class Result {

	//{"success":false,"errorcode":-1,"msg":"����ʧ��!"}
	
	private boolean success;
	private  Integer errorcode ; 
	private String msg ;
	
	private String url;
	private String path;
	
	
	public String getPath() {
		return path;
	}


	public void setPath(String path) {
		this.path = path;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public Result() {
		super();
	}
	
	
	public Integer getErrorcode() {
		return errorcode;
	}


	public void setErrorcode(Integer errorcode) {
		this.errorcode = errorcode;
	}


	public Result(boolean success, int errorcode, String msg) {
		super();
		this.success = success;
		this.errorcode = errorcode;
		this.msg = msg;
	}
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
 
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	} 
	
	
	
}
