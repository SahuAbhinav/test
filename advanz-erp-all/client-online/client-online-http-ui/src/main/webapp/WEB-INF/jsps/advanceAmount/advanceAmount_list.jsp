<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="true" %>
<%@ page isELIgnored="false" %>
<script type="text/javascript" charset="utf-8">
$(document).ready(function(){ 
 $(".datepicker[readonly]").css("background-color","#ffffff" );
 
 $(".fromDate" ).datepicker({dateFormat : 'dd-M-yy',changeMonth: true,
     changeYear: true, yearRange: '-99:+0'});
 $(".toDate" ).datepicker({dateFormat : 'dd-M-yy',changeMonth: true,
     changeYear: true, yearRange: '-99:+0'});
} );
</script>
<script type="text/javascript" charset="utf-8">
                                    
                $(document).ready(function() {
                    /* Initialise datatables */
                    var oTable = $('#example').dataTable({bInfo:""});
                    
                } );
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
 .View{width:30px !important; border:none!important}
 .trDate{width:80px !important; border: none !important; }
.trId{width:80px !important; border: none !important;}
.name{width:100px !important; border: none !important; }
.designation{width:100px !important; border: none !important; }
.openingBl{width:80px !important; border: none !important; }
.trType{width:80px !important; border: none !important; }
.trAmount{width:80px !important; border: none !important; }
.closingBl{width:80px !important; border: none !important; }
.remark{width:80px !important; border: none !important; }
.Ac{width:64px !important; border: none !important; }
th{font-size:10px; border:  }
 td{font-size:12px; border:  }  
 
 	.ui-widget-content {
overflow-x: hidden !important;
 
}	
	div.t_fixed_header_main_wrapper.ui div.t_fixed_header_caption {
		display:none;
		
		}
	
</style>

<%-- 
<c:if test="${melterSummaryForm.succ=='Ad'}">
  <script type="text/javascript">
  var delUrl='show_melter_summary_form';
	$(document).ready(function() {
     alert('Record Inserted Successfully !!!!');
     window.self.location = delUrl;
	});
 	</script>
</c:if>

<c:if test="${succ=='Blk'}">
  <script type="text/javascript">
  	var delUrl='show_melter_summary_form';
  	$(document).ready(function() {
      alert('No Record Found !!!!');
    //  window.self.location  = delUrl;
	});
 	</script>
</c:if>


<c:if test="${melterSummaryForm.succ=='Dl'}">
  <script type="text/javascript">
  var delUrl='show_melter_summary_form';
  $(document).ready(function() {
     alert('Record Deleted Successfully !!!!');
     window.self.location = delUrl;
	});
 	</script>
</c:if>

<c:if test="${melterSummaryForm.succ=='Up'}">
  <script type="text/javascript">
  var delUrl='show_melter_summary_form';
	$(document).ready(function() {
     alert('Record Updated Successfully !!!!');
     window.self.location = delUrl;
	});
 	</script>
</c:if>

 <c:if test="${not empty(errorList)}">
 <input type="hidden" id="errorId" value="${errorList.errorMsg}">
	<script type="text/javascript">
		var delUrl='show_melter_summary_form';
		$(document).ready(function() {
			var errorId=document.getElementById('errorId');
			alert(errorId.value);
    	 window.self.location = delUrl;
		});
 	</script>
</c:if> --%>
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

   <form:form name="input" action="advanceAmountSearch" class="formdiv" method="post" modelAttribute="advanceAmountForm" >
		     
    <div class="header">Advance Amount List </div> 
	<div class="headingdiv">
	  <table width="880"  height="31" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td width="70"><a onclick="return checkAdd();" href="new_advance" class="addbtn" iconCls="icon-add"></a></td>
          <td width="100"><a href="#" class="exportbtn" iconCls="icon-redo" onClick="javascript:$('#dlg').dialog('close')"></a></td>
          <td width="101" ><div align="center">From Date</div></td>
          <td width="100"><form:input  type="text" class="fromDate"  readonly="true" path="fromDate" size="5" id="fromDate" /></td>
          <td width="101" nowrap="nowrap"><div align="center">To Date</div></td>
          <td width="100"><form:input  type="text" class="toDate"  readonly="true" path="toDate" size="5" id="toDate" /></td>
         <td width="101"><div align="center">Name</div></td>
          <td width="100"><form:input  type="text"  path="employeeName" size="9" id="logDate" /></td> 
          <td width="101"><div align="center">Designation</div></td>
          <td width="100">
          <form:select path="designation" id="mastersId"
			 class="validate[required] text-input " style="width:100px; height: 20px;">
			 <form:option value="">Select</form:option>
			 <form:options items="${designation}" itemLabel="name" itemValue="name"/>
 	</form:select>
          
          <td width="101"><div align="center">Tr. Type</div></td>
          <td width="100">
          	<form:select style="width:80px; height:22px" path="transactionType" id="transactionType" class="validate[required] text-input" >
					<form:option value="">Select</form:option>
					<form:option value="Deduction">Deduction</form:option>
					<form:option value="Given">Given</form:option>										
			</form:select>
         
             
          <td width="45"><input  class="searchbtn"  type="submit" value=""/></td>
      <td width="45">
      <a href="advanceAmountList"  class="cancelbtn" iconCls="icon-cancel"></a> 
      </td>
      </tr>
      </table>
	</div>
	<div class="gridheadingdiv">
	 <table width="972" class="display fixmyheader-8" id="example">
  <thead>
   <tr>
	      <td class="View" width="60"><div align="center">View</div></td>
	      <td class="trDate" width="70"><div align="center"><div>Date</div></div></td>
          <td class="trId" width="70"><div align="center"><div>Tr. Id</div></div></td>
          <td class="name" width="100"><div align="center"><div>Emp Name</div></div></td>
          <td class="designation" width="100"><div align="center">Designation</div></td>      
          <td class="openingBl" width="80"><div align="center"><div>Opening Bl.</div></div></td>
          <td class="trType" width="80"><div align="center"><div>Tr. Type</div></div></td>
          <td class="trAmount" width="80"><div align="center"><div>Tr. Amount</div></div></td>
          <td class="closingBl" width="80"><div align="center"><div>Closing Bl.</div></div></td>
          <td class="closingBl" width="80"><div align="left"><div>Remark</div></div></td>
          <td class="Ac" width="65"><div align="center">Action</div></td>
        </tr>
  </thead>
  <tbody> 
        <c:forEach items="${advanceAmountList}" var="advanceAmountDto">
			<tr>
			
				<td style="text-align: center;" width="24">
			<c:url value="findBy_Id" var="view_url">
			<c:param name="sno" value="${advanceAmountDto.sno}"></c:param>
			<c:param name="opr" value="V"></c:param>
		  </c:url>
          <a href="${view_url}"><img src="static/images/view_icon.png" title="View Record" alt="" /></a></td>
          		<td width="70"><fmt:formatDate pattern="dd-MMM-yyyy"  value="${advanceAmountDto.transactionDate}" /></td>
            	<td width="70"><c:out value="${advanceAmountDto.sno}"/></td>
				<td width="100" style="text-align: right;" >&nbsp;<c:out value="${advanceAmountDto.employeeDTO.employeeName}"/></td>
				<td width="100" style="text-align: right;" >&nbsp;<c:out value="${advanceAmountDto.employeeDTO.mastersDTO.name}"/></td>
				<td width="80" style="text-align: right;" >&nbsp;<c:out value="${advanceAmountDto.openingBalance}"/></td>
				<td width="80" style="text-align: right;" >&nbsp;<c:out value="${advanceAmountDto.transactionType}"/></td>
				<td width="80" style="text-align: right;" >&nbsp;<c:out value="${advanceAmountDto.amount}"/></td>
				<td width="80" style="text-align: right;" >&nbsp;<c:out value="${advanceAmountDto.closingBalance}"/></td>
				<td width="80" style="text-align: right;" >&nbsp;<c:out value="${advanceAmountDto.remark}"/></td>
				<td width="70" style="text-align: center; " >
         			<c:url value="findBy_Id" var="remove_url">
					<c:param name="sno" value="${advanceAmountDto.sno}"></c:param>
					<c:param name="opr" value="R"></c:param>
					</c:url>
					<c:url value="findBy_Id"  var="edit_url">
					<c:param name="sno" value="${advanceAmountDto.sno}"></c:param>
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
		      <c:if test="${roleAndRights.deleteFlag=='true'}">
		      <a href="${remove_url}"><img src="static/images/drop.png" title="Delete Record"	alt="" /></a>
         	  </c:if>
         	  <c:if test="${roleAndRights.deleteFlag=='false'}">
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
  </div>
 </form:form>
 <!-- </form>-->
 