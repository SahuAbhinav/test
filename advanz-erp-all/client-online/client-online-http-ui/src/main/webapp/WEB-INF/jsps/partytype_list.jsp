<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
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
  	var delUrl='show_partytype_form';
  	$(document).ready(function() {
      alert('Record Not Found !!!!');
     // window.self.location  = delUrl;
	});
 	</script>
</c:if>

 <c:if test="${partyTypeForm.succ=='Ad'}">
  <script type="text/javascript">
  var delUrl='show_partytype_form';
	$(document).ready(function() {
     alert('Record Inserted Successfully !!!!');
     window.self.location = delUrl;
	});
 	</script>
</c:if>

<c:if test="${partyTypeForm.succ=='Dl'}">
  <script type="text/javascript">
  var delUrl='show_partytype_form';
  $(document).ready(function() {
     alert('Record Deleted Successfully !!!!');
     window.self.location = delUrl;
	});
 	</script>
</c:if>

<c:if test="${partyTypeForm.succ=='Up'}">
  <script type="text/javascript">
  var delUrl='show_partytype_form';
	$(document).ready(function() {
     alert('Record Updated Successfully !!!!');
     window.self.location = delUrl;
	});
 	</script>
</c:if>

 <c:if test="${not empty(errors)}">
 <input type="hidden" id="errorId" value="${errors.errorMsg}">
	<script type="text/javascript">
		var delUrl='show_partytype_form';
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
					height		:407,
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
 
th{font-size:10px;}
 td{font-size:12px;}  
 
 	
	div.t_fixed_header_main_wrapper.ui div.t_fixed_header_caption {
		display:none;
		
		}
	
  .pt{width:183px !important; border:none!important}
  .code{width:103px !important; border:none!important}
  .partytype{width:219px !important; border:none!important}
  .Ac{width:53px !important; border:none!important }
   .View	{	 width: 34px !important; border:none !important;		}	
  
</style>

 <form:form name="input" class="formdiv" action="get_partytype_data" modelAttribute="partyTypeDto">
  
    <div class="header"> Party Type List</div> 
	<div class="headingdiv">
	  <table width="880" height="31" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td width="54"><a onclick="return checkAdd();" href="show_new_partytype_form" class="addbtn" iconCls="icon-add"></a></td>
          <td width="68"><a href="#" class="exportbtn" iconCls="icon-redo" onClick="javascript:$('#dlg').dialog('close')"></a></td>
          <td width="88"><div align="center">Party Type</div></td>
          <td width="91">
      <form:input path="partyTypeDesc"  onkeypress="return check(event)"  size="16" id="leave" /></td>
          <td width="61"><div align="center">Code</div></td>
          <td width="239"><form:input path="partyTypeCode" onkeyup="valid1(this)" onblur="valid1(this)" data-maxsize="16"  size="16" id="code" /></td>
          <td width="71"><input class="searchbtn"  type="submit" value=""/></td>
          <td width="66"><a href="show_partytype_form" class="cancelbtn" 
			></a></td>
        </tr>
      </table>
	</div>
	<div id="demo">
	<div class="gridheadingdiv">
	  <table width="972" class="display fixmyheader-8" id="example">
  <thead>
   <tr> 
     <td class="View" width="55"><div align="center">View</div></td>
          <td class="pt" width="183"><div align="center">Party Type Name</div></td>
          <td class="code" width="103"><div align="center">Code</div></td>
          <td class="partytype" width="219"><div align="center">Party Type Flag</div></td>
          <td class="Ac"><div align="center">Action</div></td>
       </tr>
  </thead>
  <tbody>        
        <c:forEach items="${partyTypeList}" var="partyType">
			<tr>
			 		   <td style="text-align: center;" width="24">
			<c:url value="get_partytype" var="view_url">
			<c:param name="partyTypeId" value="${partyType.partyTypeId}"></c:param>
			<c:param name="opr" value="V"></c:param>
		  </c:url>
          <a href="${view_url}"><img src="static/images/view_icon.png" title="View Record" alt="" /></a></td>
	
			 
				<td width="173">&nbsp;<c:out value="${partyType.partyTypeDesc}"/></td>
				<td width="93">&nbsp;<c:out value="${partyType.partyTypeCode}"/></td>
				<td width="209">&nbsp;<c:out value="${partyType.partyTypeFlag}"/></td>				
				<td width="51"  >
         			<c:url value="get_partytype" var="remove_url">
					<c:param name="partyTypeId" value="${partyType.partyTypeId}"></c:param>
					<c:param name="opr" value="R"></c:param>
					</c:url>
					<c:url value="get_partytype" var="edit_url">
					<c:param name="partyTypeId" value="${partyType.partyTypeId}"></c:param>
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