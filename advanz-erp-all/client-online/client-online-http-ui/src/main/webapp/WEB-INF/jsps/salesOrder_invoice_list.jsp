<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false"%>
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
<c:if test="${not empty(errors)}">
 <input type="hidden" id="errorId" value="${errors.errorMsg}">
 <script type="text/javascript">
 
 		var delUrl='new_salesOrder';
 		$(document).ready(function() {
 		var errorId=document.getElementById('errorId');
		alert(errorId.value);
 		//alert('Duplicate Data In Branch');
 		window.self.location  = delUrl;
		});
 	</script>
 </c:if>
<script type="text/javascript">
			
			$(document).ready(function() {  	
				
				 
				
				$('.fixmyheader-8').fixheadertable({
					caption		: 'My header is fixed !',
					height		:420,
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
 	
</style>

<script>
	$(function() {

		$("#datepicker").datepicker({
			dateFormat : 'dd-M-yy',
			autoSize : true,
			changeMonth : true,
			changeYear : true
			   
		});
	});
</script>
	<script>
	$(document).ready(function() {
		$(function() {
			$('.sr').click(function() {
				$('.sr').css("background-color","white");
				$(this).css("background-color","yellow");
				
				$('#salesOrderNumber').val($(this).attr('id'));
				
			    });
			
			
			});
			});
function disableOkButton(){
		$('.okbtn').attr('disabled','disabled');
	}
function disableSalerOrderField(){
	$('.SalesOrderNoId').attr('disabled','disabled');
}
</script>
<style type="text/css">
 
 
 
  .sn{width:36px !important; border:none !important}
  .s0n{width:75px !important; border:none !important}
  .date{width:73px !important; border:none !important}
  .pn{width:78px !important; border:none !important}
  .ord{width:78px !important; border:none !important}
  .ddt{width:105px !important; border:none !important}
  .pd{width:72px !important; border:none !important}
  .ti{width:68px !important; border:none !important}
  .tq{width:67px !important; border:none !important}
  .iv{width:71px !important; border:none !important}
  .na{width:93px !important; border:none !important}
 
 
	.ui-widget-content {
overflow-x: hidden !important;
 
}

 
.sr{
cursor: pointer;
}
</style>
</head>

<form:form name="input" action="submitSalesOrderList" class="formdiv"
	method="GET" commandName="salesOrderForm">
	<div class="header">Sales Order Detail</div>
	<div class="headingdiv">
	<table width="100%" height="31" border="0" cellpadding="0" cellspacing="0" >
	<tr>
			
				<td width="141"><div align="center">
						Select <span class="header1">Order</span> 
					</div>
					</td>
				<td width="100"><form:input path="salesOrderNumber" size="16" id="salesOrderNumber" /></td>
		
			<td width="93"><div align="center">Party Name</div></td>
          <td width="92"> <form:input path="partyName" size="10" id="partyName" /></td>
		 <!--<td width="73"><div align="center">Date</div></td>
         <td width="68"><form:input path="salesOrderDTO.salesOrderDate"  class="datepicker" id="datepicker"  size="11" /></td>
         <td width="124"><div align="center">Party Name</div></td>
         <td width="202"><form:input path="salesOrderDTO.partyId" size="10" id="partyId" /></td> -->
          
		<td width="32">
		<input type="submit" class="searchbtn" name="operation" value="" onclick="this.value='Search',disableSalerOrderField();" /></td>
					<td width="33">
          <input name="operation" type="submit" class="okbtn"    style="font-size: 11px; font-weight: bold; border:0px;  padding: 0 0 0  ;" value="SalesOrderNo" onclick="disableOkButton();" />   
            <input type="hidden" name="operation" value="SalesOrderNo" class="SalesOrderNoId"/>
           
           </td>
					<td width="31">
			<div class="cancelbtn">
				<input type="submit" value="" name="operation" class="cancelbtn"
					onclick="this.value='Cancel',disableSalerOrderField();" />
			</div>
			</td>
				
			</tr>
		</table>
		</div>
		<div class="gridheadingdiv" >
	  <table width="972" class="display fixmyheader-8" id="example">
  <thead>
   <tr>

 
   
          <td class="sn" width="36"><div align="center">S No.</div></td>
          <td class="s0n" width="75"><div align="center">SO Number</div></td>
          <td class="date" width="73"><div align="center">Date</div></td>
          <td class="pn" width="78"><div align="center">Party Name</div></td>
          <td class="ord" width="78"><div align="center">O. Rec. Date</div></td>
          <td class="ddt" width="105"><div align="center">Desire Deliver Dt.</div></td>
          <td class="pd" width="72"><div align="center">Planned Dt.</div></td>
          <td class="ti" width="68"><div align="center">Total Items</div></td>
          <td class="tq" width="67"><div align="center">Total Qty.</div></td>
          <td class="iv" width="71"><div align="center">Item Value</div></td>
          <td class="na" width="71"><div align="center">Net Amount</div></td>
        </tr>
  </thead>
  <tbody> 
       <c:forEach items="${salesOrderList}" var="som" varStatus="s">
       <c:set var="now" value="<%=new java.util.Date()%>" />
	 	<c:if test="${som.soValidUptoDate>=now}"> 
       <tr class="sr" style="cursor: pointer;" id="${som.salesOrderNumber}">
   
          <td width="26">&nbsp;<c:out value="${s.count}" /></td>
          <td width="65">&nbsp;<c:out value="${som.salesOrderNumber}"/></td>
          <td width="63">&nbsp;<fmt:formatDate pattern="dd-MMM-yyyy"
            value="${som.salesOrderDate}" />
          </td>
          <td width="68">&nbsp;<c:out value="${som.partyDTO.partyName}" /></td>
          <td width="68">&nbsp;<fmt:formatDate pattern="dd-MMM-yyyy"
            value="${som.orderReceiptDate}" /></td>
          <td width="95">&nbsp;<fmt:formatDate pattern="dd-MMM-yyyy"
            value="${som.desireDeliveryDate}" /></td>
          <td width="62">&nbsp;<fmt:formatDate pattern="dd-MMM-yyyy"
            value="${som.plannedDeliveryDate}" /></td>
          <td width="58" style="text-align:right;" align="right">&nbsp;${som.salesOrderDetailDTOList.size()}</td>
          <td width="57" style="text-align:right;" align="right">&nbsp;</td>
          <td width="61" style="text-align:right;" align="right">&nbsp;${som.itemValue}</td>
          <td width="62" style="text-align:right; padding-right:36PX;" align="right">&nbsp;${som.soNetAmount}</td>
         
        </tr>
        </c:if>
        </c:forEach>
  
  </tbody>
</table>
  </div>
</form:form>
</body>
</html>