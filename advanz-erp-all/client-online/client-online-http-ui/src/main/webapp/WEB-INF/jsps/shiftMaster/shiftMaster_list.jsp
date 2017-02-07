<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:if test="${succ=='Blk'}">
  <script type="text/javascript">
  	var delUrl='get_shift_report_list';
  	$(document).ready(function() {
      alert('No Record Found !!!!');
 //     window.self.location  = delUrl;
	});
 	</script>
</c:if>
<c:if test="${succ=='Blk'}">
  <script type="text/javascript">
  	var delUrl='get_shift_report_list';
  	$(document).ready(function() {
      alert('No Record Found !!!!');
 //     window.self.location  = delUrl;
	});
 	</script>
</c:if>
 <script type="text/javascript">
		var redrctUrl='get_shift_report_list';
				
		function getParam(name)
		{
		 name = name.replace(/[\[]/,"\\\[").replace(/[\]]/,"\\\]");
		 var regexS = "[\\?&]"+name+"=([^&#]*)";
		 var regex = new RegExp( regexS );
		 var results = regex.exec( window.location.href);
		 if( results == null )
		  return "";
		else
		 return results[1];
		}
		$(document).ready(function() {
 		var frank_param = getParam('succ');
 		if(frank_param=='Up')
 		 {
 		  alert('Record Updated Successfully !!!!');	  
 		  window.self.location = 'get_shift_report_list';
 		 }
 		if(frank_param=='Ad')
		 {
		  alert('Record Inserted Successfully !!!!');	  
		  window.self.location = 'get_shift_report_list';
		 }
 		if(frank_param=='Dl')
		 {
 		   alert('Record Deleted Successfully !!!!');	 
 		   window.self.location = 'get_shift_report_list';
		 }
 		//	confirm('Are you sure you want to delete?');
		});
 	</script>


 	<script type="text/javascript" charset="utf-8">
                                    
                $(document).ready(function() {
                    /* Initialise datatables */
                    var oTable = $('#example').dataTable({bInfo:"", "bPaginate": false});
                    
                } );
            </script> 
            
<script type="text/javascript">
			
			$(document).ready(function() {  	
									
				$('.fixmyheader-8').fixheadertable({
					caption		: 'My header is fixed !',
					height		:412,
					addTitles	: true,
					colratio	: ['10%', '10%', '8%', '50px', 'auto', 'auto', '30%', 'auto']
				});
			});
		</script>
<script type="text/javascript">
      $(document).ready(function()
       {
           	   
	  $( ".datepicker" ).datepicker({
		  changeMonth: true,
          changeYear: true,
		  yearRange: '-99:+10',
		  dateFormat: 'dd-M-yy' });
	  
	  $( ".fromDate" ).datepicker({
		  changeMonth: true,
          changeYear: true,
		  yearRange: '-99:+10',
		  dateFormat: 'dd-M-yy' });

	  $( ".toDate" ).datepicker({
		  changeMonth: true,
          changeYear: true,
		  yearRange: '-99:+10',
		  dateFormat: 'dd-M-yy' });
	     
      });
  </script>
  <style type="text/css">
 
 	.ui-widget-content {
overflow-x: hidden !important;
 
}
body {
	font-family:Arial, Helvetica, sans-serif;
	}
code, pre {				
				padding		: 10px;	
				background	: #F5F5F5;
				border		: 1px solid #D4D4D4;
				overflow-x	: auto;
				font-size	: 12px;
			}	
	
th{font-size:10px;}
 td{font-size:12px;}  
 
 	
	div.t_fixed_header_main_wrapper.ui div.t_fixed_header_caption {
		display:none;
		
		}
   .View{width:34px !important; border:none!important}
.Sn{width:45px !important; border:none !important;}
.date{width:74px !important; border:none !important;}
.run{width:101px !important; border:none !important;}
.Shift{width:110px !important; border:none !important;}
.se{width:110px !important; border:none !important;}
.nsc{width:110px !important; border:none !important;}
.nsi{width:143px !important; border:none !important;}
.Ac{width:53px !important; border:none !important;}
</style>

<script language="javascript" type="text/javascript">
<!--
function popitup(url) {
	newwindow=window.open(url,'name','height=800,width=800');
	if (window.focus) {newwindow.focus()}
	return false;
}

// -->
</script>
<script type="text/javascript">
function printShiftReport(ele,datePrompt,shiftPrompt){
	var ret = datePrompt.split("-");
	var yy = ret[0];
	var mm = ret[1];
	var dds = ret[2];
	var ret1=dds.split(" ");
	var dd=ret1[0];
	
	datePrompt=$.datepicker.formatDate('dd-M-yy', new Date(yy, mm - 1, dd));
	
	 var url = "shift_print_report/pdf?RUNNoPrompt="+ele+ "&datePrompt="+datePrompt+ "&shiftPrompt="+shiftPrompt;
	window.open(url);
	
}
</script>


<form:form name="input" action="get_shift_report_list" modelAttribute="searchCriteria" class="formdiv" method="post">
		     
    <div class="header"> Shift Report List </div> 
	<div class="headingdiv">
	  <table width="880" height="31" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td width="45">
          <a href="shift_add" class="addbtn"	iconCls="icon-add"></a> 
          
          </td>
          <td width="45">
          <input class="exportbtn"  type="button" value=""/> </td>
       <!--   <td width="86">
 <div align="center">Date</div></td>
          <td width="76">
      <input type="text" name="shifReportDate" size="16" id="shifReportDate" class="datepicker" data-maxsize="35" /></td> -->
     <td width="86">
      <div align="center">From Date</div></td>
          <td width="76">
      <form:input type="text" path="fromDate" size="16" id="fromDate" class="fromDate" data-maxsize="35" /></td>
       <td width="86">
      <div align="center">To Date</div></td>
          <td width="76">
      <form:input type="text" path="toDate" size="16" id="toDate" class="toDate" data-maxsize="35" /></td>
          <td width="77"><div align="center">Run No</div></td>
          <td width="71"><form:input type="text" path="runNo" size="16" id="runNo" /></td>
          <td width="45"><input class="searchbtn"   type="submit" value="Search" name="operation"/></td>
          <td width="45"><a href="get_shift_report_list"  class="cancelbtn" ></a></td>
        </tr>
      </table>
	</div>
	<div class="gridheadingdiv">
	  	 <table width="972" class="fixmyheader-8" id="example">
	  	 <thead>
   <tr> <td class="View"><div align="center">View</div></td>
   <td class="Sn" width="50"><div align="center">S No</div></td>
          <td class="date" width="57"><div align="center">Date</div></td>
          <td class="run" width="88"><div align="center">Run No</div></td>
          <td class="Shift" width="97"><div align="center">Shift</div></td>
          <td class="se" width="132"><div align="center">Shift Engineer</div></td>
          <td class="nsc" width="163"><div align="center">No of Spare Consumed</div></td>
		  <td  class="nsi" width="181"><div align="center">No of Spinning Interruption</div></td>
          <td class="Ac" width="85"><div align="center">Action</div></td>
       </tr>
  </thead>
  <tbody>
         <c:forEach items="${shiftList}" var="shift" varStatus="s">
         <tr>   			<td style="text-align: center;" width="24">
		   <c:url value="get_shiftReport" var="view_url">
			<c:param name="shiftReportId" value="${shift.shiftReportId}"></c:param>
			<c:param name="opr" value="V"></c:param>
		  </c:url>
          <a href="${view_url}"><img src="static/images/view_icon.png" title="View Record" alt="" /></a></td>
            <td width="35">&nbsp;<c:out value="${s.count}" />
           <%--  <a href="/client-online-http-ui/get_shiftReport?shiftReportId=<c:out value="${shift.shiftReportId}"/>&opr=E" onclick="return popitup('/client-online-http-ui/get_shiftReport?shiftReportId=<c:out value="${shift.shiftReportId}"/>&opr=E')"> <c:out value="${s.count}" /></a> --%>
           </td>
          <td width="64">&nbsp;<fmt:formatDate  pattern="dd-MMM-yyyy" value="${shift.shifReportDate}"/>
           </td>
          <td width="91">&nbsp;
            <c:out value="${shift.runNo}"/></td>
          	
          <td width="100">&nbsp;<c:out value="${shift.mastersDTO.name}"/></td>	   
			 <td width="100">&nbsp;
	        <c:out value="${shift.shiftEngineerName}"/></td>
			   <td width="100">&nbsp;<c:out value="${shift.shiftConsumedDetailDTOList.size()}"/></td>
			    <td width="133"> <c:out value="${shift.shiftSpinInterruptionDetailDTOList.size()}"/></td>
          
          <td width="65">
          <img	onclick="printShiftReport('<c:out value="${shift.runNo}" />','<c:out value="${shift.shifReportDate}" />','<c:out value="${shift.mastersDTO.name}" />');" src="static/images/print_icon.png" title="Print Shift Report" alt="" />
           <a href="get_shiftReport?shiftReportId=<c:out value="${shift.shiftReportId}"/>&opr=E"><img src="static/images/change_btn.png"   title="Edit Record" alt="" /></a>
         <a href="get_shiftReport?shiftReportId=<c:out value="${shift.shiftReportId}"/>&opr=R"><img src="static/images/drop.png"   title="Delete Record" alt="" /></a></td>
        </tr>
       </c:forEach>
  
  </tbody>
</table>
 </div>
 
    <div style="float: right;"><c:url value="get_shift_report_list" var="remove_url">
<c:param name="next" value="${searchCriteria.next+(15)}"></c:param>
</c:url> <a href="${remove_url}" class="nextbtn" ></a>
</div>
<div style="float: right;">
<c:url value="get_shift_report_list" var="remove_url">
<c:param name="next" value="${searchCriteria.previous-(15)}"></c:param>
</c:url> <a href="${remove_url}" class="previousbtn" ></a>
</div>
  </form:form>