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

<c:url value="/invoice_report/xls" var="downloadXls" />
<c:url value="/invoice_report/pdf" var="downloadPdf" />
<c:url value="/invoice_report/csv" var="downloadCsv" />
<c:url value="/static/images/loading_icon.gif" var="imagelink" />
<style>
/*  #aside {  */
/* background: url("..../../static/images/report_header_bg.png") repeat scroll 0 -138px #40454D; */
* /
	/*   height: 114px;  */
	/*     margin-left: 4px;  */
	/*      padding-left: 13px;  */
	/*      width: 970px;  */
	/*  }  */ 
Select {
	width: 98% !important;
}

.ui-accordion .ui-accordion-header a {
	background-color: #4e8ccf;
	padding-left: 26px;
	color: #fff;
	height: 17px;
}

#container {
	background-color: #f0f4ff;
	width: 986px;
}

.headerbg {
	width: 918px;
	background: url("..../../static/images/report_header_bg.png") repeat
		scroll 0 -138px #40454D;
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
	background-position: 0 -24px;
	border: medium none;
	cursor: pointer;
}

.dwdexl {
	background: url("..../../static/images/download_excel.png");
	width: 123px;
	height: 24px;
	border: medium none;
}

.dwdexl:hover {
	background: url("..../../static/images/download_excel.png");
	width: 123px;
	height: 24px;
	border: medium none;
	background-position: 0 -24px;
	cursor: pointer;
}

.dwdpdf {
	background: url("..../../static/images/download_PDF.png");
	width: 123px;
	height: 24px;
	border: medium none;
}

.dwdpdf:hover {
	background: url("..../../static/images/download_PDF.png");
	cursor: pointer;
	width: 123px;
	background-position: 0 -24px;
	height: 24px;
	border: medium none;
}

.icon li {
	list-style: none;
	margin-bottom: 3px;
}

.sendpdfmail {
	background: url("..../../static/images/send_email.png");
	width: 123px;
	height: 24px;
	border: medium none;
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

.email{
width: 180px;
}
.fn{
width: 80px;
}
</style>


<script type="text/javascript">
$(document).ready(function() {
$("#invoiceId").change(function() {
	$.ajax({

		type : "POST",
		url : "getEmailId",
		data :"invoiceNumber="+ $("#invoiceNumber").val().replace('&','%26')+ "&invoiceId="+ $("#invoiceId").val().replace('&','%26')+"&flag=Sales_Order",
		success : function(response) {
			
			
			if(response.result!=null){
				$("#emailId").val(response.result);
			  }
			}
});

   });
	 

   });




	$(document).ready(function() {
		$("#deliveryChallanPDF").hide();
		
		$('#emailId').change(function() {
			if ($("#emailId").val() != '') {
				$("#EmailerrorMsg").text("");
			}
		});
						$('#invoiceId').change(function() {
							if ($("#invoiceId").val() != '') {
								$("#errorMsg").text("");
							}
						});
	});
						
					
				
					
					
					
		
				
					

					function sendpdfmail(){
						
						
						//To check sales order number is vailable or not for this number
						$.ajax({
							type : "POST",
							url : "CheckValidSalesNo",
							data :"invoiceNumber="+ $("#invoiceNumber").val().replace('&','%26')+ "&invoiceId="+ $("#invoiceId").val().replace('&','%26')+"&flag=Invoice",
							success : function(response) {
								$("#flageId").val(response.result);
								if(response.result==false){
									alert('Sales order in not available for this number.');
								}
								return false;
								}
					        });
						if($("#flageId").val()==false){
							return false;
						}
						
						
						
						
						if ($('#invoiceId').val() == '') {
							$("#errorMsg").text("Number can not be null!");
							return false;
						}
						
						if ($('#emailId').val() == '') {
							$("#EmailerrorMsg").text("Email id can not be null!");
							return false;
						}
						else{
						$.ajax({
							type : "POST",
							url : "sales_pdf_mail?"
									+ "invoiceNumber="
									+ $("#invoiceNumber").val().replace('&','%26')+ "&invoiceId="+ $("#invoiceId").val().replace('&','%26')+"&mailIds="+$("#emailId").val(),
							success : function(data) {
								alert("Email sent successfuly");
							}
						});
						
						}
						$('#invoiceId').val('');
					
						
					}
					
					
</script>


<body>
<div class="header">Sales Order Report</div>
	<div class='accordion'>
		<h3>
			<a href='#'>Show Control</a>
		</h3>
		<div class="headerbg">

			<input type="hidden" name="flage" id="flageId">

			<table>
				<!-- 	<tr>					
				<td></td>
				<td><input type="button" id="showreport" value="Show Report"/></td>
			</tr> -->
				<tr>
					<td width="65" nowrap="nowrap">Sales Order Series:</td>
					<td width="45" class="fn"><form:select
							path="invoiceReportFrom.bill.invoiceNumber" items="${billList}"
							itemLabel="FinyearTransactionSeris"
							itemValue="FinyearTransactionSeris" id="invoiceNumber"
							style=" height: 23px;" cssClass="fn" >
							<form:option value="All" selected="selected">All</form:option>
						</form:select>
				</td>
				<td width="55" nowrap="nowrap">Number:<span style="color: red;">*</span></td>
				<td><form:input path="invoiceReportFrom.invoiceId" id="invoiceId" style="width:50px;" /></td><td><span id="errorMsg" style="color: red;"></span></td>
				</tr>
				<tr>
				
				<td width="55" nowrap="nowrap">Buyer's Email Id:</td>
				<td width="120">
				<input type="text" name="emailId" value="" id="emailId" class="email" width="120">
				</td><td><span id="EmailerrorMsg" style="color: red;"></span></td>
				
				</tr>
				
			</table>
		
			<div style="float: right; margin-top: -70px; width: 280px;">
				<ul class="icon">
						<li><input type="button" class="sendpdfmail" id="sendpdfmail" onclick="sendpdfmail()"
						value="" />&nbsp;&nbsp;</li>
				</ul>
			</div>
		</div>
	</div>
	

	<div id="reportHtml">
		<img style="display: none" alt="loading" id="loadingImageHtmlReport" src="${imagelink}" />
	</div>
	
	
</body>
