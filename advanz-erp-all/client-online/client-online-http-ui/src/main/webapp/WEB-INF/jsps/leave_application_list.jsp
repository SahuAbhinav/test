<%@page import="java.util.List"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false"%>
<%@ page session="true" %>
	<script type="text/javascript" charset="utf-8">
	
                $(document).ready(function() {
                    /* Initialise datatables */
                    var oTable = $('#example').dataTable({bInfo:""});
                } );
            </script>
 <c:if test="${succ=='Blk'}">
  <script type="text/javascript">
  	var delUrl='get_area_list';
  	$(document).ready(function() {
      alert('No Record Found !!!!');
      //window.self.location  = delUrl;
	});
 	</script>
</c:if>

 <c:if test="${areaForm.succ=='Ad'}">
  <script type="text/javascript">
  var delUrl='get_area_list';
	$(document).ready(function() {
     alert('Record Inserted Successfully !!!!');
     window.self.location = delUrl;
	});
 	</script>
</c:if>

<c:if test="${areaForm.succ=='Dl'}">
  <script type="text/javascript">
  var delUrl='get_area_list';
  $(document).ready(function() {
     alert('Record Deleted Successfully !!!!');
     window.self.location = delUrl;
	});
 	</script>
</c:if>

<c:if test="${areaForm.succ=='Up'}">
  <script type="text/javascript">
  var delUrl='get_area_list';
	$(document).ready(function() {
     alert('Record Updated Successfully !!!!');
     window.self.location = delUrl;
	});
 	</script>
</c:if>

 <c:if test="${not empty(errors)}">
 <input type="hidden" id="errorId" value="${errors.errorMsg}">
	<script type="text/javascript">
		var delUrl='get_area_list';
		$(document).ready(function() {
			var errorId=document.getElementById('errorId');
			alert(errorId.value);
    	 window.self.location = delUrl;
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
			.View{width:65px !important; border:none!important}
	.An{width:189px !important; border:none!important}
	.code{width:118px !important; border:none!important}
	.region{width:174px !important; border:none!important}
	.desp{width:100px !important; border:none!important}
	.Ac{width:62px !important; border:none!important}			
	
th{font-size:10px;}
 td{font-size:12px;}  
 
 	
	div.t_fixed_header_main_wrapper.ui div.t_fixed_header_caption {
		display:none;
		
		}
	
</style>

    
  
<form:form name="input" action="show_leave_application_list" class="formdiv" method="post" modelAttribute="areaSearchCriteria" >
		     
    <div class="header">Leave Application List</div> 
	<div class="headingdiv">
	  <table width="880" height="31" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td width="45"><a onclick="return checkAdd();" value="" name="operation"  href="show_leave_application?operation=show"	class="addbtn" iconCls="icon-add"> </a></td>
        
        </tr>
      </table>
	</div>
<div id="demo">
	<div class="gridheadingdiv">
	  <table width="972" class="display fixmyheader-8" id="example">
 
  <thead>
   <tr>
	      <td class="View" width="55"><div align="center">View</div></td>
          <td class="An" width="189"><div align="center">Employee Name</div></td>
          <td class="code" width="118"><div align="center">Employee Code</div></td>
          <td class="region" width="174"><div align="center">Leave Type</div></td>
          <td class="desp" width="100"><div align="center">From Date</div></td>
           <td class="desp" width="100"><div align="center">To Date</div></td>
          <td class="Ac" ><div align="center">Action</div></td>
        </tr>
    
     </thead>
  <tbody>  
        <c:forEach items="${leaveList}" var="area">			
        <tr>
         <td style="text-align: center;" width="55">
			<c:url value="show_leave_application" var="view_url">
			<c:param name="sno" value="${area.sno}"></c:param>
			<c:param name="operation" value="view"></c:param>
		  </c:url>
          <a href="${view_url}"><img src="static/images/view_icon.png" title="View Record" alt="" /></a></td>
            
          <td width="179">&nbsp; <c:out value="${area.employeeDTO.employeeName}" /></td>
          <td width="108">&nbsp;<c:out value="${area.employeeDTO.employeeCode}" /></td>
          <td width="164">&nbsp;<c:out value="${area.leaveTypeMastDTO.leaveName}" /></td>
          <td width="100">&nbsp;<fmt:formatDate pattern="dd-MMM-yyyy"	value="${area.fromDate}" /></td>
           <td width="100">&nbsp;<fmt:formatDate pattern="dd-MMM-yyyy"	value="${area.toDate}" /></td>
          <td width="62" style="" >
          <c:url value="show_leave_application" var="edit_url">
					<c:param name="sno" value="${area.sno}"></c:param>
					<c:param name="operation" value="update"></c:param>
		  </c:url>
		  <c:url value="show_leave_application" var="remove_url">
					<c:param name="sno" value="${area.sno}"></c:param>
					<c:param name="operation" value="Delete"></c:param>
		  </c:url>
		
		 <c:forEach items="${roleAndRightsDTOList}" var="roleAndRights">
		   <c:if test="${roleAndRights.menuId==sessionScope.menuId}">
		    <input type="hidden"  id="addFlag" value="${roleAndRights.addFlag}" >
		  	 <c:if test="${roleAndRights.editFlag=='true'}">
		  	 <c:choose>
												<c:when test="${area.approveFlag=='true'}">
													<img onclick="checkApproved();"
														src="static/images/change_btn.png" title="Edit Record"
														alt="" />
			</c:when>
		  	 <c:otherwise>
		      <a id="editUrlId" href="${edit_url}"><img	 src="static/images/change_btn.png" title="Edit Record" alt="" /></a>
		      </c:otherwise>
				</c:choose>
		      </c:if>
		      <c:if test="${roleAndRights.editFlag=='false'}">
		      <img	onclick="checkEdit();" src="static/images/change_btn.png" title="Edit Record" alt="" />
		      </c:if>
		      <c:if test="${roleAndRights.deleteFlag=='true'}">
		       <c:choose>
												<c:when test="${area.approveFlag=='true'}">
													<img onclick="checkApproved();"
														src="static/images/drop.png" title="Edit Record"
														alt="" />
			</c:when>
		  	 <c:otherwise>
		      <a href="${remove_url}"><img src="static/images/drop.png" title="Delete Record"	alt="" /></a>
         	  </c:otherwise></c:choose>
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

