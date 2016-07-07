<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../../js/common.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
    <html>
    <head>
        <meta charset="UTF-8">
        <title>showUsers</title>

    </head>
    <body>
        <table id="dg" title="用户" class="easyui-datagrid" style="width:100%;height:98%"
                url="getUsersJson.do"
                toolbar="#toolbar" pagination="true"
                rownumbers="true" fitColumns="true" singleSelect="false"
                
                >
            <thead>
                <tr>
                    <th field="id" checkbox="true"></th>
                    <th field="username" width="50">用户名</th>
                    <th field="password" width="50">密码</th>
                   
                </tr>
            </thead>
        </table>
        <div id="toolbar">
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">增加</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">编辑</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">移除</a>
        </div>
        
        <div id="dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
                closed="true" buttons="#dlg-buttons">
            <div class="ftitle">用户信息:</div>
            <form id="fm" method="post" novalidate>
               
                <div class="fitem">
                    <label>用户名:</label>
                    <input name="username" class="easyui-textbox" required="true">
                </div>
                <div class="fitem">
                    <label>密码:</label>
                    <input name="password" class="easyui-textbox" required="true">
                </div>
               
            </form>
        </div>
        <div id="dlg-buttons">
            <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveUser()" style="width:90px">保存</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</a>
        </div>
        <script type="text/javascript">
            var url;
            function newUser(){
                $('#dlg').dialog('open').dialog('center').dialog('setTitle','增加');
                $('#fm').form('clear');
                url = 'saveUser.do';
            }
            function editUser(){
                var row = $('#dg').datagrid('getSelected');
                if (row){
                    $('#dlg').dialog('open').dialog('center').dialog('setTitle','编辑');
                    $('#fm').form('load',row);
                    url = 'updateUser.do?id='+row.id;
                }
            }
            function saveUser(){
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
                            $('#dlg').dialog('close');        // close the dialog
                            $('#dg').datagrid('reload');    // reload the user data
                        }
                    }
                });
            }
            function destroyUser(){
              //  var row = $('#dg').datagrid('getSelected');
               var rows = $('#dg').datagrid('getSelections');
               var selectedIds = [];
               if (rows.length>0){
                	
                    $.messager.confirm('确认','你确定要移除这个信息码?',function(r){
                        if (r){
                        	for (var i = 0; i < rows.length; i++) {
                                selectedIds.push(rows[i].id);
                            }
                        	console.info(selectedIds);
                            $.post('destroyUsers.do',{selectedIds:selectedIds.join(':')},function(result){
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
    </html>