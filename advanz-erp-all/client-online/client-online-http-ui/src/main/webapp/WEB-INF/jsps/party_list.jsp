<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page isELIgnored="false" %>
	<script type="text/javascript" charset="utf-8">
                                    
                $(document).ready(function() {
                    /* Initialise datatables */
                    var oTable = $('#example').dataTable({bInfo:""});
                    
                } );
            </script>

<c:if test="${succ=='Blk'}">
  <script type="text/javascript">
  	var delUrl='show_party_form';
  	$(document).ready(function() {
      alert('No Record Found !!!!');
     // window.self.location  = delUrl;
	});
 	</script>
</c:if>

 <c:if test="${partyForm.succ=='Ad'}">
  <script type="text/javascript">
  var delUrl='show_party_form';
	$(document).ready(function() {
     alert('Record Inserted Successfully !!!!');
     window.self.location = delUrl;
	});
 	</script>
</c:if>

<c:if test="${partyForm.succ=='Dl'}">
  <script type="text/javascript">
  var delUrl='show_party_form';
  $(document).ready(function() {
     alert('Record Deleted Successfully !!!!');
     window.self.location = delUrl;
	});
 	</script>
</c:if>

<c:if test="${partyForm.succ=='Up'}">
  <script type="text/javascript">
  var delUrl='show_party_form';
	$(document).ready(function() {
     alert('Record Updated Successfully !!!!');
     window.self.location = delUrl;
	});
 	</script>
</c:if>

 <c:if test="${not empty(errors)}">
 <input type="hidden" id="errorId" value="${errors.errorMsg}">
	<script type="text/javascript">
		 var delUrl='show_party_form';
		 $(document).ready(function() {
	 	 var errorId=document.getElementById('errorId');
		 alert(errorId.value);
    	 window.self.location = delUrl;
		});
 	</script>
</c:if>

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
					height		:407,
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
	.ui-widget-content {
overflow-x: hidden !important;
 
}
  .View{width:34px !important; border:none!important}
  .Pn{width:141px !important; border:none !important }
  .Pt{width:82px !important;  border:none !important }
 .Ope{width:78px !important;  border:none !important }
 .Cy{width:87px !important;  border:none !important }
 .P1{width:83px !important;  border:none !important }
 .P2{width:83px !important;  border:none !important  }
 .Pr{width:78px !important;  border:none !important  }
 .Mob{width:74px !important;  border:none !important  }
 .Ln{width:76px !important;  border:none !important  }
 .Cp{width:89px !important;  border:none !important  } 
  .Ac {width:54px !important;  border:none !important  }
</style>
  <form:form method="post"    name="input" class="formdiv" action="get_party_data" modelAttribute="searchCriteria">
		     
    <div class="header" > Party List </div> 
	<div class="headingdiv">
	  <table width="880" height="31" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td width="70"><a href="show_new_party_form" class="addbtn" iconCls="icon-add"></a></td>
          <td width="98"><a href="#" class="exportbtn" iconCls="icon-redo" onClick="javascript:$('#dlg').dialog('close')"></a></td>
          <td width="111"><div align="center">Party name</div></td>
          <td width="62">
 		  <form:input onkeyup="valid(this)" onblur="valid(this)" path="partyName" size="16" id="partyName" /></td>
      
        <td width="70"><div align="center">Party Type</div></td>
        <td width="140"><form:select path="partyTypeDTO.partyTypeId"  id="party" >
		<form:option value="">Select</form:option>
		<form:options items="${partyTypeList}" itemLabel="partyTypeDesc" itemValue="partyTypeId"/>
		</form:select>										       
        </td>
          
        <td width="90"><div align="center">Party Type Flag</div></td>
        <td width="110"><form:select path="partyTypeDTO.partyTypeFlag" id="party" >
		<form:option value="">Select</form:option>
		<form:option value="Debtor">Debtor</form:option>
		<form:option value="Creditor">Creditor</form:option>
		</form:select>										
          
         </td>
          <td width="68"><input class="searchbtn" type="submit" value=""/></td>
          <td width="71"> <a href="show_party_form" class="cancelbtn" > </a></td>
        </tr>
      </table>
	</div>
	<div id="demo">
	<div class="gridheadingdiv">
	  <table width="972" class="display fixmyheader-8" id="example">
  <thead>

   <tr>    <td class="View"><div align="center">View</div></td>
   		  <td class="Pn"><div align="center">Party name</div></td>
          <td class="Pt"><div align="center">Party Type</div></td>
          <td class="Ope" ><div align="center">Opening</div></td>
          <td class="Cy"><div align="center">City</div></td>
          <td class="P1"><div align="center">Phone 1 (O)</div></td>
          <td class="P2"><div align="center">Phone 2 (O)</div></td>
          <td class="Pr"><div align="center">Phone(  R)</div></td>
          <td class="Mob"><div align="center">Mobile</div></td>
          <td class="Ln"><div align="center">LST No</div></td>
          <td class="Cp"><div align="center">Contact Person</div></td>
          <td class="Ac"><div align="center">Action</div></td>
        </tr>
  </thead>
  <tbody>  
 
        <c:forEach items="${partyList}" var="party">
			<tr>
			<td style="text-align: center;" width="24">
			<c:url value="get_party" var="view_url">
			<c:param name="partyId" value="${party.partyId}"></c:param>
			<c:param name="opr" value="V"></c:param>
		  </c:url>
          <a href="${view_url}"><img src="static/images/view_icon.png" title="View Record" alt="" /></a></td>
          <td width="131">&nbsp;<c:out value="${party.partyName}"/></td>
          <td width="72">&nbsp;<c:out value="${party.partyTypeDesc}"/></td>
          <td width="68" ><fmt:formatNumber value="${party.openingBalance}" type="number"  minFractionDigits="2" maxFractionDigits="2"/>
          
          <c:if test="${party.openingBalance!=0.0 }">
          <c:out value="${party.balanceType}"/>
          </c:if>
          </td>
          <td width="77">&nbsp;<c:out value="${party.cityName}"/></td>
          <td width="73">&nbsp;<c:out value="${party.phoneO1}"/></td>
          <td width="73">&nbsp;<c:out value="${party.phoneO2}"/></td>
          <td width="68">&nbsp;<c:out value="${party.phoneW2}"/></td>
          <td width="64">&nbsp;<c:out value="${party.mobile2}"/></td>
          <td width="66">&nbsp;<c:out value="${party.cstNo}"/></td>
          <td width="79">&nbsp;<c:out value="${party.contactPerson1}"/></td>
          <td width="57">
               <c:url value="get_party" var="remove_url">
					<c:param name="partyId" value="${party.partyId}"></c:param>
					<c:param name="opr" value="R"></c:param>
				</c:url>
				<c:url value="get_party" var="edit_url">
					<c:param name="partyId" value="${party.partyId}"></c:param>
					<c:param name="opr" value="E"></c:param>
					</c:url>
					
				<a href="${edit_url}"><img src="static/images/change_btn.png" title="Edit Record" alt="" /> </a>
				 <a href="${remove_url}"><img src="static/images/drop.png" title="Delete Record" alt="" /> </a>
				</td>
			</tr>
			</c:forEach>
  
  </tbody>
</table>
  </div>
  </div>
 </form:form>
