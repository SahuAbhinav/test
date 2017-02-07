<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page isELIgnored="false"%>
<c:if test="${not empty(errors)}"> 
 <input type="hidden" id="errorId" value="${errors.errorMsg}">
 <script type="text/javascript">
 
 		var delUrl='new_indent';
 		$(document).ready(function() {
 		var errorId=document.getElementById('errorId');
		alert(errorId.value);
 		//alert('Duplicate Data In Branch');
 		//window.self.location  = delUrl;
		});
 	</script>
 </c:if>
<c:if test="${msg!=null && msg=='notRemove'}">
	<script type="text/javascript">
$(document).ready(function() {
alert("Sorry you cann't remove this item as it is used in purchase order!!!!");
});
</script>
</c:if>

 <c:if test="${opr=='R'}">
 <script type="text/javascript">
		var redrctUrl='get_indent_list';
				
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
 		 var frank_param = getParam('indentAutoId');
 		 //	confirm('Are you sure you want to delete?');
		 var delUrl='remove_indent?id='+frank_param;
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
 	
 	
<c:if test="${opr=='V' }">
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

	<script type="text/javascript">
	function checkEdit()
		{
		alert('Login User Not Permit to Edit Record !!!!!!');
		}
	function editMethod()
 	 { 
 	 var frank_param = $('#indentAutoId').val();
 	 //	confirm('Are you sure you want to delete?');
	 var delUrl='get_indent?indentAutoId='+frank_param+'&opr=E';
 	 window.self.location = delUrl;  
	}
	 function alertFunction(){
	      	alert("After select fulfill option you cann't use this item in proforma");
	      }
	      
 	</script>

 	
<script type="text/javascript">

/* function approvedCheck(){
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
			window.self.location = "get_indent_list";
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
   
  </script>
 
  
   <script type="text/javascript">


    
$(document).ready(function(){ 
    //called when key is pressed in textbox
	$(".newWindow").click(function (e)  
	{ 
		
		document.forms["formID"].action="show_item_list_indent";
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

   <form:form name="input" id="formID" action="saveIndent"  modelAttribute="indentMasterForm">
<form:hidden path="indentMasterDTO.indentAutoId"/>
<form:hidden path="lastIndentDate" id="lastDate" />
  
  <div class="panel-header"   >
	<div class="panel-title">Indent Entry</div>
	<div class="panel-tool"></div>
  </div>
  
  <div align="left" class="bkgColor"  >
      
 <table  height="128" width="967" class="tableview"   border="0"   style=" margin-top:12px;">

    <tr>
	<td width="233" >Branch Name <span style="color: #FF0000">*</span></td>
	<td colspan="3" ><form:select  path="indentMasterDTO.branchDTO.branchId" style="width:96%; height:20px;" readonly="true" id="item_name" items="${branchList}" itemLabel="branch" itemValue="branchId"></form:select></td>
	
	
	</tr><tr> <td width="233" height="24">Indent Series <span style="color: #FF0000">*</span></td>
      <td width="218">
        <form:input path="indentMasterDTO.orderSeries"  class="validate[required] text-input" data-maxsize="15" readonly="true" style="width:53%"  disabled="disabled" size="8" id="orderSeries" />
        <form:input path="indentMasterDTO.indentId"   class="validate[required] text-input" data-maxsize="15" readonly="true" style="width:30%" disabled="disabled" size="11" id="quotationSeries" />
        <form:hidden path="indentMasterDTO.indentAutoId" id="indentAutoId" />
        </td>
      <td width="220">Indent Number  <span style="color: #FF0000">*</span></td>
      <td width="163"><form:input path="indentMasterDTO.indentNumber"  data-maxsize="15" style="width:82%" readonly="true" disabled="disabled" size="11" id="qNumber" /></td>
      <td width="113">Date  <span style="color: #FF0000">*</span></td>
      <td width="135"><form:input path="indentMasterDTO.indentDate" class="datepicker2" id="date" readonly="true" style="width:95%" size="11" /></td>
    </tr>
	<tr>
    <td width="113">Raised  By  </td>
    <td width="135"><form:input path="indentMasterDTO.raisedBy" data-maxsize="65" onkeypress="return check(event)"	 readonly="" id="raisedBy"  style="width:94%" size="11" /></td>
	</tr>
	
  </table>
  <div class="gridheadingdiv" style="float:left">
  <table width="668" class="fixmyheader" id="fixmyheader-8">
  <thead>
  <tr>
        
  <td width="34"><div align="center"><strong>S No.</strong></div></td>
  <td width="109"><div align="center">
        <strong><c:if test="${opr!='R'}"><input class="newWindow" style="font-size: 11px; font-weight: bold;  width: 13px;" type="submit" value=" "/></c:if>&nbsp; Item Name</strong>
        </div></td>
       <td width="65"><div align="center">
		<strong>UMO</strong>
		</div>
		</td>
		<td width="65"><div align="center">
		<strong>Indent Qty</strong>
		</div>
		</td>
		<td width="65"><div align="center">
		<strong>Department</strong>
		</div>
		</td>
		<td width="65"><div align="center">
		<strong>Item Make</strong>
		</div>
		<td width="65"><div align="center">
		<strong>Indent Remark</strong>
		</div>
	
		</td>
		<td width="50"><div align="center"><strong>Order Fulfill</strong></div></td>
       <td width="65"><div align="center">
		<strong>Action</strong>
		</div>
		</td>
  
 
  </tr>
  </thead>
   <%int i=1; %>
  <tbody>
       <c:forEach items="${indentMasterForm.indentMasterDTO.indentDetailDTO}" var="e" varStatus="s">
  
      <tr>
        <td width="24"><%= i%></td>
        <td width="102">&nbsp;${e.itemName} </td>
		  <td width="56">${e.measurementUnitId.name} </td>
		  <td width="56"><form:input path="indentMasterDTO.indentDetailDTO[${s.index}].indentQty" style="text-align:right; border:1px solid #7f9db9; width:100%" class="quantity validate[custom[number]] qtyCls"  data-maxsize="15"   size="8" id="qty" />
           </td>
          <td width="56"><form:select
						path="indentMasterDTO.indentDetailDTO[${s.index}].departmentId" items="${deptTypeList}"
						itemLabel="name" itemValue="mastersId" id="mastersId"
						class="validate[required]"
						style="width: 100%; height: 20px;">
		  </form:select></td>
          
          <td width="56"><form:input path="indentMasterDTO.indentDetailDTO[${s.index}].itemMake" style="border:1px solid #7f9db9; width:100%"   data-maxsize="50"   size="8" id="qty" />
           </td> 
          <td width="56"><form:input path="indentMasterDTO.indentDetailDTO[${s.index}].indentRemark" style="border:1px solid #7f9db9; width:100%"   data-maxsize="50"   size="8" id="qty" />
           </td> 
           <td width="42"><form:checkbox path="indentMasterDTO.indentDetailDTO[${s.index}].fullFill" value="0"  data-maxsize="150" onclick="alertFunction();" onkeypress="return check(event)" style=" border:1px solid #7f9db9; height: 17px; width:100%" size="8"/></td>
      <td width="56" style="text-align: center;" >
      <c:if test="${opr!='R'}">
      <c:url value="removeItemFromIndent" var="remove_url">
					<c:param name="index" value="${s.index}"></c:param>
					<c:param name="opr" value="${opr}"></c:param>
					</c:url>
      <c:if test="${opr!='V' }">
      <a href="${remove_url}"><img src="static/images/drop.png" 
								 title="Delete Record"
								alt="" /></a>
								</c:if><c:if test="${opr=='V' }">
								<img src="static/images/drop.png" 
								 title="Delete Record"
								alt="" />
								</c:if>
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
	<td> <form:input path="" value="<%=i-1 %>" readonly="true"  id ="issueRemark" style="width:87%" size="11"/></td>
	<td width="33"   ><div align="center"><strong>Remark</strong></div></td>
	<td > <form:textarea rows="2" cols="101" path="indentMasterDTO.indentRemark" onkeypress="return check(event)" id ="issueRemark" style="width:98%" size="11"/></td>
	 
	</tr>
	</table>
	
    
 
    
	<div class="btn" style="width: 300px;" >
	
   <div class="savbtn">
   <c:if test="${opr=='R'}">
   <c:url value="remove_indent" var="remove_url">
   <c:param name="id" value="${indentMasterForm.indentMasterDTO.indentAutoId}"></c:param>
   </c:url><%--  <a href="${remove_url}" class="removebtn" ></a>  --%>
   </c:if>
   
   <c:if test="${indentMasterForm.indentMasterDTO.indentAutoId<0 || indentMasterForm.indentMasterDTO.indentAutoId==null|| indentMasterForm.indentMasterDTO.indentAutoId==0}">
   <input class="submit" type="submit" value=" "/>
   <div class="cancelbtn">
   <a href="get_indent_list"  ></a>   
   </div>
   </c:if>
   
   <c:if test="${indentMasterForm.indentMasterDTO.indentAutoId>0}">
   <c:if test="${opr=='E'}">
   <input class="updatebtn" type="submit" value=" " />
   <div class="cancelbtn">
   <a href="get_indent_list"  ></a>   
   </div>
   </c:if>
    <c:if test="${opr=='V'}">
	<c:forEach items="${roleAndRightsDTOList}" var="roleAndRights">
		   <c:if test="${roleAndRights.menuId==sessionScope.menuId}">
    	  	 <c:if test="${roleAndRights.editFlag=='true'}">
		  	 <input class="edit_btn"  type="button" onclick="editMethod();" value=""/>
    	      <a href="get_indent_list"  class="cancelbtn" ></a>  
		     </c:if>
		      <c:if test="${roleAndRights.editFlag=='false'}">
		      <input class="edit_btn"  type="button" onclick="checkEdit();" value=""/>
    	      <a href="get_indent_list"  class="cancelbtn" ></a>  
		      </c:if>
		    
          </c:if>
          </c:forEach></c:if>	</c:if>
   </div>
     <div style="width:906px; margin:0 auto;"> <span style="margin-left:80px;" class="errmsg"></span> </div>
    </div>
  </div>
</form:form>  