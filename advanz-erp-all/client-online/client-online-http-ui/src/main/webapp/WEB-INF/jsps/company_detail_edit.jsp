<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page isELIgnored="false" %>
 <c:if test="${not empty(errors)}">
 <input type="hidden" id="errorId" value="${errors.errorMsg}">
 <script type="text/javascript">
 
 		var delUrl='show_new_form';
 		$(document).ready(function() {
 		var errorId=document.getElementById('errorId');
		alert(errorId.value);
 		//alert('Duplicate Data In Branch');
 		window.self.location  = delUrl;
		});
 	</script>
 </c:if>

 <c:if test="${opr=='R'}">
 <script type="text/javascript">
		var redrctUrl='show_company_form';
				
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
 		 var frank_param = getParam('companyId');
 		 //	confirm('Are you sure you want to delete?');
		 var delUrl='delete_company?companyId='+frank_param;
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
	<script>
	$(document).ready(function() {
	$('input').attr('readonly','readonly');	
	$('select').attr('disabled','disabled');
	$('textarea').attr('readonly','readonly');
	$('.datepicker2').attr('disabled','disabled');
	$('.datepicker').attr('disabled','disabled');
	$('input:radio').attr('disabled',true);
	 $("input:checkbox").attr("disabled", true);
	});
	</script>
</c:if> 	

<script type="text/javascript">
	//	$(document).ready(function() {
 	function editMethod()
 	 { 
 	 var frank_param = $('#companyId').val();
 	 //	confirm('Are you sure you want to delete?');
	 var delUrl='get_company?companyId='+frank_param+'&opr=E';
 	 window.self.location = delUrl;  
	}
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
	$(".quantity2").keypress(function(e) {
		//if the letter is not digit then display error and don't type anything
		if (e.which != 8 && e.which != 0 && (e.which<48 || e.which>57)) {
			//display error message
			$(".errmsg2").html("Digits Only").show().fadeOut("slow");
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

  
 <script type="text/javascript">
      $(document).ready(function()
       {
       $("button").button();
    	  //$('input:text, input:password').button()   
	    $(".datepicker" ).datepicker();
  //      $(":submit").button()
	    $('.datepicker').attr('readonly','true');	
      });
  </script>
  
 <!-- the jQuery -->
<script type="text/javascript">
function checkEdit()
	{
	alert('Login User Not Permit to Edit Record !!!!!!');
	}

var url;
function newUser(){
	$('#dlg').dialog('open').dialog('setTitle','New User');
	$('#formID').form('clear');
	url = 'save_company';
}
</script>
<style>
<!--
input{
width:76%;
}
 
</style>     

<c:if test="${opr=='E' || opr=='V'}">
<form:form id="formID" name="formID" action="update_company" method="post" modelAttribute="companyForm">
 
   
  <div class="panel-header">
	<div class="panel-title">Company Detail</div>	
	<div class="panel-tool"></div>
  </div>
  
  <div align="left" class="bkgColor" style="padding-top: 12px;" > 
   <table  height="426" width="770" border="0" align="center" style="margin-left:20px;">
   <form:hidden path="companyDto.companyId" id="companyId"/>
   <form:hidden path="companyDto.emailFlag" />
    <tr>
      <td width="109">Company<span style="color: #FF0000">*</span></td>
      <td colspan="3"><form:input onkeypress="return check(event)" type="text" data-maxsize="65" class="validate[required] text-input" path="companyDto.companyName" 
      style="width:91%;  padding-left: 2px;" id="company" size="58"/></td>
      <td width="101" align="left">Company Code<span style="color: #FF0000">*</span></td>
      <td width="141"><form:input readonly="true"  onkeyup="valid1(this)" onblur="valid1(this)" type="text" path="companyDto.companyCode" id="companyCode" data-maxsize="16" 
      class="validate[required] text-input" size="16" /></td>
    </tr>
    <tr>
     <td>Fin. Year From<span style="color: #FF0000">*</span></td>
     <td width="162"><form:input onkeypress="return check(event)" type="text" class="validate[required] datepicker" path="companyDto.financialYrBeg"  size="16" id="finYearFrom" readonly="true" /></td>
      <td width="104">Year To<span style="color: #FF0000">*</span></td>
      <td width="151"><form:input onkeyup="valid(this)"  onblur="valid(this)"  style="width:75%;   padding-left: 2px;"    type="text" class="validate[required] datepicker"  
       path="companyDto.financialYrEnd" size="16" id="yearTo" readonly="true"/></td>
      <td align="left">Data Lock</td>
      <td><form:input type="text" onkeypress="return check(event)" path="companyDto.systemLocking" size="16"  class="datepicker"  id="dataLock" readonly="true"/></td>
    </tr>
    <tr>
      <td>Local Address </td>
      <td colspan="3"><form:input  type="text" onkeypress="return check(event)" data-maxsize="150" style="width:91%;   padding-left: 2px;" path="companyDto.companyAdd" id="localAddress" size="58" /></td>
      <td align="left">Local City</td>
      <td><form:input type="text" onkeypress="return check(event)" id="localCity" path="companyDto.companyCity" data-maxsize="25" size="16" /></td>
    </tr>
    <tr>
      <td>Local State</td>
      <td><form:input type="text" onkeypress="return check(event)" path="companyDto.companyState" size="16" data-maxsize="25" id="localState" /></td>
      <td>Local Country</td>
      <td><form:input type="text" onkeypress="return check(event)" path="companyDto.companyCountry" data-maxsize="25" size="16" id="localCountry" /></td>
      <td align="left">PIN/ZIP Code</td>
      <td><form:input class="quantity2" onkeypress="return check(event)" path="companyDto.pinZipCode" size="16" data-maxsize="11" id="pinZipCode" /></td>
    </tr>
    <tr>
      <td>Phone 1 (0)</td>
      <td><form:input type="text" onkeypress="return check(event)" data-maxsize="35" path="companyDto.phone1" size="16" id="phone1" /></td>
      <td>Phone 2 (0)</td>
      <td><form:input type="text" onkeypress="return check(event)" data-maxsize="35" path="companyDto.phone2" size="16" id="phone2" /></td>
      <td align="left">Factory Phone</td>
      <td><form:input type="text" onkeypress="return check(event)"  path="companyDto.factoryPhone" data-maxsize="35" size="16" id="pinZipCode" /></td>
    </tr>
    <tr>
      <td>Reg. Office Address</td>
      <td colspan="3"><form:input onkeypress="return check(event)" type="text" data-maxsize="150" style="width:91%;  padding-left: 2px;" path="companyDto.officeAdd" id="regOfficecAddress" size="58" /></td>
      <td align="left">Reg. Office City</td>
      <td><form:input type="text" path="companyDto.regCompanyCity" onkeypress="return check(event)" data-maxsize="25" id="regOfficeCity" size="16" /></td>
    </tr>
    <tr>
      <td>Reg. Office State</td>
      <td><form:input type="text" onkeypress="return check(event)" path="companyDto.regCompanyState" data-maxsize="25" size="16" id="regOfficeState" /></td>
      <td>Reg. Office Country</td>
      <td><form:input type="text" onkeypress="return check(event)" path="companyDto.regCompanyCountry" size="16" data-maxsize="25" id="regOfficeCountry" /></td>
      <td align="left">Reg. PIN/ZIP Code</td>
      <td><form:input type="text" onkeypress="return check(event)" data-maxsize="11" id="regPinZipCode" path="companyDto.regPinZipCode" size="16" class="quantity2" /></td>
    </tr>
    <tr>
      <td>Reg.  Phone 1 </td>
      <td><form:input type="text" onkeypress="return check(event)" path="companyDto.officePhone1" data-maxsize="35"  size="16" id="regPhone1" /></td>
      <td>Reg. Phone 2 </td>
      <td><form:input type="text" onkeypress="return check(event)" path="companyDto.officePhone2" data-maxsize="35" size="16" id="regPhone2" /></td>
      <td align="left">Fax</td>
      <td><form:input type="text" onkeypress="return check(event)" id="fax" path="companyDto.fax" data-maxsize="35" size="16" /></td>
    </tr>
   
   
    <tr>
      <td>TIN (VAT) No.</td>
      <td><form:input type="text" onkeypress="return check(event)" path="companyDto.vatNo" data-maxsize="35" id="tinVatNo" size="16"/></td>
      <td>Date (VAT)</td>
      <td><form:input type="text" class="datepicker" path="companyDto.vatDt" size="16" id="dateVat"/></td>
      <td align="left">Curr. Symbol</td>
      <td><form:input type="text" onkeypress="return check(event)" path="companyDto.currancySymbol" data-maxsize="4" id="currSymbol" size="16"/></td>
    </tr>
    <tr>
      <td>TIN (CST) No.</td>
      <td><form:input type="text" onkeypress="return check(event)" path="companyDto.cstNo" data-maxsize="35" id="tinCstNo" size="16" /></td>
      <td>Date (CST)</td>
      <td><form:input type="text" onkeypress="return check(event)"  path="companyDto.cstDt" class="datepicker" size="16"  id="dateCst"/></td>
      <td align="left">IE Code</td>
      <td><form:input type="text" onkeypress="return check(event)" data-maxsize="25" id="ieCode" path="companyDto.importExportCode" size="16" /></td>
    </tr>
    <tr>
      <td>PAN</td>
      <td><form:input type="text" onkeypress="return check(event)" path="companyDto.panNo" data-maxsize="25" id="pan" size="16" /></td>
      <td>PAN Date</td>
      <td><form:input type="text" path="companyDto.panDt" id="panDate" class="datepicker" size="16"/></td>
      <td align="left">Drug License 1</td>
      <td><form:input type="text" onkeypress="return check(event)" path="companyDto.drugLicence1" id="drugLicence1" data-maxsize="35" size="16" /></td>
    </tr>
    <tr>
      <td>Service Tax No</td>
      <td><form:input type="text" onkeypress="return check(event)" data-maxsize="25" path="companyDto.servTaxNo" id="serviceTaxNo" size="16" /></td>
      <td>Ser. Date</td>
      <td><form:input type="text" onkeypress="return check(event)" path="companyDto.servTaxDt" class="datepicker" size="16" id="serDate" /></td>
      <td align="left">Drug License 2</td>
      <td><form:input type="text" onkeypress="return check(event)" path="companyDto.drugLicence2" data-maxsize="35" id="drugLicence2" size="16" /></td>
    </tr>
    <tr>
      <td>Email ID</td>
      <td><form:input id="emailId" onkeyup="email(this)" onblur="email(this)" class="validate[custom[email]] text-input" type="text" size="16"  data-maxsize="65" path="companyDto.emailId"/></td>
      <td>Website</td>
      <td><form:input id="website"   class="validate[custom[url]] text-input"   type="text" size="16"   data-maxsize="65"
       path="companyDto.website"/></td>
      <td align="left">MSME Code</td>
      <td><form:input type="text" onkeypress="return check(event)" path="companyDto.msmeCode" data-maxsize="35" id="drugLicence2" size="16" /></td>
    </tr>
    <tr>
      <td>Excise Reg Ecc No.</td>
      <td><form:input type="text" onkeypress="return check(event)" path="companyDto.exciseECCNo" data-maxsize="35" id="exciseRegEccNo" size="16" /></td>
      <td>Range</td>
      <td><form:input type="text" onkeypress="return check(event)" data-maxsize="35" path="companyDto.rangeAdd"  id="range" size="16" /></td>
      <td align="left">Division</td>
      <td><form:input type="text" onkeypress="return check(event)" path="companyDto.division" id="division" data-maxsize="35" size="16"/></td>
    </tr>
    <tr>
	<td>Commissionerate</td>
    <td><form:input type="text" path="companyDto.commissionerate"  data-maxsize="35" size="18" id="division" /></td>
	<td>Insurance Policy No</td>
								<td><form:input type="text" path="companyDto.insurancePolicyNo"  data-maxsize="35"
										size="18" id="insurancePolicyNo" /></td>
										
	<td>Stock lock at Invoice</td>
								<td>
										<div
						style="border: solid 1px; height: 20px;  margin-bottom: 5px; width: 77%; border-radius: 3px 3px 3px 3px; border-color: #7f9db9; background-color: #FFF;">
						<form:radiobutton path="companyDto.stockLockFlag"
							style="width: 10px;" id="stockLockFlag" value="1" />
						Yes
						<form:radiobutton path="companyDto.stockLockFlag"
							style="width: 10px;" id="stockLockFlag" value="0" />
						No
					</div>
							</td>								
	</tr>
    <tr>
      <td>Gen. Remark</td>
      <td colspan="3"><form:textarea onkeypress="return check(event)" path="companyDto.generalRemark" id="genRemark" style="width:90%;" cols="64" /></td>
      <td>Stock lock at Issue</td>
								<td><div
						style="border: solid 1px; height: 20px;  margin-bottom: 5px; width: 77%; border-radius: 3px 3px 3px 3px; border-color: #7f9db9; background-color: #FFF;">
						<form:radiobutton path="companyDto.issueLockFlag"
							style="width: 10px;" id="issueLockFlag" value="1" />
						Yes
						<form:radiobutton path="companyDto.issueLockFlag"
							style="width: 10px;" id="issueLockFlag" value="0" />
						No
					</div>
					
										</td>
      </tr>
      <tr>
      <td>Weighing file Path</td>
      <td colspan="5"><form:textarea onkeypress="return check(event)" data-maxsize="35" path="companyDto.weightingFilePath"  style="width:96%;" cols="64" /></td>
      </tr>
       
  </table>
    <div class="btn">
   <div class="savbtn">
   <c:if test="${opr=='V'}">
	   <c:if test="${opr=='V'}">
         <c:forEach items="${roleAndRightsDTOList}" var="roleAndRights">
		   <c:if test="${roleAndRights.menuId==sessionScope.menuId}">
    	  	 <c:if test="${roleAndRights.editFlag=='true'}">
		  	 <input class="edit_btn"  type="button" onclick="editMethod();" value=""/>
    	      <a href="show_company_form"  class="cancelbtn" ></a>  
		     </c:if>
		      <c:if test="${roleAndRights.editFlag=='false'}">
		      <input class="edit_btn"  type="button" onclick="checkEdit();" value=""/>
    	      <a href="show_company_form"  class="cancelbtn" ></a>  
		      </c:if>
		    
          </c:if>
          </c:forEach>	   		
    	</c:if>
        
	 </c:if>
	 <c:if test="${opr=='E'}">
     <input id="myButton" class="updatebtn"  type="submit" value=""/>
   	<div style="float: left;">
     <a href="show_company_form" class="cancelbtn" iconCls="icon-cancel"></a>
   </div>
   </c:if>
   </div>
    </div>
    <div ><span style="margin-left:12px;" class="errmsg"></span></div> 
  </div>
</form:form>
 </c:if> 
 <c:if test="${opr=='R'}">

 <form:form id="formID" name="formID" action="" method="post" modelAttribute="companyForm">
 
   
  <div class="panel-header">
	<div class="panel-title">Company Detail</div>	
	<div class="panel-tool"></div>
  </div>
  
  <div align="left" class="bkgColor"  > 
   <table  height="426px" width="770" border="0" align="center" style="margin-left:20px;">
   <form:hidden path="companyDto.companyId" id="companyId"/>
    <tr>
      <td width="109">Company<span style="color: #FF0000">*</span></td>
      <td colspan="3"><form:input onkeypress="return check(event)" type="text" data-maxsize="65" class="validate[required] text-input" path="companyDto.companyName" readonly="true"
      style="width:91%" id="company" size="58"/></td>
      <td width="101" align="left">Company Code<span style="color: #FF0000">*</span></td>
      <td width="141"><form:input onkeypress="return check(event)" type="text" readonly="true" path="companyDto.companyCode" id="companyCode" data-maxsize="16" 
      class="validate[required] text-input" size="16" /></td>
    </tr>
    <tr>
     <td>Fin. Year From<span style="color: #FF0000">*</span></td>
     <td width="162"><form:input onkeypress="return check(event)" type="text" readonly="true" class="validate[required] datepicker" path="companyDto.financialYrBeg"  size="16" id="finYearFrom" /></td>
      <td width="80">Year To<span onkeypress="return check(event)" style="color: #FF0000">*</span></td>
      <td width="151"><form:input onkeypress="return check(event)" type="text" readonly="true" class="validate[required] datepicker"  
       path="companyDto.financialYrEnd" size="16" id="yearTo"/></td>
      <td align="left">Data Lock</td>
      <td><form:input type="text" onkeypress="return check(event)" readonly="true" path="companyDto.systemLocking" size="16" data-maxsize="150" class="datepicker"  id="dataLock" /></td>
    </tr>
    <tr>
      <td>Local Address </td>
      <td colspan="3"><form:input  type="text" readonly="true"   style="width:91%" path="companyDto.companyAdd" id="localAddress" size="58" /></td>
      <td align="left">Local City</td>
      <td><form:input type="text" onkeypress="return check(event)" id="localCity" readonly="true" path="companyDto.companyCity" data-maxsize="25" size="16" /></td>
    </tr>
    <tr>
      <td>Local State</td>
      <td><form:input type="text" onkeypress="return check(event)" readonly="true" path="companyDto.companyState" size="16" data-maxsize="65" id="localState" /></td>
      <td>Local Country</td>
      <td><form:input type="text" onkeypress="return check(event)" readonly="true" path="companyDto.companyCountry" data-maxsize="65" size="16" id="localCountry" /></td>
      <td align="left">PIN/ZIP Code</td>
      <td><input type="text" onkeypress="return check(event)" readonly="true" name="companyDto.pinZipCode" size="16" data-maxsize="11" id="pinZipCode" /></td>
    </tr>
    <tr>
      <td>Phone 1 (0)</td>
      <td><form:input type="text" onkeypress="return check(event)" readonly="true" path="companyDto.phone1" size="16" id="phone1" /></td>
      <td>Phone 2 (0)</td>
      <td><form:input type="text" readonly="true" path="companyDto.phone2" size="16" id="phone2" /></td>
      <td align="left">Factory Phone</td>
      <td><form:input type="text" readonly="true" path="companyDto.factoryPhone" data-maxsize="35" size="16" id="pinZipCode" /></td>
    </tr>
    <tr>
      <td>Reg. Office Address</td>
      <td colspan="3"><form:input readonly="true" type="text" data-maxsize="150"   style="width:91%" path="companyDto.officeAdd" id="regOfficecAddress" size="58" /></td>
      <td align="left">Reg. Office City</td>
      <td><form:input type="text" readonly="true" path="companyDto.regCompanyCity" data-maxsize="25" id="regOfficeCity" size="16" /></td>
    </tr>
    <tr>
      <td>Reg. Office State</td>
      <td><form:input type="text" readonly="true" path="companyDto.regCompanyState" data-maxsize="25" size="16" id="regOfficeState" /></td>
      <td>Reg. Office Country</td>
      <td><form:input type="text" readonly="true" path="companyDto.regCompanyCountry" size="16" data-maxsize="25" id="regOfficeCountry" /></td>
      <td align="left">Reg. PIN/ZIP Code</td>
      <td><form:input type="text" readonly="true" id="regPinZipCode" path="companyDto.regPinZipCode" size="16" /></td>
    </tr>
    <tr>
      <td>Reg.  Phone 1 </td>
      <td><form:input type="text" readonly="true" path="companyDto.officePhone1" data-maxsize="35"  size="16" id="regPhone1" /></td>
      <td>Reg. Phone 2 </td>
      <td><form:input type="text" readonly="true" path="companyDto.officePhone2" data-maxsize="35" size="16" id="regPhone2" /></td>
      <td align="left">Fax</td>
      <td><form:input type="text" readonly="true" id="fax" path="companyDto.fax" data-maxsize="35" size="16" /></td>
    </tr>
   
   
    <tr>
      <td>TIN (VAT) No.</td>
      <td><form:input type="text" readonly="true" path="companyDto.vatNo" data-maxsize="35" id="tinVatNo" size="16"/></td>
      <td>Date (VAT)</td>
      <td><form:input type="text" readonly="true" class="datepicker" path="companyDto.vatDt" size="16" id="dateVat"/></td>
      <td align="left">Curr. Symbol</td>
      <td><form:input type="text" readonly="true"  path="companyDto.currancySymbol" data-maxsize="4" id="currSymbol" size="16"/></td>
    </tr>
    <tr>
      <td>TIN (CST) No.</td>
      <td><form:input type="text" readonly="true" path="companyDto.cstNo" data-maxsize="35" id="tinCstNo" size="16" /></td>
      <td>Date (CST)</td>
      <td><form:input type="text" readonly="true" path="companyDto.cstDt" class="datepicker" size="16"  id="dateCst"/></td>
      <td align="left">IE Code</td>
      <td><form:input type="text" readonly="true" id="ieCode" path="companyDto.importExportCode" size="16" /></td>
    </tr>
    <tr>
      <td>PAN</td>
      <td><form:input type="text" readonly="true" path="companyDto.panNo" data-maxsize="25" id="pan" size="16" /></td>
      <td>PAN Date</td>
      <td><form:input type="text" readonly="true" path="companyDto.panDt" id="panDate" class="datepicker" size="16"/></td>
      <td align="left">Drug License 1</td>
      <td><form:input type="text" readonly="true" path="companyDto.drugLicence1" id="drugLicence1" data-maxsize="35" size="16" /></td>
    </tr>
    <tr>
      <td>Service Tax No</td>
      <td><form:input type="text" readonly="true" path="companyDto.servTaxNo" id="serviceTaxNo" size="16" /></td>
      <td>Ser. Date</td>
      <td><form:input type="text" readonly="true" path="companyDto.servTaxDt" class="datepicker" size="16" id="serDate" /></td>
      <td align="left">Drug License 2</td>
      <td><form:input type="text" readonly="true" path="companyDto.drugLicence2" data-maxsize="35" id="drugLicence2" size="16" /></td>
    </tr>
    <tr>
      <td>Email ID</td>
      <td><form:input id="emailId" readonly="true" type="text" size="16"  data-maxsize="65" path="companyDto.emailId"/></td>
      <td>Website</td>
      <td><form:input id="website" readonly="true" type="text" size="16"   data-maxsize="65"
       path="companyDto.website"/></td>
      <td align="left">MSME Code</td>
      <td><form:input type="text" readonly="true" path="companyDto.msmeCode" data-maxsize="35" id="drugLicence2" size="16" /></td>
    </tr>
    <tr>
      <td>Excise Reg Ecc No.</td>
      <td><form:input type="text" readonly="true" path="companyDto.exciseECCNo" data-maxsize="35" id="exciseRegEccNo" size="16" /></td>
      <td>Range</td>
      <td><form:input type="text" readonly="true" path="companyDto.rangeAdd"  id="range" size="16" /></td>
      <td align="left">Division</td>
      <td><form:input type="text" readonly="true" path="companyDto.division" id="division" data-maxsize="35" size="16"/></td>
    </tr>
    <tr>
      <td>Gen. Remark</td>
      <td colspan="5"><form:textarea readonly="true" path="companyDto.generalRemark" id="genRemark" style="width:95%;" cols="64" /></td>
      </tr>
      <tr>
      <tr>
      <td>Weighing file Path</td>
      <td colspan="5"><form:textarea onkeypress="return check(event)" data-maxsize="35" path="companyDto.weightingFilePath"  style="width:96%;" cols="64" /></td>
      </tr>
	<td>Commissionerate</td>
    <td><form:input type="text" path="companyDto.commissionerate"  data-maxsize="35" size="18" id="division" /></td>
	</tr>
  </table>  
  <c:url value="delete_company" var="remove_url">
					<c:param name="companyId" value="${companyForm.companyDto.companyId}"></c:param>
		</c:url>
		<%-- <div class="btn">
			<a href="${remove_url}" class="removebtn" iconCls="icon-remove"></a> 
			<a href="show_company_form"  class="cancelbtn" iconCls="icon-cancel"></a> 
		</div> --%>
	</div>	
</form:form>
</c:if>
 

