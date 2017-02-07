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
	
	<style>
	input{
	width:24%;
	 
	}
	table{
	width: 800px;
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
      //  $("button").button();
      //$('input:text, input:password').button()   
	    $(".datepicker" ).datepicker();
        //$(":submit").button()
      });
  </script>

     
<%-- 

<c:if test="${not empty(errorList)}">
<div class="errorblock">
<ul>
<c:forEach items="${errorList.errorList}" var="err">
<li>
${err.errorMsg}
</li>
</c:forEach>
</ul>
</div>
</c:if>
 --%>
<form:form id="formID" name="formID" action="save_professionalTax" method="post" modelAttribute="professionalTaxForm">  
   
  <div class="panel-header">
	<div class="panel-title">Professional Tax Master</div>
	<div class="panel-tool"></div>
  </div>
  
  <div align="left" class="bkgColor" style="height:auto;"> 
  <table width="500" height="113" style="margin-left:10px;" border="0" align="left" >
  <form:hidden path="professionalTaxDto.ptaxId" id="ptaxId"/>
         <tbody>
           <tr> <td width="40">Slab Name<span style="color: #FF0000">*</span></td>
             <td width="50" colspan="1"><form:input onkeypress="return check(event)" data-maxsize="65" type="text" style="width:100px;" class="validate[required] text-input" 
              path="professionalTaxDto.slabName" id="" size="72" /></td>
             <td width="49" align="left">PTax Code<span style="color: #FF0000">*</span></td>
             <td width="225"><form:input onkeyup="valid1(this)" data-maxsize="16" onblur="valid1(this)" type="text" class="validate[required] text-input" 
              path="professionalTaxDto.ptaxCode" id="" size="18" /></td>
           </tr>
     
     
     	  <tr> <td>From Amount</td>
             <td colspan="1">
             <form:input class="quantity validate[custom[number]]" data-maxsize="15"  type="text" style="width:100px;" path="professionalTaxDto.fromAmount" id="" size="72" /></td>
             <td align="left">To Amount</td>
             <td><form:input  type="text" class="quantity validate[custom[number]]" data-maxsize="15" style="text-align:right" path="professionalTaxDto.toAmount" id="city" size="18" /></td>
          </tr>
     
          <tr>
            <td>Deduct Type</td>
            <td><form:select
						path="professionalTaxDto.professionalTaxDeductTypeDto.ptaxDeductTypeId" items="${ptaxDeductTypes}"
						itemLabel="ptaxDeductType" itemValue="ptaxDeductTypeId" id="ptaxDeductTypeId"
						class="validate[required]"
						style="width: 53%; height: 20px;">
					</form:select></td>
            <td>Deduct Amount</td>
            <td><form:input class="quantity validate[custom[number]]" style="text-align:right" data-maxsize="15" type="text" path="professionalTaxDto.deductAmount" size="18" id="country" /></td>
            
         </tbody>
     </table>
    <div class="btn" style="  width: 153px;">
   <div class="savbtn">
     <input class="submit"  type="submit" value=""/>
   </div>
   <div >
        <a href="show_professionalTax_form"   class="cancelbtn" iconCls="icon-cancel"></a>
   </div>
    </div>  
         <div ><span style="margin-left:12px; float: left;" class="errmsg"></span></div>   
   </div>
  </form:form>
   