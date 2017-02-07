 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
  	var delUrl='show_new_state_form';
  	$(document).ready(function() {
      alert('No Record Found !!!!');
      window.self.location  = delUrl;
	});
 	</script>
</c:if>

 <c:if test="${stateForm.succ=='Ad'}">
  <script type="text/javascript">
  var delUrl='show_new_state_form';
	$(document).ready(function() {
     alert('Record Inserted Successfully !!!!');
     window.self.location = delUrl;
	});
 	</script>
</c:if>

<c:if test="${succ=='Dl'}">
  <script type="text/javascript">
  var delUrl='show_new_state_form';
  $(document).ready(function() {
     alert('Record Deleted Successfully !!!!');
     window.self.location = delUrl;
	});
 	</script>
</c:if>

<c:if test="${stateForm.succ=='Up'}">
  <script type="text/javascript">
  var delUrl='show_new_state_form';
	$(document).ready(function() {
     alert('Record Updated Successfully !!!!');
     window.self.location = delUrl;
	});
 	</script>
</c:if>

 <c:if test="${not empty(errorList)}">
 <input type="hidden" id="errorId" value="${errorList.errorMsg}">
	<script type="text/javascript">
		var delUrl='show_new_state_form';
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
		.View	{	 width: 34px !important; border:none !important;		}
		.sn{width:194px !important;}
  .code{width:154px !important;}
  .zone{width:154px !important;}
  .desc{width:210px !important;}
  .ac{width:71px !important;} 
  	.ui-widget-content {
overflow-x: hidden !important;
 
}	
	th{font-size:10px;}
 	td{font-size:12px;}  
 	div.t_fixed_header_main_wrapper.ui div.t_fixed_header_caption {
		display:none;
	}
</style>

 <form name="input" action="get_state_data" class="formdiv" method="post"  modelAttribute="stateForm">
		     
    <div class="header"> State List </div> 
	<div class="headingdiv" > 
	  <table width="880" height="32"  border="0" cellpadding="0" cellspacing="0">
        <tr >
           <td width="45">
          	<a onclick="return checkAdd();" href="add_state_form" class="addbtn" iconCls="icon-add"> </a> 
          </td>
          <td  width="45"><input class="exportbtn"  type="button" value=" "/> </td>
          <td width="88"><div align="center">State Name</div></td>
          <td width="91">
      <input onkeyup="valid(this)" onblur="valid(this)" data-maxsize="65" type="text" name="stateName" size="16" id="stateName" /></td>
          <td width="61"><div align="center">Code</div></td>
          <td width="64"><input onkeyup="valid1(this)" onblur="valid1(this)" data-maxsize="16" type="text" name="stateCode" size="16" id="stateCode" /></td>
          <td width="81"><div align="center">Zone</div></td>
          <td width="150"><input type="text"  name="zoneName" onkeyup="valid(this)" onblur="valid(this)" data-maxsize="35" size="16" id="zoneName" /></td>
          <td width="84"><input class="searchbtn" type="submit" value=""/></td>
           <td width="45"><a href="show_new_state_form"  class="cancelbtn" ></a></td>
        </tr>
      </table>
      </div>
	 
	<div class="gridheadingdiv">
	  <table width="972" class="display fixmyheader-8" id="example">
  <thead>
   <tr>
     <td class="View" width="55"><div align="center">View</div></td>
 
          <td style="border: none;" class="sn" width="194" ><div align="center">State Name</div></td>
          <td style="border: none;" class="code" width="154" ><div align="center">Code</div></td>
          <td style="border: none;" class="zone"  width="154"><div align="center">Zone</div></td>
          <td style="border: none;" class="desc" width="210"><div align="center">Description</div></td>
          <td style="border: none;" class="ac" width="79"><div align="center">Action</div></td>
        </tr>
         </thead>
  <tbody>
          
         <c:forEach items="${stateList}" var="state">
        <tr>
         <td style="text-align: center;" width="24">
			<c:url value="get_state" var="view_url">
			<c:param name="stateId" value="${state.stateId}"></c:param>
			<c:param name="opr" value="V"></c:param>
		  </c:url>
          <a href="${view_url}"><img src="static/images/view_icon.png" title="View Record" alt="" /></a></td>
   
         
          <td  width="184">&nbsp;<c:out value="${state.stateName}"/></td>
          <td width="144">&nbsp;<c:out value="${state.stateCode}"/></td>
          <td width="144">&nbsp;<c:out value="${state.zoneDTO.zoneName}"/></td>
          <td width="200">&nbsp;<c:out value="${state.description}"/></td>
          <td width="70"  >
          <c:url value="get_state" var="edit_url">
					<c:param name="stateId" value="${state.stateId}"></c:param>
					<c:param name="opr" value="E"></c:param>
		  </c:url>
		  <c:url value="get_state" var="remove_url">
					<c:param name="stateId" value="${state.stateId}"></c:param>
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
  
 </form>
