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
                } );
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
		
		//$("div.dataTables_info").text('');

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
	.View{width:60px !important; border:none!important}
	.finishedItem{width:170px !important; border:none!important}
	.pack{width:67px !important; border:none!important}
	.unit{width:65px !important; border:none!important}
	.batchSize{width:130px !important; border:none!important}
	.formulaBatch{width:134px !important; border:none!important}
	.Active{width:70px !important; border:none!important}
	.modDate{width:131px !important; border:none!important}
	.Ac{width:62px !important; border:none!important}			
	
th{font-size:10px;}
 td{font-size:12px;}  
 
 	
	div.t_fixed_header_main_wrapper.ui div.t_fixed_header_caption {
		display:none;
		
		}
	
</style>

    
  
<form:form name="input" action="search_master_formula" class="formdiv" method="post" modelAttribute="masterFormulaForm" >
		     
    <div class="header">Master Formula List</div> 
	<div class="headingdiv">
	  <table width="880" height="31" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td width="45"><a onclick="return checkAdd();" href="show_new_master_formula"	class="addbtn" iconCls="icon-add"> </a></td>
          <td width="45"><input class="exportbtn"  type="button" value=""/> </td>
          <td width="111"><div align="center">Finished Product</div></td>
          <td width="62">
      	<form:input path="itemName" data-maxsize="100"  size="16" id="itemNameSearch" /></td>
          <td width="75"><div align="center">Code</div></td>
          <td width="63">
		 <form:input data-maxsize="30" path="itemCode" size="16" id="itemCodeSearch" /></td>
         <td width="80"><input class="searchbtn" style="font-size: 11px; font-weight: bold;  padding: 0 0 0 20px;" type="submit" value=" "/></td>
   		 <td width="105">
    	  <a href="show_master_formula_list" class="cancelbtn" iconCls="icon-cancel" > </a>
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
	      <td class="finishedItem" width="150"><div align="center">Finished Product</div></td>
          <td class="pack" width="60"><div align="center">Pack</div></td>
          <td class="unit" width="60"><div align="center">Unit</div></td>
          <td class="batchSize" width="160"><div align="center">Standard Batch Size</div></td>
          <td class="formulaBatch" width="160"><div align="center">Formulae Batch Size</div></td>
		  <td class="Active" width="100"><div align="center">Active</div></td>	
		  <td class="modDate" width="100"><div align="center">Modified Date</div></td>	
          <td class="Ac" ><div align="center">Action</div></td>
        </tr>
    
     </thead>
  <tbody>  
        <c:forEach items="${formulaMasterDTOList}" var="formula">			
        <tr>
         <td style="text-align: center;" width="50">
			<c:url value="get_master_formula" var="view_url">
			<c:param name="id" value="${formula.masterFormulaAutoId}"></c:param>
			<c:param name="opr" value="V"></c:param>
		  </c:url>
          <a href="${view_url}"><img src="static/images/view_icon.png" title="View Record" alt="" /></a></td>
          <td width="160">&nbsp;<c:out value="${formula.itemDTO.itemName}" /></td>
          <td width="60">&nbsp;<c:out value="${formula.itemDTO.masterPack.name}" /></td>
          <td width="60">&nbsp;<c:out value="${formula.itemDTO.masterUnit.name}" /></td>
          <td width="120">&nbsp;<c:out value="${formula.standardBatchSize}" /></td>
          <td width="120">&nbsp;<c:out value="${formula.formulaBatchSize}" /></td>
          <td width="70">&nbsp;<c:if test="${formula.activeStatus==1}">
          <c:out value="yes" /></c:if>
          <c:if test="${formula.activeStatus==0}">
          <c:out value="No" /></c:if>
          
          </td>
          <td width="120">&nbsp;<fmt:formatDate pattern="dd-MMM-yyyy"  value="${formula.modifiedDate}" /></td>
                 <td width="62" style="" >
          <c:url value="get_master_formula" var="edit_url">
					<c:param name="id" value="${formula.masterFormulaAutoId}"></c:param>
					<c:param name="opr" value="E"></c:param>
					
		  </c:url>
		  <c:url value="get_master_formula" var="remove_url">
					<c:param name="id" value="${formula.masterFormulaAutoId}"></c:param>
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

