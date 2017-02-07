<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>
<script type="text/javascript" charset="utf-8">
                                    
                $(document).ready(function() {
                    /* Initialise datatables */
                    var oTable = $('#example').dataTable({bInfo:""});
                    
                } );
            </script>

  <c:if test="${succ=='Blk'}">
  <script type="text/javascript">
  	var delUrl='show_branch_form';
  	$(document).ready(function() {
      alert('No Record Found !!!!');
      //window.self.location  = delUrl;
	});
 	</script>
</c:if>

 <c:if test="${branchForm.succ=='Ad'}">
  <script type="text/javascript">
  var delUrl='show_branch_form';
	$(document).ready(function() {
     alert('Record Inserted Successfully !!!!');
     window.self.location = delUrl;
	});
 	</script>
</c:if>

<c:if test="${branchForm.succ=='Dl'}">
  <script type="text/javascript">
  var delUrl='show_branch_form';
  $(document).ready(function() {
     alert('Record Deleted Successfully !!!!');
     window.self.location = delUrl;
	});
 	</script>
</c:if>

<c:if test="${branchForm.succ=='Up'}">
  <script type="text/javascript">
  var delUrl='show_branch_form';
	$(document).ready(function() {
     alert('Record Updated Successfully !!!!');
     window.self.location = delUrl;
	});
 	</script>
</c:if>

 <c:if test="${not empty(errors)}">
 <input type="hidden" id="errorId" value="${errors.errorMsg}">
	<script type="text/javascript">
		var delUrl='show_branch_form';
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
	
th{font-size:10px;}
 td{font-size:12px;}  
 
 	
	div.t_fixed_header_main_wrapper.ui div.t_fixed_header_caption {
		display:none;
		
		}
	.View	{	 width: 34px !important; border:none !important;		}
	.Bn {	 width: 92px !important; border:none !important;		}	 	
	.Ic{	 width: 83px !important;  border:none !important;	}	
	.C{	 width: 76px !important;  border:none !important;	}
	.P1{	 width: 75px !important;  border:none !important;	}
	.P2{	 width: 76px !important;  border:none !important;	}
	.Fx{	 width: 79px !important;  border:none !important;	}
	.Cr{	 width: 78px !important;  border:none !important;	}
	.Cl{	 width: 78px !important;  border:none !important;	}
	.En{	 width: 99px !important;  border:none !important;	}
	.Ac{	 width: 58px !important;  border:none !important;	}			
			
	 
.ui-widget-content {
overflow-x: hidden !important;
}	
</style>
	<form:form method="post" id="editForm" name="input" class="formdiv" action="get_branch_data" modelAttribute="searchCriteria">
		     
		     
    <div class="header"> Branch List </div> 
	<div class="headingdiv">
	  <table width="880" height="31" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td width="76"><a onclick="return checkAdd();" href="show_new_branch_form" class="addbtn" iconCls="icon-add"></a></td>
          <td width="38"><a href="#" class="exportbtn" iconCls="icon-redo" onClick="javascript:$('#dlg').dialog('close')"></a></td>
          <td width="95"><div align="center">  Branch Name</div></td>
          <td width="62">
      <form:input path="branchName" size="16" onkeyup="valid(this)" onblur="valid(this)" data-maxsize="35" id="branchName" /></td>
          <td width="83"><div align="center">Code</div></td>
          <td width="220"><form:input onkeyup="valid1(this)" onblur="valid1(this)" path="invoiceCode" data-maxsize="16" size="16" id="invoice" /></td>
          <td width="74"><input class="searchbtn"  type="submit" value=""/></td>
          <td width="74"><a href="show_branch_form"  class="cancelbtn"></a>
   </td>
        </tr>
      </table>
	</div>
 <div id="demo">
	<div class="gridheadingdiv">
	  <table width="972" class="display fixmyheader-8" id="example">
	
         <thead>
   <tr>
   <td class="View" width="55"><div align="center">View</div></td>
          <td class="Bn"><div align="center">Branch Name</div></td>
          <td  class="Ic"><div align="center"> Code</div></td>
          <td  class="C"><div align="center">            <div>City </div>          </div></td>
          <td  class="P1"><div align="center">Phone1 (O)</div>            </td>
          <td  class="P2"><div align="center">Phone2 (O)           </div></td>
          <td  class="Fx"><div align="center">Fax</div></td>
          <td  class="Cr"><div align="center"><div>Credit Days </div>   </div></td>
          <td  class="Cl"><div align="center"><div>Credit Limit</div>          </div></td>
          <td  class="En"><div align="center"> <div>Excise No</div></div></td>
          <td class="Ac"><div align="center">Action</div></td>
        </tr>
  </thead>
  <tbody>     <c:forEach items="${branchList}" var="branch">
			<tr>
	<td style="text-align: center;" width="24">
			<c:url value="get_branch" var="view_url">
			<c:param name="branchId" value="${branch.branchId}"></c:param>
			<c:param name="opr" value="V"></c:param>
		  </c:url>
          <a href="${view_url}"><img src="static/images/view_icon.png" title="View Record" alt="" /></a></td>
   
				<td style="width:82px">&nbsp;<c:out value="${branch.branch}"/></td>
				<td  style="width:73px">&nbsp;<c:out value="${branch.invoiceCode}"/></td>
				<td  style="width:66px">&nbsp;<c:out value="${branch.city}"/></td>
				<td  style="width:65px"">&nbsp;<c:out value="${branch.phone1}"/></td>
				<td style="width:66px">&nbsp;<c:out value="${branch.phone2}"/></td>
				<td  style="width:69px">&nbsp;<c:out value="${branch.fax}"/></td>
				<td style="text-align: right; width:68px;" align="right"   >&nbsp;<c:out value="${branch.creditDays}"/></td>
				<td   style="text-align: right; width:68px;" align="right" >&nbsp;<fmt:formatNumber value="${branch.creditLimit}" type="number" minFractionDigits="2" maxFractionDigits="2"/>&nbsp;
				</td>
				<td  style="width:89px">&nbsp;<c:out value="${branch.exciseECCNo}"/></td>
				
				<td   style=" width:58px" >
         			<c:url value="get_branch" var="remove_url">
					<c:param name="branchId" value="${branch.branchId}"></c:param>
					<c:param name="opr" value="R"></c:param>
					</c:url>
					<c:url value="get_branch" var="edit_url">
					<c:param name="branchId" value="${branch.branchId}"></c:param>
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
 