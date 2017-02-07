<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
 <c:if test="${not empty(notDelete)}">

 <script type="text/javascript">
 		$(document).ready(function() {
 		var redrctUrl='get_finishedGoods_list';
		alert('Warning : Can not delete because it is used in blanket');
		window.self.location = redrctUrl;
 		});
 	</script>
 </c:if>
 

 <c:if test="${opr=='R'}">
 <script type="text/javascript">
		var redrctUrl='get_finishedGoods_list';
				
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
 		 var fn_no = getParam('finishedGoodsNumber');
 		 //	confirm('Are you sure you want to delete?');
		 var delUrl='remove_finishedGoods?id='+frank_param+'&finishedGoodsNumber='+fn_no;
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
 	function editMethod()
 	 { 
 	 var frank_param = $('#finishedGoodsAutoId').val();
	 var delUrl='get_finishedGoods?id='+frank_param+'&opr=E';
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
		$(".newWindow").attr("disabled", true);
		$(".newWindow1").attr("disabled", true);
		$('.datepicker2').attr('disabled','disabled');
		});
	</script>
	</c:if>
	
	<c:if test="${opr=='E' }">
	<script type="text/javascript">
		$(document).ready(function() {
		$('.datepicker2').attr('disabled','disabled');
		});
	</script>
	</c:if>
 	
 	
 	
 	 <script type="text/javascript">
 	      $(document).ready(function()
       {	
    	  var l=$('#lastDate').val();
    	  console.log(l);
    	  
    	  //console.log(theyear,themonth,thetoday);
    	   $(".datepicker2" ).datepicker({dateFormat : 'dd-M-yy',changeMonth: true,
 	          changeYear: true, yearRange: '-99:+0', minDate: new Date(l),maxDate: '+0M +0D'});
      });
  </script>
  
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

/* 	function approvedCheck(){
		var staus=	$("#aprovedId").attr("checked");
		
		if(staus=='checked') {
		 if(confirm("Are you sure about approving the record, as you will not be able to edit / delete it after approval.")) 
		   {
		    return true;
		 	}
		 return false;	
		}
		 return true;	
		 
	} */
	
	$(function() {
$('#aprovedId').click(function() {
var staus=	$("#aprovedId").attr("checked");
		
if(staus=='checked') {
	alert('Are you sure about approving the record, as you will not be able to edit / delete it after approval.');
}
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
					height		:273,
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
$(document).ready(function(){ 
$("input[readonly]").css("background-color","#ebebe4");
});
</script>
  <script>
	$(document).ready(function() {
		$(".cancelbtn").click(function() {
			window.self.location = "get_finishedGoods_list";
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

   function checkApproved(){
		
		alert("You can not edit / delete this record as it is already approved.");
		return false;
	}
    
$(document).ready(function(){ 
    //called when key is pressed in textbox
	$(".newWindow").click(function (e)  
	{ 
		document.forms["formID"].action="show_item_list_fg";
		 document.forms["formID"].submit();
	});
  });
  </script>
  
<style type="text/css">

.qtyCls {}

.basicRateCls{}

.itemValCls{}

.excisePerCls{}

.discPerCls{}

.taxPerCls{}

.netAmtCls{}

.cessCls{}

.hcessCls{}
 </style>
 
 <style>
  p { color:blue; }
 
	/*input {
	width:87%;
	margin-bottom:6px;
	}*/	
	select {
	width:87%;
	height:22px;
	}
 
 .newWindow {
		background:url(static/images/search_small.png);
		background-repeat:no-repeat;
		height:12px;
		border:none;
		width:12px;
		cursor: pointer;
		
	}
</style>

<form:form name="input" id="formID" action="saveFinishedGoods"  modelAttribute="finishedGoodsMasterForm" >
<form:hidden path="finishedGoodsMasterDTO.finishedGoodsAutoId" id="finishedGoodsAutoId"/>
<form:hidden path="lastFinishedGoodDate" id="lastDate" />

<%--  <form:hidden path="finishedGoodsMasterDTO.checkShiftReport" id="checkShiftReport"/> --%>
<div class="page">
  <div class="panel-header"  >
	<div class="panel-title">Finished Goods Entry</div>
	<div class="panel-tool"></div>
  </div>
  
  <div align="left" class="bkgColor"  >
     <div class="headingdiv" style="background:none;"  >
 <table  height="24" width="833"   border="0"   >
    <tr>
    <td height="20">Branch<span style="color: #FF0000">*</span></td>
	<td colspan="5"><form:select path="finishedGoodsMasterDTO.branchDTO.branchId" onkeypress="return check(event)" items="${branchList}" itemLabel="branch"
		itemValue="branchId" style="width:99%" class="validate[required] text-input" id="branchId"></form:select></td>
	</tr>
	<tr>	
      <td width="233" height="24">Finish Good Series <span style="color: #FF0000">*</span></td>
      <td width="218">
        <form:input path="finishedGoodsMasterDTO.orderSeries"  class="validate[required] text-input" data-maxsize="15" readonly="true"   disabled="disabled" size="8" id="orderSeries" /><form:input path="finishedGoodsMasterDTO.finishGoodId"   class="validate[required] text-input"   data-maxsize="15" readonly="true" style="52%" disabled="disabled" size="11" id="quotationSeries" /></td>
      <td width="220">Finish Good Number</td>
      <td width="163"><form:input path="finishedGoodsMasterDTO.finishedGoodsNumber"  data-maxsize="15" readonly="true" disabled="disabled" style=" width: 105px;" size="11" id="qNumber" /></td>
      <td width="113">Date <span style="color: #FF0000">*</span></td>
      <td width="135"><form:input path="finishedGoodsMasterDTO.finishGoodDate" class="validate[required] text-input datepicker2" id="date" readonly="true" style="width:95%" size="11" />
     
      </td>
      
    </tr>
   <%--  <tr>
    <td width="56">Run No<span style="color: #FF0000">*</span></td>
							<td width="92"><form:input type="text"
									path="finishedGoodsMasterDTO.runNo"
									class="validate[required] text-input" onkeyup="valid(this)"
									onblur="valid(this)" id="runNo" style="width:70%" size="11" />
							</td>
							<td width="40">&nbsp;</td>
							<td width="56">Shift<span style="color: #FF0000">*</span></td>
							<td width="92"><form:select id="shiftId"
									class="validate[required] text-input"
									style="width:70% !important; height:20px;"
									path="finishedGoodsMasterDTO.mastersDTO.mastersId" items="${shift}"
									itemLabel="name" itemValue="mastersId"></form:select></td>
							<td width="85">&nbsp;</td>
    </tr>  --%>
    
  </table>
     <div class="gridheadingdiv" style="float:left">
  
<table width="668" class="fixmyheader" id="fixmyheader-8">
  <thead>
   <tr>
        <td width="25"><div align="center"><strong>S No.</strong></div></td>
        <td width="154"><div align="center">
        
        <strong><c:if test="${opr!='R'}">
        <c:if test="${token!='veiw'}">
        <input class="newWindow" style="font-size: 11px; font-weight: bold;  width: 13px;" type="submit" value=" "/>
        </c:if>
        </c:if>&nbsp; Item Name</strong>
        </div>
        </td>
        <td width="55"><div align="center"><strong>Batch No</strong></div></td>
        <td width="50"><div align="center"><strong>Mfg. Date</strong></div></td>
        <td width="50"><div align="center"><strong>Exp. Date</strong></div></td>
        
        <td width="52"><div align="center"><strong>UOM</strong></div></td>
        <td width="38"><div align="center"><strong>Quantity</strong></div></td>
        <td width="99"><div align="center"><strong>Store Location</strong></div></td>        
        <td width="101"><div align="center"><strong>Packing Detail</strong></div></td>
        <td width="31"><div align="center">
								<strong>Action</strong>
							</div>
						</td>
        
     </tr>
  </thead>
  <tbody> 
        <c:forEach items="${finishedGoodsMasterForm.finishedGoodsMasterDTO.finishedGoodsDetailDTOList}" var="e" varStatus="s">
    
      <tr>
        <td width="15">${s.count}</td>
        <td width="145">&nbsp;${e.itemName} </td>
        <td width="44">&nbsp;${e.batchNo} </td>
         <td width="42">&nbsp;<fmt:formatDate pattern="dd-MMM-yyyy" value="${e.mfgDate}" /> </td>
        <td width="42">&nbsp;<fmt:formatDate pattern="dd-MMM-yyyy" value="${e.expiryDate}" /> </td>
       
        <td width="42">&nbsp;${e.measurementUnitMasterDTO.name}</td>
        
        <td width="28">
        <form:input path="finishedGoodsMasterDTO.finishedGoodsDetailDTOList[${s.index}].quantity" style="text-align:right; width:100%; border:1px solid #7f9db9; " class="quantity qtyCls  validate[custom[number]]"  data-maxsize="10"   size="8" id="qty" /></td>
         <td width="89">
         <form:select
						path="finishedGoodsMasterDTO.finishedGoodsDetailDTOList[${s.index}].storeLocationId" items="${storeLocationList}"
						itemLabel="storeLocation" itemValue="storeLocationId" id="storeLocationId"
						class="validate[required]"
						style="width: 100%; height: 20px;">

		  </form:select>
		  </td>
        <td width="90"><form:input path="finishedGoodsMasterDTO.finishedGoodsDetailDTOList[${s.index}].packingDetails" style="width:100%; border:1px solid #7f9db9; " onkeypress="return check(event)"   data-maxsize="150"   size="8" id="qty" /></td>
         <td width="23"style="text-align: center;" >
         <c:if test="${opr!='V' }">
      <c:if test="${opr!='R' && token!='veiw'}">
      <c:url value="removeItemFromFG" var="remove_url">
					<c:param name="index" value="${s.index}"></c:param>
					<c:param name="opr" value="${opr}"></c:param>
					</c:url>
      
      <a href="${remove_url}"><img src="static/images/drop.png" 
								 title="Delete Record"
								alt="" /></a>
		</c:if></c:if>
		<c:if test="${opr=='V' }">
		<img src="static/images/drop.png" 
								 title="Delete Record"
								alt="" />
		</c:if>
								</td>
       </tr>
         </c:forEach>
  
  </tbody>
</table>
  </div>
  <table>
  <tr>
  <td width="30">
  Approved
  </td>
  <td>
  <td><form:checkbox onkeypress="return check(event)"
					path="finishedGoodsMasterDTO.approvalFlag" value="1" data-maxsize="15" id="aprovedId" 
					size="11"  />
  </td>
  </tr>
  </table>
 
    
	<div class="btn">
	
   <div class="savbtn">
   <c:if test="${opr=='R'}">
	<c:url value="remove_finishedGoods" var="remove_url">
					<c:param name="id" value="${finishedGoodsMasterForm.finishedGoodsMasterDTO.finishedGoodsAutoId}"></c:param>
					<c:param name="finishedGoodsNumber" value="${finishedGoodsMasterForm.finishedGoodsMasterDTO.finishedGoodsNumber}"></c:param>
		</c:url> <%-- <a href="${remove_url}" class="removebtn" ></a>  --%>
	</c:if>
  <c:if test="${opr=='E' && token!='veiw'}">
     <input class="updatebtn" type="submit" value=" "  />
     
     </c:if>

 <c:if test="${opr=='E' || token=='veiw'}">

     <div class="cancelbtn">
    <a href="get_finishedGoods_list"  ></a>   
   </div>
  
     </c:if>
  
    <c:if test="${opr=='N'}">
     <input class="submit" type="submit" value=" " onclick="return approvedCheck();" />
     <div class="cancelbtn">
	    <a href="get_finishedGoods_list"  ></a>   
   	</div>
     </c:if>
     
      <c:if test="${approvalFlag=='1'}">
		<input class="edit_btn"  type="button" onclick="return checkApproved();" value=""/>
	    <a href="get_finishedGoods_list"  class="cancelbtn" ></a>     		
	 </c:if>
     <c:if test="${opr=='V' && approvalFlag!='1'}">
		<input class="edit_btn"  type="button" onclick="editMethod();" value=""/>
	    <a href="get_finishedGoods_list"  class="cancelbtn" ></a>     		
	 </c:if>
   </div>
   
    <span style="margin-left:80px;" class="errmsg"></span> 
    </div>
  </div>
  </div>
</div>
</form:form>


  