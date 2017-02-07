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


<script type="text/javascript">
	$(document).ready(function() {
		$("button").button();
		//$('input:text, input:password').button()   
		$(".datepicker").datepicker();
		//      $(":submit").button()
	});
</script>


	<form:form name="input" id="formID" action="save_area"
		method="post" modelAttribute="areaForm">
		
		<form:errors path="*" cssClass="errorblock" element="div" />
	
		<div class="panel-header">
			<div class="panel-title">Area Master</div>
			<div class="panel-tool"></div>
		</div>

		<div align="left" class="bkgColor">
			<table class="" width="743" height="102" border="0" align="left">
				<tr>
					<td width="101" height="30" align="left"><label>
							Area Name<span style="color: #FF0000">*</span>
					</label>
					</td>
					<td width="216"><form:input data-maxsize="55" onkeyup="valid(this)" onblur="valid(this)"  
							class="validate[required] text-input" style="width:246px;"
							path="areaDTO.areaName" size="45" id="areaName" />
					</td>
					<td width="52" height="30"><label> Code<span
							style="color: #FF0000">*</span>
					</label>
					</td>
					<td width="259"><form:input data-maxsize="4" onkeyup="valid1(this)" onblur="valid1(this)"  
							class="validate[required] text-input" style="width:34%;" path="areaDTO.areaCode"
							size="16" id="code" />
					</td>
				</tr>
				<tr>
					<td height="31" width="101" align="left"><label>Region<span
							style="color: #FF0000">*</span>
					</label>
					</td>
					<td height="31" colspan="1">
					<form:select id="region"
							class="validate[required] text-input"
							style="width:250px; height:20px;"
							path="areaDTO.regionDTO.regionId" items="${regiones}"
							itemLabel="regionName" itemValue="regionId">
					</form:select></td>
				</tr>
				<tr>
					<td width="101" height="26" align="left"><label>
							Description&nbsp;</label>
					</td>
					<td height="26" colspan="3"><form:textarea onkeyup="valid(this)" onblur="valid(this)" data-maxsize="225"
							path="areaDTO.description" style="width:70%;" cols="84" id="description" />
					</td>
				</tr>
			</table>
			 <div class="btn">
   <div class="savbtn">
     <input class="submit"  type="submit" value=""/>
   </div>
   <div >
     <a href="#"><input  class="cancelbtn" 
     type="reset" value=""/></a>
   </div>
    </div>

		</div>



	</form:form>