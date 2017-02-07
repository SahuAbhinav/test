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
var delUrl='get_indent_list';
$(document).ready(function() {
alert('No Record Found !!!!');
//window.self.location = delUrl;
});
</script>
</c:if>

<c:if test="${capacitiveConsumptionForm.succ=='Ad'}">

	<script type="text/javascript">
		var delUrl = 'show_capative_consumtion_list';
		$(document).ready(function() {
			alert('Record Inserted Successfully !!!!');
			window.self.location = delUrl;
		});
	</script>
</c:if>





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


.Sn {
	width: 11px !important;
	border: none !important;
}

.Id {
	width: 25px !important;
	border: none !important;
}
.code {
	width: 22px !important;
	border: none !important;
}

.cap {
	width: 27px !important;
	border: none !important;
}
.sItemname {
	width: 82px !important;
	border: none !important;
}


.sQty {
	width: 20px !important;
	border: none !important;
}

.tItemName {
	width: 80px !important;
	border: none !important;
}

.tQty {
	width: 20px !important;
	border: none !important;
}




</style>

<form:form name="input" action="search_capative_consumtion_list" class="formdiv"
	method="post" modelAttribute="capacitiveConsumptionForm">
	<div class="bkgColor_grid">
		<div class="header">Captive Consumption List</div>
		<div class="headingdiv">
			 <table width="880" height="31" border="0" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="83"><a onclick="return checkAdd();"
						href="show_capative_consumtion" class="addbtn" iconCls="icon-add"></a>
					</td>
					
					
					<td width="75"><div align="center">Date</div>
					</td>
					<td width="99"><form:input onkeypress="return check(event)"
							class="toDate" data-maxsize="35" path="enteredDate" size="10" />
					</td>
					<td width="99"><div align="center">Source Item Name</div>
					</td>
					<td width="99"><form:input path="sourceItemCode" size="10"
							id="sourceItemCode" />
					</td>
					<td width="99"><div align="center">Target Item Name</div>
					</td>
					<td width="99"><form:input path="targetItemCode" size="10"
							id="targetItemCode" />
					</td>
					<td width="82"><input class="searchbtn" type="submit" value="" />
					</td>
					<td width="83"><a href="show_capative_consumtion_list" class="cancelbtn"></a>
					</td>
				</tr>
			</table> 
		</div>
		<div class="gridheadingdiv">
			<table width="972" class="display fixmyheader-8" id="example">
				<thead>
					<tr>
						<td class="vi" width="15" hidden="true"><div align="center">view.</div></td>
						<td class="Sn" width="15"><div align="center">S No.</div></td>
						<td class="Id" width="35"><div align="center">Enter
								Date</div></td>
								<td class="cap" width="35"><div align="center">Cap. Cons. No.</div></td>
								
						<td class="sItemname" width="70"><div align="center">Source Item Name</div></td>
						<td class="code" width="20"><div align="center">Item Code</div></td>
						
						
						<td class="sQty" width="20"><div align="center">Source Qty</div></td>
						<td class="tItemName" width="98"><div align="center">Target Item Name</div></td>
						<td class="code" width="20"><div align="center">Item Code</div></td>
						<td class="tQty" width="20"><div align="center">Target Qty</div></td>
					</tr>
				</thead>
				<tbody>
				<% int i=1;  %>
					<c:forEach items="${capativeList}" var="cat" varStatus="s">
						<tr>
							
							<td style="text-align: center;" width="24"  hidden="true">
			
          <a href="#"></a></td>		
        
        
							<td style="text-align: center;" width="4">&nbsp;<c:out value="${s.count}" />
							</td>
							<td style="text-align: center;" width="23">&nbsp;<fmt:formatDate pattern="dd-MMM-yyyy"
									value="${cat.enteredDate}" />
							</td>
							<td style="text-align: center;" width="25">&nbsp;
									${cat.capativeConsumptionNumber}
							</td>
							
							<td style="text-align: left;" width="90">&nbsp;<c:out value="${cat.sourceItemName}" />
							</td>
							<td style="text-align: center;" width="20">&nbsp;
									${cat.sourceItemCode}
							</td>
							<td width="20">&nbsp;<c:out value="${cat.sourceQuantity}" />
							</td>
							<td width="90" style="text-align: left">&nbsp;${cat.targetItemName}</td>
							<td style="text-align: center;" width="20">&nbsp;
									${cat.targetItemCode}
							</td>
							<td width="20" style="text-align: center" align="right">&nbsp;${cat.targetQuantity}</td>
						</tr>
						<% i++; %>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<div style="float: right;">
		<c:url value="show_capative_consumtion_list" var="remove_url">
			<c:param name="next"
				value="${capacitiveConsumptionForm.next+(15)}"></c:param>
		</c:url>
		<a href="${remove_url}" class="nextbtn"></a>
	</div>
	<div style="float: right;">
		<c:url value="show_capative_consumtion_list" var="remove_url">
			<c:param name="next"
				value="${capacitiveConsumptionForm.previous-(15)}"></c:param>
		</c:url>
		<a href="${remove_url}" class="previousbtn"></a>
	</div>
</form:form>
