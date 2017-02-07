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

<c:if test="${succ=='Blk'}">
  <script type="text/javascript">
  	var delUrl='show_ItemGroup_form';
  	$(document).ready(function() {
      alert('No Record Found !!!!');
      //window.self.location= delUrl;
	});
 	</script>
</c:if>

 <c:if test="${itemGroupForm.succ=='Ad'}">
 
  <script type="text/javascript">
  var delUrl='show_ItemGroup_form';
	$(document).ready(function() {
     alert('Record Inserted Successfully !!!!');
     window.self.location= delUrl;
     
	});
 	</script>
</c:if>

<c:if test="${succ=='Dl'}">
  <script type="text/javascript">
  var delUrl='show_ItemGroup_form';
  $(document).ready(function() {
     alert('Record Deleted Successfully !!!!');
     window.self.location = delUrl;
	});
 	</script>
</c:if>

<c:if test="${itemGroupForm.succ=='Up'}">
  <script type="text/javascript">
  var delUrl='show_ItemGroup_form';
	$(document).ready(function() {
     alert('Record Updated Successfully !!!!');
     window.self.location = delUrl;
	});
 	</script>
</c:if>

 <c:if test="${not empty(errors)}">
 <input type="hidden" id="errorId" value="${errors.errorMsg}">
	<script type="text/javascript">
		var delUrl='show_ItemGroup_form';
		$(document).ready(function() {
		var errorId=document.getElementById('errorId');
		alert(errorId.value);
    	// alert('Item Group can not be Removed this Field is using in other Form');
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
	th{font-size:10px;}
 	td{font-size:12px;}  
	div.t_fixed_header_main_wrapper.ui div.t_fixed_header_caption {
		display:none;
		
		}
		.View	{	 width: 34px !important; border:none !important;		}
		   .Ac {width: 43px !important; border: none !important;}
   .ig{width:208px !important; border: none !important; }
   .gc{width:224px !important;border: none !important; }	

</style>

<form:form name="input" action="get_itemGroup_data" class="formdiv" method="post">

		     
    <div class="header"> Item Group List </div> 
	<div class="headingdiv">
	  <table width="880" height="31" border="0" cellpadding="0" cellspacing="0">
        <tr> 
                
           <td width="45">
          	<a onclick="return checkAdd();"  href="add_itemGroup_form" class="addbtn" iconCls="icon-add"></a> 
          </td>
          <td width="45"><input class="exportbtn"  type="button" value=" "/> </td>
          <td width="128"><div align="center">&nbsp;Item Group Name </div></td>
          <td width="80">
          <input type="text"  onkeypress="return check(event)"    name="itemGroupName" size="16" id="itemGroupName" /></td>
          <td width="94"><div align="center">&nbsp;Group Code </div></td>
          <td width="210"><input onkeyup="valid1(this)" onblur="valid1(this)" data-maxsize="16" type="text" name="itemGroupCode" size="16" id="itemGroupCode" /></td>
          <td width="45"><input  class="searchbtn"  type="submit" value=""/></td>
          <td width="45"> <div class="cancelbtn">
				<a href="show_ItemGroup_form"
						class="cancelbtn" iconCls="icon-cancel"> </a>
			</div></td>
        </tr>
      </table>
  </div>
	<div id="demo">
	<div class="gridheadingdiv"  >
	 <table width="972" class="fixmyheader-8" id="example">
  <thead>
   <tr>
 <td class="View" width="55"><div align="center">View</div></td>
          <td class="ig" width="150"><div align="center">Item Group Name</div></td>
          <td class="gc" width="190"><div align="center">Group Code</div></td>
          <td class="Ac"><div align="center">Action</div></td>
        </tr>
        
  </thead>
  <tbody>
  			
        
         <c:forEach items="${itemGroupList}" var="itemGroup">
        <tr>
         <td style="text-align: center;" width="24">
			<c:url value="get_itemGroup" var="view_url">
			<c:param name="itemGroupId" value="${itemGroup.itemGroupId}"></c:param>
			<c:param name="opr" value="V"></c:param>
		  </c:url>
          <a href="${view_url}"><img src="static/images/view_icon.png" title="View Record" alt="" /></a></td>
        
          <td style="width:198px" >&nbsp;<c:out value="${itemGroup.itemGroupName}"/></td>
          <td style="width:214px">&nbsp;<c:out value="${itemGroup.itemGroupCode}"/></td>
          <td style="width:38px;"   >
           <c:url value="get_itemGroup" var="edit_url">
					<c:param name="itemGroupId" value="${itemGroup.itemGroupId}"></c:param>
					<c:param name="opr" value="M"></c:param>
					</c:url> 
					
			<c:url value="get_itemGroup" var="remove_url">
					<c:param name="itemGroupId" value="${itemGroup.itemGroupId}"></c:param>
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
      </table>
	</div>
	</div>
 </form:form>
