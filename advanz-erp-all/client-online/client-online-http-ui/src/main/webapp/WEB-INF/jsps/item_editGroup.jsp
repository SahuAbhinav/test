 <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page isELIgnored="false" %>
   
 <c:if test="${opr=='R'}">
 <script type="text/javascript">
		var redrctUrl='show_ItemGroup_form';
				
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
 		 var frank_param = getParam('itemGroupId');
 		 //	confirm('Are you sure you want to delete?');
		 var delUrl='remove_itemGroup?itemGroupId='+frank_param;
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
	//	$(document).ready(function() {
		
	function checkEdit()
	{
	alert('Login User Not Permit to Edit Record !!!!!!');
	}
 	function editMethod()
 	 { 
 	 var frank_param = $('#itemGroupId').val();
 	 //	confirm('Are you sure you want to delete?');
	 var delUrl='get_itemGroup?itemGroupId='+frank_param+'&opr=M';
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
       <style type="text/css">
    .datepicker  {
    	background-color:#ebebe4;
    }

    .datepicker1  {
    	background-color:#ebebe4;
    }
    </style>
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

<c:if test="${opr=='M' || opr=='V'}">
  <form:form id="formID" name="input" action="update_itemGroup" method="post" modelAttribute="itemGroupForm"> 
  <div class="panel-header">
	<div class="panel-title">Item Group Master</div>
	<div class="panel-tool"></div>
  </div>
  
  <div align="left" class="bkgColor"> 
   <table class="" width="743" height="60"  border="0" align="left">
    <form:hidden path="itemGroupDTO.itemGroupId" id="itemGroupId"/>
     <tr>
       <td width="133" height="20" align="left"><label>Item Group Name <span style="color: #FF0000">*</span></label></td>
       <td width="269">      <form:input  onkeypress="return check(event)"  type="text" data-maxsize="55" class="validate[required] text-input" style="width:81%;"  
       path="itemGroupDTO.itemGroupName"  size="45" id="itemGroupName" /></td>
       <td width="135" height="20"><label> Group Code <span style="color: #FF0000">*</span></label></td>
       <td width="188">
       <form:input type="text" onkeyup="valid1(this)" onblur="valid1(this)"  data-maxsize="16" class="validate[required] text-input" path="itemGroupDTO.itemGroupCode" size="22"  
       id="itemGroupCode" style="background-color:#ebebe4;" readonly="true"/></td>
     </tr>
      <tr>
       <td width="133" height="20" align="left"><label>Item Group Flag <span style="color: #FF0000">*</span></label></td>
       <td height="26" colspan="3">
       <!-- need to change -->
       <form:select
						path="itemGroupDTO.itemGroupFlagId" items="${itemGroupFlages}"
						itemLabel="itemGroupFlagName" itemValue="itemGroupFlagId" id="itemCategoryId"
						class="validate[required]"
						style="width: 247px; height: 20px;">

					</form:select>
       
      <!-- <select name="itemGroupFlag" id="itemGroupFlag" class="validate[required] text-input"
        style="width:250px; height:20px;">
         <option value="test">Test</option>
       </select>
        --> 
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
    	      <a href="show_ItemGroup_form"  class="cancelbtn" ></a>  
		     </c:if>
		      <c:if test="${roleAndRights.editFlag=='false'}">
		      <input class="edit_btn"  type="button" onclick="checkEdit();" value=""/>
    	      <a href="show_ItemGroup_form"  class="cancelbtn" ></a>  
		      </c:if>
		    
          </c:if>
          </c:forEach>	
   	 		
    	</c:if>
            <c:if test="${opr=='M'}">
               <input class="updatebtn"  type="submit" value=""/> 
                       <a href="show_ItemGroup_form"  class="cancelbtn" ></a>     	
   		   	</c:if>
    </div>
	</div>
	</div>
</form:form>

</c:if>
<c:if test="${opr=='R'}">
  <form:form id="formID" name="input" action="remove_itemGroup" method="get" modelAttribute="itemGroupForm"> 
  <div class="panel-header">
	<div class="panel-title">Item Group Master</div>
	<div class="panel-tool"></div>
  </div>
  
  <div align="left" class="bkgColor"> 
   <table class="" width="743" height="60"  border="0" align="left">
    <form:hidden path="itemGroupDTO.itemGroupId" id="itemGroupId"/>
     <tr>
       <td width="133" height="20" align="left"><label>Item Group Name <span style="color: #FF0000">*</span></label></td>
       <td width="269">
       <form:input type="text" data-maxsize="55" class="validate[required] text-input" style="width:250px;" 
       path="itemGroupDTO.itemGroupName"  size="45" id="itemGroupName" readonly="true"/></td>
       <td width="135" height="20"><label> Group Code <span style="color: #FF0000">*</span></label></td>
       <td width="188">
       <form:input type="text" data-maxsize="16" class="validate[required] text-input" path="itemGroupDTO.itemGroupCode" size="22" 
       id="itemGroupCode" readonly="true"/></td>
     </tr>
      <tr>
       <td width="133" height="20" align="left"><label>Item Group Flag <span style="color: #FF0000">*</span></label></td>
       <td height="20" colspan="3">
       <!-- need to change -->
       <form:select  path="itemGroupDTO.itemGroupFlagId" items="${itemGroupFlages}"
						itemLabel="itemGroupFlagName" itemValue="itemGroupFlagId" id="itemCategoryId"
						class="validate[required]"
						style="width: 247px; height: 20px;"   disabled="true">

		</form:select>
       
      <!-- <select name="itemGroupFlag" id="itemGroupFlag" class="validate[required] text-input"
        style="width:250px; height:20px;">
         <option value="test">Test</option>
       </select>
        --> 
       </td>
      </tr>
   </table>
     
    
    <div class="btn">
				<c:url value="remove_itemGroup" var="remove_url">
					<c:param name="itemGroupId" value="${itemGroupForm.itemGroupDTO.itemGroupId}"></c:param>
				</c:url>
				
				<div class="btn">
			<div class="savbtn">
<%-- 			<a href="${remove_url}" class="removebtn" ></a>  --%>
				
			</div>
			  <!-- <div class="cancelbtn">
				<a href="show_ItemGroup_form"
						class="cancelbtn" iconCls="icon-cancel"></a>
			</div> -->
						    
		</div>
				

			</div>
   </div>
   </div>
</form:form>
</c:if>