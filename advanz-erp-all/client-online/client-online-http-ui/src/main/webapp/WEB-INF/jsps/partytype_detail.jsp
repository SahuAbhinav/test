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
    	  $(".datepicker" ).datepicker({dateFormat : 'dd-M-yy',changeMonth: true,
 	          changeYear: true, yearRange: '-99:+0', maxDate: '+0M +0D'});
      });
  </script>
    

  <form:form id="formID" name="formID" action="save_partytype" method="post" modelAttribute="partyTypeForm">
   
  <div class="panel-header">
	<div class="panel-title">Party Type Master</div>
	<div class="panel-tool"></div>
  </div>
  
  <div align="left" class="bkgColor"> 
   <table class="" width="643" height="83"  border="0" align="left">
      <tr>
        <td width="86" height="42" align="left"><label>Party Type<span style="color: #FF0000">*</span></label></td>
        <td width="93"><form:input onkeypress="return check(event)"  style="width:222px;" data-maxsize="55" class="validate[required] text-input" 
        path="partyTypeDTO.partyTypeDesc"  size="40" id="partyType" /></td>
        <td width="57" height="42"><label>Code<span style="color: #FF0000">*</span></label></td>
        <td width="342"><form:input onkeyup="valid1(this)" onblur="valid1(this)"  type="text" path="partyTypeDTO.partyTypeCode" data-maxsize="16" class="validate[required] text-input"
         style="width:120px;" size="18" id="code" /></td>
      </tr>
      <tr>
        <td height="24" width="86" align="left" >Type<span style="color: #FF0000">*</span></label></td>
        <td height="24" colspan="3" ><div style="border:solid 1px; height:20px; width:225px; border-color:#7f9db9;background-color:#FFF;">&nbsp;  &nbsp; &nbsp; &nbsp;
                <span style="    float: left;    margin-top:2px;    padding-left: 12px;">        	Debtor</span>
                
                <form:radiobutton path="partyTypeDTO.partyTypeFlag" id="type" style="    float: left; WIDTH:20PX" value="Debtor"/>
  
          <span style="    float: left;    margin-top: 2px;  "> Creditor</span>
            <form:radiobutton path="partyTypeDTO.partyTypeFlag" style="  float: left;    margin-right: 16px;    width: 20px; " id="type" value="Creditor" checked="true"/>
          
         </div></td>
      </tr>
    </table>
    <div class="btn">
   <div class="savbtn">
     <input class="submit"  type="submit" value=""/>
   </div>
   <div >
     <a href="show_partytype_form" class="cancelbtn" iconCls="icon-cancel"></a></td>
   </div>
    </div>    
   
  </div>
   </div>
 </form:form>
