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
function submitForm(){
	document.forms["formID"].submit();
}


	$(function() {
		$("#datepicker").datepicker({
			dateFormat : 'dd-M-yy',
			autoSize : true,
			changeMonth : true,
			changeYear : true
			
		});
		
		
		
		$("#datepicker").change(function() {
			if(confirm('Are you sure you want to leave this page without save/edite?')) 
	 		   {
				document.forms["formID"].submit();
	 		 	}
			
			
			/* $.ajax({

				type : "POST",
				url : "getDay",
				data : "date=" + $('#datepicker').val(),

				success : function(response) {
					var day=response.result;
					$("#dayOfDate").val(day);
				}}); */
			
			
			
			
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

<%-- <input type="hidden" value="${attandenceFlag}" id="attandenceFlag">
<c:if test="${attandenceFlag!=null}">
<script type="text/javascript">
 		$(document).ready(function() {
		 alert($("#attandenceFlag").val());
		});
 	</script>
</c:if> --%>


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
 function changeCombo(index){
	 var index=index;
	 $('#attandaceId'+index).show();
	 $("#weakOff"+index).attr("disabled","disabled"); 
	 
	 $("#attandanceCombo"+index).removeAttr("disabled");
	 $('#weakOffId'+ele).hide();
	 
	
 }

 function changeAttendanceCombo(ele,index){
	 var index=index; 
	 var val=ele;
	 //alert(index+'value is :'+$("#attandanceCombo"+index).val());
	//alert('employee ID :'+$("#employeeId"+index).val());
	 
	 $.get('chech_leave_avalability', {
		 employeeId: $("#employeeId"+index).val(),
		 leaveType:ele
		 },
		 function(data) {	
			
			if(data=="NA"){
				alert(ele+' is not available for this employee');	
	              $('#attandanceCombo'+index).val('');
	              $('#attendanceFlagId'+index).val('');
			}if(data=="FDNA"){
				alert("Full Day "+ele+' is not available for this employee');	
	              $('#dayStatusId'+index).val("Half Day");
			}
			
	 });
	 
	 
	 
	 
	 
	 
	 
	 if($("#attandanceCombo"+index).val()!=''){
		 $('#activeStatus'+index).attr('checked', 'checked'); 
	 } if($("#attandanceCombo"+index).val()==''){
		 $('#activeStatus'+index).attr('checked', false); 
	 }
	 
 }
 
 $(document).ready(function() {
	 for ( var ele = 0; true; ele++) {
			var frm = document.forms[0];
			//alert("2" + "billDetailList["+ ele+ "].quantity");
			var quantity = frm.elements["attandanceMasterList[" + ele+ "].employeeName"]; 
			//alert("3" + quantity);
			if (!quantity) {
				//alert("4" );
				break;
			}
			
			$('#attandaceId'+ele).hide();
	 }
	 
	 });
	 
</script>
<script type="text/javascript" charset="utf-8"> 
function checkDataValidation(){
	
	for ( var ele = 0; true; ele++) {
		var frm = document.forms[0];
		var empName = frm.elements["attandanceMasterList[" + ele
				+ "].employeeName"];
		if (!empName) {
			break;
		}
		var empName = frm.elements["attandanceMasterList[" + ele
		           				+ "].attandanceFlag"];
		if (empName.value == '') {
			 alert('Sorry you can not save reocrd without attendance');
			 return false;
		}}
	return true;
}
</script>
<script type="text/javascript" charset="utf-8">            
$(document).ready(function() {
    /* Initialise datatables */
    var oTable = $('#example').dataTable({
    	 
		   "aLengthMenu": [['',10, 25, 50, -1], ['',10, 25, 50, "All"]],
           "iDisplayLength":10000,
           "bPaginate": false,
           bInfo:""
	       });
    
    
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
					addTitles	: false,
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
	font-family: Arial, Helvetica, sans-serif;
}

code,pre {
	padding: 10px;
	background: #F5F5F5;
	border: 1px solid #D4D4D4;
	overflow-x: auto;
	font-size: 12px;
}

th {
	font-size: 10px;
}

td {
	font-size: 12px;
}

div.t_fixed_header_main_wrapper.ui div.t_fixed_header_caption {
	display: none;
}

.ui-widget-content {
	overflow-x: hidden !important;
}

.mn {
	width: 158px !important;
	border: none !important;
}

.sb {
	width: 158px !important;
	border: none !important;
}

.menuname {
	width: 158px !important;
	border: none !important;
}

.visible {
	width: 80px !important;
	border: none !important;
}

.add {
	width: 80px !important;
	border: none !important;
}

.edit {
	width: 80px !important;
	border: none !important;
}

.del {
	width: 70px !important;
	border: none !important;
}

.info,.success,.warning,.error,.validation {
	border: 1px solid;
	margin: 10px 0px;
	padding: 15px 10px 15px 50px;
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
	background-image: url('success.png');
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
<!-- <script type="text/javascript">
function filterS(term, _id, cellNr) {
//alert(_id);
var suche = term.value.toLowerCase();
var table = document.getElementById(_id);
//alert(table);
var ele;
for ( var r = 1; r < table.rows.length; r++) {
ele = table.rows[r].cells[cellNr].innerHTML.replace(/<[^>]+>/g, "");
//alert(ele);
if (ele.toLowerCase().indexOf(suche) >= 0) {
//alert();
table.rows[r].style.display = '';
} else {
table.rows[r].style.display = 'none';
}
}
}

//function filter() {
//alert('called');
//}
</script> -->



<form:form id="formID" action="save_attandance" method="post"
	modelAttribute="attandanceMasterForm">
	<div>
		<form:errors path="*" cssClass="error" />
	</div>

	<div class="panel-header" style="width: 963px">
	<div class="panel-title">Attendance Entry Form</div>
		<div class="panel-tool"></div>
	</div>
	<div align="left" class="bkgColor" style="width: 973px; height:340px; padding : 0px;">
		<table class="" width="405" height="31" border="0" align="center">
			<tr>
				<td width="45" height="24" align="left"><label>Date<span
						style="color: #FF0000">*</span> </label>
				</td>
				<td width="150"><form:input onkeypress="return check(event)"
						data-maxsize="25" class="validate[required] text-input"
						path="attandanceMasterDTO.date" size="20" id="datepicker" />
				</td>
				<td width="45" height="24" align="left"><label>Day<span
						style="color: #FF0000">*</span> </label>
				</td>
				<td width="150"><form:input style="width:113px; height:21px"
						path="attandanceMasterDTO.dayOfDate" id="dayOfDate"
						readonly="true" /></td>
						<td width="50" height="24" align="left">
						Order By
						</td>
				<td><div
									style="border: solid 1px; height: 20px; width: 61%; border-radius: 3px 3px 3px 3px; border-color: #7f9db9; background-color: #FFF;">
									<span style="    float: left;    margin-top:2px;    padding-left: 12px;">Attendance </span>
									<form:radiobutton style="width:20px; float: left; "
										path="attandanceMasterDTO.orderBy"  value="0" onclick="submitForm();"/>
								
								<span style="    float: left;    margin-top: 2px;  ">Employee Name</span>
									<form:radiobutton 
										style="width:20px; " path="attandanceMasterDTO.orderBy"
										 value="1" onclick="submitForm();" />
									
								
								</div></td>
			</tr>
		</table>
<%-- <form>
<input name="filter" onkeyup="filterS(this, 'example', 2)" type="text">
</form> --%>
		<div class="gridheadingdiv" style="width: 973px">
			<table width="972" class="example fixmyheader-8" id="example">
				<thead>
					<tr>
						<td class="mn"><div align="center">Employee Name</div>
						</td>
						<td class="sb"><div align="center">Employee Code</div>
						</td>
						<td class="menuname"><div align="center">Attendance
								Flag</div>
						</td>
						<td class="visible"><div align="center">Day Status</div>
						</td>
						<!-- <td  class="add"><div align="center">Add</div></td>
					<td  class="edit"><div align="center">Edit</div></td>
					<td  class="del"><div align="center">Delete</div></td> -->

					</tr>
				</thead>
				<tbody>
					<c:forEach items="${attandanceMasterForm.attandanceMasterList}"
						var="menuRole" varStatus="status">
						<tr>
							<td width="100">
								<%-- <c:out value="${menuRole.employeeName}"/> --%> <form:input
									style="width:100%"
									path="attandanceMasterList[${status.index}].employeeName" readonly="true"/></td>
							<td width="85" nowrap="nowrap">
								<%-- c:out value="${menuRole.employeeCode}"/> --%> <form:input
									style="width:100%"
									path="attandanceMasterList[${status.index}].employeeCode" readonly="true"/> <%--  <form:hidden path="moduleMenuMasterDTOList[${status.count - 1}].menuName"/> --%>
							<form:hidden
									style="width:100%"
									path="attandanceMasterList[${status.index}].employeeId" id="employeeId${status.index}" readonly="true"/>
							
							</td>
							<td width="70">
							<c:if test="${menuRole.weakOff==null}">
									<form:select
										path="attandanceMasterList[${status.index}].attandanceFlag"
										style="width:70%" id="attendanceFlagId${status.index}"  onchange="changeAttendanceCombo(this.value,${status.index})">
										<form:option value=""></form:option>
										<form:option value="LWP">ABSENT</form:option> 
										<form:option value="PRESENT">PRESENT</form:option>
										<form:option value="EXTRA">EXTRA</form:option>
										<form:options items="${leaveList}" itemValue="leaveName"
											itemLabel="leaveName" />
									</form:select>
								</c:if> <c:if test="${menuRole.weakOff!=null}">
									<div id="weakOffId${status.index}">
										<form:input
											path="attandanceMasterList[${status.index}].attandanceFlag"
											value="${menuRole.weakOff}" style="width:70%"
											id="weakOff${status.index}" />
									</div>
									<div id="attandaceId${status.index}">
										<form:select
											path="attandanceMasterList[${status.index}].attandanceFlag" 
											style="width:70%" id="attandanceCombo${status.index}"
											disabled="true" onchange="changeAttendanceCombo(this.value,${status.index})" >
											<form:option value=""></form:option>
											<form:option value="LWP">ABSENT</form:option>
											<form:option value="PRESENT">PRESENT</form:option>
											<form:option value="EXTRA">EXTRA</form:option>
											<form:options items="${leaveList}" itemValue="leaveName"
												itemLabel="leaveName" />
										</form:select>
									</div>
									<img onclick="changeCombo(${status.index})"
										src="static/images/view_icon.png" title="Change" alt=""
										style="float: right; margin-right: 10px" />

								</c:if></td>
							 <td width="70"><form:select style="width:62%" id="dayStatusId${status.index}"
									path="attandanceMasterList[${status.index}].dayStatus">
									<form:option value="Full Day">Full Day</form:option>
									<form:option value="Half Day">Half Day</form:option>
								</form:select></td> 
								
								 <%-- <td width="70"><div
									style="border: solid 1px; height: 20px; width: 84%; border-radius: 3px 3px 3px 3px; border-color: #7f9db9; background-color: #FFF;">
									<span style="    float: left;    margin-top:2px;    padding-left: 12px;">Full Day</span>
									<form:radiobutton 										style="width:20px; float: left; " path="attandanceMasterList[${status.index}].dayStatus"
										id="activeStatus${status.index}" value="Full Day" />
								<span style="    float: left;    margin-top: 2px;  ">Half Day</span>
									<form:radiobutton style="width:20px;"
										path="attandanceMasterList[${status.index}].dayStatus" id="activeStatusHalf" value="Half Day" />
								</div>
								
								</td> --%>
								
								
								
								
							<%-- <td width="148"><c:out value="${menuRole.subModuleName}"/>
						<form:hidden path="moduleMenuMasterDTOList[${status.count - 1}].subModuleName"/></td>  --%>
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
		<div class="btn">
			<div class="savbtn">
				<input class="submit"
					style="font-size: 11px; font-weight: bold; padding: 0 0 0 30px;"
					type="submit" value="Save" name="operation" />
			</div>
			<div>
				<a href="weicome" class="cancelbtn"></a>

			</div>
		</div>

	</div>

	<%-- <form:hidden path="role.roleId" /> --%>
</form:form>