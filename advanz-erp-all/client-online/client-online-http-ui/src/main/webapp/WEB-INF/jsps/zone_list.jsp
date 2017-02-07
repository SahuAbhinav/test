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



<c:if test="${succ=='Blk'}">
  <script type="text/javascript">
  	var delUrl='get_zone_list';
  	$(document).ready(function() {
      alert('No Record Found !!!!');
    //  window.self.location  = delUrl;
	});
 	</script>
</c:if>

 
<c:if test="${zoneForm.succ=='Ad'}">
  <script type="text/javascript">
  var delUrl='get_zone_list';
	$(document).ready(function() {
     alert('Record Inserted Successfully !!!!');
     window.self.location = delUrl;
	});
 	</script>
</c:if>


<c:if test="${succ=='Dl'}">
  <script type="text/javascript">
  var delUrl='get_zone_list';
  $(document).ready(function() {
     alert('Record Deleted Successfully !!!!');
     window.self.location = delUrl;
	});
 	</script>
</c:if>

<c:if test="${zoneForm.succ=='Up'}">
  <script type="text/javascript">
  var delUrl='get_zone_list';
	$(document).ready(function() {
     alert('Record Updated Successfully !!!!');
     window.self.location = delUrl;
	});
 	</script>
</c:if>

 <c:if test="${not empty(errors)}">
 <input type="hidden" id="errorId" value="${errors.errorMsg}">
	<script type="text/javascript">
		var delUrl='get_zone_list';
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
 .Ac {width: 55px !important; }
 	
	div.t_fixed_header_main_wrapper.ui div.t_fixed_header_caption {
		display:none;
		
		}
	.zn{width:163px !important; border:none !important;}
  .zc{width:158px !important; border:none !important;}
  .con{width:158px !important; border:none !important;}
  .desp{width:227px !important; border:none !important;}
  .ac{width:63px !important; border:none !important;} 		 
	 	.View	{	 width: 34px !important; border:none !important;		}
</style>

<form:form name="input" action="get_zone_list" class="formdiv" method="post" modelAttribute="zoneSearchCriteria">

	<div class="header">Zone List</div>
	<div class="headingdiv">
		<table width="880" height="31" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				<td width="45">
				<a onclick="return checkAdd();" href="add_zone_form" class="addbtn"	iconCls="icon-add"></a>				</td>
				 <td width="45"><input 	class="exportbtn"	type="" value="" /> </td>
				<td width="164"><div align="center">&nbsp;&nbsp;&nbsp;Zone Name</div>
			  </td>
				<td width="70">
				<form:input onkeyup="valid(this)" onblur="valid(this)" data-maxsize="65" path="zoneName" size="16" id="zoneName" />
				<!--<input type="text" name="zoneDTO.zoneName"
					size="10" id="zoneName" />-->
			  </td>
				<td width="71"><div align="center">Code</div>
			  </td>
				<td width="88">
				<form:input onkeyup="valid1(this)" onblur="valid1(this)" data-maxsize="16" path="zoneCode" size="16" id="zoneCode" />
				<!--<input type="text" name="zoneDTO.zoneCode" size="10"
					id="zoneCode" />-->
			  </td>
				<td width="87"><div align="center">Country</div>
			  </td>
				<td width="145">
				<form:input onkeyup="valid(this)" onblur="valid(this)" data-maxsize="35" path="countryName" size="16" id="countryName" />
				<!--<input type="text" name="zoneDTO.zoneCode" size="10"
					id="zoneCode" />-->
			  </td>
				
				<td width="45"><input class="searchbtn"
					style="font-size: 11px; font-weight: bold; padding: 0 0 0 20px;"
					type="submit" value="" />
			  </td>
				<td width="45"><a href="get_zone_list"  class="cancelbtn" ></a>
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
     			<td class="zn" width="163"><div align="center">Zone Name</div>				</td>
				<td class="zc" width="158"><div align="center">Zone Code</div>				</td>
				<td class="con" width="158"><div align="center">Country</div>				</td>
				<td class="desp" width="227"><div align="center">Description</div>				</td>
				<td  class="ac"><div align="center">Action</div>				</td>
				</tr>
  </thead>
  <tbody>			<c:forEach items="${zoneList}" var="cat">
			<tr>
			<td style="text-align: center;" width="24">
			<c:url value="get_zone" var="view_url">
			<c:param name="zoneId" value="${cat.zoneId}"></c:param>
			<c:param name="opr" value="V"></c:param>
		  </c:url>
          <a href="${view_url}"><img src="static/images/view_icon.png" title="View Record" alt="" /></a></td>
			
				<td width="161">&nbsp;
			  <c:out value="${cat.zoneName}"/></td>
				<td width="155">&nbsp;
			  <c:out value="${cat.zoneCode}"/></td>
				<td width="155">&nbsp;
			  <c:out value="${cat.countryDTO.countryName}"/></td>
				<td width="228">&nbsp;
			  <c:out value="${cat.description}"/></td>
				<!--<td width="69"><a href="get_zone?zoneId=<c:out value="${cat.countryId}"/>"><img src="static/images/change_btn.png" style="float: left; margin-left: 10px;" title="Edit Record" alt="" />
				</a>
				<a href="remove_zone?zoneId=<c:out value="${cat.zoneId}"/>"><img src="static/images/drop.png"
						style="float: right; margin-right: 12px;" title="Delete Record"
						alt="" />
				</a>				</td>-->
				<td width="66" style="text-align: center;" >					
					<c:url value="get_zone" var="edit_url">
					<c:param name="zoneId" value="${cat.zoneId}"></c:param>
					<c:param name="opr" value="E"></c:param>
					</c:url>
					<c:url value="get_zone" var="remove_url">
					<c:param name="zoneId" value="${cat.zoneId}"></c:param>
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
          </c:forEach>					</td>
	</tr>
				</c:forEach>
  
  </tbody>
</table>
  </div>
  </div>
 </form:form>