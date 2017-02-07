<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>

 <c:if test="${opr=='R'}">
 <script type="text/javascript">
		var redrctUrl='show_Employee_list';
				
		function getParam(name)
		{
		 name = name.replace(/[\[]/,"\\\[").replace(/[\]]/,"\\\]");
		 var regexS = "[\\?&]"+name+"=([^&#]*)";
		 var regex = new RegExp( regexS );
		 var results = regex.exec( window.location.href );
		 if( results == null )
		  return "";
		else
		 return results[1];
		}

		$(document).ready(function() {
 		 var frank_param = getParam('employeeId');
 		 //	confirm('Are you sure you want to delete?');
		 var delUrl='employee_remove?employeeId='+frank_param;
 		 if(confirm('Are you sure you want to delete?')) 
 		   {
 			window.self.location = delUrl;
 		 	}
		 else{
			 window.self.location = redrctUrl;
  			}
		});
 	</script>
 	</c:if>

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
	$(document).ready(function() {
		$('input').attr('disabled', 'disabled');
		$('select').attr('disabled', 'disabled');
	});
</script>


<script type="text/javascript">
	function refresh() {
		alert("call325");
		// $.post("ajax_Employee_form", $("#formIDTest").serialize());
		document.getElementById("script").value = '1';
		document.forms["formIDTest"].submit();

		alert("call1234");
	}
</script>

<script type="text/javascript">
	//anonymous self invoking function to avoid conflicting with other JavaScript
	(function($) {
		//function is called when the page is fully loaded
		$(document).ready(function() {
			//the page is loaded so attach the time picker to an input field
			$(".myTimePicker").timepicker({});
		});
	})(jQuery);

	$(document).ready(function() {
		$(".quantity").keypress(function(e) {
			//if the letter is not digit then display error and don't type anything
			if (e.which != 8 && e.which != 0 && (e.which<48 || e.which>57)) {
				//display error message
				$(".errmsg").html("Digits Only").show().fadeOut("slow");
				return false;
			}
		});
	});
</script>


<script type="text/javascript">
	$(document).ready(function() {

		$("button").button();

		$(".datepicker").datepicker();
	});
</script>


<script type="text/javascript">
	$(function() {
		$("#tabs").tabs();
	});
</script>
<style type="text/css">
.ui-tabs .ui-tabs-nav li.ui-tabs-selected a,.ui-tabs .ui-tabs-nav li.ui-state-disabled a,.ui-tabs .ui-tabs-nav li.ui-state-processing a
	{
	background-color: #4e8ccf;
	color: #FFFFFF;
}

.bkgColor {
	background: #F6F6F6;
	border-color: #4E8CCF;
	width: 997px;
	float: left;
	padding-top: 25px;
	height: 443px;
}

select {
	width: 127px !important;
	height: 21px !important;
}

.ui-state-default,.ui-widget-content .ui-state-default,.ui-widget-header .ui-state-default
	{
	background-color: #4e8ccf !important;
}

.ui-state-default,.ui-widget-content .ui-state-default,.ui-widget-header .ui-state-default
	{
	background: none;
	color: #FFFFFF;
}

.ui-widget-content {
	background: none;
}

.ui-corner-all,.ui-corner-bottom,.ui-corner-right,.ui-corner-br {
	background-color: #e0ebff;
}

.ui-tabs .ui-tabs-nav li a,.ui-tabs-collapsible .ui-tabs-nav li.ui-tabs-active a
	{
	background-color: #e0ecff;
	color: #416aa3;
	font-weight: normal;
	font-family: Verdana, Arial, Helvetica, sans-serif;
	width: 99px;
}

.tabs li.tabs-selected a.tabs-inner {
	background: none !important;
}

.ui-tabs .ui-tabs-nav li {
	border: 1px solid #4E8CCF !important;
	border-radius: 0 0 0 0;
	width: 123px;
}

.tabs li a.tabs-inner {
	background: none !important;
	text-align: center !important;
}

.tabs li {
	width: 80px !important;
	text-align: center;
}

.easyui-tabs tabs-container {
	height: 150px !important;
}

.tabs-panels {
	height: 142px !important;
	padding: 0px !important;
	width: 800px;
	!
	important;
}

div.ui-datepicker {
	font-size: 10px;
}

.panel-header {
	width: 68%;
	height: 12px;
}

.ui-tabs .ui-tabs-panel {
	padding: 0px;
}

.ui-widget input,.ui-widget select,.ui-widget textarea,.ui-widget button
	{
	font-family: Verdana, Arial, Helvetica, sans-serif !important;
}

.ui-widget-header {
	background: #4e8ccf !important;
	border: 1px solid #4E8CCF !important;
	padding-bottom: 12px;
}

#dlg-buttons {
	text-align: center;
	margin-top: 11px;
	float: left;
	margin-left: 9px;
}

.ui-tabs .ui-tabs-nav li.ui-tabs-active a,.ui-tabs .ui-tabs-nav li.ui-state-disabled a,.ui-tabs .ui-tabs-nav li.ui-tabs-loading a
	{
	background-color: #4e8ccf;
	color: #FFFFFF;
}

.ui-tabs-nav {
	background-color: #e0ecff !important;
}

#tabs .ui-tabs-nav {
	background-color: #e0ecff !important;
	font-size: 12px;
}

select {
	width: 151px;
	height: 20px;
}

h2 {
	text-align: center;
	font-size: 16px;
	margin: 11px 0 0 0;
}

.ui-tabs .ui-tabs-nav {
	padding: 0px;
}

th {
	font-size: 10px;
	text-align: center;
}

td {
	font-size: 12px;
	vertical-align: top;
}

.submit {
	background: url(static/images/save.jpg);
	height: 24px;
	border: none;
	width: 78px;
}

.savbtn {
	width: 78px;
	float: left;
}

.netotal {
	float: right;
	margin-right: 9px;
	margin-top: 15px;
	width: 203px;
}


.btn {
	margin-left: 5px;
	margin-top: 12px;
	float: left;
	width: 450px;
}

.submit1 {
	background: url(static/images/save.jpg);
	height: 24px;
	border: none;
	width: 78px;
}

.gridheadingdiv td {
	border: 1px solid #7F9DB9;
	height: 24px;
}

p {
	color: blue;
}

.errmsg {
	color: red;
}

a.disabled:link,a.disabled:visited {
	color: grey;
}

.errorblock {
	color: #000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	margin-bottom: 16px;
	width: 82%;
}
</style>
<%-- <div class="errorblock">
	<ul>
		<li>Remove Confirmation</li>
	</ul>
</div>

<c:if test="${not empty(errorList)}">
	<div class="errorblock">
		<ul>
			<c:forEach items="${errorList.errorList}" var="err">
				<li>${err.errorMsg}</li>
			</c:forEach>
		</ul>
	</div>
</c:if> --%>
<form:form name="input" id="formID" action="" method="post"
	modelAttribute="employeeForm">

	<div id="tabs-1" style="background-color: #FF0000; font-size: 11px">
		<div align="left" class="bkgColor">
			<h2>Employee Details</h2>
			<table width="997" height="220"
				style="margin-left: 9px; float: left; font-family: Arial;"
				border="0" align="right">
				<tbody>
					<tr>
						<td width="136" height="30">Employee First Name <span
							style="color: #FF0000">*</span>
						</td>
						<td width="137"><form:input type="text" data-maxsize="35"
								class="validate[required] text-input"
								path="employeeDTO.employeeName" id="employeeName" size="18" />
						</td>
						<td width="94">Last Name <span style="color: #FF0000">*</span>
						</td>
						<td width="143"><form:input type="text" data-maxsize="35"
								class="validate[required] text-input"
								path="employeeDTO.employeeLastName" id="employeeLastName"
								size="18" /></td>
						<td width="96" align="left">Employee Code <span
							style="color: #FF0000">*</span>
						</td>
						<td width="120"><form:input type="text" data-maxsize="16"
								class="validate[required] text-input"
								path="employeeDTO.employeeCode" id="employeeCode" size="18" />
						</td>
					</tr>
					<tr>
						<td height="30">Gross Annual Salary</td>
						<td><form:input type="text" path="employeeDTO.empSalary"
								class="quantity" id="empSalary" size="18" /> <span
							class="errmsg"></span></td>
						<td>Nationality</td>
						<td><form:input type="text" path="employeeDTO.nationality"
								id="nationality" size="18" /></td>
						<td align="left">Active Status<span style="color: #FF0000">*</span>
						</td>
						<td><div
								style="border: solid 1px; height: 20px; width: 124px; border-color: #7f9db9; background-color: #FFF;">
								&nbsp; Yes
								<form:radiobutton class="validate[required] radio"
									style="width:20px;" path="employeeDTO.activeStatus"
									id="activeStatus" value="1" />
								No
								<form:radiobutton style="width:20px;"
									class="validate[required] radio"
									path="employeeDTO.activeStatus" id="activeStatus" value="0" />
							</div></td>
					</tr>
					<tr>
						<td height="30">Qualification <span style="color: #FF0000">*</span>
						</td>
						<td><form:select path="employeeDTO.qualificationId"
								items="${qualificationes}" itemLabel="formName"
								itemValue="formId" id="qualificationId"
								class="validate[required]" style="width: 250px; height: 20px;">

							</form:select></td>
						<td>Experience in year (At Joining)</td>
						<td><form:input type="text" data-maxsize="35"
								path="employeeDTO.experience" size="18" id="experience" /></td>
						<td align="left">Join Date<span style="color: #FF0000">*</span>
						</td>
						<td><form:input type="text" path="employeeDTO.joinDateString"
								class="datepicker" id="joinDateString" size="16" /> <!-- <input type="text" name="joinDate" size="18" id="joinDate" class="datepicker validate[required] text-input"/>
             --></td>
					</tr>


					<tr>
						<td height="28">Employee Type <span style="color: #FF0000">*</span>
						</td>

						<!--   <select style="width:113px; height:21px" class="validate[required] text-input" name="employeeType" 
            id="employeeType">
              <option></option>
            </select> -->
						<td><form:select path="employeeDTO.employeeType"
								items="${employeeType}" itemLabel="formName" itemValue="formId"
								id="employeeType" class="validate[required]"
								style="width: 250px; height: 20px;">

							</form:select></td>


						<td>Department <span style="color: #FF0000">*</span>
						</td>
						<td>
							<!-- <select style="width:113px; height:21px" class="validate[required] text-input" name="department" id="department">
              <option></option>
            </select> --> <form:select
								path="employeeDTO.masterEntityDepartment"
								items="${Departmentes}" itemLabel="formName" itemValue="formId"
								id="employeeType" class="validate[required]"
								style="width: 250px; height: 20px;">

							</form:select>
						</td>
						<td align="left">Sub Department <span style="color: #FF0000">*</span>
						</td>
						<td>
							<!--  <select style="width:113px; height:21px" class="validate[required] text-input"
             name="subDepartment" id="subDepartment">
              <option></option>
            </select>--> <form:select
								path="employeeDTO.masterSubEntityDepartment"
								items="${subDepartment}" itemLabel="formName" itemValue="formId"
								id="employeeType" class="validate[required]"
								style="width: 250px; height: 20px;">

							</form:select>
						</td>
					</tr>

					<tr>
						<td height="30">Designation <span style="color: #FF0000">*</span>
						</td>
						<td>
							<!-- <select style="width:113px; height:21px" class="validate[required] text-input"
                 name="designation" id="designation">
     	          <option></option>
   	          </select> --> <form:select path="employeeDTO.designation"
								items="${designation}" itemLabel="formName" itemValue="formId"
								id="designation" class="validate[required]"
								style="width: 250px; height: 20px;">

							</form:select>
						</td>
						<td>Shift <span style="color: #FF0000">*</span>
						</td>
						<td>
							<!--  <select style="width:113px; height:21px" class="validate[required] text-input" name="shift" id="shift">
                  <option></option>
                </select> --> <form:select
								path="employeeDTO.masterEntitShift" items="${shift}"
								itemLabel="formName" itemValue="formId" id="masterEntitShift"
								class="validate[required]" style="width: 250px; height: 20px;">

							</form:select>
						</td>
						<td align="left">Special Skills</td>
						<td><form:input type="text" path="employeeDTO.specialSkills"
								data-maxsize="35" id="specialSkills" size="18" /></td>
					</tr>
					<tr>
						<td height="31">Separation Date</td>
						<td><form:input type="text" path="employeeDTO.separationDate"
								class="datepicker" id="separationDate" size="16" /></td>
						<td>Separation Reason <span style="color: #FF0000">*</span>
						</td>
						<td><form:select style="width: 113px; height: 21px"
								path="employeeDTO.separationReason"
								class="validate[required] text-input" id="separationReason">
								<option value="resign">Resign</option>
								<option value="death">Death</option>
								<option value="diseased">Diseased</option>
								<option value="others">Others</option>
							</form:select></td>
						<td align="left">Grade <span style="color: #FF0000">*</span>
						</td>
						<td>
							<!--      <select style="width:113px; height:21px" class="validate[required] text-input" name="grade" id="grade">
                  <option></option>
                </select>  --> <form:select
								path="employeeDTO.masterEntityGrade" items="${shift}"
								itemLabel="formName" itemValue="formId" id="masterEntityGrade"
								class="validate[required]" style="width: 250px; height: 20px;">

							</form:select>
						</td>
					</tr>
					<tr>
						<td height="25">Gender</td>
						<td><div
								style="border: solid 1px; height: 20px; width: 124px; border-color: #7f9db9; background-color: #FFF;">
								&nbsp; M
								<form:radiobutton style="width:20px;" path="employeeDTO.gender"
									id="gender" value="male" />
								F
								<form:radiobutton style="width:20px;" path="employeeDTO.gender"
									id="gender" value="female" />
							</div></td>
						<td>Marital Status</td>
						<td><div
								style="border: solid 1px; height: 20px; width: 124px; border-color: #7f9db9; background-color: #FFF;">
								&nbsp; Yes
								<form:radiobutton style="width:20px;"
									path="employeeDTO.maritalStatus" id="maritalStatus" value="1" />
								No
								<form:radiobutton style="width:20px;"
									path="employeeDTO.maritalStatus" id="maritalStatus" value="0" />
							</div></td>
						<td align="left">Email ID</td>
						<td><form:input type="text" path="employeeDTO.emailId"
								id="emailId" size="18" /></td>
					</tr>
				</tbody>
			</table>

			<div id="dlg-buttons">
				<div class="btn">
					<c:url value="employee_remove" var="remove_url">
						<c:param name="employeeId"
							value="${employeeForm.employeeDTO.employeeId}"></c:param>
					</c:url>


					<div class="btn">
						<div class="savbtn">
							<a href="${remove_url}" class="removebtn"></a>
						</div>

						<div class="cancelbtn">
							<a href="show_Employee_list" class="cancelbtn"
								iconCls="icon-cancel"></a>
						</div>
					</div>

				</div>
			</div>

		</div>
	</div>
</form:form>