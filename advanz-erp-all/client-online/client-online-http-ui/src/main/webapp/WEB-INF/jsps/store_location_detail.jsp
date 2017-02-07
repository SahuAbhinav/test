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
   
   
   <script type="text/javascript">
	//	$(document).ready(function() {
 	function editMethod()
 	 { 
 	 var frank_param = $('#storeLocationId').val();
 	 //	confirm('Are you sure you want to delete?');
	 var delUrl='get_storelocation?storeLocationId='+frank_param+'&opr=E';
 	 window.self.location = delUrl;  
	}
 	</script>
     
 <c:if test="${opr=='R'}">
 <script type="text/javascript">
		var redrctUrl='show_storelocation_form';
				
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
 		 var frank_param = getParam('storeLocationId');
 		 //	confirm('Are you sure you want to delete?');
		 var delUrl='remove_storelocation?id='+frank_param;
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
<c:if test="${opr=='R'}">
<script>
$(document).ready(function() {
	$('input').attr('readonly','disabled');	
	$('select').attr('disabled','disabled');	
});
</script>
</c:if>

<c:if test="${opr=='V'}">
<script>
$(document).ready(function() {

	$('input').attr('readonly','readonly');	
	$('select').attr('readonly','readonly');	
});
</script>
</c:if>

<script type="text/javascript">
function checkEdit()
	{
	alert('Login User Not Permit to Edit Record !!!!!!');
	}
   
$(document).ready(function(){ 
	 function fixNumFormat(){
		 $(".quantity").each(function(){				
				var v=parseFloat($(this).val());				
				v=v.toFixed(2);
				if(v=='NaN')
					v='0.00';
				$(this).val(v);
			});
		 }
		 fixNumFormat();
		 $(".quantity").change(function (e)  
		    		{ 
			 fixNumFormat();
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
  body {
	font-family:Arial, Helvetica, sans-serif;
	}
 	p { color:blue; } 
   p { color:blue; }
	.errmsg1 { color:red; }

	 
	
   p { color:blue; }
	.errmsg2 { color:red; }
</style>

 
<form:form id="formID" name="formID" action="save_storelocation" method="post" modelAttribute="storeLocationForm">
<c:if test="${opr=='E'}">
 <form:hidden path="storeLocationDTO.storeLocationId" id="storeLocationId"/>
 <input type="hidden" name="opr" value="${opr}" />
</c:if>  
<c:if test="${opr=='V'}">
 <form:hidden path="storeLocationDTO.storeLocationId" id="storeLocationId"/>
 <input type="hidden" name="opr" value="C" />
</c:if>  


  <div class="panel-header">
	<div class="panel-title">Store Location </div>
	<div class="panel-tool"></div>
  </div>
  
  <div align="center" class="bkgColor"> 
   <table width="800" border="0" cellspacing="0" height="116" cellpadding="0">
  <tr>
    <td width="98">&nbsp;&nbsp;&nbsp;&nbsp;Store Location <span style="color: #FF0000">*</span></td>
    <td colspan="3"><form:input type="text"  path="storeLocationDTO.storeLocation" style="width:97%;    padding-left: 1px;" onkeypress="return check(event)" data-maxsize="55"  class="validate[required] text-input" size="56" id="storeLocation" /></td>
    <td width="94"> &nbsp;&nbsp;&nbsp;Location Code<span style="color: #FF0000">*</span></td>
    <td width="173">
    <c:if test="${opr!='E'}">
    <form:input type="text" style="width:36%" path="storeLocationDTO.locationCode" data-maxsize="16" onkeyup="valid1(this)" onblur="valid1(this)"  class="validate[required] text-input"  size="15"
     id="locationCode" />
     </c:if>
     <c:if test="${opr=='E'}">
    <form:input type="text" style="width:36%" path="storeLocationDTO.locationCode" data-maxsize="16" onkeyup="valid1(this)" onblur="valid1(this)"  class="validate[required] text-input"  size="15"
     id="locationCode" readonly="true" />
     </c:if>
     </td>
  </tr>
  <tr>
    <td>&nbsp;&nbsp;&nbsp;&nbsp;Length (M)</td>
    <td width="86"><form:input style="width:70%" type="text" path="storeLocationDTO.storeLength" data-maxsize="6"  class="quantity validate[custom[number]]"   size="15" id="lengthM" />   </span></td>
    <td width="60">&nbsp;&nbsp;&nbsp;&nbsp;Width (M)</td>
    <td width="64"><form:input style="width:92%" type="text" path="storeLocationDTO.storeWidth" data-maxsize="6"  class="quantity validate[custom[number]]"  size="15" id="widthM" />&nbsp;
    </td>
    <td>&nbsp;&nbsp;&nbsp;Height (M)</td>
    <td><form:input type="text" style="width:36%" path="storeLocationDTO.storeHeight"  class="quantity validate[custom[number]]" data-maxsize="6"  size="15" id="heightM" />&nbsp;
     </td>
  </tr>
  <tr>
    <td>&nbsp;&nbsp;&nbsp;&nbsp;Store Condition </td>
    <td colspan="5"><form:input type="text"  style="width:77%"  onkeypress="return check(event)" data-maxsize="35" path="storeLocationDTO.storeCondition"  size="102" id="country" /></td>
    </tr>
    	
    
</table>
		
    <div class="btn">
         <div class="savbtn">
         <c:if test="${opr=='V'}">
        
         <c:forEach items="${roleAndRightsDTOList}" var="roleAndRights">
		   <c:if test="${roleAndRights.menuId==sessionScope.menuId}">
    	  	 <c:if test="${roleAndRights.editFlag=='true'}">
		  	 <input class="edit_btn"  type="button" onclick="editMethod();" value=""/>
    	      <a href="show_storelocation_form"  class="cancelbtn" ></a>  
		     </c:if>
		      <c:if test="${roleAndRights.editFlag=='false'}">
		      <input class="edit_btn"  type="button" onclick="checkEdit();" value=""/>
    	      <a href="show_storelocation_form"  class="cancelbtn" ></a>  
		      </c:if>
		    
          </c:if>
          </c:forEach>	   		
    	</c:if>

            <c:if test="${opr=='E'}">
               <input class="updatebtn"  type="submit" value=""/> 
                         <a href="show_storelocation_form" class="cancelbtn"></a>  
   		   	</c:if>
    
    	<c:if test="${opr!='R' && opr!='E' &&  opr!='V'}">
     		<input class="submit"  type="submit" value=""/>
     			 <a href="show_storelocation_form" class="cancelbtn" ></a>
     	</c:if>
      </div> 
      
    <div ><span style="margin-left:12px;" class="errmsg"></span></div>
  </div>
  </div>
  </div>
  
</form:form>