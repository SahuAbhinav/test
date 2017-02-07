<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page isELIgnored="false"%>

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
		var redrctUrl='get_region_list';
				
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
 		 var frank_param = getParam('regionId');
 		 //	confirm('Are you sure you want to delete?');
		 var delUrl='remove_region?regionId='+frank_param;
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
 	 var frank_param = $('#regionId').val();
 	 //	confirm('Are you sure you want to delete?');
	 var delUrl='get_region?regionId='+frank_param+'&opr=E';
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
	$(document).ready(function() {
		$(".cancelbtn").click(function() {
			window.self.location = "get_region_list";
		});

	});
</script>

<script>
	jQuery(document).ready(function() {
		// binds form submission and fields to the validation engine
		jQuery("#formID").validationEngine();
	});

	function checkHELLO(field, rules, i, options) {
		if (field.val() != "HELLO") {
			// this allows to use i18 for the error msgs
			return options.allrules.validate2fields.alertText;
		}
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
	<form:form name="input" id="formID" action="update_region"
		method="post" modelAttribute="regionForm">
		<form:hidden path="regionDTO.regionId" id="regionId" />
		<div class="panel-header">
			<div class="panel-title"> Region</div>
			<div class="panel-tool"></div>
		</div>

		<div align="left" class="bkgColor">
			<table class="" width="743" height="102" border="0" align="left">
				<tr>
					<td width="101" height="30" align="left"><label>
							Region Name<span style="color: #FF0000">*</span>
					</label>
					</td>
					<td width="270"><form:input onkeypress="return check(event)"   data-maxsize="55"
							class="validate[required] text-input" style="width:246px;"
							path="regionDTO.regionName" size="45" id="regionName" />
					</td>
					<td width="79" height="30"><label> Code<span
							style="color: #FF0000">*</span>
					</label>
					</td>
					<td width="334"><form:input data-maxsize="16" onkeyup="valid1(this)" onblur="valid1(this)"  
							class="validate[required] text-input" path="regionDTO.regionCode"
							size="22" style="width:42%" id="code" readonly="true" />
					</td>
				</tr>
				<tr>
					<td height="31" width="101" align="left"><label>State<span
							style="color: #FF0000">*</span>
					</label>
					</td>
					<td height="31" colspan="3"><form:select id="state"
							class="validate[required] text-input"
							style="width:250px; height:20px;"    
							path="regionDTO.stateDTO.stateId" items="${states}"
							itemLabel="stateName" itemValue="stateId"></form:select></td>
				</tr>
				<tr>
					<td width="101" height="26" align="left"><label>
							Description&nbsp;</label>
					</td>
					 <td height="26" colspan="3"><form:textarea onkeypress="return check(event)"  
							path="regionDTO.description" cols="104" style="width:72%" id="description" />
					</td>
				</tr>
			</table>
			<div class="savbtn">
         <c:if test="${opr=='V'}">
   		
   		<c:forEach items="${roleAndRightsDTOList}" var="roleAndRights">
		   <c:if test="${roleAndRights.menuId==sessionScope.menuId}">
    	  	 <c:if test="${roleAndRights.editFlag=='true'}">
		  	 <input class="edit_btn"  type="button" onclick="editMethod();" value=""/>
    	      <a href="get_region_list"  class="cancelbtn" ></a>  
		     </c:if>
		      <c:if test="${roleAndRights.editFlag=='false'}">
		      <input class="edit_btn"  type="button" onclick="checkEdit();" value=""/>
    	      <a href="get_region_list"  class="cancelbtn" ></a>  
		      </c:if>
		    
          </c:if>
          </c:forEach>	
   		</c:if>
            <c:if test="${opr=='E'}">
               <input class="updatebtn"  type="submit" value=""/> 
                       <a href="get_region_list"  class="cancelbtn" ></a>     	
   		   	</c:if>
    </div>
	</div>
	</div>



	</form:form>
</c:if>
<c:if test="${opr=='R'}">
	<div class="errorblock">
		<ul>
			<li>Remove Confirmation</li>
		</ul>
	</div>
	<form:form name="input" id="formID" action="" method="post"
		modelAttribute="regionForm">
		<form:hidden path="regionDTO.regionId" />
		<div class="panel-header">
			<div class="panel-title">Remove Region</div>
			<div class="panel-tool"></div>
		</div>

		<div align="left" class="bkgColor">
			<table class="" width="743" height="102" border="0" align="left">
				<tr>
					<td width="74" height="30" align="left"><label>
							Region Name<span style="color: #FF0000">*</span>
					</label>
					</td>
					<td width="212"><form:input data-maxsize="55"
							class="validate[required] text-input" style="width:246px;"
							path="regionDTO.regionName" size="45" id="regionName" />
					</td>
					<td width="89" height="30"><label> Code<span
							style="color: #FF0000">*</span>
					</label>
					</td>
					<td width="277"><form:input data-maxsize="16"
							class="validate[required] text-input" path="regionDTO.regionCode"
							size="22" id="code" />
					</td>
				</tr>
				<tr>
					<td height="31" width="101" align="left"><label>State<span
							style="color: #FF0000">*</span>
					</label>
					</td>
					<td height="31" colspan="3"><form:select id="state"
							class="validate[required] text-input"
							style="width:250px; height:20px;"
							path="regionDTO.stateDTO.stateId" items="${states}"
							itemLabel="stateName" itemValue="stateId"></form:select></td>
				</tr>
				<tr>
					<td width="101" height="26" align="left"><label>
							Description&nbsp;</label>
					</td>
					<td height="26" colspan="3"><form:textarea
							path="regionDTO.description" cols="72" id="description" />
					</td>
				</tr>
			</table>
			<div class="btn">
				<c:url value="remove_region" var="remove_url">
					<c:param name="regionId" value="${regionForm.regionDTO.regionId}"></c:param>
				</c:url>				
			<div class="savbtn">
					<a href="${remove_url}" class="removebtn" ></a> 				
			</div>
			<div>
			<a href="get_region_list" class="cancelbtn"></a>
			</div>
			</div>

		</div>



	</form:form>
</c:if>
