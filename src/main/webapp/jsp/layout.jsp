<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript"	src="./js/jquery-easyui-1.4.5/jquery.min.js"></script>
	<script type="text/javascript"	src="./js/jquery-easyui-1.4.5/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="./js/jquery-easyui-1.4.5/locale/easyui-lang-zh_CN.js"></script>
	<link rel="stylesheet" type="text/css"	href="./js/jquery-easyui-1.4.5/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css"	href="./js/jquery-easyui-1.4.5/themes/icon.css">
	<link rel="stylesheet" type="text/css"	href="./js/jquery-easyui-1.4.5/demo/demo.css">
	<link rel="stylesheet" type="text/css" href="./js/jquery-easyui-1.4.5/themes/color.css">
</head>
<script type="text/javascript">
	function operator(){
		alert('设备类型');
	}
	
	function addPanel(url,title){
		$('#tt').tabs('add',{
			title: title,
			content: '<iframe width="100%" height="100%" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>',
			closable: true
		});
	}
</script>
<body class="easyui-layout">
	<div data-options="region:'north',border:false" style="height:60px;background:#B3DFDA;padding:10px">工作平台</div>
	<div data-options="region:'west',split:true,title:'工作目录'" style="width:150px;padding:0px;">
	       <div class="easyui-accordion" data-options="fit:true,border:false">
                <div title="平台维护" style="padding:10px;" data-options="selected:true" >
                    
                    	<div><a  class="easyui-linkbutton" iconCls="icon-add" plain="true"  onclick="addPanel('concentratorType/getList.do','集中器类型')">集中器类型</a></div>
                    	<div><a  class="easyui-linkbutton" iconCls="icon-add" plain="true"  onclick="addPanel('concentrator/getList.do','集中器管理')">集中器管理</a></div>
						<div><a  class="easyui-linkbutton" iconCls="icon-add" plain="true"  onclick="addPanel('equipmentType/getList.do','设备类型')">设备类型</a></div>
						<div><a  class="easyui-linkbutton" iconCls="icon-add" plain="true"  onclick="addPanel('equipment/getList.do','设备管理')">设备管理</a></div>
						<div><a target="centerArea"  class="easyui-linkbutton" iconCls="icon-add" plain="true" href="javascript://"  onclick=" $.messager.alert('提醒','稍安勿躁，尽职的程序员哥哥正在加班加点开发中。。。','warning');">开发中</a></div>
					
                </div>
                <div title="Title2"  style="padding:10px;">
                    content2
                </div>
                <div title="Title3" style="padding:10px">
                    content3
                </div>
            </div>
	</div>
	<div data-options="region:'east',split:true,collapsed:true,title:'提示区域'" style="width:100px;padding:10px;">暂无数据</div>
	<div data-options="region:'south',border:false" style="height:50px;background:#A9FACD;padding:10px;">页脚区域</div>
	<div id="tt" class="easyui-tabs" data-options="tools:'#tab-tools',region:'center',title:'工作区域'" style="height:100%;background:#A9FACD;width:100%;">
	  <!--  iframe  id="centerArea" name="centerArea" src="" width=100% height=100%  frameborder="0" scrolling="no" marginheight="0" marginwidth="0">
	  
	  </iframe-->
	</div>
</body>
</html>