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

<script>
function invoiceNoFunction(){
	$('#invoiceNumberToPrint').val($('#iNumber').val());
	
}
</script>
<c:if test="${error.operationName=='Delete'}">
	<script type="text/javascript">
			var redrctUrl='show_proforma_list?operation=show';
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
			 var delUrl='submitProformaList?invoiceAutoId='+invoiceAutoId+'&invoiceNumber='+invoiceNumber+'&operation=Delete';
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

<c:if test="${error.operationName=='V' || error.operationName=='V1' }">
	<script type="text/javascript">
		$(document).ready(function() {
		$('input').attr('readonly','readonly');
		$('select').attr('disabled','disabled');
		$('textarea').attr('readonly','readonly');
		$('.datepicker').attr('disabled','disabled');
		$('input:radio').attr('disabled',true);
		$("input:checkbox").attr("disabled", true);
		$(".searchbtn1").attr("disabled", true);
		$("#timepicker").attr("disabled", true);
		$('.datepicker2').attr('disabled','disabled');
		$('.searchbtn1').attr('disabled','disabled');
		});
	</script>
</c:if>




<script type="text/javascript">
 	
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

 	function editMethod()
 	 { 
 	 var frank_param = getParam('invoiceAutoId');
	 var delUrl='show_proforma?invoiceAutoId='+frank_param+'&operation=Get_Invoice';
 	 window.self.location = delUrl;  
	}
 	function errorMethod()
 	{
 	alert('Sorry you can not Edit this proforma as it is already converted to invoice.');
 	}
 	
 	function bookedByFun(t,uomid) {
 		
 		 $.ajax({

 			type : "POST",
 			url : "getUnitName",
 			data : "itemId=" +t+"&UOM="+ $('#bookedBy'+uomid).val(),

 			success : function(response) {
 				// we have the response
 				//console.log(response);
 				if (response.status == "SUCCESS") {
 					var city = response.result[0].cityName;
 					//console.log(response);
 					$("#umoName"+uomid).val(response.result);
 					$("#measurementUnitId"+uomid).val(response.result3);
 					$("#secondaryConvUnit"+uomid).val(response.result2);
 					$("#primaryUOM"+uomid).val(response.result1);
 					//$("#primaryUOM"+uomid).val(response.result1);
 					//console.log($('#primaryUOM'+uomid).val());
 				}else{
 					//console.log("false");
 					//console.log($('#bookedBy'+uomid).val(1));
 					//console.log($('#bookedBy'+uomid).val());
 					
 					}
 				}

 		}); 
 	};
 	</script>


<script src="././static/js/jquery-1.8.2.js"></script>
<script src="././static/js/ui/1.9.1/jquery-ui.js"></script>
<script src="././static/js/jquery.validationEngine.js"></script>


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

<!-- <script>
function cosigneeId(ele){
	alert('dfdfdfdf');
	
	
}
</script> -->


<script>

$(document).ready(function() {
	
	$('.sr').click(function() {
		$('.sr').css("background-color","white");
		$(this).css("background-color","yellow");
		 
	 $('#indexId').val($(this).attr('id'));
	 
	});
	

	
	
	
	 if($('#itemCountId').val()>0){
	 $('#partyId').attr('disabled','disabled');	
	 //$('#vatCstTaxType').attr('readOnly','readOnly');	
	 //$('#vatCstTaxType').attr('disabled','disabled');	
	 $('#branchId').attr('disabled','disabled');
	 }
	 else{
		 $('#partyIdHidden').attr('disabled','disabled');	
		// $('#vatCstTaxTypeHidden').attr('disabled','disabled');	
		 $('#branchIdHidden').attr('disabled','disabled');	
		 
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
					

					//abcd.elements["proformaMasterDTO.transportId"].value = response.result[0].transporterId;
					//abcd.elements["proformaMasterDTO.vatCstTaxType"].value = response.result[0].vatCstTaxType;
					
					if($('#itemCountId').val()=='0'){
					//$('#vatCstTaxType').val(response.result[0].vatCstTaxType);
					}
					$('#transporterId').val( response.result[0].transporterId);
					/* if($('#despatchThrough').val()==''){
						$('#despatchThrough').val($('#transporterId option:selected').text());
					} */
					  
					 if($('#vatCstTaxType').val()== 'Without Tax'){
							$('#taxType').val('Without Tax');
						}
					
					
					if($("#invoiceAutoId").val()==''){
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
					    }}
					
						
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
					
				
					
					
					$('#city').val(city);
					$('#state').val(state);
					$('#billingAddress').val(billingAddress);
					$('#phoneNo').val(phoneO1);
					$('#contactPerson').val(contactPerson1);
					
				   $('#cityId').val(response.result[0].billingCityId);
					
				   $('#buyerId1').val(response.result[0].partyName);
					var abc = document.forms[0];
					
					abc.elements["proformaMasterDTO.consigneeId"].value = response.result[0].PartyId;
					//cosigneeId(response.result[0].PartyId);
					
					
					//$('#city1').val(city);
					//$('#state1').val(state);
					//$('#billingAddress1').val(billingAddress);
					//$('#phoneNo1').val(phoneO1);
					//$('#contactPerson1').val(contactPerson1); 
					
					// var html = '<option value="' + response.result[0].partyNameCity + '">' + response.result[0].partyNameCity + '</option>';
	                 // $('#partyId1').append(html);  
	                  //$('#partyId1').val(response.result[0].partyId);
				}
			  }

		});
	 
	 
	 
	 
	 
	 
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
						//abcd.elements["proformaMasterDTO.transportId"].value = response.result[0].transporterId;
						abcd.elements["proformaMasterDTO.vatCstTaxType"].value = response.result[0].vatCstTaxType;
						
						if($('#itemCountId').val()=='0'){
						//$('#vatCstTaxType').val(response.result[0].vatCstTaxType);
						}
						$('#transporterId').val( response.result[0].transporterId);
					
						if($("#invoiceAutoId").val()==''){
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
							}
							else 
							{
								$('#taxType').val('VAT');
							}
						
						
						
						$('#city').val(city);
						$('#state').val(state);
						$('#billingAddress').val(billingAddress);
						$('#phoneNo').val(phoneO1);
						$('#contactPerson').val(contactPerson1);
						
						
					  // $('#buyerId1').val(response.result[0].partyName);
						//var abc = document.forms[0];
						//abc.elements["proformaMasterDTO.consigneeId"].value = response.result[0].PartyId;
						
						//$('#city1').val(city);
						//$('#state1').val(state);
						//$('#billingAddress1').val(billingAddress);
					    //$('#phoneNo1').val(phoneO1);
						//$('#contactPerson1').val(contactPerson1); 
						
						// var html = '<option value="' + response.result[0].partyNameCity + '">' + response.result[0].partyNameCity + '</option>';
		                  //$('#partyId1').append(html);  
		                  //$('#partyId1').val(response.result[0].partyId);
		                
						
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
	/* $(function() {
	 $('#transporterId').change(function() {
		
		 if($('#despatchThrough').val()==''){
			 $('#despatchThrough').val($('#transporterId option:selected').text()); 
		 }
		 
		 
	 });
	
	 }); */
</script>
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
      $(document).ready(function()
       {
     
	    $(".datepicker" ).datepicker({dateFormat : 'dd-M-yy',changeMonth: true,
	          changeYear: true, yearRange: '-99:+10', maxDate: +0});
	    
	    if(parseInt($('#itemCountId').val())>0)
		{
		$('#itemGroupFlagId').attr('disabled','disabled');
		
		}
	    
      });
  </script>
  
  <c:if test="${operation=='Get_Invoice' }">
	<script type="text/javascript">
		$(document).ready(function() {
		$('.invoice_datepicker').attr('disabled','disabled');
		});
	</script>
	</c:if>

<script type="text/javascript">
      $(document).ready(function()
       {
    	  
    	  
	    $(".datepicker" ).datepicker({
	    	dateFormat : 'dd-M-yy',
	    	changeMonth: true,
	          changeYear: true,
	          yearRange: '-99:+10', maxDate: +0
	          });
      });
  </script>
<input type="hidden" id="timeId" value="${invoice.timeToshow}">
<script>
	$(function() {
		var l=$('#lastDate').val();
  	  console.log(l);
		//$( "#datepicker" ).datepicker();
		$(".invoice_datepicker").datepicker({
			dateFormat : 'dd-M-yy',
			autoSize : true,
			changeMonth : true,
			changeYear : true,
			 minDate: new Date(l),
			  yearRange: '-99:+10', maxDate: +0
		});

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
		$("#lrdatepicker2").datepicker({
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
	/* $(function() {
		$("#sodatepicker").datepicker({
			dateFormat : 'dd-M-yy',
			autoSize : true,
			changeMonth : true,
			changeYear : true,
			yearRange: '-99:+10',
			maxDate: +0
		});
	}); */
	
	
	
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
			timeFormat : "hh:mm:ss",
			ampm : true
		});
	});
	$(function() {
		$("#buyerPoDateDatepicker").timepicker({
			timeFormat : "hh:mm:ss",
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
		
		$(".bookBy").each(function(index) {
			if ($(this).val() != null && $(this).val() != "" && $(this).val() == 1) {
				$("#bookedBy"+index).attr('disabled','disabled');
			}
			
			console.log($(this).val());
			console.log(index);
			console.log($("#bookedBy"+index));
			//bookedBy${s.index}

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

		//$('#itemValue').val($('#basicRate').val());
		
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
				//alert("2" + "proformaDetailList["+ ele+ "].quantity");
				var quantity = frm.elements["proformaDetailList[" + ele+ "].quantity"];
				//alert("3" + quantity);
				if (!quantity) {
					//alert("4" );
					break;
				}
				
				qtPerPacket = frm.elements["proformaDetailList[" + ele+ "].qtyPerPacket"];
				noOfPacket = frm.elements["proformaDetailList[" + ele+ "].noOfPacket"];
				
			/* if(quantity.value>=1  && qtPerPacket.value<=1 && noOfPacket.value<=1){
			frm.elements["proformaDetailList[" + ele+ "].noOfPacket"].value=getFloat(quantity.value);
			} */
			
				
				// Set quantity value
				qtPerPacket = frm.elements["proformaDetailList[" + ele+ "].qtyPerPacket"];
				noOfPacket = frm.elements["proformaDetailList[" + ele+ "].noOfPacket"];
				
				
				//frm.elements["proformaDetailList[" + ele+ "].qtyPerPacket"].value= getFloat(qtPerPacket.value);
				frm.elements["proformaDetailList[" + ele+ "].qtyPerPacket"].value= Number(getFloat(qtPerPacket.value).toString().match(/^\d+(?:\.\d{0,6})?/));
				//frm.elements["proformaDetailList[" + ele+ "].noOfPacket"].value=getFloat(noOfPacket.value);
				frm.elements["proformaDetailList[" + ele+ "].noOfPacket"].value=Number(getFloat(noOfPacket.value).toString().match(/^\d+(?:\.\d{0,0})?/));
				 
				
				
				
				if (qtPerPacket.value >0) {
				var qt=getFloat(qtPerPacket.value)*getFloat(noOfPacket.value);
				quantity.value  = Number(qt.toString().match(/^\d+(?:\.\d{0,6})?/));
				
				
				}
			 // Item value calculating
			 frm.elements["proformaDetailList[" + ele+ "].itemValue"].value= round(getFloat(frm.elements["proformaDetailList[" + ele+ "].quantity"].value) * getFloat(frm.elements["proformaDetailList[" + ele
				       				                                                                                                                   						+ "].salesRate"].value),2);				
			 // Variable decleare and get value
			 var salesRate = frm.elements["proformaDetailList[" + ele
						+ "].salesRate"];
			 
			 frm.elements["proformaDetailList[" + ele
							+ "].salesRate"].value=Number(getFloat(salesRate.value).toString().match(/^\d+(?:\.\d{0,6})?/));
								
			 
			 var itemValues = frm.elements["proformaDetailList[" + ele
						+ "].itemValue"];
				
			 var discountPer = frm.elements["proformaDetailList[" + ele + "].discountPer"];
			
			 
			 
			 var discountAmount = frm.elements["proformaDetailList[" + ele+ "].discountAmountPerToShow"];
			 frm.elements["proformaDetailList[" + ele + "].discountAmountPerToShow"].value=Number(getFloat(discountAmount.value).toString().match(/^\d+(?:\.\d{0,3})?/));
			 var discountAmountToSave =  frm.elements["proformaDetailList[" + ele + "].discountAmount"];
			 var excisePerc = frm.elements["proformaDetailList[" + ele+ "].excisePerc"];
			 var exciseAmt = frm.elements["proformaDetailList[" + ele + "].exciseAmount"];
			 var cessAmo = frm.elements["proformaMasterDTO.educationCessperc"].value;
			 var hcessAmo = frm.elements["proformaMasterDTO.highEducationCessPerc"].value;
			
			 //Cess and HCess Percentage to show	
			 frm.elements["proformaDetailList[" + ele + "].cessPercent"].value=cessAmo;
			 frm.elements["proformaDetailList[" + ele + "].hCessPercent"].value=hcessAmo;
			 
			 
			 
			 var vatPerc = frm.elements["proformaDetailList[" + ele+ "].tax"];
			 var vtotal	= frm.elements["proformaDetailList[" + ele + "].tax"];
			 // To Saev Discount Amount Hiden Field
			 discountAmountToSave.value = (getFloat(itemValues.value)*getFloat(discountAmount.value))/100;

			//To Packing and forwording
			   var packing= $("#packingForwarding").val();
			   var itmCount= $("#itemCountId").val();
			   var packingForSingleItem=packing/itmCount;
			  // var packingAmnt=(getFloat(packingForSingleItem)*getFloat(excisePerc.value))/100;
			  // exciseAmt=exciseAmt+packingAmnt;
			   //End packing forwording 
			 
			 
			 var excisableAmount=(getFloat(itemValues.value))-(getFloat(discountAmountToSave.value))+(getFloat(packingForSingleItem));
			 
			// console.log("excisableAmount"+excisableAmount);
			 
			 exciseAmt=((getFloat(excisableAmount))*getFloat(excisePerc.value))/100;
			 exciseAmt=round(exciseAmt,0);
			 //console.log("exciseAmt"+exciseAmt);
			 frm.elements["proformaDetailList[" + ele + "].exciseAmount"].value=round(getFloat(exciseAmt),0);
			    
				
			 var cesAmt= ( getFloat(exciseAmt)*cessAmo ) /100;
			 var hcesAmt=   (getFloat(exciseAmt)*hcessAmo ) /100;
			    
			 //console.log("cesAmt"+cesAmt+"hcesAmt"+hcesAmt);
			 
			 frm.elements["proformaDetailList[" + ele + "].eduCessAmount"].value= round(getFloat(cesAmt),0);
			 frm.elements["proformaDetailList[" + ele + "].heduCessAmount"].value= round(getFloat(hcesAmt),0);
				
			 var eduCessAmount = frm.elements["proformaDetailList[" + ele+ "].eduCessAmount"];
			 var eduHCessAmount = frm.elements["proformaDetailList[" + ele + "].heduCessAmount"];
				
			 discountPer.value =getFloat(discountAmount.value);

			 var taxableAmount=0.0;
			 var itemTotAmount=0.0;
				
			 //console.log("eduCessAmount"+eduCessAmount.value+"eduHCessAmount"+eduHCessAmount.value);
			 
			 //taxableAmount = ((getFloat(itemValues.value) + exciseAmt+getFloat(eduCessAmount.value)+getFloat(eduHCessAmount.value))-discountAmountToSave.value)+(getFloat(packingForSingleItem));
			 
			taxableAmount =  (getFloat(excisableAmount)+ round(exciseAmt,0)+round(getFloat(eduCessAmount.value),0)+round(getFloat(eduHCessAmount.value),0));
			
			//console.log("taxableAmount:"+taxableAmount);
			 taxAmt=taxableAmount* getFloat(vatPerc.value) / 100;
			 taxAmt= round(taxAmt,0);
			// console.log("E & D:"+excisableAmount+",T:"+taxAmt);
			 frm.elements["proformaDetailList[" + ele + "].taxAmoForVatOrCst"].value=round(taxAmt,0);
		 
  			 var noOfPacket = frm.elements["proformaDetailList[" + ele + "].noOfPacket"].value;
  			 
   			 itemTotAmount =getFloat(itemValues.value)-getFloat(discountAmountToSave.value)+getFloat(exciseAmt)+getFloat(eduCessAmount.value)+getFloat(eduHCessAmount.value)+getFloat(taxAmt);
   		
   			 frm.elements["proformaDetailList[" + ele + "].netAmount"].value=round(itemTotAmount+(getFloat(packingForSingleItem)),0);

   			 frm.elements["proformaDetailList[" + ele + "].assessableValue"].value=round((getFloat(itemValues.value)+(getFloat(packingForSingleItem))-getFloat(discountAmountToSave.value)),2);
   	
   			 
   			//console.log("itemValues.value"+itemValues.value);
   			 
  			 itemSum += getFloat(itemValues.value);
			 discountTotal += getFloat(discountAmountToSave.value);
			 exciseTotal += getFloat(exciseAmt);
			 cessAmount += getFloat(eduCessAmount.value);
			 hcessAmount += getFloat(eduHCessAmount.value);
			 totalTaxable += taxableAmount;
             totalTaxVatCst +=getFloat(taxAmt);
			 totalnoOfPacket+=getFloat(noOfPacket);
			 //console.log("------"+ele+"----------");
			}
			console.log("totalTaxable:"+totalTaxable);
			console.log("totalTaxVatCst:"+totalTaxVatCst);
			
			var freightAmou=frm.elements["proformaMasterDTO.freightAmt"].value;
			// var advanceFreightAmou=frm.elements["proformaMasterDTO.advanceFreight"].value;
			var packingForwardingAmou=getFloat(frm.elements["proformaMasterDTO.packingForwarding"].value);
			 
			var valFrieghtAmoun=getFloat(freightAmou);
			var billNetAmnt = itemSum - discountTotal + round(exciseTotal,0) + round(cessAmount,0) + round(hcessAmount,0) + round(totalTaxVatCst,0)  + round(valFrieghtAmoun,0);
			var v= frm.elements["proformaMasterDTO.otherAmount"].value;
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
			
			
			frm.elements["proformaMasterDTO.billNetAmountRoundOf"].value = round(rountOfAmount,2);
			
			frm.elements["proformaMasterDTO.itemValue"].value =round(itemSum,2);
			frm.elements["proformaMasterDTO.exciseDutyAmount"].value = round(exciseTotal,0);
			frm.elements["proformaMasterDTO.discountAmount"].value = round(discountTotal,2);
			frm.elements["proformaMasterDTO.educationCessAmount"].value =round(cessAmount,0);
			
			frm.elements["proformaMasterDTO.highEducationCessAmount"].value = round(hcessAmount,0);
			
			var exciseDutyAmount1=$("#exciseDutyAmount").val();
			
			
			
			//frm.elements["proformaMasterDTO.billNetAmount"].value=round(getFloat(mySplitResult[0]),2);  
			frm.elements["proformaMasterDTO.billNetAmount"].value=round(getFloat(billNetAmnt)+getFloat($("#packingForwarding").val()),0);  
			
			
			//frm.elements["taxTotal"].value = taxTotal;
			
			frm.elements["proformaMasterDTO.vatAmount"].value =round(totalTaxVatCst,0); 
			frm.elements["proformaMasterDTO.packetTotal"].value =totalnoOfPacket; 
			
			//exiseAmou
			frm.elements["proformaMasterDTO.taxableAmount"].value =round(totalTaxable,2);
			
			frm.elements["proformaMasterDTO.assessableValue"].value =round((itemSum - discountTotal+getFloat($("#packingForwarding").val())),2);
			
		   }

		$('#invoiceForm').change(function() {
		formChange();
		});

		formChange();

	});
	
	
</script>

<script type="text/javascript">
	$(document).ready(
			function() {
				
				$('#vatCstTaxType').change(function() {
					
					if($('#vatCstTaxType').val()== 'Without Tax'){
						$('#taxType').val('Without Tax');
					}
					
				});
				
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
<c:if test="${error.operationName=='Delete'}">
	<script>
$(document).ready(function() {
	$('input').attr('disabled','disabled');	
	$('select').attr('disabled','disabled');
	$('textarea').attr('disabled','disabled');

	 
});

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
//$('#sodatepicker').attr('disabled','disabled');	
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
<script>
$(document).ready(function(){
	
	$("#vatCstTaxType").change(function(){
		
		$("#taxTypeToshow").val("Get Tax");
		 $( "#invoiceForm" ).submit();
		//window.self.location = "getTaxType?operation=show";
	});
	
$("#exciseTaxType").change(function(){
		
		$("#taxTypeToshow").val("Excise");
		 $( "#invoiceForm" ).submit();
		//window.self.location = "getTaxType?operation=show";
	});
	
	
	
	$("#invoiceForm").submit(function() {
		var btn = $(this).find("input[type=submit]:focus" ).val();
		console.log(btn);
		//return false;
		if(($('#timepicker').val()!="" &&  $('#datepicker5').val()!="") || btn=='Item Name'){
			//event.preventDefault();
			$('#itemGroupFlagId').removeAttr('disabled');
	    $('.bookBy').removeAttr('disabled');
	    $('.bookingId').removeAttr('disabled');
	    $('.invoice_datepicker').removeAttr('disabled');
		}
	});
});
</script>
<%-- <%
Integer userId=(Integer)session.getAttribute("userId");
if(userId==null){
%>
<script>
var delUrl='j_spring_security_logout?Logout';
window.self.location = delUrl;
</script>

<%} %> --%>

	<form:form action="save_proforma" method="POST" id="invoiceForm"
		commandName="invoice">

		<form:hidden path="dutyVideNotification" id="dutyVideNotificationId" />
		<form:hidden path="lastInvoiceDate" id="lastDate" />
		<div class="panel-header">
			<div class="panel-title">PROFORMA INVOICE(NOT FOR CENVAT
				PURPOSE)</div>
			<div class="panel-tool"></div>
		</div>
		<div align="left" class="bkgColor"
			style="height: auto; padding-bottom: 14px;">
			<table height="25" width="970" border="0"
				style="width: 450px; margin-top: 2px;"">
				<tr>
					<td width="105">Branch Name<span style="color: #FF0000">*</span>
					</td>
					<td colspan="3" width="115">
					<form:hidden path="taxTypeToshow" id="taxTypeToshow" />
					<form:select
							path="proformaMasterDTO.branchDTO.branchId" id="branchId">

							<form:options items="${branchList}" itemValue="branchId"
								itemLabel="branch" />
						</form:select> <form:hidden style="width:113px; height:21px"
							path="proformaMasterDTO.branchDTO.branchId" id="branchIdHidden"></form:hidden>
					</td>
				</tr>
			</table>
			<table height="81" width="970" border="0" class="tableview">
				<tr>
					<td width="94" height="20">Invoice Series <span
						style="color: #FF0000">*</span>
					</td>
					<td width="101"><form:input
							path="proformaMasterDTO.transactionSeries"
							class="validate[required] text-input" style="width: 48px;"
							data-maxsize="15" readonly="true" disabled="disabled" size="8"
							id="orderSeries" /> <form:hidden
							path="proformaMasterDTO.invoiceAutoId" id="invoiceAutoId"/> <form:hidden
							class="validate[required] text-input" style="width: 43px;"
							path="proformaMasterDTO.finyr" data-maxsize="15" readonly="true"
							disabled="disabled" size="7" id="quotationSeries" /> <form:input
							class="validate[required] text-input" style="width: 43px;"
							path="proformaMasterDTO.invoiceId" data-maxsize="15"
							readonly="true" disabled="disabled" size="7" id="quotationSeries" />
					</td>
					<td width="92" nowrap="nowrap">Invoice Number</td>
					<td width="78">
					<form:hidden path="invoiceNumberToPrint" id="invoiceNumberToPrint"/>
					<form:hidden path="proformaMasterDTO.exciseInvoiceNo" />
					<form:input
							path="proformaMasterDTO.invoiceNumber" readonly="true"
							data-maxsize="15" disabled="disabled" size="11"
							style=" width: 84px;" id="iNumber" />
					</td>
					<td width="52">Date<span style="color: #FF0000">*</span>
					</td>
					<td width="94"><form:input
							class="validate[required] text-input invoice_datepicker" style="width: 88%;"
							path="proformaMasterDTO.invoiceDate" id="datepicker" size="21"
							readonly="true" />
					</td>

					<td width="109" nowrap="nowrap">SO. No.<input type="submit"
						class="searchbtn1" style="width: 100%; float: none;"
						name="operation" value="" onclick="this.value='Sales Order No';" />
					</td>
					<td width="88" nowrap="nowrap">
						<!-- <a href="save_invoice?operationSales Order No"
					class="searchbtn1"></a> --> <form:input
							path="proformaMasterDTO.salesOrderNumber" class="quantity"
							style="text-align:left; width: 98%" data-maxsize="15" id="quoNo"
							size="11" />
					</td>

					<td width="70">SO. Date</td>
					<td>
						<!--   <form:hidden path="proformaMasterDTO.salesOrderDate" id="hiddenSalesOrderDateId" /> -->
						<form:input path="proformaMasterDTO.salesOrderDate"
							id="sodatepicker" style="width:98%" readonly="true" size="16"
							data-maxsize="15" />
					</td>
				</tr>
				<tr>
					<td height="20">Buyer Name <span style="color: #FF0000"
						id="error">*</span>
					</td>
					<td colspan="5">
						<!--  <form:select path="proformaMasterDTO.partyDTO.partyId" items="${partyList}" itemLabel="partyNameCity"   itemValue="partyId" style="width:100%" class="validate[required] text-input"  id="partyId"></form:select> -->
						<form:hidden path="proformaMasterDTO.partyDTO.partyId"
							id="partyIdHidden" /> 
							<form:select path="proformaMasterDTO.partyDTO.partyId" style="width:98%"
							id="partyId">

							<form:options items="${partyList}" itemValue="partyId"
								itemLabel="partyNameCity" />
						</form:select></td>
					<td>City</td>
					<td><form:input path="cityName" data-maxsize="15"
							readonly="true" disabled="disabled" size="11" style="width: 98%"
							class="validate[required] text-input" id="city" /> <form:hidden
							path="proformaMasterDTO.cityId" id="cityId" />
					</td>
					<td>State</td>
					<td><form:input path="state" data-maxsize="15" readonly="true"
							disabled="disabled" size="16" id="state" />
					</td>
				</tr>

				<tr>
					<td height="20">Billing Address</td>
					<td colspan="5"><form:input path="billingAddress"
							class="validate[required] text-input" readonly="true"
							data-maxsize="150" style="padding-left: 2px;    width: 97%;"
							disabled="disabled" size="11" id="billingAddress" />
					</td>
					<td>Phone 1 (0)</td>
					<td><form:input path="phonNo" data-maxsize="15"
							readonly="true" disabled="disabled" style="width: 98%;" size="11"
							id="phoneNo" />
					</td>
					<td nowrap="nowrap">Contact Person</td>
					<td><form:input path="contactPerson" data-maxsize="15"
							readonly="true" disabled="disabled" size="16" id="contactPerson" />
					</td>
				</tr>


				<tr>
					<td height="20">Consignee Name <span style="color: #FF0000"
						id="error">*</span>
					</td>
					<td colspan="5">
						<!--  <form:select path="proformaMasterDTO.partyDTO.partyId" items="${partyList}" itemLabel="partyNameCity"   itemValue="partyId" style="width:100%" class="validate[required] text-input"  id="partyId"></form:select> -->
						<form:select path="proformaMasterDTO.consigneeId"
							style="width:98%" id="partyId1">
							<form:options items="${partyList}" itemValue="partyId"
								itemLabel="partyNameCity" id="buyerId1" />
						</form:select></td>
					<td>City</td>
					<td><form:input path="buyerCityName" data-maxsize="15"
							style="width: 98%;" readonly="true" disabled="disabled" size="11"
							class="validate[required] text-input" id="city1" />
					</td>
					<td>State</td>
					<td><form:input path="buyerState" data-maxsize="15"
							readonly="true" disabled="disabled" size="16" id="state1" />
					</td>
				</tr>


				<tr>
					<td height="20">Address</td>
					<td colspan="5"><form:input path="buyerBillingAddress"
							data-maxsize="150" class="validate[required] text-input"
							readonly="true" style=" padding-left: 2px;    width: 97%;"
							disabled="disabled" size="11" id="billingAddress1" />
					</td>
					<td>Phone 1 (0)</td>
					<td><form:input path="buyerPhonNo" data-maxsize="15"
							readonly="true" disabled="disabled" size="11"
							style=" width: 98%;" id="phoneNo1" />
					</td>
					<td nowrap="nowrap">Contact Person</td>
					<td><form:input path="buyerContactPerson" data-maxsize="55"
							readonly="true" disabled="disabled" size="16" id="contactPerson1" />
					</td>
				</tr>
				<tr>
    <td align="left">Item Group</td>
    <td width="150"><form:select
						path="proformaMasterDTO.itemGroupFlagId"
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
							path="proformaMasterDTO.roadPermitDate" style="width:98%"
							class="datepicker" id="roadPermitDateDatepicker" size="11" />
					</td>
					<td width="60" nowrap="nowrap">Road Permit No</td>
					<td><form:input path="proformaMasterDTO.roadPermitNo"
							onkeypress="return check(event)" data-maxsize="15" size="11" />
					</td>
					<td>Buyer Po. No.</td>
					<td><form:input path="proformaMasterDTO.buyerPoNo"
							onkeypress="return check(event)" data-maxsize="35" size="11" />
					</td>

					<td nowrap="nowrap">Buyer Po Date</td>
					<td><form:input path="proformaMasterDTO.buyerPoDate"
							style="width:98%" class="datepicker" id="datepicker2" size="16" />
					</td>
				</tr>
				<tr>
					<td height="20" width="113" nowrap="nowrap">Transporter Name <span
						style="color: #FF0000">*</span>
					</td>
					<td width="50" colspan="3"><form:select
							path="proformaMasterDTO.transportId" style="width:100%"
							class="validate[required] text-input" id="transporterId">
							<form:options items="${transporterList}"
								itemValue="transporterId" itemLabel="transName" id="aaa" />
						</form:select>
					</td>
					<td width="91">Form No.</td>
					<td>
						<div id="showFormNo">
							<form:input path="proformaMasterDTO.formNo" style="width:98%"
								data-maxsize="25" id="formNo" size="11" />
						</div>
						<div id="hideFormNo">
							<form:input path="proformaMasterDTO.formNo" data-maxsize="25"
								style="width:98%" id="formNo" size="11" readonly="true"
								disabled="true" />
						</div>
					</td>

					<td>Employee Name <span style="color: #FF0000">*</span>
					</td>
					<td colspan="3"><form:select
							path="proformaMasterDTO.employeeDTO.employeeId"
							class="validate[required] text-input" style="width:100%"
							id="employeeId">
							<form:options items="${employeeList}" itemValue="employeeId"
								itemLabel="employeeName" />
						</form:select>
					</td>
				</tr>
				<tr>
					<td height="20">Form required</td>
					<td colspan="1" nowrap="nowrap">
						<div
							style="border: solid 1px; border-color: #7f9db9; border-radius: 3px; width: 120px; background-color: #FFF;">
							<form:radiobutton path="proformaMasterDTO.formReqFlag" value="1"
								id="yes" />
							Yes
							<form:radiobutton path="proformaMasterDTO.formReqFlag" value="0"
								id="no" />
							No
						</div>
					</td>
					<td width="60">Form Type</td>
					<td>
						<div id="showFormType">
							<form:select path="proformaMasterDTO.formTypeId"
								style="width:100%" id="select4">

								<form:option value=""></form:option>
								<form:options items="${masterFormList}" itemValue="mastersId"
									itemLabel="name" />
							</form:select>
						</div>
						<div id="hideFormType">
							<form:select path="proformaMasterDTO.formTypeId"
								style="width:100%" disabled="true" id="select4">
								<form:option value=""></form:option>
								<form:options items="${masterFormList}" itemValue="mastersId"
									itemLabel="name" />
							</form:select>
						</div></td>
					<td>Form Date</td>
					<td>
						<div id="showFormDate">
							<form:input path="proformaMasterDTO.formDate" style="width:98%"
								class="datepicker" id="datepicker1" size="11" />
						</div>
						<div id="hideFormDate">
							<form:input path="proformaMasterDTO.formDate" class="datepicker"
								style="width:98%" id="datepicker1" size="11" readonly="true"
								disabled="true" />
						</div></td>
					<td>LR No.</td>
					<td><form:input path="proformaMasterDTO.lrNo"
							onkeypress="return check(event)" data-maxsize="15" size="20" />
					</td>
					<td nowrap="nowrap">LR Date</td>
					<td><form:input path="proformaMasterDTO.lrDate"
							style="width:98%" class="datepicker" id="lrdatepicker2" size="16" />
					</td>
				</tr>

				<tr>
					<td>Delivery Note No</td>
					<td><form:input path="proformaMasterDTO.deliveryNoteNo"
							onkeypress="return check(event)" data-maxsize="15" size="20" />
					</td>
					<td>Note Date</td>
					<td><form:input path="proformaMasterDTO.deliveryNoteDate"
							style="width:98%" class="datepicker" id="datepicker4" size="11" />
					</td>
					<td>Motor Vehicle No.</td>
					<td><form:input path="proformaMasterDTO.motorVehicleNo"
							style="width:98%" onkeypress="return check(event)"
							data-maxsize="25" size="11" />
					</td>
					<td>Mode/Terms of Payment</td>
					<td ><form:input
							path="proformaMasterDTO.termsOfPayment"
							style="  padding-left: 2px;
    width: 98%;"
							onkeypress="return check(event)" data-maxsize="25" size="11" />
					</td>
					<td nowrap="nowrap">Exicse Type</td>
					<td><form:select style="width:113px; height:21px"
							path="proformaMasterDTO.exciseType" id="exciseTaxType">
							<form:option value="excisable">Excisable</form:option>
						<form:option value="exempted">Exempted</form:option>
						</form:select><%--  <form:hidden style="width:113px; height:21px"
							path="proformaMasterDTO.vatCstTaxType" id="vatCstTaxTypeHidden"></form:hidden> --%>
					</td>
				</tr>

				<tr>
					<td>Despatch Document No.</td>
					<td><form:input path="proformaMasterDTO.despatchDocumentNo"
							onkeypress="return check(event)" data-maxsize="15" size="20" />
					</td>
					<td>Date of Removal<span
						style="color: #FF0000">*</span></td>
					<td><form:input path="proformaMasterDTO.dateOfRemoval"
							style="width:98%" class="validate[required] text-input datepicker" readonly="true" id="datepicker5" size="11" />
					</td>
					<td>Time of Removal<span
						style="color: #FF0000">*</span></td>
					<td><form:input path="proformaMasterDTO.timeOfRemoval"
							style="width:98%" class="validate[required] text-input timepicker" readonly="true" id="timepicker" 
							data-maxsize="25" size="11" />
					</td>
					<td>Despatch Through</td>
					<td><form:input path="proformaMasterDTO.despatchThrough"
							onkeypress="return check(event)" id="despatchThrough"
							data-maxsize="65" style="  padding-left: 2px;    width: 98%;"
							size="11" />
					</td>
					<td nowrap="nowrap">Tax Type</td>
					<td><form:select style="width:113px; height:21px"
							path="proformaMasterDTO.vatCstTaxType" id="vatCstTaxType">
							<form:option value="vat">VAT</form:option>
							<form:option value="cst with c form">CST with C Form</form:option>
							<form:option value="cst w/o c form">CST w/o C Form</form:option>
							<form:option value="Without Tax">Without Tax</form:option>
						</form:select><%--  <form:hidden style="width:113px; height:21px"
							path="proformaMasterDTO.vatCstTaxType" id="vatCstTaxTypeHidden"></form:hidden> --%>
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
								</div>
							</td>
							<td width="135" nowrap="nowrap">
							
							<input type="submit"
								class="searchbtn1" name="operation" value=""
								onclick=" this.value='Item Name';" style="float: none;" /><strong
								style="line-height: 29px; padding-left: 3px;">Item Name</strong>

								<!-- <a href="showItemList"   class="searchbtn1"></a>Item Name  -->
							</td>
							<td width="126	"><div align="center">
									<strong>Description</strong>
								</div>
							</td>
							<td width="73"><input type="submit" class="searchbtn1"
								name="operation" value="" onclick=" this.value='Batch No';"
								style="float: none;" /> <strong
								style="line-height: 29px; padding-left: 3px;">Batch No</strong>
							</td>
							<!-- <td width="89"><div align="center">								<strong>Mfg Date </strong>							</div>						</td>
						<td width="87"><div align="center">								<strong>Exp. Date</strong>							</div>						</td> -->
							<td width="85"><div align="center"><strong>Booked By</strong></div></td>
							<td width="91"><div align="center">
									<strong>UOM </strong>
								</div>
							</td>
							<td width="88"><div align="center">
									<strong>Qty/Packet</strong>
								</div>
							</td>
							<td width="94"><div align="center">
									<strong>No of Packet</strong>
								</div>
							</td>
							<td width="77"><div align="center">
									<strong>Quantity</strong>
								</div>
							</td>
							<td width="95"><div align="center">
									<strong>Basic Rate </strong>
								</div>
							</td>
							<td width="80"><div align="center">
									<strong>Item Value</strong>
								</div>
							</td>
							
							<td width="67"><div align="center">
									<strong>Discount%</strong>
								</div>
							</td>
							<td width="66"><div align="center">
									<strong>Excise %</strong>
								</div>
							</td>
							<td width="66"><div align="center">
									<strong>Cess %</strong>
								</div>
							</td>
							<td width="66"><div align="center">
									<strong>H. Cess %</strong>
								</div>
							</td>

							<td width="58"><div align="center">
									<strong>Tax %</strong>
								</div>
							</td>
							<td width="90"><div align="center">
									<strong>Amount</strong>
								</div>
							</td>
							
							<td width="54">
								<div align="center">
									<strong>Action</strong>
								</div>
							</td>
						</tr>
					</thead>
					<tbody>
						<%
							int i = 1;
						%>
						<c:forEach items="${invoice.proformaDetailList}" var="pr"
							varStatus="s">
							<tr class="sr" style="cursor: pointer" id="${s.index}">
								<td width="34"><%=i%><form:hidden
										path="proformaDetailList[${s.index}].sno" value="${pr.sno}"
										style="text-align:left; background-color:none; border:none; width:100%"
										readonly="true" data-maxsize="15" size="8" />
								</td>
								<td width="125"> ${pr.itemName} <form:hidden
										path="proformaDetailList[${s.index}].itemName"
										value="${pr.itemName}"
										style=" border:1px solid #7f9db9;  height: 17px; width:100%"
										readonly="true" data-maxsize="350" size="8" /> <form:hidden
										path="proformaDetailList[${s.index}].itemId"
										value="${pr.itemId}" data-maxsize="15" size="8" /> <form:hidden
										path="proformaDetailList[${s.index}].sno" value="${pr.sno}"
										style="text-align:right; width:100%" data-maxsize="15"
										size="8" />
								</td>
								<td width="116"><form:input
										path="proformaDetailList[${s.index}].packingDetail"
										value="${pr.packingDetail}"
										style="width:100%;  border:1px solid #7f9db9; "
										data-maxsize="50" size="8" id="packingDetail" />
								</td>
								<td width="63"><form:input
										path="proformaDetailList[${s.index}].batchNo"
										style="border:1px solid #7f9db9;  height: 17px; width:100%"
										readonly="true" data-maxsize="15" size="8" id="batchNo" /> <%-- <fmt:formatDate pattern="dd-MMM-yyyy"
										value="${pr.mfgDate}" var="ab" /> <form:hidden
										path="proformaDetailList[${s.index}].mfgDate" value="${ab}"
										style="text-align:right; background-color: #DDDDDD; width:100%"
										class="mfgDate" data-maxsize="15" size="8" id="mfgDate"
										readonly="true" /> --%> <fmt:formatDate pattern="dd-MMM-yyyy"
										value="${pr.expiryDate}" var="abc" /> <form:hidden
										path="proformaDetailList[${s.index}].expiryDate"
										value="${abc}"
										style="text-align:right; background-color: #DDDDDD; width:100%"
										class="expiryDate" data-maxsize="15" size="8" id="expiryDate"
										readonly="true" />
								</td>
								<td width="75"><form:select path="proformaDetailList[${s.index}].bookedBy"
							id="bookedBy${s.index}" onchange="bookedByFun(${pr.itemId},${s.index})"  style="width:100%"  itemLabel="name" itemValue="value" class="bookingId">
							<form:option value="1">Primary UOM</form:option>
							<form:option value="2">Secondary UOM</form:option>
						</form:select></td>	

								<td width="81"><form:input
										path="proformaDetailList[${s.index}].umoName"
										value="${pr.umoName}"
										style="border:1px solid #7f9db9;  height: 17px; width:100%"
										class="umoName" data-maxsize="15" size="8" id="umoName${s.index}"
										readonly="true" />
										<form:hidden path="proformaDetailList[${s.index}].measurementUnitId" style="text-align:right; border:1px solid #7f9db9;  height: 17px; width:100%" class="validate[custom[number]]"  data-maxsize="15" size="8" id="measurementUnitId${s.index}" />
										<form:hidden path="proformaDetailList[${s.index}].secondaryConvUnit"  class="validate[custom[number]]"  data-maxsize="15" size="8" id="secondaryConvUnit${s.index}" />
		<form:hidden path="proformaDetailList[${s.index}].primaryUOM"  class="validate[custom[number]]"  data-maxsize="15" size="8" id="primaryUOM${s.index}" />
		<form:hidden path="proformaDetailList[${s.index}].primaryUnit"  class="validate[custom[number]]"  data-maxsize="15" size="8" id="primaryUnit${s.index}" />
										<%-- <form:hidden
										path="proformaDetailList[${s.index}].measurementUnitId"
										value="${pr.measurementUnitId}"
										style="text-align:right; border:1px solid #7f9db9; width:100%"
										class="umoName" data-maxsize="15" size="8"
										id="measurementUnitId" /> --%></td>

								<td width="78"><form:input
										path="proformaDetailList[${s.index}].qtyPerPacket"
										value="${pr.qtyPerPacket}"
										style="text-align:right; border:1px solid #7f9db9;  width:100%"
										class="digitOnly validate[custom[number]]" maxFractionDigits="3" data-maxsize="15" size="8" id="qtyPerPacket" />
								</td>
								<td width="84"><form:input
										path="proformaDetailList[${s.index}].noOfPacket"
										value="${pr.noOfPacket}"
										style="text-align:right;  border:1px solid #7f9db9;  width:100%"
										class="digitOnly validate[custom[number]]" data-maxsize="15" size="8" id="noOfPacket" />
								</td>
								<td width="67"><form:input
										path="proformaDetailList[${s.index}].quantity"
										value="${pr.quantity}"
										style="text-align:right; background-color:none !important; border:none !important; width:100%"
										data-maxsize="15" readonly="true" size="8" id="quantity" />
								</td>
								<td width="85"><form:input value="${pr.salesRate}"
										path="proformaDetailList[${s.index}].salesRate"
										style="text-align: right; width: 100% ; border:1px solid #7f9db9;"
										class="digitOnly validate[custom[number]]" data-maxsize="15" size="8"
										id="basicRate" />
								</td>
								<td width="70"><form:input
										path="proformaDetailList[${s.index}].itemValue"
										value="${pr.itemValue}"
										style="text-align:right; background-color: #DDDDDD;  border:1px solid #7f9db9; width:100%"
										class="digite" data-maxsize="15" readonly="true" size="8"
										id="itemValue" />
								</td>
								<td width="57"><form:input
										path="proformaDetailList[${s.index}].discountAmountPerToShow"
										style="text-align:right; border:1px solid #7f9db9; width:100%"
										class="digitOnly validate[custom[number]]" data-maxsize="15" size="8" id="discount"
										value="${pr.discountAmountPerToShow} " /> <form:hidden
										path="proformaDetailList[${s.index}].discountAmount"
										style="text-align:right; width:100%" class="digite"
										data-maxsize="5" size="8" id="discountAmount"
										value="${pr.discountAmount} " /> <form:hidden
										path="proformaDetailList[${s.index}].discountPer"
										style="text-align:right; width:100%" class="digite quantity"
										data-maxsize="15" size="8" id="discountPer"
										value="${pr.discountPer} " />
										
										<form:hidden
										path="proformaDetailList[${s.index}].assessableValue"
										style="text-align:right; width:100%" class="digite quantity"
										data-maxsize="15" size="8" id="assessableId"
										value="${pr.assessableValue} " />
								</td>
								<td width="56"><form:input value="${pr.excisePerc}"
										path="proformaDetailList[${s.index}].excisePerc"
										style="text-align: right; background-color: #DDDDDD; width: 100%"
										class="digite" data-maxsize="15" readonly="true" size="8"
										id="excisePerc" /> <form:hidden value="${pr.exciseAmount}"
										path="proformaDetailList[${s.index}].exciseAmount"
										style="text-align: right; background-color: #DDDDDD; width: 100%"
										class="digite" data-maxsize="15" readonly="true" size="8"
										id="exciseAmount" /> <form:hidden value="${pr.eduCessAmount}"
										path="proformaDetailList[${s.index}].eduCessAmount"
										style="text-align: right; background-color: #DDDDDD; width: 100%"
										class="digite" data-maxsize="15" readonly="true" size="8"
										id="eduCessAmount" /> <form:hidden
										value="${pr.heduCessAmount}"
										path="proformaDetailList[${s.index}].heduCessAmount"
										style="text-align: right; background-color: #DDDDDD; width: 100%"
										class="digite" data-maxsize="15" readonly="true" size="8"
										id="heduCessAmount" />
								</td>
<td width="56">

<form:input
										path="proformaDetailList[${s.index}].cessPercent" value="${pr.cessPercent}"
										style="text-align:right; background-color: #DDDDDD; width:100%"
										class="digite" data-maxsize="15" size="8" readonly="true"
										id="cessPercent" />
</td>
<td width="56">
<form:input
										path="proformaDetailList[${s.index}].hCessPercent" value="${pr.hCessPercent}"
										style="text-align:right; background-color: #DDDDDD; width:100%"
										class="digite" data-maxsize="15" size="8" readonly="true"
										id="hCessPercent" />

</td>

								<td width="48"><form:input
										path="proformaDetailList[${s.index}].tax" value="${pr.tax}"
										style="text-align:right; background-color: #DDDDDD; width:100%"
										class="digite" data-maxsize="15" size="8" readonly="true"
										id="tax" /> <form:hidden
										path="proformaDetailList[${s.index}].taxAmoForVatOrCst"
										value="${pr.taxAmoForVatOrCst}"
										style="text-align:right; background-color: #DDDDDD; width:100%"
										class="digite" data-maxsize="15" size="8" readonly="true"
										id="taxAmoForVatOrCst" /> <!--  <form:input path="proformaDetailList[${s.index}].vatPerc"
								value="${pr.vatPerc}" style="text-align:right; background-color: #DDDDDD; width:100%"
								class="quantity" data-maxsize="15" size="8" readonly="true"
								id="tax" />-->
								</td>
								<td width="80"><form:input
										path="proformaDetailList[${s.index}].netAmount"
										value="${pr.netAmount}"
										style="text-align:right; background-color: #DDDDDD; width:100%  border:1px solid #7f9db9; "
										class="digite" data-maxsize="15" readonly="true" size="8"
										id="amountId" />
								</td>
								
								<td width="44">
									<!-- <input type="submit" class="change1" name="operation" value="edite" /> -->
									<c:if test="${error.operationName!='V' && error.operationName!='V1' }">
									<input type="submit" class="drop1"
									style="height: 12px; width: 12px" name="operation" value=""
									onclick="edit('<c:out value="${s.index}"/>');this.value='remove';" />
</c:if>
<c:if test="${error.operationName=='V' || error.operationName=='V1'}">
<input type="submit" disabled="disabled" class="drop1" style="height: 12px; width: 12px" name="operation" value="" />
</c:if>
								</td>
								<form:hidden path="proformaDetailList[${s.index}].salesOrderItem"  class="bookBy" />
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
					<td width="177" height="20"><strong>Item Count</strong>
					</td>
					<td width="73"><input name="" value="<%=i - 1%>"
						data-maxsize="15" readonly="true" style="width: 75px;" size="18"
						id="itemCountId" />
					</td>
					<td width="277"><strong>Packet Total</strong>
					</td>
					<td width="71"><span style="font-weight: bold"> <form:input
								path="proformaMasterDTO.packetTotal" data-maxsize="15"
								class="digite quantity" style="text-align:right" size="11"
								id="packetTotal" readonly="false" /> </span>
					</td>
					<td width="101" nowrap="nowrap"><span
						style="font-weight: bold;"> Sales Type <span
							style="color: #FF0000">*</span> </span>
					</td>
					<td width="398"><form:input path="proformaMasterDTO.salesType"
							id="salesType" style="width:87%;" readonly="true" /></td>
				</tr>

				<tr>
					<td style="vertical-align: top" height="20">Bill Remark</td>
					<td colspan="5"><form:textarea cols="" rows="4"
							path="proformaMasterDTO.billRemark" id="billRemark" size="16"
							data-maxsize="500"
							style=" height: 80px; border: 1px solid #7F9DB9; border-radius:3px; padding-left: 5px;  width: 96%;" />



					</td>
				</tr>
			</table>
			<table height="81" style="float: left; width: 364px;" width="970"
				border="0" class="tableview">
				<tr>
					<td width="215" colspan="1"><strong>Item Value</strong>
					</td>
					<td width="215">&nbsp;</td>
					<td width="215"><form:input path="proformaMasterDTO.itemValue"
							data-maxsize="15" readonly="true" style="text-align:right"
							class="digite" disabled="disabled" size="11" id="billitemValue" />
					</td>
				</tr>
				<tr>
					<td height="20">Discount Total (-)</td>
					<td>&nbsp;</td>
					<td><form:input path="proformaMasterDTO.discountAmount"
							data-maxsize="15" readonly="true" style="text-align:right"
							class="digite" size="11" id="discountTotal" />
					</td>
				</tr>
				<tr>
					<td colspan="2">Packing & Forwarding</td>
					<td width="215"><form:input
							path="proformaMasterDTO.packingForwarding" data-maxsize="15"
							style="text-align:right" size="11" id="packingForwarding" />
					</td>
				</tr>
				<tr>
					<td height="20">Assessable Value</td>
					<td>&nbsp;</td>
					<td><form:input path="proformaMasterDTO.assessableValue"
							data-maxsize="15" readonly="true" style="text-align:right"
							class="digite" size="11" id="assessableId" />
					</td>
				</tr>
				
				
				<tr>
					<td colspan="1">Excise Total (+)</td>
					<td>&nbsp;</td>
					<td><form:input path="proformaMasterDTO.exciseDutyAmount"
							data-maxsize="15" readonly="true" style="text-align:right"
							class="digite" disabled="disabled" size="11"
							id="exciseDutyAmount" />
					</td>
				</tr>
				<tr>
					<td nowrap="nowrap">Cess % (on Excise) (+)</td>
					<td width="215"><form:input
							path="proformaMasterDTO.educationCessperc"
							style="text-align: right; width:100%;" class="digitOnly digite validate[custom[number]]"
							data-maxsize="15" size="8" id="eduCessPerce" />
					</td>

					<td><form:input path="proformaMasterDTO.educationCessAmount"
							data-maxsize="15" readonly="true" style="text-align:right"
							class="digite" disabled="disabled" size="11"
							id="quotationSeries242" />
					</td>
				</tr>

				<tr>

					<td width="174" height="20">H. Cess %(on Excise)(+)</td>
					<td><form:input path="proformaMasterDTO.highEducationCessPerc"
							style="text-align: right; width:100%;" class="digitOnly digite validate[custom[number]]"
							data-maxsize="15" size="8" id="heduCessPerce" />
					</td>
					<td><form:input
							path="proformaMasterDTO.highEducationCessAmount"
							data-maxsize="15" readonly="true" style="text-align:right"
							class="digite" size="11" id="taxTotal1" />
					</td>
				</tr>

				<tr>
					<td height="20">Taxable Amount</td>
					<td>&nbsp;</td>
					<td><form:input path="proformaMasterDTO.taxableAmount"
							data-maxsize="15" readonly="true" style="text-align:right"
							disabled="disabled" class="digite" size="11" id="others" />
					</td>
				</tr>
				<tr>
					<td height="20">Tax Total (+)</td>
					<td><form:input path="taxType" data-maxsize="25"
							readonly="true" style="width:100%" size="11" id="taxType" />
					</td>
					<td><form:input path="proformaMasterDTO.vatAmount"
							name="taxTotal" data-maxsize="15" readonly="true"
							style="text-align:right" class="digite" size="11"
							id="vatAmountId" /></td>
				</tr>


				<tr>
					<%-- 	<td colspan="2">Bal. Freight Amount</td>
					<td width="215"><form:input
							path="proformaMasterDTO.balanceFreightAmount" data-maxsize="15"
							readonly="true" style="text-align:right" disabled="disabled" class="digite"
							size="11" id="balanceFreightAmount" />
					</td> --%>

					<td>Freight/Type Amount</td>
					<td><form:select path="proformaMasterDTO.freightType"
							id="freightType" style="width:100%" data-maxsize="25">
							<form:option value="ToPay">To Pay</form:option>
							<form:option value=" TobeBilled"> To be Billed</form:option>
							<form:option value="Paid">Paid</form:option>
						</form:select>
					</td>

					<td><form:input path="proformaMasterDTO.freightAmt"
							id="freightAmount" class="digitOnly digite validate[custom[number]]" size="11" data-maxsize="15"
							style="  text-align:right;" onchange="calc(this)" />
					</td>

				</tr>
				
				<tr>
					<td height="20">Others (+/-)</td>
					<td><form:input path="proformaMasterDTO.othersDetail"
							onkeypress="return check(event)" data-maxsize="25"
							id="othersDetail" style="width:100%" size="11" />
					</td>
					<td><form:input path="proformaMasterDTO.otherAmount"
							id="salesOrderRemark" onkeyup="valid2(this)"
							onblur="valid2(this)" size="11" data-maxsize="500" class="digite validate[custom[number]]"
							style="text-align:right" />
					</td>
				</tr>
				<tr>
					<td colspan="2">Rounding Off</td>
					<td width="215"><form:input
							path="proformaMasterDTO.billNetAmountRoundOf" data-maxsize="15"
							style="text-align:right" readonly="true" size="11"
							id="billNetAmountRoundOf" />
					</td>
				</tr>
				<tr>
					<td height="20"><strong>Net Amount</strong>
					</td>
					<td>&nbsp;</td>
					<td><form:input path="proformaMasterDTO.billNetAmount"
							data-maxsize="15" readonly="true" style="text-align:right"
							class="digite" size="11" id="netAmount" />
					</td>
				</tr>
			</table>
			<c:if test="${error.operationName=='Delete'}">
				<c:url value="submitInvoiceList" var="remove_url">
					<c:param name="invoiceAutoId"
						value="${invoice.proformaMasterDTO.invoiceAutoId}"></c:param>
					<c:param name="invoiceNumber"
						value="${invoice.proformaMasterDTO.invoiceNumber}"></c:param>
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
			<c:if
				test="${error.operationName!='Delete' && error.operationName!='V'&& error.operationName!='V1'}">
				<div class="btn" style="width: 300px;">
					<div class="savbtn" style="margin-right: 8px;">
						<c:if test="${invoice.proformaMasterDTO.invoiceAutoId !=null}">
							<input class="updatebtn" type="submit" value="" name="operation"
								value="" onclick="this.value='Save',$('#invoiceForm').validationEngine();" />
						</c:if>
						<c:if test="${invoice.proformaMasterDTO.invoiceAutoId==null}">
							<input id="myButton" style="margin-right: 6px;" class="submit" type="submit" value=""
								name="operation" value=""
								onclick="this.value='Save',$('#invoiceForm').validationEngine();" />
							<!--<input type="submit" value="List" name="operation"> -->
							
							<input id="myButton" class="saveAndPrint" style="width: 99px; height: 24px;" type="submit" value=""
								name="operation" value=""
								onclick="this.value='Save',$('#invoiceForm').validationEngine(),invoiceNoFunction();" />
								
						</c:if>
					</div>
					
					<div class="cancelbtn">



						<c:url value="show_proforma_list?operation=show" var="remove_url">
							<c:param name="operation" value=""></c:param>
						</c:url>
						<a href="${remove_url}" class="cancelbtn"
							onclick="this.value='Cancel';"></a>

					</div>
					<span style="margin-left: 80px;" class="errmsg"></span>
				</div>
			</c:if>


			<c:if test="${error.operationName=='V'}">
				<div class="btn">
					<div class="savbtn">
						<input class="edit_btn" type="button" onclick="editMethod();"
							value="" />
					</div>
					<div class="cancelbtn">
						<c:url value="show_proforma_list?operation=show" var="remove_url">
							<c:param name="operation" value=""></c:param>
						</c:url>
						<a href="${remove_url}" class="cancelbtn"
							onclick="this.value='Cancel';"></a> <span
							style="margin-left: 80px;" class="errmsg"></span>
					</div>
				</div>
			</c:if>

			<c:if test="${error.operationName=='V1'}">
				<div class="btn">
					<div class="savbtn">
						<input class="edit_btn" type="button" onclick="errorMethod();"
							value="" />
					</div>
					<div class="cancelbtn">
						<c:url value="show_proforma_list?operation=show" var="remove_url">
							<c:param name="operation" value=""></c:param>
						</c:url>
						<a href="${remove_url}" class="cancelbtn"
							onclick="this.value='Cancel';"></a> <span
							style="margin-left: 80px;" class="errmsg"></span>
					</div>
				</div>
			</c:if>
	</form:form>
	</div>
</body>
</html>