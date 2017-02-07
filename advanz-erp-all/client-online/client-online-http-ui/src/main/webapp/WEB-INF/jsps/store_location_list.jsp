<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page session="true" %>

<script type="text/javascript" charset="utf-8">                               
                $(document).ready(function() {
                    /* Initialise datatables */
                    var oTable = $('#example').dataTable({bInfo:""});
                    
                } );
            </script>

<%@ page isELIgnored="false" %>
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
</script>

<c:if test="${succ=='Blk'}">
  <script type="text/javascript">
  	var delUrl='show_storelocation_form';
  	$(document).ready(function() {
      alert('No Record Found !!!!');
     // window.self.location = delUrl;
	});
 	</script>
</c:if>

 <c:if test="${storeLocationForm.succ=='Ad'}">
  <script type="text/javascript">
	$(document).ready(function() {
     alert('Record Inserted Successfully !!!!');
	});
 	</script>
</c:if>

<c:if test="${storeLocationForm.succ=='Dl'}">
  <script type="text/javascript">
  var delUrl='show_storelocation_form';
  $(document).ready(function() {
     alert('Record Deleted Successfully !!!!');
     window.self.location= delUrl;
	});
 	</script>
</c:if>

<c:if test="${storeLocationForm.succ=='Up'}">
  <script type="text/javascript">
	$(document).ready(function() {
     alert('Record Updated Successfully !!!!');
	});
 	</script>
</c:if>

   <c:if test="${not empty(errors)}">
    <input type="hidden" id="errorId" value="${errors.errorMsg}">
	<script type="text/javascript">
		var delUrl='show_storelocation_form';
		$(document).ready(function() {
			var errorId=document.getElementById('errorId');
			alert(errorId.value);
    	 //alert(' Store Location can not be Remove, Its already used in another Master/Transaction');
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
				
				$("button").button();
				$("#lightness").click(function() { $('#link').attr('href', 'jquery-ui/css/ui-lightness/jquery-ui-1.8.4.custom.css'); });
				$("#hotsneaks").click(function() { $('#link').attr('href', 'jquery-ui/css/hot-sneaks/jquery-ui-1.8.4.custom.css'); });
				$("#flick").click(function() { $('#link').attr('href', 'jquery-ui/css/flick/jquery-ui-1.8.4.custom.css'); });
				$("#redmond").click(function() { $('#link').attr('href', 'jquery-ui/css/redmond/jquery-ui-1.8.4.custom.css'); });
				$("#smoothness").click(function() { $('#link').attr('href', 'jquery-ui/css/smoothness/jquery-ui-1.8.4.custom.css'); });
			 
				
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
			.Ac {width:78px !important;}	
		th{font-size:10px;}
 		td{font-size:12px;}  
 		div.t_fixed_header_main_wrapper.ui div.t_fixed_header_caption {
		display:none;	
		}
		.sl{width:176px !important; border:none !important}
  .code{width:93px !important; border:none !important}
  .len{width:95px !important; border:none !important}
  .widh{width:94px !important; border:none !important}
  .heigh{width:93px !important; border:none !important}
  .sc{width:142px !important; border:none !important}
 .View {width:34px !important; border:none !important}
			.ui-widget-content {
overflow-x: hidden !important;
 
}
</style>
 

<form:form id="editForm" name="input" class="formdiv" action="get_storelocation_data" modelAttribute="searchCriteria">
		     
    <div class="header">Store Location List</div> 
	<div class="headingdiv">
	  <table width="880" height="31" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td width="75"><a onclick="return checkAdd();" href="show_new_storelocation_form" class="addbtn" iconCls="icon-add"></a></td>
          <td width="62"><a href="#" class="exportbtn" iconCls="icon-redo" onClick="javascript:$('#dlg').dialog('close')"></a></td>
          <td width="124"><div align="center">Store Location Name</div></td>
          <td width="62">
          <form:input  onkeypress="return check(event)"  data-maxsize="65" path="name" size="16" id="storeLocationName" /></td>
          <td width="83"><div align="center">Code</div></td>
          <td width="160"><form:input onkeyup="valid1(this)" onblur="valid1(this)" data-maxsize="16" path="code" size="16" id="code" /></td>
          <td width="67"><input  class="searchbtn"  type="submit" value=""/></td>
          <td width="52">
          <a href="show_storelocation_form" class="cancelbtn"></a>
	</td>
        </tr>
      </table>
  </div>
	<div id="demo">
	<div class="gridheadingdiv"  >
	 <table width="972" class="fixmyheader-8" id="example">
  <thead>
   <tr>
  		  <td class="View" widtd="24"><div align="center">View</div></td>
          <td class="sl" widtd="176"><div align="center">Store Location</div></td>
          <td class="code" widtd=93"><div align="center">Code</div></td>
          <td class="len" widtd="95"><div align="center">Length</div></td>
          <td class="widh" widtd="94"><div align="center">Width</div></td>
          <td class="heigh" widtd="93"><div align="center">Height</div></td>
          <td class="sc" widtd="142"><div align="center">Store Condition</div></td>
          <td class="Ac" style="text-align: center;" ><div align="center">Action</div></td>
        </tr>
  </thead>
  <tbody>
        
        <c:forEach items="${storeLocationList}" var="storeLocation">
			<tr> <td style="text-align: center;" width="24">
			<c:url value="get_storelocation" var="view_url">
			<c:param name="storeLocationId" value="${storeLocation.storeLocationId}"></c:param>
			<c:param name="opr" value="V"></c:param>
		  </c:url>
          <a href="${view_url}"><img src="static/images/view_icon.png" title="View Record" alt="" /></a></td>
        
				<td style="width:171px" >&nbsp;<c:out value="${storeLocation.storeLocation}"/></td>
				<td style="width:86px">&nbsp;<c:out value="${storeLocation.locationCode}"/></td>
				<td style="width:87px;text-align: right;" >&nbsp;<fmt:formatNumber value="${storeLocation.storeLength}" type="number"  minFractionDigits="2" maxFractionDigits="2"/>&nbsp;</td>
				<td style="width:87px;text-align: right;" >&nbsp;<fmt:formatNumber value="${storeLocation.storeWidth}" type="number"  minFractionDigits="2" maxFractionDigits="2"/>&nbsp;</td>
				<td style="width:86px; text-align: right;"  >&nbsp;<fmt:formatNumber value="${storeLocation.storeWidth}" type="number"  minFractionDigits="2" maxFractionDigits="2"/>&nbsp;</td>
				<td style="width:136px">&nbsp;<c:out value="${storeLocation.storeCondition}"/></td>				
			
				<td style="width:80px;"  >
         			<c:url value="get_storelocation" var="remove_url">
					<c:param name="storeLocationId" value="${storeLocation.storeLocationId}"></c:param>
					<c:param name="opr" value="R"></c:param>
					</c:url>
					
					<c:url value="get_storelocation" var="edit_url">
					<c:param name="storeLocationId" value="${storeLocation.storeLocationId}"></c:param>
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
