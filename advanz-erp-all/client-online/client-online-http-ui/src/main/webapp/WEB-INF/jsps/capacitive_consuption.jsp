<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page isELIgnored="false"%>

<script type="text/javascript">
			
			$(document).ready(function() {  	
				$('.fixmyheader-8').fixheadertable({
					caption		: 'My header is fixed !',
					height		:190,
					addTitles	: true,
					colratio	: ['10%', '10%', '8%', '50px', 'auto', 'auto', '30%', 'auto']
				});
			});
		</script>
 
<style type="text/css">
div.t_fixed_header table {
 border-collapse: separate !important;
}
#tbrollL  td{
border-color: solid;
border-width: 0 1px 1px 0;}

body {
	font-family:Arial, Helvetica, sans-serif;
	}
code, pre {				
				padding		: 10px;	
				background	: #F5F5F5;
				border		: 0px solid #D4D4D4;
				overflow-x	: auto;
				font-size	: 12px;
			}
			div.t_fixed_header_main_wrapper.ui {
			float: left;
			}	
	
th{font-size:10px;}
 td{font-size:12px;}  
 
 	
	div.t_fixed_header_main_wrapper.ui div.t_fixed_header_caption {
		display:none;
		
		}
	
</style>



  
 
 <style>
 table{
 width: 971px;
 
 }
 
   .newRow {
		background:url(static/images/new.png);
		background-repeat:no-repeat;
		height:22px;
		border:none;
		width:12px;
		cursor: pointer;
		
		}
	.ui-widget-content {
		
		height: 193px;
		}
	.remRow{}
	.editRow{}
</style> 
<script>
	$(document).ready(function() {
		$(".cancelbtn").click(function() {
			window.self.location = "get_blanketProduction_list";
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



<script type="text/javascript">
      $(document).ready(function()
       {     
    	  $(".enteredDate").datepicker({
  			dateFormat : 'dd-M-yy',
  			autoSize : false,
  			changeMonth: false,
  	        changeYear: false, maxDate: '0', minDate: '0'
  		});
    	  
    	  $(".sourceItemId").change(function(e){
    		  var ChangedId=(e.target.id).replace ( /[^\d.]/g, '' );
    		  var listSize=$("#listSize").val();
    			 $.ajax({
    					type : "POST",
    					url : "get_item_code",
    					data : "itemId=" + $('#sourceItemId'+ChangedId).val(),

    					success : function(response) {
    			               $("#sourceItemCode"+ChangedId).val(response.status);
    					}
    		  });
    	  });
    	  
$(".targetItemId").change(function(e){
	var ChangedId=(e.target.id).replace ( /[^\d.]/g, '' );
 	 $.ajax({
			type : "POST",
			url : "get_item_code",
			data : "itemId=" + $('#targetItemId'+ChangedId).val(),

			success : function(response) {
				$("#targetItemCode"+ChangedId).val(response.status);
				}
			});
    	  });
$(".newRow").click(function (e){ 	
	console.log((e));
	 var listSize=$("#ListSize").val();
	if(!getFilterScreen($("#sourceItemCode"+listSize).val()) || 
		!getFilterScreen($("#targetItemCode"+listSize).val()) ||
		!getFilterInt($("#sourceItemId"+listSize).val()) ||
		!getFilterInt($("#targetItemId"+listSize).val()) ||
		!getFilterInt($("#sourceQuantity"+listSize).val()) ||
		!getFilterInt($("#targetQuantity"+listSize).val())){
		alert('Please fill last entry before add more items.');
		return;
	}
	
	document.forms["formID"].action="add_new_row";
	 document.forms["formID"].submit();	

    	   });
    	   
$("#formID").submit(function(e){
	var listSize=0;
	if(!getFilterScreen($("#sourceItemCode"+listSize).val()) || 
			!getFilterScreen($("#targetItemCode"+listSize).val()) ||
			!getFilterInt($("#sourceItemId"+listSize).val()) ||
			!getFilterInt($("#targetItemId"+listSize).val()) ||
			!getFilterInt($("#sourceQuantity"+listSize).val()) ||
			!getFilterInt($("#targetQuantity"+listSize).val())){
			alert('Please fill at least one entry');
			 e.preventDefault();
			return;
		}
   
});
    	   
      });
      
      function getFilterScreen(str){
    	 // console.log(str);
    	  if(str=="" || str==" " || str==null || ($.trim(str).length)<=0){
    		  return false;
    	  }
    	  return true;
      }
      
      function getFilterInt(i){
    	 // console.log(i);
    	  if(i=="" || i==" " || i==null || i==0){
    		  return false;
    	  }
    	  return true;
      }

      
     
      
  </script>


<style type="text/css">
<!--
#formID .bkgColor table tr td label {
	
}
.scroll {
height: 514px;
overflow-x:hidden;
overflow-y:scroll;  

}
input {
    border: medium none;
    width: 100px;
}
.tableview{     border: 0px solid #7F9DB9;
    border-radius: 3px 3px 3px 3px;
    margin-bottom: 7px;
    margin-left: 5px;
    margin-top: 5px;
    padding: 7px;
    width: 961px;
}
.fixmyheader-8 {
float: left;
}

-->
</style>
<input type="hidden" name="ListSize" value="${listSize-1}" id="ListSize">

	<form:form name="input" id="formID" action="submit_capative_consumtion" modelAttribute="capacitiveConsumptionForm" >
		<div class="panel-header" >
			<div class="panel-title">Captive Consumption</div>
		
		</div>
<table width="668" >
				  <tr>
				  <td width="32"><input class="newRow" title="Add" style="font-size: 11px; font-weight: bold;width:23px;  padding: 0 0 0 0px;  margin-left: 6px;" type="button" value=" "/></td>
						<%-- <td width="32"><div align="center">								<strong>Entered Date</strong>							</div>						</td>
						<td width="59">
						<form:input path="capativeConsuptionDTOList[${s.index}].enteredDate"  id="enteredDate"  size="16" readonly="true" /></td> --%>
						
					</tr>
					</table>
					<div class="gridheadingdiv">
					<table width="668" id="tbrollL"  class="fixmyheader-8" >
					   <tr>
					   <td width="32"><div align="center">								<strong>Entered Date</strong>							</div>						</td>
						<td width="70"><div align="center">								<strong>Source Item</strong>							</div>						</td>
						<td width="22"><div align="center">								<strong>Item Code</strong>							</div>						</td>
						<td width="22"><div align="center">														<strong>Source Qty</strong>	</div>						</td>
						
						<td width="70"><div align="center">								<strong>Target Item</strong>							</div>						</td>
						<td width="22"><div align="center">								<strong>Item Code</strong>							</div>						</td>
						<td width="22"><div align="center">															<strong>Target Qty</strong></div>						</td>
					</tr>
					
					<c:forEach items="${capacitiveConsumptionForm.capativeConsuptionDTOList}" var="bpd" varStatus="s">
					<tbody>
					<tr>
					<td width="59">
						<form:input path="capativeConsuptionDTOList[${s.index}].enteredDate"  id="enteredDate" class="enteredDate" size="16" readonly="readonly" /></td>
						
					<td width="70">
					<form:select path="capativeConsuptionDTOList[${s.index}].sourceItemId" id="sourceItemId${s.index}" class="sourceItemId" style="width:98%">
<form:option value="0" selected="selected">---Select Item---</form:option>
							<form:options items="${itemList}" itemValue="itemId" 
								itemLabel="itemName" />
						</form:select>
					</td>
					<td width="22">
			<form:input path="capativeConsuptionDTOList[${s.index}].sourceItemCode" id="sourceItemCode${s.index}" readonly="readonly" style="text-align:right; border:1px solid #7f9db9; width:100%" />
					</td>
					<td width="22">
					<form:input path="capativeConsuptionDTOList[${s.index}].sourceQuantity" id="sourceQuantity${s.index}"  style="text-align:right; border:1px solid #7f9db9; width:100%" class="quantity validate[custom[number]] qtyCls"/>
					</td>
					
					<td width="70">
					<form:select path="capativeConsuptionDTOList[${s.index}].targetItemId" id="targetItemId${s.index}" class="targetItemId" style="width:98%">
							<form:option value="0" selected="selected">---Select Item---</form:option>
							<form:options items="${itemList}" itemValue="itemId"
								itemLabel="itemName" />
						</form:select>
					</td>
					<td width="22">
					<form:input path="capativeConsuptionDTOList[${s.index}].targetItemCode" id="targetItemCode${s.index}" readonly="readonly" style="text-align:right; border:1px solid #7f9db9; width:100%" />
					</td>
					<td width="22">
					<form:input path="capativeConsuptionDTOList[${s.index}].targetQuantity" id="targetQuantity${s.index}" style="text-align:right; border:1px solid #7f9db9; width:100%" class="quantity validate[custom[number]] qtyCls"/>
					</td>
					</tr>
					</tbody>
					</c:forEach>
		
	</table>
					
</div>
<input class="submit" style="margin-top: 10px;margin-left: 5px; " type="submit" value=" " />
<a href="show_capative_consumtion_list" class="cancelbtn" style="margin-top: 10px;margin-left: 5px; " iconCls="icon-add"></a>
</form:form>