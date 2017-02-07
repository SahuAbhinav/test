 <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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

 <c:if test="${opr=='R'}">
 <script type="text/javascript">
		var redrctUrl='get_zone_list';
				
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
 		 var frank_param = getParam('zoneId');
 		 //	confirm('Are you sure you want to delete?');
		 var delUrl='remove_zone?zoneId='+frank_param;
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
 	 var frank_param = $('#zoneId').val();
 	 //	confirm('Are you sure you want to delete?');
	 var delUrl='get_zone?zoneId='+frank_param+'&opr=E';
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
      $(document).ready(function()
       {
      
	    $(".datepicker" ).datepicker();
  //      $(":submit").button()
      });
  </script>
     

<c:if test="${not empty(errorList)}">
	<div class="errorblock">
		<ul>
			<c:forEach items="${errorList.errorList}" var="err">
				<li>${err.errorMsg}</li>
			</c:forEach>
		</ul>
	</div>
</c:if>
<c:if test="${opr=='E' || opr=='V'}">

<form:form name="input" id="formID" action="update_zone" method="post" modelAttribute="zoneForm">


<div class="panel-header">
	<div class="panel-title">Zone Master</div>
	<div class="panel-tool"></div>
  </div>
  
  <div align="left" class="bkgColor"> 
 <!-- <div class="btn">
   <div class="savbtn">
     <input class="submit" style="font-size: 11px; font-weight: bold;  padding: 0 0 0 30px;" type="submit" value="Save"/>
   </div>
   <div class="cancelbtn">
     <input  style="font-size: 11px; font-weight: bold; background:none; border:0px;  padding: 5 0 0 0px;" 
     type="reset" value="Cancel"/>
   </div>
    </div>-->
   <table  height="106" width="850" border="0"  >
    <tr>
	<form:hidden path="zoneDTO.zoneId" id="zoneId" />
      <td width="113" height="35">Zone Name<span style="color: #FF0000">*</span></td>
      <td colspan="1">
	  <form:input type="text" onkeypress="return check(event)"  data-maxsize="55"
					class="validate[required] text-input" style="width: 246px;"
					path="zoneDTO.zoneName" size="45" id="zoneName" ></form:input>
	  <!--<input type="text" data-maxsize="65" class="validate[required] text-input" name="zoneDTO.zoneName" 
      style="width:250px;" id="zoneName" size="20"/>--></td>
      <td width="64" align="left">Zone Code <span style="color: #FF0000">*</span></td>
      <td width="304">
	  <form:input type="text" onkeyup="valid1(this)" onblur="valid1(this)" readonly="true" data-maxsize="16"
					class="validate[required] text-input" style="width: 87px;"
					path="zoneDTO.zoneCode" size="45" id="zoneCode" ></form:input>
	 <!-- <input type="text" name="zoneDTO.zoneCode"  class="validate[required] text-input" data-maxsize="16" id="itemCode" 
      size="16" />--></td>
    </tr><tr>
	 <td width="113" height="35">Country Name<span style="color: #FF0000">*</span></td>
      <td width="177">
	  
     
     <form:select
						path="zoneDTO.countryDTO.countryId" items="${countryList}"
						itemLabel="countryName" itemValue="countryId" id="countryId"
						class="validate[required]"
						style="width: 250px; height: 20px;">

		  </form:select>
     </td>
   </tr>
   
    <tr>
					<td width="101" height="26" align="left"><label>
							Description&nbsp;</label>
					</td>
					<td height="26" colspan="3"><form:input onkeypress="return check(event)"
							path="zoneDTO.description" style="width:64%;"  size="85" id="description" />
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
    	      <a href="get_zone_list"  class="cancelbtn" ></a>  
		     </c:if>
		      <c:if test="${roleAndRights.editFlag=='false'}">
		      <input class="edit_btn"  type="button" onclick="checkEdit();" value=""/>
    	      <a href="get_zone_list"  class="cancelbtn" ></a>  
		      </c:if>
		    
          </c:if>
          </c:forEach>	
   				
    	</c:if>
            <c:if test="${opr=='E'}">
               <input class="updatebtn"  type="submit" value=""/> 
                       <a href="get_zone_list"  class="cancelbtn" ></a>     	
   		   	</c:if>
    </div>
	</div>
	</div>

  
</form:form>
</c:if>

<c:if test="${opr=='R'}">
 
<form:form name="input" id="formID" action="remove_zone" method="get" modelAttribute="zoneForm">


<div class="panel-header">
	<div class="panel-title">Zone Master</div>
	<div class="panel-tool"></div>
  </div>
  
  <div align="left" class="bkgColor"> 

   <table  height="109" width="850" border="0" align="left" style="margin-left:20px; width:757px;">
    <tr>
	<form:hidden path="zoneDTO.zoneId" id="" />
      <td width="116" height="35">Zone Name<span style="color: #FF0000">*</span></td>
      <td colspan="1">
	  <form:input type="text" data-maxsize="55"
					class="validate[required] text-input" style="width: 69%;"
					path="zoneDTO.zoneName" size="45" readOnly="true" id="zoneName" ></form:input>
	  <!--<input type="text" data-maxsize="65" class="validate[required] text-input" name="zoneDTO.zoneName" 
      style="width:250px;" id="zoneName" size="20"/>--></td>
      <td width="72" align="left">Zone Code <span style="color: #FF0000">*</span></td>
      <td width="304">
	  <form:input type="text" data-maxsize="16"
					class="validate[required] text-input" style="width: 113px;" readOnly="true"
					path="zoneDTO.zoneCode" size="45" id="zoneCode" ></form:input>
	 <!-- <input type="text" name="zoneDTO.zoneCode"  class="validate[required] text-input" data-maxsize="16" id="itemCode" 
      size="16" />--></td>
    </tr><tr>
	 <td width="113" height="35">Country Name<span style="color: #FF0000">*</span></td>
      <td width="177">
	  
     
     <form:select
						path="zoneDTO.countryDTO.countryId" items="${countryList}"
						itemLabel="countryName" itemValue="countryId" id="countryId"
						class="validate[required]" disabled="true"
						style="width: 71%	; height: 20px;">

		  </form:select>
     </td>
   </tr>
   
  <tr>
					<td width="101" height="26" align="left"><label>
							Description&nbsp;</label>
					</td>
					<td height="26" colspan="3"><form:input
							readOnly="true" path="zoneDTO.description" size="75" id="description" />
					</td>
				</tr>
  </table>
   <div class="btn">
				<c:url value="remove_zone" var="remove_url">
					<c:param name="zoneId" value="${zoneForm.zoneDTO.zoneId}"></c:param>
				</c:url>
			</div>
   </div> 



  
</form:form>
</c:if>
  
   
