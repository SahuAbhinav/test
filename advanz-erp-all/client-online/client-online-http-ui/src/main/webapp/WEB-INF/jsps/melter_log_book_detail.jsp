<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page isELIgnored="false"%>
<script type="text/javascript" charset="utf-8">
$(document).ready(function(){ 
 $(".myTimePicker[readonly]").css("background-color","#ffffff" );
 $(".datepicker1[readonly]").css("background-color","#ffffff" );
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
		var redrctUrl='show_melter_log_book';
				
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
		 var delUrl='remove_melter_log?sno='+frank_param;
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
 	 var frank_param = $('#sno').val();
 	 //	confirm('Are you sure you want to delete?');
	 var delUrl='get_melter_log_book?sno='+frank_param+'&opr=E';
 	 window.self.location = delUrl;  
	}
 	</script>	
 <c:if test="${opr=='V' }">
<script>
$(document).ready(function() {
	$('input').attr('readonly','readonly');	
	$('select').attr('disabled','disabled');
	$('textarea').attr('readonly','readonly');
	$('.datepicker1').attr('disabled','disabled');
	$('.myTimePicker').attr('disabled','disabled');
	$('input:radio').attr('disabled',true);
	 $("input:checkbox").attr("disabled", true);
	 
});
</script>
</c:if> 	
 
 <c:if test="${opr=='E' }">
	<script type="text/javascript">
		$(document).ready(function() {
		$('.datepicker1').attr('disabled','disabled');
		});
	</script>
	</c:if>	
 	
  <script>
	$(document).ready(function() {
		$(".cancelbtn").click(function() {
			window.self.location = "show_melter_log_book";
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
    	  var l=$('#lastDate').val();
    	  console.log(l);  
       
	    $(".datepicker1" ).datepicker({dateFormat : 'dd-M-yy',changeMonth: true,
	          changeYear: true, yearRange: '-99:+0', minDate: new Date(l) , maxDate: '+0M +0D'});
	  });
  </script>
     
 
<style>
  p { color:blue; }
   
 
	/*input {
	width:87%;
	margin-bottom:6px;
	}*/	
	select {
	width:87%;
	height:22px;
	}
	table {
	width:1108; !important}
	.gridheadingdiv td {
	height:22px;
	
	}
	.btn{
	 margin-left: 110px;
	}
	td{
	
	border: none;
	}
	.gridheadingdiv input {
    border: medium none;
    width: 70px;
}
.gridheadingdiv {
width:967px; !important
}

 .newWindow {
		background:url(static/images/search_small.png);
		background-repeat:no-repeat;
		height:12px;
		border:none;
		width:12px;
		
		}
		.bkgColor {
    border: 0px solid #4E8CCF;
    }
</style>


   <form:form id="formID" name="formID" action="save_melter_log_book"  modelAttribute="melterForm">
	<form:hidden path="melterLogBookDTO.sno" id="sno"/>
	<form:hidden path="lastRecordDate" id="lastDate"/>
  
  <div class="panel-header"  style="width:964px;">
	<div class="panel-title">Melter Log Book</div>
	<div class="panel-tool"></div>
  </div>
  
  <div align="center"  class="bkgColor" style="width: 100%;" 	> 
   <div ><span style="margin-left:80px;" class="errmsg"></span>  </div>
   <table  height="102" width="601" border="1" align="center" style="width:600px;">
    <tr>
      <td height="31">Date<span style="color: #FF0000">*</span></td>
      <td><form:input path="melterLogBookDTO.logDate"  class="validate[required] datepicker1" id="logDate" readonly="true"   style="width:91%; background:#fff; " size="11" /></td>
     
      <td width="118">Run No.<span style="color: #FF0000">*</span></td>
      <td width="245"><form:input path="melterLogBookDTO.runNo" onkeypress="return check(event)" data-maxsize="15" class="validate[required] text-input " id="runNo"  style="width:49%" size="11" /></td>
    </tr>
    <tr>
      <td height="30">Shift<span style="color: #FF0000">*</span></td>
      <td><form:select path="melterLogBookDTO.mastersDto.mastersId" 
				items="${shift}" itemLabel="name" itemValue="mastersId" id="mastersId"
			 class="validate[required] text-input " style="width: 94%; height: 20px;">
 	</form:select></td>
     
      <td>Operator</td>
      <td><form:input path="melterLogBookDTO.operatorName" style="width:49%" data-maxsize="65" onkeypress="return check(event)" id="operatorName"  size="20" /></td>
    </tr>
    <tr>
      <td width="89" height="33">Time<span style="color: #FF0000">*</span></td>
      <td width="131">
                      <form:input path="melterLogBookDTO.logTime" readonly="true" name="time" onKeyUp="valid(this)" onBlur="valid(this)" class="validate[required] text-input myTimePicker " style="width:92%" size="18" id="time" />
      </td>
      <td colspan="2">&nbsp;</td>
      </tr>
  </table>
  <table  height="536" width="600" border="1" style="width: 600px;" align="center"  >
    <tr>
      <td height="31" colspan="6"><div  style="background-color:#4e8ccf; color:#FFF;" align="center"><strong>INPUT VALUES</strong></div></td>
    </tr>
    <tr>
      <td width="91" height="30">Current (AMP)</td>
      <td width="98"><form:input path="melterLogBookDTO.inputCurrAmp" class="quantity validate[custom[number]]" data-maxsize="15" id="inputCurrAmp"  size="11" /></td>
      <td width="80">Voltage</td>
      <td width="87"><form:input path="melterLogBookDTO.inputVoltage" class="quantity validate[custom[number]]" data-maxsize="15" id="inputVoltage"  size="11" /></td>
      <td width="98">KW</td>
      <td width="120"><form:input path="melterLogBookDTO.inputKw"  id="inputKw" size="11"     data-maxsize="15"   style="text-align:right; width:57%" class="quantity validate[custom[number]]" name="regularTemp3"/></td>
    </tr>
    <tr>
      <td height="30" data-maxsize="15"  colspan="6"><div  style="background-color:#4e8ccf; color:#FFF;" align="center"><strong> ELECTRODE SECTION</strong></div></td>
      </tr>
    <tr>
      <td height="23" colspan="2" style="font-weight: bold; text-align: center;">Power 1 (DC)</td>
      <td colspan="2" style="font-weight: bold; text-align: center; ">Power 2 (DC)</td>
      <td colspan="2" style="font-weight: bold; text-align: center; padding-right:47px;">Power 3 (DC)</td>
      </tr>
    <tr>
      <td height="20" style="padding-left: 26px;">Voltage</td>
      <td style="padding-left:16px;">AMP</td>
      <td style="text-align: center"><span style="padding-left: 7px;">Voltage</span></td>
      <td style="padding-left: 23px;">AMP</td>
      <td style="text-align: center"><span style="text-align: center">Voltage</span></td>
      <td style="padding-left: 7px;">AMP</td>
    </tr>
    <tr>
      <td height="24" colspan="2" >
       <form:input path="melterLogBookDTO.power1DcVoltage" class="quantity validate[custom[number]]" data-maxsize="15" id="power1DcVoltage"  size="11" />
       <form:input path="melterLogBookDTO.power1DcAmp"  class="quantity validate[custom[number]]" id="power1DcAmp"  data-maxsize="15" size="11" /></td>
      <td colspan="2">
       <form:input path="melterLogBookDTO.power2DcVoltage" class="quantity validate[custom[number]]" id="power1DcVoltage" data-maxsize="15" size="11" />
       <form:input path="melterLogBookDTO.power2DcAmp" style="width: 47%;" class="quantity validate[custom[number]]" id="power1DcAmp" data-maxsize="15" size="11" /></td>
      <td colspan="2">
       <form:input path="melterLogBookDTO.power3DcVoltage" class="quantity validate[custom[number]]" id="power1DcVoltage" data-maxsize="15" size="11" />
       <form:input path="melterLogBookDTO.power3DcAmp"  class="quantity validate[custom[number]]" id="power1DcAmp" data-maxsize="15" size="11" /></td>
    </tr>
    <tr>
      <td height="22" colspan="2" style="font-weight: bold; text-align: center;">Power 1 (AC)</td>
      <td colspan="2" style="font-weight: bold; text-align: center;">Power 2 (AC)</td>
      <td colspan="2" style="font-weight: bold; text-align: center; padding-right:47px;">Power 3 (AC)</td>
    </tr>
    <tr>
      <td height="20" style="padding-left:23px;">Voltage</td>
      <td style="padding-left:16px;">AMP</td>
      <td style="text-align: center"><span style="padding-left:7px;">Voltage</span></td>
      <td style="padding-left: 23px;">AMP</td>
      <td style="text-align: center"><span style="text-align: center">Voltage</span></td>
      <td style="padding-left: 7px;">AMP</td>
      </tr>
    <tr>
      <td height="30" colspan="2" >
      			<form:input path="melterLogBookDTO.power1AcVoltage" data-maxsize="15" class="quantity validate[custom[number]]" id="power1AcVoltage"  size="11" />
                <form:input path="melterLogBookDTO.power1AcAmp"  class="quantity validate[custom[number]]"  data-maxsize="15" id="power1AcAmp"  size="11" /></td>
      <td colspan="2">
      			<form:input path="melterLogBookDTO.power2AcVoltage" data-maxsize="15" class="quantity validate[custom[number]]" id="power2AcVoltage"  size="11" />
                <form:input path="melterLogBookDTO.power2AcAmp" style="width: 47%;" class="quantity validate[custom[number]]" data-maxsize="15"  id="ppower2AcAmp"  size="11" /></td>
      <td colspan="2">
      			<form:input path="melterLogBookDTO.power3AcVoltage" class="quantity validate[custom[number]]" data-maxsize="15" id="power3AcVoltage"  size="11" />
             	<form:input path="melterLogBookDTO.power3AcAmp" class="quantity validate[custom[number]]"  data-maxsize="15" id="power3AcAmp"  size="11" /></td>
    </tr>
    <tr>
      <td height="30" colspan="2" style="font-weight: bold; text-align: center;" >TAP ELECTRODE (AC)</td>
      <td colspan="4" rowspan="3">&nbsp;</td>
      </tr>
    <tr>
      <td height="20" style="padding-left: 25px;">Voltage</td>
      <td style="padding-left:14px;">AMP</td>
      </tr>
    <tr>
      <td height="30" colspan="2" ><form:input path="melterLogBookDTO.tapElectrodeAcVoltage" data-maxsize="15" class="quantity validate[custom[number]]"  id="tapElectrodeAcVoltage"  size="11" />
                      <form:input path="melterLogBookDTO.tapElectrodeAcAmp" data-maxsize="15" class="quantity validate[custom[number]]" id="tapElectrodeAcAmp"  size="11" /></td>
      </tr>
    <tr>
      <td height="30"   colspan="6"><div  style="background-color:#4e8ccf; color:#FFF;" align="center"><strong>ELECTRODE POSITION VERTICAL(MM.)</strong></div></td>
    </tr>
    <tr>
      <td height="21">Power 1</td>
      <td><form:input path="melterLogBookDTO.electrodePositionPower1" data-maxsize="15" class="quantity validate[custom[number]]"  id="electrodePositionPower1"  size="11" /></td>
      <td>Power 2</td>
      <td><form:input path="melterLogBookDTO.electrodePositionPower2" data-maxsize="15" class="quantity validate[custom[number]]" id="electrodePositionPower2"  size="11" /></td>
      <td>Power 3</td>
      <td><form:input path="melterLogBookDTO.electrodePositionPower3" data-maxsize="15" class="quantity validate[custom[number]]" id="electrodePositionPower3"  size="11" /></td>
    </tr>
    
     <tr>
      <td height="30"   colspan="6"><div  style="background-color:#4e8ccf; color:#FFF;" align="center"><strong> ELECTRODE POSITION HORIZONTAL(MM.)</strong></div></td>
    </tr>
    <tr>
      <td height="21">Power 1</td>
      <td><form:input path="melterLogBookDTO.electrodePosition2Power1" data-maxsize="15" class="quantity validate[custom[number]]"  id="electrodePosition2Power1"  size="11" /></td>
      <td>Power 2</td>
      <td><form:input path="melterLogBookDTO.electrodePosition2Power2" data-maxsize="15" class="quantity validate[custom[number]]" id="electrodePosition2Power2"  size="11" /></td>
      <td>Power 3</td>
      <td><form:input path="melterLogBookDTO.electrodePosition2Power3" data-maxsize="15" class="quantity validate[custom[number]]" id="electrodePosition2Power3"  size="11" /></td>
    </tr>
    <tr>
      <td height="30"   colspan="6"><div  style="background-color:#4e8ccf; color:#FFF;" align="center"><strong> TOTAL SECTION</strong></div></td>
    </tr>
    <tr>
      <td height="30" style="text-align: center"><!-- Final Level(in mm) --></td>
      <%-- <td style="text-align: left"><form:input path="melterLogBookDTO.finalLevel"  data-maxsize="15" class="quantity validate[custom[number]]"  id="finalLevel"  size="11" /></td>  --%>
      
      <td colspan="2" style="text-align: center"><span style="padding-left:12px;">H2 Cyl. Pressure Kg/cm2</span></td>
      <td style="text-align: center"><span style="text-align: center">Total Power (KW)</span></td>
      <td style="text-align: center;">Pool Level</td>
    </tr>
    <tr>
      <td height="30" style="text-align: center"><!-- Shift Melter Power --></td>
     <%--  <td style="text-align: left"><form:input path="melterLogBookDTO.shiftMelterPower" data-maxsize="15" class="quantity validate[custom[number]]" id="shiftMelterPower"  size="11" /></td> --%>
      <td colspan="2"><form:input path="melterLogBookDTO.h2CylPressure" data-maxsize="15" class="quantity validate[custom[number]]" id="h2cylPressure"  size="29" /></td>
      <td colspan="2"><form:input path="melterLogBookDTO.totalPower" data-maxsize="15" class="quantity validate[custom[number]]" id="TotalPower"  size="16" />
        <form:input path="melterLogBookDTO.poolLevel"  style="width:41%" data-maxsize="15" class="quantity validate[custom[number]]" id="PoolLevel"  size="17" /></td>
    </tr>
    <tr>
      <td height="30">Remarks</td>
      <td colspan="5">
      <form:textarea  rows="3" cols="67" path="melterLogBookDTO.melterLogRemark"  onkeypress="return check(event)" id="PoolLevel" /></td>
      </tr>
  </table>

   
  <table align="center">
 <div class="btn">
   <div class="savbtn">
   <c:choose>
			<c:when test="${opr=='R'}">
				<c:url value="remove_melter_log" var="remove_url">
					<c:param name="sno" value="${melterForm.melterLogBookDTO.sno}"></c:param>
			</c:url> <a href="${remove_url}" class="removebtn" ></a> 
			</c:when>
			
			<c:when test="${opr=='V'}">
			
			<c:forEach items="${roleAndRightsDTOList}" var="roleAndRights">
		   <c:if test="${roleAndRights.menuId==sessionScope.menuId}">
    	  	 <c:if test="${roleAndRights.editFlag=='true'}">
		  	 <input class="edit_btn"  type="button" onclick="editMethod();" value=""/>
    	      <a href="show_melter_log_book"  class="cancelbtn" ></a>  
		     </c:if>
		      <c:if test="${roleAndRights.editFlag=='false'}">
		      <input class="edit_btn"  type="button" onclick="checkEdit();" value=""/>
    	      <a href="show_melter_log_book"  class="cancelbtn" ></a>  
		      </c:if>
		    
          </c:if>
          </c:forEach>	
	 	   
	 </c:when>
	 
	 
			<c:otherwise>
				<c:choose>
			<c:when test="${opr=='E'}">
				<input class="updatebtn" style="font-size: 11px; font-weight: bold;  padding: 0 0 0 30px;" type="submit" value=" "/>
				 <a href="show_melter_log_book"  class="cancelbtn" ></a>    
			</c:when>
			<c:otherwise>
				 <input class="submit" style="font-size: 11px; font-weight: bold;  padding: 0 0 0 30px;" type="submit" value=" "/>
				  <a href="show_melter_log_book"  class="cancelbtn" ></a>    
			</c:otherwise>
		</c:choose>
			</c:otherwise>
		</c:choose>
   </div>
         <div ><span style="margin-left:80px;" class="errmsg"></span>  </div>
    
    </div></table>
  </div>
  
</form:form>


  