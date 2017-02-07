<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false"%>
<c:url value="/static/images/loading_icon.gif" var="imagelink" />
<c:url value="/static/images/006.gif" var="imagelink1" />
<c:if test="${errorList!=null}">
	<input type="hidden" id="errorId" value="${errorList.errorMsg}">
	<script type="text/javascript">
		$(document).ready(function() {
			var errorId = document.getElementById('errorId');
			alert(errorId.value);
		});
	</script>
</c:if>

<c:if test="${opr=='R'}">
	<script type="text/javascript">
		var redrctUrl = 'show_master_formula_list';

		function getParam(name) {
			name = name.replace(/[\[]/, "\\\[").replace(/[\]]/, "\\\]");
			var regexS = "[\\?&]" + name + "=([^&#]*)";
			var regex = new RegExp(regexS);
			var results = regex.exec(window.location.href);
			if (results == null)
				return "";
			else
				return results[1];
		}

		$(document).ready(function() {
			var frank_param = getParam('id');
			//confirm('Are you sure you want to delete?');
			var delUrl = 'remove_master_formula?id=' + frank_param;
			if (confirm('Are you sure you want to delete?')) {
				window.self.location = delUrl;
			} else {
				window.self.location = redrctUrl;
			}
		});
	</script>
</c:if>

 <script type="text/javascript">
 
	/****
	 find ItemGroupFlag according to item Group flag if we get flagId 
	 than go for select item Value for item combo by this function 
	 'findItemByItemGroupFlagId(data.result[0].itemId,itemCode,oldItemCode)' other wise go for 
	 select previous combo value by this function 'changeItem(itemCode)';.
	***/
 		var itemCode;
 		function findItemByCode(itemCode) {
 			$("#reportHtml1").show();
 			var oldItemCode=$("#itemCode"+itemCode).val().replace('&','%26') ;
 		  $.ajax({
 				type : "POST",
 			 	url : "find_item_group_flag_by_itemCode?itemCode=" +$("#itemCode"+itemCode).val().replace('&','%26') ,
 				success: function(data) {
 				 $("#reportHtml1").hide();
 				 if(data.result.length==0 || data.result==null)
				   {
					 alert('No Item Found');
					 changeItem(itemCode);
					}
				 else
				 {
	 			$("#itemGroupFlagId"+itemCode+" option[value="+ data.result[0].itemId + "]").attr("selected", "selected");
		 		findItemByItemGroupFlagId(data.result[0].itemId,itemCode,oldItemCode);
 	 		 //$("#itemGroupFlagId"+itemCode+" option:contains(" + data.result[0].itemId + ")").attr('selected', 'selected');
				 }
			 }
 	 		});
 		 
		}

 		/*****
 		generate item combo according to Item Group flagId fill value in combobox 
 		***/
 		
		var flagId;
 		var flgIndex;
 		var oldCode;
		function findItemByItemGroupFlagId(flagId,flgIndex,oldCode)
		{	$("#reportHtml1").show();
				 $.ajax({
				 type : "POST",
				 url : "find_item_by_itemGrupflag?itemGroupFlagId="+ flagId,
				 success: function(data) {
					 if(data.result.length==0)
					   {
						 alert('No Item Found');
						 $("#itemName"+flgIndex).find("option").remove().end();
						 $("#itemCode"+flgIndex).val('');
						 $("#uomId"+flgIndex).val('');
					   }
					 else
					 {
					
					 $("#itemName"+flgIndex).find("option").remove().end();//append(new Option("All", "All")).attr("value", "All");
					 for (var i = 0; i< data.result.length; i++) {
					 $('<option></option>').val(data.result[i].itemId).html(data.result[i].itemName).appendTo('#itemName'+flgIndex);
					 }
					 $("#reportHtml1").hide();
					 findItemByItemCode(oldCode,flgIndex);	
				 }	 
			 }
			}); 
		 }
			
		/****
 		find item record according to ItemGroupFlagId for selected ItemComboValue
 		this function is return itemDto by this we can change item selected Value.
 		
 		***/
		var itemcd;
		var itmIndx;
		function findItemByItemCode(itemcd,itmIndx)
		{
		 $("#reportHtml1").show();
		 $.ajax({
		 type : "POST",
		 url : "find_item_by_itemCode?itemCode=" +itemcd ,
		  success: function(data) {
		 		
		$("#itemName"+itmIndx+" option:contains(" + data.result[0].itemName + ")").attr('selected', 'selected');
		$("#uomId"+itmIndx).val(data.result[0].masterUnit.name);
		$("#itemCode"+itmIndx).val(data.result[0].itemCode);
		 $("#reportHtml1").hide();
		 }
		});
	  }
		
		  
		
		
	$(document).ready(function() {
		/*****
		when change value of finished goods product combo then change party type and measuerment unit
		according to change value in this combo.
		***/
		$('#finishedItemId').change(function() {
			$("#reportHtml1").show();
		$.ajax({
		 type : "POST",
		 url : "find_item_by_id?itemId=" + $("#finishedItemId").val(),
			 success: function(data) {
				 $("#reportHtml1").hide();	
				 $("#packTypeId").val(data.result[0].masterPack.name);
				 $("#measurementUnitId").val(data.result[0].masterUnit.name);
			 }
			}); 
		  return false; 
		});
	});
	

</script>

  <script type="text/javascript">
     /*****
     when change ItemGroupFlag combo value according this value itemComboValue itemCode other 
     field witch is related to this combo should be change
     ***/
     var flagIndex; 
	 function changeGroupFlag(flagIndex)
	 {
		 $("#reportHtml1").show();
		 $.ajax({
		 type : "POST",
		 url : "find_item_by_itemGrupflag?itemGroupFlagId="+  $("#itemGroupFlagId"+flagIndex).val().replace('&','%26'),
		 success: function(data) {
			 if(data.result.length==0)
			   {
				 alert('No Item Found in '+$('#itemGroupFlagId'+flagIndex+' option:selected').text());
				 updateChangeFlagId(flagIndex);
			   }
			 else
			 {
			 $("#itemCode"+flagIndex).val(data.result[0].itemCode);
			 $("#uomId"+flagIndex).val(data.result[0].masterUnit.name);
			 $("#itemName"+flagIndex).find("option").remove().end();//append(new Option("All", "All")).attr("value", "All");
			 for (var i = 0; i< data.result.length; i++) {
			 $('<option></option>').val(data.result[i].itemId).html(data.result[i].itemName).appendTo('#itemName'+flagIndex);
			 $("#reportHtml1").hide();
			 }
			 }
		 }
		}); 
	  }
	 
	 
	 /**** 
	 When find value according to item group flag combo and value coun't find than 
	 than combo should replace with previous value
	 ***/
	 var itemCdFlag;
	 function updateChangeFlagId(itemCdFlag)
	 {
		 $.ajax({
				type : "POST",
			 	url : "find_item_group_flag_by_itemCode?itemCode=" +$("#itemCode"+itemCdFlag).val().replace('&','%26') ,
				success: function(data) 
				{
				 $("#reportHtml1").hide();
				 $("#itemGroupFlagId"+itemCdFlag+" option[value="+ data.result[0].itemId + "]").attr("selected", "selected");
		 		//findItemByItemGroupFlagId(data.result[0].itemId,itemCode,oldItemCode);
	 		 //$("#itemGroupFlagId"+itemCode+" option:contains(" + data.result[0].itemId + ")").attr('selected', 'selected');
				}	 
	 		});
	 }
	 
	 /******
	 When item combo box change any item value than item code and master unit also change 
	 accoding to item value
	 ****/
	 
	 var itemIndex;
	 function changeItem(itemIndex){
		 $("#reportHtml1").show();
		 $.ajax({
				type : "POST",
				url : "find_item_by_id?itemId="+  $("#itemName"+itemIndex).val().replace('&','%26'),
				 success: function(data) {
					 $("#reportHtml1").hide();
					 $("#itemCode"+itemIndex).val(data.result[0].itemCode);
					 $("#uomId"+itemIndex).val(data.result[0].masterUnit.name);
				 }
			}); 
			
	 }
		</script>
		
		
		
	<c:if test="${opr=='A'}">
	<script type="text/javascript">
	/******
	when form is go for add new item in Add mode than it return with opr A 
	****/
	
	
		$(document).ready(	function() {
			 $("#reportHtml1").show();
			 
			$.ajax({
				 type : "POST",
				 url : "find_item_by_id?itemId=" + $("#finishedItemId").val(),
					 success: function(data) {
						 $("#reportHtml1").hide();	
						 $("#packTypeId").val(data.result[0].masterPack.name);
						 $("#measurementUnitId").val(data.result[0].masterUnit.name);
					 }
					}); 
			
			
			 var count=$("#countId").val();
			
			 count=count-1;
			$.ajax({
			type : "POST",
			url : "find_item_by_itemGrupflag?itemGroupFlagId="+  $("#itemGroupFlagId"+count).val().replace('&','%26'),
			 success: function(data) {
				 $("#itemCode"+count).val(data.result[0].itemCode);
				 $("#uomId"+count).val(data.result[0].masterUnit.name);
				 $("#itemName"+count).find("option").remove().end();//append(new Option("All", "All")).attr("value", "All");
				 for (var i = 0; i< data.result.length; i++) {
				 $('<option></option>').val(data.result[i].itemId).html(data.result[i].itemName).appendTo('#itemName'+count);
				 $("#reportHtml1").hide();
			 }}
		}); 
	});		
		</script>
	</c:if>
	
	
	<c:if test="${opr=='AE'}">
	<script type="text/javascript">
	/******
		when form is go for add new item in edit mode than it return with opr AE 
	****/
		$(document).ready(	function() {
			 var count=$("#countId").val();
			 $("#reportHtml1").show();
			 count=count-1;
			$.ajax({
			type : "POST",
			url : "find_item_by_itemGrupflag?itemGroupFlagId="+  $("#itemGroupFlagId"+count).val().replace('&','%26'),
			 success: function(data) {
				 $("#itemCode"+count).val(data.result[0].itemCode);
				 $("#uomId"+count).val(data.result[0].masterUnit.name);
				 $("#itemName"+count).find("option").remove().end();//append(new Option("All", "All")).attr("value", "All");
				 for (var i = 0; i< data.result.length; i++) {
				 $('<option></option>').val(data.result[i].itemId).html(data.result[i].itemName).appendTo('#itemName'+count);
				 $("#reportHtml1").hide();
				 }}
		}); 
	});		
		</script>
	</c:if>
<script type="text/javascript">
function checkEdit()
	{
	alert('Login User Not Permit to Edit Record !!!!!!');
	}

	function editMethod() {
		var frank_param = $('#id').val();
		var delUrl = 'get_master_formula?id=' + frank_param + '&opr=E';
		window.self.location = delUrl;
	}
</script>




<script type="text/javascript">
var index;
function removeItem(index)
{
	var opr=$('#oprId').val();
	
	document.forms["formID"].action = "remove_master_formula_item?id="+index+"&opr="+opr;
	document.forms["formID"].submit();
	 
}


	$(document).ready(function() {
		 $('#modifiedDateId').attr('readonly', 'readonly');
		 $('#createdDateId').attr('readonly', 'readonly');
		  $('.readonlyItems').attr('readonly', true);
	
		  
		//called when key is pressed in textbox
		$(".newWindow").click(function(e) {
			 /* 
			var row = $('#fixmyheader-8 tbody>tr:last').clone(true);
			  row.insertAfter('#fixmyheader-8 tbody>tr:last');
			  $('#fixmyheader-8 tbody>tr:last #itemCode0').val('');
			  var ss=parseInt(1);
			  alert($('#itemSequenceNumber0').val());
			  $('#itemSequenceNumber1').val(ss);
			   
			  alert($('#itemSequenceNumber1').val());
			 
			$.ajax({
				type : "POST",
				url : "find_item_by_itemGrupflag?itemGroupFlagId=1",
				 success: function(data) {
					 $("#itemCode"+count).val(data.result[0].itemCode);
					 $("#uomId"+count).val(data.result[0].masterUnit.name);
					 $("#itemName"+count).find("option").remove().end();//append(new Option("All", "All")).attr("value", "All");
					 for (var i = 0; i< data.result.length; i++) {
						 $('#fixmyheader-8 tbody>tr:last #itemName0').$('<option></option>').val(data.result[i].itemId).html(data.result[i].itemName);
					 $("#reportHtml1").hide();
					 }}
			}); 
			  //$('#fixmyheader-8 tbody>tr:last #itemName0').$('<option></option>').val(data.result[i].itemId).html(data.result[i].itemName).appendTo('#itemName'+count);
			  
			  
			 */
			 
			
		 	document.forms["formID"].action = "add_item_master_formula?opr=AE";
			document.forms["formID"].submit(); 
		});
	});

		$(document).ready(function() {
			$(".newWindow1").click(function(e) {
				document.forms["formID"].action = "add_item_master_formula?opr=A";
				document.forms["formID"].submit();

			});
		});
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
			$(".delelteImg").attr("disabled", true);
			$('.datepicker2').attr('disabled', 'disabled');
		});
	</script>
</c:if>


	
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
	 $(document).ready(
			function() {
				$(".quantity").each(function(){
					var v=(!$(this).val())? 0 : parseFloat($(this).val());
					//var v=parseFloat($(this).val());
					v=v.toFixed(5);
				//	$(this).val(v);
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


<script type="text/javascript">
	$(document)
			.ready(
					function() {

						$("button").button();
						$("#lightness")
								.click(
										function() {
											$('#link')
													.attr('href',
															'jquery-ui/css/ui-lightness/jquery-ui-1.8.4.custom.css');
										});
						$("#hotsneaks")
								.click(
										function() {
											$('#link')
													.attr('href',
															'jquery-ui/css/hot-sneaks/jquery-ui-1.8.4.custom.css');
										});
						$("#flick")
								.click(
										function() {
											$('#link')
													.attr('href',
															'jquery-ui/css/flick/jquery-ui-1.8.4.custom.css');
										});
						$("#redmond")
								.click(
										function() {
											$('#link')
													.attr('href',
															'jquery-ui/css/redmond/jquery-ui-1.8.4.custom.css');
										});
						$("#smoothness")
								.click(
										function() {
											$('#link')
													.attr('href',
															'jquery-ui/css/smoothness/jquery-ui-1.8.4.custom.css');
										});

						$('#fixmyheader-1').fixheadertable({
							caption : 'My header is fixed !',
							zebra : true
						});

						$('#fixmyheader-2').fixheadertable({
							caption : 'My header is fixed !',
							height : 200,
							whiteSpace : 'normal'
						});

						$('#fixmyheader-3').fixheadertable({
							caption : 'My header is fixed !',
							height : 200
						});

						$('#fixmyheader-4').fixheadertable({
							caption : 'My header is fixed !',
							width : 400,
							height : 150
						});

						$('#fixmyheader-5').fixheadertable({
							caption : 'My header is fixed !',
							height : 200,
							minWidth : 840
						});

						$('#fixmyheader-6').fixheadertable({
							caption : 'My header is fixed !',
							height : 200,
							minWidthAuto : false
						});

						$('#fixmyheader-7').fixheadertable({
							caption : 'My header is fixed !',
							height : 200
						});

						$('#fixmyheader-8').fixheadertable(
								{
									caption : 'My header is fixed !',
									height : 150,
									addTitles : true,
									colratio : [ '10%', '10%', '8%', '50px',
											'auto', 'auto', '30%', 'auto' ]
								});
					});

	function checkAmount() {
		
		if ($("#formulaBatchId").val() == undefined || $("#formulaBatchId").val() == 0) {
			
			alert("Pls Insert Data In Formula Batch Size.");
			return false;
		}
	
		if ($("#snoid").val() == undefined) {
			alert("Sorry you can not save record without item.");
			return false;
		}
		
		for ( var ele = 0; true; ele++) {
			var frm = document.forms[0];
			
			var qt = frm.elements["masterFormulaMasterDTO.masterFormulaDetailDTOList[" + ele
					+ "].quantity"];
			if (!qt) {
				break;
			}
			
			if (qt.value == undefined || qt.value == 0) {
				alert('Recieve quantity can not be zero or null');
				return false;
			}
		}
		
		return true;
	}
	
</script>


<style type="text/css">
/* 
#reportHtml1
 { position: absolute; top: 0; left: 0; right: 0; bottom: 0; background-color: #000; opacity: 0.7; }
 */
 #reportHtml1
{position: fixed;
top: 0;
left: 0;
width: 100%;
height: 100%;
background-color: #000;
z-index: 1000;  
opacity: 0.3;  
display: none;

}
body {
	font-family: Arial, Helvetica, sans-serif;
}

.tableview {
	border: 1px solid #7F9DB9;
	border-radius: 3px 3px 3px 3px;
	margin-bottom: 7px;
	margin-left: 5px;
	margin-top: 5px;
	padding: 7px;
	width: 961px;
}

code,pre {
	padding: 10px;
	background: #F5F5F5;
	border: 1px solid #D4D4D4;
	overflow-x: auto;
	font-size: 12px;
}

th {
	font-size: 10px;
}

td {
	font-size: 12px;
}

div.t_fixed_header_main_wrapper.ui div.t_fixed_header_caption {
	display: none;
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

table {
	width: 967;
}

.gridheadingdiv td {
	height: 22px;
}

.gridheadingdiv input {
	border: medium none;
	width: 75px;
}

.gridheadingdiv {
	width: 967px;
	!
	important
}

.newWindow {
	background: url(static/images/new.png);
	background-repeat: no-repeat;
	height: 18px;
	border: none;
	width: 18px;
}

.newWindow1 {
	background: url(static/images/new.png);
	background-repeat: no-repeat;
	height: 18px;
	border: none;
	width: 18px;
	cursor: pointer;
}

.errorblock {
	color: #000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	margin-bottom: 16px;
	width: 82%;
}
</style>


<form:form name="input" id="formID" action="save_master_formula"
	method="post" modelAttribute="masterFormulaForm">
<form:hidden path="masterFormulaMasterDTO.masterFormulaAutoId" id="id" />
		
<div class="panel-header" style="width: 960px;">
		<div class="panel-title">Master Formula Entry</div>
		<div class="panel-tool"></div>
	</div>
	<input type="hidden" value="${opr}" id="oprId">

	<div align="left" class="bkgColor" 	style="height: auto; padding-bottom: 7px;  width: 969px;">

		<table height="114" width="900" border="0" style="padding: 21px; height: 270px;"  class="tableview">
			<tr>
				<td height="20">Finished Product <span style="color: #FF0000">*</span>
				</td>
				<td colspan="4">
				<form:select path="masterFormulaMasterDTO.itemDTO.itemId" tabindex="1"
				items="${itemFinishedGoodsList}" itemLabel="itemName" itemValue="itemId" id="finishedItemId">
					</form:select></td>
			</tr>
			<tr>
				<td width="113" height="20">Product Information	</td>
				<td width="20"  style="width:100px;">
				<%-- <img style="display:none" alt="loading"  id="loadingImageHtmlReport" src="${imagelink}"/> --%>
				<div id="reportHtml" align="center" style="position:absolute;width:100%;display:none"" > 
 				<img  style="position:fixed;align:center;left:32%;top:30%"  src="${imagelink}"   />
   			</div>
				
				
				Pack</td>
				<td width="108"><form:input type="text" onkeypress="return check(event)" path="masterFormulaMasterDTO.itemDTO.masterPack.name" 
				data-maxsize="65" style="width:100px;" value="" class="readonlyItems" size="11" id="packTypeId" />
				</td>
				<td width="99">Unit</td>
				<td width="115">
				<form:input type="text" onkeypress="return check(event)" path="masterFormulaMasterDTO.itemDTO.masterUnit.name" 
				data-maxsize="65" style="width:100px;" value="" class="readonlyItems" size="11" id="measurementUnitId" />
				
				</td>
				
			</tr>
			<tr>
				
		<td>Standard Batch Size </td>
		<td><form:input type="text" onkeypress="return check(event)" path="masterFormulaMasterDTO.standardBatchSize" 
			class="quantity validate[custom[number]]" tabindex="2"	data-maxsize="65" style="width:100px;" size="11" id="standardBatchId" />
		</td>
		<td></td>
	
		<td>Formula Batch Size<span style="color: #FF0000">*</span></td>
		<td><form:input type="text" onkeypress="return check(event)" path="masterFormulaMasterDTO.formulaBatchSize" data-maxsize="65"
				class="quantity validate[custom[number]]" size="11" tabindex="3" style="width:100px" id="formulaBatchId" />
		
		
		</td>
			</tr>
			 <tr>
				<td>Date Entered</td>
				<td><form:input onkeypress="return check(event)"
						path="masterFormulaMasterDTO.creationDate" data-maxsize="65" 
						size="11" id="createdDateId"  style="width:100px"  />
				</td>
				<td></td>
				<td>Date Modified</td>
				<td>
				<form:input onkeypress="return check(event)" style="width:100px" onblur="valid(this)"
						path="masterFormulaMasterDTO.modifiedDate" data-maxsize="65" 
						size="11" id="modifiedDateId" />
				</td>
			</tr>
		<tr>
				<td>Created by</td>
				<td><form:input type="text" onkeypress="return check(event)"
						path="masterFormulaMasterDTO.createdBy" data-maxsize="65" style="width:100px" 
						size="11" class="readonlyItems" id="createdById" />
				</td><td></td>
				
				<td>Approved by</td>
				<td><form:input type="text" onkeypress="return check(event)"
						style="width:100px" onblur="valid(this)"
						path="masterFormulaMasterDTO.approvedBy" data-maxsize="65"
						size="11" class="" id="approvedById" />
				</td>
			</tr>
			
			<tr>
				<td>Remark</td>
				<td colspan="5"><form:textarea rows="0" cols="93" path="masterFormulaMasterDTO.formulaRemark"
				  onkeypress="return check(event)"  tabindex="4" id="formulaRemark" />
				</td>
			</tr> 
			<tr>
				<td>Formula Status</td>
		<td><form:select path="masterFormulaMasterDTO.activeStatus" tabindex="5" style="width:100px" itemLabel="name" class="validate[required]">
			 	<form:option value="1">Active</form:option>
			 	<form:option value="0">InActive</form:option>
			</form:select> <div id="reportHtml1" align="center" style="position:absolute;width:100%;display:none"" > 
 				<img  style="position:fixed;align:center;left:40%;top:28%;"  src="${imagelink1}"   />
   			</div>
				</td>
			
			</tr></table>
			
			
	<div class="gridheadingdiv">
	 <table width="668" class="fixmyheader" id="fixmyheader-8">
	  <thead>
		<tr>
		  <td width="24"><div align="center"><strong>S No.</strong></div></td>
		
							<td width="111"><div align="center">
									<strong>Item Group Flag</strong>
								</div>
							</td>
							
							
		<td width="111"><div align="center"><strong><c:choose>
		  	<c:when test="${opr=='E' || opr=='AE'}">
			 <input tabindex="6" class="newWindow" style="font-size: 11px; font-weight: bold; width: 18px; 
			 padding: 0 0 0 0px;"  value=" " />
	 		</c:when>
		  <c:otherwise>

		<input tabindex="6" class="newWindow1" style="font-size: 11px; font-weight: bold; width: 18px;"	type="submit" value=" " />
		</c:otherwise>
		</c:choose>Item Name </strong>
			
		 </div>	</td>
			<td width="45"><div align="center"><strong>Item Code</strong>
								</div>
							</td>
							
							<td width="41"><div align="center">
									<strong>Quantity </strong>
								</div>
							</td>
							<td width="70"><div align="center">
									<strong>UOM </strong>
								</div>
							</td>

				<td width="120">
				<div align="center">
					<strong>Remark</strong>
								</div>
							</td>
							<td width="26"><div align="center">
									<strong>Action</strong>
								</div>
							</td>
						</tr>
					</thead>
					<tbody>
			<%	int i = 1;	%>
		<c:forEach items="${masterFormulaForm.masterFormulaMasterDTO.masterFormulaDetailDTOList}"
			var="e" varStatus="s" >
		<tr class="sport-row">
			<td width="27">
	 		<form:input tabindex="7"
			 path="masterFormulaMasterDTO.masterFormulaDetailDTOList[${s.index}].itemSequenceNumber"
			 class="quantity1 validate[custom[number]]" style="width:100%; border:1px solid #7f9db9; "
			 data-maxsize="3" size="15" value="${s.count}"  id="itemSequenceNumber${s.index}" /> 
			 <input type="hidden"  	value="${masterFormulaForm.masterFormulaMasterDTO.masterFormulaDetailDTOList.size()}" id="countId">
			 <input type="hidden" name="snoid" 	value="${s.count}" id="snoid">
	 		</td>
								
			<td width="156">
			<form:select tabindex="8"  path="masterFormulaMasterDTO.masterFormulaDetailDTOList[${s.index}].itemGroupFlagId"
			 items="${itemGroupFlagList}" onchange="changeGroupFlag(${s.index})"
		            itemLabel="itemGroupFlagName" itemValue="itemGroupFlagId" id="itemGroupFlagId${s.index}">
			<%--  <form:options items="${e.itemGroupFlagDTOList}" itemLabel="itemGroupFlagId" itemValue="itemGroupFlagId" /> --%> 
			</form:select>
			</td>
	
			<td width="158">
			<form:select tabindex="9" path="masterFormulaMasterDTO.masterFormulaDetailDTOList[${s.index}].itemDTO.itemId" 
				items="${itemList}" itemValue="itemId"  itemLabel="itemName" onchange="changeItem(${s.index})" id="itemName${s.index}"  >
				  <%-- <form:options items="${itemList}" itemLabel="itemName" itemValue="itemName" />    --%>
			</form:select></td>
			
			<td width="57"> 
			<form:input tabindex="10" path="masterFormulaMasterDTO.masterFormulaDetailDTOList[${s.index}].itemDTO.itemCode"
			  data-maxsize="50" onkeypress="return check(event)" onchange="findItemByCode(${s.index})" style="  width:80%; border:1px solid #7f9db9; " size="20" id="itemCode${s.index}" /> 
			</td>
			
			<td width="52"><form:input tabindex="11"
			 path="masterFormulaMasterDTO.masterFormulaDetailDTOList[${s.index}].quantity"
			 class="quantity validate[custom[number]]" style="  width:100%; border:1px solid #7f9db9; "
			 data-maxsize="15"  size="15" id="itemQuantity${s.index}" /> 
			</td>
			
			
			<td width="95">
			 <form:input path="masterFormulaMasterDTO.masterFormulaDetailDTOList[${s.index}].itemDTO.masterUnit.name"  data-maxsize="50" 
			 style="width:80%; border:1px solid #7f9db9;" class="readonlyItems" size="8"	id="uomId${s.index}" /> 
			</td>
			
			<td width="170">
			 <form:input tabindex="12"
			path="masterFormulaMasterDTO.masterFormulaDetailDTOList[${s.index}].itemRemark"
			onkeypress="return check(event)" data-maxsize="50" style="  width:100%; border:1px solid #7f9db9; " size="8"
			id="itemRemark${s.index}" /> 
			</td>
			
			<td width="30">  
			 <input  class="delelteImg"  type="image" src="static/images/drop.png"  tabindex="13" style="width:12px;height: 12px; " onclick="removeItem(${s.index})"/>
			<%-- <img class="delelteImg" tabindex="13" src="static/images/drop.png" onclick="removeItem(${s.index})" title="Delete Record" alt="" /> --%>
				<!-- </a> --></td>		
			
			</tr>
			</c:forEach>

			</tbody>
			</table>
		</div>
		
			 <div class="btn" style="float: none;">
				<div class="savbtn">
					<c:choose>
						<c:when test="${opr=='R'}">
							<c:url value="remove_purchaseOrder" var="remove_url">
								<c:param name="id"
									value=""></c:param>
							</c:url>
							<%-- <a href="${remove_url}" class="removebtn"></a> --%>
						</c:when>
					<c:otherwise>
						<c:choose>
						<c:when test="${opr=='E' || opr=='AE'}">
						<input class="updatebtn" type="submit"  onclick="return checkAmount();"/>
									<div class="cancelbtn">
										<a href="show_master_formula_list" class="cancelbtn"
											iconCls="icon-cancel"></a>
									</div>
								</c:when>
		<c:when test="${opr=='V'}">
		<c:forEach items="${roleAndRightsDTOList}" var="roleAndRights">
		   <c:if test="${roleAndRights.menuId==sessionScope.menuId}">
    	  	 <c:if test="${roleAndRights.editFlag=='true'}">
		  	 <input class="edit_btn"  type="button" onclick="editMethod();" value=""/>
    	      <a href="show_master_formula_list"  class="cancelbtn" ></a>  
		     </c:if>
		      <c:if test="${roleAndRights.editFlag=='false'}">
		      <input class="edit_btn"  type="button" onclick="checkEdit();" value=""/>
    	      <a href="show_master_formula_list"  class="cancelbtn" ></a>  
		      </c:if>
		    
          </c:if>
          </c:forEach>	
						</c:when>
								<c:otherwise>
									<input class="submit" onclick="return checkAmount();"
										style="font-size: 11px; font-weight: bold; padding: 0 0 0 30px;"
										type="submit" value=" "  />
										<!-- onclick="return checkAmount();" -->
									<div class="cancelbtn">
										<a href="show_master_formula_list" class="cancelbtn"
											iconCls="icon-cancel"></a>
									</div>
								</c:otherwise>
							</c:choose>
						</c:otherwise>
					</c:choose>
				</div>
			</div> 
			</div>
			
			</form:form>