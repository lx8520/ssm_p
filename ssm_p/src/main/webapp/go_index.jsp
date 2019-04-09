<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%String path=request.getContextPath();
	   String basePath= request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- JSTL -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- JSTL -->

<base href="<%=basePath %>>">
<!-- 强制  高速模式 渲染网页    -->
<meta NAME="renderer" content="webkit">
<!-- 强制  高速模式 渲染网页    -->

<link href="static/favicon.ico" rel="shortcut icon" />

<!--添加  jq  支持加载-->
<script	src="static/easy-ui/jquery.min.js"></script>
<!--添加  jq  支持加载-->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

<% response.sendRedirect("index.html"); %>

</body>
</html>