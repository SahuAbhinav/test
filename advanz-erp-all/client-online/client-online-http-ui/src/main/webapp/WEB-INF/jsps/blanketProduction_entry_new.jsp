<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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



<c:if test="${not empty(deleteStatusL)}">
 <input type="hidden" id="deleteStatusL" value="${deleteStatusL}">
 <script type="text/javascript">
 		$(document).ready(function() {
 		var errorId=document.getElementById('deleteStatusL');
		alert(errorId.value);
 		});
 	</script>
 </c:if>
 
 <c:if test="${not empty(deleteStatusR)}">
 <input type="hidden" id="deleteStatusR" value="${deleteStatusR}">
 <script type="text/javascript">
 		$(document).ready(function() {
 		var errorId=document.getElementById('deleteStatusR');
		alert(errorId.value);
 		});
 	</script>
 </c:if>






 <c:if test="${opr=='R'}">
 <script type="text/javascript">
		var redrctUrl='get_blanketProduction_list_new';
				
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
 		 var frank_param = getParam('id');
 		 //	confirm('Are you sure you want to delete?');
		 var delUrl='remove_blanketProduction_new?id='+frank_param;
 		 if(confirm('Are you sure you want to delete?')) 
 		   {
 			window.self.location = delUrl;
 		 	}
		 else{
			 window.self.location = redrctUrl;
  			}
		});
 	</script>
 	</c:if>
 	
 	
 <script type="text/javascript">
 function checkEdit()
	{
	alert('Login User Not Permit to Edit Record !!!!!!');
	}
	
	//	$(document).ready(function() {
 	function editMethod()
 	 { 
 	 var frank_param = $('#sno').val();
 	 //	confirm('Are you sure you want to delete?');
	 var delUrl='get_blanketProduction_new?id='+frank_param+'&opr=E';
 	 window.self.location = delUrl;  
	}
 	</script>	
 <c:if test="${opr=='V' }">
<script>
$(document).ready(function() {
	$('input').attr('readonly','readonly');	
	$('select').attr('disabled','disabled');
	$('textarea').attr('readonly','readonly');
	$('.datepicker2').attr('disabled','disabled');
	$('.myTimePicker').attr('disabled','disabled');
	$('.newRow').attr('disabled','disabled');
	$('input:radio').attr('disabled',true);
	 $("input:checkbox").attr("disabled", true);
	 $('#updateId').attr('disabled','disabled');
	 $('#timerStart').attr('disabled','disabled'); 
	 $('.datepicker1').attr('disabled','disabled');
	 $('#spliterCountSelect').attr('disabled', true);
	 
});
</script>
</c:if> 		
 	
<script type="text/javascript">
			
			$(document).ready(function() {  	
				
				 
				
				$('.fixmyheader-8').fixheadertable({
					caption		: 'My header is fixed !',
					height		:128,
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
				border		: 1px solid #D4D4D4;
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
<script type="text/javascript">    
$(document).ready(function(){ 
 function fixNumFormat(){
	$(".quantity").each(function(){		
		var v=parseFloat($(this).val());		
		v=v.toFixed(2);
		if(v=='NaN'){
		//	v='0.00';
		v='';
		}
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

<input type="hidden" value="${opr}" id="opr">


 <script type="text/javascript">
$(document).ready(function(){ 
    //called when key is pressed in textbox
$(".newRow").click(function (e){ 	
		
	var opr=$("#opr").val();
	if(opr!=null || opr!=''){
		opr=opr;
	}else{
		opr="show";
	}
var leftbpListSize=$("#leftbpListSize").val();
//var rightbpListSize=$("#rightbpListSize").val();
if($(this).attr('id')=='L'){
	if($("#densityLeft"+leftbpListSize).val()=='' || $("#blanketTypeL"+leftbpListSize).val()==''){
		alert('Please first fill up complete entry for last roll no');
	}else{
		 document.forms["formID"].action="add_new_row_in_bp_new?rt="+$(this).attr('id')+"&opr="+opr;
		 document.forms["formID"].submit();	
	}}

/* if($(this).attr('id')=='R'){
	if($("#densityRight"+rightbpListSize).val()=='' || $("#blanketTypeR"+leftbpListSize).val()==''){
		alert('Please first fill up complete entry for last roll no');
	}else{
		 document.forms["formID"].action="add_new_row_in_bp?rt="+$(this).attr('id')+"&opr="+opr;
		 document.forms["formID"].submit();	
	}} */


  
   });
function isInt(n) {
	   return n % 1 === 0;
	}
	

	
	$(".remRow").click(function (e)  
			{ 
		var opr=$("#opr").val();
		if(opr!=null || opr!=''){
			opr=opr;
		}else{
			opr="show";
		}
		var recordNo= +($(this).attr('id').match(/\d+$/)[0])+1;
		var spliterCount=$('#spliterCountId').val();
		 var leftbpListSize=$("#leftbpListSize").val();
		console.log(recordNo+"-"+spliterCount+"-"+(leftbpListSize));
		console.log(isInt(recordNo/spliterCount));
		
		if((recordNo-1)==leftbpListSize){
			console.log('This is empty Record can not delete.');
			document.forms["formID"].action="remove_row_from_bp_new?rs="+$(this).attr('id')+"&opr="+$("#opr").val()+"&spliterCount="+$("#spliterCountId").val();
			document.forms["formID"].submit(); 
			//return;
		}
		
		/* if(isInt(recordNo/spliterCount) || (recordNo)==leftbpListSize)
		{
			 document.forms["formID"].action="remove_row_from_bp_new?rs="+$(this).attr('id')+"&opr="+$("#opr").val()+"&spliterCount="+$("#spliterCountId").val();
			document.forms["formID"].submit(); 
		} */else{
			alert('U cant delete this entry before deleting all latest entries.');
		}
	});
	
	$(".editRow").click(function (e)  
			{ 
			document.forms["formID"].action="edite_row_from_bp_new?rs="+$(this).attr('id')+"&opr="+$("#opr").val();
			document.forms["formID"].submit();	
	});
    
});
  </script>
  
 
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
		
		height: 153px;
		}
	.remRow{}
	.editRow{}
	
	.button {
 border: 4px outset;
 padding: 2px;
 text-decoration: none;
 }
 .button:active {
 border: 4px inset;
 }
</style> 
<script>
	$(document).ready(function() {
		$(".cancelbtn").click(function() {
			window.self.location = "get_blanketProduction_list_new";
		});

	});
</script>

<c:if test="${opr=='R'}">
	<script>
		$(document).ready(function() {
			$('input').attr('disabled', 'disabled');
			$('select').attr('disabled', 'disabled');
			$('textarea').attr('disabled', 'disabled');
		});
	</script>
</c:if>

<c:if test="${errormsg!=null}">
	<script>
		$(document).ready(function() {	
			alert("Record is not available for this combination");
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

<script type="text/javascript">
	$(document).ready(
			function() {
				//called when key is pressed in textbox
				$(".quantity2").keypress(
						function(e) {
							//if the letter is not digit then display error and don't type anything
							 if( e.which!=8 && e.which!=0 && (e.which<48 || e.which>57)){
								//display error message
								$(".errmsg").html("Digits Only").show()
										.fadeOut("slow");
								return false;
							}
						});

			});
</script>


<script type="text/javascript">
      $(document).ready(function()
       {
    	  $("#formID").submit(function() {
  		    $('.datepicker1').removeAttr('disabled');
  		 // $('#spliterCountSelect').removeAttr('disabled');
  		});  
    	  
    	  
       
    	  var l=$('#lastDate').val();
    	  var m=$('#cutoffDate').val();
    	  //console.log(l);
    	  //console.log(m);
    	  console.log(new Date());
    	  console.log(new Date(m));
    	  if(new Date() <= new Date(m)){
    		  console.log(new Date());
    		  $(".datepicker1" ).val('');
    		  alert("Use Old Form for entry below date "+m);
    		  window.self.location = "get_blanketProduction_list";
    	  }else{
    	  
    	 // console.log(l);
       
	    $(".datepicker1" ).datepicker({dateFormat : 'dd-M-yy',changeMonth: true,
	          changeYear: true, yearRange: '-99:+0', minDate: new Date(l), maxDate: '+0M +0D'});
    	  }
    	  });
      
   //To Shift cursor at last line............
    	var scrollDown = function(elementId) {
    	    "use strict";
    	    var element = document.getElementById(elementId);
    	    element.scrollTop = element.scrollHeight;
    	};

    	var addDashes = function(elementId) {
    	
    	    "use strict";
    	    var element = document.getElementById(elementId);
    	    if(element.value.substring(element.value.length - 4, element.value.length) !== "\n--\n") {
    	        element.value = element.value;
    	        element.focus();
    	    }};

    	$(document).ready(function(){
    	    
    		 var leftbpListSize=$("#leftbpListSize").val();
    		 //var rightbpListSize=$("#rightbpListSize").val();
    		 
    	        scrollDown("weightLeft"+leftbpListSize);
    	        //scrollDown("weightRight"+rightbpListSize);
    	        
    	        addDashes("weightLeft"+leftbpListSize);
    			//addDashes("weightRight"+rightbpListSize);
    	});
    	//End shift cursor............
  </script>
<style type="text/css">
<!--
#formID .bkgColor table tr td label {
	
}
.scroll {
height: 114px;
overflow-x:hidden;
overflow-y:scroll;  

}
.tableview{     border: 1px solid #7F9DB9;
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
<input type="hidden" name="leftbpListSize" value="${leftbpListSize-1}" id="leftbpListSize">
<input type="hidden" name="rightbpListSize" value="${rightbpListSize}" id="rightbpListSize">
<input type="hidden" name="flag" value="${flag}" id="flag">

<c:if test="${opr=='E'}">
<script type="text/javascript">
$(document).ready(function(){
	 var leftbpListSize=$("#leftbpListSize").val();
	// var rightbpListSize=$("#rightbpListSize").val();
	 

	$('#date').attr('readOnly', 'readOnly');
	$('#runNo').attr('readOnly', 'readOnly');
	$('#grade').attr('disabled', true);
	$('#shift').attr('disabled', true);
	$('#batchNo').attr('readOnly', 'readOnly');
	$('#spliterCountSelect').attr('disabled', true);
	if($("#itemIdL"+leftbpListSize).val()>0){
		$('#lengthLeft'+leftbpListSize).attr('readOnly', 'readOnly');
		$('#rollnumberL'+leftbpListSize).attr('readOnly', 'readOnly');
		$('#thickLeft'+leftbpListSize).attr('readOnly', 'readOnly');
		$('#weightLeft'+leftbpListSize).attr('readOnly', 'readOnly');
		$('#blanketTypeL'+leftbpListSize).attr('readOnly', 'readOnly');
		//$('#remarksL'+leftbpListSize).attr('readOnly', 'readOnly');
		$('#widthLeft'+leftbpListSize).attr('readOnly', 'readOnly');
	
	}/* if($("#itemIdR"+rightbpListSize).val()>0){
		$('#lengthRight'+rightbpListSize).attr('readOnly', 'readOnly');
		$('#rollnumberR'+rightbpListSize).attr('readOnly', 'readOnly');
		$('#thickRight'+rightbpListSize).attr('readOnly', 'readOnly');
		$('#weightRight'+rightbpListSize).attr('readOnly', 'readOnly');
		$('#blanketTypeR'+rightbpListSize).attr('readOnly', 'readOnly');
		//$('#remarksR'+rightbpListSize).attr('readOnly', 'readOnly');
		$('#widthRight'+rightbpListSize).attr('readOnly', 'readOnly');
	
	
	} */
	
	$('.datepicker1').attr('disabled','disabled');
	
	
	
	
});
</script>
</c:if>

<script type="text/javascript">
$(document).ready(function(){
	 var leftbpListSize=$("#leftbpListSize").val();
	 //var rightbpListSize=$("#rightbpListSize").val();
	 
if(leftbpListSize>0 ){
	$('#date').attr('readOnly', 'readOnly');
	$('#runNo').attr('readOnly', 'readOnly');
	$('#grade').attr('disabled', true);
	$('#shift').attr('disabled', true);
	$('#batchNo').attr('readOnly', 'readOnly');
	$('#spliterCountSelect').attr('disabled', true);
}


console.log("size"+leftbpListSize);
	
	function formChange() {
	 for ( var ele = 0; ele<leftbpListSize; ele++) {
			var frm = document.forms[0];
			var weightLeft = frm.elements["blanketProductionMasterNewDTO.blanketProductionDetailNewDTOList[" + ele+ "].weight"];
			//console.log(frm)
			//alert("3" + quantity);
			//console.log(weightLeft);
			if (!weightLeft) {
				//alert("4" );
				break;
			}
			$('#lengthLeft'+ele).attr('readOnly', 'readOnly');
			$('#rollnumberL'+ele).attr('readOnly', 'readOnly');
			$('#thickLeft'+ele).attr('readOnly', 'readOnly');
			$('#weightLeft'+ele).attr('readOnly', 'readOnly');
			$('#blanketTypeL'+ele).attr('readOnly', 'readOnly');
			//$('#remarksL'+ele).attr('readOnly', 'readOnly');
			$('#widthLeft'+ele).attr('readOnly', 'readOnly');
			
	 }
	 //TO make readOnly remark
	 /* for ( var ele = 0; ele<leftbpListSize-1; ele++) {
			var frm = document.forms[0];
			var weightLeft = frm.elements["blanketProductionMasterDTO.blanketProductionDetailLeftDTOList[" + ele+ "].weightLeft"];
			if (!weightLeft) {
				break;
			}
			$('#remarksL'+ele).attr('readOnly', 'readOnly');
	 } */
	 
	 /* for ( var ele = 0; ele<rightbpListSize; ele++) {
			var frm = document.forms[0];
			var weightRight = frm.elements["blanketProductionMasterDTO.blanketProductionDetailRightDTOList[" + ele+ "].weightRight"];
			//alert("3" + quantity);
			if (!weightRight) {
				//alert("4" );
				break;
			}
			$('#lengthRight'+ele).attr('readOnly', 'readOnly');
			$('#rollnumberR'+ele).attr('readOnly', 'readOnly');
			$('#thickRight'+ele).attr('readOnly', 'readOnly');
			$('#weightRight'+ele).attr('readOnly', 'readOnly');
			$('#blanketTypeR'+ele).attr('readOnly', 'readOnly');
			//$('#remarksR'+ele).attr('readOnly', 'readOnly');
			$('#widthRight'+ele).attr('readOnly', 'readOnly');
	        } */
	 
	 //To make readOnly remark
	 /* for ( var ele = 0; ele<rightbpListSize-1; ele++) {
			var frm = document.forms[0];
			var weightRight = frm.elements["blanketProductionMasterDTO.blanketProductionDetailRightDTOList[" + ele+ "].weightRight"];
			if (!weightRight) {
				break;
			}
			$('#remarksR'+ele).attr('readOnly', 'readOnly');
	 } */
	 
	 
	 }
	$('#formID').change(function() { 
		formChange();
});
	formChange();


});
</script>

<%-- <c:if test="${opr=='E'}">
<script type="text/javascript">
var ele;
function blanketTypeL(ele){
	
	 if($("#widthLeft"+ele).val()==''){
		 
			alert('Please fill the complete item detail');
			 }else{
				 document.forms["formID"].action="save_blanket_in_bp?rt="+$("#operationL"+ele).val()+"&opr="+$("#opr").val();
				 document.forms["formID"].submit();	
			 }
	 
	 }</script></c:if> --%>
	 
	 
	
<script type="text/javascript">

$(document).ready(function(){
	
	$("#updateId").click(function() {
	 document.forms["formID"].action="saveBlanketMaster_new?operation=update";
	 document.forms["formID"].submit();	
	});
	
	
	
	
	
	
	 var leftbpListSize=$("#leftbpListSize").val();
	 var rightbpListSize=$("#rightbpListSize").val();

	 
	 $('#weightLeft'+leftbpListSize).change(function() {
		 if($('#date').val()=='' ||$("#runNo").val()=='' || $("#grade").val()=='' || $("#shift").val()=='' || $("#batchNo").val()==''){
			 
			 alert('Please fill the all mandatory fields');
		 }else{
			 
			 //
			 $.ajax({
					type : "POST",
					url : "add_row_in_bp_new",
					data : "rt="+$("#operationL"+leftbpListSize).val()+"&gradeId="+$("#grade").val()+"&lenght="+$("#lengthLeft"+leftbpListSize).val()+"&width="+$("#widthLeft"+leftbpListSize).val()+"&thickness="+$("#thickLeft"+leftbpListSize).val()+"&weight="+$("#weightLeft"+leftbpListSize).val(),

					success : function(response) {
						if(response.result!=null){
							$("#densityLeft"+leftbpListSize).val(response.result);
							$("#itemIdL"+leftbpListSize).val(response.result1);
							$("#itemNameL"+leftbpListSize).val(response.result2);
							
							$("#itemNameL"+leftbpListSize).prop('title', response.result2);
							
							$('#lengthLeft'+leftbpListSize).attr('readOnly', 'readOnly');
							$('#rollnumberL'+leftbpListSize).attr('readOnly', 'readOnly');
							$('#thickLeft'+leftbpListSize).attr('readOnly', 'readOnly');
							$('#weightLeft'+leftbpListSize).attr('readOnly', 'readOnly');
							$('#blanketTypeL'+leftbpListSize).attr('readOnly', 'readOnly');
							//$('#remarksL'+leftbpListSize).attr('readOnly', 'readOnly');
							$('#widthLeft'+leftbpListSize).attr('readOnly', 'readOnly');
							
							/* document.forms["formID"].action="save_blanket_in_bp?rt="+$("#operationL"+leftbpListSize).val();
							 document.forms["formID"].submit(); */	
						}else{
							alert('Record is not available for this combination');
							 $('#weightLeft'+leftbpListSize).val('');
						}
						
					}
			 });
		//
			 
			/*  document.forms["formID"].action="add_row_in_bp?rt="+$("#operationL"+leftbpListSize).val()+"&gradeId="+$("#grade").val()+"&lenght="+$("#lengthLeft"+leftbpListSize).val()+"&width="+$("#widthLeft"+leftbpListSize).val()+"&thickness="+$("#thickLeft"+leftbpListSize).val()+"&weight="+$("#weightLeft"+leftbpListSize).val();
			document.forms["formID"].submit(); */	
		 }
		 
	 });
	 $('#weightRight'+rightbpListSize).change(function() {
		 if($('#date').val()=='' ||$("#runNo").val()=='' || $("#grade").val()=='' || $("#shift").val()=='' || $("#batchNo").val()==''){
			 
			 alert('Please fill the all mandatory fields');
		 }else{
			
			 //To get Density
			 $.ajax({
					type : "POST",
					url : "add_row_in_bp",
					data : "rt="+$("#operationR"+rightbpListSize).val()+"&gradeId="+$("#grade").val()+"&lenght="+$("#lengthRight"+rightbpListSize).val()+"&width="+$("#widthRight"+rightbpListSize).val()+"&thickness="+$("#thickRight"+rightbpListSize).val()+"&weight="+$("#weightRight"+rightbpListSize).val(),

					success : function(response) {
						if(response.result!=null){
							$("#densityRight"+rightbpListSize).val(response.result);
							$("#itemIdR"+rightbpListSize).val(response.result1);
						
							$("#itemNameR"+rightbpListSize).val(response.result2);
							$("#itemNameR"+rightbpListSize).prop('title', response.result2);
							
							$('#lengthRight'+rightbpListSize).attr('readOnly', 'readOnly');
							$('#rollnumberR'+rightbpListSize).attr('readOnly', 'readOnly');
							$('#thickRight'+rightbpListSize).attr('readOnly', 'readOnly');
							$('#weightRight'+rightbpListSize).attr('readOnly', 'readOnly');
							$('#blanketTypeR'+rightbpListSize).attr('readOnly', 'readOnly');
							//$('#remarksR'+rightbpListSize).attr('readOnly', 'readOnly');
							$('#widthRight'+rightbpListSize).attr('readOnly', 'readOnly');
						
							/* document.forms["formID"].action="save_blanket_in_bp?rt="+$("#operationR"+rightbpListSize).val();
							 document.forms["formID"].submit(); */
						}else{
							 alert('Record is not available for this combination');
							 $('#weightRight'+rightbpListSize).val('');
						}
					
					}
			 });
			 //To get Density
		
			 
		 /* document.forms["formID"].action="add_row_in_bp?rt="+$("#operationR"+rightbpListSize).val()+"&gradeId="+$("#grade").val()+"&lenght="+$("#lengthRight"+rightbpListSize).val()+"&width="+$("#widthRight"+rightbpListSize).val()+"&thickness="+$("#thickRight"+rightbpListSize).val()+"&weight="+$("#weightRight"+rightbpListSize).val();
		 document.forms["formID"].submit(); */
		 }
	 });
	
	
	 $('#batchNo').change(function() {
		 $.ajax({
				type : "POST",
				url : "checkDuplicateRecordInBPMaster_new",
				data : "date=" + $('#date').val()+"&runNo="+$("#runNo").val()+"&grade="+$("#grade").val()+"&shift="+$("#shift").val()+"&batchNo="+$("#batchNo").val(),

				success : function(response) {
					if(response.result=='Duplicate'){
						alert('Sorry, Record already exist, Duplicate entries are not allowed for date,run no and shift.');	
						$("#runNo").val("");
						$("#batchNo").val("");
					}}
		 });
	 });
	 
  });
  

  </script>
  <c:if test="${opr!='E' || opr==''}">
 <script type="text/javascript">
$(document).ready(function(){
	
	 var leftbpListSize=$("#leftbpListSize").val();
	 var rightbpListSize=$("#rightbpListSize").val();
//To submmit form to save entry in blanket production left and right 
	
			 
	 $('#blanketTypeL'+leftbpListSize).change(function() {
		
		 if($("#weightLeft"+leftbpListSize).val()=='' || $('#blanketTypeL'+leftbpListSize).val()==''){
			 
		alert('Please fill the complete item detail or blanket type can not be blank');
		 $('#blanketTypeL'+leftbpListSize).val("");
		 
		 }else{
			 document.forms["formID"].action="save_blanket_in_bp_new?rt="+$("#operationL"+leftbpListSize).val();
			// $('#spliterCountSelect').removeAttr('disabled');
			 document.forms["formID"].submit();	
			
		 }
	 });
	 $('#blanketTypeR'+rightbpListSize).change(function() {
		 if($("#weightRight"+rightbpListSize).val()=='' ||  $('#blanketTypeR'+rightbpListSize).val()==''){
			 
			 alert('Please fill the complete item detail or blanket type can not be blank');
			 $('#blanketTypeR'+rightbpListSize).val("");
		 }else{
			
		 document.forms["formID"].action="save_blanket_in_bp?rt="+$("#operationR"+rightbpListSize).val();
		 document.forms["formID"].submit();
		
		 }
	 }); 
	 	
	 
});

	// End..................
    </script>
</c:if>

<!-- To get max Left and right rollNo acording to Batch Number............ -->
 <script type="text/javascript">
$(document).ready(function(){
	

	$('#batchNo').change(function() {
		$.ajax({
			type : "POST",
			url : "getMaxRollNo_new",
			data :"batchNo="+$('#batchNo').val() ,
			success : function(response) {
				//response.result.rollNoR;
				$("#rollnumberL0").val(response.result.rollNo);
				//$("#rollnumberR0").val(response.result.rollNoR);
				}
	        });
	});
	
	$('#spliterCountId').change(function(){
		
	//console.log($('#spliterCountSelect').val());
	//console.log($('#spliterCountId').val());
		$('#spliterCountSelect').val($('#spliterCountId').val());
		console.log($('#spliterCountSelect').val());
		});
	
	
	 $('#spliterCountSelect').change(function() {
		 if($('#date').val()=='' ||$("#runNo").val()=='' || $("#grade").val()=='' || $("#shift").val()=='' || $("#batchNo").val()==''){
			 $('#spliterCountSelect').val($('#spliterCountId').val());
			 alert('Please fill the Mandetory Field');
		 }else{
			
		 document.forms["formID"].action="change_spliter";
		 document.forms["formID"].submit();
		
		 }
	 });
	 
});


//To check Duplicat RollNoL Acording to Batch No
var ele;
function rollnumberL(ele){
	

	$.ajax({
		type : "POST",
		url : "checkDuplicatRollNo_new",
		data :"batchNo="+$('#batchNo').val()+"&rollnumberL="+$("#rollnumberL"+ele).val() ,
		success : function(response) {
			
			if(response.result==true){
				   alert("Sorry you can not insert duplicate roll no for the same batch");	
				   $("#rollnumberL"+ele).val('');
				}
			
	}
      });
}

//To check Duplicat RollNoR Acording to Batch No
var ele;
function rollnumberR(ele){

	$.ajax({
		type : "POST",
		url : "checkDuplicatRollNoR",
		data :"batchNo="+$('#batchNo').val()+"&rollnumberR="+$("#rollnumberR"+ele).val() ,
		success : function(response) {
			
			if(response.result==true){
			   alert("Sorry you can not insert duplicate roll no for the same batch");	
			   $("#rollnumberR"+ele).val('');
			}
	}
    });
}
</script>


<script type="text/javascript">
function updateValidation(){
	alert("You can not update this record as it is approved");
	
}function deleteValidation(){
	alert("You can not delete this record as it is approved");
	
}
function startTimer(){
	
	var leftbpListSize=$("#leftbpListSize").val();
	// var rightbpListSize=$("#rightbpListSize").val();
	// var flag=$("#flag").val();
	 
	 if($('#date').val()=='' ||$("#runNo").val()=='' || $("#grade").val()=='' || $("#shift").val()=='' || $("#batchNo").val()=='' || $("#thickLeft"+leftbpListSize).val()=='' || $("#lengthLeft"+leftbpListSize).val()=='' ||$("#widthLeft"+leftbpListSize).val()==''){
		 
		 alert('Please fill the all mandatory fields');
	 }else{
		// console.log("leftbpListSize"+leftbpListSize);
		 //console.log("rightbpListSize"+rightbpListSize);
		 //console.log("flag"+flag);
		 //
		 //if( flag==='' || flag==1){
			// console.log("in left side");
		  $.ajax({ 
				type : "POST",
				url : "add_row_in_bp_new",
				data : "rt="+$("#operationL"+leftbpListSize).val()+"&gradeId="+$("#grade").val()+"&lenght="+$("#lengthLeft"+leftbpListSize).val()+"&width="+$("#widthLeft"+leftbpListSize).val()+"&thickness="+$("#thickLeft"+leftbpListSize).val()+"&weight="+$("#weightLeft"+leftbpListSize).val()+"&spliterType="+$("#spliterType"+leftbpListSize).val(),

				success : function(response) {
					if(response.result!=null){
						$("#densityLeft"+leftbpListSize).val(response.result);
						$("#itemIdL"+leftbpListSize).val(response.result1);
						$("#itemNameL"+leftbpListSize).val(response.result2);
						
						$("#itemNameL"+leftbpListSize).prop('title', response.result2);
						$("#weightLeft"+leftbpListSize).val(response.result3);
						
						$('#lengthLeft'+leftbpListSize).attr('readOnly', 'readOnly');
						$('#rollnumberL'+leftbpListSize).attr('readOnly', 'readOnly');
						$('#thickLeft'+leftbpListSize).attr('readOnly', 'readOnly');
						$('#weightLeft'+leftbpListSize).attr('readOnly', 'readOnly');
						$('#blanketTypeL'+leftbpListSize).attr('readOnly', 'readOnly');
						//$('#remarksL'+leftbpListSize).attr('readOnly', 'readOnly');
						$('#widthLeft'+leftbpListSize).attr('readOnly', 'readOnly');
						
						 //document.forms["formID"].action="save_blanket_in_bp?rt="+$("#operationL"+leftbpListSize).val();
						 //document.forms["formID"].submit(); 	
					}else{
						alert('Record is not available for this combination');
						 $('#weightLeft'+leftbpListSize).val('');
					}
					
				}
		 }); 
		 //}
		 
		 /* else{ console.log("in right side");
			 $.ajax({
					type : "POST",
					url : "add_row_in_bp",
					data : "rt="+$("#operationR"+rightbpListSize).val()+"&gradeId="+$("#grade").val()+"&lenght="+$("#lengthRight"+rightbpListSize).val()+"&width="+$("#widthRight"+rightbpListSize).val()+"&thickness="+$("#thickRight"+rightbpListSize).val()+"&weight="+$("#weightRight"+rightbpListSize).val(),

					success : function(response) {
						if(response.result!=null){
							$("#densityRight"+rightbpListSize).val(response.result);
							$("#itemIdR"+rightbpListSize).val(response.result1);
						
							$("#itemNameR"+rightbpListSize).val(response.result2);
							$("#itemNameR"+rightbpListSize).prop('title', response.result2);
							$("#weightRight"+rightbpListSize).val(response.result3);
							
							$('#lengthRight'+rightbpListSize).attr('readOnly', 'readOnly');
							$('#rollnumberR'+rightbpListSize).attr('readOnly', 'readOnly');
							$('#thickRight'+rightbpListSize).attr('readOnly', 'readOnly');
							$('#weightRight'+rightbpListSize).attr('readOnly', 'readOnly');
							$('#blanketTypeR'+rightbpListSize).attr('readOnly', 'readOnly');
							//$('#remarksR'+rightbpListSize).attr('readOnly', 'readOnly');
							$('#widthRight'+rightbpListSize).attr('readOnly', 'readOnly');
						
							// document.forms["formID"].action="save_blanket_in_bp?rt="+$("#operationR"+rightbpListSize).val();
							// document.forms["formID"].submit(); 
						}else{
							 alert('Record is not available for this combination');
							 $('#weightRight'+rightbpListSize).val('');
						}
					
					}
			 });
			 flag=1;
		 } */
		 
		 
		
	 }
	
	}
	
function stopTimer(){
	 alert('stop');
	}
</script>
	<form:form name="input" id="formID" action="save_blanketProduction_new" modelAttribute="blanketProductionMasterNewForm" >
	<form:hidden path="blanketProductionMasterNewDTO.blanketProductionId" id="sno"/>
	<form:hidden path="lastBlanketEntryDate" id="lastDate"/>
	<form:hidden path="blanketCutoffDate" id="cutoffDate"/>
		<div class="panel-header" >
			<div class="panel-title">Blanket Production New</div>
		
		</div>

		<div align="left" class="bkgColor">
			<table width="557" height="115" border="0" class="tableview" align="left" class="">
				<tr>
					<td height="37" align="left"><label> Date<span
							style="color: #FF0000">* </span>
					</label>
					</td>
					<td><form:input path="blanketProductionMasterNewDTO.blanketProductionDate" data-maxsize="11"
						class="validate[required] text-input datepicker1" readonly="true" style="width:42%" size="17"
						id="date" />
						<form:hidden path="blanketProductionMasterNewDTO.blanketProductionDate"/>
					</td>
					<td height="37"><label> Run No.<label><span
								style="color: #FF0000">* </span>
						</label>
					</td>
					<td><form:input path="blanketProductionMasterNewDTO.runNo" class="validate[required]"
						onkeypress="return check(event)" data-maxsize="15"
						style="width: 51%;" size="20" id="runNo" />
					</td>
				</tr>
				<tr>
					<td width="101" height="24" align="left"><label> Grade<span
							style="color: #FF0000">* </span>
					</label>
					</td>
					<td width="209"><form:select path="blanketProductionMasterNewDTO.gradeMasterDTO.mastersId"
					items="${gradeMastersList}" itemLabel="name" itemValue="mastersId" style="height: 21px; width: 43%;"
						class="validate[required] text-input" id="grade"/>
						<form:hidden path="blanketProductionMasterNewDTO.gradeMasterDTO.mastersId" id="hiddenGradeId"
					items="${gradeMastersList}" itemLabel="name" itemValue="mastersId"/>
							
			
					</td>
					<td width="94" height="24">Shift <span style="color: #FF0000">*
					</span>
					</td>
					<td width="266"><form:select path="blanketProductionMasterNewDTO.shiftMasterDTO.mastersId"
					items="${shiftMastersList}" itemLabel="name" itemValue="mastersId"
						class="validate[required] text-input"
						style="height: 21px; width: 52%;" id="shift"/>
						<form:hidden path="blanketProductionMasterNewDTO.shiftMasterDTO.mastersId"
					items="${shiftMastersList}" itemLabel="name" itemValue="mastersId" id="hiddenShift"/>
					</td>
				</tr>
			
				<tr>
					<td width="101" height="24" align="left"><label> Batch No.<span
							style="color: #FF0000">* </span>
					</label>
					</td>
					<td width="209"><form:input path="blanketProductionMasterNewDTO.batchNumber"
						style="width: 121px;" onkeypress="return check(event)"
						data-maxsize="65" size="8" id="batchNo" />
					</td>
			
				<td width="94" height="24">Shift Engineer</td>
				<td width="266"><form:input path="blanketProductionMasterNewDTO.shiftEngineerName"
						style="width: 190px;" onkeypress="return check(event)"
						data-maxsize="65" size="8" id="shiftEnginner" />
				</td>
				</tr>
				<tr>
					<td width="101" height="24" align="left"><label> No of Spliter.<span
							style="color: #FF0000">* </span>
					</label>
					</td>
					<td width="209">
					<form:select path="blanketProductionMasterNewDTO.spliterCount" 	cssStyle="width:84px; " id="spliterCountSelect">
							<form:option value="1">1</form:option> 
							<form:option value="2">2</form:option>
							<form:option value="3">3</form:option>
							<form:option value="4">4</form:option>
							<form:option value="5">5</form:option>
							<form:option value="6">6</form:option>
							</form:select>
					</td>	
					<form:hidden path="blanketProductionMasterNewDTO.spliterCount" id="spliterCountId"/>	
				</tr>
			
				
				<tr>
					<td width="36" height="34" align="char"><label><strong>Production Planning</strong>
					</label>
					</td>
					<td colspan="5" width="560"><form:textarea  rows="2" cols="67" style="width: 625px; height: 40px;"
							path="blanketProductionMasterNewDTO.productionPlanning"/>
					</td>
				</tr>
				<tr>
					
				
			</table>
			
				<h4
					style="  margin-left: 6px; margin-bottom: 0px; width: 524px;">Add
					Records&nbsp;
						<c:if test="${opr!='R'}">
					<input class="newRow" id="L" title="Add" style="font-size: 11px; font-weight: bold;width:23px;  : 0 0 0 0px;" type="button" value=" "/>
					<span style="margin-left:310px;">
					 <input style="font-weight: bold;" type="button" onclick="startTimer();"
					 id="timerStart"  value="&nbsp;&nbsp;Read&nbsp;&nbsp;"> 
					</span>
					
					</c:if></h4>
					
					<div class="gridheadingdiv">
					
					<table width="668" id="tbrollL"  class="fixmyheader-8" >
  <thead>
   <tr>
				 
						<td width="32"><div align="center">								<strong>S.No.</strong>							</div>						</td>
						<td width="30"><div align="center">								<strong>Spl typ</strong>							</div>						</td>
						<td width="51"><div align="center">								<strong>Roll No.</strong>							</div>						</td>
						<td width="59"><div align="center">								<strong>Length (mm)</strong>							</div>						</td>
						<td width="63"><div align="center">								<strong>Width (mm)</strong>							</div>						</td>
						<td width="63"><div align="center">								<strong>Thick (mm)</strong>							</div>						</td>
						<td width="61"><div align="center">								<strong>Weight (Kg)</strong>						</div>						</td>
						<td width="68"><div align="center">								<strong>Density (Kg/m3)</strong>							</div>						</td>
						<td width="58"><div align="center">								<strong>Type</strong>							</div>						</td>
						<td width="70"><div align="center">								<strong>Item Name</strong>							</div>						</td>
						<td width="50"><div align="center">								<strong>Remarks</strong>							</div>						</td>
						
						<td width="24"><div align="center">
								<strong>Action</strong>
							</div>
						</td>
					</tr>
				 
					<c:set var="cursor" value="${nodes }" scope="application"></c:set> 
					<c:forEach items="${blanketProductionMasterNewForm.blanketProductionMasterNewDTO.blanketProductionDetailNewDTOList}" var="bpd" varStatus="s">
					<tbody> 
					<tr id="Lr${s.index}" >
						<td style="" width="22" ><label>${s.count }</label>
						</td>
						<td width="20" ><form:input path="blanketProductionMasterNewDTO.blanketProductionDetailNewDTOList[${s.index}].spliterType"
							style="border:1px solid #7f9db9; width: 100%" class=""
							data-maxsize="15" size="8" id="spliterType${s.index}" readonly="true" />
						</td>
						<td width="41" ><form:input path="blanketProductionMasterNewDTO.blanketProductionDetailNewDTOList[${s.index}].rollNo"
							style="border:1px solid #7f9db9; width: 100%" class="quantity2"			data-maxsize="11" size="8" id="rollnumberL${s.index}" onchange="rollnumberL(${s.index});" />
							
							<form:hidden path="blanketProductionMasterNewDTO.blanketProductionDetailNewDTOList[${s.index}].blanketProductionId" />
							<form:hidden path="blanketProductionMasterNewDTO.blanketProductionDetailNewDTOList[${s.index}].itemId" id="itemIdL${s.index}" />
						<form:hidden path="blanketProductionMasterNewDTO.blanketProductionDetailNewDTOList[${s.index}].sno" />
						</td>
						
						
						<td width="49" ><form:input path="blanketProductionMasterNewDTO.blanketProductionDetailNewDTOList[${s.index}].length"
							style="border:1px solid #7f9db9; width: 100%" class="quantity"
							data-maxsize="15" size="8" id="lengthLeft${s.index}" />
						</td>
						<td width="53"><form:input path="blanketProductionMasterNewDTO.blanketProductionDetailNewDTOList[${s.index}].width"
							style="border:1px solid #7f9db9; width: 100%" class="quantity"
							data-maxsize="15" size="8" id="widthLeft${s.index}" />
						</td>
						<td width="53" ><form:input path="blanketProductionMasterNewDTO.blanketProductionDetailNewDTOList[${s.index}].thick"
							style="border:1px solid #7f9db9; width: 100%" class="quantity"
							data-maxsize="15" size="8" id="thickLeft${s.index}" />
						</td>
						<td width="51" ><form:input path="blanketProductionMasterNewDTO.blanketProductionDetailNewDTOList[${s.index}].weight" 
							style="border:1px solid #7f9db9; width: 100%" class="quantity"
							data-maxsize="15" size="8" id="weightLeft${s.index}" />
							<input type="hidden" value="L" id="operationL${s.index}">
						</td>
						<td width="58" ><form:input path="blanketProductionMasterNewDTO.blanketProductionDetailNewDTOList[${s.index}].density" 
							style="border:1px solid #7f9db9; width: 100%" class="quantity" readonly="true"
							data-maxsize="15" size="8" id="densityLeft${s.index}" />
						</td> 
						<td width="47" ><form:select path="blanketProductionMasterNewDTO.blanketProductionDetailNewDTOList[${s.index}].blanketType"  cssStyle="width:84px; " id="blanketTypeL${s.index}">
							 <form:option value=""></form:option> 
							<form:option value="OK">OK</form:option>
							<%-- <form:option value="A Grade">A Grade</form:option> --%>
							<form:option value="R.A.">R.A.</form:option>
							<form:option value="Rejection">Rejection</form:option>
							</form:select>
						</td>
						<td width="59" ><form:input path="blanketProductionMasterNewDTO.blanketProductionDetailNewDTOList[${s.index}].itemName" style="width: 100%;border:1px solid #7f9db9;"
							data-maxsize="250" size="8" title="${bpd.itemName}" id="itemNameL${s.index}" readonly="true" />
						</td>
						<td width="40" ><form:input path="blanketProductionMasterNewDTO.blanketProductionDetailNewDTOList[${s.index}].remark" style="width: 100%;border:1px solid #7f9db9;"
							data-maxsize="250" size="8" id="remarksL${s.index}" />
						</td>
						
						<td width="15" style=" text-align: center;">
						
						<c:if test="${opr=='E' && bpd.approvedStatus!=1}">	
						<a href="#" class="editRow" id="L${s.index}"><img src="static/images/updateIcon.png" title="Update Record"
												alt="" /></a>
						<form:hidden path="blanketProductionMasterNewDTO.blanketProductionDetailNewDTOList[${s.index}].blanketProductionId"/>
												<form:hidden path="blanketProductionMasterNewDTO.blanketProductionDetailNewDTOList[${s.index}].sno"/>
						</c:if>
						<c:if test="${opr=='E' && bpd.approvedStatus==1}">	
						<img src="static/images/updateIcon.png" onclick="updateValidation();" title="Update Record"
												alt="" /></c:if>
						
						<c:if test="${opr!='R' && bpd.approvedStatus!=1}">
						<a href="#" class="remRow" id="L${s.index}"><img src="static/images/drop.png" 
							 title="Delete Record"
								alt="" /></a>
						</c:if><c:if test="${opr!='R' && bpd.approvedStatus==1}">
						<img src="static/images/drop.png" 
							 title="Delete Record"
								alt="" onclick="deleteValidation();" />
						</c:if>
						
						</td>
					</tr>
  </thead>
  <tbody>
  </c:forEach>
					
  </table>
				
					

			</div>
			<table width="557" height="38" style="margin-top: 12px;" border="0"
				align="left">
				<tr>
					<td width="36" height="34" align="center"><label><strong>Remark</strong>
					</label>
					</td>
					<td width="58"><form:textarea style="width: 100%; height: 40px;"
							path="blanketProductionMasterNewDTO.remark"/>
					</td>
					<td width="36" height="34" align="center"><label><strong>Roll Packed</strong>
					</label>
					</td>
					<td width="58" ><form:input path="blanketProductionMasterNewDTO.rollPacked"
							style="border:1px solid #7f9db9; width: 100%" class="quantity" 
							data-maxsize="15" size="8" />
						</td> 
						<td width="36" height="34" align="center"><label><strong>Carton Used</strong>
					</label>
					</td>
					<td width="58" ><form:input path="blanketProductionMasterNewDTO.cartonUsed"
							style="border:1px solid #7f9db9; width: 100%" class="quantity" 
							data-maxsize="15" size="8" />
						</td> 
				</tr>
				<tr>
				<td width="36" height="34" align="center"><label><strong>Short Length</strong>
					</label>
					</td>
					<td width="58" ><form:input path="blanketProductionMasterNewDTO.shortLenght"
							style="border:1px solid #7f9db9; width: 100%" class="quantity" 
							data-maxsize="15" size="8" />
						</td> 
						
						
						
						<td width="36" height="34" align="center"><label><strong>Edge Trim</strong>
					</label>
					</td>
					<td width="58" ><form:input path="blanketProductionMasterNewDTO.edgeTrim"
							style="border:1px solid #7f9db9; width: 100%" class="quantity" 
							data-maxsize="15" size="8" />
						</td> 
						
						
						
						
						<td width="36" height="34" align="center"><label><strong>Bulk Fiber</strong>
					</label>
					</td>
					<td width="58" ><form:input path="blanketProductionMasterNewDTO.bulkFiber"
							style="border:1px 	solid #7f9db9; width: 100%" class="quantity" 
							data-maxsize="15" size="8" />
						</td> 

				</tr>
				
				
				
			</table>


			<div class="btn" style="margin: 12px 0 0 0px; float: left;margin-left: 6px;">
				<div class="savbtn">
			<c:if test="${opr=='R'}">
				<c:url value="remove_blanketProduction" var="remove_url">
					<c:param name="id"
						value="${blanketProductionMasterNewForm.blanketProductionMasterNewDTO.blanketProductionId}"></c:param>
				</c:url>
				<a href="${remove_url}" class="removebtn"></a>
			</c:if>
			<%-- <c:if test="${opr=='V'}">
			<c:forEach items="${roleAndRightsDTOList}" var="roleAndRights">
		   <c:if test="${roleAndRights.menuId==sessionScope.menuId}">
    	  	 <c:if test="${roleAndRights.editFlag=='true'}">
		  	 <input class="edit_btn"  type="button" onclick="editMethod();" value=""/>
    	      <a href="get_blanketProduction_list"  class="cancelbtn" ></a>  
		     </c:if>
		      <c:if test="${roleAndRights.editFlag=='false'}">
		      <input class="edit_btn"  type="button" onclick="checkEdit();" value=""/>
    	      <a href="get_blanketProduction_list"  class="cancelbtn" ></a>  
		      </c:if>
		    
          </c:if>
          </c:forEach>	
   				   		
    		</c:if> --%>
			
			<%-- <c:if test="${opr=='E'}">
				<input class="updatebtn" type="submit" value=" " />
				 <a href="get_blanketProduction_list"  class="cancelbtn" ></a> 
			</c:if> --%>
				<!-- <input class="submit" type="submit" value=" " /> -->
					
				 <input class="updatebtn" type="submit" id="updateId" name="operation" value="update" />
				  <a href="get_blanketProduction_list_new"  class="cancelbtn" ></a>
			</div>
			<div style="width: 906px; margin: 0 auto;">
				<span style="margin-left: 80px;" class="errmsg"></span>
			</div>
		</div>


	</form:form>