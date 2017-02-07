<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false"%>
<%@ page session="true" %>

<script type="text/javascript" charset="utf-8">
$(document).ready(function() {
$(".datepicker[readonly]").css("background-color", "#ffffff");
});
</script>
<script type="text/javascript" charset="utf-8">
$(document).ready(function() {
/* Initialise datatables */
var oTable = $('#example').dataTable({bInfo:""});

});
</script>


<c:if test="${succ=='Blk'}">
<script type="text/javascript">
var delUrl = 'get_annealingOven_list';
$(document).ready(function() {
alert('No Record Found !!!!');
// window.self.location = delUrl;
});
</script>
</c:if>

<c:if test="${annealingOvenForm.succ=='Ad'}">

<script type="text/javascript">
var delUrl = 'get_annealingOven_list';
$(document).ready(function() {
alert('Record Inserted Successfully !!!!');
window.self.location = delUrl;
});
</script>
</c:if>

<c:if test="${annealingOvenForm.succ=='Dl'}">
<script type="text/javascript">
var delUrl = 'get_annealingOven_list';
$(document).ready(function() {
alert('Record Deleted Successfully !!!!');
window.self.location = delUrl;
});
</script>
</c:if>

<c:if test="${annealingOvenForm.succ=='Up'}">

<script type="text/javascript">
var delUrl = 'get_annealingOven_list';
$(document).ready(function() {
alert('Record Updated Successfully !!!!');
window.self.location = delUrl;
});
</script>
</c:if>



<c:if test="${not empty(errors)}">
<input type="hidden" id="errorId" value="${errors.errorMsg}">
<script type="text/javascript">
var delUrl = 'get_annealingOven_list';
$(document).ready(function() {
var errorId = document.getElementById('errorId');
alert(errorId.value);
// alert('Branch can not be Removed this Field is using in other Form');
document.location = delUrl;
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
$(document).ready(
function() {

$('.fixmyheader-8').fixheadertable(
{
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

div.t_fixed_header_main_wrapper.ui div.t_fixed_header_caption {
display: none;
}
.View{width:34px !important; border:none!important}
.Sn { width: 47px !important; border: none !important;` }
.date { width: 91px !important; border: none !important;}
.time { width: 116px !important; border: none !important;}
.Shift { width: 109px !important; border: none !important;}
.Hsp { width: 114px !important; border: none !important; }
.Lpg { width: 120px !important; border: none !important;}
.fr { width: 120px !important; border: none !important;}
.Ac { width: 59px !important; border: none !important;}

</style>



<script type="text/javascript">
$(document).ready(function() {

$(".datepicker").datepicker({
changeMonth : true,
changeYear : true,
yearRange : '-99:+10',
dateFormat : 'dd-M-yy'
});
$(".fromDate").datepicker({
changeMonth : true,
changeYear : true,
yearRange : '-99:+10',
dateFormat : 'dd-M-yy'
});
$(".toDate").datepicker({
changeMonth : true,
changeYear : true,
yearRange : '-99:+10',
dateFormat : 'dd-M-yy'
});
//
});
</script>


<style type="text/css">
</style>
<form:form name="input" action="get_annealingOven_list" class="formdiv"
method="post" modelAttribute="annealingOvenForm">


<div class="header">Annealing Oven &amp; Gasifier Operation List</div>
<div class="headingdiv">
<table width="876" height="31" border="0" cellpadding="0"
cellspacing="0">
<tr>
<td width="65"><a onclick="return checkAdd();" href="annealingOven_add" class="addbtn"></a>
</td>
<td width="104">
<input class="exportbtn" style="font-size: 11px; font-weight: bold; padding: 0 0 0 38px;" type="button" value="" />
</td>
<%-- <td width="66"><div align="center">Date</div>
</td>
<td width="69"><form:input type="text"
path="annealingOvenDTO.ovenDate" class="datepicker" id="date"
size="16" readonly="true" />
</td> --%>
<td width="66"><div align="center">From Date</div>
</td>
<td width="69"><form:input type="text"
path="annealingOvenMasterDTO.fromDate" class="fromDate" id="fromDate"
size="16" readonly="true" />
</td>
<td width="66"><div align="center">To Date</div>
</td>
<td width="69"><form:input type="text"
path="annealingOvenMasterDTO.toDate" class="toDate" id="toDate"
size="16" readonly="true" />
</td>
<td width="49"><div align="center">Shift</div>
</td>
<td width="300"><form:input id="shift" type="text" size="11"
data-maxsize="35" style=" width:30%"
path="annealingOvenMasterDTO.shiftMasterDTO.name" />
</td>
<td width="69"><input class="searchbtn"
style="font-size: 11px; font-weight: bold; padding: 0 0 0 20px;"
type="submit" value="" />
</td>
<td width="69">
<div class="cancelbtn">
<a href="get_annealingOven_list" class="cancelbtn"
iconCls="icon-cancel"></a>
</div></td>
</tr>
</table>
</div>


<div class="gridheadingdiv">
<table width="972" class="display fixmyheader-8" id="example">
<thead>
<tr>

<td class="View" width="55"><div align="center">View</div></td>
<td class="Sn" width="47"><div align="center">S No.</div></td>
<td class="date" width="91"><div align="center">Date</div></td>
<td class="Shift" width="109"><div align="center">Shift</div></td>
<td class="Hsp" width="114"><div align="center">HSD Dip Reading</div></td>
<td class="Lpg" width="120"><div align="center">LPG Reading</div></td>
<td class="fr" width="120"><div align="center">Final Reading</div></td>
<td class="Ac" width="64"><div align="center">Action</div></td>
</tr>
</thead>
<tbody>
<c:forEach items="${annealingOvenMasterDTOList}" var="pom" varStatus="s">
<tr>
<td style="text-align: center;" width="24">
<c:url value="get_annealingOven" var="view_url">
<c:param name="ovenId" value="${pom.ovenId}"></c:param>
<c:param name="opr" value="V"></c:param>
</c:url>
<a href="${view_url}"><img src="static/images/view_icon.png" title="View Record" alt="" /></a></td>

<td width="37">&nbsp;<c:out value="${s.count}" /></td>
<td width="81">&nbsp;<fmt:formatDate pattern="dd-MMM-yyyy" value="${pom.ovenDate}" /></td>
<td width="99">&nbsp;${pom.shiftMasterDTO.name}</td>
<td width="104" style="text-align: right;">&nbsp;${pom.hsdDipReading}</td>
<td width="110" style="text-align: right;">&nbsp;${pom.lpgReading}</td>
<td width="110" style="text-align: right;">&nbsp;${pom.finalReading}</td>
<td width="59" style=""><c:url
value="get_annealingOven" var="remove_url">
<c:param name="ovenId" value="${pom.ovenId}"></c:param>
<c:param name="opr" value="R"></c:param>
</c:url> <c:url value="get_annealingOven" var="edit_url">
<c:param name="ovenId" value="${pom.ovenId}"></c:param>
<c:param name="opr" value="E"></c:param>
</c:url>
<c:forEach items="${roleAndRightsDTOList}" var="roleAndRights">
<c:if test="${roleAndRights.menuId==sessionScope.menuId}">
<input type="hidden" id="addFlag" value="${roleAndRights.addFlag}" >
<c:if test="${roleAndRights.editFlag=='true'}">
<a id="editUrlId" href="${edit_url}"><img src="static/images/change_btn.png" title="Edit Record" alt="" /></a>
</c:if>
<c:if test="${roleAndRights.editFlag=='false'}">
<img onclick="checkEdit();" src="static/images/change_btn.png" title="Edit Record" alt="" />
</c:if>
<c:if test="${roleAndRights.deleteFlag=='true'}">
<a href="${remove_url}"><img src="static/images/drop.png" title="Delete Record" alt="" /></a>
</c:if>
<c:if test="${roleAndRights.deleteFlag=='false'}">
<img onclick="checkDelete();" src="static/images/drop.png" title="Delete Record" alt="" />
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
