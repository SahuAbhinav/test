<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		var redrctUrl='show_itemCategory_form';
				
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
 		 var frank_param = getParam('itemCategoryId');
 		 //	confirm('Are you sure you want to delete?');
		var delUrl='remove_itemCategory?itemCategoryId='+frank_param;
 		if(confirm('Are you sure you want to delete?')) 
 		 {
 		 window.self.location = delUrl;
 		 }
		else{
		  window.self.location  = redrctUrl;
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
 	 var frank_param = $('#itemCategoryId').val();
 	 //	confirm('Are you sure you want to delete?');
	 var delUrl='edit_itemCategory?itemCategoryId='+frank_param+'&opr=E';
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
			window.self.location = "show_itemCategory_form";
		});
	});
	(function($) {
	     $.fn.currencyFormat = function() {
	         this.each( function( i ) {
	             $(this).change( function( e ){
	                 if( isNaN( parseFloat( this.value ) ) ) return;
	                 this.value = parseFloat(this.value).toFixed(2);
	             });
	         });
	         return this; //for chaining
	     }
	 })(jQuery);

	 // apply the currencyFormat behaviour to elements with 'currency' as their class
	 $( function() {
	     $('.quantity').currencyFormat();
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
				<li>${err.errorMsg}</li>
			</c:forEach>
		</ul>
	</div>
</c:if> --%>
<c:if test="${opr=='E' || opr=='V'}">
<form:form id="formID" name="fm" action="update_itemCategory"
	method="post" modelAttribute="itemCategoryForm">
	<form:hidden path="itemCategoryDTO.itemCategoryId" id="itemCategoryId"></form:hidden>

	<div class="panel-header">
		<div class="panel-title">Item Category</div>
		<div class="panel-tool"></div>
	</div>

	<div align="left" class="bkgColor">
		<table class="" width="743" height="61" border="0" align="left">
			<tr>
				<td width="133" height="20" align="left"><label>Item
						Category Name <span style="color: #FF0000">*</span> </label>
				</td>
				<td width="269"><form:input data-maxsize="55"
						class="validate[required] text-input"  onkeypress="return check(event)"   style="width: 246px;"
						path="itemCategoryDTO.itemCategoryName" size="45"
						id="itemCategoryName"></form:input>
				</td>
				<td width="135" height="20"><label> Category Code <span
						style="color: #FF0000">*</span> </label>
				</td>
				<td width="188"><form:input data-maxsize="16"
						class="validate[required] text-input" onkeyup="valid1(this)" onblur="valid1(this)" 
						path="itemCategoryDTO.itemCategoryCode" size="22" id="groupCode" style="background-color:#ebebe4;" readonly="true"/>
				</td>
			</tr>
			<tr>
				<td height="20" width="133" align="left"><label>Item
						Group <span style="color: #FF0000">*</span> </label>
				</td>
				<td height="20" colspan="3"><form:select
						path="itemCategoryDTO.itemGroupDTO.itemGroupId"
						items="${itemGroupss}" itemLabel="itemGroupName"
						itemValue="itemGroupId" id="itemGroup"
						class="validate[required] text-input"
						style="width: 249px; height: 20px;">

					</form:select>
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
    	      <a href="show_itemCategory_form"  class="cancelbtn" ></a>  
		     </c:if>
		      <c:if test="${roleAndRights.editFlag=='false'}">
		      <input class="edit_btn"  type="button" onclick="checkEdit();" value=""/>
    	      <a href="show_itemCategory_form"  class="cancelbtn" ></a>  
		      </c:if>
		    
          </c:if>
          </c:forEach>	
    	</c:if>
            <c:if test="${opr=='E'}">
               <input class="updatebtn"  type="submit" value=""/> 
                       <a href="show_itemCategory_form"  class="cancelbtn" ></a>     	
   		   	</c:if>
    </div>
	</div>
	</div>

</form:form>
</c:if>
<c:if test="${opr=='R'}">
<!-- <div class="errorblock">
		<ul>
			Remove Confirmation
		</ul>
	</div> -->
<form:form id="formID" name="fm" action=""
	method="post" modelAttribute="itemCategoryForm">
	<form:hidden path="itemCategoryDTO.itemCategoryId"></form:hidden>

	<div class="panel-header">
		<div class="panel-title">Item Category</div>
		<div class="panel-tool"></div>
	</div>

	<div align="left" class="bkgColor">
		<table class="" width="743" height="61" border="0" align="center">
			<tr>
				<td width="133" height="28" align="left"><label>Item
						Category Name <span style="color: #FF0000">*</span> </label>
				</td>
				<td width="269">
				<form:input data-maxsize="55"
						 
						path="itemCategoryDTO.itemCategoryName" size="45"
						id="itemCategoryName"
						readonly="true"
						></form:input>
				</td>
				<td width="135" height="28"><label> Category Code <span
						style="color: #FF0000">*</span> </label>
				</td>
				<td width="188"><form:input path="itemCategoryDTO.itemCategoryCode" size="22" 
									id="groupCode"
									readonly="true" 
									/>
				</td>
			</tr>
			<tr>
				<td height="27" width="133" align="left"><label>Item
						Group <span style="color: #FF0000">*</span> </label>
				</td>
				<td height="27" colspan="3">
				<form:input 
									path="itemCategoryDTO.itemGroupDTO.itemGroupName" size="45" 
									id="groupCode"
									readonly="true" 
									/>
				
				</td>
			</tr>
		</table>
<c:url value="remove_itemCategory" var="remove_url">
					<c:param name="itemCategoryId" value="${itemCategoryForm.itemCategoryDTO.itemCategoryId}"></c:param>
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

