<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page isELIgnored="false"%>
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
		var redrctUrl='get_area_list';
				
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
 		 var frank_param = getParam('areaId');
 		 //	confirm('Are you sure you want to delete?');
		 var delUrl='remove_area?areaId='+frank_param;
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
 	 var frank_param = $('#areaId').val();
 	 //	confirm('Are you sure you want to delete?');
	 var delUrl='get_area?areaId='+frank_param+'&opr=E';
 	 window.self.location = delUrl;  
	}
 	</script>

<script>
	$(document).ready(function() {
		$(".cancelbtn").click(function() {
			window.self.location = "get_area_list";
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

<script type="text/javascript">
	$(document).ready(function() {

		//called when key is pressed in textbox
		$("#quantity").keypress(function(e) {
			//if the letter is not digit then display error and don't type anything
			if (e.which != 8 && e.which != 0 && (e.which<48 || e.which>57)) {
				//display error message
				$("#errmsg").html("Digits Only").show().fadeOut("slow");
				return false;
			}
		});

	});
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

<c:if test="${opr=='V' }">
<script>
$(document).ready(function() {
	$('input').attr('readonly','readonly');	
	$('select').attr('disabled','disabled');
	$('textarea').attr('readonly','readonly');
});
</script>
</c:if>



<c:if test="${opr=='E' || opr=='V'}">
	<form:form name="input" id="formID" action="update_area"
		method="post" modelAttribute="areaForm">
		<form:hidden path="areaDTO.areaId" id="areaId" />
		<div class="panel-header">
			<div class="panel-title">Area</div>
			<div class="panel-tool"></div>
		</div>

		<div align="left" class="bkgColor">
			<table class="" width="743" height="102" border="0" align="left">
				<tr>
					<td width="101" height="30" align="left"><label>
							Area Name<span style="color: #FF0000">*</span>
					</label>
					</td>
					<td width="232"><form:input data-maxsize="55" onkeypress="return check(event)"  
							class="validate[required] text-input" style="width:246px;"
							path="areaDTO.areaName" size="45" id="areaName" />
					</td>
					<td width="50" height="30"><label> Code<span
							style="color: #FF0000">*</span>
					</label>
					</td>
					<td width="215"><form:input data-maxsize="16" onkeyup="valid1(this)" onblur="valid1(this)"  
							class="validate[required] text-input" path="areaDTO.areaCode"
							size="22" id="code" style="width:50%" readonly="true" />
					</td>
				</tr>
				<tr>
					<td height="31" width="101" align="left"><label>Region<span
							style="color: #FF0000">*</span>
					</label>
					</td>
					<td height="31" colspan="3"><form:select id="region"
							class="validate[required] text-input"
							style="width:250px; height:20px;"
							path="areaDTO.regionDTO.regionId" items="${regiones}"
							itemLabel="regionName" itemValue="regionId"></form:select></td>
				</tr>
				<tr>
					<td width="101" height="26" align="left"><label>
							Description&nbsp;</label>
					</td>
					<td height="26" colspan="3"><form:textarea onkeypress="return check(event)" data-maxsize="150"
							path="areaDTO.description" cols="104" style="width:79%" id="description" />
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
    	      <a href="show_company_form"  class="cancelbtn" ></a>  
		     </c:if>
		      <c:if test="${roleAndRights.editFlag=='false'}">
		      <input class="edit_btn"  type="button" onclick="checkEdit();" value=""/>
    	      <a href="show_company_form"  class="cancelbtn" ></a>  
		      </c:if>
		    
          </c:if>
          </c:forEach>	   		
    	</c:if>
    	
            <c:if test="${opr=='E'}">
               <input class="updatebtn"  type="submit" value=""/> 
                        <input class="cancelbtn" type="reset" value=" " />  
   		   	</c:if>
    </div>
	</div>
	</form:form>
</c:if>

<c:if test="${opr=='R'}">
	 
	<form:form name="input" id="formID" action="" method="post"
		modelAttribute="areaForm">
		<form:hidden path="areaDTO.areaId" />
		<div class="panel-header">
			<div class="panel-title">Area</div>
			<div class="panel-tool"></div>
		</div>

		<div align="left" class="bkgColor">
			<table class="" width="743" height="102" border="0" align="left">
				<tr>
					<td width="101" height="30" align="left"><label>
							Area Name<span style="color: #FF0000">*</span>
					</label>
					</td>
					<td width="219"><form:input data-maxsize="55"
							class="validate[required] text-input" style="width:242px;"
							path="areaDTO.areaName" size="45" id="areaName" />
					</td>
					<td width="89" height="30"><label> Code<span
							style="color: #FF0000">*</span>
					</label>
					</td>
					<td width="181"><form:input data-maxsize="16"
							class="validate[required] text-input" path="areaDTO.areaCode"
							size="22" id="code" />
					</td>
				</tr>
				<tr>
					<td height="31" width="101" align="left"><label>Region<span
							style="color: #FF0000">*</span>
					</label>
					</td>
					<td height="31" colspan="3"><form:select id="region"
							class="validate[required] text-input"
							style="width:246px; height:20px;"
							path="areaDTO.regionDTO.regionId" items="${regiones}"
							itemLabel="regionName" itemValue="regionId"></form:select></td>
				</tr>
				<tr>
					<td width="101" height="26" align="left"><label>
							Description&nbsp;</label>
					</td>
					<td height="26" colspan="3"><form:textarea
							path="areaDTO.description" cols="82" id="description" />
					</td>
				</tr>
			</table>
			<div class="btn">
				<c:url value="remove_area" var="remove_url">
					<c:param name="areaId" value="${areaForm.areaDTO.areaId}"></c:param>
				</c:url>
				
				<div class="btn">
			<div class="savbtn">
		</div>
		</div>
		</div>
		</div>
	</form:form>
</c:if>
