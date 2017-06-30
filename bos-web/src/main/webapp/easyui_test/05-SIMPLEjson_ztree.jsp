<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/js/ztree/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/ztree/jquery.ztree.all-3.5.js"></script>
<title>简单json构造ztree</title>
</head>
<body class="easyui-layout">
	<!-- 使用div描述每个区域 -->
	<div title="BOS物流管理系统" style="height: 100px" data-options="region:'north'">页头</div>
	<div style="height: 50px" data-options="region:'south'">页脚</div>
	<div title="系统菜单" style="width: 200px" data-options="region:'west'">
		<!-- 制作accordion折叠面板 -->
		<div class="easyui-accordion" data-options="fit:true">
			<!-- 使用子div表示每个面板 -->
			<div title="面板1">
				<a id="btn1" class="easyui-linkbutton">添加一个选项卡</a>
				<script type="text/javascript">
					$(function(){
						$("#btn1").click(function(){
							//判断是否已经有选项卡
							var e = $("#mytabs").tabs("exists","系统管理");
							if (e) {
								// 如果已经打开了该选项卡,选中即可
								$("#mytabs").tabs("select","系统管理");
							}else{
								// 如没有,就添加
								$("#mytabs").tabs("add",{
									title:'系统管理',
									iconCls:'icon-edit',
									closable:true,
									content:'<iframe frameborder="0" height="100%" width="100%" src="${pageContext.request.contextPath}/easyui_test/test.jsp"></iframe>'
								});
							}
						});
					})
				</script>
			</div>
			<div title="面板2">
				<ul id="ztree1" class="ztree"></ul>
				<script type="text/javascript">
					var setting1 = {
						data:{
							simpleData:{
								enable:true
							}
						}
					};
					var zNodes1 = [
		               {"id":"1","pId":"2","name":"节点一"},		//每个json对象表示一个节点数据
			   		   {"id":"2","pId":"3","name":"节点二"},
			   		   {"id":"3","pId":"0","name":"节点三"}
	                ];
					
					//调用API初始化ztree
					$.fn.zTree.init($("#ztree1"), setting1, zNodes1);
				</script>
			</div> 
			<div title="面板3">CCC</div>
			<div title="面板4">DDD</div>
		</div>
	</div>
	<div style="width: 100px" data-options="region:'east'">右侧描述</div>
	<div data-options="region:'center'">
		<!-- 制作中心选项卡面板 -->
		<div id="mytabs" class="easyui-tabs" data-options="fit:true">
			<!-- 使用子div表示每个面板 -->
			<div data-options="iconCls:'icon-save'" title="面板1">AAA</div>
			<div data-options="closable:true" title="面板2">BBB</div> 
			<div title="面板3">CCC</div>
			<div title="面板4">DDD</div>
		</div>
	</div>
</body>    
</html>