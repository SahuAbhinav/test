<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<c:if test="${errors!=null}">
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
		var redrctUrl = 'get_purchaseOrder_list';

		function getParam(name) {
			name = name.replace(/[\[]/, "\\\[").replace(/[\]]/, "\\\]");
			var regexS = "[\\?&]" + name + "=([^&#]*)";
			var regex = new RegExp(regexS);
			var results = regex.exec(window.location.href);
			if (results == null)
				return "";
			else
				return results[1];
		}

		$(document).ready(function() {
			var frank_param = getParam('id');
			//confirm('Are you sure you want to delete?');
			var delUrl = 'remove_purchaseOrder?id=' + frank_param;
			if (confirm('Are you sure you want to delete?')) {
				window.self.location = delUrl;
			} else {
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
	function editMethod() {
		var frank_param = $('#purchaseAutoOrderId').val();
		var delUrl = 'get_purchaseOrder?id=' + frank_param + '&opr=E';
		window.self.location = delUrl;
	}
</script>


<c:if test="${opr=='V' && opr!=null}">
	<script type="text/javascript">
		$(document).ready(function() {
			$('input').attr('readonly', 'readonly');
			$('select').attr('disabled', 'disabled');
			$('textarea').attr('readonly', 'readonly');
			$('.datepicker').attr('disabled', 'disabled');
			$('.dltImg').attr('disabled', 'disabled');
			$('input:radio').attr('disabled', true);
			$("input:checkbox").attr("disabled", true);
			$(".newWindow").attr("disabled", true);
			$(".newWindow1").attr("disabled", true);
			$('.datepicker2').attr('disabled', 'disabled');
		});
	</script>
</c:if>


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

	function checkAmount() {

		if ($("#snoid").val() == undefined) {
			alert("Sorry you can not save record without item.");
			return false;
		}

		if ($('#poNetAmount').val() == '0.00') {
			alert('Purchase order can not be save with zero amount');
			return false;
		}

		return true;
	}
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
		function abc(cb) {

			$.get('getPartyBy_id', {
				id : $(cb).val()
			}, function(data) {
				//		alert('hello');
				$("#pcity").val(data.cityName);
				$("#cityIdValue").val(data.billingCityId);
				$("#pstate").val(data.state);
				$("#paddress").val(data.billingAddress);
				$("#phoneOffice").val(data.phoneO1);
				$("#contactPerson").val(data.contactPerson1);
				$("#taxtVatCstType").val(data.vatCstTaxType);

			});
		}
		$(function() {
			$('#party').change(function() {
				//	alert($(this).val());
				abc(this);

			});
		});

		abc($('#party'));
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
	$(document).ready(function() {
		//called when key is pressed in textbox
		$("#select5").attr("disabled", "disabled");
		$("#formnumber").attr("disabled", "disabled");
		$("#formDate").attr("disabled", "disabled");
		/* $(".quantity").change(function(e) {
			document.forms["formID"].action = "purchaseCalc?opr=${opr}";
			document.forms["formID"].submit();
		});
		 */});

	$(document).ready(function() {
		//called when key is pressed in textbox
		$(".newWindow").click(function(e) {
			document.forms["formID"].action = "show_item_list_po?opr=E";
			document.forms["formID"].submit();

		});
	});
	$(document).ready(function() {
		$(".newWindow1").click(function(e) {

			document.forms["formID"].action = "show_item_list_po?opr=A";
			document.forms["formID"].submit();

		});
	});
	$(document).ready(function() {
		$(".indentSearch").click(function(e) {

			document.forms["formID"].action = "get_indent_list_for_purchase?opr=indent";
			document.forms["formID"].submit();

		});
	});

	
	

	$(document).ready(function() {
		$(".quantity").each(function() {
			var v = parseFloat($(this).val());

			v = v.toFixed(2);

			$(this).val(v);
		});
	});
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
				
				
				 if($('#itemCountId').val()>0){
					 $('#party').attr('disabled','disabled');	
					 }
					 else{
						 $('#partyIdHidden').attr('disabled','disabled');	
						 
					    }
					 
				
				//called when key is pressed in textbox
				$(".quantity1").keypress(
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
			$('textarea').attr('disabled', 'disabled');

		});
	</script>
</c:if>

<c:if test="${opr=='E' }">
	<script type="text/javascript">
		$(document).ready(function() {
		$('.datepicker1').attr('disabled','disabled');
		});
	</script>
	</c:if>
	
<script type="text/javascript">
	$(document).ready(function() {

		$(".datepicker").datepicker({
			changeMonth : true,
			changeYear : true,
			yearRange : '-99:+10',
			dateFormat : 'dd-M-yy'
		});
		//     
	});

	
	$(document).ready(function() {
	
		var l=$('#lastDate').val();
  	  
		$(".datepicker1").datepicker({
			changeMonth : true,
			changeYear : true,
			yearRange : '-99:+10',
			dateFormat : 'dd-M-yy',
			maxDate : new Date(),
			 minDate: new Date(l)
		});

		$("#poValidUpto").datepicker({
			changeMonth : true,
			changeYear : true,
			yearRange : '-99:+10',
			dateFormat : 'dd-M-yy'

		});

	});

	$(document).ready(function() {

		$(".datepicker2").datepicker({
			changeMonth : true,
			changeYear : true,
			yearRange : '-99:+10',
			dateFormat : 'dd-M-yy',
			minDate : new Date()
		});

	});
</script>
<script type="text/javascript">
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



<script>
	$(document)
			.ready(
					function() {

						var round = function(value, precision) {
							var result = Number(value);
							if (typeof precision == 'number') {
								precision = Math.pow(10, precision);
								result = Math.round(value * precision)
										/ precision;
							}
							return result;
						};

						function formChange() {

							var totalItemValue = 0.0;
							var totalDiscountValue = 0.0;
							var totalTaxableValue = 0.0;
							var totalExcisableValue = 0.0;
							var totalTaxValue = 0.0;

							var totalCessAmt = 0.0;
							var totalHcessAmt = 0.0;
							for ( var ele = 0; true; ele++) {
								var frm = document.forms[0];

								var quantity = frm.elements["purchaseOrderMasterDTO.purchaseOrderDetailDTOList["
										+ ele + "].itemQuantity"];
								if (!quantity) {
									break;
								}
								quantity.value  = Number((quantity.value).toString().match(/^\d+(?:\.\d{0,6})?/));
								var purchaseRate = frm.elements["purchaseOrderMasterDTO.purchaseOrderDetailDTOList["
										+ ele + "].purchaseRate"];
								// default set 0 value
								if (purchaseRate.value == 'NaN') {
									purchaseRate.value = 0.0;
								}
								var excisePerc = frm.elements["purchaseOrderMasterDTO.purchaseOrderDetailDTOList["
										+ ele + "].itemDTO.excisePerc"];
								if (excisePerc.value == 'NaN') {
									excisePerc.value = 0.0;
								}
								var taxPerc = frm.elements["purchaseOrderMasterDTO.purchaseOrderDetailDTOList["
										+ ele + "].taxPerc"];
								if (taxPerc.value == 'NaN') {
									taxPerc.value = 0.0;
								}
								// default set 0 value end
                                     purchaseRate.value=Number((purchaseRate.value).toString().match(/^\d+(?:\.\d{0,6})?/));
								var itemValue = quantity.value
										* purchaseRate.value;
								frm.elements["purchaseOrderMasterDTO.purchaseOrderDetailDTOList["
										+ ele + "].itemValue"].value = round(
										itemValue, 2);

								var discountPerc = frm.elements["purchaseOrderMasterDTO.purchaseOrderDetailDTOList["
										+ ele + "].discountPerc"];
								var discountAmount = (itemValue * discountPerc.value) / 100;
								frm.elements["purchaseOrderMasterDTO.purchaseOrderDetailDTOList["
										+ ele + "].discountAmount"].value = round(
										discountAmount, 2);

								var exisableAmt = itemValue - discountAmount;
								// var exisableAmount= (itemValue*excisePerc.value)/100;
								var exisableAmount = (exisableAmt * excisePerc.value) / 100;
								
								

								

								var cessPerc = $("#cessPerceId").val();
								var hCessPerc = $("#hCessPerceId").val();

								var cessAmnt = (exisableAmount * cessPerc) / 100;
								var hCessAmnt = (exisableAmount * hCessPerc) / 100;

								var taxAmt = (itemValue +cessAmnt+hCessAmnt+ exisableAmount - discountAmount)
								* taxPerc.value / 100.0;
								
								var taxableAmount = (itemValue + cessAmnt+hCessAmnt+exisableAmount - discountAmount)
								* taxPerc.value / 100;
								
								
								
								frm.elements["purchaseOrderMasterDTO.purchaseOrderDetailDTOList["
										+ ele + "].netAmount"].value = round(
										itemValue - discountAmount
												+ exisableAmount + cessAmnt
												+ hCessAmnt + taxableAmount, 2);

								// Cess and hCess percentge and amount for detail stert
								frm.elements["purchaseOrderMasterDTO.purchaseOrderDetailDTOList["
										+ ele + "].educationCessPerc"].value = cessPerc;
								frm.elements["purchaseOrderMasterDTO.purchaseOrderDetailDTOList["
										+ ele + "].highEducationCessPerc"].value = hCessPerc;

								frm.elements["purchaseOrderMasterDTO.purchaseOrderDetailDTOList["
										+ ele + "].educationCessAmount"].value = cessAmnt;
								frm.elements["purchaseOrderMasterDTO.purchaseOrderDetailDTOList["
										+ ele + "].highEducationCessAmount"].value = hCessAmnt;
								// end

								totalTaxValue += taxAmt;
								totalItemValue += itemValue;
								totalDiscountValue += discountAmount;

								totalTaxableValue += taxableAmount;
								totalExcisableValue += exisableAmount;

								totalCessAmt += cessAmnt;
								totalHcessAmt += hCessAmnt;
							}

							frm.elements["purchaseOrderMasterDTO.itemValue"].value = round(totalItemValue,2);
							
							frm.elements["purchaseOrderMasterDTO.discountAmount"].value = round(totalDiscountValue,2);
									

							
							
							
							 frm.elements["purchaseOrderMasterDTO.poNetAmount"].value = round(
									totalItemValue - totalDiscountValue
											+ totalTaxableValue + totalCessAmt
											+ totalHcessAmt
											+ totalExcisableValue, 2);

							frm.elements["purchaseOrderMasterDTO.exciseDutyAmount"].value = round(
									totalExcisableValue, 2);
							frm.elements["purchaseOrderMasterDTO.taxableAmount"].value = round((totalItemValue + totalExcisableValue+totalCessAmt+ totalHcessAmt)- totalDiscountValue, 2);
							frm.elements["purchaseOrderMasterDTO.taxVatCstTotal"].value = round(
									totalTaxValue, 2);

							frm.elements["purchaseOrderMasterDTO.educationCessAmount"].value = round(
									totalCessAmt, 2);
							frm.elements["purchaseOrderMasterDTO.highEducationCessAmount"].value = round(
									totalHcessAmt, 2);
						}

						$('#formID').change(function() {
							formChange();
						});

						formChange();

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
	width: 967;
}

.gridheadingdiv td {
	height: 22px;
}

.gridheadingdiv input {
	border: medium none;
	width: 75px;
}

.gridheadingdiv {
	width: 967px;
	!
	important
}

.newWindow {
	background: url(static/images/search_small.png);
	background-repeat: no-repeat;
	height: 12px;
	border: none;
	width: 12px;
}
.indentSearch {
	background: url(static/images/search_small.png);
	background-repeat: no-repeat;
	height: 12px;
	border: none;
	width: 12px;
}
.newWindow1 {
	background: url(static/images/search_small.png);
	background-repeat: no-repeat;
	height: 12px;
	border: none;
	width: 12px;
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

<form:form name="input" id="formID" action="savePurchaseOrder"
	method="post" modelAttribute="purchaseOrderMasterForm">

	<form:hidden path="purchaseOrderMasterDTO.poAutoId" />
	<form:hidden path="lastPurchaseOrderDate"  id="lastDate" />

	<div class="panel-header" style="width: 960px;">
		<div class="panel-title">Purchase Order Entry</div>
		<div class="panel-tool"></div>
	</div>

	<div align="left" class="bkgColor"
		style="height: auto; padding-bottom: 14px; width: 969px;">

		<table height="114" width="967" border="0" class="tableview">
			<tr>
				<td height="20">Branch Name <span style="color: #FF0000">*</span>
				</td>
				<td colspan="5"><form:select
						path="PurchaseOrderMasterDTO.branchDTO.branchId"
						items="${branchList}" itemLabel="branch" itemValue="branchId"
						style="width:100%;" id="branchId" class="validate[required]">
					</form:select>
			</tr>
			<tr>
				<td width="113" height="20">PO Series <span
					style="color: #FF0000">*</span>
				</td>
				<td width="180"><form:input onkeypress="return check(event)"
						path="PurchaseOrderMasterDTO.purchaseOrderIdSeries"
						class="validate[required] text-input" data-maxsize="15"
						style="background-color:#ebebe4;" readonly="true"
						disabled="disabled" size="8" id="orderSeries" /> <form:hidden
						onkeypress="return check(event)"
						path="PurchaseOrderMasterDTO.transactionSeries"
						class="validate[required] text-input" data-maxsize="15"
						style="background-color:#ebebe4;" readonly="true"
						disabled="disabled" size="8" id="orderSeries" /> <form:input
						class="validate[required] text-input"
						path="PurchaseOrderMasterDTO.purchaseOrderId" data-maxsize="15"
						style="width:40%;" readonly="true" disabled="disabled" size="11"
						id="purchaseOrderId" /> <form:hidden
						class="validate[required] text-input"
						path="PurchaseOrderMasterDTO.poAutoId" data-maxsize="15"
						style="width:40%;" readonly="true" disabled="disabled" size="11"
						id="purchaseAutoOrderId" /> <form:hidden
						class="validate[required] text-input"
						path="PurchaseOrderMasterDTO.finYear" data-maxsize="15"
						style="width:40%;" readonly="true" disabled="disabled" size="11"
						id="quotationSeries" /></td>
				<td width="122">PO Number</td>
				<td width="133"><form:input type="text"
						onkeypress="return check(event)"
						path="PurchaseOrderMasterDTO.purchaseOrderNumber"
						style="background-color:#ebebe4;width:97%;" data-maxsize="15"
						readonly="true" disabled="disabled" size="11" id="iNumber" />
				</td>
				<td width="99">Date</td>
				<td width="115"><form:input type="text"
						onkeypress="return check(event)"
						path="PurchaseOrderMasterDTO.purchaseOrderDate"
						class="datepicker1 validate[required] text-input" id="date322"
						style="background-color:#ebebe4;width:97%;" size="11"
						readonly="true" />
				</td>
				<td width="73" nowrap="nowrap">Indent No.<input class="indentSearch" 
													style="font-size: 11px; font-weight: bold; width: 12px; padding: 0 0 0 0px;"
													type="submit" value=" " /></td>
				<td width="55"><form:input type="text"
						onkeypress="return check(event)"
						path="PurchaseOrderMasterDTO.indentNumber" data-maxsize="15" readonly="true"
						id="date22222" style="width:95%" size="11" />
				</td>
				<td width="60">Indent Date</td>
				<td width="143"><form:input type="text"
						onkeypress="return check(event)"
						path="PurchaseOrderMasterDTO.indentDate" 
						data-maxsize="11" size="11" style="width:100%"
						readonly="true" /></td>
			</tr>
			<tr>
				<td height="20">Supplier Name <span style="color: #FF0000">*</span>
				</td>
				<td colspan="5">
					<form:hidden
						path="PurchaseOrderMasterDTO.partyDTO.partyId" id="partyIdHidden"></form:hidden>
						<form:select
						path="PurchaseOrderMasterDTO.partyDTO.partyId"
						onkeypress="return check(event)" style="width:100%"
						class="validate[required] text-input" id="party" readonly="readonly" >
						<form:options items="${partyList}"
						itemLabel="partyName" itemValue="partyId"></form:options>
						</form:select>
						
				</td>
				<td>City</td>
				<td><input type="text" onkeypress="return check(event)"
					style="width: 95%" name="city" data-maxsize="35" readonly="true"
					style="background-color:#ebebe4;" disabled="disabled" size="11"
					id="pcity" />
				</td>
				<form:hidden path="PurchaseOrderMasterDTO.cityId" id="cityIdValue" />
				<td>State</td>
				<td><input type="text" onkeypress="return check(event)"
					style="width: 99%" name="state" data-maxsize="15"
					style="background-color:#ebebe4;" readonly="true"
					disabled="disabled" size="11" id="pstate" />
				</td>
			</tr>
			<tr>
				<td height="20">Address</td>
				<td colspan="5"><input type="text"
					onkeypress="return check(event)" name="address" data-maxsize="65"
					readonly="true" style="padding-left: 3px; width: 99%;"
					disabled="disabled" size="11" id="paddress" />
				</td>
				<td>Phone (0)</td>
				<td><form:input type="text" onkeypress="return check(event)"
						path="PurchaseOrderMasterDTO.phoneOffice" data-maxsize="65"
						size="11" id="phoneOffice" readonly="true"/>
				</td>
				<td>Contact person</td>
				<td><form:input type="text" onkeypress="return check(event)"
						style="width:100%" onblur="valid(this)"
						path="PurchaseOrderMasterDTO.contactPerson" data-maxsize="65"
						size="11" readonly="true" id="contactPerson" />
				</td>
			</tr>
			<table height="65" width="967" border="0" class="tableview">
				<tr>
					<td height="20">Item Group<span style="color: #FF0000"></span>
					</td>
					<td colspan="1">
								<form:select
									path="PurchaseOrderMasterDTO.itemGroupFlagDTO.itemGroupFlagId"
									items="${itemGroupList}" itemLabel="itemGroupFlagName"
									itemValue="itemGroupFlagId" id="itemGroupFlagId"
									 class="validate[required]">
								</form:select>
					</td>
					<td colspan="1">Supplier Reference</td>
					<td colspan="3"><form:input type="text"
							path="PurchaseOrderMasterDTO.supplierReference"
							onkeypress="return check(event)" data-maxsize="65"
							id="supplierReference"
							style="    padding-left: 1px;    width: 98%;" size="11" />
					</td>
					<td>Payment Terms</td>
					<td colspan="3"><form:input type="text"
							onkeypress="return check(event)"
							path="PurchaseOrderMasterDTO.paymentTerms" data-maxsize="65"
							id="paymentTerms" style="width:99%" size="11" />
					</td>
				</tr>
				<tr>
					<td height="20">Preferred Transporter</td>
					<td colspan="1"><form:select
							path="PurchaseOrderMasterDTO.transportDTO.transporterId"
							items="${transporterList}" itemLabel="transName"
							itemValue="transporterId" id="transporterId"
							class="validate[required]">
						</form:select>
					</td>
					<td>Our Reference</td>
					<td><form:input type="text" onkeypress="return check(event)"
							path="PurchaseOrderMasterDTO.ourReference" data-maxsize="65"
							id="ourReference" style="width:90%" size="11" />
					</td>

					<td>Desire Deliver Date</td>
					<td><form:input onkeypress="return check(event)" type="text"
							path="PurchaseOrderMasterDTO.desireDeliveryDate"
							class="datepicker2" id="desireDeliveryDate" readonly="true"
							style="width:94%;" size="11" />
					</td>
					<td>Delivery Terms</td>
					<td colspan="3"><form:input onkeypress="return check(event)"
							type="text" path="PurchaseOrderMasterDTO.delivery_terms"
							data-maxsize="65" id="delivery_terms" style="width:99%" size="11" />
					</td>
				</tr>
			</table>
			<div style="width: 906px; margin: 0 auto;">
				<span style="margin-left: 80px;" class="errmsg"></span>
			</div>
			<div class="gridheadingdiv">
				<table width="668" class="fixmyheader" id="fixmyheader-8">
					<thead>
						<tr>
							<td width="34"><div align="center">
									<strong>S No.</strong>
								</div>
							</td>
							<td width="172"><div align="center">
									<strong> <c:choose>
											<c:when test="${opr=='E'}">
												<input class="newWindow"
													style="font-size: 11px; font-weight: bold; width: 12px; padding: 0 0 0 0px;"
													type="submit" value=" " />
											</c:when>
											<c:otherwise>

												<input class="newWindow1" onclick="return checkItem();"
													style="font-size: 11px; font-weight: bold; width: 13px;"
													type="submit" value=" " />
											</c:otherwise>
										</c:choose> &nbsp;&nbsp;Item Name </strong>
								</div>
							</td>
							<td width="73"><div align="center">
									<strong>UOM</strong>
								</div>
							</td>
							<td width="54"><div align="center">
									<strong>PO Qty</strong>
								</div>
							</td>
							<td width="88"><div align="center">
									<strong>Purchase Rate</strong>
								</div>
							</td>
							<td width="91"><div align="center">
									<strong>Item Value </strong>
								</div>
							</td>
							<td width="77"><div align="center">
									<strong>Discount % </strong>
								</div>
							</td>

							<td width="68"><div align="center">
									<strong>Excise %</strong>
								</div>
							</td>

							<td width="77"><div align="center">
									<strong>Cess % </strong>
								</div>
							</td>
							<td width="77"><div align="center">
									<strong>H. Cess % </strong>
								</div>
							</td>

							<td width="91"><div align="center">
									<strong>Tax %</strong>
								</div>
							</td>
							<td width="91"><div align="center">
									<strong>Amount</strong>
								</div>
							</td>
							<td width="120"><div align="center"
									title="Under Delivery Tolerance (in %)">
									<strong>(+) Tolerance %</strong>
								</div>
							</td>
							<td width="100"><div align="center"
									title="Over Delivery Tolerance (in %)">
									<strong>(-) Tolerance %</strong>
								</div>
							</td>
							<td width="106"><div align="center">
									<strong>Remark</strong>
								</div>
							</td>
							<td width="43"><div align="center">
									<strong>Action</strong>
								</div>
							</td>
						</tr>
					</thead>
					<tbody>
						<%
							int i = 1;
						%>
						<c:forEach
							items="${purchaseOrderMasterForm.purchaseOrderMasterDTO.purchaseOrderDetailDTOList}"
							var="e" varStatus="s">

							<tr>
								<td width="24">${s.count} <input type="hidden" name="snoid"
									value="${s.count}" id="snoid"></td>

								<td width="162">&nbsp;${e.itemDTO.itemName}</td>
								<td width="63">&nbsp;${e.measurementUnitName}</td>
								<td width="44"><form:input
										path="purchaseOrderMasterDTO.purchaseOrderDetailDTOList[${s.index}].itemQuantity"
										class="quantity1"
										style="text-align: right;  width:100%; border:1px solid #7f9db9; "
										data-maxsize="15" size="8" id="itemQuantity${s.index}" />
								<form:hidden path="purchaseOrderMasterDTO.purchaseOrderDetailDTOList[${s.index}].indentNumber" data-maxsize="15" size="8" id="indentNumber${s.index}" />
								</td>
								<td width="78"><form:input
										path="purchaseOrderMasterDTO.purchaseOrderDetailDTOList[${s.index}].purchaseRate"
										class="quantity1"
										style="text-align: right; width:100%; border:1px solid #7f9db9; "
										data-maxsize="15" size="8" id="purchaseRate${s.index}" />
								</td>
								<td style="text-align: right;" width="81"><form:input
										path="purchaseOrderMasterDTO.purchaseOrderDetailDTOList[${s.index}].itemValue"
										style=" width:100%; border:1px;  background-color:#ebebe4;"
										class="quantity validate[custom[number]]" data-maxsize="15"
										readonly="true" size="8" id="itemValue${s.index}" /></td>
								<td width="67"><form:input
										path="purchaseOrderMasterDTO.purchaseOrderDetailDTOList[${s.index}].discountPerc"
										style="text-align:right; border:1px solid #7f9db9; width:100%"
										class="quantity" data-maxsize="15" size="8" id="discountPerc" />
									<form:hidden
										path="purchaseOrderMasterDTO.purchaseOrderDetailDTOList[${s.index}].discountAmount"
										style="text-align:right; border:1px solid #7f9db9; width:100%"
										class="quantity validate[custom[number]]" data-maxsize="15"
										size="8" id="discountAmount" /></td>

								<td style="text-align: right;" width="58"><form:input
										path="purchaseOrderMasterDTO.purchaseOrderDetailDTOList[${s.index}].itemDTO.excisePerc"
										style=" width:100%; border:1px; background-color:#ebebe4;"
										class="quantity" data-maxsize="15" readonly="true" size="8"
										id="excisePerc${s.index}" /></td>
								<td width="67"><form:input
										path="purchaseOrderMasterDTO.purchaseOrderDetailDTOList[${s.index}].educationCessPerc"
										style="text-align:right; border:1px solid #7f9db9; width:100%"
										class="quantity" readonly="true" data-maxsize="5" size="8"
										id="educationCessPerc" /> <form:hidden
										path="purchaseOrderMasterDTO.purchaseOrderDetailDTOList[${s.index}].educationCessAmount"
										style="text-align:right; border:1px solid #7f9db9; width:100%"
										class="quantity validate[custom[number]]" data-maxsize="5"
										size="8" id="educationCessAmount" /></td>
								<td width="67"><form:input
										path="purchaseOrderMasterDTO.purchaseOrderDetailDTOList[${s.index}].highEducationCessPerc"
										readonly="true"
										style="text-align:right; border:1px solid #7f9db9; width:100%"
										class="quantity" data-maxsize="5" size="8"
										id="highEducationCessPerc" /> <form:hidden
										path="purchaseOrderMasterDTO.purchaseOrderDetailDTOList[${s.index}].highEducationCessAmount"
										style="text-align:right; border:1px solid #7f9db9; width:100%"
										class="quantity validate[custom[number]]" data-maxsize="5"
										size="8" id="highEducationCessAmount" /></td>

								<td style="text-align: right;" width="81"><form:input
										path="purchaseOrderMasterDTO.purchaseOrderDetailDTOList[${s.index}].taxPerc"
										style=" width:100%; border:1px; background-color:#ebebe4;"
										class="quantity validate[custom[number]]" data-maxsize="15"
										readonly="true" size="8" id="taxPerc${s.index}" /></td>
								<td style="text-align: right;" width="81"><form:input
										path="purchaseOrderMasterDTO.purchaseOrderDetailDTOList[${s.index}].netAmount"
										style=" width:100%; border:1px; background-color:#ebebe4;"
										class="quantity validate[custom[number]]" data-maxsize="15"
										readonly="true" size="8" id="netAmount${s.index}" /></td>
								<td width="110"><form:input
										path="purchaseOrderMasterDTO.purchaseOrderDetailDTOList[${s.index}].itemUnderDeliveryTolerancePer"
										class="quantity1"
										style=" border:1px solid #7f9db9; width:100%; text-align:center; "
										data-maxsize="5" size="8" id="itemQuan" align="right" />
								</td>
								<td width="90"><form:input
										path="purchaseOrderMasterDTO.purchaseOrderDetailDTOList[${s.index}].overDeliveryTolerancePer"
										size="8" id="itemQuanti"
										style=" border:1px solid #7f9db9;  text-align:center; width:100%"
										data-maxsize="5" align="right" />
								</td>
								<td width="96"><form:input
										path="purchaseOrderMasterDTO.purchaseOrderDetailDTOList[${s.index}].itemRemark"
										onkeypress="return check(event)" data-maxsize="50"
										style="  width:100%; border:1px solid #7f9db9; " size="8"
										id="itemRemark" />
								</td>
								<td width="32">
								<c:if test="${opr=='V' && opr!=null}">
								<img src="static/images/drop.png" class="dltImg"
										title="Delete Record" alt="" />
								</c:if>
								<c:if test="${opr!='V'}">
								<c:url value="remove_grid_item"	var="remove_url">
										<c:param name="id" value="${s.index}"></c:param>
										<c:param name="opr" value="${opr}"></c:param>
										</c:url> <a href="${remove_url}">
									<img src="static/images/drop.png" class="dltImg"
										title="Delete Record" alt="" />
								</a>
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
			<table height="65" width="967" border="0"
				style="width: 660px; float: left;" class="tableview">
				<tr>
					<td width="86" height="20"><strong>Item Count</strong>
					</td>
					<td width="88"><input name="" value="<%=i - 1%>"
						data-maxsize="15" readonly="true" style="width: 75px;" size="18"
						id="itemCountId" /> <%-- <form:input type="text"
						path="purchaseOrderMasterDTO.count"
						data-maxsize="15" readonly="true" 
						disabled="disabled" size="11" id="count" />
		 --%></td>
					<td width="83">Form required</td>
					<td width="106"><div
							style="border: solid 1px; height: 20px; width: 91%; border-color: #7f9db9; background-color: #FFF;">
							<form:radiobutton class="validate[required] radio"
								path="purchaseOrderMasterDTO.formReqFlag" value="1"
								style="width: 10px;" id="formReqFlag1" />
							Yes
							<form:radiobutton class="validate[required] radio"
								path="purchaseOrderMasterDTO.formReqFlag" style="width: 10px;"
								id="formReqFlag" value="0" />
							No
						</div>
					</td>
					<td width="89"><span style="font-weight: bold"> Form
							Type<span style="color: #FF0000"></span> </span>
					</td>
					<td width="197"><form:select
							path="purchaseOrderMasterDTO.formTypeId" items="${formType}"
							itemLabel="name" itemValue="mastersId" style="width:61%;" id="select5">
						</form:select>
					</td>
				</tr>
				<tr>
					<td height="28" colspan="1">PO Valid Upto
					<td><form:input type="text"
							path="purchaseOrderMasterDTO.poValidUptoDate" data-maxsize="15"
							size="11" class="datepicker" id="" />
					</td>
					<td>Form No.</td>
					<td><form:input id="formnumber" class="quantity1" type="text"
							size="15" data-maxsize="35" style="  text-align:right;"
							path="purchaseOrderMasterDTO.formnumber" />
					</td>
					<td>Form Date</td>
					<td><form:input type="text"
							path="purchaseOrderMasterDTO.formDate" class="datepicker"
							id="formDate" readonly="true" size="11" style="width:60%" />
					</td>
				</tr>
				<tr>

					<td height="29">PO Remark</td>
					<td colspan="5"><form:textarea id="poRemark"
							onkeypress="return check(event)" type="text" size="16"
							data-maxsize="150" style=" padding-left:3px;
    width: 86%;"
							path="purchaseOrderMasterDTO.poRemark" />
					</td>

				</tr>
			</table>
			<table height="65" width="967" border="0"
				style="float: right; margin-right: 8px; width: 265px;"
				class="tableview">
				<tr>
					<td width="199"><strong>Item Value</strong>
					</td>
					<td></td>
					<td width="283"><form:input
							class="quantity validate[custom[number]]"
							path="purchaseOrderMasterDTO.itemValue" data-maxsize="15"
							readonly="true"
							style="text-align:right;background-color:#ebebe4;"
							size="11" id="itemValue" />
					</td>
				</tr>
				<tr>
					<td nowrap="true">Discount Total (-)</td>
					<td></td>
					<td><form:input class="quantity validate[custom[number]]"
							path="purchaseOrderMasterDTO.discountAmount" data-maxsize="15"
							readonly="true"
							style="text-align:right;background-color:#ebebe4;"
							size="11" id="discountAmount" />
					</td>

				</tr>

				<tr>
					<td>Excise Total (+)</td>
					<td></td>
					<td><form:input class="quantity validate[custom[number]]"
							path="purchaseOrderMasterDTO.exciseDutyAmount" data-maxsize="15"
							readonly="true"
							style="text-align:right;background-color:#ebebe4;"
							size="11" id="exciseTotal" />
					</td>

				</tr>
				<tr>

					<td>Cess % (on Excise) (+)</td>
					<td><form:input
							path="purchaseOrderMasterDTO.educationCessPerc"
							style="text-align:right; width:97%"
							class="quantity validate[custom[number]]" data-maxsize="15"
							size="8" id="cessPerceId" />
					</td>
					<td><form:input
							path="purchaseOrderMasterDTO.educationCessAmount"
							class="quantity validate[custom[number]]" data-maxsize="15"
							readonly="true"
							style="text-align:right;background-color:#ebebe4;"
							 size="11" id="cessAmt" />
					</td>
				</tr>
				<tr>
					<td>H. Cess % (on Excise) (+)</td>
					<td><form:input
							path="purchaseOrderMasterDTO.highEducationCessPerc"
							style="text-align:right; width:97%"
							class="quantity validate[custom[number]]" data-maxsize="15"
							size="8" id="hCessPerceId" />
					</td>
					<td><form:input
							path="purchaseOrderMasterDTO.highEducationCessAmount"
							class="quantity validate[custom[number]]" data-maxsize="15"
							readonly="true"
							style="text-align:right;background-color:#ebebe4;"
							 size="11" id="hcessAmt" />
					</td>
				</tr>

				<tr>
					<td>Taxable Total (+)</td>
					<td></td>
					<td><form:input class="quantity"
							path="purchaseOrderMasterDTO.taxableAmount" data-maxsize="15"
							readonly="true"
							style="text-align:right;background-color:#ebebe4;"
							size="11" id="taxableTotal" />
					</td>

				</tr>

				<tr>
					<td>Tax Total (+)</td>
					<td><form:input path="purchaseOrderMasterDTO.vatCstType"
							data-maxsize="15"
							style="text-align:right;background-color:#ebebe4;" size="11"
							id="taxtVatCstType" />
					</td>
					<td><form:input class="quantity"
							path="purchaseOrderMasterDTO.taxVatCstTotal" data-maxsize="15"
							readonly="true"
							style="text-align:right;background-color:#ebebe4;"
							 size="11" id="taxVatCstTotal" />
					</td>

				</tr>

				<tr>
					<td><strong>Net Amount </strong>
					</td>
					<td></td>
					<td><form:input type="text" class="quantity"
							path="purchaseOrderMasterDTO.poNetAmount" data-maxsize="15"
							readonly="true"
							style="text-align:right;background-color:#ebebe4;" size="11"
							id="poNetAmount" />
					</td>
				</tr>

			</table>
			<div class="btn" style="float: none;">
				<div class="savbtn">
					<c:choose>
						<c:when test="${opr=='R'}">
							<c:url value="remove_purchaseOrder" var="remove_url">
								<c:param name="id"
									value="${purchaseOrderMasterForm.purchaseOrderMasterDTO.poAutoId}"></c:param>
							</c:url>
							<%-- <a href="${remove_url}" class="removebtn"></a> --%>
						</c:when>
						<c:otherwise>
							<c:choose>
								<c:when test="${opr=='E'}">
									<input class="updatebtn" type="submit" value=" "
										onclick="return checkAmount();" />
									<div class="cancelbtn">
										<a href="get_purchaseOrder_list" class="cancelbtn"
											iconCls="icon-cancel"></a>
									</div>
								</c:when>
								<c:when test="${opr=='V'}">
							<c:forEach items="${roleAndRightsDTOList}" var="roleAndRights">
		   <c:if test="${roleAndRights.menuId==sessionScope.menuId}">
    	  	 <c:if test="${roleAndRights.editFlag=='true'}">
		  	 <input class="edit_btn"  type="button" onclick="editMethod();" value=""/>
    	      <a href="get_purchaseOrder_list"  class="cancelbtn" ></a>  
		     </c:if>
		      <c:if test="${roleAndRights.editFlag=='false'}">
		      <input class="edit_btn"  type="button" onclick="checkEdit();" value=""/>
    	      <a href="get_purchaseOrder_list"  class="cancelbtn" ></a>  
		      </c:if>
		    
          </c:if>
          </c:forEach>	
									
								</c:when>
								<c:when test="${opr=='add' || opr=='A' || opr=='Remove'}">
								
									<input class="submit"
										style="font-size: 11px; font-weight: bold; padding: 0 0 0 30px;"
										type="submit" value=" " onclick="return checkAmount();" />
									<div class="cancelbtn">
										<a href="get_purchaseOrder_list" class="cancelbtn"
											iconCls="icon-cancel"></a>
									</div>
								</c:when>
							</c:choose>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			</div>
			</form:form>