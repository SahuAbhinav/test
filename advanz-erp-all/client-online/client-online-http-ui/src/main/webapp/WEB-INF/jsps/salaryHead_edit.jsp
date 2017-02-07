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
		var redrctUrl='get_salaryHead_list';
				
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
 		 var frank_param = getParam('salaryHeadId');
 		 //	confirm('Are you sure you want to delete?');
		 var delUrl='remove_salaryHead?salaryHeadId='+frank_param;
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
 	 var frank_param = $('#salaryHeadId').val();
 	 //	confirm('Are you sure you want to delete?');
	 var delUrl='get_salaryHead?salaryHeadId='+frank_param+'&opr=E';
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
function getHeadSeq(ele){
	var headSequence = ele.value;
	$.ajax({
		type : "POST",
		url : "getHeadSequenceNo",
		data : "headSequence=" + headSequence,
		success : function(response) {
			$("#sequenceNo").val(response.result);
			
		}});
	
	
}
</script>
<script >
 

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
	
	function check(index) {
		//	alert("Hello");
			var name = "baseHeadPer";
			var name1="type"+index;
			if (document.getElementById(name1).checked) {
				document.getElementById(name).disabled = false;
				document.getElementById(name).readonly = false;
			} else {
				document.getElementById(name).value = "";
				document.getElementById(name).disabled = true;
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
		$("button").button();
		//$('input:text, input:password').button()   
		$(".datepicker").datepicker();
		//      $(":submit").button()
		
		$('#professionalTaxFlag').click(function(){
		    if ($('#professionalTaxFlag').attr('checked')) {
		       $("#baseHeadPer").val('');
		    }
		}); 
		//1
		$('#professionalTaxFlag').click(function(){
			//To check flag is available or not
			$.ajax({
				type : "POST",
				url : "checkFlagIsAvlaible",
				data : "flagTYpe=ProfessionalTaxFlag",
				success : function(response) {
					if(response.result==false){
					alert("You can not select this flag as it is already existe");
					 $("#professionalTaxFlag").removeAttr("checked");
					}
				}});
			
			
		    if ($('#basicSalaryFlag').attr('checked')) {
		      alert("You can not check more than one flag");
		      $("#professionalTaxFlag").removeAttr("checked");
		    }
		    if ($('#pfEmployeeFlag').attr('checked')) {
			      alert("You can not check more than one flag");
			      $("#professionalTaxFlag").removeAttr("checked");
			    }
		    if ($('#esiEmployeeFlag').attr('checked')) {
			      alert("You can not check more than one flag");
			      $("#professionalTaxFlag").removeAttr("checked");
			    }
		}); 
		//
		//2
		$('#basicSalaryFlag').click(function(){
			
			//To check flag is available or not
			$.ajax({
				type : "POST",
				url : "checkFlagIsAvlaible",
				data : "flagTYpe=BasicSalaryFlag",
				success : function(response) {
					if(response.result==false){
					alert("You can not select this flag as it is already existe");
					 $("#basicSalaryFlag").removeAttr("checked");
					}
				}});
			
			
			
		    if ($('#professionalTaxFlag').attr('checked')) {
		      alert("You can not check more than one flag");
		      $("#basicSalaryFlag").removeAttr("checked");
		    }
		    if ($('#pfEmployeeFlag').attr('checked')) {
			      alert("You can not check more than one flag");
			      $("#basicSalaryFlag").removeAttr("checked");
			    }
		    if ($('#esiEmployeeFlag').attr('checked')) {
			      alert("You can not check more than one flag");
			      $("#basicSalaryFlag").removeAttr("checked");
			    }
		}); 
		//
		//3
		$('#pfEmployeeFlag').click(function(){
			
			//To check flag is available or not
			$.ajax({
				type : "POST",
				url : "checkFlagIsAvlaible",
				data : "flagTYpe=PfEmployeeFlag",
				success : function(response) {
					if(response.result==false){
					alert("You can not select this flag as it is already existe");
					 $("#pfEmployeeFlag").removeAttr("checked");
					}
				}});
			
		    if ($('#professionalTaxFlag').attr('checked')) {
		      alert("You can not check more than one flag");
		      $("#pfEmployeeFlag").removeAttr("checked");
		    }
		    if ($('#basicSalaryFlag').attr('checked')) {
			      alert("You can not check more than one flag");
			      $("#pfEmployeeFlag").removeAttr("checked");
			    }
		    if ($('#esiEmployeeFlag').attr('checked')) {
			      alert("You can not check more than one flag");
			      $("#pfEmployeeFlag").removeAttr("checked");
			    }
		}); 
		//
		//4
		$('#esiEmployeeFlag').click(function(){
			
			//To check flag is available or not
			$.ajax({
				type : "POST",
				url : "checkFlagIsAvlaible",
				data : "flagTYpe=EsiEmployeeFlag",
				success : function(response) {
					if(response.result==false){
					alert("You can not select this flag as it is already existe");
					 $("#esiEmployeeFlag").removeAttr("checked");
					}
				}});
			
		    if ($('#professionalTaxFlag').attr('checked')) {
		      alert("You can not check more than one flag");
		      $("#esiEmployeeFlag").removeAttr("checked");
		    }
		    if ($('#basicSalaryFlag').attr('checked')) {
			      alert("You can not check more than one flag");
			      $("#esiEmployeeFlag").removeAttr("checked");
			    }
		    if ($('#pfEmployeeFlag').attr('checked')) {
			      alert("You can not check more than one flag");
			      $("#esiEmployeeFlag").removeAttr("checked");
			    }
		}); 
	});
	
</script>

<style type="text/css">
.errorblock {
	color: #000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	margin-bottom: 16px;
	width: 82%;
}
.scrolltable {
overflow: hidden;
}
.scrolltable:hover {
    overflow-y: scroll;
}
</style>


<c:if test="${opr=='E' || opr=='V'}">
	<form:form name="input" id="formID" action="update_salaryHead"
		method="post" modelAttribute="salaryHeadForm">
		<form:hidden path="salaryHeadDTO.salaryHeadId" id="salaryHeadId" />
		<form:errors path="*" cssClass="errorblock" element="div" />

		<div class="panel-header">
			<div class="panel-title">Salary Head Master</div>
			<div class="panel-tool"></div>
		</div>

		<div align="left" class="bkgColor">
			<table class="" width="743" height="102" border="0">
				<tr>
					<td width="145" height="30" align="left"><label> Head
							Name<span style="color: #FF0000">*</span> </label></td>
					<td width="143"><form:input data-maxsize="55"
							class="validate[required] text-input" onkeypress="return check(event)" style="width:68%;"
							path="salaryHeadDTO.salaryHeadName" size="45" id="salaryHeadName" />
					</td>
					<td width="116" height="30"><label> Code<span
							style="color: #FF0000">*</span> </label></td>
					<td width="143"><form:input data-maxsize="16"
							 readonly="true" onkeypress="return check(event)"
							class="validate[required] text-input"
							path="salaryHeadDTO.salaryHeadCode" size="22" style="width:61%;"
							id="code" /></td>
				</tr>
				<tr>
					<td width="145" height="30" align="left"><label> Head
							Type<span style="color: #FF0000">*</span> </label></td>
					<td width="143">
					<div id="radiobutton" 	style="border: solid 1px; height: 20px; width: 68%;  border-radius: 3px 3px 3px 3px; border-color: #7f9db9; background-color: #FFF;">
					<span style="    float: left;    margin-top:2px;    padding-left: 12px;"> Earning</span>
					<form:radiobutton  
							class="validate[required] radio" style="    float: left;     margin-top: 2px;" path="salaryHeadDTO.headType" onclick="getHeadSeq(this);"
							value="E" id="headType"></form:radiobutton>
							
							<span style="    float: left;    margin-top: 2px;  "> Deduction</span>	 <form:radiobutton
							  class="validate[required] radio"  style="   margin-top: 2px;" 
							path="salaryHeadDTO.headType" onclick="getHeadSeq(this);" value="D" id="headType"></form:radiobutton>
</div>
					</td>
					<td width="116" height="30"><label>Type<span
							style="color: #FF0000">*</span> </label></td>
					<td width="143"><form:select style="width: 63%; height: 21px"
							path="salaryHeadDTO.type" id="type">
							
							<form:option value="constant">Constant</form:option>
							<%-- <form:option value="slab">Slab</form:option> --%>
							<form:option value="variable">Variable</form:option>
						</form:select></td>
				</tr>
				<tr>
					<td width="145" height="30" align="left"><label> Base
							Head </label></td>
					<td width="100" rowspan="3" align="top">
						<div class="scrolltable"Style="height: 86px; width: 163px; background: none;">
							<table style="width: 146px;" width="175" cellpadding="0"
								cellspacing="0" style="float:left; border:1px solid #7F9DB9;">
								<c:forEach items="${salaryHeadList}" var="cat" varStatus="salaryHead">
									<tr>
										<th style="border-bottom:1px solid #99BBE8; padding-left: 3px;" width="587"><c:out value="${cat.salaryHeadName}" /></th>
											<th style="border-bottom:1px solid #99BBE8; border-left:1px solid #99BBE8; border-right:1px solid #99BBE8; "> 
											<form:checkbox class="checkbox"	path="salaryHeadDTO.baseHeads[${salaryHead.index}].salaryHeadId"
											id="type${salaryHead.index}" value="${cat.salaryHeadId}" onclick="check(${salaryHead.index});" /></th>
									</tr>
								</c:forEach>
							</table>
							</div>
					</td>
					<td width="116" height="30"><label> Base Head % </label>
						 </td>
					<td width="143"><form:input path="salaryHeadDTO.baseHeadPer"
							size="22" class="quantity" style="width:61%;" id="baseHeadPer" />
					</td>
				</tr>
				<tr>
					<td></td>
					<td width="116" height="30" align="left"><label>Payable
							Type<span style="color: #FF0000">*</span> </label></td>
					<td width="143"><form:select style="width: 63%; height: 21px"
							path="salaryHeadDTO.payableType" id="payableType">
							 <form:option value="monthly">Monthly</form:option>
             <form:option value="quarterly">Quarterly</form:option>
			<form:option value="halfYearly">Half Yearly</form:option>
             <form:option value="yearly">Yearly</form:option>
						</form:select></td>
				</tr>
				<tr>
					<td></td>
					<td width="116" height="30" align="left"><label>Payable
							Month<span style="color: #FF0000">*</span> </label></td>
					<td width="143"><form:select style="width: 63%; height: 21px"
							path="salaryHeadDTO.payableMonth" id="payableMonth">
							 <form:option value="january">January</form:option>
             <form:option value="february">February</form:option>
             <form:option value="march">March</form:option>
             <form:option value="april">April</form:option>
             <form:option value="may"> May</form:option>
             <form:option value="june">June</form:option>
             <form:option value="july">July</form:option>
             <form:option value="august">August</form:option>
             <form:option value="september">September</form:option>
             <form:option value="october">October</form:option>
             <form:option value="november">November</form:option>
             <form:option value="december">December</form:option>
						</form:select></td>
				</tr>
				<tr>
					<td>Salary Head Sequence</td><td><form:input
						path="salaryHeadDTO.salaryHeadSequence" style="text-align:right; width:60%"  class="validate[custom[number]]" onkeypress="return check(event);"  size="110" id="sequenceNo" /></td>
					<td width="116" height="30" align="left"><label>Professional
							Tax </label> <form:checkbox  class="checkbox"  path="salaryHeadDTO.professionalTaxFlag"
							id="professionalTaxFlag" value="1" />
					</td>
					<td width="35" height="30"><label> Basic Salary </label> <form:checkbox  class="checkbox" 
							path="salaryHeadDTO.basicSalaryFlag" id="basicSalaryFlag"
							value="1" />
					</td>
				</tr>
				 <tr>
			 <td height="30" width="35" align="left" nowrap="nowrap"><label>P. F. Flag</label></td><td width="35">			
		     <form:checkbox path="salaryHeadDTO.pfEmployeeFlag" id="pfEmployeeFlag"  value="1"/>&nbsp;&nbsp;<label style="padding-left: 20px;">E.S.I. Flag</label>&nbsp;			 
			 <form:checkbox path="salaryHeadDTO.esiEmployeeFlag" id="esiEmployeeFlag"  value="1" /></td>
			 </tr>
				<tr>
					<td width="145" height="26" align="left"><label>
							Description&nbsp;</label></td>
					<td height="26" colspan="3"><form:input
							path="salaryHeadDTO.description" style="width:87%" onkeypress="return check(event)" data-maxsize="150" size="94"
							id="description" /></td>
				</tr>
			</table>
			<div class="savbtn">
         <c:if test="${opr=='V'}">
         <c:forEach items="${roleAndRightsDTOList}" var="roleAndRights">
		   <c:if test="${roleAndRights.menuId==sessionScope.menuId}">
    	  	 <c:if test="${roleAndRights.editFlag=='true'}">
		  	 <input class="edit_btn"  type="button" onclick="editMethod();" value=""/>
    	      <a href="get_salaryHead_list"  class="cancelbtn" ></a>  
		     </c:if>
		      <c:if test="${roleAndRights.editFlag=='false'}">
		      <input class="edit_btn"  type="button" onclick="checkEdit();" value=""/>
    	      <a href="get_salaryHead_list"  class="cancelbtn" ></a>  
		      </c:if>
		    
          </c:if>
          </c:forEach>	
   		 		
    	</c:if>
            <c:if test="${opr=='E'}">
               <input class="updatebtn"  type="submit" value=""/> 
                       <a href="get_salaryHead_list"  class="cancelbtn" ></a>     	
   		   	</c:if>
    </div>
	</div>
	</div>




	</form:form>
</c:if>
 <c:if test="${opr=='R'}">
<%--	<div class="errorblock">
		<ul>
			<li>Remove Confirmation</li>
		</ul>
	</div> --%>
	<form:form name="input" id="formID" action="" method="post"
		modelAttribute="salaryHeadForm">

		<form:errors path="*" cssClass="errorblock" element="div" />
		<%-- <form:hidden path="salaryHeadDTO.salaryHeadId" /> --%>
		<div class="panel-header">
			<div class="panel-title">Salary Head Master</div>
			<div class="panel-tool"></div>
		</div>

		<div align="left" class="bkgColor">
			<table class="" width="743" style="width: 743px;" height="102" border="0">
				<tr>
					<td width="145" height="30" align="left"><label> Head
							Name<span style="color: #FF0000">*</span> </label></td>
					<td width="143"><form:input data-maxsize="55"
							class="validate[required] text-input" style="width:77%;"
							readOnly="true" path="salaryHeadDTO.salaryHeadName" size="45"
							id="salaryHeadName" /></td>
					<td width="116" height="30"><label> Code<span
							style="color: #FF0000">*</span> </label></td>
					<td width="143"><form:input data-maxsize="16"
							class="validate[required] text-input" readOnly="true"
							path="salaryHeadDTO.salaryHeadCode" size="22" style="width:66%;"
							id="code" /></td>
				</tr>
				<tr>
					<td width="145" height="30" align="left"><label> Head
							Type<span style="color: #FF0000">*</span> </label></td>
					<td width="143"><form:radiobutton label="Earning"
							class="validate[required] radio" disabled="true"
							path="salaryHeadDTO.headType" value="E" id="headType"></form:radiobutton>
						<form:radiobutton label="Deduction"
							class="validate[required] radio" path="salaryHeadDTO.headType"
							disabled="true" value="D" id="headType"></form:radiobutton></td>
					<td width="116" height="30"><label>Type<span
							style="color: #FF0000">*</span> </label></td>
					<td width="143"><form:select style="width: 67%; height: 21px"
							readOnly="true" path="salaryHeadDTO.type" id="type" disabled="true">
							<form:option value="variable">Variable</form:option>
							<form:option value="constant">Constant</form:option>
							<form:option value="slab">Slab</form:option>
						</form:select></td>
				</tr>
				<tr>
					<td width="145" height="30" align="left"><label> Base
							Head </label></td>
					<td width="100" rowspan="3" align="top">
						<div class="scrolltable" Style="height: 86px; width: 163px; background: none;">
							<table style="width: 146px;" width="175" cellpadding="0"
								cellspacing="0" style="float:left; border:1px solid #7F9DB9;">
								<c:forEach items="${salaryHeadList}" var="cat"
									varStatus="salaryHead">
									<tr>
										<th style="border-bottom:1px solid #99BBE8; padding-left: 3px;" width="587">
											
											<c:out value="${cat.salaryHeadName}" /> </th>
											<th style="border-bottom:1px solid #99BBE8;border-left:1px solid #99BBE8; " width="67"><form:checkbox
											  class="checkbox" 	path="salaryHeadDTO.baseHeads[${salaryHead.index}].salaryHeadId"
												id="type${salaryHead.index}" value="${cat.salaryHeadId}" onclick="check(${salaryHead.index});" disabled="true" />
												</th>
									</tr>
								</c:forEach>
							</table></div>
					</td>
					<td width="116" height="30"><label> Base Head % </label></td>
					<td width="143"><form:input path="salaryHeadDTO.baseHeadPer" readOnly="true"
							size="22" style="width:66%;" id="baseHeadPer" disabled="true" /></td>
				</tr>
				<tr>
					<td></td>
					<td width="116" height="30" align="left"><label>Payable
							Type<span style="color: #FF0000">*</span> </label></td>
					<td width="143"><form:select style="width: 67%; height: 21px" disabled="true"
							readOnly="true" path="salaryHeadDTO.payableType" id="payableType">
							<form:option value="monthly">Monthly</form:option>
							<form:option value="quarterly">Quarterly</form:option>
							<form:option value="halfYearly">Half Yearly</form:option>
							<form:option value="yearly">Yearly</form:option>
						</form:select></td>
				</tr>
				<tr>
					<td></td>
					<td width="116" height="30" align="left"><label>Payable
							Month<span style="color: #FF0000">*</span> </label></td>
					<td width="143"><form:select style="width: 67%; height: 21px" disabled="true"
							readOnly="true" path="salaryHeadDTO.payableMonth"
							id="payableMonth">
							 <form:option value="january">January</form:option>
             <form:option value="february">February</form:option>
             <form:option value="march">March</form:option>
             <form:option value="april">April</form:option>
             <form:option value="may"> May</form:option>
             <form:option value="june">June</form:option>
             <form:option value="july">July</form:option>
             <form:option value="august">August</form:option>
             <form:option value="september">September</form:option>
             <form:option value="october">October</form:option>
             <form:option value="november">November</form:option>
             <form:option value="december">December</form:option>
						</form:select></td>
				</tr>
				<tr>
					<td>Salary Head Sequence</td><td><form:input
						path="salaryHeadDTO.salaryHeadSequence" style="text-align:right; width:60%"  class="validate[custom[number]]" onkeypress="return check(event);"  size="110" id="sequenceNo" /></td>
					<td width="116" height="30" align="left"><label>Professional
							Tax </label> <form:checkbox  class="checkbox"  path="salaryHeadDTO.professionalTaxFlag"
							readOnly="true" id="professionalTaxFlag" disabled="true" value="1" />
					</td>
					<td width="35" height="30"><label> Basic Salary </label> <form:checkbox
							path="salaryHeadDTO.basicSalaryFlag" id="basicSalaryFlag" disabled="true"
							value="1" />
					</td>
				</tr>
				
				
				 <tr>
			 <td height="30" width="35" align="left" nowrap="nowrap"><label>P. F.Employee Flag</label></td><td width="35">			
		     <form:checkbox path="salaryHeadDTO.pfEmployeeFlag" id="pfEmployeeFlag"  value="1"/></td>
			 <td width="35" height="30"><label>E.S.I. Employee Flag</label></td><td width="35">			 
			 <form:checkbox path="salaryHeadDTO.esiEmployeeFlag" id="esiEmployeeFlag"  value="1" /></td>
			 </tr>
			 
				
				
				<tr>
					<td width="145" height="26" align="left"><label>
							Description&nbsp;</label></td>
					<td height="26" colspan="3"><form:input
							path="salaryHeadDTO.description"  style="width:89%;" readOnly="true" size="94"
							id="description" /></td>
				</tr>
			</table>
			<div class="btn">
				<c:url value="remove_salaryHead" var="remove_url">
					<c:param name="salaryHeadId"
						value="${salaryHeadForm.salaryHeadDTO.salaryHeadId}"></c:param>
				</c:url>
				<div class="btn">
					<a href="${remove_url}" class=removebtn iconCls="icon-remove">
					</a> <a href="get_salaryHead_list" class="cancelbtn"
						iconCls="icon-cancel"> </a>
				</div>
			</div>
		</div>
	</form:form>
</c:if>
