<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
 	<script type="text/javascript" charset="utf-8">
                                    
                $(document).ready(function() {
                    /* Initialise datatables */
                    var oTable = $('#example').dataTable({bInfo:""});
                    
                } );
            </script>  
<%@ page isELIgnored="false" %>

<c:if test="${succ=='Blk'}">
  <script type="text/javascript">
  	var delUrl='show_company_form';
  	$(document).ready(function() {
      alert('No Record Found !!!!');
      //window.self.location  = delUrl;
	});
 	</script>
</c:if>

 <c:if test="${companyForm.succ=='Ad'}">
  <script type="text/javascript">
  var delUrl='show_company_form';
	$(document).ready(function() {
     alert('Record Inserted Successfully !!!!');
     window.self.location = delUrl;
	});
 	</script>
</c:if>

<c:if test="${companyForm.succ=='Dl'}">
  <script type="text/javascript">
  var delUrl='show_company_form';
  $(document).ready(function() {
     alert('Record Deleted Successfully !!!!');
     window.self.location = delUrl;
	});
 	</script>
</c:if>

<c:if test="${companyForm.succ=='Up'}">
  <script type="text/javascript">
  var delUrl='show_company_form';
	$(document).ready(function() {
     alert('Record Updated Successfully !!!!');
     window.self.location = delUrl;
	});
 	</script>
</c:if>

 <c:if test="${not empty(errorList)}">
 <input type="hidden" id="errorId" value="${errorList.errorMsg}">
	<script type="text/javascript">
		var delUrl='show_company_form';
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
   .View{width: 24px !important; border:none !important;}
   .Com{width:133px !important;}
   .Code{width:30px !important;}
   .City{width:65px !important;}
   .Phone{width:80px !important;}
   .fax{width:80px !important;}
   .Vat{width:60px !important;}
   .Vath{width:75px !important;}

   .Ie{width:52px !important;}
   .Exice{width:106px !important;}
   .Range{width:78px !important;}
   .Ac{width:66px !important;}		
   
</style>


  
<form:form method="post" id="editForm" name="input"   class="formdiv" action="get_company_data" modelAttribute="searchCriteria">
		     
    <div class="header"  > Company List </div> 
	<div class="headingdiv"  >
	  <table width="880"  height="31" border="0" cellpadding="0" cellspacing="0">
        <tr>        
          <td width="45"><a href="#" class="exportbtn" iconCls="icon-redo" onClick="javascript:$('#dlg').dialog('close')"></a></td>
          <td width="115"><div align="center">Name</div></td>
          <td width="64">
      <form:input  onkeypress="return check(event)"    path="name"  data-maxsize="55" size="16" id="name"/></td>
          <td width="40"><div align="center">code</div></td>
          <td width="40"><form:input   data-maxsize="16" path="code" size="16" id="code"/></td>
          <td width="57"><div align="center">City</div></td>
          <td width="150"><form:input  onkeypress="return check(event)"   data-maxsize="35" path="city" size="16" id="city"/></td>
          <td width="45"><input class="searchbtn"  type="submit" value=""/></td>
          <td width="45"><a href="show_company_form"   class="cancelbtn"></a>
    </td>
        </tr>
      </table>
	</div>
	<div id="demo">
	<div class="gridheadingdiv"  >
	 <table width="972" class="fixmyheader-8" id="example">
  <thead>
   <tr>
 
    <!--  <td widtd="178"><div align="center">View</div></td> -->
		  <th class="View" width="55"><div align="center">View</div></th>
          <th class="Com" width="178"><div align="center">Company</div></th>
          <th class="Code" width="64"><div align="center">Code</div></th>
          <th  class="City" width="76"><div align="center">City.</div></th>
          <th class="Phone" width="94"><div align="center">Phone(0)</div></th>
          <th class="fax" width="92"><div align="center">Fax</div></th>
          <th class="Vat" width="70"><div align="center">VAT No</div></th>
          <th class="Vath" width="87"><div align="center">VAT Date</div></th>
          <th class="Ie" width="60"><div align="center">IE Code</div></th>
          <th class="Exice"  width="82"><div align="center">Excise ECC No</div></th>
          <th class="Range" width="63"><div align="center">Rang</div></th>
          <th class="Ac" width="64"><div align="center">Action</div></th>
        </tr>
  </thead>
  <tbody>  
        
        <c:forEach items="${companyList}" var="company">
			<tr>
	
				
			<!-- 	<tr><td width="168"><img src="static/images/change_btn.png" class="button"	style="float: left; margin-left: 4px;" title="Edit Record"	alt="" /></td> -->
			<td style="text-align: center;" width="24">
			<c:url value="get_company" var="view_url">
			<c:param name="companyId" value="${company.companyId}"></c:param>
			<c:param name="opr" value="V"></c:param>
		  </c:url>
          <a href="${view_url}"><img src="static/images/view_icon.png" title="View Record" alt="" /></a></td>
				<td width="132">&nbsp;<c:out value="${company.companyName}"/></td>
				<td width="40">&nbsp;<c:out value="${company.companyCode}"/></td>
				<td width="60">&nbsp;<c:out value="${company.companyCity}"/></td>
				<td width="80">&nbsp;<c:out value="${company.phone1}"/></td>
				<td width="80">&nbsp;<c:out value="${company.fax}"/></td>
				<td width="60">&nbsp;<c:out value="${company.vatNo}"/></td>
				<td width="70">&nbsp;<fmt:formatDate pattern="dd-MMM-yyyy"
            value="${company.vatDt}" /></td>
				<td width="50">&nbsp;<c:out value="${company.importExportCode}"/></td>
				<td width="106">&nbsp;<c:out value="${company.exciseECCNo}"/></td>
				<td width="78">&nbsp;<c:out value="${company.rangeAdd}"/></td>
				<td width="67" style="text-align: center;" >
         			<c:url value="get_company" var="remove_url">
					<c:param name="companyId" value="${company.companyId}"></c:param>
					<c:param name="opr" value="R"></c:param>
					</c:url>
					<c:url value="get_company" var="edit_url">
					<c:param name="companyId" value="${company.companyId}"></c:param>
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
  </div>
   
 </form:form>

