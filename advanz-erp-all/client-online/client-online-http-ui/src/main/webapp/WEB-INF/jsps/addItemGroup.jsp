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
     


  <form:form id="formID" name="input" action="save_ItemGroup" method="post" modelAttribute="itemGroupForm"> 
  <div class="panel-header">
	<div class="panel-title">Item Group Master</div>
	<div class="panel-tool"></div>
  </div>
  
  <div align="left" class="bkgColor"> 
   <table class="" width="743" height="60"  border="0" align="left">
    <form:hidden path="itemGroupDTO.itemGroupId" id="itemGroupId"/>
     <tr>
       <td width="133" height="28" align="left"><label>Item Group Name <span style="color: #FF0000">*</span></label></td>
       <td width="269">
       <form:input onkeypress="return check(event)"  type="text" data-maxsize="55" class="validate[required] text-input" style="width:246px;" 
       path="itemGroupDTO.itemGroupName"  size="45" id="itemGroupName" /></td>
       <td width="135" height="28"><label> Group Code <span style="color: #FF0000">*</span></label></td>
       <td width="188">
       <form:input type="text" onkeyup="valid1(this)" onblur="valid1(this)"   data-maxsize="16" class="validate[required] text-input" path="itemGroupDTO.itemGroupCode" size="22" 
       id="itemGroupCode" /></td>
     </tr>
      <tr>
       <td width="133" height="26" align="left"><label>Item Group Flag <span style="color: #FF0000">*</span></label></td>
       <td height="26" colspan="3">
       <!-- need to change -->
        <form:select
						path="itemGroupDTO.itemGroupFlagId" items="${itemGroupFlages}"
						itemLabel="itemGroupFlagName" itemValue="itemGroupFlagId" id="itemCategoryId"
						class="validate[required]"
						style="width: 250px; height: 20px;">

					</form:select>
       
       <!--  <select name="itemGroupFlag" id="itemGroupFlag" class="validate[required] text-input"
        style="width:250px; height:20px;">
         <option value="test">Test</option>
       </select>
       --></td>
      </tr>
   </table>
      <div class="btn">
   <div class="savbtn">
     <input class="submit"  type="submit" value=""/>
   </div>
  <div class="cancelbtn">
				<a href="show_ItemGroup_form"
						class="cancelbtn" iconCls="icon-cancel"></a>
			</div>
    </div> 
   </div>
   </div>
</form:form>