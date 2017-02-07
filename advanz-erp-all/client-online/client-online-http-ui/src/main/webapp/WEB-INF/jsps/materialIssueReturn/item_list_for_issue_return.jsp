<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script type="text/javascript" charset="utf-8">

$(document).ready(function() {
    /* Initialise datatables */
    var oTable = $('#example').dataTable({
    	   "aLengthMenu": [['',10, 25, 50, -1], ['',10, 25, 50, "All"]],
           "iDisplayLength":10000,
           "bPaginate": false,
           bInfo:""
	       });
} );    
</script>
	<script type="text/javascript">
			$(document).ready(function() {  	
				$('.fixmyheader-8').fixheadertable({
					caption		: 'My header is fixed !',
					height		:318,
					addTitles	: true,
					colratio	: ['10%', '10%', '8%', '50px', 'auto', 'auto', '30%', 'auto']
				});
			});
	</script>
     
	<style type="text/css">
	body {
	font-family:Arial, Helvetica, sans-serif;
	}
	code, pre {				
				padding		: 10px;	
				background	: #F5F5F5;
				border		: 1px solid #D4D4D4;
				overflow-x	: auto;
				font-size	: 12px;
			}	
	
	th{font-size:10px;}
 	td{font-size:12px;}  
 
 	
	div.t_fixed_header_main_wrapper.ui div.t_fixed_header_caption {
		display:none;
		}
 		.ui-widget-content {
	overflow-x: hidden !important;
 	}
	</style>

	<script>
	$(document).ready(function() {
		$(".cancelbtn").click(function() {
			window.self.location = "backto_issue_return";
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

<script>
	$(document).ready(
			function() {
				//called when key is pressed in textbox
				$(".quantity").keypress(
						function(e) {
							//if the letter is not digit then display error and don't type anything
							if (e.which != 8 && e.which != 46 && e.which != 0
									&& (e.which<48 || e.which>57)) {
								//display error message
								$(".errmsg").html("Digits Only").show()
										.fadeOut("slow");
								return false;
							}
						});

			});
	$(document).ready(
			function() {
				//called when key is pressed in textbox
				$(".quantity1").keypress(
						function(e) {
							//if the letter is not digit then display error and don't type anything
							if (e.which != 8 && e.which != 46 && e.which != 0
									&& (e.which<48 || e.which>57)) {
								//display error message
								$(".errmsg1").html("Digits Only").show()
										.fadeOut("slow");
								return false;
							}
						});

			});
</script>


<script>
	$(document).ready(function() {
		$("button").button();
		//$('input:text, input:password').button()   
		$(".datepicker").datepicker();
		//      $(":submit").button()
	});
</script>

<script>
	$(document).ready(function() {		
		function isValidNumber(x){
			//	alert(x);	
			if(x!="null" && x!=null )
				return true;
		
				
			return false;
		}
		
		$(function() {
			$('.sr').click(function() {
				//$('.sr').css("background-color","white");
				$('.sr').css("background-color","white");
				$(this).css("background-color","yellow");
				$("#itemIdSO").val($(this).attr('id'));
				$.get('get_item_by_idr', { id: $(this).attr('id')}, function(data) {	
					$("#itemIdSO").val(data.itemId);		
					
					if(isValidNumber(data.itemLength))
					 	$("#length").val(data.itemLength.toFixed(2));
					else
						$("#length").val(data.itemLength);
					
					if(isValidNumber(data.itemWidth))
					 	$("#width").val(data.itemWidth.toFixed(2));
					else
						$("#width").val(data.itemWidth);
					
					if(isValidNumber(data.minStock))
					 	$("#minStock").val(data.minStock.toFixed(2));
					else
						$("#minStock").val(data.minStock);
					
					if(isValidNumber(data.maxStock))
	 					$("#maxStock").val(data.maxStock.toFixed(2));
					else
						$("#maxStock").val(data.maxStock);

					if(isValidNumber(data.reorderLevel))
	 					$("#reorderLevel").val(data.reorderLevel.toFixed(2));
					else
						$("#reorderLevel").val(data.reorderLevel);
					
					if(isValidNumber(data.suggReorderQty))
	 					$("#suggqty").val(data.suggReorderQty.toFixed(2));
					else
						$("#suggqty").val(data.suggReorderQty);

					if(isValidNumber(data.itemDensity))
	 					$("#density").val(data.itemDensity.toFixed(2));
					else
						$("#density").val(data.itemDensity); 
					
					
					if(isValidNumber(data.itemHeight))
 						$("#thikness").val(data.itemHeight.toFixed(2));
					else
						$("#thikness").val(data.itemHeight); 			
					 					
					 					
					 					
					});
				// $(this).toggleClass('active');
				//alert($(this).attr('id'));
			});
		});
	});
	function submitForm(){
		document.forms["formID"].submit();
	}
	function setIssueNumber(ele,issueQuantity,issueDate){
		var issueNumber=ele;
		var issueQuantity=issueQuantity;
		var issueDate=issueDate;
		$("#issueQuantity").val(issueQuantity);
		$("#issueDate").val(issueDate);
		$("#issueNumberId").val(issueNumber);
	}
</script>
<style type="text/css">
.sr {
	background-color: white;
	 
}
</style>
<style type="text/css">
p {
	color: blue;
}
 
 

.gridheadingdiv td {
	height: 22px;
}
 .dataTables_info {
    width: 761px !important;
}
select {
	width:90px;
	height:22px;
	}


.ino{width:54px !important; border:none !important}
  .idate{width:48px !important; border:none !important}
  .iBy{width:66px !important; border:none !important}
  .rb{width:66px !important; border:none !important}
  .lt{width:66px !important; border:none !important}
  .dpt{width:66px !important; border:none !important}
  .pqty{width:40px !important; border:none !important}
  
  .icode{width:40px !important; border:none !important} 
  .iname{width:83px !important; border:none !important} 
</style>

	
		<div class="panel-header">
		<div class="panel-title">Item List</div>
		<div class="panel-tool"></div>
		</div>
		<div align="left" class="bkgColor" style="height: auto;x">
		<form:form name="input" id="formID" action="show_item_list_issue_return" method="post" modelAttribute="issueReturnMasterForm">
		
			 <div class="headingdiv"  >
				<table width="628" height="31" border="0" cellpadding="0"
					cellspacing="0">
					<tr>
					<td width="250"><div
									style="border: solid 1px; height: 20px; width: 100%; border-radius: 3px 3px 3px 3px; border-color: #7f9db9; background-color: #FFF;">
									<span style="    float: left;    margin-top:2px;    padding-left: 2px;">Returnable</span>
									<form:radiobutton style="width:20px; float: left; "
										path="operationFlage"  value="0" onclick="submitForm();"/>
								
								<span style="float: left;    margin-top: 2px;  ">All</span>
								<form:radiobutton style="width:20px; " path="operationFlage"
										 value="1" onclick="submitForm();" />
								</div>
								</td>
								<td width="50"><div align="center">Issue Number</div>
						</td>
						<td width="50"><form:input path="issueNo" size="10"
							id="sonumber" />
						</td>
						<td width="50"><div align="center"> Raised By</div>
						</td>
						<td width="50"><form:input path="raisedBy" size="11"
							 />
						</td>
						
						
 <td width="50"><div align="center"> Loan Type</div></td>
	<td><form:select  path="loanType" >
	<form:option value=""></form:option>
	<form:option value="PERSONAL">PERSONAL</form:option>
	<form:option value="DEPARTMENTAL">DEPARTMENTAL</form:option>
	<form:option value="CONTRACTOR">CONTRACTOR</form:option>
	</form:select></td>
						
						
						
						
						
						<td width="50"><div align="center">Item Code</div>
						</td>
						<td width="50"><form:input path="itemCode" size="12"
							id="sonumber" />
						</td>
						<td width="50"><div align="center">Item Name</div>
						</td>
						<td width="50"><form:input path="itemName"
					id="invoiceName" size="12" />
						</td>
						
						<td width="69"><input class="searchbtn"
							style="font-size: 11px; font-weight: bold; padding: 0 0 0 20px;"
							type="submit" name="operation" value="search" />
						</td>
							
						<td width="69" align="right"><div >
						<input type="hidden" name="itemID" value="" id="itemIdSO">
						<input type="hidden" name="issueNumber" value="" id="issueNumberId">
						<input type="hidden" name="issueQuantity" value="" id="issueQuantity"> 
						<input type="hidden" name="issueDate" value="" id="issueDate">
						<input type="submit" id="btnok" name="btn" value="&nbsp;" class="okbtn"  style="font-size: 11px; font-weight: bold; border:0px;  padding: 0 0 0  ;"/>    					
						</div></td>
						<td width="69">
					          <!-- <a href="new_issues" class="cancelbtn" ></a> -->
					           <input name="operation" type="submit" class="cancelbtn"  style="font-size: 11px; font-weight: bold; border:0px;  padding: 0 0 0 10px;" value=" " onclick="this.value='Cancel';"/>
						</td>
						 
					</tr>
				</table>
			</div> 
			
	</form:form>
			<div style="width: 906px; margin: 0 auto;">
				<span style="margin-left: 80px;" class="errmsg"></span>
			</div>
			<div class="gridheadingdiv"  >
			   <table width="972" class="display fixmyheader-8" id="example">
  <thead>
   <tr>
   <td style="border: none;" class="ino"><div align="center">	<strong>Issue Number</strong>	</div>						</td>
   <td style="border: none; " class="idate"><div align="center">	<strong>Issue Date</strong>	</div>						</td>
   <td style="border: none; " class="iBy"><div align="center">	<strong>Issue By</strong>	</div>						</td>
   <td style="border: none;" class="rb"><div align="center">	<strong>Raised By</strong>	</div>						</td>
   <td style="border: none;" class="lt"><div align="center">	<strong>Loan Type</strong>	</div>						</td>
   
   
   <td style="border: none;" class="dpt"><div align="center">	<strong>Department</strong>	</div>						</td>
       
<td style="border: none;" class="pqty"><div align="center">	<strong>Pending Qty</strong>	</div>						</td>
						<td style="border: none;" class="icode"><div align="center">	<strong>Item code</strong>	</div>						</td>
						<td style="border: none;" class="iname"><div align="center">	<strong>Item name</strong></div>						</td>
												
						
				
					</tr>
  </thead>
  <tbody> 	<c:forEach items="${itemList}" var="item">
						<tr class="sr" style="cursor: pointer;" onclick="setIssueNumber('${item.issueNumber}','${item.issueQuantity}','${item.issueDate}');" id="${item.itemId}">
					
					<td width="50">&nbsp;<c:out value="${item.issueNumber}" />
					<td width="44">&nbsp;<fmt:formatDate pattern="dd-MMM-yyyy" value="${item.issueDate}" />
					<td width="65">&nbsp;<c:out value="${item.issuedBy}" />
					<td width="65">&nbsp;<c:out value="${item.raisedBy}" />
					<td width="65">&nbsp;<c:out value="${item.loanType}" />
					
					<td width="65">&nbsp;<c:out value="${item.departmentName}" /></td>
					<td width="40">&nbsp;<c:out value="${item.pendingQuantity}" />							</td>
					<td width="38">&nbsp;<c:out value="${item.itemCode}" />							</td>
					<td width="100">&nbsp;<c:out value="${item.itemName}" />	</td>
					</tr>

					</c:forEach>
  
  </tbody>
</table>
  <div><c:url value="show_item_list_issue_return" var="item_url">
			<c:param name="next" value="${issueReturnMasterForm.issueReturnMasterDTO.previous}"></c:param>
			</c:url> <a href="${item_url}" class="previousbtn" ></a>
		</div>
         <div><c:url value="show_item_list_issue_return" var="item_url">
			<c:param name="next"  value="${issueReturnMasterForm.issueReturnMasterDTO.next}"></c:param>
			</c:url> <a href="${item_url}" class="nextbtn" ></a>
		</div>
 

			</div>
			<table height="78" width="792" border="0" style="margin-top: 12px;">
				<tr>
					<td width="139" height="36">Length<span style="color: #FF0000"></span>
					</td>
					<td width="108"><input type="text" name="length"
						data-maxsize="11" readonly="readonly" disabled="disabled"
						size="11" id="length" />
					</td>
					<td width="113">Width</td>
					<td width="130"><input type="text" name="width"
						data-maxsize="11" readonly="readonly" disabled="disabled"
						size="11" id="width" />
					</td>
					<td width="117">Thickness</td>
					<td width="92"><input type="text" name="thikness"
						data-maxsize="11" readonly="readonly" disabled="disabled"
						size="11" id="thikness" />
					</td>
					<td width="119">Density</td>
					<td width="115"><input type="text" name="density"
						data-maxsize="11" readonly="readonly" disabled="disabled"
						size="11" id="density" />
					</td>
				</tr>
				<tr>
					<td height="36">Min Stock</td>
					<td><input type="text" name="minStock" data-maxsize="11"
						readonly="readonly" disabled="disabled" size="11" id="minStock" />
					</td>
					<td>Max Stock</td>
					<td><input type="text" name="maxStock" data-maxsize="11"
						readonly="readonly" disabled="disabled" size="11" id="maxStock" />
					</td>
					<td>Re-order Level</td>
					<td><input type="text" name="reorderLevel" data-maxsize="11"
						readonly="readonly" disabled="disabled" size="11"
						id="reorderLevel" />
					</td>
					<td>Sugg. Qty</td>
					<td><input type="text" name="suggqty" data-maxsize="11"
						readonly="readonly" disabled="disabled" size="11" id="suggqty" />
					</td>
				</tr>
			</table>


		
<div>
</div>

	</div>