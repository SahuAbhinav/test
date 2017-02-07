<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="true" %>
<script type="text/javascript" charset="utf-8">
$(document).ready(function(){ 
 $(".datepicker[readonly]").css("background-color","#ffffff" );
 
 $(".fromDate" ).datepicker({dateFormat : 'dd-M-yy',changeMonth: true,
     changeYear: true, yearRange: '-99:+0'});
$(".toDate" ).datepicker({dateFormat : 'dd-M-yy',changeMonth: true,
     changeYear: true, yearRange: '-99:+0'});
} );
</script>
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
 .View{width:34px !important; border:none!important}
 .Melt{width:115px !important; border: none !important; }
.Time{width:115px !important; border: none !important;}
.Kwh{width:115px !important; border: none !important; }
.water{width:115px !important; border: none !important; }
.Regu{width:115px !important; border: none !important; }
.Trans{width:115px !important; border: none !important; }
.Ac{width:64px !important; border: none !important; }
th{font-size:10px; border:  }
 td{font-size:12px; border:  }  
 
 	.ui-widget-content {
overflow-x: hidden !important;
 
}	
	div.t_fixed_header_main_wrapper.ui div.t_fixed_header_caption {
		display:none;
		
		}
	
</style>
<%@ page isELIgnored="false" %>

<c:if test="${melterSummaryForm.succ=='Ad'}">
  <script type="text/javascript">
  var delUrl='show_melter_summary_form';
	$(document).ready(function() {
     alert('Record Inserted Successfully !!!!');
     window.self.location = delUrl;
	});
 	</script>
</c:if>

<c:if test="${succ=='Blk'}">
  <script type="text/javascript">
  	var delUrl='show_melter_summary_form';
  	$(document).ready(function() {
      alert('No Record Found !!!!');
    //  window.self.location  = delUrl;
	});
 	</script>
</c:if>


<c:if test="${melterSummaryForm.succ=='Dl'}">
  <script type="text/javascript">
  var delUrl='show_melter_summary_form';
  $(document).ready(function() {
     alert('Record Deleted Successfully !!!!');
     window.self.location = delUrl;
	});
 	</script>
</c:if>

<c:if test="${melterSummaryForm.succ=='Up'}">
  <script type="text/javascript">
  var delUrl='show_melter_summary_form';
	$(document).ready(function() {
     alert('Record Updated Successfully !!!!');
     window.self.location = delUrl;
	});
 	</script>
</c:if>

 <c:if test="${not empty(errorList)}">
 <input type="hidden" id="errorId" value="${errorList.errorMsg}">
	<script type="text/javascript">
		var delUrl='show_melter_summary_form';
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

   <form:form name="input" action="get_melter_summary_form" class="formdiv" method="post" modelAttribute="melterSummaryForm" >
		     
    <div class="header">Melter Log Summary List </div> 
	<div class="headingdiv">
	  <table width="880"  height="31" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td width="70"><a onclick="return checkAdd();" href="show_new_melter_summary" class="addbtn" iconCls="icon-add"></a></td>
          <td width="233"><a href="#" class="exportbtn" iconCls="icon-redo" onClick="javascript:$('#dlg').dialog('close')"></a></td>
         <%--  <td width="101"><div align="center">Melter Log Date</div></td>
          <td width="262"><form:input  type="text" class="datepicker"  readonly="true" path="logDate" size="16" id="logDate" /></td> --%>
           <td width="101" nowrap="nowrap"><div align="center">From Log Date</div></td>
          <td width="262"><form:input  type="text" class="fromDate"  readonly="true" path="fromDate" size="16" id="fromDate" /></td>
           <td width="101" nowrap="nowrap"><div align="center">To Log Date</div></td>
          <td width="262"><form:input  type="text" class="toDate"  readonly="true" path="toDate" size="16" id="toDate" /></td>
          <td width="45"><input  class="searchbtn"  type="submit" value=""/></td>
      <td width="45">
      <a href="show_melter_summary_form"  class="cancelbtn" iconCls="icon-cancel"></a> 
      </td>
      </tr>
      </table>
	</div>
	<div class="gridheadingdiv">
	 <table width="972" class="display fixmyheader-8" id="example">
  <thead>
   <tr>

 
		       <td class="View" width="55"><div align="center">View</div></td>
          <td class="Melt" widtd="115"><div align="center"><div>MelterLog Date</div></div></td>
          <td class="Time" widtd="115"><div align="center"><div>Time</div></div></td>
          <td class="Kwh" widtd="115"><div align="center">KWH</div></td>      
          <td class="water" widtd="115"><div align="center"><div>Water Temp.</div></div></td>
          <td class="Regu" widtd="115"><div align="center"><div>Regulator Temp.</div></div></td>
          <td class="Trans" widtd="115"><div align="center"><div>Transformer Temp.</div></div></td>
          <td class="Ac" widtd="65"><div align="center">Action</div></td>
        </tr>
  </thead>
  <tbody> 
        <c:forEach items="${melterLogSummaryList}" var="summaryList">
			<tr>
			
				<td style="text-align: center;" width="24">
			<c:url value="get_melter_log_summary" var="view_url">
			<c:param name="sno" value="${summaryList.sno}"></c:param>
			<c:param name="opr" value="V"></c:param>
		  </c:url>
          <a href="${view_url}"><img src="static/images/view_icon.png" title="View Record" alt="" /></a></td>
            
 
				<td width="105"><fmt:formatDate pattern="dd-MMM-yyyy"  value="${summaryList.logDate}" /></td>
				<td width="105"><fmt:formatDate pattern="HH:mm" type="time" value="${summaryList.logTime}" /></td>
				 <td width="105" style="text-align: right;" >&nbsp;<c:out value="${summaryList.logKwh}"/></td>
				
				<td width="105" style="text-align: right;" >&nbsp;<c:out value="${summaryList.waterTemp}"/></td>
				<td width="105" style="text-align: right;" >&nbsp;<c:out value="${summaryList.regulerTemp}"/></td>
				<td width="105" style="text-align: right;" >&nbsp;<c:out value="${summaryList.transformerTemp}"/></td>
				
				<td width="64" style="text-align: center; " >
         			<c:url value="get_melter_log_summary" var="remove_url">
					<c:param name="sno" value="${summaryList.sno}"></c:param>
					<c:param name="opr" value="R"></c:param>
					</c:url>
					<c:url value="get_melter_log_summary"  var="edit_url">
					<c:param name="sno" value="${summaryList.sno}"></c:param>
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
 