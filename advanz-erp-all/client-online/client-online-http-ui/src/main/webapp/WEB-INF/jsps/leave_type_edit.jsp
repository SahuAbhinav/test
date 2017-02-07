<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page isELIgnored="false"%>

<c:if test="${not empty(errors)}">
 <input type="hidden" id="errorId" value="${errors.errorMsg}">
 <script type="text/javascript">
 		$(document).ready(function() {
 		var errorId=document.getElementById('errorId');
		alert(errorId.value);
 		});
 	</script>
 </c:if>

 <c:if test="${opr=='R'}">
 <script type="text/javascript">
		var redrctUrl='get_leave_type_list';
				
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
 		 var frank_param = getParam('leaveId');
 		 //	confirm('Are you sure you want to delete?');
		 var delUrl='remove_leaveType?leaveId='+frank_param;
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

   <script type="text/javascript">
   function checkEdit()
	{
	alert('Login User Not Permit to Edit Record !!!!!!');
	}
	//	$(document).ready(function() {
 	function editMethod()
 	 { 
 	 var frank_param = $('#leaveId').val();
 	 //	confirm('Are you sure you want to delete?');
	 var delUrl='get_leaveType?leaveId='+frank_param+'&opr=E';
 	 window.self.location = delUrl;  
	}
 	</script>	
 <c:if test="${opr=='V' }">
<script>
$(document).ready(function() {
	$('input').attr('readonly','readonly');	
	$('select').attr('disabled','disabled');
	$('textarea').attr('readonly','readonly');
	$('.datepicker').attr('disabled','disabled');
	$('input:radio').attr('disabled',true);
	 $("input.checkbox").attr("disabled", true);
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
		//$("button").button();
		//$('input:text, input:password').button()   
		$(".datepicker").datepicker();
		//      $(":submit").button()
	});
</script>

<style type="text/css">
 table{
 width: 89%;
 }

.errorblock {
	color: #000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	margin-bottom: 16px;
	width: 82%;
}

.error {
	color: #ff0000;
}
</style>

<%-- 
<c:if test="${not empty(errorList)}">
	<div class="errorblock">
		<ul>
			<c:forEach items="${errorList.errorList}" var="err">
				<li>${err.errorMsg}</li>
			</c:forEach>
		</ul>
	</div>
</c:if> --%>
<c:if test="${opr=='E' || opr=='V'}">

<form:form name="input" id="formID" action="update_leaveType"
	method="post" modelAttribute="leaveTypeMastForm">
<form:hidden path="leaveTypeMastDTO.leaveId" id="leaveId" />
	<form:errors path="*" cssClass="errorblock" element="div" />

	<div class="panel-header">
		<div class="panel-title">Leave Type</div>
		<div class="panel-tool"></div>
	</div>

	<div align="left" class="bkgColor">
		<table class="" width="743" height="102" border="0"  >
			<tr>
			  <td width="224" height="30" align="left"><label>Leave<span style="color: #FF0000">*</span> </label>			  </td>
				<td width="145"><form:input  onkeypress="return check(event)"   data-maxsize="55"
						class="validate[required] text-input" style="width:80%;"
						path="leaveTypeMastDTO.leaveName" size="45" id="leaveName" />			  </td>
			  <td width="172" height="30"><label> Code<span
						style="color: #FF0000">*</span> </label>			  </td>
			  <td width="184"><form:input data-maxsize="16" style="width:72%" onkeyup="valid1(this)" onblur="valid1(this)" 
						class="validate[required] text-input" readonly="true"
						path="leaveTypeMastDTO.leaveCode" size="22" id="code" />			  </td>
			</tr>
			<tr>
				<td width="224" height="30" align="left"><label> Leave
			  Type<span style="color: #FF0000">*</span> </label>			  </td>
				<td width="145">
				
				  <form:select style="width: 83%; height: 21px"
						path="leaveTypeMastDTO.leaveType" id="leaveType">
													 <form:option value="monthly">Monthly</form:option>
             <form:option value="quarterly">Quarterly</form:option>
			<form:option value="halfYearly">Half Yearly</form:option>
             <form:option value="yearly">Yearly</form:option>

				</form:select>		 
							
			  </td>
			<td>Allow days<span style="color: #FF0000">*</span></td>
			  <td width="184">
			  <form:input data-maxsize="55"
						class="validate[required] text-input quantity validate[custom[number]]" style="width:71%;"  onkeypress="return check(event)"  
						path="leaveTypeMastDTO.allowDays" size="45" id="allowDays" />	  	  </td>
			</tr>
						
			<tr>
			  <td width="224" height="30" align="left"><label> Leave Carry Forward (yearly)<span style="color: #FF0000">*</span> </label>			  </td>
				<td width="145">
				<div id="radiobutton" style="border:solid 1px; height:20px; width:81%; border-color:#7f9db9;  border-radius: 3px 3px 3px 3px; background-color:#FFF;">
				<span style="    float: left;    margin-top:2px;   padding-left: 12px;"> Yes</span>
				 <form:radiobutton  style="    float: left; width:20px; " class="validate[required] radio"  value="1"
							path="leaveTypeMastDTO.leaveCarryForwardFlag"  id="leaveCarryForwardFlag"></form:radiobutton>
							
							<span style="    float: left;    margin-top: 2px;  "> No</span>			
				<form:radiobutton  
							class="validate[required] radio" style=" width:20px; "  value="0" path="leaveTypeMastDTO.leaveCarryForwardFlag"
							 id="leaveCarryForwardFlag"></form:radiobutton>   
						</div>	
			  </td>
							 <td>Applicable days (after join)<span style="color: #FF0000">*</span></td>
							  <td width="184">
			  <form:input data-maxsize="55"
						class="validate[required] text-input quantity validate[custom[number]]" style="width:72%;"  onkeypress="return check(event)"  
						path="leaveTypeMastDTO.applicableDays" size="45" id="applicableDays" />	  	  </td>
		  </tr>			
		  <tr>
		   <td height="24">Leave Encashment</td>
		  <td width="145">
				<div id="radiobutton" style="border:solid 1px; height:20px; width:81%; border-color:#7f9db9;  border-radius: 3px 3px 3px 3px; background-color:#FFF;">
				<span style="    float: left;    margin-top:2px;   padding-left: 12px;"> Yes</span>
				
				 <form:radiobutton  style="    float: left;  width:20px"
							path="leaveTypeMastDTO.encashmentFlag" value="1" id="encashmentFlag"></form:radiobutton>
							
				<span style="    float: left;    margin-top: 2px;  "> No</span>							
				<form:radiobutton  
							  path="leaveTypeMastDTO.encashmentFlag"  value="0"  style="   width:20px"
							 id="encashmentFlag"></form:radiobutton>   
						</div>	
			  </td>
			  <td>Encashment on</td>
			  <td width="100" rowspan="3" align="top">
		<div class="scrolltable" Style="height: 86px; width: 135px; background: none;"><table width="175" style="width:120px;" cellpadding="0" cellspacing="0" style="float:left; border:1px solid #7F9DB9;">
			  <c:forEach items="${salaryHeadList}" var="cat" varStatus="s">
                <tr>                  
                <th style="border-bottom:1px solid #99BBE8; padding-left: 3px;" width="587"><c:out value="${cat.salaryHeadName}"/></th>
                  <th style="border-bottom:1px solid #99BBE8;border-left:1px solid #99BBE8; " width="67"><form:checkbox   class="checkbox"  path="leaveTypeMastDTO.encashments[${s.index}].salaryHeadId" id="type"  value="${cat.salaryHeadId}"/></th>
                </tr></c:forEach></table></div>
               </td>
		  </tr>
			
			
			<tr>
				<td width="224" height="26" align="left"><label>
			  Description&nbsp;</label>			  </td>
				<td height="26" colspan="3"><form:input
						path="leaveTypeMastDTO.description" size="35"  onkeypress="return check(event)"  data-maxsize="150"  id="description" />				</td>
						<td></td><td></td>
			</tr>
		</table>
		<div class="savbtn">
         <c:if test="${opr=='V'}">
         <c:forEach items="${roleAndRightsDTOList}" var="roleAndRights">
		   <c:if test="${roleAndRights.menuId==sessionScope.menuId}">
    	  	 <c:if test="${roleAndRights.editFlag=='true'}">
		  	 <input class="edit_btn"  type="button" onclick="editMethod();" value=""/>
    	      <a href="get_leave_type_list"  class="cancelbtn" ></a>  
		     </c:if>
		      <c:if test="${roleAndRights.editFlag=='false'}">
		      <input class="edit_btn"  type="button" onclick="checkEdit();" value=""/>
    	      <a href="get_leave_type_list"  class="cancelbtn" ></a>  
		      </c:if>
		    
          </c:if>
          </c:forEach>	
   			
    	</c:if>
            <c:if test="${opr=='E'}">
               <input class="updatebtn"  type="submit" value=""/> 
                       <a href="get_leave_type_list"  class="cancelbtn" ></a>     	
   		   	</c:if>
    </div>
	</div>
	</div>


</form:form>
</c:if>

<c:if test="${opr=='R'}">

<form:form name="input" id="formID" action="update_leaveType"
	method="post" modelAttribute="leaveTypeMastForm">
<form:hidden path="leaveTypeMastDTO.leaveId" />
	<form:errors path="*" cssClass="errorblock" element="div" />

	<div class="panel-header">
		<div class="panel-title">Leave Type</div>
		<div class="panel-tool"></div>
	</div>

	<div align="left" class="bkgColor">
		<table class="" width="743" height="102" border="0" align="center">
			<tr>
			  <td width="224" height="30" align="left"><label>Leave<span style="color: #FF0000">*</span> </label>			  </td>
				<td width="145"><form:input data-maxsize="55"
						class="validate[required] text-input" style="width:78%;"
						path="leaveTypeMastDTO.leaveName" size="45" id="leaveName" readonly="true"/>			  </td>
			  <td width="172" height="30"><label> Code<span
						style="color: #FF0000">*</span> </label>			  </td>
			  <td width="184"><form:input data-maxsize="16" 
						class="validate[required] text-input" style="width:72%;"
						path="leaveTypeMastDTO.leaveCode" size="19" id="code" readonly="true" />			  </td>
			</tr>
			<tr>
				<td width="224" height="30" align="left"><label> Leave
			  Type<span style="color: #FF0000">*</span> </label>			  </td>
				<td width="145">
				
				  <form:select style="width: 113px; height: 21px"
						path="leaveTypeMastDTO.leaveType" id="leaveType" readonly="true" disabled="true">
													 <form:option value="monthly">Monthly</form:option>
             <form:option value="quarterly">Quarterly</form:option>
			<form:option value="halfYearly">Half Yearly</form:option>
             <form:option value="yearly">Yearly</form:option>

				</form:select>		 
							
			  </td>
			<td>Allow days<span style="color: #FF0000">*</span></td>
			  <td width="184">
			  <form:input data-maxsize="55"
						class="validate[required] text-input" style="width:72%;" readonly="true"
						path="leaveTypeMastDTO.allowDays" size="45" id="allowDays" />	  	  </td>
			</tr>
						
			<tr>
			  <td width="224" height="30" align="left"><label> Leave Carry Forward (yearly)<span style="color: #FF0000">*</span> </label>			  </td>
				<td width="145">
				
				 <form:radiobutton label="Yes" class="validate[required] radio" readonly="true" disabled="true"
							path="leaveTypeMastDTO.leaveCarryForwardFlag" value="1" id="leaveCarryForwardFlag"></form:radiobutton>			
				<form:radiobutton label="No"
							class="validate[required] radio"  readonly="true" disabled="true" path="leaveTypeMastDTO.leaveCarryForwardFlag"
							value="0" id="leaveCarryForwardFlag"></form:radiobutton>   
							
			  </td>
							 <td>Applicable days (after join)<span style="color: #FF0000">*</span></td>
							  <td width="184">
			  <form:input data-maxsize="55"
						class="validate[required] text-input" style="width:72%;" readonly="true"
						path="leaveTypeMastDTO.applicableDays" size="45" id="applicableDays" />	  	  </td>
		  </tr>			
		  <tr>
		   <td height="24">Leave Encashment</td>
		  <td width="145">
				
				 <form:radiobutton label="Yes"  
							path="leaveTypeMastDTO.encashmentFlag" value="1" id="encashmentFlag" disabled="true"></form:radiobutton>			
				<form:radiobutton label="No"
							  path="leaveTypeMastDTO.encashmentFlag"  value="0" disabled="true"
							 id="encashmentFlag"></form:radiobutton>   
							
			  </td>
			  <td>Encashment on</td>
			  <td width="100" rowspan="3" align="top">
		<div class="scrolltable" Style="height: 86px; width: 134px; background: none;"><table width="175" style="width: 120px;" cellpadding="0" cellspacing="0" style="float:left; border:1px solid #7F9DB9;">
			  <c:forEach items="${salaryHeadList}" var="cat" varStatus="s">
                <tr>                  
                 <th style="border-bottom:1px solid #99BBE8; padding-left: 3px;" width="587"><c:out value="${cat.salaryHeadName}"/></th>
                   <th style="border-bottom:1px solid #99BBE8;border-left:1px solid #99BBE8; " width="67"><form:checkbox  path="leaveTypeMastDTO.encashments[${s.index}].salaryHeadId" disabled="true" id="type"  value="${cat.salaryHeadId}"/></th>
                </tr></c:forEach></table></div>
               </td>
		  </tr>
			
			
			<tr>
			<td width="224" height="26" align="left"><label>  Description&nbsp;</label>		
			  </td>
				<td height="26" colspan="3"><form:input	path="leaveTypeMastDTO.description" size="50"  onkeypress="return check(event)"  data-maxsize="150" readonly="true" id="description" />				
				</td>
				<td></td><td></td>
			</tr>
		</table>
		<div class="btn">
				<c:url value="remove_leaveType" var="remove_url">
					<c:param name="leaveId" value="${leaveTypeMastForm.leaveTypeMastDTO.leaveId}"></c:param>
				</c:url>
				<div class="btn">
					<a href="${remove_url}" class="removebtn" iconCls="icon-remove"> </a> 
					<a href="get_leave_type_list" class="cancelbtn" iconCls="icon-cancel"> </a>
				</div>
			</div>
	</div>
</form:form>
</c:if>