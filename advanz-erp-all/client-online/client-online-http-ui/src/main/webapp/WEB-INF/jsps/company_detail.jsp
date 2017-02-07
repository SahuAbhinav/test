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
 

  <style>
  input {
  width:81%;
  }
  </style>
 <script type="text/javascript">
      $(document).ready(function()
       {
    	  $(".datepicker" ).datepicker({dateFormat : 'dd-M-yy',changeMonth: true,
	          changeYear: true, yearRange: '-99:+0', maxDate: '+0M +0D'});
  </script>
  
  

<form:form id="formID" name="formID" action="save_company" method="post" modelAttribute="companyForm">
 
   
  <div class="panel-header">
	<div class="panel-title">Company Detail</div>	
	<div class="panel-tool"></div>
  </div>
  
  <div align="left" class="bkgColor"  style="padding-top: 12px;" > 
   <table  height="426px" width="746" border="0" align="center" style="margin-left:13px;">
    <tr>
      <td width="109">Company<span style="color: #FF0000">*</span></td>
      <td colspan="3"><form:input type="text" data-maxsize="65"  onkeypress="return check(event)" class="validate[required] text-input " path="companyDto.companyName" 
      style="width:93%;    padding-left: 1px;" id="company" size="58"/></td>
      <td width="103" align="left">Company Code<span style="color: #FF0000">*</span></td>
      <td width="141"><form:input type="text" onkeyup="valid1(this)" onblur="valid1(this)" path="companyDto.companyCode" id="companyCode" data-maxsize="16" 
      class="validate[required] text-input" size="16" /></td>
    </tr>
    <tr>
     <td>Fin. Year From<span style="color: #FF0000">*</span></td>
     <td width="162"><form:input type="text" class="validate[required] datepicker" style="width:81%"  readonly="true" path="companyDto.financialYrBeg"  size="16" id="finYearFrom" /></td>
      <td width="104">Year To<span style="color: #FF0000">*</span></td>
      <td width="151"><form:input type="text"  style="width:81%;  "  readonly="true" class="validate[required] datepicker"  
       path="companyDto.financialYrEnd" size="16" id="yearTo"/></td>
      <td align="left">Data Lock</td>
      <td><form:input type="text" onkeyup="valid(this)"  style="width:81%"  readonly="true" onblur="valid(this)" path="companyDto.systemLocking" size="16" data-maxsize="150" class="datepicker"  id="dataLock" /></td>
    </tr>
    <tr>
      <td>Local Address </td>
      <td colspan="3"><form:input type="text" data-maxsize="150"  onkeypress="return check(event)" style="width:93%;    padding-left: 1px;" path="companyDto.companyAdd" id="localAddress" size="58" /></td>
      <td align="left">Local City</td>
      <td><form:input type="text" onkeypress="return check(event)" id="localCity" path="companyDto.companyCity" data-maxsize="25" size="16" /></td>
    </tr>
    <tr>
      <td>Local State</td>
      <td><form:input type="text" onkeypress="return check(event)" path="companyDto.companyState" size="16" data-maxsize="25" id="localState" /></td>
      <td>Local Country</td>
      <td><form:input type="text" onkeypress="return check(event)" Style="width:81%" path="companyDto.companyCountry" data-maxsize="25" size="16" id="localCountry" /></td>
      <td align="left">PIN/ZIP Code</td>
      <td><form:input onkeypress="return check(event)" path="companyDto.pinZipCode" size="16" class="quantity2" data-maxsize="11" id="pinZipCode" /></td>
    </tr>
    <tr>
      <td>Phone 1 (0)</td>
      <td><form:input type="text" onkeypress="return check(event)" path="companyDto.phone1" data-maxsize="35" size="16" id="phone1" /></td>
      <td>Phone 2 (0)</td>
      <td><form:input type="text" onkeypress="return check(event)" path="companyDto.phone2" size="16" data-maxsize="35" id="phone2" /></td>
      <td align="left">Factory Phone</td>
      <td><form:input onkeypress="return check(event)" type="text" path="companyDto.factoryPhone" data-maxsize="35" size="16" id="pinZipCode" /></td>
    </tr>
    <tr>
      <td>Reg. Office Address</td>
      <td colspan="3"><form:input type="text" onkeypress="return check(event)" data-maxsize="150" style="width:93%;     padding-left: 1px;" path="companyDto.officeAdd" id="regOfficecAddress" size="58" /></td>
      <td align="left">Reg. Office City</td>
      <td><form:input type="text" onkeypress="return check(event)"  path="companyDto.regCompanyCity" data-maxsize="25" id="regOfficeCity" size="16" /></td>
    </tr>
    <tr>
      <td>Reg. Office State</td>
      <td><form:input type="text" onkeypress="return check(event)" path="companyDto.regCompanyState" data-maxsize="25" size="16" id="regOfficeState" /></td>
      <td>Reg. Office Country</td>
      <td><form:input type="text" onkeypress="return check(event)" path="companyDto.regCompanyCountry" size="16" data-maxsize="25" id="regOfficeCountry" /></td>
      <td align="left">Reg. PIN/ZIP Code</td>
      <td><form:input type="text" onkeypress="return check(event)" id="regPinZipCode" path="companyDto.regPinZipCode"  data-maxsize="11"  size="16" class="quantity2"/></td>
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
      <td><form:input type="text" readonly="true"  style="width:81%;"  onkeypress="return check(event)" class="datepicker" path="companyDto.vatDt" size="16" id="dateVat"/></td>
      <td align="left">Curr. Symbol</td>
      <td><form:input type="text"   path="companyDto.currancySymbol" data-maxsize="16" id="currSymbol" size="16"/></td>
    </tr>
    <tr>
      <td>TIN (CST) No.</td>
      <td><form:input type="text" onkeypress="return check(event)" path="companyDto.cstNo" data-maxsize="35" id="tinCstNo" size="16" /></td>
      <td>Date (CST)</td>
      <td><form:input type="text"  style="width:81% ; "  readonly="true" onkeypress="return check(event)" path="companyDto.cstDt" class="datepicker" size="16"  id="dateCst"/></td>
      <td align="left">IE Code</td>
      <td><form:input type="text" onkeypress="return check(event)" id="ieCode" data-maxsize="25" path="companyDto.importExportCode" size="16" /></td>
    </tr>
    <tr>
      <td>PAN</td>
      <td><form:input type="text" onkeypress="return check(event)" path="companyDto.panNo" data-maxsize="25" id="pan" size="16" /></td>
      <td>PAN Date</td>
      <td><form:input type="text" readonly="true"  style="width:81%;  "  onkeypress="return check(event)" path="companyDto.panDt" id="panDate" class="datepicker" size="16"/></td>
      <td align="left">Drug License 1</td>
      <td><form:input type="text" onkeypress="return check(event)" path="companyDto.drugLicence1" id="drugLicence1" data-maxsize="35" size="16" /></td>
    </tr>
    <tr>
      <td>Service Tax No</td>
      <td><form:input type="text" onkeypress="return check(event)" data-maxsize="25" path="companyDto.servTaxNo" id="serviceTaxNo" size="16" /></td>
      <td>Ser. Date</td>
      <td><form:input type="text" readonly="true"  style="width:81%;   "  onkeypress="return check(event)" path="companyDto.servTaxDt" class="datepicker" size="16" id="serDate" /></td>
      <td align="left">Drug License 2</td>
      <td><form:input type="text" onkeypress="return check(event)" path="companyDto.drugLicence2" data-maxsize="35" id="drugLicence2" size="16" /></td>
    <tr>
      <td>Email ID</td>
      <td><form:input id="emailId"  class="validate[custom[email]] text-input" onkeyup="email(this)" onblur="email(this)" type="text" size="16"  data-maxsize="65" path="companyDto.emailId"/></td>
      <td>Website</td>
      <td><form:input  id="website" class="validate[custom[url]] text-input"  onkeypress="return check(event)" type="text" size="16"   data-maxsize="65"
       path="companyDto.website"/></td>
      <td align="left">MSME Code</td>
      <td><form:input type="text" onkeypress="return check(event)" path="companyDto.msmeCode" data-maxsize="35" id="drugLicence2" size="16" /></td>
    </tr>
    <tr>
      <td>Excise Reg Ecc No.</td>
      <td><form:input type="text" onkeypress="return check(event)" path="companyDto.exciseECCNo" data-maxsize="35" id="exciseRegEccNo" size="16" /></td>
      <td>Range</td>
      <td><form:input type="text" onkeypress="return check(event)" path="companyDto.rangeAdd" data-maxsize="35" id="range" size="16" /></td>
      <td align="left">Division</td>
      <td><form:input type="text" onkeypress="return check(event)" path="companyDto.division" id="division" data-maxsize="35" size="16"/></td>
    </tr>
     <tr>
								
								<td align="left">Commissionerate</td>
								<td><form:input type="text" path="companyDto.commissionerate"  data-maxsize="35"
										size="18" id="division" /></td>
										
										<td>Insurance Policy No</td>
								<td><form:input type="text" path="companyDto.insurancePolicyNo"  data-maxsize="35"
										size="18" id="insurancePolicyNo" /></td>
							</tr>
    <tr>
      <td>Gen. Remark</td>
      <td colspan="5"><form:textarea onkeypress="return check(event)" data-maxsize="35" path="companyDto.generalRemark" id="genRemark" style="width:96%;" cols="64" /></td>
      </tr>
  </table>
    <div class="btn">
   <div class="savbtn">
     <input id="myButton" class="submit"  type="submit" value=""/>
   </div>
   <div >
     <a href="show_company_form" class="cancelbtn" iconCls="icon-cancel"></a></td>
   </div>
   
    </div>
     <div ><span style="margin-left:12px;" class="errmsg"></span></div> 
  </div>
</form:form>
  
 

