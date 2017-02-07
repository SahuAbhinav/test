<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>
	<script type="text/javascript" charset="utf-8">
                $(document).ready(function() {
                    /* Initialise datatables */
                    var oTable = $('#example').dataTable({bInfo:""});
                    
                } );
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


<c:if test="${error.errorMsg!=null}">
 <input type="hidden" id="errorId" value="${error.errorMsg}">
 <script type="text/javascript">
 		var delUrl='show_Employee_list';
 		$(document).ready(function() {
 		var errorId=document.getElementById('errorId');
		alert(errorId.value);
 		//alert('Duplicate Data In Branch');
 		window.self.location  = delUrl;
		});
 	</script>
 </c:if>

<c:if test="${employeeForm.succ=='Up'}">
  <script type="text/javascript">
  var delUrl='show_Employee_list';
	$(document).ready(function() {
     alert('Record Updated Successfully !!!!');
     window.self.location = delUrl;
	});
 	</script>
</c:if>
<c:if test="${employeeForm.succ=='Ad'}">
  <script type="text/javascript">
  var delUrl='show_Employee_list';
	$(document).ready(function() {
     alert('Record Inserted Successfully !!!!');
     window.self.location = delUrl;
	});
 	</script>
</c:if>

<c:if test="${errors.errorMsg!=null}">
 <input type="hidden" id="errorId1" value="${errors.errorMsg}">
 <script type="text/javascript">
 		var delUrl='show_Employee_list';
 		$(document).ready(function() {
 		var errorId=document.getElementById('errorId1');
		alert(errorId.value);
 		
 		window.self.location  = delUrl;
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
   .View { width: 65px !important; border:none !important;	}
   .Ec{ width:101px !important; border:none !important } 
  .En{ width:136px !important;  border:none !important  }
   .Add{ width:155px !important;  border:none !important  }
    .Cy{ width:101px !important;  border:none !important  }
    .pr {width:85px !important;  border:none !important  }
    .mob {width:90px !important;  border:none !important  }
    .email {width:158px !important;  border:none !important }
     .Ac {width:64px !important;  border:none !important  }
	
</style>

<form:form name="input" action="get_employee_data" class="formdiv" method="post" modelAttribute="employeeForm" >
		     
    <div class="header"   > Employee  List </div> 
	<div class="headingdiv"   >
	  <table width="880" height="31" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td width="79">
          <a onclick="return checkAdd();" href="show_Employee_form" class="addbtn"	iconCls="icon-add"></a> 
          
          </td>
          <td width="66">
          <input class="exportbtn"  type="button" value=""/> </td>
          <td width="129">
          <div align="center">Employee Code</div></td>
          <td width="76">
      <form:input type="text" path="employeeCode" size="16" id="employeeCode" /></td>
          <td width="120"><div align="center">Employee Name</div></td>
          <td width="71"><form:input type="text" path="employeeName" size="16" id="employeeName" /></td>
          <td width="75"><div align="center">City</div></td>
          <td width="122"><form:input type="text" path="empliyeeCity" size="16" id="empliyeeCity" /></td>
          <td width="85"><input class="searchbtn"   type="submit" value=""/></td>
          <td width="102"><div class="cancelbtn">
		  <a href="show_Employee_list" class="cancelbtn"	iconCls="icon-cancel"></a>
		</div></td>
        </tr>
      </table>
 <div id="demo">
	<div class="gridheadingdiv">
	  <table width="972" class="display fixmyheader-8" id="example">
  <thead>

  
     
   <tr>
  			 <td class="View" width="55"><div align="center">View</div></td>
          <td class="Ec"><div align="center">Employee Code</div></td>
          <td class="En"><div align="center">Employee Name</div></td>
          <td class="Add"><div align="center">Address.</div></td>
          <td class="Cy" ><div align="center">City</div></td>
          <td class="pr" width="85"><div align="center">Phone (R)</div></td>
          <td  class="mob" width="90"><div align="center">Mobile</div></td>
          <td  class="email" width="158"><div align="center">Email Id</div></td>
          <td class="Ac"><div align="center">Action</div></td>
        </tr>
  </thead>
  <tbody>   
       <c:forEach items="${employeeList}" var="employee">
        <tr>
   			<td style="text-align: center;" width="55">
			<c:url value="get_employee" var="view_url">
			<c:param name="employeeId" value="${employee.employeeId}"></c:param>
			<c:param name="opr" value="V"></c:param>
		  </c:url>
          <a href="${view_url}"><img src="static/images/view_icon.png" title="View Record" alt="" /></a></td>
        
          <td width="91">&nbsp;<c:out value="${employee.employeeCode}"/></td>
          <td width="126">&nbsp;<c:out value="${employee.employeeFullName}"/></td>
          <td width="145">&nbsp;<c:out value="${employee.employeeAddress}"/></td>
          <td width="91">&nbsp;<c:out value="${employee.employeeCity.cityName}"/></td>
          <td width="75">&nbsp;<c:out value="${employee.phoneResi}"/></td>
          <td width="80"align="right">&nbsp;<c:out value="${employee.contactMobile}"/></td>
          <td width="148">&nbsp;<c:out value="${employee.emailId}"/></td>
          <td width="64" style="" > 
          
        <c:url value="get_employee" var="edit_url">
					<c:param name="employeeId" value="${employee.employeeId}"></c:param>
					<c:param name="opr" value="M"></c:param>
					</c:url> 
					
		<c:url value="get_employee" var="remove_url">
					<c:param name="employeeId" value="${employee.employeeId}"></c:param>
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
     
        </tr>
        </c:forEach>
  
  </tbody>
</table>
  </div>
  </div>
  </div>
 </form:form>
