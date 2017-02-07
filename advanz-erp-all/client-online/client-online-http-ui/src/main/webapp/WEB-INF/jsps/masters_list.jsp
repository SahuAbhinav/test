<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page isELIgnored="false"%>
<script type="text/javascript" charset="utf-8">
                                    
                $(document).ready(function() {
                    /* Initialise datatables */
                    var oTable = $('#example').dataTable({bInfo:""});
                    
                } );
            </script>
        
 <c:if test="${succ=='Blk'}">
  <script type="text/javascript">
  	var delUrl='get_masters_list';
  	$(document).ready(function() {
      alert('No Record Found !!!!');
     // window.self.location  = delUrl;
	});
 	</script>
</c:if>

 <c:if test="${mastersForm.succ=='Ad'}">
  <script type="text/javascript">
  var delUrl='get_masters_list';
	$(document).ready(function() {
     alert('Record Inserted Successfully !!!!');
     window.self.location = delUrl;
	});
 	</script>
</c:if>

<c:if test="${mastersForm.succ=='Dl'}">
  <script type="text/javascript">
  var delUrl='get_masters_list';
  $(document).ready(function() {
     alert('Record Deleted Successfully !!!!');
     window.self.location = delUrl;
	});
 	</script>
</c:if>

<c:if test="${mastersForm.succ=='Up'}">
  <script type="text/javascript">
  var delUrl='get_masters_list';
	$(document).ready(function() {
     alert('Record Updated Successfully !!!!');
     window.self.location = delUrl;
	});
 	</script>
</c:if>

 <c:if test="${not empty(errors)}">
 <input type="hidden" id="errorId" value="${errors.errorMsg}">
	<script type="text/javascript">
		var delUrl='get_masters_list';
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
					height		:450,
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
	.View{width:34px !important; border:none!important}
  .fn{width:120px !important; }
  .name{width:120px !important; }
  .code{width:74px !important; }
  .print{width:95px !important; }
  .emp{width:107px !important; }
  .desp{width:219px !important; }
   .ac{width:59px !important; }
	
</style>



  
<form:form name="input" action="get_masters_list" class="formdiv" method="post" modelAttribute="mastersSearchCriteria">
		     
    <div class="header">Masters List</div> 
	<div class="headingdiv">
	  <table width="880" height="31" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td width="89"><a onclick="return checkAdd();"  href="show_new_masters_form" class="addbtn" iconCls="icon-add"></a> </td>
          <td width="60"><input class="exportbtn"  type="button" value=""/> </td>
          <td width="143"><div align="center">Masters Form</div></td>
          <td width="91">
      <form:input path="formName"  onkeypress="return check(event)"  data-maxsize="35" size="16" id="masterForm" /></td>
          <td width="61"><div align="center">Name</div></td>
          <td width="64"><form:input  onkeypress="return check(event)"  data-maxsize="35" path="name" size="16" id="name" /></td>
          <td width="81"><div align="center">Code</div></td>
          <td width="160"><form:input onkeyup="valid1(this)" onblur="valid1(this)" data-maxsize="16" path="code" size="16" id="code" /></td>
          <td width="84"><input class="searchbtn" style="font-size: 11px; font-weight: bold;  padding: 0 0 0 20px;" type="submit" value=""/></td>
          <td width="45">
           <a href="get_masters_list" class="cancelbtn" 
			></a></td>
        </tr>
      </table>
	</div>
 
	<div class="gridheadingdiv">
		   <table width="972" class="display fixmyheader-8" id="example">
  <thead>
   <tr>
	      <td class="View"><div align="center">View</div></td>
          <td style="border: none;" class="fn" width="120"><div align="center">Form Name</div></td>
          <td style="border: none;" class="name" width="120"><div align="center">Name</div></td>
          <td style="border: none;" class="code"   width="74"><div align="center">Code</div></td>
          <td style="border: none;" class="print" width="95"><div align="center">Print Sequence</div></td>
          <td style="border: none;" class="emp" width="107"><div align="center">Employee Type</div></td>
          <td style="border: none;" class="desp" width="219"><div align="center">Description</div></td>
          <td style="border: none;" class="ac" width="67" ><div align="center">Action</div></td>
     </tr>
  </thead>
  <tbody>
         
        <c:forEach items="${mastersList}" var="ma" >
        <tr>
        <td style="text-align: center;" width="55">
			<c:url value="get_masters" var="view_url">
				<c:param name="mid" value="${ma.mastersId}"></c:param>
					<c:param name="fid" value="${ma.formId}"></c:param>
					<c:param name="opr" value="V"></c:param>
					<c:param name="tv" value="tabs-${ma.formId }"></c:param>
		  </c:url>
          <a href="${view_url}"><img src="static/images/view_icon.png" title="View Record" alt="" /></a></td>
         <td width="110">&nbsp;<c:out value="${ma.formName}" /></td>
          <td width="110">&nbsp;<c:out value="${ma.name}" /></td>
          <td width="64">&nbsp;<c:out value="${ma.code}" /></td>
          <td width="85">&nbsp;<c:out value="${ma.gradePrintSeqNo}" /></td>
          <td width="97">&nbsp;<c:out value="${empTypes[ma.empTypeId]}" /></td>
          <td width="209">&nbsp;<c:out value="${ma.description}" /></td>
          <td width="58" >
          <c:url value="get_masters" var="remove_url">
					<c:param name="mid" value="${ma.mastersId}"></c:param>
					<c:param name="fid" value="${ma.formId}"></c:param>
					<c:param name="opr" value="R"></c:param>
					<c:param name="tv" value="tabs-${ma.formId }"></c:param>
		  </c:url>
			
		<c:set var="ru" value="${remove_url}#tabs-${ma.formId}" />	
		
			
					<c:url value="get_masters" var="edit_url">
					<c:param name="mid" value="${ma.mastersId}"></c:param>
					<c:param name="fid" value="${ma.formId}"></c:param>
					<c:param name="opr" value="E"></c:param>
					<c:param name="tv" value="tabs-${ma.formId }"></c:param>
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
  
 </form:form>

    
  