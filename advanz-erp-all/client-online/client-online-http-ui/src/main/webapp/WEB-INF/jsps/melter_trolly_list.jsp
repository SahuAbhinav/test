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
$(document).ready(function(){ 
 $(".datepicker[readonly]").css("background-color","#ffffff" );
} );
</script>
<script type="text/javascript">
			
			$(document).ready(function() {  	
				
				
				$('.fixmyheader-8').fixheadertable({
					caption		: 'My header is fixed !',
					height		:413,
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
.View{width:34px !important; border:none!important}
	  .Md{width:115px !important; border: none !important;  }
        .time{width:115px !important; border: none !important; }
        .Tn{width:115px !important;  border: none !important; }
        .Ac{width:39px !important; border: none !important; }
th{font-size:10px;}
 td{font-size:12px;}  
 
 	
	div.t_fixed_header_main_wrapper.ui div.t_fixed_header_caption {
		display:none;
		
		}
	
</style>
<c:if test="${melterTrollyForm.succ=='Ad'}">
  <script type="text/javascript">
  var delUrl='show_melter_trolly_form';
	$(document).ready(function() {
     alert('Record Inserted Successfully !!!!');
     window.self.location = delUrl;
	});
 	</script>
</c:if>

<c:if test="${succ=='Blk'}">
  <script type="text/javascript">
  	var delUrl='show_melter_trolly_form';
  	$(document).ready(function() {
      alert('No Record Found !!!!');
      //window.self.location  = delUrl;
	});
 	</script>
</c:if>


<c:if test="${melterTrollyForm.succ=='Dl'}">
  <script type="text/javascript">
  var delUrl='show_melter_trolly_form';
  $(document).ready(function() {
     alert('Record Deleted Successfully !!!!');
     window.self.location = delUrl;
	});
 	</script>
</c:if>

<c:if test="${melterTrollyForm.succ=='Up'}">
  <script type="text/javascript">
  var delUrl='show_melter_trolly_form';
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
           	   
	  $( ".datepicker" ).datepicker({
		  changeMonth: true,
          changeYear: true,
		  yearRange: '-99:+10',
		  dateFormat: 'dd-M-yy' });
	  
	  $( ".fromDate" ).datepicker({
		  changeMonth: true,
          changeYear: true,
		  yearRange: '-99:+10',
		  dateFormat: 'dd-M-yy' });
	  
	  $( ".toDate" ).datepicker({
		  changeMonth: true,
          changeYear: true,
		  yearRange: '-99:+10',
		  dateFormat: 'dd-M-yy' });
  //     
      });
  </script>


   <form:form name="input" action="get_melter_trolly_form" class="formdiv" method="post" modelAttribute="melterTrollyForm" >
		   
    <div class="header">Melter Log Trolly List </div> 
	<div class="headingdiv">
	  <table width="880" height="31" border="0" cellpadding="0" cellspacing="0">
        <tr>
        
          <td width="45"><a onclick="return checkAdd();" href="show_new_melter_trolly" class="addbtn" iconCls="icon-add"></a></td>
          <td width="48"><a href="#" class="exportbtn" iconCls="icon-redo" onClick="javascript:$('#dlg').dialog('close')"></a></td>
         <%--  <td width="90"><div align="center">Melter Log Date</div></td>
          <td width="72"><form:input  type="text" class="datepicker"  readonly="true" path="logDate" size="16" id="logDate" /></td> --%>
          
           <td width="90"><div align="center">From Date</div></td>
          <td width="72"><form:input  type="text" class="fromDate"  readonly="true" path="fromDate" size="16" id="fromDate" /></td>
           <td width="90"><div align="center">To Date</div></td>
          <td width="72"><form:input  type="text" class="toDate"  readonly="true" path="toDate" size="16" id="toDate" /></td>
          <td width="111"><div align="center">Trolly No</div></td>
          <td width="165"><form:input  type="text" onKeyUp="valid(this)" onBlur="valid(this)" data-maxsize="16" path="trollyNumber" size="16" id="trollyNumber" /></td>   
          <td width="45"><input  class="searchbtn"  type="submit" value=""/></td>
          <td width="45"> <a href="show_melter_trolly_form"  class="cancelbtn" iconCls="icon-cancel"></a> 
          </td>
          </tr>
      </table>
	</div>
	<div class="gridheadingdiv">
	                <table width="972" class="display fixmyheader-8" id="example">
      <thead>
       <tr>

      
        <td class="View" width="55"><div align="center">View</div></td>
          <td class="Md" widtd="115"><div align="center"><div>MelterLog Date</div></div></td>
          <td class="time" widtd="115"><div align="center"><div>Time</div></div></td>
          <td class="Tn" widtd="115"><div align="center">Trolly No</div></td>      
          <td class="Ac" widtd="40"><div align="center">Action</div></td>
        </tr>
  </thead>
  <tbody>  
        <c:forEach items="${melterLogTrollyList}" var="trollyList">
			<tr>
				
					<td style="text-align: center;" width="24">
			<c:url value="get_melter_trolly_log" var="view_url">
			<c:param name="sno" value="${trollyList.sno}"></c:param>
			<c:param name="opr" value="V"></c:param>
		  </c:url>
          <a href="${view_url}"><img src="static/images/view_icon.png" title="View Record" alt="" /></a></td>
        
				<td width="105"><fmt:formatDate pattern="dd-MMM-yyyy"  value="${trollyList.logDate}" /></td>
				<td width="105"  ><fmt:formatDate pattern="HH:mm"  type="time" value="${trollyList.logTime}" /></td>
				 <td width="105" style="text-align: right;" >&nbsp;<c:out value="${trollyList.trollyNumber}"/></td>
							
				<td width="34" style="text-align: center;" >
         			<c:url value="get_melter_trolly_log" var="remove_url">
					<c:param name="sno" value="${trollyList.sno}"></c:param>
					<c:param name="opr" value="R"></c:param>
					</c:url>
					<c:url value="get_melter_trolly_log"  var="edit_url">
					<c:param name="sno" value="${trollyList.sno}"></c:param>
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


 