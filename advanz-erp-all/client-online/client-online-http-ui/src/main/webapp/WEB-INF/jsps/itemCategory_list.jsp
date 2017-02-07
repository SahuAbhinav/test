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
  	var delUrl='show_itemCategory_form';
  	$(document).ready(function() {
      alert('No Record Found !!!!');
      //window.self.location = delUrl;
	});
 	</script>
</c:if>

 <c:if test="${itemCategoryForm.succ=='Ad'}">
  <script type="text/javascript">
  var delUrl='show_itemCategory_form';
	$(document).ready(function() {
     alert('Record Inserted Successfully !!!!');
     window.self.location = delUrl;
	});
 	</script>
</c:if>

<c:if test="${itemCategoryForm.succ=='Dl'}">
  <script type="text/javascript">
  var delUrl='show_itemCategory_form';
  $(document).ready(function() {
     alert('Record Deleted Successfully !!!!');
     window.self.location = delUrl;
	});
 	</script>
</c:if>

<c:if test="${itemCategoryForm.succ=='Up'}">

  <script type="text/javascript">
  var delUrl='show_itemCategory_form';
	$(document).ready(function() {
     alert('Record Updated Successfully !!!!');
     window.self.location = delUrl;
	});
 	</script>
</c:if>

 <c:if test="${not empty(errorList)}">
  <input type="hidden" id="errorId" value="${errorList.errorMsg}">
	<script type="text/javascript">
	 var delUrl='show_itemCategory_form';
	 $(document).ready(function() {
	 var errorId=document.getElementById('errorId');
	 alert(errorId.value);
     //alert('Item Category can not be Removed this Field is using in other Form');
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
	
th{font-size:10px;}
 td{font-size:12px;}  
 
 	.ui-widget-content {
overflow-x: hidden !important;
}
.ic{width:209px !important; border: none !important; }
		.cc{width:210px !important; border: none !important;}
		.ig{width:217px !important; border: none !important;}
		
	div.t_fixed_header_main_wrapper.ui div.t_fixed_header_caption {
		display:none;
		
		}
			.View	{	 width: 34px !important; border:none !important;		}
	.Ac{Width:57px !important; border: none !important;}
</style>

</head>



<form:form name="input" action="search_item_category" class="formdiv" method="post" modelAttribute="itemCategoryForm">

	<div class="header">Item Category List</div>
	<div class="headingdiv">
		<table width="880" height="31" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				<td width="45"><a onclick="return checkAdd();" href="show_new_icategory_form"
					class="addbtn" iconCls="icon-add"> </a>
				</td>
				<td width="45"><input class="exportbtn"
					
					type="button" value="" /></td>
				<td width="138"><div align="center">&nbsp;&nbsp;&nbsp;Item
						Category Name</div>
				</td>
				<td width="77"><form:input  onkeypress="return check(event)"  data-maxsize="35"  path="itemCategoryDTO.itemCategoryName"	size="16" id="iteamCategoryName" />
				</td>
				<td width="79"><div align="center">Code</div>
				</td>	
				<td width="203"><form:input onkeyup="valid1(this)" onblur="valid1(this)" data-maxsize="16"  path="itemCategoryDTO.itemCategoryCode" size="16"	id="code" />
				</td>
				<td width="45"><input class="searchbtn"
					
					type="submit" value=" " />
				</td>
				<td width="45"><a href="show_itemCategory_form" class="cancelbtn" 
			></a>
				</td>
			</tr>
		</table>
	</div>
	<div id="demo">
	<div class="gridheadingdiv"  >
	 <table width="972" class="fixmyheader-8" id="example">
  <thead>
   <tr>
		   <td class="View" width="55"><div align="center">View</div></td>
				<td class="ic" width="209"><div align="center">Item Category Name</div>
				</td>
				<td class="cc" width="210"><div align="center">Category Code</div>
				</td>
				<td class="ig" width="217"><div align="center">Item Group Name</div>
				</td>
				<td class="Ac"><div align="center">Action</div>
				</td>
			</tr>
  </thead>
  <tbody>
      
			<c:forEach items="${catList}" var="cat">
				<tr >
				<td style="text-align: center;" width="24">
			<c:url value="edit_itemCategory" var="view_url">
			<c:param name="itemCategoryId" value="${cat.itemCategoryId}"></c:param>
			<c:param name="opr" value="V"></c:param>
		  </c:url>
          <a href="${view_url}"><img src="static/images/view_icon.png" title="View Record" alt="" /></a></td>
   
	
					<td style="width:199px" >&nbsp;<c:out value="${cat.itemCategoryName}" />
					</td>
					<td style="width:200px">&nbsp;<c:out value="${cat.itemCategoryCode}" />
					</td>
					<td style="width:207px">&nbsp;<c:out
							value="${cat.itemGroupDTO.itemGroupName}" /></td>
					<td style="width:57px;"  >
					
					<c:url value="edit_itemCategory" var="remove_url">
					<c:param name="itemCategoryId" value="${cat.itemCategoryId}"></c:param>
					<c:param name="opr" value="R"></c:param>
					</c:url>
					<c:url value="edit_itemCategory" var="edit_url">
					<c:param name="itemCategoryId" value="${cat.itemCategoryId}"></c:param>
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
          </c:forEach>					</td>
				</tr>
			</c:forEach>
  
  </tbody>
</table>
  </div>
  </div>
  
 </form:form>