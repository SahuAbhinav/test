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

}
</style>

<script type="text/javascript">
	$(document).ready(
			function() {
					$('#itemGroupFlag').change(function() {
						
						if($("#itemGroupFlag").val()=='All'){
							$.ajax({type : "POST",
								url : "get_item_group_list",
								 success: function(data) {
									 $("#itemGroup").find("option").remove().end().append(new Option("All", "All")).attr("value", "All");
									 for (var i = 0; i< data.result.length; i++) {
									 $('<option></option>').val(data.result[i].itemGroupName).html(data.result[i].itemGroupName).appendTo('#itemGroup');
								 }}
							}); 
							}
						else
							{
						$.ajax({type : "POST",
						url : "get_item_group_name?itemGroupFlagName=" +  $("#itemGroupFlag").val().replace('&','%26'),
						 success: function(data) {
							
					   	$("#itemGroup").find("option").remove().end().append(new Option("All", "All")).attr("value", "All");
				      for (var i = 0; i< data.result.length; i++) {
					    $('<option></option>').val(data.result[i].itemGroupName).html(data.result[i].itemGroupName).appendTo('#itemGroup');
					    }}
					  }); 
					}
					  return false; 
					});	

				
		$('#itemGroup').change(function() {
			//alert($("#itemGroup").val());
		if($("#itemGroup").val()=='All'){
			 $.ajax({
				type : "POST",
				url : "get_category_name_for_report",
				 success: function(data) {
					 $("#itemcategory").find("option").remove().end().append(new Option("All", "All")).attr("value", "All");
					 for (var i = 0; i< data.result.length; i++) {
					 $('<option></option>').val(data.result[i].itemCategoryName).html(data.result[i].itemCategoryName).appendTo('#itemcategory');
				 }}
			}); 
			}
		else
			{ 
		$.ajax({
		type : "POST",
		url : "get_item_category_by_group?itemGroupId=" +  $("#itemGroup").val().replace('&','%26'),
		 success: function(data) {
	  $("#itemcategory").find("option").remove().end().append(new Option("All", "All")).attr("value", "All");
  	 for (var i = 0; i< data.result.length; i++) {
	    $('<option></option>').val(data.result[i].itemCategoryName).html(data.result[i].itemCategoryName).appendTo('#itemcategory');
	    }
	  }
	  }); 
	}
	  return false; 
	});	
 
 
 
 
 			$("#showreport").click(function() {
 				
	             $("#loadingImageHtmlReport").show();
						 $.ajax({
							type : "POST",
							url : "item_list_report/html?itemGroup=" + $('#itemGroup option:selected').text().replace('&','%26')+"&itemcategory="+$("#itemcategory").val().replace('&','%26')+"&itemGroupFlag="+$("#itemGroupFlag").val().replace('&','%26')+"&grade="+$("#grade").val().replace('&','%26')+"&classType="+$("#classType").val().replace('&','%26')+"&activeStatus="+$("#activeStatus").val(),
																
						    success: function(data) {
						    	
						    	$("#loadingImageHtmlReport").hide();
						    	$("#reportHtml").html(data.reportHtml);
						        }
							}); 
					
							return false;

						});

				$("#downloadPdf").click(function() {
					
					var url = "item_list_report/pdf?itemcategory=" +  $("#itemcategory").val().replace('&','%26')+ "&itemGroup=" + $('#itemGroup option:selected').text().replace('&','%26')+"&itemGroupFlag="+$("#itemGroupFlag").val().replace('&','%26')+"&grade="+$("#grade").val().replace('&','%26')+"&classType="+$("#classType").val().replace('&','%26')+"&activeStatus="+$("#activeStatus").val();
					try {
						var child = window.open(url);
						child.focus();
					} catch (e) {
					}
				});
				

				$("#downloadXls").click(function() {
					var url = "item_list_report/xls?itemcategory=" +  $("#itemcategory").val().replace('&','%26')+ "&itemGroup=" + $('#itemGroup option:selected').text().replace('&','%26')+"&itemGroupFlag="+$("#itemGroupFlag").val().replace('&','%26')+"&grade="+$("#grade").val().replace('&','%26')+"&classType="+$("#classType").val().replace('&','%26')+"&activeStatus="+$("#activeStatus").val();
					try {
						var child = window.open(url);
						child.focus();
					} catch (e) {
					}
				});	
				$("#downloadCsv").click(function() {
					var url = "item_list_report/csv?itemcategory=" +  $("#itemcategory").val().replace('&','%26')+ "&itemGroup=" + $('#itemGroup option:selected').text().replace('&','%26')+"&itemGroupFlag="+$("#itemGroupFlag").val().replace('&','%26')+"&grade="+$("#grade").val().replace('&','%26')+"&classType="+$("#classType").val().replace('&','%26')+"&activeStatus="+$("#activeStatus").val();
					try {
						var child = window.open(url);
						child.focus();
					} catch (e) {
					}
				});

			});// ready
</script>


<body>
<div class="header">Item List Report</div>
	<div class='accordion'>
		<h3>
			<a href='#'>Show Control</a>
		</h3>
		

<div class="headerbg">
	 
	<table style="width: 650px; float: left;">
		<tr>
		<td width="35" nowrap="nowrap">Group Flag Name:</td>
			<td width="75"><form:select path="itemReportFrom.itemGroupFlag"  id="itemGroupFlag"  >
				<form:option value="All" selected="selected">All</form:option>
				<form:options items="${itemGroupFlagList}" itemLabel="itemGroupFlagName" itemValue="itemGroupFlagName" />
			</form:select></td>
		
		<td width="33" >Item Group:</td>
		<td width="75" >
		<form:select style="width:98%" path="itemReportFrom.itemGroup" id="itemGroup" >	
					<form:option value="All" selected="selected">All</form:option>
					<form:options items="${itemgroupList}" itemLabel="itemGroupName" itemValue="itemGroupId"/>
				    </form:select></td>
				    
		<td width="33">Item Category:</td>
		<td width="75" >
		<form:select  path="itemReportFrom.itemCategory" id="itemcategory">
				<form:option value="All" selected="selected">All</form:option>
				<form:options items="${itemcategoryList}" itemLabel="itemCategoryName" itemValue="itemCategoryName"/>
					</form:select></td>
			</tr>
			<tr>		
					
		<td width="35">Grade:</td>
			<td width="135"><form:select path="itemReportFrom.grade"  id="grade" >
				<form:option value="All" selected="selected">All</form:option>
				<form:options items="${masterList}" itemLabel="name" itemValue="name" />
			</form:select></td>
			
		<td width="85">Class Type:</td>
			<td width="145"><form:select path="itemReportFrom.classType" id="classType">
				<form:option value="All" selected="selected">All</form:option>
				<form:options items="${masterList2}" itemLabel="name" itemValue="name" />
			</form:select></td>
			<td width="85">Active Status</td>
			<td width="145"><form:select path="itemReportFrom.activeStatus" id="activeStatus">
				<form:option value="All" selected="selected">All</form:option>
				<form:option value="1" >Yes</form:option>
				<form:option value="0" >No</form:option>
			</form:select></td>
			</tr>
			</table>
				
			<div style="float: right; margin-top: -18px; width: 250px; " >
			<ul class="icon">
	<li><input type="button" class="dwdshowreport" id="showreport" value=""/></li>
	<li><input type="button" class="dwdexl" id="downloadXls" value=""/>&nbsp;&nbsp;</li>
	
	<li><input type="button" class="dwdpdf" id="downloadPdf" value=""/>&nbsp;&nbsp;</li>
	</ul>

</div>
		 	</div>
		 	
			</div>

	<div id="reportHtml">
		<img style="display: none" alt="loading" id="loadingImageHtmlReport"
			src="${imagelink}" />
	</div>
</body>
