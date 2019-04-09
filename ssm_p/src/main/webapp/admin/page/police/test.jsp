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
<body >
 <!-- 轮播 -->
<div class="layui-carousel" id="test10">
  <div carousel-item="">
    <div><img src="../images/20190321133624105832.jpg"></div>
    <div><img src="../images/20190321153250142924.jpg"></div>
    <div><img src="../images/20190321153520118424.jpg"></div>
    <div><img src=""></div>
    <div><img src=""></div>
    <div><img src=""></div>
    <div><img src=""></div>
  </div>
</div>



<!-- 多图片上传 -->
<div class="layui-upload">
  <button type="button" class="layui-btn" id="test2">多图片上传</button> 
  <blockquote class="layui-elem-quote layui-quote-nm" style="margin-top: 10px;">
    预览图：
    <div class="layui-upload-list" id="demo2"></div>
 </blockquote>
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
	  
	  //图片轮播
	  carousel.render({
	    elem: '#test10'
	    ,width: '508px'
	    ,height: '300px'
	    ,interval: 5000
	  });
	  var $ = layui.$, active = {
	    set: function(othis){
	      var THIS = 'layui-bg-normal'
	      ,key = othis.data('key')
	      ,options = {};
	      
	      othis.css('background-color', '#5FB878').siblings().removeAttr('style'); 
	      options[key] = othis.data('value');
	      ins3.reload(options);
	    }
	  };
	  //监听开关
	  form.on('switch(autoplay)', function(){
	    ins3.reload({
	      autoplay: this.checked
	    });
	  });
	  
	  $('.demoSet').on('keyup', function(){
	    var value = this.value
	    ,options = {};
	    if(!/^\d+$/.test(value)) return;
	    options[this.name] = value;
	    ins3.reload(options);
	  });
	  
	  //其它示例
	  $('.demoTest .layui-btn').on('click', function(){
	    var othis = $(this), type = othis.data('type');
	    active[type] ? active[type].call(this, othis) : '';
	  });
	  
	  
	  
	  
	  //多图片上传
	  upload.render({
	    elem: '#test2'
	    ,url: '/upload/'
	    ,multiple: true
	    ,before: function(obj){
	      //预读本地文件示例，不支持ie8
	      obj.preview(function(index, file, result){
	        $('#demo2').append('<img src="'+ result +'" alt="'+ file.name +'" class="layui-upload-img">')
	      });
	    }
	    ,done: function(res){
	      //上传完毕
	    }
	  });
	  
	  
	 
});
</script>
</body>
</html>