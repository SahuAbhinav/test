<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script type="text/javascript" charset="utf-8">
$(document).ready(function(){ 
 $(".myTimePicker[readonly]").css("background-color","#ffffff" );
 $(".datepicker2[readonly]").css("background-color","#ffffff" );
} );
</script>
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
		var redrctUrl='show_melter_summary_form';
				
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
		 var delUrl='remove_melter_log_summary?sno='+frank_param;
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
 	function editMethod()
 	 { 
 	 var frank_param = $('#sno').val();
 	 //	confirm('Are you sure you want to delete?');
	 var delUrl='get_melter_log_summary?sno='+frank_param+'&opr=E';
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
 	
 <script type="text/javascript">
 function checkEdit()
	{
	alert('Login User Not Permit to Edit Record !!!!!!');
	}
      $(document).ready(function()
       {
        $(".datepicker" ).datepicker({dateFormat : 'dd-M-yy',maxDate: +0});
      });
  </script>

<%@ page isELIgnored="false"%>

<script>
	$(document).ready(function() {
		$(".cancelbtn").click(function() {
			window.self.location = "show_melter_summary_form";
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
  
   
  </script>
 
 <c:if test="${opr=='E' }">
	<script type="text/javascript">
		$(document).ready(function() {
		$('.datepicker2').attr('disabled','disabled');
		});
	</script>
	</c:if>
	

 <script type="text/javascript">
      $(document).ready(function()
       {
    	  var l=$('#lastDate').val();
    	   $(".datepicker2" ).datepicker({dateFormat : 'dd-M-yy',changeMonth: true,
 	          changeYear: true, yearRange: '-99:+0', minDate: new Date(l), maxDate: '+0M +0D'});
      });
  </script>

     


<style>
p {
	color: blue;
}

/*input {
width:87%;
margin-bottom:6px;
}*/
select {
	width: 87%;
	height: 22px;
}

 

.gridheadingdiv td {
	height: 22px;
}

.gridheadingdiv input {
	border: medium none;
	width: 70px;
}

 

.newWindow {
	background: url(static/images/search_small.png);
	background-repeat: no-repeat;
	height: 12px;
	border: none;
	width: 12px;
}
</style>
<%-- 
<c:if test="${opr=='R'}">
	<div class="errorblock">
		<ul>Remove Confirmation
		</ul>
	</div>
</c:if>
<c:if test="${not empty(errorList)}">
	<div class="errorblock">
		<ul>
			<c:forEach items="${errorList.errorList}" var="err">
				<li>${err.errorMsg}</li>
			</c:forEach>
		</ul>
	</div>
</c:if>

 --%>
<form:form name="input" id="formID" action="save_melter_summary_form"
	modelAttribute="melterSummaryForm" method="post">
	<form:hidden path="melterLogSummaryDTO.sno" id="sno" />
<form:hidden path="lastMelterLogDate" id="lastDate" />
	<div class="panel-header"  >
		<div class="panel-title">Melter Log Summary</div>
		<div class="panel-tool"></div>
	</div>

	<div align="left" class="bkgColor" >

		<table height="134" width="746" border="0" align="left"
			style="margin-left: 11px; width: 962px;">
			<tr>
				<td height="20">Date <span style="color: #FF0000">*</span>
				</td>
				<td width="100"><form:input path="melterLogSummaryDTO.logDate"
						
						style="width:92%; background:#fff; " readonly="true"
						data-maxsize="11" class="validate[required] datepicker2" 
						size="20" id="validMaxDatepicker" />
				</td>
				<td width="71">&nbsp;</td>
				<td width="94">Time <span style="color: #FF0000">*</span>
				</td>
				<td width="90"><form:input path="melterLogSummaryDTO.logTime" readonly="true"
						name="time" onKeyUp="valid(this)" onBlur="valid(this)"
						class="validate[required] text-input myTimePicker "
						style="width:92%" size="18" id="time" />
				</td>
				<td width="63" align="left">&nbsp;</td>
				<td width="108" align="left">KWH</td>
				<td width="98"><form:input path="melterLogSummaryDTO.logKwh"
						size="11" data-maxsize="15" style="text-align:right; width:92%"
						class="quantity validate[custom[number]]"  onkeypress="return check(event)"  name="kwh" />
				</td>
				<td width="58">&nbsp;</td>
			</tr>
			<tr>
				<td height="20">Water Temp</td>
				<td><form:input path="melterLogSummaryDTO.waterTemp" size="11"
						data-maxsize="15" style="text-align:right; width:92%"
						tabindex='4' class="quantity validate[custom[number]]" name="watertemp" />
				</td>
				<td>40 &#176;C Max.</td>
				<td>Regulator Temp.</td>
				<td><form:input path="melterLogSummaryDTO.regulerTemp"
						size="11" data-maxsize="15" style="text-align:right; width:92%"
						tabindex='5' class="quantity validate[custom[number]]" name="regularTemp" />
				</td>
				<td align="left">75 &#176;C Max.</td>
				<td align="left">Transformer Temp.</td>
				<td><form:input path="melterLogSummaryDTO.transformerTemp"
						size="11" data-maxsize="15" style="text-align:right; width:92%"
						tabindex='6' class="quantity validate[custom[number]]" name="transformerTemp" />
				</td>
				<td>75 &#176;C Max.</td>
			</tr>
			<tr>
			
			<td height="20">Trolly No
				</td>
				<td width="100"><form:input path="melterLogSummaryDTO.trollyNumber" style="width:92%; background:#fff; " data-maxsize="11" size="20"/>
				</td>
				<td width="71">&nbsp;</td>
				<td width="94">Quantity
				</td>
				<td width="90"><form:input path="melterLogSummaryDTO.quantity" class="quantity validate[custom[number]]" style="width:92%" size="18" />
				</td>
			</tr>
			<tr>
				<td style="vertical-align: top;" width="68" height="42">Remark</td>
				<td colspan="8"><form:textarea  rows="3" cols="90" style="padding-left: 6px; width: 50%;"
						path="melterLogSummaryDTO.melterLogRemark" onkeypress="return check(event)" id="remark"
						tabindex='7' name="remark" size="25" data-maxsize="500"  />
				</td>
			</tr>
		</table>




		<div class="btn">
			<div class="savbtn">

				<c:choose>
					<c:when test="${opr=='R'}">
						<c:url value="remove_melter_log_summary" var="remove_url">
							<c:param name="sno"
								value="${melterSummaryForm.melterLogSummaryDTO.sno}"></c:param>
						</c:url>
						<%-- <a href="${remove_url}" class="removebtn"></a> --%>
					</c:when>
					
					<c:when test="${opr=='V'}">
					<c:forEach items="${roleAndRightsDTOList}" var="roleAndRights">
		   <c:if test="${roleAndRights.menuId==sessionScope.menuId}">
    	  	 <c:if test="${roleAndRights.editFlag=='true'}">
		  	 <input class="edit_btn"  type="button" onclick="editMethod();" value=""/>
    	      <a href="show_melter_summary_form"  class="cancelbtn" ></a>  
		     </c:if>
		      <c:if test="${roleAndRights.editFlag=='false'}">
		      <input class="edit_btn"  type="button" onclick="checkEdit();" value=""/>
    	      <a href="show_melter_summary_form"  class="cancelbtn" ></a> 
		      </c:if>
		    
          </c:if>
          </c:forEach>
	 	
	 </c:when>
					
					<c:otherwise>
						<c:choose>
							<c:when test="${opr=='E'}">
								<input class="updatebtn"
									style="font-size: 11px; font-weight: bold; padding: 0 0 0 30px;"
									type="submit" value=" " />
										    <a href="show_melter_summary_form"  class="cancelbtn" ></a>  
							</c:when>
							<c:otherwise>
								<input class="submit"
									style="font-size: 11px; font-weight: bold; padding: 0 0 0 30px;"
									type="submit" value=" " />
									 	    <a href="show_melter_summary_form"  class="cancelbtn" ></a>  
							</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose>





			</div>
			
			<div style="width: 906px; margin: 0 auto;">
				<span style="margin-left: 80px;" class="errmsg"></span>
			</div>

		</div>
	</div>

</form:form>
