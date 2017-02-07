<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="true" %>
<c:if test="${succ=='Blk'}">
  <script type="text/javascript">
   	$(document).ready(function() {
      alert('No Record Found !!!!');
   	});
 	</script>
</c:if>

 
<c:if test="${userMasterForm.succ=='Ad'}">
  <script type="text/javascript">
  var delUrl='show_user_master';
	$(document).ready(function() {
     alert('Record Inserted Successfully !!!!');
     window.self.location = delUrl;
	});
 	</script>
</c:if>


<c:if test="${userMasterForm.succ=='Dl'}">
  <script type="text/javascript">
  var delUrl='show_user_master';
  $(document).ready(function() {
     alert('Record Deleted Successfully !!!!');
     window.self.location = delUrl;
	});
 	</script>
</c:if>

<c:if test="${userMasterForm.succ=='Up'}">
  <script type="text/javascript">
  var delUrl='show_user_master';
	$(document).ready(function() {
     alert('Record Updated Successfully !!!!');
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
	
<script type="text/javascript" charset="utf-8">
                                    
                $(document).ready(function() {
                    /* Initialise datatables */
                    var oTable = $('#example').dataTable({bInfo:""});
                    
                } );
            </script>
 <script type="text/javascript">
      $(document).ready(function()
       {
        $(".datepicker" ).datepicker({dateFormat : 'dd-M-yy',maxDate: +0});
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
	
th{font-size:10px;}
 td{font-size:12px;}  
 
 	
	div.t_fixed_header_main_wrapper.ui div.t_fixed_header_caption {
		display:none;
		
		}
		.ui-widget-content {
overflow-x: hidden !important;
 
}
.View{width:55px !important; border:none !important;}
	.lid{width:304px !important; border:none !important;}
	.fn{width:316px !important; border:none !important;}
	.rn{width:210px !important; border:none !important;}
	.ac{width:57px !important; border:none !important;}

</style>


<%@ page isELIgnored="false" %>

   <form:form name="input" action="get_user_master_role_form" modelAttribute="userMasterForm" method="post" class="formdiv" >
		     
    <div class="header">User Master List </div> 
	<div class="headingdiv">
	  <table width="880" height="31" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td width="45"><a href="get_new_user_master_form"  class="addbtn" iconCls="icon-add"></a></td>
          <td width="45"><a onclick="return checkAdd();" href="#" class="exportbtn" iconCls="icon-redo" onClick="javascript:$('#dlg').dialog('close')"></a></td>
          <td width="100"><div align="center">Full Name</div></td>
          <td width="72"><form:input  path="userFullName" size="16" id="userFullName" /></td>
          <td width="66"><div align="center">Role Name</div></td>
          <td width="262"><form:input path="roleName" size="16" id="roleName" /></td>   
          <td width="45"><input  class="searchbtn"  type="submit" value=""/></td>
          <td width="45">
          <a href="show_user_master"  class="cancelbtn" iconCls="icon-cancel"></a> 
          </td>
          </tr>
      </table>
	</div>
	<div class="gridheadingdiv">
	 <table width="972" class="display fixmyheader-8" id="example">
			<thead>

				<tr>
	  			
				<td class="View" width="55"><div align="center">View</div></td>
          <td class="lid"  width="297"><div align="center"><div>Login Id</div></div></td>
          <td class="fn" width="306"><div align="center"><div>Full Name</div></div></td>
          <td class="rn" width="210"><div align="center">Role Name</div></td>      
          <td class="ac"  width="104"><div align="center">Action</div></td>
        </tr>
   
         
			</thead>
			<tbody>
  
        <c:forEach items="${userMasterList}" var="userMasterList">
			<tr>
			
			  <td style="text-align: center;" width="45">
			<c:url value="get_user_master_form" var="view_url">
			<c:param name="userId" value="${userMasterList.userId}"></c:param>
			<c:param name="opr" value="V"></c:param>
		  </c:url>
          <a href="${view_url}"><img src="static/images/view_icon.png" title="View Record" alt="" /></a></td>
	
				<td width="294">&nbsp;<c:out value="${userMasterList.userLoginId}"/></td>
				<td width="299">&nbsp;<c:out value="${userMasterList.userFullName}"/></td>
			    <td width="200">&nbsp;<c:out value="${userMasterList.roleMasterDTO.roleName}"/></td> 
							
				 <td width="56">
         			<c:url value="get_user_master_form" var="remove_url">
					<c:param name="userId" value="${userMasterList.userId}"></c:param>
					<c:param name="opr" value="R"></c:param>
					</c:url>
					<c:url value="get_user_master_form"  var="edit_url">
					<c:param name="userId" value="${userMasterList.userId}"></c:param>
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
</form:form>
 