<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %>

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
		var redrctUrl='show_branch_form';
				
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
 		 var frank_param = getParam('branchId');
 		 //	confirm('Are you sure you want to delete?');
		 var delUrl='delete_branch?branchId='+frank_param;
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
	//	$(document).ready(function() {
 	function editMethod()
 	 { 
 	 var frank_param = $('#branchId').val();
 	 //	confirm('Are you sure you want to delete?');
	 var delUrl='get_branch?branchId='+frank_param+'&opr=E';
 	 window.self.location = delUrl;  
	}
 	</script>	
 <c:if test="${opr=='V' }">
<script>
$(document).ready(function() {
	$('input').attr('readonly','readonly');	
	$('select').attr('disabled','disabled');
	$('textarea').attr('readonly','readonly');
	$('.datepicker').attr('disabled','disabled');
	$('#radiobutton input:radio').attr('disabled',true);
	 $("input.checkbox").attr("disabled", true);
});
</script>
</c:if>	





 <script type="text/javascript">    
$(document).ready(function(){ 
 function fixNumFormat(){
	$(".quantity").each(function(){
		
		var v=parseFloat($(this).val());
		
		v=v.toFixed(2);
		if(v=='NaN')
			v='0.00';
		$(this).val(v);
	});
 }
 fixNumFormat();
    $(".quantity").change(function (e)  
    		{ 
    	 fixNumFormat();
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
	$("#quantity").keypress(function (e)  
	{ 
	  //if the letter is not digit then display error and don't type anything
	  if( e.which!=8 && e.which!=0 && (e.which<48 || e.which>57))
	  {
		//display error message
		$("#errmsg").html("Digits Only").show().fadeOut("slow"); 
	    return false;
      }	
	});
	$("#creditDays").keypress(function (e)  
			{ 
			  //if the letter is not digit then display error and don't type anything
			  if( e.which!=8 && e.which!=0 && (e.which<48 || e.which>57))
			  {
				//display error message
				$("#errmsg").html("Digits Only").show().fadeOut("slow"); 
			    return false;
		      }	
			});
	$(".quantity2").keypress(function (e)  
			{ 
			  //if the letter is not digit then display error and don't type anything
			  if( e.which!=8 && e.which!=0 && (e.which<48 || e.which>57))
			  {
				//display error message
				$("#errmsg").html("Digits Only").show().fadeOut("slow"); 
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
	  $('.datepicker').attr('readonly','true');	
      });
  </script>

  <style>
	input{
	width:81%;
	}
	</style>
       
<c:if test="${opr=='E' || opr=='V'}">
<form:form id="formID" name="formID" action="update_branch" method="post" modelAttribute="branchForm">  
   
  <div class="panel-header">
	<div class="panel-title">Branch Master</div>
	<div class="panel-tool"></div>
  </div>
  
  <div align="left" class="bkgColor" > 
  <table width="891" height="325" style="margin-left:6px;" border="0"  >
  <form:hidden path="branchDto.branchId" id="branchId"/>
         <tbody>
           <tr> <td width="106">Branch Name<span style="color: #FF0000">*</span></td>
             <td colspan="3"><form:input onkeypress="return check(event)" data-maxsize="35" type="text" style="width:93%" class="validate[required] text-input" 
              path="branchDto.branch" id="branchName" size="72" /></td>
             <td width="103" align="left"> Code<span style="color: #FF0000">*</span></td>
             <td width="143"><form:input onkeyup="valid1(this)" onblur="valid1(this)" data-maxsize="16" type="text" class="validate[required] text-input" 
              path="branchDto.invoiceCode" id="invoiceCode" size="18" readonly="true" style="width:117px"  /></td>
           </tr>
     
     	  <tr> <td>Address</td>
             <td colspan="3"><form:input onkeypress="return check(event)" data-maxsize="150" type="text" style="width:93%;" path="branchDto.address" id="address" size="72" /></td>
             <td align="left">City</td>
             <td><form:input type="text" onkeypress="return check(event)" data-maxsize="25" path="branchDto.city" id="city" size="18" /></td>
          </tr>
     
          <tr>
            <td>State</td>
            <td><form:input onkeypress="return check(event)" data-maxsize="25" type="text" path="branchDto.state" size="18" id="state" /></td>
            <td>Country</td>
            <td><form:input type="text" onkeypress="return check(event)" data-maxsize="25" path="branchDto.country" size="18" id="country" /></td>
            <td align="left">PIN/ZIP Code</td>
            <td><form:input type="text" onkeypress="return check(event)" data-maxsize="11"  class="quantity2 validate[custom[number]]" path="branchDto.pinZip" id="pinZipCode" size="18" /></td>
          </tr>
          <tr>
            <td>Phone1 (0)</td>
            <td width="170"><form:input onkeypress="return check(event)" data-maxsize="25" type="text" path="branchDto.phone1" id="phone1" size="18"/></td>
            <td width="91">Phone2 (0)</td>
            <td width="149"><form:input onkeypress="return check(event)" data-maxsize="25" type="text" path="branchDto.phone2" size="18" id="phone1"/></td>
            <td align="left">Fax</td>
            <td><form:input onkeypress="return check(event)" data-maxsize="11" type="text" path="branchDto.fax" id="fax" size="18"/>
            </td>
         </tr>
     
    	  <tr>
    	    <td>Tin(VAT)No.</td>
    	    <td><form:input onkeypress="return check(event)" data-maxsize="35" type="text" path="branchDto.vatNo" id="tinVatNo" size="18"/></td>
    	    <td>Date(VAT)</td>
    	    <td><form:input  type="text" path="branchDto.vatDate" style="width:123px" readonly="true" size="18" id="dateVat" class="datepicker"/></td>
    	    <td align="left">Stock Restriction</td>
    	    <td><div id="radiobutton" style="border:solid 1px; height:20px; width:118px;
            border-color:#7f9db9; background-color:#FFF;">&nbsp;
    	        <span style="    float: left;    margin-top:2px;    padding-left: 12px;"> Yes</span>
    	      <form:radiobutton style="width:20px;  float: left; " path="branchDto.stockRestrictFlag" value="1" id="stockRestriction" />
    	    <span style="    float: left;    margin-top: 2px;  "> No</span>	
 			 <form:radiobutton style="width:20px;" path="branchDto.stockRestrictFlag" value="0" id="stockRestriction" />
  	      </div></td>
  	    </tr>
    	  <tr>
    	    <td>TIN(CST)No.</td>
    	    <td><form:input type="text" onkeypress="return check(event)" data-maxsize="35" path="branchDto.cstNo" id="tinCstNo" size="18"/></td>
    	    <td>Date(CST)</td>
    	    <td><form:input type="text" onkeyup="valid(this)" style="width:123px" readonly="true" onblur="valid(this)" data-maxsize="35" path="branchDto.cstDate" size="18" id="dateCst" class="datepicker"/></td>
    	    <td align="left">Credit Day's</td>
    	    <td><form:input type="text" path="branchDto.creditDays" data-maxsize="3"  class="quantity validate[custom[number]]" style="text-align: right;" id="creditDays"  size="18" />
            <td> </td>
  	    </tr>
    	  <tr>
            <td height="29">Email-ID</td><td><form:input class="validate[custom[email]] text-input" onkeyup="email(this)" onblur="email(this)" data-maxsize="65" type="text" path="branchDto.emailId" id="emailId" size="18" /></td>
            <td>Website</td><td><form:input type="text"   path="branchDto.website" size="18" id="website" data-maxsize="65" class="website validate[custom[url]] text-input"/></td>
            <td align="left">Credit Limit</td><td><form:input type="text" path="branchDto.creditLimit" class="quantity" data-maxsize="15"
             id="creditLimit" size="18" /></td>
         </tr>
     
     	  <tr>
            <td>Service Tax No</td><td><form:input onkeypress="return check(event)" data-maxsize="25" type="text" path="branchDto.servTaxNo"  id="branchDto.servTaxNo" size="18" /></td>
            <td>Tax Date</td><td><form:input style="width:123px" readonly="true" type="text" path="branchDto.servTaxDate" size="18" id="taxDate" class="datepicker"/></td>
            <td align="left">MSME Code</td>
            <td><form:input onkeypress="return check(event)" data-maxsize="35" type="text" path="branchDto.MSMECode"  id="memeCode" size="18" /></td>
          </tr>
     
     	      <tr>
               <td>Excise Reg Ecc No</td><td><form:input onkeypress="return check(event)" data-maxsize="35" type="text" path="branchDto.exciseECCNo" id="exciseRegEccNo" size="18" /></td>
               <td>Range</td><td><form:input type="text" onkeypress="return check(event)" path="branchDto.rangAdd" size="18" id="rance" data-maxsize="35" class="rance"/></td>
               <td align="left">Division</td><td><form:input type="text" onkeypress="return check(event)" data-maxsize="35" path="branchDto.division" id="devision" size="18" />		
               </td>				
             </tr>
              <tr>
    	    <td>Commissionerate</td>
    	    <td><form:input   data-maxsize="35"  onkeypress="return check(event)" type="text" path="branchDto.commissionerate"  size="18"/></td>
    	    <td>Notification 08/2006 (Exemption)</td>
    	    <td><form:checkbox  class="checkbox"   style="width:81%" readonly="true"  onkeypress="return check(event)" path="branchDto.dutyVideNotification" value="1" size="18" ></form:checkbox></td>
    	    
            <td> </td>
  	    </tr>
           <tr> 
            <td height="28">Good's Desc.</td>
              <td colspan="5"><form:textarea onkeypress="return check(event)"   style="width:96%" path="branchDto.generalDesc" id="goodsDesc" cols="126" /></td>
           </tr>
      </tbody>
     </table>
    <div class="btn">
         <div class="savbtn">
         <c:if test="${opr=='V'}">
          <c:forEach items="${roleAndRightsDTOList}" var="roleAndRights">
		   <c:if test="${roleAndRights.menuId==sessionScope.menuId}">
    	  	 <c:if test="${roleAndRights.editFlag=='true'}">
		  	 <input class="edit_btn"  type="button" onclick="editMethod();" value=""/>
    	      <a href="show_branch_form"  class="cancelbtn" ></a>  
		     </c:if>
		      <c:if test="${roleAndRights.editFlag=='false'}">
		      <input class="edit_btn"  type="button" onclick="checkEdit();" value=""/>
    	      <a href="show_branch_form"  class="cancelbtn" ></a>  
		      </c:if>
		    
          </c:if>
          </c:forEach>	
   		   		
    	</c:if>
            <c:if test="${opr=='E'}">
               <input class="updatebtn"  type="submit" value=""/> 
                       <a href="show_branch_form"  class="cancelbtn" ></a>     	
   		   	</c:if>
    </div>
	</div>
	</div>
  </form:form>
</c:if>
 <c:if test="${opr=='R'}">

<form:form id="formID" name="formID" action="" method="post" modelAttribute="branchForm">  
   
  <div class="panel-header">
	<div class="panel-title">Branch Master</div>
	<div class="panel-tool"></div>
  </div>
  
  <div align="left" class="bkgColor" style="height:387px;"> 
  <table width="891" height="325" style="margin-left:6px;" border="0"  >
  <form:hidden path="branchDto.branchId" id="branchId"/>
         <tbody>
           <tr> <td width="106">Branch Name<span style="color: #FF0000">*</span></td>
             <td colspan="3"><form:input type="text" readonly="true" style="width:93%" class="validate[required] text-input" 
              path="branchDto.branch" id="branchName" size="72" /></td>
             <td width="103" align="left">Code<span style="color: #FF0000">*</span></td>
             <td width="143"><form:input type="text" readonly="true" class="validate[required] text-input" 
              path="branchDto.invoiceCode" id="invoiceCode" size="18" /></td>
           </tr>
     
     	  <tr> <td>Address</td>
             <td colspan="3"><form:input type="text" readonly="true" style="width:93%;" path="branchDto.address" id="address" size="72" /></td>
             <td align="left">City</td>
             <td><form:input type="text" readonly="true" path="branchDto.city" id="city" size="18" /></td>
          </tr>
     
          <tr>
            <td>State</td>
            <td><form:input type="text" readonly="true" path="branchDto.state" size="18" id="state" /></td>
            <td>Country</td>
            <td><form:input type="text" readonly="true" path="branchDto.country" size="18" id="country" /></td>
            <td align="left">PIN/ZIP Code</td>
            <td><form:input type="text" readonly="true" path="branchDto.pinZip" id="pinZipCode" size="18" /></td>
          </tr>
          <tr>
            <td>Phone1 (0)</td>
            <td width="170"><form:input type="text" readonly="true" path="branchDto.phone1" id="phone1" size="18"/></td>
            <td width="91">Phone2 (0)</td>
            <td width="149"><form:input type="text" readonly="true" path="branchDto.phone2" size="18" id="phone1"/></td>
            <td align="left">Fax</td>
            <td><form:input type="text" readonly="true" path="branchDto.fax" id="fax" size="18"/>
            </td>
         </tr>
     
    	  <tr>
    	    <td>Tin(VAT)No.</td>
    	    <td><form:input type="text" readonly="true" path="branchDto.vatNo" id="tinVatNo" size="18"/></td>
    	    <td>Date(VAT)</td>
    	    <td><form:input type="text" readonly="true" path="branchDto.vatDate" size="18" id="dateVat" class="datepicker"/></td>
    	    <td align="left">Stock Restriction</td>
    	    <td><div style="border:solid 1px; height:20px; width:117px;
            border-color:#BABABA; background-color:#ebebe4;">&nbsp;
    	      Yes
    	      <form:radiobutton style="width:20px;" readonly="true" path="branchDto.stockRestrictFlag" value="1" id="stockRestriction" />
    	      No
 			 <form:radiobutton style="width:20px;" readonly="true" path="branchDto.stockRestrictFlag" value="0" id="stockRestriction" />
  	      </div></td>
  	    </tr>
    	  <tr>
    	    <td>TIN(CST)No.</td>
    	    <td><form:input type="text" readonly="true" path="branchDto.cstNo" id="tinCstNo" size="18"/></td>
    	    <td>Date(CST)</td>
    	    <td><form:input type="text" readonly="true" path="branchDto.cstDate" size="18" id="dateCst" class="datepicker"/></td>
    	    <td align="left">Credit Day's</td>
    	    <td><form:input type="text" readonly="true" path="branchDto.creditDays"  id="creditDays1"  style="text-align: right;" size="18"/>
            <td> 
            </td>
  	    </tr>
    	  <tr>
            <td height="29">Email-ID</td><td><form:input type="text" readonly="true"  path="branchDto.emailId" id="emailId" size="18" /></td>
            <td>Website</td><td><form:input type="text" readonly="true" path="branchDto.website" size="18" id="website" class="website"/></td>
           <td align="left">Credit Limit</td><td><form:input type="text" readonly="true" path="branchDto.creditLimit" class="quantity" 
             id="creditLimit" size="18" /></td>
         </tr>
     
     	  <tr>
            <td>Service Tax No</td><td><form:input type="text" readonly="true" path="branchDto.servTaxNo"  id="branchDto.servTaxNo" size="18" /></td>
            <td>Tax Date</td><td><form:input type="text" readonly="true" path="branchDto.servTaxDate" size="18" id="taxDate" class="datepicker"/></td>
            <td align="left">MEME Code</td>
            <td><form:input type="text" readonly="true" path="branchDto.MSMECode"  id="memeCode" size="18" /></td>
          </tr>
     
     	      <tr>
               <td>Excise Reg Ecc No</td><td><form:input type="text" readonly="true" path="branchDto.exciseECCNo" id="exciseRegEccNo" size="18" /></td>
               <td>Range</td><td><form:input type="text" readonly="true" path="branchDto.rangAdd" size="18" id="rance" class="rance"/></td>
               <td align="left">Division</td><td><form:input type="text" readonly="true" path="branchDto.division" id="devision" size="18" />		
               </td>				
             </tr>
           <tr> 
            <td height="28">Good's Desc.</td>
              <td colspan="5"><form:textarea readonly="true" style="width:96%" path="branchDto.generalDesc" id="goodsDesc" cols="126" /></td>
           </tr>
      </tbody>
     </table>
   <c:url value="delete_branch" var="remove_url">
					<c:param name="branchId" value="${branchForm.branchDto.branchId}"></c:param>
		</c:url>
	<%-- 	<div class="btn">
			<a href="${remove_url}" class="removebtn" iconCls="icon-remove"></a> 
			<a href="show_branch_form"  class="cancelbtn" iconCls="icon-cancel"></a> 
		</div> --%>
		
	</div>	
  </form:form>
  </c:if>