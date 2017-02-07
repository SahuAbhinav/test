<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false"%>
<script type="text/javascript" charset="utf-8">
$(document).ready(function(){ 
 $(".datepicker[readonly]").css("background-color","#ffffff" );
} );
</script>
<script type="text/javascript" charset="utf-8">
    
    $(document).ready(function() {
        /* Initialise datatables */
        var oTable = $('#example').dataTable({
        	 
   		       "aLengthMenu": [['',10, 25, 50, -1], ['',10, 25, 50, "All"]],
               "iDisplayLength":10000,
               "bPaginate": false,
               bInfo:""
   		   // "bStateSave": true
		       });
        
        
    } );          
               
            </script>
<c:if test="${succ=='Blk'}">
  <script type="text/javascript">
  	var delUrl='get_purchaseOrder_list';
  	$(document).ready(function() {
      alert('No Record Found !!!!');
      //window.self.location  = delUrl;
	});
 	</script>
</c:if>

 <c:if test="${purchaseOrderMasterForm1.succ=='Ad'}">
  <script type="text/javascript">
  var delUrl='get_purchaseOrder_list';
	$(document).ready(function() {
     alert('Record Inserted Successfully !!!!');
     window.self.location = delUrl;
	});
 	</script>
</c:if>

<%-- <c:if test="${purchaseOrderMasterForm1.succ=='Dl'}">
  <script type="text/javascript">
  var delUrl='get_purchaseOrder_list';
  $(document).ready(function() {
     alert('Record Deleted Successfully !!!!');
     window.self.location = delUrl;
	});
 	</script>
</c:if> --%>

<c:if test="${purchaseOrderMasterForm1.succ=='Up'}">
  <script type="text/javascript">
  var delUrl='get_purchaseOrder_list';
	$(document).ready(function() {
     alert('Record Updated Successfully !!!!');
     window.self.location = delUrl;
	});
 	</script>
</c:if>

 <c:if test="${errors!=null}">
 
 <input type="hidden" id="errorId" value="${errors.errorMsg}">
	<script type="text/javascript">
		var delUrl='get_purchaseOrder_list';
		$(document).ready(function() {
			var errorId=document.getElementById('errorId');
			alert(errorId.value);
    	 window.self.location = delUrl;
		});
 	</script>
</c:if>
 <c:if test="${errorDl!=null}">
 
 <input type="hidden" id="errorId" value="${errorDl.errorMsg}">
	<script type="text/javascript">
		var delUrl='get_purchaseOrder_list';
		$(document).ready(function() {
			var errorId=document.getElementById('errorId');
			alert(errorId.value);
    	 window.self.location = delUrl;
		});
 	</script>
</c:if>


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
 .View{width:34px !important; border:none!important}
 .sn{width:46px !important; border: none !important; }
 .pn{width:79px !important; border: none !important;}
 .date{width:79px !important; border: none !important; }
 .supp{width:156px !important; border: none !important; }
 .suppre{width:118px !important; border: none !important; }
 .indent{width:88px !important; border: none !important; }
 .trans{width:143px !important; border: none !important; }
 .ti{width:68px !important; border: none !important; }
 .ddt{width:111px !important; border: none !important; }
 .refre{width:101px !important; border: none !important; }
 .nm{width:74px !important; border: none !important; }
 .ac{width:55px !important; border: none !important; }
th{font-size:10px;}
 td{font-size:12px;}  
 
 	
	div.t_fixed_header_main_wrapper.ui div.t_fixed_header_caption {
		display:none;
		
		}
	
</style>
<script type="text/javascript">
function printPurchaseOrder(ele){
	 var url = "purchase_order_print_report/pdf?PurchaseOrderNoPrompt="+ele;
	window.open(url);
	
}
</script>
 <script type="text/javascript">
      $(document).ready(function()
       {
           	   
	  $( ".datepicker" ).datepicker({
		  changeMonth: true,
          changeYear: true,
		  yearRange: '-99:+10',
		  dateFormat: 'dd-M-yy' });
  //     
      });
     </script>
 
     
<form:form name="input" action="get_purchaseOrder_list" class="formdiv" method="post" modelAttribute="purchaseOrderMasterForm1">
		     
    <div class="header"> Purchase Order List</div> 
	<div class="headingdiv">
	  <table width="880" height="31" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td width="81"><a onclick="return checkAdd();" href="purchaseOrder_add?opr=add" class="addbtn"></a>
				</td>
				<td width="66"><input class="exportbtn" 	type="button" value="" /></td>
			
				   <td width="178"><div align="center"> Purchase Order  No</div> </td>
          <td width="67">
      <form:input type="text" path="purchaseOrderMasterDTO.purchaseOrderNumber" size="10" id="ponumber" /></td>
          <%-- <td width="71"><div align="center">Date</div></td>
          <td width="82"><form:input type="text" path="purchaseOrderMasterDTO.purchaseOrderDate" class="datepicker" id="date"  readonly="true" size="11" /></td>
           --%>
           <td width="71"><div align="center">From Date</div></td>
          <td width="82"><form:input type="text" path="purchaseOrderMasterDTO.fromDate" class="fromDate"  size="11" /></td>
          <td width="71"><div align="center">To Date</div></td>
          <td width="82"><form:input type="text" path="purchaseOrderMasterDTO.toDate" class="toDate"  size="11" /></td>
          <td width="144"><div align="center">Supplier Name</div></td>
          <td width="111"><form:input type="text" path="purchaseOrderMasterDTO.partyDTO.partyName" size="10" id="partyName" /></td>
          
         
          <td width="119"><div align="center">Item Name</div></td>
          <td width="111"><form:input type="text" path="purchaseOrderMasterDTO.itemName" size="10" id="partyName" /></td>
          
          
          
          <td width="87"><input class="searchbtn" style="font-size: 11px; font-weight: bold;  padding: 0 0 0 20px;" type="submit" name="opr" value="search"/></td>
          <td width="87"><div class="cancelbtn">
				<a href="get_purchaseOrder_list"
						class="cancelbtn" iconCls="icon-cancel"></a>
			</div></td>
        </tr>
      </table>
	</div>
	<div class="gridheadingdiv">
	  <table width="972" class="display fixmyheader-8" id="example">
  <thead>

  
   <tr>   <td class="View"><div align="center">View</div></td>  
          <td class="sn" width="46"><div align="center">S No.</div></td>
          <td class="pn" width="79"><div align="center">PO Number</div></td>
          <td class="date" width="79"><div align="center">Date</div></td>
          <td class="supp" width="156"><div align="center">Supplier Name</div></td>
          <td class="suppre" width="118"><div align="center">Supplier Reference</div></td>
          <td class="indent"   width="88"><div align="center">Indent No</div></td>
          <td class="trans" width="143"><div align="center">Transporter</div></td>
          <td class="ti" width="68"><div align="center">Total Items</div></td>
          <td class="ddt" width="111"><div align="center">Desire Deliver Dt.</div></td>
          <td class="refre" width="101"><div align="center">Our Reference</div></td>
          <td class="nm" width="74"><div align="center">Net Amount</div></td>
          <td class="ac" width="70"><div align="center">Action</div></td>
        </tr>
  </thead>
  <tbody>   
       <c:forEach items="${pomList}" var="pom" varStatus="s">
          <tr>
   		  <td style="text-align: center;" width="24">
			<c:url value="get_purchaseOrder" var="view_url">
			<c:param name="id" value="${pom.poAutoId}"></c:param>
			<c:param name="opr" value="V"></c:param>
		  </c:url>
          <a href="${view_url}"><img src="static/images/view_icon.png" title="View Record" alt="" /></a></td>		
          <td width="36">&nbsp;<c:out value="${s.count}" /></td>
          <td width="72">&nbsp;<c:out value="${pom.purchaseOrderNumber}" /></td>
          <td width="69">&nbsp;<fmt:formatDate pattern="dd-MMM-yyyy"
            value="${pom.purchaseOrderDate}" /></td>
          <td width="146">&nbsp;<c:out value="${pom.partyDTO.partyName}" /></td>
          <td width="108">&nbsp;<c:out value="${pom.supplierReference}" /></td>
          <td width="78">&nbsp;<c:out value="${pom.indentNumber}" /></td>
          <td width="133">&nbsp;<c:out value="${pom.transportDTO.transName}" /></td>
         
          <td width="58" style="text-align:right"  align="right">&nbsp;<c:out value="${pom.purchaseOrderDetailDTOList.size()}" /></td>
          
          <td width="101">&nbsp;<fmt:formatDate pattern="dd-MMM-yyyy"
            value="${pom.desireDeliveryDate}" /></td>
          <td width="91">&nbsp;<c:out value="${pom.ourReference}" /></td>
          <td width="64" style="text-align:right"  align="right">&nbsp; <fmt:formatNumber value="${pom.poNetAmount}" type="number"  minFractionDigits="2" maxFractionDigits="2"/></td>
            <td width="52" style="text-align:center" >
          <c:url value="get_purchaseOrder" var="remove_url">
					<c:param name="id" value="${pom.poAutoId}"></c:param>
					<c:param name="opr" value="R"></c:param>
					</c:url>
					<c:url value="get_purchaseOrder" var="edit_url">
					<c:param name="id" value="${pom.poAutoId}"></c:param>
					<c:param name="opr" value="E"></c:param>
					</c:url>
						<img	onclick="printPurchaseOrder('<c:out value="${pom.purchaseOrderNumber}" />');" src="static/images/print_icon.png" title="Print Purchase Order" alt="" />
		<c:forEach items="${roleAndRightsDTOList}" var="roleAndRights">
		   <c:if test="${roleAndRights.menuId==sessionScope.menuId}">
		    <input type="hidden"  id="addFlag" value="${roleAndRights.addFlag}" >
		  	 <c:if test="${roleAndRights.editFlag=='true'}">
		      <a id="editUrlId" href="${edit_url}"><img	 src="static/images/change_btn.png" title="Edit Record" alt="" /></a>
		      </c:if>
		      <c:if test="${roleAndRights.editFlag=='false'}">
		      <img	onclick="checkEdit();" src="static/images/change_btn.png" title="Edit Record" alt="" />
		      </c:if>
		      <c:if test="${roleAndRights.deleteFlag=='true'}">
		      <a href="${remove_url}"><img src="static/images/drop.png" title="Delete Record"	alt="" /></a>
         	  </c:if>
         	  <c:if test="${roleAndRights.deleteFlag=='false'}">
		      <img onclick="checkDelete();" src="static/images/drop.png" title="Delete Record"	alt="" />
         	  </c:if>
          </c:if>
          </c:forEach>
       </td></tr>
             </c:forEach>
  
  </tbody>
</table>
  </div>
  
  
  <div style="float: right;"><c:url value="get_purchaseOrder_list" var="remove_url">
<c:param name="next" value="${purchaseOrderMasterForm1.next+(15)}"></c:param>
</c:url> <a href="${remove_url}" class="nextbtn" ></a>
</div>
<div style="float: right;">
<c:url value="get_purchaseOrder_list" var="remove_url">
<c:param name="next" value="${purchaseOrderMasterForm1.previous-(15)}"></c:param>
</c:url> <a href="${remove_url}" class="previousbtn" ></a>
</div>
 </form:form>

