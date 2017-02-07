<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!-- 	  <script src="http://code.jquery.com/jquery-1.8.3.js"></script> -->
 
	<script>
	$(document).ready(function() {
	    $( ".accordion" ).accordion({
	     collapsible: true,
	active: -1
	});
	});

	  </script>
 


	<c:url value="/geographic_report/xls" var="downloadXls" />
	<c:url value="/geographic_report/pdf" var="downloadPdf" />
	<c:url value="/geographic_report/csv" var="downloadCsv" />
	<c:url value="/static/images/loading_icon.gif" var="imagelink" />
	
<style>
 
Select{
	text-transform: uppercase;
width: 98% !important ;
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
	$(document).ready(	function() {
						//Append all option to the select of zone.
						// initially set option to all
						$("#zone").attr("value", "All");
						$("#state").attr("value", "All");
						$("#region").attr("value", "All");
						$("#area").attr("value", "All");
						
						//disable all the select except zone.
						$("#state").prop('disabled', 'disabled');
						$("#region").prop('disabled', 'disabled');
						$("#area").prop('disabled', 'disabled');
						
						$("#zone").change( function() { 
							$("#loadingImageState").show();
							$("#state").find("option").remove().end().append(new Option("All", "All")).attr("value", "All").prop("disabled", "disabled");
							$("#region").find("option").remove().end().append(new Option("All", "All")).attr("value", "All").prop("disabled", "disabled");
							
							$("#area").find("option").remove().end().append(new Option("All", "All")).attr("value", "All").prop("disabled", "disabled");
														
							var ruleAssignment  = {  zone: $("#zone").val() };
							jQuery.ajax({
							    url: "geographic_report/getStates",
							    type: "POST",
							    dataType: "json",
							    data: JSON.stringify(ruleAssignment),
							    contentType: "application/json",
							    success: function(data) {
							    	$("#loadingImageState").hide();
							    	$("#state").prop('disabled', false);
									
							         var html = "<option value=\"\">All</option>";
										var len = data.length;
										for ( var i = 0; i < len; i++) {
											html += "<option value=\"" + data[i].stateId + "\">"
													+ data[i].stateName
													+ "</option>";
										}
										html += "</option>";

										$("#state").html(html);
							    }
							}); 
					//return false;

					});
																	
					$("#state").change( function() { 
							$("#loadingImageRegion").show();
							
							$("#region").find("option").remove().end().append(new Option("All", "All")).attr("value", "All").prop("disabled", "disabled");
							
							$("#area").find("option").remove().end().append(new Option("All", "All")).attr("value", "All").prop("disabled", "disabled");
							
							var ruleAssignment  = {"state":$("#state").val()};
							jQuery.ajax({
							    url: "geographic_report/getRegions",
							    type: "POST",
							    dataType: "json",
							    data: JSON.stringify(ruleAssignment),
							    contentType: "application/json",
							    success: function(data) {
							    	$("#loadingImageRegion").hide();
							    	$("#region").prop('disabled', false);
									var html = "<option value=\"\">All</option>";
										var len = data.length;
										for ( var i = 0; i < len; i++) {
											html += "<option value=\"" + data[i].regionId + "\">"
													+ data[i].regionName
													+ "</option>";
										}
										html += "</option>";

										$("#region").html(html);
							    }
							}); 
					//return false;

					});	
					
					$("#region").change( function() { 
						$("#loadingImageArea").show();
						
						$("#area").find("option").remove().end().append(new Option("All", "All")).attr("value", "All").prop("disabled", "disabled");
						
						var ruleAssignment  = {"region": $("#region").val()};
						jQuery.ajax({
						    url: "geographic_report/getAreas",
						    type: "POST",
						    dataType: "json",
						    data: JSON.stringify(ruleAssignment),
						    contentType: "application/json",
						    success: function(data) {
						    	$("#loadingImageArea").hide();
						    	$("#area").prop('disabled', false);
								 var html = "<option value=\"\">All</option>";
									var len = data.length;
									for ( var i = 0; i < len; i++) {
										html += "<option value=\"" + data[i].areaId + "\">"
												+ data[i].areaName
												+ "</option>";
									}
									html += "</option>";

									$("#area").html(html);
						    }
						}); 
				//return false;

				});
					
					$("#showreport").click( function() {
						var ruleAssignment  = {"region": $('#region option:selected').text() , "zone": $('#zone option:selected').text() , "area": $('#area option:selected').text() , "state": $('#state option:selected').text()};
						$("#loadingImageHtmlReport").show();
						jQuery.ajax({
						    url: "geographic_report/html",
						    type: "POST",
						    dataType: "json",
						    data: JSON.stringify(ruleAssignment),
						    contentType: "application/json",
						    success: function(data) {
						    	$("#loadingImageHtmlReport").hide();
						    	$("#reportHtml").html(data.reportHtml);
						    }
						}); 
						return false;
					});	
					
					$("#downloadPdf").click( function() {
						var url = "geographic_report/pdf?"+ "region=" +  $('#zone option:selected').text()+ "&zone=" +   $('#zone option:selected').text()+ "&area=" +  $('#area option:selected').text()+"&state=" +   $('#state option:selected').text();
							try {
								var child = window.open(url); 
								child.focus(); } catch (e) {
									
								}
					});	
					
					$("#downloadXls").click( function() {
						var url = "geographic_report/xls?"+ "region=" +  $('#zone option:selected').text()+ "&zone=" +   $('#zone option:selected').text()+ "&area=" +  $('#area option:selected').text()+"&state=" +   $('#state option:selected').text();
						
					 		try { var child = window.open(url); child.focus(); } catch (e) { }
					 });	
	
					$("#downloadCsv").click( function() {
						var url = "geographic_report/csv?"+ "region=" +  $('#zone option:selected').text()+ "&zone=" +   $('#zone option:selected').text()+ "&area=" +  $('#area option:selected').text()+"&state=" +   $('#state option:selected').text();
							try { var child = window.open(url); child.focus(); } catch (e) { }
					});	
	
				});// ready
</script>

	
<body>
<div class="header">Geographic List Report</div>
<div class='accordion'>
  <h3><a href='#'>Show Control</a></h3>
 
<div class="headerbg">
	 
	<table style="width: 467px;" >
		<tr>
			<td width="33" >Zone:</td>
			<td width="75" ><form:select style="width:98%" path="zone.zoneId"  id="zone">	
				<form:option value="All" selected="selected">All</form:option>
				<form:options items="${zones}" itemLabel="zoneName" itemValue="zoneId" />
				</form:select>
				</td>
				<td width="33" >State:</td>
				<td width="75" ><form:select id="state" style="width:98%"  path="state.stateId">
						<form:option value="All">All</form:option>
					</form:select> <img style="display:none" alt="loading"  id="loadingImageState" src="${imagelink}"/>
				</td>
			</tr>
			
			<tr>
				<td>Region:</td>
					<td><form:select style="width:98%" id="region"  path="region.regionId" >
						<form:option value="All">All</form:option>
				        </form:select> <img style="display:none" alt="loading"  id="loadingImageRegion" src="${imagelink}"/>
					</td>
					<td>Area:</td>
				<td><form:select style="width:98%" id="area" path="area.areaId">
				    	<form:option value="All">All</form:option>
				    </form:select> <img style="display:none" alt="loading"  id="loadingImageArea" src="${imagelink}"/>
				</td>
			</tr>
			</table>
			<div style="float: right;    margin-top: -74px;    width: 359px; " >
			<ul class="icon">
	<li><input type="button" class="dwdshowreport" id="showreport" value=""/></li>
	<li><input type="button" class="dwdexl" id="downloadXls" value=""/>&nbsp;&nbsp;</li>
	
	<li><input type="button" class="dwdpdf" id="downloadPdf" value=""/>&nbsp;&nbsp;</li>
	</ul>
<!-- 	<input type="button" id="downloadCsv" value="Download CSV"/>&nbsp;&nbsp; -->
	
<%-- 	<c:url value="/geographic_report/html" var="downloadHTML" />
	<a href="${downloadHTML}">Download HTML</a> --%>

</div>
		 	</div>
			</div>
		 
	
 
 
 
 
	<div id="reportHtml"><img style="display:none" alt="loading"  id="loadingImageHtmlReport" src="${imagelink}"/></div>
</body>
