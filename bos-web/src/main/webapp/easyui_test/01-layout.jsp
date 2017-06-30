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
<title>layout</title>
</head>
<body class="easyui-layout">
	<!-- 使用div描述每个区域 -->
	<div title="BOS物流管理系统" style="height: 100px" data-options="region:'north'">页头</div>
	<div style="height: 50px" data-options="region:'south'">页脚</div>
	<div title="系统菜单" style="width: 200px" data-options="region:'west'">左侧导航</div>
	<div style="width: 100px" data-options="region:'east'">右侧描述</div>
	<div data-options="region:'center'">中间信息</div>
</body>  
</html>