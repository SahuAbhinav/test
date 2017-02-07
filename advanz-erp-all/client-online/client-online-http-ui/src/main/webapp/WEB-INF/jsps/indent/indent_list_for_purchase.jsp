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
	width: 90px !important;
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
	width: 60px !important;
	border: none !important;
}
</style>
<script>
	$(document).ready(function() {
		$(".cancelbtn").click(function() {
			window.self.location = "backto_purchaseOrder?opr=${opr}";
		});

	});
</script>
<script>
	$(document).ready(function() {
		$(function() {
			$('.sr').click(function() {
				$('.sr').css("background-color","white");
				$(this).css("background-color","yellow");
				$("#itemIdPO").val($(this).attr('id'));
			});
				// $(this).toggleClass('active');
				//alert($(this).attr('id'));
			});
		});
	function submitIndentNo(){
	window.self.location = "getIndentItem_for_purchaseOrder?indentNumber="+$("#itemIdPO").val();
	}
</script>
<style type="text/css">
.sr {
	background-color: white;
}
</style>
<form:form name="input" action="get_indent_list_for_purchase" class="formdiv"
	method="post" modelAttribute="indtDTO">
	<div class="bkgColor_grid">
		<div class="header">Indent List for Purchase Order</div>


		<div class="headingdiv">
			<table width="880" height="31" border="0" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="111"><div align="center">Indent Number</div>
					</td>
					<td width="62"><form:input onkeypress="return check(event)"
							data-maxsize="35" path="indentNumber" size="10" id="issueNumber" />
					</td>
					<td width="75"><div align="center">From Date</div>
					</td>
					<td width="99"><form:input onkeypress="return check(event)"
							class="fromDate" data-maxsize="35" path="fromDate" size="10" />
					</td>
					<td width="75"><div align="center">To Date</div>
					</td>
					<td width="99"><form:input onkeypress="return check(event)"
							class="toDate" data-maxsize="35" path="toDate" size="10" />
					</td>
					<td width="99"><div align="center">Item Name</div>
					</td>
					<td width="99"><form:input path="itemName" size="10"
							id="itemName" />
					</td>
					<td width="82"><input class="searchbtn" type="submit" value="" />
					</td>
					<td width="83"><input name="reset2" type="reset" class="cancelbtn"  style="font-size: 11px; font-weight: bold; border:0px;  padding: 0 0 0 10px;" value=" "/>
					</td><td>
				       <input type="hidden" name="itemID" value="" id="itemIdPO">
						<input  class="okbtn" onclick="submitIndentNo();" id="ok" style="font-size: 11px; font-weight: bold; border:0px;  padding: 0 0 0  ;" value=" "/></td>    					
				</tr>
			</table>
		</div>


		<div class="gridheadingdiv">
			<table width="972" class="display fixmyheader-8" id="example">
				<thead>
					<tr>
						<td class="Sn" width="32"><div align="center">S No.</div></td>
						<td class="In" width="78"><div align="center">Indent
								Number</div></td>
						<td class="Id" width="71"><div align="center">Indent
								Date</div></td>
						<td class="Raise" width="90"><div align="center">Raised
								by</div></td>
						<td class="Toi" width="71"><div align="center">Total Items</div></td>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${indentList}" var="cat" varStatus="s">
						<tr class="sr" style="cursor: pointer;" id="${cat.indentNumber}">
							<td style="text-align: center;" width="22">&nbsp;<c:out value="${s.count}" />
							</td>
							<td style="text-align: center;" width="68">&nbsp;<c:out value="${cat.indentNumber}" />
							</td>
							<td style="text-align: center;" width="61">&nbsp;<fmt:formatDate pattern="dd-MMM-yyyy"
									value="${cat.indentDate}" />
							</td>
							<td width="80">&nbsp;<c:out value="${cat.raisedBy}" />
							</td>
							<td width="49" style="text-align: center" align="right">&nbsp;${cat.indentDetailDTO.size()}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<div style="float: right;">
		<c:url value="get_indent_list_for_purchase" var="remove_url">
			<c:param name="next"
				value="${indentMasterForm.indentMasterDTO.next+(15)}"></c:param>
		</c:url>
		<a href="${remove_url}" class="nextbtn"></a>
	</div>
	<div style="float: right;">
		<c:url value="get_indent_list_for_purchase" var="remove_url">
			<c:param name="next"
				value="${indentMasterForm.indentMasterDTO.previous-(15)}"></c:param>
		</c:url>
		<a href="${remove_url}" class="previousbtn"></a>
	</div>
</form:form>
