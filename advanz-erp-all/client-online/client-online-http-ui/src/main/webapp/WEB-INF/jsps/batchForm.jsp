<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>	
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="keywords" content="jquery,ui,easy,easyui,web">
<meta name="description"
	content="easyui help you build your web page easily!">
<title>Advanz ERP</title>
<link rel="stylesheet" type="text/css"
	href="http://www.jeasyui.com/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="http://www.jeasyui.com/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="http://www.jeasyui.com/easyui/demo/demo.css">

<style type="text/css">
form {
	margin: 0;
	padding: 0;
}

.dv-table td {
	border: 0;
}

.dv-table input {
	border: 1px solid #ccc;
}
</style>

<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.6.min.js"></script>
<script type="text/javascript"
	src="http://www.jeasyui.com/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="http://www.jeasyui.com/easyui/datagrid-detailview.js"></script>
<script type="text/javascript">
	$(function() {
		$('#dg').datagrid(
				{
					view : detailview,
					detailFormatter : function(index, row) {
						return '<div class="ddv"></div>';
					},
					onExpandRow : function(index, row) {
						var ddv = $(this).datagrid('getRowDetail', index).find(
								'div.ddv');
						ddv.panel({
							border : false,
							cache : true,
							href : 'show_new_batch_form?index=' + index,
							onLoad : function() {
								$('#dg').datagrid('fixDetailRowHeight', index);
								$('#dg').datagrid('selectRow', index);
								$('#dg').datagrid('getRowDetail', index).find(
										'form').form('load', row);
							}
						});
						$('#dg').datagrid('fixDetailRowHeight', index);
					}
				});
	});
	function saveItem(index) {
		alert('called');
		var row = $('#dg').datagrid('getRows')[index];
		var url = row.isNewRecord ? 'save_batch' : 'update_batch?id='
				+ row.id;
		$('#dg').datagrid('getRowDetail', index).find('form').form('submit', {
			url : url,
			onSubmit : function() {
				return $(this).form('validate');
			},
			success : function(data) {
				data = eval('(' + data + ')');
				data.isNewRecord = false;
				$('#dg').datagrid('collapseRow', index);
				$('#dg').datagrid('updateRow', {
					index : index,
					row : data
				});
			}
		});
	}
	function cancelItem(index) {
		var row = $('#dg').datagrid('getRows')[index];
		if (row.isNewRecord) {
			$('#dg').datagrid('deleteRow', index);
		} else {
			$('#dg').datagrid('collapseRow', index);
		}
	}
	function destroyItem() {
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			$.messager.confirm('Confirm',
					'Are you sure you want to remove this batch?', function(r) {
						if (r) {
							var index = $('#dg').datagrid('getRowIndex', row);
							$.post('delete_batch', {
								id : row.id
							}, function() {
								$('#dg').datagrid('deleteRow', index);
							});
						}
					});
		}
	}
	function newItem() {
		$('#dg').datagrid('appendRow', {
			isNewRecord : true
		});
		var index = $('#dg').datagrid('getRows').length - 1;
		$('#dg').datagrid('expandRow', index);
		$('#dg').datagrid('selectRow', index);
	}
</script>
</head>
<body>

	<h2>Edit form in expanded row details</h2>
	<div class="demo-info" style="margin-bottom: 10px">
		<div class="demo-tip icon-tip">&nbsp;</div>
		<div>Click the expand button to expand a detail form.</div>
	</div>


	<table id="dg" title="My Users" style="width: 700px; height: 550px;overflow-x: scroll;"
		url="get_batch_data" toolbar="#toolbar" pagination="true"
		fitColumns="true" singleSelect="true">
		<thead>
			<tr>
				<th field="itemId" width="50">Item Id</th>
				<th field="batchNo" width="50">Batch No</th>
			<!-- 	<th field="addressOne" width="50">addressOne</th>
				<th field="addressTwo" width="50">addressTwo</th>
				<th field="city" width="50">city</th>
				<th field="state" width="50">state</th>
				<th field="country" width="50">country</th>
				<th field="currencySymbol" width="50">currencySymbol</th>
				<th field="email" width="50">email</th>
				<th field="rangeAddress" width="50">rangeAddress</th>
				<th field="website" width="50">website</th>
				<th field="division" width="50">division</th>
				<th field="finYearFrm" width="50">finYearFrm</th>
				<th field="finYearTo" width="50">finYearTo</th>
				<th field="dataLock" width="50">dataLock</th>
				<th field="phoneNumber" width="50">phoneNumber</th>
				<th field="fax" width="50">fax</th>
				<th field="lstNo" width="50">lstNo</th>
				<th field="iecode" width="50">iecode</th>
				<th field="tin" width="50">tin</th>
				<th field="pan" width="50">pan</th>
				<th field="drugLicenceNo1" width="50">drugLicenceNo1</th>
				<th field="drugLicenceNo2" width="50">drugLicenceNo2</th>
				<th field="servicetaxNo" width="50">servicetaxNo</th>
				<th field="cstNo" width="50">cstNo</th>
				<th field="regECCNo" width="50">regECCNo</th>
				<th field="tarrifHeadNo" width="50">tarrifHeadNo</th>
				<th field="commisionFRate" width="50">commisionFRate</th>
				<th field="lstDate" width="50">lstDate</th>
				<th field="cstDate" width="50">cstDate</th>
				<th field="tinDate" width="50">tinDate</th>
				<th field="panDate" width="50">panDate</th>
				<th field="serviceDate" width="50">serviceDate</th> -->
			</tr>
		</thead>
	</table>

	<div id="toolbar">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true"
			onclick="newItem()">New</a> <a href="#" class="easyui-linkbutton"
			iconCls="icon-remove" plain="true" onclick="destroyItem()">Destroy</a>
	</div>

</body>
</html>