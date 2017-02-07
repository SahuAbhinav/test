<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %>

<c:if test="${not empty(errors)}">
 <input type="hidden" id="errorId" value="${errors.errorMsg}">
 <script type="text/javascript">
 		$(document).ready(function() {
 		var errorId=document.getElementById('errorId');
		alert(errorId.value);
 		//alert('Duplicate Data In Branch');
 		});
 	</script>
 </c:if>

 <c:if test="${opr=='R'}">
 <script type="text/javascript">
		var redrctUrl='show_transporter_form';
				
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
 		 var frank_param = getParam('transporterId');
 		 //	confirm('Are you sure you want to delete?');
		 var delUrl='remove_transporter?id='+frank_param;
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
    <script>
	$(document).ready(function() {
	function fillCityData(cb){
			$.get('get_csc_by_id', { id: $(cb).val()}, function(data) {		
						//alert('hello**');
						 $("#state").val(data.stateName);
						 $("#country").val(data.countryName);
			});
			}
	$(function() {			
		$('#scity').change(function() {
				var v=$(this).val();
				fillCityData(this);
		});
	});	
	fillCityData($('#scity'));	
	});
</script> 

  <script type="text/javascript">
	//	$(document).ready(function() {
 	function editMethod()
 	 { 
 	 var frank_param = $('#transporterId').val();
 	 //	confirm('Are you sure you want to delete?');
	 var delUrl='get_transporter?transporterId='+frank_param+'&opr=E';
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
	$('input:radio').attr('disabled',true);
	 $("input:checkbox").attr("disabled", true);
});
</script>
</c:if>	


  <c:if test="${opr=='R'}">
<script>
$(document).ready(function() {
	$('input').attr('disabled','disabled');	
	$('select').attr('disabled','disabled');	
});
</script>
</c:if>
	<script type="text/javascript">
	function checkEdit()
		{
		alert('Login User Not Permit to Edit Record !!!!!!');
		}
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
	input {
	width: 74%;
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
	  $('#transporterRating').change(function() {
	  $("#transporterRating").val(Number($("#transporterRating").val().toString().match(/^\d+(?:\.\d{0,0})?/)));
	  });
  //    
      });
  </script>



<form:form id="formID" name="formID" action="save_transporter" method="post" modelAttribute="transporterForm">
 <c:if test="${opr=='E' || opr=='V'}">
 <form:hidden path="transporterDTO.transporterId" id="transporterId"/>
 <input type="hidden" name="opr" value="${opr}" />
</c:if>   
  <div class="panel-header">
	<div class="panel-title">Transporter Master</div>
	<div class="panel-tool"></div>
  </div>
  
  <div align="left" class="bkgColor"> 
  
   <table  height="396" width="850" border="0" align="left" style="margin-left: 50px;">
    <tr>
      <td width="131">Transporter Name<span style="color: #FF0000">*</span></td>
      <td colspan="3"><form:input type="text" onkeypress="return check(event)"  data-maxsize="85" class="validate[required] text-input" path="transporterDTO.transName" 
      style="width:91%;" id="transporterName" size="65"/></td>
      <td width="149" align="left">Transporter Code <span style="color: #FF0000">*</span></td>
      <td width="184">
      <c:if test="${opr!='E'}">
      <form:input onkeyup="valid1(this)" onblur="valid1(this)"  type="text"  path="transporterDTO.transCode" id="transporterCode" data-maxsize="16"
        class="validate[required] text-input" size="16" />
    
      </c:if>
          <c:if test="${opr=='E'}">
      <form:input onkeyup="valid1(this)" onblur="valid1(this)"  type="text"  path="transporterDTO.transCode" id="transporterCode" data-maxsize="16"
        class="validate[required] text-input" size="16" readonly="true" />
    
      </c:if>
        </td>
    </tr>
    <tr>
      <td>Transporter Address</td>
      <td width="116" colspan="3"><form:input onkeypress="return check(event)" data-maxsize="150" type="text" style="width:91%;" path="transporterDTO.transAddress" id="transporterAddress" 
      size="58" /></td>
      <td align="left">City<span style="color: #FF0000">*</span></td>
      <td>
      <form:select path="transporterDTO.cityId"
										items="${cityList}" itemLabel="cityName" itemValue="cityId"
										id="scity"  
										style="width: 76%; height: 20px;"></form:select>
      
      
    </td>
    </tr>
    <tr>
      <td>State</td>
      <td width="184"><form:input type="text" onkeypress="return check(event)"  path="transporterDTO.state" size="16" disabled="true" data-maxsize="65" id="state" /></td>
      <td width="130">Country</td>
      <td width="184"><form:input onkeypress="return check(event)"  type="text" path="transporterDTO.country" disabled="true" data-maxsize="65" size="16" id="country" /></td>
      <td align="left">PIN/ZIP Code</td>
      <td><form:input type="text" onkeypress="return check(event)" class="quantity validate[custom[number]]"  path="transporterDTO.zipCode" size="16" data-maxsize="11" id="pinZipCode" /></td>
    </tr>
    <tr>
      <td>Phone 1 (0)</td>
      <td><form:input type="text" onkeypress="return check(event)" data-maxsize="35"  path="transporterDTO.phone1" size="16" id="phone1" /></td>
      <td>Phone 2 (0)</td>
      <td><form:input onkeypress="return check(event)" data-maxsize="35" type="text" path="transporterDTO.phone2" size="16" id="phone2" /></td>
      <td align="left">Contact Person</td>
      <td><form:input  type="text" path="transporterDTO.contactPerson" onkeypress="return check(event)"  data-maxsize="35" size="16" id="contactPerson" /></td>
    </tr>
    <tr>
      <td>Mobile</td>
      <td><form:input type="text" onkeypress="return check(event)"  path="transporterDTO.mobile" data-maxsize="35" size="16" id="mobile" /></td>
      <td>Phone (W)</td>
      <td><form:input type="text" onkeypress="return check(event)"  path="transporterDTO.phoneWork" size="16" data-maxsize="35" id="phoneW" /></td>
      <td align="left">Contact Mobile</td>
      <td><form:input type="text" onkeypress="return check(event)" data-maxsize="35" id="contactMobile" path="transporterDTO.contactMobile" size="16" /></td>
    </tr>
    <tr>
      <td>TIN (VAT) No.</td>
      <td><form:input type="text" onkeypress="return check(event)"  path="transporterDTO.vatNo" data-maxsize="35"  size="16" id="tinVatNo" /></td>
      <td>Date (VAT)</td>
      <td><form:input type="text" path="transporterDTO.vatDt" class="datepicker" style="width:74%" readonly="true" data-maxsize="35" size="16" id="dateVat" /></td>
      <td align="left">Contact Email</td>
      <td><form:input type="text" onkeypress="return check(event)"  id="contactEmail" path="transporterDTO.contactEmail" data-maxsize="35" size="16" /></td>
    </tr>
   
   
    <tr>
      <td>Banker A/c No (1)</td>
      <td><form:input type="text" onkeypress="return check(event)"  path="transporterDTO.bankAccount1" data-maxsize="35" id="bankerAcNo1" size="16"/></td>
      <td>Banker A/c No (2)</td>
      <td><form:input type="text" onkeypress="return check(event)"  class="bankerAcNo1" data-maxsize="35" path="transporterDTO.bankAccount2" size="16" id="bankAccount2"/></td>
      <td align="left">Transporter Rating</td>
      <td><form:input type="text" onkeypress="return check(event)"  class="quantity validate[custom[number]]"   path="transporterDTO.transRating" data-maxsize="11" id="transporterRating" size="16"/></td>
    </tr>
    <tr>
      <td>Bank Name (1)</td>
      <td><form:input type="text" onkeypress="return check(event)"  path="transporterDTO.bankName1" data-maxsize="65" id="bankName1" size="16" /></td>
      <td>Bank Name (2)</td>
      <td><form:input type="text" onkeypress="return check(event)"  path="transporterDTO.bankName2"  data-maxsize="65"  size="16"  id="bankName2"/></td>
      <td align="left">Date of Assessment</td>
      <td><form:input type="text" onkeyup="valid(this)" class="datepicker" style="width:74%" readonly="true" onblur="valid(this)"  id="dateOfAssesment" path="transporterDTO.assessmentDt" size="16" /></td>
    </tr>
    <tr>
      <td>Branch Name (1)</td>
      <td><form:input type="text" onkeypress="return check(event)"  path="transporterDTO.branchName1" data-maxsize="65" id="branchName1" size="16" /></td>
      <td>Branch Name (2)</td>
      <td><form:input type="text" onkeypress="return check(event)"  path="transporterDTO.branchName2" data-maxsize="65"  id="branchName2" size="16"/></td>
      <td align="left">Service Tax No.</td>
      <td><form:input type="text" onkeypress="return check(event)"  path="transporterDTO.servTaxNo" id="serviceTaxNo" data-maxsize="25" size="16" /></td>
    </tr>
    <tr>
      <td>IFSC Code 1</td>
      <td><form:input type="text" onkeypress="return check(event)"  data-maxsize="25"  path="transporterDTO.ifscCode1" id="ifscCode1" size="16" /></td>
      <td>IFSC Code 2</td>
      <td><form:input type="text" onkeypress="return check(event)"  data-maxsize="25"  path="transporterDTO.ifscCode2"  size="16" id="ifscCode2" /></td>
      <td align="left">Service tax Date</td>
      <td><form:input type="text" path="transporterDTO.servTaxDt"  class="datepicker" data-maxsize="35" style="width:74%" id="serviceTaxDate" size="16" readonly="true" /></td>
    </tr>
    <tr>
      <td height="27">Email ID</td>
      <td><form:input id="emailId" size="16" class="validate[custom[email]] text-input"   
      data-maxsize="65" path="transporterDTO.email" /></td>
      <td>Website</td>
      <c:if test="${opr!='R' && opr!='E'}">
      <td><form:input  id="website"   class="validate[custom[url]] text-input"  size="16"  data-maxsize="35"  path="transporterDTO.website" /></td>
      </c:if>
      <c:if test="${opr=='R' || opr=='E'}">
      <td><form:input  id="website" class="validate[custom[url]] text-input"  size="16"  data-maxsize="35"  path="transporterDTO.website" /></td>
      </c:if>
      
      <td align="left">Active Status<span style="color: #FF0000">*</span></td>
      <td><div style="border:solid 1px; height:20px; width:75%;  border-radius: 3px 3px 3px 3px; border-color:#7f9db9; background-color:#FFF;">
       <span style="    float: left;    margin-top:2px;    padding-left: 12px;"> Yes</span>
        <form:radiobutton path="transporterDTO.activeStatus" value="1" class="validate[required] radio" style="width:20px;  float: left;" id="activeStatus" checked="true" />       
           <span style="    float: left;    margin-top: 2px;  "> No</span>
        <form:radiobutton  class="validate[required] radio" value="0"  path="transporterDTO.activeStatus" style="width:20px;" id="activeStatus"/>
        
        </div></td>
    </tr>
    <tr>
      <td height="27">Gen. Remark</td>
      <td colspan="5"><form:textarea onkeypress="return check(event)"  type="text" path="transporterDTO.generalRemark" id="genRemark" style="width:94%; padding-left: 4px;" cols="64" /></td>
    </tr>
  </table>
  <div class="btn">
  	
    <div class="btn">
         <div class="savbtn">
         <c:if test="${opr=='V'}">
        
         <c:forEach items="${roleAndRightsDTOList}" var="roleAndRights">
		   <c:if test="${roleAndRights.menuId==sessionScope.menuId}">
    	  	 <c:if test="${roleAndRights.editFlag=='true'}">
		  	 <input class="edit_btn"  type="button" onclick="editMethod();" value=""/>
    	      <a href="show_transporter_form"  class="cancelbtn" ></a>  
		     </c:if>
		      <c:if test="${roleAndRights.editFlag=='false'}">
		      <input class="edit_btn"  type="button" onclick="checkEdit();" value=""/>
    	      <a href="show_transporter_form"  class="cancelbtn" ></a>  
		      </c:if>
		    
          </c:if>
          </c:forEach>	
   			
    	</c:if>
            <c:if test="${opr=='E'}">
               <input class="updatebtn"  type="submit" value=""/> 
                         <a href="show_transporter_form" class="cancelbtn" ></a>  
   		   	</c:if>
    
    	<c:if test="${opr!='R' && opr!='E' &&  opr!='V'}">
     		<input class="submit"  type="submit" value=""/>
     			 <a href="show_transporter_form" class="cancelbtn" ></a>
     	</c:if>
      </div> 
      
    <div ><span style="margin-left:12px;" class="errmsg"></span></div>
  </div>
  </div>
  </div>

    </form:form>
   
