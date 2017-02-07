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
  	var delUrl='get_finishedGoods_list';
  	$(document).ready(function() {
      alert('No Record Found !!!!');
     // window.self.location  = delUrl;
	});
 	</script>
</c:if>
 <c:if test="${not empty(notDelete)}">
  <script type="text/javascript">
  	$(document).ready(function() {
      alert('Warning : Can not delete because it is used in blanket');
	});
 	</script>
</c:if>


 <c:if test="${finishedGoodsMasterForms.succ=='Ad'}">
  <script type="text/javascript">
  var delUrl='get_finishedGoods_list';
	$(document).ready(function() {
     alert('Record Inserted Successfully !!!!');
     window.self.location = delUrl;
	});
 	</script>
</c:if>

<c:if test="${finishedGoodsMasterForms.succ=='Dl'}">
  <script type="text/javascript">
  var delUrl='get_finishedGoods_list';
  $(document).ready(function() {
     alert('Record Deleted Successfully !!!!');
     window.self.location = delUrl;
	});
 	</script>
</c:if>

<c:if test="${finishedGoodsMasterForms.succ=='Up'}">
  <script type="text/javascript">
  var delUrl='get_finishedGoods_list';
	$(document).ready(function() {
     alert('Record Updated Successfully !!!!');
     window.self.location = delUrl;
	});
 	</script>
</c:if>

 <c:if test="${not empty(errors)}">
 <input type="hidden" id="errorId" value="${errors.errorMsg}">
	<script type="text/javascript">
		var delUrl='get_finishedGoods_list';
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

<script type="text/javascript">
 

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
					height		:412,
					addTitles	: true,
					colratio	: ['10%', '10%', '8%', '50px', 'auto', 'auto', '30%', 'auto']
				});
			});
		</script>
  
     
<style type="text/css">
.ui-widget-content {
overflow-x: hidden !important;
 
}
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
   .sn{width:19px !important; border: none !important}
   .finished{width:79px !important; border: none !important}
   .date{width:67px !important; border: none !important}
   .noi{width:69px !important; border: none !important}
   .tq{width:69px !important; border: none !important}
   .Ac{width:27px !important; border: none !important}
 th{font-size:10px;}
 td{font-size:12px;}  
 	div.t_fixed_header_main_wrapper.ui div.t_fixed_header_caption {
		display:none;	
		}
</style>

  <script type="text/javascript">
$(document).ready(function(){ 
 $(".datepicker[readonly]").css("background-color","#ffffff" );
} );
</script>
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
   
  
<form:form name="input" action="get_finishedGoods_list" class="formdiv" modelAttribute="soSearchCriteria" >
		     
    <div class="header"> Finished Goods List</div> 
	<div class="headingdiv">
	  <table width="880" height="31" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td width="42"><a onclick="return checkAdd()"  onkeypress="return check(event)"  href="new_finishedGoods" class="addbtn"></a></td>
          <td width="81"><input class="exportbtn" style="font-size: 11px; font-weight: bold;  padding: 0 0 0 38px;" type="button" value=""/> </td>
          <td width="114"><div align="center">F. Good Number</div></td>
          <td width="64">
      <form:input path="finishedGoodsNumber" size="16" id="finishedGoodsNumber" /></td>
          <%-- <td width="82"><div align="center">Date</div></td>
          <td width="64"> <form:input path="finishGoodDate" readonly="true" class="datepicker" id="date"  size="16" /></td>
         --%>
        
         <td width="82"><div align="center">From Date</div></td>
          <td width="64"> <form:input path="fromDate" readonly="true" class="fromDate"   size="16" /></td>
        
         <td width="82"><div align="center">To Date</div></td>
          <td width="64"> <form:input path="toDate" readonly="true" class="toDate" size="16" /></td>
        
        
         <td width="90"><div align="center">Item Name</div></td>
		 <td><form:input path="itemName" size="16" id="itemName" /></td>
		
          <td width="70"><input class="searchbtn" style="font-size: 11px; font-weight: bold;  padding: 0 0 0 20px;" type="submit" name="opr" value="search"/></td>
          <td width="62">
          <a href="get_finishedGoods_list" class="cancelbtn"
			iconCls="icon-cancel" > </a>
          </td>
        </tr>
      </table>
	</div>
	<div id="demo">
	<div class="gridheadingdiv">
	  <table width="972" class="display fixmyheader-8" id="example">
  <thead>
   <tr>
   			<td class="View"><div align="center">View</div></td>
          <td class="sn"><div align="center">S No.</div></td>    
		  <td  class="finished" width="79"><div align="center">F. Good Number</div></td>      
          <td  class="date" width="67"><div align="center">Date</div></td>        
          
          <td  class="noi" width="69"><div align="center">No. of Items</div></td>
          <td  class="tq" width="69"><div align="center">Total Qty.</div></td>
          <td class="Ac"><div align="center">Action</div></td>
        </tr>
  </thead>
  <tbody>    
          <c:forEach items="${fgmList}" var="som" varStatus="s">
           <c:if test="${s.count%2==0}"> <tr></c:if>
        <c:if test="${s.count%2!=0}"> <tr></c:if>
   		
   		  <td style="text-align: center;" width="24">
			<c:url value="get_finishedGoods" var="view_url">
			<c:param name="id" value="${som.finishedGoodsAutoId}"></c:param>
			<c:param name="approvalFlag" value="${som.approvalFlag}"></c:param>
			<c:param name="opr" value="V"></c:param>
		  </c:url>
          <a href="${view_url}"><img src="static/images/view_icon.png" title="View Record" alt="" /></a></td>		
        
          <td width="9">&nbsp;<c:out value="${s.count}" />
          </td>
          <td width="69">&nbsp;<c:out value="${som.finishedGoodsNumber}" /></td>
          <td width="57">&nbsp;<fmt:formatDate pattern="dd-MMM-yyyy" value="${som.finishGoodDate}" />
          </td>
          <td width="59" style="text-align: right;" align="right">&nbsp;${som.finishedGoodsDetailDTOList.size()}</td>
          <td width="59" style="text-align: right;" align="right">&nbsp;${som.totalQuantity}</td>
          <td width="21" style="text-align: left;" >
          <c:url value="get_finishedGoods" var="remove_url">
					<c:param name="id" value="${som.finishedGoodsAutoId}"></c:param>
					<c:param name="finishedGoodsNumber" value="${som.finishedGoodsNumber}"></c:param>
					<c:param name="opr" value="R"></c:param>
					</c:url>
					<c:url value="get_finishedGoods" var="edit_url">
					<c:param name="id" value="${som.finishedGoodsAutoId}"></c:param>
					<c:param name="opr" value="E"></c:param>
					</c:url>

		<c:forEach items="${roleAndRightsDTOList}" var="roleAndRights">
		   <c:if test="${roleAndRights.menuId==sessionScope.menuId}">
		    <input type="hidden"  id="addFlag" value="${roleAndRights.addFlag}" >
		  	 <c:if test="${roleAndRights.editFlag=='true'}">
		  	 <c:choose>
		  	 <c:when test="${som.approvalFlag=='1'}">
		  	   <img	onclick="checkApproved();" src="static/images/change_btn.png" title="Edit Record" alt="" />
		  	 </c:when><c:otherwise>
		      <a id="editUrlId" href="${edit_url}"><img	 src="static/images/change_btn.png" title="Edit Record" alt="" /></a>
		      </c:otherwise></c:choose></c:if>
		      <c:if test="${roleAndRights.editFlag=='false'}">
		         <c:choose> <c:when test="${som.approvalFlag=='1'}">
		  	   <img	onclick="checkApproved();" src="static/images/change_btn.png" title="Edit Record" alt="" />
		  	 </c:when><c:otherwise>
		      <img	onclick="checkEdit();" src="static/images/change_btn.png" title="Edit Record" alt="" />
		     </c:otherwise></c:choose>
		      </c:if>
		      <c:if test="${roleAndRights.deleteFlag=='true'}">
		      
		      <c:choose> <c:when test="${som.approvalFlag=='1'}">
		  	   <img	onclick="checkApproved();" src="static/images/drop.png" title="Edit Record" alt="" />
		  	 </c:when><c:otherwise>
		      <a href="${remove_url}"><img src="static/images/drop.png" title="Delete Record"	alt="" /></a>
         	 </c:otherwise></c:choose>
         	  </c:if>
         	  <c:if test="${roleAndRights.deleteFlag=='false'}">
         	     <c:choose> <c:when test="${som.approvalFlag=='1'}">
		  	   <img	onclick="checkApproved();" src="static/images/drop.png" title="Edit Record" alt="" />
		  	 </c:when><c:otherwise>
		      <img onclick="checkDelete();" src="static/images/drop.png" title="Delete Record"	alt="" />
         	  </c:otherwise></c:choose></c:if>
          </c:if>
          </c:forEach>	
         </td>
        	</tr>
				</c:forEach>
  
  </tbody>
</table>
  </div>
  </div>
  <div style="float: right;"><c:url value="get_finishedGoods_list" var="remove_url">
<c:param name="next" value="${finishedGoodsMasterForms.next+(15)}"></c:param>
</c:url> <a href="${remove_url}" class="nextbtn" ></a>
</div>
<div style="float: right;">
<c:url value="get_finishedGoods_list" var="remove_url">
<c:param name="next" value="${finishedGoodsMasterForms.previous-(15)}"></c:param>
</c:url> <a href="${remove_url}" class="previousbtn" ></a>
</div>
 </form:form>