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
 		bInfo:""
 		});
  } );
</script>
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
	   .bn{width:75px !important; border:none !important}
   .date{width:58px !important; border:none !important}
   .pn{width:116px !important; border:none !important}
   .sonum{width:75px !important; border:none !important}
   .en{width:75px !important; border:none !important}
   .ti{width:74px !important; border:none !important}
   .tp{width:74px !important; border:none !important}
      .net{width:67px !important; border:none !important}
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
</script>


<script>
	$(document).ready(function() {
		$(function() {
			$('.sr').click(function() {
				$('.sr').css("background-color","white");
				$(this).css("background-color","yellow");
				
				$('#invoiceNumber').val($(this).attr('id'));
				
			    });
			
			
			});
			})

				</script>
				 


<form:form action="submitDispatchInvoiceList" method="GET" commandName="TTList"
	modelAttribute="invoice">
	
	  <form:hidden path="invoiceNumber" id="invoiceNumber"/>
	<div class="header">Select Invoice List</div>
	<div class="headingdiv">
		<table width="880" height="31" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				<td width="133"><div align="center">Bill Number</div></td>
				<td width="57"><form:input type="text"
						path="billDTO.invoiceNumber" size="10" id="sonumber" /></td>
				
				<td width="102" nowrap="nowrap"><div align="center">SO
						Number</div></td>
				<td width="160"><form:input path="billDTO.salesOrderNumber"
						size="10" id="salesOrderNumber" /></td>
				<td width="73"><input type="submit" class="searchbtn"
					name="operation" value=""  onclick="this.value='Search';"/></td>
					 <td width="42"><input class="okbtn" type="submit" name="operation" value="GetInvoice"/></td>
				<td width="58"><input type="submit" value="" name="operation" class="cancelbtn" onclick="this.value='Cancel';" /></td>
		</tr>
		</table>
		</div>
		<div class="gridheadingdiv"  >
	 <table width="972" class="fixmyheader-8" id="example">
  <thead>
   <tr>

				<!-- <th>#</th> -->
				<td class="bn" width="75"><div align="center">Bill Number</div></td>
				<td class="date" width="58"><div align="center">Date</div></td>
				<td class="pn" width="116"><div align="center">Party Name</div></td>
				<td class="sonum" width="75"><div align="center">SO Number</div></td>
				<td class="en"  width="75"><div align="center">Employee Name</div></td>
				<td class="ti" width="74">					<div align="center">Total Item</div>				</td>
				<td class="tp" width="74"><div align="center">Total Packet</div></td>
				<td class="net" width="66"><div align="center">Net Amount</div></td>
				<!-- <td width="60"><div align="center">Action</div></td> -->
			 </tr>
  </thead>
  <tbody>  			<c:forEach items="${invoiceList}" var="pr">
					<tr class="sr" style="cursor: pointer" id="${pr.invoiceNumber}">
						<!-- <td><input type="checkbox" name="chkBox"> </td> -->
						<td width="65">&nbsp;<c:out value="${pr.invoiceNumber}" /></td>
						<td width="48">&nbsp;<fmt:formatDate pattern="dd-MMM-yyyy" value="${pr.invoiceDate}" /></td>
						<td width="106"><c:out value="${pr.partyDTO.partyName}" /></td>
						<td width="65"><c:out value="${pr.salesOrderNumber}" /></td>
						<td width="65"><c:out value="${pr.employeeDTO.employeeName}" /></td>
						<td width="64" style="text-align: right;" align="right"><c:out value="${pr.totalItem}" /></td>
						<td width="64" style="text-align: right;" align="right">
						 <fmt:formatNumber value="${pr.packetTotal}" type="number"  minFractionDigits="2" maxFractionDigits="2"/>
						</td>
						<td width="37" style="text-align: right;  padding-right: 34px;" align="right">
						 <fmt:formatNumber value="${pr.billNetAmount}" type="number"  minFractionDigits="2" maxFractionDigits="2"/>
						</td>
						
						<!--<td width="60">
						 <a href="show_invoice?invoiceAutoId=<c:out value="${pr.invoiceAutoId}"/>&operation=Get_Invoice"><img
								src="static/images/change_btn.png"
								style="float: left; margin-left: 10px;" title="Edit Record"
								alt="" /></a><a
							href="submitDispatchInvoiceList?invoiceAutoId=<c:out value="${pr.invoiceAutoId}"/>&operation=Delete"><img
								src="static/images/drop.png"
								style="float: right; margin-right: 30px;" title="Delete Record"
								alt="" /></a></td> -->
					</tr>
				</c:forEach>
  
  </tbody>
</table>
  </div>
  
</form:form>
</body>
</html>