<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<script type="text/javascript">
	var equipmentId = ${equipmentId};
	var equipmentTypeId = ${equipmentTypeId};
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
					equipmentId : equipmentId
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
						selectedIds.push(rows[i].equipmentInfId);
					}
					console.info(selectedIds);
					$.post('../equipmentInf/destroys.do', {
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
			url = '../equipmentInf/save.do';
		}
		if (updated.length > 0) {
			url = '../equipmentInf/update.do';
		}

		$.ajax({
			url : url,
			type : 'post',
			data : {
		
				equipmentId : equipmentId,
				equipmentInfId : rowData.equipmentInfId,
				equipmentInfName : rowData.equipmentInfName,
				equipmentPortId : rowData.equipmentPortId,
				equipmentInfType : rowData.equipmentInfType,
				valueType : rowData.valueType,
				unit : rowData.unit,
				prec : rowData.prec,
				upLimit : rowData.upLimit,
				lowLimit : rowData.lowLimit,
				updateTime : rowData.updateTime,
				conId : rowData.conId,
				portId : rowData.portId,
				infCode : rowData.infCode
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
	 function linkStyler(value,row,index){
		if(row.equipmentInfType==null||row.equipmentInfType==undefined){
			return ;
		}
     	var equipmentInfTypeId = row.equipmentInfType.code;
     	var equipmentInfId = row.equipmentInfId;
		if (equipmentInfId!=''&&(equipmentInfTypeId=='DI'||equipmentInfTypeId=='AI')){
				var r ='<a href="#" style=\'text-decoration:none;\' onclick="inOperator(\'' + equipmentInfId + '\')">[输入定义]</a>';
				return r;
			}else if(equipmentInfId!=''&&(equipmentInfTypeId=='DO'||equipmentInfTypeId=='AO')){
				var r ='<a href="#" style=\'text-decoration:none;\' onclick="outOperator(\'' + equipmentInfId + '\')">[输出定义]</a>';
				return r;
			}
		}

	function outOperator(equipmentInfId) {
		alert("Developing");
		return ;
	        $("#out").dialog({
	            title: '配置输出',
	            width: 600,
	            height: 350
//	            href: '../control/configEasyOutexp?equipmentInfId=' + infId 
	        });
	    }
	function inOperator(infId) {
		alert("Developing");
		return;
	        $("#in").dialog({
	            title: '配置输入',
	            width: 600,
	            height: 350
//	            href: '../control/configEasyInexp?equipmentInfId=' + infId 
	        });
	    }
</script>

<table id="dgSon" title="信息点属性" class="easyui-datagrid"
	style="width: 100%; height: 98%"
	data-options="
					iconCls: 'icon-edit',
					singleSelect: false,
					toolbar: '#tb',
					url: '../equipmentInf/getListJson.do?equipmentId=${equipmentId}',
					method: 'get',
					pagination: true,
					rownumbers: true,
					fitColumns: true">
	<thead>
		<tr>
			<th field="equipmentInfId" checkbox="true"></th>
			<th data-options="field:'equipmentInfName',width:80,align:'center',editor:'textbox'">信息点名称</th>
			<th class="easyui-combobox" data-options="field:'equipmentPortId',width:80,required:true,
							formatter:function(value,row){if(row.equipmentTypeAttr!=null&&row.equipmentTypeAttr!=undefined) return row.equipmentTypeAttr.portName},
							editor:{
								type:'combobox',
								options:{
									url:'../equipmentInf/getEquipmentTypeAttrJson.do?equipmentTypeId='+${equipmentTypeId},
									valueField:'id',
									textField:'name',
									required:true,
									onSelect:function(r){
										var ed = $('#dgSon').datagrid('getEditor', {index:editIndex,field:'equipmentInfName'});
                                		$(ed.target).textbox('setValue',r.name);
									}
								}
							}">所属设备端口类型
			</th>
			<th class="easyui-combobox" data-options="field:'equipmentInfType',width:80,required:true,
							formatter:function(value,row){if(row.equipmentInfType!=null&&row.equipmentInfType!=undefined) return row.equipmentInfType.code},
							editor:{
								type:'combobox',
								options:{
									url:'../equipmentInf/getEquipmentInfTypeJson.do',
									valueField:'id',
									textField:'name',
									required:true,
									onSelect:function(r){
										var ed = $('#dgSon').datagrid('getEditor', {index:editIndex,field:'equipmentInfType'});
										$(ed.target).combobox('setValue',r.id).combobox('setText',r.name);
//										$('#cc').combobox('setValue','bitem3').combobox('setText','bitem3')
									}
								}
							}">信息点I/O类型
			</th>
			<th class="easyui-combobox" data-options="field:'valueType',width:80,required:true,
							formatter:function(value,row){if(row.valueType!=null&&row.valueType!=undefined) return row.valueType.name},
							editor:{
								type:'combobox',
								options:{
									url:'../equipmentInf/getValueTypeJson.do',
									valueField:'id',
									textField:'name',
									required:true,
									onSelect:function(r){
										var ed = $('#dgSon').datagrid('getEditor', {index:editIndex,field:'valueType'});
										$(ed.target).combobox('setValue',r.id).combobox('setText',r.name);
									}
								}
							}">数值类型
			</th>
			<th field="unit" width="80" align="center" editor="textbox">单位</th>
			<th field="prec" width="80" align="center" editor="textbox">精度</th>
			<th field="upLimit" width="80" align="center" editor="textbox">上限</th>
			<th field="lowLimit" width="80" align="center" editor="textbox">下限</th>
			<th field="updateTime" width="80" align="center" editor="textbox">更新时间</th>
			<th class="easyui-combobox" data-options="field:'conId',width:50,required:true,
							formatter:function(value,row){if(row.concentrator!=null&&row.concentrator!=undefined) return row.concentrator.conName},
							editor:{
								type:'combobox',
								options:{
									url:'../equipmentInf/getConcentratorJson.do',
									valueField:'id',
									textField:'name',
									required:true,
									onSelect:function(r){
                               		 	var url = '../equipmentInf/getConcentratorTypeAttrJson.do?conId='+ r.id;
                                		var ed = $('#dgSon').datagrid('getEditor', {index:editIndex,field:'portId'});
                                		$(ed.target).combobox('setValue',r.id).combobox('setText',r.name);
                                		$(ed.target).combobox('reload', url);
									}
								}
							}">关联集中器
			</th>
			<th class="easyui-combobox" data-options="field:'portId',width:50,required:true,
							formatter:function(value,row){if(row.concentratorTypeAttr!=null&&row.concentratorTypeAttr!=undefined) return row.concentratorTypeAttr.portName},
							editor:{
								type:'combobox',
								options:{
									valueField:'id',
									textField:'name',
									required:true,
									onSelect:function(r){
										var ed = $('#dgSon').datagrid('getEditor', {index:editIndex,field:'portId'});
										$(ed.target).combobox('setValue',r.id).combobox('setText',r.name);
//										$('concentratorTypeAttrId').combobox('setValue',r.id);
//                   					$('concentratorTypeAttrId').combobox('setText',r.name);
									}
								}
							}">关联集中器端口
			</th>
			<th data-options="field:'infCode',width:80,align:'center',editor:'textbox'">信息点编码</th>
			<th data-options="field:'OP',width:80,align:'center', formatter:linkStyler" >配置输入输出</th>
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
<div id="in"></div>
<div id="out"></div>

