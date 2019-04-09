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

<script>
var fileId='${fileId}';
var save_url = '${save_url}';
function sav(){
	var carType=$("#carType").val();
	var cost=$("#cost").val();
	var lawEnforcement=$("#lawEnforcement").val();
	var index = layer.load(1, {
		  shade: [0.1,'#fff'] //0.1透明度的白色背景
	});
	
	$.post(save_url,{
		fileId:fileId
		,carId:app.carId
		,identifierCode:app.identifierCode
		,carType:carType
		,carColor:app.carColor
		,carBrand:app.carBrand
		,cost:cost
		,park:app.park
		,note:app.note
		,itemName:app.itemName
		,lawEnforcement:lawEnforcement
		,police:app.police
		,specifications:app.specifications
		,itemNum:app.itemNum
		,itemNote:app.itemNote
		,photo1:app.img_url1
		 
		},function(result){
		if(result.success){
			//调用 父窗口的  关闭所有窗口 并且刷新 页面
			window.parent.closeDlg(result.msg);
		}else{
			layer.closeAll();//关闭loading
			layer.msg(result.msg);
		}
	},'json');
	
}


</script>
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
	    <label class="layui-form-label"style="width:35%;">档案编号</label>
	    <div class="layui-input-inline"style="width:60%;" >
		      <input type="text"  disabled="disabled" id="fileId" autocomplete="off" value="${fileId }" v-model="fileId" placeholder="请输入帐号" class="layui-input">
	    </div>
	    </div>
	
	  




      <div class="layui-form-item">
	    <label class="layui-form-label"style="width:35%;">车牌号码</label>
	    <div class="layui-input-inline"style="width:60%;">
		      <input type="text"  id="carId" autocomplete="off" value="${carInformation.carId}" v-model="carId" placeholder="请输入帐号" class="layui-input">
	    </div>
	  </div>
	 
 
 
	 <div class="layui-form-item">
	    <label class="layui-form-label"style="width:35%;">识别代码</label>
	    <div class="layui-input-inline"style="width:60%;">
		      <input type="text"   id="identifierCode" autocomplete="off" value="${carInformation.identifierCode}" v-model="identifierCode" placeholder="请输入帐号" class="layui-input">
	    </div>
	    </div>
	   
	    
	
	  <div class="layui-form-item">
	    <label class="layui-form-label">车辆类型</label>
	    <div class="layui-input-inline">
	      <select name="interest" id="carType" value="" v-model="carType" lay-filter="carType" >
	        <option value="${carInformation.carType}"selected>${carInformation.carType}</option>
	        <option value="1x">1x</option>
	        <option value="2x">2x</option>
	        <option value="3x">3x</option>
	        <option value="4x">4x</option>
	        <option value="5x">5x</option>
	      </select>
	    </div>
	  </div>
	 
   
	  <div class="layui-inline">
	    <label class="layui-form-label"style="width:35%;">车辆颜色</label>
	    <div class="layui-input-block"style="width:60%;">
		      <input type="text"   id="carColor" autocomplete="off" v-model="carColor" value="${carInformation.carColor}" placeholder="请输入备注" class="layui-input">
	    </div>
	  </div>
	
	  

	  <div class="layui-inline">
	    <label class="layui-form-label"style="width:35%;">车辆品牌</label>
	    <div class="layui-input-block"style="width:60%;">
		      <input type="text"   id="carBrand" autocomplete="off" v-model="carBrand" value="${carInformation.carBrand}" placeholder="请输入备注" class="layui-input">
	    </div>
	  </div>
	  
	     
	  <div class="layui-inline">
	    <label class="layui-form-label"style="width:35%;">收费标准</label>
	    <div class="layui-input-block"style="width:60%;">
	       
	      <select name="interest" id="cost" v-model="cost"lay-filter="cost" >
	        <option value="${carInformation.cost}"selected>${carInformation.cost}</option>
	        <option value="1x">1x</option>
	        <option value="2x">2x</option>
	        <option value="3x">3x</option>
	        <option value="4x">4x</option>
	        <option value="5x">5x</option>
	      </select>
	    </div>
	  </div>
	
	  
	 
	   <div class="layui-inline">
	    <label class="layui-form-label"style="width:35%;">停车场</label>
	    <div class="layui-input-inline"style="width:60%;">
		      <input type="text"   id="park" autocomplete="off" value="${carInformation.park}" v-model="park" placeholder="请输入帐号" class="layui-input">
	    </div>
	    </div>
	    
	   
	  
	 <div class="layui-inline">
	    <label class="layui-form-label"style="width:35%;">备注</label>
	    <div class="layui-input-block"style="width:60%;">
		      <input type="text"   id="note" autocomplete="off" v-model="note" value="${carInformation.note}" placeholder="请输入备注" class="layui-input">
	    </div>
	  </div>
	  <div class="layui-inline">
	    <label class="layui-form-label"style="width:35%;">物品名称</label>
	    <div class="layui-input-inline"style="width:60%;">
		      <input type="text"   id="itemName" autocomplete="off" value="${carInformation.itemName}" v-model="itemName" placeholder="请输入帐号" class="layui-input">
	    </div>
	  </div>
	  <div class="layui-inline">
	    <label class="layui-form-label">执法单位</label>
	    <div class="layui-input-block layui-inline">
	      <select name="interest" id="lawEnforcement" v-model="lawEnforcement" lay-filter="lawEnforcement" >
	        <option value=""></option>
	        <option value="1x">1x</option>
	        <option value="2x">2x</option>
	        <option value="3x">3x</option>
	        <option value="4x">4x</option>
	        <option value="5x">5x</option>
	      </select>
	    </div>
	  </div>
	  
	  <div class="layui-inline">
	    <label class="layui-form-label"style="width:35%;">办事民警</label>
	    <div class="layui-input-block"style="width:60%;">
		      <input type="text"   id="police" autocomplete="off" v-model="police" value="${carInformation.police}" placeholder="请输入备注" class="layui-input">
	    </div>
	  </div>
	   <div class="layui-inline">
	    <label class="layui-form-label"style="width:35%;">规格</label>
	    <div class="layui-input-inline"style="width:60%;">
		      <input type="text"  id="specifications" autocomplete="off" value="${carInformation.specifications}" v-model="specifications" placeholder="请输入帐号" class="layui-input">
	    </div>
	    </div>
	   <div class="layui-inline">
	    <label class="layui-form-label"style="width:35%;">数量</label>
	    <div class="layui-input-inline"style="width:60%;">
		      <input type="text"   id="itemNum" autocomplete="off" value="${carInformation.itemNum}" v-model="itemNum" placeholder="请输入帐号" class="layui-input">
	    </div>
	  </div>
	  <div class="layui-inline">
	    <label class="layui-form-label"style="width:35%;">备注</label>
	    <div class="layui-input-inline"style="width:60%;">
		      <input type="text"   id="itemNote" autocomplete="off" value="${carInformation.itemNote}" v-model="itemNote" placeholder="请输入帐号" class="layui-input">
	    </div>
	    </div>
	    

     <label class="layui-form-label">照片</label>
       <div class="layui-input-block"> 
        <button type="button" class="layui-btn" id="upload1">上传图片</button> 
        <input type="text"   id="img_url1" autocomplete="off" value="${carInformation.photo1}" v-model="img_url1" placeholder="" class="layui-input">
       <div class="layui-upload-list"> 
       <img class="layui-upload-img" width="100px" height="80px" id="demo1"/>
        <p id="demoText"></p>
       </div>
         </div> 


         
	    
	  </form>
		<div class="site-demo-button" style="margin-top: 20px;">
		  <button id="save" onclick="sav()" class="layui-btn site-demo-layedit" data-type="content">${btn_text }</button>
		</div>
</div>


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
	
	
	  var uploadInst1 = upload.render({
          elem: '#upload1' //绑定元素
          ,url: 'admin/police/car/addimage' //上传接口
          ,before: function(obj){
              //预读本地文件示例，不支持ie8
            obj.preview(function(index, file, result){
                  $('#demo1').attr('src', result); //图片链接（base64）
              });
          }
          ,done: function(result){
              //如果上传失败
              if(result.success){
              	alert(result.url)
              	 $('#img_url1').val(result.url).change();
              }else{
              	return layer.msg('上传失败');
              	
              }
              
          
              

          }
          ,error: function(){
              //演示失败状态，并实现重传
              var demoText = $('#demoText');
              demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-mini demo-reload">重试</a>');
              demoText.find('.demo-reload').on('click', function(){
                  uploadInst1.upload();
              });
          }
      });  

	  form.on('select(carType)', function(data){
		  console.log(data.elem); //得到select原始DOM对象
		  console.log(data.value); //得到被选中的值
		  console.log(data.othis); //得到美化后的DOM对象
		  $("#carType").val(data.value);
		});    
	  form.on('select(cost)', function(data){
		  console.log(data.elem); //得到select原始DOM对象
		  console.log(data.value); //得到被选中的值
		  console.log(data.othis); //得到美化后的DOM对象
		  $("#cost").val(data.value);
		});
	  form.on('select(lawEnforcement)', function(data){
		  console.log(data.elem); //得到select原始DOM对象
		  console.log(data.value); //得到被选中的值
		  console.log(data.othis); //得到美化后的DOM对象
		  $("#lawEnforcement").val(data.value);
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