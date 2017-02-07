<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ page isELIgnored="false" %>


<c:if test="${not empty(errors)}">
 <input type="hidden" id="errorId" value="${errors.errorMsg}">
 <script type="text/javascript">
  		var delUrl='show_country';
 		$(document).ready(function() {
 		var errorId=document.getElementById('errorId');
		alert(errorId.value);
 		//alert('Duplicate Data In Branch');
 		window.self.location  = delUrl;
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
	    $( ".datepicker" ).datepicker({ dateFormat: 'dd-mm-yy' });
  //      $(":submit").button()
      });
  </script>
  <%--    
<c:if test="${not empty(errorList)}">
	<div class="errorblock">
		<ul>
			<c:forEach items="${errorList.errorList}" var="err">
				<li>${err.errorMsg}</li>
			</c:forEach>
		</ul>
	</div>
</c:if> --%>

<form:form id="formID" name="input" action="save_country" method="post"
	modelAttribute="countryForm">


	<div class="panel-header">
	<div class="panel-title">Country Master</div>
	<div class="panel-tool"></div>
  </div>


	<div align="left" class="bkgColor">

		<table class="" width="743" height="61" border="0" align="left">
		<tr>
         <td colspan="3" align="center" style="color: red; font:20px bold;" ><c:out value="${responseMessage}"/></td></tr>
          
			<tr>
				<td width="133" height="28" align="left"><label>Country Name <span style="color: #FF0000">*</span>
				</label>
				</td>
				<td width="269"><form:input onkeypress="return check(event)"   type="text" data-maxsize="55"
					class="validate[required] text-input" style="width: 250px;"
					path="countryDTO.countryName" size="45" id="countryName" ></form:input>
				</td>
				<td width="135" height="28"><label>Country Code <span
						style="color: #FF0000">*</span>
				</label>
				</td>
				<td width="188"><form:input type="text" data-maxsize="16" onkeyup="valid1(this)" onblur="valid1(this)"  
					class="validate[required] text-input" path="countryDTO.countryCode" size="22" maxlength="4"
					id="countryCode" />
				</td>
			</tr>
			<tr>
				<td height="27" width="133" align="left"><label>Description </label>
				</td>
				<td height="27" colspan="3">
				<form:input onkeypress="return check(event)" data-maxsize="150" type="text" path="countryDTO.description" style="width:88%" size="104"
					id="description" />
				
				</td>
			</tr>
		</table>


  <div class="btn">
				<div class="savbtn">
					<input class="submit"
						
						type="submit" value="" />
				</div>
				<div class="cancelbtn">
					<a href="get_country_list"
						class="cancelbtn" iconCls="icon-cancel"></a>
				</div>
			</div>
			</div>

</form:form>

