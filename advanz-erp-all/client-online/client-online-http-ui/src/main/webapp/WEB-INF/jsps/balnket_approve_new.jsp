<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page isELIgnored="false"%>

<script type="text/javascript">
			
			$(document).ready(function() {  	
				$('.fixmyheader-8').fixheadertable({
					caption		: 'My header is fixed !',
					height		:90,
					addTitles	: true,
					colratio	: ['10%', '10%', '8%', '50px', 'auto', 'auto', '30%', 'auto']
				});
			});
		</script>
 
<style type="text/css">
div.t_fixed_header table {
 border-collapse: separate !important;
}
#tbrollL  td{
border-color: solid;
border-width: 0 1px 1px 0;}

body {
	font-family:Arial, Helvetica, sans-serif;
	}
code, pre {				
				padding		: 10px;	
				background	: #F5F5F5;
				border		: 0px solid #D4D4D4;
				overflow-x	: auto;
				font-size	: 12px;
			}
			div.t_fixed_header_main_wrapper.ui {
			float: left;
			}	
	
th{font-size:10px;}
 td{font-size:12px;}  
 
 	
	div.t_fixed_header_main_wrapper.ui div.t_fixed_header_caption {
		display:none;
		
		}
	
</style>



  
 
 <style>
 table{
 width: 971px;
 
 }
 
   .newRow {
		background:url(static/images/new.png);
		background-repeat:no-repeat;
		height:22px;
		border:none;
		width:12px;
		cursor: pointer;
		
		}
	.ui-widget-content {
		
		height: 113px;
		}
	.remRow{}
	.editRow{}
</style> 
<script>
	$(document).ready(function() {
		$(".cancelbtn").click(function() {
			window.self.location = "get_blanketProduction_list_new";
		});

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
      $(document).ready(function()
       {     
	    $(".datepicker1" ).datepicker({dateFormat : 'dd-M-yy',changeMonth: true,
	          changeYear: true, yearRange: '-99:+0', maxDate: '+0M +0D'});
      });
  </script>


<style type="text/css">
<!--
#formID .bkgColor table tr td label {
	
}
.scroll {
height: 114px;
overflow-x:hidden;
overflow-y:scroll;  

}
input {
    border: medium none;
    width: 100px;
}
.tableview{     border: 0px solid #7F9DB9;
    border-radius: 3px 3px 3px 3px;
    margin-bottom: 7px;
    margin-left: 5px;
    margin-top: 5px;
    padding: 7px;
    width: 961px;
}
.fixmyheader-8 {
float: left;
}


.gridheadingdiv td {
	height: 22px;
	text-align: center;
}

.gridheadingdiv input {
	border: medium none;
	width: 75px;
}

-->



element.style {
    display: block;
    left: 151.95px;
    position: absolute;
    top: 145.4px;
    z-index: 1;
}
.ui-helper-clearfix:before, .ui-helper-clearfix:after {
    display: table;
}
.ui-helper-clearfix:after {
    clear: both;
    content: ".";
    display: none;
    height: 0;
    visibility: hidden;
}
.ui-helper-clearfix:after {
    clear: both;
}
.ui-helper-clearfix:before, .ui-helper-clearfix:after {
    display: table;
}
.ui-widget-content {
    display: none;
}
.ui-datepicker {
    padding: 0.2em 0.2em 0;
    width: 17em;
}
.ui-corner-all {
    border-radius: 0;
}
.ui-widget-content {
    background: url("images/ui-bg_highlight-soft_100_eeeeee_1x100.png") repeat-x scroll 50% top #EEEEEE;
    border: 1px solid #99BBE8;
    color: #333333;
}
.ui-widget {
    font-family: Trebuchet MS,Tahoma,Verdana,Arial,sans-serif;
    font-size: 1.1em;
}
.ui-helper-clearfix {
    display: block;
}
.ui-helper-clearfix {
    display: inline-block;
}
.ui-corner-all, .ui-corner-bottom, .ui-corner-right, .ui-corner-br {
    border-bottom-right-radius: 4px;
}
.ui-corner-all, .ui-corner-bottom, .ui-corner-left, .ui-corner-bl {
    border-bottom-left-radius: 4px;
}
.ui-corner-all, .ui-corner-top, .ui-corner-right, .ui-corner-tr {
    border-top-right-radius: 4px;
}
.ui-corner-all, .ui-corner-top, .ui-corner-left, .ui-corner-tl {
    border-top-left-radius: 4px;
}
.ui-widget-content {
    background: url("images/ui-bg_flat_75_ffffff_40x100.png") repeat-x scroll 50% 50% #FFFFFF;
    border: 1px solid #AAAAAA;
    color: #222222;
}
.ui-widget {
    font-family: Verdana,Arial,sans-serif;
    font-size: 1.1em;
}
.ui-datepicker {
    display: none;
    padding: 0.2em 0.2em 0;
    width: 17em;
}
.ui-helper-clearfix {
}
* {
    font-size: 12px;
}
body {
    font-family: Arial,Sans-Serif;
    font-size: 13px;
}
body {
    font-family: helvetica,tahoma,verdana,sans-serif;
    font-size: 13px;
}
* {
    font-size: 12px;
}
* {
    font-size: 12px;
}
</style>
<script type="text/javascript">
     /*****
     when change ItemGroupFlag combo value according this value itemComboValue itemCode other 
     field witch is related to this combo should be change
     ***/
     var flagIndex; 
	 function checkLeftItem(flagIndex)
	 {
			 $.ajax({
					type : "POST",
					url : "isItemAvailable",
					data : "itemId=" + $("#itemIdLeft"+flagIndex).val(),

					success : function(response) {
						if(response.result=='Duplicate'){
							alert('Please check item width,thickness,weight,density is not available for this item');	
							 $("#itemIdLeft"+flagIndex).val('');
						}}
			 });
	 }
	 
	 var flagIndex1; 
	 function checkRightItem(flagIndex1)
	 {
			 $.ajax({
					type : "POST",
					url : "isItemAvailable",
					data : "itemId=" + $("#itemIdRight"+flagIndex1).val(),

					success : function(response) {
						if(response.result=='Duplicate'){
							alert('Please check item width,thickness,weight,density is not available for this item');	
							 $("#itemIdRight"+flagIndex1).val('');
						}}
			 }); }
	 
	 
	 
	 
	 
	 $(document).ready(function() {
		    $('#selecctall').click(function(event) {  //on click
		        if(this.checked) { // check select status
		            $('.checkbox1').each(function() { //loop through each checkbox
		                this.checked = true;  //select all checkboxes with class "checkbox1"              
		            });
		        }else{
		            $('.checkbox1').each(function() { //loop through each checkbox
		                this.checked = false; //deselect all checkboxes with class "checkbox1"                      
		            });        
		        }
		    });
		   
		    
		    $('#selecctallR').click(function(event) {  //on click
		        if(this.checked) { // check select status
		            $('.checkbox2').each(function() { //loop through each checkbox
		                this.checked = true;  //select all checkboxes with class "checkbox1"              
		            });
		        }else{
		            $('.checkbox2').each(function() { //loop through each checkbox
		                this.checked = false; //deselect all checkboxes with class "checkbox1"                      
		            });        
		        }
		    });
		   
		    
		    $("#onDate").datepicker({
				changeMonth : true,
				changeYear : true,
				dateFormat : 'dd-M-yy',changeMonth: true,
		        changeYear: true, yearRange: '-99:+0', maxDate: '+0M +0D'
			});
			
		    
		    
		});
	 </script>

	<form:form name="input" id="formID" action="updateApprovedBlanket_new"  modelAttribute="blanketProductionMasterNewForm" >
	<form:hidden path="blanketProductionMasterNewDTO.blanketProductionId" id="sno"/>
		<div class="panel-header" >
			<div class="panel-title">Approved Blanket Production New</div>
		
		</div>
		
<table >
				 <tr>
 <td>Date</td>
  <td align="right"><form:input path="onDate"  id="onDate" style="size: 20;" /></td><td align="right"></td>
  <td>Shift</td>
  <td><form:select path="blanketProductionMasterNewDTO.shiftMasterDTO.mastersId"
						style="height: 21px; width: 52%;" id="shift">
						<form:option value="0" selected="selected">All</form:option>
						<form:options items="${shiftMastersList}" itemLabel="name" itemValue="mastersId"/>
						</form:select>
						</td>
 <td><input class="searchbtn"
							style="font-size: 11px; font-weight: bold; padding: 0 0 0 20px;"
							type="submit" value="Serarch" name="operation" /></td>
  </tr>
  
  </table>
					<div class="gridheadingdiv"><fieldset><legend style="color: #15428B">Blanket Production </legend>
				
					<table width="668" class="fixmyheader" id="fixmyheader-8" >
  <thead>

   <tr>
				        <td width="32"><div align="center">								<strong>S.No.</strong>							</div>						</td>
						<td width="51"><div align="center">								<strong>Roll No.</strong>							</div>						</td>
						<td width="68"><div align="center">								<strong>Spliter Type</strong>							</div>						</td>
						<td width="59"><div align="center">								<strong>Length (mm)</strong>							</div>						</td>
						<td width="63"><div align="center">								<strong>Width (mm)</strong>							</div>						</td>
						<td width="63"><div align="center">								<strong>Thick (mm)</strong>							</div>						</td>
						<td width="61"><div align="center">								<strong>Weight (Kg)</strong>						</div>						</td>
						<td width="68"><div align="center">								<strong>Density (Kg/m3)</strong>							</div>						</td>
						<td width="58"><div align="center">								<strong>Type</strong>							</div>						</td>
						<td width="58"><div align="center">								<strong>Created Date</strong>							</div>						</td>
						<td width="55"><div align="center">								<strong>Status</strong>							</div>						</td>
						<td width="55"><div align="center">								<strong>Approved All</strong>							</div>	<input type="checkbox" id="selecctall"/>					</td>
						
						<td width="55"><div align="center">								<strong>Reject All</strong>							</div>	<input type="checkbox" id="selecctallR"/>					</td>
						
  </tr>
					</thead>
					<tbody>
					<c:forEach items="${blanketProductionMasterNewForm.blanketProductionDetailNewList}" var="bpd" varStatus="s"> 
					<tr id="Lr${s.index}">
						<td style="" width="12" ><label>${s.count }</label></td>
						<td width="31" ><form:input path="blanketProductionDetailNewList[${s.index}].rollNo"
							readonly="true" cssStyle="width:100%; "/>
						<form:hidden path="blanketProductionDetailNewList[${s.index}].sno" />
						<form:hidden path="blanketProductionDetailNewList[${s.index}].itemId" />
						<form:hidden path="blanketProductionDetailNewList[${s.index}].updateDate" />
							<form:hidden path="blanketProductionDetailNewList[${s.index}].blanketProductionId" />
						
						</td>
						<td width="37" ><form:input path="blanketProductionDetailNewList[${s.index}].spliterType"
							readonly="true" cssStyle="width:100%; "/>
						</td>
						
						<c:if test="${bpd.rejItemId==null}">
						<td width="35" >
						<form:input path="blanketProductionDetailNewList[${s.index}].length"
							readonly="true" cssStyle="width:100%; "/>
							
						</td>
						<td width="34"><form:input path="blanketProductionDetailNewList[${s.index}].width"
							readonly="true" cssStyle="width:100%; "/>
						</td>
						<td width="32" ><form:input path="blanketProductionDetailNewList[${s.index}].thick"
							readonly="true" cssStyle="width:100%; "/>
						</td>
						<td width="37" ><form:input path="blanketProductionDetailNewList[${s.index}].weight"
							readonly="true" cssStyle="width:100%; "/>
							
						</td>
						<td width="31" ><form:input path="blanketProductionDetailNewList[${s.index}].density"
							readonly="true" cssStyle="width:100%; "/>
						</td> 
						
						</c:if>
						<c:if test="${bpd.rejItemId!=null}" >
						<td width="35" >
						<form:input path="blanketProductionDetailNewList[${s.index}].rejLengthl"
							readonly="true" cssStyle="width:100%; "/>
							
						</td>
						<td width="34"><form:input path="blanketProductionDetailNewList[${s.index}].rejWidthl"
							readonly="true" cssStyle="width:100%; "/>
						</td>
						<td width="32" ><form:input path="blanketProductionDetailNewList[${s.index}].rejThickl"
							readonly="true" cssStyle="width:100%; "/>
						</td>
						<td width="37" ><form:input path="blanketProductionDetailNewList[${s.index}].rejWeightl"
							readonly="true" cssStyle="width:100%; "/>
							
						</td>
						<td width="31" ><form:input path="blanketProductionDetailNewList[${s.index}].rejDensityl"
							readonly="true" cssStyle="width:100%; "/>
						</td> 
						
						</c:if>
						
						<td width="31" ><form:input path="blanketProductionDetailNewList[${s.index}].blanketType"
							readonly="true" cssStyle="width:100%; "/>
						</td>
						 <td width="31" ><form:input path="blanketProductionDetailNewList[${s.index}].creationDate"
							readonly="true" cssStyle="width:100%; "/>
						</td>
						<td width="61" >
						<form:input path="blanketProductionDetailNewList[${s.index}].status"
							readonly="true" cssStyle="width:100%; "/>
						</td><td width="61" >
						<form:checkbox class="checkbox1" path="blanketProductionDetailNewList[${s.index}].approvedStatus" value="1" />
						</td>
						<td width="61" >
						<form:checkbox class="checkbox2" path="blanketProductionDetailNewList[${s.index}].checkRejection" value="1" />
						</td>
						
						
					</tr>
 	</c:forEach>
  <tbody>
	</table></fieldset>
</div>
<input class="updatebtn" style="margin-top: 10px;margin-left: 5px; " type="submit" value=" " />
</form:form>