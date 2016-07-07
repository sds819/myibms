<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<script type="text/javascript">
	var conId = ${conId};
	var editIndex = undefined;
	function endEditing() {
		if (editIndex == undefined) {
			return true
		}
		if ($('#dgSon').datagrid('validateRow', editIndex)) {
			$('#dgSon').datagrid('endEdit', editIndex);
			editIndex = undefined;
			return true;
		} else {
			return false;
		}
	}

	function append() {
		editIndex = 0;
		var rowLength = $('#dgSon').datagrid('getRows').length;
		if (editIndex != undefined) {
			$('#dgSon').datagrid('insertRow', {
				index : 0,
				row : {
					portCode : rowLength + 1,
					conId : conId
				}
			});
			$('#dgSon').datagrid('selectRow', editIndex).datagrid('beginEdit',
					editIndex);

		} else {
			$('#dgSon').datagrid('endEdit', editIndex);
		}

	}

	function modify() {
		var rows = $('#dgSon').datagrid('getSelections');
		if (rows.length == 1) {
			var index = $('#dgSon').datagrid('getRowIndex', rows[0]);
			$('#dgSon').datagrid('beginEdit', index);
			editIndex = index;
		} else {
			$.messager.alert('小警告', '一次只能修改一条记录，请勿误操作!', 'warning');
		}
	}

	function removeit() {
		var rows = $('#dgSon').datagrid('getSelections');
		console.info(rows);
		var selectedIds = [];
		if (rows.length > 0) {
			$.messager.confirm('确认', '你确定要移除这个信息码?', function(r) {
				if (r) {
					for (var i = 0; i < rows.length; i++) {
						selectedIds.push(rows[i].portId);
					}
					console.info(selectedIds);
					$.post('../concentratorParam/destroys.do', {
						selectedIds : selectedIds.join(':')
					}, function(result) {
						if (result == "success") {
							$.messager.show({ // show error message
								title : 'Success',
								msg : '亲爱的，你的数据删除成功'
							});
							$('#dgSon').datagrid('reload'); // reload the user data
						} else {
							$.messager.show({ // show error message
								title : 'Error',
								msg : 'Darling,你的数据删除失败'
							});
						}
					}, 'json');
				}
			});
		} else {
			$.messager.alert('提醒', '亲爱的小伙伴，您还没有选择删除项!', 'warning');
		}

	}
	function accept() {
		//if editIndex !=undefined ,then the operator is create,else is modify
		$('#dgSon').datagrid('endEdit', editIndex);//结束编辑
		var rowData = $('#dgSon').datagrid('getSelected');
		var inserted = $('#dgSon').datagrid('getChanges', 'inserted');
		var updated = $('#dgSon').datagrid('getChanges', 'updated');
		var url = '';
		if (inserted.length > 0) {
			url = '../concentratorParam/save.do';
		}
		if (updated.length > 0) {
			url = '../concentratorParam/update.do';
		}

		$.ajax({
			url : url,
			type : 'post',
			data : {
				conParamId : rowData.conParamId,
				conParam : rowData.conParam,
				conValue : rowData.conValue,
				conId : rowData.conId
			},
			datatype : 'json',
			success : function(r) {
				$('#dgSon').datagrid('load');
				$('#dgSon').datagrid('unselectAll');
			}
		});
	}
	function reject() {
		$('#dgSon').datagrid('rejectChanges');
		editIndex = undefined;
	}
	function getChanges() {
		var rows = $('#dgSon').datagrid('getChanges');
		alert(rows.length + ' rows are changed!');
	}
	function reload() {
		$('#dgSon').datagrid('load');
		$('#dgSon').datagrid('unselectAll');
	}
</script>

<table id="dgSon" title="类型属性" class="easyui-datagrid"
	style="width: 100%; height: 98%"
	data-options="
					iconCls: 'icon-edit',
					singleSelect: false,
					toolbar: '#tb',
					url: '../concentratorParam/getListJson.do?conId=${conId}',
					method: 'get',
					pagination: true,
					rownumbers: true,
					fitColumns: true">
	<thead>
		<tr>
			<th field="conParamId" checkbox="true"></th>
			<th data-options="field:'conParam',width:80,align:'center',editor:'textbox'">端口参数名</th>
			<th field="conValue" width="80" align="center" editor="textbox">端口参数值</th>
			<th field="conId" hidden="true"></th>

		</tr>
	</thead>
</table>
<div id="tb" style="height: auto">
	<a href="javascript:void(0)" class="easyui-linkbutton"
		data-options="iconCls:'icon-add',plain:true" onclick="append()">增加</a>
	<a href="javascript:void(0)" class="easyui-linkbutton"
		data-options="iconCls:'icon-edit',plain:true" onclick="modify()">修改</a>
	<a href="javascript:void(0)" class="easyui-linkbutton"
		data-options="iconCls:'icon-undo',plain:true" onclick="reject()">撤销</a>
	<a href="javascript:void(0)" class="easyui-linkbutton"
		data-options="iconCls:'icon-remove',plain:true" onclick="removeit()">移除</a>
	<a href="javascript:void(0)" class="easyui-linkbutton"
		data-options="iconCls:'icon-save',plain:true" onclick="accept()">保存</a>
	<a href="javascript:void(0)" class="easyui-linkbutton"
		data-options="iconCls:'icon-reload',plain:true" onclick="reload()">刷新</a>
	<!-- a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="getChanges()">查获改变</a-->
</div>

