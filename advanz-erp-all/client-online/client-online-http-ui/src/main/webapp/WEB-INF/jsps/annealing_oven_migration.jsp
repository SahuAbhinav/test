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
 
<script type="text/javascript">
      $(document).ready(function()
       {     
    	  
		    $("#onDate").datepicker({
				changeMonth : true,
				changeYear : true,
				dateFormat : 'dd-M-yy',changeMonth: true,
		        changeYear: true, yearRange: '-99:+0', maxDate: '+0M +0D'
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
			window.self.location = "get_blanketProduction_list";
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

-->
</style>


	<form:form name="input" id="formID" action="updateBlanketLeftRight" modelAttribute="blanketProductionMasterForm" >
	<form:hidden path="blanketProductionMasterDTO.blanketProductionId" id="sno"/>
		<div class="panel-header" >
			<div class="panel-title">Annealing Oven Migration</div>
		
		</div>

<table >
				 <tr>
 <td>Date</td>
  <td align="right"><form:input path="onDate"  id="onDate" style="size: 20;" /></td><td align="right"></td>
  <td>Shift</td>
  <td><form:select path="blanketProductionMasterDTO.shiftMasterDTO.mastersId"
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

				
					<div class="gridheadingdiv"><fieldset><legend style="color: #15428B">Blanket Production Left</legend>
					<table width="668" id="tbrollL"  class="fixmyheader-8" >
  <thead>
   <tr>
				 
						<td width="32"><div align="center">								<strong>S.No.</strong>							</div>						</td>
						
						<td width="59"><div align="center">								<strong>Run No</strong>							</div>						</td>
						<td width="63"><div align="center">								<strong>Date</strong>							</div>						</td>
						<td width="63"><div align="center">								<strong>Shift</strong>							</div>						</td>
						<td width="61"><div align="center">								<strong>Batch</strong>						</div>						</td>
						<td width="68"><div align="center">								<strong>Roll No. (L)</strong>							</div>						</td>
						<td width="58"><div align="center">								<strong>Type</strong>							</div>						</td>
						<td width="100"><div align="center">								<strong>New Status</strong>							</div>						</td>
						
					</tr>
				 
					
					
					<tbody>
					<c:forEach items="${blanketProductionMasterForm.blanketProductionLeftList}" var="bpd" varStatus="s"> 
					<tr id="Lr${s.index}">
						<td style="" width="12" ><label>${s.count }</label></td>
						<td width="31" ><form:input path="blanketProductionLeftList[${s.index}].runNo"
							readonly="true" cssStyle="width:100%; "/>
						<form:hidden path="blanketProductionLeftList[${s.index}].sno" />
						</td>
						<td width="35" ><form:input path="blanketProductionLeftList[${s.index}].shiftDate"
							readonly="true" cssStyle="width:100%; "/>
						</td>
						<td width="34"><form:input path="blanketProductionLeftList[${s.index}].shift"
							readonly="true" cssStyle="width:100%; "/>
						</td>
						<td width="32" ><form:input path="blanketProductionLeftList[${s.index}].batchNo"
							readonly="true" cssStyle="width:100%; "/>
						</td>
						<td width="37" ><form:input path="blanketProductionLeftList[${s.index}].rollNoLeft"
							readonly="true" cssStyle="width:100%; "/>
							
						</td>
						<td width="31" ><form:input path="blanketProductionLeftList[${s.index}].blanketType"
							readonly="true" cssStyle="width:100%; "/>
						</td> 
						<td width="61" ><form:select path="blanketProductionLeftList[${s.index}].statusLeft" cssStyle="width:100%; " id="blanketTypeL${s.index}">
							<form:option value="OK">OK</form:option>
							<form:option value="A Grade">A Grade</form:option>
							<form:option value="R.A.">R.A.</form:option>
							<form:option value="Rejection">Rejection</form:option>
							</form:select>
						</td>
						
					</tr>
 	</c:forEach>
  <tbody></thead>
	</table></fieldset>
					<div class="gridheadingdiv" >
					<fieldset><legend style="color: #15428B">Blanket Production Right</legend>
					<table width="668" id="tbrollR"  class="fixmyheader-8" >
					   <thead>
					   <tr> 
						
						<td width="32"><div align="center">								<strong>S.No.</strong>							</div>						</td>
						
						<td width="59"><div align="center">								<strong>Run No</strong>							</div>						</td>
						<td width="63"><div align="center">								<strong>Date</strong>							</div>						</td>
						<td width="63"><div align="center">								<strong>Shift</strong>							</div>						</td>
						<td width="61"><div align="center">								<strong>Batch</strong>						</div>						</td>
						<td width="68"><div align="center">								<strong>Roll No. (L)</strong>							</div>						</td>
						<td width="58"><div align="center">								<strong>Type</strong>							</div>						</td>
						<td width="100"><div align="center">								<strong>New Status</strong>							</div>						</td>
						
					</tr>
					
				    <tbody >
				    <c:forEach items="${blanketProductionMasterForm.blanketProductionRightList}" var="bpd" varStatus="s">
					<tr id="Rr${s.index}">
						<td style="" width="12" ><label>${s.count }</label></td>
						<td width="31" ><form:input path="blanketProductionRightList[${s.index}].runNo"
							readonly="true" cssStyle="width:100%; " />
						<form:hidden path="blanketProductionRightList[${s.index}].sno" />
						</td>
						<td width="35" ><form:input path="blanketProductionRightList[${s.index}].shiftDate"
							readonly="true" cssStyle="width:100%; "/>
						</td>
						<td width="34"><form:input path="blanketProductionRightList[${s.index}].shift"
							readonly="true" cssStyle="width:100%; "/>
						</td>
						<td width="32" ><form:input path="blanketProductionRightList[${s.index}].batchNo"
							readonly="true" cssStyle="width:100%; "/>
						</td>
						<td width="37" ><form:input path="blanketProductionRightList[${s.index}].rollNoRight"
							readonly="true" cssStyle="width:100%; "/>
							
						</td>
						<td width="31" ><form:input path="blanketProductionRightList[${s.index}].blanketType"
							readonly="true" cssStyle="width:100%; "/>
						</td> 
						<td width="61" ><form:select path="blanketProductionRightList[${s.index}].statusRight" cssStyle="width:100%; " id="blanketTypeL${s.index}">
							<form:option value="OK">OK</form:option>
							<form:option value="A Grade">A Grade</form:option>
							<form:option value="R.A.">R.A.</form:option>
							<form:option value="Rejection">Rejection</form:option>
							</form:select>
						</td>
					</tr>
					</c:forEach>
					<tbody>
					</thead>
				</table></fieldset>
</div>
</div>
<input class="updatebtn" style="margin-top: 10px;margin-left: 5px; " type="submit" name="operation" value="Update" />
</form:form>