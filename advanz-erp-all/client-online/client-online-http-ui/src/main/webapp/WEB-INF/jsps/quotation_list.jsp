<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false"%>
<script type="text/javascript" charset="utf-8">
$(document).ready(function(){ 
 $(".datepicker[readonly]").css("background-color","#ffffff" );
} );
</script>
<script type="text/javascript" charset="utf-8">
    $(document).ready(function() {
      /* Initialise datatables */
    var oTable = $('#example').dataTable({ 	 
   		       "aLengthMenu": [['',10, 25, 50, -1], ['',10, 25, 50, "All"]],
               "iDisplayLength":10000,
               "bPaginate": false,
               bInfo:""
       });
    } );          
  </script>
 <c:if test="${succ=='Blk'}">
  <script type="text/javascript">
  	var delUrl='get_quotation_list';
  	$(document).ready(function() {
      alert('No Record Found !!!!');
      //window.self.location  = delUrl;
	});
 	</script>
</c:if>

 <c:if test="${quotationMasterForm.succ=='Ad'}">
  <script type="text/javascript">
  var delUrl='get_quotation_list';
	$(document).ready(function() {
     alert('Record Inserted Successfully !!!!');
     window.self.location = delUrl;
	});
 	</script>
</c:if>

<c:if test="${quotationMasterForms.succ=='Dl'}">
  <script type="text/javascript">
  var delUrl='get_quotation_list';
  $(document).ready(function() {
     alert('Record Deleted Successfully !!!!');
     window.self.location = delUrl;
	});
 	</script>
</c:if>

<c:if test="${quotationMasterForms.succ=='Up'}">
  <script type="text/javascript">
  var delUrl='get_quotation_list';
	$(document).ready(function() {
     alert('Record Updated Successfully !!!!');
     window.self.location = delUrl;
	});
 	</script>
</c:if>

 <c:if test="${not empty(errors)}">
 <input type="hidden" id="errorId" value="${errors.errorMsg}">
	<script type="text/javascript">
		var delUrl='quotationMasterForms';
		$(document).ready(function() {
			var errorId=document.getElementById('errorId');
			alert(errorId.value);
    	 window.self.location = delUrl;
		});
 	</script>
</c:if>

<script type="text/javascript">
 		function checkEdit()
 		{
 		alert('Login User Not Permit to Edit Record !!!!!!');
 		}
		
	  function checkDelete()
 		{
 		alert('Login User Not Permit to Delete Record !!!!!!');
 		}

 		function checkAdd()
 		{
 		  var adId=document.getElementById('addFlag').value;
 		  if(adId=='true')
 			  {
 				return true;
 			  }
 		  else
 			{
 			 alert('Login User Not Permit to Add Record !!!!!!');
 			 return false;  
 			} 		  
 		}
	</script>

<script type="text/javascript">
	function remoneConformation(){
		var name =	confirm('Are you sure that you want to delete this item?');
		if(name==true){
				return true;
			} else{
			return false;
		     }
		  }
</script>

<script type="text/javascript">
			$(document).ready(function() {  	
				$('.fixmyheader-8').fixheadertable({
					caption		: 'My header is fixed !',
					height		:390,
					addTitles	: true,
					colratio	: ['10%', '10%', '8%', '50px', 'auto', 'auto', '30%', 'auto']
				});
			});
		</script>
<style type="text/css">
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
		
#aside {height: 450px;}
	.View{width:34px !important; border:none!important}	
	 .Sn{width:35px !important; border: none! important }
 .Qn{width:63px !important; border: none! important }
 .Da{width:62px !important; border: none! important }
 .Pn{width:122px !important; border: none! important }
 .Val{width:62px !important; border: none! important }
 .Tn{width:51px !important; border: none! important }
 .Tq{width:65px !important; border: none! important }
 .Iv{width:72px !important; border: none! important}
 .Na{width:66px !important; border: none! important }
  .Ac{width:45px !important; border: none! important }
th{font-size:10px; }
 td{font-size:12px;}  
 .ui-widget-content {
overflow-x: hidden !important;
 
}
 	
	div.t_fixed_header_main_wrapper.ui div.t_fixed_header_caption {
		display:none;
		
		}
	
</style>


<c:if test="${not empty RT}">
<script>
	$(document).ready(function() {
		$(function() {
			$('.sr').click(function() {
				//$('.sr').css("background-color","white");
				$('.sr').css("background-color","white");
				
				$(this).css("background-color","yellow");
				$("#qnoSO").val($(this).attr('id'));
				// $(this).toggleClass('active');
				//alert($(this).attr('id'));
			});
		});
	});
</script>
</c:if>
 <script type="text/javascript">
      $(document).ready(function()
       {
       $('.remQ').click(function(){
    	   
    	   var v=$(this).attr('id');
    	  
    	   $.get('check_before_remove', { id: $(this).attr('id')}, function(data) {
    		   
    		   if(data=='CONTINUE'){
    			   window.self.location="get_quotation?opr=R&id="+v;
    		   }else{
    			   $('#msg_text').text(data);
    			   $( "#dialog" ).dialog();
    			   //alert(data);
    		   }
    	   });
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
	  
  //     
      });
     </script>
 
 

 		     <form:form name="input" action="get_quotation_list" class="formdiv"   modelAttribute="qoSearchCriteria" >
    <div class="header"> Quotation List</div> 
	<div class="headingdiv">

      <input type="hidden" name="qid" value="" id="qnoSO">
<input type="hidden" name="RT" value="${RT}"/>
	  <table width="880" height="31" border="0" cellpadding="0" cellspacing="0">
        <tr><c:if test="${empty RT}">
          <td width="82"><a onclick="return checkAdd()" onkeypress="return check(event)"  href="new_quotation" class="addbtn"></a></td>
          <td width="90"><input class="exportbtn" style="font-size: 11px; font-weight: bold;  padding: 0 0 0 38px;" type="button" value=""/> </td>
          </c:if>
          <td width="90"><div align="center">Q. Number</div></td>
          <td width="64">
      <form:input path="qoNumber" size="10" id="qonumber" /></td>
          <%-- <td width="82"><div align="center">Date</div></td>
          <td width="64"> <form:input path="qoDate" class="datepicker" id="date"  size="11" readonly="true" /></td> --%>
          <td width="82"><div align="center">From Date</div></td>
          <td width="64"> <form:input path="fromDate" class="fromDate"  size="11" readonly="true" /></td>
          <td width="82"><div align="center">To Date</div></td>
          <td width="64"> <form:input path="toDate" class="toDate" size="11" readonly="true" /></td>
          
          <td width="93"><div align="center">Party Name</div></td>
          <td width="92"> <form:input path="partyName" size="10" id="partyName" /></td>
          <td width="92"><div align="center">Item Name</div></td>
		 <td width="92"><form:input path="itemName" size="10" id="itemName" /></td>
          
          <td width="80"><input class="searchbtn" style="font-size: 11px; font-weight: bold;  padding: 0 0 0 20px;" type="submit" name="opr" value="search"/></td>
          <c:if test="${not empty RT}">
          <td width="80"><input type="submit" name="btn" class="okbtn"  style="font-size: 11px; font-weight: bold; border:0px;  padding: 0 0 0  ;" value="add"/> </td>
          </c:if>
          <td width="105">
           <c:if test="${empty RT}">  <a href="get_quotation_list" class="cancelbtn"> </a></c:if> <c:if test="${not empty RT}">  <a href="backto_salesOrder" class="cancelbtn"> </a></c:if>
          </td>
        </tr>
      </table>
   
	</div>
	<div class="gridheadingdiv" >
	  <table width="972" class="display fixmyheader-8" id="example">
  <thead>

 
  
   <tr>
   		  <td class="View"><div align="center">View</div></td>
          <td class="Sn" width="35"><div align="center">S No.</div></td>
          <td class="Qn" width="63"><div align="center">Q. Number</div></td>
          <td class="Da" width="62"><div align="center">Date</div></td>
          <td class="Pn" width="122"><div align="center">Party Name</div></td>
          <td class="Val" width="62"><div align="center">Valid Upto</div></td>
          <td class="Tn" width="51"><div align="center">Total Items</div></td>
          <td class="Tq" width="65"><div align="center">Total Qty.</div></td>
          <td class="Iv" width="72"><div align="center">Item Value</div></td>
          <td class="Na" width="60"><div align="center">Net Amount</div></td>
           <c:if test="${empty RT}">  
          <td class="Ac" width="43"><div align="center">Action</div></td>
        </c:if>
        </tr>
  </thead>
  <tbody>       
	<c:forEach items="${qmList}" var="qm" varStatus="s">
       <tr class="sr" style="cursor: pointer;" id="${qm.quotationAutoId}">
       	  <td style="text-align: center;" width="24">
			<c:url value="get_quotation" var="view_url">
			<c:param name="id" value="${qm.quotationAutoId}"></c:param>
			<c:param name="opr" value="V"></c:param>
		  </c:url>
          <a href="${view_url}"><img src="static/images/view_icon.png" title="View Record" alt="" /></a></td>			
          <td width="25">&nbsp;<c:out value="${s.count}" /></td>
          <td width="53">&nbsp;<c:out value="${qm.quotationNumber}" /></td>
          <td width="52">&nbsp;<fmt:formatDate pattern="dd-MMM-yyyy"   value="${qm.quotationDate}" />
          </td>
          <td width="112">&nbsp;<c:out value="${qm.partyDTO.partyName}" /></td>
          <td width="52">&nbsp;<fmt:formatDate pattern="dd-MMM-yyyy"
            value="${qm.validUpTo}" /></td>
          
          <td width="41" style="text-align: right;" align="right">&nbsp;${qm.quotationDetailDTOList.size()}&nbsp;</td>
          <td width="55" style="text-align: right;" align="right">&nbsp;<fmt:formatNumber value="${qm.totalQuantity}" type="number"  minFractionDigits="2" maxFractionDigits="2"/>&nbsp;</td>
          <td width="62" style="text-align: right;" align="right">&nbsp;<fmt:formatNumber value="${qm.itemValue}" type="number"  minFractionDigits="2" maxFractionDigits="2"/>&nbsp;</td>
          <td width="50" style="text-align: right; padding-right: 19px;" align="right">&nbsp;<fmt:formatNumber value="${qm.qoNetAmount}" type="number"  minFractionDigits="2" maxFractionDigits="2"/>&nbsp;</td>
         
          <c:if test="${empty RT}"> <td width="43" >&nbsp;
         
        <c:url value="get_quotation" var="remove_url">
					<c:param name="id" value="${qm.quotationAutoId}"></c:param>
					<c:param name="opr" value="R"></c:param>
					</c:url>
					<c:url value="get_quotation" var="edit_url">
					<c:param name="id" value="${qm.quotationAutoId}"></c:param>
					<c:param name="opr" value="E"></c:param>
					</c:url>
	<c:forEach items="${roleAndRightsDTOList}" var="roleAndRights">
		   <c:if test="${roleAndRights.menuId==sessionScope.menuId}">
		    <input type="hidden"  id="addFlag" value="${roleAndRights.addFlag}" >
		  	 <c:if test="${roleAndRights.editFlag=='true'}">
		      <a id="editUrlId" href="${edit_url}"><img	 src="static/images/change_btn.png" title="Edit Record" alt="" /></a>
		      </c:if>
		      <c:if test="${roleAndRights.editFlag=='false'}">
		      <img	onclick="checkEdit();" src="static/images/change_btn.png" title="Edit Record" alt="" />
		      </c:if>
		      <c:if test="${roleAndRights.deleteFlag=='true'}">
		      <a href="${remove_url}"><img src="static/images/drop.png" title="Delete Record"	alt="" /></a>
         	  </c:if>
         	  <c:if test="${roleAndRights.deleteFlag=='false'}">
		      <img onclick="checkDelete();" src="static/images/drop.png" title="Delete Record"	alt="" />
         	  </c:if>
          </c:if>
          </c:forEach>		
         </td> </c:if>
        </tr>
       </c:forEach>
  
  </tbody>
</table>
  </div>
  
      <div style="visibility:hidden">
      <div id="dialog" title="Message Dialog" >
  			<p id="msg_text">Hello</p>  			
	  </div>
</div>
<div style="float: right;"><c:url value="get_quotation_list" var="remove_url">
<c:param name="next" value="${quotationMasterForms.next+(15)}"></c:param>
</c:url> <a href="${remove_url}" class="nextbtn" ></a>
</div>
<div style="float: right;">
<c:url value="get_quotation_list" var="remove_url">
<c:param name="next" value="${quotationMasterForms.previous-(15)}"></c:param>
</c:url> <a href="${remove_url}" class="previousbtn" ></a>
</div>
</form:form>