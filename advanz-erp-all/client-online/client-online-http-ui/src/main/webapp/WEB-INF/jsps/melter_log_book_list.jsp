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
			.View{width:34px !important; border:none!important}
	.Med{width:115px !important; border:none !important;}
.Rn{width:115px !important; border:none !important;}
.Time{width:115px !important; border:none !important;}
.shift{width:115px !important; border:none !important;}
.Optr{width:115px !important; border:none !important;}
 .Tp{width:115px !important; border:none !important;}
 .Ac{width:57px !important; border:none !important;}
th{font-size:10px;}
 td{font-size:12px;}  
 
 	
	div.t_fixed_header_main_wrapper.ui div.t_fixed_header_caption {
		display:none;
		
		}
}
 	
</style>
<c:if test="${melterForm.succ=='Ad'}">
  <script type="text/javascript">
  var delUrl='show_melter_log_book';
	$(document).ready(function() {
     alert('Record Inserted Successfully !!!!');
     window.self.location = delUrl;
	});
 	</script>
</c:if>

<c:if test="${succ=='Blk'}">
  <script type="text/javascript">
  	var delUrl='show_melter_log_book';
  	$(document).ready(function() {
      alert('No Record Found !!!!');
      //window.self.location  = delUrl;
	});
 	</script>
</c:if>


<c:if test="${melterForm.succ=='Dl'}">
  <script type="text/javascript">
  var delUrl='show_melter_log_book';
  $(document).ready(function() {
     alert('Record Deleted Successfully !!!!');
     window.self.location = delUrl;
	});
 	</script>
</c:if>

<c:if test="${melterForm.succ=='Up'}">
  <script type="text/javascript">
  var delUrl='show_melter_log_book';
	$(document).ready(function() {
     alert('Record Updated Successfully !!!!');
     window.self.location = delUrl;
	});
 	</script>
</c:if>

 <c:if test="${not empty(errorList)}">
 <input type="hidden" id="errorId" value="${errorList.errorMsg}">
	<script type="text/javascript">
		var delUrl='show_melter_trolly_form';
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
      $(document).ready(function()
       {     
	    $(".datepicker1" ).datepicker({dateFormat : 'dd-M-yy',changeMonth: true,
	          changeYear: true, yearRange: '-99:+0'});
	    
	    $(".fromDate" ).datepicker({dateFormat : 'dd-M-yy',changeMonth: true,
	          changeYear: true, yearRange: '-99:+0'});
	    $(".toDate" ).datepicker({dateFormat : 'dd-M-yy',changeMonth: true,
	          changeYear: true, yearRange: '-99:+0'});
	  
      });
  </script>

 
<form:form name="input" action="get_melter_log_book" class="formdiv" modelAttribute="melterForm" >
		     
    <div class="header"> Melter Log Book List</div> 
	<div class="headingdiv">
	  <table width="880" height="31" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td width="82"><a onclick="return checkAdd();" href="show_new_melter_form" class="addbtn"></a></td>
          <td width="70"><input class="exportbtn" style="font-size: 11px; font-weight: bold;  padding: 0 0 0 38px;" type="button" value=""/> </td>
          <%-- <td width="111"><div align="center">LogDate</div></td>
          <td width="62"><form:input  type="text" class="datepicker1" path="logDate" size="16" id="logDate" /></td> --%>
          
          <td width="111"><div align="center">From Date</div></td>
          <td width="62"><form:input  type="text" class="fromDate" path="fromDate" size="16"  /></td>
          <td width="111"><div align="center">To Date</div></td>
          <td width="62"><form:input  type="text" class="toDate" path="toDate" size="16" id="toDate" /></td>
           <td width="111"><div align="center">Run No</div></td>
          <td width="62"><form:input  type="text"  data-maxsize="16" onKeyUp="valid(this)" onBlur="valid(this)" path="runNo" size="16" id="runNo" /></td>
          <td width="144"><div align="center">Operator Name</div></td>
          <td width="162"><form:input type="text" onKeyUp="valid(this)" onBlur="valid(this)" path="operatorName" size="16" id="operatorName" /></td>

          <td width="80"><input class="searchbtn" style="font-size: 11px; font-weight: bold;  padding: 0 0 0 20px;" type="submit" value=" "/></td>
          <td width="94">
          <a href="show_melter_log_book" class="cancelbtn"
			iconCls="icon-cancel" > </a>
          </td>
        </tr>
      </table>
	</div>
	<div class="gridheadingdiv" >
	 <table width="972" class="display fixmyheader-8" id="example">
  <thead>

   <tr>
         <td class="View" width="55"><div align="center">View</div></td>
       <td class="Med" width="115"><div align="center">MelterLog Date</div></td>
          <td class="Rn" width="115"><div align="center">Run No</div></td>
          <td class="Time" width="115"><div align="center">Time</div></td>      
          <td class="shift" width="115"><div align="center">Shift</div></td>
          <td class="Optr" width="115"><div align="center">Operator</div></td>
          <td class="Tp" width="115"><div align="center">TotalPwd</div></td>
          <td class="Ac" width="65"><div align="center">Action</div></td>

        </tr>
  </thead>
  <tbody>      <c:forEach items="${melterLogBookList}" var="booklist">
			<tr>
			
			<td style="text-align: center;" width="24">
			<c:url value="get_melter_log_book" var="view_url">
			<c:param name="sno" value="${booklist.sno}"></c:param>
			<c:param name="opr" value="V"></c:param>
		  </c:url>
          <a href="${view_url}"><img src="static/images/view_icon.png" title="View Record" alt="" /></a></td>
            
 
			
				<td width="105"><fmt:formatDate pattern="dd-MMM-yyyy"  value="${booklist.logDate}" /></td>
				 <td width="105" style="text-align: right;"  >&nbsp;<c:out value="${booklist.runNo}"/></td>
				<td width="105"><fmt:formatDate pattern="HH:mm" type="time" value="${booklist.logTime}" /></td>
				<td width="105">&nbsp;<c:out value="${booklist.mastersDto.name}"/></td>
				<td width="105">&nbsp;<c:out value="${booklist.operatorName}"/></td>
				<td width="105" style="text-align: right;" >&nbsp;<c:out value="${booklist.totalPower}"/></td>
				

				
				<td width="56" style="text-align: center" >
         			<c:url value="get_melter_log_book" var="remove_url">
					<c:param name="sno" value="${booklist.sno}"></c:param>
					<c:param name="opr" value="R"></c:param>
					</c:url>
					<c:url value="get_melter_log_book"  var="edit_url">
					<c:param name="sno" value="${booklist.sno}"></c:param>
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
  
 </form:form>
 <!-- </form>-->


    
    
