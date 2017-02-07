<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page isELIgnored="false"%>
<script>
$(document).ready(function(){ 
$("input[readonly]").css("background-color","#ebebe4");
});
</script>


   
 <c:if test="${opr=='R'}">
 <script type="text/javascript">
		var redrctUrl='get_batch_list';
				
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
 		 var frank_param = getParam('batch_no');
 		 var sno = $("#sno").val();
 
 		 //confirm('Are you sure you want to delete?');
		 var delUrl='remove_batch?batchNo='+frank_param+'&sno='+sno;
		
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

	function editMethod() {
		var frank_param = $('#batchNo').val();
		var delUrl = 'get_batch?batch_no=' + frank_param + '&opr=E';
		window.self.location = delUrl;
	}
</script>
<c:if test="${opr=='V' && opr!=null}">
	<script type="text/javascript">
		$(document).ready(function() {
			$('input').attr('readonly', 'readonly');
			$('select').attr('disabled', 'disabled');
			$('textarea').attr('readonly', 'readonly');
			$('.datepicker').attr('disabled', 'disabled');
			$('input:radio').attr('disabled', true);
			$("input:checkbox").attr("disabled", true);
			$(".newWindow").attr("disabled", true);
			$(".newWindow1").attr("disabled", true);
			$('#mfgDate').attr('disabled', 'disabled');
			$('.datepicker2').attr('disabled', 'disabled');
		});
	</script>
</c:if>

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
 fixNumFormat();
    $(".quantity").change(function (e)  
    		{ 
    	 fixNumFormat1($(this));
    		});
});
</script>

<c:if test="${opr=='R'}">
<script>
$(document).ready(function() {
	$('input').attr('disabled','disabled');	
	$('select').attr('disabled','disabled');	
});
</script>
</c:if>

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
				abc(this);
		});
	});
	//abc($('#openingStock'));	
	});
</script>

<script>
 	jQuery(document).ready(function() {
		// binds form submission and fields to the validation engine
		//jQuery("#formID").validationEngine();
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
<!-- <script type="text/javascript">
	$(document).ready(function() {
		$("button").button();
		//$('input:text, input:password').button()   
		$(".datepicker").datepicker({
			dateFormat : "dd-mm-yy"
		});

		//      $(":submit").button()
	});
</script> -->
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
	  $( "#expiryDate" ).datepicker({
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
</script>

<script>
function calcNetRate(){
	var invoiceRate=$('#invoiceRate').val();
	var excisePerc=$('#excisePerc').val();
	var cstPerc=$('#cstPerc').val();
	var lstPerc=$('#vatPerc').val();
	var surcharge=$('#surcharge').val();
	var disPerc=$('#discountPerc').val();	
	
	if(isEmptyData(excisePerc)){
		excisePerc=0.0;
		$('#excisePerc').val(0);
	}
	
	if(isEmptyData(surcharge)){
		surcharge=0.0;
		$('#surcharge').val(0);
	}
	var excise=invoiceRate*excisePerc/100;
	var cst=invoiceRate*cstPerc/100;
	var lst=invoiceRate*lstPerc/100;
	var discount=invoiceRate*disPerc/100;
	var netRate=(0+parseFloat(invoiceRate)+parseFloat(excise)+parseFloat(cst)+parseFloat(lst)+parseFloat(surcharge))-parseFloat(discount);

	$('#netRate').val(netRate);
	

}


function isEmptyData(x){
	if (x==null || x=="")
	{
	return true;
	}
	return false;
	}
</script>

<style>
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
.ui-multiselect-checkboxes label input {
margin-left:0px;
margin-right:19px;
left:1em;
    top: 1px;
}


</style>


 <link rel="stylesheet" href="static/jquery/themes/base/jquery.ui.all.css">
		<script type="text/javascript" src="<c:url value="static/jquery/jquery-1.6.1.min.js"/>"></script>
		 <script type="text/javascript"
			src="<c:url value="static/jquery/ui/jquery-ui-1.8.13.custom.min.js"/>"></script>
		<link rel="stylesheet" type="text/css"
			href="static/jquery/css/jquery.multiselect.css" />
		<script type="text/javascript" src="<c:url value="static/jquery/jquery.multiselect.js"/>"></script>




  <script>
	$(function() {
		
	    /* var v=$('#listId').val();
	     alert(v.length);
	     for(var i=0;i<v.length;i++){
	 
	     $("#itemId").find('option').each(function(){
			if(this.value == v[i]){
			   $(this).attr("selected",true);
			}  
		 });
	     }
	     */
	    
	     
	     for ( var ele = 0; true; ele++) {
				var frm = document.forms[0];
				
			   var itemId = frm.elements["batchDTO.itemDTOList[" + ele
				       						+ "].itemId"];
				  
					if (!itemId) {
						break;
					}
			   $("#itemId").find('option').each(function(){
					if(this.value == itemId.value){
					   $(this).attr("selected",true);
					}  
				 });
	          }
	     
		 $("#itemId").multiselect({ autoOpen: true}); 
		 
		 
		/*  $("#itemId").multiselect({
			 noneSelectedText:'Select value',
			  selectedText: "# of # selected",
			  selectedList: 10,
			  header: false
			 });  */
	});
	</script>  



<script>
function check(index) {
	var name = "submitItemId"+index;
	var name1="itemId"+index;
	
/* 	 alert('check function is calling :' +name + ' Name is  ' + name1);
		
	 $("formID").action = 'id='+index;
	 $("formID").submit(); */
	 
	 $('#hiddenId').val(index);
	//document.forms[0]["id"].value = index;
    document.forms[0].submit();
}
</script>
<script>
function check1(index) {
	
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


<c:if test="${opr=='E' || opr=='V'}">
<form:form name="input" id="formID" action="update_batch" method="post"
	modelAttribute="batchForm">
<form:errors path="*" cssClass="errorblock"  element="div"  />
	<div class="panel-header">
		<div class="panel-title">Batch Master</div>
		<div class="panel-tool"></div>
	</div>

	<div align="left" class="bkgColor" style="height:297px;">
		<table width="826" height="210" border="0" class="select_box"
			align="left"
			style="margin-bottom: 20px; margin-left: 20px; float: left;">
			<tbody>
								<tr>
				<td width="400">
				<table width="0">
				<tr>
					<td nowrap="nowrap" width="85">Batch No.<span style="color: #FF0000">*</span>
					</td>
					<td>
					<form:hidden path="id" id="hiddenId" />
					<form:input class="validate[required] text-input" 
						data-maxsize="15" path="batchDTO.batchNo"  id="batchNo" size="18" />
						<form:hidden  path="batchDTO.sno"  id="sno" />
					</td>
					</tr><tr>
					<td>Mfg Date<span style="color: #FF0000">*</span>
					</td>
					<td><form:input path="batchDTO.mfgDate" size="18" id="mfgDate"
						class="datepicker1 validate[required] text-input" readonly="true" style=" width:82%"/>
					</td>
					</tr><tr>
					<td align="left">Expiry Date
					</td>
					<td><form:input path="batchDTO.expiryDate" id="expiryDate"
						size="18"
						class="datepicker2" readonly="true" style=" width:82%" />
					</td>
				</tr>
				<tr>
					<td align="left">Active<span style="color: #FF0000">*</span>
					</td>
					<td><div
							style="border: solid 1px; height: 20px; width: 82%; border-radius: 3px 3px 3px 3px; border-color: #7f9db9; background-color: #FFF;">
							<span style="    float: left;    margin-top:2px;    padding-left: 12px;"> Yes</span>
							<form:radiobutton path="batchDTO.activeStatus" value="1" 
							class="validate[required] radio"
							style="width: 10px;  margin:2px 9px; float: left;" id="activeStatus" />  
							<form:radiobutton path="batchDTO.activeStatus" value="0" 
								class="validate[required] radio"
								style="width: 10px;  margin:2px 9px;  " id="activeStatus"/> <span style="    float: left;    margin-top: 2px;  "> No</span>
						</div>
					</td>
				</tr>
				</table>
				</td>
				<td>
				<table width="0" border="1">
				<tr>
					<td width="93" nowrap="nowrap">Item Name<span style="color: #FF0000">*</span>
					</td>
					<td colspan="3">
					
					
					
					<div class="scrolltable"
									Style="height: 200px; width: 100%; background: none;">
									<table width="129" style="width: 129px;" cellpadding="0"
										cellspacing="0">
									<!-- 	<tr>

											<th style="border-bottom:1px solid #99BBE8; color:#fff; height:19px; background-color: #4e8ccf; padding-left: 3px;" width="587"> Select</th>
											<th style="border-bottom:1px solid #99BBE8;border-left:1px solid #99BBE8; color:#fff; background-color: #4e8ccf; " width="587">Item Name</th>
										</tr> -->

										<c:forEach items="${selectItemList}" var="cat" varStatus="item" >
											<tr>
													<th style="border-bottom:1px solid #99BBE8; " 
													width="316" >
													<input type="hidden" name="${selectItemList}" value="${selectItemList}" id="listId" />
													<form:hidden path="batchDTO.itemDTOList[${item.index}].itemId" style="float:left; width:40px;" 
														value="${cat.itemId}" id="itemId${cat.itemId}"  />
														</th>
														<th style="border-bottom:1px solid #99BBE8; " 
													width="316" nowrap="nowrap">
												<%-- <c:out value="${cat.itemName}" /> --%> 
														
												</th>
											</tr>
										</c:forEach>
										   <tr>
										<th nowrap="nowrap"><form:select
							path="batchDTO.itemIdsList" style="width: 350px; top: 211px; float:left;  left: 400px; display: block;"  multiple="true" 
							class="validate[required] text-input" id="itemId" >
							<form:options items="${items}"
								itemValue="itemId" itemLabel="itemName"  id="itemIds"/>
						</form:select></th>
										</tr>   
									
										
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
					
	  <c:if test="${opr=='V'}">
		<c:forEach items="${roleAndRightsDTOList}" var="roleAndRights">
		   <c:if test="${roleAndRights.menuId==sessionScope.menuId}">
    	  	 <c:if test="${roleAndRights.editFlag=='true'}">
		  	 <input class="edit_btn"  type="button" onclick="editMethod();" value=""/>
    	      <a href="get_batch_list"  class="cancelbtn" ></a>  
		     </c:if>
		      <c:if test="${roleAndRights.editFlag=='false'}">
		      <input class="edit_btn"  type="button" onclick="checkEdit();" value=""/>
    	      <a href="get_batch_list"  class="cancelbtn" ></a>  
		      </c:if>
	      </c:if>
          </c:forEach>	
		</c:if>
		<c:if test="${opr=='E'}">
			<input class="updatebtn" type="submit"  />
			<div class="cancelbtn">
			<a href="get_batch_list" class="cancelbtn"	iconCls="icon-cancel"></a>
			</div>
		</c:if>
		</div>
		<div>
			 	
   				</div>
						    
		</div>
				</td>
				</tr>
				</tbody>
			</tbody>
		</table>
		

	</div>



</form:form>
</c:if>
<c:if test="${opr=='R'}">
 
<form:form name="input" id="formID" action="" method="post"
	modelAttribute="batchForm">

	<div class="panel-header">
		<div class="panel-title">Remove Batch Master</div>
		<div class="panel-tool"></div>
	</div>

	<div align="left" class="bkgColor">
		<table width="826" height="210" border="0" class="select_box"
			align="left"
			style=" margin-left: 20px; float: left;">

			<tbody>
<tr>
				<td width="400">
				<table width="0">
				<tr>
					<td nowrap="nowrap" width="85">Batch No.<span style="color: #FF0000">*</span>
					<form:hidden  path="batchDTO.sno"  id="sno" />
					</td>
					<td><form:input class="validate[required] text-input" onkeypress="return check(event)"
						data-maxsize="15" path="batchDTO.batchNo"  id="batchNo" size="18" />
					</td>
					</tr><tr>
					<td>Mfg Date<span style="color: #FF0000">*</span>
					</td>
					<td><form:input path="batchDTO.mfgDate" size="18" id="mfgDate"
						class="datepicker1 validate[required] text-input" readonly="true" style=" width:82%"/>
					</td>
					</tr><tr>
					<td align="left">Expiry Date
					</td>
					<td><form:input path="batchDTO.expiryDate" id="expiryDate"
						size="18"
						class="datepicker2" readonly="true" style=" width:82%" />
					</td>
				</tr>
				<tr>
					<td align="left">Active<span style="color: #FF0000">*</span>
					</td>
					<td><div
							style="border: solid 1px; height: 20px; width: 82%; border-radius: 3px 3px 3px 3px; border-color: #7f9db9; background-color: #FFF;">
							<form:radiobutton path="batchDTO.activeStatus" value="1" 
							class="validate[required] radio"
							style="width: 10px;" id="activeStatus" /> Yes 
							<form:radiobutton path="batchDTO.activeStatus" value="0" 
								class="validate[required] radio"
								style="width: 10px;" id="activeStatus"/> No
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
									Style="height: 200px; width: 82%; background: none;">
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
													width="316" >
													
													<form:checkbox
														path="batchDTO.itemDTOList[${item.index}].itemId"
														style="float:left; width:40px;" 
														value="${cat.itemId}" id="itemId${cat.itemId}" onclick="check(${item.index})" />
														
														</th>
														<th style="border-bottom:1px solid #99BBE8; " 
													width="316" nowrap="nowrap">
												<c:out value="${cat.itemName}" /> 
											
												<form:hidden path="batchDTO.itemDTOList[${item.index}].itemClassId"
														style="float:left; width:40px;" 
														value="${cat.itemId}" id="submitItemId${item.index}" onclick="check(${item.index})" disabled="true"/>
														
												</th>
											</tr>
										</c:forEach></table></div></td></tr></table></td></tr>
			</tbody>
		</table>
		
		<c:url value="remove_batch" var="remove_url">
		<c:param name="sno" value="${batchForm.batchDTO.sno}"></c:param>
					<c:param name="batchNo" value="${batchForm.batchDTO.batchNo}"></c:param>
					
		</c:url>
		<div class="btn">
			<%-- <div class="savbtn">
			<a href="${remove_url}" class="removebtn" ></a> 
				
			</div>
			<div >
			 <a href="get_batch_list" class="cancelbtn" ></a>	
   
   </div> --%>
						    
		</div>
		
		
		
		

	</div>



</form:form>
</c:if>
