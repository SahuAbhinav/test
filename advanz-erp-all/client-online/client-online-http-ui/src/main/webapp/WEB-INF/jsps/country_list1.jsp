<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="true" %>
<script type="text/javascript" charset="utf-8">
                                    
                $(document).ready(function() {
                    /* Initialise datatables */
                    var oTable = $('#example').dataTable({bInfo:""});
                    
                } );
            </script>
<%@ page isELIgnored="false" %>

 <c:if test="${succ=='Blk'}">
  <script type="text/javascript">
  	var delUrl='get_country_list';
  	$(document).ready(function() {
      alert('No Record Found !!!!');
      //window.self.location  = delUrl;
	});
 	</script>
</c:if>

 
<c:if test="${countryForm.succ=='Ad'}">
  <script type="text/javascript">
  var delUrl='get_country_list';
	$(document).ready(function() {
     alert('Record Inserted Successfully !!!!');
     window.self.location = delUrl;
	});
 	</script>
</c:if>


<c:if test="${succ=='Dl'}">
  <script type="text/javascript">
  var delUrl='get_country_list';
  $(document).ready(function() {
     alert('Record Deleted Successfully !!!!');
     window.self.location = delUrl;
	});
 	</script>
</c:if>

<c:if test="${countryForm.succ=='Up'}">
  <script type="text/javascript">
  var delUrl='get_country_list';
	$(document).ready(function() {
     alert('Record Updated Successfully !!!!');
     window.self.location = delUrl;
	});
 	</script>
</c:if>

 <c:if test="${not empty(errorList)}">
 <input type="hidden" id="errorId" value="${errorList.errorMsg}">
	<script type="text/javascript">
		var delUrl='get_country_list';
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
		.View	{	 width: 34px !important; border:none !important;		}
		.cn{width:212px !important; border: none !important}
   .cc{width:195px !important; border: none !important}
   .desc{width:305px !important; border: none !important}
.Ac{width:66px !important; border: none !important }

	
</style>
 
<form:form name="input" action="get_country_list" class="formdiv" method="post" modelAttribute="countrySearchCriteria">
	<div class="header">Country List  </div>
	<div class="headingdiv">
		<table width="880" height="31" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td width="66"><a onclick="return checkAdd();" href="show_country" class="addbtn" iconCls="icon-add"> </a></td>
          <td width="7"><input class="exportbtn"  type="button" value=""/> </td>
          <td width="78"><div align="center">Country Name</div></td>
          <td width="62">
      <form:input path="countryName" onkeypress="return check(event)" data-maxsize="35" size="16" id="countryName" /></td>
          <td width="75"><div align="center">Code</div></td>
          <td width="230"><form:input onkeyup="valid1(this)" onblur="valid1(this)" data-maxsize="16" path="countryCode" size="16" id="code" /></td>
          
          <td width="63"><input class="searchbtn" type="submit" value=""/></td>
          <td width="63"><a href="get_country_list" class="cancelbtn" iconCls="icon-cancel" ></a></td>
        </tr>
      </table>
	</div>
	<div id="demo">
	<div class="gridheadingdiv">
	  <table width="972" class="display fixmyheader-8" id="example">
  <thead>
   <tr>
      <td class="View" width="55"><div align="center">View</div></td>
   				<td class="cn" width="212"><div align="center">Country Name</div>
				</td>
				<td class="cc" width="195"><div align="center">Country Code</div>
				</td>
				<td class="desc" width="305"><div align="center">Description</div>
				</td>
				<td class="Ac"><div align="center">Action</div>
				</td>
			</tr>
		</thead>
  <tbody>
			<c:forEach items="${list}" var="cat">
			<tr>
		  <td style="text-align: center;" width="24">
			<c:url value="get_country" var="view_url">
			<c:param name="countryId" value="${cat.countryId}"></c:param>
			<c:param name="opr" value="V"></c:param>
		  </c:url>
          <a href="${view_url}"><img src="static/images/view_icon.png" title="View Record" alt="" /></a></td>
   
		 
				<td  width="202">&nbsp;<c:out value="${cat.countryName}"/></td>
				<td width="185">&nbsp;<c:out value="${cat.countryCode}"/></td>
				<td width="295">&nbsp;<c:out value="${cat.description}"/></td>
				 <td width="65" style="" >
          <c:url value="get_country" var="edit_url">
					<c:param name="countryId" value="${cat.countryId}"></c:param>
					<c:param name="opr" value="E"></c:param>
		  </c:url>
		  <c:url value="get_country" var="remove_url">
					<c:param name="countryId" value="${cat.countryId}"></c:param>
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
   



</form:form>