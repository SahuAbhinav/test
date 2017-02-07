<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:if test="${succ=='Blk'}">
  <script type="text/javascript">
  	var delUrl='role_list';
  	$(document).ready(function() {
      alert('No Record Found !!!!');
      //window.self.location  = delUrl;
	});
 	</script>
</c:if>

 <c:if test="${succ=='Ad'}">
  <script type="text/javascript">
  var delUrl='role_list';
	$(document).ready(function() {
     alert('Record Inserted Successfully !!!!');
     window.self.location = delUrl;
	});
 	</script>
</c:if>

<c:if test="${succ=='Dl'}">
  <script type="text/javascript">
  var delUrl='role_list';
  $(document).ready(function() {
     alert('Record Deleted Successfully !!!!');
     window.self.location = delUrl;
	});
 	</script>
</c:if>

<c:if test="${succ=='Up'}">
  <script type="text/javascript">
  var delUrl='role_list';
	$(document).ready(function() {
     alert('Record Updated Successfully !!!!');
     window.self.location = delUrl;
	});
 	</script>
</c:if>

 <c:if test="${errorList!=null}">
 
 <input type="hidden" id="errorId" value="${errorList.errorMsg}">
	<script type="text/javascript">
	var delUrl='role_list';
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
<script type="text/javascript" charset="utf-8">
                                    
                $(document).ready(function() {
                    /* Initialise datatables */
                    var oTable = $('#example').dataTable({bInfo:""});
                    
                } );
            </script>
<script type="text/javascript">
	$(document).ready(function() {

		
		$(".cancelbtn").click(function() {
				$("#roleName").val("");
				window.self.location = "role_list";
		});
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
.View{width:65px !important; border:none!important}
.rn{width:328px !important; border:none !important }
 .ac{width:30px !important; border:none !important }

</style>

<form name="input" action="role_list" class="formdiv" method="get"  modelAttribute="roleForm">
<div class="header"> Role List</div>
	<div class="headingdiv">
		<table width="880" height="31" border="0" cellpadding="0"
			cellspacing="0">	
			<tr>
				<td width="45"><a onclick="return checkAdd();"  href="role_add_form"	class="addbtn" iconCls="icon-add"> </a></td>
			<td width="45"><a class="exportbtn" onclick="javascript:$('#dlg').dialog('close')" iconcls="icon-redo" href="#"></a></td>
				<td width="90"><div align="center">Role Name</div></td>
				<td width="415"><input type="text" name="roleName" size="16" id="roleName" /></td>
				<td width="81"><input class="searchbtn" type="submit" value="" /></td>
				<td width="102"><div>
					<input class="cancelbtn" type="reset" value=" ">
					</div>
				</td>
			</tr>
		</table>
	</div>
	<div class="gridheadingdiv">
		  <table width="972" class="display fixmyheader-8" id="example">
		<thead>
				<tr>
				<td class="View" width="55"><div align="center">View</div></td>
				<td class="rn"><div style="padding-left: 12px;" align="left">Role Name</div></td>
			<td class="ac"><div align="center">Action</div></td>
		</tr>
			</thead>
			<tbody>
			 <c:forEach items="${roleList}" var="role">
			
		<tr>
			<td style="text-align: center;" width="55">
			<c:url value="get_role" var="view_url">
			<c:param name="roleId" value="${role.roleId}"></c:param>
			<c:param name="opr" value="V"></c:param>
		  </c:url>
          <a href="${view_url}"><img src="static/images/view_icon.png" title="View Record" alt="" /></a></td>
            
					<td width="318">${role.roleName}</td>
		<td width="24" style="text-align: center;" >
				<c:url value="get_role" var="edit_url">
				<c:param name="roleId" value="${role.roleId}"></c:param>
				<c:param name="opr" value="E"></c:param>
				</c:url>
		<c:url value="get_role" var="remove_url">
				<c:param name="roleId" value="${role.roleId}"></c:param>
				<c:param name="opr" value="R"></c:param>
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
          </c:forEach>
          </td>
		
		</tr>
		</c:forEach>
		</tbody>
		</table>
</div>



</form>