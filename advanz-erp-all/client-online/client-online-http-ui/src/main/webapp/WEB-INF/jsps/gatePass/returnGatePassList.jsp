<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false"%>
<html>

  <title>Return Gate Pass List</title>
  	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
  <script type="text/javascript">
  function checkApproved(){
		alert("You can not edit / delete this record as it is already approved.");
	}
      $(document).ready(function()
       {
    	  
       $("button").button();
    	  //$('input:text, input:password').button()   
	  	$( ".datepicker" ).datepicker({ dateFormat: 'dd-mm-yy' });
  //      $(":submit").button()
      });
</script>
<c:if test="${returnGetPassForm.message!=null && returnGetPassForm.message!=',show'}">
<input type="hidden" name="message" value="${returnGetPassForm.message}" id="message"/>
<script type="text/javascript">
var delUrl='show_return_get_pass_list';
$(document).ready(function() {
alert('Record '+$("#message").val()+' Successfully !!!!');
window.self.location = delUrl;
  });
 	</script>
</c:if>
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
	.View{width:45px !important; border:none!important}
	.SNo{width:40px !important; border:none!important}
	.ReturnNumber{width:120px !important; border:none!important}
	.ReturnDate{width:120px !important; border:none!important}
	.ReturnBy{width:120px !important; border:none!important}
	.Department{width:120px !important; border:none!important}
	.TotalItems{width:60px !important; border:none!important}
	.Ac{width:62px !important; border:none!important}			
	
th{font-size:10px;}
 td{font-size:12px;}  
	div.t_fixed_header_main_wrapper.ui div.t_fixed_header_caption {
		display:none;
		}
</style>
<script type="text/javascript">
function printReturnGatePass(ele){
	var url = "gate_pass_return_print_report/pdf?ReturnGatePassNoPrompt="+ele;
	window.open(url);
}
</script>
<form:form name="input" action="submitReturnGetPassList" class="formdiv" method="get" modelAttribute="returnGetPass"  >
    <div class="header">Return Gate Pass List</div> 
	<div class="headingdiv">
	  <table width="1002" height="31" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td width="79"><input type="submit" class="addbtn"
					name="operation" value="" onclick="this.value='Add';" /></td>
          <td width="78"><input class="exportbtn" style="font-size: 11px; font-weight: bold;  padding: 0 0 0 38px;" type="button" value=""/> </td>
          <td width="87"><div align="center">Pass number</div></td>
          <td width="51">
     <form:input path="returnGetPassMasterDTO.returnGatePassNumber" size="10" id="sonumber" /></td>
          <td width="57"><div align="center">Date</div></td>
          <td width="56"><form:input path="returnGetPassMasterDTO.returnGatePassDate" class="datepicker" id="date"  size="11" /></td>
          <td width="95"><div align="center">Party Name</div></td>
          <td width="61"><form:input path="returnGetPassMasterDTO.partyDTO.partyName"  size="10" id="partyName2" /></td>
          <td width="77">Item Name</td>
          <td width="72"><form:input path="returnGetPassMasterDTO.itemName" size="10" id="partyName" /></td>
          <td width="75"><input class="searchbtn" style="font-size: 11px; font-weight: bold;  padding: 0 0 0 20px;" type="submit" name="operation" value="Search"/></td>
          <td width="92"><input  style="font-size: 11px; font-weight: bold; border:0px;  padding: 0 0 0 10px;" class="cancelbtn"
   type="submit" name="operation" value="Cancel"/></td>
        </tr>
      </table>
</div>
<div id="demo">
	<div class="gridheadingdiv">
	  <table width="972" class="display fixmyheader-8" id="example">
	   <thead>
        <tr><td class="View"><div align="center">View</div></td>
          <td class="SNo"><div align="center">S No.</div></td>
          <td class="ReturnNumber"><div align="center">Re. Gate Pass Number</div></td>
          <td class="ReturnDate"><div align="center">Re. Gate Pass Date</div></td>
          <td width="190"><div align="center">Party Name</div></td>
          <td width="165"><div align="center">Received by</div></td>
          <td width="70"><div align="center">Total Items</div></td>
          <td width="70"><div align="center">Action</div></td>
        </tr></thead>
        <tbody>
		<c:forEach items="${returnGetPassList}" var="gp" varStatus="s">
        <tr>
        <td style="text-align: center;" width="28">
		<c:url value="show_return_get_pass" var="view_url">
		<c:param name="returnGatePassAutoId" value="${gp.returnGatePassAutoId}"></c:param>
		<c:param name="returnGatePassNumber" value="${gp.returnGatePassNumber}"></c:param>
		<c:param name="operation" value="V"></c:param>
		</c:url>
		<a href="${view_url}"><img src="static/images/view_icon.png" title="View Record" alt="" /></a>
        </td>
        <td width="26">&nbsp;<c:out value="${s.count}" /></td>
        <td width="82">&nbsp;<c:out value="${gp.returnGatePassNumber}" /></td>
        <td width="94">&nbsp;<fmt:formatDate pattern="dd-MMM-yyyy" value="${gp.returnGatePassDate}" /></td>
        <td width="150">&nbsp;<c:out value="${gp.partyDTO.partyName}" /></td>
        <td width="128">&nbsp;<c:out value="${gp.returnGatePassReceivedBy}" /></td>
        <td width="50">&nbsp;<c:out value="${gp.returnGetPassDetailDTOList.size()}" /></td>
        <td width="50">
        <c:url value="show_return_get_pass" var="remove_url">
		<c:param name="returnGatePassAutoId" value="${gp.returnGatePassAutoId}"></c:param>
		<c:param name="operation" value="Delete"></c:param>
		</c:url>
		<c:url value="show_return_get_pass" var="edit_url">
		<c:param name="returnGatePassAutoId" value="${gp.returnGatePassAutoId}"></c:param>
		<c:param name="returnGatePassNumber" value="${gp.returnGatePassNumber}"></c:param>
		<c:param name="operation" value="Edite"></c:param>
		</c:url>
		<img onclick="printReturnGatePass('<c:out value="${gp.returnGatePassNumber}" />');" src="static/images/print_icon.png" title="Print Return Gate Pass" alt="" />
		<c:forEach items="${roleAndRightsDTOList}" var="roleAndRights">
		<input type="hidden"  id="addFlag" value="${roleAndRights.addFlag}" >
		<c:if test="${roleAndRights.menuId==sessionScope.menuId}">
		<input type="hidden"  id="addFlag" value="${roleAndRights.addFlag}" >
		<c:if test="${roleAndRights.editFlag=='true'}">
		<c:choose>
		<c:when test="${gp.approved=='1'}">
		<img	onclick="checkApproved();" src="static/images/change_btn.png" title="Edit Record" alt="" />
		</c:when><c:otherwise>
		<a id="editUrlId" href="${edit_url}"><img	 src="static/images/change_btn.png" title="Edit Record" alt="" /></a>
		</c:otherwise>
		</c:choose>
		</c:if>
		<c:if test="${roleAndRights.editFlag=='false'}">
		<img onclick="checkEdit();" src="static/images/change_btn.png" title="Edit Record" alt="" />
		</c:if>
		<c:if test="${roleAndRights.deleteFlag=='true'}">
		<c:choose>
		<c:when test="${gp.approved=='1'}">
		<img onclick="checkApproved();" src="static/images/drop.png" title="Edit Record" alt="" />
		</c:when><c:otherwise>
		<a href="${remove_url}"><img src="static/images/drop.png" title="Delete Record"	alt="" /></a>
		</c:otherwise>
		</c:choose>
        </c:if>
        <c:if test="${roleAndRights.deleteFlag=='false'}">
		<img onclick="checkDelete();" src="static/images/drop.png" title="Delete Record"	alt="" />
        </c:if>
        </c:if>
        </c:forEach>		
        </td>
        </tr></c:forEach></tbody>
        </table>
	    </div></div>
	    
<div style="float: right;"><c:url value="show_return_get_pass_list" var="remove_url">
<c:param name="next" value="${returnGetPassForm.next+(15)}"></c:param>
</c:url> <a href="${remove_url}" class="nextbtn" ></a>
</div>
<div style="float: right;">
<c:url value="show_return_get_pass_list" var="remove_url">
<c:param name="next" value="${returnGetPassForm.previous-(15)}"></c:param>
</c:url> <a href="${remove_url}" class="previousbtn" ></a>
</div>
        </form:form>
        </body>
        </html>