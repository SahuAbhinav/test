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

 <c:if test="${opr=='R'}">
 <script type="text/javascript">
		var redrctUrl='show_new_state_form';
				
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
 		 var frank_param = getParam('stateId');
 		 //	confirm('Are you sure you want to delete?');
		 var delUrl='remove_state?stateId='+frank_param;
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
 	
 	function editMethod()
 	 { 
 	 var frank_param = $('#stateId').val();
 	 //	confirm('Are you sure you want to delete?');
	 var delUrl='get_state?stateId='+frank_param+'&opr=E';
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
    //   $("button").button();
    	  //$('input:text, input:password').button()   
	    $(".datepicker" ).datepicker();
  //      $(":submit").button()
      });
  </script>
    
       
<c:if test="${opr=='E' || opr=='V'}">
 <form:form name="input" id="formID" action="update_state" method="post"  modelAttribute="stateForm">
   
  <div class="panel-header">
	<div class="panel-title">State Master</div>
	<div class="panel-tool"></div>
  </div>
  
  <div align="left" class="bkgColor"> 
   <table class="" width="743" height="102"  border="0" align="left">
    <form:hidden path="stateDTO.stateId" id="stateId"/>
     <tr>
       <td width="101" height="30" align="left"><label>   State Name<span style="color: #FF0000">*</span></label></td>
       <td width="231"><form:input type="text" data-maxsize="55" class="validate[required] text-input" onkeypress="return check(event)" style="width:246px;" 
       path="stateDTO.stateName"  size="45" id="stateName" /></td>
       <td width="98" height="30"><label>  Code<span style="color: #FF0000">*</span></label></td>
       <td width="215"><form:input style="width:42%;" readonly="true" type="text" data-maxsize="16" class="validate[required] text-input" path="stateDTO.stateCode" size="22" 
       id="code" /></td>
     </tr>
     <tr>
       <td height="31" width="101" align="left"><label>Zone<span style="color: #FF0000">*</span></label></td>
       <td height="31" colspan="3">
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
       <td height="26" colspan="3"><form:input type="text" style="width:78%;" onkeypress="return check(event)" path="stateDTO.description"  size="104" id="description" /></td>
      </tr>
   </table>
    <div class="savbtn">
         <c:if test="${opr=='V'}">
		<c:forEach items="${roleAndRightsDTOList}" var="roleAndRights">
		   <c:if test="${roleAndRights.menuId==sessionScope.menuId}">
    	  	 <c:if test="${roleAndRights.editFlag=='true'}">
		  	 <input class="edit_btn"  type="button" onclick="editMethod();" value=""/>
    	      <a href="show_new_state_form"  class="cancelbtn" ></a>  
		     </c:if>
		      <c:if test="${roleAndRights.editFlag=='false'}">
		      <input class="edit_btn"  type="button" onclick="checkEdit();" value=""/>
    	      <a href="show_new_state_form"  class="cancelbtn" ></a>  
		      </c:if>
		    
          </c:if>
          </c:forEach>	
   				
    	</c:if>
            <c:if test="${opr=='E'}">
               <input class="updatebtn"  type="submit" value=""/> 
                       <a href="show_new_state_form"  class="cancelbtn" ></a>     	
   		   	</c:if>
    </div>
	</div>
	</div>
  </form:form>
 </c:if>
  <c:if test="${opr=='R'}">
 
  <form:form name="input" id="formID" action="" method="post"  modelAttribute="stateForm">
   
  <div class="panel-header">
	<div class="panel-title">State Master</div>
	<div class="panel-tool"></div>
  </div>
  
  <div align="left" class="bkgColor"> 
  
   <table class="" width="743" height="102"  border="0" align="left">
    <form:hidden path="stateDTO.stateId" id="stateId"/>
     <tr>
       <td width="101" height="30" align="left"><label>   State Name<span style="color: #FF0000">*</span></label></td>
       <td width="231"><form:input type="text" data-maxsize="55" readonly="true" class="validate[required] text-input" style="width:250px;" 
       path="stateDTO.stateName"  size="45" id="stateName" /></td>
       <td width="81" height="30"><label>  Code<span style="color: #FF0000">*</span></label></td>
       <td width="251"><form:input type="text" data-maxsize="16" readonly="true" class="validate[required] text-input" path="stateDTO.stateCode" size="22" 
       id="code" /></td>
     </tr>
     <tr>
       <td height="31" width="101" align="left"><label>Zone<span style="color: #FF0000">*</span></label></td>
       <td height="31" colspan="3">
	      <form:select
						path="stateDTO.zoneDTO.zoneId" items="${zoneList}" disabled="true"
						itemLabel="zoneName" itemValue="zoneId" id="zoneId"
						class="validate[required]" style="width: 250px; height: 20px;">
					</form:select>
       </td>
      </tr>
      <tr>
       <td width="101" height="26" align="left"><label> 
        Description&nbsp;</label></td>
       <td height="26" colspan="3"><form:input type="text" readonly="true" path="stateDTO.description"  size="103" id="description" /></td>
      </tr>
   </table>
   <div class="btn">
				<c:url value="remove_state" var="remove_url">
					<c:param name="stateId" value="${stateForm.stateDTO.stateId}"></c:param>
				</c:url>
				<div class="btn">
				</div>
   </div>
 
 
</form:form>
</div>
	
	</c:if>


