   <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page isELIgnored="false"%>
<%@ page session="true" %>
	<script type="text/javascript" charset="utf-8">
                                    
                $(document).ready(function() {
                    /* Initialise datatables */
                    var oTable = $('#example').dataTable();
                    
                } );
            </script>
<c:if test="${succ=='Blk'}">
  <script type="text/javascript">
  	var delUrl='get_leave_type_list';
  	$(document).ready(function() {
      alert('No Record Found !!!!');
      //window.self.location  = delUrl;
	});
 	</script>
</c:if>

 <c:if test="${leaveTypeMastForm.succ=='Ad'}">
  <script type="text/javascript">
  var delUrl='get_leave_type_list';
	$(document).ready(function() {
     alert('Record Inserted Successfully !!!!');
     window.self.location = delUrl;
	});
 	</script>
</c:if>

<c:if test="${leaveTypeMastForm.succ=='Dl'}">
  <script type="text/javascript">
  var delUrl='get_leave_type_list';
  $(document).ready(function() {
     alert('Record Deleted Successfully !!!!');
     window.self.location = delUrl;
	});
 	</script>
</c:if>

<c:if test="${leaveTypeMastForm.succ=='Up'}">
  <script type="text/javascript">
  var delUrl='get_leave_type_list';
	$(document).ready(function() {
     alert('Record Updated Successfully !!!!');
     window.self.location = delUrl;
	});
 	</script>
</c:if>

 <c:if test="${not empty(errors)}">
 <input type="hidden" id="errorId" value="${errors.errorMsg}">
	<script type="text/javascript">
		var delUrl='get_leave_type_list';
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
  		.View	{	 width: 34px !important; border:none !important;		}
     .Ac {width:42px !important;}
      .Leave {width:77px !important;}
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
		.Leave{width:63px !important; border:none !important}
.code{width:63px !important; border:none !important}     
.lt{width:50px !important; border:none !important}     

.days{width:50px !important; border:none !important}     
.desc{width:135px !important; border:none !important}     
.Ac{width:31px !important; border:none !important}     
	
</style>

<form:form name="input" action="get_leave_type_list" class="formdiv"
	method="post" modelAttribute="leaveTypeMastSearchCriteriaDTO">

	<div class="header"> Leave Type List</div>
	<div class="headingdiv">
		<table width="880" height="31" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				<td width="89"><a onclick="return checkAdd();" href="show_new_leave_type_form"	class="addbtn" iconCls="icon-add"> </a>
				</td>
				<td width="66"><input class="exportbtn"
					
					type="button" value="" /></td>
				<td width="40"><div align="center">Leave</div>
				</td>
				<td width="62"><form:input  onkeypress="return check(event)"   data-maxsize="65" path="leaveName" size="16"
						id="leaveName" />
				</td>
				<td width="75"><div align="center">Code</div>
				</td>
				<td width="63"><form:input onkeyup="valid1(this)" onblur="valid1(this)" data-maxsize="16" path="leaveCode" size="16"
						id="leaveCode" />
				</td>
				<td width="83"><div align="center">Leave Type</div>
				</td>
				<td width="160"><form:input  onkeypress="return check(event)"  path="leaveType" size="16"
						id="leaveType" />
				</td>
				<td width="81"><input class="searchbtn"
					
					type="submit" value="" />
				</td>
				<td width="88"><a href="get_leave_type_list"
					class="cancelbtn" iconCls="icon-cancel"></a>
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
             	<td class="Leave"><div align="center">Leave</div>				</td>
				<td class="code" width="63"><div align="center">Code</div>				</td>
				<td class="lt" width="50"><div align="center">Leave Type</div>				</td>
				<td class="days" width="50"><div align="center">Days</div>				</td>				
				<td class="desc" width="137"><div align="center">Description</div>			  </td>
				<td class="Ac"><div align="center">Action</div></td>		</tr>
  </thead>
  <tbody>
			<c:forEach items="${leaveTypeList}" var="leaveTypeVar">
			 
				<tr>
				   <td style="text-align: center;" width="24">
			<c:url value="get_leaveType" var="view_url">
			<c:param name="leaveId" value="${leaveTypeVar.leaveId}"></c:param>
			<c:param name="opr" value="V"></c:param>
		  </c:url>
          <a href="${view_url}"><img src="static/images/view_icon.png" title="View Record" alt="" /></a></td>
				 
					<td width="53">&nbsp;
					  <c:out value="${leaveTypeVar.leaveName}" />
				  </td>
					<td width="53">&nbsp;
					  <c:out value="${leaveTypeVar.leaveCode}" />
				  </td>
					<td width="40">&nbsp;
					  <c:out value="${leaveTypeVar.leaveType}" />
				  </td>
					<td width="40" align="right" style="text-align: right;" >&nbsp;
					  <c:out value="${leaveTypeVar.applicableDays}" />
				  </td>
					
					<td width="127">&nbsp;
					  <c:out value="${leaveTypeVar.description}" />
				  </td>
					
					
				  <td width="26" style=" " ><c:url value="get_leaveType" var="edit_url">
							<c:param name="leaveId" value="${leaveTypeVar.leaveId}"></c:param>
							<c:param name="opr" value="E"></c:param>
						</c:url> <c:url value="get_leaveType" var="remove_url">
							<c:param name="leaveId" value="${leaveTypeVar.leaveId}"></c:param>
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




 
