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
		var redrctUrl='get_country_list';
				
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
 		 var frank_param = getParam('countryId');
 		 //	confirm('Are you sure you want to delete?');
		 var delUrl='remove_country?countryId='+frank_param;
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
 	 var frank_param = $('#countryId').val();
 	 //	confirm('Are you sure you want to delete?');
	 var delUrl='get_country?countryId='+frank_param+'&opr=E';
 	 window.self.location = delUrl;  
	}
 	</script>	
 <c:if test="${opr=='V' }">
<script>
$(document).ready(function() {
	$('input').attr('readonly','readonly');	
	 
});
</script>
</c:if>	
 
 
 
 
   <script>
	$(document).ready(function() {
		$(".cancelbtn").click(function() {
			window.self.location = "get_country_list";
		});

	});
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
     


<c:if test="${opr=='E' || opr=='V'}">


<form:form id="formID" name="input" action="update_country" method="post"
	modelAttribute="countryForm">


	<div class="panel-header">
	<div class="panel-title">Country</div>
	<div class="panel-tool"></div>
  </div>


	<div align="left" class="bkgColor">

		<table class="" width="743" height="61" border="0" align="left">
		<form:hidden path="countryDTO.countryId" id="countryId"/>	
          
			<tr>
				<td width="133" height="28" align="left"><label>Country Name <span style="color: #FF0000">*</span>
				</label>
				</td>
				<td width="269"><form:input onkeypress="return check(event)"  type="text" data-maxsize="55"
					class="validate[required] text-input" style="width: 250px;"
					path="countryDTO.countryName" size="45" id="countryName" ></form:input>
				</td>
				<td width="135" height="28"><label>Country Code <span
						style="color: #FF0000">*</span>
				</label>
				</td>
				<td width="188"><form:input onkeyup="valid1(this)" onblur="valid1(this)" readonly="true" type="text" data-maxsize="16"
					class="validate[required] text-input" path="countryDTO.countryCode" size="22" maxlength="4"
					id="countryCode"/>
				</td>
			</tr>
			<tr>
				<td height="27" width="133" align="left"><label>Description </label>
				</td>
				<td height="27" colspan="3"> 
				<form:input type="text" onkeypress="return check(event)" data-maxsize="150" path="countryDTO.description" size="114"
					id="description" />
				
				</td>
			</tr>
		</table>



		<div class="btn">
         <div class="savbtn">
         <c:if test="${opr=='V'}">
         <c:forEach items="${roleAndRightsDTOList}" var="roleAndRights">
		   <c:if test="${roleAndRights.menuId==sessionScope.menuId}">
    	  	 <c:if test="${roleAndRights.editFlag=='true'}">
		  	 <input class="edit_btn"  type="button" onclick="editMethod();" value=""/>
    	      <a href="get_country_list"  class="cancelbtn" ></a>  
		     </c:if>
		      <c:if test="${roleAndRights.editFlag=='false'}">
		      <input class="edit_btn"  type="button" onclick="checkEdit();" value=""/>
    	      <a href="get_country_list"  class="cancelbtn" ></a>  
		      </c:if>
		    
          </c:if>
          </c:forEach>	
   		 		
    	</c:if>
            <c:if test="${opr=='E'}">
               <input class="updatebtn"  type="submit" value=""/> 
                       <a href="get_country_list"  class="cancelbtn" ></a>     	
   		   	</c:if>
    </div>
	</div>
	</div>
</form:form>
</c:if>

<c:if test="${opr=='R'}">

 
<form:form name="input" id="formID" action="" method="post"
		modelAttribute="countryForm">


	<div class="panel-header">
	<div class="panel-title">Country</div>
	<div class="panel-tool"></div>
  </div>


	<div align="left" class="bkgColor">

		<table class="" width="743" height="61" border="0" align="left">
		<form:hidden path="countryDTO.countryId" id="countryId"/>	
          
			<tr>
				<td width="137" height="28" align="left"><label>Country Name <span style="color: #FF0000">*</span>
				</label>
				</td>
				<td width="269"><form:input readonly="true" type="text" data-maxsize="55"
					class="validate[required] text-input" style="width: 250px;"
					path="countryDTO.countryName" size="45" id="countryName" ></form:input>
				</td>
				<td width="135" height="28"><label>Country Code <span
						style="color: #FF0000">*</span>
				</label>
				</td>
				<td width="188"><form:input readonly="true" type="text" data-maxsize="16"
					class="validate[required] text-input" path="countryDTO.countryCode" size="22" maxlength="4"
					id="countryCode"/>
				</td>
			</tr>
			<tr>
				<td height="27" width="133" align="left"><label>Description </label>
				</td>
				<td height="27" colspan="3">
				<form:input readonly="true" type="text" path="countryDTO.description" size="114"
					id="description" />
				
				</td>
			</tr>
		</table>



		<c:url value="remove_country" var="remove_url">
					<c:param name="countryId" value="${countryForm.countryDTO.countryId}"></c:param>
				</c:url>
				
			
			<div class="btn">
			<%-- <div class="savbtn">
			<a href="${remove_url}" class="removebtn" ></a> 
				
			</div>
			<div>
				<input
					 class="cancelbtn"
					type="reset" value=" " />
			</div> --%>
						    
		</div>
	</div>

</form:form>
</c:if>
