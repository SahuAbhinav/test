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

<script type="text/javascript">
	//	$(document).ready(function() {
 	function editMethod()
 	 { 
 	 var frank_param = $('#userId').val();
 	 //	confirm('Are you sure you want to delete?');
	 var delUrl='get_user_master_form?userId='+frank_param+'&opr=E';
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
	$('input:radio').attr('disabled',true);
	 $("input:checkbox").attr("disabled", true);
	 
});
</script>
</c:if>

<c:if test="${opr=='R'}">
 <script type="text/javascript">
		var redrctUrl='show_user_master';
				
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
 		 var frank_param = getParam('userId');
 		 //	confirm('Are you sure you want to delete?');
		 var delUrl='remove_user_master_role?userId='+frank_param;
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

  <script>
	$(document).ready(function() {
		$(".cancelbtn").click(function() {
			window.self.location = "show_user_master";
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


	<script type="text/javascript">
	function checkEdit()
		{
		alert('Login User Not Permit to Edit Record !!!!!!');
		}
	
  //anonymous self invoking function to avoid conflicting with other JavaScript
   (function ($){
    //function is called when the page is fully loaded
     $(document).ready(function(){
       //the page is loaded so attach the time picker to an input field
      $(".myTimePicker").timepicker({});
    });
  })(jQuery);
  
</script>    
	
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
	$(".quantity").keypress(function (e)  
	{ 
	  //if the letter is not digit then display error and don't type anything
	  if( e.which!=8 && e.which!=46 && e.which!=0 && (e.which<48 || e.which>57))
	  {
		//display error message
		$(".errmsg").html("Digits Only").show().fadeOut("slow"); 
	    return false;
      }	
	});

  });
  $(document).ready(function(){ 
    //called when key is pressed in textbox
	$(".quantity1").keypress(function (e)  
	{ 
	  //if the letter is not digit then display error and don't type anything
	  if( e.which!=8 && e.which!=46 && e.which!=0 && (e.which<48 || e.which>57))
	  {
		//display error message
		$(".errmsg1").html("Digits Only").show().fadeOut("slow"); 
	    return false;
      }	
	});

  });
   
  </script>
 

 <script type="text/javascript">
      $(document).ready(function()
       {
        $(".datepicker" ).datepicker({dateFormat : 'dd-M-yy',maxDate: +0});
      });
  </script>

     

 <style>
  p { color:blue; }
 
 
 }
	/*input {
	width:87%;
	margin-bottom:6px;
	}*/	
	select {
	width:87%;
	height:22px;
	}
 
	.gridheadingdiv input {
    border: medium none;
    width: 70px;
}
 
 .newWindow {
		background:url(static/images/search_small.png);
		background-repeat:no-repeat;
		height:12px;
		border:none;
		width:12px;
		
		}
</style>


<div>
 <form:form name="input" id="formID" action="save_user_master_form"  modelAttribute="userMasterForm" method="post">
 <form:hidden path="userMasterRoleDTO.userId"/>
 <form:hidden path="userId"/>
  
  <div class="panel-header">
	<div class="panel-title">User Master Detail</div>
	<div class="panel-tool"></div>
  </div>
  
  <div align="center" class="bkgColor" style=" height:auto; padding-bottom:14px; ">
     
 <table  height="0"   border="0"   style="width:500px; margin-top:12px;">
  
    <tr>
      <td width="46">User Login ID<span style="color: #FF0000">*</span></td>
      <td width="60">
      <form:input path="userMasterRoleDTO.userLoginId" class="validate[required] text-input " style="width:80%" /></td></tr>
      <tr><td width="44	">Password<span style="color: #FF0000">*</span></td>
      <td width="54">
      <form:input path="userMasterRoleDTO.userPassword" name="password" id="password" class="validate[required] text-input "  style="width:80%" size="11" /></td></tr>
      <tr><td width="44	">Confirm Password<span style="color: #FF0000">*</span></td>
      <td width="54">
      <form:input path="" name="password2" id="password2" class="validate[required,equals[password]] text-input"  style="width:80%" size="11" /></td></tr>
      
      <tr><td width="46">User Full Name<span style="color: #FF0000">*</span></td>
      <td width="60">
      <form:input path="userMasterRoleDTO.userFullName"  class="validate[required] text-input " style="width:80%;" size="11" /></td>
    </tr>
    
      <tr>
       <td height="30">Select Role<span style="color: #FF0000">*</span></td>
      <td><form:select path="userMasterRoleDTO.roleMasterDTO.roleId"
				items="${roleType}" itemLabel="roleName" itemValue="roleId" id="roleId"
			 class="validate[required] text-input " style="width: 81%; height: 20px;">
 	</form:select></td></tr>
 	
 	<tr>
 	<td align="left">Active Status</td>
    	    <td><div style="border:solid 1px; height:20px; width:116px; border-color:#7F9DB9;  background-color:#FFF;">&nbsp;
    	    <span style="    float: left;    margin-top:2px;    padding-left: 12px;"> Yes</span>
    	      <form:radiobutton  path="userMasterRoleDTO.activeFlag" value="1" style="width:20px; float: left; " id="activeFlag"/>
    	     <span style="    float: left;    margin-top: 2px;   "> No</span>
 			 <form:radiobutton  path="userMasterRoleDTO.activeFlag" value="0" style="width:20px;" id="activeFlag"/>
  	      </div></td>
  	    </tr>
  
    </table>
<div class="btn">
 <div class="savbtn">
 <c:choose>
	  <c:when test="${opr=='R'}">
	   <c:url value="remove_user_master_role" var="remove_url">
	   <c:param name="userId" value="${userId}"></c:param>
	   </c:url> <a href="${remove_url}" class="removebtn" ></a> 
	 </c:when>
	 
	   <c:when test="${opr=='V'}">
	   <c:forEach items="${roleAndRightsDTOList}" var="roleAndRights">
		   <c:if test="${roleAndRights.menuId==sessionScope.menuId}">
    	  	 <c:if test="${roleAndRights.editFlag=='true'}">
		  	 <input class="edit_btn"  type="button" onclick="editMethod();" value=""/>
    	      <a href="show_user_master"  class="cancelbtn" ></a>  
		     </c:if>
		      <c:if test="${roleAndRights.editFlag=='false'}">
		      <input class="edit_btn"  type="button" onclick="checkEdit();" value=""/>
    	      <a href="show_user_master"  class="cancelbtn" ></a>  
		      </c:if>
		    
          </c:if>
          </c:forEach>	
	 	
	 </c:when>
	 
  <c:otherwise>
    <c:choose>
	  <c:when test="${opr=='E'}">
		<input class="updatebtn" style="font-size: 11px; font-weight: bold;  padding: 0 0 0 30px;" type="submit" value=" "/>
		<a href="show_user_master"  class="cancelbtn" ></a>  
		
		</c:when>
		<c:otherwise>
		 <input class="submit" style="font-size: 11px; font-weight: bold;  padding: 0 0 0 30px;" type="submit" value=" "/>
		 <a href="show_user_master"  class="cancelbtn" ></a>  
		 
		</c:otherwise>
	  </c:choose>
	 </c:otherwise>
  	</c:choose>
	 </div>
   
     <div style="width:906px; margin:0 auto;"> <span style="margin-left:80px;" class="errmsg"></span> </div>
    
    </div>
  </div>
  
  
  
</form:form>
</div>

