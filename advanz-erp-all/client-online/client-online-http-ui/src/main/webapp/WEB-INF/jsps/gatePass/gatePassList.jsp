<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false"%>
<html>
<title>Gate Pass List</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript">
	function checkApproved() {
		alert("You can not edit / delete this record as it is already approved.");
	}
	$(document).ready(function() {

		$("button").button();
		//$('input:text, input:password').button()   
		$(".datepicker").datepicker({
			dateFormat : 'dd-mm-yy'
		});
		//      $(":submit").button()
	});
</script>
<c:if test="${getPassForm.message!=null && getPassForm.message!=',show'}">
<input type="hidden" name="message" value="${getPassForm.message}" id="message"/>
  <script type="text/javascript">
  var delUrl='show_get_pass_list';
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
<script type="text/javascript">
function printGatePass(ele){
	 var url = "gate_pass_print_report/pdf?GatePassNoPrompt="+ele;
	window.open(url);
	
}
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
	.View{width:40px !important; border:none!important}
	.SNo{width:40px !important; border:none!important}
	.GatePassType{width:92px !important; border:none!important}
	.GatePassNumber{width:80px !important; border:none!important}
	.GatePassDate{width:90px !important; border:none!important}
	.PartyName{width:105px !important; border:none!important}
	.IssueIncharge{width:115px !important; border:none!important}
	.VehicleNo{width:65px !important; border:none!important}
	.TotalItems{width:60px !important; border:none!important}
	.Ac{width:62px !important; border:none!important}			
	
th{font-size:10px;}
 td{font-size:12px;}  
 
 	
	div.t_fixed_header_main_wrapper.ui div.t_fixed_header_caption {
		display:none;
		
		}
	
</style>



<form:form name="input" action="submitGetPassList" class="formdiv" method="get" modelAttribute="getPass">
	<div class="header">Gate Pass List</div>
	<div class="headingdiv">
		<table width="100%" height="31" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				<td width="79">
				<a onclick="return checkAdd();" href="submitGetPassList?operation=Add" class="addbtn"></a>
				</td>
				
				<td width="40"><div align="center">Pass no</div>
				</td>
				<td width="30"><form:input
						path="getPassMasterDTO.gatePassNumber" size="6" id="sonumber" />
				</td>
				<td width="40"><div align="center">From Date</div>
				</td>
				<td width="40"><form:input path="getPassMasterDTO.gatePassDate"
						class="datepicker" id="date1" size="5" />
				</td>
				
				<td width="40"><div align="center">To Date</div>
				</td>
				<td width="40"><form:input path="getPassMasterDTO.toDate"
						class="datepicker" id="date" size="5" />
				</td>
				<td width="95"><div align="center">Party Name</div>
				</td>
				<td width="61"><form:input
						path="getPassMasterDTO.partyDTO.partyName" size="8"
						id="partyName2" />
				</td>
				<td width="77">Item Name</td>
				<td width="72"><form:input path="getPassMasterDTO.itemName"
						size="10" id="partyName" />
				</td>
				
				 <td width="86" height="33">Get Pass Type<span style="color: #FF0000"></span></td>
      <td width="250" ><form:select path="getPassMasterDTO.gatePassType" style="width:100%" class="validate[required] text-input"  id="gatePassType" >
       <form:option value="">All</form:option>
       <form:option value="Returnable">Returnable</form:option>
       <form:option value="Non Returnable">Non Returnable</form:option>
      </form:select></td>
				<td width="75"><input class="searchbtn"
					style="font-size: 11px; font-weight: bold; padding: 0 0 0 20px;"
					type="submit" name="operation" value="Search" />
				</td>
				<td width="92"><input
					style="font-size: 11px; font-weight: bold; border: 0px; padding: 0 0 0 10px;"
					class="cancelbtn" type="submit" name="operation" value="Cancel" />
				</td>
			</tr>
		</table>
	</div>
	<div id="demo">
	<div class="gridheadingdiv">
	  <table width="972" class="display fixmyheader-8" id="example">
	   <thead>
			<tr>
				<td class="View"><div align="center">View</div>
				</td>
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
				<td class="Ac"><div align="center">Action</div>
				</td>
			</tr></thead>
			<tbody>
				<c:forEach items="${getPassList}" var="gp" varStatus="s">
					<tr>
						<td style="text-align: center;" width="30"><c:url
								value="show_get_pass" var="view_url">
								<c:param name="gatePassAutoId" value="${gp.gatePassAutoId}"></c:param>
								<c:param name="gatePassNumber" value="${gp.gatePassNumber}"></c:param>
								<c:param name="operation" value="V"></c:param>
							</c:url> 
								<a href="${view_url}"><img src="static/images/view_icon.png"
									title="View Record" alt="" />
								</a>
							</td>
						<td width="30">&nbsp;<c:out value="${s.count}" />
						</td>
						<td width="80">&nbsp;<c:out value="${gp.gatePassType}" />
						</td>
						<td width="80">&nbsp;<c:out value="${gp.gatePassNumber}" />
						</td>
						<td width="80">&nbsp;<fmt:formatDate pattern="dd-MMM-yyyy"
								value="${gp.gatePassDate}" />
						</td>
						<td width="100">&nbsp;<c:out value="${gp.partyDTO.partyName}" />
						</td>
						<td width="100">&nbsp;<c:out value="${gp.gatePassIssuedBy}" />
						</td>
						<td width="50">&nbsp;<c:out value="${gp.vehicalNumber}" />
						</td>
						<td width="52">&nbsp;<c:out value="${gp.getPassDetailDTOList.size()}" /></td>
						<td width="55"><c:url value="show_get_pass" var="remove_url">
								<c:param name="gatePassAutoId" value="${gp.gatePassAutoId}"></c:param>
								<c:param name="operation" value="Delete"></c:param>
							</c:url> <c:url value="show_get_pass" var="edit_url">
								<c:param name="gatePassAutoId" value="${gp.gatePassAutoId}"></c:param>
								<c:param name="gatePassNumber" value="${gp.gatePassNumber}"></c:param>
								<c:param name="operation" value="Edite"></c:param>
							</c:url> 
							<img	onclick="printGatePass('<c:out value="${gp.gatePassNumber}" />');" src="static/images/print_icon.png" title="Print Gate Pass" alt="" />
							<c:forEach items="${roleAndRightsDTOList}" var="roleAndRights">
								<input type="hidden" id="addFlag"
									value="${roleAndRights.addFlag}">
								<c:if test="${roleAndRights.menuId==sessionScope.menuId}">
									<input type="hidden" id="addFlag"
										value="${roleAndRights.addFlag}">
									<c:if test="${roleAndRights.editFlag=='true'}">
										<c:choose>
											<c:when test="${gp.approved=='1'}">
												<img onclick="checkApproved();"
													src="static/images/change_btn.png" title="Edit Record"
													alt="" />
											</c:when>
											<c:otherwise>
												<a id="editUrlId" href="${edit_url}"><img
													src="static/images/change_btn.png" title="Edit Record"
													alt="" />
												</a>
											</c:otherwise>
										</c:choose>
									</c:if>
									<c:if test="${roleAndRights.editFlag=='false'}">
										<img onclick="checkEdit();" src="static/images/change_btn.png"
											title="Edit Record" alt="" />
									</c:if>
									<c:if test="${roleAndRights.deleteFlag=='true'}">
										<c:choose>
											<c:when test="${gp.approved=='1'}">
												<img onclick="checkApproved();" src="static/images/drop.png"
													title="Edit Record" alt="" />
											</c:when>
											<c:otherwise>
												<a href="${remove_url}"><img
													src="static/images/drop.png" title="Delete Record" alt="" />
												</a>
											</c:otherwise>
										</c:choose>
									</c:if>
									<c:if test="${roleAndRights.deleteFlag=='false'}">
										<img onclick="checkDelete();" src="static/images/drop.png"
											title="Delete Record" alt="" />
									</c:if>
								</c:if>
							</c:forEach></td>


					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div></div>

<div style="float: right;"><c:url value="show_get_pass_list" var="remove_url">
<c:param name="next" value="${getPassForm.next+(15)}"></c:param>
</c:url> <a href="${remove_url}" class="nextbtn" ></a>
</div>
<div style="float: right;">
<c:url value="show_get_pass_list" var="remove_url">
<c:param name="next" value="${getPassForm.previous-(15)}"></c:param>
</c:url> <a href="${remove_url}" class="previousbtn" ></a>
</div>


</form:form>
</body>
</html>