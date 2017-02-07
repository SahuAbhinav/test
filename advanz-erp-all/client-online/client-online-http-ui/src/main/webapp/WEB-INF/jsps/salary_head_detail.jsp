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
.error {
	color: #ff0000;
}
.scrolltable {
overflow: hidden;
}
.scrolltable:hover {
    overflow-y: scroll;
}
#radiobutton lable{
margin-top: -11px;
}
</style>
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
<c:if test="${not empty(errorList)}">
	<div class="errorblock">
		<ul>
			<c:forEach items="${errorList.errorList}" var="err">
				<li>${err.errorMsg}</li>
			</c:forEach>
		</ul>
	</div>
</c:if>

<form:form name="input" id="formID" action="save_salary_Head"
	method="post" modelAttribute="salaryHeadForm">

	<form:errors path="*" cssClass="errorblock" element="div" />

	<div class="panel-header">
		<div class="panel-title">Salary Head Master</div>
		<div class="panel-tool"></div>
	</div>

	<div align="left" class="bkgColor">
		<table class="" width="743" height="102" border="0"  >
			<tr>
				<td width="60" height="30" align="left"><label> Head
			  Name<span style="color: #FF0000">*</span> </label>			  </td>
				<td width="143"><form:input data-maxsize="55" style="width:91%"  onkeypress="return check(event)"	class="validate[required] text-input"  
						path="salaryHeadDTO.salaryHeadName" size="28" id="salaryHeadName" />			  </td>
			  <td width="123" height="30"><label> Code<span
						style="color: #FF0000">*</span> </label>			  </td>
			  <td width="363"><form:input data-maxsize="16" onkeyup="valid1(this)" onblur="valid1(this)"  
						class="validate[required] text-input"
						path="salaryHeadDTO.salaryHeadCode" style=" padding-left: 3px;  width: 27%;" size="24" id="code" />			  </td>
			</tr>
			<tr>
				<td width="60" height="30" align="left"><label> Head Type<span style="color: #FF0000">*</span> </label>			  </td>
				<td width="143">
				<div id="radiobutton" 	style="border: solid 1px; height: 20px; width: 91%;  border-radius: 3px 3px 3px 3px; border-color: #7f9db9; background-color: #FFF;">
				<span style="    float: left;    margin-top:2px;    padding-left: 12px;"> Earning</span>
				 <form:radiobutton   class="validate[required] radio"  style="width:10px; float: left; "
							path="salaryHeadDTO.headType" value="E" id="headType" onclick="getHeadSeq(this);"></form:radiobutton>	
							<span style="    float: left;    margin-top: 2px;  "> Deduction</span>		
				<form:radiobutton  
							class="validate[required] radio"  style="width:20px;" path="salaryHeadDTO.headType" onclick="getHeadSeq(this);"
							value="D" id="headType"></form:radiobutton>   
					</div>		
							</td>
							
			  <td   height="30"><label>Type<span
						style="color: #FF0000">*</span> </label>			  </td>
			  <td width="143">
			  <form:select style="width: 28%; height: 21px"
						path="salaryHeadDTO.type" id="type">							 
             <form:option value="constant">Constant</form:option>
             <%-- <form:option value="slab">Slab</form:option> --%>
             <form:option value="variable">Variable</form:option>
				</form:select>		  	  </td>
			</tr>
			<tr>
			<td width="60" height="30" align="left" ><label> Base Head </label></td>
			<td width="100" rowspan="3" align="top">
			<div class="scrolltable" Style="height: 86px; width: 163px; background: none;"><table width="175" style="width: 146px;" cellpadding="0" cellspacing="0" style="float:left; border:1px solid #7F9DB9;">
			  <c:forEach items="${salaryHeadList}" var="cat" varStatus="leave">
                <tr>                  
                  <th style="border-bottom:1px solid #99BBE8; padding-left: 3px;" width="587"><c:out value="${cat.salaryHeadName}"/></th>
                  <th style="border-bottom:1px solid #99BBE8;border-left:1px solid #99BBE8;  border-right:1px solid #99BBE8; "  width="67"><form:checkbox  path="salaryHeadDTO.baseHeadIds" id="type${leave.index}"  value="${cat.salaryHeadId}" onclick="check(${leave.index});" /></th>
                </tr></c:forEach></table></div>
               </td>
			 <td width="60" height="30"><label> Base Head  % </label>			  </td>
			  <td width="143"><form:input data-maxsize="16" class="quantity" onkeypress="return check(event)" 
						
						path="salaryHeadDTO.baseHeadPer" size="24" style=" padding-left: 3px;  width: 27%;" id="baseHeadPer" disabled="true" />			  </td>
			</tr>
			<tr>
			<td></td><td  height="30" align="left"><label>Payable Type<span style="color: #FF0000">*</span> </label>			  </td>
			<td width="143">
			<form:select style="width: 28%; height: 21px" path="salaryHeadDTO.payableType" id="payableType">
			<form:option value="monthly">Monthly</form:option>
            <form:option value="quarterly">Quarterly</form:option>
			<form:option value="halfYearly">Half Yearly</form:option>
            <form:option value="yearly">Yearly</form:option>
			</form:select></td>
			</tr>
			<tr>
			<td></td><td  height="30" align="left"><label>Payable Month<span style="color: #FF0000">*</span> </label>			  </td>
			<td width="143">
			 <form:select style="width: 28%; height: 21px" path="salaryHeadDTO.payableMonth" id="payableMonth">
			 <form:option value="January">January</form:option>
             <form:option value="February">February</form:option>
             <form:option value="March">March</form:option>
             <form:option value="April">April</form:option>
             <form:option value="May"> May</form:option>
             <form:option value="June">June</form:option>
             <form:option value="July">July</form:option>
             <form:option value="August">August</form:option>
             <form:option value="September">September</form:option>
             <form:option value="October">October</form:option>
             <form:option value="November">November</form:option>
             <form:option value="December">December</form:option>
			 </form:select>		  	  </td>
			 </tr>
			 <tr><td>Salary Head Sequence</td><td><form:input path="salaryHeadDTO.salaryHeadSequence" style="text-align:right; width:91%"  class="validate[custom[number]]" onkeypress="return check(event);" size="110" id="sequenceNo" /></td>
			 
			 <td height="30" align="left"><label>Professional Tax </label>			
		     <form:checkbox path="salaryHeadDTO.professionalTaxFlag" id="professionalTaxFlag"  value="1"/></td>
			 <td width="35" height="30"><label> Basic Salary </label>			 
			 <form:checkbox path="salaryHeadDTO.basicSalaryFlag" id="basicSalaryFlag"  value="1" /></td>
			 </tr>
			 
			 <tr>
			 <td height="30" width="35" align="left" nowrap="nowrap"><label>P. F. Flag</label></td><td width="35">			
		     <form:checkbox path="salaryHeadDTO.pfEmployeeFlag" id="pfEmployeeFlag"  value="1"/>&nbsp;&nbsp;<label style="padding-left: 20px; ">E.S.I. Flag</label>&nbsp;			 
			 <form:checkbox path="salaryHeadDTO.esiEmployeeFlag" id="esiEmployeeFlag"  value="1" /></td>
			 </tr>
			 
			 <tr>
			 <td width="60" height="26" align="left"><label>
			  Description&nbsp;</label>			  </td>
				<td height="26" colspan="3"><form:input	path="salaryHeadDTO.description" style="width:60%" data-maxsize="150" size="110" id="description" />				</td>
			</tr>
		</table>
		<div class="btn">
			<div class="savbtn">
				<input class="submit" type="submit" value="" />
			</div>
			<div >
				<a href="get_salaryHead_list" class="cancelbtn" iconCls="icon-cancel"> </a>
			</div>
		</div>
		<div ><span style="margin-left:12px;" class="errmsg"></span></div>

	</div>



</form:form>