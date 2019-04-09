<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<% 
	String path=request.getContextPath();
	String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<base href="<%=basePath %>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- JSTL -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- JSTL -->
<!-- 强制  高速模式 渲染网页    -->
<meta NAME="renderer" content="webkit">
<!-- 强制  高速模式 渲染网页    -->

<link href="static/favicon.ico" rel="shortcut icon" />

<!--添加  jq  支持加载-->
<script	src="static/easy-ui/jquery.min.js"></script>
<!--添加  jq  支持加载-->

<!--添加 layui  支持加载-->
<link rel="stylesheet"	href="static/layui-v2.2.5/layui/css/layui.css">
<script	src="static/layui-v2.2.5/layui/layui.js"></script>
<!--添加 layui  支持加载-->


<!-- 加入移动布局 -->
<meta name="viewport"	content="width=device-width, initial-scale=1.0,maximum-scale=1.0, user-scalable=no" />
<!-- 加入移动布局 -->

<!--添加  vue.js 支持加载-->
<script src="${pageContext.request.contextPath}/static/vue/vue.min.js"></script>
<!--添加  vue.js 支持加载-->
<style>
html, body {
}
.layui-form-item {
    margin-bottom: 3px;
}
</style>

</head>
<body >

	<ul class="layui-timeline">
	<c:forEach items="${sub}" var="su">
		<li class="layui-timeline-item">
			<i class="layui-icon layui-timeline-axis">&#xe63f;</i>
			<div class="layui-timeline-content layui-text">
				<h3 class="layui-timeline-title" >${su.subDateTime}</h3>
				<p>提交人:${su.trueName}</p>
				<p>所属部门：${su.state}</p>
				<p>${su.sub}</p>
				<p>${su.note}</p>
			</div>
		</li>	
		</c:forEach>	
	
	</ul>





<script>
layui.use([ 'laydate', 'laypage', 'layer', 'table', 'carousel','form',
		'upload', 'element' ], function() {
	var form = layui.form
	, laydate = layui.laydate //日期
	, laypage = layui.laypage //分页
	,layer = layui.layer //弹层
	, table = layui.table //表格
	, carousel = layui.carousel //轮播
	, upload = layui.upload //上传
	, element = layui.element; //元素操作
	laydate.render({ 
		  elem: '#date'
		  ,type: 'datetime'
		  ,format:'yyyy-MM-dd HH:mm:ss'
		});	
});
</script>

</body>
</html>