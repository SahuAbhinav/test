<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<script type="text/javascript" charset="utf-8">
$(document).ready(function(){ 
 $(".myTimePicker[readonly]").css("background-color","#ffffff" );
} );
</script>
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
		var redrctUrl='get_masters_list';
				
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
 		 var frank_param = getParam('mid');
 		 var fid = getParam('fid');
 		// alert(frank);
 		 //	confirm('Are you sure you want to delete?');
		 var delUrl='remove_masters?mid='+frank_param+'&fid='+fid;
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
 	
 	
 	

<c:if test="${opr=='V' && opr!=null}">
	<script type="text/javascript">
		$(document).ready(function() {
			$('input').attr('readonly', 'readonly');
			$('select').attr('disabled', 'disabled');
			$('textarea').attr('readonly', 'readonly');
			$('.datepicker').attr('disabled', 'disabled');
			$('input:radio').attr('disabled', true);
			$("input:checkbox").attr("disabled", true);
			$(".newWindow").attr("disabled", true);
			$(".myTimePicker").attr("disabled", true);
			$(".delelteImg").attr("disabled", true);
			$('.datepicker2').attr('disabled', 'disabled');
		});
	</script>
</c:if>

 	
  <script type="text/javascript">
	//	$(document).ready(function() {
		
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
		
 	function editMethod()
 	 { 
 		var mid = getParam('mid');
		var fid = getParam('fid');
		var tv = getParam('tv');
		
	 	 //	confirm('Are you sure you want to delete?');
	 var delUrl='get_masters?mid='+mid+'&fid='+fid+'&tv='+tv+'&opr=E';
 	 window.self.location = delUrl;  
	}
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
<c:if test="${opr=='E'}">
<script>
$(document).ready(function() {
	$('#code').attr('readonly','true');	
	
});
</script>
</c:if>

<script type="text/javascript">
	function checkEdit()
	{
	alert('Login User Not Permit to Edit Record !!!!!!');
	}
	//anonymous self invoking function to avoid conflicting with other JavaScript
	(function($) {
		//function is called when the page is fully loaded
		$(document).ready(function() {
			//the page is loaded so attach the time picker to an input field
			$(".myTimePicker").timepicker({});
		});
	})(jQuery);
</script>


<script>
	jQuery(document).ready(function() {
		// binds form submission and fields to the validation engine
		jQuery("#formID1").validationEngine();
	});

	function checkHELLO(field, rules, i, options) {
		if (field.val() != "HELLO") {
			// this allows to use i18 for the error msgs
			return options.allrules.validate2fields.alertText;
		}
	}
</script>
<script>
	jQuery(document).ready(function() {
		// binds form submission and fields to the validation engine
		jQuery("#formID2").validationEngine();
	});

	function checkHELLO(field, rules, i, options) {
		if (field.val() != "HELLO") {
			// this allows to use i18 for the error msgs
			return options.allrules.validate2fields.alertText;
		}
	}
</script>

<script>
	jQuery(document).ready(function() {
		// binds form submission and fields to the validation engine
		jQuery("#formID3").validationEngine();
	});

	function checkHELLO(field, rules, i, options) {
		if (field.val() != "HELLO") {
			// this allows to use i18 for the error msgs
			return options.allrules.validate2fields.alertText;
		}
	}
</script>

<script>
	jQuery(document).ready(function() {
		// binds form submission and fields to the validation engine
		jQuery("#formID4").validationEngine();
	});

	function checkHELLO(field, rules, i, options) {
		if (field.val() != "HELLO") {
			// this allows to use i18 for the error msgs
			return options.allrules.validate2fields.alertText;
		}
	}
</script>

<script>
	jQuery(document).ready(function() {
		// binds form submission and fields to the validation engine
		jQuery("#formID5").validationEngine();
	});

	function checkHELLO(field, rules, i, options) {
		if (field.val() != "HELLO") {
			// this allows to use i18 for the error msgs
			return options.allrules.validate2fields.alertText;
		}
	}
</script>

<script>
	jQuery(document).ready(function() {
		// binds form submission and fields to the validation engine
		jQuery("#formID6").validationEngine();
	});

	function checkHELLO(field, rules, i, options) {
		if (field.val() != "HELLO") {
			// this allows to use i18 for the error msgs
			return options.allrules.validate2fields.alertText;
		}
	}
</script>

<script>
	jQuery(document).ready(function() {
		// binds form submission and fields to the validation engine
		jQuery("#formID7").validationEngine();
	});

	function checkHELLO(field, rules, i, options) {
		if (field.val() != "HELLO") {
			// this allows to use i18 for the error msgs
			return options.allrules.validate2fields.alertText;
		}
	}
</script>

<script>
	jQuery(document).ready(function() {
		// binds form submission and fields to the validation engine
		jQuery("#formID8").validationEngine();
	});

	function checkHELLO(field, rules, i, options) {
		if (field.val() != "HELLO") {
			// this allows to use i18 for the error msgs
			return options.allrules.validate2fields.alertText;
		}
	}
</script>

<script>
	jQuery(document).ready(function() {
		// binds form submission and fields to the validation engine
		jQuery("#formID9").validationEngine();
	});

	function checkHELLO(field, rules, i, options) {
		if (field.val() != "HELLO") {
			// this allows to use i18 for the error msgs
			return options.allrules.validate2fields.alertText;
		}
	}
</script>

<script>
	jQuery(document).ready(function() {
		// binds form submission and fields to the validation engine
		jQuery("#formID10").validationEngine();
	});

	function checkHELLO(field, rules, i, options) {
		if (field.val() != "HELLO") {
			// this allows to use i18 for the error msgs
			return options.allrules.validate2fields.alertText;
		}
	}
</script>


<script>
	jQuery(document).ready(function() {
		// binds form submission and fields to the validation engine
		jQuery("#formID11").validationEngine();
	});

	function checkHELLO(field, rules, i, options) {
		if (field.val() != "HELLO") {
			// this allows to use i18 for the error msgs
			return options.allrules.validate2fields.alertText;
		}
	}
</script>

<script>
	jQuery(document).ready(function() {
		// binds form submission and fields to the validation engine
		jQuery("#formID12").validationEngine();
	});

	function checkHELLO(field, rules, i, options) {
		if (field.val() != "HELLO") {
			// this allows to use i18 for the error msgs
			return options.allrules.validate2fields.alertText;
		}
	}
</script>

<script>
	jQuery(document).ready(function() {
		// binds form submission and fields to the validation engine
		jQuery("#formID13").validationEngine();
	});

	function checkHELLO(field, rules, i, options) {
		if (field.val() != "HELLO") {
			// this allows to use i18 for the error msgs
			return options.allrules.validate2fields.alertText;
		}
	}
</script>

<script>
	jQuery(document).ready(function() {
		// binds form submission and fields to the validation engine
		jQuery("#formID14").validationEngine();
	});

	function checkHELLO(field, rules, i, options) {
		if (field.val() != "HELLO") {
			// this allows to use i18 for the error msgs
			return options.allrules.validate2fields.alertText;
		}
	}
</script>

<script>
	jQuery(document).ready(function() {
		// binds form submission and fields to the validation engine
		jQuery("#formID15").validationEngine();
	});

	function checkHELLO(field, rules, i, options) {
		if (field.val() != "HELLO") {
			// this allows to use i18 for the error msgs
			return options.allrules.validate2fields.alertText;
		}
	}
</script>
<script>
	jQuery(document).ready(function() {
		// binds form submission and fields to the validation engine
		jQuery("#formID16").validationEngine();
	});

	function checkHELLO(field, rules, i, options) {
		if (field.val() != "HELLO") {
			// this allows to use i18 for the error msgs
			return options.allrules.validate2fields.alertText;
		}
	}
</script>
<script>
	jQuery(document).ready(function() {
		// binds form submission and fields to the validation engine
		jQuery("#formID17").validationEngine();
	});

	function checkHELLO(field, rules, i, options) {
		if (field.val() != "HELLO") {
			// this allows to use i18 for the error msgs
			return options.allrules.validate2fields.alertText;
		}
	}
</script>


<script>
	jQuery(document).ready(function() {
		// binds form submission and fields to the validation engine
		jQuery("#formID18").validationEngine();
	});

	function checkHELLO(field, rules, i, options) {
		if (field.val() != "HELLO") {
			// this allows to use i18 for the error msgs
			return options.allrules.validate2fields.alertText;
		}
	}
</script>
<script>
	jQuery(document).ready(function() {
		// binds form submission and fields to the validation engine
		jQuery("#formID19").validationEngine();
	});

	function checkHELLO(field, rules, i, options) {
		if (field.val() != "HELLO") {
			// this allows to use i18 for the error msgs
			return options.allrules.validate2fields.alertText;
		}
	}
</script>
 <script type="text/javascript">
      $(document).ready(function()
       {
           	   
	  $( ".datepicker" ).datepicker({
		  changeMonth: true,
          changeYear: true,
		  yearRange: '-99:+10',
		  dateFormat: 'dd-M-yy' });
  //     
	  $('.datepicker').attr('readonly','true');	
      });
     </script>

<script>
	$(document).ready(function() {
		$(".cancelbtn").click(function() {
			window.self.location = "get_masters_list";
		});

	});
</script>

<script type="text/javascript">
	$(function() {
	$("#tabs").tabs();
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
    var tv = getParam('tv');
	$("#tabs").tabs("select", tv);	
	});
</script>

<style type="text/css">
body {
	font-family: Arial, Helvetica, sans-serif;
}

.ui-tabs .ui-tabs-nav li.ui-tabs-selected a,.ui-tabs .ui-tabs-nav li.ui-state-disabled a,.ui-tabs .ui-tabs-nav li.ui-state-processing a
	{
	background-color: #4e8ccf;
	color: #FFFFFF;
}

.bkgColor {
	background: #F6F6F6;
	border-color: #4E8CCF;
	width: 674px;
	float: left;
	height: 198px;
}
 .ui-datepicker table {
    border-collapse: collapse;
    font-size: 0.9em;
    margin: 0 0 0.4em;
    width: 100% !important;
} 
.ui-state-default,.ui-widget-content .ui-state-default,.ui-widget-header .ui-state-default
	{
	background-color: #4e8ccf !important;
}

.ui-state-default,.ui-widget-content .ui-state-default,.ui-widget-header .ui-state-default
	{
	background: none;
	color: #FFFFFF;
}

.ui-widget-content {
	background: none;
}

.ui-corner-all,.ui-corner-bottom,.ui-corner-right,.ui-corner-br {
	background-color: #e0ebff;
}

.ui-tabs .ui-tabs-nav li a,.ui-tabs-collapsible .ui-tabs-nav li.ui-tabs-active a
	{
	background-color: #e0ecff;
	color: #416aa3;
	font-weight: normal;
	font-family: Verdana, Arial, Helvetica, sans-serif;
	width: 106px;
}

.tabs li.tabs-selected a.tabs-inner {
	background: none !important;
}

.ui-tabs .ui-tabs-nav li {
	border: 1px solid #4E8CCF !important;
	border-radius: 0 0 0 0;
	width: 130px;
}

.tabs li a.tabs-inner {
	background: none !important;
	text-align: center !important;
}

.tabs li {
	width: 80px !important;
	text-align: center;
}

.easyui-tabs tabs-container {
	height: 150px !important;
}

.tabs-panels {
	height: 142px !important;
	padding: 0px !important;
	width: 800px;
	!
	important;
}

 div.ui-datepicker {
	font-size: 10px;
	width: 18%;
} 

.panel-header {
	width: 68%;
	height: 12px;
}

.ui-tabs .ui-tabs-panel {
	padding: 0px;
}

.ui-widget input,.ui-widget select,.ui-widget textarea,.ui-widget button
	{
	font-family: Verdana, Arial, Helvetica, sans-serif !important;
}
.btn {
    margin-left: 48px;
    margin-top: 12px;
}
table{
width: 580px;
}

.ui-widget-header {
	background: #4e8ccf !important;
	border: 1px solid #4E8CCF !important;
	padding-bottom: 12px;
}

#dlg-buttons {
	text-align: center;
	margin-top: 11px;
	float: left;
	margin-left: 97px;
}

.ui-tabs .ui-tabs-nav li.ui-tabs-active a,.ui-tabs .ui-tabs-nav li.ui-state-disabled a,.ui-tabs .ui-tabs-nav li.ui-tabs-loading a
	{
	background-color: #4e8ccf;
	color: #FFFFFF;
}

.ui-tabs-nav {
	background-color: #e0ecff !important;
}

#tabs .ui-tabs-nav {
	background-color: #e0ecff !important;
	font-size: 12px;
}

select {
	width: 151px;
	height: 20px;
}

h2 {
	text-align: center;
	font-size: 16px;
	margin: 11px 0 0 0;
}

.ui-tabs .ui-tabs-nav {
	padding: 0px;
}

th {
	font-size: 10px;
}

td {
	font-size: 12px;
}
.errorblock {
	color: #000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	margin-bottom: 16px;
	width: 82%;
}

.dis-able {
	display: inline;
}
</style>

<div id="tabs" style="width: 674px; height: 324px; border: 1px solid #4E8CCF; padding: 0px;">
	<ul style="background-color: #e0ecff; padding-bottom: 1px">
		<li><a href="#tabs-1">Cast</a></li>
		<li><a href="#tabs-2">Religion</a></li>
		<li><a href="#tabs-3" onclick="getTab();">Language</a></li>
		<li><a href="#tabs-4">Qualification</a></li>
		<li><a href="#tabs-5">Employee Type</a></li>
		<li><a href="#tabs-6">Employee Grade</a></li>
		<li><a href="#tabs-7">Designation</a></li>
		<li><a href="#tabs-8">Department</a></li>
		<li><a href="#tabs-9">Sub Department</a></li>
		<li><a href="#tabs-10">Holiday Master </a></li>
		<li><a href="#tabs-11">Shift master</a></li>
		<li><a href="#tabs-12">VAT Form Type</a></li>
		<li><a href="#tabs-13">Item Class</a></li>
		<li><a href="#tabs-14">Specialization</a></li>
		<li><a href="#tabs-15">Pack Type</a></li>
		<li><a href="#tabs-16">Item Grade</a></li>
		<li><a href="#tabs-17">Measurement Unit</a></li>
		 <li><a href="#tabs-18">Head</a></li>
        <li><a href="#tabs-19">Section</a></li>
	</ul>

	<c:if test="${opr=='E'|| opr=='V'}">
		<c:if test="${tv=='tabs-1'}">
			<form:form id="formID1" action="update_masters#tabs-1"	modelAttribute="mastersForm" method="post">
				<input type="hidden" name="tv" value="tabs-1" />
				<form:hidden path="mastersDTO.id" />
				<form:hidden path="mastersDTO.mastersId" />
				<input type="hidden" name="mastersDTO.formName" value="Cast" />
				<input type="hidden" name="mastersDTO.formId" value="1" />
				<div id="tabs-1" style="background-color: #FF0000; font-size: 11px">
					<div align="left" class="bkgColor">
						<h2>Cast</h2>

						<table class="" width="580" height="74" border="0" align="center">
							<tr>
								<td width="107" height="39" align="left"><label>Name<span
										style="color: #FF0000"> * </span> </label></td>
								<td width="202"><form:input onkeypress="return check(event)"   data-maxsize="55"
										class="validate[required] text-input" path="mastersDTO.name"
										size="22" id="name" /></td>
								<td width="61" height="39"><label> Code<span
										style="color: #FF0000"> *</span> </label></td>
								<td width="200"><form:input onkeyup="valid1(this)" onblur="valid1(this)" readonly="true"     path="mastersDTO.code"
										data-maxsize="16" class="validate[required] text-input"
										size="22" id="code" /></td>
							</tr>
							<tr>
								<td height="29" align="left"><label>Description</label></td>
								<td height="29" colspan="3">
								<form:textarea onkeypress="return check(event)" cols="64" data-maxsize="150"
										path="mastersDTO.description" size="67" id="description" />
								
							<%-- 	<form:input onkeypress="return check(event)" data-maxsize="150"
										path="mastersDTO.description" size="67" id="description" /> --%></td>
							</tr>
						</table>
	 <div class="btn">
	  <div class="savbtn">
		<c:if test="${opr=='V'}">
   		 
         <c:forEach items="${roleAndRightsDTOList}" var="roleAndRights">
		   <c:if test="${roleAndRights.menuId==sessionScope.menuId}">
    	  	 <c:if test="${roleAndRights.editFlag=='true'}">
		  	 <input class="edit_btn"  type="button" onclick="editMethod();" value=""/>
    	      <a href="get_masters_list"  class="cancelbtn" ></a>  
		     </c:if>
		      <c:if test="${roleAndRights.editFlag=='false'}">
		      <input class="edit_btn"  type="button" onclick="checkEdit();" value=""/>
    	      <a href="get_masters_list"  class="cancelbtn" ></a>  
		      </c:if>
		    
          </c:if>
          </c:forEach>	   		
    	</c:if>
    	
            <c:if test="${opr=='E'}">
              <input class="updatebtn"  type="submit" value=""/> 
             <a href="get_masters_list" class="cancelbtn" ></a>  
   		   	</c:if>
		</div>
		</div>
	</div>
	</form:form>
		</c:if>
		<c:if test="${tv=='tabs-2'}">
			<form:form id="formID2" action="update_masters#tabs-2"
				modelAttribute="mastersForm" method="post">
				<input type="hidden" name="tv" value="tabs-2" />
				<form:hidden path="mastersDTO.id" /><form:hidden path="mastersDTO.mastersId" />
				<input type="hidden" name="mastersDTO.formName" value="Religion" />
				<input type="hidden" name="mastersDTO.formId" value="2" />
				<div id="tabs-2" style="background-color: #FF0000; font-size: 11px">
					<div align="left" class="bkgColor">
						<h2>Religion</h2>
						<table class="" width="580" height="74" border="0" align="center">
							<tr>
								<td width="107" height="39" align="left"><label>Name<span
										style="color: #FF0000"> * </span> </label></td>
								<td width="202"><form:input onkeypress="return check(event)"   data-maxsize="55"
										class="validate[required] text-input" path="mastersDTO.name"
										size="22" id="name" /></td>
								<td width="61" height="39"><label> Code<span
										style="color: #FF0000"> *</span> </label></td>
								<td width="200"><form:input onkeyup="valid1(this)" onblur="valid1(this)" readonly="true"     path="mastersDTO.code"
										data-maxsize="16" class="validate[required] text-input"
										size="22" id="code" /></td>
							</tr>
							<tr>
								<td height="29" align="left"><label>Description</label></td>
								<td height="29" colspan="3"><form:textarea onkeypress="return check(event)" cols="64" data-maxsize="150"
										path="mastersDTO.description" size="67" id="description" /></td>
							</tr>
						</table>
	 <div class="btn">
		<div class="savbtn">
		<c:if test="${opr=='V'}">
   		<c:forEach items="${roleAndRightsDTOList}" var="roleAndRights">
		   <c:if test="${roleAndRights.menuId==sessionScope.menuId}">
    	  	 <c:if test="${roleAndRights.editFlag=='true'}">
		  	 <input class="edit_btn"  type="button" onclick="editMethod();" value=""/>
    	      <a href="get_masters_list"  class="cancelbtn" ></a>  
		     </c:if>
		      <c:if test="${roleAndRights.editFlag=='false'}">
		      <input class="edit_btn"  type="button" onclick="checkEdit();" value=""/>
    	      <a href="get_masters_list"  class="cancelbtn" ></a>  
		      </c:if>
		    
          </c:if>
          </c:forEach>  		
    	</c:if>
            <c:if test="${opr=='E'}">
               <input class="updatebtn"  type="submit" value=""/> 
                <a href="get_masters_list" class="cancelbtn" ></a>  
   		   	</c:if>	
		</div>
	</div>
			</div>
			</div>
			</form:form>
		</c:if>

		<c:if test="${tv=='tabs-3'}">

			<form:form id="formID4" action="update_masters#tabs-3"
				modelAttribute="mastersForm" method="post">
				<input type="hidden" name="tv" value="tabs-3" />
				<form:hidden path="mastersDTO.id" /><form:hidden path="mastersDTO.mastersId" />
				<input type="hidden" name="mastersDTO.formName" value="Language" />
				<input type="hidden" name="mastersDTO.formId" value="3" />

				<div id="tabs-3" style="background-color: #FF0000; font-size: 11px">
					<div align="left" class="bkgColor">
						<h2>Language</h2>
						<table class="" width="580" height="74" border="0" align="center">
							<tr>
								<td width="107" height="39" align="left"><label>Name<span
										style="color: #FF0000"> * </span> </label></td>
								<td width="202"><form:input onkeypress="return check(event)"   data-maxsize="55"
										class="validate[required] text-input" path="mastersDTO.name"
										size="22" id="name" /></td>
								<td width="61" height="39"><label> Code<span
										style="color: #FF0000"> *</span> </label></td>
								<td width="200"><form:input onkeyup="valid1(this)" onblur="valid1(this)" readonly="true"    path="mastersDTO.code"
										data-maxsize="16" class="validate[required] text-input"
										size="22" id="code" /></td>
							</tr>
							<tr>
								<td height="29" align="left"><label>Description</label></td>
								<td height="29" colspan="3"><form:textarea onkeypress="return check(event)" cols="64" data-maxsize="150"
										path="mastersDTO.description" size="67" id="description" /></td>
							</tr>
						</table>
						<div class="btn">
		<div class="savbtn">
		<c:if test="${opr=='V'}">
   		<c:forEach items="${roleAndRightsDTOList}" var="roleAndRights">
		   <c:if test="${roleAndRights.menuId==sessionScope.menuId}">
    	  	 <c:if test="${roleAndRights.editFlag=='true'}">
		  	 <input class="edit_btn"  type="button" onclick="editMethod();" value=""/>
    	      <a href="get_masters_list"  class="cancelbtn" ></a>  
		     </c:if>
		      <c:if test="${roleAndRights.editFlag=='false'}">
		      <input class="edit_btn"  type="button" onclick="checkEdit();" value=""/>
    	      <a href="get_masters_list"  class="cancelbtn" ></a>  
		      </c:if>
		    
          </c:if>
          </c:forEach>  		
    	</c:if>
            <c:if test="${opr=='E'}">
               <input class="updatebtn"  type="submit" value=""/> 
                <a href="get_masters_list" class="cancelbtn" ></a>  
   		   	</c:if>	
		</div>
	</div>
	
					</div>
				</div>
			</form:form>
		</c:if>

		<c:if test="${tv=='tabs-4'}">
			<form:form id="formID5" action="update_masters#tabs-4"
				modelAttribute="mastersForm" method="post">
				<input type="hidden" name="tv" value="tabs-4" />
				<form:hidden path="mastersDTO.id" /><form:hidden path="mastersDTO.mastersId" />
				<input type="hidden" name="mastersDTO.formName"
					value="Qualification" />
				<input type="hidden" name="mastersDTO.formId" value="4" />

				<div id="tabs-4" style="background-color: #FF0000; font-size: 11px">
					<div align="left" class="bkgColor">
						<h2>Qualification</h2>
						<table class="" width="580" height="74" border="0" align="center">
							<tr>
								<td width="107" height="39" align="left"><label>Name<span
										style="color: #FF0000"> * </span> </label></td>
								<td width="202"><form:input onkeypress="return check(event)"   data-maxsize="55"
										class="validate[required] text-input" path="mastersDTO.name"
										size="22" id="name" /></td>
								<td width="61" height="39"><label> Code<span
										style="color: #FF0000"> *</span> </label></td>
								<td width="200"><form:input path="mastersDTO.code"
										data-maxsize="16" onkeyup="valid1(this)" onblur="valid1(this)" readonly="true"    class="validate[required] text-input"
										size="22" id="code" /></td>
							</tr>
							<tr>
								<td height="29" align="left"><label>Description</label></td>
								<td height="29" colspan="3"><form:textarea onkeypress="return check(event)" cols="64" data-maxsize="150"
										path="mastersDTO.description" size="67" id="description" /></td>
							</tr>
						</table>
						<div class="btn">
		<div class="savbtn">
		<c:if test="${opr=='V'}">
   		<c:forEach items="${roleAndRightsDTOList}" var="roleAndRights">
		   <c:if test="${roleAndRights.menuId==sessionScope.menuId}">
    	  	 <c:if test="${roleAndRights.editFlag=='true'}">
		  	 <input class="edit_btn"  type="button" onclick="editMethod();" value=""/>
    	      <a href="get_masters_list"  class="cancelbtn" ></a>  
		     </c:if>
		      <c:if test="${roleAndRights.editFlag=='false'}">
		      <input class="edit_btn"  type="button" onclick="checkEdit();" value=""/>
    	      <a href="get_masters_list"  class="cancelbtn" ></a>  
		      </c:if>
		    
          </c:if>
          </c:forEach>    		
    	</c:if>
            <c:if test="${opr=='E'}">
               <input class="updatebtn"  type="submit" value=""/> 
                <a href="get_masters_list" class="cancelbtn" ></a>  
   		   	</c:if>	
		</div>
	</div>
						</div>
				</div>
			</form:form>
		</c:if>

		<c:if test="${tv=='tabs-5'}">

			<form:form id="formID7" action="update_masters#tabs-5"
				modelAttribute="mastersForm" method="post">
				<input type="hidden" name="tv" value="tabs-5" />
				<form:hidden path="mastersDTO.id" /><form:hidden path="mastersDTO.mastersId" />
				<input type="hidden" name="mastersDTO.formName"
					value="Employee Type" />
				<input type="hidden" name="mastersDTO.formId" value="5" />
				<div id="tabs-5" style="background-color: #FF0000; font-size: 11px">
					<div align="left" class="bkgColor">
						<h2>Employee Type</h2>
						<table class="" width="580" height="74" border="0" align="center">
							<tr>
								<td width="107" height="39" align="left"><label>Name<span
										style="color: #FF0000"> * </span> </label></td>
								<td width="202"><form:input onkeypress="return check(event)"   data-maxsize="55"
										class="validate[required] text-input" path="mastersDTO.name"
										size="22" id="name" /></td>
								<td width="61" height="39"><label> Code<span
										style="color: #FF0000"> *</span> </label></td>
								<td width="200"><form:input onkeyup="valid1(this)" onblur="valid1(this)" readonly="true"   path="mastersDTO.code"
										data-maxsize="16" class="validate[required] text-input"
										size="22" id="code" /></td>
							</tr>
							<tr>
								<td height="29" align="left"><label>Description</label></td>
								<td height="29" colspan="3"><form:textarea onkeypress="return check(event)" cols="64" data-maxsize="150"
										path="mastersDTO.description" size="67" id="description" /></td>
							</tr>
						</table>
						<div class="btn">
	<div class="savbtn">
		<c:if test="${opr=='V'}">
   		<c:forEach items="${roleAndRightsDTOList}" var="roleAndRights">
		   <c:if test="${roleAndRights.menuId==sessionScope.menuId}">
    	  	 <c:if test="${roleAndRights.editFlag=='true'}">
		  	 <input class="edit_btn"  type="button" onclick="editMethod();" value=""/>
    	      <a href="get_masters_list"  class="cancelbtn" ></a>  
		     </c:if>
		      <c:if test="${roleAndRights.editFlag=='false'}">
		      <input class="edit_btn"  type="button" onclick="checkEdit();" value=""/>
    	      <a href="get_masters_list"  class="cancelbtn" ></a>  
		      </c:if>
		    
          </c:if>
          </c:forEach> 		
    	</c:if>
            <c:if test="${opr=='E'}">
               <input class="updatebtn"  type="submit" value=""/> 
                <a href="get_masters_list" class="cancelbtn" ></a>  
   		   	</c:if>	
		</div>
	</div>
	
					</div>
				</div>
			</form:form>
		</c:if>

		<c:if test="${tv=='tabs-6'}">
			<form:form id="formID6" action="update_masters#tabs-6"
				modelAttribute="mastersForm" method="post">
				<input type="hidden" name="tv" value="tabs-6" />
				<form:hidden path="mastersDTO.id" /><form:hidden path="mastersDTO.mastersId" />
				<input type="hidden" name="mastersDTO.formName"
					value="Employee Grade" />
				<input type="hidden" name="mastersDTO.formId" value="6" />
				<div id="tabs-6" style="background-color: #FF0000; font-size: 11px">
					<div align="left" class="bkgColor">
						<h2>Employee Grade</h2>
						<table class="" width="566" height="97" border="0" align="center">
							<tr>
								<td height="27" align="left"><label>Name<span
										style="color: #FF0000"> * </span> </label>
								</td>
								<td><form:input path="mastersDTO.name" onkeypress="return check(event)"  data-maxsize="55"
										class="validate[required] text-input" size="22" id="name" />
								</td>
								<td height="27"><label> Code<span
										style="color: #FF0000"> *</span> </label></td>
								<td><form:input path="mastersDTO.code" onkeyup="valid1(this)" onblur="valid1(this)" readonly="true"   data-maxsize="16"
										class="validate[required] text-input" size="16" id="code" />
								</td>
							</tr>
							<tr>
								<td width="113" height="31" align="left"><label>
										Print sequence <span style="color: #FF0000"> * </span> </label></td>
								<td width="172"><form:input
										path="mastersDTO.gradePrintSeqNo" data-maxsize="16" style="text-align:right;"
										class="validate[required] text-input quantity" size="20"
										id="printSquence" /></td>
								<td width="90" height="31"><label></label></td>
								<td width="173">&nbsp;</td>
							</tr>
							<tr>
								<td height="29" align="left"><label>Description</label></td>
								<td height="29" colspan="3"><form:textarea onkeypress="return check(event)" cols="60" data-maxsize="150"
										path="mastersDTO.description" size="67" id="description" /></td>
							</tr>
						</table>
						<div class="btn">
		<div class="savbtn">
		<c:if test="${opr=='V'}">
   		<c:forEach items="${roleAndRightsDTOList}" var="roleAndRights">
		   <c:if test="${roleAndRights.menuId==sessionScope.menuId}">
    	  	 <c:if test="${roleAndRights.editFlag=='true'}">
		  	 <input class="edit_btn"  type="button" onclick="editMethod();" value=""/>
    	      <a href="get_masters_list"  class="cancelbtn" ></a>  
		     </c:if>
		      <c:if test="${roleAndRights.editFlag=='false'}">
		      <input class="edit_btn"  type="button" onclick="checkEdit();" value=""/>
    	      <a href="get_masters_list"  class="cancelbtn" ></a>  
		      </c:if>
		    
          </c:if>
          </c:forEach>     		
    	</c:if>
            <c:if test="${opr=='E'}">
               <input class="updatebtn"  type="submit" value=""/> 
                <a href="get_masters_list" class="cancelbtn" ></a>  
   		   	</c:if>	
		</div>
	</div>
	
						<div ><span style="margin-left:12px;" class="errmsg"></span></div>
					</div>
				</div>
			</form:form>
		</c:if>

		<c:if test="${tv=='tabs-7'}">
			<form:form id="formID11" action="update_masters#tabs-7"
				modelAttribute="mastersForm" method="post">
				<input type="hidden" name="tv" value="tabs-7" />
				<form:hidden path="mastersDTO.id" /><form:hidden path="mastersDTO.mastersId" />
				<input type="hidden" name="mastersDTO.formName" value="Designation" />
				<input type="hidden" name="mastersDTO.formId" value="7" />
				<div id="tabs-7" style="background-color: #FF0000; font-size: 11px">
					<div align="left" class="bkgColor">
						<h2>Designation</h2>
						<table class="" width="580" height="90" border="0" align="center">
							<tr>
								<td width="138" height="39" align="left"><label>Name<span
										style="color: #FF0000"> * </span> </label></td>
								<td width="202"><form:input path="mastersDTO.name"
										data-maxsize="55" onkeypress="return check(event)"   class="validate[required] text-input"
										size="22" id="name" /></td>
								<td width="61" height="39"><label> Code<span
										style="color: #FF0000"> *</span> </label></td>
								<td width="207"><form:input path="mastersDTO.code" onkeyup="valid1(this)" onblur="valid1(this)" readonly="true"  
										data-maxsize="16" class="validate[required] text-input"
										size="22" id="code" /></td>
							</tr>
							<tr>
								<td height="26" align="left"><label>Empolyee Type<span
										style="color: #FF0000"> * </span> </label></td>
								<td><form:select path="mastersDTO.empTypeId"
										items="${empTypeList}" itemLabel="name" itemValue="mastersId"
										class="validate[required] text-input" id="employeeType"></form:select>

								</td>
								<td height="26">&nbsp;</td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td height="29" align="left"><label>Description</label></td>
								<td height="29" colspan="3"><form:textarea onkeypress="return check(event)" cols="64" data-maxsize="150"
										path="mastersDTO.description" size="67" id="description" /></td>
							</tr>
						</table>
						<div class="btn">
		<div class="savbtn">
		<c:if test="${opr=='V'}">
   		<c:forEach items="${roleAndRightsDTOList}" var="roleAndRights">
		   <c:if test="${roleAndRights.menuId==sessionScope.menuId}">
    	  	 <c:if test="${roleAndRights.editFlag=='true'}">
		  	 <input class="edit_btn"  type="button" onclick="editMethod();" value=""/>
    	      <a href="get_masters_list"  class="cancelbtn" ></a>  
		     </c:if>
		      <c:if test="${roleAndRights.editFlag=='false'}">
		      <input class="edit_btn"  type="button" onclick="checkEdit();" value=""/>
    	      <a href="get_masters_list"  class="cancelbtn" ></a>  
		      </c:if>
		    
          </c:if>
          </c:forEach>   		
    	</c:if>
            <c:if test="${opr=='E'}">
               <input class="updatebtn"  type="submit" value=""/> 
                <a href="get_masters_list" class="cancelbtn" ></a>  
   		   	</c:if>	
		</div>
	</div>
	
					</div>
				</div>
			</form:form>
		</c:if>

		<c:if test="${tv=='tabs-8'}">
			<form:form id="formID9" action="update_masters#tabs-8"
				modelAttribute="mastersForm" method="post">
				<input type="hidden" name="tv" value="tabs-8" />
				<form:hidden path="mastersDTO.id" /><form:hidden path="mastersDTO.mastersId" />
				<input type="hidden" name="mastersDTO.formName" value="Department" />
				<input type="hidden" name="mastersDTO.formId" value="8" />

				<div id="tabs-8" style="background-color: #FF0000; font-size: 11px">
					<div align="left" class="bkgColor">
						<h2>Department</h2>
						<table class="" width="580" height="74" border="0" align="center">
							<tr>
								<td width="107" height="39" align="left"><label>Name<span
										style="color: #FF0000"> * </span> </label></td>
								<td width="202"><form:input onkeypress="return check(event)"   data-maxsize="55"
										class="validate[required] text-input" path="mastersDTO.name"
										size="22" id="name" /></td>
								<td width="61" height="39"><label> Code<span
										style="color: #FF0000"> *</span> </label></td>
								<td width="200"><form:input path="mastersDTO.code" onkeyup="valid1(this)" onblur="valid1(this)" readonly="true" 
										data-maxsize="16" class="validate[required] text-input"
										size="22" id="code" /></td>
							</tr>
							<tr>
								<td height="29" align="left"><label>Description</label></td>
								<td height="29" colspan="3"><form:textarea onkeypress="return check(event)" cols="64" data-maxsize="150"
										path="mastersDTO.description" size="67" id="description" /></td>
							</tr>
						</table>

						<div class="btn">
		<div class="savbtn">
		<c:if test="${opr=='V'}">
   		<c:forEach items="${roleAndRightsDTOList}" var="roleAndRights">
		   <c:if test="${roleAndRights.menuId==sessionScope.menuId}">
    	  	 <c:if test="${roleAndRights.editFlag=='true'}">
		  	 <input class="edit_btn"  type="button" onclick="editMethod();" value=""/>
    	      <a href="get_masters_list"  class="cancelbtn" ></a>  
		     </c:if>
		      <c:if test="${roleAndRights.editFlag=='false'}">
		      <input class="edit_btn"  type="button" onclick="checkEdit();" value=""/>
    	      <a href="get_masters_list"  class="cancelbtn" ></a>  
		      </c:if>
		    
          </c:if>
          </c:forEach>		
    	</c:if>
            <c:if test="${opr=='E'}">
               <input class="updatebtn"  type="submit" value=""/> 
                <a href="get_masters_list" class="cancelbtn" ></a>  
   		   	</c:if>	
		</div>
	</div>
	
					</div>
				</div>
			</form:form>
		</c:if>

		<c:if test="${tv=='tabs-9'}">
			<form:form id="formID10" action="update_masters#tabs-9"
				modelAttribute="mastersForm" method="post">
				<input type="hidden" name="tv" value="tabs-9" />
				<form:hidden path="mastersDTO.id" /><form:hidden path="mastersDTO.mastersId" />
				<input type="hidden" name="mastersDTO.formName"
					value="Sub Department" />
				<input type="hidden" name="mastersDTO.formId" value="9" />

				<div id="tabs-9" style="background-color: #FF0000; font-size: 11px">
					<div align="left" class="bkgColor">
						<h2>Sub Department</h2>
						<table class="" width="582" height="88" border="0" align="center">
							<tr>
								<td width="97" height="26" align="left"><label>Name<span
										style="color: #FF0000"> * </span> </label></td>
								<td width="206"><form:input path="mastersDTO.name"
										data-maxsize="55" onkeypress="return check(event)"  class="validate[required] text-input"
										size="22" id="name" /></td>
								<td width="53" height="26"><label> Code<span
										style="color: #FF0000"> *</span> </label></td>
								<td width="208"><form:input onkeyup="valid1(this)" onblur="valid1(this)" readonly="true"   path="mastersDTO.code"
										data-maxsize="16" class="validate[required] text-input"
										size="22" id="code" /></td>
							</tr>
							<tr>
								<td height="25" align="left"><label>Department<span
										style="color: #FF0000"> * </span> </label></td>
								<td><form:select path="mastersDTO.deptId"
										items="${deptTypeList}" itemLabel="name" itemValue="mastersId"
										data-maxsize="55" class="validate[required] text-input"
										id="department"></form:select>
								</td>
								<td height="25">&nbsp;</td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td height="29" align="left"><label>Description</label></td>
								<td height="29" colspan="3"><form:textarea onkeypress="return check(event)" cols="64" data-maxsize="150"
										path="mastersDTO.description" size="67" id="description" /></td>
							</tr>
						</table>
						<div class="btn">
		<div class="savbtn">
		<c:if test="${opr=='V'}">
   		<c:forEach items="${roleAndRightsDTOList}" var="roleAndRights">
		   <c:if test="${roleAndRights.menuId==sessionScope.menuId}">
    	  	 <c:if test="${roleAndRights.editFlag=='true'}">
		  	 <input class="edit_btn"  type="button" onclick="editMethod();" value=""/>
    	      <a href="get_masters_list"  class="cancelbtn" ></a>  
		     </c:if>
		      <c:if test="${roleAndRights.editFlag=='false'}">
		      <input class="edit_btn"  type="button" onclick="checkEdit();" value=""/>
    	      <a href="get_masters_list"  class="cancelbtn" ></a>  
		      </c:if>
		    
          </c:if>
          </c:forEach>    		
    	</c:if>
            <c:if test="${opr=='E'}">
               <input class="updatebtn"  type="submit" value=""/> 
                <a href="get_masters_list" class="cancelbtn" ></a>  
   		   	</c:if>	
		</div>
	</div>
	
					</div>
				</div>
			</form:form>
		</c:if>

		<c:if test="${tv=='tabs-10'}">
			<form:form id="formID12" action="update_masters#tabs-10"
				modelAttribute="mastersForm" method="post">
				<input type="hidden" name="tv" value="tabs-10" />
				<form:hidden path="mastersDTO.id" /><form:hidden path="mastersDTO.mastersId" />
				<input type="hidden" name="mastersDTO.formName"
					value="Holiday Master" />
				<input type="hidden" name="mastersDTO.formId" value="10" />
				<div id="tabs-10" style="background-color: #FF0000; font-size: 11px">
					<div align="left" class="bkgColor">
						<h2>Holiday Master</h2>
						<table class="" width="" height="88" border="0" align="center">
							<tr>
								<td width="153" height="24" align="left"><label>&nbsp;Name<span
										style="color: #FF0000"> * </span> </label></td>
								<td width="133"><form:input path="mastersDTO.name" onkeypress="return check(event)"  
										data-maxsize="55" class="validate[required] text-input"
										size="18" id="name" /></td>
								<td width="154" height="24"><label> Code<span
										style="color: #FF0000"> *</span> </label></td>
								<td width="135"><form:input onkeyup="valid1(this)" onblur="valid1(this)" readonly="true"   path="mastersDTO.code"
										data-maxsize="16" class="validate[required] text-input"
										size="18" id="code" /></td>
							</tr>
							<tr>
								<td height="27" align="left"><label> Holiday From
										Date<span style="color: #FF0000"> * </span> </label></td>
								<td><form:input path="mastersDTO.holidayFromDate"
										id="holidayFromDate"
										class="datepicker validate[required] text-input" size="18" />
								</td>
								<td height="27" align="left"><label> Holiday To
										Date<span style="color: #FF0000"> * </span> </label></td>
								<td><form:input path="mastersDTO.holidayToDate"
										id="holidayToDate"
										class="datepicker validate[required] text-input" size="18" />
								</td>
							</tr>
							<tr>
								<td height="29" align="left"><label>Description</label></td>
								<td height="29" colspan="3"><form:textarea onkeypress="return check(event)" cols="64" data-maxsize="150"
										path="mastersDTO.description" size="67" id="description" /></td>
							</tr>
						</table>
						<div class="btn">
		<div class="savbtn">
		<c:if test="${opr=='V'}">
   		<c:forEach items="${roleAndRightsDTOList}" var="roleAndRights">
		   <c:if test="${roleAndRights.menuId==sessionScope.menuId}">
    	  	 <c:if test="${roleAndRights.editFlag=='true'}">
		  	 <input class="edit_btn"  type="button" onclick="editMethod();" value=""/>
    	      <a href="get_masters_list"  class="cancelbtn" ></a>  
		     </c:if>
		      <c:if test="${roleAndRights.editFlag=='false'}">
		      <input class="edit_btn"  type="button" onclick="checkEdit();" value=""/>
    	      <a href="get_masters_list"  class="cancelbtn" ></a>  
		      </c:if>
		    
          </c:if>
          </c:forEach>   		
    	</c:if>
            <c:if test="${opr=='E'}">
               <input class="updatebtn"  type="submit" value=""/> 
                <a href="get_masters_list" class="cancelbtn" ></a>  
   		   	</c:if>	
		</div>
	</div>
	
					</div>
				</div>
			</form:form>
		</c:if>

		<c:if test="${tv=='tabs-11'}">
			<form:form id="formID13" action="update_masters#tabs-11"
				modelAttribute="mastersForm" method="post">
				<input type="hidden" name="tv" value="tabs-11" />
				<form:hidden path="mastersDTO.id" /><form:hidden path="mastersDTO.mastersId" />
				<input type="hidden" name="mastersDTO.formName" value="Shift Master" />
				<input type="hidden" name="mastersDTO.formId" value="11" />
				<div id="tabs-11" style="background-color: #FF0000; font-size: 11px">
					<div align="left" class="bkgColor">
						<h2>Shift master</h2>

						<table class="" width="" height="109" border="0" align="center">
							<tr>
								<td width="134" height="24" align="left"><label>Name<span
										style="color: #FF0000"> * </span> </label></td>
								<td width="158"><form:input onkeypress="return check(event)"  path="mastersDTO.name"
										data-maxsize="55" class="validate[required] text-input"
										size="18" id="name" /></td>
								<td width="128" height="24"><label> Code<span
										style="color: #FF0000"> *</span> </label></td>
								<td width="143"><form:input onkeyup="valid1(this)" onblur="valid1(this)" readonly="true"   path="mastersDTO.code"
										data-maxsize="16" class="validate[required] text-input"
										size="18" id="code" /></td>
							</tr>
							<tr>
								<td height="27" align="left"><label> Shift Start
										Time<span style="color: #FF0000"> * </span> </label></td>
								<td><form:input path="mastersDTO.shiftFromTime"
										class="myTimePicker validate[required] text-input" readonly="true" size="18"
										id="shiftStartTime" /></td>
								<td height="27" align="left"><label> Shift End Time<span
										style="color: #FF0000"> * </span> </label></td>
								<td><form:input path="mastersDTO.shiftToTime" readonly="true"
										class="myTimePicker validate[required] text-input" size="18"
										id="shiftEndTime" /></td>
							</tr>
							<tr>
								<td height="24" align="left"><label>Working Hour<span
										style="color: #FF0000"> * </span> </label></td>
								<td><form:input path="mastersDTO.workingHour" readonly="true"
										class="myTimePicker validate[required] text-input" size="18"
										id="workingHour" /></td>
								<td height="24" align="left"><label>Late Permit<span
										style="color: #FF0000"> * </span> </label></td>
								<td><form:input path="mastersDTO.latePermit" readonly="true"
										class="myTimePicker validate[required] text-input" size="18"
										id="letePermit" /></td>
							</tr>
							<tr>
								<td height="24" align="left"><label>Description</label></td>
								<td height="24" colspan="3"><form:textarea onkeypress="return check(event)" cols="64" data-maxsize="150"
										path="mastersDTO.description" size="67" id="description" /></td>
							</tr>
						</table>
						<div class="btn">
		<div class="savbtn">
		<c:if test="${opr=='V'}">
   		<c:forEach items="${roleAndRightsDTOList}" var="roleAndRights">
		   <c:if test="${roleAndRights.menuId==sessionScope.menuId}">
    	  	 <c:if test="${roleAndRights.editFlag=='true'}">
		  	 <input class="edit_btn"  type="button" onclick="editMethod();" value=""/>
    	      <a href="get_masters_list"  class="cancelbtn" ></a>  
		     </c:if>
		      <c:if test="${roleAndRights.editFlag=='false'}">
		      <input class="edit_btn"  type="button" onclick="checkEdit();" value=""/>
    	      <a href="get_masters_list"  class="cancelbtn" ></a>  
		      </c:if>
		    
          </c:if>
          </c:forEach>  		
    	</c:if>
            <c:if test="${opr=='E'}">
               <input class="updatebtn"  type="submit" value=""/> 
                <a href="get_masters_list" class="cancelbtn" ></a>  
   		   	</c:if>	
		</div>
	</div>
	
					</div>
				</div>
			</form:form>
		</c:if>

		<c:if test="${tv=='tabs-12'}">


			<form:form id="formID8" action="update_masters#tabs-12"
				modelAttribute="mastersForm" method="post">
				<input type="hidden" name="tv" value="tabs-12" />
				<form:hidden path="mastersDTO.id" /><form:hidden path="mastersDTO.mastersId" />
				<input type="hidden" name="mastersDTO.formName"
					value="VAT Form Type" />
				<input type="hidden" name="mastersDTO.formId" value="12" />

				<div id="tabs-12" style="background-color: #FF0000; font-size: 11px">
					<div align="left" class="bkgColor">
						<h2>VAT Form Type</h2>
						<table class="" width="580" height="74" border="0" align="center">
							<tr>
								<td width="107" height="39" align="left"><label>Name<span
										style="color: #FF0000"> * </span> </label></td>
								<td width="202"><form:input onkeypress="return check(event)"   data-maxsize="55"
										class="validate[required] text-input" path="mastersDTO.name"
										size="22" id="name" /></td>
								<td width="61" height="39"><label> Code<span
										style="color: #FF0000"> *</span> </label></td>
								<td width="200"><form:input path="mastersDTO.code" onkeyup="valid1(this)" onblur="valid1(this)" readonly="true"  
										data-maxsize="16" class="validate[required] text-input"
										size="22" id="code" /></td>
							</tr>
							<tr>
								<td height="29" align="left"><label>Description</label></td>
								<td height="29" colspan="3"><form:textarea onkeypress="return check(event)" cols="64" data-maxsize="150"
										path="mastersDTO.description" size="67" id="description" /></td>
							</tr>
						</table>

						<div class="btn">
		<div class="savbtn">
		<c:if test="${opr=='V'}">
   		<c:forEach items="${roleAndRightsDTOList}" var="roleAndRights">
		   <c:if test="${roleAndRights.menuId==sessionScope.menuId}">
    	  	 <c:if test="${roleAndRights.editFlag=='true'}">
		  	 <input class="edit_btn"  type="button" onclick="editMethod();" value=""/>
    	      <a href="get_masters_list"  class="cancelbtn" ></a>  
		     </c:if>
		      <c:if test="${roleAndRights.editFlag=='false'}">
		      <input class="edit_btn"  type="button" onclick="checkEdit();" value=""/>
    	      <a href="get_masters_list"  class="cancelbtn" ></a>  
		      </c:if>
		    
          </c:if>
          </c:forEach>   		
    	</c:if>
            <c:if test="${opr=='E'}">
               <input class="updatebtn"  type="submit" value=""/> 
                <a href="get_masters_list" class="cancelbtn" ></a>  
   		   	</c:if>	
		</div>
	</div>
	
					</div>
				</div>
			</form:form>
		</c:if>

		<c:if test="${tv=='tabs-13'}">

			<form:form id="formID14" action="update_masters#tabs-13"
				modelAttribute="mastersForm" method="post">
				<input type="hidden" name="tv" value="tabs-13" />
				<form:hidden path="mastersDTO.id" /><form:hidden path="mastersDTO.mastersId" />
				<input type="hidden" name="mastersDTO.formName" value="Item Class" />
				<input type="hidden" name="mastersDTO.formId" value="13" />

				<div id="tabs-13" style="background-color: #FF0000; font-size: 11px">
					<div align="left" class="bkgColor">
						<h2>Item Class</h2>
						<table class="" width="580" height="74" border="0" align="center">
							<tr>
								<td width="107" height="39" align="left"><label>Name<span
										style="color: #FF0000"> * </span> </label></td>
								<td width="202"><form:input onkeypress="return check(event)"   data-maxsize="55"
										class="validate[required] text-input" path="mastersDTO.name"
										size="22" id="name" /></td>
								<td width="61" height="39"><label> Code<span
										style="color: #FF0000"> *</span> </label></td>
								<td width="200"><form:input onkeyup="valid1(this)" onblur="valid1(this)" readonly="true"   path="mastersDTO.code"
										data-maxsize="16" class="validate[required] text-input"
										size="22" id="code" /></td>
							</tr>
							<tr>
								<td height="29" align="left"><label>Description</label></td>
								<td height="29" colspan="3"><form:textarea onkeypress="return check(event)" cols="64" data-maxsize="150"
										path="mastersDTO.description" size="67" id="description" /></td>
							</tr>
						</table>

						<div class="btn">
		<div class="savbtn">
		<c:if test="${opr=='V'}">
   		<c:forEach items="${roleAndRightsDTOList}" var="roleAndRights">
		   <c:if test="${roleAndRights.menuId==sessionScope.menuId}">
    	  	 <c:if test="${roleAndRights.editFlag=='true'}">
		  	 <input class="edit_btn"  type="button" onclick="editMethod();" value=""/>
    	      <a href="get_masters_list"  class="cancelbtn" ></a>  
		     </c:if>
		      <c:if test="${roleAndRights.editFlag=='false'}">
		      <input class="edit_btn"  type="button" onclick="checkEdit();" value=""/>
    	      <a href="get_masters_list"  class="cancelbtn" ></a>  
		      </c:if>
		    
          </c:if>
          </c:forEach>    		
    	</c:if>
            <c:if test="${opr=='E'}">
               <input class="updatebtn"  type="submit" value=""/> 
                <a href="get_masters_list" class="cancelbtn" ></a>  
   		   	</c:if>	
		</div>
	</div>
	
					</div>
				</div>
			</form:form>
		</c:if>

		<c:if test="${tv=='tabs-14'}">
			<form:form id="formID3" action="update_masters#tabs-14"
				modelAttribute="mastersForm" method="post">
				<input type="hidden" name="tv" value="tabs-14" />
				<form:hidden path="mastersDTO.id" /><form:hidden path="mastersDTO.mastersId" />
				<input type="hidden" name="mastersDTO.formName"
					value="Specialisation" />
				<input type="hidden" name="mastersDTO.formId" value="14" />

				<div id="tabs-14" style="background-color: #FF0000; font-size: 11px">
					<div align="left" class="bkgColor">
						<h2>Specialization</h2>
						<table class="" width="580" height="74" border="0" align="center">
							<tr>
								<td width="107" height="39" align="left"><label>Name<span
										style="color: #FF0000"> * </span> </label></td>
								<td width="202"><form:input onkeypress="return check(event)"   data-maxsize="55"
										class="validate[required] text-input" path="mastersDTO.name"
										size="22" id="name" /></td>
								<td width="61" height="39"><label> Code<span
										style="color: #FF0000"> *</span> </label></td>
								<td width="200"><form:input onkeyup="valid1(this)" onblur="valid1(this)" readonly="true"   path="mastersDTO.code"
										data-maxsize="16" class="validate[required] text-input"
										size="22" id="code" /></td>
							</tr>
							<tr>
								<td height="29" align="left"><label>Description</label></td>
								<td height="29" colspan="3"><form:textarea onkeypress="return check(event)" cols="64" data-maxsize="150"
										path="mastersDTO.description" size="67" id="description" /></td>
							</tr>
						</table>

						<div class="btn">
		<div class="savbtn">
		<c:if test="${opr=='V'}">
   		<c:forEach items="${roleAndRightsDTOList}" var="roleAndRights">
		   <c:if test="${roleAndRights.menuId==sessionScope.menuId}">
    	  	 <c:if test="${roleAndRights.editFlag=='true'}">
		  	 <input class="edit_btn"  type="button" onclick="editMethod();" value=""/>
    	      <a href="get_masters_list"  class="cancelbtn" ></a>  
		     </c:if>
		      <c:if test="${roleAndRights.editFlag=='false'}">
		      <input class="edit_btn"  type="button" onclick="checkEdit();" value=""/>
    	      <a href="get_masters_list"  class="cancelbtn" ></a>  
		      </c:if>
		    
          </c:if>
          </c:forEach>   		
    	</c:if>
            <c:if test="${opr=='E'}">
               <input class="updatebtn"  type="submit" value=""/> 
                <a href="get_masters_list" class="cancelbtn" ></a>  
   		   	</c:if>	
		</div>
	</div>
	
					</div>
				</div>
			</form:form>
		</c:if>

		<c:if test="${tv=='tabs-15'}">
			<form:form id="formID15" action="update_masters#tabs-15"
				modelAttribute="mastersForm" method="post">
				<input type="hidden" name="tv" value="tabs-15" />
				<form:hidden path="mastersDTO.id" /><form:hidden path="mastersDTO.mastersId" />
				<input type="hidden" name="mastersDTO.formName" value="Pack Type" />
				<input type="hidden" name="mastersDTO.formId" value="15" />

				<div id="tabs-15" style="background-color: #FF0000; font-size: 11px">
					<div align="left" class="bkgColor">
						<h2>Pack Type</h2>
						<table class="" width="580" height="74" border="0" align="center">
							<tr>
								<td width="107" height="39" align="left"><label>Name<span
										style="color: #FF0000"> * </span> </label></td>
								<td width="202"><form:input onkeypress="return check(event)"   data-maxsize="55"
										class="validate[required] text-input" path="mastersDTO.name"
										size="22" id="name" /></td>
								<td width="61" height="39"><label> Code<span
										style="color: #FF0000"> *</span> </label></td>
								<td width="200"><form:input onkeyup="valid1(this)" onblur="valid1(this)" readonly="true"   path="mastersDTO.code"
										data-maxsize="16" class="validate[required] text-input"
										size="22" id="code" /></td>
							</tr>
							<tr>
								<td height="29" align="left"><label>Description</label></td>
								<td height="29" colspan="3"><form:textarea onkeypress="return check(event)" cols="64" data-maxsize="150"
										path="mastersDTO.description" size="67" id="description" /></td>
							</tr>
						</table>

						<div class="btn">
		<div class="savbtn">
		<c:if test="${opr=='V'}">
   		<c:forEach items="${roleAndRightsDTOList}" var="roleAndRights">
		   <c:if test="${roleAndRights.menuId==sessionScope.menuId}">
    	  	 <c:if test="${roleAndRights.editFlag=='true'}">
		  	 <input class="edit_btn"  type="button" onclick="editMethod();" value=""/>
    	      <a href="get_masters_list"  class="cancelbtn" ></a>  
		     </c:if>
		      <c:if test="${roleAndRights.editFlag=='false'}">
		      <input class="edit_btn"  type="button" onclick="checkEdit();" value=""/>
    	      <a href="get_masters_list"  class="cancelbtn" ></a>  
		      </c:if>
		    
          </c:if>
          </c:forEach>     		
    	</c:if>
            <c:if test="${opr=='E'}">
               <input class="updatebtn"  type="submit" value=""/> 
                <a href="get_masters_list" class="cancelbtn" ></a>  
   		   	</c:if>	
		</div>
	</div>
	
					</div>
				</div>
			</form:form>
		</c:if>

		<c:if test="${tv=='tabs-16'}">
			<form:form id="formID16" action="update_masters#tabs-16"
				modelAttribute="mastersForm" method="post">
				<input type="hidden" name="tv" value="tabs-16" />
				<form:hidden path="mastersDTO.id" /><form:hidden path="mastersDTO.mastersId" />
				<input type="hidden" name="mastersDTO.formName" value="Item Grade" />
				<input type="hidden" name="mastersDTO.formId" value="16" />

				<div id="tabs-16" style="background-color: #FF0000; font-size: 11px">
					<div align="left" class="bkgColor">
						<h2>Item Grade</h2>
						<table class="" width="580" height="74" border="0" align="center">
							<tr>
								<td width="107" height="39" align="left"><label>Name<span
										style="color: #FF0000"> * </span> </label></td>
								<td width="202"><form:input data-maxsize="55" onkeypress="return check(event)"  
										class="validate[required] text-input" path="mastersDTO.name"
										size="22" id="name" /></td>
								<td width="61" height="39"><label> Code<span
										style="color: #FF0000"> *</span> </label></td>
								<td width="200"><form:input onkeyup="valid1(this)" onblur="valid1(this)" readonly="true"   path="mastersDTO.code"
										data-maxsize="16" class="validate[required] text-input"
										size="22" id="code" /></td>
							</tr>
							<tr>
								<td height="29" align="left"><label>Description</label></td>
								<td height="29" colspan="3"><form:textarea onkeypress="return check(event)" cols="64" data-maxsize="150"
										path="mastersDTO.description" size="67" id="description" /></td>
							</tr>
						</table>

						<div class="btn">
		<div class="savbtn">
		<c:if test="${opr=='V'}">
   		<c:forEach items="${roleAndRightsDTOList}" var="roleAndRights">
		   <c:if test="${roleAndRights.menuId==sessionScope.menuId}">
    	  	 <c:if test="${roleAndRights.editFlag=='true'}">
		  	 <input class="edit_btn"  type="button" onclick="editMethod();" value=""/>
    	      <a href="get_masters_list"  class="cancelbtn" ></a>  
		     </c:if>
		      <c:if test="${roleAndRights.editFlag=='false'}">
		      <input class="edit_btn"  type="button" onclick="checkEdit();" value=""/>
    	      <a href="get_masters_list"  class="cancelbtn" ></a>  
		      </c:if>
		    
          </c:if>
          </c:forEach>    		
    	</c:if>
            <c:if test="${opr=='E'}">
               <input class="updatebtn"  type="submit" value=""/> 
                <a href="get_masters_list" class="cancelbtn" ></a>  
   		   	</c:if>	
		</div>
	</div>
	
					</div>
				</div>
			</form:form>
		</c:if>
		<c:if test="${tv=='tabs-17'}">
			<form:form id="formID17" action="update_masters#tabs-17"
				modelAttribute="mastersForm" method="post">
				<input type="hidden" name="tv" value="tabs-17" />
				<form:hidden path="mastersDTO.id" /><form:hidden path="mastersDTO.mastersId" />
				<input type="hidden" name="mastersDTO.formName"
					value="Measurement Unit" />
				<input type="hidden" name="mastersDTO.formId" value="17" />

				<div id="tabs-17" style="background-color: #FF0000; font-size: 11px">
					<div align="left" class="bkgColor">
						<h2>Measurement Unit</h2>
						<table class="" width="580" height="74" border="0" align="center">
							<tr>
								<td width="107" height="39" align="left"><label>Name<span
										style="color: #FF0000"> * </span> </label></td>
								<td width="202"><form:input data-maxsize="55" onkeypress="return check(event)"  
										class="validate[required] text-input" path="mastersDTO.name"
										size="22" id="name" /></td>
								<td width="61" height="39"><label> Code<span
										style="color: #FF0000"> *</span> </label></td>
								<td width="200"><form:input onkeyup="valid1(this)" onblur="valid1(this)" readonly="true"   path="mastersDTO.code"
										data-maxsize="16" class="validate[required] text-input"
										size="22" id="code" /></td>
							</tr>
							<tr>
								<td height="29" align="left"><label>Description</label></td>
								<td height="29" colspan="3"><form:textarea onkeypress="return check(event)" cols="64" data-maxsize="150"
										path="mastersDTO.description" size="67" id="description" /></td>
							</tr>
						</table>

						<div class="btn">
		<div class="savbtn">
		<c:if test="${opr=='V'}">
   		<c:forEach items="${roleAndRightsDTOList}" var="roleAndRights">
		   <c:if test="${roleAndRights.menuId==sessionScope.menuId}">
    	  	 <c:if test="${roleAndRights.editFlag=='true'}">
		  	 <input class="edit_btn"  type="button" onclick="editMethod();" value=""/>
    	      <a href="get_masters_list"  class="cancelbtn" ></a>  
		     </c:if>
		      <c:if test="${roleAndRights.editFlag=='false'}">
		      <input class="edit_btn"  type="button" onclick="checkEdit();" value=""/>
    	      <a href="get_masters_list"  class="cancelbtn" ></a>  
		      </c:if>
		    
          </c:if>
          </c:forEach>     		
    	</c:if>
            <c:if test="${opr=='E'}">
               <input class="updatebtn"  type="submit" value=""/> 
                <a href="get_masters_list" class="cancelbtn" ></a>  
   		   	</c:if>	
		</div>
	</div>
	
					</div>
				</div>
			</form:form>
		</c:if>
	</c:if>


	<!-- 								Remove     						-->




	<c:if test="${opr=='R'}">
		<c:if test="${tv=='tabs-1'}">
			<form:form id="formID1" action="update_masters#tabs-1"
				modelAttribute="mastersForm" method="post">
				<input type="hidden" name="tv" value="tabs-1" />
				<form:hidden path="mastersDTO.id" /><form:hidden path="mastersDTO.mastersId" />
				<input type="hidden" name="mastersDTO.formName" value="Cast" />
				<input type="hidden" name="mastersDTO.formId" value="1" />
				<div id="tabs-1" style="background-color: #FF0000; font-size: 11px">
					<div align="left" class="bkgColor">
						<h2>Cast</h2>

						<table class="" width="580" height="74" border="0" align="center">
							<tr>
								<td width="107" height="39" align="left"><label>Name<span
										style="color: #FF0000"> * </span> </label></td>
								<td width="202"><form:input data-maxsize="55"
										class="validate[required] text-input" path="mastersDTO.name"
										size="22" id="name" /></td>
								<td width="61" height="39"><label> Code<span
										style="color: #FF0000"> *</span> </label></td>
								<td width="200"><form:input path="mastersDTO.code"
										data-maxsize="16" class="validate[required] text-input"
										size="22" id="code" /></td>
							</tr>
							<tr>
								<td height="29" align="left"><label>Description</label></td>
								<td height="29" colspan="3"><form:textarea onkeypress="return check(event)" cols="64" data-maxsize="150"
										path="mastersDTO.description" size="67" id="description" /></td>
							</tr>
						</table>
						<div class="btn">
							<div class="removebtn">
								<c:url value="remove_masters" var="remove_url">
									<c:param name="mid" value="${mastersForm.mastersDTO.mastersId}"></c:param>
									<c:param name="fid" value="${mastersForm.mastersDTO.formId}"></c:param>
								</c:url>
								 
							</div>

							<div >
								 
							</div>
						</div>

					</div>
				</div>
			</form:form>
		</c:if>
		<c:if test="${tv=='tabs-2'}">
			<form:form id="formID2" action="update_masters#tabs-2"
				modelAttribute="mastersForm" method="post">
				<input type="hidden" name="tv" value="tabs-2" />
				<form:hidden path="mastersDTO.id" /><form:hidden path="mastersDTO.mastersId" />
				<input type="hidden" name="mastersDTO.formName" value="Religion" />
				<input type="hidden" name="mastersDTO.formId" value="2" />
				<div id="tabs-2" style="background-color: #FF0000; font-size: 11px">
					<div align="left" class="bkgColor">
						<h2>Religion</h2>
						<table class="" width="580" height="74" border="0" align="center">
							<tr>
								<td width="99" height="39" align="left"><label>Name<span
										style="color: #FF0000"> * </span> </label></td>
								<td width="202"><form:input data-maxsize="55"
										class="validate[required] text-input" path="mastersDTO.name"
										size="22" id="name" /></td>
								<td width="61" height="39"><label> Code<span
										style="color: #FF0000"> *</span> </label></td>
								<td width="200"><form:input path="mastersDTO.code"
										data-maxsize="16" class="validate[required] text-input"
										size="22" id="code" /></td>
							</tr>
							<tr>
								<td height="29" align="left"><label>Description</label></td>
								<td height="29" colspan="3"><form:textarea onkeypress="return check(event)" cols="64" data-maxsize="150"
										path="mastersDTO.description" size="67" id="description" /></td>
							</tr>
						</table>
						<div class="btn">
							<div class="removebtn">
								<c:url value="remove_masters" var="remove_url">
									<c:param name="mid" value="${mastersForm.mastersDTO.mastersId}"></c:param>
									<c:param name="fid" value="${mastersForm.mastersDTO.formId}"></c:param>
								</c:url>
								 
							</div>

							<div >
								 
							</div>
						</div>
					</div>
				</div>
			</form:form>
		</c:if>

		<c:if test="${tv=='tabs-3'}">

			<form:form id="formID4" action="update_masters#tabs-3"
				modelAttribute="mastersForm" method="post">
				<input type="hidden" name="tv" value="tabs-3" />
				<form:hidden path="mastersDTO.id" /><form:hidden path="mastersDTO.mastersId" />
				<input type="hidden" name="mastersDTO.formName" value="Language" />
				<input type="hidden" name="mastersDTO.formId" value="3" />

				<div id="tabs-3" style="background-color: #FF0000; font-size: 11px">
					<div align="left" class="bkgColor">
						<h2>Language</h2>
						<table class="" width="580" height="74" border="0" align="center">
							<tr>
								<td width="99" height="39" align="left"><label>Name<span
										style="color: #FF0000"> * </span> </label></td>
								<td width="202"><form:input data-maxsize="55"
										class="validate[required] text-input" path="mastersDTO.name"
										size="22" id="name" /></td>
								<td width="61" height="39"><label> Code<span
										style="color: #FF0000"> *</span> </label></td>
								<td width="200"><form:input path="mastersDTO.code"
										data-maxsize="16" class="validate[required] text-input"
										size="22" id="code" /></td>
							</tr>
							<tr>
								<td height="29" align="left"><label>Description</label></td>
								<td height="29" colspan="3"><form:textarea onkeypress="return check(event)" cols="64" data-maxsize="150"
										path="mastersDTO.description" size="67" id="description" /></td>
							</tr>
						</table>
						<div class="btn">
							<div class="removebtn">
								<c:url value="remove_masters" var="remove_url">
									<c:param name="mid" value="${mastersForm.mastersDTO.mastersId}"></c:param>
									<c:param name="fid" value="${mastersForm.mastersDTO.formId}"></c:param>
								</c:url>
							 
							</div>

							<div>
							 
							</div>
						</div>
					</div>
				</div>
			</form:form>
		</c:if>

		<c:if test="${tv=='tabs-4'}">
			<form:form id="formID5" action="update_masters#tabs-4"
				modelAttribute="mastersForm" method="post">
				<input type="hidden" name="tv" value="tabs-4" />
				<form:hidden path="mastersDTO.id" /><form:hidden path="mastersDTO.mastersId" />
				<input type="hidden" name="mastersDTO.formName"
					value="Qualification" />
				<input type="hidden" name="mastersDTO.formId" value="4" />

				<div id="tabs-4" style="background-color: #FF0000; font-size: 11px">
					<div align="left" class="bkgColor">
						<h2>Qualification</h2>
						<table class="" width="580" height="74" border="0" align="center">
							<tr>
								<td width="99" height="39" align="left"><label>Name<span
										style="color: #FF0000"> * </span> </label></td>
								<td width="202"><form:input data-maxsize="55"
										class="validate[required] text-input" path="mastersDTO.name"
										size="22" id="name" /></td>
								<td width="61" height="39"><label> Code<span
										style="color: #FF0000"> *</span> </label></td>
								<td width="200"><form:input path="mastersDTO.code"
										data-maxsize="16" class="validate[required] text-input"
										size="22" id="code" /></td>
							</tr>
							<tr>
								<td height="29" align="left"><label>Description</label></td>
								<td height="29" colspan="3"><form:textarea onkeypress="return check(event)" cols="64" data-maxsize="150"
										path="mastersDTO.description" size="67" id="description" /></td>
							</tr>
						</table>
						<div class="btn">
							<div class="removebtn">
								<c:url value="remove_masters" var="remove_url">
									<c:param name="mid" value="${mastersForm.mastersDTO.mastersId}"></c:param>
									<c:param name="fid" value="${mastersForm.mastersDTO.formId}"></c:param>
								</c:url>
							 
							</div>

							<div >
							 
							</div>
						</div>
					</div>
				</div>
			</form:form>
		</c:if>

		<c:if test="${tv=='tabs-5'}">

			<form:form id="formID7" action="update_masters#tabs-5"
				modelAttribute="mastersForm" method="post">
				<input type="hidden" name="tv" value="tabs-5" />
				<form:hidden path="mastersDTO.id" /><form:hidden path="mastersDTO.mastersId" />
				<input type="hidden" name="mastersDTO.formName"
					value="Employee Type" />
				<input type="hidden" name="mastersDTO.formId" value="5" />
				<div id="tabs-5" style="background-color: #FF0000; font-size: 11px">
					<div align="left" class="bkgColor">
						<h2>Employee Type</h2>
						<table class="" width="580" height="74" border="0" align="center">
							<tr>
								<td width="99" height="39" align="left"><label>Name<span
										style="color: #FF0000"> * </span> </label></td>
								<td width="202"><form:input data-maxsize="55"
										class="validate[required] text-input" path="mastersDTO.name"
										size="22" id="name" /></td>
								<td width="61" height="39"><label> Code<span
										style="color: #FF0000"> *</span> </label></td>
								<td width="200"><form:input path="mastersDTO.code"
										data-maxsize="16" class="validate[required] text-input"
										size="22" id="code" /></td>
							</tr>
							<tr>
								<td height="29" align="left"><label>Description</label></td>
								<td height="29" colspan="3"><form:textarea onkeypress="return check(event)" cols="64" data-maxsize="150"
										path="mastersDTO.description" size="67" id="description" /></td>
							</tr>
						</table>
						<div class="btn">
							<div class="removebtn">
								<c:url value="remove_masters" var="remove_url">
									<c:param name="mid" value="${mastersForm.mastersDTO.mastersId}"></c:param>
									<c:param name="fid" value="${mastersForm.mastersDTO.formId}"></c:param>
								</c:url>
					 
							</div>

							<div class="cancelbtn">
					 
							</div>
						</div>
					</div>
				</div>
			</form:form>
		</c:if>

		<c:if test="${tv=='tabs-6'}">
			<form:form id="formID6" action="update_masters#tabs-6"
				modelAttribute="mastersForm" method="post">
				<input type="hidden" name="tv" value="tabs-6" />
				<form:hidden path="mastersDTO.id" /><form:hidden path="mastersDTO.mastersId" />
				<input type="hidden" name="mastersDTO.formName"
					value="Employee Grade" />
				<input type="hidden" name="mastersDTO.formId" value="6" />
				<div id="tabs-6" style="background-color: #FF0000; font-size: 11px">
					<div align="left" class="bkgColor">
						<h2>Employee Grade</h2>
						<table class="" width="566" height="97" border="0" align="center">
							<tr>
								<td height="27" align="left"><label>Name<span
										style="color: #FF0000"> * </span> </label>
								</td>
								<td><form:input path="mastersDTO.name" data-maxsize="55"
										class="validate[required] text-input" size="22" id="name" />
								</td>
								<td height="27"><label> Code<span
										style="color: #FF0000"> *</span> </label></td>
								<td><form:input path="mastersDTO.code" data-maxsize="16"
										class="validate[required] text-input" size="22" id="code" />
								</td>
							</tr>
							<tr>
								<td width="113" height="31" align="left"><label>
										Print sequence <span style="color: #FF0000"> * </span> </label></td>
								<td width="172"><form:input
										path="mastersDTO.gradePrintSeqNo" data-maxsize="16"
										class="validate[required] text-input" size="22"
										id="printSquence" /></td>
								<td width="90" height="31"><label></label></td>
								<td width="173">&nbsp;</td>
							</tr>
							<tr>
								<td height="29" align="left"><label>Description</label></td>
								<td height="29" colspan="3"><form:textarea onkeypress="return check(event)" cols="64" data-maxsize="150"
										path="mastersDTO.description" size="67" id="description" /></td>
							</tr>
						</table>
						<div class="btn">
							<div class="removebtn">
								<c:url value="remove_masters" var="remove_url">
									<c:param name="mid" value="${mastersForm.mastersDTO.mastersId}"></c:param>
									<c:param name="fid" value="${mastersForm.mastersDTO.formId}"></c:param>
								</c:url>
							 
							</div>

							 
						</div>
					</div>
				</div>
			</form:form>
		</c:if>

		<c:if test="${tv=='tabs-7'}">
			<form:form id="formID11" action="update_masters#tabs-7"
				modelAttribute="mastersForm" method="post">
				<input type="hidden" name="tv" value="tabs-7" />
				<form:hidden path="mastersDTO.id" /><form:hidden path="mastersDTO.mastersId" />
				<input type="hidden" name="mastersDTO.formName" value="Designation" />
				<input type="hidden" name="mastersDTO.formId" value="7" />
				<div id="tabs-7" style="background-color: #FF0000; font-size: 11px">
					<div align="left" class="bkgColor">
						<h2>Designation</h2>
						<table class="" width="580" height="90" border="0" align="center">
							<tr>
								<td width="126" height="39" align="left"><label>Name<span
										style="color: #FF0000"> * </span> </label></td>
								<td width="202"><form:input path="mastersDTO.name"
										data-maxsize="55" class="validate[required] text-input"
										size="22" id="name" /></td>
								<td width="61" height="39"><label> Code<span
										style="color: #FF0000"> *</span> </label></td>
								<td width="207"><form:input path="mastersDTO.code"
										data-maxsize="16" class="validate[required] text-input"
										size="22" id="code" /></td>
							</tr>
							<tr>
								<td height="26" align="left"><label>Empolyee Type<span
										style="color: #FF0000"> * </span> </label></td>
								<td><form:select path="mastersDTO.empTypeId"
										items="${empTypeList}" itemLabel="name" itemValue="mastersId"
										class="validate[required] text-input" id="employeeType"></form:select>

								</td>
								<td height="26">&nbsp;</td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td height="29" align="left"><label>Description</label></td>
								<td height="29" colspan="3"><form:textarea onkeypress="return check(event)" cols="62" data-maxsize="150"
										path="mastersDTO.description" size="67" id="description" /></td>
							</tr>
						</table>
						<div class="btn">
							<div class="removebtn">
								<c:url value="remove_masters" var="remove_url">
									<c:param name="mid" value="${mastersForm.mastersDTO.mastersId}"></c:param>
									<c:param name="fid" value="${mastersForm.mastersDTO.formId}"></c:param>
								</c:url>
							 
							</div>

							<div  >
							 
							</div>
						</div>
					</div>
				</div>
			</form:form>
		</c:if>

		<c:if test="${tv=='tabs-8'}">
			<form:form id="formID9" action="update_masters#tabs-8"
				modelAttribute="mastersForm" method="post">
				<input type="hidden" name="tv" value="tabs-8" />
				<form:hidden path="mastersDTO.id" /><form:hidden path="mastersDTO.mastersId" />
				<input type="hidden" name="mastersDTO.formName" value="Department" />
				<input type="hidden" name="mastersDTO.formId" value="8" />

				<div id="tabs-8" style="background-color: #FF0000; font-size: 11px">
					<div align="left" class="bkgColor">
						<h2>Department</h2>
						<table class="" width="580" height="74" border="0" align="center">
							<tr>
								<td width="99" height="39" align="left"><label>Name<span
										style="color: #FF0000"> * </span> </label></td>
								<td width="202"><form:input data-maxsize="55"
										class="validate[required] text-input" path="mastersDTO.name"
										size="22" id="name" /></td>
								<td width="61" height="39"><label> Code<span
										style="color: #FF0000"> *</span> </label></td>
								<td width="200"><form:input path="mastersDTO.code"
										data-maxsize="16" class="validate[required] text-input"
										size="22" id="code" /></td>
							</tr>
							<tr>
								<td height="29" align="left"><label>Description</label></td>
								<td height="29" colspan="3"><form:textarea onkeypress="return check(event)" cols="64" data-maxsize="150"
										path="mastersDTO.description" size="67" id="description" /></td>
							</tr>
						</table>

						<div class="btn">
							<div class="removebtn">
								<c:url value="remove_masters" var="remove_url">
									<c:param name="mid" value="${mastersForm.mastersDTO.mastersId}"></c:param>
									<c:param name="fid" value="${mastersForm.mastersDTO.formId}"></c:param>
								</c:url>
						 
							</div>

							<div >
							 
							</div>
						</div>
					</div>
				</div>
			</form:form>
		</c:if>

		<c:if test="${tv=='tabs-9'}">
			<form:form id="formID10" action="update_masters#tabs-9"
				modelAttribute="mastersForm" method="post">
				<input type="hidden" name="tv" value="tabs-9" />
				<form:hidden path="mastersDTO.id" /><form:hidden path="mastersDTO.mastersId" />
				<input type="hidden" name="mastersDTO.formName"
					value="Sub Department" />
				<input type="hidden" name="mastersDTO.formId" value="9" />

				<div id="tabs-9" style="background-color: #FF0000; font-size: 11px">
					<div align="left" class="bkgColor">
						<h2>Sub Department</h2>
						<table class="" width="582" height="88" border="0" align="center">
							<tr>
								<td width="97" height="26" align="left"><label>Name<span
										style="color: #FF0000"> * </span> </label></td>
								<td width="206"><form:input path="mastersDTO.name"
										data-maxsize="55" class="validate[required] text-input"
										size="22" id="name" /></td>
								<td width="53" height="26"><label> Code<span
										style="color: #FF0000"> *</span> </label></td>
								<td width="208"><form:input path="mastersDTO.code"
										data-maxsize="16" class="validate[required] text-input"
										size="22" id="code" /></td>
							</tr>
							<tr>
								<td height="25" align="left"><label>Department<span
										style="color: #FF0000"> * </span> </label></td>
								<td><form:select path="mastersDTO.deptId"
										items="${deptTypeList}" itemLabel="name" itemValue="mastersId"
										data-maxsize="55" class="validate[required] text-input"
										id="department"></form:select>
								</td>
								<td height="25">&nbsp;</td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td height="29" align="left"><label>Description</label></td>
								<td height="29" colspan="3"><form:textarea onkeypress="return check(event)" cols="64" data-maxsize="150"
										path="mastersDTO.description" size="67" id="description" /></td>
							</tr>
						</table>
						<div class="btn">
							<div class="removebtn">
								<c:url value="remove_masters" var="remove_url">
									<c:param name="mid" value="${mastersForm.mastersDTO.mastersId}"></c:param>
									<c:param name="fid" value="${mastersForm.mastersDTO.formId}"></c:param>
								</c:url>
							 
							</div>

							<div  >
							 
							</div>
						</div>
					</div>
				</div>
			</form:form>
		</c:if>

		<c:if test="${tv=='tabs-10'}">
			<form:form id="formID12" action="update_masters#tabs-10"
				modelAttribute="mastersForm" method="post">
				<input type="hidden" name="tv" value="tabs-10" />
				<form:hidden path="mastersDTO.id" /><form:hidden path="mastersDTO.mastersId" />
				<input type="hidden" name="mastersDTO.formName"
					value="Holiday Master" />
				<input type="hidden" name="mastersDTO.formId" value="10" />
				<div id="tabs-10" style="background-color: #FF0000; font-size: 11px">
					<div align="left" class="bkgColor">
						<h2>Holiday Master</h2>
						<table class="" width="" height="88" border="0" align="center">
							<tr>
								<td width="141" height="24" align="left"><label>&nbsp;Name<span
										style="color: #FF0000"> * </span> </label></td>
								<td width="133"><form:input path="mastersDTO.name"
										data-maxsize="55" class="validate[required] text-input"
										size="18" id="name" /></td>
								<td width="154" height="24"><label> Code<span
										style="color: #FF0000"> *</span> </label></td>
								<td width="135"><form:input path="mastersDTO.code"
										data-maxsize="16" class="validate[required] text-input"
										size="18" id="code" /></td>
							</tr>
							<tr>
								<td height="27" align="left"><label> Holiday From
										Date<span style="color: #FF0000"> * </span> </label></td>
								<td><form:input path="mastersDTO.holidayFromDate"
										id="holidayFromDate"
										class="datepicker validate[required] text-input" size="18" />
								</td>
								<td height="27" align="left"><label> Holiday To
										Date<span style="color: #FF0000"> * </span> </label></td>
								<td><form:input path="mastersDTO.holidayToDate"
										id="holidayToDate"
										class="datepicker validate[required] text-input" size="18" />
								</td>
							</tr>
							<tr>
								<td height="29" align="left"><label>Description</label></td>
								<td height="29" colspan="3"><form:textarea onkeypress="return check(event)" cols="64" data-maxsize="150"
										path="mastersDTO.description" size="67" id="description" /></td>
							</tr>
						</table>
						<div class="btn">
							<div class="removebtn">
								<c:url value="remove_masters" var="remove_url">
									<c:param name="mid" value="${mastersForm.mastersDTO.mastersId}"></c:param>
									<c:param name="fid" value="${mastersForm.mastersDTO.formId}"></c:param>
								</c:url>
						 
							</div>

							<div  >
						 
							</div>
						</div>
					</div>
				</div>
			</form:form>
		</c:if>

		<c:if test="${tv=='tabs-11'}">
			<form:form id="formID13" action="update_masters#tabs-11"
				modelAttribute="mastersForm" method="post">
				<input type="hidden" name="tv" value="tabs-11" />
				<form:hidden path="mastersDTO.id" /><form:hidden path="mastersDTO.mastersId" />
				<input type="hidden" name="mastersDTO.formName" value="Shift Master" />
				<input type="hidden" name="mastersDTO.formId" value="11" />
				<div id="tabs-11" style="background-color: #FF0000; font-size: 11px">
					<div align="left" class="bkgColor">
						<h2>Shift master</h2>

						<table class="" width="" height="109" border="0" align="center">
							<tr>
								<td width="134" height="24" align="left"><label>Name<span
										style="color: #FF0000"> * </span> </label></td>
								<td width="158"><form:input path="mastersDTO.name"
										data-maxsize="55" class="validate[required] text-input"
										size="18" id="name" /></td>
								<td width="128" height="24"><label> Code<span
										style="color: #FF0000"> *</span> </label></td>
								<td width="143"><form:input path="mastersDTO.code"
										data-maxsize="16" class="validate[required] text-input"
										size="18" id="code" /></td>
							</tr>
							<tr>
								<td height="27" align="left"><label> Shift Start
										Time<span style="color: #FF0000"> * </span> </label></td>
								<td><form:input path="mastersDTO.shiftFromTime"
										class="myTimePicker validate[required] text-input" size="18"
										id="shiftStartTime" /></td>
								<td height="27" align="left"><label> Shift End Time<span
										style="color: #FF0000"> * </span> </label></td>
								<td><form:input path="mastersDTO.shiftToTime"
										class="myTimePicker validate[required] text-input" size="18"
										id="shiftEndTime" /></td>
							</tr>
							<tr>
								<td height="24" align="left"><label>Working Hour<span
										style="color: #FF0000"> * </span> </label></td>
								<td><form:input path="mastersDTO.workingHour"
										class="myTimePicker validate[required] text-input" size="18"
										id="workingHour" /></td>
								<td height="24" align="left"><label>Late Permit<span
										style="color: #FF0000"> * </span> </label></td>
								<td><form:input path="mastersDTO.latePermit"
										class="myTimePicker validate[required] text-input" size="18"
										id="letePermit" /></td>
							</tr>
							<tr>
								<td height="24" align="left"><label>Description</label></td>
								<td height="24" colspan="3"><form:textarea cols="64" 
										path="mastersDTO.description" size="67" id="description" /></td>
							</tr>
						</table>
						<div class="btn">
							<div class="savbtn">
								<input class="removebtn"
									
									type="submit" value="" />
							</div>
							<div >
								<input
								class="cancelbtn"
									type="button" value="" />
							</div>
						</div>
					</div>
				</div>
			</form:form>
		</c:if>

		<c:if test="${tv=='tabs-12'}">


			<form:form id="formID8" action="update_masters#tabs-12"
				modelAttribute="mastersForm" method="post">
				<input type="hidden" name="tv" value="tabs-12" />
				<form:hidden path="mastersDTO.id" /><form:hidden path="mastersDTO.mastersId" />
				<input type="hidden" name="mastersDTO.formName"
					value="VAT Form Type" />
				<input type="hidden" name="mastersDTO.formId" value="12" />

				<div id="tabs-12" style="background-color: #FF0000; font-size: 11px">
					<div align="left" class="bkgColor">
						<h2>VAT Form Type</h2>
						<table class="" width="580" height="74" border="0" align="center">
							<tr>
								<td width="99" height="39" align="left"><label>Name<span
										style="color: #FF0000"> * </span> </label></td>
								<td width="202"><form:input data-maxsize="55"
										class="validate[required] text-input" path="mastersDTO.name"
										size="22" id="name" /></td>
								<td width="61" height="39"><label> Code<span
										style="color: #FF0000"> *</span> </label></td>
								<td width="200"><form:input path="mastersDTO.code"
										data-maxsize="16" class="validate[required] text-input"
										size="22" id="code" /></td>
							</tr>
							<tr>
								<td height="29" align="left"><label>Description</label></td>
								<td height="29" colspan="3"><form:textarea cols="64" 
										path="mastersDTO.description" size="67" id="description" /></td>
							</tr>
						</table>

						<div class="btn">
							<div class="removebtn">
								<c:url value="remove_masters" var="remove_url">
									<c:param name="mid" value="${mastersForm.mastersDTO.mastersId}"></c:param>
									<c:param name="fid" value="${mastersForm.mastersDTO.formId}"></c:param>
								</c:url>
							 
							</div>

							<div  >
							 
							</div>
						</div>
					</div>
				</div>
			</form:form>
		</c:if>

		<c:if test="${tv=='tabs-13'}">

			<form:form id="formID14" action="update_masters#tabs-13"
				modelAttribute="mastersForm" method="post">
				<input type="hidden" name="tv" value="tabs-13" />
				<form:hidden path="mastersDTO.id" /><form:hidden path="mastersDTO.mastersId" />
				<input type="hidden" name="mastersDTO.formName" value="Item Class" />
				<input type="hidden" name="mastersDTO.formId" value="13" />

				<div id="tabs-13" style="background-color: #FF0000; font-size: 11px">
					<div align="left" class="bkgColor">
						<h2>Item Class</h2>
						<table class="" width="580" height="74" border="0" align="center">
							<tr>
								<td width="99" height="39" align="left"><label>Name<span
										style="color: #FF0000"> * </span> </label></td>
								<td width="202"><form:input data-maxsize="55"
										class="validate[required] text-input" path="mastersDTO.name"
										size="22" id="name" /></td>
								<td width="61" height="39"><label> Code<span
										style="color: #FF0000"> *</span> </label></td>
								<td width="200"><form:input path="mastersDTO.code"
										data-maxsize="16" class="validate[required] text-input"
										size="22" id="code" /></td>
							</tr>
							<tr>
								<td height="29" align="left"><label>Description</label></td>
								<td height="29" colspan="3"><form:textarea cols="64" 
										path="mastersDTO.description" size="67" id="description" /></td>
							</tr>
						</table>

						<div class="btn">
							<div class="removebtn">
								<c:url value="remove_masters" var="remove_url">
									<c:param name="mid" value="${mastersForm.mastersDTO.mastersId}"></c:param>
									<c:param name="fid" value="${mastersForm.mastersDTO.formId}"></c:param>
								</c:url>
							 
							</div>

							<div  >
							 
							</div>
						</div>
					</div>
				</div>
			</form:form>
		</c:if>

		<c:if test="${tv=='tabs-14'}">
			<form:form id="formID3" action="update_masters#tabs-14"
				modelAttribute="mastersForm" method="post">
				<input type="hidden" name="tv" value="tabs-14" />
				<form:hidden path="mastersDTO.id" /><form:hidden path="mastersDTO.mastersId" />
				<input type="hidden" name="mastersDTO.formName"
					value="Specialisation" />
				<input type="hidden" name="mastersDTO.formId" value="14" />

				<div id="tabs-14" style="background-color: #FF0000; font-size: 11px">
					<div align="left" class="bkgColor">
						<h2>Specialization</h2>
						<table class="" width="580" height="74" border="0" align="center">
							<tr>
								<td width="99" height="39" align="left"><label>Name<span
										style="color: #FF0000"> * </span> </label></td>
								<td width="202"><form:input data-maxsize="55"
										class="validate[required] text-input" path="mastersDTO.name"
										size="22" id="name" /></td>
								<td width="61" height="39"><label> Code<span
										style="color: #FF0000"> *</span> </label></td>
								<td width="200"><form:input path="mastersDTO.code"
										data-maxsize="16" class="validate[required] text-input"
										size="22" id="code" /></td>
							</tr>
							<tr>
								<td height="29" align="left"><label>Description</label></td>
								<td height="29" colspan="3"><form:textarea cols="64" 
										path="mastersDTO.description" size="67" id="description" /></td>
							</tr>
						</table>

						<div class="btn">
							<div class="removebtn">
								<c:url value="remove_masters" var="remove_url">
									<c:param name="mid" value="${mastersForm.mastersDTO.mastersId}"></c:param>
									<c:param name="fid" value="${mastersForm.mastersDTO.formId}"></c:param>
								</c:url>
							 
							</div>

							<div  >
							 
							</div>
						</div>
					</div>
				</div>
			</form:form>
		</c:if>

		<c:if test="${tv=='tabs-15'}">
			<form:form id="formID15" action="update_masters#tabs-15"
				modelAttribute="mastersForm" method="post">
				<input type="hidden" name="tv" value="tabs-15" />
				<form:hidden path="mastersDTO.id" /><form:hidden path="mastersDTO.mastersId" />
				<input type="hidden" name="mastersDTO.formName" value="Pack Type" />
				<input type="hidden" name="mastersDTO.formId" value="15" />

				<div id="tabs-15" style="background-color: #FF0000; font-size: 11px">
					<div align="left" class="bkgColor">
						<h2>Pack Type</h2>
						<table class="" width="580" height="74" border="0" align="center">
							<tr>
								<td width="99" height="39" align="left"><label>Name<span
										style="color: #FF0000"> * </span> </label></td>
								<td width="202"><form:input data-maxsize="55"
										class="validate[required] text-input" path="mastersDTO.name"
										size="22" id="name" /></td>
								<td width="61" height="39"><label> Code<span
										style="color: #FF0000"> *</span> </label></td>
								<td width="200"><form:input path="mastersDTO.code"
										data-maxsize="16" class="validate[required] text-input"
										size="22" id="code" /></td>
							</tr>
							<tr>
								<td height="29" align="left"><label>Description</label></td>
								<td height="29" colspan="3"><form:textarea cols="64" 
										path="mastersDTO.description" size="67" id="description" /></td>
							</tr>
						</table>

						<div class="btn">
							<div class="removebtn">
								<c:url value="remove_masters" var="remove_url">
									<c:param name="mid" value="${mastersForm.mastersDTO.mastersId}"></c:param>
									<c:param name="fid" value="${mastersForm.mastersDTO.formId}"></c:param>
								</c:url>
							 
							</div>
							<div  >
						 
							</div>
						</div>
					</div>
				</div>
			</form:form>
		</c:if>

		<c:if test="${tv=='tabs-16'}">
			<form:form id="formID16" action="update_masters#tabs-16"
				modelAttribute="mastersForm" method="post">
				<input type="hidden" name="tv" value="tabs-16" />
				<form:hidden path="mastersDTO.id" /><form:hidden path="mastersDTO.mastersId" />
				<input type="hidden" name="mastersDTO.formName" value="Item Grade" />
				<input type="hidden" name="mastersDTO.formId" value="16" />

				<div id="tabs-16" style="background-color: #FF0000; font-size: 11px">
					<div align="left" class="bkgColor">
						<h2>Item Grade</h2>
						<table class="" width="580" height="74" border="0" align="center">
							<tr>
								<td width="99" height="39" align="left"><label>Name<span
										style="color: #FF0000"> * </span> </label></td>
								<td width="202"><form:input data-maxsize="55"
										class="validate[required] text-input" path="mastersDTO.name"
										size="22" id="name" /></td>
								<td width="61" height="39"><label> Code<span
										style="color: #FF0000"> *</span> </label></td>
								<td width="200"><form:input path="mastersDTO.code"
										data-maxsize="16" class="validate[required] text-input"
										size="22" id="code" /></td>
							</tr>
							<tr>
								<td height="29" align="left"><label>Description</label></td>
								<td height="29" colspan="3"><form:textarea cols="64" 
										path="mastersDTO.description" size="67" id="description" /></td>
							</tr>
						</table>

						<div class="btn">
							<div class="removebtn">
								<c:url value="remove_masters" var="remove_url">
									<c:param name="mid" value="${mastersForm.mastersDTO.mastersId}"></c:param>
									<c:param name="fid" value="${mastersForm.mastersDTO.formId}"></c:param>
								</c:url>
							 


							</div>
							<div  >
							 
							</div>
						</div>
					</div>
				</div>
			</form:form>
		</c:if>
		<c:if test="${tv=='tabs-17'}">
			<form:form id="formID17" action="update_masters#tabs-17"
				modelAttribute="mastersForm" method="post">
				<input type="hidden" name="tv" value="tabs-17" />
				<form:hidden path="mastersDTO.id" /><form:hidden path="mastersDTO.mastersId" />
				<input type="hidden" name="mastersDTO.formName"
					value="Measurement Unit" />
				<input type="hidden" name="mastersDTO.formId" value="17" />

				<div id="tabs-17" style="background-color: #FF0000; font-size: 11px">
					<div align="left" class="bkgColor">
						<h2>Measurement Unit</h2>
						<table class="" width="580" height="74" border="0" align="center">
							<tr>
								<td width="99" height="39" align="left"><label>Name<span
										style="color: #FF0000"> * </span> </label></td>
								<td width="202"><form:input data-maxsize="55"
										class="validate[required] text-input" path="mastersDTO.name"
										size="22" id="name" /></td>
								<td width="61" height="39"><label> Code<span
										style="color: #FF0000"> *</span> </label></td>
								<td width="200"><form:input path="mastersDTO.code"
										data-maxsize="16" class="validate[required] text-input"
										size="22" id="code" /></td>
							</tr>
							<tr>
								<td height="29" align="left"><label>Description</label></td>
								<td height="29" colspan="3"><form:textarea cols="64" 
										path="mastersDTO.description" size="67" id="description" /></td>
							</tr>
						</table>

						<div class="btn">
							<div class="removebtn">
								<c:url value="remove_masters" var="remove_url">
									<c:param name="mid" value="${mastersForm.mastersDTO.mastersId}"></c:param>
									<c:param name="fid" value="${mastersForm.mastersDTO.formId}"></c:param>
								</c:url>
							 


							</div>
							<div  >
						 
							</div>
						</div>
					</div>
				</div>
			</form:form>
		</c:if>
	</c:if>


 <c:if test="${tv=='tabs-18'}">
			<form:form id="formID18" action="update_masters#tabs-18"
				modelAttribute="mastersForm" method="post">
				<input type="hidden" name="tv" value="tabs-18" />
				<form:hidden path="mastersDTO.id" /><form:hidden path="mastersDTO.mastersId" />
				<input type="hidden" name="mastersDTO.formName" value="Head" />
   <input type="hidden" name="mastersDTO.formId" value="18" />
 
<div id="tabs-18" style="background-color:#FF0000;  font-size:11px">
    <div align="left" class="bkgColor"> 
	<h2>Head Name</h2>	
   <table class="" width="580" height="74"  border="0" align="center">
     <tr>
       <td width="107" height="39" align="left"><label>Name<span style="color: #FF0000"> *
       </span></label></td>
       <td width="202"><form:input onkeypress="return check(event)"  data-maxsize="55" class="validate[required] text-input"  path="mastersDTO.name"   size="22" id="name" /></td>
       <td width="61" height="39"><label>  Code<span style="color: #FF0000"> *</span></label></td>
       <td width="200"><form:input onkeyup="valid1(this)" onblur="valid1(this)"  path="mastersDTO.code" data-maxsize="16" class="validate[required] text-input" size="22" id="code" /></td>
     </tr>
	 <tr>
       <td height="29" align="left"><label>Description</label></td>
       <td height="29" colspan="3"><form:textarea  onkeypress="return check(event)" data-maxsize="150" path="mastersDTO.description"  cols="64" id="description" /></td>
       </tr>
   </table>
   
   <div class="btn">
     <div class="savbtn">
       <input class="submit"  type="submit" value=""/>
     </div>
     <div >
       <input  class="cancelbtn" 
     type="button" value=""/>
     </div>
   </div>
    </div>
  </div>	
  </form:form>
  </c:if>
  
   <c:if test="${tv=='tabs-19'}">
			<form:form id="formID19" action="update_masters#tabs-19"
				modelAttribute="mastersForm" method="post">
				<input type="hidden" name="tv" value="tabs-19" />
				<form:hidden path="mastersDTO.id" /><form:hidden path="mastersDTO.mastersId" />
				<input type="hidden" name="mastersDTO.formName" value="Section" />
   <input type="hidden" name="mastersDTO.formId" value="19" />
 
<div id="tabs-19" style="background-color:#FF0000;  font-size:11px">
    <div align="left" class="bkgColor"> 
	<h2>Section Name</h2>	
   <table class="" width="580" height="74"  border="0" align="center">
     <tr>
       <td width="107" height="39" align="left"><label>Name<span style="color: #FF0000"> *
       </span></label></td>
       <td width="202"><form:input onkeypress="return check(event)"  data-maxsize="55" class="validate[required] text-input"  path="mastersDTO.name"   size="22" id="name" /></td>
       <td width="61" height="39"><label>  Code<span style="color: #FF0000"> *</span></label></td>
       <td width="200"><form:input onkeyup="valid1(this)" onblur="valid1(this)"  path="mastersDTO.code" data-maxsize="16" class="validate[required] text-input" size="22" id="code" /></td>
     </tr>
	 <tr>
       <td height="29" align="left"><label>Description</label></td>
       <td height="29" colspan="3"><form:textarea  onkeypress="return check(event)" data-maxsize="150" path="mastersDTO.description"  cols="64" id="description" /></td>
       </tr>
   </table>
   
   <div class="btn">
     <div class="savbtn">
       <input class="submit"  type="submit" value=""/>
     </div>
     <div >
       <input  class="cancelbtn" 
     type="button" value=""/>
     </div>
   </div>
    </div>
  </div>	
  </form:form>
  </c:if>
</div>





