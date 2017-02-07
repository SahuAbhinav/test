<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %>

<c:if test="${not empty(errorList)}">
 <input type="hidden" id="errorId" value="${errorList.errorMsg}">
 <script type="text/javascript">
  		$(document).ready(function() {
 		var errorId=document.getElementById('errorId');
		alert(errorId.value);
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
	<style>
	input{
	width:81%;
	}
	</style>
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

<script type="text/javascript">
	$(document).ready(function() {
		function fixNumFormat() {
			$(".quantity").each(function() {

				var v = parseFloat($(this).val());

				v = v.toFixed(2);
				if (v == 'NaN')
					v = '0.00';
				$(this).val(v);
			});
		}
		
		$(".quantity").change(function(e) {
			fixNumFormat();
		});
	});
</script>
<form:form id="formID" name="formID" action="save_branch" method="post" modelAttribute="branchForm">  
   
  <div class="panel-header">
	<div class="panel-title">Branch Master</div>
	<div class="panel-tool"></div>
  </div>
  
  <div align="center" class="bkgColor" style="height:387px;"> 
  <table width="891" height="300" style="margin-left:6px;" border="0">
         <tbody>
           <tr> <td width="106">Branch Name<span style="color: #FF0000">*</span></td>
             <td colspan="3"><form:input  onkeypress="return check(event)" data-maxsize="35"   type="text" style="width:93%;    padding-left: 1px;" class="validate[required] text-input" 
              path="branchDto.branch" id="branchName" size="72" /></td>
             <td width="103" align="left"> Code<span style="color: #FF0000">*</span></td>
             <td width="143"><form:input onkeyup="valid1(this)" onblur="valid1(this)" type="text" class="validate[required] text-input" 
              path="branchDto.invoiceCode" id="invoiceCode" data-maxsize="16" size="18" /></td>
           </tr>
     
     	  <tr> <td>Address</td>
             <td colspan="3"><form:input data-maxsize="150" onkeypress="return check(event)" type="text" style="width:93%;    padding-left: 1px;	" path="branchDto.address" id="address" size="72" /></td>
             <td align="left">City</td>
             <td><form:input   data-maxsize="25"  onkeypress="return check(event)" type="text" path="branchDto.city" id="city" size="18" /></td>
          </tr>
     
          <tr>
            <td>State</td>
            <td><form:input   data-maxsize="25"  onkeypress="return check(event)" type="text" path="branchDto.state" size="18" id="state" /></td>
            <td>Country</td>
            <td><form:input onkeypress="return check(event)" data-maxsize="25"   type="text" path="branchDto.country" size="18" id="country" /></td>
            <td align="left">PIN/ZIP Code</td>
            <td><form:input type="text" data-maxsize="11"  class="quantity2 validate[custom[number]]" onkeypress="return check(event)" path="branchDto.pinZip" id="pinZipCode" size="18" /></td>
          </tr>
          <tr>
            <td>Phone1 (0)</td>
            <td width="170"><form:input  onkeypress="return check(event)" onkeyup="valid(this)" data-maxsize="35"   type="text" path="branchDto.phone1" id="phone1" size="18"/></td>
            <td width="91">Phone2 (0)</td>
            <td width="149"><form:input  data-maxsize="35"  onkeypress="return check(event)" type="text" path="branchDto.phone2" size="18" id="phone1"/></td>
            <td align="left">Fax</td>
            <td><form:input type="text" data-maxsize="11" onkeypress="return check(event)" path="branchDto.fax" id="fax" size="18"/>
            </td>
         </tr>
     
    	  <tr>
    	    <td>Tin(VAT)No.</td>
    	    <td><form:input type="text" data-maxsize="35" onkeypress="return check(event)" path="branchDto.vatNo" id="tinVatNo" size="18"/></td>
    	    <td>Date(VAT)</td>
    	    <td><form:input type="text" onkeypress="return check(event)"  style="width:81%"  readonly="true"   path="branchDto.vatDate" size="18" id="dateVat" class="datepicker"/></td>
    	    <td align="left">Stock Restriction</td>
    	    <td><div style="border:solid 1px; height:20px; width:116px; border-radius:3px;
            border-color:#7F9DB9;  background-color:#FFF;">&nbsp;
    	      <span style="    float: left;    margin-top:2px;    padding-left: 12px;"> Yes</span>
    	      <form:radiobutton style="width:20px; float: left;  " path="branchDto.stockRestrictFlag" value="1" id="stockRestriction"/>
    	    <span style="    float: left;    margin-top: 2px;  "> No</span>	
 			 <form:radiobutton style="width:20px;" path="branchDto.stockRestrictFlag" value="0" id="stockRestriction"/>
  	      </div></td>
  	    </tr>
    	  <tr>
    	    <td>TIN(CST)No.</td>
    	    <td><form:input   data-maxsize="35"  onkeypress="return check(event)" type="text" path="branchDto.cstNo" id="tinCstNo" size="18"/></td>
    	    <td>Date(CST)</td>
    	    <td><form:input    style="width:81%" readonly="true"  onkeypress="return check(event)" type="text" path="branchDto.cstDate" size="18" id="dateCst" class="datepicker"/></td>
    	    <td align="left">Credit Day's</td>
    	    <td><form:input   data-maxsize="3"  class="quantity2 validate[custom[number]]" onkeypress="return check(event)" type="text" path="branchDto.creditDays"   id="creditDays" style="text-align: right;" size="18" />
            <td> </td>
  	    </tr>
    	  <tr>
            <td height="20">Email-ID</td><td><form:input onkeyup="email(this)" onblur="email(this)" type="text" path="branchDto.emailId" class="validate[custom[email]] text-input" data-maxsize="65" id="emailId" size="18" /></td>
            <td>Website</td><td><form:input   class="validate[custom[url]] text-input" data-maxsize="65"  type="text" path="branchDto.website" size="18" id="website" /></td>
           <td align="left">Credit Limit</td><td><form:input onkeypress="return check(event)" data-maxsize="15" type="text" path="branchDto.creditLimit" class="quantity" 
             id="creditLimit" size="18" /></td>
         </tr>
     
     	  <tr>
            <td>Service Tax No</td><td><form:input data-maxsize="25" onkeypress="return check(event)" type="text" path="branchDto.servTaxNo"  id="branchDto.servTaxNo" size="18" /></td>
            <td>Tax Date</td><td><form:input style="width:81%"  readonly="true" onkeyup="valid(this)"  onblur="valid(this)" type="text" path="branchDto.servTaxDate" size="18" id="taxDate" class="datepicker"/></td>
            <td align="left">MSME Code</td>
            <td><form:input type="text"  onkeypress="return check(event)" data-maxsize="35"   path="branchDto.MSMECode"  id="memeCode" size="18" /></td>
          </tr>
     
     	      <tr>
               <td>Excise Reg Ecc No</td><td><form:input type="text" data-maxsize="35" onkeypress="return check(event)" path="branchDto.exciseECCNo" id="exciseRegEccNo" size="18" /></td>
               <td>Range</td><td><form:input type="text" onkeypress="return check(event)" data-maxsize="35" path="branchDto.rangAdd" size="18" id="rance" class="rance"/></td>
               <td align="left">Division</td><td><form:input data-maxsize="35" onkeypress="return check(event)" type="text" path="branchDto.division" id="devision" size="18" />		
               </td>				
             </tr>
             
             <tr>
    	    <td>Commissionerate</td>
    	    <td><form:input   data-maxsize="35"  onkeypress="return check(event)" type="text" path="branchDto.commissionerate"  size="18"/></td>
    	    <td>Notification 08/2006 (Exemption)</td>
    	    <td><form:checkbox    style="width:81%"  readonly="true"  onkeypress="return check(event)" path="branchDto.dutyVideNotification" value="1" size="18" ></form:checkbox></td>
    	    
            <td> </td>
  	    </tr>
        <tr> 
            <td height="28">Good's Desc.</td>
              <td colspan="5"><form:textarea type="text"   onkeypress="return check(event)" style="width:96%" path="branchDto.generalDesc" id="goodsDesc" cols="126" /></td>
       </tr>
      </tbody>
     </table>
    <div class="btn">
   <div class="savbtn">
     <input class="submit"  type="submit" value=""/>
   </div>
   <div >
       <a href="show_branch_form" class="cancelbtn" iconCls="icon-cancel"></a></td>
   </div>
    </div>  
        <div ><span style="margin-left:12px;" class="errmsg"></span></div>  
   </div>
  </form:form>
   