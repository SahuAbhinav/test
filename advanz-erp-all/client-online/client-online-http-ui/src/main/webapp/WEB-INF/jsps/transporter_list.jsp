<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>
	<script type="text/javascript" charset="utf-8">
                                    
                $(document).ready(function() {
                    /* Initialise datatables */
                    var oTable = $('#example').dataTable({bInfo:""});
                    
                } );
            </script>
<c:if test="${succ=='Blk'}">
  <script type="text/javascript">
  	var delUrl='show_transporter_form';
  	$(document).ready(function() {
      alert('No Record Found !!!!');
      //window.self.location  = delUrl;
	});
 	</script>
</c:if>

 <c:if test="${transporterForm.succ=='Ad'}">
  <script type="text/javascript">
  var delUrl='show_transporter_form';
	$(document).ready(function() {
     alert('Record Inserted Successfully !!!!');
     window.self.location = delUrl;
	});
 	</script>
</c:if>

<c:if test="${transporterForm.succ=='Dl'}">
  <script type="text/javascript">
  var delUrl='show_transporter_form';
  $(document).ready(function() {
     alert('Record Deleted Successfully !!!!');
     window.self.location = delUrl;
	});
 	</script>
</c:if>

<c:if test="${transporterForm.succ=='Up'}">
  <script type="text/javascript">
  var delUrl='show_transporter_form';
	$(document).ready(function() {
     alert('Record Updated Successfully !!!!');
     window.self.location = delUrl;
	});
 	</script>
</c:if>

 <c:if test="${not empty(errors)}">
 <input type="hidden" id="errorId" value="${errors.errorMsg}">
	<script type="text/javascript">
		var delUrl='show_transporter_form';
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

 <script>
	$(function() {

		//$( "#datepicker" ).datepicker();
		$("#datepicker").datepicker({
			dateFormat : 'dd/mm/yy',
			autoSize : true,
			changeMonth : true,
			changeYear : true,
			altFormat : 'mm/dd/yy',
			altField : '#edd1'
		});
	});

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
					height		:420,
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
	
th{font-size:10px;}
 td{font-size:12px;}  
 
 	
	div.t_fixed_header_main_wrapper.ui div.t_fixed_header_caption {
		display:none;
		
		}
   .Tr{width:137px !important;  border: none !important }
.Add{width:154px !important;  border: none !important }
.Cy{width:81px !important;  border: none !important }
.P1{width:70px !important;  border: none !important }
.P2{width:67px !important;  border: none !important}

.Cp{width:105px !important;  border: none !important }
.Cm{width:96px !important;  border: none !important }
.St{width:88px !important;  border: none !important }
.Vn{width:68px !important;  border: none !important }
.Vd{width:79px !important;  border: none !important }
.An{width:58px !important;  border: none !important }	  	
 .View	{	 width: 34px !important; border:none !important;		}	
</style>

<form:form name="input" class="formdiv"    action="get_transporter_data" modelAttribute="searchCriteria">
		     
    <div class="header"  > Transporter List</div> 
	<div class="headingdiv"  >
	  <table width="880"   height="31" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td width="71"><a onclick="return checkAdd();" href="show_new_transporter_form" class="addbtn" ></a></td>
          <td width="77"><a href="#" class="exportbtn" onClick="javascript:$('#dlg').dialog('close')"></a></td>
          <td width="111"><div align="center">Transporter Name</div></td>
          <td width="62">
      <form:input  onkeypress="return check(event)"  data-maxsize="65" path="name" size="10" id="transName" /></td>
          <td width="111"><div align="center">Transporter Code</div></td>
          <td width="63"><form:input onkeyup="valid1(this)" onblur="valid1(this)" data-maxsize="16" path="code" size="10" id="transCode" /></td>
          <td width="147"><div align="center">Transporter Address</div></td>
          <td width="78"><form:input  onkeypress="return check(event)"  data-maxsize="225" path="address" size="10" id="transAddress" /></td>
          <td width="81"><input class="searchbtn"  type="submit" value=""/></td>
          <td width="74">
          <a href="show_transporter_form" class="cancelbtn"></a>
			</td>
        </tr>
      </table>
	</div>
	<div class="gridheadingdiv"  >
	  <table width="668" class="fixmyheader-8" id="example">
  <thead>
 
 
  
   <tr>   
   <td class="View" width="55"><div align="center">View</div></td>
          <td class="Tr" width="137" align="left"><div align="center">Transporter</div></td>
          <td class="Add" width="154" align="left"><div align="center">Address</div></td>
          <td class="Cy" width="81" align="left"><div align="center">City</div></td>
          <td class="P1" width="70"><div align="center">Phone 1</div></td>
          <td class="P2" width="67"><div align="center">Phone 2</div></td>
          <td class="Cp" width="105"><div align="center">Contact Person</div></td>
          <td class="Cm" width="96"><div align="center">Contact Mobile</div></td>
          <td class="St" width="88"><div align="center">Service Tax No</div></td>
          <td class="Vn" width="68"><div align="center">Vat No</div></td>
          <td class="Vd" width="79"><div align="center">Vat Date</div></td>
          <td class="An" width="58"><div align="center">Action</div></td>
       </tr>
  </thead>
  <tbody>        
        <c:forEach items="${transporterList}" var="transporter">
			<tr>
			
				   <td style="text-align: center;" width="24">
			<c:url value="get_transporter" var="view_url">
			<c:param name="transporterId" value="${transporter.transporterId}"></c:param>
			<c:param name="opr" value="V"></c:param>
		  </c:url>
          <a href="${view_url}"><img src="static/images/view_icon.png" title="View Record" alt="" /></a></td>
	
		  <td width="127">&nbsp<c:out value="${transporter.transName}"/></td>
          <td width="144">&nbsp;<c:out value="${transporter.transAddress}"/></td>
          <td width="71">&nbsp;<c:out value="${transporter.cityName}"/></td>
          <td width="60">&nbsp;<c:out value="${transporter.phone1}"/></td>
          <td width="57">&nbsp;<c:out value="${transporter.phone2}"/></td>
          <td width="95">&nbsp;<c:out value="${transporter.contactPerson}"/></td>
          <td width="86">&nbsp<c:out value="${transporter.contactMobile}"/>;</td>
          <td width="78">&nbsp;<c:out value="${transporter.servTaxNo}"/></td>
          <td width="58">&nbsp;<c:out value="${transporter.vatNo}"/></td>
          <td width="69">&nbsp;<fmt:formatDate pattern="dd-MMM-yyyy"
            value="${transporter.vatDt}" /></td>
				
				<td width="48">
         			<c:url value="get_transporter" var="remove_url">
					<c:param name="transporterId" value="${transporter.transporterId}"></c:param>
					<c:param name="opr" value="R"></c:param>
					</c:url>
					<c:url value="get_transporter" var="edit_url">
					<c:param name="transporterId" value="${transporter.transporterId}"></c:param>
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
          </c:forEach>				</td>
			</tr>
			</c:forEach>
  
  </tbody>
</table>
  </div>
  
 </form:form>
