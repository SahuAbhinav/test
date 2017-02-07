<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
 
	<c:url value="/proforma_report/xls" var="downloadXls" />
	<c:url value="/proforma_report/pdf" var="downloadPdf" />
	<c:url value="/proforma_report/csv" var="downloadCsv" />
	<c:url value="/static/images/loading_icon.gif" var="imagelink" />
	<style>
/*  #aside {  */
/* background: url("..../../static/images/report_header_bg.png") repeat scroll 0 -138px #40454D; */ */
/*   height: 114px;  */
/*     margin-left: 4px;  */
/*      padding-left: 13px;  */
/*      width: 970px;  */
    
/*  }  */
Select{
width: 98% !important ;
}
.ui-accordion .ui-accordion-header a {
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
height: auto !important;
}
</style>
	

<script type="text/javascript">
	$(document).ready(	function() {
		$('#proformaId').change(function() {
			if ($("#proformaId").val() != '') {
				$("#errorMsg").text("");	
			}
		});				
		
		
		$("#showreport").click( function() {
			$("#loadingImageHtmlReport").show();
			/*  jQuery.ajax({
			    url: "invoice_report/html",
			    type: "POST",
			   // dataType: "json",
			   // data: JSON.stringify(ruleAssignment),
			   // contentType: "application/json", */
			   
			   $.ajax({

				type : "POST",
				url : "proforma_report/html?"+ "invoiceNumber=" +  $("#invoiceNumber").val()+"&proformaId="+$("#proformaId").val(),
			    success: function(data) {
			    	$("#loadingImageHtmlReport").hide();
			    	$("#reportHtml").html(data.reportHtml);
			}
				}); 
				   return false; 
				   
			    });	
		
					
					$("#downloadPdf").click( function() {
						
						if($('#proformaId').val()==''){
							$("#errorMsg").text("Number can not be null!");
							return false;
							}
						
						var url = "proforma_report/pdf?"
						+ "invoiceNumber=" +  $("#invoiceNumber").val()+"&proformaId="+  $("#proformaId").val(); 
						 		try { var child = window.open(url); child.focus(); } catch (e) { }
					});	
					
					$("#downloadXls").click( function() {
						
						if($('#proformaId').val()==''){
							$("#errorMsg").text("Number can not be null!");
							return false;
							}
						var url = "proforma_report/xls?"
									+ "invoiceNumber=" +  $("#invoiceNumber").val()+"&proformaId="+  $("#proformaId").val();
					 		try { var child = window.open(url); child.focus(); } catch (e) { }
					 });	
	
					$("#downloadCsv").click( function() {
						var url = "proforma_report/csv?"
							+ "invoiceNumber=" +  $("#invoiceNumber").val();
					 		try {
					 			var child = window.open(url);
					 	  child.focus(); 
					 	} catch (e) { }
					});	
	
				});// ready
</script>

	
<body>
<div class="header">Proforma Invoice Report</div>
<div class='accordion'>
  <h3><a href='#'>Show Control</a></h3>
<div class="headerbg">
	

		<table>
		<!-- 	<tr>					
				<td></td>
				<td><input type="button" id="showreport" value="Show Report"/></td>
			</tr> -->
			<tr>
			<td width="65" nowrap="nowrap">Proforma Series:</td>
			<td width="45"><form:select path="proformaReportFrom.masterDTO.invoiceNumber" items="${proformaList}" itemLabel="FinyearTransactionSeris" itemValue="FinyearTransactionSeris" id="invoiceNumber"  style="height: 23px;" >
           <form:option value="All" selected="selected">All</form:option>
			</form:select></td>
			<td width="55" nowrap="nowrap">
			    Number:<span style="color: red;">*</span>
			</td>
			<td>
			    <form:input path="proformaReportFrom.proformaId" id="proformaId" style="width:45px;" />
			</td>
			</tr>
			<tr>
	<td></td><td></td><td></td>
	<td><span id="errorMsg" style="color: red;"></span>
	</td>
	</tr>
	</table> 
	<div style="float: right;    margin-top: -40px;    width: 359px; " >
			<ul class="icon">
	<!-- <li><input type="button" class="dwdexl" id="downloadXls" value=""/>&nbsp;&nbsp;</li> -->
	
	<li><input type="button" class="dwdpdf" id="downloadPdf" value=""/>&nbsp;&nbsp;</li>
	</ul>
	</div>
		 	</div>
			</div>
		 
	
<!-- 	<input type="button" id="downloadCsv" value="Download CSV"/>&nbsp;&nbsp; -->
	
<%-- 	<c:url value="/geographic_report/html" var="downloadHTML" />
	<a href="${downloadHTML}">Download HTML</a> --%>

	<div id="reportHtml"><img style="display:none" alt="loading"  id="loadingImageHtmlReport" src="${imagelink}"/></div>
</body>
