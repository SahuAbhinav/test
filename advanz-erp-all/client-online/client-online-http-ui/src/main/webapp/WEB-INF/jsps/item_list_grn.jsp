<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false"%>
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

<script type="text/javascript">
			
			$(document).ready(function() {  	
				
				$('.fixmyheader-8').fixheadertable({
					caption		: 'My header is fixed !',
					height		:293,
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
 	.dataTables_info {
   
    width: 600px;
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
				$('.sr').css("background-color", "white");
				$(this).css("background-color", "yellow");
				$("#ok").removeAttr("disabled");
				//$("#itemIdSO").val($(this).attr('id'));
				$(".itId").val($(this).attr('id'));
				$.get('get_item_by_id', {
					id : $(this).attr('id')
				}, function(data) {
					$("#length").val(data.itemLength);
					$("#width").val(data.itemWidth);
					$("#minStock").val(data.minStock);
					$("#maxStock").val(data.maxStock);
					$("#reorderLevel").val(data.reorderLevel);
					$("#suggqty").val(data.suggReorderQty);
					//$("#itemIdSO").val(data.itemId);
					$("#density").val(data.itemDensity);

				});
				// $(this).toggleClass('active');
				//alert($(this).attr('id'));
			});
		});
	});
</script>
<style type="text/css">
.sr {
	background-color: white;
}
</style>
<style type="text/css">
p {
	color: blue;
}
	 		.ui-widget-content {
overflow-x: hidden !important;
 
}
.scrolltable tr {
	cursor: pointer;
}
 

 
.gridheadingdiv td {
	height: 22px;
}
.ic{width:42px !important; border:none !important}
  .in{width:300px !important; border:none !important}
  .pt{width:60px !important; border:none !important}
  .stock{width:75px !important; border:none !important}
  .br{width:70px !important; border:none !important}
  .mrp{width:70px !important; border:none !important}
  .ld{width:70px !important; border:none !important}
  
  .weight{width:60px !important; border:none !important} 
 
</style>



 
	 
       
<form:form name="input" id="formID"   action="get_item_data_grn" method="post" modelAttribute="itemForm">
 <input type="hidden" name="groupId" value="${groupId}" id="groupId">
  

     <div class="panel-header"  >
	<div class="panel-title">Item List</div>
	<div class="panel-tool"></div>
</div>
 <div class="headingdiv"  >
	  <table width="800" height="31" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td width="75"><div align="center">Item code</div></td>
          <td width="44"><input type="text" name="itemCode" size="10" id="itemCode" /></td>
          <td width="75"><div align="center">
            <div align="center">Item Name</div>
          </div></td>
          <td width="312"><input type="text" name="invoiceName" size="10" id="itemName" /></td>
          <td width="45"><input class="searchbtn" style="font-size: 11px; font-weight: bold;  padding: 0 0 0 20px;" type="submit" value=" "/></td>
         	</form:form>
          <td width="45">
        
	<form action="addItemInGrn">

		<input type="hidden" name="itemID" class="itId"  id="itemIdSO">
		
		 <input type="hidden" name="opr" value="Add" id="opr"> <input
			type="submit" class="okbtn"  id="ok"
			style="font-size: 11px; font-weight: bold; border: 0px; padding: 0 0 0;"
			value=" " />

	</form></td>
	<td width="45"><input name="reset2" type="reset" class="cancelbtn"  style="font-size: 11px; font-weight: bold; border:0px;  padding: 0 0 0 10px;" value=" "/></td>
        </tr>
        </table>
        </div>
        
	 
	<div style="width: 906px; margin: 0 auto;">
		<span style="margin-left: 80px;" class="errmsg"></span>
	</div>
	<div class="gridheadingdiv" >
	 <table width="972" class="display fixmyheader-8" id="example">
  <thead>
   <tr>

				<td style="border: none"  class="ic"  width="68"><div align="center">	<strong>Item code</strong>					</div></td>
				<td style="border: none"  class="invoice" width="78"><div align="center">			<strong>Item name</strong>					</div></td>
				<td  style="border: none;" class="basic"><div align="center">								<strong>UMO</strong>							</div>
			</td>
				<td style="border: none"  class="pack" width="78"><div align="center">			<strong>Pack Type</strong>					</div></td>
				<td style="border: none"  class="stock" width="78"><div align="center">			<strong>Stock </strong>					</div></td>
				<td style="border: none"  class="br" width="78"><div align="center">		<strong>Basic Rate</strong>					</div></td>
				<td style="border: none"  class="mrp" width="78"><div align="center">			<strong>M. R. P.</strong>					</div></td>
				<td style="border: none"  class="ld" width="78"><div align="center"><strong>Launch Dt.</strong>					</div></td>
				<td style="border: none"  class="weight" width="77"><div align="center">	<strong>Weight</strong>		</div></td>

			 </tr>
  </thead>
  <tbody>  
        			<c:forEach items="${itemList}" var="item">
					<tr class="sr" style="cursor: pointer;" id="${item.itemId}">

						<td width="31">&nbsp;<c:out value="${item.itemCode}" />
						</td>
						<td width="300">&nbsp;<c:out value="${item.itemName}" />
						</td>
						<td width="60" >&nbsp;<c:out value="${item.masterUnit.name}" />
		  </td>
						<td width="68">&nbsp;<c:out
								value="${item.masterPack.name}" />
						</td>
						<td width="68">
								
								 <fmt:formatNumber value="${item.stockTotal}" type="number"  minFractionDigits="2" maxFractionDigits="2"/></td>
								
								
						<td width="68"style="text-align: right;" >&nbsp;<c:out value="${item.salesRate}" /></td>
						<td width="68" style="text-align: right;" >&nbsp;<c:out value="${item.mrp}" /></td>
						<td width="68"  >&nbsp;<fmt:formatDate pattern="dd-MMM-yyyy" value="${item.launchDate}" /></td>
						<td width="37" style="text-align: right; padding-right: 34px; " >&nbsp;<c:out value="${item.netWeight}" /></td>

					</tr>

				 
				</c:forEach>
  
  </tbody>
</table>
  </div>
  <div><c:url value="show_item_list_grn" var="item_list_url">
			<c:param name="next" value="${itemForm.itemDTO.previous}"></c:param>
			</c:url> <a href="${item_list_url}" class="previousbtn" ></a>
		</div>
         <div><c:url value="show_item_list_grn" var="item_list_url">
			<c:param name="next"  value="${itemForm.itemDTO.next}"></c:param>
			</c:url> <a href="${item_list_url}" class="nextbtn" ></a>
		</div>
  <div class="bkgColor" align="left" style="padding-top: 12px;">
	<table height="78" width="792" border="0" style="margin-top: 12px;">
		<tr>
			<td width="139" height="36">Length<span style="color: #FF0000"></span>
			</td>
			<td width="108"><input type="text" name="length"
				data-maxsize="11" readonly="readonly" disabled="disabled" size="11"
				id="length" /></td>
			<td width="113">Width</td>
			<td width="130"><input type="text" name="width"
				data-maxsize="11" readonly="readonly" disabled="disabled" size="11"
				id="width" /></td>
			<td width="117">Thickness</td>
			<td width="92"><input type="text" name="thikness"
				data-maxsize="11" readonly="readonly" disabled="disabled" size="11"
				id="thikness" /></td>
			<td width="119">Density</td>
			<td width="115"><input type="text" name="density"
				data-maxsize="11" readonly="readonly" disabled="disabled" size="11"
				id="density" /></td>
		</tr>
		<tr>
			<td height="36">Min Stock</td>
			<td><input type="text" name="minStock" data-maxsize="11"
				readonly="readonly" disabled="disabled" size="11" id="minStock" />
			</td>
			<td>Max Stock</td>
			<td><input type="text" name="maxStock" data-maxsize="11"
				readonly="readonly" disabled="disabled" size="11" id="maxStock" />
			</td>
			<td>Re-order Level</td>
			<td><input type="text" name="reorderLevel" data-maxsize="11"
				readonly="readonly" disabled="disabled" size="11" id="reorderLevel" />
			</td>
			<td>Sugg. Qty</td>
			<td><input type="text" name="suggqty" data-maxsize="11"
				readonly="readonly" disabled="disabled" size="11" id="suggqty" /></td>
		</tr>
	</table>
	</div>
	 
	</div>
 

	 



 