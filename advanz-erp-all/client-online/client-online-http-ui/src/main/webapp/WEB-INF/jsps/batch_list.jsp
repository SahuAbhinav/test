<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
 <c:if test="${succ=='Blk'}">
  <script type="text/javascript">
   	var delUrl='get_batch_list';
  	$(document).ready(function() {
      alert('No Record Found !!!!');
     // window.self.location = delUrl;
	});
 	</script>
</c:if>

 <c:if test="${batchForm.succ=='Ad'}">

  <script type="text/javascript">
  var delUrl='get_batch_list';
	$(document).ready(function() {
     alert('Record Inserted Successfully !!!!');
     window.self.location = delUrl;
	});
 	</script>
</c:if>

<c:if test="${batchForm.succ=='Dl'}">
  <script type="text/javascript">
  var delUrl='get_batch_list';
  $(document).ready(function() {
     alert('Record Deleted Successfully !!!!');
     window.self.location = delUrl;
	});
 	</script>
</c:if>

<c:if test="${batchForm.succ=='Up'}">

  <script type="text/javascript">
  var delUrl='get_batch_list';
	$(document).ready(function() {
     alert('Record Updated Successfully !!!!');
     window.self.location = delUrl;
	});
 	</script>
</c:if>



 <c:if test="${not empty(errors)}">
  <input type="hidden" id="errorId" value="${errors.errorMsg}">
	<script type="text/javascript">
	  var delUrl='get_batch_list';
	   $(document).ready(function() {
		 var errorId=document.getElementById('errorId');
		 alert(errorId.value);
		 //	alert('Branch can not be Removed this Field is using in other Form');
    	 document.location = delUrl;
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
			$(document).ready(function() {  	
				$('.fixmyheader-8').fixheadertable({
					caption		: 'My header is fixed !',
					height		:450,
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
		.View{width:48px !important; border:none!important}
		.in{width:177px !important}
   .bn{width:76px !important}
   .mfdate{width:83px !important}
   .expdate{width:78px !important}
   .purc{width:77px !important}
   .mrp{width:64px !important}
   .inv{width:78px !important}
   .ex{width:70px !important}
   .open{width:83px !important}
    .close{width:70px !important}
       .Ac{width:59px !important}
		.ui-widget-content {
overflow-x: hidden !important;
 
}
</style> 
  
<form:form name="input" action="get_batch_list"   class="formdiv" method="post" modelAttribute="batchSearchCriteria" >
		    
    <div class="header"  > Batch List </div> 
	<div class="headingdiv "  >
	  <table width="880" height="31" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td width="61"><a onclick="return checkAdd();" href="show_new_batch_form" class="addbtn"	iconCls="icon-add"></a> </td>
          <td width="98"><input class="exportbtn"  type="export" value=""/> </td>
        
          <td width="105"><div align="center">Item </div></td>
          <td width="63"><form:input  path="itemName" size="16" id="itemName" /></td>
          <td width="83"><div align="center">Batch No </div></td>
          <td width="203"><form:input  path="batchNo" size="16" id="batchNo" /></td>
          <td width="62"><input class="searchbtn"  type="submit" value=""/></td>
          <td width="64">
          <a href="get_batch_list" class="cancelbtn" 
			iconCls="icon-cancel"></a>
          </td>
        </tr>
      </table>
	</div>
	<div class="gridheadingdiv"  >	 
	<table width="972" class="fixmyheader-8" id="example">
  <thead>
   <tr>        
   		<td style="border: none" class="View" width="50"><div align="center">View</div></td>	
          <td style="border: none" class="in" width="177"><div align="center">Item Name</div></td>
          <td style="border: none"  class="bn" width="76"><div align="center">Batch No</div></td>
          <td style="border: none"  class="mfdate" width="83"><div align="center">Mfg. Dt.</div></td>
          <td style="border: none"  class="expdate" width="78"><div align="center">Expiry Dt.</div></td>
          <td style="border: none"  class="purc" width="77"><div align="center">Purchase</div></td>
          <td style="border: none"  class="mrp" width="64"><div align="center">MRP</div></td>
          <td style="border: none"  class="inv" width="78"><div align="center">Invoice</div></td>
          <td style="border: none"  class="ex" width="70"><div align="center">Excise %</div></td>
          <td style="border: none"  class="open" width="83"><div align="center">Opening Stk.</div></td>
          <td style="border: none"  class="close" width="70"><div align="center">Closing</div></td>
          <td style="border: none"  class="Ac" width="59"><div align="center">Action</div></td>
        </tr>
          </thead>
  <tbody>
        <c:forEach items="${batchList}" var="batch" varStatus="s">
        <tr>
       <td style="text-align: center;" width="38">
			<c:url value="get_batch" var="view_url">
			<c:param name="batch_no" value="${batch.batchNo}"></c:param>
			<c:param name="opr" value="V"></c:param>
		  </c:url>
          <a href="${view_url}"><img src="static/images/view_icon.png" title="View Record" alt="" /></a></td>
          <td width="167">&nbsp;<c:out value="${batch.itemDTO.itemName}" /></td>
          <td width="66">&nbsp;<c:out value="${batch.batchNo}" /></td>
          <td width="73">&nbsp;<fmt:formatDate pattern="dd-MMM-yyyy"
            value="${batch.mfgDate}" /></td>
          <td width="68">&nbsp;<fmt:formatDate pattern="dd-MMM-yyyy"
            value="${batch.expiryDate}" /></td>
          <td width="67" style="text-align: right;" align="right">&nbsp;
           <fmt:formatNumber value="${batch.purchaseRate}" type="number"   minFractionDigits="2" maxFractionDigits="2"/>
          </td>
          <td width="54" style="text-align: right;" align="right">&nbsp;<fmt:formatNumber value="${batch.mrp}" type="number"  minFractionDigits="2" maxFractionDigits="2"/>&nbsp;</td>
          <td width="68" style="text-align: right;" align="right">&nbsp;<fmt:formatNumber value="${batch.invoiceRate}" type="number"  minFractionDigits="2" maxFractionDigits="2"/>&nbsp;</td>
          <td width="60" style="text-align: right;" align="right">&nbsp;<fmt:formatNumber value="${batch.excisePerc}" type="number"  minFractionDigits="2" maxFractionDigits="2"/>&nbsp;</td>
          <td width="73" style="text-align: right;" align="right">&nbsp;<fmt:formatNumber value="${batch.openingStock}" type="number"  minFractionDigits="2" maxFractionDigits="2"/>&nbsp;</td>
          <td width="60" style="text-align: right;" align="right">&nbsp;<fmt:formatNumber value="${batch.closingStock}" type="number"  minFractionDigits="2" maxFractionDigits="2"/>&nbsp;</td>
          <td width="59" style="" >
         	<c:url value="get_batch" var="remove_url">
					<c:param name="batch_no" value="${batch.batchNo}"></c:param>
					<c:param name="opr" value="R"></c:param>
					</c:url>
					<c:url value="get_batch" var="edit_url">
					<c:param name="batch_no" value="${batch.batchNo}"></c:param>
					<c:param name="opr" value="E"></c:param>
					</c:url>
		
		<c:forEach items="${roleAndRightsDTOList}" var="roleAndRights">
		   <c:if test="${roleAndRights.menuId==sessionScope.menuId}">
		    <input type="hidden"  id="addFlag" value="${roleAndRights.addFlag}" >
		  	 <c:if test="${roleAndRights.editFlag=='true'}">
		      <a id="editUrlId" href="${edit_url}"><img	 src="static/images/change_btn.png" title="Edit Record" alt="" /></a>
		      </c:if>
		      <c:if test="${roleAndRights.editFlag=='false'}">
		      <img	onclick="checkEdit();" src="static/images/change_btn.png" title="Edit Record" alt="" />
		      </c:if>
		      <c:if test="${roleAndRights.editFlag=='true'}">
		      <a href="${remove_url}"><img src="static/images/drop.png" title="Delete Record"	alt="" /></a>
         	  </c:if>
         	  <c:if test="${roleAndRights.editFlag=='false'}">
		      <img onclick="checkDelete();" src="static/images/drop.png" title="Delete Record"	alt="" />
         	  </c:if>
          </c:if>
          </c:forEach>				        </tr>
       </c:forEach>
  
  </tbody>
</table>
  </div>
  
 </form:form>
