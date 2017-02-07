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

 <c:if test="${opr=='R'}">
 <script type="text/javascript">
		var redrctUrl='get_bulkFiber_list';
				
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
		 var delUrl='remove_bulkFiber?id='+frank_param;
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
	 var delUrl='get_bulkFiber?id='+frank_param+'&opr=E';
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
	 
});
</script>
</c:if> 		
 	
<script type="text/javascript">
			
			$(document).ready(function() {  	
				
				 
				
				$('.fixmyheader-8').fixheadertable({
					caption		: 'My header is fixed !',
					height		:102,
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




 <script type="text/javascript">
$(document).ready(function(){ 
    //called when key is pressed in textbox
	$(".newRow").click(function (e)  
	{ 	
	document.forms["formID"].action="add_row_in_bf?rt="+$(this).attr('id');
	document.forms["formID"].submit();	
   });
	$(".remRow").click(function (e)  
			{ 
			document.forms["formID"].action="remove_row_from_bf?rs="+$(this).attr('id');
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
		
		height: 124px;
		}
	.remRow{}
</style> 
<script>
	$(document).ready(function() {
		$(".cancelbtn").click(function() {
			window.self.location = "get_bulkFiber_list";
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
	    $(".datepicker1" ).datepicker({dateFormat : 'dd-M-yy',changeMonth: true,
	          changeYear: true, yearRange: '-99:+0', maxDate: '+0M +0D'});
      });
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

<script>
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

$(document).ready(function() {
	function formChange() {
		var billQty=0;
		var sumOfTotalBag=0;
		var sumOfTotalBulk=0;
		for ( var ele = 0; true; ele++) {
			var frm = document.forms[0];
			
			billQty= frm.elements["bulkFiberMasterDTO.bulkFiberDetailDTOList["
									+ ele + "].quantity"];
			if (!billQty) {
				break;
			}
			
		var totaolBag= frm.elements["bulkFiberMasterDTO.bulkFiberDetailDTOList["+ ele + "].noOfBag"];
		var totalBulk= frm.elements["bulkFiberMasterDTO.bulkFiberDetailDTOList["+ ele + "].totalBag"];
		
		 sumOfTotalBag +=getInt(totaolBag.value);
		 sumOfTotalBulk +=getInt(totalBulk.value);
		}
		$("#totalBag").val(sumOfTotalBag);
		$("#totalBulk").val(sumOfTotalBulk); 
		
		}
	$('#formID').change(function() {
		formChange();
	});
	         formChange();
	
});
</script>


<html><body>
	<form:form name="input" id="formID" action="save_bulkFiber" modelAttribute="bulkFiberMasterForm" >
	<form:hidden path="bulkFiberMasterDTO.bulkFiberId" id="sno"/>
		<div class="panel-header" >
			<div class="panel-title">Bulk Fiber Entry Form</div>
		
		</div>

		<div align="left" class="bkgColor">
			<table width="557" height="115" border="0" class="tableview" align="left" class="">
				<tr>
					<td height="37" align="left"><label> Date<span
							style="color: #FF0000">* </span>
					</label>
					</td>
					<td  ><form:input path="bulkFiberMasterDTO.bulkFiberDate" data-maxsize="11"
						class="validate[required] text-input datepicker1" readonly="true" style="width:42%" size="17"
						id="date" />
					</td>
					<td height="37"><label> Run No.<label><span
								style="color: #FF0000">* </span>
						</label>
					</td>
					<td><form:input path="bulkFiberMasterDTO.runNo" class="validate[required]"
						onkeypress="return check(event)" data-maxsize="15"
						style="width: 51%;" size="20" id="code" />
					</td>
				</tr>
				<tr>
					<td width="101" height="24" align="left"><label> Grade<span
							style="color: #FF0000">* </span>
					</label>
					</td>
					<td width="209"><form:select path="bulkFiberMasterDTO.gradeMasterDTO.mastersId"
					items="${gradeMastersList}" itemLabel="name" itemValue="mastersId"
						style="height: 21px; width: 43%;"
						class="validate[required] text-input" id="grade"/>
							
			
					</td>
					<td width="94" height="24">Shift <span style="color: #FF0000">*
					</span>
					</td>
					<td width="266"><form:select path="bulkFiberMasterDTO.shiftMasterDTO.mastersId"
					items="${shiftMastersList}" itemLabel="name" itemValue="mastersId"
						class="validate[required] text-input"
						style="height: 21px; width: 52%;" id="shift"/>
					</td>
				</tr>
			
				<tr>
					<td width="101" height="24" align="left"><label> Batch No.<span
							style="color: #FF0000">* </span>
					</label>
					</td>
					<td width="209"><form:input path="bulkFiberMasterDTO.batchNo"
						style="width: 121px;" onkeypress="return check(event)"
						data-maxsize="65" size="8" id="batchNo" />
					</td>
			
				<td width="94" height="24">Shift Engineer</td>
				
				<td width="266"><form:input path="bulkFiberMasterDTO.shiftEng"
						style="width: 190px;" onkeypress="return check(event)"
						data-maxsize="65" size="8" id="shiftEnginner" />
				</td>
				</tr>
			
				
				<tr>
					<td width="36" height="34" align="char"><label><strong>Production Planning</strong>
					</label>
					</td>
					<td colspan="5" width="560"><form:textarea  rows="2" cols="67" style="width: 625px; height: 40px;"
							path="bulkFiberMasterDTO.productionPlanning"/>
					</td>
				</tr>
				<tr>
					
				
			</table>
			
 			<h4
					style="  margin-left: 6px; margin-bottom: 0px; width: 524px;">Records&nbsp;
					<c:if test="${opr!='R'}">
					<input class="newRow" id="L" title="Add" style="font-size: 11px; font-weight: bold;width:23px;  : 0 0 0 0px;" type="button" value=" "/></c:if></h4>
					<div class="gridheadingdiv">
<table width="668" id="tbrollL"  class="fixmyheader-8" >
  <thead>
  
 <!--  <tr>
  <h4 style="  margin-left: 6px; margin-bottom: 0px; width: 524px;">Left
					Records&nbsp;
					<input class="newRow" type="button" onclick="addrow('tbrollL')"  value=""></h4>
  </tr>
   -->
   <tr>
				 
						<td width="32"><div align="center">								<strong>S.No.</strong>							</div>						</td>
						<td width="51"><div align="center">								<strong>Quantity/Bag</strong>							</div>						</td>
						<td width="59"><div align="center">								<strong>No of Bag</strong>							</div>						</td>
						<td width="63"><div align="center">								<strong>Total(in kg)</strong>							</div>						</td>
						<td width="63"><div align="center">								<strong>Remark</strong>							</div>						</td>
						<td width="58"><div align="center">
								<strong>Action</strong>
							</div>
						</td>
					</tr>
				 
					
					<c:forEach items="${bulkFiberMasterForm.bulkFiberMasterDTO.bulkFiberDetailDTOList}" var="bpd" varStatus="s">
					<tbody> 
					<tr id="Lr${s.index}">
						<td style="" width="22" ><label>${s.count }</label></td>
						<td width="41" ><form:input path="bulkFiberMasterDTO.bulkFiberDetailDTOList[${s.index}].quantity"
							style="border:1px solid #7f9db9; width: 100%" class="quantity2"			data-maxsize="11" size="8" id="totalquaqntity" />
						</td>
						<td width="49" ><form:input path="bulkFiberMasterDTO.bulkFiberDetailDTOList[${s.index}].noOfBag"
							style="border:1px solid #7f9db9; width: 100%" class="quantity2"
							data-maxsize="15" size="8" id="noOfBag" /> 
						</td>
						<td width="53"><form:input path="bulkFiberMasterDTO.bulkFiberDetailDTOList[${s.index}].totalBag"
							style="border:1px solid #7f9db9; width: 100%" class="quantity2"
							data-maxsize="15" size="8" id="width" />
						</td>
						<td width="53" ><form:input path="bulkFiberMasterDTO.bulkFiberDetailDTOList[${s.index}].remark"
							style="border:1px solid #7f9db9; width: 100%" 
							data-maxsize="15" size="8" id="thick" />
						</td>
						<td width="49" style=" text-align: center;">
						<c:if test="${opr!='R'}">
						<a href="#" class="remRow" id="L${s.index}"><img src="static/images/drop.png" 
							 title="Delete Record"
								alt="" /></a>
						</c:if>
						<c:if test="${opr=='E'}">		
						<form:hidden path="bulkFiberMasterDTO.bulkFiberDetailDTOList[${s.index}].bulkFiberId"/>
						<form:hidden path="bulkFiberMasterDTO.bulkFiberDetailDTOList[${s.index}].sno"/>
						</c:if>
						</td>
					</tr>
  
  <tbody>
</c:forEach>
<tr>
<td>

</td><td>
     
</td>
<td>
<center>Total no of Bags</center>
<form:input path="bulkFiberMasterDTO.totalBag" id="totalBag" style="border:0px; solid #7f9db9; width: 100%" readonly="true"/>

</td>
<td>
<center>Total Bulk Fiber(in kg)</center>
<form:input path="bulkFiberMasterDTO.totalBulk" id="totalBulk" style="border:0px; solid #7f9db9; width: 100%" readonly="true"/>

</td>
</tr>

</thead>
</table>
				
</div>
			


			<div class="btn" style="margin: 12px 0 0 0px; float: left;margin-left: 6px;">
				<div class="savbtn">
			<c:if test="${opr=='R'}">
				<c:url value="remove_bulkFiber" var="remove_url">
					<c:param name="id"
						value="${bulkFiberMasterForm.bulkFiberMasterDTO.bulkFiberId}"></c:param>
				</c:url>
				<a href="${remove_url}" class="removebtn"></a>
			</c:if>
			<c:if test="${opr=='V'}">
			<c:forEach items="${roleAndRightsDTOList}" var="roleAndRights">
		   <c:if test="${roleAndRights.menuId==sessionScope.menuId}">
    	  	 <c:if test="${roleAndRights.editFlag=='true'}">
		  	 <input class="edit_btn"  type="button" onclick="editMethod();" value=""/>
    	      <a href="get_bulkFiber_list"  class="cancelbtn" ></a>  
		     </c:if>
		      <c:if test="${roleAndRights.editFlag=='false'}">
		      <input class="edit_btn"  type="button" onclick="checkEdit();" value=""/>
    	      <a href="get_bulkFiber_list"  class="cancelbtn" ></a>  
		      </c:if>
		    
          </c:if>
          </c:forEach>	
   				   		
    		</c:if>
			
			<c:if test="${opr=='E'}">
				<input class="updatebtn" type="submit" value=" " />
				 <a href="get_bulkFiber_list"  class="cancelbtn" ></a> 
			</c:if>
			<c:if test="${opr!='R' && opr!='E' && opr!='V'}">
				<input class="submit" type="submit" value=" " />
				 <a href="get_bulkFiber_list"  class="cancelbtn" ></a> 
			</c:if>
		 
			</div>
			<div style="width: 906px; margin: 0 auto;">
				<span style="margin-left: 80px;" class="errmsg"></span>
			</div>
		</div>


	</form:form>
	</body></html>