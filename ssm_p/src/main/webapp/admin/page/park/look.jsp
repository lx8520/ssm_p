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
<meta NAME=”renderer” content=”webkit”>
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
<body id="app">


<div style="padding: 10px;">
	<form class="layui-form layui-form-pane">
	
	  <div class="layui-form-item">
	  
	
	
	

	    <div  class="layui-inline" >
	    <label class="layui-form-label">档案编号</label>
	    <div class="layui-input-inline">
		      <input type="text"  disabled="disabled" id="fileId"  value="${carInformation.fileId}" placeholder="请输入帐号" class="layui-input">
	    </div>
	    </div>

	   <div  class="layui-inline">
      <label class="layui-form-label"style="width:35%;">入场日期</label>
      <div class="layui-input-inline"style="width:60%;">
        <input type="text" disabled="disabled" id="date" name="date" lay-verify="date" placeholder="yyyy-MM-dd HH:mm:ss" value="${carInformation.date }" autocomplete="off" class="layui-input" v-model="trueName">
      </div>
   
    </div>


      <div class="layui-inline">
	    <label class="layui-form-label"style="width:35%;">车牌号码</label>
	    <div class="layui-input-inline"style="width:60%;">
		      <input type="text"  disabled="disabled" id="carId" autocomplete="off" value="${carInformation.carId}" v-model="carId" placeholder="请输入帐号" class="layui-input">
	    </div>
	  </div>
	  
	 <div class="layui-inline">
	    <label class="layui-form-label">识别代码</label>
	    <div class="layui-input-inline">
		      <input type="text"  disabled="disabled" id="identifierCode" autocomplete="off" value="${carInformation.identifierCode}" v-model="identifierCode" placeholder="请输入帐号" class="layui-input">
	    </div>
	    </div>
	    
	    
	    <div class="layui-inline">
	    <label class="layui-form-label">车辆类型</label>
	    <div class="layui-input-inline">
		      <input type="text"  disabled="disabled" id="carType" autocomplete="off" value="${carInformation.carType}" v-model="carType" placeholder="请输入帐号" class="layui-input">
	    </div>
	    </div>
	    
	 
		  <div class="layui-inline">
	    <label class="layui-form-label"style="width:35%;">车辆颜色</label>
	    <div class="layui-input-block"style="width:60%;">
		      <input type="text" disabled="disabled"  id="carColor" autocomplete="off" v-model="carColor" value="${carInformation.carColor}" placeholder="请输入备注" class="layui-input">
	    </div>
	  </div>

	  
	  
	
	  <div class="layui-inline">
	    <label class="layui-form-label">车辆品牌</label>
	    <div class="layui-input-block">
		      <input type="text" disabled="disabled"  id="carBrand" autocomplete="off" v-model="carBrand" value="${carInformation.carBrand}" placeholder="请输入备注" class="layui-input">
	    </div>
	  </div>
	  
	  
	  	  <div class="layui-inline">
	    <label class="layui-form-label">收费标准</label>
	    <div class="layui-input-block">
		      <input type="text" disabled="disabled"  id="cost" autocomplete="off" v-model="cost" value="${carInformation.cost}" placeholder="请输入备注" class="layui-input">
	    </div>
	  </div>
	  
	  

      <div class="layui-inline">
	    <label class="layui-form-label"style="width:35%;">登记人</label>
	    <div class="layui-input-inline"style="width:60%;">
		      <input type="text"  disabled="disabled" id="registrationName" autocomplete="off" value="${carInformation.registrationName}" v-model="registrationName" placeholder="请输入帐号" class="layui-input">
	    </div>
	  </div>
	
	  <div class="layui-inline">
	    <label class="layui-form-label">停车场</label>
	    <div class="layui-input-inline">
		      <input type="text"  disabled="disabled" id="park" autocomplete="off" value="${carInformation.park}" v-model="park" placeholder="请输入帐号" class="layui-input">
	    </div>
	    </div>

 <div class="layui-inline">
	    <label class="layui-form-label">执法单位</label>
	    <div class="layui-input-inline">
		      <input type="text"  disabled="disabled" id="lawEnforcement" autocomplete="off" value="${carInformation.lawEnforcement}" v-model="lawEnforcement" placeholder="请输入帐号" class="layui-input">
	    </div>
	    </div>
	  
	  <div class="layui-inline">
	    <label class="layui-form-label"style="width:35%;">备注</label>
	    <div class="layui-input-block"style="width:60%;">
		      <input type="text" disabled="disabled"  id="note" autocomplete="off" v-model="note" value="${carInformation.note}" placeholder="请输入备注" class="layui-input">
	    </div>
	  </div>

      <div class="layui-inline">
	    <label class="layui-form-label"style="width:35%;">物品名称</label>
	    <div class="layui-input-inline"style="width:60%;">
		      <input type="text"  disabled="disabled" id="itemName" autocomplete="off" value="${carInformation.itemName}" v-model="itemName" placeholder="请输入帐号" class="layui-input">
	    </div>
	  </div>

	  <div class="layui-inline">
	    <label class="layui-form-label"style="width:35%;">规格</label>
	    <div class="layui-input-inline"style="width:60%;">
		      <input type="text"  disabled="disabled" id="specifications" autocomplete="off" value="${carInformation.specifications}" v-model="specifications" placeholder="请输入帐号" class="layui-input">
	    </div>
	    </div>

      <div class="layui-inline">
	    <label class="layui-form-label"style="width:35%;">数量</label>
	    <div class="layui-input-inline"style="width:60%;">
		      <input type="text"  disabled="disabled" id="itemNum" autocomplete="off" value="${carInformation.itemNum}" v-model="itemNum" placeholder="请输入帐号" class="layui-input">
	    </div>
	  </div>
	 
	  <div class="layui-inline">
	    <label class="layui-form-label"style="width:35%;">备注</label>
	    <div class="layui-input-inline"style="width:60%;">
		      <input type="text"  disabled="disabled" id="itemNote" autocomplete="off" value="${carInformation.itemNote}" v-model="itemNote" placeholder="请输入帐号" class="layui-input">
	    </div>
	    </div>
	
	    
	    <div>
	    <label class="layui-form-label"style="width:100%">照片</label>
	    <img src="${carInformation.photo1}" alt="images"/>
	    <img src="${carInformation.photo2}" alt="images">
	    <img src="${carInformation.photo3}" alt="images">
	    <img src="${carInformation.photo4}" alt="images">
	    </div>
	    
	    
	   
	  
	  
	  </form>
</div>


<script>
layui.use([ 'laydate', 'laypage', 'layer', 'table', 'carousel',
		'upload', 'element' ], function() {
	var laydate = layui.laydate //日期
	, laypage = layui.laypage //分页
	layer = layui.layer //弹层
	, table = layui.table //表格
	, carousel = layui.carousel //轮播
	, upload = layui.upload //上传
	, element = layui.element; //元素操作
	laydate.render({
	    elem: '#date'
	    	,type: 'datetime'
	  });
	  laydate.render({
	    elem: '#date1'
	    	,type: 'datetime'
	  });
	 
});
</script>
<script>
var app = new Vue({
	el : '#app',
	data : {
	}
});
 
</script>


</body>
</html>