 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page session="true" %>
<%@ page isELIgnored="false" %>
<script type="text/javascript" charset="utf-8">
                                    
                $(document).ready(function() {
                    /* Initialise datatables */
                    var oTable = $('#example').dataTable( {
                    	"aLengthMenu": [[10,10, 25, 50, -1], ['Select',10, 25, 50, "All"]],
                 		"iDisplayLength":10000,
                 		 "sPaginationType": "full_numbers",
                 		"bPaginate": false
                 		
                    } );
                } );
            </script>


  <c:if test="${succ=='Blk'}">
  <script type="text/javascript">
  function getParam(name)
	{
	 name = name.replace(/[\[]/,"\\\[").replace(/[\]]/,"\\\]");
	 var regexS = "[\\?&]"+name+"=([^&#]*)";
	 var regex = new RegExp( regexS );
	 var results = regex.exec( window.location.href );
	 if( results == null )
	  return "";
	else
	 return results[1];
	}
	 var next =  Number(getParam('next'));
	 next=next-15;
	var delUrl='show_Item_form?next='+next;
  	$(document).ready(function() {
      alert('No Record Found !!!!');
     //document.location = delUrl;
	});
 	</script>
</c:if>

 <c:if test="${itemForm.succ=='Ad'}">
  <script type="text/javascript">
  function getParam(name)
	{
	 name = name.replace(/[\[]/,"\\\[").replace(/[\]]/,"\\\]");
	 var regexS = "[\\?&]"+name+"=([^&#]*)";
	 var regex = new RegExp( regexS );
	 var results = regex.exec( window.location.href );
	 if( results == null )
	  return "";
	else
	 return results[1];
	}
	 var next =  Number(getParam('next'));
	 next=next-15;
	var delUrl='show_Item_form?next='+next;
	$(document).ready(function() {
     alert('Record Inserted Successfully !!!!');
     window.self.location= delUrl;
	});
 	</script>
</c:if>

<c:if test="${succ=='Dl'}">
  <script type="text/javascript">
	function getParam(name)
	{
	 name = name.replace(/[\[]/,"\\\[").replace(/[\]]/,"\\\]");
	 var regexS = "[\\?&]"+name+"=([^&#]*)";
	 var regex = new RegExp( regexS );
	 var results = regex.exec( window.location.href );
	 if( results == null )
	  return "";
	else
	 return results[1];
	}
	 var next =  Number(getParam('next'));
	 next=next-15;
	// alert(next);
	var delUrl='show_Item_form?next='+next;
    $(document).ready(function() {
    	
     alert('Record Deleted Successfully !!!!');
     window.self.location = delUrl;
	});
 	</script>
</c:if>

<c:if test="${itemForm.succ=='Up'}">
  <script type="text/javascript">
  function getParam(name)
	{
	 name = name.replace(/[\[]/,"\\\[").replace(/[\]]/,"\\\]");
	 var regexS = "[\\?&]"+name+"=([^&#]*)";
	 var regex = new RegExp( regexS );
	 var results = regex.exec( window.location.href );
	 if( results == null )
	  return "";
	else
	 return results[1];
	}
	 var next =  Number(getParam('next'));
	 next=next-15;
	var delUrl='show_Item_form?next='+next;
	$(document).ready(function() {
     alert('Record Updated Successfully !!!!');
     window.self.location
	});
 	</script>
</c:if>

 <c:if test="${not empty(errorList)}">
 <input type="hidden" id="errorId" value="${errorList.errorMsg}">
	<script type="text/javascript">
	function getParam(name)
	{
	 name = name.replace(/[\[]/,"\\\[").replace(/[\]]/,"\\\]");
	 var regexS = "[\\?&]"+name+"=([^&#]*)";
	 var regex = new RegExp( regexS );
	 var results = regex.exec( window.location.href );
	 if( results == null )
	  return "";
	else
	 return results[1];
	}
	 var next =  Number(getParam('next'));
	 	next=next-15;
		var delUrl='show_Item_form?next='+next;
		$(document).ready(function() {
		var errorId=document.getElementById('errorId');
		alert(errorId.value);
    	// alert('Branch can not be Removed this Field is using in other Form');
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
		
	function checkSearchField()
	{
	 //alert($("#itemName").val());
	    if($("#itemName").val()=="" && $("#itemGroup").val()=="" && $("#itemCode").val()=="" &&$("#category").val()=="" && $("#mastersId").val()=="" && $("#activeStatus").val()=="") 
	    	{
	    	alert('Please Insert Search Fields !!!!');
	    	return false;
	    	}
	    else
	    	{
	    	return true;
	    	} 
	}

	$(document).ready(function() {
		
	$( "#expButton" ).click(function() {
	   alert($("#itemSize").val());	
	});
				
	$('.fixmyheader-8').fixheadertable({
	caption		: 'My header is fixed !',
	height		:407,
	addTitles	: true,
	colratio	: ['10%', '10%', '8%', '50px', 'auto', 'auto', '30%', 'auto']
	});
	});
	</script>
  
     
<style type="text/css">

.dataTables_info
{
display:none;
	
}
.paging_full_numbers
{
display: none;
}
body {
	font-family:Arial, Helvetica, sans-serif;
	}
code, pre {		padding		: 10px;	
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
 
 	
	div.t_fixed_header_main_wrapper.ui div.t_fixed_header_caption {
		display:none;
		
		}
	#container{
	min-height: 533px;
	}	
  .View	{width: 34px !important; border:none !important;		}
  .in{width:180px !important; border:none !important}
  .code{width:54px !important; border:none !important}
  .cate{width:70px !important; border:none !important}
  .ig{width:90px !important; border:none !important}
  .pt{width:60px !important; border:none !important}
  .pack{width:40px !important; border:none !important}
  .open{width:50px !important; border:none !important}
  .mis{width:50px !important; border:none !important}
  .mas{width:70px !important; border:none !important}
  .ro{width:48px !important; border:none !important} 
 .store{width:48px !important; border:none !important}
  .ac{width:59px !important; border:none !important}
	  
</style>

  
<form:form name="input" action="get_item_data"  class="formdiv" method="post" modelAttribute="itemForm">

    <div class="header"  > Item List</div> 
	<div class="headingdiv"   >
	 <table width="880" height="31" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td width="45">
            
          	<a onclick="return checkAdd();" href="add_item_form" class="addbtn" iconCls="icon-add"> </a> 
        <td width="59"><div align="center">Item Code</div></td>
          <td width="96">
          	<form:input type="text" onkeyup="valid(this)" onblur="valid(this)" data-maxsize="35" path="itemDTO.itemCode" size="16" id="itemCode" />
       </td>
       <td width="99"><div align="center">Item Name</div></td>
          <td width="96"> 
          	<form:input type="text"  data-maxsize="180" path="itemDTO.itemName"   id="itemName" />
       </td>
       
          <td width="78"><div align="center">Category</div></td>
          <td width="65"><form:input onkeyup="valid(this)" onblur="valid(this)" data-maxsize="35" type="text"  path="itemDTO.itemCategoryDTO.itemCategoryName" size="16" id="category"/></td>
          <td width="64"><div align="center">Item Group</div></td>
          <td width="65"><form:input onkeyup="valid(this)" onblur="valid(this)" data-maxsize="35"  type="text" path="itemDTO.itemGroupName" size="16" id="itemGroup" />
          <input type="hidden" name="next" value="${itemForm.itemDTO.next}" size="16"  />
          </td>
          <td width="78"><div align="center">Item Grade</div></td>
           <td width="65"> <form:select path="itemDTO.masterGrade.mastersId" id="mastersId" >
				<form:option value="" ></form:option>
				<form:options items="${grade}" itemLabel="name" itemValue="mastersId" />
				</form:select></td>
					
					 <td width="78"><div align="center">Activated</div></td>
           <td width="65"> <form:select path="itemDTO.activeStatus" id="activeStatus"> 
           <form:option value=""></form:option>
						<form:option value="1">Yes</form:option>
						<form:option value="0">No</form:option>
					</form:select></td>
          <td width="45"><input class="searchbtn" onclick="return checkSearchField()" type="submit" value=" "/></td>
          <td width="45">
          <div><c:url value="show_Item_form" var="remove_url">
			<c:param name="next" value="${itemForm.itemDTO.next-(15)}"></c:param>
			</c:url> <a href="${remove_url}"  class="cancelbtn" ></a>
		</div>
				 <!-- <a href="show_Item_form"  class="cancelbtn" iconCls="icon-cancel"></a> --> 
			</td>
        </tr>
        <tr>
         
        </tr>
      </table>
	</div>
<div id="demo">
	<div class="gridheadingdiv">
	 <table width="972" class="fixmyheader-8" id="example">
  <thead>
   <tr>
 <td class="View" width="55"><div align="center">View</div></td>
	      <td class="in"><div align="center">Item Name</div></td>
          <td class="code" width="54"><div align="center">Code</div></td>
          <td class="cate" width="105"><div align="center">Category</div></td>
          <td class="ig" width="90"><div align="center">Item Group</div></td>
          <td class="pt" width="66"><div align="center">UOM</div></td>
         
          <td class="open" width="74"><div align="center">Closing</div></td>
          <td class="mis" width="77"><div align="center">Min Stock</div></td>
         
          <td class="ro" width="50"><div align="center">Re-Order</div></td>
           <td class="store" width="50"><div align="center">Store Location</div></td>
          <td class="ac"><div align="center">Action</div></td>
        
         </tr>
  </thead>
  <tbody>

  
    <c:forEach items="${itemList}" var="item">
	  <tr>
		<td style="text-align: center;" width="24">
			<c:url value="get_item" var="view_url">
			<c:param name="next" value="${itemForm.itemDTO.next}"></c:param>
			<c:param name="itemId" value="${item.itemId}"></c:param>
			<c:param name="opr" value="V"></c:param>
		  </c:url>
          <a href="${view_url}"><img src="static/images/view_icon.png" title="View Record" alt="" /></a></td>
 
		  <td width="181">&nbsp;<c:out value="${item.itemName}"/></td>
          <td width="44">&nbsp;<c:out value="${item.itemCode}"/></td>
          <td width="70">&nbsp;<c:out value="${item.itemCategoryDTO.itemCategoryName}"/></td>
          <td width="81">&nbsp;<c:out value="${item.itemCategoryDTO.itemGroupDTO.itemGroupName}"/></td>
          <td width="50">&nbsp;
         <c:out value="${item.masterUnit.name}" /></td>
         
          <td width="40" style="text-align: right;" align="right"><c:if test="${item.stockTotal==null || item.stockTotal=='0.0'}">0.00
          </c:if><c:if test="${item.stockTotal!=''}">
          <fmt:formatNumber value="${item.stockTotal}" type="number"  minFractionDigits="2" maxFractionDigits="2"/>
          </c:if>
          </td>
          <td width="40" style="text-align: right;" align="right"><fmt:formatNumber value="${item.minStock}" type="number"  minFractionDigits="2" maxFractionDigits="2"/></td>
          <td width="45" style="text-align: right;" align="right"><fmt:formatNumber value="${item.reorderLevel}" type="number"  minFractionDigits="2" maxFractionDigits="2"/></td>
          <td width="60">
       <c:out value="${item.storeLocationDTO.storeLocation}"/> 
         </td>
          <td width="60">
     
         <c:url value="get_item" var="edit_url">		
				<c:param name="next" value="${itemForm.itemDTO.next}"></c:param>
				<c:param name="itemId"  value="${item.itemId}"></c:param>
				<c:param name="opr" value="M"></c:param>
		</c:url> 
					
		<c:url value="get_item" var="remove_url">
				<c:param name="itemId" value="${item.itemId}"></c:param>
				<c:param name="next" value="${itemForm.itemDTO.next}"></c:param>
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
  <div class="dataTables_paginate" ><c:url value="show_Item_form" var="remove_url">
		<c:param name="next" value="${itemForm.itemDTO.previous}"></c:param>
		</c:url> <a href="${remove_url}" class="paginate_disabled_previous" >Previous</a>

	<c:url value="show_Item_form" var="remove_url">
		<c:param name="next" value="${itemForm.itemDTO.next}"></c:param>
		</c:url> <a href="${remove_url}" class="paginate_disabled_next" >Next</a>
	</div>
 
 </form:form>
 </body>
</html>
 
  