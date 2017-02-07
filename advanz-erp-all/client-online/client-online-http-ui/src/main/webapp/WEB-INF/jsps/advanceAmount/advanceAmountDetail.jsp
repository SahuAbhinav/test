<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script type="text/javascript" charset="utf-8">
$(document).ready(function(){ 
 $(".myTimePicker[readonly]").css("background-color","#ffffff" );
 $(".datepicker2[readonly]").css("background-color","#ffffff" );
} );
</script>
 <c:if test="${not empty(errorList)}">
 <input type="hidden" id="errorId" value="${errorList.errorMsg}">
 <script type="text/javascript">
 		$(document).ready(function() {
 		var errorId=document.getElementById('errorId');
		alert(errorId.value);
		});
 	</script>
 </c:if>

 	<c:if test="${opr=='R'}">
 	<script type="text/javascript">
		var redrctUrl='advanceAmountList';
				
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
 		 var frank_param = getParam('sno');
 		 //	confirm('Are you sure you want to delete?');
		 var delUrl='delete_advance?sno='+frank_param;
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
	//	$(document).ready(function() {
 	function editMethod()
 	 { 
 	 var frank_param = $('#sno').val();
 	 //	confirm('Are you sure you want to delete?');
	 var delUrl='findBy_Id?sno='+frank_param+'&opr=E';
 	 window.self.location = delUrl;  
	}
 	</script>	
 <c:if test="${opr=='V' }">
<script>
$(document).ready(function() {
	$('input').attr('readonly','readonly');	
	$('select').attr('disabled','disabled');
	$('textarea').attr('readonly','readonly');
	$('.datepicker2').attr('disabled','disabled');
	$('.myTimePicker').attr('disabled','disabled');
	$('input:radio').attr('disabled',true);
	 $("input:checkbox").attr("disabled", true);
	 
	 
	 
});
</script>
</c:if> 	 
 	
 <script type="text/javascript">
 	
 function checkEdit()
	{
	alert('Login User Not Permit to Edit Record !!!!!!');
	}
 	
      $(document).ready(function()
       {
    	  
         $(".datepicker" ).datepicker({dateFormat : 'dd-M-yy',maxDate: +0});
      });
  </script>

<%@ page isELIgnored="false"%>

<script>
	$(document).ready(function() {
		$(".cancelbtn").click(function() {
			window.self.location = "advanceAmountList";
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
    
    function employee()
 	 {
    	console.log($('#employeeID option:selected').val());
    	$("#employeeName").val($('#employeeID option:selected').text());
    	 var postData = $(this).serializeArray()
    	   $.ajax({
  			url : 'find_by_empId?employeeId='+$("#employeeID").val(),
  			type: "POST",
  			data : postData,
  			success:function(data, textStatus, jqXHR) 
  			 {
  			 if(data.status=='SUCCESS')
  		   		{
  			   $("#employeeDesignation").val(data.result.employeeDTO.mastersDTO.name); 
  			   $("#monthlySalary").val(data.result.employeeDTO.totalAmount);
  			   $("#employeeCode").val(data.result.employeeCode);
  			   $("#openingBalance").val(data.result.closingBalance);
  			  // $("#closingBalance").val(data.result.closingBalance);
  			  
  		   	  }
  			 else{
  				$.ajax({
  				url : 'employee_detail?employeeId='+$("#employeeID").val(),
  					type: "POST",
  					data : postData,
  					success:function(data, textStatus, jqXHR) 
  					 {
  					  $("#employeeDesignation").val(data.mastersDTO.name); 
  					  $("#monthlySalary").val(data.totalAmount);
  					  $("#employeeCode").val(data.employeeCode); 
  					  $("#openingBalance").val(0);
  	  			   	  $("#closingBalance").val(0);
  					 },
  				   }); 
  			 }},
  		   });
      	}

    
$(document).ready(function(){ 
	
	$('#amountId').change(function()
	  {
	    var opBal=  parseFloat($("#openingBalance").val());
		var clBal=0.0;
		var amt= parseFloat($('#amountId').val());
	    console.log($('#transactionType').val());
	    if($('#transactionType').val()=='Given')
	     {
		   clBal=opBal+amt;		 
	     }
	    else if($('#transactionType').val()=='Deduction')
	     {
		  if(opBal!=0){
	    	clBal=opBal-amt;
		   }
		  else{
			   clBal=amt;
			   }
	     }
	    $("#closingBalance").val(clBal);
	   });
		$('#transactionType').change(function()
		  {
		    
			var opBal= parseFloat($("#openingBalance").val());
			var clBal=0.0;
			var amt= parseFloat($('#amountId').val());
			if(isNaN(amt))
			 {amt=0;}
		    console.log(amt);
		    if($('#transactionType').val()=='Given')
		     {
		    	if(opBal!=0){
		    		  clBal=opBal+amt;
			    	if(amt==0)
			    	  {clBal=0;}
				   }else{
				   clBal=amt;
				   }
		     }
		    else if($('#transactionType').val()=='Deduction')
		     {
			  if(opBal!=0){
		    	clBal=opBal-amt;
		    	if(amt==0)
		    	  {clBal=0;}
			   }else{
			   clBal=amt;
			   }
		     }
		    else
		     {
		       clBal=0;
		     }
		    $("#closingBalance").val(clBal);
			   });
	
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
  </script>
 

 <script type="text/javascript">
      $(document).ready(function()
       {
    	   $(".datepicker2" ).datepicker({dateFormat : 'dd-M-yy',changeMonth: true,
 	          changeYear: true, yearRange: '-99:+0', maxDate: '+0M +0D'});
      });
  </script>

<style>
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

.gridheadingdiv td {
	height: 22px;
}

.gridheadingdiv input {
	border: medium none;
	width: 70px;
}

.newWindow {
	background: url(static/images/search_small.png);
	background-repeat: no-repeat;
	height: 12px;
	border: none;
	width: 12px;
}
</style>

<c:if test="${opr=='R'}">
	<div class="errorblock">
		<ul>Remove Confirmation
		</ul>
	</div>
</c:if>
<%-- <c:if test="${not empty(errorList)}">
	<div class="errorblock">
		<ul>
			<c:forEach items="${errorList.errorList}" var="err">
				<li>${err.errorMsg}</li>
			</c:forEach>
		</ul>
	</div>
</c:if> --%>


<form:form name="input" id="formID" action="advance_save" modelAttribute="advanceAmountForm" method="post">
	<form:hidden path="advanceAmountDTO.sno" id="sno" />

	<div class="panel-header"  >
		<div class="panel-title">Advance Amount</div>
		<div class="panel-tool"></div>
	</div>

	<div align="left" class="bkgColor" >

		<table height="200" width="746" border="0" 	style="margin-left: 50px; width: 800px; margin-top: 20px;">
			<tr>
			<td width="120">Transaction Id</td>
				<td><form:input path="advanceAmountDTO.sno" data-maxsize="11" size="20"/></td>
			<td>Transaction Date</td>
				<td><form:input readonly="true" path="advanceAmountDTO.transactionDate" id="transactionDateID" class="validate[required] text-input datepicker2" size="20" />
				</td>
			</tr>
		
			<tr>
			<td>Employee Name</td>
				<td>
				<form:select path="advanceAmountDTO.employeeDTO.employeeId" class="validate[required]" style="width:180px; height:22px" onchange="employee()" id="employeeID"  >
				<form:option value="" selected="selected">Select</form:option>
				<form:options items="${employeeList}" itemLabel="employeeName" itemValue="employeeId" />
				</form:select>
				<%-- <form:select path="advanceAmountDTO.employeeDTO.employeeId" items="${employeeList}" itemLabel="employeeFullName"
				style="width:180px; height:22px" itemValue="employeeId" onchange="employee()" id="employeeID" class="validate[required] text-input "></form:select> --%>
				<form:hidden path="advanceAmountDTO.employeeName" id="employeeName"/>
				</td>
			<td>Employee Code</td>
				<td ><form:input path="advanceAmountDTO.employeeCode" class="validate[required] text-input" id="employeeCode"  data-maxsize="11" size="20"/></td>
			</tr>
		
			<tr>
			  <td>Designation</td>
				<td><form:input path="advanceAmountDTO.employeeDesignation"   id="employeeDesignation" data-maxsize="11" size="20"/></td>			
			  <td>Monthly Salary</td>
				<td ><form:input path="advanceAmountDTO.monthlySalary" id="monthlySalary" data-maxsize="11" size="20"/></td>
			</tr>
			
			<tr>
			  <td>Opening Balance</td>
				<td><form:input path="advanceAmountDTO.openingBalance" class="validate[custom[number]]" readonly="true"  id="openingBalance" data-maxsize="11" size="20"/></td>		
			  <td>Transaction Type</td>
				<td >
				<form:select style="width:180px; height:22px" path="advanceAmountDTO.transactionType" id="transactionType" class="validate[required]" >
					<form:option value="">Select</form:option>
					<form:option value="Deduction">Deduction</form:option>
					<form:option value="Given">Given</form:option>										
				</form:select></td>
			</tr>
		
			<tr>
	         <td>Closing Balance</td>
				<td><form:input path="advanceAmountDTO.closingBalance" class="validate[custom[number]]" readonly="true" id="closingBalance"  data-maxsize="11" size="20"/></td>
			<td>Amount</td>
			  <td ><form:input path="advanceAmountDTO.amount" class="validate[custom[number]]"  id="amountId" data-maxsize="11" size="20"/></td>
			</tr>
			
			<tr>
				<td style="vertical-align: top;" width="68" height="42">Remark</td>
				<td colspan="8"><form:textarea  rows="3" cols="90" style="padding-left: 6px; width: 50%;"
						path="advanceAmountDTO.remark" onkeypress="return check(event)" id="remark"
						tabindex='7' name="remark" size="25" data-maxsize="500"  />
				</td>
			</tr>
		</table>




		<div class="btn">
			<div class="savbtn">

				<c:choose>
					<c:when test="${opr=='R'}">
						<c:url value="remove_melter_log_summary" var="remove_url">
							<c:param name="sno"
								value="${advanceAmountForm.advanceAmountDTO.sno}"></c:param>
						</c:url>
						<%-- <a href="${remove_url}" class="removebtn"></a> --%>
					</c:when>
					
					<c:when test="${opr=='V'}">
					<c:forEach items="${roleAndRightsDTOList}" var="roleAndRights">
		   <c:if test="${roleAndRights.menuId==sessionScope.menuId}">
    	  	 <c:if test="${roleAndRights.editFlag=='true'}">
		  	 <input class="edit_btn"  type="button" onclick="editMethod();" value=""/>
    	      <a href="advanceAmountList"  class="cancelbtn" ></a>  
		     </c:if>
		      <c:if test="${roleAndRights.editFlag=='false'}">
		      <input class="edit_btn"  type="button" onclick="checkEdit();" value=""/>
    	      <a href="advanceAmountList"  class="cancelbtn" ></a> 
		      </c:if>
		    
          </c:if>
          </c:forEach>
	 	
	 </c:when>
					
					<c:otherwise>
						<c:choose>
							<c:when test="${opr=='E'}">
							 <input type="hidden" name="succ" value="update">
								<input class="updatebtn"
									style="font-size: 11px; font-weight: bold; padding: 0 0 0 30px;"
									type="submit" value=" " />
										    <a href="advanceAmountList"  class="cancelbtn" ></a>  
							</c:when>
							<c:otherwise>
								<input class="submit"
									style="font-size: 11px; font-weight: bold; padding: 0 0 0 30px;"
									type="submit" value=" " />
									 	    <a href="advanceAmountList"  class="cancelbtn" ></a>  
							</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose>
			</div>
			
			<div style="width: 906px; margin: 0 auto;">
				<span style="margin-left: 80px;" class="errmsg"></span>
			</div>

		</div>
	</div>

</form:form>
