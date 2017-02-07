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
	$(document).ready(function() {
		$(".cancelbtn").click(function() {
			window.self.location = "show_itemCategory_form";
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


<%-- 

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
</c:if> --%>
<form:form id="formID" name="fm" action="save_itemCategory"
	method="post" modelAttribute="itemCategoryForm">


	<div class="panel-header">
		<div class="panel-title">Item Category Master</div>
		<div class="panel-tool"></div>
	</div>

	<div align="left" class="bkgColor">
		<table class="" width="743" height="61" border="0" align="left">
			<tr>
				<td width="133" height="20" align="left"><label>Item
						Category Name <span style="color: #FF0000">*</span> </label></td>
				<td width="240"><form:input onkeyup="valid(this)" onblur="valid(this)"  data-maxsize="55"
						class="validate[required] text-input" style="width: 246px;"
						path="itemCategoryDTO.itemCategoryName" size="45"
						id="itemCategoryName"></form:input></td>
				<td width="135" height="20"><label> Category Code <span
						style="color: #FF0000">*</span> </label></td>
				<td width="188"><form:input onkeyup="valid1(this)" onblur="valid1(this)"  data-maxsize="16"
						class="validate[required] text-input"
						path="itemCategoryDTO.itemCategoryCode" size="22" id="groupCode" />
				</td>
			</tr>
			<tr>
				<td height="27" width="133" align="left"><label>Item
						Group <span style="color: #FF0000">*</span> </label></td>
				<td height="27" colspan="3"><form:select
						path="itemCategoryDTO.itemGroupDTO.itemGroupId" items="${itemGroupss}"
						itemLabel="itemGroupName" itemValue="itemGroupId" id="itemGroup"
						class="validate[required] text-input"
						style="width: 249px; height: 20px;">

					</form:select></td>
			</tr>
		</table>

	 <div class="btn">
		<div class="savbtn">
			<input class="submit" 	type="submit" value="" />
		</div>
	<div >
     <input class="cancelbtn" type="reset" value=""/>
   </div>
		</div>

	</div>

</form:form>

