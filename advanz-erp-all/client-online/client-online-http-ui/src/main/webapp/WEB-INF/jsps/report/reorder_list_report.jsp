<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>


<script>
	$(document).ready(function() {
		$(".accordion").accordion({
			collapsible : true,
			active : -1
		});
	});
</script>

<c:url value="/proforma_report/xls" var="downloadXls" />
<c:url value="/proforma_report/pdf" var="downloadPdf" />
<c:url value="/proforma_report/csv" var="downloadCsv" />
<c:url value="/static/images/loading_icon.gif" var="imagelink" />
<style>
 
Select{
width: 98% !important ;
	text-transform: uppercase;
}
.ui-accordion .ui-accordion-header a {
/* background: url("..../../static/images/hide_controll.jpg"); */
/* width:82px; */
/* height:29px; */
background-color: #4e8ccf;
padding-left: 26px;
color:#fff;
  height: 17px;
} 
#container {
background-color: #f0f4ff;
width: 986px;

}
.headerbg {
width: 918px;
background: url("..../../static/images/report_header_bg.png") repeat scroll 0 -138px #40454D;
 height: 63px !important;
    overflow: hidden !important;
}
.dwdshowreport {
background: url("..../../static/images/show_report.png");
width: 123px;
height: 24px;
  border: medium none;
}
.dwdshowreport:hover {
background: url("..../../static/images/show_report.png");
width: 123px;
height: 24px;
background-position:0 -24px;
  border: medium none;
  cursor: pointer;
}
.dwdexl{
background: url("..../../static/images/download_excel.png");
width: 123px;
height: 24px;
  border: medium none;
}
.dwdexl:hover{
background: url("..../../static/images/download_excel.png");
width: 123px;
height: 24px;
  border: medium none;
  background-position: 0 -24px;
  cursor: pointer;
}
.dwdpdf{
background: url("..../../static/images/download_PDF.png");
width: 123px;
height: 24px;
  border: medium none;
}
.dwdpdf:hover{
background: url("..../../static/images/download_PDF.png");
cursor: pointer;
width: 123px;
background-position:0 -24px;
height: 24px;
  border: medium none;
}
.icon li {
list-style: none;
margin-bottom: 3px;
}

 
#reportHtml {
 background-color: #EEEEEE;
    float: left;
    padding-left: 77px;
    width: 896px;
}
.accordion {
 margin-left: -5px;
    width: 979px;
background:#F0F4FF;
height: auto !important;
}
.ui-widget-content {
display:none;
}
</style>

<script>
$(function() {
	
	$("#toDate").datepicker({
		dateFormat : 'dd-M-yy',
		autoSize : true,
		changeMonth : true,
		changeYear : true,
		dateFormat : 'dd-M-yy',changeMonth: true,
        changeYear: true, yearRange: '-99:+0', maxDate: '+0M +0D'
	});
	$('#toDate').datepicker().datepicker('setDate',new Date());
	
});
	</script>


<script type="text/javascript">
	$(document).ready(
			function() {

				$('#toDate').change(function() {
					if ($("#toDate").val() != '') {
						$("#msg1").text("");	
					}
				});
				
				
				$("#showreport").click(
						function() {
							if ($("#toDate").val() == '') {
								$("#msg1").text("To date can not be null!");
								 return false;
								}
							$("#loadingImageHtmlReport").show();
							$.ajax({
								type : "POST",
								url : "reorder_list_report/html?"+ "toDate=" +  $("#toDate").val()+ "&itemGroupFlagName=" +  $("#itemGroupFlagName").val().replace('&','%26')+ "&ItemNamePrompt=" + $("#ItemNamePrompt").val().replace('&','%26')+ "&name=" + $("#name").val().replace('&','%26')+"&activeStatus="+$("#activeStatus").val(),
									success : function(data) {
									$("#loadingImageHtmlReport").hide();
									$("#reportHtml").html(data.reportHtml);
								}
							});
							return false;

						});

				$("#downloadPdf").click(function() {
					if ($("#toDate").val() == '') {
						$("#msg1").text("To date can not be null!");
						 return false;
						}
					var url = "reorder_list_report/pdf?"+ "toDate=" +  $("#toDate").val()+ "&itemGroupFlagName=" +  $("#itemGroupFlagName").val().replace('&','%26')+ "&ItemNamePrompt=" + $("#ItemNamePrompt").val().replace('&','%26')+ "&name=" + $("#name").val().replace('&','%26')+"&activeStatus="+$("#activeStatus").val();
					try {
						var child = window.open(url);
						child.focus();
					} catch (e) {
					}
				});

				$("#downloadXls").click(function() {
					if ($("#toDate").val() == '') {
						$("#msg1").text("To date can not be null!");
						 return false;
						}
					var url = "reorder_list_report/xls?"+ "toDate=" +  $("#toDate").val()+ "&itemGroupFlagName=" +  $("#itemGroupFlagName").val().replace('&','%26')+ "&ItemNamePrompt=" + $("#ItemNamePrompt").val().replace('&','%26')+ "&name=" + $("#name").val().replace('&','%26')+"&activeStatus="+$("#activeStatus").val();
					try {
						var child = window.open(url);
						child.focus();
					} catch (e) {
					}
				});

				$("#downloadCsv").click(function() {
					if ($("#toDate").val() == '') {
							$("#msg1").text("To date can not be null!");
							 return false;
							}
					var url = "reorder_list_report/csv?"+ "toDate=" +  $("#toDate").val()+ "&itemGroupFlagName=" +  $("#itemGroupFlagName").val().replace('&','%26')+ "&ItemNamePrompt=" + $("#ItemNamePrompt").val().replace('&','%26')+ "&name=" + $("#name").val().replace('&','%26')+"&activeStatus="+$("#activeStatus").val();
					try {
						var child = window.open(url);
						child.focus();
					} catch (e) {
					}
				});

			});// ready
</script>


<body>
<div class="header">Reorder List Report</div>
	<div class='accordion'>
		<h3>
			<a href='#'>Show Control</a>
		</h3>
		<div class="headerbg">


 <table style="width: 550px;  margin-top: -7px; float: left">
		
			<tr>
			
			<td width="35" nowrap="nowrap">As On Date:<span style="color: red;">*</span></td>
			<td width="152"><form:input path="reorderReportFrom.toDate"  id="toDate" style="width:84px;"
							 size="16" readonly="true"/>
							</td>
							<td width="35" nowrap="nowrap">Item Group Flag Name:</td>
			<td width="135"><form:select path="reorderReportFrom.itemGroupFlagName"  id="itemGroupFlagName"  >
				<form:option value="All" selected="selected">All</form:option>
				<form:options items="${itemGroupList}" itemLabel="itemGroupFlagName" itemValue="itemGroupFlagName" />
			</form:select></td>
							</tr>
							<tr>
					<td></td><td><span id="msg1" style="color: red"></span></td>
					<td></td><td></td>
					</tr>
			<tr>
			
			<td width="35" nowrap="nowrap">Item Name:</td>
			<td width="135"><form:select path="reorderReportFrom.itemDTO.itemName"  id="ItemNamePrompt"  >
				<form:option value="All" selected="selected">All</form:option>
				<form:options items="${itemList}" itemLabel="itemName" itemValue="itemName" />
			</form:select></td>
			
			<td width="35">Grade:</td>
			<td width="135"><form:select path="reorderReportFrom.name"  id="name" >
				<form:option value="All" selected="selected">All</form:option>
				<form:options items="${masterList}" itemLabel="name" itemValue="name" />
			</form:select></td>
			<td width="85">Active Status</td>
			<td width="145"><select name="activeStatus" id="activeStatus">
				<option value="All" selected="selected">All</option>
				<option value="1" >Yes</option>
				<option value="0" >No</option>
			</select></td>
			</tr>
	    </table> 

			<div style="float: right; margin-top: -21px; width: 359px;">
				<ul class="icon">
				<li><input type="button" class="dwdshowreport" id="showreport" value=""/></li>
					<li><input type="button" class="dwdexl" id="downloadXls"
						value="" />&nbsp;&nbsp;</li>

					<li><input type="button" class="dwdpdf" id="downloadPdf"
						value="" />&nbsp;&nbsp;</li>
				</ul>
			</div>
		</div>
	</div>



	<div id="reportHtml">
		<img style="display: none" alt="loading" id="loadingImageHtmlReport"
			src="${imagelink}" />
	</div>
</body>
