<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- 导入jquery核心类库 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<!-- 导入easyui类库 -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/ext/portal.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/default.css">	
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/ext/jquery.portal.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/ext/jquery.cookie.js"></script>
<script
	src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"
	type="text/javascript"></script>
<script type="text/javascript">
	function doAdd(){
		//alert("增加...");
		$('#addStaffWindow').window("open");
	}
	
	function doView(){
		alert("查看...");
	}
	
	function doDelete(){
		//获取数据表格中所有选中的行，返回数组对象
		var rows = $("#grid").datagrid("getSelections");
		if(rows.length == 0){
			//没有选中记录，弹出提示
			$.messager.alert("提示信息","请选择需要删除的取派员！","warning");
		}else{
			//选中了取派员,弹出确认框
			$.messager.confirm("删除确认","你确定要删除选中的取派员吗？",function(r){
				if(r){
					
					var array = new Array();
					//确定,发送请求
					//获取所有选中的取派员的id
					for(var i=0;i<rows.length;i++){
						var staff = rows[i];//json对象
						var id = staff.id;
						array.push(id);
					}
					var ids = array.join(",");//1,2,3,4,5
					location.href = "staffAction_deleteBatch.action?ids="+ids;
				}
			});
		}
	}
	
	function doRestore(){
		alert("将取派员还原...");
	}
	//工具栏
	var toolbar = [ {
		id : 'button-view',	
		text : '查询',
		iconCls : 'icon-search',
		handler : doView
	}, {
		id : 'button-add',
		text : '增加',
		iconCls : 'icon-add',
		handler : doAdd
	}, {
		id : 'button-delete',
		text : '删除',
		iconCls : 'icon-cancel',
		handler : doDelete
	},{
		id : 'button-save',
		text : '还原',
		iconCls : 'icon-save',
		handler : doRestore
	}];
	// 定义列
	var columns = [ [ {
		field : 'id',
		checkbox : true,
	},{
		field : 'name',
		title : '姓名',
		width : 120,
		align : 'center'
	}, {
		field : 'telephone',
		title : '手机号',
		width : 120,
		align : 'center'
	}, {
		field : 'haspda',
		title : '是否有PDA',
		width : 120,
		align : 'center',
		formatter : function(data,row, index){
			if(data=="1"){
				return "有";
			}else{
				return "无";
			}
		}
	}, {
		field : 'deltag',
		title : '是否删除',
		width : 120,
		align : 'center',
		formatter : function(data,row, index){
			if(data=="0"){
				return "正常使用"
			}else{
				return "已删除";
			}
		}
	}, {
		field : 'standard',
		title : '取派标准',
		width : 120,
		align : 'center'
	}, {
		field : 'station',
		title : '所谓单位',
		width : 200,
		align : 'center'
	} ] ];
	
	$(function(){
		// 先将body隐藏，再显示，不会出现页面刷新效果
		$("body").css({visibility:"visible"});
		
		//扩展手机号校验规则
		var reg = /^1[3|4|5|7|8][0-9]{9}$/;
		$.extend($.fn.validatebox.defaults.rules, { 
			telephone: { 
				validator: function(value,param){ 
				return reg.test(value);
			}, 
				message: '手机号输入有误！' 
			}
		}); 
		
		// 取派员信息表格
		$('#grid').datagrid( {
			fit : true,
			border : true,
			rownumbers : true,
			striped : false,
			pageList: [20,50,100],
			pagination : true,
			toolbar : toolbar,
			url : "staffAction_pageQuery.action",
			idField : 'id',
			columns : columns,
			//为数据表格绑定双击事件
			onDblClickRow : doDblClickRow
		});
		
		// 添加取派员窗口
		$('#addStaffWindow').window({
	        title: '添加取派员',
	        width: 400,
	        modal: true,//遮罩效果
	        shadow: true,//阴影效果
	        closed: true,//关闭
	        height: 400,
	        resizable:false
	    });
		
		// 修改取派员窗口
		$('#editStaffWindow').window({
	        title: '修改取派员',
	        width: 400,
	        modal: true,//遮罩效果
	        shadow: true,//阴影效果
	        closed: true,//关闭
	        height: 400,
	        resizable:false
	    });
		
	});

	//数据表格绑定的双击行事件对应的函数
	function doDblClickRow(rowIndex, rowData){
		//打开修改取派员窗口
		$('#editStaffWindow').window("open");
		//使用form表单对象的load方法回显数据
		$("#editStaffForm").form("load",rowData);
	}
</script>
</head>
<body class="easyui-layout" style="visibility:hidden;">
	<div region="center" border="false">
    	<table id="grid"></table>
	</div>
	
	<!-- 添加取派员的窗口 -->
	<div class="easyui-window" title="添加取派员" id="addStaffWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
		<div region="north" style="height:31px;overflow:hidden;" split="false" border="false" >
			<div class="datagrid-toolbar">
				<a id="save" icon="icon-save" href="#" class="easyui-linkbutton" plain="true" >保存</a>
				<!-- 给保存按钮绑定事件 -->
				<script type="text/javascript">
					$('#save').click(function(){
						var v = $('#addStaffForm').form("validate");
						if (v) {
							$('#addStaffForm').form("submit");
							$.messager.alert("信息提示","添加成功","info",function(){
								location.href="${pageContext.request.contextPath}/page_base_staff.action";
							});
						}
					});
				</script>
			</div>
		</div>
		
		<div region="center" style="overflow:auto;padding:5px;" border="false">
			<form id="addStaffForm" method="post" action="${pageContext.request.contextPath }/staffAction_save.action">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">收派员信息</td>
					</tr>
					<!-- TODO 这里完善收派员添加 table -->
					<tr>
						<td>姓名</td>
						<td><input type="text" name="name" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>手机</td>
						<td>
							<input type="text" data-options="validType:'telephone'" name="telephone" class="easyui-validatebox" required="true"/>
						</td>
					</tr>
					<tr>
						<td>站点</td>
						<td>
							<select id="station" class="easyui-combobox" name="station" style="width:150px;" data-options="panelHeight:'auto'">
							    <option value="">请选择......</option>
							    <option value="珠江国际">珠江国际</option>
							    <option value="大学城">大学城</option>
							    <option value="天府市集">天府市集</option>
							    <option value="万春">万春</option>
							    <option value="金马">金马</option>
							    <option value="杨柳河">杨柳河</option>
							    <option value="沙子沟">沙子沟</option>
							    <option value="柑子树">柑子树</option>
							</select>
						</td>
					</tr>
					<tr>
						<td colspan="2">
						<input type="checkbox" name="haspda" value="1" />
						是否有PDA</td>
					</tr>
					<tr>
						<td>取派标准</td>
						<td>
							<select id="standard" class="easyui-combobox" name="standard" style="width:150px;" data-options="panelHeight:'auto'">
								<option value="">请选择......</option>
							    <option value="0-5kg">0-5kg</option>
							    <option value="5-10kg">5-10kg</option>
							    <option value="10-20kg">10-20kg</option>
							    <option value="20-50kg">20-50kg</option>
							    <option value="50kg-100kg">50kg-100kg</option>
							    <option value="100kg-200kg">100kg-200kg</option>
							    <option value="200kg-500kg">200kg-500kg</option>
							    <option value="500kg-1000kg">500kg-1000kg</option>
							</select>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	
	
	<!-- 修改取派员的窗口 -->
	<div class="easyui-window" title="修改取派员" id="editStaffWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
		<div region="north" style="height:31px;overflow:hidden;" split="false" border="false" >
			<div class="datagrid-toolbar">
				<a id="edit" icon="icon-save" href="#" class="easyui-linkbutton" plain="true" >保存</a>
				<!-- 给保存按钮绑定事件 -->
				<script type="text/javascript">
					$('#edit').click(function(){
						var v = $('#editStaffForm').form("validate");
						if (v) {
							$('#editStaffForm').form("submit");
							$.messager.alert("信息提示","修改成功","info",function(){
								location.href="${pageContext.request.contextPath}/page_base_staff.action";
							});
						}
					});
				</script>
			</div>
		</div>
		
		<div region="center" style="overflow:auto;padding:5px;" border="false">
			<form id="editStaffForm" method="post" action="${pageContext.request.contextPath }/staffAction_update.action">
				<!-- 隐藏域,用于保存取派员id -->
				<input type="hidden" name="id">
				<!-- 隐藏域,用于保存取派员deltag -->
				<input type="hidden" name="deltag">
				
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">收派员信息</td>
					</tr>
					<!-- TODO 这里完善收派员添加 table -->
					<tr>
						<td>姓名</td>
						<td><input type="text" name="name" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>手机</td>
						<td>
							<input type="text" data-options="validType:'telephone'" name="telephone" class="easyui-validatebox" required="true"/>
						</td>
					</tr>
					<tr>
						<td>站点</td>
						<td>
							<select id="station" class="easyui-combobox" name="station" style="width:150px;"
									data-options="panelHeight:'auto'">
							    <option value="">请选择......</option>
							    <option value="珠江国际">珠江国际</option>
							    <option value="大学城">大学城</option>
							    <option value="天府市集">天府市集</option>
							    <option value="万春">万春</option>
							    <option value="金马">金马</option>
							    <option value="杨柳河">杨柳河</option>
							    <option value="沙子沟">沙子沟</option>
							    <option value="柑子树">柑子树</option>
							</select>
						</td>
					</tr>
					<tr>
						<td colspan="2">
						<input type="checkbox" name="haspda" value="1" />
						是否有PDA</td>
					</tr>
					<tr>
						<td>取派标准</td>
						<td>
							<select id="standard" class="easyui-combobox" name="standard" style="width:150px;"
									data-options="panelHeight:'auto'">
								<option value="">请选择......</option>
							    <option value="0-5kg">0-5kg</option>
							    <option value="5-10kg">5-10kg</option>
							    <option value="10-20kg">10-20kg</option>
							    <option value="20-50kg">20-50kg</option>
							    <option value="50kg-100kg">50kg-100kg</option>
							    <option value="100kg-200kg">100kg-200kg</option>
							    <option value="200kg-500kg">200kg-500kg</option>
							    <option value="500kg-1000kg">500kg-1000kg</option>
							</select>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>	