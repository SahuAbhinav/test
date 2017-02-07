<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false"%>
<%@ page session="true" %>
<script type="text/javascript" charset="utf-8">
    $(document).ready(function() {
      /* Initialise datatables */
    var oTable = $('#example').dataTable({ 	 
   		       "aLengthMenu": [['',10, 25, 50, -1], ['',10, 25, 50, "All"]],
               "iDisplayLength":10000,
               bInfo:""
       });
    } );          
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
	<c:if test="${error.errorMsg!=null}">
	<input type="hidden" id="errorId" value="${error.errorMsg}">
		<script type="text/javascript">
		$(document).ready(function() {
			var errorId=document.getElementById('errorId');
			alert(errorId.value);
    	});
 	</script>
	</c:if>
	
	<c:if test="${errors.errorMsg!=null}">
	<input type="hidden" id="errorId1" value="${error.errorMsg}">
		<script type="text/javascript">
		$(document).ready(function() {
			var errorId=document.getElementById('errorId1');
			alert(errorId.value);
    	});
 	</script>
	</c:if>
	
	<c:if test="${error123.errorMsg!=null}">
	<input type="hidden" id="errorId" value="${error123.errorMsg}">
	<script type="text/javascript">
		$(document).ready(function() {
		var errorId=document.getElementById('errorId');
		alert(errorId.value);
		 window.self.location = 'show_debit_duty_list?operation=show';
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
.View{width:34px !important; border:none!important}			
.Sn{width:30px !important; border:none !important;  }
.Dn{width:77px !important; border:none !important }
  .Da{width:79px !important; border:none !important  }
  .Pn{width:75px !important; border:none !important  }
  .Tra{width:75px !important; border:none !important  }
  .Vn{width:77px !important; border:none !important  }
  .Dri{width:87px !important; border:none !important  }
  .Ti{width:82px !important; border:none !important  }
   .Tp {width:78px !important; border:none !important }
  .Tw{width:72px !important; border:none !important }
 
  .Ac{width:62px !important; border:none !important }
</style>
<script type="text/javascript">
function checkApproved(){
	alert('You can not update or delete ,this item has been approved ');
}
	function destroyItem(id) {

		var r = confirm("Are you sure you want to remove this Item?");

		if (r) {

			$.get('remove_item', {
				itemId : id
			});

		}
		$('#tt').datagrid('reload');
	}
	
	function remoneConformation(){
		var name =	confirm('Are you sure that you want to delete this item?');
		if(name==true){
				return true;
			} else{
			return false;
		     }
	  }
</script>
<form:form name="input" action="submit_debit_duty_List"
	commandName="debitDuty" class="formdiv" method="get">
	<div class="header">Debit Duty List</div>
	<div class="headingdiv">
		<table width="880" height="31" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				<td width="84">
					
			<c:forEach items="${roleAndRightsDTOList}" var="roleAndRights">
		   <c:if test="${roleAndRights.menuId==sessionScope.menuId}">
		     <c:if test="${roleAndRights.addFlag=='true'}">
		      <input type="submit" class="addbtn"	name="operation" value="" onclick="this.value='Add';" />
			 </c:if>
		      <c:if test="${roleAndRights.addFlag=='false'}">
		        <input type="submit" class="addbtn"	name="operation" value="" onclick="return checkAdd();" />
		      </c:if>
			
			</c:if></c:forEach>
					</td>
					<td width="96"><input type="submit" class="exportbtn"
					type="button" value=" " /></td>
				
				<td width="82"><div align="center">From Date</div></td>
          <td width="64"> <form:input path="debitDutyMasterDTO.fromDate" class="fromDate"   size="16" /></td>
         <td width="82"><div align="center">To Date</div></td>
          <td width="64"> <form:input path="debitDutyMasterDTO.toDate"  class="toDate" size="16" /></td>
				<td width="96"><div align="center">Approval Status</div></td>
				<td>
				<form:select path="approveStatus" >
				<form:option value=""></form:option>
				<form:option value="Approved">Approved</form:option>
				<form:option value="Not Approved">Not Approved</form:option>
				</form:select>
			</td>
	        <td width="87">
	        <input type="submit" class="searchbtn"
					name="operation" value=""  onclick="this.value='Search';"/>
	        </td>
	        <td width="105"><div class="cancelbtn">
			<input type="submit" value="" name="operation" class="cancelbtn" onclick="this.value='Cancel';" />
			</div></td>
			</tr>
		</table>
	</div>
	<div class="gridheadingdiv">
		  <table width="972" class="display fixmyheader-8" id="example">
  <thead>
   <tr>
				<td class="View"><div align="center">View</div></td>
                 <td class="Sn"><div align="center">S. No.</div></td>
				<td class="Dn"><div align="center">Debit Duty No</div></td>
				<td class="Da"><div align="center">Date</div></td>
				<td class="Pn"><div align="center">Excise Amount</div></td>
				<td class="Tra"><div align="center">Cess Amount</div></td>
				<td class="Vn"><div align="center">H.Cess Amount</div></td>
				<td class="Dri"><div align="center">Narration</div></td>
				<td class="Ti"><div align="center">Approved Status</div></td>
				<td class="Ac"><div align="center">Action</div></td>
		</tr>
  </thead>
  <% int i=1;%>
  <tbody>
		<c:forEach items="${debitDutyList}" var="disp">
					        <tr>		
					        <td style="text-align: center;" width="24">
			<c:url value="show_debit_duty" var="view_url">
			<c:param name="debitDutyAutoId" value="${disp.debitDutyAutoId}"></c:param>
			<c:param name="debitDutyNumber" value="${disp.debitDutyNumber}"></c:param>
			<c:param name="operation" value="V"></c:param>
			<c:param name="approvedFlag" value="${disp.approvedFlag}"></c:param>
		  </c:url>
          <a href="${view_url}"><img src="static/images/view_icon.png" title="View Record" alt="" /></a></td>						        
					        <td  width="20">&nbsp;<%= i%></td>
							<td  width="67">&nbsp;<c:out value="${disp.debitDutyNumber}" /></td>
							<td width="69">&nbsp;<fmt:formatDate pattern="dd-MMM-yyyy"  value="${disp.debitDutyDate}" /></td>
							<td width="70">&nbsp;<c:out value="${disp.exciseAmount}" /></td>
							<td width="70">&nbsp;<c:out value="${disp.eduCessAmount}" /></td>
							<td width="67">&nbsp;<c:out value="${disp.hEduCessAmount}" /></td>
							<td width="77">&nbsp;<c:out value="${disp.narration}" /></td>
							<td width="72" style="text-align:right" >&nbsp;
							 <c:choose>
		  	 <c:when test="${disp.approvedFlag=='1'}">Approved
		  	 </c:when><c:otherwise>Not Approved</c:otherwise></c:choose>
							</td>
		<td width="62" style="">
		
		<c:url value="show_debit_duty" var="remove_url">
		<c:param name="debitDutyAutoId" value="${disp.debitDutyAutoId}"></c:param>
		<c:param name="operation" value="Delete"></c:param>
		</c:url>
		<c:url value="show_debit_duty" var="edit_url">
			<c:param name="debitDutyAutoId" value="${disp.debitDutyAutoId}"></c:param>
			<c:param name="debitDutyNumber" value="${disp.debitDutyNumber}"></c:param>
			<c:param name="operation" value="Edite"></c:param>
		</c:url>
		
		<c:forEach items="${roleAndRightsDTOList}" var="roleAndRights">
	
		<input type="hidden"  id="addFlag" value="${roleAndRights.addFlag}" >
		   <c:if test="${roleAndRights.menuId==sessionScope.menuId}">
		    <input type="hidden"  id="addFlag" value="${roleAndRights.addFlag}" >
		  	 <c:if test="${roleAndRights.editFlag=='true'}">
		  	  <c:choose>
		  	 <c:when test="${disp.approvedFlag=='1'}">
		  	   <img	onclick="checkApproved();" src="static/images/change_btn.png" title="Edit Record" alt="" />
		  	 </c:when><c:otherwise>
		  	 
		      <a id="editUrlId" href="${edit_url}"><img	 src="static/images/change_btn.png" title="Edit Record" alt="" /></a>
		     </c:otherwise></c:choose>
		     
		      </c:if>
		      <c:if test="${roleAndRights.editFlag=='false'}">
		      <c:choose>
		  	 <c:when test="${disp.approvedFlag=='1'}">
		  	   <img	onclick="checkApproved();" src="static/images/change_btn.png" title="Edit Record" alt="" />
		  	 </c:when><c:otherwise>
		      <img	onclick="checkEdit();" src="static/images/change_btn.png" title="Edit Record" alt="" />
		      </c:otherwise></c:choose>
		      
		      </c:if>
		      <c:if test="${roleAndRights.deleteFlag=='true'}">
		      <c:choose>
		  	 <c:when test="${disp.approvedFlag=='1'}">
		  	   <img	onclick="checkApproved();" src="static/images/drop.png" title="Edit Record" alt="" />
		  	 </c:when><c:otherwise>
		      <a href="${remove_url}"><img src="static/images/drop.png" title="Delete Record"	alt="" /></a>
         	 </c:otherwise></c:choose> </c:if>
         	  <c:if test="${roleAndRights.deleteFlag=='false'}">
         	   <c:choose>
		  	 <c:when test="${disp.approvedFlag=='1'}">
		  	   <img	onclick="checkApproved();" src="static/images/drop.png" title="Edit Record" alt="" />
		  	 </c:when><c:otherwise>
		      <img onclick="checkDelete();" src="static/images/drop.png" title="Delete Record"	alt="" />
         	 </c:otherwise></c:choose> </c:if>
          </c:if>
          </c:forEach>		
			<%i++; %>
		</tr>
	</c:forEach>
  </tbody>
</table>
  </div>
  
 </form:form>

</html>
