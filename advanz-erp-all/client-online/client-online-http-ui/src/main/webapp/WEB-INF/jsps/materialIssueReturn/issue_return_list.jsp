<%@page import="java.util.List"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false"%>
<%@ page session="true" %>
	<script type="text/javascript" charset="utf-8">
	             $(document).ready(function() {
                    /* Initialise datatables */
                    var oTable = $('#example').dataTable({bInfo:"", "bPaginate": false});
                } );
            </script>
 <c:if test="${succ=='Blk'}">
  <script type="text/javascript">
  	var delUrl='show_issue_return_list';
  	$(document).ready(function() {
      alert('No Record Found !!!!');
      //window.self.location  = delUrl;
	});
 	</script>
</c:if>

 <c:if test="${issueReturnMasterForm.opr=='Ad'}">
  <script type="text/javascript">
  var delUrl='show_issue_return_list';
	$(document).ready(function() {
     alert('Record Inserted Successfully !!!!');
    // window.self.location = delUrl;
	});
 	</script>
</c:if>

<c:if test="${issueReturnMasterForm.opr=='Dl'}">
  <script type="text/javascript">
  var delUrl='show_issue_return_list';
  $(document).ready(function() {
     alert('Record Deleted Successfully !!!!');
    // window.self.location = delUrl;
	});
 	</script>
</c:if>

<c:if test="${issueReturnMasterForm.opr=='Up'}">
  <script type="text/javascript">
  var delUrl='show_issue_return_list';
	$(document).ready(function() {
     alert('Record Updated Successfully !!!!');
    // window.self.location = delUrl;
	});
 	</script>
</c:if>

 <c:if test="${not empty(errorList)}">
 <input type="hidden" id="errorId" value="${errorList.errorMsg}">
	<script type="text/javascript">
	  var delUrl='show_issue_return_list';
		$(document).ready(function() {
			var errorId=document.getElementById('errorId');
			alert(errorId.value);
    	// window.self.location = delUrl;
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
		//$("div.dataTables_info").text('');
		//$( "#datepicker" ).datepicker();
		$("#datepicker").datepicker({
			dateFormat : 'dd/mm/yy',
			autoSize : true,
			changeMonth : true,
			changeYear : true,
			altFormat : 'mm/dd/yy',
			altField : '#edd1'
		});
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
					height		:407,
					addTitles	: true,
					colratio	: ['10%', '10%', '8%', '50px', 'auto', 'auto', '30%', 'auto']
				});
			});
		</script>
  
     
<style type="text/css">
	body {
		font-family:Arial, Helvetica, sans-serif;
		}
		.ui-widget-content {
	overflow-x: hidden !important;
 }
	code, pre {		
	
				padding		: 10px;	
				background	: #F5F5F5;
				border		: 1px solid #D4D4D4;
				overflow-x	: auto;
				font-size	: 12px;
			}
	.View{width:45px !important; border:none!important}
	.SNo{width:40px !important; border:none!important}
	.ReturnNumber{width:120px !important; border:none!important}
	.ReturnDate{width:120px !important; border:none!important}
	.ReturnBy{width:120px !important; border:none!important}
	.Department{width:120px !important; border:none!important}
	.TotalItems{width:60px !important; border:none!important}
	.Ac{width:62px !important; border:none!important}			
	
th{font-size:10px;}
 td{font-size:12px;}  
 
 	
	div.t_fixed_header_main_wrapper.ui div.t_fixed_header_caption {
		display:none;
		
		}
	
</style>
<script type="text/javascript">
function printIssueReturn(ele){
	 var url = "issue_return_print_report/pdf?IssueReturnNoPrompt="+ele;
	window.open(url);
	
}
</script>
    
  
<form:form name="input" action="search_issue_return" class="formdiv" method="post" modelAttribute="issueReturnSearchCriteriaDTO" >
		     
    <div class="header">Issue Return List</div> 
	<div class="headingdiv">
	  <table width="880" height="31" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td width="45"><a onclick="return checkAdd();" href="show_new_issue_return"	class="addbtn" iconCls="icon-add"> </a></td>
          <td width="45"><input class="exportbtn"  type="button" value=""/> </td>
        <td width="80"><div align="center">Return No.</div></td>
          <td width="60">
      	 <form:input path="returnNumber"  data-maxsize="50"  size="15" id="" /></td>
      	 <td width="65"><div align="center">From Date</div></td>
          <td width="62">
      	 <form:input path="fromDate" class="fromDate" data-maxsize="50"  size="10" id="" /></td>
        
          <td width="60"><div align="center">To Date</div></td>
          <td width="63">
		 <form:input data-maxsize="100" path="toDate" class="toDate" size="10" id="" /></td>
        
		
        <td width="40">Dept.</td>
	<td><form:select  path="departmentId"  id="deptId">
			<form:option value="">Select</form:option>
		<form:options items="${deptTypeList}" itemLabel="name" itemValue="mastersId"/>
		</form:select></td>
			<td width="82"><input class="searchbtn"  type="submit" value=""/></td>
          <td width="83"><a href="show_issue_return_list"  class="cancelbtn" ></a></td>
        </tr>
      </table>
	</div>
<div id="demo">
	<div class="gridheadingdiv">
	  <table width="972" class="display fixmyheader-8" id="example">
 
  <thead>
   <tr>
	      <td class="View" width="45"><div align="center">View</div></td>
	      <td class="SNo" width="40"><div align="center">SNo</div></td>
          <td class="ReturnNumber" width="120"><div align="center">Return Number</div></td>
          <td class="ReturnDate" width="120"><div align="center">Return Date</div></td>
          <td class="ReturnBy" width="120"><div align="center">Return By</div></td>
          <td class="Department" width="120"><div align="center">Department</div></td>
		  <td class="TotalItems" width="60"><div align="center">Total Items</div></td>	
		  <td class="Ac" width="60"><div align="center">Action</div></td>
        </tr>
    
     </thead>
  <tbody>  
        <c:forEach items="${issueReturnMasterDTOList}" var="issueReturn" varStatus="s">			
        <tr>
         <td style="text-align: center;" width="40">
			<c:url value="get_issue_return" var="view_url">
			<c:param name="issueId" value="${issueReturn.issueReturnAutoId}"></c:param>
			<c:param name="opration" value="View"></c:param>
		  </c:url>
          <a href="${view_url}"><img src="static/images/view_icon.png" title="View Record" alt="" /></a></td>
          <td width="40">&nbsp;<c:out value="${s.count}" /></td>
          <td width="120">&nbsp;<c:out value="${issueReturn.issueReturnNumber}" /></td>
          <td width="120">&nbsp;<fmt:formatDate pattern="dd-MMM-yyyy"  value="${issueReturn.issueReturnDate}" /></td>
          <td width="120">&nbsp;<c:out value="${issueReturn.issuedReturnBy}" /></td>
          <td width="120">&nbsp;<c:out value="${issueReturn.mastersDTO.name}" /></td>
           <td width="62" style="" >&nbsp;<c:out value="${issueReturn.issueReturnDetailDTOList.size()}" /></td>
          <td width="62">&nbsp;
         <c:url value="get_issue_return" var="edit_url">
					<c:param name="issueId" value="${issueReturn.issueReturnAutoId}"></c:param>
					<c:param name="opration" value="E"></c:param>
					
		  </c:url>
		  <c:url value="get_issue_return" var="remove_url">
					<c:param name="issueId" value="${issueReturn.issueReturnAutoId}"></c:param>
					<c:param name="opration" value="Remove"></c:param> 
		  </c:url>
		<img	onclick="printIssueReturn('<c:out value="${issueReturn.issueReturnNumber}" />');" src="static/images/print_icon.png" title="Print Issue Return Slip" alt="" />
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
          
        </tr>
        </c:forEach>
  
  </tbody>
</table>
  </div>
  </div>
  
   <div style="float: right;"><c:url value="show_issue_return_list" var="remove_url">
<c:param name="next" value="${issueReturnMasterForm.next+(15)}"></c:param>
</c:url> <a href="${remove_url}" class="nextbtn" ></a>
</div>
<div style="float: right;">
<c:url value="show_issue_return_list" var="remove_url">
<c:param name="next" value="${issueReturnMasterForm.previous-(15)}"></c:param>
</c:url> <a href="${remove_url}" class="previousbtn" ></a>
</div>
 </form:form>

