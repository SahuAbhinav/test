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
		var redrctUrl = 'get_grn_list';

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
			//	confirm('Are you sure you want to delete?');
			var delUrl = 'remove_grn?id=' + frank_param;
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


<script type="text/javascript">
function checkApproved(){
	
	alert("You can not edit / delete this record as it is already approved.");
	return false;
}


	function editMethod()
 	 { 
 	 var frank_param = $('#id').val();
 	 //	confirm('Are you sure you want to delete?');
	 var delUrl='get_grn?id='+frank_param+'&opr=E';
 	 window.self.location = delUrl;  
	}
 	</script>


<script type="text/javascript">


	function onSave() {
		
		
		if($("#snoid").val()==undefined){
			alert("Sorry you can not save record without item.");
			return false;
		}
		
		for ( var ele = 0; true; ele++) {
			var frm = document.forms[0];
			var qt = frm.elements["grnMasterDTO.grnDetailDTOList[" + ele
					+ "].receivedQty"];
			if (!qt) {
				break;
			}
			if (qt.value == '0.00') {
				 $('#showButton'+ele).hide();
				 $('#hideButton'+ele).show(); 
				 $('#checkShowId'+ele).hide();
				 $('#checkHideId'+ele).show();
				 alert('Recieve quantity can not be zero');
				 return false;
			}}
		return true;
	}
	
	
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
						
						function formChange() {
						
							var totalItemValue=0.0;
							var totalDiscount=0.0;
							var totalTaxableAmnt=0.0;
							var totalNetAmnt=0.0;
							var taxTotal=0.0;
							var billQuantity=0.0;
							var billQty=0;
							var recievQty=0;
							var itemExciseAmnt=0.0;
							var totalExciseAmnout=0;
							
							var totalReciveExciseAmnout=0;
							var totalReceivedBillExciseAmount=0;
							var totalReceivedEducationExciseAmount=0;
							var totalReceivedHighEducationExciseAmount=0;
							for ( var ele = 0; true; ele++) {
								var frm = document.forms[0];

								 billQty= frm.elements["grnMasterDTO.grnDetailDTOList["
															+ ele + "].billQty"];

								if (!billQty) {
									break;
								}
								  frm.elements["grnMasterDTO.grnDetailDTOList["+ ele + "].billQty"].value=Number(getFloat(billQty.value).toString().match(/^\d+(?:\.\d{0,6})?/));
								  var receivedBillExciseAmount=0;
								  var receivedHighEducationExciseAmount=0;
								  var receivedEducationExciseAmount=0;
								  var receivedItemVATAmount=0;
								
							   // validation start
							   recievQty= frm.elements["grnMasterDTO.grnDetailDTOList["+ ele + "].receivedQty"];
							   frm.elements["grnMasterDTO.grnDetailDTOList["+ ele + "].receivedQty"].value=Number(getFloat(recievQty.value).toString().match(/^\d+(?:\.\d{0,6})?/));
							   var shrotQty=	getFloat(billQty.value)-getFloat(recievQty.value);
							   frm.elements["grnMasterDTO.grnDetailDTOList["+ ele + "].shrotQty"].value=round(shrotQty,3);
							   var approvedQty= frm.elements["grnMasterDTO.grnDetailDTOList["+ ele + "].approvedQty"];
								  if(getFloat(approvedQty.value) > getFloat(recievQty.value)){
										alert('Approved qty can not be greater than reciev qty');
										frm.elements["grnMasterDTO.grnDetailDTOList["+ ele + "].approvedQty"].value='0.00';
								  }
							   var rejectedQty=getFloat(recievQty.value)-getFloat(approvedQty.value);
							   frm.elements["grnMasterDTO.grnDetailDTOList["+ ele + "].rejectedQty"].value=round(rejectedQty,3);
							// validation end	
							   
							 
							var recieveQuntity=getFloat(recievQty.value);
							   if(getFloat(recievQty.value) > getFloat(billQty.value)){
								   recieveQuntity=billQty.value;
								}
							//
						        itemExciseAmnt= frm.elements["grnMasterDTO.grnDetailDTOList["+ ele + "].itemBillExciseAmt"].value;
						        frm.elements["grnMasterDTO.grnDetailDTOList["+ ele + "].itemBillExciseAmt"].value=Number(getFloat(itemExciseAmnt).toString().match(/^\d+(?:\.\d{0,6})?/));
						       var itemBasicAmount= frm.elements["grnMasterDTO.grnDetailDTOList["+ ele + "].itemBasicAmount"];
						       frm.elements["grnMasterDTO.grnDetailDTOList["+ ele + "].itemBasicAmount"].value=Number(getFloat(itemBasicAmount.value).toString().match(/^\d+(?:\.\d{0,6})?/));
							// billQty.value
								if(itemExciseAmnt){
									 receivedBillExciseAmount=getFloat((recieveQuntity*itemExciseAmnt)/billQty.value);
								}
								
								var itemEducationCessAmount= frm.elements["grnMasterDTO.grnDetailDTOList["+ ele + "].itemEducationCessAmount"].value;
								frm.elements["grnMasterDTO.grnDetailDTOList["+ ele + "].itemEducationCessAmount"].value=Number(getFloat(itemEducationCessAmount).toString().match(/^\d+(?:\.\d{0,6})?/));
								if(itemEducationCessAmount){
									receivedEducationExciseAmount=getFloat((recieveQuntity*itemEducationCessAmount)/billQty.value);
								}
								
								var itemHighEducationCessAmount= frm.elements["grnMasterDTO.grnDetailDTOList["+ ele + "].itemHighEducationCessAmount"].value;
								frm.elements["grnMasterDTO.grnDetailDTOList["+ ele + "].itemHighEducationCessAmount"].value=Number(getFloat(itemHighEducationCessAmount).toString().match(/^\d+(?:\.\d{0,6})?/));
								if(itemHighEducationCessAmount){
								receivedHighEducationExciseAmount=getFloat((recieveQuntity*itemHighEducationCessAmount)/billQty.value);
								}
								var itemVAT= frm.elements["grnMasterDTO.grnDetailDTOList["+ ele + "].itemVatAmount"].value;
								frm.elements["grnMasterDTO.grnDetailDTOList["+ ele + "].itemVatAmount"].value=Number(getFloat(itemVAT).toString().match(/^\d+(?:\.\d{0,6})?/));
								if(itemVAT){
									receivedItemVATAmount=getFloat((recieveQuntity*itemVAT)/billQty.value);
								}
								
								if(receivedBillExciseAmount){
								frm.elements["grnMasterDTO.grnDetailDTOList["+ ele + "].receivedBillExciseAmt"].value=round(receivedBillExciseAmount,2);
								}
								if(receivedEducationExciseAmount){
								frm.elements["grnMasterDTO.grnDetailDTOList["+ ele + "].receivedEducationCessAmount"].value=round(receivedEducationExciseAmount,2);
								}
								if(receivedHighEducationExciseAmount){
								frm.elements["grnMasterDTO.grnDetailDTOList["+ ele + "].receivedHighEducationCessAmount"].value=round(receivedHighEducationExciseAmount,2);
								}
								
								if(receivedItemVATAmount){
									frm.elements["grnMasterDTO.grnDetailDTOList["+ ele + "].receivedItemVatAmount"].value=round(receivedItemVATAmount,2);
									
								}
							//
							
							 var approved= frm.elements["grnMasterDTO.grnDetailDTOList["+ ele + "].grnApprovalFlag"].checked;
							 if(approved){
								
								 $('#discountPer'+ele).prop('readOnly', true);
								 $('#purchaseRate'+ele).prop('readOnly', true);
								 $('#receivedQty'+ele).prop('readOnly', true);
								 $('#billPacketTot'+ele).prop('readOnly', true);
								 $('#billNetWeight'+ele).prop('readOnly', true);
								 $('#billTareWeight'+ele).prop('readOnly', true);
								 $('#itemVatAmount'+ele).prop('readOnly', true);
								 $('#itemHighEducationCessAmount'+ele).prop('readOnly', true);
								 $('#itemEducationCessAmount'+ele).prop('readOnly', true);
								 $('#itemBillExciseAmt'+ele).prop('readOnly', true);
								 $('#billQty'+ele).prop('readOnly', true);	
								 $('#itemBasicAmount'+ele).prop('readOnly', true);
								 $('#approvedQty'+ele).prop('readOnly', true);
								 $('#recTareWeight'+ele).prop('readOnly', true);
								 $('#recNetWeight'+ele).prop('readOnly', true);
								 $('#recPacketTot'+ele).prop('readOnly', true);	
								 $('#poQty'+ele).prop('readOnly', true);	
								 $('#containerDescription'+ele).prop('readOnly', true);
								  
								 /*  $("#grnApprovalFlag"+ele).click(function() {
								 if(confirm('Are you sure about approving the record, as you will not be able to edit / delete it after approval.')) 
						 		   { */
									 if($("#id").val()>0){
										 $('#showButton'+ele).show();
										  $('#hideButton'+ele).hide();
										  $('#checkShowId'+ele).show();
										  $('#checkHideId'+ele).hide(); 
									 }else{
										 $('#showButton'+ele).hide();
										 $('#checkShowId'+ele).hide();
									 }
									  
									  
						 		 	//}
								/*  else{
									 $('#showButton'+ele).hide();
									 $('#hideButton'+ele).show(); 
									 $('#checkShowId'+ele).hide();
									 $('#checkHideId'+ele).show();
						  			}
								 }); */
								 
								  
							 }else{
								 $('#showButton'+ele).hide();
								 $('#hideButton'+ele).show(); 
								 $('#checkShowId'+ele).hide();
								 $('#checkHideId'+ele).show();
							 }
								
							
							  var purchaseRate= frm.elements["grnMasterDTO.grnDetailDTOList["+ ele + "].purchaseRate"];
							  frm.elements["grnMasterDTO.grnDetailDTOList["+ ele + "].purchaseRate"].value=Number(getFloat(purchaseRate.value).toString().match(/^\d+(?:\.\d{0,6})?/));
							  frm.elements["grnMasterDTO.grnDetailDTOList["+ ele + "].itemValue"].value=round(getFloat(purchaseRate.value)*getFloat(recievQty.value),2);
							  var itemValue= frm.elements["grnMasterDTO.grnDetailDTOList["+ ele + "].itemValue"];
							  var vatCstTaxPerc= frm.elements["grnMasterDTO.grnDetailDTOList["+ ele + "].vatCstPerc"];
							  var discountPer= frm.elements["grnMasterDTO.grnDetailDTOList["+ ele + "].discountPer"]; 
							  var discountAmnt=(getFloat(itemValue.value)*getFloat(discountPer.value))/100;
							 
							  var taxableAmnt=getFloat(itemValue.value)-getFloat(discountAmnt);
							
							  var itemVatAmnt=  $("#itemVatId").val();
							  var taxAmnt=  getFloat((recieveQuntity*itemVatAmnt)/billQty.value);
							  
							  var netAmnt=(getFloat(itemValue.value))-getFloat(discountAmnt)+getFloat(taxAmnt);
							  frm.elements["grnMasterDTO.grnDetailDTOList["+ ele + "].vatCstAmount"].value=round(getFloat(taxAmnt),2); 
							  frm.elements["grnMasterDTO.grnDetailDTOList["+ ele + "].discountAmount"].value=round(getFloat(discountAmnt),2); 
							  frm.elements["grnMasterDTO.grnDetailDTOList["+ ele + "].netAmount"].value=round(getFloat(itemValue.value)-getFloat(discountAmnt),2); 
							  frm.elements["grnMasterDTO.grnDetailDTOList["+ ele + "].grossAmount"].value=round(getFloat(netAmnt)+receivedBillExciseAmount+receivedEducationExciseAmount+receivedHighEducationExciseAmount+receivedItemVATAmount,2);
								
								 totalItemValue +=getFloat(getFloat(itemValue.value)-getFloat(discountAmnt));
								 totalDiscount +=getFloat(discountAmnt);
								 //taxTotal +=getFloat(taxAmnt);
              					 totalNetAmnt +=getFloat(netAmnt);
              					billQuantity=billQty.value;
              					taxTotal +=receivedItemVATAmount;
              					totalExciseAmnout +=(receivedBillExciseAmount+receivedEducationExciseAmount+receivedHighEducationExciseAmount+receivedItemVATAmount);
              					totalReciveExciseAmnout+=(receivedBillExciseAmount+receivedEducationExciseAmount+receivedHighEducationExciseAmount);
							
							
              					 totalReceivedBillExciseAmount +=receivedBillExciseAmount;
    							 totalReceivedEducationExciseAmount +=receivedEducationExciseAmount;
    							 totalReceivedHighEducationExciseAmount +=receivedHighEducationExciseAmount;
							
							}
							var entryTax= $("#entryTax").val();
							
						    //var entryTaxAmount=((totalNetAmnt+totalExciseAmnout)*entryTax)/100;
						    var entryTaxAmount=((totalNetAmnt+totalReciveExciseAmnout)*entryTax)/100;
						  
						    
						    
							var v= frm.elements["grnMasterDTO.otherAmount"].value;
							var plus="+";
							var minus="-";
							var as="0";
							
							if(v!='0.00'){
								
							 //if (v.startsWith(plus)){
							 if (v.indexOf('+') === 0){
							 as =v.replace('+', '');
							 totalNetAmnt = totalNetAmnt + getInt(as);
							}else
							if (v.indexOf('-') === 0){
								 as =v.replace('-', '');
								 totalNetAmnt=totalNetAmnt-getInt(as);
							}else{
								totalNetAmnt = totalNetAmnt + getInt(v);
							 }  
							} 
						    
						    
						    
						    
						    
						    
						    if(totalItemValue>0){
						    $("#entryTaxAmount").val(round(entryTaxAmount,2));
						    frm.elements["grnMasterDTO.itemValue"].value =round(totalItemValue,2);
							frm.elements["grnMasterDTO.discountAmount"].value =round(totalDiscount,2);
							frm.elements["grnMasterDTO.vatAmount"].value = round(taxTotal,2);
							
							var frieghtAmnt= $("#freightAmount").val();
							frm.elements["grnMasterDTO.grnNetAmount"].value = round(totalNetAmnt+totalExciseAmnout+entryTaxAmount+getFloat(frieghtAmnt),0);
							$("#exciseInclCess").val(round(totalReciveExciseAmnout,2));
							frm.elements["grnMasterDTO.receivedBillExciseAmount"].value = round(totalReceivedBillExciseAmount,2);
							frm.elements["grnMasterDTO.receivedEducationCessAmount"].value = round(totalReceivedEducationExciseAmount,2);
							frm.elements["grnMasterDTO.receivedHighEducationCessAmount"].value = round(totalReceivedHighEducationExciseAmount,2);
							} else{
								$("#entryTaxAmount").val(0);
							    frm.elements["grnMasterDTO.itemValue"].value =round(0,2);
								frm.elements["grnMasterDTO.discountAmount"].value =round(0,2);
								frm.elements["grnMasterDTO.vatAmount"].value = round(0,2);
								frm.elements["grnMasterDTO.grnNetAmount"].value = round(0,0);
								$("#exciseInclCess").val(round(0,2));
								frm.elements["grnMasterDTO.receivedBillExciseAmount"].value = round(0,2);
								frm.elements["grnMasterDTO.receivedEducationCessAmount"].value = round(0,2);
								frm.elements["grnMasterDTO.receivedHighEducationCessAmount"].value = round(0,2);
								
								
							}
						}

						$('#formID').change(function() {
							formChange();
						});
						         formChange();
					    });
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


	$(document).ready(function() {

						/* $("button").button();
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

 */						$('#fixmyheader-8').fixheadertable(
								{
									caption : 'My header is fixed !',
									height : 158,
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

	$(document).ready(function() {
		function abc(cb) {

			$.get('getPartyById', {
				id : $(cb).val()
			}, function(data) {
				//		alert('hello');
				$("#pcity").val(data.cityName);
				$("#pstate").val(data.state);
				$("#paddress").val(data.billingAddress);
				$("#pphone").val(data.phoneO1);
				$("#pcontact").val(data.contactPerson1);
				
				//$('#transporterId').val(data.transporterId);
				
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
	$(".newWindow").click(function(e) {
	document.forms["formID"].action = "show_item_list_grn?opr=E&itemFor=G";
	document.forms["formID"].submit();
	});});
	$(document).ready(function() {
		//called when key is pressed in textbox
$(".getPurchaseOrderItem").click(function(e) {
document.forms["formID"].action = "show_item_list_grn?opr=GetPO";
document.forms["formID"].submit();
});});
	
	$(document)
			.ready(
					function() {
						$(".newWindow1")
								.click(
										function(e) {
											
												document.forms["formID"].action = "show_item_list_grn?opr=A&itemFor=G";
												document.forms["formID"]
														.submit();
											
										});
					});
</script>

<c:if test="${not empty(grnMasterForm.grnMasterDTO.grnDetailDTOList)}">
	<script>
		$(document).ready(function() {
			//alert("Hello");
			$("#itemGroupFlagId").attr("disabled", "disabled");
		});
	</script>
</c:if>

<c:if test="${opr=='E'}">
	<script>
		$(document).ready(function() {
			//alert("Hello");
			$("#itemGroupFlagId").attr("disabled", "disabled");
		});
	</script>
</c:if>

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

	<c:if test="${opr=='E' }">
	<script type="text/javascript">
		$(document).ready(function() {
		$('.datepicker1').attr('disabled','disabled');
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
	
		 var l=$('#lastDate').val();
		 
		$(".supplierBillDate").datepicker({
			changeMonth : true,
			changeYear : true,
			yearRange : '-99:+10',
			dateFormat : 'dd-M-yy hh:mm',
			maxDate : new Date()
		});

		$(".datepicker1").datepicker({
			changeMonth : true,
			changeYear : true,
			yearRange : '-99:+10',
			dateFormat : 'dd-M-yy HH:mm:ss',
			maxDate : new Date()
			, minDate: new Date(l)
		});
		
		if ($('#goodsReceiveDate').val() == '') {
			$('#goodsReceiveDate').val($('#date').val());
		}
		
	
		
		 $("#goodsReceiveDate").datepicker({
			changeMonth : true,
			changeYear : true,
			yearRange : '-99:+10',
			dateFormat : 'dd-M-yy'
			//minDate: $("#grnMaxDate").val()
		}); 

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

		$("#date").change(function() {
			abc();
		});

		abc();
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
	
 	$(function() {
		 var flag= $('input:checkbox[name=showAllPartyId]').is(':checked');
		//checked
		 var checked = $('input[type=checkbox]:checked').val() != undefined;
		 //unchecked
		 var unchecked = $('input[type=checkbox]:checked').val() == undefined;
		 
		if(checked ==true){
			$('#allPartyId').show();
			$('#partyId').hide();
			$('.allPartyId').removeAttr('disabled');
			$('.party').attr('disabled','disabled');
		}else{
		$('#allPartyId').hide();
		$('#partyId').show();
		$('.allPartyId').attr('disabled','disabled');	
		$('#showAllPartyId').click(function() {
		var staus=	$("#showAllPartyId").attr("checked");
			if(staus=='checked'){
			$('#allPartyId').show();
			$('#partyId').hide();
			$('.allPartyId').removeAttr('disabled');
			$('.party').attr('disabled','disabled');
			}if(staus ==undefined){
				$('#allPartyId').hide();
				$('#partyId').show();
				$('.party').removeAttr('disabled');
				$('.allPartyId').attr('disabled','disabled');
			}
	});
		}
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

<form:form name="input" id="formID" action="saveGrn" method="post"
	modelAttribute="grnMasterForm">

	<form:hidden path="grnMasterDTO.grnAutoId" id="id" />
	<form:hidden path="lastGrnDate" id="lastDate" />
	<form:hidden path="grnMasterDTO.purchaseOrderDTO.poAutoId" id="poAutoId" />
	<div class="panel-header" style="width: 960px;">
		<div class="panel-title">GRN Entry</div>
		<div class="panel-tool"></div>
	</div>

	<div align="left" class="bkgColor"
		style="height: auto; padding-bottom: 14px; width: 969px;">

		<table height="159" width="967" class="tableview" border="0"
			style="margin-top: 12px;">
			<tr>	
				<td height="20">Branch Name<span style="color: #FF0000">*</span>
				</td>
				<td colspan="3">
				<form:hidden 
						path="grnMasterDTO.grnMaxDate" id="grnMaxDate" />
				<form:select
						path="grnMasterDTO.branchDTO.branchId"
						onkeypress="return check(event)" items="${branchList}"
						itemLabel="branch" itemValue="branchId" style="width:100%"
						class="validate[required] text-input" id="branchId"></form:select>
				</td>
			</tr>
			<tr>
				<td width="113" height="20">GRN Series <span
					style="color: #FF0000">*</span>
				</td>
				<td width="176"><form:input type="text"
						path="grnMasterDTO.transactionSeries"
						class="validate[required] text-input" data-maxsize="15"
						readonly="true" disabled="disabled" size="8"
						id="transactionSeries" style="background-color:#ebebe4;" /> <form:input
						type="text" class="validate[required] text-input"
						path="grnMasterDTO.grnId" data-maxsize="15" readonly="true"
						disabled="disabled" size="11" id="grnId"
						style="background-color:#ebebe4;width:39%;" />
						
				</td>
				<td width="110">GRN No</td>
				<td width="147"><form:input type="text"
						path="grnMasterDTO.grnNumber" style="width:98%" data-maxsize="15"
						readonly="true" disabled="disabled" size="11" id="iNumber" />
				</td>
				<td width="76">Date<span style="color: #FF0000">*</span>
				</td>
				<td width="119"><form:input type="text"
						path="grnMasterDTO.grnDate"
						class="datepicker1 validate[required] text-input" id="date"
						size="11" readonly="true"
						style="background-color:#ebebe4;width:94%;" />
				</td>



				<td>LR No.</td>
				<td><form:input type="text" data-maxsize="15"
						onkeypress="return check(event)" path="grnMasterDTO.lrNo"
						id="lrNo" style="width:98%" size="11" />
				</td>
				<td width="123">LR Date</td>
				<td><form:input type="text" path="grnMasterDTO.lrDate"
						class="datepicker1" style="right;background-color:#ebebe4;"
						id="lrDate" size="11" readonly="true" />
				</td>




			</tr>
			<tr>
				<td height="20">Supplier Name<span style="color: #FF0000">*</span>
				</td>
				<td colspan="3">
				<div id="partyId">
						<form:select path="grnMasterDTO.partyDTO.partyId"
							onkeypress="return check(event)" items="${partyList}"
							itemLabel="partyName" itemValue="partyId" style="width:100%"
							class="validate[required] text-input party" id="party"></form:select>
</div><div id="allPartyId">
						<form:select path="grnMasterDTO.partyDTO.partyId"
							onkeypress="return check(event)" items="${allPartyList}"
							itemLabel="partyName" itemValue="partyId" style="width:100%"
							class="validate[required] text-input allPartyId" id="party"></form:select>
							 </div>
				</td>
				<td>All Party</td>
				<td><form:checkbox path="grnMasterDTO.showAllPartyFlag" value="1" id="showAllPartyId" onkeypress="return check(event)"
					 data-maxsize="15" 
					size="11"  />
				</td>
				<td>City</td>
				<td><input type="text" onkeypress="return check(event)"
					name="city" data-maxsize="15" readonly="true" disabled="disabled"
					size="11" id="pcity" style="background-color: #ebebe4;" />
				</td>

				<!-- <td>State</td>
				<td><input type="text" onkeypress="return check(event)" name="state" data-maxsize="15" readonly="true"
					disabled="disabled" size="11" id="pstate"  style="background-color:#ebebe4;"/></td> -->

				<td>Goods Receive Date</td>
				<td><form:input type="text"
						path="grnMasterDTO.goodsReceiveDate" class="validate[required] text-input goodsReceiveDate"
						style="right;background-color:#ebebe4;" id="goodsReceiveDate"
						size="11" readonly="true" />
				</td>
			</tr>
			<tr>
				<td height="20">Address</td>
				<td colspan="3"><input type="text"
					onkeypress="return check(event)" name="state" data-maxsize="15"
					readonly="true" disabled="disabled" size="11"
					style="background-color: #ebebe4; width: 98%; padding-left: 3px;"
					id="paddress" />
				</td>
				<td>Phone 1 (0)</td>
				<td><input type="text" onkeypress="return check(event)"
					name="state" data-maxsize="15" readonly="true"
					style="background-color: #ebebe4; width: 92%;" disabled="disabled" size="11"
					id="pphone" />
				</td>
				<td height="20">Transporter Name</td>
				<td ><form:select
						path="grnMasterDTO.transportDTO.transporterId"
						items="${transporterList}" itemLabel="transName"
						itemValue="transporterId" id="transporterId" style="width:96%"
						class="validate[required]">
					</form:select>
				</td>
				<td>Goods Rec. Type<!-- Contact person --></td>
				<td>
					<form:select path="grnMasterDTO.goodsReceiveType" 
						style="width:100%" id="select4">
						<form:option value="Against PO" >Against PO</form:option>
						<form:option value="Purchase w/o PO">Purchase w/o PO</form:option>
						<form:option value="Goods recd from other store">Goods recd from other store</form:option>
						<form:option value="Goods returned from customer">Goods returned from customer</form:option>
						<form:option value="Goods returned against old goods issue">Goods returned against old goods issue</form:option>
						<form:option value="Goods returned from contractor">Goods returned from contractor</form:option>
						<form:option value="Goods returned form vendor">Goods returned form vendor</form:option>
						<form:option value="Others">Others</form:option>
					</form:select>
				<input type="hidden" onkeypress="return check(event)"
					name="state" data-maxsize="15" readonly="true"
					style="background-color: #ebebe4;" disabled="disabled" size="11"
					id="pcontact" />
				</td>
				
				
				
				
			</tr>
			<tr>
				
				<td>Supplier Bill No</td>
				<td><form:input type="text" 
						path="grnMasterDTO.supplierBillNo" data-maxsize="15"
						id="supplierBillNo" style="width:99%" size="11" />
				</td>
				<td>Sup. Bill Dt.</td>
				<td><form:input type="text"
						path="grnMasterDTO.supplierBillDate" class="datepicker1"
						id="supplierBillDate" style="background-color:#ebebe4;width:92%;"
						size="11" readonly="true" />
				</td>
				<td>Entry Tax(%)</td>
				<td><form:input type="text" 
						path="grnMasterDTO.entryTax" data-maxsize="15"
						class="digitOnly"
						id="entryTax" style="text-align:right; width:99%" size="11" />
				</td>
				
				<td>Supplier Bill Amt</td>
				<td><form:input type="text"
						path="grnMasterDTO.supplierBillAmount"
						style="text-align:right; width:98%" class="quantity digitOnly"
						data-maxsize="15" size="8" id="supplierBillAmount" />
				</td>
				
				
			
				

				<td>Vehicle no.</td>
				<td><form:input type="text" path="grnMasterDTO.vehicleNumber"
						data-maxsize="25" onkeypress="return check(event)"
						id="vehicleNumber" style="width:100%" size="11" />
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
						<td width="35"><div align="center">
								<strong>S No.</strong>
							</div>
						</td>
					<td width="135"><div align="center">PO No. 
					 <input class="getPurchaseOrderItem" style="font-size: 11px; font-weight: bold; width: 13px;"	type="submit" name="" value=" " />
					
					<%-- <c:choose>
					<c:when test="${opr=='E'}">
					<a href="get_purchaseOrder_list?redirect=grn&opr=E" class="newWindow">
					</c:when>
					<c:otherwise>
					 <a href="get_purchaseOrder_list?redirect=grn&opr=A"  class="newWindow"> 
					</c:otherwise>
					</c:choose> <img src="static/images/search_small.png" title="Search" alt="" class="newWindow"/></a> --%>
</div>
				</td>
	<td width="135"><div align="center">
	<strong>
	 <c:choose>
		<c:when test="${opr=='E'}">
		 <input class="newWindow" style="font-size: 11px; font-weight: bold; width: 13px;"	type="submit" value=" " />
		</c:when>
		<c:otherwise>
			<input class="newWindow1" style="font-size: 11px; font-weight: bold; width: 13px;"	type="submit" value=" " />
		</c:otherwise>
	</c:choose> &nbsp;&nbsp;Item Name </strong>

							</div>
						</td>

						<td width="60"><div align="center">
								<strong>UOM</strong>
							</div>
						</td>
						<td width="90"><div align="center">
		<strong>Department</strong>
		</div>
		</td>
						<td width="70"><div align="center">
								<strong>Bill Qty</strong>
							</div>
						</td>
						<td width="90"><div align="center">
								<strong>Item Basic Amt.</strong>
							</div>
						</td>
						<td width="90"><div align="center">
								<strong>Item Excise Amt.</strong>
							</div>
						</td>
						<td width="90"><div align="center">
								<strong>Item Cess Amt.</strong>
							</div>
						</td><td width="90"><div align="center">
								<strong>Item H.Cess Amt.</strong>
							</div>
						</td><td width="70"><div align="center">
								<strong>VAT/CST</strong>
							</div>
						</td>
						<td width="90"><div align="center">
								<strong>Bill Tare Wt.</strong>
							</div>
						</td>
						<td width="90"><div align="center">
								<strong>Bill Net Wt.</strong>
							</div>
						</td>
						<td width="90"><div align="center">
								<strong>Bill Packet Tot. </strong>
							</div>
						</td>
						<td width="90"><div align="center">
								<strong>Rec. Qty </strong>
							</div>
						</td>
						
						
							<td width="90"><div align="center">
								<strong>Purchase Rate </strong>
							</div>
						</td>
						<td width="90"><div align="center">
								<strong>Item Value</strong>
							</div>
						</td>
						<td width="90"><div align="center">
								<strong>Short Qty.</strong>
							</div>
						</td>
						<td width="90"><div align="center">
								<strong>Approved Qty.</strong>
							</div>
						</td>
						<td width="90"><div align="center">
								<strong>Rejected Qty.</strong>
							</div>
						</td>
						<td width="90"><div align="center">
								<strong>Discount(%)</strong>
							</div>
						</td>
						<!-- <td width="90"><div align="center">
								<strong>Tax(%)</strong>
							</div>
						</td> -->
						<td width="90"><div align="center">
								<strong>Net Amount</strong>
							</div>
						</td>
						
							<td width="90"><div align="center">
								<strong>Rec. Excise Amt. </strong>
							</div>
						</td>
							<td width="90"><div align="center">
								<strong>Rec. Cess Amt.</strong>
							</div>
						</td>
							<td width="90"><div align="center">
								<strong>Rec. H.Cess Amt.</strong>
							</div>
						</td>
						<td width="90"><div align="center">
								<strong>Rec. Item VAT</strong>
							</div>
						</td><td width="90"><div align="center">
								<strong>Gross Amount</strong>
							</div>
						</td>
						<td width="90"><div align="center">
								<strong>Tare Wt.</strong>
							</div>
						</td>
						<td width="90"><div align="center">
								<strong>Net Wt.</strong>
							</div>
						</td>
						<td width="90"><div align="center">
								<strong>Packet Tot. </strong>
							</div>
						</td>
						<td width="80"><div align="center">
								<strong>PO Qty</strong>
							</div>
						</td>
						<td width="110"><div align="center">
								<strong>Remark</strong>
							</div>
						</td>
						<td width="70"><div align="center">
								<strong>Approved</strong>
							</div>
						</td>
						<td width="65"><div align="center">
								<strong>Action</strong>
						</div>
						</td>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${grnMasterForm.grnMasterDTO.grnDetailDTOList}"
						var="e" varStatus="s">

						<tr>
							<td width="25">${s.count}
							<input type="hidden" name="snoid" value="${s.count}" id="snoid" >
							<input type="hidden" value="${s.count}" id="itemId" />
							</td>
							<td width="125">
							<form:input
									path="grnMasterDTO.grnDetailDTOList[${s.index}].poNumber"
									style="border:1px solid #7f9db9;  width:100%" readonly="true"
									data-maxsize="15" size="8" id="poNumber${s.index}" />
							</td>
							<td width="125">&nbsp;${e.itemDTO.itemName}</td>
							<td width="50">&nbsp;${e.measurementUnitName}</td>
							
							 <td width="80"><form:select
						path="grnMasterDTO.grnDetailDTOList[${s.index}].departmentId" items="${deptTypeList}"
						itemLabel="name" itemValue="mastersId" id="mastersId"
						class="validate[required]"
						style="width: 100%; height: 20px;">
		  </form:select></td>
							<td width="60">
							<form:input
									path="grnMasterDTO.grnDetailDTOList[${s.index}].billQty"
									class="digitOnly validate[custom[number]]" 
									style="text-align:right; border:1px solid #7f9db9;  width:100%"
									data-maxsize="15" size="8" id="billQty${s.index}" />
							</td>
							
							<td width="80">
							<form:input
									path="grnMasterDTO.grnDetailDTOList[${s.index}].itemBasicAmount"
									class="digitOnly"
									style="text-align:right; border:1px solid #7f9db9;  width:100%"
									data-maxsize="15" size="8" id="itemBasicAmount${s.index}" />
							</td>
							<td width="80">
							<form:input
									path="grnMasterDTO.grnDetailDTOList[${s.index}].itemBillExciseAmt"
									class="digitOnly"
									style="text-align:right; border:1px solid #7f9db9;  width:100%"
									data-maxsize="15" size="8" id="itemBillExciseAmt${s.index}" />
							</td>
							<td width="80">
							<form:input
									path="grnMasterDTO.grnDetailDTOList[${s.index}].itemEducationCessAmount"
									class="digitOnly"
									style="text-align:right; border:1px solid #7f9db9;  width:100%"
									data-maxsize="15" size="8" id="itemEducationCessAmount${s.index}" />
							</td><td width="80">
							<form:input
									path="grnMasterDTO.grnDetailDTOList[${s.index}].itemHighEducationCessAmount"
									class="digitOnly"
									style="text-align:right; border:1px solid #7f9db9;  width:100%"
									data-maxsize="15" size="8" id="itemHighEducationCessAmount${s.index}" />
							</td><td width="60">
							<form:input
									path="grnMasterDTO.grnDetailDTOList[${s.index}].itemVatAmount"
									class="digitOnly"
									style="text-align:right; border:1px solid #7f9db9;  width:100%"
									data-maxsize="15" size="8" id="itemVatAmount${s.index}" />
							</td>
							
							<td width="80"><form:input
									path="grnMasterDTO.grnDetailDTOList[${s.index}].billTareWeight"
									class="quantity"
									style="text-align:right; border:1px solid #7f9db9;  width:100%"
									data-maxsize="15" size="8" id="billTareWeight${s.index}" />
							</td>
							
							<td width="80"><form:input
									path="grnMasterDTO.grnDetailDTOList[${s.index}].billNetWeight"
									class="quantity"
									style="text-align:right; border:1px solid #7f9db9;  width:100%"
									data-maxsize="15" size="8" id="billNetWeight${s.index}" />
							</td>
							
							<td width="80"><form:input
									path="grnMasterDTO.grnDetailDTOList[${s.index}].billPacketTot"
									class="quantity"
									style="text-align:right; border:1px solid #7f9db9;  width:100%"
									data-maxsize="15" size="8" id="billPacketTot${s.index}" />
							</td>
							<td width="80"><form:input
									path="grnMasterDTO.grnDetailDTOList[${s.index}].receivedQty"
									style="text-align:right; border:1px solid #7f9db9;  width:100%"
									class="digitOnly" data-maxsize="15" size="8"
									id="receivedQty${s.index}" />
							</td>
							
							<td width="80"><form:input
									path="grnMasterDTO.grnDetailDTOList[${s.index}].purchaseRate"
									style="text-align:right; border:1px solid #7f9db9;  width:100%"
									class="digitOnly" data-maxsize="15" size="8"
									id="purchaseRate${s.index}" />
							</td>
							<td width="80"><form:input
									path="grnMasterDTO.grnDetailDTOList[${s.index}].itemValue"
									style="text-align:right; border:1px solid #7f9db9;  width:100%" readonly="true"
									class="quantity" data-maxsize="15" size="8"
									id="itemValue${s.index}" />
							</td>

							<td width="80"><form:input
									path="grnMasterDTO.grnDetailDTOList[${s.index}].shrotQty"
									style="text-align:right; border:1px solid #7f9db9;  width:100%"
									class="quantity" data-maxsize="15" size="8" readonly="true"
									id="shrotQty${s.index}" />
							</td>
							<td width="80"><form:input
									path="grnMasterDTO.grnDetailDTOList[${s.index}].approvedQty"
									style="text-align:right; border:1px solid #7f9db9;  width:100%"
									class="digitOnly" data-maxsize="15" size="8"
									id="approvedQty${s.index}" />
							</td>
							<td width="80"><form:input
									path="grnMasterDTO.grnDetailDTOList[${s.index}].rejectedQty"
									readonly="true" style="text-align:right; border:1px solid #7f9db9;  width:100%"
									class="quantity" data-maxsize="15" size="8" 
									id="rejectedQty${s.index}" />
							</td>
                          <td width="80">
                          <form:input
									path="grnMasterDTO.grnDetailDTOList[${s.index}].discountPer"
									style="text-align:right; border:1px solid #7f9db9;  width:100%"
									class="quantity" data-maxsize="15" size="8" 
									id="discountPer${s.index}" />
									<form:hidden
									path="grnMasterDTO.grnDetailDTOList[${s.index}].discountAmount"
									style="text-align:right; border:1px solid #7f9db9;  width:100%"
									class="quantity" data-maxsize="15" size="8" 
									id="discountAmount${s.index}" />
									
									
									
									<form:hidden
									path="grnMasterDTO.grnDetailDTOList[${s.index}].vatCstPerc"
									style="text-align:right; border:1px solid #7f9db9;  width:100%" readonly="true"
									class="quantity" data-maxsize="15" size="8" 
									id="vatCstPerc${s.index}" />
									
									<form:hidden
									path="grnMasterDTO.grnDetailDTOList[${s.index}].vatCstAmount"
									style="text-align:right; border:1px solid #7f9db9;  width:100%"
									class="quantity" data-maxsize="15" size="8" 
									id="vatCstAmount${s.index}" />
							</td>
							
							
							
							<%--  <td width="80"><form:hidden
									path="grnMasterDTO.grnDetailDTOList[${s.index}].vatCstPerc"
									style="text-align:right; border:1px solid #7f9db9;  width:100%" readonly="true"
									class="quantity" data-maxsize="15" size="8" 
									id="vatCstPerc${s.index}" />
									
									<form:hidden
									path="grnMasterDTO.grnDetailDTOList[${s.index}].vatCstAmount"
									style="text-align:right; border:1px solid #7f9db9;  width:100%"
									class="quantity" data-maxsize="15" size="8" 
									id="vatCstAmount${s.index}" />
							</td> --%>
							 <td width="80">
							 <form:input
									path="grnMasterDTO.grnDetailDTOList[${s.index}].netAmount"
									style="text-align:right; border:1px solid #7f9db9;  width:100%"
									class="quantity" data-maxsize="15" size="8" readonly="true"
									id="netAmount${s.index}" />
							</td>
							
							
							<td width="80">
							<form:input
									path="grnMasterDTO.grnDetailDTOList[${s.index}].receivedBillExciseAmt"
									readonly="true" style="text-align:right; border:1px solid #7f9db9;  width:100%"
									class="quantity" data-maxsize="15" size="8"
									id="receivedBillExciseAmt${s.index}" />
							</td>
							<td width="80">
							<form:input
									path="grnMasterDTO.grnDetailDTOList[${s.index}].receivedEducationCessAmount"
									readonly="true" style="text-align:right; border:1px solid #7f9db9;  width:100%"
									class="quantity" data-maxsize="15" size="8"
									id="receivedEducationCessAmount${s.index}" />
							</td>
							<td width="80">    
							<form:input
									path="grnMasterDTO.grnDetailDTOList[${s.index}].receivedHighEducationCessAmount"
									readonly="true" style="text-align:right; border:1px solid #7f9db9;  width:100%"
									class="quantity" data-maxsize="15" size="8"
									id="receivedHighEducationCessAmount${s.index}" />
							</td><td width="80">    
							<form:input
									path="grnMasterDTO.grnDetailDTOList[${s.index}].receivedItemVatAmount"
									readonly="true" style="text-align:right; border:1px solid #7f9db9;  width:100%"
									class="quantity" data-maxsize="15" size="8"
									id="receivedItemVatAmount${s.index}" />
							</td><td width="80">    
							<form:input
									path="grnMasterDTO.grnDetailDTOList[${s.index}].grossAmount"
									readonly="true" style="text-align:right; border:1px solid #7f9db9;  width:100%"
									class="quantity" data-maxsize="15" size="8"
									id="grossAmount${s.index}" />
							</td>
							<td width="80"><form:input
									path="grnMasterDTO.grnDetailDTOList[${s.index}].recTareWeight"
									style="text-align:right; border:1px solid #7f9db9;  width:100%"
									class="quantity" data-maxsize="15" size="8"
									id="recTareWeight${s.index}" />
							</td>
							
							<td width="80"><form:input
									path="grnMasterDTO.grnDetailDTOList[${s.index}].recNetWeight"
									style="text-align:right; border:1px solid #7f9db9;  width:100%"
									class="quantity" data-maxsize="15" size="8"
									id="recNetWeight${s.index}" />
							</td>
							<td width="80"><form:input
									path="grnMasterDTO.grnDetailDTOList[${s.index}].recPacketTot"
									data-maxsize="15"
									style=" border:1px solid #7f9db9;  width:100%" size="8"
									id="recPacketTot${s.index}" />
							</td>
							<td width="70"><form:input
									path="grnMasterDTO.grnDetailDTOList[${s.index}].poQty"
									data-maxsize="15"
									style=" border:1px solid #7f9db9;  width:100%" size="8"
									id="poQty${s.index}" />
							</td>
							<td width="100"><form:input
									path="grnMasterDTO.grnDetailDTOList[${s.index}].containerDescription"
									data-maxsize="50"
									style=" border:1px solid #7f9db9;   width:100%" size="8"
									id="containerDescription${s.index}" />
							</td>
							<td width="60">
							
						<div id="checkHideId${s.index}">
							<form:checkbox
									path="grnMasterDTO.grnDetailDTOList[${s.index}].grnApprovalFlag"
									 value="1" 
									style="text-align:right; border:1px solid #7f9db9;  width:100%"
									data-maxsize="15" size="8" id="grnApprovalFlag${s.index}" />
								</div><div id="checkShowId${s.index}">
								<input type="checkbox" value="" checked="true" disabled="disabled">
								</div>	
							</td>
							
							<td width="55" style="text-align: center;">
						<div id="showButton${s.index}">
						<img src="static/images/drop.png" title="Delete Record" alt="" />
						</div> 
						<div id="hideButton${s.index}">
							<c:if test="${opr!='V' }">
							        <c:url value="addItemInGrn" var="remove_url">
									<c:param name="itemID" value="${s.index}"></c:param>
									<c:param name="Remove" value="Remove"></c:param>
									<c:param name="opr" value="${opr}"></c:param>
								    </c:url> <a href="${remove_url}"><img src="static/images/drop.png"  title="Delete Record" alt="" /></a>
								    </c:if>
							<c:if test="${opr=='V' }">
							<img src="static/images/drop.png" title="Delete Record" alt="" />
							</c:if>
							</div>
							
							</td>

						</tr>
					</c:forEach>

				</tbody>
			</table>
		</div>
		
		<table height="72" class="tableview" width="700" border="0"
			style="float: left; width: 725px;">
			<tr>

				<td width="93" height="25"><strong>Item Count</strong>
				</td>
				<td width="41"><form:input type="text"
						path="grnMasterDTO.count" data-maxsize="15" readonly="true"
						style="text-align:right;background-color:#ebebe4; width:90% "
						disabled="disabled" size="3" id="count" />
				</td>
				<td width="114">Form required</td>
				<td width="114"><div
						style="border: solid 1px; height: 20px; width: 99%; border-color: #7f9db9; background-color: #FFF;">
						<form:radiobutton path="grnMasterDTO.formReqFlag" value="1"
							style="width: 10px;" id="formReqFlag1" />
						Yes
						<form:radiobutton class="validate[required] radio"
							path="grnMasterDTO.formReqFlag" style="width: 10px;"
							id="formReqFlag" value="0" />
						No
					</div>
				</td>
				<td width="74"><span style="font-weight: normal;"> Form
						Type<span style="color: #FF0000"></span> </span>
				</td>
				<td width="91"><form:select path="grnMasterDTO.formTypeId"
						items="${formType}" itemLabel="name" itemValue="mastersId"
						style="width:97%" id="select5">
					</form:select>
				</td>
				<td width="76">Form No.</td>
				<td width="98"><form:input id="formnumber" class="digitOnly"
						type="text" size="7" data-maxsize="35"
						style="width:100%;  text-align:right;"
						path="grnMasterDTO.formnumber" />
				</td>
				</tr><tr>
				<td width="76">Form Date</td>
				<td width="116"><form:input type="text"
						path="grnMasterDTO.formDate"  id="formDate"
						 style="background-color:#ebebe4;width:90%;"
						size="11" />
				</td>

				<td width="69">QA Check Required</td>
				<td width="126"><div
						style="border: solid 1px; height: 20px; width: 99%; border-color: #7f9db9; background-color: #FFF;">
						<form:radiobutton path="grnMasterDTO.qaCheckRequired"
							style="width:10px;" id="radio" value="1" />
						Yes
						<form:radiobutton path="grnMasterDTO.qaCheckRequired"
							style="width:10px;" id="radio" value="0" />
						No
					</div>
				</td>
				<td>Freight Type</td>
				<td><form:select path="grnMasterDTO.freightYype"
						style="width:98%" id="freightYype">
						<form:option value="Inclusive">Inclusive</form:option>
						<form:option value="Extra">Extra</form:option>
					</form:select>
				</td>

				
			</tr>
			<tr>
				<td height="40" colspan="1" style="vertical-align: top;">GRN
					Remark</td>
				<td colspan="5"><form:textarea id="grnRemark"
						onkeypress="return check(event)" type="text" size="16"
						data-maxsize="500" style="   padding-left: 1px;    width: 99%;"
						path="grnMasterDTO.grnRemark" />
				</td>
				<%-- <td>Approved</td>
				<td><form:checkbox onkeypress="return check(event)"
					path="grnMasterDTO.aproved" value="1" data-maxsize="15" id="aprovedId" 
					size="11"  />
				</td> --%>
			</tr>
</table>

	<table class="tableview" style="float: right; height: 142px; width: 230px; ">
	<tr>
	<td colspan="2">Net Amount
	</td>
	<td>
	<form:input type="text"
						path="grnMasterDTO.itemValue" data-maxsize="15" readonly="true"
						style="text-align:right;background-color:#ebebe4; width:98%;"
						 size="8" />
	</td>
	</tr>
	<tr>
	<td colspan="2">
	Excise (Incl Cess)
	</td>
	<td>
	<input type="text" name="exciseInclCess" id="exciseInclCess" data-maxsize="15" readonly="true"
						style="text-align:right;background-color:#ebebe4; width:98%;"
						 size="8" />
	</td>
	</tr>
	
	<tr>
	<td><!-- Discount Amount -->
	</td>
	<td>
	<form:hidden path="grnMasterDTO.discountAmount" data-maxsize="15" readonly="true"
						style="text-align:right;background-color:#ebebe4; width:98%;"
						 />
	</td>
	
	</tr>
	<tr>
		<td colspan="2">Entry Tax Amount
	</td>
	<td><input name="entryTaxAmount" data-maxsize="15" readonly="true" id="entryTaxAmount"
						style="text-align:right;background-color:#ebebe4; width:98%;"
						 size="8" />
	</tr>
	<%-- <tr>
	<td>CST Amount
	</td>
	<td><form:input type="text"
						path="grnMasterDTO.cstAmount" data-maxsize="15" readonly="true"
						style="text-align:right;background-color:#ebebe4;"
						 size="8" />
	</td>
	
	</tr>--%>
	<tr>
<td colspan="2">Tax Amount
	</td>
	<td>
	<form:input type="text"
						path="grnMasterDTO.vatAmount" data-maxsize="15" readonly="true"
						style="text-align:right;background-color:#ebebe4; width:98%;"
						 size="8" />
	</td>
	</tr>
	<tr>
	<td colspan="2">Freight Amt.</td>
				<td><form:input type="text" path="grnMasterDTO.freightAmount"
						style="text-align:right; width:100%" class="quantity"
						data-maxsize="15" size="8" id="freightAmount" />
				</td>
	</tr>
	<tr>
					<td height="20">Others (+/-)</td>
					<td><form:input path="grnMasterDTO.otherDetail"
							onkeypress="return check(event)" data-maxsize="25"
							id="othersDetail" style="width:100%" size="11" />
					</td>
					<td><form:input path="grnMasterDTO.otherAmount"
							id="salesOrderRemark" onkeyup="valid2(this)"
							onblur="valid2(this)" size="11" data-maxsize="500" class="digite validate[custom[number]]"
							style="text-align:right" />
					</td>
				</tr>
	<tr> 
	<td colspan="2">Gross Amount
	</td>
	<td><form:input path="grnMasterDTO.grnNetAmount" data-maxsize="15" readonly="true"
						style="text-align:right;background-color:#ebebe4; width:98%;"
						 size="8" />
						 
<form:hidden path="grnMasterDTO.receivedBillExciseAmount" id="receivedBillExciseAmount" />
<form:hidden path="grnMasterDTO.receivedEducationCessAmount" id="receivedEducationCessAmount" />
<form:hidden path="grnMasterDTO.receivedHighEducationCessAmount" id="receivedHighEducationCessAmount" />
	</td>
	
	</tr>
	</table>
		<div class="btn" style="width: 300px;" >
			<div class="savbtn">
				<c:choose>
					<c:when test="${opr=='R'}">
						<c:url value="remove_grn" var="remove_url">
						<c:param name="id" value="${grnMasterForm.grnMasterDTO.grnAutoId}"></c:param>
						</c:url>
						<%-- <a href="${remove_url}" class="removebtn"></a> --%>
					</c:when>
					<c:otherwise>
						<c:choose>
						
							<c:when test="${opr=='E'}">
								<input class="updatebtn"
									style="font-size: 11px; font-weight: bold; padding: 0 0 0 30px;"
									type="submit" value=" " onclick="return onSave();" />
								
								<input id="myButton"  class="saveAndPrint grnNumber" style=" height: 24px;" 
								type="submit" name="opration" value="Print View" onclick="return onSave();"  />
								
								
								<div class="cancelbtn">
									<a href="get_grn_list" class="cancelbtn" iconCls="icon-cancel"></a>
								</div>
							</c:when>
							
					<%-- <c:when test="${aproved=='1'}">
								<input class="edit_btn"  type="button"  value="" onclick="return checkApproved();"/>
								<a href="get_grn_list"  class="cancelbtn" ></a>   
							</c:when> --%>
						<c:when test="${opr=='V'}">
								<input class="edit_btn"  type="button" onclick="editMethod();" value=""/>
    	 					   <a href="get_grn_list"  class="cancelbtn" ></a>     		
    					</c:when>
							
					<c:when test="${opr=='Add' || opr=='A'}">
								<input class="submit"
									style="font-size: 11px;  font-weight: bold; padding: 0 0 0 30px;"
									type="submit" value=" " onclick="return onSave();" />
								<input id="myButton"  class="saveAndPrint grnNumber" style=" height: 24px;" 
								type="submit" name="opration" value="Print View" onclick="return onSave();"  />
								
								<div class="cancelbtn">
									<a href="get_grn_list" class="cancelbtn" iconCls="icon-cancel"></a>
								</div>
								
							</c:when>
						</c:choose>
					</c:otherwise>
				</c:choose>
			</div>

			<span style="margin-left: 80px;" class="errmsg"></span>
		</div>




	</div>

</form:form>





