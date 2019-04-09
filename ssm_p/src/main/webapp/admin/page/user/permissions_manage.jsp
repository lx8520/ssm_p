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
<title>${title}</title>

<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %> 
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
<meta name="viewport" content="width=device-width, initial-scale=1.0,maximum-scale=1.0, user-scalable=no"/>


<!-- 引入manage 的基础css -->
<script	src="${pageContext.request.contextPath}/static/common/manage/manage_base.js"></script>
<link href="${pageContext.request.contextPath}/static/common/manage/manage_base.css" rel="stylesheet"/>
<!-- 引入manage 的基础css -->


</head>
<style>
body{
	padding-top: 3px;
}
</style>
<body>
<script>

//用户选中的行ids = 1,2,3,5   len=4
var global_ids;
var global_ids_len;
//用户选中的行ids = 1,2,3,5   len=4
var w ;//窗口的宽
var h ;//窗口的高

//子窗口调用 的  关闭窗口方法 
function closeDlg(msg){
	 layer.closeAll();
	 layer.msg(msg);
	 reload_data();
}

//相当前刷新  重新加载
function reload_data(){
	table.reload('table', {
		 where: {
	        }
    });
}

//打开添加窗口
function add(){
	w = 500;
	h = 400;
	
	layer.open({
	  type: 2,
	  title: '添加',
	  shadeClose: true,
	  maxmin: true,
	  shade: 0.8,
	  area: [w+'px', h+'px'],
	  content: 'houtai/permissions/add', //iframe的url
	  end: function () {
          location.reload();
      }
	});
}


//打开编辑窗口
function edit(id){
	w = 500;
	h = 400;
	
	layer.open({
	  type: 2,
	  title: '修改',
	  shadeClose: true,
	  maxmin: true,
	  shade: 0.8,
	  area: [w+'px', h+'px'],
	  content: 'houtai/permissions/edit?id='+id, //iframe的url
	  end: function () {
          location.reload();
      }
	});
}


function del(ids){
	var index = layer.load(1, {
		  shade: [0.1,'#fff'] //0.1透明度的白色背景
	});
	$.post('admin/user/permissions/delete',{ids:ids},function(result){
		if(result.success){
			layer.closeAll();
			layer.msg('删除成功');
			reload_data();
		}else{
			layer.closeAll();
			layer.msg(result.msg);
		}
	},'json');
}

function set_Role(id){
	w = 500;
	h = 400;
	layer.open({
		  type: 2,
		  title: '设置角色',
		  shadeClose: true,
		  maxmin: true,
		  shade: 0.8,
		  area: [w+'px', h+'px'],
		  content: 'houtai/user/setRole?id='+id, //iframe的url
		  end: function () {
              location.reload();
          }
	}); 
}

function set_ps(id){
	
	w = 500;
	h = 300;
	layer.open({
		  type: 2,
		  title: '设置新密码',
		  shadeClose: true,
		  maxmin: true,
		  shade: 0.8,
		  area: [w+'px', h+'px'],
		  content: 'houtai/user/setPassword?id='+id,//iframe的url
		  end: function () {
              location.reload();
          }
	}); 
}


</script>

<div class="layui-form" style=" ">

<div class="layui-table-toolbar" style="margin-bottom: 3px;">
	<div class="layui-btn-group">
	<shiro:hasPermission name="user:manage9">
	 	<button onclick="add()" class="layui-btn   layui-btn-sm"><i class="layui-icon">&#xe654;</i>增加</button>
	 	</shiro:hasPermission>
	 	<shiro:hasPermission name="user:manage10">
	  	<button class="layui-btn layui-btn-sm" onclick="delSelected()"><i class="layui-icon">&#xe640;</i>删除选中项</button>
	  	</shiro:hasPermission>
	    <button onclick="reload_data()" class="layui-btn layui-btn-sm"><i class="layui-icon">&#x1002;</i>刷新</button>
 	 </div>
</div>

<table class="layui-hide"   id="table" lay-filter="table"></table> 
<script type="text/html" id="table_bar">
<div class="layui-btn-group">
    <shiro:hasPermission name="user:manage11">
	<a class="layui-btn layui-btn-xs" lay-event="edit">修改</a>
    </shiro:hasPermission>
</div>
</script>


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
			  table.render({
			    elem: '#table'
			    ,url: 'admin/user/permissions',
			    height: 'full-60',
			   cols: [[
			      {checkbox: true, fixed: true}
			      ,{field:'id', title: 'id', width:120, sort:true}
			      ,{field:'text', title: 'text', width:120 }
			      ,{field:'father', title: 'father', width:120, sort:true }
			      ,{field:'url', title: 'url', width:150 }
			      ,{field:'state', title: 'state', width:100  }
			      ,{field:'iconCls', title: 'iconCls', width:100}
			      ,{field:'permissions', title: 'permissions', width:100 }
			      ,{field:'dd_id', title: 'dd_id', width:100 }
			      ,{fixed:'right', width:100,title: '操作', align:'center', toolbar: '#table_bar'}
			    ]]
			    ,id: 'table'
			    ,page: true
			    ,limits:[10,20,50,100],
			   limit:10
			  });
			    
			
			//监听工具条 table_bar
				table.on('tool(table)', function(obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
					var data = obj.data //获得当前行数据
					, layEvent = obj.event; //获得 lay-event 对应的值
					if (layEvent === 'del') {
						//layer.msg('查看操作' + data.id);
						opend_del_dlg(data.id);
					} else if (layEvent === 'set_ps') {
						set_ps(data.id);
					} else if (layEvent === 'edit') {
						
						edit(data.id);
					} else if (layEvent === 'set_Role'){
						set_Role(data.id);
					}
				});
			
			
				//监听单元格编辑
				  table.on('edit(table)', function(obj){
				    var value = obj.value //得到修改后的值
				    ,data = obj.data //得到所在行所有键值
				    ,field = obj.field; //得到字段
				    
				    //layer.msg('[ID: '+ data.id +'] ' + field + ' 字段更改为：'+ value);
				    table_edit_update(data.id,field,value);
				  });
				
				
		});
</script>


</body>
</html>
