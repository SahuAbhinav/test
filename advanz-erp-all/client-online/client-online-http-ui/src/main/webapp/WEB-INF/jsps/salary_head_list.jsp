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
  	var delUrl='get_salaryHead_list';
  	$(document).ready(function() {
      alert('No Record Found !!!!');
      //window.self.location  = delUrl;
	});
 	</script>
</c:if>

 <c:if test="${salaryHeadForm.succ=='Ad'}">
  <script type="text/javascript">
  var delUrl='get_salaryHead_list';
	$(document).ready(function() {
     alert('Record Inserted Successfully !!!!');
     window.self.location = delUrl;
	});
 	</script>
</c:if>

<c:if test="${salaryHeadForm.succ=='Dl'}">
  <script type="text/javascript">
  var delUrl='get_salaryHead_list';
  $(document).ready(function() {
     alert('Record Deleted Successfully !!!!');
     window.self.location = delUrl;
	});
 	</script>
</c:if>

<c:if test="${salaryHeadForm.succ=='Up'}">
  <script type="text/javascript">
  var delUrl='get_salaryHead_list';
	$(document).ready(function() {
     alert('Record Updated Successfully !!!!');
     window.self.location = delUrl;
	});
 	</script>
</c:if>

 <c:if test="${not empty(errors)}">
 <input type="hidden" id="errorId" value="${errors.errorMsg}">
	<script type="text/javascript">
		var delUrl='get_salaryHead_list';
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
	


<script type="text/javascript">
			$(document).ready(function() {  	
				$('.fixmyheader-8').fixheadertable({
					caption		: 'My header is fixed !',
					height		:411,
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
 
 .Sh {width:98px !important;  } 
 .Co {width:68px !important;} 
 .Ht {width:74px !important;}
  .Ty {width:71px !important;}
 .Bh {width:84px !important;}
 .Bper {width:57px !important;}
 
 .Pt {width:77px !important;}
 .Pm {width:86px !important;}
 .Des {width:87px !important;}
 .Ac {width: 46px !important;}  
 
 		.View	{	 width: 34px !important; border:none !important;		}
	div.t_fixed_header_main_wrapper.ui div.t_fixed_header_caption {
		display:none;
		
		}
	
</style>



<form:form name="input" action="get_salaryHead_list"  class="formdiv"
	method="post" modelAttribute="salaryHeadSearchCriteria">

	<div class="header"  >Salary Head List</div>
	<div class="headingdiv"  >
		<table width="880" height="31" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				<td width="45"><a onclick="return checkAdd();"  href="show_new_salary_Head_form" class="addbtn" iconCls="icon-add"> </a>
				</td>
				<td width="45"><input class="exportbtn"
					
					type="button" value="" /></td>
				<td width="111"><div align="center">Salary Head Name</div>
				</td>
				<td width="62"><form:input onkeyup="valid(this)" onblur="valid(this)" data-maxsize="65" path="salaryHeadName" size="16"
						id="salaryHeadName" />
				</td>
				<td width="75"><div align="center">Code</div>
				</td>
				<td width="63"><form:input onkeyup="valid1(this)" onblur="valid1(this)" data-maxsize="16" path="salaryHeadCode" size="16"
						id="salaryHeadCode" />
				</td>
				<td width="83"><div align="center">Head Type</div>
				</td>
				<td width="81"><form:input  onkeypress="return check(event)"  data-maxsize="35" path="headType" size="16"
						id="headType" />
				</td>
				<td width="45"><input class="searchbtn"
					
					type="submit" value="" />
				</td>
				<td width="45"><a href="get_salaryHead_list"
					class="cancelbtn" iconCls="icon-cancel"></a>
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
				<td style="border: none" class="Sh"><div align="center">Salary Head Name</div>				</td>
				<td style="border: none" class="Co"><div align="center">Code</div>				</td>
				<td style="border: none" class="Ht"><div align="center">Head Type</div>				</td>
				<td style="border: none" class="Ty"><div align="center">Type</div>				</td>
				<td style="border: none" class="Bh"><div align="center">Base Head</div>				</td>
				<td style="border: none" class="Bper"><div align="center">Base %</div>				</td>
				<td style="border: none" class="Pt" ><div align="center">Payable Type</div>				</td >
				<td style="border: none"  class="Pm" ><div align="center">Payable Montd</div>				</td>
				<td style="border: none"  class="Des" ><div align="center">Description</div>			  </td>
				<td style="border: none" class="Ac"><div align="center">Action</div>
				</td>
			 </tr>
  </thead>
  <tbody>		<c:forEach items="${salaryHeadList}" var="salaryHead">
				 
				<tr>
				
				  <td style="text-align: center;" width="24">
			<c:url value="get_salaryHead" var="view_url">
			<c:param name="salaryHeadId" value="${salaryHead.salaryHeadId}"></c:param>
			<c:param name="opr" value="V"></c:param>
		  </c:url>
          <a href="${view_url}"><img src="static/images/view_icon.png" title="View Record" alt="" /></a></td>
  
					<td width="88">&nbsp;					  <c:out value="${salaryHead.salaryHeadName}" />
				  </td>
					<td width="58">&nbsp;					  <c:out value="${salaryHead.salaryHeadCode}" />
				  </td>
					<td width="64">&nbsp;					  <c:out value="${salaryHead.headType}" />
				  </td>
					<td width="61">&nbsp;					  <c:out value="${salaryHead.type}" />
				  </td>
					<td width="74">&nbsp;					<c:forEach items="${salaryHead.baseHeads}" var="baseHeadIds" varStatus="s">
					  <c:out value="${baseHeadIds.salaryHeadName}" />,</c:forEach>
				  </td>
				  <td width="47" style="text-align: right" align="right">&nbsp;					  <c:out value="${salaryHead.baseHeadPer}" />
				  </td>
					<td width="67">&nbsp;					  <c:out value="${salaryHead.payableType}" />
				  </td>
					<td width="76">&nbsp;					  <c:out value="${salaryHead.payableMonth}" />
				  </td>
					<td width="77">&nbsp;					  <c:out value="${salaryHead.description}" />
				  </td>
					
					
				  <td width="46"  ><c:url value="get_salaryHead" var="edit_url">
							<c:param name="salaryHeadId" value="${salaryHead.salaryHeadId}"></c:param>
							<c:param name="opr" value="E"></c:param>
						</c:url> <c:url value="get_salaryHead" var="remove_url">
							<c:param name="salaryHeadId" value="${salaryHead.salaryHeadId}"></c:param>
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
