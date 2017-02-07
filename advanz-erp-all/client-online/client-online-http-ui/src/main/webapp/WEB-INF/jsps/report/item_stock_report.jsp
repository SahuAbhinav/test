
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
 
	<c:url value="/item_stock_report/xls" var="downloadXls" />
	<c:url value="/item_stock_report/pdf" var="downloadPdf" />
	<c:url value="/item_stock_report/csv" var="downloadCsv" />
	<c:url value="/static/images/loading_icon.gif" var="imagelink" />
	<style>

Select{
width: 100% !important ;
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
					
		//	alert($('#itemGroupName option:selected').text()); 
		$('#itemGroupFlagName').change(function() {
			if($("#itemGroupFlagName").val()=='All'){
				$.ajax({
					type : "POST",
					url : "get_item_group_list",
					 success: function(data) {
						 $("#itemGroupName").find("option").remove().end().append(new Option("All", "All")).attr("value", "All");
						 for (var i = 0; i< data.result.length; i++) {
						 $('<option></option>').val(data.result[i].itemGroupId).html(data.result[i].itemGroupId).appendTo('#itemGroupName');
					 }}
				}); 
				}
			else
				{
			$.ajax({
			type : "POST",
			url : "get_item_group_name?itemGroupFlagName=" +  $("#itemGroupFlagName").val().replace('&','%26'),
			 success: function(data) {
		   	
			 $("#itemGroupName").find("option").remove().end().append(new Option("All", "All")).attr("value", "All");
	      for (var i = 0; i< data.result.length; i++) {
		    $('<option></option>').val(data.result[i].itemGroupId).html(data.result[i].itemGroupName).appendTo('#itemGroupName');
		    }
		 	}
		  }); 
			
			// item list as per item group flage name
			$.ajax({
			type : "POST",
			url : "get_item_by_ItemGroupflagName?itemGroupflagName=" +  $("#itemGroupFlagName").val().replace('&','%26'),
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
		$('#itemGroupName').change(function() {
			if($("#itemGroupName").val()=='All'){
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
			url : "get_item_category_by_ItemGroupflagId?itemGroupflagId=" +  $("#itemGroupName").val().replace('&','%26'),
			 success: function(data) {
		   	
				$('#itemCategoryName').html('');  
				$('<option></option>').html('All').val('All').appendTo('#itemCategoryName');
			
	      for (var i = 0; i< data.result.length; i++) {
		    $('<option></option>').val(data.result[i].itemCategoryId).html(data.result[i].itemCategoryName).appendTo('#itemCategoryName');
		    }
		
			}
		  }); 
			
			// item list as per item group id
			$.ajax({
			type : "POST",
			url : "get_item_by_ItemGroupId?itemGroupId=" +  $("#itemGroupName").val().replace('&','%26'),
			 success: function(data) {
		   	
			  
				 $("#itemName").find("option").remove().end().append(new Option("All", "All")).attr("value", "All");
				 for (var i = 0; i< data.result.length; i++) {
				 $('<option></option>').val(data.result[i].itemName).html(data.result[i].itemName).appendTo('#itemName');
		    }
		  
			}
		  }); 
			
			// end
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
		
		$("#showreport").click(function() {
			
					if ($("#fromDate").val() == '') {
					$("#msg").text("From date can not be null!");
					return false;
					}
					if ($("#toDate").val() == '') {
						$("#msg1").text("To date can not be null!");
						 return false;
						}
					
			$("#loadingImageHtmlReport").show();
			
			var groupName=$("#itemGroupName").val();
			groupName=groupName.replace('&','%26'); 
			var itemGroupFlagName= $("#itemGroupFlagName").val();
			itemGroupFlagName=itemGroupFlagName.replace('&','%26');
			var itemName=$("#itemName").val();
			itemName=itemName.replace('&','%26');
			var name=$("#name").val();
			name=name.replace('&','%26');
			var itemcategory=$("#itemCategoryName").val();
			itemcategory=itemcategory.replace('&','%26');
			
			if ($("#fromDate").val() == '') {
				$("#msg").text("From date can not be null!");
				return false;
				}
				if ($("#toDate").val() == '') {
					$("#msg1").text("To date can not be null!");
					 return false;
					}
			   $.ajax({
				type : "POST",
				url : "item_stock_report/html?"+ "fromDate=" +  $("#fromDate").val()+ "&toDate=" +  $("#toDate").val()+ "&itemGroupFlagName=" + itemGroupFlagName +"&itemGroupName=" +  groupName + "&name=" + name + "&itemName=" +  itemName + "&itemcategory="+itemcategory+"&activeStatus="+$("#activeStatus").val()+"&valuationBy="+$("#valuationBy").val(),
			    success: function(data) {
			    	$("#loadingImageHtmlReport").hide();
			    	$("#reportHtml").html(data.reportHtml);
				}
				}); 
				   return false; 
			    });	
		
					
					$("#downloadPdf").click( function() {
						
						var groupName=$("#itemGroupName").val();
						groupName=groupName.replace('&','%26'); 
						var itemGroupFlagName= $("#itemGroupFlagName").val();
						itemGroupFlagName=itemGroupFlagName.replace('&','%26');
						var itemName=$("#itemName").val();
						itemName=itemName.replace('&','%26');
						var name=$("#name").val();
						name=name.replace('&','%26');
						var itemcategory=$("#itemCategoryName").val();
						itemcategory=itemcategory.replace('&','%26');
						
						if ($("#fromDate").val() == '') {
							$("#msg").text("From date can not be null!");
							return false;
							}
							if ($("#toDate").val() == '') {
								$("#msg1").text("To date can not be null!");
								 return false;
								}
						var url = "item_stock_report/pdf?"+ "fromDate=" +  $("#fromDate").val()+ "&toDate=" +  $("#toDate").val()+ "&itemGroupFlagName=" + itemGroupFlagName +"&itemGroupName=" +  groupName + "&name=" + name + "&itemName=" +  itemName + "&itemcategory="+itemcategory+"&activeStatus="+$("#activeStatus").val()+"&valuationBy="+$("#valuationBy").val();
						try { var child = window.open(url); child.focus(); } catch (e) { }
					});	
					
					$("#downloadXls").click( function() {
						var groupName=$("#itemGroupName").val();
						groupName=groupName.replace('&','%26'); 
						var itemGroupFlagName= $("#itemGroupFlagName").val();
						itemGroupFlagName=itemGroupFlagName.replace('&','%26');
						var itemName=$("#itemName").val();
						itemName=itemName.replace('&','%26');
						var name=$("#name").val();
						name=name.replace('&','%26');
						var itemcategory=$("#itemCategoryName").val();
						itemcategory=itemcategory.replace('&','%26');
						if ($("#fromDate").val() == '') {
							$("#msg").text("From date can not be null!");
							return false;
							}
							if ($("#toDate").val() == '') {
								$("#msg1").text("To date can not be null!");
								 return false;
								}
						var url = "item_stock_report/xls?"+ "fromDate=" +  $("#fromDate").val()+ "&toDate=" +  $("#toDate").val()+ "&itemGroupFlagName=" + itemGroupFlagName +"&itemGroupName=" +  groupName + "&name=" + name + "&itemName=" +  itemName + "&itemcategory="+itemcategory+"&activeStatus="+$("#activeStatus").val()+"&valuationBy="+$("#valuationBy").val();
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
						var url = "item_stock_report/csv?"+ "fromDate=" +  $("#fromDate").val()+ "&toDate=" +  $("#toDate").val()+ "&itemGroupFlagName=" +  $("#itemGroupFlagName").val()+"&itemGroupName=" +  $("#itemGroupName").val()+ "&name=" + $("#name").val()+ "&itemName=" +  $("#itemName").val()+"&activeStatus="+$("#activeStatus").val()+"&valuationBy="+$("#valuationBy").val();
					 		try {
					 			var child = window.open(url);
					 	  child.focus(); 
					 	} catch (e) { }
					});	
	
				});// ready
</script>

	
<body>
<div class="header">Item Stock Report</div>
<div class='accordion'>
  <h3><a href='#'>Show Control</a></h3>
<div class="headerbg">
	

		 <table style="width: 600px; float:left;">
	
			<tr>
			<td width="35" nowrap="nowrap">From Date:</td>
			<td width="135"><form:input path="itemReportFrom.fromDate"  id="fromDate" style="width:80px;"
							 size="16" readonly="true"/>
							</td>
			<td width="35" nowrap="nowrap">To Date:</td>
			<td width="135"><form:input path="itemReportFrom.fromDate"  id="toDate" style="width:80px;"
							 size="16" readonly="true"/>
							</td>
							<td width="85">Valuation By:</td>
			<td width="45"><select name="valuationBy" id="valuationBy">
				<option value="Weighted Rate" selected="selected">Weighted Rate</option>
				<option value="Basic Rate" >Basic Rate</option>
				
			</select></td>
							</tr>
							<tr>
					<td></td><td><span id="msg" style="color: red"></span></td>
					<td></td><td><span id="msg1" style="color: red"></span></td>
					</tr>
			<tr>
			<td width="35" nowrap="nowrap">Group Flag Name:</td>
			<td width="135"><form:select path="itemReportFrom.itemGroupFlagName"  id="itemGroupFlagName"  style="width:120px;" >
				<form:option value="All" selected="selected">All</form:option>
				<form:options items="${itemGroupFlagList}" itemLabel="itemGroupFlagName" itemValue="itemGroupFlagName" />
			</form:select></td>
			<td width="35" nowrap="nowrap">Group Name:</td>
			<td width="135"><form:select path="itemReportFrom.itemGroupName"  id="itemGroupName" style="width:120px;" >
				<form:option value="All" selected="selected">All</form:option>
				<form:options items="${itemGroupList}" itemLabel="itemGroupName" itemValue="itemGroupId" /> 
			</form:select></td>
			<td width="33">Item Category:</td>
		<td width="75" >
		<form:select  path="itemReportFrom.itemCategory" id="itemCategoryName" style="width:120px;" >
				<form:option value="All" selected="selected">All</form:option>
				<form:options items="${itemcategoryList}" itemLabel="itemCategoryName" itemValue="itemCategoryId"/>
					</form:select></td>
			</tr>
			<tr style="width:80px;">
			
			<td width="35" nowrap="nowrap">Item Name:</td>
			<td width="135"><form:select path="itemReportFrom.itemName"  id="itemName" style="width:120px;" >
				<form:option value="All" selected="selected">All</form:option>
			 	<form:options items="${itemList}" itemLabel="itemName" itemValue="itemName" /> 
			</form:select></td>
			
			<td width="35">Grade:</td>
			<td width="135"><form:select path="itemReportFrom.name"  id="name" style="width:120px;">
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
	<div style="float: right;    margin-top: -18px;    width: 180px; " >
			<ul class="icon">
			<li><input type="button" class="dwdshowreport" id="showreport" value=""/></li>
	<li><input type="button" class="dwdexl" id="downloadXls" value=""/>&nbsp;&nbsp;</li>
	
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
