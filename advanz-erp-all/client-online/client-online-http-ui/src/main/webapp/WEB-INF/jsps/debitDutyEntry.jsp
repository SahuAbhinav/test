<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page isELIgnored="false"%>
	
	<script type="text/javascript">
	function checkEdit()
		{
		alert('Login User Not Permit to Edit Record !!!!!!');
		}
	function approve(){
		alert('This debit number has been approved, you can not be edit');
		return false;
	}
	
 	  function editMethod()
 	  { 
 	 	var debitDutyAutoId = $('#debitDutyAutoId').val();
 	 	var debitDutyNumber = $('#orderSeries').val();
	 	var delUrl='show_debit_duty?debitDutyAutoId='+debitDutyAutoId+'&debitDutyNumber='+debitDutyNumber+'&operation=Edite';
 	 	window.self.location = delUrl;  
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
<title> Entry</title>
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
	$(document).ready(function() {
				//called when key is pressed in textbox
				$(".quantity").keypress(function(e) {
							//if the letter is not digit then display error and don't type anything
							if (e.which != 8 && e.which != 46 && e.which != 0 && (e.which<48 || e.which>57)) {
								//display error message
								$(".errmsg").html("Digits Only").show().fadeOut("slow");
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

<c:if test="${operation=='Edite' }">
	<script type="text/javascript">
		$(document).ready(function() {
		$('.datepicker2').attr('disabled','disabled');
		});
	</script>
	</c:if>
	
	
<script type="text/javascript">
      $(document).ready(function()
       {
    	  
    	  $("#formID").submit(function() {
  		    $('.datepicker2').removeAttr('disabled');
  		});
    	  
    	  
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
			var redrctUrl='show_debit_duty_list?operation=show';
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
	 		 var dispatchAutoId = getParam('debitDutyAutoId');
	 		 var dispatchNumber = getParam('debitDutyNumber');
	 		// alert(frank);
	 		 //	confirm('Are you sure you want to delete?');
			 var delUrl='submit_debit_duty_List?debitDutyAutoId='+dispatchAutoId+'&debitDutyNumber='+dispatchNumber+'&operation=Delete';
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
	
	
<form:form commandName="debitDuty" action="save_debit_duty" method="POST"
		id="formID">
		<div class="panel-header"  >
			<div class="panel-title">Debit Duty Entry</div>
			<div class="panel-tool"></div>
		</div>

		<div align="left" class="bkgColor"
			style="height: auto; padding-bottom: 14px;">
			<table height="219" class="tableview"  width="840" border="0" style="margin-top: 12px;">
				<tr>
					<td height="20">Branch Name <span style="color: #FF0000">*</span></td>
					<td colspan="5"><form:select path="debitDutyMasterDTO.branchDTO.branchId" class="validate[required] text-input"
							style="width:100%" id="branchId">
							<form:options items="${branchList}" itemValue="branchId"
								itemLabel="branch" />
						</form:select></td>
				</tr>

				<tr>
					<td width="120" height="20">Debit Duty Series<span
						style="color: #FF0000">*</span></td>
					<td width="158"><form:input
							path="debitDutyMasterDTO.transactionSeries"
							 style="width:44%" data-maxsize="15"
							readonly="true"   size="8" /> <form:input
							path="debitDutyMasterDTO.debitDutyId"
							class="validate[required] text-input" data-maxsize="15"
							readonly="true"  style="width:35%" disabled="disabled" size="8" /></td>
					<td width="151">
					Debit Duty Number<span style="color: #FF0000">*</span></td>
					<td width="131">
					<form:input
							path="debitDutyMasterDTO.debitDutyNumber"
							 style="width:98%" data-maxsize="15"
							readonly="true" disabled="disabled" size="11" id="orderSeries" />
						<form:hidden path="debitDutyMasterDTO.debitDutyAutoId" id="debitDutyAutoId" />
						<form:hidden path="lastDebitDate" id="lastDate" /></td>
					<td width="75">Date<span style="color: #FF0000">*</span></td>
					<td width="104"><form:input style="width:96%" readonly="true" class="validate[required] text-input datepicker2"
							path="debitDutyMasterDTO.debitDutyDate"    size="21" /></td>
					<td colspan="2">&nbsp;</td>
				</tr>
				
			
			
			<tr>
					<td width="120" height="20">Basic Excise</td>
					<td width="158"><form:input
							path="debitDutyMasterDTO.exciseAmount"
							 style="width:44%" data-maxsize="15"
							  size="8" /></td>
					<td width="151">
					Cess Amount</td>
					<td width="131">
					<form:input
							path="debitDutyMasterDTO.eduCessAmount"
							class="quantity validate[custom[number]]" style="width:98%" data-maxsize="15"
							 disabled="disabled" size="11" id="orderSeries" />
						</td>
					<td width="75">H.Cess Amount</td>
					<td width="104"><form:input style="width:96%" class="quantity validate[custom[number]]"
							path="debitDutyMasterDTO.hEduCessAmount"    size="21" /></td>
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td height="20" valign="top">Description</td>
					<td colspan="3"><form:textarea cols="" rows="4" path="debitDutyMasterDTO.narration"
							 onkeypress="return check(event)" style="width:99%" data-maxsize="25" size="21" /></td>
	<td  width="113">Approved</td>
	<td width="135"><form:checkbox path="debitDutyMasterDTO.approvedFlag" value="1" style="width:1%" size="11" id="aprovedId" /></td>
				</tr>
			</table>
			<div style="width: 906px; margin: 0 auto;">
				<span style="margin-left: 80px;" class="errmsg"></span>
			</div>
			
	<c:if test="${error.operationName=='Delete'}">
	<c:url value="submit_debit_duty_List" var="remove_url">
					<c:param name="debitDutyAutoId" value="${debitDuty.debitDutyMasterDTO.debitDutyAutoId}"></c:param>
					<c:param name="debitDutyNumber" value="${debitDuty.debitDutyMasterDTO.debitDutyNumber}"></c:param>
					<c:param name="operation" value="Delete"></c:param>
		</c:url> <%-- <a href="${remove_url}" class="removebtn" onclick="return remoneConformation();"></a>  --%>
	<c:url value="show_debit_duty_list" var="remove_url">
					<c:param name="operation" value=""></c:param>
		</c:url> <%-- <a href="${remove_url}" class="cancelbtn" onclick="this.value='Cancel';"></a>  --%>
</c:if>
		<c:if test="${error.operationName!='Delete'}">	
			<div class="btn">
				<div class="savbtn">

		<c:if test="${debitDuty.debitDutyMasterDTO.debitDutyAutoId !=null && operation=='Edite'}">
			<input id="myButton" class="updatebtn" type="submit" value=""
				name="operation" value="" onclick="this.value='Save',$('#formID').validationEngine();" />
							<div class="cancelbtn">
			     	<input type="submit" value="" name="operation" class="cancelbtn" onclick="this.value='Cancel';" />
				</div>
					</c:if>


	<c:if test="${debitDuty.debitDutyMasterDTO.debitDutyAutoId !=null && error.operationName=='V' && approvedFlag!='1'}">
		
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
	
	<c:if test="${approvedFlag=='1'}">
		<input class="edit_btn"  type="button" onclick="return approve();" value=""/>
			<div class="cancelbtn">
     	<input type="submit" value=""  name="operation" class="cancelbtn" onclick="this.value='Cancel';" />
			</div>
	</c:if>
	
<c:if test="${debitDuty.debitDutyMasterDTO.debitDutyAutoId ==null}">
						<input id="myButton" class="submit" type="submit" value=""
							name="operation" value="" onclick="this.value='Save',$('#formID').validationEngine();" />
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