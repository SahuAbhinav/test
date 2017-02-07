<%@page import="java.util.List"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false"%>
<html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<meta charset="utf-8" />

<title>ASPL</title>
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



<c:if test="${checkTime=='No'}">

<script type="text/javascript">
		var delUrl='show_proforma_list?operation=show';
		$(document).ready(function() {
			alert('Sorry you can not convert this proforma to invoice because issue time is greater than removal time');
    	 window.self.location = delUrl;
		});
 	</script>
</c:if>
<c:if test="${error.operationName=='Delete'}">
	<script type="text/javascript">
			var redrctUrl='show_invoice_list?operation=show';
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
	 		 var invoiceAutoId = getParam('invoiceAutoId');
	 		 var invoiceNumber = getParam('invoiceNumber');
	 		// alert(frank);
	 		 //	confirm('Are you sure you want to delete?');
			 var delUrl='submitInvoiceList?invoiceAutoId='+invoiceAutoId+'&invoiceNumber='+invoiceNumber+'&operation=Delete';
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


<script src="http://code.jquery.com/jquery-1.8.2.js"></script>
<script src="http://code.jquery.com/ui/1.9.1/jquery-ui.js"></script>
<script src="http://code.jquery.validationEngine.js"></script>


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="keywords" content="jquery,ui,easy,easyui,web">
<meta name="description"
	content="easyui help you build your web page easily!">
<!--

<link rel="stylesheet" href="/resources/demos/style.css" />
<link rel="stylesheet" href="http://code.jquery.com/ui/1.9.1/themes/base/jquery-ui.css" />
 
<script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="/js/maxlength.js"></script>
<script type="text/javascript"
	src="/jquery-easyui-1.3.1/jquery-1.8.0.min.js"></script>
<script type="text/javascript"
	src="/jquery-easyui-1.3.1/jquery.easyui.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="/jquery-easyui-1.3.1/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="/jquery-easyui-1.3.1/demo/demo.css">
<link rel="stylesheet" type="text/css"
	href="/jquery-easyui-1.3.1/themes/default/easyui.css">
	 
	
<link rel="stylesheet" href="/css/validationEngine.jquery.css"
	type="text/css" />
<link rel="stylesheet" href="/css/template.css" type="text/css" />
<link rel="stylesheet"
	href="/jquery-easyui-1.3.1/themes/base/jquery-ui.css" />
<link rel="stylesheet" href="/css/style/style.css" />
<script src="/js/ui/1.9.0/jquery-ui.js"></script>
-->

<script src="././static/js/languages/jquery.validationEngine-en.js"
	type="text/javascript" charset="utf-8">
	
</script>
<script src="././static/js/jquery.validationEngine.js"
	type="text/javascript" charset="utf-8"></script>
<script type="text/javascript"
	src="././static/js/jquery-ui-timepicker-addon.js"></script>

<script type="text/javascript"
	src="././static/js/jquery.fixheadertable.js"></script>
<script>
$(document).ready(function() {
	
	
	$('.sr').click(function() {
		$('.sr').css("background-color","white");
		$(this).css("background-color","yellow");
		 
	 $('#indexId').val($(this).attr('id'));
	 
	});
	
	$("#invoiceForm").submit(function() {
	    $('#itemGroupFlagId').removeAttr('disabled');
	    $(".bookedByCls").removeAttr('disabled');
	});
	
	 if(parseInt($('#itemCountId').val())>0)
		{
		$('#itemGroupFlagId').attr('disabled','disabled');
		
		}
	
	
	 if($('#itemCountId').val()>0){
	 $('#partyId').attr('disabled','disabled');	
	 $('#vatCstTaxType').attr('readOnly','readOnly');	
	 $('#exciseTaxType').attr('readOnly','readOnly');	
	 }
	 
	 else{
		 $('#partyIdHidden').attr('disabled','disabled');	
	    }
	 
	 
	 // By default party selected party information
	 
	 $.ajax({

			type : "POST",
			url : "getPartyInfo",
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
					
					var abcd = document.forms[0];
					
					abcd.elements["billDTO.transportId"].value = response.result[0].transporterId;
					abcd.elements["billDTO.vatCstTaxType"].value = response.result[0].vatCstTaxType;
					
					 if($('#vatCstTaxType').val()== 'Without Tax'){
							$('#taxType').val('Without Tax');
						}				
					//if($("#invoiceAutoId").val()==''){
						
						if (invoiceType == 'saleswithInState') {
							 if($('#vatCstTaxType').val()!= 'Without Tax'){
							$('#taxType').val('VAT');
							 }
							$('#salesType').val('Sales with In State');
						    } 
						else {
							 if($('#vatCstTaxType').val()!= 'Without Tax'){
							$('#taxType').val('CST');
							 }
							$('#salesType').val('Sales Outside State');
						    }
						//}
						
							
					 if($('#salesType').val()=='Sales Outside State'){
						 if($('#vatCstTaxType').val()!= 'Without Tax'){
						 $('#taxType').val('CST');
						 }
						}
						else 
						{
							 if($('#vatCstTaxType').val()!= 'Without Tax'){
							$('#taxType').val('VAT');
							 }
						}
					
					
				/* 	if($("#invoiceAutoId").val()==''){
						if (invoiceType == 'saleswithInState') {
							$('#taxType').val('VAT');
							$('#salesType').val('Sales with In State');
						    } 
						else {
							$('#taxType').val('CST');
							$('#salesType').val('Sales Outside State');
						    }}
					
						if($('#salesType').val()=='Sales Outside State'){
							$('#taxType').val('CST');
						}else{
							$('#taxType').val('VAT');
						}
					 */
					
					$('#city').val(city);
					$('#state').val(state);
					$('#billingAddress').val(billingAddress);
					$('#phoneNo').val(phoneO1);
					$('#contactPerson').val(contactPerson1);
					
				   $('#cityId').val(response.result[0].billingCityId);
					
				   $('#buyerId1').val(response.result[0].partyName);
					var abc = document.forms[0];
					abc.elements["billDTO.consigneeId"].value = response.result[0].PartyId;
					
					
					
					//$('#city1').val(city);
					//$('#state1').val(state);
					//$('#billingAddress1').val(billingAddress);
					//$('#phoneNo1').val(phoneO1);
					//$('#contactPerson1').val(contactPerson1); 
					
					 var html = '<option value="' + response.result[0].partyNameCity + '">' + response.result[0].partyNameCity + '</option>';
	                 // $('#partyId1').append(html);  
	                  //$('#partyId1').val(response.result[0].partyId);
				}
			  }

		});
	 
	 function bookedByFun(t,uomid) {
	 		
 		 $.ajax({

 			type : "POST",
 			url : "getUnitName",
 			data : "itemId=" +t+"&UOM="+ $('#bookedBy'+uomid).val(),

 			success : function(response) {
 				// we have the response
 				console.log(response);
 				if (response.status == "SUCCESS") {
 					var city = response.result[0].cityName;
 					console.log(response);
 					$("#umoName"+uomid).val(response.result);
 					
 					$("#secondaryConvUnit"+uomid).val(response.result2);
 					//$("#primaryUOM"+uomid).val(response.result1);
 					//console.log($('#primaryUOM'+uomid).val());
 				}else{
 					//console.log("false");
 					console.log($('#bookedBy'+uomid).val(1));
 					console.log($('#bookedBy'+uomid).val());
 					
 					}
 				}

 		}); 
 	};
	 
	 
	 
	 
});


	$(function() {
		$('#partyId').change(function() {
			$.ajax({

				type : "POST",
				url : "getPartyInfo",
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
						
						
						var abcd = document.forms[0];
						abcd.elements["billDTO.transportId"].value = response.result[0].transporterId;
						
						
						abcd.elements["billDTO.vatCstTaxType"].value = response.result[0].vatCstTaxType;

											
						if (invoiceType == 'saleswithInState') {
							$('#taxType').val('VAT');
							$('#salesType').val('Sales with In State');
						} 
						else {
							$('#taxType').val('CST');
							$('#salesType').val('Sales Outside State');
						}

						
						
						$('#city').val(city);
						$('#state').val(state);
						$('#billingAddress').val(billingAddress);
						$('#phoneNo').val(phoneO1);
						$('#contactPerson').val(contactPerson1);
						
						
					   //$('#buyerId1').val(response.result[0].partyName);
						//var abc = document.forms[0];
						//abc.elements["billDTO.consigneeId"].value = response.result[0].PartyId;
						
						//$('#city1').val(city);
						//$('#state1').val(state);
						//$('#billingAddress1').val(billingAddress);
						//$('#phoneNo1').val(phoneO1);
						//$('#contactPerson1').val(contactPerson1); 
						
						 var html = '<option value="' + response.result[0].partyNameCity + '">' + response.result[0].partyNameCity + '</option>';
		                  $('#partyId1').append(html);  
		                  $('#partyId1').val(response.result[0].partyId);
		                
						
					}
				}

			});
		});
	});
	
	
	

	// Get pary for buyer User
	
	
	$(function() {
		$('#partyId1').change(function() {
			$.ajax({

				type : "POST",
				url : "getPartyInfo",
				data : "partyId=" + $('#partyId1').val(),

				success : function(response) {
					// we have the response
					if (response.status == "SUCCESS") {
						var city = response.result[0].cityName;
						var state = response.result[0].state;
						var billingAddress = response.result[0].billingAddress;
						var phoneO1 = response.result[0].phoneO1;
						var contactPerson1 = response.result[0].contactPerson1;

						var invoiceType = response.result[0].invoiceType;
						
						

						$('#city1').val(city);
						$('#state1').val(state);
						$('#billingAddress1').val(billingAddress);
						$('#phoneNo1').val(phoneO1);
						$('#contactPerson1').val(contactPerson1);

					}
				}

				});
				});
			    });
	
	
</script>
<script type="text/javascript">
function approvedCheck(){

	 if(confirm("Are you sure about converting the proforma to invoice, as you will not be able to edit / delete it after converting.")) 
	   {
	    return true;
	   
	 	}
	
	 return false;	
	 
}
			
			
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
<script type="text/javascript">
     /*  $(document).ready(function()
       {
	    $(".datepicker" ).datepicker({dateFormat : 'dd-M-yy',changeMonth: true,
	          changeYear: true, yearRange: '-99:+10', maxDate: +0});
      }); */
      
  </script>

<script type="text/javascript">
      $(document).ready(function()
       {
	    $(".datepicker" ).datepicker({dateFormat : 'dd-M-yy',changeMonth: true,
	          changeYear: true, yearRange: '-99:+10', maxDate: +0});
      });
  </script>
<input type="hidden" id="timeId" value="${invoice.timeToshow}">
<script>
	$(function() {

		//$( "#datepicker" ).datepicker();
		$("#datepicker").datepicker({
			dateFormat : 'dd-M-yy',
			autoSize : true,
			changeMonth : true,
			changeYear : true
			
		});
		
		//$("#datepicker").readonlyDatepicker(true);
		$("#expDate").datepicker({
			dateFormat : 'dd-M-yy',
			autoSize : true,
			changeMonth : true,
			changeYear : true
		});
	});

	$(function() {
		$("#datepicker1").datepicker({
			dateFormat : 'dd-M-yy',
			autoSize : true,
			changeMonth : true,
			changeYear : true
		});
	});
	$(function() {
		$("#datepicker2").datepicker({
			dateFormat : 'dd-M-yy',
			autoSize : true,
			changeMonth : true,
			changeYear : true
		});
	});
	$(function() {
		$("#datepicker3").datepicker({
			dateFormat : 'dd-M-yy',
			autoSize : true,
			changeMonth : true,
			changeYear : true
		});
	});
	
	$(function() {
		$("#datepicker4").datepicker({
			dateFormat : 'dd-M-yy',
			autoSize : true,
			changeMonth : true,
			changeYear : true
		});
	});
	$(function() {
		$("#datepicker5").datepicker({
			dateFormat : 'dd-M-yy',
			autoSize : true,
			changeMonth : true,
			changeYear : true
		});
	});
	$(function() {
		$("#sodatepicker").datepicker({
			dateFormat : 'dd-M-yy',
			autoSize : true,
			changeMonth : true,
			changeYear : true,
			yearRange: '-99:+10',
			maxDate: +0
		});
	});
	
	
	$(function() {
		$("#timepicker").val($("#timeId").val());
		$("#timepicker").timepicker({
			timeFormat : "hh:mm:ss",
			 show24Hours: true,
			  separator: '.',
			  step: 15
		});
	});
	
	$(function() {
		$("#roadPermitDateDatepicker").timepicker({
			timeFormat : "hh:mm:ss TT",
			ampm : true
		});
	});
	$(function() {
		$("#buyerPoDateDatepicker").timepicker({
			timeFormat : "hh:mm:ss TT",
			ampm : true
		});
	});
	
	// Float value formate up to two digites
	$(document).ready(function() {
		
		$(".digite").each(function() {
			if ($(this).val() == null || $(this).val() == "") {
				$(this).val(0);
			}
			var v = parseFloat($(this).val());

			v = v.toFixed(2);

			$(this).val(v);

		});
		
		

		$(".digiteRoundZero").each(function() {
			if ($(this).val() == null || $(this).val() == "") {
				$(this).val(0);
			}
			var v = parseFloat($(this).val());

			v = v.toFixed(0);

			$(this).val(v);

		});

	});
	
	
</script>
<script>
	function edit(index) {
		
		var frm1 = document.forms[0];
		var ind = parseInt(index);
		frm1.elements["indexNo"].value = ind;
	}

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

	$(function() {
		$("#hideFormNo").toggle(true);
		$("#showFormNo").toggle(false);

		$("#hideFormDate").toggle(true);
		$("#showFormDate").toggle(false);

		$("#hideFormType").toggle(true);
		$("#showFormType").toggle(false);

	
		
		if ($('#yes').is(':checked')== false) {
			jQuery("#no").attr('checked', true);
		}
		
		$('#yes').change(function() {
			$("#hideFormNo").toggle(false);
			$("#showFormNo").toggle(true);

			$("#hideFormDate").toggle(false);
			$("#showFormDate").toggle(true);
			$("#hideFormType").toggle(false);
			$("#showFormType").toggle(true);
			
		});
		
		$('#no').change(function() {
			$("#hideFormNo").toggle(true);
			$("#showFormNo").toggle(false);

			$("#hideFormDate").toggle(true);
			$("#showFormDate").toggle(false);
			$("#hideFormType").toggle(true);
			$("#showFormType").toggle(false);
		});

		$('#freightAmount').change(function() {
			var fa = 0.00;
			if ($('#freightAmount').val() != '') {
				fa = parseInt($('#freightAmount').val());
				
				$('#netAmount').val(fa);
			}
		});

		$('#itemValue').val($('#basicRate').val());
		
		$('#advanceFreightPaid').change(function() {
			var afp = 0;
			if ($('#advanceFreightPaid').val() !== '') {
				afp = parseInt($('#advanceFreightPaid').val());
				var netAmount = $('#netAmount').val();
				$('#netAmount').val((netAmount) - (afp));
			
			}
		});
		
		var  round = function(value, precision) {
	            var result = Number(value);
	            if (typeof precision == 'number') {
	                precision = Math.pow(10, precision);
	                result = Math.round(value * precision) / precision;
	            }
	            return result;
	           }

		function formChange() {
			//alert("1");

			var itemqut = 0.00;
			var itemVal = 0.00;
			var itemSum = 0.00;
			var exciseTotal = 0.00;
			var exiseAmou = 0.00;
			var discountTotal = 0.00;
			var taxAmtTotal = 0.00;
			var cessAmount = 0.00;
			var hcessAmount = 0.00;
			var taxTotal = 0.00;
            var highCessAmou = 0.00;
			var netAmount = 0.00;
			var totalTaxVatCst=0.00;
			var totalTaxable=0.00;
			
			var qtPerPacket=1;
			var noOfPacket=1;
			var totalnoOfPacket=0;
			for ( var ele = 0; true; ele++) {
				var frm = document.forms[0];
				//alert("2" + "billDetailList["+ ele+ "].quantity");
				var quantity = frm.elements["billDetailList[" + ele+ "].quantity"];
				//alert("3" + quantity);
				if (!quantity) {
					//alert("4" );
					break;
				}
				

				
				// Set quantity value
				qtPerPacket = frm.elements["billDetailList[" + ele+ "].qtyPerPacket"];
				noOfPacket = frm.elements["billDetailList[" + ele+ "].noOfPacket"];
				frm.elements["billDetailList[" + ele+ "].qtyPerPacket"].value= Number(getFloat(qtPerPacket.value).toString().match(/^\d+(?:\.\d{0,6})?/));
				frm.elements["billDetailList[" + ele+ "].noOfPacket"].value= Number(getFloat(noOfPacket.value).toString().match(/^\d+(?:\.\d{0,3})?/));
				if (qtPerPacket.value >0) {
					var qt=getFloat(qtPerPacket.value)*getFloat(noOfPacket.value);
					quantity.value  = Number(qt.toString().match(/^\d+(?:\.\d{0,6})?/));
				}
				
			frm.elements["billDetailList[" + ele+ "].itemValue"].value=round(getFloat(frm.elements["billDetailList[" + ele+ "].quantity"].value) * getFloat(frm.elements["billDetailList[" + ele+ "].salesRate"].value),2);	
				                                                                                                                                       				
				//alert("5" );
			var salesRate = frm.elements["billDetailList[" + ele
						+ "].salesRate"];
			frm.elements["billDetailList[" + ele
							+ "].salesRate"].value=Number(getFloat(salesRate.value).toString().match(/^\d+(?:\.\d{0,6})?/)); 
			
			var itemValues = frm.elements["billDetailList[" + ele
						+ "].itemValue"];
				
		    var discountPer = frm.elements["billDetailList[" + ele + "].discountPer"];
		    var discountAmount = frm.elements["billDetailList[" + ele+ "].discountAmountPerToShow"];
            var discountAmountToSave=  frm.elements["billDetailList[" + ele + "].discountAmount"];
            var excisePerc = frm.elements["billDetailList[" + ele+ "].excisePerc"];
            var exciseAmt = frm.elements["billDetailList[" + ele + "].exciseAmount"];
            
	        var cessAmo = frm.elements["billDTO.educationCessperc"].value;
			var hcessAmo = frm.elements["billDTO.highEducationCessPerc"].value;
					
		    var vatPerc = frm.elements["billDetailList[" + ele+ "].tax"];
			var vtotal	= frm.elements["billDetailList[" + ele + "].tax"];
			// To Saev Discount Amount Hiden Field
            discountAmountToSave.value = (getFloat(itemValues.value)*getFloat(discountAmount.value))/100;
	
			
          //To Packing and forwording
			   var packing= $("#packingForwarding").val();
			   var itmCount= $("#itemCountId").val();
			   var packingForSingleItem=packing/itmCount;
			 
			   //End packing forwording 
			
			
            var excisableAmount=(getFloat(itemValues.value))-(getFloat(discountAmountToSave.value))+(getFloat(packingForSingleItem));
            exciseAmt=(getFloat(excisableAmount)*getFloat(excisePerc.value))/100;
            exciseAmt=round(exciseAmt,0);
            frm.elements["billDetailList[" + ele + "].exciseAmount"].value=round(getFloat(exciseAmt),0);
		    
		    var cesAmt= ( getFloat(exciseAmt)*cessAmo ) /100;
		    var hcesAmt=   (getFloat(exciseAmt)*hcessAmo ) /100;
          
		    frm.elements["billDetailList[" + ele + "].eduCessAmount"].value= round(getFloat(cesAmt),0);
			frm.elements["billDetailList[" + ele + "].heduCessAmount"].value= round(getFloat(hcesAmt),0);
			
          
          
			var eduCessAmount = frm.elements["billDetailList[" + ele+ "].eduCessAmount"];
			var eduHCessAmount = frm.elements["billDetailList[" + ele + "].heduCessAmount"];
			
			discountPer.value =getFloat(discountAmount.value);
			var taxableAmount=0.0;
			var itemTotAmount=0.0;
			 
			//taxableAmount = ((getFloat(itemValues.value) + exciseAmt+getFloat(eduCessAmount.value)+getFloat(eduHCessAmount.value))-discountAmountToSave.value)+(getFloat(packingForSingleItem));
				taxableAmount =  (getFloat(excisableAmount)+ round(exciseAmt,0)+round(getFloat(eduCessAmount.value),0)+round(getFloat(eduHCessAmount.value),0));
			taxAmt=taxableAmount* getFloat(vatPerc.value) / 100;
			 taxAmt= round(taxAmt,0);
			frm.elements["billDetailList[" + ele + "].taxAmoForVatOrCst"].value=round(getFloat(taxAmt),0);
  			var noOfPacket = frm.elements["billDetailList[" + ele + "].noOfPacket"].value;
			  
  			itemTotAmount =getFloat(itemValues.value)-getFloat(discountAmountToSave.value)+getFloat(exciseAmt)+getFloat(eduCessAmount.value)+getFloat(eduHCessAmount.value)+getFloat(taxAmt);
   			frm.elements["billDetailList[" + ele + "].netAmount"].value=round(itemTotAmount+(getFloat(packingForSingleItem)),0); 
   			frm.elements["billDetailList[" + ele + "].assessableValue"].value=round((getFloat(itemValues.value)+(getFloat(packingForSingleItem))-getFloat(discountAmountToSave.value)),2); 
   		
   			
   		itemSum += getFloat(itemValues.value);
		discountTotal += getFloat(discountAmountToSave.value);
		exciseTotal += getFloat(exciseAmt);
		cessAmount += getFloat(eduCessAmount.value);
		hcessAmount += getFloat(eduHCessAmount.value);
		totalTaxable += taxableAmount;
        totalTaxVatCst +=getFloat(taxAmt);
        totalnoOfPacket+=getFloat(noOfPacket);
		}
			
			
			 var freightAmou=frm.elements["billDTO.freightAmt"].value;
			// var advanceFreightAmou=frm.elements["billDTO.advanceFreight"].value;
			 var packingForwardingAmou=getFloat(frm.elements["billDTO.packingForwarding"].value);
			 var valFrieghtAmoun=getFloat(freightAmou);
			 var billNetAmnt = itemSum - discountTotal + round(exciseTotal,0) + round(cessAmount,0) + round(hcessAmount,0) + round(totalTaxVatCst,0)  + round(valFrieghtAmoun,0);
				
			
			var v= frm.elements["billDTO.otherAmount"].value;
			var plus="+";
			var minus="-";
			var as="0";
			
			if(v!='0.00'){
				
			 //if (v.startsWith(plus)){
			 if (v.indexOf('+') === 0){
			as =v.replace('+', '');
			billNetAmnt = billNetAmnt + getInt(as);
			}else
			if (v.indexOf('-') === 0){
			as =v.replace('-', '');
			billNetAmnt=billNetAmnt-getInt(as);
			}else{
			billNetAmnt = billNetAmnt + getInt(v);
			}  
			} 
			var stirngAmount= billNetAmnt.toString();
			var mySplitResult = stirngAmount.split(".");
			
			var rountOfAmount=0.00;
			if(mySplitResult[1]){
			 rountOfAmount =getFloat("."+mySplitResult[1]);
			}
			
	
			
			var rountNetAmnt= round(getFloat(billNetAmnt),0);
			if(billNetAmnt<rountNetAmnt)
				rountOfAmount=(rountNetAmnt-billNetAmnt);
			else
				rountOfAmount=-(billNetAmnt-rountNetAmnt);
			
			
			
			
			
			
			/* var rountNetAmnt= round(getFloat(billNetAmnt),0);
			rountOfAmount=(billNetAmnt-rountNetAmnt); */
			
			frm.elements["billDTO.billNetAmountRoundOf"].value = round(rountOfAmount,2);
			frm.elements["billDTO.itemValue"].value =round(itemSum,2);
			frm.elements["billDTO.exciseDutyAmount"].value = round(exciseTotal,0);
			frm.elements["billDTO.discountAmount"].value = round(discountTotal,2);
			frm.elements["billDTO.educationCessAmount"].value =round(cessAmount,0);
			
			frm.elements["billDTO.highEducationCessAmount"].value = round(hcessAmount,0);
			var exciseDutyAmount1=$("#exciseDutyAmount").val();
			
			frm.elements["billDTO.taxableAmount"].value =round(totalTaxable,2);
			frm.elements["billDTO.billNetAmount"].value=round(getFloat(billNetAmnt)+getFloat($("#packingForwarding").val()),0);  
			
			frm.elements["billDTO.vatAmount"].value = round(totalTaxVatCst,0);
			frm.elements["billDTO.packetTotal"].value =totalnoOfPacket;
			
			frm.elements["billDTO.assessableValue"].value = round((itemSum - discountTotal)+getFloat($("#packingForwarding").val()),2);
			
		   }

		$('#invoiceForm').change(function() {
		formChange();
		});
		formChange();
	});
</script>

<script>
$(document).ready(function() {
	
	$('input').attr('readOnly','readOnly');	
	$('select').attr('readOnly','readOnly');
	$('textarea').attr('readOnly','readOnly');
	$('#invoiceAutoIds').removeAttr('readOnly');
	$(".bookedByCls").attr('disabled','disabled');
	
	$('#invoiceAutoIds').change(function() {
	$("#iNumber").val($("#orderSeries").val()+"/"+$('#invoiceAutoIds').val());
	});
	
	
});
</script>
<c:if test="${error.operationName=='Delete'}">
	<script>
function remoneConformation(){
	var name =	confirm('Are you sure you want to delete this Invoice?');
	if(name==true){
	  return true;
	  }else{
	  return false;
	  }
	  }
</script>

</c:if>
<c:if test="${invoice.salesOrderDate !=null}">
	<script>
$(document).ready(function() {
$('#sodatepicker').attr('disabled','disabled');	
$('#partyId').attr('disabled','disabled');	
  }
);
</script>
</c:if>




<style type="text/css">
p {
	color: blue;
}

.errmsg {
	color: red;
}
/*input {
	width:87%;
	margin-bottom:6px;
	}*/
input {
	border: 1px solid #7f9db9;
	border-radius: 3px;
}

select {
	width: 90%;
	height: 19px;
	border: 1px solid #7f9db9;
	border-radius: 3px;
}

.gridheadingdiv td {
	height: 22px;
	text-align: center;
}

.gridheadingdiv input {
	border: medium none;
	width: 75px;
}

.headingdiv1 {
	background-color: #99BBE8;
	height: 31px;
	width: 1100;
}

.searchbtn1 {
	cursor: pointer;
	float: left;
	height: 13px;
	margin-top: 5px;
	width: 12px !important;
	"
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

}
/* css for timepicker */
.ui-timepicker-div .ui-widget-header {
	margin-bottom: 8px;
}

.ui-timepicker-div dl {
	text-align: left;
}

.ui-timepicker-div dl dt {
	height: 25px;
	margin-bottom: -25px;
}

.ui-timepicker-div dl dd {
	margin: 0 10px 10px 65px;
}

.ui-timepicker-div td {
	font-size: 90%;
}

table {
	width: 971px;
}

.ui-tpicker-grid-label {
	background: None;
	border: None;
	margin: 0;
	padding: 0;
}
</style>

</head>


 <body>
	<%-- <c:if test="${not empty(error)}">
		<c:out value="${errorDTO.errorMsg}"></c:out>
		<div class="gridheadingdiv">
			<c:forEach items="${errorList}" var="err">
				<c:out value="${err.errorMsg}"></c:out>
			</c:forEach>

		</div>

	</c:if>
	<c:if test="${error.errorMsg!=null}">
		<div class="errorblock">
			<ul>
				<li>${error.errorMsg}</li>
			</ul>
		</div>
	</c:if>
	<c:if test="${error.errorCode!=null}">
		<div class="errorblock">
			<ul>
				<li>${error.errorCode}</li>
			</ul>
		</div>
	</c:if>
 --%>
	<%-- <c:if test="${not empty(error)}">
	<div class="errorblock">
		<ul>
				<li>${error.errorMsg}</li>
		</ul>
	</div>
</c:if>
<c:if test="${not empty(errors)}">
	<div class="errorblock">
		<ul>
		<c:forEach items="${errors.errorList}" var="e">
		<li>${e.errorMsg}</li>
		</c:forEach>
		</ul>
	</div>
</c:if> 
--%>


	<form:form action="save_invoice" method="POST" id="invoiceForm"
		commandName="invoice">
		
		<form:hidden path="dutyVideNotification" id="dutyVideNotificationId" />
		<div class="panel-header">
			<div class="panel-title">Invoice Entry</div>
			<div class="panel-tool"></div>
		</div>
		<div align="left" class="bkgColor"
			style="height: auto; padding-bottom: 14px;">
			<table height="25" width="970" border="0"
				style="width: 450px; margin-top: 2px;"">
				<tr>
					<td width="105">Branch Name<span style="color: #FF0000">*</span>
					</td>
					<td colspan="3" width="115"><form:select
							path="billDTO.branchDTO.branchId" id="branchId" disabled="true">

							<form:options items="${branchList}" itemValue="branchId" itemLabel="branch"  />
						</form:select>
						<form:hidden path="billDTO.branchDTO.branchId" id="partyIdHidden"  />
						</td>
				</tr>
			</table>
			<table height="81" width="970" border="0" class="tableview">
				<tr>
					<td width="94" height="20">Invoice Series <span
						style="color: #FF0000">*</span></td>
					<td width="101"><form:input path="billDTO.transactionSeries"
							class="validate[required] text-input" style="width: 62px;"
							data-maxsize="15" readonly="true" disabled="disabled" size="8"
							id="orderSeries" /> <form:hidden path="billDTO.invoiceAutoId" id="invoiceAutoId"/>
						<form:hidden class="validate[required] text-input"
							style="width: 43px;" path="billDTO.finyr" data-maxsize="15"
							readonly="true" disabled="disabled" size="7" id="finyrId" />
						
						<c:choose>
		  	 <c:when test="${hotKeyStatus==true}">
		  	 <form:input class="validate[required] text-input"
							style="width: 32px;" id="invoiceAutoIds" path="billDTO.invoiceId" data-maxsize="15"
							 size="7"/>
		  	 </c:when><c:otherwise>
			<form:input class="validate[required] text-input"
							style="width: 32px;" path="billDTO.invoiceId" data-maxsize="15"
							readonly="true" disabled="disabled" size="7" id="quotationSeries" />
							</c:otherwise></c:choose>
					</td>
					<td width="92" nowrap="nowrap">Invoice Number</td>
					<td width="78">
					<form:hidden class="validate[required] text-input" style="width: 43px;" path="billDTO.proformaNumber"/>
					<form:input path="billDTO.invoiceNumber"
							readonly="true" data-maxsize="15" disabled="disabled" size="11"
							style=" width: 84px;" id="iNumber" /></td>
					<td width="52">Date<span style="color: #FF0000">*</span></td>
					<td width="94"><form:input
							class="validate[required] text-input" style="width: 88%;"
							path="billDTO.invoiceDate"  size="21"
							readonly='true' /></td>

					<td width="109" nowrap="nowrap">SO. No.</td>
					<td width="88" nowrap="nowrap">
						<!-- <a href="save_invoice?operationSales Order No"
					class="searchbtn1"></a> --> <form:input
							path="billDTO.salesOrderNumber" class="quantity"
							style="text-align:left; width: 98%" data-maxsize="15" id="quoNo"
							size="11" readonly="true"/></td>

					<td width="70">SO. Date</td>
					<td>
					<!--   <form:hidden path="billDTO.salesOrderDate" id="hiddenSalesOrderDateId" /> -->
						<form:input path="billDTO.salesOrderDate"  style="width:98%" readonly="true" size="16" data-maxsize="15" />
					</td>
				</tr>
				<tr>
					<td height="20">Party Name <span style="color: #FF0000"
						id="error">*</span></td>
					<td colspan="5">
						<!--  <form:select path="billDTO.partyDTO.partyId" items="${partyList}" itemLabel="partyNameCity"   itemValue="partyId" style="width:100%" class="validate[required] text-input"  id="partyId"></form:select> -->
						<form:hidden path="billDTO.partyDTO.partyId" id="partyIdHidden" />

						<form:select path="billDTO.partyDTO.partyId" style="width:98%"
							id="partyId" >

							<form:options items="${partyList}" itemValue="partyId"
								itemLabel="partyNameCity" />
						</form:select>
					</td>
					<td>City</td>
					<td><form:input path="cityName" data-maxsize="15"
							readonly="true" disabled="disabled" size="11" style="width: 98%"
							class="validate[required] text-input" id="city" /> <form:hidden
							path="billDTO.cityId" id="cityId" /></td>
					<td>State</td>
					<td><form:input path="state" data-maxsize="15" readonly="true"
							disabled="disabled" size="16" id="state" /></td>
				</tr>

				<tr>
					<td height="20">Billing Address</td>
					<td colspan="5"><form:input path="billingAddress"
							class="validate[required] text-input" readonly="true"
							data-maxsize="150" style="padding-left: 2px;    width: 97%;"
							disabled="disabled" size="11" id="billingAddress" /></td>
					<td>Phone 1 (0)</td>
					<td><form:input path="phonNo" data-maxsize="15"
							readonly="true" disabled="disabled" style="width: 98%;" size="11"
							id="phoneNo" /></td>
					<td nowrap="nowrap">Contact Person</td>
					<td><form:input path="contactPerson" data-maxsize="15"
							readonly="true" disabled="disabled" size="16" id="contactPerson" />
					</td>
				</tr>


				<tr>
					<td height="20">Buyer Name <span style="color: #FF0000"
						id="error">*</span></td>
					<td colspan="5">
						<!--  <form:select path="billDTO.partyDTO.partyId" items="${partyList}" itemLabel="partyNameCity"   itemValue="partyId" style="width:100%" class="validate[required] text-input"  id="partyId"></form:select> -->
						<form:select path="billDTO.consigneeId" style="width:98%" 
							id="partyId1" disabled="true">

							<form:options items="${partyList}" itemValue="partyId"
								itemLabel="partyNameCity" id="buyerId1" />
						</form:select>
						<form:hidden path="billDTO.consigneeId" id="partyId1" />
					</td>
					<td>City</td>
					<td><form:input path="buyerCityName" data-maxsize="15"
							style="width: 98%;" readonly="true" disabled="disabled" size="11"
							class="validate[required] text-input" id="city1" /></td>
					<td>State</td>
					<td><form:input path="buyerState" data-maxsize="15"
							readonly="true" disabled="disabled" size="16" id="state1" /></td>
				</tr>


				<tr>
					<td height="20">Address</td>
					<td colspan="5"><form:input path="buyerBillingAddress"
							data-maxsize="150" class="validate[required] text-input"
							readonly="true" style=" padding-left: 2px;    width: 97%;"
							disabled="disabled" size="11" id="billingAddress1" /></td>
					<td>Phone 1 (0)</td>
					<td><form:input path="buyerPhonNo" data-maxsize="15"
							readonly="true" disabled="disabled" size="11"
							style=" width: 98%;" id="phoneNo1" /></td>
					<td nowrap="nowrap">Contact Person</td>
					<td><form:input path="buyerContactPerson" data-maxsize="55"
							readonly="true" disabled="disabled" size="16" id="contactPerson1" />
					</td>
				</tr>
				<tr>
    <td align="left">Item Group</td>
    <td width="150"><form:select
						path="billDTO.itemGroupFlagId"
						items="${itemGroupFlagList}" itemLabel="itemGroupFlagName"
						itemValue="itemGroupFlagId" id="itemGroupFlagId"
						class="validate[required]">
					</form:select></td>
    </tr>
			</table>
			<table height="81" width="970" border="0" class="tableview">

				<tr>
					<td height="20">Road Permit Date</td>
					<td colspan="1" nowrap="nowrap"><form:input
							path="billDTO.roadPermitDate" style="width:98%"
							 size="11" readonly="true"/></td>
					<td width="60" nowrap="nowrap">Road Permit No</td>
					<td><form:input path="billDTO.roadPermitNo"
							onkeypress="return check(event)" data-maxsize="15" size="11" readonly="true"/>
					</td>
					<td>Buyer Po. No.</td>
					<td><form:input path="billDTO.buyerPoNo"
							onkeypress="return check(event)" data-maxsize="35" size="11" readonly="true"/>
					</td>

					<td nowrap="nowrap">Buyer Po Date</td>
					<td><form:input path="billDTO.buyerPoDate" style="width:98%"
							 size="16" readonly="true"/></td>
				</tr>




				<tr>
					<td height="20" width="113" nowrap="nowrap">Transporter Name <span
						style="color: #FF0000">*</span></td>
					<td width="50" colspan="3"><form:select
							path="billDTO.transportId" style="width:100%"
							class="validate[required] text-input" id="transporterId" disabled="true">
							<form:options items="${transporterList}"
								itemValue="transporterId" itemLabel="transName" />
						</form:select>
						<form:hidden path="billDTO.transportId" id="transporterId" />
						</td>
					<td width="91">Form No.</td>
					<td>
						<div id="showFormNo">
							<form:input path="billDTO.formNo" style="width:98%"
								data-maxsize="25" id="formNo" size="11" readonly="true"/>
						</div>

						<div id="hideFormNo">
							<form:input path="billDTO.formNo" data-maxsize="25"
								style="width:98%" id="formNo" size="11" readonly="true"
								disabled="true" />
						</div></td>

					<td>Employee Name <span style="color: #FF0000">*</span></td>
					<td colspan="3"><form:select
							path="billDTO.employeeDTO.employeeId"
							class="validate[required] text-input" style="width:100%"
							id="employeeId" disabled="true">
							<form:options items="${employeeList}" itemValue="employeeId"
								itemLabel="employeeName" />
						</form:select>
						<form:hidden path="billDTO.employeeDTO.employeeId" id="employeeId" />
						</td>
				</tr>
				<tr>
					<td height="20">Form required</td>
					<td colspan="1" nowrap="nowrap">
						<div
							style="border: solid 1px; border-color: #7f9db9; border-radius: 3px; width: 120px; background-color: #FFF;">
							<form:radiobutton path="billDTO.formReqFlag" value="1" id="yes" disabled="true"/>
							<form:hidden path="billDTO.formReqFlag" value="1" id="yes" />
							Yes
							<form:radiobutton path="billDTO.formReqFlag" value="0" id="no" disabled="true"/>
							<form:hidden path="billDTO.formReqFlag" value="0" id="no" />
							No
						</div></td>
					<td width="60">Form Type</td>
					<td>
						<div id="showFormType">
							<form:select path="billDTO.formTypeId" style="width:100%"
								id="select4" disabled="true">

								<form:option value=""></form:option>
								<form:options items="${masterFormList}" itemValue="mastersId"
									itemLabel="name" />
									
							</form:select>
							<form:hidden path="billDTO.formTypeId" id="select4" />
						</div>
						<div id="hideFormType">
							<form:select path="billDTO.formTypeId" style="width:100%"
								disabled="true" id="select4">
								<form:option value=""></form:option>
								<form:options items="${masterFormList}" itemValue="mastersId"
									itemLabel="name" />
							</form:select>
						</div>
					</td>
					<td>Form Date</td>
					<td>
						<div id="showFormDate">
							<form:input path="billDTO.formDate" style="width:98%"
								 size="11" readonly="true"/>
						</div>
						<div id="hideFormDate">
							<form:input path="billDTO.formDate" class="datepicker"
								style="width:98%"  size="11" readonly="true"
								disabled="true" />
						</div>
					</td>
					<td>LR No.</td>
					<td><form:input path="billDTO.lrNo"
							onkeypress="return check(event)" data-maxsize="15" size="20" readonly="true"/>
					</td>
					<td nowrap="nowrap">LR Date</td>
					<td><form:input path="billDTO.lrDate" style="width:98%"
							 size="16" readonly="true"/></td>
				</tr>

				<tr>
					<td>Delivery Note No</td>
					<td><form:input path="billDTO.deliveryNoteNo"
							onkeypress="return check(event)" readonly="true" data-maxsize="15" size="20" />
					</td>
					<td>Note Date</td>
					<td><form:input path="billDTO.deliveryNoteDate"
							style="width:98%"  size="11" readonly="true" />
					</td>
					<td>Motor Vehicle No.</td>
					<td><form:input path="billDTO.motorVehicleNo"
							style="width:98%" onkeypress="return check(event)"
							data-maxsize="25" size="11" readonly="true"/></td>
					<td>Mode/Terms of Payment</td>
					<td ><form:input path="billDTO.termsOfPayment"
							style="  padding-left: 2px;
    width: 98%;"
							onkeypress="return check(event)" data-maxsize="25" size="11" readonly="true"/>
					</td>
					<td nowrap="nowrap">Exicse Type</td>
					<td><form:select style="width:113px; height:21px"
							path="billDTO.exciseType" id="exciseTaxType" disabled="true">
							<form:option value="excisable">Excisable</form:option>
						<form:option value="exempted">Exempted</form:option>
						</form:select><%--  <form:hidden style="width:113px; height:21px"
							path="proformaMasterDTO.vatCstTaxType" id="vatCstTaxTypeHidden"></form:hidden> --%>
					<form:hidden path="billDTO.exciseType" />
					</td>
				</tr>

				<tr>
					<td>Despatch Document No.</td>
					<td><form:input path="billDTO.despatchDocumentNo"
							onkeypress="return check(event)" data-maxsize="15" size="20" readonly="true"/>
					</td>
					<td>Date of Removal</td>
					<td><form:input path="billDTO.dateOfRemoval" style="width:98%"
							size="11" readonly="true"/></td>
					<td>Time of Removal</td>
					<td><form:input path="billDTO.timeOfRemoval" style="width:98%"
							 data-maxsize="25" id="timepicker"
							size="11" readonly="true"/></td>
					<td>Despatch Through</td>
					<td><form:input path="billDTO.despatchThrough"
							onkeypress="return check(event)" data-maxsize="65"
							style="  padding-left: 2px;    width: 98%;" size="11" readonly="true"/></td>
					<td nowrap="nowrap">Tax Type</td>
					<td><form:select style="width:113px; height:21px"
							path="billDTO.vatCstTaxType" id="vatCstTaxType" disabled="true">
							<form:option value="vat">VAT</form:option>
							<form:option value="cst with c form">CST with C Form</form:option>
							<form:option value="cst w/o c form">CST w/o C Form</form:option>
						    <form:option value="Without Tax">Without Tax</form:option>
						</form:select>
						<form:hidden path="billDTO.vatCstTaxType" id="vatCstTaxType" />
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

							<td width="44"><div align="center">
									<strong>S No.</strong>
								</div></td>
							<td width="135" nowrap="nowrap">Item Name</td>
							<td width="126	"><div align="center">
									<strong>Description</strong>
								</div></td>
							<td width="73">Batch No</td>
							<td width="91"><div align="center">
							<strong>Booked By </strong>
							</div></td>
							<td width="91"><div align="center">
							<strong>UOM </strong>
							</div></td>
							<td width="88"><div align="center">
									<strong>Qty/Packet</strong>
								</div></td>
							<td width="94"><div align="center">
									<strong>No of Packet</strong>
								</div></td>
							<td width="77"><div align="center">
									<strong>Quantity</strong>
								</div></td>
							<td width="95"><div align="center">
									<strong>Basic Rate </strong>
								</div></td>
							<td width="80"><div align="center">
									<strong>Item Value</strong>
								</div></td>
								<td width="67"><div align="center">
									<strong>Discount%</strong>
								</div></td>
							<td width="66"><div align="center">
									<strong>Excise %</strong>
								</div></td>
							
							<td width="58"><div align="center">
									<strong>Tax %</strong>
							</div></td>
							<td width="90"><div align="center">
									<strong>Amount</strong>
								</div></td>
							
							
						</tr>
					</thead>
					<tbody>
						<%
							int i = 1;
						%>
						<c:forEach items="${invoice.billDetailList}" var="pr"
							varStatus="s">
							<tr style="cursor: pointer" id="${s.index}">
								<td width="34"><%=i%><form:hidden
										path="billDetailList[${s.index}].sno" value="${pr.sno}"
										style="text-align:left; background-color:none; border:none; width:100%"
										readonly="true" data-maxsize="15" size="8" /></td>
								<td width="125">${pr.itemName}<form:hidden
										path="billDetailList[${s.index}].itemName"
										value="${pr.itemName}"
										style="text-align:left; background-color:none; border: medium none; width:100%"
										readonly="true" data-maxsize="350" size="8" /> <form:hidden
										path="billDetailList[${s.index}].itemId" value="${pr.itemId}"
										style="text-align:left; background-color:none; border: medium none; width:100%"
										data-maxsize="15" size="8" /> <form:hidden
										path="billDetailList[${s.index}].sno" value="${pr.sno}"
										style="text-align:right; width:100%" data-maxsize="15"
										size="8" /></td>
										<td width="116"><form:input
										path="billDetailList[${s.index}].packingDetail"
										value="${pr.packingDetail}"
										style="width:100%;  border:1px solid #7f9db9; "
										data-maxsize="50" size="8" id="packingDetail" readonly="true"/></td>
								<td width="63"><form:input
										path="billDetailList[${s.index}].batchNo"
										style="text-align:right; background-color: none; width:100%"
										readonly="true" data-maxsize="15" size="8" id="batchNo" /> <%-- <fmt:formatDate
										pattern="dd-MMM-yyyy" value="${pr.mfgDate}" var="abcd" /> <form:hidden
										path="billDetailList[${s.index}].mfgDate" value="${abcd}"
										style="text-align:right; background-color: none; width:100%"
										class="mfgDate" data-maxsize="15" size="8" id="mfgDate"
										readonly="true" /> --%> <fmt:formatDate pattern="dd-MMM-yyyy"
										value="${pr.expiryDate}" var="abc" /> <form:hidden
										path="billDetailList[${s.index}].expiryDate" value="${abc}"
										style="text-align:right; background-color: none; width:100%"
										class="expiryDate" data-maxsize="15" size="8" id="expiryDate"
										readonly="true" /></td>
								<td width="75"><form:select path="billDetailList[${s.index}].bookedBy"
							id="bookedBy${s.index}" class="bookedByCls" onchange="bookedByFun(${pr.itemId},${s.index})"  style="width:100%"  readonly="true" itemLabel="name" itemValue="value" >
							<form:option value="1">Primary UOM</form:option>
							<form:option value="2">Secondary UOM</form:option>
						</form:select></td>	
						
								<td width="81"><form:input
										path="billDetailList[${s.index}].umoName"
										value="${pr.umoName}"
										style="text-align:right; background-color: none; width:100%"
										class="umoName" data-maxsize="15" size="8" id="umoName${s.index}"
										readonly="true" />
										<%-- <form:hidden path="billDetailList[${s.index}].measurementUnitId" class="validate[custom[number]]"  data-maxsize="15" size="8" id="measurementUnitId${s.index}" /> --%>
										<form:hidden path="billDetailList[${s.index}].secondaryConvUnit"  class="validate[custom[number]]"  data-maxsize="15" size="8" id="secondaryConvUnit${s.index}" />
		<form:hidden path="billDetailList[${s.index}].primaryUOM"  class="validate[custom[number]]"  data-maxsize="15" size="8" id="primaryUOM${s.index}" />
		<form:hidden path="billDetailList[${s.index}].primaryUnit"  class="validate[custom[number]]"  data-maxsize="15" size="8" id="primaryUnit${s.index}" />
										 <form:hidden
										path="billDetailList[${s.index}].measurementUnitId"
										value="${pr.measurementUnitId}"
										style="text-align:right; border:1px solid #7f9db9; width:100%"
										class="umoName" data-maxsize="15" size="8"
										id="measurementUnitId" />
								</td>

								<td width="78"><form:input
										path="billDetailList[${s.index}].qtyPerPacket"
										value="${pr.qtyPerPacket}"
										style="text-align:right; border:1px solid #7f9db9;  width:100%"
										 data-maxsize="15" size="8" readonly="true"
										id="qtyPerPacket" />
								</td>
								<td width="84"><form:input
										path="billDetailList[${s.index}].noOfPacket"
										value="${pr.noOfPacket}"
										style="text-align:right;  border:1px solid #7f9db9;  width:100%"
										 data-maxsize="15" size="8"
										id="noOfPacket" readonly="true"/></td>
								<td width="67"><form:input
										path="billDetailList[${s.index}].quantity"
										value="${pr.quantity}"
										style="text-align:right; background-color:none !important; border:none !important; width:100%"
										data-maxsize="15" readonly="true" size="8" id="quantity" /></td>
								<td width="85"><form:input value="${pr.salesRate}"
										path="billDetailList[${s.index}].salesRate"
										style="text-align: right; width: 100% ; border:1px solid #7f9db9;"
										class=" quantity" data-maxsize="15" size="8"
										id="basicRate" readonly="true"/></td>
								<td width="70"><form:input
										path="billDetailList[${s.index}].itemValue"
										value="${pr.itemValue}"
										style="text-align:right; background-color: none;  border:1px solid #7f9db9; width:100%"
										 data-maxsize="15" readonly="true" size="8"
										id="itemValue" /></td>
										<td width="57"><form:input
										path="billDetailList[${s.index}].discountAmountPerToShow"
										style="text-align:right; border:1px solid #7f9db9; width:100%"
										 data-maxsize="15" size="8" id="discount"
										value="${pr.discountAmountPerToShow} " readonly="true"/> <form:hidden
										path="billDetailList[${s.index}].discountAmount"
										style="text-align:right; width:100%" class="digite"
										data-maxsize="5" size="8" id="discountAmount"
										value="${pr.discountAmount} " /> <form:hidden
										path="billDetailList[${s.index}].discountPer"
										style="text-align:right; width:100%" class="digite quantity"
										data-maxsize="15" size="8" id="discountPer"
										value="${pr.discountPer} " />
										
										<form:hidden
										path="billDetailList[${s.index}].assessableValue"
										style="text-align:right; width:100%" class="digite quantity"
										data-maxsize="15" size="8" id="assessableId"
										value="${pr.assessableValue} " />
										</td>
								<td width="56"><form:input value="${pr.excisePerc}"
										path="billDetailList[${s.index}].excisePerc"
										style="text-align: right; background-color: none; width: 100%"
										class="digite" data-maxsize="15" readonly="true" size="8"
										id="excisePerc" /> <form:hidden value="${pr.exciseAmount}"
										path="billDetailList[${s.index}].exciseAmount"
										style="text-align: right; background-color: none; width: 100%"
										class="digite" data-maxsize="15" readonly="true" size="8"
										id="exciseAmount" />
										
										
										
										<form:hidden value="${pr.eduCessAmount}"
										path="billDetailList[${s.index}].eduCessAmount"
										style="text-align: right; background-color: #DDDDDD; width: 100%"
										class="digite" data-maxsize="15" readonly="true" size="8"
										id="eduCessAmount" />
										<form:hidden value="${pr.heduCessAmount}"
										path="billDetailList[${s.index}].heduCessAmount"
										style="text-align: right; background-color: #DDDDDD; width: 100%"
										class="digite" data-maxsize="15" readonly="true" size="8"
										id="heduCessAmount" />
										
										
										
										
										
										</td>
								

								<td width="48"><form:input
										path="billDetailList[${s.index}].tax" value="${pr.tax}"
										style="text-align:right; background-color: none; width:100%"
										class="digite" data-maxsize="15" size="8" readonly="true"
										id="tax" /> <form:hidden
										path="billDetailList[${s.index}].taxAmoForVatOrCst"
										value="${pr.taxAmoForVatOrCst}"
										style="text-align:right; background-color: none; width:100%"
										class="digite" data-maxsize="15" size="8" readonly="true"
										id="taxAmoForVatOrCst" /> <!--  <form:input path="billDetailList[${s.index}].vatPerc"
								value="${pr.vatPerc}" style="text-align:right; background-color: none; width:100%"
								class="quantity" data-maxsize="15" size="8" readonly="true"
								id="tax" />--></td>
								<td width="80"><form:input
										path="billDetailList[${s.index}].netAmount"
										value="${pr.netAmount}"
										style="text-align:right; background-color: none; width:100%  border:1px solid #7f9db9; "
										class="digite" data-maxsize="15" readonly="true" size="8"
										id="amountId" /></td>
								
									<%
									i++;
								%>
							</tr>
						</c:forEach>

					</tbody>
				</table>
			</div>
			<table height="81" style="float: left; width: 598px;" width="970"
				border="0" class="tableview">
				<tr>
					<form:hidden path="indexNo" id="indexId" />
					<td width="177" height="20"><strong>Item Count</strong></td>
					<td width="73"><input name="" value="<%=i - 1%>"
						data-maxsize="15" readonly="true" style="width: 75px;" size="18"
						id="itemCountId" /></td>
					<td width="277"><strong>Packet Total</strong></td>
					<td width="71"><span style="font-weight: bold"> <form:input
								path="billDTO.packetTotal" data-maxsize="15" class="quantity"
								style="text-align:right" size="11" id="packetTotal" readonly="true"/> </span></td>
					<td width="101" nowrap="nowrap"><span
						style="font-weight: bold;"> Sales Type <span
							style="color: #FF0000">*</span> </span></td>
					<td width="398"><form:input path="billDTO.salesType"
							id="salesType" style="width:87%;" readonly="true" />
					</td>
				</tr>

				<tr>
					<td style="vertical-align: top" height="20">Bill Remark</td>
					<td colspan="5"><form:textarea cols="" rows="4"
							path="billDTO.billRemark"  size="16"
							data-maxsize="500"
							style=" height: 80px; border: 1px solid #7F9DB9; border-radius:3px; padding-left: 5px;  width: 96%;" readonly="true"/>



					</td>
				</tr>
			</table>
			<table height="81" style="float: left; width: 364px;" width="970"
				border="0" class="tableview">
				<tr>
					<td width="215" colspan="1"><strong>Item Value</strong></td>
					<td width="215">&nbsp;</td>
					<td width="215"><form:input path="billDTO.itemValue"
							data-maxsize="15" readonly="true" style="text-align:right"
							class="digite" disabled="disabled" size="11" id="billitemValue" />
					</td>
				</tr>
				<tr>
					<td height="20">Discount Total (-)</td>
					<td>&nbsp;</td>
					<td><form:input path="billDTO.discountAmount"
							data-maxsize="15" readonly="true" style="text-align:right"
							class="digite" size="11" id="discountTotal" /></td>
				</tr>
				<tr>
					<td colspan="2">Packing & Forwarding</td>
					<td width="215"><form:input path="billDTO.packingForwarding"
							data-maxsize="15" style="text-align:right" size="11"
							id="packingForwarding" readonly="true"/></td>
				</tr>
				<tr>
					<td height="20">Assessable Value</td>
					<td>&nbsp;</td>
					<td><form:input path="billDTO.assessableValue"
							data-maxsize="15" readonly="true" style="text-align:right"
							class="digite" size="11" id="assessableId" /></td>
				</tr>
				<tr>
					<td colspan="1">Excise Total (+)</td>
					<td>&nbsp;</td>
					<td><form:input path="billDTO.exciseDutyAmount"
							data-maxsize="15" readonly="true" style="text-align:right"
							class="digite" disabled="disabled" size="11"
							id="exciseDutyAmount" /></td>
				</tr>
				<tr>
					<td nowrap="nowrap">Cess % (on Excise) (+)</td>
					<td width="215"><form:input path="billDTO.educationCessperc"
							style="text-align: right; width:100%;" class="digite"
							data-maxsize="15" size="8" id="eduCessPerce"  readonly="true"/></td>

					<td><form:input path="billDTO.educationCessAmount"
							data-maxsize="15" readonly="true" style="text-align:right"
							class="digite" disabled="disabled" size="11"
							id="quotationSeries242" /></td>
				</tr>

				<tr>

					<td width="174" height="20">H. Cess %(on Excise)(+)</td>
					<td><form:input path="billDTO.highEducationCessPerc"
							style="text-align: right; width:100%;" class="digite"
							data-maxsize="15" size="8" id="heduCessPerce"  readonly="true"/></td>
					<td><form:input path="billDTO.highEducationCessAmount"
							data-maxsize="15" readonly="true" style="text-align:right"
							class="digite" size="11" id="taxTotal1" /></td>
				</tr>
				
				<tr>
					<td height="20">Taxable Amount</td>
					<td>&nbsp;</td>
					<td><form:input path="billDTO.taxableAmount" data-maxsize="15"
							readonly="true" style="text-align:right" disabled="disabled"
							class="digite" size="11" id="others" /></td>
				</tr>
				<tr>
					<td height="20">Tax Total (+)</td>
					<td><form:input path="taxType" data-maxsize="25"
							readonly="true" disabled="disabled" style="width:100%" size="11"
							id="taxType" /></td>
					<td><form:input path="billDTO.vatAmount" name="taxTotal"
							data-maxsize="15" readonly="true" style="text-align:right"
							class="digite" size="11" id="vatAmountId" />
					</td>
				</tr>


				<tr>
					<%-- 	<td colspan="2">Bal. Freight Amount</td>
					<td width="215"><form:input
							path="billDTO.balanceFreightAmount" data-maxsize="15"
							readonly="true" style="text-align:right" disabled="disabled" class="digite"
							size="11" id="balanceFreightAmount" />
					</td> --%>

					<td>Freight/Type Amount</td>
					<td><form:select path="billDTO.freightType" id="freightType"
							style="width:100%" data-maxsize="25" disabled="true">
							<form:option value="ToPay">To Pay</form:option>
							<form:option value=" TobeBilled"> To be Billed</form:option>
							<form:option value="Paid">Paid</form:option>
						</form:select>
						<form:hidden path="billDTO.freightType" id="freightType"  />
						</td>

					<td><form:input path="billDTO.freightAmt" id="freightAmount"
							class="digite" size="11" data-maxsize="15"
							style="  text-align:right;" onchange="calc(this)" readonly="true"/></td>

				</tr>
				
				<tr>
					<td height="20">Others (+/-)</td>
					<td><form:input path="billDTO.othersDetail"
							onkeypress="return check(event)" data-maxsize="25"
							id="othersDetail" style="width:100%" size="11" readonly="true"/></td>
					<td><form:input path="billDTO.otherAmount"
							id="salesOrderRemark" onkeyup="valid2(this)"
							onblur="valid2(this)" size="11" data-maxsize="500" class="digite"
							style="text-align:right" readonly="true" /></td>
				</tr>
				<tr>
					<td colspan="2">Rounding Off</td>
					<td width="215"><form:input
							path="billDTO.billNetAmountRoundOf" data-maxsize="15"
							style="text-align:right" readonly="true" size="11"
							id="billNetAmountRoundOf" /></td>
				</tr>
				<tr>
					<td height="20"><strong>Net Amount</strong></td>
					<td>&nbsp;</td>
					<td><form:input path="billDTO.billNetAmount" data-maxsize="15"
							readonly="true" style="text-align:right" class="digite" size="11"
							id="netAmount" /></td>
				</tr>
			</table>
			<c:if test="${error.operationName=='Delete'}">
				<c:url value="submitInvoiceList" var="remove_url">
					<c:param name="invoiceAutoId"
						value="${invoice.billDTO.invoiceAutoId}"></c:param>
					<c:param name="invoiceNumber"
						value="${invoice.billDTO.invoiceNumber}"></c:param>
					<c:param name="operation" value="Delete"></c:param>
				</c:url>
				<a href="${remove_url}" class="removebtn"
					onclick="return remoneConformation();"></a>

				<c:url value="show_invoice_list?operation=show" var="remove_url">

					<c:param name="operation" value=""></c:param>
				</c:url>
				<a href="${remove_url}" class="cancelbtn"
					onclick="this.value='Cancel';"></a>
			</c:if>
			<c:if test="${error.operationName=='ConvertToInvoice'}">
				<div class="btn">
					
						
							<input class="convertbtn" type="submit"  name="operation" id="submitId" 
								value="Save" onclick="return approvedCheck();" />
								
                                <!-- //onclick="this.value='Save';" -->
							<c:url value="show_proforma_list?operation=show" var="remove_url">
								<c:param name="operation" value=""></c:param>
							</c:url>
							<a href="${remove_url}" class="cancelbtn"
								onclick="this.value='Cancel';"></a>
						
						<%-- <c:if test="${invoice.billDTO.invoiceAutoId==null}">
							<input id="myButton" class="submit" type="submit" value=""
								name="operation" value=""
								onclick="this.value='Save',$('#invoiceForm').validationEngine();" />
					<!--<input type="submit" value="List" name="operation"> -->
						</c:if> --%>
					</div>

					<span style="margin-left: 80px;" class="errmsg"></span>
				</div>
			</c:if>
			<c:if test="${error.operationName!='ConvertToInvoice'}">
				<div class="cancelbtn">
					<c:url value="show_invoice_list?operation=show" var="remove_url">
						<c:param name="operation" value=""></c:param>
					</c:url>
					<a href="${remove_url}" class="cancelbtn"
						onclick="this.value='Cancel';"></a>

				</div>
			</c:if>
	</form:form>
	</div>
</body>
</html>