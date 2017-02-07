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
			jQuery("#formID1").validationEngine();
		});

		function checkHELLO(field, rules, i, options){
			if (field.val() != "HELLO") {
				// this allows to use i18 for the error msgs
				return options.allrules.validate2fields.alertText;
			}
		}
	</script>
     <script>
		jQuery(document).ready(function(){
			// binds form submission and fields to the validation engine
			jQuery("#formID2").validationEngine();
		});

		function checkHELLO(field, rules, i, options){
			if (field.val() != "HELLO") {
				// this allows to use i18 for the error msgs
				return options.allrules.validate2fields.alertText;
			}
		}
	</script>
    
    <script>
		jQuery(document).ready(function(){
			// binds form submission and fields to the validation engine
			jQuery("#formID3").validationEngine();
		});

		function checkHELLO(field, rules, i, options){
			if (field.val() != "HELLO") {
				// this allows to use i18 for the error msgs
				return options.allrules.validate2fields.alertText;
			}
		}
	</script>

    <script>
		jQuery(document).ready(function(){
			// binds form submission and fields to the validation engine
			jQuery("#formID4").validationEngine();
		});

		function checkHELLO(field, rules, i, options){
			if (field.val() != "HELLO") {
				// this allows to use i18 for the error msgs
				return options.allrules.validate2fields.alertText;
			}
		}
	</script>
    
    <script>
		jQuery(document).ready(function(){
			// binds form submission and fields to the validation engine
			jQuery("#formID5").validationEngine();
		});

		function checkHELLO(field, rules, i, options){
			if (field.val() != "HELLO") {
				// this allows to use i18 for the error msgs
				return options.allrules.validate2fields.alertText;
			}
		}
	</script>

 <script>
		jQuery(document).ready(function(){
			// binds form submission and fields to the validation engine
			jQuery("#formID6").validationEngine();
		});

		function checkHELLO(field, rules, i, options){
			if (field.val() != "HELLO") {
				// this allows to use i18 for the error msgs
				return options.allrules.validate2fields.alertText;
			}
		}
	</script>

<script>
		jQuery(document).ready(function(){
			// binds form submission and fields to the validation engine
			jQuery("#formID7").validationEngine();
		});

		function checkHELLO(field, rules, i, options){
			if (field.val() != "HELLO") {
				// this allows to use i18 for the error msgs
				return options.allrules.validate2fields.alertText;
			}
		}
	</script>

 <script>
		jQuery(document).ready(function(){
			// binds form submission and fields to the validation engine
			jQuery("#formID8").validationEngine();
		});

		function checkHELLO(field, rules, i, options){
			if (field.val() != "HELLO") {
				// this allows to use i18 for the error msgs
				return options.allrules.validate2fields.alertText;
			}
		}
	</script>

 <script>
		jQuery(document).ready(function(){
			// binds form submission and fields to the validation engine
			jQuery("#formID9").validationEngine();
		});

		function checkHELLO(field, rules, i, options){
			if (field.val() != "HELLO") {
				// this allows to use i18 for the error msgs
				return options.allrules.validate2fields.alertText;
			}
		}
	</script>

<script>
		jQuery(document).ready(function(){
			// binds form submission and fields to the validation engine
			jQuery("#formID10").validationEngine();
		});

		function checkHELLO(field, rules, i, options){
			if (field.val() != "HELLO") {
				// this allows to use i18 for the error msgs
				return options.allrules.validate2fields.alertText;
			}
		}
	</script>
    
    
    <script>
		jQuery(document).ready(function(){
			// binds form submission and fields to the validation engine
			jQuery("#formID11").validationEngine();
		});

		function checkHELLO(field, rules, i, options){
			if (field.val() != "HELLO") {
				// this allows to use i18 for the error msgs
				return options.allrules.validate2fields.alertText;
			}
		}
	</script>

<script>
		jQuery(document).ready(function(){
			// binds form submission and fields to the validation engine
			jQuery("#formID12").validationEngine();
		});

		function checkHELLO(field, rules, i, options){
			if (field.val() != "HELLO") {
				// this allows to use i18 for the error msgs
				return options.allrules.validate2fields.alertText;
			}
		}
	</script>
    
    <script>
		jQuery(document).ready(function(){
			// binds form submission and fields to the validation engine
			jQuery("#formID13").validationEngine();
		});

		function checkHELLO(field, rules, i, options){
			if (field.val() != "HELLO") {
				// this allows to use i18 for the error msgs
				return options.allrules.validate2fields.alertText;
			}
		}
	</script>

<script>
		jQuery(document).ready(function(){
			// binds form submission and fields to the validation engine
			jQuery("#formID14").validationEngine();
		});

		function checkHELLO(field, rules, i, options){
			if (field.val() != "HELLO") {
				// this allows to use i18 for the error msgs
				return options.allrules.validate2fields.alertText;
			}
		}
	</script>
    
    <script>
		jQuery(document).ready(function(){
			// binds form submission and fields to the validation engine
			jQuery("#formID15").validationEngine();
		});

		function checkHELLO(field, rules, i, options){
			if (field.val() != "HELLO") {
				// this allows to use i18 for the error msgs
				return options.allrules.validate2fields.alertText;
			}
		}
	</script>
	<script>
		jQuery(document).ready(function(){
			// binds form submission and fields to the validation engine
			jQuery("#formID16").validationEngine();
		});

		function checkHELLO(field, rules, i, options){
			if (field.val() != "HELLO") {
				// this allows to use i18 for the error msgs
				return options.allrules.validate2fields.alertText;
			}
		}
	</script>
	<script>
		jQuery(document).ready(function(){
			// binds form submission and fields to the validation engine
			jQuery("#formID17").validationEngine();
		});

		function checkHELLO(field, rules, i, options){
			if (field.val() != "HELLO") {
				// this allows to use i18 for the error msgs
				return options.allrules.validate2fields.alertText;
			}
		}
	</script>

	<script>
		jQuery(document).ready(function(){
			// binds form submission and fields to the validation engine
			jQuery("#formID18").validationEngine();
		});

		function checkHELLO(field, rules, i, options){
			if (field.val() != "HELLO") {
				// this allows to use i18 for the error msgs
				return options.allrules.validate2fields.alertText;
			}
		}
	</script>
		<script>
		jQuery(document).ready(function(){
			// binds form submission and fields to the validation engine
			jQuery("#formID19").validationEngine();
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
        $( "#tabs" ).tabs();
	  });
    </script>
    
	<!--<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="keywords" content="jquery,ui,easy,easyui,web">
	<meta name="description" content="easyui help you build your web page easily!">
	<title>jQuery EasyUI Demo</title>
	<link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/themes/icon.css">
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.6.1.min.js"></script>
	<script type="text/javascript" src="http://www.jeasyui.com/easyui/jquery.easyui.min.js"></script>-->
    <style type="text/css">
	body {
	font-family:Arial, Helvetica, sans-serif;
	}

	.ui-tabs .ui-tabs-nav li.ui-tabs-selected a, .ui-tabs .ui-tabs-nav li.ui-state-disabled a, .ui-tabs .ui-tabs-nav li.ui-state-processing a{
	background-color:#4e8ccf;
	color:#FFFFFF;
	}
	
	.ui-datepicker table {
    border-collapse: collapse;
    font-size: 0.9em;
    margin: 0 0 0.4em;
    width: 100% !important;
}
	.bkgColor{
	 background: #F6F6F6;
	 border-color: #4E8CCF;  
	 width:674px; 
	 float:left; 
	
	 height:198px;
   }

 .ui-state-default, .ui-widget-content .ui-state-default, .ui-widget-header .ui-state-default
 {
 background-color:#4e8ccf !important;
 
 }
 .ui-state-default, .ui-widget-content .ui-state-default, .ui-widget-header .ui-state-default {
 background:none;
 color:#FFFFFF;
 }
   .ui-widget-content {
   background:none;
   }
   .ui-corner-all, .ui-corner-bottom, .ui-corner-right, .ui-corner-br {
   background-color:#e0ebff;
   
   }
   .ui-tabs .ui-tabs-nav li a, .ui-tabs-collapsible .ui-tabs-nav li.ui-tabs-active a
   {
   background-color:#e0ecff;
   color:#416aa3;
   font-weight:normal;
   font-family:Verdana, Arial, Helvetica, sans-serif;
     width: 106px;
   }
   
   .tabs li.tabs-selected a.tabs-inner {
   background:none !important;
   }
   .ui-tabs .ui-tabs-nav li {
 border:1px solid #4E8CCF !important; 
border-radius: 0 0 0 0;
 width: 130px;
   }
   .tabs li a.tabs-inner
   {
   background:none !important;
   text-align:center !important;
   }
   .tabs li {
   width:80px !important;
   text-align:center;
   }
   .easyui-tabs tabs-container {
   height:150px !important; 
   }
  .tabs-panels {
  height:142px !important;
  padding:0px !important;
  width:800px; !important;
  }
    div.ui-datepicker{
  font-size:10px;
  width: 18%;
 }
.panel-header
 {
	width:68%;
	height:12px;
 }
 .ui-tabs .ui-tabs-panel {
 padding:0px;
 }
 .ui-widget input, .ui-widget select, .ui-widget textarea, .ui-widget button {
 font-family:Verdana, Arial, Helvetica, sans-serif !important;
 }
 .ui-widget-header {
 background:#4e8ccf !important;
  border:1px solid #4E8CCF !important;
  padding-bottom:12px;
 }
 #dlg-buttons {
 text-align:center;
 margin-top:11px;
 float:	left;
 margin-left:97px;
 }
 .ui-tabs .ui-tabs-nav li.ui-tabs-active a, .ui-tabs .ui-tabs-nav li.ui-state-disabled a, .ui-tabs .ui-tabs-nav li.ui-tabs-loading a {
 background-color:#4e8ccf;
 color:#FFFFFF;
 }
.ui-tabs-nav
 {
background-color:#e0ecff !important;
 }
 #tabs .ui-tabs-nav {
background-color:#e0ecff !important;
font-size:12px;
 }
 select {
 width:151px;
 height:20px;
 }
 h2 {
 text-align:center;
 font-size:16px;
 margin:11px 0 0 0;
 }
.ui-tabs .ui-tabs-nav {
padding:0px;
}
 th{font-size:10px;}
 
 td{font-size:12px;}  
 
 
 table{
 width: 580px !important;
 }
		
 	.btn {
    margin-left: 48px;
    margin-top: 12px;
}
 

  .errorblock {
	color: #000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	margin-bottom: 16px;
	width: 82%;
}
    </style>
	
 
<div id="tabs" style="width:674px; height:324px; float:right; border:1px solid #4E8CCF; padding:0px;">
    <ul style="background-color:#e0ecff; padding-bottom:1px">
           <li><a href="#tabs-1">Cast</a></li>
           <li><a href="#tabs-2">Religion</a></li>
		   <li><a href="#tabs-3">Language</a></li>		
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
  
  
  <form:form  id="formID1" action="save_masters#tabs-1" modelAttribute="mastersForm" method="post">
   <input type="hidden" name="mastersDTO.formName" value="Cast" />
   <input type="hidden" name="mastersDTO.formId" value="1" />
    <div id="tabs-1" style="background-color:#FF0000;  font-size:11px">
        <div align="left" class="bkgColor"> 
		<h2>Cast</h2>
		
   <table class="" width="580" height="74"  border="0" align="center">
     <tr>
       <td width="44" height="39" align="left"><label>Name<span style="color: #FF0000"> *
       </span></label></td>
       <td width="150"><form:input onkeypress="return check(event)" data-maxsize="55" class="validate[required] text-input"  path="mastersDTO.name"   size="22" id="name" /></td>
       <td width="54" height="39"><label>  Code<span style="color: #FF0000"> *</span></label></td>
       <td width="178"><form:input onkeyup="valid1(this)" onblur="valid1(this)"  path="mastersDTO.code" data-maxsize="16" class="validate[required] text-input" size="22" id="code" /></td>
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
   <div class="cancelbtn">
    <a href="#"><input  class="cancelbtn" 
     type="reset" value=""/></a>
   </div>
    </div>    
   
  </div>
    </div>
   </form:form>
   
   
    <form:form  id="formID2" action="save_masters#tabs-2" modelAttribute="mastersForm" method="post">
   <input type="hidden" name="mastersDTO.formName" value="Religion" />
   <input type="hidden" name="mastersDTO.formId" value="2" />
    <div id="tabs-2" style="background-color:#FF0000;  font-size:11px">
      <div align="left" class="bkgColor"> 
		<h2>Religion</h2>
  <table class="" width="580" height="74"  border="0" align="center">
     <tr>
       <td width="107" height="39" align="left"><label>Name<span style="color: #FF0000"> *
       </span></label></td>
       <td width="202"><form:input onkeypress="return check(event)"   data-maxsize="55" class="validate[required] text-input"  path="mastersDTO.name"   size="22" id="name" /></td>
       <td width="61" height="39"><label>  Code<span style="color: #FF0000"> *</span></label></td>
       <td width="204"><form:input onkeyup="valid1(this)" onblur="valid1(this)"   path="mastersDTO.code" data-maxsize="16" class="validate[required] text-input" size="22" id="code" /></td>
     </tr>
	 <tr>
       <td height="29" align="left"><label>Description</label></td>
       <td height="29" colspan="3"><form:textarea  onkeypress="return check(event)" data-maxsize="150" path="mastersDTO.description"  cols="64" id="description" /></td>
       </tr>
   </table>
   <div class="btn">
     <div class="savbtn">
       <a href="#"><input class="submit"  type="submit" value=""/></a>
     </div>
     <div >
       <input  class="cancelbtn" 
     type="button" value=""/>
     </div>
   </div>
      </div>
    </div>
    </form:form>
   
   
   <form:form  id="formID4" action="save_masters#tabs-3" modelAttribute="mastersForm" method="post">
   <input type="hidden" name="mastersDTO.formName" value="Language" />
   <input type="hidden" name="mastersDTO.formId" value="3" />
   
	<div id="tabs-3" style="background-color:#FF0000;  font-size:11px">
      <div align="left" class="bkgColor"> 
		<h2>Language</h2>
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
     type="button" value=" "/>
     </div>
   </div>
      </div>
    </div>
    </form:form>
	
	<form:form  id="formID5" action="save_masters#tabs-4" modelAttribute="mastersForm" method="post">
   <input type="hidden" name="mastersDTO.formName" value="Qualification" />
   <input type="hidden" name="mastersDTO.formId" value="4" />
   
    <div id="tabs-4" style="background-color:#FF0000;  font-size:11px">
      <div align="left" class="bkgColor"> 
		<h2>Qualification</h2>
<table class="" width="580" height="74"  border="0" align="center">
     <tr>
       <td width="107" height="39" align="left"><label>Name<span style="color: #FF0000"> *
       </span></label></td>
       <td width="202"><form:input onkeypress="return check(event)"   data-maxsize="55" class="validate[required] text-input"  path="mastersDTO.name"   size="22" id="name" /></td>
       <td width="61" height="39"><label>  Code<span style="color: #FF0000"> *</span></label></td>
       <td width="200"><form:input onkeyup="valid1(this)" onblur="valid1(this)"   path="mastersDTO.code" data-maxsize="16" class="validate[required] text-input" size="22" id="code" /></td>
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
     type="button" value=" "/>
     </div>
   </div>
      </div>
    </div>
    </form:form>
	
	<form:form id="formID7" action="save_masters#tabs-5" modelAttribute="mastersForm" method="post">
   <input type="hidden" name="mastersDTO.formName" value="Employee Type" />
   <input type="hidden" name="mastersDTO.formId" value="5" />
	<div id="tabs-5" style="background-color:#FF0000;  font-size:11px">
      <div align="left" class="bkgColor"> 
		<h2>Employee Type</h2>
   <table class="" width="580" height="74"  border="0" align="center">
     <tr>
       <td width="99" height="39" align="left"><label>Name<span style="color: #FF0000"> *
       </span></label></td>
       <td width="202"><form:input onkeypress="return check(event)"  data-maxsize="55" class="validate[required] text-input"  path="mastersDTO.name"   size="22" id="name" /></td>
       <td width="61" height="39"><label>  Code<span style="color: #FF0000"> *</span></label></td>
       <td width="200"><form:input  onkeyup="valid1(this)" onblur="valid1(this)"   path="mastersDTO.code" data-maxsize="16" class="validate[required] text-input" size="22" id="code" /></td>
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
     type="button" value=" "/>
     </div>
   </div>
      </div>
    </div>
    </form:form>
	
	<form:form  id="formID6" action="save_masters#tabs-6" modelAttribute="mastersForm" method="post">
   <input type="hidden" name="mastersDTO.formName" value="Employee Grade" />
   <input type="hidden" name="mastersDTO.formId" value="6" />
	<div id="tabs-6" style="background-color:#FF0000;  font-size:11px">
      <div align="left" class="bkgColor"> 
		<h2>Employee Grade</h2>
   <table class="" width="566" height="97"  border="0" align="center">
     <tr>
       <td height="27" align="left"><label>Name<span style="color: #FF0000"> *
       </span></label> </td>
       <td><form:input path="mastersDTO.name" onkeypress="return check(event)"   data-maxsize="55" class="validate[required] text-input"   size="22" id="name" /></td>
       <td height="27"><label>  Code<span style="color: #FF0000"> *</span></label></td>
       <td><form:input path="mastersDTO.code" onkeyup="valid1(this)" onblur="valid1(this)"   data-maxsize="16" class="validate[required] text-input"  size="20" id="code" /></td>
     </tr>
     <tr>
       <td width="113" height="31" align="left"><label> Print sequence <span style="color: #FF0000"> *
       </span></label></td>
       <td width="172"><form:input  path="mastersDTO.gradePrintSeqNo" data-maxsize="16" style="text-align:right;" class="validate[required] text-input quantity"  
        size="22" id="printSquence" /></td>
       <td width="90" height="31"><label></label></td>
       <td width="173">&nbsp;</td>
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
    <div ><span style="margin-left:12px;" class="errmsg"></span></div>
      </div>
         
    </div>
    </form:form>
	
	
	
	
	<form:form  id="formID11" action="save_masters#tabs-7" modelAttribute="mastersForm" method="post">
   <input type="hidden" name="mastersDTO.formName" value="Designation" />
   <input type="hidden" name="mastersDTO.formId" value="7" />
<div id="tabs-7" style="background-color:#FF0000;  font-size:11px">
    <div align="left" class="bkgColor"> 
		<h2>Designation</h2>
   <table class="" width="580" height="90"  border="0" align="center">
      <tr>
       <td width="138" height="39" align="left"><label>Name<span style="color: #FF0000"> *
       </span></label></td>
       <td width="202"><form:input onkeypress="return check(event)" path="mastersDTO.name" data-maxsize="55" class="validate[required] text-input"     size="22" id="name" /></td>
       <td width="61" height="39"><label>  Code<span style="color: #FF0000"> *</span></label></td>
       <td width="207"><form:input onkeyup="valid1(this)" onblur="valid1(this)"   path="mastersDTO.code" data-maxsize="16" class="validate[required] text-input" size="22" id="code" /></td>
     </tr>
     <tr>
        <td height="26" align="left"><label>Empolyee Type<span style="color: #FF0000"> *
       </span></label></td>
       <td>
       <form:select path="mastersDTO.empTypeId"  items="${empTypeList}"  itemLabel="name" itemValue="mastersId" class="validate[required] text-input" id="employeeType"></form:select>
       
      </td>
       <td height="26">&nbsp;</td>
       <td>&nbsp;</td>
     </tr>
	<tr>
       <td height="29" align="left"><label>Description</label></td>
       <td height="29" colspan="3"><form:textarea  onkeypress="return check(event)" data-maxsize="150" path="mastersDTO.description"  cols="62" id="description" /></td>
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
	
	
	
	
<form:form  id="formID9" action="save_masters#tabs-8" modelAttribute="mastersForm" method="post">
   <input type="hidden" name="mastersDTO.formName" value="Department" />
   <input type="hidden" name="mastersDTO.formId" value="8" />
 
<div id="tabs-8" style="background-color:#FF0000;  font-size:11px">
    <div align="left" class="bkgColor"> 
	<h2>Department</h2>	
   <table class="" width="580" height="74"  border="0" align="center">
     <tr>
       <td width="107" height="39" align="left"><label>Name<span style="color: #FF0000"> *
       </span></label></td>
       <td width="202"><form:input onkeypress="return check(event)"   data-maxsize="55" class="validate[required] text-input"  path="mastersDTO.name"   size="22" id="name" /></td>
       <td width="61" height="39"><label>  Code<span style="color: #FF0000"> *</span></label></td>
       <td width="200"><form:input onkeyup="valid1(this)" onblur="valid1(this)"    path="mastersDTO.code" data-maxsize="16" class="validate[required] text-input" size="22" id="code" /></td>
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
  
  <form:form  id="formID10" action="save_masters#tabs-9" modelAttribute="mastersForm" method="post">
   <input type="hidden" name="mastersDTO.formName" value="Sub Department" />
   <input type="hidden" name="mastersDTO.formId" value="9" />
  
<div id="tabs-9" style="background-color:#FF0000;  font-size:11px">
    <div align="left" class="bkgColor"> 
		<h2>Sub Department</h2>
   <table class="" width="582" height="88"  border="0" align="center">
     <tr>
       <td width="97" height="26" align="left"><label>Name<span style="color: #FF0000"> *
       </span></label></td>
       <td width="206"><form:input onkeypress="return check(event)"  path="mastersDTO.name" data-maxsize="55" class="validate[required] text-input"  size="22" id="name" /></td>
       <td width="53" height="26"><label>  Code<span style="color: #FF0000"> *</span></label></td>
       <td width="208"><form:input onkeyup="valid1(this)" onblur="valid1(this)"   path="mastersDTO.code" data-maxsize="16" class="validate[required] text-input" size="22"
        id="code" /></td>
     </tr>
     <tr>
        <td height="25" align="left"><label>Department<span style="color: #FF0000"> *
       </span></label></td>
       <td>
       <form:select path="mastersDTO.deptId" items="${deptTypeList}" itemLabel="name" itemValue="mastersId" data-maxsize="55" class="validate[required] text-input"  id="department"></form:select>
       
      </td>
       <td height="25">&nbsp;</td>
       <td>&nbsp;</td>
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
  
 <form:form  id="formID12"  action="save_masters#tabs-10" modelAttribute="mastersForm" method="post">
   <input type="hidden" name="mastersDTO.formName" value="Holiday Master" />
   <input type="hidden" name="mastersDTO.formId" value="10" />
<div id="tabs-10" style="background-color:#FF0000;  font-size:11px">
    <div align="left" class="bkgColor"> 
	<h2>Holiday Master</h2>	
   <table class="" width="" height="88"  border="0" align="center">
     <tr>
       <td width="162" height="24" align="left"><label>&nbsp;Name<span style="color: #FF0000"> *
       </span></label></td>
       <td width="133"><form:input onkeypress="return check(event)"  path="mastersDTO.name" data-maxsize="55" class="validate[required] text-input" size="18" id="name" /></td>
       <td width="154" height="24"><label>  Code<span style="color: #FF0000"> *</span></label></td>
       <td width="135"><form:input onkeyup="valid1(this)" onblur="valid1(this)"   path="mastersDTO.code" data-maxsize="16" class="validate[required] text-input"  size="18" id="code" /></td>
     </tr>
     <tr>
        <td height="27" align="left"><label> Holiday From Date<span style="color: #FF0000"> *
       </span></label></td>
       <td><form:input path="mastersDTO.holidayFromDate" id="holidayFromDate" class="datepicker validate[required] text-input" size="18" /></td>
       <td height="27" align="left"><label> Holiday To Date<span style="color: #FF0000"> *
       </span></label></td>
       <td><form:input path="mastersDTO.holidayToDate" id="holidayToDate" class="datepicker validate[required] text-input" size="18" /></td>
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
  



<form:form  id="formID13" action="save_masters#tabs-11" modelAttribute="mastersForm" method="post">
  
       
        
        <input type="hidden" name="mastersDTO.formName" value="Shift Master" />
   <input type="hidden" name="mastersDTO.formId" value="11" />
<div id="tabs-11" style="background-color:#FF0000;  font-size:11px">
    <div align="left" class="bkgColor">
		<h2>Shift master</h2> 

   <table class="" width="" height="109"  border="0" align="center">
     <tr>
       <td width="137" height="24" align="left"><label>Name<span style="color: #FF0000"> *
       </span></label></td>
       <td width="158"><form:input onkeypress="return check(event)"   path="mastersDTO.name" data-maxsize="55" class="validate[required] text-input"  size="18" id="name" /></td>
       <td width="128" height="24"><label>  Code<span style="color: #FF0000"> *</span></label></td>
       <td width="143"><form:input onkeyup="valid1(this)" onblur="valid1(this)"   path="mastersDTO.code" data-maxsize="16" class="validate[required] text-input" size="18" id="code" /></td>
     </tr>
     <tr>
      <td height="27" align="left"><label> Shift Start Time<span style="color: #FF0000"> *
       </span></label></td>
       <td><form:input path="mastersDTO.shiftFromTime" readonly="true" class="myTimePicker validate[required] text-input" size="18" 
       id="shiftStartTime" /></td>
    <td height="27" align="left"><label> Shift End Time<span style="color: #FF0000"> *
       </span></label></td>
       <td><form:input path="mastersDTO.shiftToTime"  readonly="true" class="myTimePicker validate[required] text-input" size="18" 
       id="shiftEndTime" /></td>
     </tr>
     <tr>
        <td height="24" align="left"><label>Working Hour<span style="color: #FF0000"> *
       </span></label></td>
       <td><form:input path="mastersDTO.workingHour"  readonly="true" class="myTimePicker validate[required] text-input" size="18" id="workingHour" /></td>
       <td height="24" align="left"><label>Late Permit<span style="color: #FF0000"> *
       </span></label></td>
       <td><form:input path="mastersDTO.latePermit" readonly="true" class="myTimePicker validate[required] text-input" size="18" id="letePermit" /></td>
     </tr>
	 <tr>
       <td height="24" align="left"><label>Description</label></td>
       <td height="24" colspan="3"><form:textarea  onkeypress="return check(event)" data-maxsize="150" path="mastersDTO.description"  cols="64" id="description" /></td>
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





<form:form  id="formID8" action="save_masters#tabs-12" modelAttribute="mastersForm" method="post">
   <input type="hidden" name="mastersDTO.formName" value="VAT Form Type" />
   <input type="hidden" name="mastersDTO.formId" value="12" />
 
<div id="tabs-12" style="background-color:#FF0000;  font-size:11px">
    <div align="left" class="bkgColor"> 
	<h2>VAT Form Type</h2>	
   <table class="" width="580" height="74"  border="0" align="center">
     <tr>
       <td width="107" height="39" align="left"><label>Name<span style="color: #FF0000"> *
       </span></label></td>
       <td width="202"><form:input onkeypress="return check(event)"  data-maxsize="55" class="validate[required] text-input"  path="mastersDTO.name"   size="22" id="name" /></td>
       <td width="61" height="39"><label>  Code<span style="color: #FF0000"> *</span></label></td>
       <td width="200"><form:input onkeyup="valid1(this)" onblur="valid1(this)"    path="mastersDTO.code" data-maxsize="16" class="validate[required] text-input" size="22" id="code" /></td>
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
  

<form:form  id="formID14" action="save_masters#tabs-13" modelAttribute="mastersForm" method="post">
   <input type="hidden" name="mastersDTO.formName" value="Item Class" />
   <input type="hidden" name="mastersDTO.formId" value="13" />
 
<div id="tabs-13" style="background-color:#FF0000;  font-size:11px">
    <div align="left" class="bkgColor"> 
	<h2>Item Class</h2>	
   <table class="" width="580" height="74"  border="0" align="center">
     <tr>
       <td width="107" height="39" align="left"><label>Name<span style="color: #FF0000"> *
       </span></label></td>
       <td width="202"><form:input onkeypress="return check(event)"  data-maxsize="55" class="validate[required] text-input"  path="mastersDTO.name"   size="22" id="name" /></td>
       <td width="61" height="39"><label>  Code<span style="color: #FF0000"> *</span></label></td>
       <td width="200"><form:input onkeyup="valid1(this)" onblur="valid1(this)"   path="mastersDTO.code" data-maxsize="16" class="validate[required] text-input" size="22" id="code" /></td>
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
  


<form:form  id="formID3" action="save_masters#tabs-14" modelAttribute="mastersForm" method="post">
   <input type="hidden" name="mastersDTO.formName" value="Specialisation" />
   <input type="hidden" name="mastersDTO.formId" value="14" />
 
<div id="tabs-14" style="background-color:#FF0000;  font-size:11px">
    <div align="left" class="bkgColor"> 
	<h2>Specialization</h2>	
   <table class="" width="580" height="74"  border="0" align="center">
     <tr>
       <td width="107" height="39" align="left"><label>Name<span style="color: #FF0000"> *
       </span></label></td>
       <td width="202"><form:input onkeypress="return check(event)" data-maxsize="55" class="validate[required] text-input"  path="mastersDTO.name"   size="22" id="name" /></td>
       <td width="61" height="39"><label>  Code<span style="color: #FF0000"> *</span></label></td>
       <td width="200"><form:input onkeyup="valid1(this)" onblur="valid1(this)"   path="mastersDTO.code" data-maxsize="16" class="validate[required] text-input" size="22" id="code" /></td>
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
  

<form:form  id="formID15" action="save_masters#tabs-15" modelAttribute="mastersForm" method="post">
   <input type="hidden" name="mastersDTO.formName" value="Pack Type" />
   <input type="hidden" name="mastersDTO.formId" value="15" />
 
<div id="tabs-15" style="background-color:#FF0000;  font-size:11px">
    <div align="left" class="bkgColor"> 
	<h2>Pack Type</h2>	
   <table class="" width="580" height="74"  border="0" align="center">
     <tr>
       <td width="107" height="39" align="left"><label>Name<span style="color: #FF0000"> *
       </span></label></td>
       <td width="202"><form:input onkeypress="return check(event)"   data-maxsize="55" class="validate[required] text-input"  path="mastersDTO.name"   size="22" id="name" /></td>
       <td width="61" height="39"><label>  Code<span style="color: #FF0000"> *</span></label></td>
       <td width="200"><form:input onkeyup="valid1(this)" onblur="valid1(this)"    path="mastersDTO.code" data-maxsize="16" class="validate[required] text-input" size="22" id="code" /></td>
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

 <form:form  id="formID16" action="save_masters#tabs-16" modelAttribute="mastersForm" method="post">
   <input type="hidden" name="mastersDTO.formName" value="Item Grade" />
   <input type="hidden" name="mastersDTO.formId" value="16" />
 
<div id="tabs-16" style="background-color:#FF0000;  font-size:11px">
    <div align="left" class="bkgColor"> 
	<h2>Item Grade</h2>	
   <table class="" width="580" height="74"  border="0" align="center">
     <tr>
       <td width="107" height="39" align="left"><label>Name<span style="color: #FF0000"> *
       </span></label></td>
       <td width="202"><form:input onkeypress="return check(event)"   data-maxsize="55" class="validate[required] text-input"  path="mastersDTO.name"   size="22" id="name" /></td>
       <td width="61" height="39"><label>  Code<span style="color: #FF0000"> *</span></label></td>
       <td width="200"><form:input onkeyup="valid1(this)" onblur="valid1(this)"   path="mastersDTO.code" data-maxsize="16" class="validate[required] text-input" size="22" id="code" /></td>
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
 
 
 <form:form  id="formID17" action="save_masters#tabs-17" modelAttribute="mastersForm" method="post">
   <input type="hidden" name="mastersDTO.formName" value="Measurement Unit" />
   <input type="hidden" name="mastersDTO.formId" value="17" />
 
<div id="tabs-17" style="background-color:#FF0000;  font-size:11px">
    <div align="left" class="bkgColor"> 
	<h2>Measurement Unit</h2>	
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
  
  <form:form  id="formID18" action="save_masters#tabs-18" modelAttribute="mastersForm" method="post">
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
  
  
   <form:form  id="formID19" action="save_masters#tabs-19" modelAttribute="mastersForm" method="post">
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
  
  </div>





