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

	<c:url value="/invoice_report/xls" var="downloadXls" />
	<c:url value="/invoice_report/pdf" var="downloadPdf" />
	<c:url value="/invoice_report/csv" var="downloadCsv" />
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
#aside h3 {
background-color:#4e8ccf;
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

}.ui-widget-content {
			display:none;
			}
			</style>
<script type="text/javascript">
	$(document).ready(	function() {
		
		

		//	alert($('#itemGroupName option:selected').text()); 
		$('#itemGroupFlag').change(function() {
			if($("#itemGroupFlag").val()=='All'){
				$.ajax({
					type : "POST",
					url : "get_category_name_for_report",
					 success: function(data) {
						 $("#itemCategoryName").find("option").remove().end().append(new Option("All", "All")).attr("value", "All");
						 for (var i = 0; i< data.result.length; i++) {
						 $('<option></option>').val(data.result[i].itemCategoryId).html(data.result[i].itemCategoryName).appendTo('#itemCategoryName');
					 }}
				}); 
			}
			else
		{
			$.ajax({
			type : "POST",
			url : "get_category_group_flag_name?itemGroupFlagName=" +  $("#itemGroupFlag").val().replace('&','%26'),
			 success: function(data) {
		   	
				$('#itemCategoryName').html('');  
				$('<option></option>').html('All').val('All').appendTo('#itemCategoryName');
			
	      for (var i = 0; i< data.result.length; i++) {
		    $('<option></option>').val(data.result[i].itemCategoryId).html(data.result[i].itemCategoryName).appendTo('#itemCategoryName');
		    }
		 
			}
		  }); 
			
			
			
			
			// item list as per item group flege name
			$.ajax({
			type : "POST",
			url : "get_item_by_ItemGroupflagName?itemGroupflagName=" +  $("#itemGroupFlag").val().replace('&','%26'),
			 success: function(data) {
		   	
				 $("#itemName").find("option").remove().end().append(new Option("All", "All")).attr("value", "All");
				 for (var i = 0; i< data.result.length; i++) {
				 $('<option></option>').val(data.result[i].itemName).html(data.result[i].itemName).appendTo('#itemName');
		    }
			}
		  }); 
			
			//end
		}
		  return false; 
		});	
	
		
		
		
		
			//	alert($('#itemGroupName option:selected').text()); 
			$('#itemCategoryName').change(function() {
				if($("#itemCategoryName").val()=='All'){
					$.ajax({
						type : "POST",
						url : "get_item_name_for_report",
						 success: function(data) {
							 $("#itemName").find("option").remove().end().append(new Option("All", "All")).attr("value", "All");
							 for (var i = 0; i< data.result.length; i++) {
							 $('<option></option>').val(data.result[i].itemName).html(data.result[i].itemName).appendTo('#itemName');
						 }}
					}); 
					}
				else
					{
				$.ajax({
				type : "POST",
				url : "get_item_by_ItemCategoryId?itemCategoryId=" +  $("#itemCategoryName").val().replace('&','%26'),
				 success: function(data) {
			   	
					$('#itemName').html('');  
					$('<option></option>').html('All').val('All').appendTo('#itemName');
				
		      for (var i = 0; i< data.result.length; i++) {
			    $('<option></option>').val(data.result[i].itemName).html(data.result[i].itemName).appendTo('#itemName');
			    }
				}
			  }); 
			}
			  return false; 
			});	
		
		
		
		
		
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
				url : "booked_finish_good_report/html?"+ "fromDate=" +  $("#fromDate").val()+ "&toDate=" +  $("#toDate").val()+ "&itemName=" +  $("#itemName").val()+ "&itemGroupFlag=" + $("#itemGroupFlag").val()+"&milShort="+$("#milShort").val()+"&orderShort="+$("#orderShort").val()+"&itemcategory="+$("#itemCategoryName").val()+"&activeStatus="+$("#activeStatus").val(),  
			    success: function(data) {
			    	$("#loadingImageHtmlReport").hide();
			    	$("#reportHtml").html(data.reportHtml);
			}
				}); 
				   return false; 
				   
				   
				   
			    });	
		
					
					$("#downloadPdf").click( function() {
						
						var itemName=$("#itemName").val();
						itemName=itemName.replace('&','%26');
						
						var itemGroupFlag=$("#itemGroupFlag").val();
						itemGroupFlag=itemGroupFlag.replace('&','%26');
						
						if ($("#fromDate").val() == '') {
							$("#msg").text("From date can not be null!");
							return false;
							}
							if ($("#toDate").val() == '') {
								$("#msg1").text("To date can not be null!");
								 return false;
								}
						var url = "booked_finish_good_report/pdf?"+ "fromDate=" +  $("#fromDate").val()+ "&toDate=" +  $("#toDate").val()+ "&itemName=" + itemName + "&itemGroupFlag=" + itemGroupFlag +"&milShort="+$("#milShort").val()+"&orderShort="+$("#orderShort").val()+"&itemcategory="+$("#itemCategoryName").val()+"&activeStatus="+$("#activeStatus").val();
						 		try { var child = window.open(url); child.focus(); } catch (e) { }
					});	
					
					$("#downloadXls").click( function() {
						var itemName=$("#itemName").val();
						itemName=itemName.replace('&','%26');
						
						var itemGroupFlag=$("#itemGroupFlag").val();
						itemGroupFlag=itemGroupFlag.replace('&','%26');
						if ($("#fromDate").val() == '') {
							$("#msg").text("From date can not be null!");
							return false;
							}
							if ($("#toDate").val() == '') {
								$("#msg1").text("To date can not be null!");
								 return false;
								}
						var url = "booked_finish_good_report/xls?"+ "fromDate=" +  $("#fromDate").val()+ "&toDate=" +  $("#toDate").val()+ "&itemName=" + itemName + "&itemGroupFlag=" + itemGroupFlag +"&milShort="+$("#milShort").val()+"&orderShort="+$("#orderShort").val()+"&itemcategory="+$("#itemCategoryName").val()+"&activeStatus="+$("#activeStatus").val();
					 		try { var child = window.open(url); child.focus(); } catch (e) { }
					 });	
	
					$("#downloadCsv").click( function() {
						var itemName=$("#itemName").val();
						itemName=itemName.replace('&','%26');
						
						var itemGroupFlag=$("#itemGroupFlag").val();
						itemGroupFlag=itemGroupFlag.replace('&','%26');
						if ($("#fromDate").val() == '') {
							$("#msg").text("From date can not be null!");
							return false;
							}
							if ($("#toDate").val() == '') {
								$("#msg1").text("To date can not be null!");
								 return false;
								}
						var url = "booked_finish_good_report/csv?"+ "fromDate=" +  $("#fromDate").val()+ "&toDate=" +  $("#toDate").val()+ "&itemName=" + itemName + "&itemGroupFlag=" + itemGroupFlag +"&milShort="+$("#milShort").val()+"&orderShort="+$("#orderShort").val()+"&itemcategory="+$("#itemCategoryName").val()+"&activeStatus="+$("#activeStatus").val();
					 		try {
					 			var child = window.open(url);
					 	  child.focus(); 
					 	} catch (e) { }
					});	
	
				});// ready
</script>
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
<body>
<div class="header">Booked Finished Good Report</div>
<div class='accordion'>
  <h3><a href='#'>Show Control</a></h3>
 
<div class="headerbg">
	
	

		<table style="width:650px; float: left;">
			<tr>
			<td width="35" nowrap="nowrap"> Date:<span style="color: red;">*</span></td>
			<td width="135"><input name="fromDate"  id="fromDate" style="width:50pxl;"
							 size="16" readonly="true"/>
							</td>
			<!-- <td width="35" nowrap="nowrap">To Date:<span style="color: red;">*</span></td>
			<td width="135"><input name="issueReportFrom.toDate"  id="toDate"
							 size="16" readonly="true"/>
							</td> -->
								<td width="55" nowrap="nowrap">Item Group Name:</td>
			<td width="135"><form:select path="salesReportForm.itemGroupFlagName" id="itemGroupFlag" >
				<form:option value="All" selected="selected">All</form:option>
				<form:options items="${itemGroupFlagList}" itemLabel="itemGroupFlagName" itemValue="itemGroupFlagName"  />
			</form:select></td>
							<td width="33">Item Category:</td>
		<td width="135" >
		<form:select  path="salesReportForm.itemCategory" id="itemCategoryName" style="width:120px;" >
				<form:option value="All" selected="selected">All</form:option>
				<form:options items="${itemcategoryList}" itemLabel="itemCategoryName" itemValue="itemCategoryId"/>
					</form:select></td>
							</tr>
							<tr>
					<td></td><td><span id="msg" style="color: red"></span></td>
					<td></td><td><span id="msg1" style="color: red"></span></td>
					</tr>
			<tr>
			<tr>
		
			<td width="35" nowrap="nowrap">Item Name:</td>
			<td width="135"><form:select path="salesReportForm.itemName"  id="itemName"  >
				<form:option value="All" selected="selected">All</form:option>
				<form:options items="${itemList}" itemLabel="itemName" itemValue="itemName" />
			</form:select></td>
			
			<td width="35">MIL Short</td>
			<td width="135"><select name="milShort"  id="milShort" >
				<option value="" selected="selected">No</option>
				<option value="yes" >Yes</option>
				
				
			</select></td>
				<td width="35">Order Short</td>
			<td width="135"><select name="orderShort"  id="orderShort" >
				<option value="yes" >Yes</option>
				<option value="">No</option>
				
			</select></td>
			<td width="85">Active Status</td>
			<td width="145"><form:select path="salesReportForm.activeStatus" id="activeStatus">
				<form:option value="All" selected="selected">All</form:option>
				<form:option value="1" >Yes</form:option>
				<form:option value="0" >No</form:option>
			</form:select></td>
			</tr>
	
		
	</table> 
	<div style="float: right;    margin-top: -21px;    width: 250px; " >
			<ul class="icon" >
	<li><input type="button" class="dwdshowreport" id="showreport" value=""/></li>
	<li><input type="button" class="dwdexl" id="downloadXls" value=""/>&nbsp;&nbsp;</li>
	
	<li><input type="button" class="dwdpdf" id="downloadPdf" value=""/>&nbsp;&nbsp;</li>
	
<!-- 	<li><input type="button" id="downloadCsv" value="Download CSV"/>&nbsp;&nbsp;</li> -->
	</ul>
	</div>
		 	</div>
			</div>
	
<%-- 	<c:url value="/geographic_report/html" var="downloadHTML" />
	<a href="${downloadHTML}">Download HTML</a> --%>

<div id="reportHtml"><img style="display:none" alt="loading"  id="loadingImageHtmlReport" src="${imagelink}"/></div>
</body>
