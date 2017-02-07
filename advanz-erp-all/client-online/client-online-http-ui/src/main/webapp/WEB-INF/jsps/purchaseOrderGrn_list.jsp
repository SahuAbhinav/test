<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false"%>
 <script type="text/javascript" charset="utf-8">
                                    
 $(document).ready(function() {
		/* Initialise datatables */
		var oTable = $('#example').dataTable({

		"aLengthMenu": [[10,10, 25, 50, -1], ['Select',10, 25, 50, "All"]],
		"iDisplayLength":10000,
		bInfo:""
		});
} );
            </script> 
<script type="text/javascript">
			
			$(document).ready(function() {  	
				
				 
				
				$('.fixmyheader-8').fixheadertable({
					caption		: 'My header is fixed !',
					height		:450,
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
	 		.ui-widget-content {
overflow-x: hidden !important;
 
}
th{font-size:10px;}
 td{font-size:12px;}  
 
 	
	div.t_fixed_header_main_wrapper.ui div.t_fixed_header_caption {
		display:none;
		
		}
 	
</style>

<script>
	$(document).ready(function() {
		$(".cancelbtn").click(function() {
			window.self.location = "backto_grn?opr=${opr}";
		});

	});
</script>

<script>
	jQuery(document).ready(function() {
		// binds form submission and fields to the validation engine
		jQuery("#formID").validationEngine();
	});

	function checkHELLO(field, rules, i, options) {
		if (field.val() != "HELLO") {
			// this allows to use i18 for the error msgs
			return options.allrules.validate2fields.alertText;
		}
	}
</script>

<script>
	$(document).ready(
			function() {
				//called when key is pressed in textbox
				$(".quantity").keypress(
						function(e) {
							//if the letter is not digit then display error and don't type anything
							if (e.which != 8 && e.which != 46 && e.which != 0
									&& (e.which<48 || e.which>57)) {
								//display error message
								$(".errmsg").html("Digits Only").show()
										.fadeOut("slow");
								return false;
							}
						});

			});
	$(document).ready(
			function() {
				//called when key is pressed in textbox
				$(".quantity1").keypress(
						function(e) {
							//if the letter is not digit then display error and don't type anything
							if (e.which != 8 && e.which != 46 && e.which != 0
									&& (e.which<48 || e.which>57)) {
								//display error message
								$(".errmsg1").html("Digits Only").show()
										.fadeOut("slow");
								return false;
							}
						});

			});
</script>


<script>
	$(document).ready(function() {
		$("button").button();
		//$('input:text, input:password').button()   
		$(".datepicker").datepicker();
		//      $(":submit").button()
	});
</script>

<script>
	$(document).ready(function() {
		$(function() {
			$('.sr').click(function() {
				$('.sr').css("background-color","white");
				$(this).css("background-color","yellow");
				$("#poIdGrn").val($(this).attr('id'));
		
			});
				// $(this).toggleClass('active');
				//alert($(this).attr('id'));
			});
		});
	function getPONumber(ele){
		$("#poNumber").val(ele);
	}
</script>


<style type="text/css">
</style>
<form:form name="input" id="formID" action="get_po_data_grn" method="post" commandName="grnMasterForm">
	<div class="header">Purchase Order List</div>
	<div class="headingdiv">
		<table width="880" height="31" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				 
				<td width="160"><div align="center">
						Purchase Order Number
					</div></td>
				<td width="67"><input type="text" name="poNo" size="16"
					id="poNo" /></td>
				<td width="119"><div align="center">Supplier Name</div></td>
				<td width="312"><input type="text" name="supplierName" size="16"
					id="supplierName" /></td>
					
                 <td width="45"></td>
              
				<td width="45"><input class="searchbtn"				
					type="submit" value=" " /></td>
			</form:form>
		<td width="45" >
					
	<form:form  action="addPoInGrn">
	<input type="hidden" name="poID" value="" id="poIdGrn"> 
	<input type="hidden" name="poNumber" value="" id="poNumber"> 
	<input type="hidden" name="opr" value="${opr}" id="opr"> 
		<input type="submit" class="okbtn" style="font-size: 11px; font-weight: bold; border: 0px; padding: 0 0 0;" value=" " />
	</form:form>
				</td>
	   <td width="45"><input name="reset2" type="reset"		class="cancelbtn" value=" " /></td>
			</tr>
		</table>
	</div>
	<div class="gridheadingdiv">
		 <table width="972" class="display fixmyheader-8" id="example">

 <thead>
   <tr>
				<td style="border: none"   padding-left: 9px; width="24 px;">S.No</td>
				<td style="border: none" width="142"><div align="center"><strong>PO No.</strong></div></td>
				<td style="border: none" width="84"><div align="center"><strong>Date</strong></div></td>
				<td style="border: none" width="110"><div align="center"><strong>Supplier Name</strong></div></td>
				<td style="border: none" width="100"><div align="center"><strong>Supplier Bill No</strong></div></td>
				<td style="border: none" width="96"><div align="center"><strong>Total Item</strong></div></td>
				<td style="border: none" width="104"><div align="center"><strong>PO Amount</strong></div></td>
				<td style="border: none" width="104"><div align="center"><strong>PO Valid Upto</strong></div></td>
				<td style="border: none" width="104"><div align="center"><strong>Balance Quantity</strong></div></td>
		</tr>
  </thead>
  <tbody>
		<c:forEach items="${pomList}" var="pom" varStatus="s">
	 	 <c:set var="now" value="<%=new java.util.Date()%>" />
	 	 
	  	<%-- <c:out value="${pom.purchaseOrderDetailDTOList[s.count].pendingQuantity}">  --%>
	  	<c:if test="${pom.balanceQuantity>0.0}">
	 	<c:if test="${pom.poValidUptoDate>=now}"> 
		    <tr class="sr" onclick="getPONumber('${pom.purchaseOrderNumber}');" style="cursor: pointer; " id="${pom.poAutoId}">
			<td width="21">&nbsp;<c:out value="${s.count}" /></td>
			<td width="120">&nbsp;<c:out	value="${pom.purchaseOrderNumber}" />
			</td>
			<td width="73">&nbsp;<fmt:formatDate pattern="dd-MMM-yyyy"  value="${pom.purchaseOrderDate}" /></td>
			<td width="120">&nbsp;<c:out value="${pom.partyDTO.partyName}" /></td>
			<td width="90"></td>
			<td width="86"  style="text-align: right;" >&nbsp;<c:out value="${pom.itemCount}" /></td>
			<td width="52" style="text-align: right; padding-right: 35px;" >&nbsp;
			<c:out value="${pom.poNetAmount}" /></td>
			<td width="62" style="text-align: right; padding-right: 35px;" >&nbsp;
			<fmt:formatDate pattern="dd-MMM-yyyy"  value="${pom.poValidUptoDate}"/></td>
			<td width="100"><c:out value="${pom.balanceQuantity}" />
			<%-- <form:input path="pom[${s.index}].purchaseOrderMasterDTO.purchaseOrderDetailDTOList[${s.index}].pendingQuantity"></form:input> --%> 
			</td>
			</tr>
		  </c:if>   
		 </c:if>    
			 </c:forEach>
  
  </tbody>
</table>
  </div>
  
<%-- </form>
<form action="addPoInGrn">
	<input type="hidden" name="poID" value="" id="poIdGrn"> 
	<input type="hidden" name="opr" value="${opr}" id="opr"> 
		<input type="submit" class="okbtn" style="font-size: 11px; font-weight: bold; border: 0px; padding: 0 0 0;" value=" " />

</form> --%>