<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page isELIgnored="false"%>

 <c:if test="${not empty(quotationMasterForms.errors)}">
 <input type="hidden" id="errorId" value="${quotationMasterForms.errors.errorMsg}">
 <script type="text/javascript">
 		$(document).ready(function() {
 		var errorId=document.getElementById('errorId');
		alert(errorId.value);
 		});
 	</script>
 </c:if>
 
 <c:if test="${not empty(errors)}"> 
 <input type="hidden" id="errorId" value="${errors.errorMsg}">
 <script type="text/javascript">
 
 		var delUrl='new_issues';
 		$(document).ready(function() {
 		var errorId=document.getElementById('errorId');
		alert(errorId.value);
 		//alert('Duplicate Data In Branch');
 		//window.self.location  = delUrl;
		});
 	</script>
 </c:if>
  <script type="text/javascript">
 		$(document).ready(function() {
 $(function(){
   $("select").multiselect(); 
});
 		});
	</script>
 <c:if test="${opr=='R'}">
 <script type="text/javascript">
		var redrctUrl='get_quotation_list';
				
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
		 var delUrl='remove_quotation?id='+frank_param;
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
	function checkEdit()
		{
		alert('Login User Not Permit to Edit Record !!!!!!');
		}
 	  function editMethod()
 	  { 
 	 	var frank_param = $('#quotationAutoId').val();
	 	var delUrl='get_quotation?id='+frank_param+'&opr=E';
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
		$('.datepicker1').attr('disabled','disabled');
		$('#validUpTo').attr('disabled','disabled');
		$('.datepicker2').attr('disabled','disabled');
		$('input:radio').attr('disabled',true);
		$("input:checkbox").attr("disabled", true);
		$(".newWindow").attr("disabled", true);
		$(".newWindow1").attr("disabled", true);
		$('.datepicker2').attr('disabled','disabled');
		});
	</script>
	</c:if>
 	
 	
<script>
	$(function() {

		//$( "#datepicker" ).datepicker();
		$("#datepicker").datepicker({
			dateFormat : 'dd/mm/yy',
			autoSize : true,
			changeMonth : true,
			changeYear : true,
			altFormat : 'mm/dd/yy',
			altField : '#edd1'
		});
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



<script type="text/javascript">
			
			$(document).ready(function() {  	
				$("input[disable]").css("background-color","#ebebe4" );
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

		 if($('#itemValue42').val()>0){
			 
							 $('#party').attr('disabled','disabled');	
							 }
		 else{
								 $('#partyIdHidden').attr('disabled','disabled');	
								 
							    }
		
		
		
		$(".cancelbtn").click(function() {
			window.self.location = "get_quotation_list";
		});

	});
</script>

<script>
	$(document).ready(function() {	
		
	$(function() {			
			$('#party').change(function() {
				var v=$(this).val();
		//	alert(v);
				document.forms["formID"].action="qoparty_changed?partyId="+v;
				document.forms["formID"].submit();	
		
		});
	});	
	});
</script>
 <script type="text/javascript">
$(document).ready(function(){ 
    //called when key is pressed in textbox
	$(".newWindow").click(function (e)  
	{ 
		if($('#itemValue42').val()<9){
		 document.forms["formID"].action="show_item_list_qo";
		 document.forms["formID"].submit();
		 document.forms["formID"].submit();
		}else{
			alert('You cannot add more than 9 Items');
			return false;
		}
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


<script type="text/javascript">
    
$(document).ready(function(){ 
	 function fixNumFormat(){
		 $(".quantity").each(function(){
			 	var v=(!$(this).val())? 0 : parseFloat($(this).val());
				v=v.toFixed(2);
				if(v=='NaN')
					v='0.00';
				$(this).val(v);
			});
		 }
		 fixNumFormat();
    
		 
		 $('#formID').change(function() {
				formChange();
			});

		formChange();
		 
		 
		 
    //$(".quantity").change(function (e){ 
    	function formChange() {
    	
    	var totalItemValue=0.0;
    	var totalTaxAmt=0.0;
    	var totalExciseAmt=0.0;
    	var totalDiscAmt=0.0;
    	//
    	    var cessPerc=0.0;
    		var hcessPerc=0.0;
    		var cessAmt=0.0;
    		var hcessAmt=0.0;
    	
    		var totalCessAmt=0.0;
    		var totalHcessAmt=0.0;
    		
    		
    	$('.qtyCls').each(function(index) {
    		
    	//	var qty=$($('.qtyCls')[index]).val();
    		var qty=(!$($('.qtyCls')[index]).val())? 0.0 : parseFloat($($('.qtyCls')[index]).val());
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
    		
    		var excisableAmount=(itemValue)-(discAmt);
    		
    		var exciseAmt=excisableAmount*excisePerc/100.0;
    		   
    	//	
   		 cessPerc=(!$('.cessPerCls').val())? 0 : parseFloat($('.cessPerCls').val());
   		 hcessPerc=(!$('.hcessPerCls').val())? 0 : parseFloat($('.hcessPerCls').val());
   		
   		 cessAmt=exciseAmt*cessPerc/100.0; 
   		 hcessAmt=exciseAmt*hcessPerc/100.0; 
   		 
    		var taxAmt=(cessAmt+hcessAmt+itemValue+exciseAmt-discAmt)*taxPerc/100.0;
    		//var taxAmt=(itemValue+exciseAmt)*taxPerc/100.0;
    		
    		totalTaxAmt=totalTaxAmt+taxAmt;
    		totalExciseAmt=totalExciseAmt+exciseAmt;
    		totalDiscAmt=totalDiscAmt+discAmt;
    		
    		
    		
    		
    		
    		var netAmt=itemValue+exciseAmt-discAmt+taxAmt+cessAmt+hcessAmt;
    		//var netAmt=itemValue+exciseAmt+taxAmt;
    		$($('.netAmtCls')[index]).val(netAmt);
    		 totalCessAmt +=cessAmt;
    		 totalHcessAmt +=hcessAmt;
    		
    	});
    	
    	$('#cessAmt').val(totalCessAmt.toFixed(2));
		$('#hcessAmt').val(totalHcessAmt.toFixed(2));
		
    	$('#itemValue').val(totalItemValue);
    	$('#exciseTotal').val(totalExciseAmt);
    	$('#discTotal').val(totalDiscAmt);
    	
    	/* var cessPerc=(!$('.cessPerCls').val())? 0 : parseFloat($('.cessPerCls').val());
		var hcessPerc=(!$('.hcessPerCls').val())? 0 : parseFloat($('.hcessPerCls').val());
		
		var cessAmt=totalExciseAmt*cessPerc/100.0; 
		var hcessAmt=totalExciseAmt*hcessPerc/100.0; 
		$('#cessAmt').val(cessAmt);
		$('#hcessAmt').val(hcessAmt); */
		
		var taxableAmt=totalItemValue+totalExciseAmt+totalCessAmt+totalHcessAmt-totalDiscAmt;
		$('#taxableAmt').val(taxableAmt);
		$('#taxTotal').val(totalTaxAmt);
		
		var otherAmt=(!$('#otherAmt').val())? 0 : parseFloat($('#otherAmt').val());
		
		var soNetAmt=totalItemValue+totalExciseAmt+totalCessAmt+totalHcessAmt-totalDiscAmt+totalTaxAmt+otherAmt;
		
		$('#qoNetAmt').val(soNetAmt);
		
		$(".quantity").each(function(){
			var v=(!$(this).val())? 0 : parseFloat($(this).val());
			//var v=parseFloat($(this).val());
			
			v=v.toFixed(2);
			
			$(this).val(v);
		});
    		}
    	//);
    	  });
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
  $(document).ready(function(){ 
    //called when key is pressed in textbox
	$(".quantity1").keypress(function (e)  
	{ 
	  //if the letter is not digit then display error and don't type anything
	  if( e.which!=8 && e.which!=46 && e.which!=0 && (e.which<48 || e.which>57))
	  {
		//display error message
		$(".errmsg1").html("Digits Only").show().fadeOut("slow"); 
	    return false;
      }	
	});

  });
   
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
	          changeYear: true, yearRange: '-99:+0', minDate: new Date(l), maxDate: '+0M +0D'});
	   
	    $(".datepicker2" ).datepicker({dateFormat : 'dd-M-yy',changeMonth: true,
	          changeYear: true, yearRange: '-99:+0', maxDate: '+0M +0D'});
	    
	    
	    $("#validUpTo" ).datepicker({dateFormat : 'dd-M-yy',changeMonth: true,
	          changeYear: true, yearRange: '-99:+10',  minDate: '+0M +0D'});
	    
      });
  </script>
  <style>
   .newWindow {
		background:url(static/images/search_small.png);
		background-repeat:no-repeat;
		height:12px;
		border:none;
		width:12px;
		cursor: pointer;

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
<style type="text/css">

.qtyCls {}

.basicRateCls{}

.itemValCls{
   border: medium none !important;
    background-color: #eeeeee !important}

.excisePerCls{
    border: medium none !important;
    background-color: #eeeeee !important
}

.discPerCls{}

.taxPerCls{
   border: medium none !important;
    background-color: #eeeeee !important}

.netAmtCls{
   border: medium none !important;
    background-color: #eeeeee !important
}

.cessCls{}

.hcessCls{}

.removeItem{
		background:url(static/images/drop.png) no-repeat;
		border:none;
		cursor:pointer;
		text-indent: -999em;
		}  
 </style>
<style type="text/css">


  
  p { color:blue; }
 
	/*input {
	width:87%;
	margin-bottom:6px;
	}*/	
	select {
	width:87%;
	height:22px;
	}
	 
 
</style>
<style>
.errorblock1 {
	color: #000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}

</style>

<form:form  id="formID" action="processQuotation"   modelAttribute="quotationMasterForm">
<form:errors path="*" cssClass="errorblock1"></form:errors>
<form:hidden path="quotationMasterDTO.quotationAutoId" id="quotationAutoId"/>
<form:hidden path="lastQuotationDate"  id="lastDate" />
   
  <div class="panel-header">
	<div class="panel-title">Quotation Entry</div>
	<div class="panel-tool"></div>
  </div>
  
  <div align="left" class="bkgColor"   >
     
 <table  height="71" width="1066" class="tableview"   border="0"   style="  margin-top:12px;">
  <tr>
      <td  width="122" height="27">Branch <span style="color: #FF0000">*</span></td>
      <td colspan="5">
      <form:select path="quotationMasterDTO.branchDTO.branchId" items="${branchList}" itemLabel="branch" itemValue="branchId" style="width:98%" class="validate[required] text-input"  id="batch"></form:select>
      </td>
      <td width="106">Reference No.</td>
      <td width="76"><form:input path="quotationMasterDTO.referenceNo"  style="width:98%" onkeypress="return check(event)"  data-maxsize="15" id="referenceNo" 
      size="11" /></td>
    </tr>
    <tr>
      <td  height="27" >Quotation Series <span style="color: #FF0000">*</span></td>
      <td width="111"><form:input path="quotationMasterDTO.quotationSeries" data-maxsize="15" readonly="true"    size="8" id="quotationSeries" /><form:input path="quotationMasterDTO.quotationId"  data-maxsize="15" style="width:36%" readonly="true"  size="8" id="quotationSeries" /></td>
      <td width="111">Quotation. Number</td>
      <td width="96"><form:input path="quotationMasterDTO.quotationNumber" style="width: 104px;" data-maxsize="11" readonly="true"  size="11" id="qNumber" /></td>
      <td width="89">Date</td>
      <td width="95"><form:input path="quotationMasterDTO.quotationDate"  style="width:87%" class="datepicker1 validate[required] text-input" readonly="true" id="date"  size="11" /></td>
      
      <td width="117">Reference Date</td>
      <td width="89"><form:input path="quotationMasterDTO.referenceDate"  style="width:100%" class="datepicker2" readonly="true" id="referenceDate"  size="11" /></td>
    </tr>
    <tr>
      <td height="29">Party Name <span style="color: #FF0000">*</span></td>
      <td colspan="5">
      <form:hidden path="quotationMasterDTO.partyDTO.partyId" id="partyIdHidden"></form:hidden>
      <form:select path="quotationMasterDTO.partyDTO.partyId" style="width:98%" class="validate[required] text-input"  id="party">
      <c:if test="${quotationMasterForm.quotationMasterDTO.partyDTO.activeStatus==0}">
    <form:option value="${quotationMasterForm.quotationMasterDTO.partyDTO.partyId}">${quotationMasterForm.quotationMasterDTO.partyDTO.partyName}</form:option>
    </c:if>  
      <form:options items="${partyList}" itemLabel="partyName" itemValue="partyId"/>        
      </form:select>
      
      </td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
    </tr>
  </table>
  <div style="width:906px; margin:0 auto;"> <span style="margin-left:80px;" class="errmsg"></span> </div>
  <div class="gridheadingdiv" style="float:left">
<table width="668" class="fixmyheader" id="fixmyheader-8">
  <thead>
   <tr>
        <td width="38"><div align="center"><strong>S No.</strong></div></td>
        <td width="135"><div align="center"><strong><c:if test="${opr!='R'}"><input type="submit" class="newWindow" style="font-size: 11px; font-weight: bold;width:13px;  padding: 0 0 0 0px;"  value=" "/></c:if>&nbsp;&nbsp;Item Name</strong></div></td>
        <td width="55"><div align="center"><strong>UOM</strong></div></td>
        <td width="62"><div align="center"><strong>Quantity</strong></div></td>
        <td width="86"><div align="center"><strong>Basic Rate </strong></div></td>
        <td width="64"><div align="center"><strong>Item Value</strong></div></td>
        <td width="65"><div align="center"><strong>Discount %</strong></div></td>
        <td width="55"><div align="center"><strong>Excise %</strong></div></td>
        <td width="50"><div align="center"><strong>Tax %</strong></div></td>
        <td width="60"><div align="center"><strong>Amount</strong></div></td>
        <td width="90"><div align="center"><strong>Delivery Days</strong></div></td>
        <td width="103"><div align="center"><strong>Packing Detail</strong></div></td>
        <td width="45"><div align="center"><strong>Action</strong></div></td>
      </tr>
  </thead>
  <tbody>
    <c:set value="0" var="sno" scope="page"></c:set>
      <c:forEach items="${quotationMasterForm.quotationMasterDTO.quotationDetailDTOList}" var="e" varStatus="s">
      <c:if test="${e.deletedFlag==false }">
      <c:set value="${sno+1 }" var="sno" scope="page"></c:set>
      <tr>
        <td width="28">${sno}</td>
        <td width="125">&nbsp;${e.itemName} </td>
        <td width="45">&nbsp;<span>${e.measurementUnitMasterDTO.name}</span></td>
        <td width="52"><form:input path="quotationMasterDTO.quotationDetailDTOList[${s.index}].quantity" style="text-align:right; width:100%; border:1px solid #7f9db9; " class="quantity qtyCls validate[custom[number]]"  data-maxsize="15"   size="8" id="qty" /></td>
        <td width="76"><form:input path="quotationMasterDTO.quotationDetailDTOList[${s.index}].salesRate" style="text-align:right; width:100%; border:1px solid #7f9db9;" class="quantity basicRateCls validate[custom[number]]"  data-maxsize="10"   size="8" id="brate" /></td>
        <td width="54"><form:input path="quotationMasterDTO.quotationDetailDTOList[${s.index}].itemValue" readonly="true" style="text-align:right; width:100%" class="quantity itemValCls "  data-maxsize="10"   size="8"  /></td>
        <td width="55"><form:input path="quotationMasterDTO.quotationDetailDTOList[${s.index}].discountPerc" style="text-align:right; width:100%; border:1px solid #7f9db9;" class="quantity discPerCls validate[custom[number]]"  data-maxsize="5"   size="8" id="qNumber428" /></td>
        <td width="45"><form:input path="quotationMasterDTO.quotationDetailDTOList[${s.index}].excisePerc" readonly="true" style="text-align:right; width:100%; background-color:none; " class="quantity excisePerCls"  data-maxsize="10"   size="8"  /></td>
        
        <td width="40"><form:input path="quotationMasterDTO.quotationDetailDTOList[${s.index}].taxPerc" readonly="true" style="text-align:right; width:100%" class="quantity taxPerCls"  data-maxsize="10"   size="8"  /></td>
        <td width="50"><form:input path="quotationMasterDTO.quotationDetailDTOList[${s.index}].netAmount" readonly="true" style="text-align:right; width:100%" class="netAmtCls quantity"  data-maxsize="10"   size="8"  /></td>
        <td width="80"><form:input path="quotationMasterDTO.quotationDetailDTOList[${s.index}].deliveryDays"  style="text-align:right; width:100% ; border:1px solid #7f9db9;" class="quantity1"  data-maxsize="3"   size="8" id="qNumber4284" /></td>
        <td width="93"><form:input path="quotationMasterDTO.quotationDetailDTOList[${s.index}].packingDetail"   data-maxsize="250" style="  width:100%; border:1px solid #7f9db9;" onkeypress="return check(event)"   size="8" id="qNumber4210" /></td>
      <td width="36" style="text-align: center;" >
      <c:if test="${opr!='R'}">
      <c:if test="${opr!='V' }">
      <input class="removeItem"  type="submit" name="remItemBtn" value="${s.index}"/>
     </c:if><c:if test="${opr=='V' }">
       <input class="removeItem"  type="submit" disabled="disabled" name="" value=""/>
     </c:if>
   	</c:if>
	</td>
      </tr>
    </c:if>
        </c:forEach>
  
  </tbody>
</table>
  </div>
 
 <table  height="151" width="967" class="tableview" style="width:560px; float:left; "  border="0"   style="float:left; margin-top:12px;">
      <tr>
        <td width="109" height="20"><strong>Item Count</strong></td>
        <td width="100">
         <form:input path="quotationMasterDTO.itemCount"   class="quantity" readonly="true" style="text-align:right"  data-maxsize="15"    size="11" id="itemValue42" /></td>
        <td width="109" > <strong>Packet Total</strong></td>
        <td colspan="1"><form:input path="quotationMasterDTO.packetTotal"  readonly="false"  class="quantity1" style="text-align:right"  data-maxsize="10"    size="13" id="totalQty" /></td>
           </tr>
           <tr>
           
        <td height="20">Payment Terms</td>
        <td colspan="3"><form:input path="quotationMasterDTO.paymentTerms" type="text" size="16"  onkeypress="return check(event)"  data-maxsize="150" style="width:100%"  name="paymentTerms" /></td>
          </tr>
     <tr>
        <td height="20">Quotation Valid upto</td>
        <td colspan="3"><form:input path="quotationMasterDTO.validUpTo"  readonly="true" id="validUpTo"  size="11" /></td>
          </tr>
      <tr>
        <td height="20">Quotation Remark</td>
        <td colspan="3"><form:textarea path="quotationMasterDTO.quotationRemark"  onkeypress="return check(event)" type="text" size="16"       style="width:100%"  name="quotationRemark" /></td>
              </tr></table>
      <table  height="152" width="967" class="tableview" style="width:392px;  margin-right: 7px; float: right"  border="0"  >
      
      <tr>
        <td width="499"><strong>&nbsp;Item Value</strong></td>
        <td width="55">&nbsp;</td>
        <td width="291"><form:input path="quotationMasterDTO.itemValue"   class="quantity" readonly="true" style="text-align:right"  data-maxsize="15"    size="11" id="itemValue" /></td>
      </tr>
       <tr>
        <td height="20">Discount Total(-)</td>
        <td>&nbsp;</td>
        <td><form:input path="quotationMasterDTO.discountAmount"  class="quantity" readonly="true" style="text-align:right"  data-maxsize="10"    size="11" id="discTotal" /></td>
      </tr>
      <tr>
      <td>Excise Total (+)</td>
        <td>&nbsp;</td>
        <td><form:input path="quotationMasterDTO.exciseDutyAmount" class="quantity" readonly="true" style="text-align:right"  data-maxsize="10"    size="11" id="exciseTotal"  /></td>
        </tr>
    
  
      <tr>
        
        <td height="20">Cess % (on Excise) (+) 
         &nbsp; &nbsp; &nbsp; &nbsp;</td>
        <td><form:input path="quotationMasterDTO.educationCessPerc"  class="quantity cessPerCls validate[custom[number]]" style="text-align:right"  data-maxsize="5"    size="11" id="taxTotal3" /></td>
        <td><form:input path="quotationMasterDTO.educationCessAmount"  class="quantity" readonly="true" style="text-align:right"  data-maxsize="5"    size="11" id="cessAmt" /></td>
      </tr>
      <tr>
        <td height="20">H. Cess % (on Excise) (+)
        &nbsp; &nbsp;</td>
        <td><form:input path="quotationMasterDTO.highEducationCessPerc"  class="quantity hcessPerCls validate[custom[number]]" style="text-align:right"  data-maxsize="5"    size="11" id="taxTotal32" /></td>
        <td><form:input path="quotationMasterDTO.highEducationCessAmount"  class="quantity" readonly="true" style="text-align:right"  data-maxsize="15"    size="11" id="hcessAmt" /></td>
      </tr>
     
      <tr>
        <td height="20">Taxable Amount</td>
        <td>&nbsp;</td>
        <td><form:input path="quotationMasterDTO.taxableAmount"  class="quantity" readonly="true" style="text-align:right"  data-maxsize="10"    size="11" id="taxableAmt"  /></td>
      </tr>
      <tr>
        <td height="20">Tax Total (+)</td>
        <td><form:input path="quotationMasterDTO.taxType"  style="width:98%"  data-maxsize="15" readonly="true"  size="11" id="iNumber2" /></td>
        <td><form:input path="quotationMasterDTO.taxTotal"  class="quantity" readonly="true" style="text-align:right"  data-maxsize="15"    size="11" id="taxTotal" /></td>
      </tr>
      <tr>
      <td>Others (+/-)</td>
        <td><form:input path="quotationMasterDTO.othersDetail" data-maxsize="25" onkeypress="return check(event)" id="date222"  style="width:95%" size="11" /></td>
        <td><form:input path="quotationMasterDTO.otherAmount"   class="quantity validate[custom[number]]" onkeyup="valid2(this)" onblur="valid2(this)" style="text-align:right"  data-maxsize="10"    size="11" id="otherAmt" /></td>
   
      </tr>
      <tr>
      <td><strong>&nbsp;Net Amount</strong></td>
        <td>&nbsp;</td>
        <td><form:input path="quotationMasterDTO.qoNetAmount"   class="quantity" readonly="true" style="text-align:right"  data-maxsize="15"    size="11" id="qoNetAmt"  /></td>
      
      </tr>
      
    </table>
	
	<c:if test="${opr=='R'}">
	<div class="btn" style="float: none;" >
	<c:url value="remove_quotation" var="remove_url">
		<c:param name="id" value="${quotationMasterForm.quotationMasterDTO.quotationAutoId}"></c:param>
	</c:url> 
	</c:if>
	<c:if test="${opr=='E'}">
	<div class="savbtn">
     <input class="updatebtn" style="font-size: 11px; font-weight: bold;  padding: 0 0 0 30px;" type="submit" value=" "/>
      <a class="cancelbtn" href="get_quotation_list"  ></a> 
   </div>     
     </c:if>
     
     
     <c:if test="${opr=='V'}">
	<div class="savbtn">
	<c:forEach items="${roleAndRightsDTOList}" var="roleAndRights">
		   <c:if test="${roleAndRights.menuId==sessionScope.menuId}">
    	  	 <c:if test="${roleAndRights.editFlag=='true'}">
		  	 <input class="edit_btn"  type="button" onclick="editMethod();" value=""/>
    	      <a href="get_quotation_list"  class="cancelbtn" ></a>  
		     </c:if>
		      <c:if test="${roleAndRights.editFlag=='false'}">
		      <input class="edit_btn"  type="button" onclick="checkEdit();" value=""/>
    	      <a href="get_quotation_list"  class="cancelbtn" ></a>  
		      </c:if>
		    
          </c:if>
          </c:forEach>	
   		
   </div>     
     </c:if>
        
	<c:if test="${opr!='R' && opr!='E' && opr!='V'}">
   <div class="savbtn">
     <input class="submit" style="font-size: 11px; font-weight: bold;  padding: 0 0 0 30px;" type="submit" value=" "/>
       <a class="cancelbtn" href="get_quotation_list"  ></a> 
   </div>
   </c:if>
 
 <div style="width:906px; margin:0 auto;"> <span style="margin-left:80px;" class="errmsg"></span> </div>
    </div>
  
  
  </form:form>
