<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false"%>


<script type="text/javascript">
			
			$(document).ready(function() {  	
				
				$("button").button();
				$("#lightness").click(function() { $('#link').attr('href', 'jquery-ui/css/ui-lightness/jquery-ui-1.8.4.custom.css'); });
				$("#hotsneaks").click(function() { $('#link').attr('href', 'jquery-ui/css/hot-sneaks/jquery-ui-1.8.4.custom.css'); });
				$("#flick").click(function() { $('#link').attr('href', 'jquery-ui/css/flick/jquery-ui-1.8.4.custom.css'); });
				$("#redmond").click(function() { $('#link').attr('href', 'jquery-ui/css/redmond/jquery-ui-1.8.4.custom.css'); });
				$("#smoothness").click(function() { $('#link').attr('href', 'jquery-ui/css/smoothness/jquery-ui-1.8.4.custom.css'); });
				
				$('#fixmyheader-1').fixheadertable({
					caption		: 'My header is fixed !',
					zebra		: true
				});
				
				$('#fixmyheader-2').fixheadertable({
					caption		: 'My header is fixed !',
					height		: 200,
					whiteSpace	: 'normal'
				});
				
				$('#fixmyheader-3').fixheadertable({
					caption		: 'My header is fixed !',
					height		: 200
				});
				
				$('#fixmyheader-4').fixheadertable({
					caption		: 'My header is fixed !',
					width		: 400,
					height		: 150
				});
				
				$('#fixmyheader-5').fixheadertable({
					caption		: 'My header is fixed !',
					height		: 200,
					minWidth	:840
				});
				
				$('#fixmyheader-6').fixheadertable({
					caption		: 'My header is fixed !',
					height		: 200,
					minWidthAuto: false
				});
				
				$('#fixmyheader-7').fixheadertable({
					caption		: 'My header is fixed !',
					height		: 200
				});
				
				$('#fixmyheader-8').fixheadertable({
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
	
th{font-size:10px;}
 td{font-size:12px;}  
 
 	
	div.t_fixed_header_main_wrapper.ui div.t_fixed_header_caption {
		display:none;
		
		}
	
</style>

<script>
	$(document).ready(function() {
		$(".cancelbtn").click(function() {
			window.self.location = "backto_finishGood";
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
				$.get('get_batch_by_id', { id: $(this).attr('id')}, function(data) {					 					
 					
 					$("#itemIdSO").val(data.batchNo);
});
				
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
select {
	width: 87%;
	height: 22px;
}
</style>

		<div class="panel-header">
			<div class="panel-title">Batch List</div>
			<div class="panel-tool"></div>
		</div>
		<div align="left" class="bkgColor" style="height: 177px">
		<form:form name="input" id="formID" action="get_batch_for_item" method="get" modelAttribute="searchCriteriaBatch">
		
			<div class="headingdiv"  >
				<table width="628" height="31" border="0" cellpadding="0"
					cellspacing="0">
					<tr>
						<td width="30"><div align="center">Batch No</div></td>
						
						 <td width="288"><form:input  path="batchNo" size="16" id="batchNo" /></td>
						
						<td width="45"><input class="searchbtn"
							style="font-size: 11px; font-weight: bold; padding: 0 0 0 20px;"
							type="submit" value=" " />
						</td>
						<td width="45" align="right"><div >
						<input type="hidden" name="batchNoSearch" value="" id="itemIdSO">
						<input type="hidden" name="id" value="${itemId}" id="itemIdFG">
						<input type="submit" id="btnok" name="btn" value="&nbsp;" class="okbtn"  style="font-size: 11px; font-weight: bold; border:0px;  padding: 0 0 0  ;"/>    					
						</div></td>
						<td width="45">
					<a href="backto_finishGood" class="cancelbtn" ></a>
						</td>
						<td width="16">&nbsp; 
						</td>
					</tr>
				</table>
			</div>
			
	</form:form>
			<div style="width: 906px; margin: 0 auto;">
				<span style="margin-left: 80px;" class="errmsg"></span>
			</div>
			<div class="gridheadingdiv" >
	  <table width="668" class="fixmyheader" id="fixmyheader-8">
  <thead>
					<tr>
						<td width="68"><div align="center">
								<strong>Batch No</strong>
							</div>
						</td>
						<td width="78"><div align="center">
								<strong>Mfg. Dt.</strong>
							</div>
						</td>
						<td width="78"><div align="center">
								<strong>Expiry Dt.</strong>
							</div>
						</td>
						<td width="78"><div align="center">
								<strong>Stock </strong>
							</div>
						</td>
						<td width="78"><div align="center">
								<strong>Basic Rate</strong>
							</div>
						</td>
						<td width="78"><div align="center">
								<strong>M. R. P.</strong>
							</div>
						</td>
					</tr>
			  </thead>
  <tbody> 
					<c:forEach items="${batchList}" var="batch">
						<tr class="sr" style="cursor: pointer;" id="${batch.batchNo}">
					
							<td width="58">&nbsp;
							  <c:out value="${batch.batchNo}" />
						  </td>
							<td width="68">&nbsp;
							<fmt:formatDate pattern="dd-MMM-yyyy" value="${batch.mfgDate}" />
						  </td>
							<td width="68">&nbsp;<fmt:formatDate pattern="dd-MMM-yyyy" value="${batch.expiryDate}" />
						  </td>
							<td width="68">&nbsp;
						  <c:out value="${batch.closingStock}" /></td>
							<td width="68">&nbsp;
						  <c:out value="${batch.invoiceRate}" /></td>
							<td width="68">&nbsp;
						  <c:out value="${batch.mrp}" /></td>
							
                             
					</tr>
				</c:forEach>
  
  </tbody>
</table>
  </div>

			</div>
			


		
<div>
</div>

	</div>