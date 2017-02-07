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

<script type="text/javascript">
 		$(document).ready(function() {
 			$('#ptaxCode').attr('readonly', true);
 		//document.getElementById('ptaxCode').readOnly=true;
 		});
 	</script>
 	
 	<c:if test="${opr=='R'}">
 	<script type="text/javascript">
 	
		var redrctUrl='show_professionalTax_form';
		
		$(document).ready(function() {
			
 		 var frank_param = getParam('ptaxId');
 		 //	confirm('Are you sure you want to delete?');
		 var delUrl='delete_professionalTax?ptaxId='+frank_param;
 		 if(confirm('Are you sure you want to delete?')) 
 		   {
 			window.self.location = delUrl;
 		 	}
		 else{
			 window.self.location = redrctUrl;
  			}
		});
		
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
    
    function checkEdit()
		{
		alert('Login User Not Permit to Edit Record !!!!!!');
		}
	//	$(document).ready(function() {
 	function editMethod()
 	 { 
 	 var frank_param = $('#ptaxId').val();
 	 //	confirm('Are you sure you want to delete?');
	 var delUrl='get_professionalTax?ptaxId='+frank_param+'&opr=E';
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
 <c:if test="${opr=='E'}">
<script>
$(document).ready(function() {
	$('#ptaxtCode').attr('disabled','disabled');	
});
</script>
</c:if>

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
     //   $("button").button();
      //$('input:text, input:password').button()   
	    $(".datepicker" ).datepicker();
     //   $(":submit").button()
      });
  </script>

  <style>
	 
	input{
	width:24%;
	}
	table{
	width: 823px;
	}
		 
	</style>

<c:if test="${opr=='E' || opr=='V'}">

<form:form id="formID" name="formID" action="update_professionalTax" method="post" modelAttribute="professionalTaxForm">  
   
  <div class="panel-header">
	<div class="panel-title">Professional Tax  </div>
	<div class="panel-tool"></div>
  </div>
  
  <div align="left" class="bkgColor"> 
  <table width="500" height="113" style="margin-left:10px;" border="0" align="left" >
  <form:hidden path="professionalTaxDto.ptaxId" id="ptaxId"/>
         <tbody>
           <tr> <td width="40">Slab Name<span style="color: #FF0000">*</span></td> 
             <td width="50" colspan="1"><form:input type="text"  data-maxsize="65" style="width:100px"  onkeypress="return check(event)" class="validate[required] text-input" 
              path="professionalTaxDto.slabName" id="" size="72" /></td>
             <td width="52" align="left">PTax Code<span style="color: #FF0000">*</span></td>
             <td width="225"><form:input type="text"  onkeyup="valid1(this)" data-maxsize="16" onblur="valid1(this)" class="validate[required] text-input" 
              path="professionalTaxDto.ptaxCode" id="ptaxCode" size="18" /></td>
           </tr>
     
     	  <tr> <td>From Amount</td>
             <td colspan="1">
             <form:input type="text" style="width:100px"  path="professionalTaxDto.fromAmount"  class="quantity validate[custom[number]]" data-maxsize="15"  id="" size="72" /></td>
             <td align="left">To Amount</td>
             <td><form:input type="text" path="professionalTaxDto.toAmount" class="quantity validate[custom[number]]" data-maxsize="15"  id="city" size="18" /></td>
          </tr>
     
          <tr>
            <td>Deduct Type</td>
            <td><form:select
						path="professionalTaxDto.professionalTaxDeductTypeDto.ptaxDeductTypeId" items="${ptaxDeductTypes}"
						itemLabel="ptaxDeductType" style="width: 52%;" itemValue="ptaxDeductTypeId" id="ptaxDeductTypeId"
						class="validate[required]">								</form:select></td>
            <td>Deduct Amount</td>
            <td><form:input type="text" path="professionalTaxDto.deductAmount" data-maxsize="10" class="quantity validate[custom[number]]" size="18" id="country" /></td>
            
         </tbody>
     </table>
   <div class="savbtn">
         <c:if test="${opr=='V'}">
         <c:forEach items="${roleAndRightsDTOList}" var="roleAndRights">
		   <c:if test="${roleAndRights.menuId==sessionScope.menuId}">
    	  	 <c:if test="${roleAndRights.editFlag=='true'}">
		  	 <input class="edit_btn"  type="button" onclick="editMethod();" value=""/>
    	      <a href="show_professionalTax_form"  class="cancelbtn" ></a>  
		     </c:if>
		      <c:if test="${roleAndRights.editFlag=='false'}">
		      <input class="edit_btn"  type="button" onclick="checkEdit();" value=""/>
    	      <a href="show_professionalTax_form"  class="cancelbtn" ></a>  
		      </c:if>
		    
          </c:if>
          </c:forEach>	
   		    		
    	</c:if>
            <c:if test="${opr=='E'}">
               <input class="updatebtn"  type="submit" value=""/> 
                       <a href="show_professionalTax_form"  class="cancelbtn" ></a>     	
   		   	</c:if>
    </div>
	</div>
	</div>

  </form:form>
  </c:if>
  
  <c:if test="${opr=='R'}">
  

<form:form id="formID" name="formID" action="update_professionalTax" method="post" modelAttribute="professionalTaxForm">  
   
  <div class="panel-header">
	<div class="panel-title">Professional Tax Detail</div>
	<div class="panel-tool"></div>
  </div>
  
  <div align="left" class="bkgColor"> 
  <table width="500" height="113" style="margin-left:10px;" border="0" align="left" >
  <form:hidden path="professionalTaxDto.ptaxId" id="ptaxId"/>
         <tbody>
           <tr> <td width="52">Slab Name<span style="color: #FF0000">*</span></td>
             <td colspan="1"><form:input type="text" style="width:100px;" class="validate[required] text-input" 
              path="professionalTaxDto.slabName" id="" size="72" /></td>
             <td width="52" align="left">PTax Code<span style="color: #FF0000">*</span></td>
             <td width="225"><form:input type="text" class="validate[required] text-input" 
              path="professionalTaxDto.ptaxCode" id="ptaxtCode" size="18" /></td>
           </tr>
     
     	  <tr> <td>From Amount</td>
             <td colspan="1">
             <form:input type="text"  path="professionalTaxDto.fromAmount" id="" size="72" /></td>
             <td align="left">To Amount</td>
             <td><form:input type="text" path="professionalTaxDto.toAmount" id="city" size="18" /></td>
          </tr>
     
          <tr>
            <td>Deduct Type</td>
            <td><form:select
						path="professionalTaxDto.professionalTaxDeductTypeDto.ptaxDeductTypeId" items="${ptaxDeductTypes}"
						itemLabel="ptaxDeductType" style="width:52%;" itemValue="ptaxDeductTypeId" id="ptaxDeductTypeId"
						class="validate[required]"						 >
					</form:select></td>
            <td>Deduct Amount</td>
            <td><form:input type="text" path="professionalTaxDto.deductAmount" size="18" id="country" /></td>
            
         </tbody>
     </table>
    <c:url value="delete_professionalTax" var="remove_url">
			<c:param name="ptaxId" value="${professionalTaxForm.professionalTaxDto.ptaxId}"></c:param>
		</c:url>
		<div class="btn" style="width: 157px;">
		<%-- 	<a href="${remove_url}" class="removebtn" iconCls="icon-remove"></a> 
			<a href="show_professionalTax_form"  class="cancelbtn" iconCls="icon-cancel"></a>  --%>
		</div>
	</div>	
  </form:form>
  </c:if>