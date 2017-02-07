 <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %>
<c:if test="${not empty(errorList)}">
<input type="hidden" id="errorId" value="${errorList.errorMsg}">
 <script type="text/javascript">
  		$(document).ready(function() {
 		var errorId=document.getElementById('errorId');
		alert(errorId.value);
 		});
 	</script>
 </c:if>
 
 <script>
 function check(e) {  
	    var keynum  
	    var keychar  
	    var numcheck  
	    // For Internet Explorer  
	    if (window.event)  	
	    {  
	    keynum = e.keyCode  
	    }  
	    // For Netscape/Firefox/Opera  
	    else if (e.which)  
	    {  
	    keynum = e.which  
	    }  
	    keychar = String.fromCharCode(keynum)  
	    //List of special characters you want to restrict  
	    if (keychar == "'" || keychar == "@" || keychar == "!" || keychar == "$" || keychar == "#" || keychar == "^"  || keychar == "|" || keychar == ">" || keychar == "<" || keychar == ">" || keychar == "<" || keychar == "" || keychar == "|" || keychar == "%"  )  
	    {  
	      
	    return false;  
	    }  
	    else {  
	    return true;  
	    }  
	    }
 </script>
 <script type="text/javascript">
 function checkEdit()
	{
	alert('Login User Not Permit to Edit Record !!!!!!');
	}
	//	$(document).ready(function() {
 	function editMethod()
 	 { 
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
 		 var next =  Number(getParam('next'));
 		// next=next-15;
 		var delUrl='show_Item_form?next='+next;
 	  	 var frank_param = $('#itemId').val();
 	 //	confirm('Are you sure you want to delete?');
	 var delUrl='get_item?itemId='+frank_param+'&opr=M&next='+next;
 	 window.self.location = delUrl;  
	}
 	</script>	
 <c:if test="${opr=='V' }">
<script>
$(document).ready(function() {
	$('input').attr('readonly','readonly');	
	$('select').attr('disabled','disabled');
	$('textarea').attr('readonly','readonly');
	$('.datepicker').attr('disabled','disabled');
	$('input:radio').attr('disabled',true);
	 $("input:checkbox").attr("disabled", true);
});
</script>
</c:if>	 
 
 <script>
// $(document).ready(function() {
	 $(document).ready(function() {
			$("#itemName").focusout(function()
			{
			 $("#invoiceName").val($("#itemName").val());		
			});
			
		function abc(cb) {
			
			$.get('getItemCatBy_id', { id: $(cb).val()},
					function(data) {
			   
				$("#itemGroupName").val(data.itemGroupDTO.itemGroupName);
			});
		}
		$(function() {
			$('#itemCategoryId').change(function() {
				/* alert($(this).val()); */
				abc(this);

			});
		});

		abc($('#itemCategoryId'));
	});
 
 
 
 (function($) {
     $.fn.currencyFormat = function() {
         this.each( function( i ) {
             $(this).change( function( e ){
                 if( isNaN( parseFloat( this.value ) ) ) return;
                 this.value = parseFloat(this.value).toFixed(2);
             });
         });
         return this; //for chaining
     }
 })(jQuery);
 
 (function($) {
     $.fn.secondaryFormat = function() {
         this.each( function( i ) {
             $(this).change( function( e ){
                 if( isNaN( parseFloat( this.value ) ) ) return;
                 this.value = parseFloat(this.value).toFixed(4);
             });
         });
         return this; //for chaining
     }
 })(jQuery);
 
 (function($) {
     $.fn.fiveRoundFormat = function() {
         this.each( function( i ) {
             $(this).change( function( e ){
                 if( isNaN( parseFloat( this.value ) ) ) return;
                 this.value = parseFloat(this.value).toFixed(5);
             });
         });
         return this; //for chaining
     }
 })(jQuery);

 // apply the currencyFormat behaviour to elements with 'currency' as their class
 $( function() {
     $('.quantity').currencyFormat();
     $('.quantity-sec').secondaryFormat();
     $('.quantity5').fiveRoundFormat();
     
 });
 
 

 
 $(document).ready(function() {
		$(".quantity").each(function() {

		   if( isNaN( parseFloat( this.value ) ) ) return;
			var v = parseFloat($(this).val());

			v = v.toFixed(2);

			$(this).val(v);
		});
		
		$(".quantity5").each(function() {

			   if( isNaN( parseFloat( this.value ) ) ) return;
				var v = parseFloat($(this).val());

				v = v.toFixed(5);

				$(this).val(v);
			});
	});
 
 $(document).ready(function() {
	  var data=0;
		function abc1(cb) {
			    var i= parseFloat($($('#openingStock')).val());
				
			    $.get('get_item_quantity',
					{
			    	id: $(cb).val()
			    	},
			    	function(data) {
			   
				/* $("#itemGroupName").val(data); */
				if((i+data).toFixed(5)=='NaN'){
					
				$("#closing").val(0.0);
				}else{
				$("#closing").val((i+data).toFixed(5));
				}
				
			});
				
		}
		$(function() {
			$('#openingStock').change(function() {
				/* alert($(this).val()); */
				abc1($('#itemId'));
			});
		});
		abc1($('#itemId'));
		
		//Set Primary Conversion
		$('#measurementunitmastersId').change(function() {
			 //console.log($(this)); 
			
				$("#primaryUnit").val($('#measurementunitmastersId').val());
				$('#primaryUnit').attr('disabled','disabled');
				//$('#primaryUnit').prop('disabled','disabled');
			
			//abc1($('#openingStock').val());
		});
		
		$("#formID").submit(function() {
		    $('#primaryUnit').removeAttr('disabled');
		});

	});
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

<c:if test="${itemForm.batchFlag=='isExist'}">
<script>
$(document).ready(function() {
	
	 if($('#batchFlag').is(':checked')){
	   $('#batchFlagNo').attr("disabled",true);
	 }
	 
});
</script>
</c:if>

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
      $(document).ready(function()
       {
           	   
	  $( ".datepicker" ).datepicker({
		  changeMonth: true,
          changeYear: true,
		  yearRange: '-99:+10',
		  dateFormat: 'dd-M-yy',
		  maxDate : new Date()});
  //     
      });
      
     
     </script>

<style type="text/css">
p {
	color: blue;
}

 

input {
	width: 87%;
	margin-bottom: 6px;
}

select {
	width: 88%;
	height: 20px;
}

.errorblock {
	color: #000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	margin-bottom: 16px;
	width: 82%;
}
</style>

       <style type="text/css">
    .datepicker  {
    	background-color:#ebebe4;
    }

    .datepicker1  {
    	background-color:#ebebe4;
    }
    table {
    width:951px;
}
    
    </style>

<form:form name="input" id="formID" action="update_item" method="post" modelAttribute="itemForm">

<div class="panel-header" >
		<div class="panel-title">Item Master</div>
		<div class="panel-tool"></div>
	</div>

	<div align="left" class="bkgColor"   >
		<div class="btn">
			<div class="savbtn">
			 <c:if test="${opr=='V'}">
			 <c:forEach items="${roleAndRightsDTOList}" var="roleAndRights">
		   <c:if test="${roleAndRights.menuId==sessionScope.menuId}">
    	  	 <c:if test="${roleAndRights.editFlag=='true'}">
		  	 <input class="edit_btn"  type="button" onclick="editMethod();" value=""/>
    	      <a href="show_Item_form"  class="cancelbtn" ></a>  
		     </c:if>
		      <c:if test="${roleAndRights.editFlag=='false'}">
		      <input class="edit_btn"  type="button" onclick="checkEdit();" value=""/>
    	      <a href="show_Item_form"  class="cancelbtn" ></a>  
		      </c:if>
		    
          </c:if>
          </c:forEach>	
		</c:if> 
		<c:if test="${opr=='M'}">
 	  	<input class="updatebtn"  type="submit" value=""/>
   	
   	<div class="cancelbtn">
				<a href="show_Item_form" class="cancelbtn" iconCls="icon-cancel"></a>
			</div>
 	</c:if> 
			</div>
			<span style="margin-left: 80px;" class="errmsg"> </span>
		</div>

		 <table  height="673" width="884" border="0" align="left" style="margin-left:20px; margin-top:12px;">
			 <form:hidden  path="itemDTO.itemId" id="itemId"  />
			 <input type="hidden" value="${itemForm.itemDTO.next-(15)}" name="next"/>
			 
			<tr>
				 <td width="136">Item Name<span style="color: #FF0000">*</span></td>
				
				<td colspan="3">
				<form:input type="text" data-maxsize="150" onkeypress="return check(event)" 
					class="validate[required] text-input" path="itemDTO.itemName"
					style="width:95%;" id="itemName" size="58" title="${itemForm.itemDTO.itemName}" /></td>
				<td width="129" align="left">Item Code <span
					style="color: #FF0000">*</span></td>
				 <td width="176"><form:input onkeyup="valid1(this)" onblur="valid1(this)"  type="text" path="itemDTO.itemCode"
					class="validate[required] text-input" data-maxsize="30"
					id="itemCode" size="16" style="width:87%" readonly="true" title="${itemForm.itemDTO.itemCode}"/></td>
			</tr>
			<tr>
				<td>Invoice Name<span style="color: #FF0000"></span>
				</td>
				<td width="174"><form:input type="text"
						path="itemDTO.invoiceName" onkeypress="return check(event)"  data-maxsize="150" size="16"
						id="invoiceName" title="${itemForm.itemDTO.invoiceName}" />
				</td>
				 <td width="107">Item Category<span style="color: #FF0000">*</span></td>
				 <td width="176"><form:select path="itemDTO.itemCategoryDTO.itemCategoryId"
						items="${itemCategories}" itemLabel="itemCategoryName"
						itemValue="itemCategoryId" id="itemCategoryId"
						class="validate[required]" >
					</form:select></td>
				<td align="left">Item Group<span style="color: #FF0000">*</span>
				</td>
				<td>
				<input type="text" onkeyup="valid(this)"
					onblur="valid(this)" data-maxsize="65" name="itemGroupName"
					style="width: 86%;" id="itemGroupName" readonly="true" style="width:86%" size="58" />
				</td>
				</tr>
				

			<tr>
			<td height="29">Item Grade<span style="color: #FF0000">*</span></td>
				<td><form:select path="itemDTO.masterGrade.mastersId"
						items="${grade}" itemLabel="name"
						itemValue="mastersId" id="mastersId"
						class="validate[required]" >
					</form:select></td>
					
					
					<td>Pack Type<span style="color: #FF0000">*</span></td>
				<td>
				 <form:select path="itemDTO.masterPack.mastersId"
						items="${packType}" itemLabel="name"
						itemValue="mastersId" id="mastersId"
						class="validate[required]" >
					</form:select></td>
					
					<td>Measurement Unit<span style="color: #FF0000">*</span></td>
				<td>
				 <form:select path="itemDTO.masterUnit.mastersId"
						items="${MeasurementUnit}" itemLabel="name"
						itemValue="mastersId" id="measurementunitmastersId"
						class="validate[required]" >
					</form:select></td>
			</tr>
			<tr>
			<td>Basic Rate</td>
				<td><form:input type="text" path="itemDTO.salesRate" class="quantity"
					data-maxsize="15" size="16" id="salesRate" style="text-align:right"/></td>
			<td>Launch Dt.</td>
			<td><form:input type="text" path="itemDTO.launchDate"
					class="datepicker" size="16"  readonly="true" style=" width:87%" id="launchDt" /></td>
			
			<td>Item Sequence No</td>
				<td><form:input type="text" path="itemDTO.itemSequenceNo"
						class="quantity " size="16"   style="text-align:right" /></td>
			</tr>
			<tr>
			<td>Tariff Head No</td>
				<td><form:input type="text" path="itemDTO.tariffHeadNo" onkeypress="return check(event)"
						data-maxsize="25" size="16" id="tariffHeadNo"
					   /></td>
			<!-- 
				<td>Trade Rate</td>
				<td><form:input type="text" path="itemDTO.tradeRate"
					data-maxsize="15" size="16" id="tradeRate"  readonly="true" style=" width:87%"/></td>
					 -->
					
				<td>VAT %</td>
				<td><form:input type="text" path="itemDTO.vatPerc" size="16"
					data-maxsize="5" id="vatPer" class="quantity"/></td>
				<td align="left">VAT Valid From</td>
				<td><form:input type="text" path="itemDTO.vatValidFrom"
					class="datepicker" readonly="true" style=" width:87%" id="vatValidFrom" size="16" /></td>
			</tr>
			<tr>
				<td>M.R.P</td>
				<td> <form:input   style="text-align:right; width:87%" type="text" path="itemDTO.mrp" data-maxsize="5"
					size="16" id="mrp"    /></td>
				<td>CST %</td>
				<td> <form:input class="quantity"  style="text-align:right" type="text" path="itemDTO.cstPerc" data-maxsize="5"
					size="16" id="cstPer" /></td>
				<td align="left">CST Valid From</td>
				<td><form:input type="text" path="itemDTO.cstValidFrom"
					class="datepicker"  readonly="true" style=" width:87%" id="cstValideFrom" size="16" /></td>
			</tr>
			<tr>
				<td>Purchase Rate</td>
				<td><form:input type="text"  style="text-align:right" path="itemDTO.purchaseRate"
					data-maxsize="5" size="16" id="purchaseRate" /></td>
				<td>Discount %</td>
				<td> <form:input type="text"  style="text-align:right" path="itemDTO.discountPer" size="16"
					data-maxsize="5" id="discount" class="quantity"/></td>
				<td align="left">Excise Type</td>
				<td><form:select path="itemDTO.exciseTypeFlag" id="exciseType">
                       <form:option value="excisable">Excisable</form:option>
                       <form:option value="exempted">Exempted</form:option>
						<form:option value="nonExcise">Non-Excise</form:option>
						
				</form:select>
				
				</td>
			</tr>
			<tr>
					<td>Item Class<span style="color: #FF0000">*</span></td>
				<td>
				 <form:select path="itemDTO.itemClassId"
						items="${itemClassList}" itemLabel="name"
						itemValue="mastersId" id="mastersId"
						class="validate[required]" >
					</form:select></td>
			<td>Excise %</td>
				<td><form:input class="quantity"  style="text-align:right" type="text" path="itemDTO.excisePerc"
					data-maxsize="5" size="16" id="excisePer" /></td>
				<td align="left">Excise Valid From</td>
				<td><form:input type="text" class="datepicker"
					path="itemDTO.exciseValidFrom"  readonly="true" style=" width:87%" id="exciseValidFrom" size="16" />
				</td>
			</tr>
			<tr>
		
			</tr>
			<tr>
			<td>Conversion (Primary)</td>
			<td><form:input type="text"  path="itemDTO.primaryConversion"  readonly="true" class="quantity"  data-maxsize="11" style="width:40%;text-align:right"   size="16" id="secondaryUnit" />
      
				 <form:select path="itemDTO.masterPrimaryConverUnit"
						items="${MeasurementUnit}" itemLabel="name"
						itemValue="mastersId" id="primaryUnit" disabled="true"
						class="validate[required]" style="width:45%; height:20px;">
					</form:select></td>
					
					<td>is = (Secondary)</td>
			<td><form:input type="text"  path="itemDTO.secondaryConversion"  class="quantity-sec"  data-maxsize="15" style="width:40%;text-align:right"   size="16" id="secondaryUnit" />
          
				 <form:select path="itemDTO.masterSecondaryConverUnit"
						items="${MeasurementUnit}" itemLabel="name"
						itemValue="mastersId" id="mastersId"
						class="validate[required]" style="width:45%; height:20px;">
					</form:select></td>
					
					<td>Gross Weight /Unit</td>
					<td><form:input type="text"  path="itemDTO.grossWeight"  class="quantity"  data-maxsize="11" style="width:40%;text-align:right"   size="16" id="secondaryUnit" />
          
				 <form:select path="itemDTO.masterGrossWeightUnit"
						items="${MeasurementUnit}" itemLabel="name"
						itemValue="mastersId" id="mastersId"
						class="validate[required]" style="width:45%; height:20px;">
					</form:select></td>
			</tr>
			<tr>
			<td>Production Value</td>
				<td><form:input type="text" path="itemDTO.productionValue" size="16"
					data-maxsize="15" class="quantity"  style="text-align:right" id="productionValue" /></td>
				<td>Unit/Case</td>
				<td><form:input class="quantity"  style="text-align:right" type="text" path="itemDTO.unitPerCase"
					data-maxsize="15" size="16" id="unitPerCase" /></td>
					<td>Net Weight /Unit</td>
					<td><form:input type="text"  path="itemDTO.netWeight"  class="quantity"  data-maxsize="11" style="width:40%;text-align:right"   size="16" id="secondaryUnit" />
          
				 <form:select path="itemDTO.masterNetWeightUnit"
						items="${MeasurementUnit}" itemLabel="name"
						itemValue="mastersId" id="mastersId"
						class="validate[required]" style="width:45%; height:20px;">
					</form:select></td>	
			</tr>
			<tr>
			<td>Purchase Order</td>
				<td><div id="radiobutton" style="border: solid 1px; height: 20px; width: 87%;  border-radius: 3px 3px 3px 3px; border-color: #7f9db9; background-color: #FFF;">
						 <span style="    float: left;    margin-top:2px;    padding-left: 12px;"> Yes</span>
						
						<form:radiobutton path="itemDTO.purchaseOrderFlag"
							style="width: 10px;" id="purchaseOrderFlag" value="1"/> 
 <span style="    float: left;    margin-top: 2px;  "> No</span>
 
							<form:radiobutton path="itemDTO.purchaseOrderFlag" style="width: 10px;"
							id="purchaseOrder" value="0"/>  
					</div></td>
				<td>Product Manager</td>
				<td>
				 <form:select path="itemDTO.productManagerId"
						items="${productManager}" itemLabel="employeeName"
						itemValue="employeeId" id="id"
						 >
					</form:select>
				</td>
				<td align="left">Batch <span style="color: #FF0000">*</span></td>
				<td><div id="radiobutton" 	style="border: solid 1px; height: 20px; width: 86%;  border-radius: 3px 3px 3px 3px; border-color: #7f9db9; background-color: #FFF;">
				 <span style="    float: left;    margin-top:2px;    padding-left: 12px;"> Yes</span>
				
						<form:radiobutton class="validate[required] radio"
							path="itemDTO.batchFlag" style="width: 20px;" id="batchFlag"
							value="1"/> 
<span style="    float: left;    margin-top: 2px;  "> No</span>

 <form:radiobutton
							class="validate[required] radio" path="itemDTO.batchFlag"
							style="width:20px;" id="batchFlagNo" value="0"/> 
					</div></td>
	
			</tr>

			<tr>
			
				<td>Entry Tax %</td>
				<td><form:input type="text" path="itemDTO.chapterNumber"
					data-maxsize="25" size="16" id="chapterNumber" /></td>
				<td align="left">Cenvat Allowed</td>
				<td><div id="radiobutton"
						style="border: solid 1px; height: 20px; width: 87%;  border-radius: 3px 3px 3px 3px; border-color: #7f9db9; background-color: #FFF;">
						<form:radiobutton path="itemDTO.cenvatAllowFlag"
							style="width: 10px;" id="cenvatAllowFlag" value="1"/>
						

						<form:radiobutton path="itemDTO.cenvatAllowFlag"
							style="width: 10px;" id="cenvatAllowFlag" value="0"/> 

					</div></td>
					
					<td align="left">Active Status <span style="color: #FF0000">*</span>
				</td>
			<td>
			 <div id="radiobutton" style="border:solid 1px; height:20px; width:87%; border-color:#7f9db9;  border-radius: 3px 3px 3px 3px; background-color:#FFF;">
			  <span style="    float: left;    margin-top:2px;    padding-left: 12px;"> Yes</span>
			 
						<form:radiobutton class="validate[required] radio"
							path="itemDTO.activeStatus" value="1" style="width: 20px; float: left;"
							id="activeStatus"/> 
<span style="    float: left;    margin-top: 2px;  "> No</span>
 <form:radiobutton
							class="validate[required] radio" path="itemDTO.activeStatus"
							style="width:20px;" id="activeStatus" value="0"/> 
			</div></td>
			</tr>
			<tr>
			<td>Length</td>
					<td><form:input type="text"  path="itemDTO.itemLength"  class="quantity"  data-maxsize="15" 
					style="width:40%;text-align:right"   size="16" id="secondaryUnit" />
          
				 <form:select path="itemDTO.masterLengthUnit"
						items="${MeasurementUnit}" itemLabel="name"
						itemValue="mastersId" id="mastersId"
						class="validate[required]" style="width:45%; height:20px;">
					</form:select></td>	
					
					
					
					<td>Width</td>
					<td><form:input type="text"  path="itemDTO.itemWidth"  class="quantity"  data-maxsize="15" style="width:40%;text-align:right"   size="16" id="secondaryUnit" />
         
				 <form:select path="itemDTO.masterWidthUnit"
						items="${MeasurementUnit}" itemLabel="name"
						itemValue="mastersId" id="mastersId"
						class="validate[required]" style="width:45%; height:20px;">
					</form:select></td>	
					
					<td>Thickness</td>
					<td><form:input type="text"  path="itemDTO.itemHeight"  class="quantity"  data-maxsize="15" style="width:40%;text-align:right"   size="16" id="secondaryUnit" />
         
				 <form:select path="itemDTO.masterThiknessUnit"
						items="${MeasurementUnit}" itemLabel="name"
						itemValue="mastersId" id="mastersId"
						class="validate[required]" style="width:45%; height:20px;">
					</form:select></td>	
		
			</tr>
			<tr>
			<td>Density</td>
					<td><form:input type="text"  path="itemDTO.itemDensity"  class="quantity"  data-maxsize="15" style="width:40%; text-align:right"   size="16" id="secondaryUnit" />
          
				 <form:select path="itemDTO.masterDensityUnit"
						items="${MeasurementUnit}" itemLabel="name"
						itemValue="mastersId" id="mastersId"
						class="validate[required]" style="width:45%; height:20px;">
					</form:select></td>	
				<td>Custom Duty %</td>
				<td><form:input type="text" class="quantity"  style="text-align:right" path="itemDTO.customDutyPer"
					data-maxsize="5" size="16" id="itemDTO.customDutyPer" /></td>
				<td align="left">Surcharge (on Custom Duty)</td>
				<td><form:input class="quantity"  style="text-align:right" type="text" path="itemDTO.surchargeOnCustom"
					data-maxsize="15" id="surchargeOnCustom" size="16" /></td>
	
			</tr>
			<tr>
				<td>Minimum Weight</td>
				<td><form:input type="text" class="quantity validate[custom[number]]" style="text-align:right" path="itemDTO.minimumWeight" data-maxsize="5" size="16"/></td>
				<td>Maximum Weight</td>
				<td><form:input type="text" path="itemDTO.maximumWeight"
						class="quantity validate[custom[number]]" data-maxsize="15"
						style="text-align:right; height: 15px;" size="16" />
					</td>
				<td align="left">Nominal Weight</td>
				<td><form:input class="quantity validate[custom[number]]" style="text-align:right"
						type="text" path="itemDTO.nominalWeight" data-maxsize="5" size="16" /></td>
			</tr>
			<tr>
			<td>Opening Stock</td>
				<td><form:input type="text" class="quantity5"   data-maxsize="15"
					path="itemDTO.openingStock" style="text-align:right;"  id="openingStock" size="16" /></td>
				<td>Closing Stock</td>
				<td><form:input type="text" class="quantity5"  style="text-align:right;width:87%" path="itemDTO.closingStock"
					data-maxsize="15" size="16" readonly="true"   id="closing" /></td>
				<td align="left">Rate Type</td>
				<td><form:select path="itemDTO.rateType" id="rateType"
					>
					<form:option value="excludingVat">Excluding VAT</form:option>
						<form:option value="includingVat">Including VAT</form:option>
						
				</form:select></td>
			
			</tr>
			<tr>
			<td>Min Stock</td>
				<td><form:input type="text" class="quantity"  style="text-align:right" data-maxsize="15"
					path="itemDTO.minStock" id="minStock" size="16" /></td>
				<td>Max Stock</td>
				<td><form:input type="text" class="quantity"  style="text-align:right" path="itemDTO.maxStock"
					data-maxsize="15" id="maxStock" size="16" /></td>
				<td align="left">Re-order Level</td>
				<td><form:input class="quantity"  style="text-align:right" type="text" path="itemDTO.reorderLevel"
					data-maxsize="15" id="reorderLevel" size="16" /></td>
		
			</tr>
			<tr>
			<td>Packing Details</td>
				<td><form:input onkeypress="return check(event)" data-maxsize="255" type="text" path="itemDTO.packingDetails"
					id="packingDetails" size="16" /></td>
				<td>Shelf Life</td>
				<td><form:input data-maxsize="15"  style="text-align:right; width:87%"   type="text" path="itemDTO.shelifLifePeriod"
					size="16" id="shelifLifePeriod" readonly="true"  /></td>
				<td align="left">Sugg. Re-order (Qty)</td>
				<td><form:input class="quantity"  style="text-align:right" type="text" path="itemDTO.suggReorderQty"
					data-maxsize="15" id="suggReorderQty" disabled="true" size="16" /></td>
			</tr>
			<tr>
			<td>Store Location<span style="color: #FF0000">*</span></td>
				<td>
				<form:select path="itemDTO.storeLocationDTO.storeLocationId"
						items="${storeLocation}" itemLabel="storeLocation"
						itemValue="storeLocationId" id="id"
						class="validate[required]"  >
					</form:select>
				</td>
				<td>Storing Instruction</td>
				<td><form:input onkeypress="return check(event)" data-maxsize="150" id="storingInstruction" type="text"
					size="16" path="itemDTO.storingInstruction"/></td>
				<td align="left">Vendor Approval req</td>
				<td><div id="radiobutton"
						style="border: solid 1px; height: 20px; width: 87%;  border-radius: 3px 3px 3px 3px;  border-color: #7f9db9; background-color: #FFF;">
						 <span style="    float: left;    margin-top:2px;    padding-left: 12px;"> Yes</span>
						
						<form:radiobutton path="itemDTO.vendorApprovalFlag"
							style="width: 20px;  float: left; " id="vendorApproval" value="1"/> 
							<span style="    float: left;    margin-top: 2px;  "> No</span>
							 <form:radiobutton path="itemDTO.vendorApprovalFlag"
							style="width: 20px;" id="vendorApproval" value="0"/> 
					</div></td>
					</tr>
				<tr>
			 <td>Under Delivery Tolerance (in %)</td>
      <td><form:input type="text" class="quantity"   style="text-align:right"  data-maxsize="5" path="itemDTO.underDeliveryTolerance" id="underDeliveryTolerance" size="16" /></td>
      <td>Over Delivery Tolerance (in %)</td>
      <td><form:input type="text" path="itemDTO.overDeliveryTolerance" data-maxsize="5"  class="quantity"   style="text-align:right"   size="16" id="overDeliveryTolerance" /></td>
      <td align="left">Suggested Vendor</td>
      <td>
      <form:select path="itemDTO.suggestedVendorId"
						items="${Vendor}" itemLabel="partyName"
						itemValue="partyId" id="id"
						  >
					</form:select>
      </td>
			</tr>	
			<tr>
			<td>Lead Time (in days)</td>
				<td><form:input class="quantity1"  style="text-align:right"  type="text" path="itemDTO.leadTimeDay"
					id="leadTimeDay" data-maxsize="3" size="16" /></td>
				<td>GR Processing (in days)</td>
				<td><form:input class="quantity1"  style="text-align:right"  type="text" path="itemDTO.grProcessingDay"
					data-maxsize="3" id="grProcessingDay" size="16" /></td>
				<td align="left">PO reminder Freq. (in days)</td>
				<td><form:input class="quantity1"  style="text-align:right"  type="text" path="itemDTO.poAlertFreqDay"
					data-maxsize="3" id="poAlertFreqDay" size="16" /></td>
			</tr>
			<tr>
			
				<td>Gen. Remark</td>
				<td colspan="5"><form:textarea onkeypress="return check(event)"  type="text" path="itemDTO.generalRemark"
					id="generalRemark" style=" padding-left: 1px;
    width: 97%;" size="64" /></td>
			</tr>
		</table>
		
	<div class="btn">
     <div class="savbtn">
     <c:if test="${opr=='V'}">
     <c:forEach items="${roleAndRightsDTOList}" var="roleAndRights">
		   <c:if test="${roleAndRights.menuId==sessionScope.menuId}">
    	  	 <c:if test="${roleAndRights.editFlag=='true'}">
		  	 <input class="edit_btn"  type="button" onclick="editMethod();" value=""/>
    	      <a href="show_Item_form"  class="cancelbtn" ></a>  
		     </c:if>
		      <c:if test="${roleAndRights.editFlag=='false'}">
		      <input class="edit_btn"  type="button" onclick="checkEdit();" value=""/>
    	      <a href="show_Item_form"  class="cancelbtn" ></a>  
		      </c:if>
		    
          </c:if>
          </c:forEach>	
   			
    	</c:if> 
    	
    <c:if test="${opr=='M'}">
 	  	<input class="updatebtn"  type="submit" value=""/>
   	
   	<div class="cancelbtn">
				<a href="show_Item_form" class="cancelbtn" iconCls="icon-cancel"></a>
			</div>
 	</c:if> 
    </div>   
    </div>    
    <span style="margin-left:80px;" class="errmsg"></span> 
	</div>
	


  
</form:form>
  
   




  
   
