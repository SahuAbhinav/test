<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
	<script>
	$(document).ready(function() {
	    $( ".accordion" ).accordion({
	     collapsible: true,
	active: -1
	});
	});

	  </script>
 
	<c:url value="/item_stock_report/xls" var="downloadXls" />
	<c:url value="/item_stock_report/pdf" var="downloadPdf" />
	<c:url value="/item_stock_report/csv" var="downloadCsv" />
	<c:url value="/static/images/loading_icon.gif" var="imagelink" />
	<style>

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
background: url("..../../static/images/report_header_bg.png") repeat scroll 0 -129px #40454D;
    height: 72px !important;
    overflow: hidden !important;
    width: 918px;
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
}.ui-widget-content {
display:none;
}
</style>
<script>
	$(function() {
		$("#fromDate").datepicker({
			dateFormat : 'dd-M-yy',
			autoSize : true,
			changeMonth : true,
			changeYear : true,
			dateFormat : 'dd-M-yy',changeMonth: true,
	        changeYear: true, yearRange: '-99:+0', maxDate: '+0M +0D'
		});
		var date = new Date();
		var firstDay = new Date(date.getFullYear(), date.getMonth(), 1);
		$('#fromDate').datepicker().datepicker('setDate',firstDay);
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
	$(document).ready(	function() {
		
		$('#fromDate').change(function() {
			if ($("#fromDate").val() != '') {
				$("#msg").text("");	
			}
		});
		$('#toDate').change(function() {
			if ($("#toDate").val() != '') {
				$("#msg1").text("");	
			}
		});
		
		$("#showreport").click(
				function() {
					if ($("#fromDate").val() == '') {
					$("#msg").text("From date can not be null!");
					return false;
					}
					if ($("#toDate").val() == '') {
						$("#msg1").text("To date can not be null!");
						 return false;
						}
					
			$("#loadingImageHtmlReport").show();
			
			   $.ajax({

				type : "POST",
				url : "show_rg_report/html?"+ "fromDate=" +  $("#fromDate").val()+ "&toDate=" +  $("#toDate").val()+ "&branch=" + $("#branch").val().replace('&','%26')+"&itemName=" +  $("#itemName").val().replace('&','%26'),
			    success: function(data) {
			    	$("#loadingImageHtmlReport").hide();
			    	$("#reportHtml").html(data.reportHtml);
			}
				}); 
				   return false; 
				   
			    });	
		
					
		$("#downloadPdf").click( function() {
			
		if ($("#fromDate").val() == '') {
			$("#msg").text("From date can not be null!");
			return false;
			}
		if ($("#toDate").val() == '') {
			$("#msg1").text("To date can not be null!");
			 return false;
			}
		var url = "show_rg_report/pdf?"+ "fromDate=" +  $("#fromDate").val()+ "&toDate=" +  $("#toDate").val()+ "&branch=" +  $("#branch").val().replace('&','%26')+"&itemName=" + $("#itemName").val().replace('&','%26');
		try { var child = window.open(url); child.focus(); } catch (e) { }
		});	
					
		$("#downloadXls").click( function() {
			var branch=$("#branch").val().replace('&','%26');
			var itemName=$("#itemName").val().replace('&','%26');
			
			if ($("#fromDate").val() == '') {
			$("#msg").text("From date can not be null!");
			return false;
			}
			if ($("#toDate").val() == '') {
			$("#msg1").text("To date can not be null!");
			 return false;
			}
			var url = "show_rg_report/xls?"+ "fromDate=" +  $("#fromDate").val()+ "&toDate=" +  $("#toDate").val()+ "&branch=" +  $("#branch").val().replace('&','%26')+"&itemName=" +  $("#itemName").val().replace('&','%26');
			try { var child = window.open(url); child.focus(); } catch (e) { }
		});	
	
		$("#downloadCsv").click( function() {
		if ($("#fromDate").val() == '') {
		 $("#msg").text("From date can not be null!");
			return false;
		}
		if ($("#toDate").val() == '') {
			$("#msg1").text("To date can not be null!");
			 return false;
		}
		var url = "show_rg_report/csv?"+ "fromDate=" +  $("#fromDate").val()+ "&toDate=" +  $("#toDate").val()+ "&branch=" +  $("#branch").val().replace('&','%26')+ "&itemName=" +  $("#itemName").val().replace('&','%26');
		try {
		var child = window.open(url);
	 	  child.focus(); 
	 	} catch (e) { }
	});	
	
	});// ready
</script>

	
<body>
<div class="header">RG1 Report</div>
<div class='accordion'>
  <h3><a href='#'>Show Control</a></h3>
<div class="headerbg">
	

		 <table style="width: 550px;">

			<tr>
			<td width="35" nowrap="nowrap">From Date:</td>
			<td width="135"><form:input path="rg1ReprtForm.fromDate"  id="fromDate" style="width:50pxl;"
							 size="16" readonly="true"/>
							</td>
			<td width="35" nowrap="nowrap">To Date:</td>
			<td width="135"><form:input path="rg1ReprtForm.toDate"  id="toDate"
							 size="16" readonly="true"/>
							</td></tr>
							<tr>
					<td></td><td><span id="msg" style="color: red"></span></td>
					<td></td><td><span id="msg1" style="color: red"></span></td>
					</tr>
			<tr>
			
			<td width="35" nowrap="nowrap">Branch Name:</td>
			<td width="135">
			<form:select path="rg1ReprtForm.branch"  id="branch">
				<form:option value="All" selected="selected">All</form:option>
				<form:options items="${branchList}" itemLabel="branch" itemValue="branch" />
			</form:select></td>
			
			<td width="35" nowrap="nowrap">Item Name:</td>
			
			<td width="135"><form:select path="rg1ReprtForm.itemName"  id="itemName"  >
				 <form:option value="All" selected="selected">All</form:option>
			<form:options items="${itemList}" itemLabel="itemName" itemValue="itemName" /> 
			</form:select></td>
			
						
			</tr>
			
	    </table> 
	<div style="float: right;    margin-top: -80px;    width: 359px; " >
			<ul class="icon">
			<li><input type="button" class="dwdshowreport" id="showreport" value=""/></li>
	<li><input type="button" class="dwdexl" id="downloadXls" value=""/>&nbsp;&nbsp;</li>
	
	<li><input type="button" class="dwdpdf" id="downloadPdf" value=""/>&nbsp;&nbsp;</li>
	</ul>
	</div>
		 	</div>
			</div>
	
	<div id="reportHtml"><img style="display:none" alt="loading"  id="loadingImageHtmlReport" src="${imagelink}"/></div>
</body>
