<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		var redrctUrl='show_party_form';
				
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
 		 var frank_param = getParam('partyId');
 		 //	confirm('Are you sure you want to delete?');
		 var delUrl='delete_party?partyId='+frank_param;
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
	//	$(document).ready(function() {
		function checkEdit()
 		{
 		alert('Login User Not Permit to Edit Record !!!!!!');
 		}
		
		
 	function editMethod()
 	 { 
 	 var frank_param = $('#partyId').val();
 	 //	confirm('Are you sure you want to delete?');
	 var delUrl='get_party?partyId='+frank_param+'&opr=E';
 	 window.self.location = delUrl;  
	}
 	</script>
 	

<c:if test="${opr=='V' }">
	<script type="text/javascript">
		$(document).ready(function() {
		$('input').attr('readonly','readonly');
		$('select').attr('disabled','disabled');
		$('textarea').attr('readonly','readonly');
		$('.datepicker').attr('disabled','disabled');
		$('input:radio').attr('disabled',true);
		$("input:checkbox").attr("disabled", true);
			
		});
</script>
</c:if>

<script type="text/javascript">    
$(document).ready(function(){ 
 function fixNumFormat(){
	$(".quantity").each(function(){		
		var v=parseFloat($(this).val());
		
		v=v.toFixed(2);
		if(v=='NaN')
			v='0.00';
		$(this).val(v);
	});
 }
 function fixNumFormat1(x){
			var v=parseFloat(x.val());			
			v=v.toFixed(2);
			if(v=='NaN')
				v='0.00';
			x.val(v);
		
	 }
 fixNumFormat();
    $(".quantity").change(function (e)  
    		{ 
    	 fixNumFormat1($(this));
    		});
});
</script>
<c:url value="delete_party" var="remove_url">
					<c:param name="partyId" value="${partyForm.partyDTO.partyId}"></c:param>
		</c:url> 
		<script>
	$(document).ready(function() {
	function fillPartyGroup(cb){
			$.get('get_partytype_group', { id: $(cb).val()}, function(data) {	
				//alert(data);
			$('#partyGroup').val(data);
			 
			});
			}
	$(function() {			
			$('#partyTypeId').change(function() {
				var v=$(this).val();
				fillPartyGroup(this);
		});
	});	
	fillPartyGroup($('#partyTypeId'));	
	});
</script>
<script>
	$(document).ready(function() {
	function fillBillingData(cb){
			$.get('get_csc_by_id', { id: $(cb).val()}, function(data) {		
				//		alert('hello');
						 $("#bstate").val(data.stateName);
						 $("#bcountry").val(data.countryName);
			});
			}
	$(function() {			
			$('#bcity').change(function() {
				var v=$(this).val();
				fillBillingData(this);
		});
	});	
	fillBillingData($('#bcity'));	
	});
</script>
<script>
	$(document).ready(function() {
	function fillShippingData(cb){
			$.get('get_csc_by_id', { id: $(cb).val()}, function(data) {		
				//		alert('hello');
						 $("#sstate").val(data.stateName);
						 $("#scountry").val(data.countryName);
			});
			}
	$(function() {			
			$('#scity').change(function() {
				var v=$(this).val();
				fillShippingData(this);
		});
	});	
	fillShippingData($('#scity'));	
	});
</script>


<script type="text/javascript">
  $(document).ready(function() {
			 
		$('#tabs, #tabs-3').tabs({
			  select: function(event, ui){
				  
				  if($('#partyGroup').val()=='Creditor')
					 {
					  $("#invoiceType1").hide();
					  $('#invoiceType1').attr('disabled','disabled');
					  $("#invoiceType").show();
					  $("#invoiceType").attr('disabled',false);
					 }
				  if($('#partyGroup').val()=='Debtor')
				  {
					  $("#invoiceType").hide();
					  $('#invoiceType').attr('disabled','disabled');
					  $("#invoiceType1").show();
					  $("#invoiceType1").attr('disabled',false);
				  } 
				 
			  }
			});
		
		
		$(".cancelbtn").click(function() {
			window.self.location = "show_party_form";
		});
	});
</script>

<c:if test="${opr=='R'}">
<script>
$(document).ready(function() {
	$('input').attr('disabled','disabled');	
	$('select').attr('disabled','disabled');	
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
	jQuery(document).ready(function() {
		// binds form submission and fields to the validation engine
		jQuery("#formID1").validationEngine();
	});

	function checkHELLO(field, rules, i, options) {
		if (field.val() != "HELLO") {
			// this allows to use i18 for the error msgs
			return options.allrules.validate2fields.alertText;
		}
	}
</script>

<script>
	jQuery(document).ready(function() {
		// binds form submission and fields to the validation engine
		jQuery("#formID2").validationEngine();
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
				$(".quantity").keypress(
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

	$(document).ready(
			function() {
				$(".quantity1").keypress(
						function(e) {
							//if the letter is not digit then display error and don't type anything
							if (e.which != 8 && e.which != 46 && e.which != 0
									&& (e.which<48 || e.which>57)) {
								//display error message
								$(".errmsg1").html("Digits Only").show()
										.fadeOut("slow");
								return false;
							}
						});
			});

	$(document).ready(function() {
		$(".quantity2").keypress(function(e) {
			//if the letter is not digit then display error and don't type anything
			if (e.which != 8 && e.which != 0 && (e.which<48 || e.which>57)) {
				//display error message
				$(".errmsg2").html("Digits Only").show().fadeOut("slow");
				return false;
			}
		});
	});

	$(document).ready(function() {
		$(".quantity3").keypress(function(e) {
			//if the letter is not digit then display error and don't type anything
			if (e.which != 8 && e.which != 0 && (e.which<48 || e.which>57)) {
				//display error message
				$(".errmsg3").html("Digits Only").show().fadeOut("slow");
				return false;
			}
		});
	});
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
</script>


 <script type="text/javascript">
      $(document).ready(function()
       {
           	   
	  $( ".datepicker" ).datepicker({
		  changeMonth: true,
          changeYear: true,
		  yearRange: '-99:+10',
		  dateFormat: 'dd-M-yy' });
  //   
	  $('.datepicker').attr('readonly','true');	
      });
      
     </script>


<script type="text/javascript">
	$(function() {
		$("#tabs").tabs();
	});
</script>

<!--<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="keywords" content="jquery,ui,easy,easyui,web">
	<meta name="description" content="easyui help you build your web page easily!">
	<title>jQuery EasyUI Demo</title>
	<link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/themes/icon.css">
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.6.1.min.js"></script>
	<script type="text/javascript" src="http://www.jeasyui.com/easyui/jquery.easyui.min.js"></script>-->
<style type="text/css">
body {
	font-family: Arial, Helvetica, sans-serif;
}

.ui-tabs .ui-tabs-nav li.ui-tabs-selected a,.ui-tabs .ui-tabs-nav li.ui-state-disabled a,.ui-tabs .ui-tabs-nav li.ui-state-processing a
	{
	background-color: #4e8ccf;
	color: #FFFFFF;
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
	width: 111px;
	text-align: center;
}

.tabs li.tabs-selected a.tabs-inner {
	background: none !important;
}

.ui-tabs .ui-tabs-nav li {
	border: 1px solid #4E8CCF !important;
	border-radius: 0 0 0 0;
	width: 135px;
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
	margin: 11px 0 16px;
	
}

.ui-tabs .ui-tabs-nav {
	padding: 0px;
}

th {
	font-size: 10px;
}

td {
	font-size: 12px;
	vertical-align: top;
}

 

 

 
p {
	color: blue;
}
.bkgColor {
width: 971px;
}

.errmsg {
	color: red;
}

p {
	color: blue;
}

.errmsg1 {
	color: red;
}

p {
	color: blue;
}

.errmsg2 {
	color: red;
}

p {
	color: blue;
}

.errmsg3 {
	color: red;
}
</style>

 
 
<c:if test="${step==2}">
  <script>
	$(document).ready(function() {				
			$("#tabs" ).tabs("select", 1);	
			
	});
</script>
</c:if>
<c:if test="${step==3}">
  <script>
	$(document).ready(function() {				
			$("#tabs" ).tabs("select", 2);	
			
	});
</script>
</c:if>


<form:form id="formID" action="update_party" modelAttribute="partyForm" method="post">
		<form:errors path="*" cssClass="errorblock"  element="div"  />
		
<div id="tabs"
	style="float:left;width:972px; height: auto; border: 1px solid #4E8CCF; padding: 0px;">
	<ul style="background-color: #e0ecff; padding-bottom: 1px">
		<li><a href="#tabs-1">Party Details</a></li>
		
 <c:if test="${opr!='V' && opr!='R'}">
 <li><a href="#tabs-2">Party Personal info</a></li>
		<li><a href="#tabs-3" id="tabs3">Party Business info</a></li>
	</c:if>
<c:if test="${opr=='V'}">
 <li><a href="#tabs-2">Party Personal info</a></li>
		<li><a href="#tabs-3">Party Business info</a></li>
	</c:if>

		 <c:if test="${opr=='R'}">
	<li><a href="#tabs-2">Party Personal info</a></li>
		   <li><a href="#tabs-3">Party Business info</a></li>
	
	</c:if>
	</ul>

			
			<div id="tabs-1" style="background-color: #FF0000; font-size: 11px">
			<form:hidden path="partyDTO.partyId" id="partyId"/>
			<!-- <input type="hidden" name="btn" id="btn"> -->
				<div align="left" class="bkgColor"  >
					<h2>Party Details</h2>
					<table width="750" height="125"
						style="margin-left: 9px; float: left" border="0" align="right">
						<tbody>
							<tr>
								<td width="96" height="30">Party Name <span
									style="color: #FF0000">*</span></td>
								<td colspan="3"><form:input type="text"
										class="validate[required] text-input" onkeypress="return check(event)"  data-maxsize="85"
										path="partyDTO.partyName" id="partyName" style="width:92%;"
										size="18" /></td>
								<td width="89" align="left">Party Code <span
									style="color: #FF0000">*</span></td>
								<td width="132"><form:input type="text" onkeyup="valid1(this)" onblur="valid1(this)"  data-maxsize="16"
										class="validate[required] text-input"
										path="partyDTO.partyCode" id="partyCode" size="18" readonly="true" /></td>
							</tr>
							<tr>
								<td height="30">Opening Balance</td>
								<td width="179"><form:input type="text" data-maxsize="15" class="quantity" style="float:left;"
										path="partyDTO.openingBalance" id="OpeningBalance" size="9" />
									<div
										style="background-color: #FFFFFF;    border: 1px solid #7F9DB9;    border-radius: 3px 3px 3px 3px;    float: left;    height: 20px;    margin-top: 0;    width: 54%;">
										<span style=" float:left; margin:2px 2px; padding-left:12px;" >Dr</span>
										<form:radiobutton style="width:20px; float:left;"
											path="partyDTO.balanceType" id="dr" value="Dr" />
										<span style="margin-top: 2px; float:left ">Cr</span>
										<form:radiobutton style="width:20px;"
											path="partyDTO.balanceType" id="cr" value="Cr" />
									</div></td>
								<td width="72">Party Type</td>
								<td width="140"><form:select path="partyDTO.partyTypeDTO.partyTypeId"
										items="${partyTypeList}" itemLabel="partyTypeDesc"
										itemValue="partyTypeId" id="partyTypeId"
										class="validate[required]" style="width: 250px; height: 20px;">

									</form:select></td>
								<td>Party Group</td>
								<td><form:input type="text" data-maxsize="65" readonly="true" onkeypress="return check(event)" 
										path="partyDTO.partyGroup" size="18" id="partyGroup" /></td>
							</tr>
							<tr>
								<td height="30">Debit Amount</td>
								<td><form:input type="text" class="quantity" data-maxsize="15" style="width:87%" path="partyDTO.debitAmount"
										readonly="true" size="27" id="debitAmount" /></td>
								<td>Credit Amount</td>
								<td><form:input type="text" class="quantity" style="width:78%" data-maxsize="15"  readonly="true"
										path="partyDTO.creditAmount" size="18" id="creditAmount" /></td>
								<td align="left">Closing Balance<span
									style="color: #FF0000"></span></td>
								<td><form:input type="text" class="quantity" path="partyDTO.balanceAmount" data-maxsize="15"
										size="18" id="closingBalance" readonly="true" /></td>
							</tr>
							<tr>
								<td height="25">General Remark</td>
								<td colspan="3"><form:input type="text"
										path="partyDTO.genRemark" onkeypress="return check(event)"  id="generalRemark" style="width:92%"
										size="18" /></td>

								<td align="left">Active Status<span style="color: #FF0000">*</span>
								</td>
								<td><div
										style="border: solid 1px; height: 20px; width: 124px; border-color: #7f9db9; background-color: #FFF;">
										  <span style="margin-top: 0px; float:left; margin:2px 2px; padding-left:12px;" >Yes</span>
										<form:radiobutton style="width:20px; float:left;"
											class="validate[required] radio" value="1"
											path="partyDTO.activeStatus" id="activeStatus" />
										<span style="margin-top: 2px; float:left ">No</span>
										<form:radiobutton style="width:20px;"
											class="validate[required] radio" value="0"
											path="partyDTO.activeStatus" id="activeStatus" />
									</div></td>
							</tr>
						</tbody>
					</table>
			<div class="btn">						
			<c:if test="${opr!='V' && opr!='R'}">
				<div class="savbtn">
				<input class="nextbtn"	type="submit" value="tab1"  id="submit_tab1" name="btn"  style="text-indent: -999px" />
					<div class="cancelbtn"><a href="show_party_form"></a></div>
			</div>
			</c:if>
		<c:if test="${opr=='V'}">
		<c:forEach items="${roleAndRightsDTOList}" var="roleAndRights">
		   <c:if test="${roleAndRights.menuId==sessionScope.menuId}">
    	  	 <c:if test="${roleAndRights.editFlag=='true'}">
		  	 <input class="edit_btn"  type="button" onclick="editMethod();" value=""/>
    	      <a href="show_party_form"  class="cancelbtn" ></a>  
		     </c:if>
		      <c:if test="${roleAndRights.editFlag=='false'}">
		      <input class="edit_btn"  type="button" onclick="checkEdit();" value=""/>
    	      <a href="show_party_form"  class="cancelbtn" ></a>  
		      </c:if>
		    
          </c:if>
          </c:forEach>	
   			  		
    	</c:if>					
		<c:if test="${opr=='R'}">
				<%-- <a href="${remove_url}" class="removebtn" ></a>  --%>
	 	</c:if>
		</div>
		</div>
		</div>
		
			<div id="tabs-2" style="background-color: #FF0000; font-size: 11px">
			
				<div align="left" class="bkgColor" >
					<h2>Party Personal info</h2>
					<table  width="752" height="278"
						style="margin-left: 9px; float: left" border="0" align="right">
						<tbody>

							<tr>
								<td width="133" height="30">Billing Address <span
									style="color: #FF0000">*</span></td>
								<td colspan="3"><form:input type="text" data-maxsize="150"
										path="partyDTO.billingAddress" style=" padding-left: 3px; width: 91%;"
										class="validate[required] text-input" onkeypress="return check(event)"  
										id="billingAddress " size="18" /></td>
								<td width="99" align="left">Billing City<span
									style="color: #FF0000"></span></td>
								<td width="127"><form:select path="partyDTO.billingCityId"
										items="${cityList}" itemLabel="cityName" itemValue="cityId"
										id="bcity"
										style="width: 250px; height: 20px;">

									</form:select></td>
							</tr>
							<tr>
								<td height="30">State</td>
								<td width="131"><form:input type="text" data-maxsize="35" onkeypress="return check(event)" 
										path="partyDTO.state" readonly="true" id="bstate" size="18" /></td>
								<td width="95">Country</td>
								<td width="141"><form:input type="text" style="width:width: 79%;" data-maxsize="35" onkeypress="return check(event)" 
										path="partyDTO.country" readonly="true" id="bcountry"
										size="18" /></td>
								<td align="left">ZIP/PIN Code</td> 
								<td><form:input class="quantity2 validate[custom[number]]" type="text"  data-maxsize="8" path="partyDTO.billingZipCode"
										id="zipPinCode" size="18" /></td>
							</tr>
							<tr>
								<td height="30">Phone1 (O)</td>
								<td><form:input type="text" onkeypress="return check(event)"   data-maxsize="35" path="partyDTO.phoneO1"
										id="phone1" size="18" /></td>
								<td>Phone1 (W)</td>
								<td><form:input type="text" onkeypress="return check(event)"  data-maxsize="35"  path="partyDTO.phoneW1"
										id="phone1W" size="18" /></td>
								<td align="left">Contact Person 1</td>
								<td><form:input type="text" onkeypress="return check(event)"  data-maxsize="35" path="partyDTO.contactPerson1"
										id="contactPerson1" size="18" /></td>
							</tr>
							<tr>
								<td height="30">Mobile (1)</td>
								<td><form:input class="quantity2" type="text" path="partyDTO.mobile1"  data-maxsize="35"
										id="mobile1" size="18" /></td>
								<td>Fax (1)</td>
								<td><form:input onkeypress="return check(event)"  type="text" path="partyDTO.fax1" id="fax1"  data-maxsize="35"
										size="18" /></td>
								<td align="left">Contact 1 Email Id</td>
								<td><form:input  class="text-input"  data-maxsize="100" type="text" path="partyDTO.email1"
										id="contact1EmailId" size="18" /></td>
							</tr>
							<tr>
								<td height="28">Shipping Address</td>
								<td colspan="3"><form:input type="text"  onkeypress="return check(event)" data-maxsize="150"
										path="partyDTO.shippingAddress" style=" padding-left: 3px; width: 91%;"
										id="shippingAddress" size="18" /></td>
								<td align="left">Shipping City</td>
								<td><form:select path="partyDTO.shippingCityId"  
										items="${cityList}" itemLabel="cityName" itemValue="cityId"
										id="scity"
										style="width: 250px; height: 20px;">

									</form:select></td>
							</tr>

							<tr>
								<td height="30">Shipping State</td>
								<td><form:input onkeypress="return check(event)"   data-maxsize="35"  type="text" path="partyDTO.shippingState"
										readonly="true" id="sstate" size="18" /></td>
								<td>Shipping Country</td>
								<td><form:input type="text" style="width: 79%;"  data-maxsize="35" path="partyDTO.shippingCountry"
										readonly="true" id="scountry" size="18" /></td>
								<td align="left">Shipping ZIP/PIN</td>
								<td><form:input class="quantity2 validate[custom[number]] " type="text"  data-maxsize="8" path="partyDTO.shippingZipCode"
										id="shippingPinZipPin" size="18" /></td>
							</tr>
							<tr>
								<td height="30">Phone 2 (O)</td>
								<td><form:input type="text" onkeypress="return check(event)"  data-maxsize="35" path="partyDTO.phoneO2"
										id="phone2O" size="18" /></td>
								<td>Phone 2 (W)</td>
								<td><form:input onkeypress="return check(event)"  data-maxsize="35" type="text" path="partyDTO.phoneW2"
										id="phone2W" size="18" /></td>
								<td align="left">Contact Person 2</td>
								<td><form:input onkeypress="return check(event)"  data-maxsize="35" type="text" path="partyDTO.contactPerson2"
										id="contactPerson2" size="18" /></td>
							</tr>
							<tr>
								<td height="25">Mobile (2)</td>
								<td><form:input class="quantity2" type="text" size="18" id="mobile2"  data-maxsize="35"
										path="partyDTO.mobile2" /></td>
								<td>Fax (2)</td>
								<td><form:input onkeypress="return check(event)"  type="text" size="18" id="fax2"  data-maxsize="35"
										path="partyDTO.fax2" /></td>
								<td align="left">Contact 2 Email Id</td>
								<td><form:input onkeyup="email(this)" onblur="email(this)" class="validate[custom[email]] text-input"  data-maxsize="65" type="text" path="partyDTO.email2"
										id="contact2EmailId" size="18" /></td>
							</tr>
							<tr>
								<td height="25">Email Id</td>
								<td><form:input onkeyup="email(this)" onblur="email(this)" class="validate[custom[email]] text-input"  data-maxsize="65" type="text"  path="partyDTO.email"
										id="emailID" size="18" /></td>
								<td>Website</td>
								<td><form:input onkeypress="return check(event)"  type="text" path="partyDTO.website"  data-maxsize="35"
										size="18" id="website" /></td>
								<td align="left">Shipping Add. Flag</td>
								<td><div
										style="border: solid 1px; height: 20px; width: 124px; border-color: #7f9db9; background-color: #FFF;">
										<span style="margin-top: 0px; float:left; margin:2px 2px; padding-left:12px;" >Yes</span>
										<form:radiobutton style="width:20px; float:left;"
											class="validate[required] radio" value="1"
											path="partyDTO.shippingAddFlag" id="shippingAddFlag" />
										<span style="margin-top: 2px; float:left ">No</span>
										<form:radiobutton style="width:20px;"
											class="validate[required] radio" value="0"
											path="partyDTO.shippingAddFlag" id="dispatchAddFlag" />
									</div></td>
							</tr>
						</tbody>
					</table>
					<div class="btn">
					<c:if test="${opr!='V' && opr!='R'}">
						<div class="savbtn">
							<input class="nextbtn"								
								type="submit" value="tab2" id="submit_tab2" name="btn" style="text-indent: -999px"/>
				<div class="cancelbtn"><a href="show_party_form"></a></div>
						</div>
						</c:if>
		<c:if test="${opr=='V'}">
		<c:forEach items="${roleAndRightsDTOList}" var="roleAndRights">
		   <c:if test="${roleAndRights.menuId==sessionScope.menuId}">
    	  	 <c:if test="${roleAndRights.editFlag=='true'}">
		  	 <input class="edit_btn"  type="button" onclick="editMethod();" value=""/>
    	      <a href="show_party_form"  class="cancelbtn" ></a>  
		     </c:if>
		      <c:if test="${roleAndRights.editFlag=='false'}">
		      <input class="edit_btn"  type="button" onclick="checkEdit();" value=""/>
    	      <a href="show_party_form"  class="cancelbtn" ></a>  
		      </c:if>
		    
          </c:if>
          </c:forEach>		
    	</c:if>					

						<c:if test="${opr=='R'}">
				<%-- <a href="${remove_url}" class="removebtn" ></a>  --%>
		</c:if>
					
					</div>
				</div>
			</div>
		
			<div id="tabs-3" style="background-color: #FF0000; font-size: 11px">
			
				<div align="left" class="bkgColor">
					<h2>Party Business info</h2>
					<table width="752" height="323"
						style="margin-left: 9px; float: left" border="0" align="right">
						<tbody>

							<tr>
								<td width="114" height="30">Invoice Type <span
									style="color: #FF0000">*</span></td>
								<td width="143">
								<form:select
										style="width:113px; height:21px" path="partyDTO.invoiceType"
										class="validate[required] text-input" id="invoiceType">
										<form:option value="importPurchase">Import Purchase</form:option>
										<form:option value="localPurchase">Local Purchase</form:option>										
									</form:select>
									
									<form:select
										style="width:113px; height:21px" path="partyDTO.invoiceType"
										class="validate[required] text-input" id="invoiceType1">
										<form:option value="salesOutsideState">Sales Outside State</form:option>
										<form:option value="saleswithInState">Sales with In State</form:option>									
									</form:select>
									</td>
								<td width="108">Payment Type <span style="color: #FF0000">*</span></td>
								<td width="127"><form:select
										style="width:113px; height:21px"
										class="validate[required] text-input"
										path="partyDTO.paymentType" id="paymentType">
										<form:option value="cash">Cash</form:option>
										<form:option value="cheque">Cheque</form:option>
										<form:option value="credit">Credit</form:option>
									</form:select></td>
								<td width="118" align="left">Drug License No.<span
									style="color: #FF0000"></span></td>
								<td width="108"><form:input type="text" data-maxsize="25"  onkeypress="return check(event)" 
										path="partyDTO.drugLicenceNo" id="drugLicenseNo" size="18" /></td>
							</tr>
							<tr>
								<td height="26">TIN (VAT) No.</td>
								<td><form:input type="text" path="partyDTO.vatNo" onkeypress="return check(event)"   data-maxsize="35"
										id="tinVatNo" size="18" /></td>
								<td>Date (VAT)</td>
								<td><form:input type="text" path="partyDTO.vatDt" size="18"
										id="dateVat" class="datepicker" /></td>
								<td align="left">Party Rating</td>
								<td><form:input type="text" path="partyDTO.partyRating"  data-maxsize="15" 	
										class="quantity validate[custom[number]]" id="partyRating" size="18" /> <span
									class="errmsg"></span></td>
							</tr>
							<tr>
								<td height="27">TIN (CST) No.</td>
								<td><form:input type="text" path="partyDTO.cstNo" onkeypress="return check(event)"  data-maxsize="35"
										id="tinCstNo" size="18" /></td>
								<td>Date (CST)</td>
								<td><form:input type="text" path="partyDTO.cstDt" size="18"
										id="dateCst" class="datepicker" /></td>
								<td align="left">Date of Assessment<span
									style="color: #FF0000"></span></td>
								<td><form:input type="text" path="partyDTO.assementDate"
										size="18" id="dateofAssesment" class="datepicker" /></td>
							</tr>


							<tr>
								<td height="28">Banker A/c No (1)</td>
								<td><form:input type="text" onkeypress="return check(event)"  path="partyDTO.bankAcct1"  data-maxsize="35"
										id="BankerAcNo1" size="18" /></td>
								<td>Banker A/c No (2)</td>
								<td><form:input onkeypress="return check(event)"  type="text" path="partyDTO.bankAcct2"  data-maxsize="35"
										size="18" id="bankerAcNo2" class="website" /></td>
								<td align="left">Import Export Code</td>
								<td><form:input onkeypress="return check(event)"  type="text" path="partyDTO.ieCode"
										size="18" id="importExportCode"  data-maxsize="25" class="website" /></td>
							</tr>

							<tr>
								<td height="24">Bank Name (1)</td>
								<td><form:input onkeypress="return check(event)"   data-maxsize="65" type="text" path="partyDTO.bankName1"
										id="bankName1" size="18" /></td>
								<td>Bank Name (2)</td>
								<td><form:input onkeypress="return check(event)"  data-maxsize="65" type="text" path="partyDTO.bankName2"
										size="18" id="bankName2" class="website" /></td>
								<td align="left">MSME Number</td>
								<td><form:input onkeypress="return check(event)"  data-maxsize="25" type="text" path="partyDTO.msmeCode"
										size="18" id="msmeNumber" class="website" /></td>
							</tr>
							<tr>
								<td height="24">Branch Name (1)</td>
								<td><form:input type="text" path="partyDTO.branchName1"  data-maxsize="65"	
										id="branchName1" size="18" /></td>
								<td>Branch Name (2)</td>
								<td><form:input onkeypress="return check(event)" data-maxsize="65" type="text" path="partyDTO.branchName2"
										id="branchName2" size="18" /></td>
								<td align="left">Credit Days</td>
								<td><form:input type="text" path="partyDTO.creditDays"
										class="quantity2 validate[custom[number]]" id="creditDays" size=" 18"  data-maxsize="3" style="text-align:right"/>  </span></td>
								<td width="0"></td>
							</tr>
							<tr>
								<td height="27">IFSC Code (1)</td>
								<td><form:input type="text" onkeypress="return check(event)" data-maxsize="25" path="partyDTO.ifscCode1"
										id="ifscCode1" size="18" /></td>
								<td>IFSC Code (2)</td>
								<td><form:input type="text" path="partyDTO.ifscCode2"  data-maxsize="25"
										id="ifscCode2" size="18" /></td>
								<td align="left">Credit Limit</td>
								<td><form:input type="text" path="partyDTO.creditLimit"  data-maxsize="15"
										class="quantity" id="creditLimit" size="18" /> </td>
							</tr>
							<tr>
								<td height="27">Deduct TDS Flag</td>
								<td><div
										style="border: solid 1px; height: 20px; width: 124px; border-color: #7f9db9; background-color: #FFF;">
										<span style="margin-top: 0px; float:left; margin:2px 2px; padding-left:12px;" >Yes</span>
										<form:radiobutton style="width:20px;  float:left;"
											class="validate[required] radio" value="1"
											path="partyDTO.tdsFlag" id="deductTdsFlag" />
										<span style="margin-top: 2px; float:left ">No</span>
										<form:radiobutton style="width:20px;"
											class="validate[required] radio" value="0"
											path="partyDTO.tdsFlag" id="deductTdsFlag" />
									</div></td>
								<td>TDS %</td>
								<td><form:input type="text" path="partyDTO.tds" id="tdsPer"  data-maxsize="5" class="quantity validate[custom[number]]"
										size="18" /></td>
								<td align="left">Overdue Days</td>
								<td><form:input type="text" path="partyDTO.overdueDays"  data-maxsize="3"
										class="quantity2 validate[custom[number]]" id="overdueDays" size="18" />  </td>
								</td>
								<td width="0"></td>
							</tr>
							<tr>
								<td height="26">PAN</td>
								<td><form:input type="text" onkeypress="return check(event)" data-maxsize="25" path="partyDTO.panNo" id="pan"
										size="18" /></td>
								<td>Service Tax No.</td>
								<td><form:input onkeypress="return check(event)" data-maxsize="25" type="text" path="partyDTO.servTaxNo"
										id="serviceTaxNo" size="18" /></td>
								<td align="left">Service tax Date</td>
								<td><form:input type="text" path="partyDTO.servTaxDt"
										class="datepicker" size="18" id="serviceTaxDate" /></td>
							</tr>
							<tr>
								<td height="28">Transporter<span style="color: #FF0000">*</span></td>
								<td><form:select path="partyDTO.transporterId"
										items="${transporterList}" itemLabel="transName"
										itemValue="transporterId" id="transporterId"
										class="validate[required]" style="width: 250px; height: 20px;">

									</form:select></td>
								<td>Form Type<span style="color: #FF0000">*</span></td>
								<td><form:select path="partyDTO.formId" items="${formList}"
										itemLabel="name" itemValue="mastersId" id="formId"
										class="validate[required]" style="width: 250px; height: 20px;">

									</form:select></td>
								<td align="left">
								Tax Type<span style="color: #FF0000">*</span>
								</td>
								<td>
								<form:select style="width:113px; height:21px"
									path="partyDTO.vatCstTaxType" id="vatCstTaxType">
									<form:option value="vat">VAT</form:option>
									<form:option value="cst with c form">CST with C Form</form:option>
									<form:option value="cst w/o c form">CST w/o C Form</form:option>
								</form:select>
								</td>

							</tr>
							<tr>
								<td height="28">Excise Reg. ECC No.</td>
								<td><form:input type="text" onkeypress="return check(event)" data-maxsize="35" path="partyDTO.exciseEccNo"
										id="exciseRegEccNo" size="18" /></td>
								<td>Range</td>
								<td><form:input onkeypress="return check(event)" data-maxsize="35" type="text" path="partyDTO.rangeAdd"
										id="range" size="18" /></td>
								<td align="left">Division</td>
								<td><form:input type="text" path="partyDTO.division"  data-maxsize="35"
										size="18" id="division" /></td>
							</tr>
							<tr>
								
								<td align="left">Commissionerate</td>
								<td><form:input type="text" path="partyDTO.commissionerate"  data-maxsize="35"
										size="18" id="division" /></td>
							</tr>
						</tbody>
					</table>
					<div class="btn">
						<div class="savbtn">
						<c:if test="${opr=='R'}">
				<%-- <a href="${remove_url}" class="removebtn" ></a> --%> 
	</c:if>
	
	<c:if test="${opr=='V'}">
	<c:forEach items="${roleAndRightsDTOList}" var="roleAndRights">
		   <c:if test="${roleAndRights.menuId==sessionScope.menuId}">
    	  	 <c:if test="${roleAndRights.editFlag=='true'}">
		  	 <input class="edit_btn"  type="button" onclick="editMethod();" value=""/>
    	      <a href="show_party_form"  class="cancelbtn" ></a>  
		     </c:if>
		      <c:if test="${roleAndRights.editFlag=='false'}">
		      <input class="edit_btn"  type="button" onclick="checkEdit();" value=""/>
    	      <a href="show_party_form"  class="cancelbtn" ></a>  
		      </c:if>
		    
          </c:if>
          </c:forEach>	
   				
    	</c:if>					
	
  <c:if test="${opr!='V' && opr!='R'}">
	<input	class="updatebtn" type="submit" value="" />
	<div class="cancelbtn">
   <a href="show_party_form"  ></a>   
   </div>
 </c:if>
		</div>
	
					</div>
				</div>
			</div>
			</div>
		</form:form>


