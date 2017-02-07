<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>

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
		var redrctUrl='salaryNoteList';
				
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
 		 var frank_param = getParam('noteId');
 		 //	confirm('Are you sure you want to delete?');
		 var delUrl='removeSalaryNote?noteId='+frank_param;
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
			window.self.location = "salaryNoteList";
		});
		
	//	$("input[readonly='true']").addClass('disabled');
		$("input[readonly]").css("background-color","#ebebe4");
	//	$(".datepicker").css("background-color","pink");
	});
</script>

 <script type="text/javascript">    
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
 function fixNumFormat1(x){
			var v=parseFloat(x.val());			
			v=v.toFixed(2);
			if(v=='NaN')
				v='0.00';
			x.val(v);
	 }

    $(".quantity").change(function (e)  
    		{ 
    	 fixNumFormat1($(this));
    		});
    fixNumFormat();
});
</script>
 
<script>
	$(document).ready(function() {
		function abc(cb){	
			
			$.get('get_batch_stock', { batchNo: $('#batchNo').val()}, function(data) {		
					//	alert(data);
					var cl=parseFloat(data)+parseFloat($(cb).val());
				cl=cl.toFixed(2);
						 $("#closingStock").val(cl);	
			});	
			}
	$(function() {			
			$('#openingStock').change(function() {			
			//	abc(this);
				 $("#closingStock").val($(this).val());
		});
	});
	abc($('#openingStock'));	
	});
</script>
<script>
	jQuery(document).ready(function() {
		// binds form submission and fields to the validation engine
		jQuery("#formID").validationEngine();
	});

	function checkHELLO(field, rules, i, options) {
		if (field.val() != "HELLO") {
			// this allows to use i18 for the error msgs
			return options.allrules.validate2fields.alertText;
		}
	}
</script>

<script type="text/javascript">
	$(document).ready(function() {

		//called when key is pressed in textbox
		$("#quantity").keypress(function(e) {
			//if the letter is not digit then display error and don't type anything
			if (e.which != 8 && e.which != 0 && (e.which<48 || e.which>57)) {
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
    	  $("#noteDate" ).datepicker({dateFormat : 'dd-M-yy',changeMonth: true,
	          changeYear: true, yearRange: '-99:+0'});
      });
  </script>
<script type="text/javascript">
      $(document).ready(function()
       {
     
	    $(".datepicker1" ).datepicker({dateFormat : 'dd-M-yy',changeMonth: true,
	          changeYear: true, yearRange: '-99:+0', maxDate: '+0M +0D'});
	    $(".datepicker2" ).datepicker({dateFormat : 'dd-M-yy',changeMonth: true,
	          changeYear: true, yearRange: '-99:+0', minDate: '+0M +0D'});
      });
  </script>

<script type="text/javascript">
		function checkEdit()
 		{
 		alert('Login User Not Permit to Edit Record !!!!!!');
 		}
		
 	function editMethod()
 	 { 
 	 var frank_param = $('#noteId').val();
 	 //	confirm('Are you sure you want to delete?');
	 var delUrl='getSalaryNote?noteId='+frank_param+'&opr=E';
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
 	

<style>

element.style {
    overflow-y: scroll;
}
.aAT, .aAU {
    background: none repeat scroll 0 0 #FFFFFF;
}
.aAU {
    background-color: #FFFFFF;
    overflow-x: visible;
}
body, td, input, textarea, select {
    font-family: arial,sans-serif;
    margin: 0;
}
body {
    height: 100%;
    overflow: hidden;
    width: 100%;
}
body, td, input, textarea, select {
    font-family: arial,sans-serif;
}
body {
    height: 100%;
    margin: 0;
    width: 100%;
}
element.style {
    background-color: #EBEBE4;
    border: 1px solid #7F9DB9;
    height: 16px;
    width: 104%;
}

element.style {
    overflow-y: scroll;
}
.aAT, .aAU {
    background: none repeat scroll 0 0 #FFFFFF;
}
.aAU {
    background-color: #FFFFFF;
    overflow-x: visible;
}
body, td, input, textarea, select {
    font-family: arial,sans-serif;
    margin: 0;
}
body {
    height: 100%;
    overflow: hidden;
    width: 100%;
}
body, td, input, textarea, select {
    font-family: arial,sans-serif;
}
body {
    height: 100%;
    margin: 0;
    width: 100%;
}
table {
    width: 264px;
}
input{
width: 80%;
}
</style>
<form:form name="input" id="formID" action="saveSalaryNote" method="post" modelAttribute="salaryNoteForm">
<form:errors path="*" cssClass="errorblock"  element="div"  />
<form:hidden path="salaryNoteDTO.noteId" id="noteId"/>
<form:hidden path="salaryNoteDTO.sno" id="sno"/> 
	<div class="panel-header">
		<div class="panel-title">Salary Note </div>
		<div class="panel-tool"></div>
	</div>

	<div align="left" class="bkgColor"  >
	<table width="826" height="208" border="0" class="select_box" align="center" style="  margin-left: 20px; float: left;">

			<tbody>
				<tr>
				<td width="400">
				<table width="0">
				<tr>
					<td nowrap="nowrap" width="85">Note ID.<span style="color: #FF0000">*</span>
					</td>
					<td><form:input class="validate[required] text-input" onkeypress="return check(event)"
						data-maxsize="15" path="salaryNoteDTO.noteId" readonly="true" id="batchNo" size="18" style=" width:32%"/>
					</td>
					</tr><tr>
					<td>Note Date<span style="color: #FF0000">*</span>
					</td>
					<td><form:input path="salaryNoteDTO.noteDate" size="18" id="noteDate"
						class="datepicker validate[required] text-input" readonly="true" style=" width:32%"/>
					</td>
					</tr>
				<tr>
					<td align="left">Active<span style="color: #FF0000">*</span>
					</td>
					<td><div style="border: solid 1px; height: 20px; width: 32%; border-radius: 3px 3px 3px 3px; border-color: #7f9db9; background-color: #FFF;">
							 <span style="    float: left;    margin-top:2px;    padding-left: 12px;"> Yes</span>
							 <form:radiobutton path="salaryNoteDTO.status" value="1" class="validate[required] radio"
							style="width: 10px;  margin:2px 9px; float: left;" checked="checked" id="activeStatus" />
						<span style="float: left;margin-top: 2px;"> No</span>
							 <form:radiobutton path="salaryNoteDTO.status" value="0" class="validate[required] radio"
								style="width: 10px; margin: 2px 9px;" id="activeStatus"/>
						</div>
					</td>
				</tr>
				<tr>
					<td align="left">Note<span style="color: #FF0000">*</span>
					</td>
					<td><form:textarea path="salaryNoteDTO.salaryNote" class="validate[required]" style="width: 347px; height: 143px;"  id="expiryDate"/>
					</td>
				</tr>
				</table>
				</td>
				
				<td>
				<table width="0" border="0">
				<tr>
					<td width="93" nowrap="nowrap">Employee Name<span style="color: #FF0000">*</span>
					</td>
					<td colspan="3">
					<div Style="width: 180px; background: none;">
					<table  style="width: 182px;" cellpadding="0" cellspacing="0">
						<tr>
						<th	style="border-bottom:1px solid #99BBE8; color:#fff; height:19px; background-color: #4e8ccf; padding-left: 3px;" width="50"> Select</th>
						 <th style="border-bottom:1px solid #99BBE8;border-left:1px solid #99BBE8; color:#fff; background-color: #4e8ccf; " width="350">Employee Name</th>
						</tr>
					 </table></div>
					<div class="scrolltable" Style="height: 309px; width: 180px; background: none;">
					   <table style="width: 166px;" cellpadding="0" cellspacing="0">
						<c:forEach items="${employeeList}" var="cat" varStatus="item">
						  <tr>
						   <th style="border-bottom:1px solid #99BBE8;" width="50" >
						   
						   <form:checkbox path="salaryNoteDTO.empIdList" class="validate[required] "
									style="float:left; width:40px;" value="${cat.employeeId}" /></th>
								<th style="border-bottom:1px solid #99BBE8;" width="350" nowrap="nowrap">
								<c:out value="${cat.employeeName}" />&nbsp;<c:out value="${cat.employeeLastName}" />
						</th>
					</tr>
					</c:forEach>
					</table>
				</div>
				</td></tr>
				</table>
				</td>
				</tr>
				<tr>
				<td>
			
	</div>
	</td>
	</tr>
	</tbody>
	</table>
	
<div align="left" style="width: 300px;margin-left: 60px;">
  <table align="left">
 <div class="btn">
   <div class="savbtn">
   <c:choose>
			<c:when test="${opr=='R'}">
				<c:url value="remove_melter_log" var="remove_url">
					<c:param name="noteId" value="${salaryNoteForm.salaryNoteDTO.noteId}"></c:param>
			</c:url> <a href="${remove_url}" class="removebtn" ></a> 
			</c:when>
			
			<c:when test="${opr=='V'}">
			
			<c:forEach items="${roleAndRightsDTOList}" var="roleAndRights">
		   <c:if test="${roleAndRights.menuId==sessionScope.menuId}">
    	  	 <c:if test="${roleAndRights.editFlag=='true'}">
		  	 <input class="edit_btn"  type="button" onclick="editMethod();" value=""/>
    	      <a href="salaryNoteList"  class="cancelbtn" ></a>  
		     </c:if>
		      <c:if test="${roleAndRights.editFlag=='false'}">
		      <input class="edit_btn"  type="button" onclick="checkEdit();" value=""/>
    	      <a href="salaryNoteList"  class="cancelbtn" ></a>  
		      </c:if>
		  </c:if>
          </c:forEach>	
	 		</c:when>
	 		<c:otherwise>
				<c:choose>
			<c:when test="${opr=='E'}">
				<input class="updatebtn" style="font-size: 11px; font-weight: bold;  padding: 0 0 0 30px;" type="submit" value=" "/>
				 <a href="salaryNoteList"  class="cancelbtn" ></a>    
			</c:when>
			<c:otherwise>
				 <input class="submit" style="font-size: 11px; font-weight: bold;  padding: 0 0 0 30px;" type="submit" value=" "/>
				  <a href="salaryNoteList"  class="cancelbtn" ></a>    
			</c:otherwise>
		</c:choose>
			</c:otherwise>
		</c:choose>
   </div>
         <div ><span style="margin-left:80px;" class="errmsg"></span>  </div>
    
    </div></table>
	</div>
	
	




</form:form>

