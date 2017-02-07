<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<script type="text/javascript">
function validatePhone(txtPhone) {
		   var a = document.getElementById(txtPhone).value;
		  // var a = $("#phoneResi").val();
		    
		    var filter = /^[0-9-+]+$/;
		    if (filter.test(a)) {
		        return true;
		    }
		    else {
		        return false;
		    }}
		    
function IsEmail(email) {
	  var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	  
	  if(regex.test(email) ) {
		    return false;
		  } else {
		    return true;
		  }
	}
$(function() {
	var leaveListSize=$("#leaveListSize").val();
	function leaveCalculat(){
		for(var i=0;i<leaveListSize;i++){
			if($("#allowDays"+i).val()=='' ||$("#allowDays"+i).val()==null){
				$("#allowDays"+i).val(0);
			}
		}}
	$(".form4Class").change(function(){
		leaveCalculat();
	});
	leaveCalculat();
		});
$(document).ready(function() {
	
	$('#phoneResi').blur(function(e) {
			   if (validatePhone('phoneResi')) {
			       $('#spnPhoneStatus').html('');
			       $('#spnPhoneStatus').css('color', 'green');
			   }
			   else {
			      $('#spnPhoneStatus').html('Invalid Phone Number');
			      $('#spnPhoneStatus').css('color', 'red');
			      $('#phoneResi').val('');
			   }
			}); 
	
	$('#contactMobile').blur(function(e) {
		   if (validatePhone('contactMobile')) {
		       $('#scontactMobile').html('');
		       $('#scontactMobile').css('color', 'green');
		   }
		   else {
		      $('#scontactMobile').html('Invalid Phone Number');
		      $('#scontactMobile').css('color', 'red');
		      $('#contactMobile').val('');
		   }
		}); 
	$('#phoneOffice').blur(function(e) {
		   if (validatePhone('phoneOffice')) {
		       $('#sphoneOffice').html('');
		       $('#sphoneOffice').css('color', 'green');
		   }
		   else {
		      $('#sphoneOffice').html('Invalid Mobile Number');
		      $('#sphoneOffice').css('color', 'red');
		      $('#phoneOffice').val('');
		   }
		}); 
	$('#emailId').blur(function(e) {
		   if (IsEmail('emailId')) {
		       $('#semailId').html('');
		       $('#emailId').css('color', 'green');
		   }
		   else {
		      $('#semailId').html('Invalid Email Id');
		      $('#semailId').css('color', 'red');
		      $('#emailId').val('');
		   }
		}); 
	
});
</script>
<input type="hidden" id="leaveListSize" value="${leaveListSize}">
<c:if test="${error.errorMsg!=null}">
 <input type="hidden" id="errorId" value="${error.errorMsg}">
 <script type="text/javascript">
 

 		$(document).ready(function() {
 		var errorId=document.getElementById('errorId');
		alert(errorId.value);
 		});
 	</script>
 </c:if>
 	

 
 <script type="text/javascript">
	//	$(document).ready(function() {
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
 	function editMethod()
 	 { 
 	 var frank_param = getParam('employeeId');
 	 //	confirm('Are you sure you want to delete?');
	 var delUrl='get_employee?employeeId='+frank_param+'&opr=M';
 	 window.self.location = delUrl;  
	}
 	</script>
 	
 <c:if test="${opr=='V' }">
	<script type="text/javascript">
		$(document).ready(function() {
		$('input').attr('readonly','readonly');
		$('select').attr('disabled','disabled');
		$('textarea').attr('readonly','readonly');
		$('.datepicker').attr('disabled','disabled');
		$('input:radio').attr('disabled',true);
		//$("input:checkbox").attr("disabled", true);
			
		});
</script>
</c:if>


  	

<script type="text/javascript">	
	
 		function check(index) {
 			var name = 'allowDays' + index;
 			var name1='leaveId'+index;
 			if (document.getElementById(name1).checked) {
 				document.getElementById(name).disabled = false;
 				document.getElementById(name).readonly = false;
 			} else {
 				document.getElementById(name).value = "";
 				//document.getElementById(name).disabled = true;
 				document.getElementById(name).readonly = false;
 			} 
 		}
 	</script>
<script type="text/javascript">
			
			$(document).ready(function() {  
				
				$('#addressFlag').change(function () {
					if($('#addressFlag').is(':checked'))
					 {
						$('#permanentAddress').val($('#localAddress').val()).attr('readOnly','readOnly');
				  		$('#permanentCityId').val($('#employeeCity').val()).attr('readOnly','readOnly');
				  		//$('#permanentPhone').val($('#phoneResi').val()).attr('disabled','disabled');
				  		$('#permanentState').val($('#localState').val()).attr('readOnly','readOnly');
				  		$('#permanentZipcode').val($('#employeeLocalZipcode').val()).attr('readOnly','readOnly');
				  		$('#permanentCountry').val($('#localCountry').val()).attr('readOnly','readOnly');
				  	 }
					else
						{
						$('#permanentAddress').val('').attr('disabled',false);
				  		$('#permanentCityId').val('').attr('disabled',false);
				  		//$('#permanentPhone').val('').attr('disabled',false);
				  		$('#permanentState').val('').attr('disabled',false).attr('readOnly',false);
				  		$('#permanentZipcode').val('').attr('disabled',false);
				  		$('#permanentCountry').val('').attr('disabled',false).attr('readOnly',false);
				  		
						}
				
			  });
				
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
				
				$('.fixmyheader-8').fixheadertable({
					caption		: 'My header is fixed !',
					height		:180,
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

// apply the currencyFormat behaviour to elements with 'currency' as their class
$( function() {
    $('.quantity').currencyFormat();
});

$(document).ready(function() {
	$(".quantity").each(function() {

	   if( isNaN( parseFloat( this.value ) ) ) return;
		var v = parseFloat($(this).val());

		v = v.toFixed(2);

		$(this).val(v);
	});
});

$(document).ready(function() {
	function abc(cb) {
		/*  alert('Sai');*/
		$.get('getEmpCityBy_id', { id: $(cb).val()}, function(data) {
		    /*  alert("sai ram"); */
			$("#localState").val(data.areaDTO.regionDTO.stateDTO.stateName);
			$("#localCountry").val(data.areaDTO.regionDTO.stateDTO.zoneDTO.countryDTO.countryName);
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

$(document).ready(function() {
	function abc(cb) {
		/*  alert('Sai');*/
		$.get('getEmpCityBy_id', { id: $(cb).val()}, function(data) {
		    /*  alert("sai ram"); */
			$("#permanentState").val(data.areaDTO.regionDTO.stateDTO.stateName);
			$("#permanentCountry").val(data.areaDTO.regionDTO.stateDTO.zoneDTO.countryDTO.countryName);
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
	

	function toCheckePFDisable(cb) {
		if ($(cb).is(':checked')) {
			$("#pfAcNumber").val('');
			$('#pfAcNumber').hide();
			
			$('#pfEmployerBasicContriAmt').attr('disabled','disabled');
			 $('#pfEmployerSharePer').attr('disabled','disabled');
			 $('#pfEmployeeBasicContriAmt').attr('disabled','disabled');
			 $('#pfEmployeeSharePer').attr('disabled','disabled');
		} else {
			$('#pfAcNumber').show();
			
			 $('#pfEmployerBasicContriAmt').removeAttr("disabled");
			 $('#pfEmployerSharePer').removeAttr("disabled");
			 $('#pfEmployeeBasicContriAmt').removeAttr("disabled");
			 $('#pfEmployeeSharePer').removeAttr("disabled");
		}
	}

	function toCheckePFEnable(cb) {
		if ($(cb).is(':checked')) {
			
			 $('#pfAcNumber').show();
			 $('#pfEmployerBasicContriAmt').removeAttr("disabled");
			 $('#pfEmployerSharePer').removeAttr("disabled");
			 $('#pfEmployeeBasicContriAmt').removeAttr("disabled");
			 $('#pfEmployeeSharePer').removeAttr("disabled");
		} else {
			
			$("#pfAcNumber").val('');
			$('#pfAcNumber').hide();
			
			$('#pfEmployerBasicContriAmt').attr('disabled','disabled');
			 $('#pfEmployerSharePer').attr('disabled','disabled');
			 $('#pfEmployeeBasicContriAmt').attr('disabled','disabled');
			 $('#pfEmployeeSharePer').attr('disabled','disabled');
		}
	}
	
	$(function() {
		$("#pfFlag2").click(function() {
		$('#pfAcNumber').show();
		$('#pfAcNumber').removeAttr("disabled");
		
		 $('#pfEmployerBasicContriAmt').removeAttr("disabled");
		 $('#pfEmployerSharePer').removeAttr("disabled");
		 $('#pfEmployeeBasicContriAmt').removeAttr("disabled");
		 $('#pfEmployeeSharePer').removeAttr("disabled");
		 
		 //Function To check if pf is Yes than all fields are enable
		 toCheckePFEnable($('#pfFlag2'));
		});
		
		$("#pfFlag1").click(function() {
		  $('#pfAcNumber').show();
		  $('#pfAcNumber').attr('disabled','disabled');
		  
		    $('#pfEmployerBasicContriAmt').attr('disabled','disabled');
			 $('#pfEmployerSharePer').attr('disabled','disabled');
			 $('#pfEmployeeBasicContriAmt').attr('disabled','disabled');
			 $('#pfEmployeeSharePer').attr('disabled','disabled');
			
			 //Function To check if pf is no than allfields are disable
			 toCheckePFDisable($('#pfFlag1'));
		});
		
	});
	toCheckePFDisable($('#pfFlag1'));
	toCheckePFEnable($('#pfFlag2'));
	

});

$(document).ready(function() {
	

	
	function toCheckeESIDisable(cb) {
		if ($(cb).is(':checked')) {
			$("#esiAcNumber").val('');
			$('#esiAcNumber').attr('disabled','disabled');
			 
			 $('#esiEmployerSharePer').attr('disabled','disabled');
			 $('#esiEmployeeSharePer').attr('disabled','disabled');
			 $('#esiEmployeeGrossCutoffAmt').attr('disabled','disabled');
			 $('#esiEmployerCutoffAmt').attr('disabled','disabled');
		} else {
			$('#esiAcNumber').removeAttr("disabled");
			 
			 $('#esiEmployerSharePer').removeAttr("disabled");
			 $('#esiEmployeeSharePer').removeAttr("disabled");
			 $('#esiEmployeeGrossCutoffAmt').removeAttr("disabled");
			 $('#esiEmployerCutoffAmt').removeAttr("disabled");
		}
	}

	function toCheckeESIEnable(cb) {
		if ($(cb).is(':checked')) {
			
			$('#esiAcNumber').removeAttr("disabled");
			 
			 $('#esiEmployerSharePer').removeAttr("disabled");
			 $('#esiEmployeeSharePer').removeAttr("disabled");
			 $('#esiEmployeeGrossCutoffAmt').removeAttr("disabled");
			 $('#esiEmployerCutoffAmt').removeAttr("disabled");
		} else {
			
			$('#esiAcNumber').attr('disabled','disabled');
			 
			 $('#esiEmployerSharePer').attr('disabled','disabled');
			 $('#esiEmployeeSharePer').attr('disabled','disabled');
			 $('#esiEmployeeGrossCutoffAmt').attr('disabled','disabled');
			 $('#esiEmployerCutoffAmt').attr('disabled','disabled');
		}
	}
	
	
	
	$(function() {
		
		$("#esiFlag2").click(function() {
			$('#esiAcNumber').show();
			 $('#esiAcNumber').removeAttr("disabled");
			 
			 $('#esiEmployerSharePer').removeAttr("disabled");
			 $('#esiEmployeeSharePer').removeAttr("disabled");
			 $('#esiEmployeeGrossCutoffAmt').removeAttr("disabled");
			 $('#esiEmployerCutoffAmt').removeAttr("disabled");
			  toCheckeESIEnable($("#esiFlag2"));
		});
		  $("#esiFlag1").click(function() {
			$('#esiAcNumber').show();
			$('#esiAcNumber').attr('disabled','disabled');
			
			$('#esiEmployerSharePer').attr('disabled','disabled');
			 $('#esiEmployeeSharePer').attr('disabled','disabled');
			 $('#esiEmployeeGrossCutoffAmt').attr('disabled','disabled');
			 $('#esiEmployerCutoffAmt').attr('disabled','disabled');
			 toCheckeESIDisable($("#esiFlag1"));
		});
		
	});
	
	 toCheckeESIDisable($("#esiFlag1"));
	 toCheckeESIEnable($("#esiFlag2"));
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
		
		$('#overtimeRate').show();
		$('#overtimeRate').attr('disabled','disabled');
		$("#overtimeFlag2").click(function() {
			$('#overtimeRate').show();
			 $('#overtimeRate').removeAttr("disabled");
		});
		
		$("#overtimeFlag1").click(function() {
			$('#overtimeRate').show();
			$('#overtimeRate').attr('disabled','disabled');
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
	function checkEdit()
	{
	alert('Login User Not Permit to Edit Record !!!!!!');
	}
	
	
	
    //function refresh()  
     $(document).ready(function() {
    
    $(".refresh").change(function()
    {  console.log('refresh');
    	 var postData = $('#formID3').serializeArray();
    	 var formURL = $('#formID3').attr("action");
    	  $.ajax(
    	 {
    	      url : 'ajax_editEmployee_cal',
    	      type: "POST",
    	      data : postData,
    	      success:function(data, textStatus, jqXHR) 
    	       {
    	    	var eAmount=data.employeeForm.employeeDTO.eamount;
    	    	var netAmount=data.employeeForm.employeeDTO.totalAmount;
    	    	if(eAmount<15000 && eAmount>6500)
    	    	 {
    	    	 }
    	    	$("#eamount").val(parseFloat(data.employeeForm.employeeDTO.eamount).toFixed(2));
    	    	console.log('E AMT'+data.employeeForm.employeeDTO.eamount);
    	    	$("#totalAmount").val(parseFloat(data.employeeForm.employeeDTO.totalAmount).toFixed(2));
    	    	console.log('Total AMT'+data.employeeForm.employeeDTO.totalAmount);
    	     	$("#damount").val(parseFloat(data.employeeForm.employeeDTO.damount).toFixed(2));
    	     	console.log('D AMT'+data.employeeForm.employeeDTO.damount);
    	      	for (var i = 0; i< data.employeeForm.employeeDTO.employeeSalaryDetDTOList.length; i++) {
    	   		$("#amount"+i).val(parseFloat(data.employeeForm.employeeDTO.employeeSalaryDetDTOList[i].amount).toFixed(2));
    				 }
    		        	 
    	      	for (var i = 0; i< data.employeeForm.employeeDTO.employeeSalaryDetDTOListDe.length; i++) {
    			 //console.log(data.salaryHeadListD[i].amount);
    	    		$("#amountS"+i).val(parseFloat(data.employeeForm.employeeDTO.employeeSalaryDetDTOListDe[i].amount).toFixed(2));
    				 }
    	      		},
    		    });
    					  
    		});
    
    });
    
    
    function doAjaxPost() {
        // get the form values
        var code = $('#employeeCode').val();
     

        $.ajax({
        type: "POST",
        url: "checkEmployeeCode",
        data: "code=" + code ,
        success: function(response){
        // we have the response
        if(response!=""){
        $('#info').html(response);
        $('#employeeCode').val('');
        }else{
        	 $('#info').html("");	
        }
        },
        error: function(e){
        alert('Error: ' + e);
        }
        });
        }
    </script>

<script type="text/javascript">
	//anonymous self invoking function to avoid conflicting with other JavaScript
	(function($) {
		//function is called when the page is fully loaded
		$(document).ready(function() {
			
			var isChecked = $('#maritalStatus1:checked').val()?true:false;
			if(isChecked==true){
				$("#childName1").attr("readOnly", true);
				$("#childName2").attr("readOnly", true);
			}
	
			//the page is loaded so attach the time picker to an input field
			$(".myTimePicker").timepicker({});
		});
	})(jQuery);

	$(document).ready(function() {
		$(".quantity").keypress(function(e) {
			//if the letter is not digit then display error and don't type anything
			if (e.which != 8 && e.which != 46 && e.which != 0
									&& (e.which<48 || e.which>57)){
				//display error message
				$(".errmsg").html("Digits Only").show().fadeOut("slow");
				return false;
			}
		});
	});
	
	$(document).ready(function() {
		$(".quantity1").keypress(function(e) {
			//if the letter is not digit then display error and don't type anything
			if (e.which != 8  && e.which != 0 && (e.which<48 || e.which>57)){
				//display error message
				$(".errmsg").html("Digits Only").show().fadeOut("slow");
				return false;
			}
		});
	});
</script>


<script type="text/javascript">
$(document).ready(function() {
	

	$("button").button();

	$(".datepicker").datepicker({
		  changeMonth: true,
        changeYear: true,
		  yearRange: '-99:+10',
		dateFormat : 'dd-M-yy',
		maxDate : new Date()
	});

	$(".datepicker1").datepicker({
		  changeMonth: true,
        changeYear: true,
		  yearRange: '-99:+10',
		dateFormat : 'dd-M-yy',
		maxDate : new Date()
	});
	
	
	
	
	$('#weekOff2').change(function() {
		
		if ($('#weekOff2').val() == $('#weekOff1').val()) {
			alert('Week Off2 and Week Off1 value can not be same Please select other');
			$('#weekOff2').val('');
	     }
	});
	$('#weekOff1').change(function() {
		
		if ($('#weekOff2').val() == $('#weekOff1').val()) {
			alert('Week Off1 and Week Off2 value can not be same Please select other');
			$('#weekOff1').val('');
	     }
	});
	
	
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

#dlg-buttons {
	text-align: center;
	margin-top: 11px;
	float: left;
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

select {
	width: 84% !important;
	height: 20px;
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

table {
	width: 100%;
}

input {
	width:83%;
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

.ne{
	float: right;
	width: 151px;
	margin-top: 9px;
}

a.disabled:link,a.disabled:visited {
	color: grey;
}
</style>

<script type="text/javascript">
function doAjaxPost1() {
	// get the form values
	var code = $('#depeartmetnType').val();
	$.ajax({
		type : "POST",
		url : "getSubdepartment",
		data : "code=" + code,
		success : function(response) {
			// we have the response
			 

 
			/* 	$('#subDepartments').html(response); */
				
				   $('#subDepartments').find('option').remove();

			        $.each(response, function () {
			            $('#subDepartments').append(
			                $('<option></option>').val(this.mastersId).html(this.name)
			            );
			        });
			
		},
		error : function(e) {
		}
	});
}


</script>


<script type="text/javascript">
	$(function() {
		$("#tabs").tabs();
	});
</script>

<c:if test="${step==2}">
  <script>
	$(document).ready(function() {				
			$("#tabs" ).tabs("select", 1);	
			
	});
</script>
</c:if>
<c:if test="${step==3}">
  <script>
	$(document).ready(function() {				
			$("#tabs" ).tabs("select", 2);	
			
	});
</script>
</c:if>

<c:if test="${step==4}">
  <script>
	$(document).ready(function() {	
		
			$("#tabs" ).tabs("select", 3);	
			
	});
</script>
</c:if>



<div id="tabs"
	style="width: 964px; float:left; height: auto;  none; border: 1px solid #4E8CCF; padding: 0px; font-family: Arial;">
	<ul style="background-color: #e0ecff; padding-bottom: 1px">
	<c:choose>
		<c:when test="${opr=='V'}">
		<li><a href="#tabs-1">Employee Master</a></li>
		
		<li><a href="#tabs-2" id="abc">Personal info</a></li>
		<li><a href="#tabs-3">Business info</a></li>
		<li><a href="#tabs-4">Salary info</a></li> 
		</c:when>
		</c:choose>
		<c:choose>
		<c:when test="${opr!='V'}">
		 <li><a href="#tabs-1">Employee Master</a></li>
		<c:choose>
			<c:when test="${step2=='2'}">
				<li><a href="#tabs-2" id="abc">Personal info</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="#tabs-2" onclick="this.removeAttribute('href');this.className='disabled'">Personal
						info</a></li>
			</c:otherwise>
		</c:choose>
		<!-- 	<li><a href="#tabs-2" id="abc">Personal info</a></li>
	 -->
		
		<c:choose>
			<c:when test="${step3=='3'}">
				<li><a href="#tabs-3">Business info</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="#tabs-3"
					onclick="this.removeAttribute('href');this.className='disabled'">Business
						info</a></li>
			</c:otherwise>
		</c:choose>
		<!--   <li><a href="#tabs-3">Business info</a></li> -->
		<c:choose>
			<c:when test="${step4=='4'}">
				<li><a href="#tabs-4">Salary info</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="#tabs-4"
					onclick="this.removeAttribute('href');this.className='disabled'">Salary
						info</a></li>
			</c:otherwise>
		</c:choose>
</c:when>
		</c:choose>
	</ul>

	<%-- <form:form name="formIDTest" id="formID3" action="ajax_editEmployee_form#tabs-4" method="post"
		modelAttribute="employeeForm">  --%>
	<form:form name="input" id="formID" action="edit_Employee_form2#tabs-2" method="post"
		modelAttribute="employeeForm">	
		
<form:errors path="*" cssClass="errorblock"  element="div"  />
		<div id="tabs-1" style="background-color: #FF0000; font-size: 11px">
		
		
			<div align="left" class="bkgColor" style="width:963px;" >
				<h2>Employee Details</h2>
				<div id="info" style="color: red;"></div>
				<table width="997" height="220"
					style="margin-left: 9px; float: left; font-family: Arial;"
					border="0" align="right">
					<tbody>
						<tr>
							<td width="105" height="30">Employee First Name <span
								style="color: #FF0000">*</span>
						</td>
							<td width="137"><form:input type="text" data-maxsize="35"
									class="validate[required] text-input"
									path="employeeDTO.employeeName" id="employeeName" size="18" />
							</td>
							<td width="105">Last Name <span style="color: #FF0000">*</span>
							</td>
							<td width="137"><form:input type="text" data-maxsize="35"
									class="validate[required] text-input"
									path="employeeDTO.employeeLastName" id="employeeLastName"
									size="18" />
							</td>
							<td width="105" align="left">Employee Code <span
								style="color: #FF0000">*</span>
							</td>
							<td width="137"><form:input type="text" data-maxsize="16"
									class="validate[required] text-input"
									path="employeeDTO.employeeCode" id="employeeCode" style="width:83%" size="18"
									readonly="true" />
							</td>
						</tr>
						<tr>
							<td height="30">Gross Annual Salary</td>
							<td><form:input type="text" path="employeeDTO.empSalary"
									class="quantity validate[custom[number]]" id="empSalary" size="18" /> <span
								class="errmsg"></span>
							</td>
							<td>Nationality</td>
							<td><form:input type="text" path="employeeDTO.nationality"
									id="nationality" size="18" />
							</td>
							<td align="left">Active Status<span style="color: #FF0000">*</span>
							</td>
							<td><div
									style="border: solid 1px;    border-radius: 3px 3px 3px 3px; height: 20px; width: 83%; border-color: #7f9db9; background-color: #FFF;">
									  <span style="    float: left;    margin-top:2px;    padding-left: 12px;"> Yes</span>
									<form:radiobutton class="validate[required] radio"
										style="width:20px; float: left;" path="employeeDTO.activeStatus" id="activeStatus" value="1" />
									<span style="    float: left;    margin-top: 2px;  "> No</span>
									<form:radiobutton style="width:20px;"
										class="validate[required] radio"
										path="employeeDTO.activeStatus" id="activeStatus" value="0" />
								</div>
							</td>
						</tr>
						<tr>
							<td height="30">Qualification <span style="color: #FF0000">*</span>
							</td>
							<td><form:select path="employeeDTO.qualificationId"
									items="${qualificationes}" itemLabel="name"
									itemValue="mastersId" id="qualificationId"
									class="validate[required]" style="width: 250px; height: 20px;">

								</form:select></td>
							<td>Experience in year (At Joining)</td>
							<td><form:input type="text" data-maxsize="35"
									path="employeeDTO.experience" size="18" id="experience"
									class="quantity validate[custom[number]]" /><span class="errmsg"></span>
							</td>
							<td align="left">Join Date<span style="color: #FF0000">*</span>
							</td>
							<td><form:input type="text" path="employeeDTO.joinDate" style="width:83%" readonly="true"
									class="datepicker1 validate[required] text-input"
									id="joinDate" size="18" /> <!-- <input type="text" name="joinDate" size="18" id="joinDate" class="datepicker validate[required] text-input"/>
             --></td>
						</tr>


						<tr>
							<td height="28">Employee Type <span style="color: #FF0000">*</span>
							</td>

							<!--   <select style="width:113px; height:21px" class="validate[required] text-input" name="employeeType" 
            id="employeeType">
              <option></option>
            </select> -->
							<td><form:select path="employeeDTO.employeeType"
									items="${employeeType}" itemLabel="name" itemValue="mastersId"
									id="employeeType" class="validate[required]"
									style="width: 250px; height: 20px;">

								</form:select></td>


							<td>Department <span style="color: #FF0000">*</span>
							</td>
							<td>
								<!-- <select style="width:113px; height:21px" class="validate[required] text-input" name="department" id="department">
              <option></option>
            </select> --> 
								<form:select
									path="employeeDTO.masterEntityDepartment"
									items="${Departmentes}" itemLabel="name" itemValue="mastersId"
									id="depeartmetnType" class="validate[required]"
									style="width: 250px; height: 20px; " onchange="doAjaxPost1()">

								</form:select>
								</td>
							<td align="left">Sub Department 
							</td>
							<td>
	  <form:select
									path="employeeDTO.masterSubEntityDepartment"
									items="${subDepartment}" itemLabel="name" itemValue="mastersId"
									id="subDepartments" 
									style="width: 250px; height: 20px;">

								</form:select></td>
						</tr>

						<tr>
							<td height="30">Designation <span style="color: #FF0000">*</span>
							</td>
							<td>
				
				 <form:select path="employeeDTO.designation"
									items="${designation}" itemLabel="name" itemValue="mastersId"
									id="designation" class="validate[required]"
									style="width: 250px; height: 20px;">

								</form:select></td>
							<td>Shift <span style="color: #FF0000">*</span>
							</td>
							<td>
								<!--  <select style="width:113px; height:21px" class="validate[required] text-input" name="shift" id="shift">
                  <option></option>
                </select> --> <form:select
									path="employeeDTO.masterEntitShift" items="${shift}"
									itemLabel="name" itemValue="mastersId" id="masterEntitShift"
									class="validate[required]" style="width: 250px; height: 20px;">

								</form:select></td>
							<td align="left">Special Skills</td>
							<td><form:input type="text" path="employeeDTO.specialSkills"
									data-maxsize="35" id="specialSkills" size="18" />
							</td>
						</tr>
						<tr>
							<td height="31">Separation Date</td>
							<td><form:input type="text"
									path="employeeDTO.separationDate" style="width:83%" readonly="true" class="datepicker"
									id="separationDate" size="18" />
							</td>
							<td>Separation Reason 
							</td>
							<td><form:select style="width: 113px; height: 21px"
									path="employeeDTO.separationReason"
									 id="separationReason">
									<form:option value=""></form:option>
									<form:option value="resign">Resign</form:option>
									<form:option value="death">Death</form:option>
									<form:option value="diseased">Diseased</form:option>
									<form:option value="others">Others</form:option>
								</form:select>
							</td>
							<td align="left">Grade <span style="color: #FF0000">*</span>
							</td>
							<td>
								<!--      <select style="width:113px; height:21px" class="validate[required] text-input" name="grade" id="grade">
                  <option></option>
                </select>  --> <form:select
									path="employeeDTO.masterEntityGrade" items="${grade}"
									itemLabel="name" itemValue="mastersId" id="masterEntityGrade"
									class="validate[required]" style="width: 250px; height: 20px;">

								</form:select></td>
						</tr>
						<tr>
							<td height="25">Gender</td>
							<td><div
									style="border: solid 1px; height: 20px; width: 83%; border-color: #7f9db9; background-color: #FFF;">
									<span style="    float: left;    margin-top:2px;    padding-left: 12px;"> Male</span>
									<form:radiobutton style="width:20px;  float: left;  " path="employeeDTO.gender"
										id="gender" value="male" />
									<span style="    float: left;    margin-top: 2px;  "> Female</span>
									<form:radiobutton style="width:20px;" path="employeeDTO.gender"
										id="gender" value="female" />
								</div>
							</td>
							<td>Marital Status</td>
							<td><div
									style="border: solid 1px; height: 20px;    border-radius: 3px 3px 3px 3px; width: 83%; border-color: #7f9db9; background-color: #FFF;">
									<span style="    float: left;    margin-top:2px;    padding-left: 12px;"> Yes</span>
									<form:radiobutton style="width:20px; float: left;"
										path="employeeDTO.maritalStatus" id="maritalStatus" value="1" />
									<span style="    float: left;    margin-top: 2px;  "> No</span>
									<form:radiobutton style="width:20px;"
										path="employeeDTO.maritalStatus" id="maritalStatus1" value="0" />
								</div>
							</td>
							<td align="left">Email ID</td>
							<td><form:input type="text" path="employeeDTO.emailId"
								class="validate[custom[email]] text-input" onkeyup="email(this)" onblur="email(this)"	id="emailId" size="18" />
								<span id="semailId" class="semailId"></span>
							</td>
						</tr>
					</tbody>
				</table>
				<div id="dlg-buttons">
		<div class="btn">
		<div class="savbtn">
		 <c:choose>
      	  <c:when test="${opr=='V'}">
      	  <c:forEach items="${roleAndRightsDTOList}" var="roleAndRights">
		   <c:if test="${roleAndRights.menuId==sessionScope.menuId}">
    	  	 <c:if test="${roleAndRights.editFlag=='true'}">
		  	 <input class="edit_btn"  type="button" onclick="editMethod();" value=""/>
    	      <a href="show_Employee_list"  class="cancelbtn" ></a>  
		     </c:if>
		      <c:if test="${roleAndRights.editFlag=='false'}">
		      <input class="edit_btn"  type="button" onclick="checkEdit();" value=""/>
    	      <a href="show_Employee_list"  class="cancelbtn" ></a>  
		      </c:if>
		    
          </c:if>
          </c:forEach>	
      			   		
    	    </c:when>

      <c:otherwise>
      
      <%-- 	<c:url value="edit_Employee_form2#tabs-2" var="remove_url" >
			</c:url>
			<a href="${remove_url}" class="nextbtn"></a> --%>
			
			<input class="nextbtn" type="submit" value="" />
			<div class="cancelbtn">
			<a href="show_Employee_list" class="cancelbtn"	iconCls="icon-cancel">Cancel</a>
			</div>
	     </c:otherwise>
	</c:choose>
	</div>
	</div>
	</div>
	</div>
	</div>
</form:form>
<form:form name="input" id="formID1" action="edit_Employee_form3#tabs-3" method="post"
		modelAttribute="employeeForm">	
		<div id="tabs-2" style="background-color: #FF0000; font-size: 11px">
			<div align="left" class="bkgColor"  style="width:963px;" >
				<h2>Personal info</h2>
				<table width="997" height="274"
					style="margin-left: 9px; float: left" border="0" align="right">
					<tbody>

						<tr>
							<td width="105" height="30">Local Address<span style="color: #FF0000">*</span></td>
							<td colspan="3"><form:input type="text"
									path="employeeDTO.employeeAddress" datmaxsize="35"
									style="width:94%" class="validate[required] text-input" id="localAddress" size="18" /></td>
							<td width="105" align="left">Local City<span style="color: #FF0000"> *</span></td>
							<td width="137">
			
			 <form:select
									path="employeeDTO.employeeCity.cityId" items="${cityList}"
									itemLabel="cityName" itemValue="cityId" id="employeeCity"
									class="validate[required]" style="width: 250px; height: 20px;">

								</form:select>
							</td>
						</tr>
						<tr>
							<td height="30">Local State</td>
							<td width="137"><input type="text" name="localState" style="width:83%"
								datmaxsize="35" readonly="readonly" id="localState" size="18" />
							</td>
							<td width="105">Local Country</td>
							<td width="137"><input type="text" name="localCountry"
								readonly="readonly" style="width:83%" datmaxsize="35" id="localCountry" size="18" />
							</td>
							<td align="left">Local ZIP/PIN Code</td>
							<td><form:input type="text"
									path="employeeDTO.employeeLocalZipcode" maxlength="6" class="quantity1 validate[required] text-input"
									id="employeeLocalZipcode" style="text-align:left" size="18" /><span
								class="errmsg"></span></td>
						</tr>
						<tr>
							<td height="30">Phone (O)</td>
							<td><form:input  onkeypress="return check(event)" class="validate[custom[number]]"
									type="text" path="employeeDTO.phoneOffice" maxlength="14"
									id="phoneOffice" size="18" />
									<span id="phoneOffice" class="phoneOffice"></span>
									</td>
							<td>Phone (R)</td>
							<td><form:input  onkeypress="return check(event)" class="validate[custom[number]]"
									type="text" maxlength="14" path="employeeDTO.phoneResi"
									id="phoneResi" size="18" />
									<span id="phoneOffice" class="phoneOffice"></span>
									</td>
							<td align="left">Mobile<span style="color: #FF0000"></span>
							</td>
							<td><form:input class="validate[custom[number]]" type="text" maxlength="10"
									path="employeeDTO.contactMobile" id="contactMobile" size="18" />
							<span id="scontactMobile" class="scontactMobile"></span>
							</td>
						</tr>
						<tr>
							<td style="float: right;">
								<input type="checkbox"  style="background-color:#FF0000;width: 30px" id="addressFlag" value="1"/>
							   </td>
							 <td colspan="3"> 
							 <span style="width:94%;color: blue;">If permanent address same as local address.</span></td>  
						</tr>

						<tr>
							<td height="28">Permanent Address<span style="color: #FF0000">*</span></td>
							<td colspan="3"><form:input type="text" class="validate[required] text-input" 
									path="employeeDTO.permanentAddress" datmaxsize="35" 
									style="width:94%" id="permanentAddress" size="18" /></td>
									
							<td align="left">Permanent City <span style="color: #FF0000">*</span>
							</td>
							<td><form:select path="employeeDTO.permanentCityId"
									items="${cityList}" itemLabel="cityName" itemValue="cityId"
									id="permanentCityId" class="validate[required]"
									style="width: 250px; height: 20px; ">

								</form:select></td>
						</tr>

						<tr>
							<td height="30">Permanent State</td>
							<td><input type="text" name="permanentState" datmaxsize="35"
								readonly="readonly" style="width:83%" id="permanentState" size="18" /></td>
							<td>Permanent Country</td>
							<td><input type="text" style="width:83%" name="permanentCountry"
								datmaxsize="35" readonly="readonly" id="permanentCountry"
								size="18" /></td>
							<td align="left">Permanent ZIP/PIN Code</td>
							<td><form:input class="quantity1 validate[required]" type="text"
									path="employeeDTO.permanentZipcode" id="permanentZipcode"
									size="6" maxlength="6" style="text-align:left" /><span class="errmsg"></span></td>
						</tr>
						<tr>
							<td height="31">Permanent Phone (R)</td>
							<td><form:input type="text" class="validate[custom[number]]"
									path="employeeDTO.permanentPhone" datmaxsize="35"
									id="permanentPhone" size="10" /></td>
							<td>References Name</td>
							<td><form:input type="text"
									path="employeeDTO.referencesName" datmaxsize="35"
									id="referencesName" size="18" /></td>
							<td align="left">Ref. Phone</td>
							<td><form:input type="text" path="employeeDTO.refPhone"
								class="validate[custom[number]]"	id="refPhone" size="18" /></td>
						</tr>
						<tr>
							<td height="25">Birth Date</td>
							<td><form:input type="text" path="employeeDTO.birthDate" 
									size="18" id="birthDate" style="width:83%" readonly="true" class="datepicker1" /></td>
							<td>Anni. Date</td>
							<td><form:input type="text"
									path="employeeDTO.annivDate" style="width:83%" size="18" id="anniDate"
									class="datepicker" readonly="true" /></td>
							<td align="left">Blood Group</td>
							<td><form:select path="employeeDTO.bloodGroup">
							<form:option value=""></form:option>
							<form:option value="O+">O+</form:option>
							<form:option value="O-">O-</form:option>
							<form:option value="A+">A+</form:option>
							<form:option value="A-">A-</form:option>
							<form:option value="B+">B+</form:option>
							<form:option value="B-">B-</form:option>
							<form:option value="AB+">AB+</form:option>
							<form:option value="AB-">AB-</form:option>
							</form:select></td>
						</tr>
						<tr>
							<td height="25">Cast <span style="color: #FF0000">*</span></td>
							<td><form:select path="employeeDTO.masterEntitCast"
									items="${cast}" itemLabel="name" itemValue="mastersId"
									id="designation" class="validate[required]"
									style="width: 250px; height: 20px;">

								</form:select>
							</td>
							<td>Religion <span style="color: #FF0000">*</span></td>
							<td><form:select path="employeeDTO.masterEntitReligion"
									items="${religion}" itemLabel="name" itemValue="mastersId"
									id="designation" class="validate[required]"
									style="width: 250px; height: 20px;">

								</form:select>
							</td>
							<td align="left">Language <span style="color: #FF0000">*</span>
							</td>
							<td><form:select path="employeeDTO.masterEntitLanguage"
									items="${Language}" itemLabel="name" itemValue="mastersId"
									id="designation" class="validate[required]"
									style="width: 250px; height: 20px;">

								</form:select>
							</td>
						</tr>
						<tr>
							<td height="25">Spouse Name</td>
							<td><form:input type="text" path="employeeDTO.spouseName"
									datmaxsize="35" id="spouseName" size="18" /></td>
							<td>Child Name (1)</td>
							<td><form:input path="employeeDTO.childName1"
									datmaxsize="35" id="childName1" size="18" /></td>
							<td align="left">Child Name (2)</td>
							<td><form:input path="employeeDTO.childName2"
									datmaxsize="35" id="childName2" size="18" /></td>
						</tr>
					</tbody>
				</table>
				<div class="btn">
		<div class="savbtn">
		 <c:choose>
      	  <c:when test="${opr=='V'}">
      	  <c:forEach items="${roleAndRightsDTOList}" var="roleAndRights">
		   <c:if test="${roleAndRights.menuId==sessionScope.menuId}">
    	  	 <c:if test="${roleAndRights.editFlag=='true'}">
		  	 <input class="edit_btn"  type="button" onclick="editMethod();" value=""/>
    	      <a href="show_Employee_list"  class="cancelbtn" ></a>  
		     </c:if>
		      <c:if test="${roleAndRights.editFlag=='false'}">
		      <input class="edit_btn"  type="button" onclick="checkEdit();" value=""/>
    	      <a href="show_Employee_list"  class="cancelbtn" ></a>  
		      </c:if>
		    
          </c:if>
          </c:forEach>	    		
    	    </c:when>

      <c:otherwise>
      	<%-- <c:url value="edit_Employee_form3#tabs-3" var="remove_url" >
			</c:url>
			<a href="${remove_url}" class="nextbtn"></a> --%>
			<input class="nextbtn" type="submit" value="" />
			<div class="cancelbtn">
			<a href="show_Employee_list" class="cancelbtn"	iconCls="icon-cancel">Cancel</a>
			</div>
	     </c:otherwise>
	</c:choose>
	</div>
	</div>


			</div>
		</div>

	</form:form>
	<form:form name="input" id="formID2" action="edit_Employee_form4#tabs-4" method="post"
		modelAttribute="employeeForm">	
		<div id="tabs-3" style="background-color: #FF0000; font-size: 11px">
			<div align="left" class="bkgColor"  style="width:963px;" >
				<h2>Business info</h2>
				<table width="939" height="311"
					style="margin-left: 9px; float: left" border="0" align="right">
					<tbody>
						<tr>
							<td width="126" height="30">Bank A/c. No. (1)<span style="color: #FF0000"> *</span></td>
							<td width="137"><form:input type="text" class="validate[required] text-input"
									path="employeeDTO.bankAcNumber1" id="bankAcNumber1" size="18" />
							</td>
							<td width="126">Bank A/c. No. (2)</td>
							<td width="137"><form:input type="text" 
									path="employeeDTO.bankAcNumber2" id="bankAcNumber2" size="18" />
							</td>
							<td width="126" height="30">Id Proof<span style="color: #FF0000"> *</span></td>
							<td width="137"><form:select class="validate[required]"							 
									path="employeeDTO.empIdProofEntity" id="empIdProofEntity">
									<form:option value="voterId">Voter Id</form:option>
									<form:option value="drivingLince">Driving Licence</form:option>
									<form:option value="passport">Passport</form:option>
									<form:option value="panCard">PAN Card</form:option>
									<form:option value="adharCard">Aadhar Card</form:option>
								</form:select>
							</td>
						</tr>
						<tr>
							<td height="30">Bank name (1)<span style="color: #FF0000"> *</span></td>
							<td><form:input type="text" path="employeeDTO.bankName1"
								 class="validate[required] text-input"	id="bankName1" size="18" /></td>
							<td>Bank name (2)</td>
							<td><form:input type="text" path="employeeDTO.bankName2"
									id="bankName2" size="18" /></td>
							<td>Id Proof Number<span style="color: #FF0000"> *</span></td>
							<td><form:input type="text" path="employeeDTO.idProofNumber"
								class="validate[required] text-input" size="25" id="idProofNumber" /></td>
						</tr>
						<tr>
							<td height="31">Branch name (1)<span style="color: #FF0000"> *</span></td>
							<td><form:input type="text" path="employeeDTO.branchName"
								class="validate[required] text-input" id="branchName" size="18" /></td>
							<td>Branch name(2)</td>
							<td><form:input type="text" path="employeeDTO.branchName2"
									size="18" id="branchName2" class="website" /></td>
							<td align="left">PAN<span style="color: #FF0000"></span></td>
							<td><form:input type="text" path="employeeDTO.panNumber"
								 onclick="check()"	id="panNumber" size="25" /></td>
						</tr>
						<tr>
							<td height="31">Bank (1) IFSC Code<span style="color: #FF0000"> *</span></td>
							<td><form:input type="text" path="employeeDTO.bankIfscCode1"
									class="validate[required] text-input" id="bankIfscCode1" size="18" /></td>
							<td>Bank (2) IFSC Code</td>
							<td><form:input type="text" path="employeeDTO.bankIfscCode2"
									size="18" id="bankIfscCode2" class="website" /></td>
							<td align="left">Product Manager Flag</td>
							<td><div
									style="border: solid 1px; height: 20px; width: 84%;     border-radius: 3px 3px 3px 3px; border-color: #7f9db9; background-color: #FFF;">
									<span style="    float: left;    margin-top:2px;    padding-left: 12px;"> Yes</span>
									<form:radiobutton style="width:20px; float: left;" path="employeeDTO.pmFlag"
										id="pmFlag" value="1" />
									<span style="    float: left;    margin-top: 2px;  "> No</span>
									<form:radiobutton style="width:20px;" path="employeeDTO.pmFlag"
										id="pmFlag" value="0" />
								</div></td>
						</tr>
						 <tr>
							<td height="31">Week off 1</td>
							<td><form:select style="width:113px; height:21px"
									path="employeeDTO.weekOff1" id="weekOff1">
									<form:option value=""></form:option>
									<form:option value="sunday">Sunday</form:option>
									<form:option value="monday">Monday</form:option>
									<form:option value="tuesday">Tuesday</form:option>
									<form:option value="wednesday">Wednesday</form:option>
									<form:option value="thursday">Thursday</form:option>
									<form:option value="friday">Friday</form:option>
									<form:option value="saturday">Saturday</form:option>
								</form:select>
							</td>
							<td>Week off 2</td>
							<td><form:select style="width:113px; height:21px"
									path="employeeDTO.weekOff2" id="weekOff2">
									<form:option value=""></form:option>
									<form:option value="sunday">Sunday</form:option>
									<form:option value="monday">Monday</form:option>
									<form:option value="tuesday">Tuesday</form:option>
									<form:option value="wednesday">Wednesday</form:option>
									<form:option value="thursday">Thursday</form:option>
									<form:option value="friday">Friday</form:option>
									<form:option value="saturday">Saturday</form:option>
								</form:select></td>
						  <td height="25">Increment Date<span style="color: #FF0000">*</span></td>
							<td><form:input type="text" style="width:82%" path="employeeDTO.incrementDate"
									size="18" id="incrementDate" class="validate[required] text-input datepicker datepicker" readonly="true" />
							</td> 
							
   		</tr>
   		<div>
		<span style="margin-left: 12px;" class="errmsg"></span>
						</div>

						<tr>
							<td align="left">P. F. Flag<span style="color: #FF0000">*</span>
							</td>
							<td><div style="border: solid 1px; height: 20px; width: 84%;    border-radius: 3px 3px 3px 3px; border-color: #7f9db9; background-color: #FFF;">
									<span style=" float: left; margin-top:2px; padding-left:12px;"> Yes</span>
									<form:radiobutton style="width:20px;  float: left; " path="employeeDTO.pfFlag"
										class="validate[required] radio" value="1" id="pfFlag2" />
								<span style=" float: left; margin-top: 2px; "> No</span>
									<form:radiobutton style="width:20px;" path="employeeDTO.pfFlag"
										class="validate[required] radio" value="0" id="pfFlag1" />
								</div></td>
							<td height="33" align="left">P. F. A/c. No.</td>
							<td><form:input type="text" path="employeeDTO.pfAcNumber" datmaxsize="35" id="pfAcNumber" size="18"  /></td>
							<td align="left">Applicable Leaves<span style="color: #FF0000">*</span></td>
							<td rowspan="7">
								
								<div class="scrolltable"
									Style="height: 144px; width: 82%; background: none;">
									<table width="129" style="width: 129px;" cellpadding="0" cellspacing="0">
										<tr>

											<th
												style="border-bottom:1px solid #99BBE8; color:#fff; height:19px; background-color: #4e8ccf; padding-left: 3px;" width="587"> Leave</th>
											<th style="border-bottom:1px solid #99BBE8;border-left:1px solid #99BBE8; color:#fff; background-color: #4e8ccf; " width="67">Days</th>
										</tr>

										<c:forEach items="${leaveList}" var="cat" varStatus="leave">
											<tr>
												
													<th style="border-bottom:1px solid #99BBE8; " 
													width="316"><div	style="float: right; width:72px; font-size:10px; font-weight: normal; text-align: left;">
														<c:out value="${cat.leaveName}" />
													</div> <form:checkbox path="employeeDTO.employeeLeavesDTOList[${leave.index}].leaveId"
														class="validate[required]" style="float:left; width:21px;" id="leaveId${leave.index}"
														value="${cat.leaveId}" onclick="check(${leave.index})" /></th>
												<th style="border-bottom:1px solid #99BBE8;border-left:1px solid #99BBE8;" width="67">
												<form:input type="text" path="employeeDTO.employeeLeavesDTOList[${leave.index}].allowDays"
														style="width:87%" size="4" class="quantity1" maxlength="4"  id="allowDays${leave.index}"/></th>
											</tr>
										</c:forEach>
									</table>
								</div>
							</td>
						</tr>
						
						
						<tr>
							<td align="left">P. F.Employer Basic Contri Amt<span style="color: #FF0000"></span>
							</td>
							<td>
							<form:input type="text" path="employeeDTO.pfEmployerBasicContriAmt" class="quantity validate[custom[number]]"
									datmaxsize="35" id="pfEmployerBasicContriAmt" size="18" />
								</td>
							<td height="33" align="left">P. F. Employer Share Per<span
								style="color: #FF0000"></span></td>
							<td><form:input type="text" path="employeeDTO.pfEmployerSharePer" class="quantity validate[custom[number]]"
									datmaxsize="35" id="pfEmployerSharePer" size="18" />
							</td>

							<td align="left">&nbsp;</td>
						</tr>
						
						
							<tr>
							<td align="left">P. F.Employee Basic Contri Amt<span style="color: #FF0000"></span>
							</td>
							<td>
									 <form:input type="text"  path="employeeDTO.pfEmployeeBasicContriAmt" class="quantity validate[custom[number]]"
									datmaxsize="35" id="pfEmployeeBasicContriAmt" size="18" />
								</td>
							<td height="33" align="left">P. F. Employee Share Per<span
								style="color: #FF0000"></span></td>
							<td><form:input type="text"  path="employeeDTO.pfEmployeeSharePer" class="quantity validate[custom[number]]"
									datmaxsize="35" id="pfEmployeeSharePer" size="18" />
							</td>

							<td align="left">&nbsp;</td>
						</tr>
						
						
						
						
						
						<tr>
							<td height="32" align="left">E.S.I. Flag<span style="color: #FF0000"> *</span></td>
							<td><div
									style="border: solid 1px; height: 20px; width: 84%;    border-radius: 3px 3px 3px 3px; border-color: #7f9db9; background-color: #FFF;">
									<span style="    float: left;    margin-top:2px;    padding-left: 12px;"> Yes</span>
									<form:radiobutton style="width:20px; float: left;" class="validate[required] radio"
										path="employeeDTO.esiFlag" id="esiFlag2" value="1" />
									<span style="    float: left;    margin-top: 2px;  "> No</span>
									<form:radiobutton style="width:20px;" class="validate[required] radio"
										path="employeeDTO.esiFlag" id="esiFlag1" value="0" />
								</div></td>
							<td align="left">E. S. I. A/c. No.</td>
							<td><form:input type="text" path="employeeDTO.esiAcNumber"
									id="esiAcNumber" size="18"  /></td>
							<td align="left">&nbsp;</td>
						</tr>
						
						<tr>
							<td align="left">E.S.I. Employer Share Per<span style="color: #FF0000"></span>
							</td>
							<td>
									 <form:input type="text"  path="employeeDTO.esiEmployerSharePer" class="quantity validate[custom[number]]"
									datmaxsize="35" id="esiEmployerSharePer" size="18" />
								</td>
							<td height="33" align="left">E.S.I. Employee Share Per<span
								style="color: #FF0000"></span></td>
							<td><form:input type="text" path="employeeDTO.esiEmployeeSharePer" class="quantity validate[custom[number]]"
									datmaxsize="35" id="esiEmployeeSharePer" size="18" />
							</td>

							<td align="left">&nbsp;</td>
						</tr>
						
						<tr>
							<td align="left">E.S.I. Gross Cut off<span style="color: #FF0000"></span>
							</td>
							<td>
									 <form:input type="text"  path="employeeDTO.esiEmployeeGrossCutoffAmt" class="quantity validate[custom[number]]"
									datmaxsize="35" id="esiEmployeeGrossCutoffAmt" size="18" />
								</td>
								
								
								<td align="left">E.S.I. Employer Cut off<span style="color: #FF0000"></span>
							</td>
							<td>
									 <form:input type="text"  path="employeeDTO.esiEmployerCutoffAmt" class="quantity validate[custom[number]]"
									datmaxsize="35" id="esiEmployerCutoffAmt" size="18" />
								</td>
							
						</tr>
						<tr>
							<td align="left">Over Time Flag</td>
							<td><div
									style="border: solid 1px; height: 20px; width: 84%;     border-radius: 3px 3px 3px 3px; border-color: #7f9db9; background-color: #FFF;">
									<span style="    float: left;    margin-top:2px;    padding-left: 12px;"> Yes</span>
									<form:radiobutton style="width:20px;  float: left;"
										path="employeeDTO.overtimeFlag" id="overtimeFlag2" value="1" />
									<span style="    float: left;    margin-top: 2px;  "> No</span>
									<form:radiobutton style="width:20px;"
										path="employeeDTO.overtimeFlag" id="overtimeFlag1" value="0" />
								</div></td>
							<td align="left">OT Rate (per day)</td>
							<td><form:input type="text" path="employeeDTO.overtimeRate"
									id="overtimeRate" size="18" class="quantity validate[custom[number]]" /></td>
							<td align="left">&nbsp;</td>
						</tr>
						<tr>
						<td colspan="5" align="left">&nbsp;</td>
						</tr>
					</tbody>
				</table>
		<div class="btn">
		<div class="savbtn">
		 <c:choose>
      	  <c:when test="${opr=='V'}">
      	  <c:forEach items="${roleAndRightsDTOList}" var="roleAndRights">
		   <c:if test="${roleAndRights.menuId==sessionScope.menuId}">
    	  	 <c:if test="${roleAndRights.editFlag=='true'}">
		  	 <input class="edit_btn"  type="button" onclick="editMethod();" value=""/>
    	      <a href="show_Employee_list"  class="cancelbtn" ></a>  
		     </c:if>
		      <c:if test="${roleAndRights.editFlag=='false'}">
		      <input class="edit_btn"  type="button" onclick="checkEdit();" value=""/>
    	      <a href="show_Employee_list"  class="cancelbtn" ></a>  
		      </c:if>
		    
          </c:if>
          </c:forEach>	
      			  		
    	    </c:when>

      <c:otherwise>
      	<%-- <c:url value="edit_Employee_form4#tabs-4" var="remove_url" >
			</c:url>
			<a href="${remove_url}" class="nextbtn"></a> --%>
			<input class="nextbtn" type="submit" value="" />
			<div class="cancelbtn">
			<a href="show_Employee_list" class="cancelbtn"	iconCls="icon-cancel">Cancel</a>
			</div>
	     </c:otherwise>
	</c:choose>
	</div>
	</div>
				    <div ><span style="margin-left:12px; float: left;" class="errmsg"></span></div> 
			</div>
		</div>
	</form:form>
<form:form name="formIDTest" id="formID3" action="ajax_editEmployee_form#tabs-4" method="post"
		modelAttribute="employeeForm">
		<div id="tabs-4" style="background-color: #FF0000; font-size: 11px">
			<div align="left" class="bkgColor"  style="width:963px;" >
				<h2 style="margin-bottom: 12px">Salary Info</h2>
				<div class="gridheadingdiv" style="width: 50%; float: left;">
					<h3 style="width: 400px; margin-left: 8px;">Earnings</h3>
					<table width="668" style="float: left;" class="fixmyheader fixmyheader-8" id="fixmyheader-8">
      <thead>
      

						<tr>
							<td width="90"><div align="center">Salary Head</div></td>
							<td width="66"><div align="center">Head %</div></td>
							<td width="90"><div align="center">Base Heads</div></td>
							<td width="80"><div align="center">Head Type</div></td>
							<td width="90"><div align="center">Payable Type</div></td>
							<td width="100"><div align="center">Payable Month</div></td>
							<td width="100"><div align="center">Amount</div></td>
						</tr>
                        </thead>
                        <tbody> <form:hidden path="script" id="script" /> 
						<c:forEach items="${salaryHeadListE}" var="salaryHead" varStatus="i">
							 
							<tr>
								<td width="80">&nbsp; <c:out 	value="${salaryHead.salaryHeadName}" /> 
								<form:hidden path="employeeDTO.employeeSalaryDetDTOList[${i.index}].salaryId"
										value="${salaryHead.salaryHeadId}" />
								</td>
								<td width="56">&nbsp; <c:out value="${salaryHead.baseHeadPer}" /> <form:hidden path="employeeDTO.employeeSalaryDetDTOList[${i.index}].baseHeadPer"
										value="${salaryHead.baseHeadPer}" />
								</td>
								<td width="80">&nbsp; <c:forEach items="${salaryHead.baseHeads}" var="baseHeadIds">
										<c:out value="${baseHeadIds.salaryHeadName}" />
										<br>
									</c:forEach>
								</td>
								<td width="70">&nbsp; <c:out value="${salaryHead.headType}" />
									<form:hidden path="employeeDTO.employeeSalaryDetDTOList[${i.index}].headType"
										value="${salaryHead.headType}" />
								</td>
								<td width="80">&nbsp; <%-- <c:out
										value="${salaryHead.payableType}" /> --%> <!--   <form:hidden path="employeeDTO.employeeSalaryDetDTOList[${i.index}].baseHeadPer" value="salaryHead.baseHeadPer"/> -->
								
								
								 <form:select style="width: 28%; height: 21px"
						path="employeeDTO.employeeSalaryDetDTOList[${i.index}].payableType">
						 <form:option value="${salaryHead.payableType}"><c:out
										value="${salaryHead.payableType}" /></form:option>
						 <form:option value="monthly">Monthly</form:option>
             <form:option value="quarterly">Quarterly</form:option>
			<form:option value="halfYearly">Half Yearly</form:option>
             <form:option value="yearly">Yearly</form:option>
			  </form:select> 
								
								</td>
								<td width="90">&nbsp;
								<%--  <c:out
										value="${salaryHead.payableMonth}" /> --%>
										
										
										<form:select style="width: 28%; height: 21px"
						path="employeeDTO.employeeSalaryDetDTOList[${i.index}].payableMonth" >
						 <form:option value="${salaryHead.payableMonth}"><c:out
											value="${salaryHead.payableMonth}" /></form:option>
						 <form:option value="january">January</form:option>
             <form:option value="february">February</form:option>
             <form:option value="march">March</form:option>
             <form:option value="april">April</form:option>
             <form:option value="may"> May</form:option>
             <form:option value="june">June</form:option>
             <form:option value="july">July</form:option>
             <form:option value="august">August</form:option>
             <form:option value="september">September</form:option>
             <form:option value="october">October</form:option>
             <form:option value="november">November</form:option>
             <form:option value="december">December</form:option>
			  </form:select>
											
										
										
										<%--  <form:hidden
										path="employeeDTO.employeeSalaryDetDTOList[${i.index}].payableMonth"
										value="${salaryHead.payableMonth}" /> --%>
								</td>
								<td width="73"><c:choose>
										<c:when test="${empty salaryHead.baseHeads}">

											<form:input type="text"
												path="employeeDTO.employeeSalaryDetDTOList[${i.index}].amount"
												id="amount${i.index}" style="text-align:right; height:20px; " size="10" 
												class="quantity validate[custom[number]] refresh" />
										
										</c:when>
										<c:otherwise>
											<form:input type="text"
												path="employeeDTO.employeeSalaryDetDTOList[${i.index}].amount"
												id="amount${i.index}" style="background-color:#ebebe4; height:20px;" size="10"  readonly="true" class="quantity validate[custom[number]]"/>
										</c:otherwise>
									</c:choose>
								</td>
							</tr>
					</c:forEach>
  
  			</tbody>
			</table>
				<div style="float: right; margin-top: 18px; width: 125px;">
						<div style="float: left">
							<strong>Total</strong>
						</div>
						<div style="float: right; margin-right: 13px; height: 20px; margin-top: 0px; width: 77px;">
						   <form:input type="hidden"  path="employeeDTO.pfFlag" id="pfFlagEdit"/>
						   <form:input type="hidden" path="employeeDTO.pfEmployerBasicContriAmt" />
						   <form:input type="hidden" path="employeeDTO.pfEmployerSharePer" />
						   <form:input type="hidden"  path="employeeDTO.pfEmployeeBasicContriAmt"  />
						   <form:input type="hidden"  path="employeeDTO.pfEmployeeSharePer" />
						   
							<form:input type="text" id="eamount"
								style=" background-color:#ebebe4; height: 18px;  text-align: right;"
								path="employeeDTO.eamount"  size="10" disabled="disable"
								readonly="true" class="quantity validate[custom[number]]"/>
						</div>
					</div>
				</div>
				<div class="gridheadingdiv"
						style="float: right; width: 50%; margin-top: 0px;">
					<h3 style="width: 450px; margin-left: 4px;">Deduction</h3>
					<table width="668" style=" float:left;width:100%; " class="fixmyheader fixmyheader-8" id="fixmyheader-8">
      <thead>
       <tr>				 
							<td width="90"><div align="center">Salary Head</div></td>
							<td width="60"><div align="center">Head %</div></td>
							<td width="90"><div align="center">Base Heads</div></td>
							<td width="57"><div align="center">Head Type</div></td>
							<td width="90"><div align="center">Payable Type</div></td>
							<td width="82"><div align="center">Payable Month</div></td>
							<td width="85"><div align="center">Amount</div></td>
						 </tr>
      </thead>
      <tbody>
      			<c:forEach items="${salaryHeadListD}" var="salaryHeadD"
							varStatus="j">
						 
							<tr>
								<td width="80">&nbsp; <c:out
										value="${salaryHeadD.salaryHeadName}" /> <form:hidden
										path="employeeDTO.employeeSalaryDetDTOListDe[${j.index}].salaryId"
										value="${salaryHeadD.salaryHeadId}" />
								</td>
								<td width="50">&nbsp; <c:out
										value="${salaryHeadD.baseHeadPer}" /> <form:hidden
										path="employeeDTO.employeeSalaryDetDTOListDe[${j.index}].baseHeadPer"
										value="${salaryHeadD.baseHeadPer}" />
								</td>
								<td width="80">&nbsp; <c:forEach
										items="${salaryHeadD.baseHeads}" var="baseHeadIdsD">
										<c:out value="${baseHeadIdsD.salaryHeadName}" />
										<br>
									</c:forEach>
								</td>
								<td width="47">&nbsp; <c:out
										value="${salaryHeadD.headType}" /> <form:hidden
										path="employeeDTO.employeeSalaryDetDTOListDe[${j.index}].headType"
										value="${salaryHeadD.headType}" />
								</td>
								<td width="80">&nbsp; <%-- <c:out
										value="${salaryHeadD.payableType}" /> --%> <!--   <form:hidden path="employeeDTO.employeeSalaryDetDTOListDe[${j.index}].baseHeadPer" value="salaryHead.baseHeadPer"/> -->
								
								
					<form:select style="width: 28%; height: 21px"
						path="employeeDTO.employeeSalaryDetDTOListDe[${j.index}].payableType">
						 <form:option value="${salaryHeadD.payableType}"><c:out
										value="${salaryHeadD.payableType}" /></form:option>
						 <form:option value="monthly">Monthly</form:option>
             <form:option value="quarterly">Quarterly</form:option>
			<form:option value="halfYearly">Half Yearly</form:option>
             <form:option value="yearly">Yearly</form:option>
			  </form:select>			
								
								</td>
								<td width="72">&nbsp;<%--  <c:out
										value="${salaryHeadD.payableMonth}" />  --%>
										
										
										<form:select style="width: 100%; height: 21px" 
						path="employeeDTO.employeeSalaryDetDTOListDe[${j.index}].payableMonth" >
						 <form:option value="${salaryHeadD.payableMonth}"><c:out
											value="${salaryHeadD.payableMonth}" /></form:option>
						 <form:option value="january">January</form:option>
             <form:option value="february">February</form:option>
             <form:option value="march">March</form:option>
             <form:option value="april">April</form:option>
             <form:option value="may"> May</form:option>
             <form:option value="june">June</form:option>
             <form:option value="july">July</form:option>
             <form:option value="august">August</form:option>
             <form:option value="september">September</form:option>
             <form:option value="october">October</form:option>
             <form:option value="november">November</form:option>
             <form:option value="december">December</form:option>
			 </form:select>
										
										
										
								</td>
								<td width="80"><c:choose>
									<c:when test="${empty salaryHeadD.baseHeads}">

											<form:input type="text"
												path="employeeDTO.employeeSalaryDetDTOListDe[${j.index}].amount"
												id="amountS${j.index}" size="10" onchange="refresh()" class="quantity validate[custom[number]]"/>
										</c:when>
										<c:otherwise>
											<form:input type="text"
												path="employeeDTO.employeeSalaryDetDTOListDe[${j.index}].amount"
												id="amountS${j.index}" size="10" readonly="true" class="quantity validate[custom[number]]" style="background-color:#ebebe4;"/>
										</c:otherwise>
									</c:choose>
								</td>
							</tr>
					</c:forEach>
  
  </tbody>
</table>

					<div style="float: right; margin-top: 18px; width: 146px;">
						<div style="float: left;margin: 3px 5px 0 0;">
							<strong style="text-align: left;">Total</strong>
						</div>
						<div
							style="float: right; height: 20px; margin-right: 15px; margin-top: 0; width: 79px;  text-align: right;">
							<form:input type="text"  path="employeeDTO.damount" id="damount"
								style=" width:79px; background-color:#ebebe4; height: 18px; text-align:right"
								size="10" readonly="true" class="quantity validate[custom[number]]"/>
						</div>
					
			<div class="netotal">
					<h3 style="float: left; font-weight: bold;margin-right: 8px;">Gross	Total</h3>
					<form:input type="text" style=" width:79px; background-color:#ebebe4; height: 18px; text-align:right" path="employeeDTO.totalAmount" size="10"
						readonly="true" class="quantity validate[custom[number]]" id="totalAmount"/>
				</div>
		 </div>
		</div>
		
		<div class="btn">
         <div class="savbtn">
		<c:choose>
      	<c:when test="${opr=='V'}">
      	<c:forEach items="${roleAndRightsDTOList}" var="roleAndRights">
		   <c:if test="${roleAndRights.menuId==sessionScope.menuId}">
    	  	 <c:if test="${roleAndRights.editFlag=='true'}">
		  	 <input class="edit_btn"  type="button" onclick="editMethod();" value=""/>
    	      <a href="show_Employee_list"  class="cancelbtn" ></a>  
		     </c:if>
		      <c:if test="${roleAndRights.editFlag=='false'}">
		      <input class="edit_btn"  type="button" onclick="checkEdit();" value=""/>
    	      <a href="show_Employee_list"  class="cancelbtn" ></a>  
		      </c:if>
		    
          </c:if>
          </c:forEach>	
      	</c:when>

     <c:otherwise>
      	<input class="updatebtn"  type="submit" value="updatebtn"/> 
        <a href="show_Employee_list"  class="cancelbtn" ></a>  
     </c:otherwise>
	</c:choose>
    </div>
	</div>
	<span style="margin-left: 12px; " class="errmsg"></span>
			</div>
		</div>
	</form:form>

</div>

