<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
 .sn{width:153px !important; border:none !important}
 .ptax{width:100px !important; border:none !important}  
 .famt{width:100px !important; border:none !important}
 .tamt{width:100px !important; border:none !important}
 .dp{width:100px !important; border:none !important}
 .deduce{width:100px !important; border:none !important}
 .Ac{width:56px !important; border:none !important}
	 .View	{	 width: 34px !important; border:none !important;		}
th{font-size:10px;}
 td{font-size:12px;}  
 
 	
	div.t_fixed_header_main_wrapper.ui div.t_fixed_header_caption {
		display:none;
		
		}
	
</style>
<c:if test="${succ=='Blk'}">
  <script type="text/javascript">
  	var delUrl='show_professionalTax_form';
  	$(document).ready(function() {
      alert('No Record Found !!!!');
      //window.self.location  = delUrl;
	});
 	</script>
</c:if>

 <c:if test="${professionalTaxForm.succ=='Ad'}">
  <script type="text/javascript">
  var delUrl='show_professionalTax_form';
	$(document).ready(function() {
     alert('Record Inserted Successfully !!!!');
     window.self.location = delUrl;
	});
 	</script>
</c:if>

<c:if test="${professionalTaxForm.succ=='Dl'}">
  <script type="text/javascript">
  var delUrl='show_professionalTax_form';
  $(document).ready(function() {
     alert('Record Deleted Successfully !!!!');
     window.self.location = delUrl;
	});
 	</script>
</c:if>

<c:if test="${professionalTaxForm.succ=='Up'}">
  <script type="text/javascript">
  var delUrl='show_professionalTax_form';
	$(document).ready(function() {
     alert('Record Updated Successfully !!!!');
     window.self.location = delUrl;
	});
 	</script>
</c:if>

 <c:if test="${not empty(errorList)}">
 <input type="hidden" id="errorId" value="${errorList.errorMsg}">
	<script type="text/javascript">
		var delUrl='show_professionalTax_form';
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

<form:form name="input" action="get_professionalTax_data" class="formdiv" method="post" modelAttribute="professionalTaxForm" >
<!-- <form method="post" id="editForm" name="input" class="formdiv" action="get_professionalTax_data"> -->    
		     
    <div class="header">Professional Tax List </div> 
	<div class="headingdiv">
	  <table width="880" height="31" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td width="45"><a onclick="return checkAdd();" href="show_new_professionalTax_form" class="addbtn" iconCls="icon-add"></a></td>
          <td width="45"><a href="#" class="exportbtn" iconCls="icon-redo" onClick="javascript:$('#dlg').dialog('close')"></a></td>
     <td width="80"><div align="center">Slab Name</div></td>
          <td width="62"><form:input onkeyup="valid(this)" onblur="valid(this)" type="text" path="slabName" size="16" id="slabName" /></td>
           <td width="80"><div align="center">Deduct Amount</div></td>
          <td width="90"><form:input class="quantity" type="text" path="deductAmount" size="16" id="deductAmount" /></td>
          <td width="45"><input  class="searchbtn"  type="submit" value=""/></td>
          <td width="45">
          <a href="show_professionalTax_form"  class="cancelbtn" iconCls="icon-cancel"></a> 
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
           <td class="sn" width="153"><div align="center">Slab Name</div></td>
          <td class="ptax" widtd="100"><div align="center"><div>PTaxCode</div></div></td>
          <td class="famt" widtd="100"><div align="center">FromAmount</div></td>
          <td class="tamt" widtd="100"><div align="center"><div>ToAmount</div></div></td>
          <td class="dp" widtd="100"><div align="center"><div>DeductAmount</div></div></td>
          <td class="deduce"  widtd="100"><div align="center"><div>DeductType</div></div></td>
          <td class="Ac" class="Ac"><div align="center">Action</div></td>
        </tr>
  </thead>
  <tbody>  
        <c:forEach items="${professionalTaxList}" var="professionalTax">
			<tr>
			
				   <td style="text-align: center;" width="24">
			<c:url value="get_professionalTax" var="view_url">
			<c:param name="ptaxId" value="${professionalTax.ptaxId}"></c:param>
			<c:param name="opr" value="V"></c:param>
		  </c:url>
          <a href="${view_url}"><img src="static/images/view_icon.png" title="View Record" alt="" /></a></td>
			
				<td width="143" >&nbsp;<c:out value="${professionalTax.slabName}"/></td>
				<td width="90">&nbsp;<c:out value="${professionalTax.ptaxCode}"/></td>
				<td width="90" style="text-align: right">&nbsp;<c:out value="${professionalTax.fromAmount}"/></td>
				<td width="90" style="text-align: right" >&nbsp;<c:out value="${professionalTax.toAmount}"/></td>
				<td width="90" style="text-align: right" >&nbsp;<c:out value="${professionalTax.deductAmount}"/></td>
				<td width="90">&nbsp;<c:out value="${professionalTax.professionalTaxDeductTypeDto.ptaxDeductType}"/></td>
			
				<td width="56" style="" >
         			<c:url value="get_professionalTax" var="remove_url">
					<c:param name="ptaxId" value="${professionalTax.ptaxId}"></c:param>
					<c:param name="opr" value="R"></c:param>
					</c:url>
					<c:url value="get_professionalTax" var="edit_url">
					<c:param name="ptaxId" value="${professionalTax.ptaxId}"></c:param>
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
 <!-- </form>-->
 