<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false" %>
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
		
	<script>
	
	function getQuat(ele){
		$('#poQty').val($('#itemQty'+ele).val());
	}
	
	$(document).ready(function() {
		$('.sr').click(function() {
			$('.sr').css("background-color","white");
			$(this).css("background-color","yellow");
			 // $('#itemId').val($(this).attr('id'));
			
			  $('#itemId').val($(this).attr('id'));
			  //$('#poQty').val($('#itemQty').val());
		});
		
		$(".cancelbtn").click(function() {
			window.self.location = "backto_grn?opr=${opr}";
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
			.se{width:33px !important; border:none !important}	
  .ic{width:120px !important; border:none !important}
  .in{width:60px !important; border:none !important}
  .pt{width:78px !important; border:none !important}
  .stock{width:78px !important; border:none !important}
  .br{width:70px !important; border:none !important}
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
 	.gridheadingdiv input {
    border: medium none;
    width: 33px;
}
</style>



<form:form  action="addPoInGrn1" commandName="grnMasterForm">
    <div class="header">PO Item List</div> 
	
	<div class="headingdiv"  >
				<table width="628" height="31" border="0" cellpadding="0"
					cellspacing="0">
					<tr style="float: right;">
<td width="45" >
	<!-- <input type="hidden" name="poID" value="" id="poIdGrn"> --> 
	<input type="hidden" name="poID" value="" id="itemId"> 
	<input type="hidden" name="opr" value="A" id="opr"> 
	<input type="hidden" name="poQty" value="" id="poQty">
	<input type="hidden" name="suplyerId" value="${suplyerId}">
	  <input type="hidden" name="poNumber" value="${poNumber}" >
		<input type="submit" class="okbtn" style="font-size: 11px; font-weight: bold; border: 0px; padding: 0 0 0;" value=" " />
</td> 
 <td><input name="reset2" type="reset"		class="cancelbtn" value=" " /></td>
</tr>
</table>
</div>
	
	
	
	<div class="gridheadingdiv" >
 <table width="972" class="fixmyheader-8" id="example">
  <thead>
   <tr>
  <td class="se" width="50"><div align="center">Select</div></td>
          <td class="ic" width="120"><div align="center">Item Name</div></td>
          <td class="in" width="60"><div align="center">UOM</div></td>
          <td class="pt" width="78"><div align="center">PO Qty</div></td>
          <td class="stock" width="78"><div align="center">Item Value</div></td>
          <td class="br" width="78"><div align="center">Amount</div></td>
          
          <td class="stock" width="78"><div align="center">Tolerance(%) Under Delivery</div></td>
          <td class="br" width="78"><div align="center">TolerancePer(%) Over Delivery</div></td>
    </tr>
  </thead>
  <tbody> 
          <c:forEach items="${detailList}" var="item" varStatus="s">
		<tr class="sr" onclick="getQuat(${s.count});" style="cursor: pointer;" id="${item.itemDTO.itemId}">
        <td width="22"><input type="checkbox"  name="itemIds" value="${item.itemDTO.itemId}" /></td>
          <td width="120">&nbsp;<c:out value="${item.itemDTO.itemName}" /></td>
          <td width="60">&nbsp;<c:out value="${item.measurementUnitName}"/></td>
          <td width="45" style="text-align: right;" align="right">&nbsp;<c:out value="${item.itemQuantity}"  />
          <input type="hidden" name="itemQty" value="${item.itemQuantity}" id="itemQty${s.count}"/>
          </td>
          <td width="55" style="text-align: right;" align="right">&nbsp;
         <fmt:formatNumber value="${item.itemValue}" type="number"  minFractionDigits="2" maxFractionDigits="2"/></td>
          <td width="68" style="text-align: right;" align="right">&nbsp;
           <fmt:formatNumber value="${item.netAmount}" type="number"  minFractionDigits="2" maxFractionDigits="2"/>
          </td>
          
           <td width="68" style="text-align: right;" align="right">&nbsp;
         <fmt:formatNumber value="${item.itemUnderDeliveryTolerancePer}" type="number"  minFractionDigits="2" maxFractionDigits="2"/></td>
          <td width="68" style="text-align: right;" align="right">&nbsp;
           <fmt:formatNumber value="${item.overDeliveryTolerancePer}" type="number"  minFractionDigits="2" maxFractionDigits="2"/>
        
          </td>
  </tr>
  </c:forEach>
  
  </tbody>
</table>
  </div>

		</form:form>
		</body>
		</html>
  