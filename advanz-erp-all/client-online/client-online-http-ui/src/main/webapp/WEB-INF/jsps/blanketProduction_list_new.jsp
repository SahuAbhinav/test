<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false"%>
<%@ page session="true"%>
<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {
		$(".datepicker[readonly]").css("background-color", "#ffffff");
	});
</script>
<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {
		/* Initialise datatables */
		var oTable = $('#example').dataTable({
			bInfo : "",
			"bPaginate" : false
		});

	});
</script>
<c:if test="${succ=='Blk'}">
	<script type="text/javascript">
		var delUrl = 'get_blanketProduction_list_new';
		$(document).ready(function() {
			alert('No Record Found !!!!');
			//window.self.location = delUrl;
		});
	</script>
</c:if>

<c:if test="${blanketProductionMasterForm.succ=='Ad'}">

	<script type="text/javascript">
		var delUrl = 'get_blanketProduction_list_new';
		$(document).ready(function() {
			alert('Record Inserted Successfully !!!!');
			window.self.location = delUrl;
		});
	</script>
</c:if>

<c:if test="${blanketProductionMasterForm.succ=='Dl'}">
	<script type="text/javascript">
		var delUrl = 'get_blanketProduction_list_new';
		$(document).ready(function() {
			alert('Record Deleted Successfully !!!!');
			window.self.location = delUrl;
		});
	</script>
</c:if>

<c:if test="${blanketProductionMasterForm.succ=='Up'}">

	<script type="text/javascript">
		var delUrl = 'get_blanketProduction_list_new';
		$(document).ready(function() {
			alert('Record Updated Successfully !!!!');
			window.self.location = delUrl;
		});
	</script>
</c:if>



<c:if test="${not empty(errors)}">
	<input type="hidden" id="errorId" value="${errors.errorMsg}">
	<script type="text/javascript">
		var delUrl = 'get_blanketProduction_list_new';
		$(document).ready(function() {
			var errorId = document.getElementById('errorId');
			alert(errorId.value);
			// alert('Branch can not be Removed this Field is using in other Form');
			document.location = delUrl;
		});
	</script>
</c:if>


<script type="text/javascript">
	function checkEdit() {
		alert('Login User Not Permit to Edit Record OR It Is Approved!!!!!!');
	}

	function checkDelete() {
		alert('Login User Not Permit to Delete Record OR It Is Approved!!!!!!');
	}

	function checkAdd() {
		var adId = document.getElementById('addFlag').value;
		if (adId == 'true') {
			return true;
		} else {
			alert('Login User Not Permit to Add Record !!!!!!');
			return false;
		}
	}
</script>


<script type="text/javascript">
	$(document).ready(function() {



$('.fixmyheader-8').fixheadertable({
							caption : 'My header is fixed !',
							height : 412,
							addTitles : true,
							colratio : [ '10%', '10%', '8%', '50px', 'auto',
									'auto', '30%', 'auto' ]
						});
			});
</script>


<style type="text/css">
body {
	font-family: Arial, Helvetica, sans-serif;
}

code,pre {
	padding: 10px;
	background: #F5F5F5;
	border: 1px solid #D4D4D4;
	overflow-x: auto;
	font-size: 12px;
}

.ui-widget-content {
	overflow-x: hidden !important;
}

th {
	font-size: 10px;
}

td {
	font-size: 12px;
}

.View {
	width: 34px !important;
	border: none !important
}

.Sn {
	width: 45px !important;
	border: none !important;
}

.date {
	width: 75px !important;
	border: none !important;
}

.Run {
	width: 70px !important;
	border: none !important;
}

.Grade {
	width: 70px !important;
	border: none !important;
}

.Shift {
	width: 75px !important;
	border: none !important;
}

.Se {
	width: 100px !important;
	border: none !important;
}

.TotL {
	width: 85px !important;
	border: none !important;
}

.TotR {
	width: 85px !important;
	border: none !important;
}

.TotWR {
	width: 85px !important;
	border: none !important;
}

.TotWL {
	width: 85px !important;
	border: none !important;
}

.Ac {
	width: 50px !important;
	border: none !important;
}

div.t_fixed_header_main_wrapper.ui div.t_fixed_header_caption {
	display: none;
}
</style>

<script>
	$(function() {
		$(".datepicker").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : 'dd-M-yy',
			yearRange : '-99:+10'
		});

		$(".fromDate").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : 'dd-M-yy',
			yearRange : '-99:+10'
		});
		$(".toDate").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : 'dd-M-yy',
			yearRange : '-99:+10'
		});
	});
</script>


<form:form name="input" action="get_blanketProduction_list_new"
	class="formdiv" modelAttribute="bpmSearchCriteria">

	<div class="header">Blanket Production New List</div>
	<div class="headingdiv">
		<table width="880" height="31" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				<td width="45"><a onclick="return checkAdd();"
					href="new_blanketProduction_new" class="addbtn"></a>
				</td>
				<td width="45"><input class="exportbtn"
					style="font-size: 11px; font-weight: bold; padding: 0 0 0 38px;"
					type="button" value="" /></td>
				<%-- <td width="90"><div align="center">Date</div></td>
<td width="64"><form:input path="productionDate" class="datepicker" id="logDate" size="16" readonly="true"/></td> --%>

				<td width="90"><div align="center">From Date</div>
				</td>
				<td width="64"><form:input path="fromDate" class="fromDate"
						id="fromDate" size="16" />
				</td>

				<td width="90"><div align="center">To Date</div>
				</td>
				<td width="64"><form:input path="toDate" class="toDate"
						id="toDate" size="16" />
				</td>
				<td width="78"><div align="center">Run No.</div>
				</td>
				<td width="65"><form:input path="runNo"
						onkeypress="return check(event)" size="16" id="runNo" />
				</td>
				<td width="68"><div align="center">Grade</div>
				</td>
				<td width="113"><form:select path="gradeId" id="grade">
						<form:option value="">Select</form:option>
						<form:options items="${gradeList}" itemLabel="name"
							itemValue="mastersId" />
					</form:select></td>
				<td width="45"><input class="searchbtn"
					style="font-size: 11px; font-weight: bold; padding: 0 0 0 20px;"
					type="submit" name="operation" value="Search" />
				</td>
				<td width="45"><a href="get_blanketProduction_list_new"
					class="cancelbtn"></a>
				</td>
			</tr>
		</table>
	</div>
	<div class="gridheadingdiv">
		<table width="972" class="display fixmyheader-8" id="example">
			<thead>
				<tr>
					<td class="View" width="55"><div align="center">View</div>
					</td>
					<td class="Sn" width="48"><div align="center">S No.</div>
					</td>
					<td class="date" width="70"><div align="center">Date</div>
					</td>
					<td class="Run" width="70"><div align="center">Run No</div>
					</td>
					<td class="Grade" width="70"><div align="center">Grade</div>
					</td>
					<td class="Shift" width="70"><div align="center">Shift</div>
					</td>
					<td class="Se" width="115"><div align="center">Shift
							Engineer</div>
					</td>
					<td class="Se" width="115"><div align="center">Splited Counts</div>
					</td>
					<td class="TotL" width="80"><div align="center">Tot
							Item</div>
					</td>
					
					<td class="TotL" width="80"><div align="center">Tot
							Wght</div>
					</td>
					
					<td class="Ac" width="65"><div align="center">Action</div>
					</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${bpmList}" var="bpm" varStatus="s">
					<tr>
						<td style="text-align: center;" width="24"><c:url
								value="get_blanketProduction_new" var="view_url">
								<c:param name="id" value="${bpm.blanketProductionId}"></c:param>
								<c:param name="opr" value="V"></c:param>
							</c:url> <a href="${view_url}"><img src="static/images/view_icon.png"
								title="View Record" alt="" />
						</a>
						</td>


						<td width="38">&nbsp;${s.count}</td>
						<td width="75">&nbsp; <fmt:formatDate pattern="dd-MMM-yyyy"
								value="${bpm.blanketProductionDate}" /></td>
						<td width="70">&nbsp;${bpm.runNo}</td>
						<td width="70">&nbsp;${bpm.gradeMasterDTO.name}</td>
						<td width="70">&nbsp;${bpm.shiftMasterDTO.name}</td>
						<td width="100">&nbsp;${bpm.shiftEngineerName}</td>
						<td width="100">&nbsp;${bpm.splitedCounts}</td>
						
						<td width="85" style="text-align: right;">&nbsp;${bpm.blanketProductionDetailNewDTOList.size()}</td>
						<td width="85">&nbsp; <fmt:formatNumber
								value="${bpm.blnktWeight}" type="number"
								minFractionDigits="2" maxFractionDigits="2" /></td>
						<td width="62" style="text-align: left;">
				 		<c:if test="${bpm.approveStatus==1}" >
							<img onclick="checkEdit();" src="static/images/change_btn.png"
											title="Edit Record" alt="" />
											<img onclick="checkDelete();" src="static/images/drop.png"
											title="Delete Record" alt="" />
						</c:if>
						<c:if test="${bpm.approveStatus==0 || bpm.approveStatus==null}" > 
						
						<c:url
								value="get_blanketProduction_new" var="remove_url">
								<c:param name="id" value="${bpm.blanketProductionId}"></c:param>
								<c:param name="opr" value="R"></c:param>
							</c:url> 
							
							<c:url value="get_blanketProduction_new" var="edit_url">
								<c:param name="id" value="${bpm.blanketProductionId}"></c:param>
								<c:param name="opr" value="E"></c:param>
							</c:url>
							 <c:forEach items="${roleAndRightsDTOList}" var="roleAndRights">
								<c:if test="${roleAndRights.menuId==sessionScope.menuId}">
									<input type="hidden" id="addFlag"
										value="${roleAndRights.addFlag}">
									<c:if test="${roleAndRights.editFlag=='true'}">
										<a id="editUrlId" href="${edit_url}"><img
											src="static/images/change_btn.png" title="Edit Record" alt="" />
										</a>
									</c:if>
									<c:if test="${roleAndRights.editFlag=='false'}">
										<img onclick="checkEdit();" src="static/images/change_btn.png"
											title="Edit Record" alt="" />
									</c:if>
									<c:if test="${roleAndRights.deleteFlag=='true'}">
										<a href="${remove_url}"><img src="static/images/drop.png"
											title="Delete Record" alt="" />
										</a>
									</c:if>
									<c:if test="${roleAndRights.deleteFlag=='false'}">
										<img onclick="checkDelete();" src="static/images/drop.png"
											title="Delete Record" alt="" />
									</c:if>
								</c:if>
							</c:forEach>
							 </c:if> 
							</td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
	</div>

	<div style="float: right;">
		<c:url value="get_blanketProduction_list_new" var="remove_url">
			<c:param name="next" value="${blanketProductionMasterForm.next+(15)}"></c:param>
		</c:url>
		<a href="${remove_url}" class="nextbtn"></a>
	</div>
	<div style="float: right;">
		<c:url value="get_blanketProduction_list_new" var="remove_url">
			<c:param name="next"
				value="${blanketProductionMasterForm.previous-(15)}"></c:param>
		</c:url>
		<a href="${remove_url}" class="previousbtn"></a>
	</div>
</form:form>
