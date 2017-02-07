
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page isELIgnored="false"%>

<c:if test="${shiftReportMasterForm.operation=='V'}">
	<script type="text/javascript">
		$(document).ready(function() {
			$('input').attr('readonly', 'readonly');
			$('select').attr('disabled', 'disabled');
			$('textarea').attr('readonly', 'readonly');
			$('.datepicker1').attr('disabled', 'disabled');
			$('input:radio').attr('disabled', true);
			$("input:checkbox").attr("disabled", true);
			$(".newWindow").attr("disabled", true);
			$(".newRow").attr("disabled", true);
			$(".newWindow1").attr("disabled", true);
			$(".delelteImg").attr("disabled", true);
			$('.datepicker2').attr('disabled', 'disabled');
		});
	</script>
</c:if>
<script type="text/javascript">
	//	$(document).ready(function() {
 	function editMethod()
 	 { 
 	 var frank_param = $('#shiftReportIdHidden').val();
 	 //	confirm('Are you sure you want to delete?');
	 var delUrl='get_shiftReport?shiftReportId='+frank_param+'&opr=E';
 	 window.self.location = delUrl;  
	}
 	</script>
<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {
		/* Initialise datatables */
		var oTable = $('#example').dataTable();

	});
	
</script>

<c:if test="${errorDTO.errorMsg!=null}">


	<input type="hidden" id="errorId" value="${errorDTO.errorMsg}">
		<script type="text/javascript">
	//	var delUrl='show_melter_trolly_form';
		$(document).ready(function() {
			var errorId=document.getElementById('errorId');
			alert(errorId.value);
    	// window.self.location = delUrl;
		});
 	</script>
	</c:if>



<script type="text/javascript">
	$(document).ready(
			function() {

				$('.fixmyheader-8').fixheadertable(
						{
							caption : 'My header is fixed !',
							height : 110,
							addTitles : true,
							colratio : [ '10%', '10%', '8%', '50px', 'auto',
									'auto', '30%', 'auto' ]
						});
			});
</script>
<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {
		$(".myTimePicker[readonly]").css("background-color", "#ffffff");
		$(".datepicker1[readonly]").css("background-color", "#ffffff");
	});
</script>
<script>
	function edit(index) {

		var frm1 = document.forms[0];
		var ind = parseInt(index);
		frm1.elements["indexNo"].value = ind;
	}
</script>


<!-- <script type="text/javascript">
$(document).ready(function(){ 
    //called when key is pressed in textbox
	 $(".newRow").click(function (e)  
	{ 	
	document.forms["formID"].action="add_row_in_shift?operation="+$(this).attr('id');
	document.forms["formID"].submit();	
   });
	$(".remRow").click(function (e)  
			{ 
			document.forms["formID"].action="remove_row_from_bp?rs="+$(this).attr('id');
			document.forms["formID"].submit();	
		   }); 
    
});
  </script> -->
<style>
table {
	width: 971px;
}

.newRow {
	background: url(static/images/new.png);
	background-repeat: no-repeat;
	height: 22px;
	border: none;
	width: 12px;
	cursor: pointer;
}

.remRow {
	
}
</style>
<script type="text/javascript">

function getInt(obj) {
	var val = 0;
	if (obj) {
		if (obj == '') {
			obj = 0;
		}
		val = parseInt(obj);
	}
	return val;
}
	$(document)
			.ready(
					function() {

						function formChange() {
							var end = 0;
							var start = 0;
							for ( var ele = 0; true; ele++) {
								var frm = document.forms[1];

								start = frm.elements["shiftReportMasterDTO.shiftSpinInterruptionDetailDTOList["
										+ ele + "].spinFromTime"];

								if (!start) {
									break;
								}

								end = frm.elements["shiftReportMasterDTO.shiftSpinInterruptionDetailDTOList["
										+ ele + "].spinToTime"];

								start = start.value;
								end = end.value;

								if (start != '') {
									start = start.split(":");
								}
								if (end != '') {
									end = end.split(":");
								}

								var startDate = new Date(0, 0, 0, start[0],
										start[1], 0);
								var endDate = new Date(0, 0, 0, end[0], end[1],
										0);

								if (endDate.getTime() < startDate.getTime()) {
									alert('End time can not be less than start time');
									frm.elements["shiftReportMasterDTO.shiftSpinInterruptionDetailDTOList["
											+ ele + "].spinToTime"].value = "23:00";

								}
								var diff = endDate.getTime()
										- startDate.getTime();

								var hours = Math.floor(diff / 1000 / 60 / 60);
								diff -= hours * 1000 * 60 * 60;
								var minutes = Math.floor(diff / 1000 / 60);

								if (!hours) {
									hours = '00';
								}
								if (!minutes) {
									minutes = '00';
								}

								var duration = hours + ':' + minutes;
								frm.elements["shiftReportMasterDTO.shiftSpinInterruptionDetailDTOList["
										+ ele + "].spinDuration"].value = duration;

							}
							//

							for ( var ele = 0; true; ele++) {
								var frm = document.forms[1];

								var engStart = frm.elements["shiftReportMasterDTO.shiftEngInterruptionDetailDTOList["
										+ ele + "].engFromTime"];

								if (!engStart) {
									break;
								}

								
								
								
								$("#engFromTime"+getInt(ele)+1).timepicker({
									timeFormat : "hh:mm:ss",
									 show24Hours: true,
									  separator: '.',
									  step: 15
								});
								$("#engToTime"+getInt(ele)+1).timepicker({
									timeFormat : "hh:mm:ss",
									 show24Hours: true,
									  separator: '.',
									  step: 15
								});
								
								$("#fromTime"+getInt(ele)+1).timepicker({
									timeFormat : "hh:mm:ss",
									 show24Hours: true,
									  separator: '.',
									  step: 15
								});
								$("#toTime"+getInt(ele)+1).timepicker({
									timeFormat : "hh:mm:ss",
									 show24Hours: true,
									  separator: '.',
									  step: 15
								});
								
								
								var engEnd = frm.elements["shiftReportMasterDTO.shiftEngInterruptionDetailDTOList["
										+ ele + "].engToTime"];

								engStart = engStart.value;
								engEnd = engEnd.value;

								if (engStart != '') {
									engStart = engStart.split(":");
								}
								if (engEnd != '') {
									engEnd = engEnd.split(":");
								}

								var engstartDate = new Date(0, 0, 0,
										engStart[0], engStart[1], 0);
								var engendDate = new Date(0, 0, 0, engEnd[0],
										engEnd[1], 0);

								if (engendDate.getTime() < engstartDate
										.getTime()) {
									alert('End time can not be less than start time');
									frm.elements["shiftReportMasterDTO.shiftEngInterruptionDetailDTOList["
											+ ele + "].engToTime"].value = "23:00";
								}

								var engdiff = engendDate.getTime()
										- engstartDate.getTime();

								var enghours = Math
										.floor(engdiff / 1000 / 60 / 60);
								engdiff -= enghours * 1000 * 60 * 60;
								var engminutes = Math
										.floor(engdiff / 1000 / 60);

								if (!enghours) {
									enghours = '00';
								}
								if (!engminutes) {
									engminutes = '00';
								}

								var engduration = enghours + ':' + engminutes;
								frm.elements["shiftReportMasterDTO.shiftEngInterruptionDetailDTOList["
										+ ele + "].engDuration"].value = engduration;

							}
							// 
						}

						$('#formID2').change(function() {
							formChange();
						});
						formChange();
					});
</script>

<script>
	$(document)
			.ready(
					function() {

						function abc(cb) {
							/*  alert('Sai');*/
							$
									.get(
											'getEmpCityBy_id',
											{
												id : $(cb).val()
											},
											function(data) {
												/*  alert("sai ram"); */
												$("#localState")
														.val(
																data.areaDTO.regionDTO.stateDTO.stateName);
												$("#localCountry")
														.val(
																data.areaDTO.regionDTO.stateDTO.zoneDTO.countryDTO.countryName);
											});
						}
						$(function() {
							$('#employeeCity').change(function() {
								/* alert($(this).val()); */
								abc(this);

							});
						});

						abc($('#employeeCity'));
					});

	$(document)
			.ready(
					function() {
						function abc(cb) {
							/*  alert('Sai');*/
							$
									.get(
											'getEmpCityBy_id',
											{
												id : $(cb).val()
											},
											function(data) {
												/*  alert("sai ram"); */
												$("#permanentState")
														.val(
																data.areaDTO.regionDTO.stateDTO.stateName);
												$("#permanentCountry")
														.val(
																data.areaDTO.regionDTO.stateDTO.zoneDTO.countryDTO.countryName);
											});
						}
						$(function() {
							$('#permanentCityId').change(function() {
								/* alert($(this).val()); */
								abc(this);

							});
						});

						abc($('#permanentCityId'));
					});

	$(document).ready(function() {
		function abc(cb) {
			if ($(cb).is(':checked')) {
				/* alert("jai sai "); */
				$("#pfAcNumber").val('');
				$('#pfAcNumber').hide();
			} else {
				$('#pfAcNumber').show();
			}
		}

		$(function() {
			$('#pfFlag1').click(function() {
				/* alert("jai sai "); */
				abc(this);
			});
		});
		$(function() {
			$("#pfFlag2").click(function() {

				$('#pfAcNumber').show();

			});
		});

		abc($('#pfFlag1'));

	});

	$(document).ready(function() {
		function abc(cb) {
			if ($(cb).is(':checked')) {
				/* alert("jai sai "); */
				$("#esiAcNumber").val('');
				$('#esiAcNumber').hide();
			} else {
				$('#esiAcNumber').show();
			}
		}

		$(function() {
			$('#esiFlag1').click(function() {
				/* alert("jai sai "); */
				abc(this);
			});
		});
		$(function() {
			$("#esiFlag2").click(function() {

				$('#esiAcNumber').show();

			});
		});

		abc($('#esiFlag1'));

	});

	$(document).ready(function() {
		function abc(cb) {
			if ($(cb).is(':checked')) {
				/* alert("jai sai "); */
				$("#overtimeRate").val('');
				$('#overtimeRate').hide();
			} else {
				$('#overtimeRate').show();
			}
		}

		$(function() {
			$('#overtimeFlag1').click(function() {
				/* alert("jai sai "); */
				abc(this);
			});
		});
		$(function() {
			$("#overtimeFlag2").click(function() {

				$('#overtimeRate').show();

			});
		});

		abc($('#overtimeFlag1'));

	});

	jQuery(document).ready(function() {
		// binds form submission and fields to the validation engine
		jQuery("#formID").validationEngine();
	});

	jQuery(document).ready(function() {
		// binds form submission and fields to the validation engine
		jQuery("#formID1").validationEngine();
	});
	jQuery(document).ready(function() {
		// binds form submission and fields to the validation engine
		jQuery("#formID2").validationEngine();
	});
	jQuery(document).ready(function() {
		// binds form submission and fields to the validation engine
		jQuery("#formID3").validationEngine();
	});

	function checkHELLO(field, rules, i, options) {
		if (field.val() != "HELLO") {
			// this allows to use i18 for the error msgs
			return options.allrules.validate2fields.alertText;
		}
	}
</script>

<script type="text/javascript">
function finishGood(){
	
		window.open('new_finishedGoods?operation=shift report');
		return false;	
			
	
}

	$(document)
			.ready(
					function() {
						//called when key is pressed in textbox
						$(".newRow")
								.click(
										function(e) {
											document.forms["formID2"].action = " add_row_in_shift?operation="
													+ $(this).attr('id');
											;
											document.forms["formID2"].submit();
										});

						$(".newWindow")
								.click(
										function(e) {
											document.forms["formID2"].action = "show_item_list_shift";
											document.forms["formID2"].submit();
										});
					});
</script>
<script type="text/javascript">
	function refresh() {

		// $.post("ajax_Employee_form", $("#formIDTest").serialize());
		document.getElementById("script").value = '1';
		document.forms["formIDTest"].submit();

	}

	function doAjaxPost() {
		// get the form values
		var code = $('#employeeCode').val();

		$.ajax({
			type : "POST",
			url : "checkEmployeeCode",
			data : "code=" + code,
			success : function(response) {
				// we have the response
				if (response != "") {
					$('#info').html(response);
					$('#employeeCode').val('');
				} else {
					$('#info').html("");
				}
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
</script>

 <c:if test="${shiftReportMasterForm.operation=='E' }">
	<script type="text/javascript">
		$(document).ready(function() {
		$('.datepicker1').attr('disabled','disabled');
		});
	</script>
	</c:if>
	


<script type="text/javascript">
	//anonymous self invoking function to avoid conflicting with other JavaScript
	(function($) {
		//function is called when the page is fully loaded
		$(document).ready(function() {
			//the page is loaded so attach the time picker to an input field
			$(".myTimePicker").timepicker({});
		});
	})(jQuery);

	$(document).ready(function() {
		$(".quantity").keypress(function(e) {
			 if(e.which == 46 && $(this).val().indexOf('.') != -1) 
			 {
			     e.preventDefault();
			  }
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
</script>

<script type="text/javascript">	
$( function() {
	 $('.quantity').keyup(function(){
	   if($(this).val().indexOf('.')!=-1){         
	       if($(this).val().split(".")[1].length > 5){                
	           if( isNaN( parseFloat( this.value ) ) ) return;
	           this.value = parseFloat(this.value).toFixed(5);
	       }  
	    }            
	    return this; //for chaining
	 });
	});
	$(document).ready(function() {
		
		var hash = window.location.hash;
		
		if(hash=='#tabs-2'){
			console.log(hash);
		}
		var l=$('#lastDate').val();
		
		$(".datepicker1").datepicker({
			changeMonth : true,
			changeYear : true,
			yearRange : '-99:+10',
			dateFormat : 'dd-M-yy',
			minDate: new Date(l)
		});
		//     
	});
</script>

<script type="text/javascript">
	$(function() {
		$("#tabs").tabs();
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
				$.get('get_item_by_idr', {
					id : $(this).attr('id')
				}, function(data) {
					$("#length").val(data.itemLength);
					$("#width").val(data.itemWidth);
					$("#minStock").val(data.minStock);
					$("#maxStock").val(data.maxStock);
					$("#reorderLevel").val(data.reorderLevel);
					$("#suggqty").val(data.suggReorderQty);
					$("#itemIdSO").val(data.itemId);
					$("#density").val(data.itemDensity);

				});
				// $(this).toggleClass('active');
				//alert($(this).attr('id'));
			});
		});
	});
</script>



  <script>
	$(document).ready(function() {
	function fillShippingData(cb){
			$.get('get_shift_time_masterId', { id: $(cb).val()}, function(data) {		
				//		alert('hello');
						 $("#shiftStartTime").val(data.shiftFromTime);
						 $("#endTime").val(data.shiftToTime);
			});
			}
	$(function() {			
			$('#shiftId').change(function() {
				var v=$(this).val();
				fillShippingData(this);
		});
	});	
	fillShippingData($('#shiftId'));	
	});
</script> 


<style type="text/css">
.ui-tabs .ui-tabs-nav li.ui-tabs-selected a,.ui-tabs .ui-tabs-nav li.ui-state-disabled a,.ui-tabs .ui-tabs-nav li.ui-state-processing a
	{
	background-color: #4e8ccf;
	color: #FFFFFF;
}

.ui-state-default,.ui-widget-content .ui-state-default,.ui-widget-header .ui-state-default
	{
	background-color: #4e8ccf !important;
}

.ui-state-default,.ui-widget-content .ui-state-default,.ui-widget-header .ui-state-default
	{
	background: none;
	color: #FFFFFF;
}

.ui-widget-content {
	
}

.ui-corner-all,.ui-corner-bottom,.ui-corner-right,.ui-corner-br {
	background-color: #e0ebff;
}

.ui-tabs .ui-tabs-nav li a,.ui-tabs-collapsible .ui-tabs-nav li.ui-tabs-active a
	{
	background-color: #e0ecff;
	color: #416aa3;
	font-weight: normal;
	font-family: Verdana, Arial, Helvetica, sans-serif;
	width: 99px;
}

div.t_fixed_header_main_wrapper.ui div.t_fixed_header_caption {
	display: none;
}

.tabs li.tabs-selected a.tabs-inner {
	background: none !important;
}

.ui-tabs .ui-tabs-nav li {
	border: 1px solid #4E8CCF !important;
	border-radius: 0 0 0 0;
	width: 123px;
}

.amt {
	background-color: #eeeeee !important;
	border: none !important;
}

.tabs li a.tabs-inner {
	background: none !important;
	text-align: center !important;
}

.tabs li {
	width: 80px !important;
	text-align: center;
}

.easyui-tabs tabs-container {
	height: 150px !important;
}

.tabs-panels {
	height: 142px !important;
	padding: 0px !important;
	width: 800px;
	!
	important;
}

div.ui-datepicker {
	font-size: 10px;
}

div.ui-datepicker1 {
	font-size: 10px;
}

.panel-header {
	width: 68%;
	height: 12px;
}

.ui-tabs .ui-tabs-panel {
	padding: 0px;
}

.ui-widget input,.ui-widget select,.ui-widget textarea,.ui-widget button
	{
	font-family: Verdana, Arial, Helvetica, sans-serif !important;
}

.ui-widget-header {
	background: #4e8ccf !important;
	border: 1px solid #4E8CCF !important;
	padding-bottom: 12px;
}

.newWindow {
	background: url(static/images/search_small.png);
	background-repeat: no-repeat;
	height: 12px;
	border: none;
	width: 12px;
	cursor: pointer;
}

#dlg-buttons {
	text-align: center;
	margin-top: 11px;
	float: none;
	margin-left: 9px;
}

.ui-tabs .ui-tabs-nav li.ui-tabs-active a,.ui-tabs .ui-tabs-nav li.ui-state-disabled a,.ui-tabs .ui-tabs-nav li.ui-tabs-loading a
	{
	background-color: #4e8ccf;
	color: #FFFFFF;
}

.ui-tabs-nav {
	background-color: #e0ecff !important;
}

#tabs .ui-tabs-nav {
	background-color: #e0ecff !important;
	font-size: 12px;
}

h2 {
	text-align: center;
	font-size: 16px;
	margin: 11px 0 0 0;
}

.ui-tabs .ui-tabs-nav {
	padding: 0px;
}

th {
	font-size: 10px;
	text-align: center;
}

td {
	font-size: 12px;
	vertical-align: top;
}

.gridheadingdiv td {
	border: 0 1px 1px 0 solid #7F9DB9;
	height: 24px;
}

.datepicker {
	background-color: #ebebe4;
}

.datepicker1 {
	background-color: #ebebe4;
}

.quantity {
	text-align: right;
}

.quantity1 {
	text-align: right;
}

p {
	color: blue;
}

.netotal {
	float: right;
	width: 151px;
	margin-top: 9px;
}

a.disabled:link,a.disabled:visited {
	color: grey;
}

</style>


	<script>

	function getFloat(obj) {
		if (obj) {
			if (obj == '') {
				obj = 0;
			}
			return parseFloat(obj);
		} else {
			return 0;
		}
	    }
	$(function() {
		
		function formChange() {
			$("#spiningSet1").val(getFloat($("#spiningSet1Final").val())-getFloat($("#spiningSet1Initial").val()));
			$("#spiningSet2").val(getFloat($("#spiningSet2Final").val())-getFloat($("#spiningSet2Initial").val()));
			$("#totalRmConsuptionWeight").val(getFloat($("#htWeight").val())+getFloat($("#rtWeight").val())+getFloat($("#rtzWeight").val())+getFloat($("#shot").val()));
		}
$('#formID').change(function() {
			formChange();
			});
			formChange();
		
	});
	
	function approveBlanket() {
		// get the form values
		var date = $('#date').val();
		var runNo = $('#runNo').val();
		var shiftId = $('#shiftId').val();
		if(date!='' && runNo!='' && shiftId!=''){
		$.ajax({
			type : "POST",
			url : "approveBlanket",
			data : "date=" + date+"&runNo="+runNo+"&shiftId="+shiftId,
			success : function(response) {
				
	}
		});
		}
		else{
			alert("Fill the all mendatory fields");
		}}
	
	function unApproveBlanket() {
		// get the form values
		var date = $('#date').val();
		var runNo = $('#runNo').val();
		var shiftId = $('#shiftId').val();
		if(date!='' && runNo!='' && shiftId!=''){
		$.ajax({
			type : "POST",
			url : "unApproveBlanket",
			data : "date=" + date+"&runNo="+runNo+"&shiftId="+shiftId,
			success : function(response) {
				
	}
		});
		}
		else{
			alert("Fill the all mendatory fields");
		}}
	
	function getBlanketData() {
		// get the form values
		var date = $('#date').val();
		var runNo = $('#runNo').val();
		var shiftId = $('#shiftId').val();
		if(date!='' && runNo!='' && shiftId!=''){
		$.ajax({
			type : "POST",
			url : "getBlanketProductionData",
			data : "date=" + date+"&runNo="+runNo+"&shiftId="+shiftId,
			success : function(response) {
				// we have the response
				var noOfBlankets=response.result.noOfBlankets;
				var blankets=response.result.blanketsWeigth;
				
				var shortLengthBlanketsWeight=response.result.shortLengthBlanketsWeight;
				var edgeTrim=response.result.edgeTrimWeight;
				
				var bulkFibreWeight=response.result.bulkFibreWeight;
				var totalWeight=response.result.totalWeight;
				
				$("#noOfBlankets").val(noOfBlankets);
				$("#blankets").val(blankets);
				$("#shortLengthBlanketsWeight").val(shortLengthBlanketsWeight);
				$("#edgeTrimWeight").val(edgeTrim);
				$("#bulkFibreWeight").val(bulkFibreWeight);
				$("#totalWeight").val(totalWeight);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
		}else{
			alert("Fill the all mendatory fields");
		}
	}

	</script>		
		

<c:if test="${not empty(errorList)}">
	<div class="errorblock">
		<ul>
			<c:forEach items="${errorList.errorList}" var="err">
				<li>${err.errorMsg}</li>
			</c:forEach>
		</ul>
	</div>
</c:if>

<div id="tabs"
	style="width: 974px; height: auto; float: left; border: 1px solid #4E8CCF; padding: 0px; font-family: Arial;">
	<ul style="background-color: #e0ecff; padding-bottom: 1px">
		<li style="width: 127px"><a href="#tabs-1" style="width: 104px;">Shift
				Report Entry</a> </li>
				<c:choose>
			 
				<c:when test="${step2=='2'}">
					<li style="width: 245px"><a href="#tabs-2"
						style="width: 221px;" id="abc">Spare Consumed and
							Interruptions</a></li>
				</c:when>
				<c:otherwise>
					<li style="width: 245px"><a href="#tabs-2"
						style="width: 221px;"
						onclick="this.removeAttribute('href');this.className='disabled'">Spare
							Consumed and Interruptions</a></li>
				</c:otherwise>
			</c:choose>
	</ul>
	<!-- <input type="button" name="Add Production" style="background-color: #E0ECFF; color: #416AA3; height: 29px;" onclick="finishGood();" value="Add Production"> -->
	<form:form name="input" id="formID" action="show_shift_form2#tabs-2"
		method="POST" modelAttribute="shiftReportMasterForm">
		<form:hidden path="lastShiftDate" id="lastDate" />
		<div id="tabs-1">
			<div align="left" class="bkgColor" style="width: 973px;">
				<h2>Shift Report Entry</h2>
				<div id="info" style="color: red;"></div>
				<table width="997" height="220"
					style="padding-left: 9px; float: left; width: 100%; font-family: Arial;"
					border="0" align="right">
					<tbody>
					
					
						<tr>
							<td width="87" height="30">Date<span style="color: #FF0000">*</span>
							</td>
							<td width="92"><form:input type="text"
									path="shiftReportMasterDTO.shifReportDate"
									class="validate[required] text-input datepicker1"
									readonly="true" id="date" style="width:100%" size="11" /> <form:hidden
									path="shiftReportMasterDTO.shiftReportId" id="shiftReportIdHidden" style="width:100%"
									size="11" /></td>
							<td width="40">&nbsp;</td>
							<td width="56">Run No<span style="color: #FF0000">*</span></td>
							<td width="92"><form:input type="text"
									path="shiftReportMasterDTO.runNo"
									class="validate[required] text-input" onkeyup="valid(this)"
									onblur="valid(this)" id="runNo" style="width:100%" size="11" />
							</td>
							<td width="40">&nbsp;</td>
							<td width="89">Shift<span style="color: #FF0000">*</span></td>
							<td width="92">
									
				<form:select  path="shiftReportMasterDTO.mastersDTO.mastersId" id="shiftId" class="validate[required] text-input" onchange="getBlanketData();" style="width:100% !important; height:20px;" >
				<form:option value="" selected="selected"></form:option>
				<form:options items="${shift}" itemLabel="name" itemValue="mastersId" />
					</form:select>
									
									</td>
							<td width="85">&nbsp;</td>
						</tr>
						
						<tr>
							<td height="30">Shift Engineer Name</td>
							<td><form:input type="text"
									path="shiftReportMasterDTO.shiftEngineerName"
									onkeypress="return check(event)" id="shiftEngineerName"
									style="width:100%" size="11" /></td>
							<td>&nbsp;</td>
							<td height="30">DG Power</td>
							<td ><form:input type="text"
									path="shiftReportMasterDTO.dgPowerOption" class="quantity"
									onkeypress="return check(event)" id="dgPowerOption"
									style="width:100%" size="11" /></td>
									<td>&nbsp;</td>
							<td style="text-align: center;" width="60" nowrap="nowrap">Shift Start Time<form:input
									id="shiftStartTime" type="text" size="11" readonly="true"
									data-maxsize="35" style="  width:88%;float:left"
									 path="" /></td>
							<td style="text-align: center">End Time<form:input
									id="endTime" readonly="true" type="text" size="11"
									data-maxsize="35" style=" width:100%" path="" />
							</td>
							
						</tr>
						<tr>
							<td height="30">Operation</td>
							<td><form:input type="text"
									path="shiftReportMasterDTO.shiftOperationTime"
									class="myTimePicker" readonly="TRUE" style="width:100%" size="12"
									id="opertation" /></td>
							<td>Hrs</td>
							<td>H2 Cyl. Used</td>
							<td align="left"><form:input id="h2cyUsed" type="text"
									size="11" data-maxsize="35"
									style="text-align:right; width:100%" class="quantity validate[custom[number]]"
									path="shiftReportMasterDTO.h2cyUsed" /></td>
							<td>Nos.</td>
							<td>No. of Blankets</td>
							<td><form:input id="noOfBlankets" type="text" size="11"
									data-maxsize="35" style="text-align:right; width:100%"
									class="quantity validate[custom[number]]" path="shiftReportMasterDTO.noOfBlankets" /></td>
							<td>&nbsp; Nos.</td>
						</tr>
						<tr>
							<td height="30">Melting Time</td>
							<td><form:input type="text"
									path="shiftReportMasterDTO.meltingTime" readonly="TRUE" class="myTimePicker "
									style="width:100%" size="12" id="meltingTime" /></td>
							<td>Hrs</td>
							<td>Diesel Used</td>
							<td align="left"><form:input id="dieselUsed" type="text"
									size="11" data-maxsize="35"
									style="text-align:right; width:100%" class="quantity validate[custom[number]]"
									path="shiftReportMasterDTO.dieselUsed" /></td>
							<td>Ltr.</td>
							<td>Blankets</td>
							<td><form:input id="blankets" type="text" size="11"
									data-maxsize="35" style="text-align:right; width:100%"
									class="quantity validate[custom[number]]" path="shiftReportMasterDTO.blanketsWeigth" /></td>
							<td>&nbsp; Kgs.</td>
						</tr>
						<tr>
							<td height="30">Pouring Time</td>
							<td><form:input type="text"
									path="shiftReportMasterDTO.poutingTime" readonly="TRUE" class="myTimePicker "
									style="width:100%" size="12" id="poutingTime" /></td>
							<td>Hrs</td>
							<td>Wood Consumption(In Kg)</td>
							<td align="left"><form:input id="lpgUsed" type="text"
									size="11" data-maxsize="35"
									style="text-align:right; width:100%" class="quantity validate[custom[number]]"
									path="shiftReportMasterDTO.lpgUsed" /></td>
							<td>Ltr.</td>
							<td>Short Length Blanket</td>
							<td><form:input id="shortLengthBlanketsWeight" type="text"
									size="11" data-maxsize="35"
									style="text-align:right; width:100%" class="quantity validate[custom[number]]"
									path="shiftReportMasterDTO.shortLengthBlanketsWeight" /></td>
							<td>&nbsp; Kgs.</td>
						</tr>
						<tr>
							<td height="30">Spinning Time</td>
							<td><form:input type="text"
									path="shiftReportMasterDTO.spiningTime" readonly="TRUE" class="myTimePicker "
									style="width:100%" size="12" id="spiningTime" /></td>
							<td>Hrs</td>
							<td>Melter Power</td>
							<td align="left"><form:input id="melterPower" type="text"
									size="11" data-maxsize="35"
									style="text-align:right; width:100%" class="quantity validate[custom[number]]"
									path="shiftReportMasterDTO.melterPower" /></td>
							<td>KWH</td>
							<td>Edge Trim</td>
							<td><form:input id="edgeTrimWeight" type="text" size="11"
									data-maxsize="35" style="text-align:right; width:100%"
									class="quantity validate[custom[number]]" path="shiftReportMasterDTO.edgeTrimWeight" />
							</td>
							<td>&nbsp; Kgs.</td>
							
							
						</tr>
						<tr>
							<td height="30">Initial Pool Level</td>
							<td><form:input id="initialPoolLevel" type="text" size="11"
									data-maxsize="35" style="text-align:right; width:100%"
									class="quantity validate[custom[number]]" path="shiftReportMasterDTO.initialPoolLevel" />
							</td>
							<td>mm</td>
							<td>M.D.</td>
							<td align="left"><form:input id="md" type="text" size="11"
									data-maxsize="35" style="text-align:right; width:100%"
									class="quantity validate[custom[number]]" path="shiftReportMasterDTO.md" /></td>
							<td>KVA</td>
							<td>Bulk Fiber</td>
							<td><form:input id="bulkFibreWeight" type="text" size="11"
									data-maxsize="35" style="text-align:right; width:100%"
									class="quantity validate[custom[number]]" path="shiftReportMasterDTO.bulkFibreWeight" />
							</td>
							<td>&nbsp; Kgs.</td>
						</tr>
						<tr>
							<td height="30">Final Pool Level</td>
							<td><form:input id="finalPoolLevel" type="text" size="11"
									data-maxsize="35" style="text-align:right; width:100%"
									class="quantity validate[custom[number]]" path="shiftReportMasterDTO.finalPoolLevel" />
							</td>
							<td>mm</td>
							<td>KWH</td>
							<td align="left"><form:input id="kwh" type="text" size="11"
									data-maxsize="35" style="text-align:right; width:100%"
									class="quantity validate[custom[number]]" path="shiftReportMasterDTO.kwh" /></td>
							<td>&nbsp;</td>
							<td>Total</td>
							<td><form:input id="totalWeight" type="text" size="11"
									data-maxsize="35" style="text-align:right; width:100%"
									class="quantity validate[custom[number]]" path="shiftReportMasterDTO.totalWeight" /></td>
							<td>&nbsp; Kgs.</td>
						</tr>
						<tr>
							<td height="30">H2 Cy pr Main</td>
							<td><form:input id="h2cyMain" type="text" size="11"
									data-maxsize="35" style="text-align:right; width:100%"
									class="quantity validate[custom[number]]" path="shiftReportMasterDTO.h2cyMain" /></td>
							<td>&nbsp;Kg/cm2</td>
							<td>KVAH</td>
							<td align="left"><form:input id="kvah" type="text" size="11"
									data-maxsize="35" style="text-align:right; width:100%"
									class="quantity validate[custom[number]]" path="shiftReportMasterDTO.Kvah" /></td>
							<td>&nbsp;</td>
<td>Blanket Power</td>
							<td><form:input id="blanketPower" type="text" size="11"
									data-maxsize="35" style="text-align:right; width:100%"
									class="quantity validate[custom[number]]" path="shiftReportMasterDTO.blanketPower" /></td>
						</tr>
						<tr>
							<td height="30">H2 Cy pr Spare</td>
							<td><form:input id="h2cySpare" type="text" size="11"
									data-maxsize="35" style="text-align:right; width:100%"
									class="quantity validate[custom[number]]" path="shiftReportMasterDTO.h2cySpare" /></td>
							<td>Kg/cm2</td>
							<td>Power Factor</td>
							<td><form:input id="powerFector" type="text" size="11"
									data-maxsize="35" style="text-align:right; width:100%"
									class="quantity validate[custom[number]]" path="shiftReportMasterDTO.powerFector" /></td>
							<td>&nbsp;</td>
							
							
								<td>VF Power</td>
							<td><form:input id="ltPanel" type="text" size="11"
									data-maxsize="35" style="text-align:right; width:100%"
									class="quantity validate[custom[number]]" path="shiftReportMasterDTO.ltPanel" /></td>
						</tr>
						
						<tr align="center">
					<td></td><td></td><td></td><td></td> <td></td><td><input type="button" name="Refresh" value="Refresh" onclick="getBlanketData();"></td> <td><input type="button" name="Approve"  value="Approve" onclick="approveBlanket();"></td> <td><input type="button" name="Unapprove" value="Unapprove" onclick="unApproveBlanket();"></td>
					</tr>
						</table>
						<table>
					<tr>
					<td height="20" colspan="9">
                 <div align="center" style="background-color:#4e8ccf; color:#FFF;"><strong>Spinning Set</strong></div>
                   </td>
					</tr>
						
						<tr>
							<td height="30"><BR>Spinning Set-I</td>
							<td>&nbsp;Initial<BR>
							<form:input  type="text" size="11"
									data-maxsize="35" style="text-align:right; width:100%"
									class="quantity validate[custom[number]]" id="spiningSet1Initial" path="shiftReportMasterDTO.spiningSet1Initial" />
							</td>
							
							<td>&nbsp;Final<BR>
							<form:input type="text" size="11"
									data-maxsize="35" style="text-align:right; width:100%"
									class="quantity validate[custom[number]]" id="spiningSet1Final" path="shiftReportMasterDTO.spiningSet1Final" />
							</td>
							
							<td>&nbsp;Total<BR>
							<form:input id="spiningSet1" type="text" size="11"
									data-maxsize="35" style="text-align:right; width:100%" readonly="true"
									class="quantity validate[custom[number]]" path="shiftReportMasterDTO.spiningSet1" /></td>
							
							
							<td></td>
							<td  height="30" nowrap="nowrap"><BR>Spinning Set-II</td>
							
							<td>&nbsp;Initial<BR>
							<form:input  type="text" size="11"
									data-maxsize="35" style="text-align:right; width:100%"
									class="quantity validate[custom[number]]" id="spiningSet2Initial" path="shiftReportMasterDTO.spiningSet2Initial" />
							</td>
							
							<td>&nbsp;Final<BR>
							<form:input type="text" size="11"
									data-maxsize="35" style="text-align:right; width:100%"
									class="quantity validate[custom[number]]" id="spiningSet2Final" path="shiftReportMasterDTO.spiningSet2Final" />
							</td>
							
							<td>&nbsp;Total<BR>
							<form:input  type="text" size="11"
									data-maxsize="35" style="text-align:right; width:100%" readonly="true"
									class="quantity validate[custom[number]]" id="spiningSet2" path="shiftReportMasterDTO.spiningSet2" /></td>
							
						</tr>
						</table>
						<table cellspacing="1">
						<tr>
					<td height="20" colspan="11">
                 <div align="center" style="background-color:#4e8ccf; color:#FFF;"><strong>R/M CONSUMPTION</strong></div>
                   </td>
					</tr>
						<tr>
							<td >HTZ</td>
							<td ><form:input id="htWeight" type="text" size="5"
									data-maxsize="35" style="text-align:right; width:100%"
									class="quantity validate[custom[number]]" path="shiftReportMasterDTO.htWeight" /></td>
							<td>RT</td>
							<td><form:input id="rtWeight" type="text" size="5"
									data-maxsize="35" style="text-align:right; width:100%"
									class="quantity validate[custom[number]]" path="shiftReportMasterDTO.rtWeight" /></td>
							<td >RTZ</td>
							<td><form:input id="rtzWeight" type="text" size="5"
									data-maxsize="35" style="text-align:right; width:100%"
									class="quantity validate[custom[number]]" path="shiftReportMasterDTO.rtzWeight" /></td>
							<td >Shot</td>
							<td><form:input id="shot" type="text" size="5"
									data-maxsize="35" style="text-align:right; width:100%"
									class="quantity validate[custom[number]]" path="shiftReportMasterDTO.shot" /></td>
							<td>Total</td>
							<td><form:input id="totalRmConsuptionWeight" type="text" size="5"
									data-maxsize="35" style="text-align:right; width:100%" readonly="true"
									class="quantity validate[custom[number]]" path="shiftReportMasterDTO.totalRmConsuptionWeight" /></td>
						</tr>
						
						
						<tr>
							<td height="30">Remark</td>
							<td colspan="8"><form:textarea
									style="width:570px; height: 51px;" id="remark"
									onkeypress="return check(event)"
									path="shiftReportMasterDTO.shiftRemark" /></td>
						</tr>

					</tbody>
				</table>
				<div id="dlg-buttons">
					<div class="btn">
						<div class="savbtn">
							<input class="nextbtn" type="submit" value="" />
						</div>
						<div class="cancelbtn">
							<a href="get_shift_report_list" class="cancelbtn"
								iconCls="icon-cancel"></a>
						</div>
					</div>

				</div>
				<div>
					<span style="margin-left: 12px;" class="errmsg"></span>
				</div>
			</div>
		</div>
	</form:form>


	<form:form name="input" id="formID2" action="save_shift_form"
		method="post" modelAttribute="shiftReportMasterForm">
		<form:hidden path="indexNo" id="indexId" />
		<div id="tabs-2">
			<div align="left" class="bkgColor" style="width: 973px;">
				<h2>Business info</h2>
				<table width="505" height="34" border="0" align="center">
					<tbody>

						<tr>
							<td width="87" height="30">Item Group Flag<span
								style="color: #FF0000"></span></td>
							<td width="402"><form:select id="state"
									class="validate[required] text-input"
									style="width:250px; height:20px;"
									path="shiftReportMasterDTO.shiftConsumedDetailDTO.itemGroupFlagId"
									items="${itemGroupList}" itemLabel="itemGroupFlagName"
									itemValue="itemGroupFlagId"></form:select></td>
						</tr>
					</tbody>
				</table>
				<div class="gridheadingdiv">
					<table width="972" class="fixmyheader-8">
						<thead>
							<tr>
								<td width="43"><div align="center">
										<strong>S No.</strong>
									</div></td>
								<td width="119"><div align="center">
										<strong> <input class="newWindow"
											style="font-size: 11px; font-weight: bold; width: 19px;"
											type="submit" value=" " /> &nbsp; Item Name</strong>
									</div></td>
								<td width="104"><div align="center">
										<strong>UOM</strong>
									</div></td>
								<td width="93"><div align="center">
										<strong>Qnty.</strong>
									</div></td>
								<td width="118"><div align="center">
										<strong>Remark</strong>
									</div></td>
								<td width="46"><div align="center">
										<strong>Action</strong>
									</div></td>

							</tr>
						</thead>
						<tbody>
							<c:forEach
								items="${shiftReportMasterForm.shiftReportMasterDTO.shiftConsumedDetailDTOList}"
								var="e" varStatus="s">

								<tr>
									<td width="33" height="24">${s.count}
									<input type="hidden" name="snoId" value="${s.count}" id="snoId">
									</td>
									<td width="109">&nbsp;${e.itemName} <form:hidden
											path="shiftReportMasterDTO.shiftConsumedDetailDTOList[${s.index}].itemId"
											value="${e.itemId}" /></td>
									<td width="94">&nbsp;${e.measurementUnitName} <form:hidden
											path="shiftReportMasterDTO.shiftConsumedDetailDTOList[${s.index}].measurementUnitId"
											value="${e.measurementUnitId}" /></td>
									<td width="83"><form:input type="text"
											path="shiftReportMasterDTO.shiftConsumedDetailDTOList[${s.index}].quantity"
											style="text-align:right; width:100%; border:1px solid #7f9db9; "
											size="12" /></td>
									<td width="108"><form:input type="text"
											path="shiftReportMasterDTO.shiftConsumedDetailDTOList[${s.index}].remark"
											style=" width:100%; border:1px solid #7f9db9; " size="12" />
									</td>
									<td width="37" style="text-align: center;">&nbsp; <c:url
											value="item_remove" var="remove_url">
											<c:param name="index" value="${s.index}"></c:param>
											<c:param name="operation" value="removeItem"></c:param>
										</c:url> <a href="${remove_url}"><img src="static/images/drop.png"
											title="Delete Record" alt="" /> </a>
									</td>

								</tr>
							</c:forEach>
					</table>
				</div>



				<div class="gridheadingdiv">
					<h2>Engineering Breakdown</h2>
					<table width="100%" height="80" style="height: 20px;" border="0"
						align="center">
						<tr>
							<td width="123"><div align="left">
									<strong><input class="newRow"
										style="font-size: 11px; border: none; font-weight: bold; width: 19px;"
										type="submit" id="eng row" value=" " />&nbsp; Add Row</strong>
								</div></td>
							<%-- <td>
     
      <c:url value="add_row_in_shift" var="remove_url">
					<c:param name="operation" value="eng row"></c:param>
					</c:url>
         <a href="${remove_url}"><img src="static/images/new.png" 
								 title="Delete Record"
								alt="" /></a>
     </td> --%>

						</tr>

					</table>
					<table width="972" class="fixmyheader-8">
						<thead>
							<tr>
								<td width="20"><div align="center">
										<strong>S No.</strong>
									</div></td>
								<td width="45"><div align="center">
										<strong>From Time</strong>
									</div></td>
								<td width="45"><div align="center">
										<strong>To Time</strong>
									</div></td>
								<td width="45"><div align="center">
										<strong>Duration</strong>
									</div></td>
								<td width="219"><div align="center">
										<strong>Reason</strong>
									</div></td>
								<td width="33"><div align="center">
										<strong>Action</strong>
									</div></td>
							</tr>
						</thead>
						<tbody>
							<c:forEach
								items="${shiftReportMasterForm.shiftReportMasterDTO.shiftEngInterruptionDetailDTOList}"
								var="e" varStatus="s">
								<tr>
									<td width="10"><label>${s.count }</label></td>
									<td width="35"><form:hidden
											path="shiftReportMasterDTO.shiftEngInterruptionDetailDTOList[${s.index}].sno" />

										<form:input type="text"
											path="shiftReportMasterDTO.shiftEngInterruptionDetailDTOList[${s.index}].engFromTime"
											class="myTimePicker"
											style="text-align:right;  width: 97%; border:1px solid #7f9db9; "
											size="12" id="engFromTime${s.count}" /></td>
									<td width="35"><form:input type="text"
											path="shiftReportMasterDTO.shiftEngInterruptionDetailDTOList[${s.index}].engToTime"
											class="myTimePicker"
											style="width:100%; border:1px solid #7f9db9; " size="12"
											id="engToTime${s.count}" /></td>
									<td width="35"><form:input type="text"
											path="shiftReportMasterDTO.shiftEngInterruptionDetailDTOList[${s.index}].engDuration"
											class="myTimePicker"
											style="width:100%; border:1px solid #7f9db9; "
											readonly="true" size="12" id="engDuration" /></td>

									<td width="209"><form:input id="engReason" type="text"
											size="11" onkeypress="return check(event)" data-maxsize="35"
											style="width:100%; border:1px solid #7f9db9; "
											path="shiftReportMasterDTO.shiftEngInterruptionDetailDTOList[${s.index}].engReason" />
									</td>
									<td style="text-align: center;" width="23"><c:url
											value="item_remove" var="remove_url">
											<c:param name="index" value="${s.index}"></c:param>
											<c:param name="operation" value="eng row"></c:param>
										</c:url> <a href="${remove_url}"><img src="static/images/drop.png"
											title="Delete Record" alt="" /> </a>
									</td>
								</tr>
							</c:forEach>
					</table>
				</div>
				<div class="gridheadingdiv">
					<h2>Spinning Interruption</h2>
					<table width="511" style="height: 20px;" height="90" border="0"
						align="center">
						<tr>
							<td width="123"><div align="left">
									<strong><input class="newRow"
										style="font-size: 11px; border: none; font-weight: bold; width: 19px;"
										type="submit" id="spin row" value=" " />&nbsp; Add Row</strong>
								</div></td>
							<%--  <td>
     
      <c:url value="add_row_in_shift" var="remove_url">
					<c:param name="operation" value="spin row"></c:param>
					</c:url>
         <a href="${remove_url}"><img src="static/images/new.png" 
								 title="Delete Record"
								alt="" /></a>
     
     </td> --%>
						</tr>
					</table>
					<table width="972" class="fixmyheader-8">
						<thead>

							<tr>
								<td width="20"><div align="center">
										<strong>S No.</strong>
									</div></td>
								<td width="45"><div align="center">
										<strong>From Time</strong>
									</div></td>
								<td width="45"><div align="center">
										<strong>To Time</strong>
									</div></td>
								<td width="45"><div align="center">
										<strong>Duration</strong>
									</div></td>
								<td width="219"><div align="center">
										<strong>Reason</strong>
									</div></td>
								<td width="33"><div align="center">
										<strong>Action</strong>
									</div></td>
							</tr>
						</thead>
						<tbody>

							<c:forEach
								items="${shiftReportMasterForm.shiftReportMasterDTO.shiftSpinInterruptionDetailDTOList}"
								var="e" varStatus="s">
								<tr>
									<td width="10"><label>${s.count }</label></td>
									<td width="35"><form:hidden
											path="shiftReportMasterDTO.shiftSpinInterruptionDetailDTOList[${s.index}].sno" />
										<form:input
											path="shiftReportMasterDTO.shiftSpinInterruptionDetailDTOList[${s.index}].spinFromTime"
											class="myTimePicker"
											style="text-align:right; width: 97%; border:1px solid #7f9db9;"
											size="30" id="fromTime${s.count }" /></td>
									<td width="35"><form:input
											path="shiftReportMasterDTO.shiftSpinInterruptionDetailDTOList[${s.index}].spinToTime"
											class="myTimePicker"
											style=" width:100%; border:1px solid #7f9db9; " size="12"
											id="toTime${s.count }" /></td>
									<td width="35"><form:input type="text"
											path="shiftReportMasterDTO.shiftSpinInterruptionDetailDTOList[${s.index}].spinDuration"
											class="myTimePicker"
											style=" width:100%; border:1px solid #7f9db9; " size="12"
											id="spinDuration1" /></td>
									<td width="209"><form:input id="reason" type="text"
											size="11" onkeypress="return check(event)" data-maxsize="35"
											style="width:100%; border:1px solid #7f9db9; "
											path="shiftReportMasterDTO.shiftSpinInterruptionDetailDTOList[${s.index}].spinReason" />
									</td>
									<td style="text-align: center;" width="23"><c:url
											value="item_remove" var="remove_url">
											<c:param name="index" value="${s.index}"></c:param>
											<c:param name="operation" value="spin row"></c:param>
										</c:url> <a href="${remove_url}"><img src="static/images/drop.png"
											title="Delete Record" alt="" /> </a>
									</td>
								</tr>
							</c:forEach>

						</tbody>
					</table>
				</div>

				<table width="92%" height="34" border="0" align="center">
					<tbody>
						<tr>
							<td width="103" height="30">Shift History <span
								style="color: #FF0000"></span></td>
							<td width="882"><form:input id="shiftHistory" type="text"
									size="11"  data-maxsize="250"
									style="text-align:left; width:50%"  path="shiftReportMasterDTO.shiftHistory" />
							</td>
							
						</tr>
					</tbody>
				</table>

				<div class="btn">
					<div class="savbtn">
						<c:if
							test="${shiftReportMasterForm.shiftReportMasterDTO.shiftReportId==null}">
							<input class="submit" type="submit" value="" onclick="return checkItem();"/>
						</c:if>
						<c:if
							test="${shiftReportMasterForm.shiftReportMasterDTO.shiftReportId!=null && shiftReportMasterForm.operation!='remove' && shiftReportMasterForm.operation!='V'}">
							<input class="updatebtn" type="submit" value="" />
						</c:if>
						<c:if test="${shiftReportMasterForm.operation=='remove'}">
							<input class="updatebtn" type="submit" name="operation"
								value="Delete" />
						</c:if><c:if test="${shiftReportMasterForm.operation=='V'}">
 <input class="edit_btn"  type="button" onclick="editMethod();" value=""/>
    	 					   <a href="get_shiftReport?shiftReportId=${shiftReportMasterForm.shiftReportMasterDTO.shiftReportId}" ></a>
    	 					   </c:if>     		
					</div>
					
					
					<div class="cancelbtn">
						<a href="get_shift_report_list" class="cancelbtn"
							iconCls="icon-cancel"> </a>
					</div>
				</div>
			</div>
		</div>
	</form:form>
</div>

