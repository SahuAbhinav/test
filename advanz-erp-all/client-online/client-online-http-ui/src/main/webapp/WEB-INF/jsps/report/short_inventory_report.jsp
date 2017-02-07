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
 
	<c:url value="/short_inventory_report/xls" var="downloadXls" />
	<c:url value="/Short_inventory_report/pdf" var="downloadPdf" />
	<c:url value="/Short_inventory_report/csv" var="downloadCsv" />
	<c:url value="/static/images/loading_icon.gif" var="imagelink" />
	<style>
.ui-widget-content {
display:none;
}

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
}

}

</style>


 
	<script type="text/javascript">
	$(document).ready(function()
	{
	$("#fromDate").datepicker({
		autoSize : true,
		changeMonth : true,
		changeYear : true,
		dateFormat : 'dd-M-yy',changeMonth: true,
        changeYear: true, yearRange: '-99:+0', maxDate: '+0M +0D'
	});		
	$('#fromDate').datepicker().datepicker('setDate',new Date());
	});
	</script>

	<script type="text/javascript">
	$(function() {
		
		
//		alert($('#itemGroupName option:selected').text()); 
		$('#itemGroupName').change(function() {
			if($("#itemGroupName").val()=='All'){
				$.ajax({
					type : "POST",
					url : "get_category_name_for_report",
					 success: function(data) {
						 $("#itemCategoryName").find("option").remove().end().append(new Option("All", "All")).attr("value", "All");
						 for (var i = 0; i< data.result.length; i++) {
						 $('<option></option>').val(data.result[i].itemCategoryName).html(data.result[i].itemCategoryName).appendTo('#itemCategoryName');
					 }}
				}); 
			}
			else
		{
			$.ajax({
			type : "POST",
			url : "get_category_group_flag_name?itemGroupFlagName=" +  $("#itemGroupName").val(),
			 success: function(data) {
		   	
				$('#itemCategoryName').html('');  
				$('<option></option>').html('All').val('All').appendTo('#itemCategoryName');
			if(data.status!=null){
	      for (var i = 0; i< data.result.length; i++) {
		    $('<option></option>').val(data.result[i].itemCategoryName).html(data.result[i].itemCategoryName).appendTo('#itemCategoryName');
		    }
			 }
		 //end if
			}
		  });
		}
		}); 
		
		
		
		
		
		
		
		
		
		
	$("#showreport").click(
		function() {
		$('#fromDate').change(function() {
		if ($("#fromDate").val() != '') {
			$("#msg").empty();	
			}
		});
				
				
		if ($("#fromDate").val() == '') {
			$("#msg").text("date can not be null!");
			return false;
			}
				
		$("#loadingImageHtmlReport").show();
		   var jsonReponseValues={"itemGroupName": $("#itemGroupName").val() , "reorderName":  $("#reorderName").val() , "itemClass": $("#itemClass").val(), "status": $("#status").val()};
		   $.ajax({
			type : "POST",
			url : "show_short_inventory_report/html?"+ "fromDate=" +  $("#fromDate").val()+  "&itemGroupName=" +$("#itemGroupName").val().replace('&','%26')+"&reorderName=" +  $("#reorderName").val()+ "&itemClass=" + $("#itemClass").val()+ "&status=" + $("#status").val()+ "&itemCategoryName=" + $("#itemCategoryName").val()+"&activeStatus="+$("#activeStatus").val(),
		   	type: "POST",
		    dataType: "json",
		    data: JSON.stringify(jsonReponseValues),
		    contentType: "application/json",
		    success: function(data) {
		    	$("#loadingImageHtmlReport").hide();
		    	$("#reportHtml").html(data.reportHtml);
			}
			}); 
			   return false; 
			   
		    });	
	
				
		$("#downloadPdf").click( function() {
			
			if ($("#fromDate").val() == '') {
				$("#msg").text("date can not be null!");
				return false;
			}
		var url = "show_short_inventory_report/pdf?"+ "fromDate=" +  $("#fromDate").val()+  "&itemGroupName=" +$("#itemGroupName").val().replace('&','%26')+"&reorderName=" +  $("#reorderName").val()+ "&itemClass=" + $("#itemClass").val()+ "&status=" + $("#status").val()+ "&itemCategoryName=" + $("#itemCategoryName").val()+"&activeStatus="+$("#activeStatus").val();
				try { var child = window.open(url); child.focus(); } catch (e) { }
				});	
				
				$("#downloadXls").click( function() {
										
					if ($("#fromDate").val() == '') {
						$("#msg").text("date can not be null!");
						return false;
						}
							var url = "show_short_inventory_report/xls?"+ "fromDate=" +  $("#fromDate").val()+  "&itemGroupName=" +$("#itemGroupName").val().replace('&','%26')+"&reorderName=" +  $("#reorderName").val()+ "&itemClass=" + $("#itemClass").val()+ "&status=" + $("#status").val()+ "&itemCategoryName=" + $("#itemCategoryName").val()+"&activeStatus="+$("#activeStatus").val();
				 		try { var child = window.open(url); child.focus(); } catch (e) { }
				 });	

				$("#downloadCsv").click( function() {
									
					if ($("#fromDate").val() == '') {
						$("#msg").text("date can not be null!");
						return false;
						}
						var url = "show_short_inventory_report/csv?"+ "fromDate=" +  $("#fromDate").val()+  "&itemGroupName=" + $("#itemGroupName").replace('&','%26')+"&reorderName=" +  $("#reorderName").val()+ "&itemClass=" + $("#itemClass").val()+ "&status=" + $("#status").val()+ "&itemCategoryName=" + $("#itemCategoryName").val()+"&activeStatus="+$("#activeStatus").val();
				 		try {
				 			var child = window.open(url);
				 	  child.focus(); 
				 	} catch (e) { }
				});	

			});// ready

	</script>
	
	
	
<body>
	<div class="header">Short Inventory Report</div>
	<div class='accordion'>
  	<h3><a href='#'>Show Control</a></h3>
	<div class="headerbg">

	 <table style="width: 620px;">

		<tr>
		<td width="35" nowrap="nowrap">As on Date:</td>
		<td width="135"><form:input path="shortInventoryReportForm.datePrompt" id="fromDate" style="width:50pxl;"
					 size="16" readonly="true"/></td>
		
		
		  <td width="35" nowrap="nowrap">Group Name:</td>
		 
		  <td width="135">
		 	 <form:select path="shortInventoryReportForm.itemGroupName" id="itemGroupName"  >
		     <form:option value="All" selected="selected">All</form:option>
			 <form:options items="${itemGroupList}" itemLabel="itemGroupName" itemValue="itemGroupName" />
			</form:select></td>
			 <td width="35" nowrap="nowrap">Item Category</td>
		 
		  <td width="135">
		 	 <form:select path="shortInventoryReportForm.itemCategoryName" id="itemCategoryName"  >
		     <form:option value="All" selected="selected">All</form:option>
			 <form:options items="${itemcategoryList}" itemLabel="itemCategoryName" itemValue="itemCategoryName" />
			</form:select></td>
			
		</tr>	
	<tr>		
			<td width="35" nowrap="nowrap">Reorder Prompt:</td>
			<td width="135">
			<form:select path="shortInventoryReportForm.reorderName" id="reorderName">
			<form:option value="MIL Wise" selected="selected">MIL Wise</form:option>
				<form:option value="Reorder" selected="">Reorder level</form:option>
				
				</form:select></td>
			
			<td width="85">Class Type:</td>
			<td width="145"><form:select path="shortInventoryReportForm.itemClass" id="itemClass">
				<form:option value="All" selected="selected">All</form:option>
				<form:options items="${masterList}" itemLabel="name" itemValue="name" />
			</form:select></td>
			
			<td width="35">Status:</td>
			<td width="135"><form:select path="shortInventoryReportForm.status"  id="status" >
				<form:option value="Short" selected="selected">Short</form:option>
				<form:option value="All">All</form:option>	
			</form:select></td>
			</tr>
			<tr>
			<td width="85">Active Status</td>
			<td width="145"><select name="activeStatus" id="activeStatus">
				<option value="1" >Yes</option>
				<option value="0" >No</option>
			</select></td>
			</tr>
	    </table> 
	<div style="float: right;    margin-top: -94px;    width: 215px; " >
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
	
