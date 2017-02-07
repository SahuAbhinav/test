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
    });
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
		
		 $("#datepicker" ).datepicker({dateFormat : 'dd-M-yy',changeMonth: true,
	          changeYear: true, yearRange: '-99:+0'});
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
	.View{width:24px !important; border:none!important}
	.An{width:240px !important; border:none!important}
	.code{width:30px !important; border:none!important}
	.region{width:50px !important; border:none!important}
	.desp{width:30px !important; border:none!important}
	.Ac{width:30px !important; border:none!important}			
	
th{font-size:10px;}
 td{font-size:12px;}  
  	
	div.t_fixed_header_main_wrapper.ui div.t_fixed_header_caption {
		display:none;		
		}
</style>

    
  
<form:form name="input" action="searchSalaryNote" class="formdiv" method="post" modelAttribute="salaryNoteForm" >
		     
    <div class="header"> Salary Note List</div> 
	<div class="headingdiv">
	  <table width="880" height="31" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td width="45"><a onclick="return checkAdd();" href="newSalaryNote"	class="addbtn" iconCls="icon-add"> </a></td>
          <td width="45"><input class="exportbtn"  type="button" value=""/> </td>
            <td width="80"><div align="center">Note Date</div></td>
         	 <td width="62"><form:input path="nDate" id="datepicker" readonly="true" data-maxsize="65"  size="6" /></td>  
          	<td width="45">Status</td>
			<td width="45"><div style="border: solid 1px; height: 20px; width: 155px; border-radius: 3px 3px 3px 3px; border-color: #7f9db9; background-color: #FFF;">
				 <span style="float: left;margin-top:2px; padding-left: 12px;"> Active</span>
				 <form:radiobutton path="nStatus" value="1" class="validate[required] radio"
					style="width: 10px;  margin:2px 9px; float: left;" id="activeStatus" />
				<span style="float: left;margin-top: 2px;"> InActive</span>
			 <form:radiobutton path="nStatus" value="0" class="validate[required] radio"
						style="width: 10px; margin: 2px 9px;" id="activeStatus"/>
			</div>
			</td>
          <td width="45"><input class="searchbtn"  type="submit" value=""/></td>
          <td width="45"><a href="salaryNoteList" class="cancelbtn" iconCls="icon-cancel" > </a></td>
        </tr>
      </table>
	</div>
<div id="demo">
	<div class="gridheadingdiv">
	  <table width="972" class="display fixmyheader-8" id="example">
 
  <thead>
   <tr>
	      <td class="View" width="20"><div align="center">View</div></td>
          <td class="An" width="300"><div align="center">Note</div></td>
          <td class="code" width="30"><div align="center">Assign To</div></td>
          <td class="region" width="50"><div align="center">Note Date</div></td>
          <td class="desp" width="30"><div align="center">Status</div></td>
          <td class="Ac" width="30"><div align="center">Action</div></td>
        </tr>
    
     </thead>
  <tbody>  
        <c:forEach items="${salaryNoteList}" var="note">			
        <tr>
         <td style="text-align: center;" width="20">
			<c:url value="getSalaryNote" var="view_url">
			<c:param name="noteId" value="${note.noteId}"></c:param>
			 <c:param name="opr" value="V"></c:param> 
		  </c:url>
          <a href="${view_url}"><img src="static/images/view_icon.png" title="View Record" alt="" /></a></td>
            
          <td width="300">&nbsp;<c:out value="${note.salaryNote}" /></td>
          <td width="30">&nbsp;<c:out value="${note.assignToEmp}" /></td>
          <td width="50">&nbsp;<fmt:formatDate pattern="dd-MMM-yyyy"  value="${note.noteDate}"/></td>
          
          
          <c:if test="${note.status eq 0}">
          <td width="30">&nbsp; InActive </td>
          </c:if> 
          <c:if test="${note.status eq 1}">
           <td width="30">&nbsp; Active </td>
          </c:if>   
         
          <td width="30" style="" >
          <c:url value="getSalaryNote" var="edit_url">
					<c:param name="noteId" value="${note.noteId}"></c:param>
					<c:param name="opr" value="E"></c:param>
		  </c:url>
		  <c:url value="getSalaryNote" var="remove_url">
					<c:param name="noteId" value="${note.noteId}"></c:param>
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

