<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>

	<c:if test="${not empty(errors)}">
	<input type="hidden" id="errorId" value="${errors.errorMsg}">
 <script type="text/javascript">
 		$(document).ready(function() {
 		var errorId=document.getElementById('errorId');
		alert(errorId.value);
		});
 	</script>
 </c:if>


  <script>
	$(document).ready(function() {
		$(".cancelbtn").click(function() {
			window.self.location = "get_batch_list";
		});
		
	//	$("input[readonly='true']").addClass('disabled');
		$("input[readonly]").css("background-color","#ebebe4");
	//	$(".datepicker").css("background-color","pink");
	});
</script>

 <script type="text/javascript">    
$(document).ready(function(){ 
 function fixNumFormat(){
	$(".quantity").each(function(){		
		var v=parseFloat($(this).val());
		
		v=v.toFixed(2);
		if(v=='NaN')
			v='0.00';
		$(this).val(v);
	});
 }
 function fixNumFormat1(x){
			var v=parseFloat(x.val());			
			v=v.toFixed(2);
			if(v=='NaN')
				v='0.00';
			x.val(v);
		
	 }

    $(".quantity").change(function (e)  
    		{ 
    	 fixNumFormat1($(this));
    		});
    fixNumFormat();
});
</script>
 <script>
	$(document).ready(function() {
	
	$(function() {			
		$('#itemName').change(function() {
			url="show_new_batch_form?itemId="+ $('#itemName').val();
			self.location=url;
			//	var v=$(this).val();
			//	fillBatchData(this);
		});
	});	
//	fillBatchData($('#itemName'));	
	});
</script> 
<script>
	$(document).ready(function() {
		function abc(cb){	
			
			$.get('get_batch_stock', { batchNo: $('#batchNo').val()}, function(data) {		
					//	alert(data);
					var cl=parseFloat(data)+parseFloat($(cb).val());
				cl=cl.toFixed(2);
						 $("#closingStock").val(cl);
						
			});	
			}
	$(function() {			
			$('#openingStock').change(function() {	
				
			//	abc(this);
			
				 $("#closingStock").val($(this).val());
		});
	});
	abc($('#openingStock'));	
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
	$(document).ready(function() {

		//called when key is pressed in textbox
		$("#quantity").keypress(function(e) {
			//if the letter is not digit then display error and don't type anything
			if (e.which != 8 && e.which != 0 && (e.which<48 || e.which>57)) {
				//display error message
				$("#errmsg").html("Digits Only").show().fadeOut("slow");
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
		  dateFormat: 'dd-M-yy' });
  //     
      });
  </script>
<script type="text/javascript">
      $(document).ready(function()
       {
     
	    $(".datepicker1" ).datepicker({dateFormat : 'dd-M-yy',changeMonth: true,
	          changeYear: true, yearRange: '-99:+0', maxDate: '+0M +0D'});
	    $(".datepicker2" ).datepicker({dateFormat : 'dd-M-yy',changeMonth: true,
	          changeYear: true, yearRange: '-99:+0', minDate: '+0M +0D'});
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



<script>
function getDetails() {
	url="show_new_batch_form?itemId="+ $('#itemName').val();
//	alert(url);
//location.replace(url);	
	//location.assign(url);	
	self.location=url;
}
function check(index) {
	
	var name = "submitItemId"+index;
	var name1="itemId"+index;
	
	
	if (document.getElementById(name1).checked) {
		document.getElementById(name).disabled = false;
		document.getElementById(name).readonly = false;
	} else {
		document.getElementById(name).value = "";
		document.getElementById(name).disabled = true;
	}
}


</script>
<style>


element.style {
    overflow-y: scroll;
}
.aAT, .aAU {
    background: none repeat scroll 0 0 #FFFFFF;
}
.aAU {
    background-color: #FFFFFF;
    overflow-x: visible;
}
body, td, input, textarea, select {
    font-family: arial,sans-serif;
    margin: 0;
}
body {
    height: 100%;
    overflow: hidden;
    width: 100%;
}
body, td, input, textarea, select {
    font-family: arial,sans-serif;
}
body {
    height: 100%;
    margin: 0;
    width: 100%;
}
element.style {
    background-color: #EBEBE4;
    border: 1px solid #7F9DB9;
    height: 16px;
    width: 104%;
}

element.style {
    overflow-y: scroll;
}
.aAT, .aAU {
    background: none repeat scroll 0 0 #FFFFFF;
}
.aAU {
    background-color: #FFFFFF;
    overflow-x: visible;
}
body, td, input, textarea, select {
    font-family: arial,sans-serif;
    margin: 0;
}
body {
    height: 100%;
    overflow: hidden;
    width: 100%;
}
body, td, input, textarea, select {
    font-family: arial,sans-serif;
}
body {
    height: 100%;
    margin: 0;
    width: 100%;
}
table {
    width: 264px;
}
input{
width: 80%;
}
</style>
<form:form name="input" id="formID" action="save_batch" method="post"
	modelAttribute="batchForm">
<form:errors path="*" cssClass="errorblock"  element="div"  />

	<div class="panel-header">
		<div class="panel-title">Batch Master</div>
		<div class="panel-tool"></div>
	</div>

	<div align="left" class="bkgColor"  >
	<table width="826" height="208" border="0" class="select_box"
			align="center"
			style="  margin-left: 20px; float: left;">

			<tbody>
				<tr>
				<td width="400">
				<table width="0">
				<tr>
					<td nowrap="nowrap" width="85">Batch No.<span style="color: #FF0000">*</span>
					</td>
					<td><form:input class="validate[required] text-input" onkeypress="return check(event)"
						data-maxsize="15" path="batchDTO.batchNo"  id="batchNo" size="18" />
					</td>
					</tr><tr>
					<td>Mfg Date<span style="color: #FF0000">*</span>
					</td>
					<td><form:input path="batchDTO.mfgDate" size="18" id="mfgDate"
						class="datepicker1 validate[required] text-input" readonly="true" style=" width:80%"/>
					</td>
					</tr><tr>
					<td align="left">Expiry Date
					</td>
					<td><form:input path="batchDTO.expiryDate" id="expiryDate"
						size="18"
						class="datepicker2" readonly="true" style=" width:80%" />
					</td>
				</tr>
				<tr>
					<td align="left">Active<span style="color: #FF0000">*</span>
					</td>
					<td><div
							style="border: solid 1px; height: 20px; width: 80%; border-radius: 3px 3px 3px 3px; border-color: #7f9db9; background-color: #FFF;">
							 <span style="    float: left;    margin-top:2px;    padding-left: 12px;"> Yes</span>
							 <form:radiobutton path="batchDTO.activeStatus" value="1" 
							class="validate[required] radio"
							style="width: 10px;  margin:2px 9px; float: left;" id="activeStatus" />
					<span style="    float: left;    margin-top: 2px;  "> No</span>
							 <form:radiobutton path="batchDTO.activeStatus" value="0" 
								class="validate[required] radio"
								style="width: 10px;  margin: 2px 9px;" id="activeStatus"/>
						</div>
					</td>
				</tr>
				</table>
				</td>
				<td>
				<table width="0" border="0">
				<tr>
					<td width="93" nowrap="nowrap">Item Name<span style="color: #FF0000">*</span>
					</td>
					<td colspan="3">
					
					
					
					<div class="scrolltable"
									Style="height: 200px; width: 100%; background: none;">
									<table width="129" style="width: 129px;" cellpadding="0"
										cellspacing="0">
										<tr>

											<th
												style="border-bottom:1px solid #99BBE8; color:#fff; height:19px; background-color: #4e8ccf; padding-left: 3px;" width="587"> Select</th>
											<th style="border-bottom:1px solid #99BBE8;border-left:1px solid #99BBE8; color:#fff; background-color: #4e8ccf; " width="587">Item Name</th>
										</tr>

										<c:forEach items="${items}" var="cat" varStatus="item">
											<tr>
											<th style="border-bottom:1px solid #99BBE8; " 
													width="316" ><form:checkbox
														path="batchDTO.itemDTOList[${item.index}].itemId"
														style="float:left; width:40px;" 
														value="" id="itemId${item.index}" onclick="check(${item.index})" /></th>
														<th style="border-bottom:1px solid #99BBE8; " 
													width="316" nowrap="nowrap">
												<c:out value="${cat.itemName}" /> 
												
												<form:hidden
														path="batchDTO.itemDTOList[${item.index}].itemClassId"
														style="float:left; width:40px;" 
														value="${cat.itemId}" id="submitItemId${item.index}"  disabled="true" />
												
												
												
												
												</th>
											</tr>
										</c:forEach>
									</table>
								</div>
					
					
					
					
					
					</td></tr>
				</table>
				</td>
				</tr>
				<tr>
				<td>
						<div class="btn">
			<div class="savbtn">
				<input class="submit" type="submit" value="" />
			</div>
			<div >
			 <a href="get_batch_list" class="cancelbtn" ></a>				
			</div>
		</div>
<span style="margin-left:80px;" class="errmsg"></span> 
	</div>
				
				</td>
				</tr>
				</tbody>
				</table>
	
	

	
	
	




</form:form>

