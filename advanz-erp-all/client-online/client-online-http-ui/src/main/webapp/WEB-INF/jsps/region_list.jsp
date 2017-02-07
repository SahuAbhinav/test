<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="true" %>
<%@ page isELIgnored="false"%>
<script type="text/javascript" charset="utf-8">
                                    
                $(document).ready(function() {
                    /* Initialise datatables */
                    var oTable = $('#example').dataTable({bInfo:""});
                    
                } );
            </script>
<c:if test="${succ=='Blk'}">
  <script type="text/javascript">
  	var delUrl='get_region_list';
  	$(document).ready(function() {
      alert('No Record Found !!!!');
     // window.self.location  = delUrl;
	});
 	</script>
</c:if>

 
<c:if test="${regionForm.succ=='Ad'}">
  <script type="text/javascript">
  var delUrl='get_region_list';
	$(document).ready(function() {
     alert('Record Inserted Successfully !!!!');
     window.self.location = delUrl;
	});
 	</script>
</c:if>


<c:if test="${regionForm.succ=='Dl'}">
  <script type="text/javascript">
  var delUrl='get_region_list';
  $(document).ready(function() {
     alert('Record Deleted Successfully !!!!');
     window.self.location = delUrl;
	});
 	</script>
</c:if>

<c:if test="${regionForm.succ=='Up'}">
  <script type="text/javascript">
  var delUrl='get_region_list';
	$(document).ready(function() {
     alert('Record Updated Successfully !!!!');
     window.self.location = delUrl;
	});
 	</script>
</c:if>

 <c:if test="${not empty(errorList)}">
 <input type="hidden" id="errorId" value="${errorList.errorMsg}">
	<script type="text/javascript">
		var delUrl='get_region_list';
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
	.ui-widget-content {
overflow-x: hidden !important;
 
}
th{font-size:10px;}
 td{font-size:12px;}  
  .View	{	 width: 34px !important; border:none !important;		}
 	   .Ac {width:51px !important;}
	div.t_fixed_header_main_wrapper.ui div.t_fixed_header_caption {
		display:none;
		
		}
	 .rn{width:230px !important; border:none !important }
   .rc{width:154px !important; border:none !important }
  .static{width:154px !important; border:none !important }
  .desp{width:195px !important; border:none !important }
  .ac{width:62px !important; border:none !important } 
</style>

  

<form:form name="input" action="get_region_list" class="formdiv" method="post" modelAttribute="regionSearchCriteria">

	<div class="header">Region List</div>
	<div class="headingdiv">
		<table width="880" height="31" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				<td width="45"><a onclick="return checkAdd();"  href="show_new_region_form"	class="addbtn" iconCls="icon-add"></a>				</td>
				<td width="45"><input class="exportbtn"	type="button" value="" /> </td>
				<td width="141"><div align="center">&nbsp;Region Name</div>
			  </td>
				<td width="90"><form:input  onkeypress="return check(event)"  data-maxsize="65" path="regionName" size="16" id="regionName" />
			  </td>
				<td width="64"><div align="center">Code</div>
			  </td>
				<td width="91"><form:input onkeyup="valid1(this)" onblur="valid1(this)" data-maxsize="16"  path="regionCode" size="16"	id="code" />
			  </td>
				<td width="61"><div align="center">State</div>
			  </td>
				<td width="91"><form:input  onkeypress="return check(event)"  data-maxsize="35"  path="stateName" size="16"	id="stateName" />
			  </td>
				<td width="45"><input class="searchbtn"
					
					type="submit" value="" />
			  </td>
				<td width="45"> <a  href="get_region_list"><input class="cancelbtn"
					
					type="button" value="" /> </a>
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
 
          <td class="rn" width="230"><div align="center">Region Name</div></td>
          <td class="rc" width="154"><div align="center">Region Code</div></td>
          <td class="static" width="154"><div align="center">State</div></td>
          <td class="desp" width="195"><div align="center">Description</div></td>
          <td class="ac"><div align="center">Action</div></td>
        </tr>
  </thead>
  <tbody>
        
			<c:forEach items="${regionList}" var="region">
				<tr >
				  <td style="text-align: center;" width="24">
			<c:url value="get_region" var="view_url">
			<c:param name="regionId" value="${region.regionId}"></c:param>
			<c:param name="opr" value="V"></c:param>
		  </c:url>
          <a href="${view_url}"><img src="static/images/view_icon.png" title="View Record" alt="" /></a></td>
  
					<td width="220">&nbsp;<c:out value="${region.regionName}" />
					</td>
					<td width="144">&nbsp;<c:out value="${region.regionCode}" />
					</td>
					<td width="144">&nbsp;<c:out
							value="${region.stateDTO.stateName}" />
					</td>
					<td width="185	">&nbsp;<c:out
							value="${region.description}" />
					</td>
					<td width="62" style="" >					
					<c:url value="get_region" var="edit_url">
					<c:param name="regionId" value="${region.regionId}"></c:param>
					<c:param name="opr" value="E"></c:param>
					</c:url>
					<c:url value="get_region" var="remove_url">
					<c:param name="regionId" value="${region.regionId}"></c:param>
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