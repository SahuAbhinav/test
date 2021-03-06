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
       $("button").button();
    	  //$('input:text, input:password').button()   
	    $(".datepicker" ).datepicker();
  //      $(":submit").button()
      });
  </script>
     

<%-- <c:if test="${not empty(errorList)}">
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
 <form:form name="input" id="formID" action="save_state" method="post"  modelAttribute="stateForm">
   
  <div class="panel-header">
	<div class="panel-title">State Master</div>
	<div class="panel-tool"></div>
  </div>
  
  <div align="left" class="bkgColor"> 
   <table class="" width="743" height="106"  border="0" align="left">
     <tr>
       <td width="101" height="30" align="left"><label>   State Name<span style="color: #FF0000">*</span></label></td>
       <td width="392"><form:input  type="text" onkeypress="return check(event)"  data-maxsize="55" class="validate[required] text-input" style="width:246px;" 
       path="stateDTO.stateName"  size="45" id="stateName" /></td>
       <td width="89" height="30"><label>  Code<span style="color: #FF0000">*</span></label></td>
       <td width="340"><form:input style="width:42%;" type="text" onkeyup="valid1(this)" onblur="valid1(this)"  data-maxsize="16" class="validate[required] text-input" path="stateDTO.stateCode" size="22" 
       id="code" /></td>
     </tr>
     <tr>
       <td height="31" width="101" align="left"><label>Zone<span style="color: #FF0000">*</span></label></td>
       <td height="31" colspan="1">
	      <form:select
						path="stateDTO.zoneDTO.zoneId" items="${zoneList}"
						itemLabel="zoneName" itemValue="zoneId" id="zoneId"
						class="validate[required]"
						style="width: 250px; height: 22px;">

					</form:select>
       </td>
      </tr>
      <tr>
       <td width="101" height="26" align="left"><label> 
        Description&nbsp;</label></td>
       <td height="26" colspan="3"><form:input style="width:78%;" onkeypress="return check(event)" data-maxsize="225" type="text" path="stateDTO.description"  size="103" id="description" /></td>
      </tr>
   </table>
    <div class="btn">
	  <div class="savbtn">
		<input class="submit" type="submit" value="" />
	  </div>
	<div class="cancelbtn">
	 <a href="show_new_state_form" class="cancelbtn" iconCls="icon-cancel"></a>
	</div>
	</div>
    </div>
   </div>
 
 
</form:form>



