<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page isELIgnored="false"%>

	<script type="text/javascript" charset="utf-8">
                                    
                $(document).ready(function() {
                    /* Initialise datatables */
                    var oTable = $('#example').dataTable({"bPaginate": false,bInfo:""});
                    
                } );
            </script> 
            
<script type="text/javascript">
			
			$(document).ready(function() {  	
									
				$('.fixmyheader-8').fixheadertable({
					caption		: 'My header is fixed !',
					height		:306,
					addTitles	: true,
					colratio	: ['10%', '10%', '8%', '50px', 'auto', 'auto', '30%', 'auto']
				});
			});
		</script>
<script>
	$(document).ready(function() {
		$(".cancelbtn").click(function() {
			window.self.location = "backto_issue";
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
		$(function() {
			$('.sr').click(function() {
				$('.sr').css("background-color","white");
				$(this).css("background-color","yellow");
				$.get('get_item_by_shift', { id: $(this).attr('id')}, function(data) {					 					
					 					$("#length").val(data.itemLength);
					 					$("#width").val(data.itemWidth);
					 					$("#minStock").val(data.minStock);
					 					$("#maxStock").val(data.maxStock);
					 					$("#reorderLevel").val(data.reorderLevel);
					 					$("#suggqty").val(data.suggReorderQty);
					 					$("#itemIdSO").val(data.itemId);
					 					$("#density").val(data.itemDensity);
					 			
					 					
					 					
					});
				// $(this).toggleClass('active');
				//alert($(this).attr('id'));
			});
		});
	});
</script>
 <style type="text/css">
 div.t_fixed_header_main_wrapper.ui div.t_fixed_header_caption {
		display:none;
		
		}
 	.ui-widget-content {
overflow-x: hidden !important;
 
}
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
   
  .Ic{width:78px !important; border:none !important;}
 .in{width:89px !important; border:none !important;}
 .pt{width:88px !important; border:none!important;}
 .stock{width:88px !important; border:none !important;}
 .br{width:88px !important; border:none !important;}
 .mrp{width:88px !important; border:none !important;}
 .ld{width:89px !important; border:none !important;}
 .wight{width:66px !important; border:none !important;}    
</style>
	
		<div class="panel-header">
			<div class="panel-title">Item List</div>
			<div class="panel-tool"></div>
		</div>
		<div align="left" class="bkgColor" style="height: auto;x">
		<form:form name="input" id="formID" action="show_item_list_shift" method="post" modelAttribute="searchCriteria">
		
			<div class="headingdiv"  >
				<table width="628" height="31" border="0" cellpadding="0"
					cellspacing="0">
					<tr>
						<td width="217"><div align="center">Item Code</div>
						</td>
						<td width="112"><form:input path="itemCode" size="16"
							id="sonumber" />
						</td>
						<td width="130"><div align="center">Invoice Name</div>
						</td>
						<td width="259"><form:input path="invoiceName"
					id="invoiceName" size="16" />
						</td>
						<td width="75"><input class="searchbtn"
							 
							type="submit" value=" " />
						</td>
						</form:form>
						<td>
						<form:form action="addItemInShift" method="POST">
						<input type="hidden" name="itemID" value="" id="itemIdSO">
						<input type="submit" class="okbtn"  style="font-size: 11px; font-weight: bold; border:0px;  padding: 0 0 0  ;" value=" "/>    					
						
						</form:form>
						</td>
						
					<td width="87">
					<a href="show_shift_form2" class="cancelbtn" >
					</td>
					<td width="87">&nbsp; 
					</td>
					</tr>
				</table>
			</div>
		 
			<div class="gridheadingdiv"  >
				 <table width="972" class="fixmyheader-8" id="example">
				 	 <thead>
  
   <tr> 
					 
						<td class="Ic" width="68"><div align="center">			<strong>Item code</strong>						</div>						</td>
						<td class="in" width="78"><div align="center">				<strong>Invoice name</strong>							</div>						</td>
						<td class="pt" width="78"><div align="center">				<strong>Pack Type</strong>							</div>						</td>
						<td class="stock" width="78"><div align="center">				<strong>Stock </strong>							</div>						</td>
						<td class="br" width="78"><div align="center">			<strong>Basic Rate</strong>							</div>						</td>
						<td class="mrp" width="78"><div align="center">					<strong>M. R. P.</strong>							</div>						</td>
						<td class="ld" width="78"><div align="center">				<strong>Launch Dt.</strong>							</div>						</td>
						<td class="wight" width="78"><div align="center">			<strong>Weight</strong>							</div>						</td>
					 
					</tr>
  </thead>
  <tbody>	<c:forEach items="${itemList}" var="item">

						<tr class="sr" style="cursor: pointer;" id="${item.itemId}">
					
							<td width="68">&nbsp;<c:out value="${item.itemCode}" />
							</td>
							<td width="79">&nbsp;<c:out value="${item.invoiceName}" />
							</td>
							<td width="78">&nbsp;<c:out value="${item.masterPackDTO.name}" />
							</td>
							<td width="78">&nbsp;</td>
							<td style="text-align:right;" width="78">&nbsp;<c:out value="${item.salesRate}" /></td>
							<td  style="text-align:right;"  width="78">&nbsp;<c:out value="${item.mrp}" /></td>
							<td width="79">&nbsp;<c:out value="${item.launchDate}" /></td>
							<td  style="text-align: center;" width="78">&nbsp;<c:out value="${item.netWeight}" /></td>
                               
						</tr>
				
					</c:forEach>
  
  </tbody>
</table>
      </div>
       <div class="dataTables_paginate"><c:url value="show_item_list_shift" var="get_url">
			<c:param name="next" value="${shiftReportMasterForm.previous-(13)}"></c:param>
			<%-- <c:param name="operation" value="show" ></c:param> --%>
			</c:url> <a href="${get_url}" class="paginate_disabled_previous"> Previous</a>
		 
       <c:url value="show_item_list_shift" var="get_url">
			<c:param name="next"  value="${shiftReportMasterForm.next+(13)}"></c:param>
			<%-- <c:param name="operation" value="show"></c:param> --%>
			</c:url><a href="${get_url}"  class="paginate_disabled_next"> Next</a>
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