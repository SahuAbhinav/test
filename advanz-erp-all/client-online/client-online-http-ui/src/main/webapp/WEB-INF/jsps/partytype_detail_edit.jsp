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


 <c:if test="${opr=='R'}">
 <script type="text/javascript">
		var redrctUrl='show_partytype_form';
				
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
 		 var frank_param = getParam('partyTypeId');
 		 //	confirm('Are you sure you want to delete?');
		 var delUrl='delete_partytype?partyTypeId='+frank_param;
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
 	 var frank_param = $('#partyTypeId').val();
 	 //	confirm('Are you sure you want to delete?');
	 var delUrl='get_partytype?partyTypeId='+frank_param+'&opr=E';
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
<script>
$(document).ready(function() {
	$('input').attr('disabled','disabled');	
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
     //  $("button").button();
    	  //$('input:text, input:password').button()   
	    $(".datepicker" ).datepicker();
  //      $(":submit").button()
      });
  </script>
     

<c:if test="${opr=='E' || opr=='V'}">
  <form:form id="formID" name="formID" action="update_partytype" method="post" modelAttribute="partyTypeForm">
   <form:errors path="*" cssClass="errorblock"  element="div"  />
  <div class="panel-header">
	<div class="panel-title">Party Type Master</div>
	<div class="panel-tool"></div>
  </div>
  
  <div align="left" class="bkgColor"> 
   <table class="" width="643" height="83"  border="0" align="left">
   <form:hidden path="partyTypeDTO.partyTypeId" id="partyTypeId"/>
      <tr>
        <td width="33" height="42" align="left"><label>Party Type<span style="color: #FF0000">*</span></label></td>
        <td width="99"><form:input onkeypress="return check(event)"  type="text" style="width:222px;" data-maxsize="55" class="validate[required] text-input" 
        path="partyTypeDTO.partyTypeDesc"  size="40" id="partyType" /></td>
        <td width="57" height="42"><label>Code<span style="color: #FF0000">*</span></label></td>
        <td width="342"><form:input onkeyup="valid1(this)" onblur="valid1(this)"  type="text" path="partyTypeDTO.partyTypeCode" readonly="true" data-maxsize="16" class="validate[required] text-input"
         style="width:120px;" size="18" id="code" /></td>
      </tr>
      <tr>
        <td height="24" width="86" align="left" >Type<span style="color: #FF0000">*</span></label></td>
        <td height="24" colspan="3" ><div style="border:solid 1px; height:20px; width:225px; border-color:#7f9db9;background-color:#FFF;">&nbsp;  &nbsp; &nbsp; &nbsp;
        <span style="    float: left;    margin-top:2px;    padding-left: 12px;">        	Debtor</span>
         
                <form:radiobutton path="partyTypeDTO.partyTypeFlag"   style="    float: left; WIDTH:20PX" id="type" value="Debtor"/>
         <span style="    float: left;    margin-top: 2px;  "> Creditor</span>
            <form:radiobutton path="partyTypeDTO.partyTypeFlag" id="type" style="  float: left;    margin-right: 16px;    width: 20px; " value="Creditor"/>
     
         </div></td>
      </tr>
    </table>
    <div class="btn">
         <div class="savbtn">
         <c:if test="${opr=='V'}">
      <c:forEach items="${roleAndRightsDTOList}" var="roleAndRights">
		   <c:if test="${roleAndRights.menuId==sessionScope.menuId}">
    	  	 <c:if test="${roleAndRights.editFlag=='true'}">
		  	 <input class="edit_btn"  type="button" onclick="editMethod();" value=""/>
    	      <a href="show_partytype_form"  class="cancelbtn" ></a>  
		     </c:if>
		      <c:if test="${roleAndRights.editFlag=='false'}">
		      <input class="edit_btn"  type="button" onclick="checkEdit();" value=""/>
    	      <a href="show_partytype_form"  class="cancelbtn" ></a>  
		      </c:if>
		    
          </c:if>
          </c:forEach>	
         
   		
    	</c:if>
            <c:if test="${opr=='E'}">
               <input class="updatebtn"  type="submit" value=""/> 
                       <a href="show_partytype_form"  class="cancelbtn" ></a>     	
   		   	</c:if>
    </div>
	</div>
	</div>

 </form:form>
</c:if>
<c:if test="${opr=='R'}">
<!-- <div class="errorblock">
		<ul>
			<li>Remove Confirmation</li>
		</ul>
</div> -->
  <form:form id="formID" name="formID" action="" method="post" modelAttribute="partyTypeForm">
   
  <div class="panel-header">
	<div class="panel-title">Party Type Master</div>
	<div class="panel-tool"></div>
  </div>
  
  <div align="left" class="bkgColor"> 
   <table class="" width="643" height="83"  border="0" align="center">
   <form:hidden path="partyTypeDTO.partyTypeId" id="partyTypeId"/>
      <tr>
        <td width="33" height="42" align="left"><label>Party Type<span style="color: #FF0000">*</span></label></td>
        <td width="93"><form:input type="text" readonly="true" style="width:222px;" data-maxsize="55" class="validate[required] text-input" 
        path="partyTypeDTO.partyTypeDesc"  size="40" id="partyType" /></td>
        <td width="57" height="42"><label>Code<span style="color: #FF0000">*</span></label></td>
        <td width="342"><form:input type="text" readonly="true" path="partyTypeDTO.partyTypeCode" data-maxsize="16" class="validate[required] text-input"
         style="width:120px;" size="18" id="code" /></td>
      </tr>
      <tr>
        <td height="24" width="86" align="left" >Type<span style="color: #FF0000">*</span></label></td>
        <td height="24" colspan="3" ><div style="border:solid 1px; height:20px; width:225px; border-color:#7f9db9;background-color:#FFF;">&nbsp;  &nbsp; &nbsp; &nbsp;
               <form:radiobutton path="partyTypeDTO.partyTypeFlag" id="type" value="Debtor"/>
          Debtor
           &nbsp;&nbsp;&nbsp;&nbsp;   &nbsp;&nbsp;  &nbsp;
            <form:radiobutton path="partyTypeDTO.partyTypeFlag" id="type" value="Creditor"/>
          Creditor
         </div></td>
      </tr>
    </table>
    <c:url value="delete_partytype" var="remove_url">
					<c:param name="partyTypeId" value="${partyTypeForm.partyTypeDTO.partyTypeId}"></c:param>
		</c:url>
		<div class="btn">
			<a href="${remove_url}" class="removebtn" ></a> 
			<a href="show_partytype_form"  class="cancelbtn" ></a> 
		</div>
	</div>	

 </form:form>
</c:if>