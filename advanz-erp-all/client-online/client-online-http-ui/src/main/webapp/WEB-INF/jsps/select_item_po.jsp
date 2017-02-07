<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page isELIgnored="false"%>
 	<script type="text/javascript" charset="utf-8">
                                    
                $(document).ready(function() {
                    /* Initialise datatables */
                    var oTable = $('#example').dataTable({
                    	"aLengthMenu": [[10,10, 25, 50, -1], ['Select',10, 25, 50, "All"]],
                 		"iDisplayLength":10000,
                 		 "sPaginationType": "full_numbers",
                 		"bPaginate": false,
                 		bInfo:""
                 		
                    } );
                    
                } );
            </script>
<script type="text/javascript">
			
			$(document).ready(function() {  	
				
				
				$('.fixmyheader-8').fixheadertable({
					caption		: 'My header is fixed !',
					height		:410,
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
			window.self.location = "backto_purchaseOrder?opr=${opr}";
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
				$("#itemIdPO").val($(this).attr('id'));
				$("#ok").removeAttr("disabled");
		
			});
				// $(this).toggleClass('active');
				//alert($(this).attr('id'));
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

.errmsg {
	color: red;
}

 
.scrolltable tr {
cursor: pointer;
}
 .ui-widget-content {
overflow-x: hidden !important;
 
}
.ic{width:42px !important; border:none !important}
      .in{width:300px !important; border:none !important}
      .pt{width:60px !important; border:none !important}
      .stock{width:70px !important; border:none !important}
      .br{width:70px !important; border:none !important}
      .mrp{width:70px !important; border:none !important}
      .ud{width:70px !important; border:none !important}  
        .od{width:70px !important; border:none !important} 
.gridheadingdiv td {
	height: 22px;
}

 
</style>

	
	
 
   <div class="page">
  <div class="panel-header">
	<div class="panel-title">  Item List</div>
	<div class="panel-tool"></div>
  </div>
  
  <div align="left" class="bkgColor" style=" height:auto; padding-bottom: 0px; ">
     
 <div class="headingdiv" >
	  <table width="1110" height="31" border="0" cellpadding="0" cellspacing="0">
        <tr>
        <form:form name="input" id="formID" action="get_item_data_po" method="post" modelAttribute="itemForm">
          <input type="hidden" name="groupId" value="${groupId}" id="groupId">
          <td width="62"><div align="center">Item code</div></td>
          <td width="44"><input type="text" name="itemCode" size="16" id="itemCode" /></td>
          <td width="68"><div align="center">
            <div align="center">Item name</div>
          </div></td>
          <td width="312"><input type="text" name="itemName" size="16" id="itemName" /></td>
          <td width="75"><input class="searchbtn" style="font-size: 11px; font-weight: bold;  padding: 0 0 0 20px;" type="submit" value=" "/></td>
         	</form:form>
         	<form action="addItemInPO">
         	 <td width="75">
						<input type="hidden" name="itemID" value="" id="itemIdPO">
						<input type="hidden" name="opr" value="add" id="opr">
						
						<input type="submit" class="okbtn" disabled="disabled" id="ok" style="font-size: 11px; font-weight: bold; border:0px;  padding: 0 0 0  ;" value=" "/>    					
				</td>		
						</form>
						
         	<td width="94"><input name="reset2"
    type="reset" class="cancelbtn"  style="font-size: 11px; font-weight: bold; border:0px;  padding: 0 0 0 10px;" value=" "/></td>
        </tr>
      </table>
	</div>
 
  <div class="gridheadingdiv" >
	 <table width="972" class="fixmyheader-8" id="example">
  <thead>
   <tr>
      
        <td class="ic" width="142"><div align="center"><strong>Item code</strong></div></td>
        <td class="in" width="84"><div align="center"><strong>Item name</strong></div></td>
        <td  style="border: none;" class="basic"><div align="center">								<strong>UMO</strong>							</div>
			</td>
        <td class="pt" width="110"><div align="center"><strong>Pack Type</strong></div></td>
        <td class="stock" width="100"><div align="center"><strong>Stock</strong></div></td>
        <td class="br" width="96"><div align="center"><strong>Purchase Rate</strong></div></td>
        <td class="mrp" width="104"><div align="center"><strong>M. R. P.</strong></div></td>
        <td class="ud" width="104"><div align="center" title="Under Delivery Tolerance (in %)"><strong>UDT (in %)</strong></div></td>
        <td class="od" width="104"><div align="center" title="Over Delivery Tolerance (in %)"><strong>ODT (in %)</strong></div></td>
        </tr>
  </thead>
  <tbody>  <c:forEach items="${itemList}" var="item">
						<tr class="sr" style="cursor: pointer;" id="${item.itemId}">
					
							<td width="32">&nbsp;<c:out value="${item.itemCode}" />
							</td>
							<td width="300">&nbsp;<c:out value="${item.itemName}" />
							</td>
							 <td width="60" >&nbsp;<c:out value="${item.masterUnit.name}" />
		  </td>
							<td width="50">&nbsp;<c:out value="${item.masterPack.name}" />
							</td>
							 
							<td style="text-align: right;" width="68">&nbsp;<c:out value="${item.stockTotal}" /></td>
							<td style="text-align: right;" width="68">&nbsp;<c:out value="${item.purchaseRate}" /></td>
							<td style="text-align: right;" width="68">&nbsp;<c:out value="${item.mrp}" /></td>
							<td  style="text-align: right;" width="68">&nbsp;<c:out value="${item.underDeliveryTolerance}" /></td>
							<td  style="text-align: right;" width="68">&nbsp;<c:out value="${item.overDeliveryTolerance}" /></td>

						</tr>

					</c:forEach>
  
  </tbody>
</table>
  </div> <div><c:url value="show_item_list_po" var="item_list_url">
			<c:param name="next" value="${itemForm.itemDTO.previous}"></c:param>
			</c:url> <a href="${item_list_url}" class="previousbtn" ></a>
		</div>
         <div><c:url value="show_item_list_po" var="item_list_url">
			<c:param name="next"  value="${itemForm.itemDTO.next}"></c:param>
			</c:url> <a href="${item_list_url}" class="nextbtn" ></a>
		</div>
  </div>
  
  </div>
   
  
  
						