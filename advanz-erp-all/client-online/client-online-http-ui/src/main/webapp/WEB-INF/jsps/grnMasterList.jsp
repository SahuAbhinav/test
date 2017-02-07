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




 <c:if test="${succ=='Blk'}">
  <script type="text/javascript">
   	var delUrl='get_grn_list';
  	$(document).ready(function() {
      alert('No Record Found !!!!');
     //window.self.location = delUrl;
	});
 	</script>
</c:if>
<script type="text/javascript">
function printGRN(ele){
	 var url = "grn_print_report/pdf?GRNNoPrompt="+ele;
	window.open(url);
	
}
</script>

<input type="hidden" name="PrintView" value="${grnMasterForm1.printView}" id="PrintView" >
 <c:if test="${grnMasterForm1.succ=='Ad'}">

  <script type="text/javascript">
  var url = "grn_print_report/pdf?"+ "GRNNoPrompt="+$('#PrintView').val();
  var delUrl='get_grn_list';
	$(document).ready(function() {
     alert('Record Inserted Successfully !!!!');
     window.self.location = delUrl;
     if($('#PrintView').val()!=''){
			window.open(url);	
		}
	});
 	</script>
</c:if>

<c:if test="${grnMasterForm1.succ=='Dl'}">
  <script type="text/javascript">
  var delUrl='get_grn_list';
  $(document).ready(function() {
     alert('Record Deleted Successfully !!!!');
     window.self.location = delUrl;
	});
 	</script>
</c:if>

<c:if test="${grnMasterForm1.succ=='Up'}">

  <script type="text/javascript">
  var url = "grn_print_report/pdf?"+ "GRNNoPrompt="+$('#PrintView').val();
  var delUrl='get_grn_list';
	$(document).ready(function() {
     alert('Record Updated Successfully !!!!');
     window.self.location = delUrl;
     if($('#PrintView').val()!=''){
			window.open(url);	
		}
	});
 	</script>
</c:if>



 <c:if test="${not empty(errors)}">
  <input type="hidden" id="errorId" value="${errors.errorMsg}">
	<script type="text/javascript">
	  var delUrl='get_grn_list';
	   $(document).ready(function() {
		 var errorId=document.getElementById('errorId');
		 alert(errorId.value);
		 //	alert('Branch can not be Removed this Field is using in other Form');
    	 document.location = delUrl;
		});
 	</script>
</c:if>




<script type="text/javascript">
function checkApproved(){
	alert("You can not edit / delete this record as it is already approved.");
}
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
			
			$(document).ready(function() {  	
				
				 
				$('.fixmyheader-8').fixheadertable({
					caption		: 'My header is fixed !',
					height		:419,
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
 .View{width:65px !important; border:none!important}
 .Sn {width:42px !important; border: none !important; }
 .Gn {width:90px !important; border: none !important; }
 .Da {width:80px !important; border: none  !important; }
 .Supp {width:158px !important; border: none  !important; }
 .Sbn {width:92px !important; border: none  !important; }
 .Po {width:85px !important; border: none  !important; }
 .Tra {width:142px !important; border: none  !important; }
 .Ti {width:75px !important; border: none  !important; }
 .Trq {width:90px !important; border: none  !important; }
 .Twt {width:72px !important; border: none  !important; }
 .Net {width:65px !important; border: none  !important; } 
 .Ac {width:91px !important; border: none  !important; } 
  

  
</style>

 


<script type="text/javascript">
	$(document).ready(function() {

		$(".datepicker").datepicker({
			changeMonth : true,
			changeYear : true,
			yearRange : '-99:+10',
			dateFormat : 'dd-M-yy'
		});
		$(".fromDate").datepicker({
			changeMonth : true,
			changeYear : true,
			yearRange : '-99:+10',
			dateFormat : 'dd-M-yy'
		});
		$(".toDate").datepicker({
			changeMonth : true,
			changeYear : true,
			yearRange : '-99:+10',
			dateFormat : 'dd-M-yy'
		});
		//     
	});
</script>



<form:form name="input" action="get_grn_list" class="formdiv"
	  method="post" modelAttribute="grnMasterForm1">

	<div class="header">GRN List</div>
	<div class="headingdiv">
		<table width="880" height="31" border="0" cellpadding="0"
			cellspacing="0">
			<tr>

				<td width="71"><a onclick="return checkAdd();" href="grn_add?opr=Add" class="addbtn"></a></td>
				<td width="98"><div align="center">GRN No</div></td>
				<td width="57"><form:input type="text"
						path="grnMasterDTO.grnNumber" size="10" id="sonumber" /></td>
				<%-- <td width="76"><div align="center">Date</div></td>
				<td width="66"><form:input type="text"
						path="grnMasterDTO.grnDate" class="datepicker" id="date" size="11" /></td> --%>
						<td width="76"><div align="center">From Date</div></td>
				<td width="66"><form:input type="text"
						path="grnMasterDTO.fromDate" class="fromDate" size="11" /></td>
						<td width="76"><div align="center">To Date</div></td>
				<td width="66"><form:input type="text"
						path="grnMasterDTO.toDate" class="toDate" size="11" /></td>
				<td width="102"><div align="center">Supplier Name</div></td>
				<td width="71"><form:input type="text"
						path="grnMasterDTO.partyDTO.partyName" size="10" id="partyName" /></td>
				<td width="102"><div align="center">Supplier Bill No</div></td>
				<td width="71"><form:input type="text"
						path="grnMasterDTO.supplierBillNo" size="10" id="supplierBillNo" /></td>
				<td width="102"><div align="center">Item Name</div></td>
				<td width="102"><form:input path="grnMasterDTO.itemName" size="10" id="itemName" /></td>
				<td width="75"><input class="searchbtn"
					style="font-size: 11px; font-weight: bold; padding: 0 0 0 20px;"
					type="submit" value=" " /></td>
				<td width="74"><div class="cancelbtn">
						<a href="get_grn_list" class="cancelbtn" iconCls="icon-cancel"></a>
					</div></td>
			</tr>
		</table>
	</div>
	<div class="gridheadingdiv">
  <table width="972" class="display fixmyheader-8" id="example">
  <thead>

   <tr>	      <td class="View" width="55"><div align="center">View</div></td>
   				<td class="Sn" width="42"><div align="center">S No.</div></td>
				<td class="Gn" width="90"><div align="center">GRN Number</div></td>
				<td class="Da" width="80"><div align="center">Date</div></td>
				<td class="Supp" width="158"><div align="center">Supplier Name</div></td>
				<td class="Sbn" width="92"><div align="center">Supplier Bill No</div></td>
				<td class="Po" width="85"><div align="center">PO No</div></td>
				<td class="Tra" width="142"><div align="center">Transporter</div></td>
				<td class="Ti" width="63"><div align="center">Total Items</div></td>
				<td class="Trq" width="90"><div align="center">Total Rec. Qty.</div></td>
				<td class="Twt" width="72"><div align="center">Tare Wt.</div></td>
				<td class="Net" width="65"><div align="center">Net Wt.</div></td>
				<td class="Net" width="65"><div align="center">NetAmount</div></td>
				<td class="Ac" width="63"><div align="center">Action</div></td>
			</tr>
  </thead>
  <tbody>		 
				<c:forEach items="${grnList}" var="pom" varStatus="s">
					<tr>
					<td style="text-align: center;" width="55">
					<c:url value="get_grn" var="view_url">
					<c:param name="id" value="${pom.grnAutoId}"></c:param>
					<c:param name="aproved" value="${pom.aproved}"></c:param>
					<c:param name="opr" value="V"></c:param>
		  			</c:url>
         			 <a href="${view_url}"><img src="static/images/view_icon.png" title="View Record" alt="" /></a></td>
						<td width="32">&nbsp;<c:out value="${s.count}" /></td>
						<td width="80">&nbsp;<c:out value="${pom.grnNumber}" /></td>
						<td width="70">&nbsp;<fmt:formatDate pattern="dd-MMM-yyyy"
								value="${pom.grnDate}" /></td>
						<td width="148">&nbsp;<c:out
								value="${pom.partyDTO.partyName}" /></td>
						<td width="82">&nbsp;<c:out value="${pom.supplierBillNo}" /></td>
						<td width="75">&nbsp;<c:out
								value="${pom.purchaseOrderDTO.purchaseOrderNumber}" /></td>
						<td width="132">&nbsp;<c:out
								value="${pom.transportDTO.transName}" /></td>
						<td width="53" align="right">&nbsp;<c:out
								value="${pom.count}" /></td>
						<td width="80" style="text-align:right"  align="right">&nbsp; <fmt:formatNumber value="${pom.tolRecQty}" type="number"  minFractionDigits="2" maxFractionDigits="2"/></td>
						<td width="62" style="text-align:right" align="right">&nbsp; <fmt:formatNumber value="${pom.tolTareWt}" type="number"  minFractionDigits="2" maxFractionDigits="2"/></td>
						<td width="55" style="text-align:right" align="right">&nbsp; <fmt:formatNumber value="${pom.tolNetWt}" type="number"  minFractionDigits="2" maxFractionDigits="2"/></td>
						<td width="55" style="text-align:right" align="right">&nbsp; <fmt:formatNumber value="${pom.grnNetAmount}" type="number"  minFractionDigits="0" maxFractionDigits="0"/></td>
						<td width="53" style="text-align:center" >
						
						<c:url value="get_grn" var="remove_url">
								<c:param name="id" value="${pom.grnAutoId}"></c:param>
								<c:param name="opr" value="R"></c:param>
							</c:url>
						
							 <c:url value="get_grn" var="edit_url">
								<c:param name="id" value="${pom.grnAutoId}"></c:param>
								<c:param name="opr" value="E"></c:param>
							</c:url> 
		<img	onclick="printGRN('<c:out value="${pom.grnNumber}" />');" src="static/images/print_icon.png" title="Print GRN" alt="" />
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
		      <c:choose> <c:when test="${pom.aproved=='1'}">
		  	   <img	onclick="checkApproved();" src="static/images/drop.png" title="Edit Record" alt="" />
		  	 </c:when><c:otherwise>
		      <a href="${remove_url}"><img src="static/images/drop.png" title="Delete Record"	alt="" /></a>
         	 </c:otherwise></c:choose> </c:if>
         	  <c:if test="${roleAndRights.deleteFlag=='false'}">
         	   <c:choose> <c:when test="${pom.aproved=='1'}">
		  	   <img	onclick="checkApproved();" src="static/images/drop.png" title="Edit Record" alt="" />
		  	 </c:when><c:otherwise>
		      <img onclick="checkDelete();" src="static/images/drop.png" title="Delete Record"	alt="" />
         	 </c:otherwise></c:choose> </c:if>
          </c:if>
          </c:forEach>
          	
          </td>
					</tr>
				</c:forEach>
  
  </tbody>
</table>
  </div>
  <div style="float: right;"><c:url value="get_grn_list" var="remove_url">
<c:param name="next" value="${grnMasterForm1.next+(15)}"></c:param>
</c:url> <a href="${remove_url}" class="nextbtn" ></a>
</div>
<div style="float: right;">
<c:url value="get_grn_list" var="remove_url">
<c:param name="next" value="${grnMasterForm1.previous-(15)}"></c:param>
</c:url> <a href="${remove_url}" class="previousbtn" ></a>
</div>
  
 </form:form>

