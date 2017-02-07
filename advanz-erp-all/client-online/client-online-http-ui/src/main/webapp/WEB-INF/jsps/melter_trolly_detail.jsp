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

 <c:if test="${opr=='R'}">	
 <script type="text/javascript">
		var redrctUrl='show_melter_trolly_form';
				
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
 		 var frank_param = getParam('sno');
 		 //	confirm('Are you sure you want to delete?');
		 var delUrl='remove_melter_trolly_log?sno='+frank_param;
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
			window.self.location = "show_melter_trolly_form";
		});

	});
</script>


<script type="text/javascript">
function checkEdit()
	{
	alert('Login User Not Permit to Edit Record !!!!!!');
	}
	//	$(document).ready(function() {
 	function editMethod()
 	 { 
 	 var frank_param = $('#sno').val();
 	 //	confirm('Are you sure you want to delete?');
	 var delUrl='get_melter_trolly_log?sno='+frank_param+'&opr=E';
 	 window.self.location = delUrl;  
	}
 	</script>	
 <c:if test="${opr=='V' }">
<script>
$(document).ready(function() {
	$('input').attr('readonly','readonly');	
	$('select').attr('disabled','disabled');
	$('textarea').attr('readonly','readonly');
	$('.datepicker2').attr('disabled','disabled');
	$('.myTimePicker').attr('disabled','disabled');
	$('input:radio').attr('disabled',true);
	 $("input:checkbox").attr("disabled", true);
	 
});
</script>
</c:if> 	
 	

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
      $(document).ready(function()
       {
    	   $(".datepicker2" ).datepicker({dateFormat : 'dd-M-yy',changeMonth: true,
 	          changeYear: true, yearRange: '-99:+0', maxDate: '+0M +0D'});
      });
  </script>

     

 <style>
  p { color:blue; }
 }
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
 <form:form name="input" id="formID" action="save_melter_trolly_form"  modelAttribute="melterTrollyForm">
 <form:hidden path="melterTrollyLogDTO.sno" id="sno"/>
  
  <div class="panel-header"   >
	<div class="panel-title">Melter Trolly Detail</div>
	<div class="panel-tool"></div>
  </div>
  
  <div align="left" class="bkgColor" style=" height:auto; padding-bottom:14px; ">
     
 <table  height="0"   border="0"   style=" width:900px; margin-top:12px;">
  
    <tr>
      <td width="60">Log Date<span style="color: #FF0000">*</span></td></td>
      <td width="60">
      <form:input path="melterTrollyLogDTO.logDate"    readonly="true"    style="width:86%;background:#fff;"  data-maxsize="15"  class="validate[required] datepicker2"   size="11" id="logDate" name="logDate" /></td>
      <td width="44	">Log Time<span style="color: #FF0000">*</span></td></td>
      <td width="54">
      <form:input path="melterTrollyLogDTO.logTime"  class="validate[required] myTimePicker "  id="logTime" name="logTime" style="width:86%" size="11" /></td>
      <td width="31">Trolly NO<span style="color: #FF0000">*</span></td>
      <td width="84">
      <form:input path="melterTrollyLogDTO.trollyNumber" class="validate[required] text-input" data-maxsize="15" onkeypress="return check(event)" style="width:55%;"  id="trollyNumber" name="trollyNumber"  size="11" /></td>
    <tr>
      <td style="vertical-align: top;" height="27">Remark</td>
      <td colspan="8">
 	   <form:textarea  rows="2" cols="101" onkeypress="return check(event)" style="padding-left: 1px; width: 55%;" path="melterTrollyLogDTO.melterTrollyRemark"  id="melterTrollyRemark" name="melterTrollyRemark" /></td>   
    </tr>
   
  
    </table>
<div class="btn">
 <div class="savbtn">
 <c:choose>
			<c:when test="${opr=='R'}">
				<c:url value="remove_melter_trolly_log" var="remove_url">
					<c:param name="sno" value="${melterTrollyLogForm.melterTrollyLogDTO.sno}"></c:param>
			 </c:url><%-- <a href="${remove_url}" class="removebtn" ></a>  --%>
			</c:when>
			
				<c:when test="${opr=='V'}">
				<c:forEach items="${roleAndRightsDTOList}" var="roleAndRights">
		   <c:if test="${roleAndRights.menuId==sessionScope.menuId}">
    	  	 <c:if test="${roleAndRights.editFlag=='true'}">
		  	 <input class="edit_btn"  type="button" onclick="editMethod();" value=""/>
    	      <a href="show_melter_trolly_form"  class="cancelbtn" ></a>  
		     </c:if>
		      <c:if test="${roleAndRights.editFlag=='false'}">
		      <input class="edit_btn"  type="button" onclick="checkEdit();" value=""/>
    	      <a href="show_melter_trolly_form"  class="cancelbtn" ></a>  
		      </c:if>
		    
          </c:if>
          </c:forEach>	
	 	
	 </c:when>
	 
			
			<c:otherwise>
				<c:choose>
			<c:when test="${opr=='E'}">
				<input class="updatebtn" style="font-size: 11px; font-weight: bold;  padding: 0 0 0 30px;" type="submit" value=" "/>
				    <a href="show_melter_trolly_form"  class="cancelbtn" ></a>  
			</c:when>
			<c:otherwise>
				 <input class="submit" style="font-size: 11px; font-weight: bold;  padding: 0 0 0 30px;" type="submit" value=" "/>
				     <a href="show_melter_trolly_form"  class="cancelbtn" ></a>  
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

