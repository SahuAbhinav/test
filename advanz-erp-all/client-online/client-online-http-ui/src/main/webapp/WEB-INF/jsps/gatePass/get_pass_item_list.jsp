<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false" %>
 	<script type="text/javascript" charset="utf-8">
                                    
 	$(document).ready(function() {
 		/* Initialise datatables */
 		var oTable = $('#example').dataTable({

 		"aLengthMenu": [[10,10, 25, 50, -1], ['Select',10, 25, 50, "All"]],
 		"iDisplayLength":10000,
 		 "sPaginationType": "full_numbers",
 		"bPaginate": false,
 		bInfo:""
 		});
  } );

            </script>
<script type="text/javascript">
			
			$(document).ready(function() {  	
				
				$('.fixmyheader-8').fixheadertable({
					caption		: 'My header is fixed !',
					height		:296,
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
	  .ic{width:42px !important; border:none !important}
  .in{width:290px !important; border:none !important}
  .pt{width:78px !important; border:none !important}
  .stock{width:78px !important; border:none !important}
  .br{width:78px !important; border:none !important}
  .mrp{width:78px !important; border:none !important}
  .ld{width:78px !important; border:none !important}
  
  .weight{width:72px !important; border:none !important} 
th{font-size:10px;}
 td{font-size:12px;}  
 
 	.ui-widget-content {
overflow-x: hidden !important;
 
}
	div.t_fixed_header_main_wrapper.ui div.t_fixed_header_caption {
		display:none;
		
		}
 	
.dataTables_info {
 width: 775px;
}
</style>
<script type="text/javascript">
    function destroyItem(id) {
			
				var r=confirm("Are you sure you want to remove this Item?");
				
							if (r) {
								
								$.get('remove_item', {
									itemId : id
								});
								
							}
							$('#tt').datagrid('reload');
	                     } 
    
	</script> 
	
	<script>
	$(document).ready(function() {
		$(function() {
			$('.sr').click(function() {
				$('.sr').css("background-color","white");
				$(this).css("background-color","yellow");
				
				$('#submitItemId').val($(this).attr('id'));
				
				$.get('get_pass_item_detail_by_id', { id: $(this).attr('id')}, function(data) {					 					
					 					$("#length").val(data.itemLength);
					 					$("#width").val(data.itemWidth);
					 					$("#minStock").val(data.minStock);
					 					$("#maxStock").val(data.maxStock);
					 					$("#reorderLevel").val(data.reorderLevel);
					 					$("#suggqty").val(data.suggReorderQty);
					 					$("#itemIdSO").val(data.itemId);
					 					$("#density").val(data.itemDensity);
					 					$('#itemId').val(data.itemId);
					 					$('#thikness').val(data.itemHeight);
					 					
					});
				// $(this).toggleClass('active');
				//alert($(this).attr('id'));
			});
			
			
			
		});
	});
</script>
	<%//show_invoice %>
	<form:form name="input" action="submitItemListForGetPass" class="formdiv" method="GET" modelAttribute="itemForm">
    <div class="header"> Item List For Gate Pass</div> 
	<div class="headingdiv">
	<table width="880" height="31" border="0" cellpadding="0" cellspacing="0">
    <tr>
    <td width="54"><div align="center">Item Code</div>
<form:hidden path="itemDTO.itemId" id="submitItemId"/>
    </td>
          <td width="35">
          <form:input type="text" path="itemDTO.itemCode" size="8" id="itemCode" />
          </td>
          <td width="77"><div align="center">Item Name</div></td>
          <td width="247"><form:input type="text"  path="itemDTO.invoiceName" size="24" id="invoiceName"/></td>
          <td width="67"><input class="searchbtn" type="submit" name="operation"  value="" onclick="this.value='Search';"/></td>
          <td width="42"><input class="okbtn" type="submit" name="operation" value="OK" /></td>
          <td width="38"><input name="operation" type="submit" class="cancelbtn"  style="font-size: 11px; font-weight: bold; border:0px;  padding: 0 0 0 10px;" value=" " onclick="this.value='Cancel';"/></td>
        </tr>
      </table>
	</div>
	<div class="gridheadingdiv" >
 <table width="972" class="fixmyheader-8" id="example">
  <thead>
   <tr>

          <td class="ic" width="80"><div align="center">Item Code</div></td>
          <td class="in" width="140"><div align="center">Item Name</div></td>
         <td  style="border: none;" class="basic"><div align="center">								<strong>UMO</strong>							</div>
			</td>
          <td class="pt" width="78"><div align="center">Pack Type</div></td>
          <td class="stock" width="78"><div align="center">Stock</div></td>
          <td class="br" width="78"><div align="center">Basic Rate</div></td>
          <td class="mrp" width="78"><div align="center">M.R.P.</div></td>
          <td class="ld" width="78"><div align="center">Launch Date</div></td>
          <td class="weight" width="78"><div align="center">Weight</div></td>
    </tr>
  </thead>
  <tbody> 
          <c:forEach items="${itemForm.itemList}" var="item">
		  <tr class="sr" style="cursor: pointer;" id="${item.itemId}">
          <td width="31">&nbsp;<c:out value="${item.itemCode}"/></td>
          <td width="290">&nbsp;<c:out value="${item.itemName}"/></td>
          <td width="60" >&nbsp;<c:out value="${item.masterUnit.name}" />
		  </td>
          <td width="68" style="text-align: right;" align="right">&nbsp;<c:out value="${item.masterName}"/></td>
         <td width="68">
          <fmt:formatNumber value="${item.stockTotal}" type="number"  minFractionDigits="2" maxFractionDigits="2"/> &nbsp;</td>
          <td width="68" style="text-align: right;" align="right">&nbsp;
           <fmt:formatNumber value="${item.salesRate}" type="number"  minFractionDigits="2" maxFractionDigits="2"/>
          </td>
          <td width="68" style="text-align: right;" align="right">&nbsp;
           <fmt:formatNumber value="${item.mrp}" type="number"  minFractionDigits="2" maxFractionDigits="2"/>
          </td>
          <td width="68">&nbsp;<fmt:formatDate pattern="dd-MMM-yyyy"  value="${item.launchDate}"/></td>
          <td width="83" style="" align="right">&nbsp;<c:out value="${item.itemWidth}"/></td>
  </tr>
  </c:forEach>
  
  </tbody>
</table>
  </div>
  <div><c:url value="showItemList" var="remove_url">
			<c:param name="next" value="${itemForm.itemDTO.previous}"></c:param>
			</c:url> <a href="${remove_url}" class="previousbtn" ></a>
		</div>
         <div><c:url value="showItemListForGetPass" var="remove_url">
			<c:param name="next"  value="${itemForm.itemDTO.next}"></c:param>
			</c:url> <a href="${remove_url}" class="nextbtn" ></a>
		</div>
<div class="bkgColor" align="left" style="padding-top: 12px;">
<table height="78" width="792" border="0" style="margin-top: 12px;">
<form:input type="hidden"  path="itemDTO.itemId"  data-maxsize="11" readonly="true" disabled="disabled" size="11" id="itemId" />
				<tr>
					<td width="139" height="36">Length<span style="color: #FF0000"></span>
					</td>
					     <td width="108"><input type="text" name="length" data-maxsize="11" readonly="readonly" disabled="disabled" size="11" id="length" />
					</td>
					<td width="113">Width</td>
					<td width="130"><input type="text" name="width"
						data-maxsize="11" readonly="readonly" disabled="disabled"
						size="11" id="width" />
					</td>
					<td width="117">Thickness</td>
					<td width="92"><input type="text" name="thikness" disabled="disabled"
					data-maxsize="11" readonly="readonly" size="11" id="thikness" />
					</td>
					<td width="119">Density</td>
					<td width="115"><input type="text" name="density" disabled="disabled"
						data-maxsize="11" readonly="readonly" 
						size="11" id="density" />
					</td>
				</tr>
				<tr>
					<td height="36">Min Stock</td>
					<td><input type="text" name="minStock" data-maxsize="11" disabled="disabled"
						readonly="readonly"  size="11" id="minStock" />
					</td>
					<td>Max Stock</td>
					<td><input type="text" name="maxStock" data-maxsize="11" disabled="disabled"
						readonly="readonly"  size="11" id="maxStock" />
					</td>
					<td>Re-order Level</td>
					<td><input type="text" name="reorderLevel" data-maxsize="11" disabled="disabled"
						readonly="readonly"  size="11"
						id="reorderLevel" />
					</td>
					<td>Sugg. Qty</td>
					<td><input type="text" name="suggqty" data-maxsize="11"
						readonly="readonly" disabled="disabled" size="11" id="suggqty" />
					</td>
				</tr>
			</table>
		</div>
		</div>
		 
		</form:form>
		</body>
		</html>
  