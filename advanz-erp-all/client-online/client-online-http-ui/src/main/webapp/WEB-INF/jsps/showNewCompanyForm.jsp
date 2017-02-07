<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<form:form method="post" action="save_company" modelAttribute="companyForm">
	<table class="dv-table" style="width:100%;background:#fafafa;padding:5px;margin-top:5px;">
		<tr>
			<td>Company</td>
			<td><input name="companyDto.company" class="easyui-validatebox" required="true"></input></td>
			<td>Company Code</td>
			<td><input name="companyDto.companyCode" class="easyui-validatebox" required="true"></input></td>
		</tr>
		<tr>
			<td>Address 1</td>
			<td><input name="companyDto.addressOne"  class="easyui-validatebox" required="true"></input></td>
			<td>Address 2</td>
			<td><input name="companyDto.addressTwo" ></input></td>
		</tr>
		<tr>
			<td>City</td>
			<td><input name="companyDto.city"></input></td>
			<td>State</td>
			<td><input name="companyDto.state"></input></td>
		</tr>
	</table>
	<div style="padding:5px 0;text-align:right;padding-right:30px">
		<a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="saveItem('1')">Save</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="cancelItem('2'>)">Cancel</a>
	</div>
</form:form>