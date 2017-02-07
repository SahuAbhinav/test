<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
	$(document).ready(function() {
		$(".cancelbtn").click(function() {
			window.self.location = "get_region_list";
		});

	});
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
     

  <form:form name="input" id="formID" action="save_region" method="post" modelAttribute="regionForm">
   
  <div class="panel-header">
	<div class="panel-title">Region Master</div>
	<div class="panel-tool"></div>
  </div>
  
  <div align="left" class="bkgColor"> 
   <table class="" width="743" height="102"  border="0" align="left">
     <tr>
       <td width="101" height="30" align="left"><label>   Region Name<span style="color: #FF0000">*</span></label></td>
       <td width="231"><form:input onkeypress="return check(event)"   data-maxsize="55" class="validate[required] text-input" style="width:246px;"  
       					path="regionDTO.regionName"  size="45" id="regionName" /></td>
       <td width="41" height="30"><label>  Code<span style="color: #FF0000">*</span></label></td>
       <td width="270"><form:input onkeyup="valid1(this)" onblur="valid1(this)"   data-maxsize="16" class="validate[required] text-input"  
       					path="regionDTO.regionCode" size="22" 
       id="code" /></td>
     </tr>
     <tr>
       <td height="31" width="101" align="left"><label>State<span style="color: #FF0000">*</span></label></td>
       <td height="31" colspan="1">	
		<form:select  id="state" class="validate[required] text-input" style="width:250px; height:20px;"
						path="regionDTO.stateDTO.stateId"  items="${states}" itemLabel="stateName" itemValue="stateId"></form:select>
       </td>
      </tr>
      <tr>
       <td width="101" height="26" align="left"><label> 
        Description&nbsp;</label></td>
              <td height="26" colspan="3"><form:textarea onkeypress="return check(event)"    style="width:71%;" path="regionDTO.description"  cols="87" id="description" /></td>
      </tr>
   </table>
    <div class="btn">
   <div class="savbtn">
     <input class="submit" type="submit" value=""/>
   </div>
   <div >
     <input  class="cancelbtn" type="reset" value=""/>
   </div>
    </div>    
   
  </div>
 
 
 
</form:form>
 