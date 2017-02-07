<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page isELIgnored="false"%>
	
	<script type="text/javascript">
	function checkEdit()
		{
		alert('Login User Not Permit to Edit Record !!!!!!');
		}
 	  function editMethod()
 	  { 
 	 	var dispatchAutoId = $('#dispatchAutoId').val();
 	 	var dispatchNumber = $('#orderSeries').val();
	 	var delUrl='show_dispatch?dispatchAutoId='+dispatchAutoId+'&dispatchNumber='+dispatchNumber+'&operation=Edite';
 	 	window.self.location = delUrl;  
	  }
 	</script>	
 	
 	
 	<c:if test="${error.operationName=='V' }">
	<script type="text/javascript">
		$(document).ready(function() {
		$('input').attr('readonly','readonly');
		$('select').attr('disabled','disabled');
		$('textarea').attr('readonly','readonly');
		$('.datepicker').attr('disabled','disabled');
		$('input:radio').attr('disabled',true);
		$("input:checkbox").attr("disabled", true);
		$(".searchbtn1").attr("disabled", true);
		$(".newWindow1").attr("disabled", true);
		$('.datepicker2').attr('disabled','disabled');
		});
	</script>
 </c:if>
	
	<script type="text/javascript">
			
			$(document).ready(function() {  	
				
				$("button").button();
				$("#lightness").click(function() { $('#link').attr('href', 'jquery-ui/css/ui-lightness/jquery-ui-1.8.4.custom.css'); });
				$("#hotsneaks").click(function() { $('#link').attr('href', 'jquery-ui/css/hot-sneaks/jquery-ui-1.8.4.custom.css'); });
				$("#flick").click(function() { $('#link').attr('href', 'jquery-ui/css/flick/jquery-ui-1.8.4.custom.css'); });
				$("#redmond").click(function() { $('#link').attr('href', 'jquery-ui/css/redmond/jquery-ui-1.8.4.custom.css'); });
				$("#smoothness").click(function() { $('#link').attr('href', 'jquery-ui/css/smoothness/jquery-ui-1.8.4.custom.css'); });
				
				$('#fixmyheader-1').fixheadertable({
					caption		: 'My header is fixed !',
					zebra		: true
				});
				
				$('#fixmyheader-2').fixheadertable({
					caption		: 'My header is fixed !',
					height		: 200,
					whiteSpace	: 'normal'
				});
				
				$('#fixmyheader-3').fixheadertable({
					caption		: 'My header is fixed !',
					height		: 200
				});
				
				$('#fixmyheader-4').fixheadertable({
					caption		: 'My header is fixed !',
					width		: 400,
					height		: 150
				});
				
				$('#fixmyheader-5').fixheadertable({
					caption		: 'My header is fixed !',
					height		: 200,
					minWidth	:840
				});
				
				$('#fixmyheader-6').fixheadertable({
					caption		: 'My header is fixed !',
					height		: 200,
					minWidthAuto: false
				});
				
				$('#fixmyheader-7').fixheadertable({
					caption		: 'My header is fixed !',
					height		: 200
				});
				
				$('#fixmyheader-8').fixheadertable({
					caption		: 'My header is fixed !',
					height		:150,
					addTitles	: true,
					colratio	: ['10%', '10%', '8%', '50px', 'auto', 'auto', '30%', 'auto']
				});
			});
		</script>
  
     
<style type="text/css">


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
 	.tableview{     border: 1px solid #7F9DB9;
    border-radius: 3px 3px 3px 3px;
    margin-bottom: 7px;
    margin-left: 5px;
    margin-top: 5px;
    padding: 7px;
    width: 961px;
}
</style>
<html>
<head>
<title>Dispatch Entry</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="keywords" content="jquery,ui,easy,easyui,web">
<meta name="description"
	content="easyui help you build your web page easily!">
	
<script type="text/javascript" src="../js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="../js/maxlength.js"></script>
<script src="././static/js/jquery.validationEngine.js" type="text/javascript"
	charset="utf-8"></script>



<script type="text/javascript">
  //anonymous self invoking function to avoid conflicting with other JavaScript
   (function ($){
    //function is called when the page is fully loaded
     $(document).ready(function(){
       //the page is loaded so attach the time picker to an input field
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
						
					
						
						//called when key is pressed in textbox
						$(".quantity1")
								.keypress(
										function(e) {
											//if the letter is not digit then display error and don't type anything
											if (e.which != 8
													&& e.which != 46
													&& e.which != 0
													&& (e.which<48 || e.which>_$tag__________57)) {
												//display error message
												$(".errmsg1").html(
														"Digits Only").show()
														.fadeOut("slow");
												return false;
											}
										
											
										});
					});
</script>

<script type="text/javascript">
	$(document).ready(function() {
						
						$.ajax({

							type : "POST",
							url : "getDispatchPartyInfo",
							data : "partyId=" + $('#partyId').val(),

							success : function(response) {
								// we have the response
								if (response.status == "SUCCESS") {
									var city = response.result[0].cityName;
									var state = response.result[0].state;
									var billingAddress = response.result[0].billingAddress;
									var phoneO1 = response.result[0].phoneO1;
									var contactPerson1 = response.result[0].contactPerson1;

									var invoiceType = response.result[0].invoiceType;
									if (invoiceType == 'Sales with In State') {
										$('#taxType').val('VAT');
										$('#salesType').val('Sales with In State');
									} else {
										$('#taxType').val('CST');
										$('#salesType').val('Sales Outside State');
									}

									$('#city').val(city);
									$('#state').val(state);
									$('#billingAddress').val(billingAddress);
									$('#phoneNo').val(phoneO1);
									$('#contactPerson').val(contactPerson1);

								}
							}

						});
						
});
</script>

<c:if test="${opr=='E' }">
	<script type="text/javascript">
		$(document).ready(function() {
		//$('.datepicker2').attr('disabled','disabled');
		});
	</script>
	</c:if>
	

<script type="text/javascript">
      $(document).ready(function()
       {
    	  var l=$('#lastDate').val();
    	  console.log(l);
       
    	    $(".datepicker2" ).datepicker({dateFormat : 'dd-M-yy',changeMonth: true,
  	          changeYear: true, yearRange: '-99:+0', minDate: new Date(l), maxDate: '+0M +0D'});
      });
  </script>

<c:if test="${error.errorCode=='Delete'}">
<script>
$(document).ready(function() {
	$('input').attr('disabled','disabled');	
	$('select').attr('disabled','disabled');
	$('textarea').attr('disabled','disabled');
});

function remoneConformation(){
	var name =	confirm('Are you sure that you want to delete this item?');
	if(name==true){
			return true;
		} else{
		return false;
	     }
	  }

</script>
</c:if>

<style type="text/css">
p {
	color: blue;
}
.itemName{background-color: #eeeeee !important;border: none !important;}
.invoceNum{background-color: #eeeeee !important;border: none !important;}
.Uom{ background-color: #eeeeee !important;border: none !important;}
.batchNo {background-color: #eeeeee !important;border: none !important;}
 
/*input {
	width:87%;
	margin-bottom:6px;
	}*/
select {
	width: 87%;
	height: 20px;
}

.gridheadingdiv td {
	height: 22px;
	font-weight: bold;
	text-align: center;
}

.gridheadingdiv input {
	border: medium none;
	width: 40px;
}

 

#formID .bkgColor .gridheadingdiv table tr td div {
	font-weight: bold;
}
</style>
<script>

function edit( index){
	var frm1 = document.forms[0];
	var ind = parseInt(index);
	frm1.elements["indexNo"].value = ind;
	
    }

	$(function() {
		$('#partyId').change(function() {
			$.ajax({
				type : "POST",
				url : "getDispatchPartyInfo",
				data : "partyId=" + $('#partyId').val(),

				success : function(response) {
					// we have the response
					if (response.status == "SUCCESS") {
						var city = response.result[0].cityName;
						var state = response.result[0].state;
						var billingAddress = response.result[0].billingAddress;
						var phoneO1 = response.result[0].phoneO1;
						var contactPerson1 = response.result[0].contactPerson1;

						var invoiceType = response.result[0].invoiceType;
						if (invoiceType == 'Sales with In State') {
							$('#taxType').val('VAT');
							$('#salesType').val('Sales with In State');
						} else {
							$('#taxType').val('CST');
							$('#salesType').val('Sales Outside State');
						}

						$('#city').val(city);
						$('#state').val(state);
						$('#billingAddress').val(billingAddress);
						$('#phoneNo').val(phoneO1);
						$('#contactPerson').val(contactPerson1);

					}
				}

			});
		});
	

	

	});
	
	
	
	$(function() {
		function formChange() {
			
			var packetWeight = 0;
			var wt=0;
			for ( var ele = 0; true; ele++) {
				var frm = document.forms[0];
				
				 wt = frm.elements["dispatchDetailList["+ ele + "].packetWeight"];
				
				if (!wt) {
					//alert("4" );
					break;
					}
				
				packetWeight =  packetWeight+parseFloat(wt.value);
				
			    }
				    if(packetWeight){
				    
				    	frm.elements["dispatchMasterDTO.totalWeight"].value = packetWeight;
				    }
		        }
		$('#dispatchForm').change(function() {
		       formChange();
		});
	    formChange();
     });
</script>

</head>
<body>

<c:if test="${error.errorMsg!=null}">
	<input type="hidden" id="errorId" value="${error.errorMsg}">
		<script type="text/javascript">
	//	var delUrl='show_melter_trolly_form';
		$(document).ready(function() {
			var errorId=document.getElementById('errorId');
			alert(errorId.value);
    	// window.self.location = delUrl;
		});
 	</script>
	</c:if>
	
	<c:if test="${error.errorCode!=null}">
	<input type="hidden" id="errorId1" value="${error.errorCode}">
		<script type="text/javascript">
		//var delUrl='show_melter_trolly_form';
		$(document).ready(function() {
			var errorId=document.getElementById('errorId1');
			alert(errorId.value);
    	// window.self.location = delUrl;
		});
 	</script>
 	</c:if>
	
	<c:if test="${error.operationName=='Delete'}">
		<script type="text/javascript">
	 	$(document).ready(function() {
	 	$('input').attr('disabled','disabled');	
	 	$('select').attr('disabled','disabled');
	 	$('textarea').attr('disabled','disabled');
	 	});
		</script>
	</c:if>
	
	
	<c:if test="${error.operationName=='Delete'}">
	 <script type="text/javascript">
			var redrctUrl='show_dispatch_list?operation=show';
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
	 		 var dispatchAutoId = getParam('dispatchAutoId');
	 		 var dispatchNumber = getParam('dispatchNumber');
	 		// alert(frank);
	 		 //	confirm('Are you sure you want to delete?');
			 var delUrl='submitDispatchList?dispatchAutoId='+dispatchAutoId+'&dispatchNumber='+$("#orderSeries").val()+'&operation=Delete';
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
	
	
	<form:form commandName="dispatch" action="save_dispatch" method="POST"
		id="dispatchForm">
		<div class="panel-header"  >
			<div class="panel-title">Dispatch Entry</div>
			<div class="panel-tool"></div>
		</div>

		<div align="left" class="bkgColor"
			style="height: auto; padding-bottom: 14px;">
			<table height="219" class="tableview"  width="840" border="0" style="margin-top: 12px;">
				<tr>
					<td height="20">Branch Name <span style="color: #FF0000">*</span></td>
					<td colspan="5"><form:select path="dispatchMasterDTO.branchId" class="validate[required] text-input"
							style="width:100%" id="branchId">
							<form:options items="${branchList}" itemValue="branchId"
								itemLabel="branch" />
						</form:select></td>
				</tr>

				<tr>
					<td width="120" height="20">Dispatch Series<span
						style="color: #FF0000">*</span></td>
					<td width="158"><form:input
							path="dispatchMasterDTO.transactionSeries"
							class="validate[required] text-input"  style="width:44%" data-maxsize="15"
							readonly="true"   size="8" /> <form:input
							path="dispatchMasterDTO.dispatchId"
							class="validate[required] text-input" data-maxsize="15"
							readonly="true"  style="width:35%" disabled="disabled" size="8" /></td>
					<td width="151">
					Dispatch Number<span style="color: #FF0000">*</span></td>
					<td width="131">
					<form:input
							path="dispatchMasterDTO.dispatchNumber"
							class="validate[required] text-input" style="width:98%" data-maxsize="15"
							readonly="true" disabled="disabled" size="11" id="orderSeries" />
						<form:hidden path="dispatchMasterDTO.dispatchAutoId" id="dispatchAutoId" />
						<form:hidden path="lastDispatchDate" id="lastDate" /></td>
					<td width="75">Date<span style="color: #FF0000">*</span></td>
					<td width="104"><form:input style="width:96%" readonly="true" class="validate[required] text-input datepicker2"
							path="dispatchMasterDTO.dispatchDate"    size="21" /></td>
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td height="20">Party Name <span style="color: #FF0000">*</span></td>
					<td colspan="5"><form:select
							path="dispatchMasterDTO.partyDTO.partyId" style="width:100%"
							id="partyId">
							<form:options items="${partyList}" itemValue="partyId"
								itemLabel="partyNameCity" />
						</form:select></td>
				</tr>

				<tr>
					<td height="20">Address</td>
					<td colspan="7"><form:input path="billingAddress"
							data-maxsize="15"  
							readonly="true" style="width:97%; padding-left:6px;" disabled="disabled" size="11"
							id="billingAddress" /></td>
				</tr>
				<tr>
					<td height="20">City</td>
					<td colspan="1"><form:input path="cityName" data-maxsize="15"
							readonly="true"  style="width:83%" disabled="disabled" size="21"
						  id="city" /></td>
					<td>State</td>
					<td><form:input path="state"  style="width:97%" data-maxsize="15" readonly="true"
							  size="11" id="state" /></td>
					<td>Phone 1 (0)</td>
					<td><form:input path="phonNo" data-maxsize="15"
							readonly="true" disabled="disabled" style="width:96%" size="11" id="phoneNo" /></td>
					<td width="113">Contact person 1</td>
					<td width="123"><form:input path="contactPerson"
							data-maxsize="15" readonly="true" style="width:85%" disabled="disabled" size="11"
							id="contactPerson" /></td>
				</tr>
				<tr>
					<td height="20">Transporter Name</td>
					<td colspan="3"><form:select path="transporterName"
							style="width:100%"  
							id="select">
							<form:option value=""></form:option>
							<form:options items="${transporterList}"
								itemValue="transporterId" itemLabel="transName" />
						</form:select></td>
					<td>Vehicle no.</td>
					<td colspan="3"><form:input style="width:95%" onkeypress="return check(event)"  path="dispatchMasterDTO.vehicleNo"
							data-maxsize="25" size="49" /></td>
				</tr>
				<tr>
					<td height="20">Driver Name</td>
					<td colspan="3"><form:input
							path="dispatchMasterDTO.driverName" onkeypress="return check(event)" style="width:99%" data-maxsize="25" size="21" /></td>
					<td>Mobile</td>
					<td colspan="3"><form:input
							path="dispatchMasterDTO.driverMobile" style="width:95%"  onkeypress="return check(event)" data-maxsize="25" size="49" /></td>
				</tr>
			</table>
			<div style="width: 906px; margin: 0 auto;">
				<span style="margin-left: 80px;" class="errmsg"></span>
			</div>
			<div class="gridheadingdiv">
			<table width="668" class="fixmyheader" id="fixmyheader-8">
  <thead>
   <tr>
						<td width="41"><div align="center">
								<strong>S No.</strong>
							</div></td>
						<td width="94">
						<!--  <div align="center"><strong><img src="../images/search_small.png" title="Search" alt="" /></strong>Invoice No.</div> -->
						<input type="submit" class="searchbtn1" style="width:13px; margin-left: 5px; height:13px;   margin-top: 6px;
							
    width: 13px;" name="operation"
							value="Invoice No" /><strong style="line-height: 23px;">Invoice Name</strong></td>
					

						<td width="70"><div align="center">
								<strong style="line-height: 23px;">Item Name</strong>
							</div></td>
						<td width="69"><div align="center">
								<strong>UOM</strong>
							</div></td>
						<td width="72">Batch No</td>
						<td width="74"><div align="center">
								<strong>Dispatch Qty </strong>
							</div></td>
						<td width="88"><div align="center">
								<strong>Qty/Packet</strong>
							</div></td>
						<td width="76">No of Packet</td>
						<td width="78">Packet Wt.</td>
						<td width="56"><div align="center">
								<strong>Action</strong>
							</div></td>
					</tr>
  </thead>
  <tbody> 		<%
						int i = 1;
					%>
					<c:forEach items="${dispatch.dispatchDetailList}" var="pr"
						varStatus="s" begin="0" end="10">
						<tr>
							<td width="31"><%=i%></td>
							<td width="84"> <form:input
									path="dispatchDetailList[${s.index}].invoiceNumber"
									value="${pr.invoiceNumber}" style="text-align:left; background-color: #DDDDDD; width:100%"
									data-maxsize="15" class="invoceNum" size="8" readonly="true"/> <form:hidden
									path="dispatchDetailList[${s.index}].sno" value="${pr.sno}"
									style="text-align:left; width:100%" data-maxsize="15" size="8" /></td>
							<td width="60">
							<form:input
									path="dispatchDetailList[${s.index}].itemName"
									value="${pr.itemName}" style="text-align:left; background-color: #eeeeee !important; width:100%"
									data-maxsize="15" class="itemName" size="8" readonly="true"/> <form:hidden
									path="dispatchDetailList[${s.index}].itemId"
									value="${pr.itemId}" style="text-align:left; width:100%"
									data-maxsize="15" size="8" /></td>
							<td width="59"><form:input path="dispatchDetailList[${s.index}].umoName" value="${pr.umoName}"
									style="text-align:left; background-color: #DDDDDD; width:100%" data-maxsize="15" class="Uom" size="8" readonly="true" />
							</td>
							<td width="62"><form:input
									path="dispatchDetailList[${s.index}].batchNo"
									value="${pr.batchNo}" style="text-align:left; background-color: #DDDDDD; width:100%" class="batchNo"
									data-maxsize="15" size="8" readonly="true"/></td>
							<td width="64"><form:input
									path="dispatchDetailList[${s.index}].quantity"
									value="${pr.quantity}" class="quantity" style="text-align:right;   border: 1px solid #7F9DB9; width:100%"
									data-maxsize="15" size="8" /></td>
							<td width="78"><form:input
									path="dispatchDetailList[${s.index}].qtyPerPacket"
									value="${pr.qtyPerPacket}" class="quantity" style="text-align:right;   border: 1px solid #7F9DB9; width:100%"
									data-maxsize="15" size="8" /></td>
							<td width="66"><form:input
									path="dispatchDetailList[${s.index}].noOfPacket"
									value="${pr.noOfPacket}" class="quantity" style="text-align:right;   border: 1px solid #7F9DB9; width:100%"
									data-maxsize="15" size="8" /></td>
							<td width="68"><form:input
									path="dispatchDetailList[${s.index}].packetWeight"
									value="${pr.packetWeight}" style="text-align:left;    border: 1px solid #7F9DB9; width:100%"
									data-maxsize="15" size="8" id="packetWeightId" /></td>
							<td style="text-align: center;" width="47">
							
							<c:if test="${error.operationName=='V'}">
							<input type="submit" disabled="disabled" class="drop1" style="height: 12px; width: 12px" name="operation" value="" />
							</c:if><c:if test="${error.operationName!='V'}">
							<input type="submit" class="drop1" name="operation"
								value=""
								onclick="edit('<c:out value="${s.index}"/>');this.value='remove';" />
							</c:if>
							</td>
							<%
							i++;
							%>
						</tr>
					</c:forEach>
  
  </tbody>
</table>
  </div>
			<table height="104" class="tableview"  width="840" border="0" style="margin-top: 12px;">
				<tr>
				<form:hidden path="indexNo" />
					<td width="88" height="20"><strong>Item Count</strong></td>
					<td width="88"><input type="text" name="itemCount"
						value="<%=i - 1%>" data-maxsize="15" readonly="readonly"
						style="text-align: right" size="11" id="itemCount" /></td>
					<td width="72"><strong>Total Packet</strong></td>
					<td width="88"><span style="font-weight: bold"> <form:input
								path="dispatchMasterDTO.totalPacket" data-maxsize="25" size="11" />
					</span></td>
					<td width="49"><span style="font-weight: bold"> Total
							Wt.<span style="color: #FF0000"></span>
					</span></td>
					<td width="233"><form:input
							path="dispatchMasterDTO.totalWeight" style="width:33%" data-maxsize="25" size="11"
							readonly="true" /></td>
				</tr>
				<tr>
					<td style="vertical-align: top;" height="29">Dispatch Remark </td>
					<td colspan="5"><form:textarea cols="80" rows="2"   onkeypress="return check(event)"
							path="dispatchMasterDTO.dispatchRemark"  
							size="11" /></td>
				</tr>

			</table>
			
			
	<c:if test="${error.operationName=='Delete'}">

	<c:url value="submitDispatchList" var="remove_url">
					<c:param name="dispatchAutoId" value="${dispatch.dispatchMasterDTO.dispatchAutoId}"></c:param>
					<c:param name="dispatchNumber" value="${dispatch.dispatchMasterDTO.dispatchNumber}"></c:param>
					
					<c:param name="operation" value="Delete"></c:param>
		</c:url> <%-- <a href="${remove_url}" class="removebtn" onclick="return remoneConformation();"></a>  --%>
	
	
	
	<c:url value="show_dispatch_list" var="remove_url">
					<c:param name="operation" value=""></c:param>
		</c:url> <%-- <a href="${remove_url}" class="cancelbtn" onclick="this.value='Cancel';"></a>  --%>
</c:if>
		<c:if test="${error.operationName!='Delete'}">	
			<div class="btn">
				<div class="savbtn">

		<c:if test="${dispatch.dispatchMasterDTO.dispatchAutoId !=null && operation=='Edite'}">
			<input id="myButton" class="updatebtn" type="submit" value=""
				name="operation" value="" onclick="this.value='Save',$('#dispatchForm').validationEngine();" />
							<div class="cancelbtn">
			     	<input type="submit" value="" name="operation" class="cancelbtn" onclick="this.value='Cancel';" />
				</div>
					</c:if>
<!--  && error.operationName=='V' -->

	<c:if test="${dispatch.dispatchMasterDTO.dispatchAutoId !=null && operation!='Edite'&& error.operationName=='V'}">
	
	<c:forEach items="${roleAndRightsDTOList}" var="roleAndRights">
		   <c:if test="${roleAndRights.menuId==sessionScope.menuId}">
    	  	 <c:if test="${roleAndRights.editFlag=='true'}">
		  	 <input class="edit_btn"  type="button" onclick="editMethod();" value=""/>
    	    <div class="cancelbtn">
     		<input type="submit" value="" name="operation" class="cancelbtn" onclick="this.value='Cancel';" />
			</div> 
		     </c:if>
		      <c:if test="${roleAndRights.editFlag=='false'}">
		      <input class="edit_btn"  type="button" onclick="checkEdit();" value=""/>
    	     <div class="cancelbtn">
     		<input type="submit" value="" name="operation" class="cancelbtn" onclick="this.value='Cancel';" />
			</div>
		      </c:if>
		    
          </c:if>
          </c:forEach>	
	</c:if>


			<c:if test="${dispatch.dispatchMasterDTO.dispatchAutoId ==null}">
						<input id="myButton" class="submit" type="submit" value=""
							name="operation" value="" onclick="this.value='Save',$('#dispatchForm').validationEngine();" />
								<div class="cancelbtn">
			     	<input type="submit" value="" name="operation" class="cancelbtn" onclick="this.value='Cancel';" />
				</div>
					</c:if>
				</div>
			 
			</div>
		
</c:if >
</div>
	</form:form>
	
</body>
</html>