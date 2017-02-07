<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false"%>
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
<c:if test="${error.errorMsg!=null}">
	<input type="hidden" id="errorId" value="${error.errorMsg}">
	<input type="hidden" id="invoiceNumberToPrint" value="${invoiceNumberToPrint}">
	<script type="text/javascript">
	var url = "proforma_report/pdf?"+ "invoiceNumber="+$('#invoiceNumberToPrint').val();
			$(document).ready(function() {
			var errorId=document.getElementById('errorId');
			alert(errorId.value);
			if($('#invoiceNumberToPrint').val()!='null' && $('#invoiceNumberToPrint').val()!=''){
				window.open(url);	
			}
			
    	});
 	</script>
</c:if>
<c:if test="${error.errorMsg=='Invoice has been removed successfully'}">
	<script type="text/javascript">
		$(document).ready(function() {
		 window.self.location = 'show_proforma_list?operation=show';
		});
 	</script>
</c:if>

<c:if test="${error.errorCode=='OOS'}">

<script type="text/javascript">
		var delUrl='show_proforma_list?operation=show';
		$(document).ready(function() {
			console.log("In the Exception");
			//alert(errorId);
    	 //window.self.location = delUrl;
		});
 	</script>
</c:if>

<c:if test="${error123.errorMsg!=null}">
	<input type="hidden" id="errorId" value="${error123.errorMsg}">
	<script type="text/javascript">
		$(document).ready(function() {
		var errorId=document.getElementById('errorId');
		alert(errorId.value);
	//	 window.self.location = 'show_proforma_list?operation=show';
		});
 	</script>
</c:if>

<script type="text/javascript">
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

  <script type="text/javascript">
      $(document).ready(function()
       {
           	   
	  $( ".fromDate" ).datepicker({
		  changeMonth: true,
          changeYear: true,
		  yearRange: '-99:+10',
		  dateFormat: 'dd-M-yy' });
	  $( ".toDate" ).datepicker({
		  changeMonth: true,
          changeYear: true,
		  yearRange: '-99:+10',
		  dateFormat: 'dd-M-yy' });
  //     
      });
  </script>

<script type="text/javascript">
function alertFunct(){
	alert("Sorry you can not convert this proforma to invoice");
	}
function editeProforma(){
		alert("Sorry you can not update this proforma as it is already converted to invoice.");
	}
function deleteProforma(){
		alert("Sorry you can not delete this proforma as it is already converted to invoice.");
		}
</script>
<script type="text/javascript">
			$(document).ready(function() { 
				$('.fixmyheader-8').fixheadertable({
					caption		: 'My header is fixed !',
					height		:415,
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
.View{width:35px !important; border:none!important}
.sn{width:30px !important; border:none !important}
.billn{width:59px !important; border:none  !important}
.date{width:64px !important; border:none  !important}
.partyn{width:115px !important; border:none  !important}
.sonum{width:60px !important; border:none  !important}
.exciseBill{width:62px !important; border:none  !important}

.en{width:81px !important; border:none  !important}
.ti{width:48px !important; border:none  !important}
.tp{width:61px !important; border:none  !important}
.na{width:66px !important; border:none  !important}
.ac{width:48px !important; border:none  !important}		


.dataTables_info {
    width: 775px;
}		
</style>




<form:form action="submitProformaList" method="GET" class="formdiv"
	commandName="TTList" modelAttribute="invoice" id="invoiceList">

	<div class="header">Proforma List</div>
	<div class="headingdiv">
		<table width="880" height="31" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				<td width="45"><input type="submit" class="addbtn"
					name="operation" value="" onclick="this.value='Add';" /></td>
				<td width="45"><a href="#" class="exportbtn" iconCls="icon-redo" onClick="javascript:$('#dlg').dialog('close')"></a>				</td>
				<td width="65"><div align="center">Bill Number</div>
				</td>
				<td width="57"><form:input type="text"
						path="proformaMasterDTO.invoiceNumber" size="11" id="sonumber" />
				</td>

				<td width="65" ><div align="center">Party Name</div>
				</td>
				
				<td width="82"><form:input path="proformaMasterDTO.partyName"
						size="11" id="salesOrderNumber" />
				</td>
				
				<td width="82"><div align="center">From Date</div></td>
          <td width="64"> <form:input path="proformaMasterDTO.fromDate" class="fromDate"   size="11" /></td>
        
         <td width="82"><div align="center">To Date</div></td>
          <td width="64"> <form:input path="proformaMasterDTO.toDate"  class="toDate" size="11" /></td>
				<td width="65"><div align="center">Item Name</div></td>
				<td width="160"><form:input path="proformaMasterDTO.itemName" size="11" id="itemName" /></td>
				<td width="45"><input type="submit" class="searchbtn"
					name="operation" value="" onclick="this.value='Search';" />
				</td>
				<td width="45"><div class="cancelbtn">
						<c:url value="show_proforma_list?operation=show" var="remove_url">
							<c:param name="operation" value=""></c:param>
						</c:url>
						<a href="${remove_url}" class="cancelbtn"
							onclick="this.value='Cancel';"></a>


					</div>
				</td>
			</tr>
		</table>
	</div>

	<div class="gridheadingdiv">
		  <table width="972" class="display fixmyheader-8" id="example">
			<thead>
				<tr>
					 <td class="View" width="20"><div align="center">View</div></td>
					 <td class="sn" width="20"><div align="center">S. No.</div></td>
					<td class="billn" width="59"><div align="center">Bill Number</div>				</td>
					<td class="date" width="64"><div align="center">Date</div>					</td>
					<td class="partyn" width="115"><div align="center">Party Name</div>					</td>
					<td class="sonum" width="70"><div align="center">SO Number</div>
										</td>
				<td class="exciseBill" width="70"><div align="center"> Excise Bill</div></td>
					<td class="en"  width="81"><div align="center">Employee Name</div>					</td>
					<td class="ti" width="48">						<div align="center">Total Item</div></td>
					<td class="tp" width="61"><div align="center">Total Packet</div>					</td>
					<td class="na" width="66"><div align="center">Net Amount</div>					</td>
					<td class="ac" width="48"><div align="center">Action</div>					</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${invoiceList}" var="pr" varStatus="s">
					<tr> 
						 <td style="text-align: center;" width="24">
<c:if test="${pr.exciseInvoiceNo==null}">
<c:url value="show_proforma" var="view_url">
<c:param name="invoiceAutoId" value="${pr.invoiceAutoId}"></c:param>
<c:param name="operation" value="V"></c:param>
</c:url>
<a href="${view_url}"><img src="static/images/view_icon.png" title="View Record" alt="" /></a>
</c:if>

<c:if test="${pr.exciseInvoiceNo!=null}">
<c:url value="show_proforma" var="view_url">
<c:param name="invoiceAutoId" value="${pr.invoiceAutoId}"></c:param>
<c:param name="operation" value="V1"></c:param>
</c:url>
<a href="${view_url}"><img src="static/images/view_icon.png" title="View Record" alt="" /></a>
</c:if></td>



					<td width="20">&nbsp;<c:out value="${s.count}" /></td>
						<!-- <td width="78">&nbsp;<a href="submitInvoiceList?invoiceNumber=<c:out value="${pr.invoiceNumber}"/>&operation=GetInvoice"><c:out value="${pr.invoiceNumber}" /></a></td> -->
						<td width="57"><c:out value="${pr.invoiceNumber}" />
						</td>
						<td width="54">&nbsp;<fmt:formatDate pattern="dd-MMM-yyyy"
								value="${pr.invoiceDate}" />
						</td>
						<td width="105"><c:out value="${pr.partyDTO.partyName}" />
						</td>
						<td width="53"><c:out value="${pr.salesOrderNumber}" />
						</td>
						<td width="62"><c:out value="${pr.exciseInvoiceNo}" />
						</td>
						<td width="71"><c:out value="${pr.employeeDTO.employeeName}" />
						</td>
						<td width="38" style="text-align: right"><c:out
								value="${pr.totalItem}" />
						</td>
						<td width="58" style="text-align: right"><fmt:formatNumber
								value="${pr.packetTotal}" type="number" minFractionDigits="2"
								maxFractionDigits="2" /></td>
						<td width="56" style="text-align: right"><fmt:formatNumber
								value="${pr.billNetAmount}" type="number" minFractionDigits="2"
								maxFractionDigits="2" />
						</td>
						<td width="48" style="">
						<c:if test="${pr.exciseInvoiceNo==null}">
						<a
							href="conevrt_proforma_into_invoice?invoiceAutoId=<c:out value="${pr.invoiceAutoId}"/>&operation=Get_Invoice"><img
								src="static/images/convert-icon.png" title="Convert to Invoice" alt="" />
						</a>
						</c:if>
						
						<c:if test="${pr.exciseInvoiceNo!=null}">
						<div style="margin-left: 15px;">
						<img
								src="" width="50" title="Convert to Invoice" alt="" onclick="alertFunct()" />
						<!-- <img
								src="static/images/convert-icon.png" title="Convert to Invoice" alt="" onclick="alertFunct()" /> -->
						</c:if>
						
						<c:if test="${pr.exciseInvoiceNo==null}">
						
						<a
							href="show_proforma?invoiceAutoId=<c:out value="${pr.invoiceAutoId}"/>&operation=Get_Invoice"><img
								src="static/images/change_btn.png" title="Edit Record" alt="" />
						</a>
						</c:if>
						<c:if test="${pr.exciseInvoiceNo!=null}">
						
						<img src="static/images/change_btn.png" title="Edit Record" alt="" onclick="editeProforma()"/>
						
						</c:if>
						
						<c:if test="${pr.exciseInvoiceNo==null}">
						<a	href="show_proforma?invoiceAutoId=<c:out value="${pr.invoiceAutoId}"/>&invoiceNumber=<c:out value="${pr.invoiceNumber}" />&operation=Delete"><img
							src="static/images/drop.png" title="Delete Record" alt="" />
						</a>
						</c:if>
						<c:if test="${pr.exciseInvoiceNo!=null}">
						
							<img src="static/images/drop.png" title="Delete Record" alt="" onclick="deleteProforma()" />
						</c:if>
						
						</td>

						<%--  
						<td width="60"><a href="show_invoice?invoiceAutoId=<c:out value="${pr.invoiceAutoId}"/>&operation=Get_Invoice"><img
								src="static/images/change_btn.png"
								style="float: left; margin-left: 10px;" title="Edit Record"
								alt="" /></a><a href="submitInvoiceList?invoiceAutoId=<c:out value="${pr.invoiceAutoId}"/>&invoiceNumber=<c:out value="${pr.invoiceNumber}" />&operation=Delete"><img
								src="static/images/drop.png"
								style="float: right; margin-right: 30px;" title="Delete Record" onclick="return remoneConformation();"
								alt="" /></a></td>
								 --%>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	 <div class="dataTables_paginate"><c:url value="show_proforma_list" var="get_url">
			<c:param name="next" value="${invoiceForm.proformaMasterDTO.previous}"></c:param>
			<c:param name="operation" value="show" ></c:param>
			</c:url> <a href="${get_url}" class="paginate_disabled_previous"  > Previous</a>
		 
       <c:url value="show_proforma_list" var="get_url">
			<c:param name="next"  value="${invoiceForm.proformaMasterDTO.next}"></c:param>
			<c:param name="operation" value="show"></c:param>
			</c:url> <a href="${get_url}"  class="paginate_disabled_next"  > Next</a>
		</div>
	
</form:form>
</body>
</html>