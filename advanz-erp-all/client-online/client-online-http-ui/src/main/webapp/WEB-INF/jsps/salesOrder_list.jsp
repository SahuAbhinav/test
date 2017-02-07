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
               bInfo:"",
            	   "bPaginate": false

		       });
        
        
    } );          
               
            </script>
  <c:if test="${succ=='Blk'}">
  <script type="text/javascript">
  	var delUrl='get_salesOrder_list';
  	$(document).ready(function() {
      alert('No Record Found !!!!');
     // window.self.location  = delUrl;
	});
 	</script>
</c:if>

 <c:if test="${salesOrderMasterForms.succ=='Ad'}">
  <script type="text/javascript">
  var delUrl='get_salesOrder_list';
	$(document).ready(function() {
     alert('Record Inserted Successfully !!!!');
     window.self.location = delUrl;
	});
 	</script>
</c:if>

<c:if test="${salesOrderMasterForms.succ=='Dl'}">
  <script type="text/javascript">
  var delUrl='get_salesOrder_list';
  $(document).ready(function() {
     alert('Record Deleted Successfully !!!!');
     window.self.location = delUrl;
	});
 	</script>
</c:if>

<c:if test="${salesOrderMasterForms.succ=='Up'}">
  <script type="text/javascript">
  var delUrl='get_salesOrder_list';
	$(document).ready(function() {
     alert('Record Updated Successfully !!!!');
     window.self.location = delUrl;
	});
 	</script>
</c:if>

 <c:if test="${not empty(errors)}">
 <input type="hidden" id="errorId" value="${errors.errorMsg}">
	<script type="text/javascript">
		var delUrl='get_salesOrder_list';
		$(document).ready(function() {
			var errorId=document.getElementById('errorId');
			alert(errorId.value);
    	 window.self.location = delUrl;
		});
 	</script>
</c:if>

<script type="text/javascript">
function printSalesOrder(ele){
	 var url = "sales_order_print_report/pdf?SONoPrompt="+ele;
	window.open(url);
	
}
</script>


<script type="text/javascript">
 		function checkEdit()
 		{
 		alert('Login user not permit to edit record !!!!!!');
 		}
		
	  function checkDelete()
 		{
 		alert('Login user not permit to delete record !!!!!!');
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
		.View{width:34px !important; border:none!important}
		  .Sn{width:39px !important; border: none !important; }
  .Snum{width:89px !important;border: none !important; }
  .Da{width:80px !important;border: none !important; }
  .Pn{width:221px !important; border: none !important; }
  .Or{width:90px !important; border: none !important;}
  .Dd{width:117px !important; border: none !important;}
  .Pd{width:84px !important; border: none !important; }
  .Ti{width:75px !important; border: none !important; }
  .Tq{width:65px !important; border: none !important; }
  .Iv{width:80px !important; border: none !important; }
  .Net{width:75px !important; border: none !important; }
  .Ac{width:73px !important; border: none !important; }
	
</style>



  <script type="text/javascript">
      $(document).ready(function()
       {
           	   
	  $( ".datepicker" ).datepicker({
		  changeMonth: true,
          changeYear: true,
		  yearRange: '-99:+10',
		  dateFormat: 'dd-M-yy' });
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
 
 
 
     


  
<form:form name="input" action="get_salesOrder_list"   class="formdiv" modelAttribute="soSearchCriteria" >
		     
    <div class="header" > Sales Order List</div> 
	<div class="headingdiv"  >
	  <table width="880"  height="31" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td width="82"><a onclick="return checkAdd();" href="new_salesOrder" class="addbtn"></a></td>
          <td width="104"><input class="exportbtn" style="font-size: 11px; font-weight: bold;  padding: 0 0 0 38px;" type="button" value=""/> </td>
          <td width="114"><div align="center">SO Number</div></td>
          <td width="64">
      <form:input path="soNumber" size="10" id="sonumber" /></td>
          <%-- <td width="82"><div align="center">Date</div></td>
          <td width="64"> <form:input path="soDate" class="datepicker" id="date"  size="11" /></td> --%>
          <td width="82"><div align="center">From Date</div></td>
          <td width="64"> <form:input path="fromDate" class="fromDate"  size="11" /></td>
<td width="82"><div align="center">To Date</div></td>
          <td width="64"> <form:input path="toDate" class="toDate"  size="11" /></td>          
          <td width="93"><div align="center">Party Name</div></td>
          <td width="92"> <form:input path="partyName" size="10" id="partyName" /></td>
          <td width="92"><div align="center">Item Name</div></td>
		 <td width="92"><form:input path="itemName" size="10" id="itemName" /></td>
    <td width="80"><input class="searchbtn" style="font-size: 11px; font-weight: bold;  padding: 0 0 0 20px;" type="submit" name="operation" value="Search"/></td>
    <td width="105">
    <a href="get_salesOrder_list" class="cancelbtn" iconCls="icon-cancel" > </a>
    </td>
    </tr>
    </table>
	</div>
	<div class="gridheadingdiv" >
	 <table width="972" class="display fixmyheader-8" id="example">
  <thead>
   <tr>  <td class="View"><div align="center">View</div></td>   
      <td Class="Sn" width="39"><div align="center">S No.</div></td>
          <td Class="Snum" width="89"><div align="center">SO Number</div></td>
          <td Class="Da" width="80"><div align="center">Date</div></td>
          <td Class="Pn" width="211"><div align="center">Party Name</div></td>
          <td Class="Or" width="100"><div align="center">O. Rec. Date</div></td>
          <td Class="Dd" width="117"><div align="center">Desire Deliver Dt.</div></td>
          <td Class="Pd" width="84"><div align="center">Planned Dt.</div></td>
          <td Class="Ti" width="68"><div align="center">Total Items</div></td>
          <td Class="Tq" width="65"><div align="center">Total Qty.</div></td>
          <td Class="Iv" width="80"><div align="center">Item Value</div></td>
          <td Class="Net" width="75"><div align="center">Net Amount</div></td>
          <td Class="Ac" width="73"><div align="center">Action</div></td>
       </tr>
  </thead>
  <tbody>        <c:forEach items="${somList}" var="som" varStatus="s">
           
        <tr>
   			<td style="text-align: center;" width="24">
			<c:url value="get_salesOrder" var="view_url">
			<c:param name="id" value="${som.salesOrderAutoId}"></c:param>
			<c:param name="opr" value="V"></c:param>
		  </c:url>
          <a href="${view_url}"><img src="static/images/view_icon.png" title="View Record" alt="" /></a></td>
          
          <td width="29">&nbsp;<c:out value="${s.count}" /></td>
          <td width="79">&nbsp;<c:out value="${som.salesOrderNumber}" /></td>
          <td width="70">&nbsp;<fmt:formatDate pattern="dd-MMM-yyyy"
            value="${som.salesOrderDate}" />
          </td>
          <td width="210	">&nbsp;<c:out value="${som.partyDTO.partyName}" /></td>
          <td width="80">&nbsp;<fmt:formatDate pattern="dd-MMM-yyyy"
            value="${som.orderReceiptDate}" /></td>
          <td width="107">&nbsp;<fmt:formatDate pattern="dd-MMM-yyyy"
            value="${som.desireDeliveryDate}" /></td>
          <td width="74">&nbsp;<fmt:formatDate pattern="dd-MMM-yyyy"
            value="${som.plannedDeliveryDate}" /></td>
          <td width="58" style="text-align: right;" align="right">&nbsp;${som.salesOrderDetailDTOList.size()}&nbsp;</td>
          <td width="55" style="text-align: right;" align="right">&nbsp;<fmt:formatNumber value="${som.totalQuantity}" type="number"  minFractionDigits="2" maxFractionDigits="2"/>&nbsp;</td>
          <td width="70" style="text-align: right;" align="right">&nbsp;<fmt:formatNumber value="${som.itemValue}" type="number"  minFractionDigits="2" maxFractionDigits="2"/>&nbsp;</td>
          <td width="65" style="text-align: right;"  align="right">&nbsp;<fmt:formatNumber value="${som.soNetAmount}" type="number"  minFractionDigits="2" maxFractionDigits="2"/>&nbsp;</td>
          <td width="55"   >
          <c:url value="get_salesOrder" var="remove_url">
					<c:param name="id" value="${som.salesOrderAutoId}"></c:param>
					<c:param name="opr" value="R"></c:param>
					</c:url>
					<c:url value="get_salesOrder" var="edit_url">
					<c:param name="id" value="${som.salesOrderAutoId}"></c:param>
					<c:param name="opr" value="E"></c:param>
					</c:url>
		<img	onclick="printSalesOrder('<c:out value="${som.salesOrderNumber}" />');" src="static/images/print_icon.png" title="Sales Order" alt="" />
		<c:forEach items="${roleAndRightsDTOList}" var="roleAndRights">
		   <c:if test="${roleAndRights.menuId==sessionScope.menuId}">
		    <input type="hidden"  id="addFlag" value="${roleAndRights.addFlag}" >
		  	 <c:if test="${roleAndRights.editFlag=='true' }">
		      <a id="editUrlId" href="${edit_url}"><img	 src="static/images/change_btn.png" title="Edit Record" alt="" /></a>
		      </c:if>
		      <c:if test="${roleAndRights.editFlag=='false'}">
		      <img	onclick="checkEdit();" src="static/images/change_btn.png" title="Edit Record" alt="" />
		      </c:if>
		      
		      <c:if test="${roleAndRights.deleteFlag=='true'}">
		        
		      <a href="${remove_url}"><img src="static/images/drop.png" title="Delete Record"	alt="" /></a>
         	
         	  
         	  </c:if>
         	  <c:if test="${roleAndRights.deleteFlag=='false' }">
		      <img onclick="checkDelete();" src="static/images/drop.png" title="Delete Record"	alt="" />
         	  </c:if>
          </c:if>
          </c:forEach>
         </td>
        </tr>
        </c:forEach>
  
  </tbody>
</table>
  </div>
  
 

    <div style="float: right;"><c:url value="get_salesOrder_list" var="remove_url">
<c:param name="next" value="${salesOrderMasterForms.next+(15)}"></c:param>
</c:url> <a href="${remove_url}" class="nextbtn" ></a>
</div>
<div style="float: right;">
<c:url value="get_salesOrder_list" var="remove_url">
<c:param name="next" value="${salesOrderMasterForms.previous-(15)}"></c:param>
</c:url> <a href="${remove_url}" class="previousbtn" ></a>
</div>
    

  
</form:form>