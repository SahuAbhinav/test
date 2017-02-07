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
<li>
${err.errorMsg}
</li>
</c:forEach>
</ul>
</div>
</c:if>


<form:form name="input" id="formID" action="save_zone" method="post" modelAttribute="zoneForm">


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
   <table  height="106" width="850" border="0"    >
    <tr>
      <td width="113" height="35">Zone Name<span style="color: #FF0000">*</span></td>
      <td colspan="1"><input onkeypress="return check(event)"  type="text" data-maxsize="55" class="validate[required] text-input" name="zoneDTO.zoneName" 
      style="width:246px;" id="zoneName" size="20"/></td>
      <td width="115 " align="left">Zone Code <span style="color: #FF0000">*</span></td>
      <td width="391"><input onkeyup="valid1(this)" onblur="valid1(this)" style="width: 27%;"  type="text" name="zoneDTO.zoneCode"  class="validate[required] text-input" data-maxsize="16" id="itemCode" 
      size="15" /></td>
    </tr><tr>
	 <td width="113" height="35">Country Name<span style="color: #FF0000">*</span></td>
      <td width="177">
	  
     
     <form:select
						path="zoneDTO.countryDTO.countryId" items="${countryList}"
						itemLabel="countryName" itemValue="countryId" id="countryId"
						class="validate[required]"
						style="width: 250px; height: 22px;">

		  </form:select>
     </td>
   </tr>
   
   <tr>
					<td width="101" height="26" align="left"><label>
							Description&nbsp;</label>
					</td>
					<td height="26" colspan="3"><form:input onkeypress="return check(event)"
							path="zoneDTO.description" style="width:64%;" size="85" id="description" />
					</td>
				</tr>
  </table>
    <div class="btn">
				<div class="savbtn">
					<input class="submit"
						 
						type="submit" value="" />
				</div>
				<div class="cancelbtn">
					<a href="get_zone_list"
						class="cancelbtn" iconCls="icon-cancel"></a>
				</div>
			</div>
   </div> 



  
</form:form>
  
   
