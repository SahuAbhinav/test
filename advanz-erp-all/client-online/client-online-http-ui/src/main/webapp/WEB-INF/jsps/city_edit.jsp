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
		var redrctUrl='get_city_list';
				
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
 		 var frank_param = getParam('cityId');
 		 //	confirm('Are you sure you want to delete?');
		 var delUrl='remove_city?cityId='+frank_param;
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
 	 var frank_param = $('#cityId').val();
 	 //	confirm('Are you sure you want to delete?');
	 var delUrl='get_city?cityId='+frank_param+'&opr=E';
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


<script type="text/javascript">
	$(document).ready(function() {
		$("button").button();
		//$('input:text, input:password').button()   
		$(".datepicker").datepicker();
		//      $(":submit").button()
	});
</script>



<c:if test="${opr=='E' || opr=='V'}">
	<form:form name="input" id="formID" action="update_city"
		method="post" modelAttribute="cityForm">
		<form:hidden path="cityDTO.cityId" id="cityId" />
		<div class="panel-header">
			<div class="panel-title">City</div>
			<div class="panel-tool"></div>
		</div>

		<div align="left" class="bkgColor">
			<table class="" width="743" height="102" border="0" align="left">
				<tr>
					<td width="101" height="30" align="left"><label>
							City Name<span style="color: #FF0000">*</span>
					</label>
					</td>
					<td width="218"><form:input data-maxsize="55"  onkeypress="return check(event)"   
							class="validate[required] text-input" style="width:250px;"
							path="cityDTO.cityName" size="45" id="cityName" />
					</td>
					<td width="21" height="30"><label> Code<span
							style="color: #FF0000">*</span>
					</label>
					</td>
					<td width="204"><form:input data-maxsize="16" readonly="true" onkeyup="valid1(this)" onblur="valid1(this)"  
							class="validate[required] text-input" path="cityDTO.cityCode"
							size="22" id="code" />
					</td>
				</tr>
				<tr>
					<td height="31" width="101" align="left"><label>Area<span
							style="color: #FF0000">*</span>
					</label>
					</td>
					<td height="31" colspan="3"><form:select id="area"
							class="validate[required] text-input"
							style="width:250px; height:20px;"
							path="cityDTO.areaDTO.areaId" items="${areas}"
							itemLabel="areaName" itemValue="areaId"></form:select></td>
				</tr>
				<tr>
					<td width="101" height="26" align="left"><label>
							Description&nbsp;</label>
					</td>
					<td height="26" colspan="3"><form:input  onkeypress="return check(event)"  data-maxsize="150"
							path="cityDTO.description" size="104" id="description" />
					</td>
				</tr>
			</table>
			<div class="savbtn">
         <c:if test="${opr=='V'}">
         <c:forEach items="${roleAndRightsDTOList}" var="roleAndRights">
		   <c:if test="${roleAndRights.menuId==sessionScope.menuId}">
    	  	 <c:if test="${roleAndRights.editFlag=='true'}">
		  	 <input class="edit_btn"  type="button" onclick="editMethod();" value=""/>
    	      <a href="get_city_list"  class="cancelbtn" ></a>  
		     </c:if>
		      <c:if test="${roleAndRights.editFlag=='false'}">
		      <input class="edit_btn"  type="button" onclick="checkEdit();" value=""/>
    	      <a href="get_city_list"  class="cancelbtn" ></a>  
		      </c:if>
		    
          </c:if>
          </c:forEach>	
        </c:if>
            <c:if test="${opr=='E'}">
               <input class="updatebtn"  type="submit" value=""/> 
                       <a href="get_city_list"  class="cancelbtn" ></a>     	
   		   	</c:if>
    </div>
	</div>
	</div>


	</form:form>
</c:if>
<c:if test="${opr=='R'}">
 
	<form:form name="input" id="formID" action="" method="post"
		modelAttribute="cityForm">
		<form:hidden path="cityDTO.cityId" />
		<div class="panel-header">
			<div class="panel-title">City</div>
			<div class="panel-tool"></div>
		</div>

		<div align="left" class="bkgColor">
			<table class="" width="743" height="102" border="0" align="left">
				<tr>
					<td width="101" height="30" align="left"><label>
							City Name<span style="color: #FF0000">*</span>
					</label>
					</td>
					<td width="218"><form:input data-maxsize="55"
							class="validate[required] text-input" style="width:250px;" readonly="true"
							path="cityDTO.cityName" size="45" id="cityName" />
					</td>
					<td width="21" height="30"><label> Code<span
							style="color: #FF0000">*</span>
					</label>
					</td>
					<td width="146"><form:input data-maxsize="16"
							class="validate[required] text-input" path="cityDTO.cityCode" readonly="true"
							size="22" id="code" />
					</td>
				</tr>
				<tr>
					<td height="31" width="101" align="left"><label>Area<span
							style="color: #FF0000">*</span>
					</label>
					</td>
					<td height="31" colspan="3"><form:select id="area" readonly="true"
							class="validate[required] text-input"
							style="width:250px; height:20px;"
							path="cityDTO.areaDTO.areaId" items="${areas}"
							itemLabel="areaName" itemValue="areaId"></form:select></td>
				</tr>
				<tr>
					<td width="101" height="26" align="left"><label>
							Description&nbsp;</label>
					</td>
					<td height="26" colspan="3"><form:input
							path="cityDTO.description" size="104" readonly="true" id="description" />
					</td>
				</tr>
			</table>
			<div class="btn">
				<c:url value="remove_city" var="remove_url">
					<c:param name="cityId" value="${cityForm.cityDTO.cityId}"></c:param>
				</c:url>
				<%-- <div class="btn">
					<a href="${remove_url}" class="removebtn"
						iconCls="icon-remove"></a> 
					<a href="get_city_list"
						class="cancelbtn" iconCls="icon-cancel"></a>

				</div> --%>

			</div>

		</div>



	</form:form>
</c:if>
