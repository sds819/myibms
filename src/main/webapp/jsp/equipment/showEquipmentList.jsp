<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../../js/common.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
    <html>
    <head>
        <meta charset="UTF-8">
        <title></title>

    </head>
    <body>
        <table id="dg" title="设备" class="easyui-datagrid" style="width:99%;height:99%"
                url="getListJson.do"
                toolbar="#toolbar" pagination="true"
                rownumbers="true" fitColumns="true" singleSelect="false"
                >
            <thead>
                <tr>
                    <th field="equipmentId" checkbox="true"></th>
                    <th field="equipmentName" width="50">设备名称</th>
                    <th  
                     class="easyui-combobox" 
	                    data-options="
	                    	field:'equipmentTypeId',
	                    	width:50,
							required:true,
							formatter:function(value,row){
								return row.equipmentType.equipmentTypeName
							}"
					>设备类型名称
					</th>
                    <th field="equipmentCode" width="50">设备编号</th>
                    <th field="equipmentAddress" width="50">地址编码</th>
                     <th field="regionId" hidden="true" width="50">区域Id</th>
                    <th data-options="field:'OP',width:60,align:'center', formatter:linkStyler" >操作</th>
                   
                </tr>
            </thead>
        </table>
        <div id="toolbar">
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="create()">增加</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="edit()">修改</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true" onclick="undo()">撤销</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroy()">移除</a>
      		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" onclick="refresh()">刷新</a>
        </div>
        
        <div id="dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
                closed="true" buttons="#dlg-buttons">
            <div class="ftitle">设备信息:</div>
            <form id="fm" method="post" novalidate>
               
                <div class="fitem">
                    <label>设备名称:</label>
                    <input name="equipmentName" id="equipmentName" class="easyui-textbox" required="true">
                </div>
                <div class="fitem">
                    <label>设备类型名称:</label>
                    <input name="equipmentTypeId" id="equipmentTypeId" class="easyui-combobox" required="true"
                    data-options="
						url:'getEquipmentTypeJson.do',
						method:'get',
						valueField:'id',
						textField:'name'
						">
                </div>
                <div class="fitem">
                    <label>设备编码:</label>
                    <input name="equipmentCode" id="equipmentCode" class="easyui-textbox" required="true">
                </div>
                <div class="fitem">
                    <label>地址编码:</label>
                    <input name="equipmentAddress" id="equipmentAddress" class="easyui-textbox" required="true">
                </div>
               
            </form>
        </div>
        <div id="dlg-buttons">
            <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="save()" style="width:90px">保存</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</a>
        </div>
        <script type="text/javascript">
            var url;
            function refresh() {
        		$('#dg').datagrid('load');
        		$('#dg').datagrid('unselectAll');
        	}
            function undo() {
        		$('#dg').datagrid('rejectChanges');
        		editIndex = undefined;
        	}
            function create(){
                $('#dlg').dialog('open').dialog('center').dialog('setTitle','增加');
                $('#fm').form('clear');
                url = 'save.do';
            }
            function edit(){
            	var rows = $('#dg').datagrid('getSelections');
            	if(rows.length>1){
            		 $.messager.alert('提醒','亲爱的小伙伴，您没有按要求操作哦!','warning');
            		 return;
            	}
                var row = $('#dg').datagrid('getSelected');
                console.info(row.equipmentType);
                var equipmentTypeId= row.equipmentType.equipmentTypeId;
                var equipmentTypeName= row.equipmentType.equipmentTypeName;
               
                if (row){
                    $('#dlg').dialog('open').dialog('center').dialog('setTitle','编辑');
                    $('#equipmentTypeId').combobox('setValue',equipmentTypeId);
                    $('#equipmentTypeId').combobox('setText',equipmentTypeName);
                    $('#fm').form('load',row);
                    url = 'update.do?equipmentId='+row.equipmentId;
                }
            }
            function save(){
                $('#fm').form('submit',{
                    url: url,
                    onSubmit: function(){
                        return $(this).form('validate');
                    },
                    success: function(result){
                        var result = eval('('+result+')');
                        if (result.errorMsg){
                            $.messager.show({
                                title: 'Error',
                                msg: '亲爱的，你的数据保存失败!'
                            });
                        } else {
                        	$.messager.show({ 
            					title : 'Success',
            					msg : '亲爱的，你的数据操作成功!'
            				});
                            $('#dlg').dialog('close');        // close the dialog
                            $('#dg').datagrid('reload');    // reload the user data
                        }
                    }
                });
            }
            function destroy(){
              //  var row = $('#dg').datagrid('getSelected');
               var rows = $('#dg').datagrid('getSelections');
               var selectedIds = [];
               if (rows.length>0){
                	
                    $.messager.confirm('确认','你确定要移除这个信息码?',function(r){
                        if (r){
                        	for (var i = 0; i < rows.length; i++) {
                                selectedIds.push(rows[i].equipmentId);
                            }
                            $.post('destroys.do',{selectedIds:selectedIds.join(':')},function(result){
                                if (result=="success"){
                                	$.messager.show({    // show error message
                                        title: 'Success',
                                        msg: '亲爱的，你的数据删除成功'
                                    });
                                	  $('#dg').datagrid('reload');    // reload the user data
                                } else {
                                    $.messager.show({    // show error message
                                        title: 'Error',
                                        msg: 'Darling,你的数据删除失败'
                                    });
                                }
                            },'json');
                        }
                    });
                }else{
                	 $.messager.alert('提醒','亲爱的小伙伴，您还没有选择删除项!','warning');
                }
            }
            
            
            function linkStyler(value,row,index){
//            	alert(row.equipmentTypeId);
            	var equipmentId = row.equipmentId;
            	var equipmentTypeId = row.equipmentType.equipmentTypeId;
    			if (equipmentId!=''){
    				
    				var r ='<a href="#" style=\'text-decoration:none;\' onclick="equipmentTypeOperator(\'' + equipmentId + '\',\''+equipmentTypeId+'\')">[信息点定义]</a>';
    				return r;
    				
    			}
    		}
            
            function equipmentTypeOperator(equipmentId,equipmentTypeId){
//           		var top=$(document).scrollTop() + ($(window).height()-250) * 0.5;
				var top =10;
            	$("#sonDialog").window({
                    title: '类型端口定义',
                    shadow:true,
                    modal:true,
                    top:top, 
                    width: '880px',
                    height: '400px',
                    href: '../equipmentInf/getList.do?equipmentId='+equipmentId+'&equipmentTypeId='+equipmentTypeId
                });
            	
            }
            
           
        </script>
        <style type="text/css">
            #fm{
                margin:0;
                padding:10px 30px;
            }
            .ftitle{
                font-size:14px;
                font-weight:bold;
                padding:5px 0;
                margin-bottom:10px;
                border-bottom:1px solid #ccc;
            }
            .fitem{
                margin-bottom:5px;
            }
            .fitem label{
                display:inline-block;
                width:80px;
            }
            .fitem input{
                width:160px;
            }
        </style>
    </body>
    <div id="sonDialog"></div>
    </html>