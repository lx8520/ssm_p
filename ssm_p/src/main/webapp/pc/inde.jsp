
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
		<title>首页</title>

		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="renderer" content="webkit">

		<!--添加  jq  支持加载-->
		<script	src="static/easy-ui/jquery.min.js"></script>
		<!--添加  jq  支持加载-->
		<!-- 引入 sockJS  -->
		<script src="static/sockjs.min.js"></script>
		
		<!-- 自定义JS文件 ,逻辑写在这里-->
		<script src="static/index.js"></script>

	</head>

	<body>
		
		<!-- 最外边框 -->
		<div style="margin: 20px auto; border: 1px solid blue; width: 300px; height: 500px;">
		
			<!-- 消息展示框 -->
			<div id="msg" style="width: 100%; height: 70%; border: 1px solid yellow;overflow: auto;"></div>
		
			<!-- 消息编辑框 -->
			<textarea id="tx" style="width: 100%; height: 20%;"></textarea>
			
			<!-- 消息发送按钮 -->
			<button id="TXBTN" style="width: 100%; height: 8%;">发送数据</button>
			
		</div>


	</body>

</html>
