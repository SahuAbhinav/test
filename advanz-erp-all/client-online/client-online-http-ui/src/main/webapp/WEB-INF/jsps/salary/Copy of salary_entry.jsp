<%@page import="java.util.ArrayList"%>
<%@page import="com.advanz.erp.masters.model.EmployeeDTO"%>
<%@page import="java.util.List"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<c:if test="${not empty(errors)}">
	<input type="hidden" id="errorId" value="${errors.errorMsg}">
	<script type="text/javascript">
		$(document).ready(function() {
			var errorId = document.getElementById('errorId');
			alert(errorId.value);
		});
	</script>
</c:if>

<c:if test="${opr=='R'}">
	<script type="text/javascript">
		var redrctUrl = 'get_salary_list';

		$(document).ready(function() {
			alert($("#salaryTranAutoNo").val());
			//	confirm('Are you sure you want to delete?');
			var delUrl = 'remove_salary?id=' + $("#salaryTranAutoNo").val();
			if (confirm('Are you sure you want to delete?')) {
				window.self.location = delUrl;
			} else {
				window.self.location = redrctUrl;
			}
		});
	</script>
</c:if>
<c:if test="${duplicateMsg !=null}">
  <script type="text/javascript">
   
  	$(document).ready(function() {
      alert('You cannot add more than 1 record');
    
	});
 	</script>
</c:if>

<c:if test="${opr=='V' && opr!=null }">
	<script type="text/javascript">
		$(document).ready(function() {
	
		$('input').attr('readonly','readonly');
		$('select').attr('disabled','disabled');
		$('textarea').attr('readonly','readonly');
		$('.datepicker').attr('disabled','disabled');
		$('input:radio').attr('disabled',true);
		$("input:checkbox").attr("disabled", 'disabled');
		$(".newWindow1").prop("disabled", true);
		$('.datepicker1').attr('disabled','disabled');
		$(".newWindow img").removeAttr('src');	
		$('.getPurchaseOrderItem').attr('disabled','disabled');
		});
</script>
</c:if>

<c:if test="${opr=='E' && opr!=null }">
	<script type="text/javascript">
		$(document).ready(function() {
		
		$('#mothName').attr('disabled','disabled');
		$('#departmentId').attr('disabled','disabled');
		 
		});
</script>
</c:if>
<script type="text/javascript">
function checkApproved(){
	alert("You can not edit / delete this record as it is already approved.");
	return false;
}
	function editMethod()
 	 { 
 	 var frank_param = $('#salaryTranAutoNo').val();
 	 //	confirm('Are you sure you want to delete?');
	 var delUrl='get_salary?salaryTranAutoNo='+frank_param+'&opr=E';
 	 window.self.location = delUrl;  
	}
</script>
<script type="text/javascript">
	$(function() {
		$('#aprovedId').click(function() {
		var staus=	$("#aprovedId").attr("checked");
				
		if(staus=='checked') {
			alert('Are you sure about approving the record, as you will not be able to edit / delete it after approval.');
		}
		});		
				
		});
</script>


<script type="text/javascript">
function getInt(obj) {
	var val = 0;
	if (obj) {
		if (obj == '') {
			obj = 0;
		}
		val = parseInt(obj);
	}
	return val;
}
function getFloat(obj) {
	if (obj) {
		if (obj == '') {
			obj = 0;
		}
		return parseFloat(obj);
	} else {
		return 0;
	}
    }

	$(document).ready(function() {

		
						 var  round = function(value, precision) {
				            var result = Number(value);
				            if (typeof precision == 'number') {
				                precision = Math.pow(10, precision);
				                result = Math.round(value * precision) / precision;
				            }
				            return result;
				           }; 
						
					 });
</script>


<script type="text/javascript">

/* $(function() {
	$('#aprovedId').click(function() {
	var staus=	$("#aprovedId").attr("checked");
			
	if(staus=='checked') {
		alert('Are you sure about approving the record, as you will not be able to edit / delete it after approval.');
	}
	});		
			
	}); */


	$(document).ready(function() {

							$('#fixmyheader-8').fixheadertable(
								{
									caption : 'My header is fixed !',
									height : 300,
									addTitles : true,
									colratio : [ '10%', '10%', '8%', '50px',
											'auto', 'auto', '30%', 'auto' ]
								});
					});
</script>
<style type="text/css">
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
</style>

<script>
	(function($) {
		$.fn.currencyFormat = function() {
			this.each(function(i) {
				$(this).change(function(e) {
					if (isNaN(parseFloat(this.value)))
						return;
					this.value = parseFloat(this.value).toFixed(2);
				});
			});
			return this; //for chaining
		}
	})(jQuery);

	// apply the currencyFormat behaviour to elements with 'currency' as their class
	$(function() {
		$('.quantity').currencyFormat();
	});

	$(document).ready(function() {
		$(".quantity").each(function() {
			if ($(this).val() == null || $(this).val() == "") {
				$(this).val(0);
			}
			var v = parseFloat($(this).val());

			v = v.toFixed(2);

			$(this).val(v);

		});
	});

	
		
	(function($) {
		//function is called when the page is fully loaded
		$(document).ready(function() {
			//the page is loaded so attach the time picker to an input field
			$(".myTimePicker").timepicker({});
		});
	})(jQuery);
</script>

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
	$(document).ready(
			function() {
				//called when key is pressed in textbox
				$(".digitOnly").keypress(
						function(e) {
							//if the letter is not digit then display error and don't type anything
							if (e.which != 8 && e.which != 46 && e.which != 0
									&& (e.which<48 || e.which>57)) {
								//display error message
								$(".errmsg").html("Digits Only").show()
										.fadeOut("slow");
								return false;
							}
						});

			});
	
</script>
<c:if test="${opr=='R'}">
	<script>
		$(document).ready(function() {
			$('input').attr('disabled', 'disabled');
			$('select').attr('disabled', 'disabled');
		});
	</script>
</c:if>



<script type="text/javascript">


	$(document).ready(function() {
		$("#formDate").datepicker({
			changeMonth : true,
			changeYear : true,
			yearRange : '-99:+10',
			dateFormat : 'dd-M-yy HH:mm:ss',
			maxDate : new Date()
		});
		
		
		
		$("button").button();
		$("#select5").attr("disabled", "disabled");
		$("#formnumber").attr("disabled", "disabled");
		$("#formDate").attr("disabled", "disabled");
		//$('input:text, input:password').button()   
		$(".datepicker").datepicker({
			changeMonth : true,
			changeYear : true,
			yearRange : '-99:+10',
			dateFormat : 'dd-M-yy hh:mm'

		});
		//      $(":submit").button()
	});

	
	$(document).ready(function() {

		function abc() {
			$(".datepicker2").datepicker({

				changeMonth : true,
				changeYear : true,
				yearRange : '-99:+10',
				dateFormat : 'dd-M-yy hh:mm',
				maxDate : $("#date").val()
			});
		}

	
	});

	
	$(document).ready(function() {
		$("#formReqFlag").click(function() {
			$("#select5").attr("disabled", "disabled");
			$("#formnumber").attr("disabled", "disabled");
			$("#formDate").attr("disabled", "disabled");
			$("#select5").val('');
			$("#formnumber").val('');
			$("#formDate").val('');
		});
		$("#formReqFlag1").click(function() {
			$("#select5").removeAttr("disabled");
			$("#formnumber").removeAttr("disabled");
			$("#formDate").removeAttr("disabled");
		});
	});
</script>
<style type="text/css">
p {
 color: blue;
}

/*input {
	width:87%;
	margin-bottom:6px;
	}*/
select {
	width: 87%;
	height: 22px;
}

table {
	width: 962px;
	!
	important
}

.tableview {
	border: 1px solid #7F9DB9;
	border-radius: 3px 3px 3px 3px;
	margin-bottom: 7px;
	margin-left: 5px;
	margin-top: 5px;
	padding: 7px;
	width: 961px;
	
}

.gridheadingdiv td {
	height: 22px;
}

.gridheadingdiv {
	width: 967px;
	!
	important
}
.getPurchaseOrderItem {
	background: url(static/images/search_small.png);
	background-repeat: no-repeat;
	height: 12px;
	border: none;
	width: 12px;
	cursor: pointer;
}
.newWindow {
	background: url(static/images/search_small.png);
	background-repeat: no-repeat;
	height: 12px;
	border: none;
	width: 12px;
	cursor: pointer;
}

.newWindow1 {
	background: url(static/images/search_small.png);
	background-repeat: no-repeat;
	height: 12px;
	border: none;
	width: 12px;
	cursor: pointer;
	cursor: pointer;
}

.errorblock {
	color: #000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	margin-bottom: 16px;
	width: 82%;
}
</style>

<% 
	int earningHeadListSize=0;
    int dedectionListSize=0;
	List emplList=(List)session.getAttribute("employeeList");
		if(emplList!=null){
			EmployeeDTO dto=(EmployeeDTO)emplList.get(0);
		
		%>
		<%
		List earnl=dto.getEarningHeadList(); 
		earningHeadListSize=	earnl.size();
	    
	    List dedecl=dto.getDedecationHeadList();
	    dedectionListSize=	dedecl.size();
	%>
	<input type="hidden" name="earningHeadListSize" id="earningHeadListSize" value="<%=earningHeadListSize%>">
	<input type="hidden" name="dedectionListSize" id="dedectionListSize" value="<%=dedectionListSize%>">
	<%
		}%>
<script type="text/javascript">
$(document).ready(function() {
  //Function with salary calculation formula which in call form on load and field on change	
	function formChange() {
	for ( var ele = 0; true; ele++) {
		var frm = document.forms[0];
		
		var billQty= frm.elements["employeeName"+ele];
	if (!billQty) {
		break;
	}
	var round = function(value, precision) {
		var result = Number(value);
		if (typeof precision == 'number') {
			precision = Math.pow(10, precision);
			result = Math.round(value * precision)
					/ precision;
		}
		return result;
	};
	
	
	
	
	var lTypeListSize=getInt($("#leaveTypeListSize").val());
	var laeveTypeId=(lTypeListSize-1)+""+getInt(ele);
	var payabelDays= $("#leaveType"+laeveTypeId).val();
	
	var totalDaysInMonth=$("#totalDaysInMonth").val();       
	// To earning Total
	var earningTotal=0;
	var earningTotalWiotCal=0;
	for(var i=0;i<=$("#earningHeadListSize").val()-1;i++){
		var v=getInt(i)+""+getInt(ele);
		
		    /* if($("#earningHeadActualR"+v).val()!='undefined'){
			var earnAmnt= getInt($("#earningHeadActualR"+v).val()); */
			
			if($("#earningHeadR"+v).val()!='undefined'){
			var earnAmnt= getInt($("#earningHeadR"+v).val());
			var oneDayAmnt=(earnAmnt/totalDaysInMonth);
			var payebelDaysAmnt=(oneDayAmnt*payabelDays);
			earningTotal +=payebelDaysAmnt;
			if($("#earningHeadR"+v).val()!=0){
			$("#earningHeadR"+v).val(round(payebelDaysAmnt,2));
			$("#earningAmntListR"+v).val($("#earningHeadR"+v).val());
			}}
		
		/* if($("#earningHeadActual"+v).val()!='undefined'){
		var earnAmnt1= getInt($("#earningHeadActual"+v).val());
		 */	
		 if($("#earningHead"+v).val()!='undefined'){
		 var earnAmnt1= getInt($("#earningHead"+v).val());
				
		 var oneDayAmnt1=(earnAmnt1/totalDaysInMonth);
			var payebelDaysAmnt1=(oneDayAmnt1*payabelDays);
			earningTotalWiotCal +=payebelDaysAmnt1;
			if($("#earningHead"+v).val()!=0){
				$("#earningHead"+v).val(round(payebelDaysAmnt1,2));
				$("#earningAmntList"+v).val($("#earningHead"+v).val());
			}
			
		}}
	
	var ev=getInt($("#earningHeadListSize").val())+""+getInt(ele);
	
	var earningTotal1= earningTotal+earningTotalWiotCal;
	
	$("#earningHead"+ev).val(round(earningTotal1,2));
	// To earning Total end
	
	// To Dedection Total
	var dedctionTotal=0;
	var dedctionTotalWiotCal=0;
	for(var j=0;j<=$("#dedectionListSize").val()-1;j++){
		var v=getInt(j)+""+getInt(ele);
		if($("#dedctionHead"+v).val()!='undefined'){
			var deductAmnt= getInt($("#dedctionHeadActual"+v).val());
			var oneDayAmnt=(deductAmnt/totalDaysInMonth);
			var payebelDaysAmnt=(oneDayAmnt*payabelDays);
			dedctionTotal +=payebelDaysAmnt;
			
			if($("#dedctionHead"+v).val()!=0){
				$("#dedctionHead"+v).val(round(payebelDaysAmnt,2));
				$("#dedectionAmntList"+v).val($("#dedctionHead"+v).val());
			}}
		if($("#dedctionHead1"+v).val()!='undefined'){
			
			var deductAmnt1= getInt($("#dedctionHead1"+v).val());
			var oneDayAmnt1=(deductAmnt1/totalDaysInMonth);
			var payebelDaysAmnt1=(oneDayAmnt1*payabelDays);
			dedctionTotalWiotCal +=payebelDaysAmnt1;
			
			
			if($("#dedctionHead1"+v).val()!=0){
				$("#dedctionHead1"+v).val(round(payebelDaysAmnt1,2));
				$("#dedectionAmntList"+v).val($("#dedctionHead1"+v).val());
			}
			
			//dedctionTotalWiotCal +=getInt($("#dedctionHead1"+v).val());
		}}
	var devar=getInt($("#dedectionListSize").val())+""+getInt(ele);
	//dedctionTotal=dedctionTotal+dedctionTotalWiotCal;
	var netDeducTotal= dedctionTotal+dedctionTotalWiotCal;
	$("#dedctionHead"+devar).val(round(netDeducTotal,2));
	// To Dedection Total end
	
	var deductValAmount=	$("#dedectAdvanceAmnt"+ele).val();
	//Set Net Amount    earningTotal 
	$("#netAmount"+ele).val(round(earningTotal1+netDeducTotal-deductValAmount,2));
	//$("#netAmount"+ele).val(earningTotal-netDeducTotal-deductValAmount);
	}}
	
	
$('#formID').change(function() {
	formChange();
});
	formChange();
});	


$(document).ready(function() {
	
    $("#myAsyncBtn").click(function() {
	var flag=false;
	$.ajax({

		type : "POST",
		url : "checkEntryExiste",
		data : "departmentId=" + $('#departmentId').val()+"&mothName="+$("#mothName").val(),

		success : function(response) {
			if(response.result==true){
				alert('Sorry you can not save because salary allready genrated to this department for this month');	
				flag=false;
				return flag;
			}if(response.result==false){
				flag=true;
			return flag;
			}
		}
 });
	alert('flag:'+flag);
	if(flag==''){}
	return flag;
    });
    });
</script>

<script type="text/javascript">
  var delUrl='get_employee_by_deaprtment';
	$(document).ready(function() {
		  $("#departmentId").change(function() {
			  if($("#mothName").val()==''){
				  alert('Please select month');
				  return false;
			  }
			  window.self.location = delUrl+"?departmentId="+$("#departmentId").val()+"&mothName="+$("#mothName").val();
	  });
   });
	
</script>
 	
 	
<form:form name="input" id="formID" action="saveSalary"  modelAttribute="salaryMasterForm"> 
<form:hidden path="salaryMasterDTO.salaryTranAutoNo" id="salaryTranAutoNo"/>
<form:hidden path="salaryMasterDTO.totalDaysInMonth" id="totalDaysInMonth"/>
<form:hidden path="salaryMasterDTO.salaryYear" />
<form:hidden path="operation"/>

	<div class="panel-header" style="width: 960px;">
		<div class="panel-title">Employee Salary Entry</div>
		<div class="panel-tool"></div>
	</div>

	<div align="left" class="bkgColor"
		style="height: auto; padding-bottom: 14px; width: 969px;">

		<table height="60" width="967" class="tableview" border="0"
			style="margin-top: 12px;">
			 <tr>
	<td width="80" >Salary Month <span style="color: #FF0000">*</span></td>
	<td   width="80"><form:select path="salaryMasterDTO.salaryMonth" class="validate[required] text-input" id="mothName" cssStyle="width:120px;">
	<form:option value=""></form:option>
	<form:option value="JANUARY">JANUARY</form:option>
	<form:option value="FEBRUARY">FEBRUARY</form:option>
	<form:option value="MARCH">MARCH</form:option>
	<form:option value="APRIL">APRIL</form:option>
	<form:option value="MAY">MAY</form:option>
	<form:option value="JUNE">JUNE</form:option>
	<form:option value="JULY">JULY</form:option>
	<form:option value="AUGUST">AUGUST</form:option>
	<form:option value="SEPTEMBER">SEPTEMBER</form:option>
	<form:option value="OCTOBER">OCTOBER</form:option>
	<form:option value="NOVEMBER">NOVEMBER</form:option>
	<form:option value="DECEMBER">DECEMBER</form:option>
	</form:select>
	</td>
	<td width="80" >Department <span style="color: #FF0000">*</span></td>
    <td width="80">
    <form:select  path="salaryMasterDTO.departmentId" readonly="true"  cssStyle="width:120px;" id="departmentId" >
      <form:option value="0" >All</form:option>
	  <form:options items="${deptTypeList}" itemLabel="name" itemValue="mastersId" />
      </form:select>
      </td>
      <td width="80">Approved</td>
      <td width="163"><form:checkbox path="salaryMasterDTO.approvedFlag" value="1" style="width:1%" size="11" id="aprovedId" /></td>
    </tr>
		</table>
		<div class="gridheadingdiv">
			<table width="668" class="fixmyheader" id="fixmyheader-8">
				<thead>
					<tr><c:forEach items="${empList}" var="e" varStatus="s">
						<td width="120"><div align="center">
								<strong><c:out value="${empList[s.index]}"/></strong>
							</div>
						</td></c:forEach>
				</tr>
				</thead>
				<tbody>
				
				
				<%-- 
				<c:forEach var="window" items="${employeeList}" varStatus="loopCounter" >
   <c:out value="outer loop count: ${loopCounter.getEmployeeFullName()}"/> 
   <c:forEach var="window" items="${employeeList.earningHeadList}" varStatus="loopCounter" > 
      <c:out value="${empList[s.index]}"/>
   </c:forEach>
</c:forEach> --%>
				
				<% 
			    List employeeList=(List)session.getAttribute("employeeList");
				List advanceAmtList=null;
				List dedectAdvanceAmntList=null;
				try{
				 advanceAmtList=(List)session.getAttribute("advanceAmtList");
				 dedectAdvanceAmntList=(List)session.getAttribute("dedectAdvanceAmntList");
				}catch(Exception ex){
					ex.printStackTrace();
				}
				
				try{
				if(employeeList!=null && employeeList.size()>0){
				for(int i=0;i<employeeList.size();i++){
					EmployeeDTO dto=(EmployeeDTO)employeeList.get(i);
				
				%>
				
				  <%-- <c:forEach items="${empList}" var="e" varStatus="s"> --%>
			      <tr>
						<td width="110"><div align="center">
								<strong>
								<input type="hidden" value="<%=dto.getEmployeeFullName() %>" name="salaryMasterDTO.employeeNameList[<%=i%>]" id="employeeName<%=i %>" readonly="readonly">
								<input type="hidden" value="<%=dto.getEmployeeId() %>" name="salaryMasterDTO.employeeIdList[<%=i%>]" id="employeeId<%=i %>" readonly="readonly">
								<%=dto.getEmployeeFullName() %></strong>
						</div>
						</td>	
                         <td width="110"><div align="center">
								<strong><%=dto.getEmployeeCode() %></strong>
						</div>
						</td><td width="110"><div align="center">
								<strong style="width:100%"><input type="hidden" value="<%=dto.getDesignation()%>" name="salaryMasterDTO.departmentIdList[<%=i%>]" id="departmentId<%=i %>" readonly="readonly">
								<%=dto.getDesignationName()%></strong>
						</div>
						</td>
						
						 <%
						List ltlist=dto.getLeaveTypeList(); 
						 int leaveTypeListSize=ltlist.size();
						for(int j=0;j<ltlist.size();j++){
						%>
						<td width="110"><div align="center">
						<input type="hidden" name="leaveTypeListSize" value="<%=leaveTypeListSize%>" id="leaveTypeListSize">
						<strong>
						<input type="text" value="<%=ltlist.get(j) %>" name="leaveType<%=j+""+i %>" id="leaveType<%=j+""+i %>" readonly="readonly">
						</strong>
						</div>
						</td>
						<%} %>
						<%
						List earningheadTyelist=dto.getEarningheadTypeList();
						List list=dto.getEarningHeadList(); 
						int earnListSize= list.size();
						for(int k=0;k<list.size();k++){
							int m=i+k;
						%>
						<td width="110"><div align="center">
						<strong>
						
						  <% if(earningheadTyelist.get(k).equals("variable")){%>
						 <input style="width:100%" type="text" value="<%=list.get(k) %>" name="earningHead<%=k+""+i %>" id="earningHead<%=k+""+i %>"> 
						
						<input style="width:100%" type="hidden" value="<%=list.get(k) %>" name="earningHeadActual<%=k+""+i %>" id="earningHeadActual<%=k+""+i %>" readonly="readonly"> 
						 <input type="hidden" name="salaryMasterDTO.temp[<%=i %>].tempList[<%=k %>]"  id="earningAmntList<%=k+""+i %>"> 
						<%} else{%>
						 <input style="width:100%" type="text" value="<%=list.get(k) %>" name="earningHeadR<%=k+""+i %>" id="earningHeadR<%=k+""+i %>" readonly="readonly"> 
						
						
						<input style="width:100%" type="hidden" value="<%=list.get(k) %>" name="earningHeadActualR<%=k+""+i %>" id="earningHeadActualR<%=k+""+i %>" readonly="readonly"> 
						 <input type="hidden" name="salaryMasterDTO.temp[<%=i %>].tempList[<%=k %>]"  id="earningAmntListR<%=k+""+i %>"> 
						<%} %>   
						
						<%--  <input style="width:100%" type="text" value="<%=list.get(k) %>" name="earningHead<%=k+""+i %>" id="earningHead1<%=k+""+i %>" readonly="readonly">
						 <input style="width:100%" type="hidden" value="<%=list.get(k) %>" name="earningHeadActual<%=k+""+i %>" id="earningHeadActual1<%=k+""+i %>" readonly="readonly"> 
						 <input type="hidden" name="salaryMasterDTO.temp[<%=i %>].tempList[<%=k %>]"  id="earningAmntList<%=k+""+i %>"> 
 --%>						
						</strong>
						</div>
						</td>
						<%} %>
						
						<td width="110"><div align="center">
						<strong><input type="text" name="salaryMasterDTO.earningTotalAmntList<%=i %>" id="earningHead<%=earnListSize+""+i %>" readonly="readonly"></strong>
						</div>
						</td>
						<%
						List deductionheadTyelist=dto.getDeductionheadTypeList();
						List dList=dto.getDedecationHeadList(); 
						int dedcListsize=dList.size();
						for(int j=0;j<dList.size();j++){
						%>
						<td width="110"><div align="center">
						<strong>
						<%-- <% if(deductionheadTyelist.get(j).equals("variable")){ %>
						<input type="text" value="<%=dList.get(j) %>" name="dedctionHead<%=j+""+i%>" id="dedctionHead1<%=j+""+i%>" > 
						<%}else{ %>
							<input type="text" value="<%=dList.get(j) %>" name="dedctionHead<%=j+""+i%>" id="dedctionHead<%=j+""+i%>" readonly="readonly">
							<%}%> --%>
							
							<input type="text" value="<%=dList.get(j) %>" name="dedctionHead<%=j+""+i%>" id="dedctionHead<%=j+""+i%>" readonly="readonly">
							<input type="hidden" value="<%=dList.get(j) %>" name="dedctionHeadActual<%=j+""+i%>" id="dedctionHeadActual<%=j+""+i%>" >
						    <input type="hidden" name="salaryMasterDTO.tempDedect[<%=i %>].tempDedectList[<%=j %>]" value=""  id="dedectionAmntList<%=j+""+i %>">
						
						</strong>
						</div>
						</td>
						<%} %>
						<td width="110"><div align="center">
						<strong>
						<input type="text" name="salaryMasterDTO.dedectionTotalAmntList[<%=i%>]" id="dedctionHead<%=dedcListsize+""+i%>" readonly="readonly">
						<%-- <input type="text" name="dedctionHead<%=dedcListsize+""+i%>" id="dedctionHead<%=dedcListsize+""+i%>"> --%></strong>
						</div>
						</td>
						<td width="110"><div align="center">
						<strong><input type="text" name="salaryMasterDTO.balAdvanceList[<%=i%>]" value="<%=advanceAmtList.get(i) %>" id="balAdvance<%=i%>" readonly="readonly"/></strong>
						</div>
						</td>
						<td width="110"><div align="center">
						<strong><input type="text" name="salaryMasterDTO.dedectAdvanceAmntList[<%=i%>]" value="<%=dedectAdvanceAmntList.get(i) %>" id="dedectAdvanceAmnt<%=i%>"></strong>
						</div>
						</td>
						<td width="110"><div align="center">
						<strong><input type="text" name="salaryMasterDTO.netAmntList[<%=i%>]" id="netAmount<%=i%>" readonly="readonly">
					<%-- 	<input type="hidden" name="salaryMasterDTO.salaryHeadAmntList[<%=i%>]" id="salaryHeadAmntList<%=i%>"> --%>
						</strong>
						</div>
						</td>
						
						</tr><%}}
				}catch(Exception e){
					e.printStackTrace();
				}
				%>
					<%-- </c:forEach> --%>

					</tbody>
				    </table>
			        </div>
			        <div class="btn" style="width: 300px;" >
			        
			    <c:if test="${salaryMasterForm.salaryMasterDTO.salaryTranAutoNo==0 || salaryMasterForm.salaryMasterDTO.salaryTranAutoNo==null}">    
			<div class="savbtn">
<input class="submit"
									style="font-size: 11px;  font-weight: bold; padding: 0 0 0 30px;"
									type="submit" value=" " id="myAsyncBtn" />
									 <a href="get_salary_list"  class="cancelbtn" ></a>
									</div>
									
									</c:if>
									
									
									
	<c:if test="${salaryMasterForm.salaryMasterDTO.salaryTranAutoNo>0}">
   <c:if test="${opr=='E'}">
   <input class="updatebtn" type="submit" value=" " />
   <a href="get_salary_list"  class="cancelbtn" ></a>
   </c:if>
    <c:if test="${opr=='V'}">
	<c:forEach items="${roleAndRightsDTOList}" var="roleAndRights">
		   <c:if test="${roleAndRights.menuId==sessionScope.menuId}">
    	  	 <c:if test="${roleAndRights.editFlag=='true'}">
		  	 <input class="edit_btn"  type="button" onclick="editMethod();" value=""/>
    	      <a href="get_salary_list"  class="cancelbtn" ></a>  
		     </c:if>
		      <c:if test="${roleAndRights.editFlag=='false'}">
		      <input class="edit_btn"  type="button" onclick="checkEdit();" value=""/>
    	      <a href="get_salary_list"  class="cancelbtn" ></a>  
		      </c:if>
		    
          </c:if>
          </c:forEach></c:if>	</c:if>
									</div>
		
	</div>

</form:form>





