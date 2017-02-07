<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<script>
$(document).ready(function() {
$( ".accordion" ).accordion({
collapsible: true,
active: -1
});
});

</script>
<script type="text/javascript" charset="utf-8">
$(document).ready(function() {
/* Initialise datatables */
var oTable = $('#example').dataTable({
"aLengthMenu": [['',10, 25, 50, -1], ['',10, 25, 50, "All"]],
"iDisplayLength":10000,
"bPaginate": false,
bInfo:""
});
} );
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
<c:if test="${succ=='Blk'}">
	<script type="text/javascript">
var delUrl='get_issue_list';
$(document).ready(function() {
alert('No Record Found !!!!');
//window.self.location = delUrl;
});
</script>
</c:if>
<input type="hidden" name="PrintView" value="${PrintView}"
	id="PrintView">
<%-- <input type="hidden" name="fromDate" value="${issueMasterForms.issueDate}" id="issueDate" > --%>
<c:if test="${issueMasterForms.succ=='Ad'}">
	<script type="text/javascript">
var url = "issue_print_report/pdf";

var delUrl='get_issue_list';
$(document).ready(function() {
alert('Record Inserted Successfully !!!!');
window.self.location = delUrl;
//window.open(url);
if($('#PrintView').val()=='PrintView'){
window.open(url);
}
});
</script>
</c:if>

<c:if test="${issueMasterForms.succ=='Dl'}">
	<script type="text/javascript">
var delUrl='get_issue_list';
$(document).ready(function() {
alert('Record Deleted Successfully !!!!');
window.self.location = delUrl;
});
</script>
</c:if>
<c:if test="${issueMasterForms.succ=='NDl'}">
	<script type="text/javascript">
var delUrl='get_issue_list';
$(document).ready(function() {
alert('Sorry you can not use this issue number because it is used in issue return !!!!');
window.self.location = delUrl;
});
</script>
</c:if>
<c:if test="${issueMasterForms.succ=='Up'}">

	<script type="text/javascript">
var url = "issue_print_report/pdf";
var delUrl='get_issue_list';
$(document).ready(function() {
alert('Record Updated Successfully !!!!');
window.self.location = delUrl;
if($('#PrintView').val()=='PrintView'){
window.open(url);
}
});
</script>
</c:if>
<script type="text/javascript">
function isssuePrint(ele){
var url = "issue_print_report/pdf?issueNo="+ele;
window.open(url);

}
</script>
<c:if test="${not empty(errors)}">
	<input type="hidden" id="errorId" value="${errors.errorMsg}">
	<script type="text/javascript">
var delUrl='get_issue_list';
$(document).ready(function() {
var errorId=document.getElementById('errorId');
alert(errorId.value);
// alert('Branch can not be Removed this Field is using in other Form');
document.location = delUrl;
});
</script>
</c:if>

<script type="text/javascript">
function checkApproved(){
alert("You can not edit / delete this record as it is already approved.");
}
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


$('.fixmyheader-8').fixheadertable({
caption : 'My header is fixed !',
height :413,
addTitles : true,
colratio : ['10%', '10%', '8%', '50px', 'auto', 'auto', '30%', 'auto']
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

.dataTables_info {
	display: none;
}

.paging_full_numbers {
	display: none;
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

div.t_fixed_header_main_wrapper.ui div.t_fixed_header_caption {
	display: none;
}

.View {
	width: 30px !important;
	border: none !important
}

.Sn {
	width: 32px !important;
	border: none !important;
}

.In {
	width: 78px !important;
	border: none !important;
}

.Id {
	width: 71px !important;
	border: none !important;
}

.Ib {
	width: 71px !important;
	border: none !important;
}

.Indent {
	width: 70px !important;
	border: none !important;
}

.Ind {
	width: 71px !important;
	border: none !important;
}

.Raise {
	width: 71px !important;
	border: none !important;
}

.Dept {
	width: 71px !important;
	border: none !important;
}

.Toi {
	width: 49px !important;
	border: none !important;
}

.Isst {
	width: 71px !important;
	border: none !important;
}

.Ac {
	width: 80px !important;
	border: none !important;
}
</style>

<form:form name="input" action="get_issue_list" class="formdiv"
	method="post" modelAttribute="searchCriteria">
	<div class="bkgColor_grid">
		<div class="header">Material Issue List</div>


		<div class="headingdiv">
			<table width="880" height="31" border="0" cellpadding="0"
				cellspacing="0">
				<tr>

					<td width="83"><a onclick="return checkAdd();"
						href="new_issues" class="addbtn" iconCls="icon-add"></a>
					</td>

					
					<td width="80"><div align="center">Issue Number</div>
					</td>
					<td width="62"><form:input onkeypress="return check(event)"
							data-maxsize="35" path="issueNumber" size="10" id="issueNumber" />
					</td>
					<td width="75"><div align="center">Issue Type</div>
					</td>
					<td width="100">	  
					<%-- <form:input onkeyup="valid1(this)"
							onblur="valid1(this)" data-maxsize="16" path="issueType"
							size="10" id="issueType" /> --%>
					 <form:select style="width:105%" path="issueType">
						<form:option value=""></form:option>
									<form:option value="Non-Returnable">Non-Returnable</form:option>
									<form:option value="Returnable">Returnable</form:option>
								</form:select> 
					</td>
<td width="75"><div align="center">Raised By</div>
					</td>
<td width="62"><form:input onkeypress="return check(event)"
							data-maxsize="35" path="raisedBy" size="10" id="raisedBy" />
					</td>
					<td width="75"><div align="center">From Date</div>
					</td>
					<td width="60"><form:input onkeypress="return check(event)"
							class="fromDate" data-maxsize="35" path="fromDate" size="10" />
					</td>
					<td width="60"><div align="center">To Date</div>
					</td>
					<td width="70"><form:input onkeypress="return check(event)"
							class="toDate" data-maxsize="35" path="toDate" size="10" />
					</td>
					<td width="99"><div align="center">Item Name</div>
					</td>
					<td width="76"><form:input path="itemName" size="10"
							id="itemName" />
					</td>
					<td width="82"><input class="searchbtn" type="submit" value="" />
					</td>
					<td width="83"><a href="get_issue_list" class="cancelbtn"></a>
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
						<td class="Sn" width="32"><div align="center">S No.</div></td>
						<td class="In" width="78"><div align="center">Issue
								Number</div></td>
						<td class="Id" width="71"><div align="center">Issue
								Date</div></td>
						<td class="Ib" width="71"><div align="center">Issue By</div>
						</td>
						<td class="Indent" width="70"><div align="center">Indent
								No</div></td>
						<td class="Ind" width="71"><div align="center">Loan
								Type</div></td>
						<td class="Raise" width="71"><div align="center">Raised
								by</div></td>
						<td class="Dept" width="71"><div align="center">Department</div>
						</td>
						<td class="Toi" width="71"><div align="center">Total
								Items</div></td>
						<td class="Isst" width="71"><div align="center">Issue
								Type</div></td>
						<td class="Ac" width="65"><div align="center">Action</div></td>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${issueList}" var="cat" varStatus="s">
						<tr>
							<td style="text-align: center;" width="20"><c:url
									value="get_issue" var="view_url">
									<c:param name="issueAutoId" value="${cat.issueAutoId}"></c:param>
									<c:param name="opr" value="V"></c:param>
									<c:param name="approved" value="${cat.approved}"></c:param>
								</c:url> <a href="${view_url}"><img
									src="static/images/view_icon.png" title="View Record" alt="" />
							</a>
							</td>
							<td width="22">&nbsp;<c:out value="${s.count}" />
							</td>
							<td width="68">&nbsp;<c:out value="${cat.issueNumber}" />
							</td>
							<td width="61">&nbsp;<fmt:formatDate pattern="dd-MMM-yyyy"
									value="${cat.issueDate}" />
							</td>
							<td width="61">&nbsp;<c:out value="${cat.issuedBy}" />
							</td>
							<td width="60">&nbsp;<c:out value="${cat.indentnumber}" />
							</td>
							<td width="61"><c:out value="${cat.loanType}" />
							</td>
							<td width="61">&nbsp;<c:out value="${cat.raisedBy}" />
							</td>

							<td width="61" style="text-align: right">&nbsp;<c:out
									value="${cat.departmentName}" />
							</td>
							<td width="49" style="text-align: right" align="right">&nbsp;${cat.issueDetailMasterDTOList.size()}</td>
							<td width="61" style="text-align: right">&nbsp;<c:out
									value="${cat.issueTypeName}" />
							</td>
							<td width="82" style="text-align: left"><c:url
									value="get_issue" var="edit_url">
									<c:param name="issueAutoId" value="${cat.issueAutoId}"></c:param>
									<c:param name="opr" value="E"></c:param>
								</c:url> <c:url value="get_issue" var="remove_url">
									<c:param name="issueAutoId" value="${cat.issueAutoId}"></c:param>
									<c:param name="opr" value="R"></c:param>
								</c:url> <img
								onclick="isssuePrint('<c:out value="${cat.issueNumber}"/>');"
								src="static/images/print_icon.png" title="Print Issue" alt="" />
								<c:forEach items="${roleAndRightsDTOList}" var="roleAndRights">
									<c:if test="${roleAndRights.menuId==sessionScope.menuId}">
										<input type="hidden" id="addFlag"
											value="${roleAndRights.addFlag}">
										<c:if test="${roleAndRights.editFlag=='true'}">
											<c:choose>
												<c:when test="${cat.approved=='1'}">
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
											<c:choose>
												<c:when test="${cat.approved=='1'}">
													<img onclick="checkApproved();"
														src="static/images/change_btn.png" title="Edit Record"
														alt="" />
												</c:when>
												<c:otherwise>
													<img onclick="checkEdit();"
														src="static/images/change_btn.png" title="Edit Record"
														alt="" />
												</c:otherwise>
											</c:choose>
										</c:if>
										<c:if test="${roleAndRights.deleteFlag=='true'}">
											<c:choose>
												<c:when test="${cat.approved=='1'}">
													<img onclick="checkApproved();"
														src="static/images/drop.png" title="Edit Record" alt="" />
												</c:when>
												<c:otherwise>
													<a href="${remove_url}"><img
														src="static/images/drop.png" title="Delete Record" alt="" />
													</a>
												</c:otherwise>
											</c:choose>
										</c:if>
										<c:if test="${roleAndRights.deleteFlag=='false'}">
											<c:choose>
												<c:when test="${cat.approved=='1'}">
													<img onclick="checkApproved();"
														src="static/images/drop.png" title="Edit Record" alt="" />
												</c:when>
												<c:otherwise>
													<img onclick="checkDelete();" src="static/images/drop.png"
														title="Delete Record" alt="" />
												</c:otherwise>
											</c:choose>
										</c:if>
									</c:if>
								</c:forEach></td>
						</tr>
					</c:forEach>

				</tbody>
			</table>
		</div>
	</div>
	<div style="float: right;">
		<c:url value="get_issue_list" var="remove_url">
			<c:param name="next"
				value="${issueMasterForm.issueMasterDTO.next+(15)}"></c:param>
		</c:url>
		<a href="${remove_url}" class="nextbtn"></a>
	</div>
	<div style="float: right;">
		<c:url value="get_issue_list" var="remove_url">
			<c:param name="next"
				value="${issueMasterForm.issueMasterDTO.previous-(15)}"></c:param>
		</c:url>
		<a href="${remove_url}" class="previousbtn"></a>
	</div>
</form:form>
