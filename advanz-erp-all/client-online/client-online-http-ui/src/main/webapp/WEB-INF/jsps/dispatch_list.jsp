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
               ,bInfo:""
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
		 window.self.location = 'show_dispatch_list?operation=show';
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
  .Pn{width:149px !important; border:none !important  }
  .Tra{width:96px !important; border:none !important  }
  .Vn{width:77px !important; border:none !important  }
  .Dri{width:87px !important; border:none !important  }
  .Ti{width:62px !important; border:none !important  }
   .Tp {width:78px !important; border:none !important }
  .Tw{width:72px !important; border:none !important }
 
  .Ac{width:62px !important; border:none !important }
</style>
<script type="text/javascript">
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
<form:form name="input" action="submitDispatchList"
	commandName="dispatch" class="formdiv" method="get">
	<div class="header">Dispatch List</div>
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
					<td width="96"><input  class="exportbtn"
					type="button" value=" " /></td>
				<td width="133"><div align="center">Dispatch Number</div></td>
				<td width="96"><form:input path="dispatchMasterDTO.dispatchNumber" size="16" ></form:input>
				</td>
				<td width="82"><div align="center">From Date</div></td>
          <td width="64"> <form:input path="dispatchMasterDTO.fromDate" class="fromDate"   size="16" /></td>
        
         <td width="82"><div align="center">To Date</div></td>
          <td width="64"> <form:input path="dispatchMasterDTO.toDate"  class="toDate" size="16" /></td>
				<td width="96"><div align="center">Item Name</div></td>
				<td><form:input path="dispatchMasterDTO.itemName" size="16" id="itemName" /></td>
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
				<td class="Dn"><div align="center">Dispatch No</div></td>
				<td class="Da"><div align="center">Date</div></td>
				<td class="Pn"><div align="center">Party Name</div></td>
				<td class="Tra"><div align="center">Transporter</div></td>
				<td class="Vn"><div align="center">Vehicle No</div></td>
				<td class="Dri"><div align="center">Driver Name</div></td>
				<td class="Ti"><div align="center">Total Item</div></td>
				<td class="Tp"><div align="center">Total Packet</div></td>
				<td class="Tw"><div align="center">Total Wt.</div></td>
				<td class="Ac"><div align="center">Action</div></td>
		</tr>
  </thead>
  <% int i=1;%>
  <tbody>
		<c:forEach items="${dispatchList}" var="disp">
					        <tr>		
					        <td style="text-align: center;" width="24">
			<c:url value="show_dispatch" var="view_url">
			<c:param name="dispatchAutoId" value="${disp.dispatchAutoId}"></c:param>
			<c:param name="dispatchNumber" value="${disp.dispatchNumber}"></c:param>
			<c:param name="operation" value="V"></c:param>
		  </c:url>
          <a href="${view_url}"><img src="static/images/view_icon.png" title="View Record" alt="" /></a></td>						        
					        <td  width="20">&nbsp;<%= i%></td>
							<td  width="67">&nbsp;<c:out value="${disp.dispatchNumber}" /></td>
							<td width="69">&nbsp;<fmt:formatDate pattern="dd-MMM-yyyy"  value="${disp.dispatchDate}" /></td>
							<td width="139">&nbsp;<c:out value="${disp.partyDTO.partyName}" /></td>
							<td width="86">&nbsp;<c:out value="" /></td>
							<td width="67">&nbsp;<c:out value="${disp.vehicleNo}" /></td>
							<td width="77">&nbsp;<c:out value="${disp.driverName}" /></td>
							<td width="52" style="text-align:right" >&nbsp;<c:out value="${disp.totalItem}" /></td>
							<td width="68" style="text-align:right" >&nbsp;
							 <fmt:formatNumber value="${disp.totalPacket}" type="number"  minFractionDigits="2" maxFractionDigits="2"/>
							</td>
							<td width="62" style="text-align:right" >&nbsp;<fmt:formatNumber value="${disp.totalWeight}" type="number"  minFractionDigits="2" maxFractionDigits="2"/>
							</td>
							
		<td width="62" style="">
		
		<c:url value="show_dispatch" var="remove_url">
		<c:param name="dispatchAutoId" value="${disp.dispatchAutoId}"></c:param>
		<c:param name="operation" value="Delete"></c:param>
		</c:url>
		<c:url value="show_dispatch" var="edit_url">
			<c:param name="dispatchAutoId" value="${disp.dispatchAutoId}"></c:param>
			<c:param name="dispatchNumber" value="${disp.dispatchNumber}"></c:param>
			<c:param name="operation" value="Edite"></c:param>
		</c:url>
		
		<c:forEach items="${roleAndRightsDTOList}" var="roleAndRights">
		<input type="hidden"  id="addFlag" value="${roleAndRights.addFlag}" >
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
			
									
			<%i++; %>
			
		</tr>
	</c:forEach>
  
  </tbody>
</table>
  </div>
  
 </form:form>

</html>
