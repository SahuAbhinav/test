<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script>
	jQuery(document).ready(function() {
	
		// binds form submission and fields to the validation engine
		jQuery("#formID").validationEngine();
	});

	function checkHELLO(field, rules, i, options) {
		if (field.val() != "HELLO") {
			// this allows to use i18 for the error msgs
			return options.allrules.validate2fields.alertText;
		}
	}
</script>

<script>
	$(function() {
		$("#datepicker").datepicker({
			dateFormat : 'dd-M-yy',
			autoSize : true,
			changeMonth : true,
			changeYear : true
			
		});
		
		$("#datepicker").change(function() {
			$.ajax({

				type : "POST",
				url : "getDay",
				data : "date=" + $('#datepicker').val(),

				success : function(response) {
					var day=response.result;
					$("#dayOfDate").val(day);
				}});
			
			
			
			
		});
	});
		</script>
<c:if test="${not empty(errorList)}">
 <input type="hidden" id="errorId" value="${errorList.errorMsg}">
 <script type="text/javascript">
 		$(document).ready(function() {
 		var errorId=document.getElementById('errorId');
		alert(errorId.value);
		});
 	</script>
 </c:if>



<script type="text/Javascript">
 var index;
 function changeScore(index)
 {
	var addId = "addId" + index;
	var editId = "editId" + index;
	var deleteId = "deleteId" + index;
	var name1="menuRole"+index;
	
	if(document.getElementById(name1).value=='false') {
		document.getElementById(addId).disabled = true;
		document.getElementById(editId).disabled = true;
		document.getElementById(deleteId).disabled = true;
		
		document.getElementById(addId).value ='false';
		document.getElementById(editId).value ='false';
		document.getElementById(deleteId).value ='false';
	  } 
	else{
		document.getElementById(addId).disabled = false;
		document.getElementById(editId).disabled = false;
		document.getElementById(deleteId).disabled = false;
		
	} 
  }
</script>

<script type="text/javascript" charset="utf-8">            
                $(document).ready(function() {
                    /* Initialise datatables */
                    var oTable = $('#example').dataTable();
                } );
            </script>

<script type="text/javascript">
	$(document).ready(function() {

		//called when key is pressed in textbox
		$("#quantity").keypress(function(e) {
			//if the letter is not digit then display error and don't type anything
			if (e.which != 8 && e.which != 0 && (e.which<48 || e.which>57)) {
				//display error message
				$("#errmsg").html("Digits Only").show().fadeOut("slow");
				return false;
			}
		});
		
		
	});
</script>



<script type="text/javascript">
			
			$(document).ready(function() {  	
				 
				
				$('.fixmyheader-8').fixheadertable({
					caption		: 'My header is fixed !',
					height		:334,
					addTitles	: true,
					colratio	: ['10%', '10%', '8%', '50px', 'auto', 'auto', '30%', 'auto']
				});
			});
		</script>

<style type="text/css">
th {
	font-size: 10px;
}
td {
	font-size: 11px;
}

 
 
 body {
	font-family:Arial, Helvetica, sans-serif;
	}
code, pre {				
				padding		: 10px;	
				background	: #F5F5F5;
				border		: 1px solid #D4D4D4;
				overflow-x	: auto;
				font-size	: 12px;
			}	
	
th{font-size:10px;}
 td{font-size:12px;}  
 
 	
	div.t_fixed_header_main_wrapper.ui div.t_fixed_header_caption {
		display:none;
		
		}
		.ui-widget-content {
overflow-x: hidden !important;
 
}
	.mn{width:158px !important; border:none !important;}
	.sb{width:158px !important; border:none !important;}
	.menuname{width:158px !important; border:none !important;}
	.visible{width:80px !important; border:none !important;}
	.add{width:80px !important; border:none !important;}
	.edit{width:80px !important; border:none !important;}
	.del{width:70px !important; border:none !important;}
 

.info, .success, .warning, .error, .validation {
    border: 1px solid;
    margin: 10px 0px;
    padding:15px 10px 15px 50px;
    background-repeat: no-repeat;
    background-position: 10px center;
}
.info {
    color: #00529B;
    background-color: #BDE5F8;
    background-image: url('info.png');
}
.success {
    color: #4F8A10;
    background-color: #DFF2BF;
    background-image:url('success.png');
}
.dataTables_length {
width: 966px !important;
}
.warning {
    color: #9F6000;
    background-color: #FEEFB3;
    background-image: url('warning.png');
}
.error {
    color: #D8000C;
    background-color: #FFBABA;
    background-image: url('error.png');
}
</style>


<form:form id="formID" action="save_attandance"   method="post" modelAttribute="attandanceMasterForm"> 
    <div><form:errors path="*" cssClass="error"/></div>

	<div class="panel-header" style="width: 963px" >
		<div class="panel-title">Edite Attandance Form</div>
		<div class="panel-tool"></div>
	</div>
	<div align="left" class="bkgColor"  style="width:973px">
		<table class="" width="405" height="31" border="0" align="center">
			<tr>
				<td width="45" height="24" align="left"><label>Date<span style="color: #FF0000">*</span>
				</label></td>
				<td width="150">
				<form:input onkeypress="return check(event)"   data-maxsize="25"
					class="validate[required] text-input" path="attandanceMasterDTO.date" size="20" id="datepicker" /></td>
					<td width="45" height="24" align="left"><label>Day<span style="color: #FF0000">*</span>
				</label></td>
				<td width="150">
				
				<form:input style="width:113px; height:21px"
							path="attandanceMasterDTO.dayOfDate" id="dayOfDate" readonly="true"/>
													
				</td>

			</tr>
		</table>
 
		<div class="gridheadingdiv" style="width:973px" >
		
		  <table width="972" class="display fixmyheader-8" id="example">
				<thead>
				<tr>
					<td class="mn" ><div align="center">Employee Name</div></td>
					<td  class="sb"><div align="center">Employee Code</div></td>
					 <td  class="menuname"><div align="center">Attandance Flag</div></td>
					<td  class="visible"><div align="center">Day Status</div></td>
					<!-- <td  class="add"><div align="center">Add</div></td>
					<td  class="edit"><div align="center">Edit</div></td>
					<td  class="del"><div align="center">Delete</div></td> -->
					
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${attandanceMasterForm.employeeDTOList}" var="menuRole" varStatus="status">
					<tr>
						<td width="148"><c:out value="${menuRole.employeeName}"/>
						<form:hidden  style="width:100%" path="attandanceMasterList[${status.index}].employeeName" />
						</td>
						<td width="148"><c:out value="${menuRole.employeeCode}"/>
						<form:hidden  style="width:100%" path="attandanceMasterList[${status.index}].employeeCode" />
						<%--  <form:hidden path="moduleMenuMasterDTOList[${status.count - 1}].menuName"/> --%>  </td>
							<td width="70">
						<form:select path="attandanceMasterList[${status.index}].attandanceFlag" style="width:98%"
							id="partyId" >
<form:option value="" ></form:option>
							<form:options items="${leaveList}" itemValue="leaveName"
								itemLabel="leaveName" />
						</form:select>
						</td>
						<td width="70">				
						<form:select  style="width:100%" path="attandanceMasterList[${status.index}].dayStatus" >
							<form:option value="" ></form:option>
							<form:option value="Full Day" >Full Day</form:option>
							<form:option value="Half Day" >Half Day</form:option>
						</form:select>
						</td>
						<td width="148"><%-- <c:out value="${menuRole.subModuleName}"/> --%>
						<%-- <form:hidden path="moduleMenuMasterDTOList[${status.count - 1}].subModuleName"/> --%></td> 
						
						
		
						 <%-- 
						
						<td width="70">		
						 <form:select id="editId${status.count - 1}" style="width:100%" path="userRoleAndRightsDTOList[${status.count - 1}].editFlag" disabled="true">
							<form:option value="false" >False</form:option>
							<form:option value="true" >True</form:option>
						</form:select>
						
						<td width="70">
						<form:select id="deleteId${status.count - 1}" style="width:73%" path="userRoleAndRightsDTOList[${status.count - 1}].deleteFlag" disabled="true">
							<form:option value="false" >False</form:option>
							<form:option value="true" >True</form:option>
						</form:select>
							
						</td>
						<td style="display: none;" width="0"><form:hidden path="moduleMenuMasterDTOList[${status.count - 1}].menuId"/></td>	 --%>
					</tr>
					
					<%-- <form:hidden path="userRoleAndRightsDTOList.menuRole[${status.count - 1}].menuId"/>  --%>
				</c:forEach>
		</tbody>
		</table>
</div>
		<p>&nbsp;</p>
		<div class="btn">
			<div class="savbtn">
				<input class="updatebtn" style="font-size: 11px; font-weight: bold; padding: 0 0 0 30px;" type="submit" value="Update" name="operation" />
			</div>
			<div>
				<a href="role_list" class="cancelbtn"></a>
				
			</div>
		</div>

	</div>
 
	<%-- <form:hidden path="role.roleId" /> --%>
</form:form>