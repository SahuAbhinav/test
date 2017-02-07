<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>

 <c:if test="${opr=='R'}">
 <script type="text/javascript">
		
			
		function getParam(name)
		{
		 name = name.replace(/[\[]/,"\\\[").replace(/[\]]/,"\\\]");
		 var regexS = "[\\?&]"+name+"=([^&#]*)";
		 var regex = new RegExp( regexS );
		 var results = regex.exec( window.location.href );
		 if( results == null )
		  return "";
		else
		 return results[1];
		}

		$(document).ready(function() {
 		 var frank_param = getParam('itemId');
 		var next = getParam('next');
 		 //	confirm('Are you sure you want to delete?');
		 var delUrl='remove_item?itemId='+frank_param+'&next='+next;
		 var next1=Number(next);
		 next1=next1-15;
		 var redrctUrl='show_Item_form?next='+next1;
 		 if(confirm('Are you sure you want to delete?')) 
 		   {
 			document.location = delUrl;
 		 	}
		else{
		  window.self.location = redrctUrl;
  		 }
		});
 	</script>
 	</c:if>

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
	$(document).ready(function() {
		$('input').attr('disabled', 'disabled');
		$('select').attr('disabled', 'disabled');
	});
</script>

<script type="text/javascript">
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


<script type="text/javascript">
	$(document).ready(function() {
		$("button").button();
		//$('input:text, input:password').button()   
		$(".datepicker").datepicker();
		//      $(":submit").button()
	});
</script>

<style type="text/css">
p {
	color: blue;
}

.errmsg {
	color: red;
}

input {
	width: 87%;
	margin-bottom: 6px;
}

select {
	width: 87%;
	height: 22px;
}

.errorblock {
	color: #000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	margin-bottom: 16px;
	width: 82%;
}
</style>
<%-- 
<div class="errorblock">
	<ul>
		<li>Remove Confirmation</li>
	</ul>
</div>

<c:if test="${not empty(errorList)}">
	<div class="errorblock">
		<ul>
			<c:forEach items="${errorList.errorList}" var="err">
				<li>${err.errorMsg}</li>
			</c:forEach>
		</ul>
	</div>
</c:if> --%>

<form:form name="input" id="formID" action="remove_item" method="post"
	modelAttribute="itemForm">

	<div class="panel-header">
		<div class="panel-title">Item Master</div>
		<div class="panel-tool"></div>
	</div>

	<div align="left" class="bkgColor" style="height: 755px;">

		<div id="dlg-buttons">
			<div class="btn">
				<c:url value="remove_item" var="remove_url">
					<c:param name="itemId" value="${itemForm.itemDTO.itemId}"></c:param>
				</c:url>
				<%-- <div class="btn">
					<div class="savbtn">
						<a href="${remove_url}" class="removebtn"></a>
					</div>
					<div class="cancelbtn">
						<a href="show_Item_form" class="cancelbtn" iconCls="icon-cancel"></a>
					</div>
				</div> --%>

			</div>
		</div>


		<table height="673" width="884" border="0" align="left"
			style="margin-left: 20px; margin-top: 12px;">
			<tr>
				<td width="136">Item Name<span style="color: #FF0000">*</span></td>

				<td colspan="3"><form:input type="text" data-maxsize="65"
						class="validate[required] text-input" path="itemDTO.itemName"
						style="width:95%;" id="itemName" size="58" /></td>
				<td width="129" align="left">Item Code <span
					style="color: #FF0000">*</span></td>
				<td width="136"><form:input type="text" path="itemDTO.itemCode"
						class="validate[required] text-input" data-maxsize="16"
						id="itemCode" size="16" /></td>
			</tr>
			<tr>
				<td>Invoice Name<span style="color: #FF0000"></span>
				</td>
				<td width="174"><form:input type="text"
						path="itemDTO.invoiceName" data-maxsize="65" size="16"
						id="invoiceName" /></td>
				<td width="107">Item Category<span style="color: #FF0000">*</span></td>
				<td width="176"><form:select
						path="itemDTO.itemCategoryDTO.itemCategoryId"
						items="${itemCategories}" itemLabel="itemCategoryName"
						itemValue="itemCategoryId" id="itemCategoryId"
						class="validate[required]">
					</form:select></td>
				<td align="left">Item Group<span style="color: #FF0000">*</span>itemGroups
				</td>
				<td><select name="" class="" disabled="" id="itemGroup">
						<option>test</option>
				</select></td>
			</tr>


			<tr>
				<td height="29">Item Grade<span style="color: #FF0000">*</span></td>
				<td><form:select path="itemDTO.masterGrade" items="${grade}"
						itemLabel="name" itemValue="id" id="id" class="validate[required]">
					</form:select></td>


				<td>Pack Type<span style="color: #FF0000">*</span></td>
				<td><form:select path="itemDTO.masterPack" items="${packType}"
						itemLabel="name" itemValue="id" id="id" class="validate[required]">
					</form:select></td>

				<td>Measurement Unit<span style="color: #FF0000">*</span></td>
				<td><form:select path="itemDTO.masterUnit"
						items="${MeasurementUnit}" itemLabel="name" itemValue="id" id="id"
						class="validate[required]">
					</form:select></td>
			</tr>
			<tr>
				<td>Basic Rate</td>
				<td><form:input type="text" path="itemDTO.salesRate"
						data-maxsize="10" size="16" id="salesRate"
						style="text-align:right" /></td>
				<td>Launch Dt.</td>
				<td><form:input type="text" path="itemDTO.lunchDateString"
						class="datepicker" size="16" id="launchDt" /></td>
				<td align="left">Active Status <span style="color: #FF0000">*</span>
				</td>
				<td>
					<div
						style="border: solid 1px; height: 20px; width: 86%; border-color: #7f9db9; background-color: #FFF;">
						<form:radiobutton class="validate[required] radio"
							path="itemDTO.activeStatus" value="1" style="width: 10px;"
							id="activeStatus" />
						Yes
						<form:radiobutton class="validate[required] radio"
							path="itemDTO.activeStatus" style="width: 10px;"
							id="activeStatus" value="0" />
						No
					</div>
				</td>
			</tr>
			<tr>
			<td>Tariff Head No</td>
				<td><form:input type="text" path="itemDTO.tariffHeadNo"
						data-maxsize="10" size="16" id="tariffHeadNo"
						style=" width:87%"  /></td>
			<!-- 	<td>Trade Rate</td>
				<td><form:input type="text" path="itemDTO.tradeRate"
						data-maxsize="10" size="16" id="tradeRate" class="quantity" /></td> -->
						
				<td>VAT %</td>
				<td><form:input type="text" path="itemDTO.vatPerc" size="16"
						data-maxsize="10" id="vatPer" class="quantity" /></td>
				<td align="left">VAT Valid From</td>
				<td><form:input type="text" path="itemDTO.vatValidFromString"
						class="datepicker" id="vatValidFrom" size="16" /></td>
			</tr>
			<tr>
				<td>M.R.P</td>
				<td><span class="errmsg2"></span> <form:input class="quantity"
						type="text" path="itemDTO.mrp" data-maxsize="10" size="16"
						id="mrp" /></td>
				<td>CST %</td>
				<td><span class="errmsg2"></span> <form:input class="quantity"
						type="text" path="itemDTO.cstPerc" data-maxsize="10" size="16"
						id="cstPer" /></td>
				<td align="left">CST Valid From</td>
				<td><form:input type="text" path="itemDTO.cstValidFromString"
						class="datepicker" id="cstValideFrom" size="16" /></td>
			</tr>




			<tr>
				<td>Purchase Rate</td>
				<td><form:input type="text" path="itemDTO.purchaseRate"
						data-maxsize="10" size="16" id="purchaseRate" /></td>
				<td>Discount %</td>
				<td><span class="errmsg3"></span> <form:input type="text"
						path="itemDTO.discountPer" size="16" data-maxsize="10"
						id="discount" class="quantity" /></td>
				<td align="left">Excise Type</td>
				<td><form:select path="itemDTO.exciseTypeFlag" id="exciseType">

						<option value="excisable">Excisable</option>
						<option value="nonExcise">Non-Excise</option>
						<option value="exempted">Exempted</option>
					</form:select></td>
			</tr>
			<tr>
				<td>Primary Unit</td>
				<td><form:input type="text" path="itemDTO.primaryUnit"
						class="quantity" data-maxsize="15"
						style="width:40%;text-align:right" size="16" id="primaryUnit" />

					<form:select path="itemDTO.masterPrimaryUnit"
						items="${MeasurementUnit}" itemLabel="name" itemValue="id" id="id"
						class="validate[required]" style="width:45%; height:22px;">
					</form:select></td>
				<td>Excise %</td>
				<td><form:input type="text" path="itemDTO.excisePerc"
						data-maxsize="10" size="16" id="excisePer" /></td>
				<td align="left">Excise Valid From</td>
				<td><form:input type="text" class="datepicker"
						path="itemDTO.exciseValidFromString" id="exciseValidFrom"
						size="16" /></td>
			</tr>
			<tr>
				<td>Secondary Unit</td>
				<td><form:input type="text" path="itemDTO.secondaryUnit"
						class="quantity" data-maxsize="15"
						style="width:40%;text-align:right" size="16" id="secondaryUnit" />

					<form:select path="itemDTO.masterSecondaryUnit"
						items="${MeasurementUnit}" itemLabel="name" itemValue="id" id="id"
						class="validate[required]" style="width:45%; height:22px;">
					</form:select></td>
				<td>Surcharge</td>
				<td width="158"><form:input type="text"
						path="itemDTO.surCharge" data-maxsize="10" size="16"
						id="surcharge" /></td>
				<td align="left">Net Rate</td>
				<td><form:input type="text" path="itemDTO.netRate" size="16"
						data-maxsize="10" readonly="readonly" id="netRate" /></td>

			</tr>
			<tr>
				<td>Conversion (Primary)</td>
				<td><form:input type="text" path="itemDTO.primaryConversion"
						class="quantity" data-maxsize="15"
						style="width:40%;text-align:right" size="16" id="secondaryUnit" />

					<form:select path="itemDTO.masterPrimaryConverUnit"
						items="${MeasurementUnit}" itemLabel="name" itemValue="id" id="id"
						class="validate[required]" style="width:45%; height:22px;">
					</form:select></td>

				<td>is = (Secondary)</td>
				<td><form:input type="text" path="itemDTO.secondaryConversion"
						class="quantity" data-maxsize="15"
						style="width:40%;text-align:right" size="16" id="secondaryUnit" />

					<form:select path="itemDTO.masterSecondaryConverUnit"
						items="${MeasurementUnit}" itemLabel="name" itemValue="id" id="id"
						class="validate[required]" style="width:45%; height:22px;">
					</form:select></td>

				<td>Gross Weight /Unit</td>
				<td><form:input type="text" path="itemDTO.grossWeight"
						class="quantity" data-maxsize="15"
						style="width:40%;text-align:right" size="16" id="secondaryUnit" />

					<form:select path="itemDTO.masterGrossWeightUnit"
						items="${MeasurementUnit}" itemLabel="name" itemValue="id" id="id"
						class="validate[required]" style="width:45%; height:22px;">
					</form:select></td>
			</tr>
			<tr>
				<td>Production Value</td>
				<td><form:input type="text" path="itemDTO.productionValue"
						size="16" data-maxsize="10" id="productionValue" /></td>
				<td>Unit/Case</td>
				<td><form:input type="text" path="itemDTO.unitPerCase"
						data-maxsize="10" size="16" id="unitPerCase" /></td>
				<td>Net Weight /Unit</td>
				<td><form:input type="text" path="itemDTO.netWeight"
						class="quantity" data-maxsize="15"
						style="width:40%;text-align:right" size="16" id="secondaryUnit" />

					<form:select path="itemDTO.masterNetWeightUnit"
						items="${MeasurementUnit}" itemLabel="name" itemValue="id" id="id"
						class="validate[required]" style="width:45%; height:22px;">
					</form:select></td>
			</tr>
			<tr>
				<td>Purchase Order</td>
				<td><div
						style="border: solid 1px; height: 20px; width: 111px; border-color: #7f9db9; background-color: #FFF;">
						<form:radiobutton path="itemDTO.purchaseOrderFlag"
							style="width: 10px;" id="purchaseOrderFlag" value="1" />
						Yes
						<form:radiobutton path="itemDTO.purchaseOrderFlag"
							style="width: 10px;" id="purchaseOrder" value="0" />
						No
					</div></td>
				<td>Product Manager</td>
				<td><form:select path="itemDTO.productManagerId"
						id="productManagerId" style="width: 114px; height: 20px;">
						<option></option>
					</form:select></td>
				<td align="left">Batch <span style="color: #FF0000">*</span></td>
				<td><div
						style="border: solid 1px; height: 20px; width: 111px; border-color: #7f9db9; background-color: #FFF;">
						<form:radiobutton class="validate[required] radio"
							path="itemDTO.batchFlag" style="width: 10px;" id="batchFlag"
							value="1" />
						Yes
						<form:radiobutton class="validate[required] radio"
							path="itemDTO.batchFlag" style="width: 10px;" id="batchFlag"
							value="0" />
						No
					</div></td>

			</tr>

			<tr>
				<td>Item Class<span style="color: #FF0000">*</span></td>
				<td><form:select path="itemDTO.itemClassId"
						items="${MeasurementUnit}" itemLabel="name" itemValue="id" id="id"
						class="validate[required]">
					</form:select></td>
				<td>Chapter Number</td>
				<td><form:input type="text" path="itemDTO.chapterNumber"
						data-maxsize="25" size="16" id="chapterNumber" /></td>
				<td align="left">Cenvat Allowed</td>
				<td><div
						style="border: solid 1px; height: 20px; width: 111px; border-color: #7f9db9; background-color: #FFF;">
						<form:radiobutton path="itemDTO.cenvatAllowFlag"
							style="width: 10px;" id="cenvatAllowFlag" value="1" />
						Yes
						<form:radiobutton path="itemDTO.cenvatAllowFlag"
							style="width: 10px;" id="cenvatAllowFlag" value="0" />
						No
					</div></td>


			</tr>
			<tr>
				<td>Length</td>
				<td><form:input type="text" path="itemDTO.itemLength"
						class="quantity" data-maxsize="15"
						style="width:40%;text-align:right" size="16" id="secondaryUnit" />

					<form:select path="itemDTO.masterLengthUnit"
						items="${MeasurementUnit}" itemLabel="name" itemValue="id" id="id"
						class="validate[required]" style="width:45%; height:22px;">
					</form:select></td>



				<td>Width</td>
				<td><form:input type="text" path="itemDTO.itemWidth"
						class="quantity" data-maxsize="15"
						style="width:40%;text-align:right" size="16" id="secondaryUnit" />

					<form:select path="itemDTO.masterWidthUnit"
						items="${MeasurementUnit}" itemLabel="name" itemValue="id" id="id"
						class="validate[required]" style="width:45%; height:22px;">
					</form:select></td>

				<td>Thikness</td>
				<td><form:input type="text" path="itemDTO.itemHeight"
						class="quantity" data-maxsize="15"
						style="width:40%;text-align:right" size="16" id="secondaryUnit" />

					<form:select path="itemDTO.masterThiknessUnit"
						items="${MeasurementUnit}" itemLabel="name" itemValue="id" id="id"
						class="validate[required]" style="width:45%; height:22px;">
					</form:select></td>

			</tr>
			<tr>
				<td>Density</td>
				<td><form:input type="text" path="itemDTO.itemDensity"
						class="quantity" data-maxsize="15"
						style="width:40%; text-align:right" size="16" id="secondaryUnit" />

					<form:select path="itemDTO.masterDensityUnit"
						items="${MeasurementUnit}" itemLabel="name" itemValue="id" id="id"
						class="validate[required]" style="width:45%; height:22px;">
					</form:select></td>
				<td>Custom Duty %</td>
				<td><form:input type="text" path="itemDTO.customDutyPer"
						data-maxsize="10" size="16" id="itemDTO.customDutyPer" /></td>
				<td align="left">Surcharge (on Custom Duty)</td>
				<td><form:input type="text" path="itemDTO.surchargeOnCustom"
						data-maxsize="10" id="surchargeOnCustom" size="16" /></td>

			</tr>
			<tr>
				<td>Opening Stock</td>
				<td><form:input type="text" data-maxsize="10"
						path="itemDTO.openingStock" style="text-align:right;"
						id="openingStock" size="16" /></td>
				<td>Closing Stock</td>
				<td><form:input type="text" path="itemDTO.closingStock"
						data-maxsize="10" size="16" readonly="readonly" id="closing" /></td>
				<td align="left">Rate Type</td>
				<td><form:select path="itemDTO.rateType" id="rateType">
						<option value="includingVat">Including VAT</option>
						<option value="excludingVat">Excluding VAT</option>
					</form:select></td>

			</tr>
			<tr>
				<td>Min Stock</td>
				<td><form:input type="text" data-maxsize="10"
						path="itemDTO.minStock" id="minStock" size="16" /></td>
				<td>Max Stock</td>
				<td><form:input type="text" path="itemDTO.maxStock"
						data-maxsize="10" id="maxStock" size="16" /></td>
				<td align="left">Re-order Level</td>
				<td><form:input type="text" path="itemDTO.reorderLevel"
						data-maxsize="10" id="reorderLevel" size="16" /></td>

			</tr>
			<tr>
				<td>Packing Details</td>
				<td><form:input type="text" path="itemDTO.packingDetails"
						id="packingDetails" size="16" /></td>
				<td>Shelf Life</td>
				<td><form:input type="text" path="itemDTO.shelifLifePeriod"
						size="16" id="shelifLifePeriod" /></td>
				<td align="left">Sugg. Re-order (Qty)</td>
				<td><form:input type="text" path="itemDTO.suggReorderQty"
						data-maxsize="10" id="suggReorderQty" size="16" /></td>
			</tr>
			<tr>
				<td>Store Location<span style="color: #FF0000">*</span></td>
				<td><form:select
						path="itemDTO.storeLocationDTO.storeLocationId"
						class="validate[required]" id="storeLocationId"
						style="width: 114px; height: 20px;">
						<option value="1">Test</option>
					</form:select></td>
				<td>Storing Instruction</td>
				<td><form:input id="storingInstruction" type="text" size="16"
						path="itemDTO.storingInstruction" /></td>
				<td align="left">Vendor Approval req</td>
				<td><div
						style="border: solid 1px; height: 20px; width: 111px; border-color: #7f9db9; background-color: #FFF;">
						<form:radiobutton path="itemDTO.vendorApprovalFlag"
							style="width: 10px;" id="vendorApproval" value="1" />
						Yes
						<form:radiobutton path="itemDTO.vendorApprovalFlag"
							style="width: 10px;" id="vendorApproval" value="0" />
						No
					</div></td>
			</tr>
			<tr>
				<td>Under Delivery Tolerance (in %)</td>
				<td><form:input type="text" class="quantity" data-maxsize="15"
						path="itemDTO.underDeliveryTolerance" id="underDeliveryTolerance"
						size="16" /></td>
				<td>Over Delivery Tolerance (in %)</td>
				<td><form:input type="text"
						path="itemDTO.overDeliveryTolerance" class="quantity" size="16"
						id="overDeliveryTolerance" /></td>
				<td align="left">Suggested Vendor</td>
				<td><form:select path="itemDTO.suggestedVendorId"
						id="suggestedVendor">
						<option></option>
					</form:select></td>
			</tr>
			<tr>
				<td>Lead Time (in days)</td>
				<td><form:input type="text" path="itemDTO.leadTimeDay"
						id="leadTimeDay" data-maxsize="10" size="16" /></td>
				<td>GR Processing (in days)</td>
				<td><form:input type="text" path="itemDTO.grProcessingDay"
						data-maxsize="10" id="grProcessingDay" size="16" /></td>
				<td align="left">PO reminder Freq. (in days)</td>
				<td><form:input type="text" path="itemDTO.poAlertFreqDay" data-maxsize="10" id="poAlertFreqDay" size="16" /></td>
			</tr>
			<tr>

		<td>Gen. Remark</td>
		<td colspan="5">
		<form:input type="text"	path="itemDTO.generalRemark" id="generalRemark"	style="width: 95%;" size="64" /></td>
			</tr>
		</table>
		<div id="dlg-buttons">
			<div class="btn">
				<c:url value="remove_item" var="remove_url">
					<c:param name="itemId" value="${itemForm.itemDTO.itemId}"></c:param>
				</c:url>
			</div>
		</div>
	</div>




</form:form>








