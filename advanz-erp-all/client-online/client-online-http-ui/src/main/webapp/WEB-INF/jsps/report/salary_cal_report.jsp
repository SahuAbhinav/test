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
width: 50% !important ;
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
</style>

<script type="text/javascript">
	$(document).ready(
			function() {

				$("#showreport").click(
						function() {
							$("#loadingImageHtmlReport").show();
							$.ajax({
								type : "POST",
								url : "salary_report/html?monthName="+$('#salaryMonth').val().replace('&','%26')+"&yearName="+$('#salaryYear').val().replace('&','%26'),
									success : function(data) {									
									$("#loadingImageHtmlReport").hide();
									console.log(data.htmlSalaryCal);
									$("#reportHtml").html(data.htmlSalaryCal);
									
								}
							});
							return false;

						});

				$("#downloadPdf").click(function() {
					var url = "salary_report/pdf?monthName="+$('#salaryMonth').val().replace('&','%26')+"&yearName="+$('#salaryYear').val().replace('&','%26');
					try {
						var child = window.open(url);
						child.focus();
					} catch (e) {
					}
				});

				$("#downloadXls").click(function() {
					var url = "salary_report/xls?monthName="+$('#salaryMonth').val().replace('&','%26')+"&yearName="+$('#salaryYear').val().replace('&','%26');
					try {
						var child = window.open(url);
						child.focus();
					} catch (e) {
					}
				});

				$("#downloadCsv").click(function() {
					var url = "salary_report/csv?monthName="+$('#salaryMonth').val().replace('&','%26')+"&yearName="+$('#salaryYear').val().replace('&','%26');
					try {
						var child = window.open(url);
						child.focus();
					} catch (e) {
					}
				});

			});// ready
</script>


<body>
 <div class="header">Salary Calculation List Report</div> 
	<div class='accordion'>
		<h3>
			<a href='#'>Show Control</a>
		</h3>
		<div class="headerbg">
		 <table style="width: 550px; float: left;">
		<tr>
			<td>Month Name:</td>
			<td><form:select path="salaryMasterForm.salaryMasterDTO.salaryMonth"  id="salaryMonth" style="width: 300px; height: 23px;" >
				<form:option value="All" selected="selected">Select</form:option>
				<form:option value="JANUARY">JANUARY</form:option>
				<form:option value="FEBRUARY">FEBRUARY</form:option>
				<form:option value="MARCH">MARCH</form:option>
				<form:option value="APRIL">APRIL</form:option>
				<form:option value="MAY">MAY</form:option>
				<form:option value="JUNE">JUNE</form:option>
				<form:option value="JULY">JULY</form:option>
				<form:option value="AUGUST">AUGUST</form:option>
				<form:option value="SEPTEMBER">SEPTEMBER</form:option>
				<form:option value="OCTOBER">OCTOBER</form:option>
				<form:option value="NOVEMBER">NOVEMBER</form:option>
				<form:option value="DECEMBER">DECEMBER</form:option>
			</form:select></td>
			<td>Year:</td>
			<td><form:input path="salaryMasterForm.salaryMasterDTO.salaryYear" id="salaryYear"  style="width: 60px; height: 18px;" /> </td>
			</tr>
			
	</table>

			<div style="float: right; margin-top: -18px; width: 359px;">
				<ul class="icon">
				<li><input type="button" class="dwdshowreport" id="showreport" value=""/></li>				
					<li><input type="button" class="dwdexl" id="downloadXls" value="" />&nbsp;&nbsp;</li>
					<li><input type="button" class="dwdpdf" id="downloadPdf" value="" />&nbsp;&nbsp;</li>
				</ul>
			</div>
		</div>
	</div>


	<!-- 	<input type="button" id="downloadCsv" value="Download CSV"/>&nbsp;&nbsp; -->

	<%-- 	<c:url value="/geographic_report/html" var="downloadHTML" />
	<a href="${downloadHTML}">Download HTML</a> --%>

	<div id="reportHtml">
		<img style="display: none" alt="loading" id="loadingImageHtmlReport"
			src="${imagelink}" />
	</div>
</body>
