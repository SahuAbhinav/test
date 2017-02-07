<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false"%>
<script type="text/javascript" charset="utf-8">
    $(document).ready(function() {
      /* Initialise datatables */
    var oTable = $('#example').dataTable({ 	 
   		       "aLengthMenu": [[-1,10, 25, 50], ['All',10, 25, 50]],
               "iDisplayLength":10000,
               "sPaginationType": "full_numbers",
        		"bPaginate": false
       });
    } );          
  </script>
<c:if test="${error.errorMsg!=null}">
	<input type="hidden" id="errorId" value="${error.errorMsg}">
	<script type="text/javascript">
			$(document).ready(function() {
			var errorId=document.getElementById('errorId');
			alert(errorId.value);
    	});
 	</script>
</c:if>
<c:if test="${error.errorMsg=='Invoice has been removed successfully'}">
	<script type="text/javascript">
		$(document).ready(function() {
		 window.self.location = 'show_invoice_list?operation=show';
		});
 	</script>
</c:if>

<c:if test="${error123.errorMsg!=null}">
	<input type="hidden" id="errorId" value="${error123.errorMsg}">
	<script type="text/javascript">
		$(document).ready(function() {
		var errorId=document.getElementById('errorId');
		alert(errorId.value);
		 //window.self.location = 'show_invoice_list?operation=show';
		});
 	</script>
</c:if>
<script type="text/javascript">
  
   $(document).ready(function() {
	
	 $(".ac").hide(); 
	 $(".action").hide(); 
	 
  }); 
   </script>
<% 
Boolean flag=(Boolean)session.getAttribute("hotKeyStatus");
if(flag==true){
%>
  <script type="text/javascript">
    var metaChar = false;
    var exampleKey = 16;
    function keyEvent(event) {
      var key = event.keyCode || event.which; // alternative to ternary - if there is no keyCode, use which
    	  if(key==120){
    		
    		  $(".ac").show(); 
    		  $(".action").show(); 
    		  
    	  }
    }
   
  </script>

<%} %>
 <script type="text/javascript">
 		function checkEdit()
 		{
 		alert('Login User Not Permit to Edit Record !!!!!!');
 		}
		
	  function checkDelete()
 		{
 		alert('Login User Not Permit to Delete Record !!!!!!');
 		}

 		function checkAdd()
 		{
 		  var adId=document.getElementById('addFlag').value;
 		  if(adId=='true')
 			  {
 				return true;
 			  }
 		  else
 			{
 			 alert('Login User Not Permit to Add Record !!!!!!');
 			 return false;  
 			} 		  
 		}
	</script>


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
			
			$(document).ready(function() {  	
				 
				
				$('.fixmyheader-8').fixheadertable({
					caption		: 'My header is fixed !',
					height		:412,
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
#container {

  min-height: 533px;}
.View{width:34px !important; border:none!important}		
.Sn{width:25px !important; border: none !important;}
.Bn{width:59px !important; border: none !important; }
.date{width:64px !important; border: none !important; }
.Pn{width:115px !important; border: none !important;}
.So{width:77px !important; border: none !important; }
.En{width:81px !important; border: none !important; }
.Ti{width:48px !important; border: none !important; }
.Tp{width:78px !important; border: none !important; }
.Nm{width:78px !important; border: none !important; }


</style>
<body onkeydown="keyEvent(event)" onkeyup="metaKeyUp(event)">
<form:form action="submitInvoiceList" method="GET" class="formdiv"
	commandName="TTList" modelAttribute="invoice" id="invoiceList">
	<div class="header">Invoice List </div>
	<div class="headingdiv">
		<table width="880" height="31" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				<!-- <td width="84"><input type="submit" class="addbtn"
					name="operation" value="" onclick="this.value='Add';" /></td> -->
				<td width="96"><a class="exportbtn" onclick="javascript:$('#dlg').dialog('close')" iconcls="icon-redo" href="#"></a>
				</td>
				<td width="133"><div align="center">Bill Number</div>
				</td>

				<td width="57"><form:input type="text"	path="billDTO.invoiceNumber" size="16" id="sonumber" />

				</td>

				<td width="102" nowrap="nowrap"><div align="center">Party Name</div>
				</td>
				<td width="160"><form:input path="billDTO.partyName"	size="16" id="salesOrderNumber" />
				</td>
							<td width="82"><div align="center">From Date</div></td>
          <td width="64"> <form:input path="billDTO.fromDate" class="fromDate"   size="16" /></td>
        
         <td width="82"><div align="center">To Date</div></td>
          <td width="64"> <form:input path="billDTO.toDate"  class="toDate" size="16" /></td>
				<td width="102"><div align="center">Item Name</div></td>
				<td width="157" ><form:input path="billDTO.itemName" size="16" id="itemName" /></td>
				<td width="87"><input type="submit" class="searchbtn" name="operation" value="" onclick="this.value='Search';" />
				</td>
				<td width="105"><div class="cancelbtn">
						<c:url value="show_invoice_list?operation=show" var="remove_url">
							<c:param name="operation" value=""></c:param>
						</c:url>
						<a href="${remove_url}" class="cancelbtn"	onclick="this.value='Cancel';"></a>


					</div>
				</td>
			</tr>
		</table>
	</div>

	<div class="gridheadingdiv">
  <table width="972" class="display fixmyheader-8" id="example">
			<thead>
					<tr><td class="View"><div align="center">View</div></td>
					<td class="Sn" width="25"><div align="center">S. No.</div></td>
					<td class="Bn" width="59"><div align="center">Bill Number</div>					</td>
					<td class="date" width="64"><div align="center">Date</div>					</td>
					<td class="Pn" width="115"><div align="center">Party Name</div>					</td>
					<td class="So" width="77"><div align="center">Proforma No</div>					</td>
					<td class="So" width="77"><div align="center">SO Number</div>					</td>
					<td class="En" width="81"><div align="center">Employee Name</div>					</td>
					<td class="Ti" width="48"><div align="center">Total Item</div></td>
					<td class="Tp" width="78"><div align="center">Total Packet</div>					</td>
					<td class="Nm" width="66"><div align="center">Net Amount</div>					</td>
					<td class="ac" width="48" id="actionLable"><div align="center">Action</div>					</td>
					
				</tr>
			</thead>
		 <%int i=1; %>
		<tbody>
			<c:forEach items="${invoiceList}" var="pr">
			<tr><td style="text-align: center;" width="24">
			<c:url value="show_invoice" var="view_url">
			<c:param name="invoiceAutoId" value="${pr.invoiceAutoId}"></c:param>
			<c:param name="operation" value="Get_Invoice"></c:param>
		  </c:url>
          <a href="${view_url}"><img src="static/images/view_icon.png" title="View Record" alt="" /></a></td>						        
					<td width="15"><%=i %></td>
						<!-- <td width="78">&nbsp;<a href="submitInvoiceList?invoiceNumber=<c:out value="${pr.invoiceNumber}"/>&operation=GetInvoice"><c:out value="${pr.invoiceNumber}" /></a></td> -->
						<td width="66">&nbsp;<c:out value="${pr.invoiceNumber}" />
						</td>
						<td width="54">&nbsp;<fmt:formatDate pattern="dd-MMM-yyyy"	value="${pr.invoiceDate}" />
						</td>
						<td width="105"><c:out value="${pr.partyDTO.partyName}" />
						</td>
						<td width="67"><c:out value="${pr.proformaNumber}" />
						</td>
						<td width="67"><c:out value="${pr.salesOrderNumber}" />
						</td>
						<td width="71"><c:out value="${pr.employeeDTO.employeeName}" />
						</td>
						<td width="38" style="text-align: right"><c:out	value="${pr.totalItem}" />
						</td>
						<td width="68" style="text-align: right">
						<fmt:formatNumber	value="${pr.packetTotal}" type="number" minFractionDigits="2" maxFractionDigits="2" /></td>
						<td width="63" style="text-align: right; padding-right:35px;">
						<fmt:formatNumber value="${pr.billNetAmount}" type="number" minFractionDigits="2" maxFractionDigits="2" />
						</td>
						
						<td width="48" class="action">
						<c:if test="${hotKeyStatus==true}">
						<a	href="show_invoice?invoiceAutoId=<c:out value="${pr.invoiceAutoId}"/>&invoiceNumber=<c:out value="${pr.invoiceNumber}" />&operation=Delete"><img
							src="static/images/drop.png" title="Delete Record" alt="" />
						</a>
						</c:if>
						</td>
		
			<%i++; %>
		</tr>
		</c:forEach>
		</tbody>
		</table>
</div>
<div  class="dataTables_paginate"><c:url value="show_invoice_list" var="remove_url">
			<c:param name="next" value="${invoiceForm.billDTO.previous-(15)}"></c:param>
			<c:param name="operation" value="show"></c:param>
			</c:url> <a href="${remove_url}" class="paginate_disabled_previous" >Previous</a>
		 
 <c:url value="show_invoice_list" var="remove_url">
			<c:param name="next"  value="${invoiceForm.billDTO.next+(15)}"></c:param>
			<c:param name="operation" value="show"></c:param>
			</c:url> <a href="${remove_url}" class="paginate_disabled_next" > Next</a>
		</div>
 
</form:form></body>

</html>