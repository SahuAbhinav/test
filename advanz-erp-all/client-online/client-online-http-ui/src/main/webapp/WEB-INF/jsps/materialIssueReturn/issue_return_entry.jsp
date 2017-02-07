<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page isELIgnored="false"%>
<c:if test="${not empty(errorList)}"> 
 <input type="hidden" id="errorId" value="${errorList.errorMsg}">
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


<c:if test="${opr=='View' }">
	<script type="text/javascript">
		$(document).ready(function() {
		$('input').attr('readonly','readonly');
		$('select').attr('disabled','disabled');
		$('textarea').attr('readonly','readonly');
		$('.indentDate').attr('disabled','disabled');
		$('input:radio').attr('disabled',true);
		$("input:checkbox").attr("disabled", true);
		$(".newWindow").attr("disabled", true);
		$('.datepicker2').attr('disabled','disabled');
		$('#indentDate').attr('disabled','disabled');
		
		});
</script>
</c:if>

 <c:if test="${opr=='Remove'}">
 <script type="text/javascript">
		var redrctUrl='show_issue_return_list';
				
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
 		 var frank_param = getParam('issueId');
 		var issueReturnNumber = $('#issueReturnNumber').val();
 		 
		 var delUrl="remove_issue_return?issueId="+frank_param+"&issueReturnNumber="+issueReturnNumber;
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
 	 var frank_param = $('#issueId').val();
 	var issueReturnNumber = $('#issueReturnNumber').val();
 	 //	confirm('Are you sure you want to delete?');
	 var delUrl='get_issue_return?issueId='+frank_param+'&opration=E';
 	 window.self.location = delUrl;  
	}
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
					height		:240,
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


<script>
$(document).ready(function(){ 
$("input[readonly]").css("background-color","#ebebe4");
});
</script>
  <script>
	$(document).ready(function() {
		$(".cancelbtn").click(function() {
			window.self.location = "show_issue_return_list";
		});

	});
</script>

<c:if test="${opr=='Remove'}">
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

    function checkApproved(){
    	alert("You can not edit / delete this record as it is already approved.");
    	return false;
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
  
  
  
  function checkAmount() {
		
		/* if ($("#formulaBatchId").val() == undefined || $("#formulaBatchId").val() == 0) {
			
			alert("Pls Insert Data In Formula Batch Size.");
			return false;
		} */
	
		if ($("#snoid").val() == undefined) {
			alert("Sorry you can not save record without item.");
			return false;
		}
		
		for ( var ele = 0; true; ele++) {
			var frm = document.forms[0];
			
			var qt = frm.elements["issueReturnMasterDTO.issueReturnDetailDTOList[" + ele
					+ "].issueReturnQuantity"];
			if (!qt) {
				break;
			}
			
			if (qt.value == undefined || qt.value == 0) {
				alert('Recieve quantity can not be zero or null');
				return false;
			}
		}
		
		return true;
	}
  </script>
 
  
   <script type="text/javascript">
    
	$(document).ready(function(){ 
    //called when key is pressed in textbox
	$(".newWindow").click(function (e)  
	{ 
		document.forms["formID"].action="show_item_list_issue_return";
		 document.forms["formID"].submit();
	});
  });
  </script>
  
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
    	  
    	  $(".indentDate").datepicker({
    		  dateFormat : 'dd-M-yy ',
   		      changeMonth: true,
	          changeYear: true,
	          yearRange: '-99:+0',
	          maxDate: '+0M +0D'
    		});
    	  
    	   $(".datepicker2" ).datepicker({
    		   dateFormat : 'dd-M-yy ',
    		   changeMonth: true,
 	          changeYear: true,
 	          yearRange: '-99:+0',
 	          maxDate: '+0M +0D',
 	         minDate: new Date(l)
 	          });
    	 
    	   
      });
  </script>
     <script type="text/javascript">
	
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
	table {
	width:840px; !important}
	.gridheadingdiv td {
	height:22px;
	
	}
	.gridheadingdiv input {
    border: medium none;
    width: 70px;
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

   <form:form name="input" id="formID" action="save_issue_return" method="post" modelAttribute="issueReturnMasterForm">
	<form:hidden path="issueReturnMasterDTO.issueReturnAutoId" id="issueId"/>
   <form:hidden path="lastIssueReturnDate" id="lastDate" />
  <div class="panel-header"   >
	<div class="panel-title">Material Issue Return Entry</div>
	<div class="panel-tool"></div>
  </div>
  
  <div align="left" class="bkgColor"  >
      
 <table  height="120" width="967" class="tableview"   border="0"   style=" margin-top:12px;margin-bottom:5px;">
  
    <tr>
	<td width="100" >Branch Name <span style="color: #FF0000">*</span></td>
	<td colspan="3" ><form:select  path="issueReturnMasterDTO.branchDTO.branchId" style="width:145%; height:20px;" readonly="true" id="item_name" items="${branchList}" itemLabel="branch" itemValue="branchId"></form:select></td>
	</tr>
	
	<tr> <td width="100" height="24">Issue Return Series <span style="color: #FF0000">*</span></td>
      <td width="118">
        <form:input path="issueReturnMasterDTO.orderSeries"  class="validate[required] text-input" data-maxsize="15" readonly="true" style="width:40%"  disabled="disabled" size="8" id="orderSeries" />
        <form:input path="issueReturnMasterDTO.issueReturnId"   class="validate[required] text-input" data-maxsize="15" readonly="true" style="width:20%" disabled="disabled" size="11" id="quotationSeries" />
        <form:hidden path="issueReturnMasterDTO.issueReturnAutoId" id="issueReturnAutoId" />
        </td>
      <td width="100">Issue Retrun Number  <span style="color: #FF0000">*</span></td>
      <td width="113"><form:input path="issueReturnMasterDTO.issueReturnNumber"  data-maxsize="15" style="width:60%" readonly="true" disabled="disabled" size="11" id="issueReturnNumber" /></td>
      <td width="65" height="100%">Date<span style="color: #FF0000">*</span></td>
      <td width="135"><form:input path="issueReturnMasterDTO.issueReturnDate" class="datepicker2" id="date" readonly="true" style="width:65%" size="11" /></td>
    </tr>

    <tr>
	 <td width="100">Return By  </td>
      <td width="135"><form:input path="issueReturnMasterDTO.issuedReturnBy" data-maxsize="65" onkeypress="return check(event)"  id="issuedBy" readonly=""  style="width:64%; " size="11" /></td>
      
      <td width="100">Issue Time</td>
	  <td width="111"><form:input path="issueReturnMasterDTO.issueReturnTime" class="myTimePicker" readonly="true" style="width:60%"  size="12" id="issueTime" /></td>

	<td width="65" height="100%">Department </td>
	<td width="111"><form:select  path="issueReturnMasterDTO.mastersDTO.mastersId" style="width:67%; height:20px;"   id="item_name" items="${deptTypeList}" itemLabel="name" itemValue="mastersId"></form:select></td>
	
  </tr>
  </table>
  <div class="gridheadingdiv" style="float:left">
  <table width="668" class="fixmyheader" id="fixmyheader-8">
  <thead>
  <tr>
        
  <td width="34"><div align="center"><strong>S No.</strong></div></td>
  <td width="109"><div align="center">
        <strong><c:if test="${opr!='Remove'}">
        <input class="newWindow" style="font-size: 11px; font-weight: bold;  width: 13px;" type="submit" value=" "/></c:if>
        &nbsp; Item Name</strong></div></td>
        <td width="94"><div align="center"><strong>UOM</strong></div></td>
        <td width="98"><div align="center"><strong>Issue Qty.</strong></div></td>
        <td width="98"><div align="center"><strong>Return Qty.</strong></div></td>
        <td width="112"><div align="center"><strong>Store Location</strong></div></td>
        
        <td width="112"><div align="center"><strong>Ref. Issue Slip No</strong></div></td>
      <td width="98"><div align="center"><strong>Issue Date</strong></div></td>
       <td width="65"><div align="center"><strong>Action</strong></div></td>
  
  </tr>
  </thead>
   <%int i=1; %>
  <tbody>
       <c:forEach items="${issueReturnMasterForm.issueReturnMasterDTO.issueReturnDetailDTOList}" var="e" varStatus="s">
  
      <tr>
       <td width="19"><%= i%></td>
       <td width="86">&nbsp;${e.itemName} </td>
 	   <td width="72">&nbsp;${e.measurementUnitName}
 	   <input type="hidden" name="snoid" 	value="${s.count}" id="snoid">
 	   </td>
 	   <td width="77"><form:input path="issueReturnMasterDTO.issueReturnDetailDTOList[${s.index}].issueQuantity" readonly="true" style="text-align:right; border:1px solid #7f9db9; width:100%" class="quantity validate[custom[number]] qtyCls"  data-maxsize="15"   size="8" /></td>
 	  <td width="77"><form:input path="issueReturnMasterDTO.issueReturnDetailDTOList[${s.index}].issueReturnQuantity" style="text-align:right; border:1px solid #7f9db9; width:100%" class="quantity validate[custom[number]] qtyCls"  data-maxsize="15"   size="8" id="qty" /></td>
      <td width="88"><form:select
						path="issueReturnMasterDTO.issueReturnDetailDTOList[${s.index}].storeLocationId" items="${storeLocationList}"
						itemLabel="storeLocation" itemValue="storeLocationId" id="storeLocationId"
						class="validate[required]"
						style="width: 100%; height: 20px;">
		  </form:select></td>
		  
		   <td width="88"><form:input path="issueReturnMasterDTO.issueReturnDetailDTOList[${s.index}].issueNumber" style="text-align:left; border:1px solid #7f9db9; width:100%" readonly="true"  data-maxsize="15"   size="8" id="" /> </td>
		    <td width="77"><form:input path="issueReturnMasterDTO.issueReturnDetailDTOList[${s.index}].issueDate" readonly="true" style="text-align:left; border:1px solid #7f9db9; width:100%" data-maxsize="15"   size="8"  /></td>
   <td width="48" style="text-align: center;" >
      <c:if test="${opr!='Remove'}">
      <c:url value="remove_item_from_issues_return" var="remove_url">
			<c:param name="index" value="${s.index}"></c:param>
			<c:param name="opr" value="${opr}"></c:param>
	</c:url>
      <c:if test="${opr!='View' }">
        <a href="${remove_url}"><img src="static/images/drop.png"	 title="Delete Record"	alt="" /></a></c:if>
		<c:if test="${opr=='View' }"><img src="static/images/drop.png" title="Delete Record" alt="" /></c:if>
		</c:if>
		</td>
	 <%i++ ;%>
       </tr>
       
 </c:forEach>
 </tbody>
 </table>
 </div>
 <table>
 <tr>
 <td width="65"><div align="center"><strong>Item Count</strong></div></td>
	<td> <form:input path="" value="<%=i-1 %>" readonly="true"  id ="" style="width:87%" size="11"/></td>
	<td width="33"><div align="center"><strong>Remark</strong></div></td>
	<td > <form:textarea rows="2" cols="101" path="issueReturnMasterDTO.issueReturnRemark" onkeypress="return check(event)" id ="issueRemark" style="width:98%" size="11"/></td>
	
	</tr>
	</table>
	
	
	<div class="btn" style="width: 300px;" >
	
   <div class="savbtn">
   
 	 <c:if test="${opr!='View' && opr!='R' && issueReturnMasterForm.issueReturnMasterDTO.issueReturnAutoId!=null }">
   		<input class="updatebtn" type="submit" value=" " />
   		<div class="cancelbtn">
	   <a href="show_issue_return_list"  ></a>   
   		</div>
  	</c:if>
   

	 <c:if test="${opr=='View'}">
	 		<c:forEach items="${roleAndRightsDTOList}" var="roleAndRights">
		   <c:if test="${roleAndRights.menuId==sessionScope.menuId}">
    	  	 <c:if test="${roleAndRights.editFlag=='true'}">
		  	 <input class="edit_btn"  type="button" onclick="editMethod();" value=""/>
    	      <a href="show_issue_return_list"  class="cancelbtn" ></a>  
		     </c:if>
		      <c:if test="${roleAndRights.editFlag=='false'}">
		      <input class="edit_btn"  type="button" onclick="checkEdit();" value=""/>
    	      <a href="show_issue_return_list"  class="cancelbtn" ></a>  
		      </c:if>
	      </c:if>
          </c:forEach>
	 </c:if>
		   	
     
    <c:if test="${opr!='View' && issueReturnMasterForm.issueReturnMasterDTO.issueReturnAutoId==null}">
     	<input class="submit" type="submit" value=" " onclick="return checkAmount();"/>
       
     <div class="cancelbtn">
   	 <a href="show_issue_return_list"  ></a>   
  	 </div>
     </c:if> 
  	 </div>

     <div style="width:906px; margin:0 auto;"> <span style="margin-left:80px;" class="errmsg"></span> </div>
     
    </div>
  </div>
 

</form:form>


  