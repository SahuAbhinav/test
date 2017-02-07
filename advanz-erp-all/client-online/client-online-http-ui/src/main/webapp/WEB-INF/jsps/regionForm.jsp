
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script type="text/javascript">
	$(document).ready(function() {
		$("button").button();
		//$('input:text, input:password').button()   
		$(".datepicker").datepicker();
		//      $(":submit").button()
	});
</script>

<script type="text/javascript">
	var url;
	function newUser() {
		$('#dlg').dialog('open').dialog('setTitle', 'New User');
		$('#fm').form('clear');
		url = 'save_user.php';
	}
	function editUser() {
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			$('#dlg').dialog('open').dialog('setTitle', 'Edit User');
			$('#fm').form('load', row);
			url = 'update_user.php?id=' + row.id;
		}
	}
	function saveBranch() {
		alert('called');
		$('#fm').form('submit', {
			url : 'save_region',
			onSubmit : function() {
				return $(this).form('validate');

			},
			success : function(result) {
				var result = eval('(' + result + ')');
				if (result.success) {
					$('#dlg').dialog('close'); // close the dialog
					$('#dg').datagrid('reload'); // reload the user data
				} else {
					$.messager.show({
						title : 'Error',
						msg : result.msg
					});
				}
			}
		});
	}
	function removeUser() {
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			$.messager.confirm('Confirm',
					'Are you sure you want to remove this user?', function(r) {
						if (r) {
							$.post('remove_user.php', {
								id : row.id
							}, function(result) {
								if (result.success) {
									$('#dg').datagrid('reload'); // reload the user data
								} else {
									$.messager.show({ // show error message
										title : 'Error',
										msg : result.msg
									});
								}
							}, 'json');
						}
					});
		}
	}
</script>





<form:form id="fm" name="fm" action="save_region" method="post" modelAttribute="regionForm">


	<div class="panel-header">
		<div class="panel-title">Add New Region</div>
		<div class="panel-tool"></div>
	</div>
	<%-- 
      <table id="tt" title="Country List" class="easyui-datagrid" style="width:816px; height:300px" 
			url="get_country_data" iconCls="icon-search"
			toolbar="#toolbar" pagination="true"
			rownumbers="true" fitColumns="true" >
		<thead>
			<tr>
				<th field="countryName" width="">Country</th>
				<th field="countryCode" width="10" sortable="true">Code</th>
                
			</tr>
		</thead>
	</table>--%>

	<div align="left" class="bkgColor">


		<table width="800" height="325" style="margin-left: 10px;" border="0"
			align="left">
			<tbody>
				<tr>
					<td width="106">Region Name<span style="color: #FF0000">*</span>
					</td>
					<td colspan="3"><form:input class="easyui-validatebox"
							type="text" style="width:383px;" path="regionDTO.regionName"
							id="RegionName" maxlength="35" size="72"
							data-options="required:true"></form:input>
					</td>
					<td width="103" align="left">Code<span style="color: #FF0000">*</span>
					</td>
					<td width="143"><form:input class="easyui-validatebox"
							type="text" path="regionDTO.regionCode" id="RegionCode" size="18"
							maxlength="4" data-options="required:true"></form:input>
					</td>
				</tr>


				<tr>
				<td width="103" align="left">State<span style="color: #FF0000">*</span>
					</td>
				<td><form:select  path="regionDTO.stateId" style="width:85%; height:20px;" id="item_name" items="${states}" itemLabel="StateName" itemValue="stateId"></form:select></td>
				</tr><tr>

					<td width="103" align="left">Description<span
						style="color: #FF0000"></span>
					</td>
					<td colspan="3"><form:input type="text" style="width:383px;"
							path="regionDTO.description" id="Description" maxlength="35"
							size="72" data-options="required:true"></form:input>
					</td>


				</tr>
			</tbody>
		</table>


		<div id="dlg-buttons" align="left"
			style="width: 801px; margin-left: 40px; margin-top: 10px; margin-bottom: 10px;">

			<a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
				onClick="javascript:$('#dlg').dialog('close')">Cancel</a> <input
				type="submit" value="Submit" />
		</div>
	</div>


</form:form>

