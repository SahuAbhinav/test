<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
 	<script type="text/javascript" charset="utf-8">
       $(document).ready(function() {
           	/* Initialise datatables */
           	var oTable = $('#example').dataTable({
             	"aLengthMenu": [[10,10, 25, 50, -1], ['Select',10, 25, 50, "All"]],
              	"iDisplayLength":10000,
              	"bPaginate": false,
              	bInfo:""
              	});
              	} );
            </script>
<script>
	$(function() {

		//$( "#datepicker" ).datepicker();
		$("#datepicker").datepicker({
			dateFormat : 'dd/mm/yy',
			autoSize : true,
			changeMonth : true,
			changeYear : true,
			altFormat : 'mm/dd/yy',
			altField : '#edd1'
		});
	});

	function remoneConformation(){
		var name =	confirm('Are you sure that you want to delete this item?');
		if(name==true){
				return true;
			} else{
			return false;
		     }
		  }
	
</script>

<c:if test="${not empty(error)}">
<input type="hidden" id="errorId" value="${error.errorMsg}">
 <script type="text/javascript">
  		$(document).ready(function() {
 		var errorId=document.getElementById('errorId');
		alert(errorId.value);
 		});
 	</script>
 </c:if>

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
			
			$(document).ready(function() {  	
				
				 
				
				$('.fixmyheader-8').fixheadertable({
					caption		: 'My header is fixed !',
					height		:330,
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
 
 		.ui-widget-content {
overflow-x: hidden !important;
 
}
	div.t_fixed_header_main_wrapper.ui div.t_fixed_header_caption {
		display:none;
		
		}
	
</style>

<script>
	$(document).ready(function() {
		$(".cancelbtn").click(function() {
			window.self.location = "backto_finishGood";
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
			$.get('get_item_by_id1', { id: $(this).attr('id')}, function(data) {	
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
</script>
<style type="text/css">
.sr {
	background-color: white;
}


element.style {
    border: medium none;
    width: 57px;
}
</style>
<style type="text/css">
p {
	color: blue;
}

 .scrolltable tr{
  cursor: pointer;;
 }

 .Ac{width: 82px !important}
.gridheadingdiv td {
	height: 22px;
}
 	 .in{width:290px !important; border:none !important}
 	 .pack{width:62px !important; border:none !important}
 	 .stock{width:75px !important; border:none !important}
 	 .basic{width:75px !important; border:none !important}
 	 .mrp{width:75px !important; border:none !important}
 .launchdate{width:60px !important; border:none !important}
 	 .wieght{width:60px !important; border:none !important}
</style>

	
		<div class="panel-header">
			<div class="panel-title">Item List</div>
			<div class="panel-tool"></div>
		</div>
		<div align="left" class="bkgColor" style="height: auto; padding: 0px">
		<form:form name="input" id="formID" action="show_item_list_fg" method="post" modelAttribute="searchCriteria">
		
			<div class="headingdiv"  >
				<table width="628" height="31" border="0" cellpadding="0"
					cellspacing="0">
					<tr>
						<td width="217"><div align="center">Item Code</div>
						</td>
						<td width="112"><form:input path="itemCode" size="16"
							id="sonumber" />
						</td>
						<td width="130"><div align="center">Item Name</div>
						</td>
						<td width="259"><form:input path="invoiceName"
					id="invoiceName" size="16" />
						</td>
						<td width="97"><input class="searchbtn"
							style="font-size: 11px; font-weight: bold; padding: 0 0 0 20px;"
							type="submit" name="operation" value="Search" />
						</td>
						<td width="100" align="right"><div >
						<input type="hidden" name="itemID" value="" id="itemIdSO">
						<input type="submit" id="btnok" name="btn" value="addItem" class="okbtn"  style="font-size: 11px; font-weight: bold; border:0px;  padding: 0 0 0  ;"/>    					
						</div></td>
						<td width="87">
					<a href="backto_finishGood" class="cancelbtn" ></a>
						</td>
						 
					</tr>
				</table>
			</div>
			
	</form:form>
			<div style="width: 906px; margin: 0 auto;">
				<span style="margin-left: 80px;" class="errmsg"></span>
			</div>
			<div class="gridheadingdiv" >
	 <table width="972" class="fixmyheader-8" id="example">
  <thead>
   <tr>
						<td style="border: none;"><div align="left">								<strong>Item code</strong>							</div>
						</td>
						<td  style="border: none;"  class="in"><div align="center">								<strong>Item name</strong>							</div>
						</td>
						<td  style="border: none;" class="basic"><div align="center">								<strong>UMO</strong>							</div>
						</td>
						
						<td  style="border: none;" class="pack"><div align="center">								<strong>Pack Type</strong>							</div>
						</td>
						<td  style="border: none;" class="stock"><div align="center">								<strong>Stock </strong>							</div>
						</td>
						<td  style="border: none;" class="basic"><div align="center">								<strong>Basic Rate</strong>							</div>
						</td>
						<td  style="border: none;" class="mrp"><div align="center">								<strong>M. R. P.</strong>							</div>
						</td>
						<td  style="border: medium none;" class="launchdate"><div align="center">								<strong>Launch Dt.</strong>							</div>
						</td>
						<td  style="border: none;" class="wieght"><div align="center">								<strong>Weight</strong>							</div>
						</td>
						<!-- <td width="78"><div align="center">
								<strong>Action</strong>
							</div>
						</td> -->

					</tr>
  </thead>
  <tbody>		<c:forEach items="${itemList}" var="item">
						<tr class="sr" style="cursor: pointer;" id="${item.itemId}">
					
							<td width="15">&nbsp;<c:out value="${item.itemCode}" />
							</td>
							<td width="150">&nbsp;<c:out value="${item.itemName}" />
							</td>
							<td width="30">&nbsp<c:out value="${item.masterUnit.name}" />
							</td>
							<td width="25">&nbsp;<c:out value="${item.masterPackDTO.name}" />
							</td>
							<td width="30" style="text-align: right;" align="right" >&nbsp;<fmt:formatNumber value="${item.stockTotal}" type="number"  minFractionDigits="2" maxFractionDigits="2"/>&nbsp;</td>
							<td width="30" style="text-align: right;" align="right">&nbsp;<fmt:formatNumber value="${item.salesRate}" type="number"  minFractionDigits="2" maxFractionDigits="2"/>&nbsp;</td>
							<td width="30" style="text-align: right;" align="right">&nbsp;<fmt:formatNumber value="${item.mrp}" type="number"  minFractionDigits="2" maxFractionDigits="2"/>&nbsp;</td>
							<td width="30" >&nbsp;<fmt:formatDate pattern="dd-MMM-yyyy" value="${item.launchDate}" />			
							</td>
							<td width="32" style="text-align: right;" align="right">&nbsp;<fmt:formatNumber value="${item.netWeight}" type="number"  minFractionDigits="2" maxFractionDigits="2"/></td>

						</tr>

					</c:forEach>
  
  </tbody>
</table>
			</div>
			
			<div class="dataTables_paginate"><c:url value="show_item_list_fg" var="remove_url">
			<c:param name="next" value="${finishedGoodsMasterForm.finishedGoodsMasterDTO.previous}"></c:param>
			</c:url> <a href="${remove_url}" class="paginate_disabled_previous" >Previous</a>
		   <c:url value="show_item_list_fg" var="remove_url">
			<c:param name="next"  value="${finishedGoodsMasterForm.finishedGoodsMasterDTO.next}"></c:param>
			</c:url> <a href="${remove_url}" class="paginate_disabled_next" >Next</a>
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