<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false"%>
<html>
  <title>Gate Pass List</title>
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
 	<script>
	$(document).ready(function() {
		$(function() {
			$('.sr').click(function() {
				$('.sr').css("background-color","white");
				$(this).css("background-color","yellow");
				
				$('#getPassNumber').val($(this).attr('id'));
				
			    });
			
			
			});
			});
function submitField(ele){
		$("#partyName2").val(ele);
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
	.SNo{width:40px !important; border:none!important}
	.GatePassType{width:92px !important; border:none!important}
	.GatePassNumber{width:80px !important; border:none!important}
	.GatePassDate{width:90px !important; border:none!important}
	.PartyName{width:105px !important; border:none!important}
	.IssueIncharge{width:115px !important; border:none!important}
	.VehicleNo{width:65px !important; border:none!important}
	.TotalItems{width:60px !important; border:none!important}	
	
th{font-size:10px;}
 td{font-size:12px;}  
 
 	
	div.t_fixed_header_main_wrapper.ui div.t_fixed_header_caption {
		display:none;
		
		}
	
</style>

  
<form:form name="input" action="submitGetPassList" class="formdiv" method="get" modelAttribute="getPass"  >
    <div class="header"> Gate Pass List For Return</div> 
	<div class="headingdiv">
	  <table width="1002" height="31" border="0" cellpadding="0" cellspacing="0">
        <tr>
           
          <td width="87"><div align="center">Pass number</div></td>
          <td width="51">
     <form:input path="getPassMasterDTO.gatePassNumber" size="10" id="getPassNumber" /></td>
          <td width="57"><div align="center">From Date</div></td>
          <td width="56"><form:input path="getPassMasterDTO.gatePassDate" class="datepicker" id="date"  size="11" /></td>
          
          <td width="57"><div align="center">To Date</div></td>
          <td width="56"><form:input path="getPassMasterDTO.toDate" class="datepicker" id="toDate"  size="11" /></td>
          
          
          <td width="95"><div align="center">Party Name</div></td>
          <td width="61"><form:input path="getPassMasterDTO.partyDTO.partyName"  size="10" id="partyName1" />
          <form:hidden path="getPassMasterDTO.partyDTO.partyId"  size="10" id="partyName2" />
          </td>
          <td width="77">Item Name</td>
          <td width="72"><form:input path="getPassMasterDTO.itemName" size="10" id="partyName" /></td>
          <td width="75"><input class="searchbtn" style="font-size: 11px; font-weight: bold;  padding: 0 0 0 20px;" type="submit" name="operation" value="SearchGetPassReturn"/></td>
          <td width="79"><input type="submit" class="okbtn" name="operation" value="" onclick="this.value='Add Item In Return Gate';" /></td>
          <td width="92"><input  style="font-size: 11px; font-weight: bold; border:0px;  padding: 0 0 0 10px;" type="submit"  name="operation" value="cancelReturnGatePass" class="cancelbtn"
    type="reset" value=" "/></td>
        </tr>
      </table>
</div>
	<div id="demo">
	<div class="gridheadingdiv">
	  <table width="972" class="display fixmyheader-8" id="example">
	   <thead>
        <tr>
          <td class="SNo"><div align="center">S No.</div>
				</td>
				<td class="GatePassType"><div align="center">Gate Pass Type</div>
				</td>
				<td class="GatePassNumber"><div align="center">Gate Pass Number</div>
				</td>
				<td class="GatePassDate"><div align="center">Gate Pass Date</div>
				</td>
				<td class="PartyName"><div align="center">Party Name</div>
				</td>
				<td class="IssueIncharge"><div align="center">Issued by (Incharge)</div>
				</td>
				<td class="VehicleNo"><div align="center">Vehical No.</div>
				</td>
				<td class="TotalItems"><div align="center">Total Items</div>
				</td>
        </tr></thead>
        <tbody>
				<c:forEach items="${getPassList}" var="gp" varStatus="s">
        <tr class="sr" onclick="submitField(${gp.partyDTO.partyId})" style="cursor: pointer;" id="${gp.gatePassNumber}">
          <td width="30">&nbsp;<c:out value="${s.count}" /></td>
          <td width="80">&nbsp;<c:out value="${gp.gatePassType}" /></td>
          <td width="80">&nbsp;<c:out value="${gp.gatePassNumber}" /></td>
          <td width="80">&nbsp;<fmt:formatDate pattern="dd-MMM-yyyy" value="${gp.gatePassDate}" /></td>
          <td width="100">&nbsp;<c:out value="${gp.partyDTO.partyName}" /></td>
          <td width="100">&nbsp;<c:out value="${gp.gatePassIssuedBy}" /></td>
          <td width="50">&nbsp;<c:out value="${gp.vehicalNumber}" /></td>
          <td width="52">&nbsp;<c:out value="${gp.getPassDetailDTOList.size()}" /></td>
        </tr></c:forEach></tbody>
      </table>
	</div></div>
    
    
    
<div style="float: right;"><c:url value="show_get_pass_list" var="remove_url">
<c:param name="next" value="${getPassForm.next+(15)}"></c:param>
<c:param name="operation" value="GatePassNo"></c:param>
</c:url> <a href="${remove_url}" class="nextbtn" ></a>
</div>
<div style="float: right;">
<c:url value="show_get_pass_list" var="remove_url">
<c:param name="next" value="${getPassForm.previous-(15)}"></c:param>
<c:param name="operation" value="GatePassNo"></c:param>
</c:url> <a href="${remove_url}" class="previousbtn" ></a>
</div>

  
</form:form>
 </body>
</html>