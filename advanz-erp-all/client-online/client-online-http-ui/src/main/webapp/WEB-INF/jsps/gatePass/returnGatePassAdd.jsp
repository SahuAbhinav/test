<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<html>

  <title>Gate Pass Number</title>
  	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<script type="text/javascript">
  //anonymous self invoking function to avoid conflicting with other JavaScript
   (function ($){
    //function is called when the page is fully loaded
     $(document).ready(function(){
       //the page is loaded so attach the time picker to an input field
     $("#returnTime").val($("#timeId").val());
       $(".myTimePicker").timepicker({});
    });
  })(jQuery);
  
</script>    
	
	<script>
		jQuery(document).ready(function(){
			// binds form submission and fields to the validation engine
			jQuery("#formID").validationEngine();
		});

		function checkHELLO(field, rules, i, options){
			if (field.val() != "HELLO") {
				// this allows to use i18 for the error msgs
				return options.allrules.validate2fields.alertText;
			}
		}
	</script>
    
    <script type="text/javascript">
	function edit(index) {
		
		var frm1 = document.forms[0];
		var ind = parseInt(index);
		frm1.elements["indexNo"].value = ind;
	}

    
$(document).ready(function(){ 
    //called when key is pressed in textbox
	$(".quantity").keypress(function (e)  
	{ 
	  //if the letter is not digit then display error and don't type anything
	  if( e.which!=8 && e.which!=46 && e.which!=0 && (e.which<48 || e.which>57))
	  {
		//display error message
		$(".errmsg").html("Digits Only").show().fadeOut("slow"); 
	    return false;
      }	
	});

  });
   
  </script>
  
  <c:if test="${operation=='Edite' }">
	<script type="text/javascript">
		$(document).ready(function() {
		$('.datepicker1').attr('disabled','disabled');
		});
	</script>
	</c:if>
	
	
<c:if test="${returnGetPass.returnGetPassMasterDTO.approved=='1'}">
<script type="text/javascript">
$(function() {
		 $('.searchbtn1').attr('disabled','disabled');
	 });
  </script>
  </c:if>
 <script type="text/javascript">
      $(document).ready(function()
       {
    	  var l=$('#lastDate').val();
       $("button").button();
		$( ".datepicker1" ).datepicker({ dateFormat: 'dd-mm-yy' , minDate: new Date(l), maxDate: '+6M +0D'	 });
		
		 if($('#itemCountId').val()>0){
			 $('#partyId').attr('disabled','disabled');	
			 
			 }
			 else{
				 $('#partyIdHidden').attr('disabled','disabled');	
			    }
      });
  </script>
  
  <c:if test="${operation=='Delete'}">
	<script type="text/javascript">
	
	 $(document).ready(function() {
			var delUrl = 'submitReturnGetPassList?operation=Delete&returnGatePassAutoId=' + $("#gatePassAutoId").val();
			if (confirm('Are you sure you want to delete?')) {
				window.self.location = delUrl;
			} else {
				window.self.location = 'show_return_get_pass_list?operation=show';
			}
			
			
			
		});
</script></c:if>
     <script type="text/javascript">
	$(document)
			.ready(
					function() {

						$("button").button();
						$("#lightness")
								.click(
										function() {
											$('#link')
													.attr('href',
															'jquery-ui/css/ui-lightness/jquery-ui-1.8.4.custom.css');
										});
						$("#hotsneaks")
								.click(
										function() {
											$('#link')
													.attr('href',
															'jquery-ui/css/hot-sneaks/jquery-ui-1.8.4.custom.css');
										});
						$("#flick")
								.click(
										function() {
											$('#link')
													.attr('href',
															'jquery-ui/css/flick/jquery-ui-1.8.4.custom.css');
										});
						$("#redmond")
								.click(
										function() {
											$('#link')
													.attr('href',
															'jquery-ui/css/redmond/jquery-ui-1.8.4.custom.css');
										});
						$("#smoothness")
								.click(
										function() {
											$('#link')
													.attr('href',
															'jquery-ui/css/smoothness/jquery-ui-1.8.4.custom.css');
										});

						$('#fixmyheader-1').fixheadertable({
							caption : 'My header is fixed !',
							zebra : true
						});

						$('#fixmyheader-2').fixheadertable({
							caption : 'My header is fixed !',
							height : 200,
							whiteSpace : 'normal'
						});

						$('#fixmyheader-3').fixheadertable({
							caption : 'My header is fixed !',
							height : 200
						});

						$('#fixmyheader-4').fixheadertable({
							caption : 'My header is fixed !',
							width : 400,
							height : 150
						});

						$('#fixmyheader-5').fixheadertable({
							caption : 'My header is fixed !',
							height : 200,
							minWidth : 840
						});

						$('#fixmyheader-6').fixheadertable({
							caption : 'My header is fixed !',
							height : 200,
							minWidthAuto : false
						});

						$('#fixmyheader-7').fixheadertable({
							caption : 'My header is fixed !',
							height : 200
						});

						$('#fixmyheader-8').fixheadertable(
								{
									caption : 'My header is fixed !',
									height : 150,
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

.tableview {
	border: 1px solid #7F9DB9;
	border-radius: 3px 3px 3px 3px;
	margin-bottom: 7px;
	margin-left: 5px;
	margin-top: 5px;
	padding: 7px;
	width: 961px;
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
<style type="text/css">


 
  p { color:blue; }
  .errmsg { color:red;
 
 }
	/*input {
	width:87%;
	margin-bottom:6px;
	}*/	
	select {
	width:100%;
	height:22px;
	}
	table {
	width:1008; !important}
	.gridheadingdiv td {
	height:22px;
	
	}
	.gridheadingdiv input {
    border: medium none;
    width: 70px;
}
.gridheadingdiv {
width:967px; !important
}
</style>

<c:if test="${operation=='V' && operation!=null }">
	<script type="text/javascript">
		$(document).ready(function() {
		$('input').attr('readonly','readonly');
		$('select').attr('disabled','disabled');
		$('textarea').attr('readonly','readonly');
		$('.datepicker1').attr('disabled','disabled');
		$('input:radio').attr('disabled',true);
		$("input:checkbox").attr("disabled", 'disabled');
		$(".drop1").prop("disabled", true);
		$('.myTimePicker').attr('disabled','disabled');
		});
</script>
</c:if>
<script>

function checkItem(){
	if($("#itemCountId").val()<1){
		alert("Sorry you can not save record without item.");
		return false;
	}
	for ( var ele = 0; true; ele++) {
		var frm = document.forms[0];
		var qt = frm.elements["returnGetPassMasterDTO.returnGetPassDetailDTOList[" + ele
		  					+ "].returnGatePassQuantity"];
		if (!qt) {
			break;
		}
		if (qt.value == '') {
			 alert('Return gate pass quantity can not be zero');
			 return false;
		}}
	return true;
}
function editMethod()
 { 
 var frank_param = $('#gatePassAutoId').val();
 //	confirm('Are you sure you want to delete?');
var delUrl='show_return_get_pass?returnGatePassAutoId='+frank_param+'&operation=Edite';
 window.self.location = delUrl;  
}

$(function() {
	
	$('#aprovedId').click(function() {
	var staus=	$("#aprovedId").attr("checked");
			
	if(staus=='checked') {
		alert('Are you sure about approving the record, as you will not be able to edit / delete it after approval.');
	}
	});		
			
	});
function checkApproved(){
	alert("You can not edit / delete this record as it is already approved.");
}
</script>
<input type="hidden" id="timeId" value="${returnGetPass.timeToshow}">
<body>
<form:form action="save_return_get_pass" method="POST" id="returnGetPassForm" commandName="returnGetPass" modelAttribute="returnGetPass">
  <div class="panel-header"  style="width:966px;">
 Return Gate Pass Entry Form
  <div class="panel-tool"></div>
  </div>
  <div align="left" class="bkgColor" style=" height:auto; padding-bottom:14px; width:966px;">
 <table  height="146" width="903"   border="0"   style="  margin-top:12px;">
 <form:hidden style="width:113px; height:21px" path="returnGetPassMasterDTO.returnGatePassAutoId" id="gatePassAutoId"></form:hidden>
 <form:hidden path="indexNo" id="indexId" />
 <form:hidden path="lastReturnGetPassDate" id="lastDate" />
 <tr>
<td width="86" height="33" nowrap="nowrap" >Branch Name<span style="color: #FF0000"></span></td>
<td colspan="1">
<form:select path="returnGetPassMasterDTO.branchDTO.branchId" id="branchId">
<form:options items="${branchList}" itemValue="branchId" itemLabel="branch" />
</form:select>
</td>
     
    </tr>
    <tr>
      <td height="29" nowrap="nowrap">Return Gate Pass Series <span style="color: #FF0000">*</span></td>
      <td width="268"><form:input size="11" path="returnGetPassMasterDTO.returnGetPassYearId" id="getPassYearId" data-maxsize="35" readonly="true" style="width:48%"/>
      <form:input size="11" readonly="true"   data-maxsize="35"  style="width:48%"  path="returnGetPassMasterDTO.returnGatePassId" />
      <form:hidden style="width:113px; height:21px" path="returnGetPassMasterDTO.returnGatePassId" id="returnGatePassId"></form:hidden>
       <form:hidden style="width:113px; height:21px" path="returnGetPassMasterDTO.finyr" id="finyr"></form:hidden>
        <form:hidden style="width:113px; height:21px" path="returnGetPassMasterDTO.transactionSeries" id="transactionSeries"></form:hidden>
      </td>
      <td width="182" nowrap="nowrap">Return Gate Pass Number<span style="color: #FF0000">*</span></td>
      <td width="250" colspan="1"><form:input path="returnGetPassMasterDTO.returnGatePassNumber" id="gatePassNumber"  style="width:100px" readonly="true" size="12"/></td>
      <td width="88" nowrap="nowrap">Return Gate Pass Date <span style="color: #FF0000">*</span></td>
      <td width="191"><form:input path="returnGetPassMasterDTO.returnGatePassDate" readonly="true"  class="datepicker1" id="date"  style="width:142px;" size="11" /></td>
    </tr>
    <tr>
      <td height="29">Return Gate Pass Received by</td>
      <td><form:textarea size="11"    data-maxsize="35"  style="width:265px; height:44px"  path="returnGetPassMasterDTO.returnGatePassReceivedBy"></form:textarea></td>
      <td>Gate Pass Return Time</td>
      <td><form:input path="returnGetPassMasterDTO.returnGatePassTime" class="myTimePicker" readonly="true" style="width:98%"  size="12" id="returnTime" /></td>
         <td height="29">Party Name<span style="color: #FF0000"></span></td>
      <td colspan="3">
      <form:hidden path="returnGetPassMasterDTO.partyDTO.partyId"
							id="partyIdHidden" /> 
      <form:select path="returnGetPassMasterDTO.partyDTO.partyId" style="width:142px;"
							id="partyId">
	<form:options items="${partyList}" itemValue="partyId"
								itemLabel="partyNameCity" />
						</form:select>
						
	</td>
   </tr>
    <tr>
   
      <td>Gate Pass No<input type="submit"
						class="searchbtn1" id="GatePassNoImageId" style="width: 35%; float: none;"
						name="operation" value="" onclick="this.value='GatePassNo';" /></td>
      <td><form:input path="returnGetPassMasterDTO.gatePassNumber" id="gatePassNumberId" style="width:100%" readonly="true" size="11" /></td>
    </tr>
  </table>
 		<div style="width: 906px; margin: 0 auto;">
				<span style="margin-left: 80px;" class="errmsg"></span>
			</div>
			<div class="gridheadingdiv">
				<table width="668" class="fixmyheader" id="fixmyheader-8">
					<thead>
      <tr>
        <td width="59"><div align="center"><strong>S No.</strong></div></td>
        <td width="270"><div align="center"><strong>Item Name</strong></div></td>
        <td width="187"><div align="center"><strong>UOM</strong></div></td>
        <td width="164"><div align="center"><strong> Quantity</strong></div></td>
        <td width="203"><div align="center"><strong>Remark</strong></div></td>
        <td width="46"><div align="center"><strong>Action</strong></div></td>
      </tr></thead>
      <tbody>
      <%
		int i = 0;
		%>
      <c:forEach items="${returnGetPass.returnGetPassMasterDTO.returnGetPassDetailDTOList}" var="e" varStatus="s">
      <tr>
     <td width="49">${s.count}</td>
      <td width="267">
      ${e.itemDTO.itemName}
      <form:hidden path="returnGetPassMasterDTO.returnGetPassDetailDTOList[${s.index}].itemDTO.itemId" value="${e.itemDTO.itemId}" class="quantity" style="  width:100%; border:1px solid #7f9db9; "/>
	<form:hidden path="returnGetPassMasterDTO.returnGetPassDetailDTOList[${s.index}].itemDTO.itemName" value="${e.itemDTO.itemName}" class="quantity" style="  width:100%; border:1px solid #7f9db9; "/>
										</td>
				<td width="182">
				${e.measurementUnitName}
				<form:hidden path="returnGetPassMasterDTO.returnGetPassDetailDTOList[${s.index}].measurementUnitName" value="${e.measurementUnitName}"/>
				<form:hidden path="returnGetPassMasterDTO.returnGetPassDetailDTOList[${s.index}].measurementUnitId" value="${e.measurementUnitId}"/>
				</td>						
										
      <td width="157"><form:input path="returnGetPassMasterDTO.returnGetPassDetailDTOList[${s.index}].returnGatePassQuantity" class="quantity" style="  width:100%; border:1px solid #7f9db9; "
										data-maxsize="15" size="8"/></td>
        
        <td width="195">
        <form:input path="returnGetPassMasterDTO.returnGetPassDetailDTOList[${s.index}].remark" style="  width:100%; border:1px solid #7f9db9; "
										data-maxsize="15" size="8" /></td>
      <td><input type="submit" class="drop1"
									style="height: 12px; width: 12px" name="operation" value=""
									onclick="edit('<c:out value="${s.index}"/>');this.value='remove';" /></td>
       <%
		i++;
		%>
      </tr>
      </c:forEach>
     </tbody>
    </table>
	
	
  </div>
 <table  height="40" width="1307"   border="0"   style="float:left;   margin-top:12px;">
      <tr>
        <td width="72" height="36"><strong>Item Count </strong></td>
        <td width="148"><input type="text" size="11"  value="<%=i %>" readonly="readonly" id="itemCountId" style="width:46%"  name="orderTakenBy2"></td>
        <td width="48"><strong>Purpose</strong></td>
        <td width="464"><form:input path="returnGetPassMasterDTO.returnGatePassPurpose" style="width:100%" size="11" />          <strong> </strong> </td>
           <td width="91"><strong>Approved </strong></td>
        <td width="159">
        <form:checkbox path="returnGetPassMasterDTO.approved"  value="1" style="text-align:right; border:1px solid #7f9db9;  width:100%" data-maxsize="15" size="8" id="aprovedId" />
      </tr>
    </table>
	<div class="btn">
	<c:if test="${returnGetPass.returnGetPassMasterDTO.returnGatePassAutoId ==null && operation!='V'}" >
   <div class="savbtn">
     <input class="submit" style="font-size: 11px; font-weight: bold;  padding: 0 0 0 30px;" type="submit" name="operation" value="Save" onclick="return checkItem(); this.value='Save'"/>
   </div>
   </c:if><c:if test="${returnGetPass.returnGetPassMasterDTO.returnGatePassAutoId > 0 && operation!='V'}" >
   <input class="updatebtn" style="font-size: 11px; font-weight: bold;  padding: 0 0 0 30px;" type="submit" name="operation" value="Save" onclick="return checkItem(); this.value='Save'"/>
   </c:if>
   
   <c:if test="${operation=='V' && operation!=null }">
  <c:choose><c:when test="${returnGetPass.returnGetPassMasterDTO.approved!='1'}">
   <input class="edit_btn"  type="button" onclick="editMethod();" value=""/>
 </c:when><c:otherwise>
 <input class="edit_btn"  type="button" onclick="checkApproved();" value=""/>
 </c:otherwise>
  </c:choose>
   </c:if>
   
   <div class="cancelbtn">
						<c:url value="show_return_get_pass_list?operation=show" var="remove_url">
							<c:param name="operation" value=""></c:param>
						</c:url>
						<a href="${remove_url}" class="cancelbtn"
							onclick="this.value='Cancel';"></a> <span
							style="margin-left: 80px;" class="errmsg"></span>
					</div>
 </div>
  </form:form>
  </body>
</html>