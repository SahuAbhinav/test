<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
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
		var redrctUrl='get_salesOrder_list';
				
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
 		 var frank_param = getParam('id');
 		 //	confirm('Are you sure you want to delete?');
		 var delUrl='remove_salesOrder?id='+frank_param;
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
 	
	function checkEdit()
		{
		alert('Login user not permit to edit record !!!!!!');
		}
	//	$(document).ready(function() {
 	function editMethod()
 	 { 
 	 var frank_param = $('#salesOrderAutoId').val();
 	 //	confirm('Are you sure you want to delete?');
	 var delUrl='get_salesOrder?id='+frank_param+'&opr=E';
 	 window.self.location = delUrl;  
	}
 	</script>	
 
 	<c:if test="${opr=='V' || salesOrderMasterForm.salesOrderMasterDTO.isUsedInInvoice=='true'}">
	<script type="text/javascript">
		$(document).ready(function() {
		$('input').attr('readonly','readonly');
		$('select').attr('disabled','disabled');
		$('textarea').attr('readonly','readonly');
		$('.datepicker').attr('disabled','disabled');
		$('input:radio').attr('disabled',true);
		
		$(".newWindow").attr("disabled", true);
		$('.datepicker1').attr('disabled','disabled');
		$('.datepicker2').attr('disabled','disabled');
		$('.myTimePicker').attr('disabled','disabled');
		$('.removeItem').attr('disabled','disabled');
		});
	</script>
	</c:if>

<script type="text/javascript">

function bookedByFun(t,uomid) {
	
	 $.ajax({

		type : "POST",
		url : "getUnitName",
		data : "itemId=" +t+"&UOM="+ $('#bookedBy'+uomid).val(),

		success : function(response) {
			// we have the response
			//console.log(response);
			if (response.status == "SUCCESS") {
				
				if(response.result2==null || response.result2=="")
				{
				alert("Secondary Converstion unit not avaliable");
				$('#bookedBy'+uomid).val(1);
				return;
				//$("#measurementUnitId"+uomid).val("");
				}
				
				//console.log(response);
				$("#uomid"+uomid).html(response.result);
				$("#measurementUnitId"+uomid).val(response.result3);
				$("#secondaryConvUnit"+uomid).val(response.result2);
				$("#unitName"+uomid).val(response.result);
				//$("#primaryUOM"+uomid).val(response.result1);
				console.log($('#unitName'+uomid).val());
			}else{
				//console.log("false");
				//console.log($('#bookedBy'+uomid).val(1));
				//console.log($('#bookedBy'+uomid).val());
				
				}
			}

	}); 
};

$(document).ready(function() {
//By default party selected party information

$.ajax({

		type : "POST",
		url : "getPartyInfo",
		data : "partyId=" + $('#consigneeId').val(),

		success : function(response) {
			// we have the response
			if (response.status == "SUCCESS") {
				var city = response.result[0].cityName;
				var state = response.result[0].state;
				var billingAddress = response.result[0].billingAddress;
				var phoneO1 = response.result[0].phoneO1;
				var contactPerson1 = response.result[0].contactPerson1;
				
				var abcd = document.forms[0];
				
				
				
				
				$('#consigneeCity').val(city);
				$('#consigneeState').val(state);
				$('#consigneeBillAddress').val(billingAddress);
				$('#consigneePhone').val(phoneO1);
				$('#consigneeContactPerson').val(contactPerson1);
				$('#shipToAddress').val(billingAddress);
			 
			}
		  }

	});
});


$(function() {
	$('#consigneeId').change(function() {
		$.ajax({

			type : "POST",
			url : "getPartyInfo",
			data : "partyId=" + $('#consigneeId').val(),

			success : function(response) {
				// we have the response
				if (response.status == "SUCCESS") {
					var city = response.result[0].cityName;
					var state = response.result[0].state;
					var billingAddress = response.result[0].billingAddress;
					var phoneO1 = response.result[0].phoneO1;
					var contactPerson1 = response.result[0].contactPerson1;
					       
					$('#consigneeCity').val(city);
					$('#consigneeState').val(state);
					$('#consigneeBillAddress').val(billingAddress);
					$('#consigneePhone').val(phoneO1);
					$('#consigneeContactPerson').val(contactPerson1);
					
					$('#shipToAddress').val(billingAddress);
					
					
				}
			}

		});
	});
});






</script>



<script type="text/javascript">
			
			$(document).ready(function() {  	
				//console.log($('#itemCountId').val());
				if(parseInt($('#itemCountId').val())>0)
					{
					$('#itemGroupFlagId').attr('disabled','disabled');
					
					}
					
					
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
					height		:114,
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
 	
</style>
  <script>
	$(document).ready(function() {
	
		$(".cancelbtn").click(function() {
			window.self.location = "get_salesOrder_list";
		});

	});
</script>

<c:if test="${opr=='R'}">
<script>
$(document).ready(function() {
	$('input').attr('disabled','disabled');	
	$('select').attr('disabled','disabled');
	$('textarea').attr('disabled','disabled');
});
</script>
</c:if>
<script>
	$(document).ready(function() {
		
		$("#formID").submit(function() {
		    $('#itemGroupFlagId').removeAttr('disabled');
		});
		
	
		function abc(cb){
			var v=$(cb).val();
			document.forms["formID"].action="party_changed?partyId="+v;
			document.forms["formID"].submit();	
		}
	$('#partyId').change(function() {
				abc(this);
	});
	
	//get Unit name on chnage of Booked by
	$('#bookedBy').change(function() {
		//console.log(this);
		/* $.ajax({

			type : "POST",
			url : "getUnitName",
			data : "itemId=" + $('#bookedBy').val()+"&UOM="+ $('#bookedBy').val(),

			success : function(response) {
				// we have the response
				console.log(response);
				if (response.status == "SUCCESS") {
					var city = response.result[0].cityName;
					console.log(response);
				 
				}
			  }

		}); */
});
	
	
	});
</script>

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


    
/* $(document).ready(function(){ 
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

  }); */
  $(document).ready(function(){ 
    //called when key is pressed in textbox
	$(".quantity1").keypress(function (e)  
	{ 
	  //if the letter is not digit then display error and don't type anything
	  if( e.which!=8 && e.which!=46 && e.which!=45 && e.which!=0 && (e.which<48 || e.which>57))
	  {
		//display error message
		$(".errmsg1").html("Digits Only").show().fadeOut("slow"); 
	    return false;
      }	
	});

  });
   
  </script>
 <script type="text/javascript">


    
$(document).ready(function(){ 
	
	
 function fixNumFormat(){
	$(".quantity").each(function(){
		
		//var v=parseFloat($(this).val());
		var v=(!$(this).val())? 0 : parseFloat($(this).val());		
		v=v.toFixed(2);
		if(v=='NaN')
			v='0.00';
		$(this).val(v);
	});
	
$(".quantity3").each(function(){
		
		//var v=parseFloat($(this).val());
		var v=(!$(this).val())? 0 : parseFloat($(this).val());		
		v=Number(getFloat(v).toString().match(/^\d+(?:\.\d{0,6})?/));
		if(v=='NaN')
			v='0.000000';
		$(this).val(v);
	});
$(".numberOnly").each(function(){
		
		//var v=parseFloat($(this).val());
		var v=(!$(this).val())? 0 : parseInt($(this).val());		
		v=getInt(v)
		if(v=='NaN')
			v='0';
		$(this).val(v);
	});
	
 }
 fixNumFormat();
 
 
 
 $('#formID').change(function() {
		formChange();
		});

		formChange();
 
   // $(".quantity2").change(function (e)  { 
	   function formChange() {
    	var totalItemValue=0.0;
    	var totalTaxAmt=0.0;
    	var totalExciseAmt=0.0;
    	var totalDiscAmt=0.0;
    	
    	   var cessPerc=0.0;
    		var hcessPerc=0.0;
    		var cessAmt=0.0;
    		var hcessAmt=0.0;
    		
    		var totalCessAmt=0.0;
    		var totalHcessAmt=0.0;
    	
    	$('.qtyCls').each(function(index) {    		
    		
    		var qtyPerPacket=(!$($('.qtyPerPacket')[index]).val())? 0.0 : parseFloat($($('.qtyPerPacket')[index]).val());
    		var noOfPacket=(!$($('.noOfPacket')[index]).val())? 0.0 : parseInt($($('.noOfPacket')[index]).val());
    		if(noOfPacket>0){
    			var qty=(qtyPerPacket*noOfPacket);
    		}
    		
    		//var qty=(!$($('.qtyCls')[index]).val())? 0.0 : parseFloat($($('.qtyCls')[index]).val());
    		var fullFillByInvoice=(!$($('.fullFillByInvoice')[index]).val())? 0.0 : parseFloat($($('.fullFillByInvoice')[index]).val());
    	
    		if(fullFillByInvoice==1){
    		$('#activeStatus'+index).attr('disabled',true);
    		$('#activeStatusTosubmit'+index).val(0);
    	}else{
    		$('#activeStatusTosubmit'+index).attr('disabled',true);
    	}
    		
    		
    		$($('.qtyCls')[index]).val(qty);
    		var basicRate=(!$($('.basicRateCls')[index]).val())? 0.0 : parseFloat($($('.basicRateCls')[index]).val());
    		
    		var itemValue=qty*basicRate;
    		itemValue=isNaN(itemValue) ? 0.0 : parseFloat(itemValue);
    		var discPerc=(!$($('.discPerCls')[index]).val())? 0 : parseFloat($($('.discPerCls')[index]).val());
    		var discAmt=itemValue*discPerc/100.0;
    		
    		
    		totalItemValue=totalItemValue+itemValue;
    		
    		$($('.itemValCls')[index]).val(itemValue);
    		
    		
    		var excisePerc=(!$($('.excisePerCls')[index]).val())? 0 : parseFloat($($('.excisePerCls')[index]).val());
    		//var discPerc=(!$($('.discPerCls')[index]).val())? 0 : parseFloat($($('.discPerCls')[index]).val());
    		var taxPerc=(!$($('.taxPerCls')[index]).val())? 0 : parseFloat($($('.taxPerCls')[index]).val());
    	
    		//New cls
    		var packingForwarding=(!$('#packingForwarding').val())? 0 : parseFloat($('#packingForwarding').val());
    		var totalNoOfItem=parseFloat($('#itemCountId').val());
    		var packingForSingleItem=packingForwarding/totalNoOfItem;
    		//End
    		//console.log("itemValue"+itemValue+"discAmt"+discAmt);
    		var excisableAmount=(itemValue)-(discAmt)+(packingForSingleItem);
    		 
    		//console.log("excisableAmount"+excisableAmount);
    		
    		//var exciseAmt=itemValue*excisePerc/100.0;
    		var exciseAmt=excisableAmount*excisePerc/100.0;
    		//console.log("exciseAmt"+exciseAmt);
    		  //
    	    	 cessPerc=(!$('.cessPerCls').val())? 0 : parseFloat($('.cessPerCls').val());
    			 hcessPerc=(!$('.hcessPerCls').val())? 0 : parseFloat($('.hcessPerCls').val());
    			
    			 cessAmt=exciseAmt*cessPerc/100.0; 
    			 hcessAmt=exciseAmt*hcessPerc/100.0;
    			 //console.log("cessAmt"+cessAmt+"hcessAmt"+hcessAmt);
    		//var taxAmt=(itemValue+cessAmt+hcessAmt+exciseAmt-discAmt)*taxPerc/100.0;
    		
    		//ABHINAV COMMENT
    		//var taxAmt=(excisableAmount+cessAmt+hcessAmt+exciseAmt-discAmt)*taxPerc/100.0;
    		var taxAmt=(excisableAmount+cessAmt+hcessAmt+exciseAmt)*taxPerc/100.0;
    		//console.log("taxAmt"+taxAmt);
    		//console.log("E"+(excisableAmount+cessAmt+hcessAmt+exciseAmt-discAmt)+taxAmt);
    		
    		
    		//var taxAmt=(itemValue+exciseAmt)*taxPerc/100.0;
    		
    		totalTaxAmt=totalTaxAmt+taxAmt;
    		totalExciseAmt=totalExciseAmt+exciseAmt;
    		totalDiscAmt=totalDiscAmt+discAmt;
    		
    		
    		 
		 
		 
   //Cess and HCess perce and amount for detail start
   
        $($('.cessPerc')[index]).val(cessPerc);
		$($('.cessAmnt')[index]).val(cessAmt);
		$($('.hCessPerc')[index]).val(hcessPerc);
		$($('.hCessAmnt')[index]).val(hcessAmt);
		
        //end
		 
    		//
    		var netAmt=itemValue+exciseAmt-discAmt+taxAmt+cessAmt+hcessAmt+packingForSingleItem;
    		//var netAmt=itemValue+exciseAmt+taxAmt;
    		$($('.netAmtCls')[index]).val(netAmt);
    		
    		 totalCessAmt +=cessAmt;
    		 totalHcessAmt +=hcessAmt;
    		
    		
    	});
    	
    	
    	
    	$('#cessAmt').val(totalCessAmt.toFixed(2));
		$('#hcessAmt').val(totalHcessAmt.toFixed(2));
		
    	$('#itemValue').val(totalItemValue.toFixed(2));
    	$('#exciseTotal').val(totalExciseAmt.toFixed(2));
    	$('#discTotal').val(totalDiscAmt.toFixed(2));
    	
    	
    	 var packingForwarding=(!$('#packingForwarding').val())? 0 : parseFloat($('#packingForwarding').val());
		var taxableAmt=totalItemValue+totalExciseAmt+totalCessAmt+totalHcessAmt-totalDiscAmt+packingForwarding;
		$('#taxableAmt').val(taxableAmt.toFixed(2));
		$('#taxTotal').val(totalTaxAmt.toFixed(2));
		
		var otherAmt=(!$('#otherAmt').val())? 0 : parseFloat($('#otherAmt').val());
		
		var freightAmount=(!$('#freightAmount').val())? 0 : parseFloat($('#freightAmount').val());
		//var packingForwarding=(!$('#packingForwarding').val())? 0 : parseFloat($('#packingForwarding').val());
		
		var soNetAmt=totalItemValue+totalExciseAmt+totalCessAmt+totalHcessAmt-totalDiscAmt+totalTaxAmt+otherAmt+freightAmount+packingForwarding;
		
		$('#soNetAmt').val(soNetAmt.toFixed(2));
		
    		//document.forms["formID"].action="calc";
    		//	document.forms["formID"].submit();
    		
$(".quantity").each(function(){
			
			//var v=parseFloat($(this).val());
			var v=(!$(this).val())? 0 : parseFloat($(this).val());
			v=v.toFixed(2);
			
			$(this).val(v);
		});	
$(".quantity3").each(function(){
	
	//var v=parseFloat($(this).val());
	var v=(!$(this).val())? 0 : parseFloat($(this).val());
	v=Number(getFloat(v).toString().match(/^\d+(?:\.\d{0,6})?/));
	
	$(this).val(v);
});
$(".numberOnly").each(function(){
	
	//var v=parseFloat($(this).val());
	var v=(!$(this).val())? 0 : parseInt($(this).val());		
	v=getInt(v)
	if(v=='NaN')
		v='0';
	$(this).val(v);
});
    		}
	   //);
   
	   $(".unitNameCls").each(function(i){
			
		   if($("#unitName"+i).val()!="" && $("#unitName"+i).val()!=null){
			//console.log($("#unitName"+i).val());
			$("#uomid"+i).html($("#unitName"+i).val());
		   }
			
		});
    	  });    	  
  </script>
  
   <script type="text/javascript">    
$(document).ready(function(){ 
    //called when key is pressed in textbox
	$(".newWindow").click(function (e)  
	{ 
		if($('#itemCountId').val()<30){
		document.forms["formID"].action="show_item_list";
		document.forms["formID"].submit();
		}else{
			alert('You cannot add more than 30 Items');
			return false;
		}
	});
  });
  </script>
  <script>
    $(function() {
        $( ".datepicker" ).datepicker({
            changeMonth: true,
            changeYear: true,
			 dateFormat: 'dd-M-yy',
			 yearRange: '-99:+10'
        });
    });
    </script>
    
    <c:if test="${opr=='E' }">
	<script type="text/javascript">
		$(document).ready(function() {
		$('.datepicker1').attr('disabled','disabled');
		});
	</script>
	</c:if>
	
     <script type="text/javascript">
      $(document).ready(function()
       {
    	  var l=$('#lastDate').val();
    	  console.log(l);
    	  
	    $(".datepicker1" ).datepicker({dateFormat : 'dd-M-yy',changeMonth: true,
	          changeYear: true, yearRange: '-99:+0', minDate: new Date(l) , maxDate: '+0M +0D'});
     
       
	    /* $('#date').change(function() {
	    
		$.ajax({
				type : "POST",
				url : "getPendingSalesOrderDate",
				data : "salesOrderDate=" + $("#date").val(),
				success : function(response) {
					// we have the response
					
					var date=response.status;
					 $("#salesOrderValid").val(date);
					var a= $("#salesOrderValid").datepicker({ dateFormat: 'dd-M-yy' }).val();
					
					 
					
					if (response.status == "SUCCESS") {
				   }}
	         });
	    
	    
	    
	  		});
        */
       
       });
      
      function alertFunction(){
      	alert("After select fulfill option you cann't use this item in proforma");
      }
      
  </script>
<style type="text/css">

.qtyCls {}

.basicRateCls{}

.itemValCls{
   border: medium none !important;
    background-color: #eeeeee !important}

.excisePerCls{
   border: medium none !important;
    background-color: #eeeeee !important}

.discPerCls{}

.taxPerCls{
   border: medium none !important;
    background-color: #eeeeee !important}

.netAmtCls{
   border: medium none !important;
    background-color: #eeeeee !important}

.cessCls{}

.hcessCls{}

.removeItem{
		background:url(static/images/drop.png) no-repeat;
		border:none;
		cursor:pointer;
		text-indent: -999em;
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
 
 <style>
  p { color:blue; }
 
 
 }
	/*input {
	width:87%;
	margin-bottom:6px;
	}*/	
	select {
	width:87%;
	height:22px;
	}
	 
	table {
	width:100%; !important}
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

 .newWindow {
		background:url(static/images/search_small.png);
		background-repeat:no-repeat;
		height:12px;
		border:none;
		width:12px !important;
		cursor: pointer;
		
		}
</style>



   <form:form name="input" id="formID" action="processSalesOrder"  modelAttribute="salesOrderMasterForm">
<form:hidden path="salesOrderMasterDTO.salesOrderAutoId" id="salesOrderAutoId"/>
<form:hidden path="lastSalesOrderDate"  id="lastDate" />
  
  <div class="panel-header"   >
	<div class="panel-title">Sales Order Entry</div>
	<div class="panel-tool"></div>
  </div>
  
  <div align="left" class="bkgColor" style=" height:auto; padding-bottom:14px;  ">
    
 <table  height="128" width="967" class="tableview"    border="0"  >
 
        <tr>
      <td height="27">Branch <span style="color: #FF0000">*</span></td>
      <td colspan="5">
      <form:select path="salesOrderMasterDTO.branchDTO.branchId" items="${branchList}" itemLabel="branch" itemValue="branchId" style="width:100%" class="validate[required] text-input"  id="batch"></form:select>
      </td>
    </tr>
    <tr>
      <td width="95" height="20">Order Series <span style="color: #FF0000">*</span></td>
      <td width="109">
            <form:input path="salesOrderMasterDTO.orderSeries" style="width:55px" class="validate[required] text-input" data-maxsize="15" readonly="true"   disabled="true" size="8" id="orderSeries" />
            <form:input path="salesOrderMasterDTO.salesOrderId" style="width:35px"  class="validate[required] text-input"   data-maxsize="15" readonly="true"   disabled="true" size="11" id="salesOrderId" /></td>
      <td width="90">Order Number</td>
      <td width="80"><form:input path="salesOrderMasterDTO.salesOrderNumber"  style="width:93px;" data-maxsize="15" readonly="true" disabled="true" size="11" id="qNumber" /></td>
      <td width="68"> Date<span style="color: #FF0000">*</span> </td>
      <td width="81"><form:input path="salesOrderMasterDTO.salesOrderDate" class="validate[required] text-input datepicker1" id="date"  style="width:97%" size="11" readonly="true" /></td>
      <td width="104">Order Rec. Date.</td>
      <td width="79"><form:input path="salesOrderMasterDTO.orderReceiptDate" class="datepicker" id="orderRecDate"  size="11" readonly="true"  /></td>
      <td width="100">Quotation No.<c:if test="${opr!='E' && opr!='R'&& opr!='V'}"><a href="get_quotation_list?RT=SO"><img src="static/images/search_small.png" title="Search" alt="" /></a></c:if></td>
      <td width="97"><form:input path="salesOrderMasterDTO.quotationNumber"   data-maxsize="15" id="quoNo"  size="11" readonly="true" /></td>
   
    <tr>
      <td height="20">Party Name <span style="color: #FF0000">*</span></td>
      <td colspan="5">
      <c:set var="pdis" value="${not empty salesOrderMasterForm.salesOrderMasterDTO.quotationNumber}"></c:set>
    
      <form:select path="salesOrderMasterDTO.partyDTO.partyId"  style="width:100%" class="validate[required] text-input"  id="partyId" disabled="${pdis}" >
       <c:if test="${salesOrderMasterForm.salesOrderMasterDTO.partyDTO.activeStatus==0 }">
    <form:option value="${salesOrderMasterForm.salesOrderMasterDTO.partyDTO.partyId}">${salesOrderMasterForm.salesOrderMasterDTO.partyDTO.partyName}</form:option>
    </c:if>
          <form:options items="${partyList}" itemLabel="partyName" itemValue="partyId"/>
      </form:select>

      
      </td>
      <td>City</td>
      <td><input type="text"  name="city" value="${salesOrderMasterForm.salesOrderMasterDTO.partyDTO.cityName}" data-maxsize="25" readonly="true" disabled="true" size="11" id="pcity" /></td>
      <td>State</td>
      <td><input type="text"  name="qNumber222"  value="${salesOrderMasterForm.salesOrderMasterDTO.partyDTO.state}" data-maxsize="150" readonly="true" disabled="true" size="11" id="pstate" /></td>
    </tr>
    <tr>
      <td height="20">Billing Address</td>
      <td colspan="5"><input type="text"  value="${salesOrderMasterForm.salesOrderMasterDTO.partyDTO.billingAddress}"  name="quotationSeries2"  data-maxsize="150" readonly="true" style="width:99%;   padding-left: 3px;" disabled="true" size="11" id="pBillAddress" /></td>
      <td>Phone 1 (0)</td>
      <td><input type="text"  value="${salesOrderMasterForm.salesOrderMasterDTO.partyDTO.phoneO1}" name="qNumber2"  data-maxsize="25" readonly="true" disabled="true" size="11" id="pPhone" /></td>
      <td>Contact person</td>
      <td><input type="text"  value="${salesOrderMasterForm.salesOrderMasterDTO.partyDTO.contactPerson1}" name="qNumber2222"  data-maxsize="25" readonly="true" disabled="true" size="11" id="pContactPerson" /></td>
    </tr>
    
    
    <tr>
      <td height="20">Consignee Name <span style="color: #FF0000">*</span></td>
      <td colspan="5">
      <form:select path="salesOrderMasterDTO.consigneeId"  style="width:100%" class="validate[required] text-input"  id="consigneeId" disabled="${pdis}" >
          <form:options items="${partyList}" itemLabel="partyName" itemValue="partyId"/>
      </form:select>

      
      </td>
      <td>City</td>
      <td><form:input path="consigneeCityName"  data-maxsize="25" readonly="true"  size="11" id="consigneeCity" /></td>
      <td>State</td>
      <td><form:input path="consigneeState"  data-maxsize="150" readonly="true"  size="11" id="consigneeState" /></td>
    </tr>
    <tr>
      <td height="20">Billing Address</td>
      <td colspan="5"><form:input path="consigneeBillingAddress"  data-maxsize="150" readonly="true" style="width:99%;   padding-left: 3px;" size="11" id="consigneeBillAddress" /></td>
      <td>Phone 1 (0)</td>
      <td><form:input  path="consigneePhonNo"  data-maxsize="25" readonly="true" size="11" id="consigneePhone" /></td>
      <td>Contact person</td>
      <td><form:input path="consigneeContactPerson"  data-maxsize="25" readonly="true" size="11" id="consigneeContactPerson" /></td>
    </tr>
    
    
    <tr>
      <td height="20">Order Taken By</td>
      <td colspan="3"><form:input onkeypress="return check(event)" path="salesOrderMasterDTO.orderTakenBy" id="orderTakenBy" size="11"    data-maxsize="35"  style="width:95%" /></td>
      <td>Order Time</td>
	  <td><form:input path="salesOrderMasterDTO.orderTime" class="myTimePicker"  style="width:99%"  size="12" id="orderTime" /></td>
      <td>Desire Delivery Dt.</td>
      <td><form:input path="salesOrderMasterDTO.desireDeliveryDate" class="datepicker" id="desireDeliverDt"  size="11" readonly="true" /></td>
	  <td>Planned Date</td>
      <td><form:input path="salesOrderMasterDTO.plannedDeliveryDate" class="datepicker" id="plannedDate"  size="11" readonly="true" /></td>
    </tr>
    <tr>
    <td align="left">Item Group</td>
    <td width="150"><form:select
						path="salesOrderMasterDTO.itemGroupFlagId"
						items="${itemGroupFlagList}" itemLabel="itemGroupFlagName"
						itemValue="itemGroupFlagId" id="itemGroupFlagId"
						class="validate[required]">
					</form:select></td>
    </tr>
  </table>
  <div style="width:906px; margin:0 auto;"> <span style="margin-left:80px;" class="errmsg"></span> </div>
  <div class="gridheadingdiv" style="float:left">
  <table width="668" class="fixmyheader" id="fixmyheader-8">
  <thead>
   <tr>
        <td width="35"><div align="center"><strong>S No.</strong></div></td>
        <td width="135"><div align="center">        
        <strong><c:if test="${opr!='R'}"><input class="newWindow" style="font-size: 11px; font-weight: bold;  padding: 0 0 0 0px;" type="submit" value=" "/></c:if>&nbsp; Item Name</strong>
        </div>
        </td>
        <td width="85"><div align="center"><strong>Booked By</strong></div></td>
        <td width="55"><div align="center"><strong>UOM</strong></div></td>
         <td width="65"><div align="center"><strong>Qty/Packet</strong></div></td>
          <td width="65"><div align="center"><strong>No of Packet</strong></div></td>
        
        <td width="65"><div align="center"><strong>Quantity</strong></div></td>
        <td width="70"><div align="center"><strong>Basic Rate </strong></div></td>
        <td width="70"><div align="center"><strong>Item Value</strong></div></td>
         <td width="70"><div align="center"><strong>Discount% </strong></div></td>
          
        <td width="70"><div align="center"><strong>Excise %</strong></div></td>
       <td width="70"><div align="center"><strong>Cess % </strong></div></td>
           <td width="70"><div align="center"><strong>H. Cess % </strong></div></td>
         
         
        <td width="60"><div align="center"><strong>Tax %</strong></div></td>
        <td width="60"><div align="center"><strong>Amount</strong></div></td>
        <td width="82"><div align="center"><strong>Delivery Days</strong></div></td>
        <td width="100"><div align="center"><strong>Packing Detail</strong></div></td>
        <td width="100"><div align="center"><strong>Invoice No.</strong></div></td>
<td width="100"><div align="center"><strong>Pending Qty.(Pr.)</strong></div></td>
<td width="100"><div align="center"><strong>Supply Qty.(Pr.)</strong></div></td>
        <td width="100"><div align="center"><strong>Order Fulfill</strong></div></td>
        <td width="45" ><div align="center"><strong>Action</strong></div></td>
    </tr>
  </thead>
  <tbody>          <c:set value="0" var="sno" scope="page"></c:set>
  <%
							int i = 1;
						%>
         <c:forEach items="${salesOrderMasterForm.salesOrderMasterDTO.salesOrderDetailDTOList}" var="e" varStatus="s">
     <c:if test="${e.deletedFlag==false }">
      <c:set value="${sno+1 }" var="sno" scope="page"></c:set>
      <tr>
        <td width="25">${sno}</td>
        <td width="125">&nbsp;${e.itemName} </td>
		<td width="75"><form:select path="salesOrderMasterDTO.salesOrderDetailDTOList[${s.index}].bookedBy"
							id="bookedBy${s.index}" onchange="bookedByFun(${e.itemId},${s.index})"  style="width:100%"  itemLabel="name" itemValue="value" >
							<form:option value="1">Primary UOM</form:option>
							<form:option value="2">Secondary UOM</form:option>
						</form:select></td>		
						<!-- .name .mastersId-->
        <td width="45">&nbsp;<span id="uomid${s.index}">${e.masterUnit.name}</span></td>
		
		<td hidden="true"><form:input path="salesOrderMasterDTO.salesOrderDetailDTOList[${s.index}].unitName" class="unitNameCls" id="unitName${s.index}" /></td>
		
		<td hidden="true"><form:input path="salesOrderMasterDTO.salesOrderDetailDTOList[${s.index}].measurementUnitId" style="text-align:right; border:1px solid #7f9db9;  height: 17px; width:100%" class="validate[custom[number]]"  data-maxsize="15" size="8" id="measurementUnitId${s.index}" /></td>
		<td hidden="true"><form:input path="salesOrderMasterDTO.salesOrderDetailDTOList[${s.index}].secondaryConvUnit" style="text-align:right; border:1px solid #7f9db9;  height: 17px; width:100%" class="validate[custom[number]]"  data-maxsize="15" size="8" id="secondaryConvUnit${s.index}" /></td>
		<td hidden="true"><form:input path="salesOrderMasterDTO.salesOrderDetailDTOList[${s.index}].primaryUOM" style="text-align:right; border:1px solid #7f9db9;  height: 17px; width:100%" class="validate[custom[number]]"  data-maxsize="15" size="8" id="primaryUOM${s.index}" /></td>
		<td hidden="true"><form:input path="salesOrderMasterDTO.salesOrderDetailDTOList[${s.index}].primaryUnit" style="text-align:right; border:1px solid #7f9db9;  height: 17px; width:100%" class="validate[custom[number]]"  data-maxsize="15" size="8" id="primaryUnit${s.index}" /></td>
						
		<td width="55"><form:input path="salesOrderMasterDTO.salesOrderDetailDTOList[${s.index}].qtyPerPacket" style="text-align:right; border:1px solid #7f9db9;  height: 17px; width:100%" class=" quantity3 qtyPerPacket  validate[custom[number]]"  data-maxsize="15" size="8" id="qtyPerPacket" /></td>
		<td width="55"><form:input path="salesOrderMasterDTO.salesOrderDetailDTOList[${s.index}].noOfPacket" style="text-align:right; border:1px solid #7f9db9;  height: 17px; width:100%" class=" numberOnly noOfPacket  validate[custom[number]]"  data-maxsize="15" size="8" id="noOfPacket" /></td>

        <td width="55"><form:input path="salesOrderMasterDTO.salesOrderDetailDTOList[${s.index}].quantity" style="text-align:right; border:1px solid #7f9db9;  height: 17px; width:100%" class="  quantity3 qtyCls validate[custom[number]]"  data-maxsize="15" size="8" id="qty" readonly="true" /></td>
        <td width="60"><form:input path="salesOrderMasterDTO.salesOrderDetailDTOList[${s.index}].salesRate" style="text-align:right; border:1px solid #7f9db9;  height: 17px; width:100%" class=" quantity3 basicRateCls  validate[custom[number]]"  data-maxsize="15"  size="8" id="brate" /></td>
        <td width="60"><form:input path="salesOrderMasterDTO.salesOrderDetailDTOList[${s.index}].itemValue" style="text-align:right;   height: 17px; width:100%" readonly="true"  class="itemValCls quantity2 quantity" /></td>
        <td width="60"><form:input path="salesOrderMasterDTO.salesOrderDetailDTOList[${s.index}].discountPerc" style="text-align:right;  border:1px solid #7f9db9; height: 17px; width:100%" class="quantity2 discPerCls quantity validate[custom[number]]"  data-maxsize="5"   size="8" id="qNumber428" /></td>
        
        <td width="60"><form:input path="salesOrderMasterDTO.salesOrderDetailDTOList[${s.index}].excisePerc" style="text-align:right;   height: 17px; width:100%" readonly="true"  class="excisePerCls quantity2 quantity" /></td>
       <td width="60">
        <form:input path="salesOrderMasterDTO.salesOrderDetailDTOList[${s.index}].educationCessPerc" style="text-align:right;  border:1px solid #7f9db9; height: 17px; width:100%" readonly="true" class="quantity2 cessPerc quantity validate[custom[number]]"  data-maxsize="5"   size="8" id="cessPerc" />
        <form:hidden path="salesOrderMasterDTO.salesOrderDetailDTOList[${s.index}].educationCessAmount" style="text-align:right;  border:1px solid #7f9db9; height: 17px; width:100%" class="quantity2 cessAmnt quantity validate[custom[number]]"  data-maxsize="5"   size="8" id="cessAmnt" />
        </td>
        <td width="60">
        <form:input path="salesOrderMasterDTO.salesOrderDetailDTOList[${s.index}].highEducationCessPerc" style="text-align:right;  border:1px solid #7f9db9; height: 17px; width:100%" readonly="true" class="quantity2 hCessPerc quantity validate[custom[number]]"  data-maxsize="5"   size="8" id="hCessPerc" />
        <form:hidden path="salesOrderMasterDTO.salesOrderDetailDTOList[${s.index}].highEducationCessAmount" style="text-align:right;  border:1px solid #7f9db9; height: 17px; width:100%" class="quantity2 hCessAmnt quantity validate[custom[number]]"  data-maxsize="5"   size="8" id="hCessAmnt" />
        </td>    
        <td width="50"><form:input path="salesOrderMasterDTO.salesOrderDetailDTOList[${s.index}].taxPerc" style="text-align:right;   height: 17px; width:100%" readonly="true"  class="taxPerCls quantity2 quantity" /></td>
        <td  width="50"><form:input path="salesOrderMasterDTO.salesOrderDetailDTOList[${s.index}].netAmount" style="text-align:right;   height: 17px; width:100%" readonly="true"  class="netAmtCls quantity2 quantity" /></td>
        <td width="72"><form:input path="salesOrderMasterDTO.salesOrderDetailDTOList[${s.index}].deliveryDays"  style="text-align:right; border:1px solid #7f9db9;  height: 17px; width:100%"  data-maxsize="3"   size="8" id="qNumber4284" /></td>

       
        <td width="90"><form:input path="salesOrderMasterDTO.salesOrderDetailDTOList[${s.index}].packingDetail"   data-maxsize="150" onkeypress="return check(event)" style=" border:1px solid #7f9db9;  height: 17px;  width:100%"    size="8" id="qNumber4210" /></td>
        <td width="90"><form:input path="salesOrderMasterDTO.salesOrderDetailDTOList[${s.index}].invoiceNumber" readonly="true" data-maxsize="150" onkeypress="return check(event)" style=" border:1px solid #7f9db9; height: 17px; width:100%" size="8" /></td>
<%-- <td width="90"><form:input path="salesOrderMasterDTO.salesOrderDetailDTOList[${s.index}].pendingQty" readonly="true" data-maxsize="150" onkeypress="return check(event)" style=" border:1px solid #7f9db9; height: 17px; width:100%" size="8" /></td>
<td width="90"><form:input path="salesOrderMasterDTO.salesOrderDetailDTOList[${s.index}].supplyQty" readonly="true" data-maxsize="150" onkeypress="return check(event)" style=" border:1px solid #7f9db9; height: 17px; width:100%" size="8"/></td> --%>
<td width="90"><form:input path="salesOrderMasterDTO.salesOrderDetailDTOList[${s.index}].primaryPendingQty" readonly="true" data-maxsize="150" onkeypress="return check(event)" style=" border:1px solid #7f9db9; height: 17px; width:100%" size="8" /></td>
<td width="90"><form:input path="salesOrderMasterDTO.salesOrderDetailDTOList[${s.index}].primarySupplyQty" readonly="true" data-maxsize="150" onkeypress="return check(event)" style=" border:1px solid #7f9db9; height: 17px; width:100%" size="8"/></td>
<td width="90">
<form:hidden path="salesOrderMasterDTO.salesOrderDetailDTOList[${s.index}].fullFillByInvoice"  readonly="true" data-maxsize="150" onkeypress="return check(event)" class="fullFillByInvoice" style=" border:1px solid #7f9db9; height: 17px; width:100%" size="8"/>
<form:checkbox path="salesOrderMasterDTO.salesOrderDetailDTOList[${s.index}].activeStatus" value="0"   data-maxsize="150" onclick="alertFunction();" onkeypress="return check(event)" id="activeStatus${s.index}" class="activeStatus" style=" border:1px solid #7f9db9; height: 17px; width:100%" size="8"/>
<form:hidden path="salesOrderMasterDTO.salesOrderDetailDTOList[${s.index}].activeStatus"  readonly="true" data-maxsize="150" onkeypress="return check(event)" id="activeStatusTosubmit${s.index}"/>

</td>
  <td style="text-align:center" width="35">
      <c:if test="${opr!='R'}">
      <input class="removeItem" style="width: 12px;"  type="submit" name="remItemBtn" value="${s.index}"/>
     
		</c:if>
		</td>
      </tr>
      </c:if>
      <%i++;%>
      </c:forEach>
  
  </tbody>
</table>
	
	
  </div>
 <table  height="125" width="1307" class="tableview"    border="0"   style="float:left; width:595px;   margin-top:12px;">
      <tr>
    
        <td width="100" height="20"><strong>Party PO Number</strong>
        <input type="hidden" name="" value="<%=i - 1%>" id="itemCountId" >
        </td>
         <%-- title="${salesOrderMasterForm.salesOrderMasterDTO.patyPoNumber}" --%>
        <td width="175"><form:input path="salesOrderMasterDTO.patyPoNumber" id="orderTakenBy2" type="text" size="14" title="${salesOrderMasterForm.salesOrderMasterDTO.patyPoNumber}" onkeypress="return check(event)"  data-maxsize="50"  style="width:95%" /></td>
        <td width="100"><strong>Party PO Date</strong></td>
        <td width="70"><form:input path="salesOrderMasterDTO.partyPoDate"  class="datepicker" id="desireDeliverDt2"  size="5" readonly="true"/></td>
         <td width="100"><strong>SO Valid Upto</strong></td>
         <td width="69">
         <form:input path="salesOrderMasterDTO.soValidUptoDate"  class="datepicker" id="salesOrderValid"  size="5" /></td>
         </tr>
         
             <tr>
    
        <td width="100" height="20"><strong> Terms Of Payment </strong>
        </td>
        <td width="101"><form:input path="salesOrderMasterDTO.termsOfPayment" id="termsOfPayment" type="text" size="14"  onkeypress="return check(event)"  data-maxsize="25"  style="width:95%" /></td>
        <td width="100"><strong>Terms Of Delivery</strong></td>
        <td width="100"><form:input path="salesOrderMasterDTO.termsOfDelivery" id="termsOfDelivery"  size="11" onkeypress="return check(event)"  data-maxsize="25"  style="width:81%"/></td>
         <td width="100"><strong>Ship To Address</strong></td>
         <td width="100" >
         <form:textarea path="salesOrderMasterDTO.shipToAddress"  id="shipToAddress"  size="11" onkeypress="return check(event)" rows="2"  data-maxsize="150"  style="width:81%"/></td>
         </tr>
         
      <tr>
        <td >Sales Order Remark</td>
        <td colspan="3"  rowspan="2"><form:textarea path="salesOrderMasterDTO.salesOrderRemark" onkeypress="return check(event)"  id="salesOrderRemark"  size="16" data-maxsize="500"  style="width:100%"  />
		</td>
		</tr>
	<table  height="233" width="1307" class="tableview" border="0" style="float:right; margin-right: 8px; width:356px;  margin-top:12px;">
	<tr>
	<td width="122"><strong>Item Value</strong></td>
        <td width="61">&nbsp;</td>
        <td width="95"><form:input path="salesOrderMasterDTO.itemValue" class="quantity" data-maxsize="15" readonly="true" style="text-align:right; width: 61%;"   size="11" id="itemValue" /></td>
        </tr>
         <tr>
        
      
        <td>Discount Total (-)</td>
        <td>&nbsp;</td>
        <td><form:input path="salesOrderMasterDTO.discountAmount" class="quantity" data-maxsize="15" readonly="true" style="text-align:right; width: 61%;"  size="11" id="discTotal" /></td>
      </tr>
      <tr>
					<td colspan="2">Packing & Forwarding</td>
					<td width="215"><form:input
							path="salesOrderMasterDTO.packingForwarding" class="quantity" data-maxsize="15"
							style="text-align:right;width: 61%;" size="11" id="packingForwarding" />
					</td>
				</tr>
    	<tr>
        <td >Excise Total (+)        </td>
        <td >&nbsp;</td>
        <td ><form:input path="salesOrderMasterDTO.exciseDutyAmount"  class="quantity" data-maxsize="15" readonly="true" style="text-align:right; width: 61%;"  size="11" id="exciseTotal" /></td>
      </tr>
       <tr>
      
       
        <td>Cess % (on Excise) (+)        </td>
        <td><form:input path="salesOrderMasterDTO.educationCessPerc" style="text-align:right; width:97%" class="quantity cessPerCls quantity2"  data-maxsize="15"   size="8" id="qNumber42852" /></td>
        <td><form:input path="salesOrderMasterDTO.educationCessAmount"  class="quantity validate[custom[number]]" data-maxsize="5" readonly="true" style="text-align:right; width: 61%;"    size="11" id="cessAmt" /></td>
      </tr>
      <tr>
        <td>H. Cess % (on Excise) (+)</td>
        <td><form:input path="salesOrderMasterDTO.highEducationCessPerc" style="text-align:right; width:97%" class="quantity hcessPerCls quantity2"  data-maxsize="15"   size="8" id="qNumber42853" /></td>
        <td><form:input path="salesOrderMasterDTO.highEducationCessAmount" class="quantity validate[custom[number]]" data-maxsize="5" readonly="true" style="text-align:right; width: 61%;"   size="11" id="hcessAmt" /></td>
      </tr> 
     
      <tr>
       
        <td>Taxable Amount</td>
        <td>&nbsp;</td>
        <td><form:input path="salesOrderMasterDTO.taxableAmount" class="quantity" data-maxsize="15" readonly="true" style="text-align:right; width: 61%;"  size="11" id="taxableAmt" /></td>
      </tr>
      <tr>
        <td>Tax Total (+)</td>
        <td><form:input style="width:100%" path="salesOrderMasterDTO.taxType"   data-maxsize="15" readonly="true"  size="11" id="iNumber2" /></td>
        <td><form:input path="salesOrderMasterDTO.taxTotal"  class="quantity" data-maxsize="15" readonly="true" style="text-align:right; width: 61%;"   size="11" id="taxTotal" /></td>
      </tr>
      <tr>
       <tr>
					<%-- 	<td colspan="2">Bal. Freight Amount</td>
					<td width="215"><form:input
							path="proformaMasterDTO.balanceFreightAmount" data-maxsize="15"
							readonly="true" style="text-align:right" disabled="disabled" class="digite"
							size="11" id="balanceFreightAmount" />
					</td> --%>

					<td>Freight/Type Amount</td>
					<td><form:select path="salesOrderMasterDTO.freightType"
							id="freightType" style="width:100%" data-maxsize="25">
							<form:option value="ToPay">To Pay</form:option>
							<form:option value=" TobeBilled"> To be Billed</form:option>
							<form:option value="Paid">Paid</form:option>
						</form:select>
					</td>

					<td><form:input path="salesOrderMasterDTO.freightAmt"
							id="freightAmount" class="quantity" size="11" data-maxsize="15"
							style="  text-align:right; width: 61%;" onchange="calc(this)" />
					</td>

				</tr>
       
       
        <td>Others (+/-)</td>
        <td><form:input path="salesOrderMasterDTO.othersDetail" onkeypress="return check(event)" data-maxsize="25"  id="date222"  style="width:97%; " size="11" /></td>
        <td><form:input path="salesOrderMasterDTO.otherAmount"  onkeyup="valid2(this)" onblur="valid2(this)" data-maxsize="15"    style="text-align:right;width:61%"    size="11" id="otherAmt" class="quantity2 quantity1 validate[custom[number]]" /></td>
      </tr>
      <tr>
   
        <td><strong>Net Amount</strong></td>
        <td>&nbsp;</td>
        <td><form:input path="salesOrderMasterDTO.soNetAmount" class="quantity" data-maxsize="15" readonly="true" style="text-align:right; width: 61%;"    size="11" id="soNetAmt" /></td>
      </tr>
    </table>
	<div class="btn" style="float: none;" >
	
   <div class="savbtn">
   <c:if test="${opr=='R'}">
	<c:url value="remove_salesOrder" var="remove_url">
					<c:param name="id" value="${salesOrderMasterForm.salesOrderMasterDTO.salesOrderAutoId}"></c:param>
		</c:url>  
		
	</c:if>
  <c:if test="${opr!='R' && opr!='E' && opr!='V'}">
     <input class="submit" style="font-size: 11px; font-weight: bold;  padding: 0 0 0 30px;" type="submit" value=" "/>
     <div class="cancelbtn">
   <a href="get_salesOrder_list"  ></a>   
   </div>
  </c:if>
  <c:if test="${opr=='E'}">
     <input class="updatebtn" style="font-size: 11px; font-weight: bold;  padding: 0 0 0 30px;" type="submit" value=" "/>
     <div class="cancelbtn">
   <a href="get_salesOrder_list"  ></a>   
   </div>
  </c:if>
   </div>
   
   	<c:if test="${opr=='V'}">
   	<c:forEach items="${roleAndRightsDTOList}" var="roleAndRights">
		   <c:if test="${roleAndRights.menuId==sessionScope.menuId}">
    	  	 <c:if test="${roleAndRights.editFlag=='true'}">
    	  	
		  	 <input class="edit_btn"  type="button" onclick="editMethod();" value=""/>
    	      <a href="get_salesOrder_list"  class="cancelbtn" ></a>  
    	      
		     </c:if>
		      <c:if test="${roleAndRights.editFlag=='false'}">
		      <input class="edit_btn"  type="button" onclick="checkEdit();" value=""/>
    	      <a href="get_salesOrder_list"  class="cancelbtn" ></a>  
		      </c:if>
		    
          </c:if>
          </c:forEach>	
    	</c:if>
   
   
     <div style="width:906px; margin:0 auto;"> <span style="margin-left:80px;" class="errmsg"></span> </div>
    
    </div>
  </div>
<input type="hidden" name="partyId" value="" id="partyIdParam">
</form:form>


  