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
var redrctUrl='get_annealingOven_list';

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
var frank_param = getParam('ovenId');
// confirm('Are you sure you want to delete?');
var delUrl='remove_annealingOven?ovenId='+frank_param;
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

function editMethod()
{
var frank_param = $('#ovenId').val();
var delUrl='get_annealingOven?ovenId='+frank_param+'&opr=E';
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
$(".timePick").attr("disabled", true);
$(".remRow").attr("disabled", true);


});
</script>
</c:if>


<script type="text/javascript">

$(document).ready(function() {
$('.fixmyheader-8').fixheadertable({
caption : 'My header is fixed !',
height :160,
addTitles : true,
colratio : ['10%', '10%', '8%', '50px', 'auto', 'auto', '30%', 'auto']
});
});
</script>


<style type="text/css">
div.t_fixed_header table {
	border-collapse: separate !important;
}

#tbrollL td {
	border-color: solid;
	border-width: 0 1px 1px 0;
}

body {
	font-family: Arial, Helvetica, sans-serif;
}

code,pre {
	padding: 10px;
	background: #F5F5F5;
	border: 1px solid #D4D4D4;
	overflow-x: auto;
	font-size: 12px;
}

div.t_fixed_header_main_wrapper.ui {
	float: left;
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
<script type="text/javascript">
$(document).ready(function(){
function fixNumFormat(){
$(".quantity").each(function(){
var v=parseFloat($(this).val());
v=v.toFixed(2);
if(v=='NaN'){
v='0.00';
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
document.forms["formID"].action="add_row_in_annealingOven";
document.forms["formID"].submit();
});

$(".remRow").click(function (e)
{

document.forms["formID"].action="remove_annealingOven_item?id="+$(this).attr('id');
document.forms["formID"].submit();
});
});
</script>


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
/* .ui-widget-content {
height: 113px;
} */
.remRow {
	
}
</style>

<script type="text/javascript">
var timeId;

$(document).ready(function() {
$(".cancelbtn").click(function() {
window.self.location = "get_annealingOven_list";
});

for(var i=0;i<50;i++)
{
$("#ovenTime"+i).timepicker({});
}

$('.timePick').timepicker({ onClose: function(dateText, inst) {
var listSize= $("#listSize").val();
var cc=$(this).attr('id');
var ovenCurrenDate=$("#"+cc).val();
for(var i=0;i<listSize;i++)
{
if("ovenTime"+i!==cc)
{
if($("#ovenTime"+i).val()==$("#"+cc).val())
{
alert('Time can not be duplicate in list Pls Select');
$("#"+cc).focus();
}
}
}
}});
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
function onSave()
{
if($("#snoid").val()==undefined){
alert("Sorry you can not save record without item.");
return false;
}
}

$(document).ready(function() {

var hsdDipReading=0;
var totalVal=0;
var totalVal1=0;

$('#initialReading').change(function() {
if($('#finalReading').val()!=='' && $('#initialReading').val()!=='' && $('#totalReading1').val()!=='')
{
if(parseInt($('#finalReading').val())>parseInt($('#initialReading').val()))
{
alert('Initial value can not be less then Final value');
}
else
{
totalVal1=$('#totalReading1').val();
totalVal= $('#initialReading').val()-$('#finalReading').val();
$('#totalReading').val($('#initialReading').val()-$('#finalReading').val());
hsdDipReading=parseInt(totalVal) + parseInt(totalVal1);
$('#hsdDipReading').val(hsdDipReading*0.96);
}
}

else if($('#finalReading').val()!=='' && $('#initialReading').val()!=='')
{
if(parseInt($('#finalReading').val())>parseInt($('#initialReading').val()))
{
alert('Initial value can not be less then Final value');
}
else
{
$('#totalReading').val($('#initialReading').val()-$('#finalReading').val());
$('#hsdDipReading').val(($('#initialReading').val()-$('#finalReading').val())*0.96);
}
}
});

$('#finalReading').change(function() {
if($('#finalReading').val()!=='' && $('#initialReading').val()!=='' && $('#totalReading1').val()!=='')
{
if(parseInt($('#finalReading').val())>parseInt($('#initialReading').val()))
{
alert('Initial value can not be less then Final value');
}
else
{
totalVal1=$('#totalReading1').val();
totalVal= $('#initialReading').val()-$('#finalReading').val();
$('#totalReading').val($('#initialReading').val()-$('#finalReading').val());
hsdDipReading=parseInt(totalVal) + parseInt(totalVal1);
$('#hsdDipReading').val(hsdDipReading*0.96);
}
}

else if($('#finalReading').val()!=='' && $('#initialReading').val()!=='')
{
if(parseInt($('#finalReading').val())>parseInt($('#initialReading').val()))
{
alert('Initial value can not be less then Final value');
}
else
{
$('#totalReading').val($('#initialReading').val()-$('#finalReading').val());
$('#hsdDipReading').val(($('#initialReading').val()-$('#finalReading').val())*0.96);
}
}
});

$('#initialReading1').change(function() {
if($('#finalReading1').val()!=='' && $('#initialReading1').val()!=='' && $('#totalReading').val()!=='')
{
if(parseInt($('#finalReading1').val())>parseInt($('#initialReading1').val()))
{
alert('Initial value can not be less then Final value');
}
else
{
totalVal=$('#totalReading').val();
totalVal1= $('#initialReading1').val()-$('#finalReading1').val();
$('#totalReading1').val($('#initialReading1').val()-$('#finalReading1').val());
hsdDipReading=parseInt(totalVal) + parseInt(totalVal1);
$('#hsdDipReading').val(hsdDipReading*0.96);
}
}

else if($('#finalReading1').val()!=='' && $('#initialReading1').val()!=='')
{
if(parseInt($('#finalReading1').val())>parseInt($('#initialReading1').val()))
{
alert('Initial value can not be less then Final value');
}
else
{
$('#totalReading1').val($('#initialReading1').val()-$('#finalReading1').val());
$('#hsdDipReading').val(($('#initialReading1').val()-$('#finalReading1').val())*0.96);
}
}
});

$('#finalReading1').change(function() {
if($('#finalReading1').val()!=='' && $('#initialReading1').val()!=='' && $('#totalReading').val()!=='')
{
if(parseInt($('#finalReading1').val())>parseInt($('#initialReading1').val()))
{
alert('Initial value can not be less then Final value');
}
else
{
totalVal=$('#totalReading').val();
totalVal1= $('#initialReading1').val()-$('#finalReading1').val();
$('#totalReading1').val($('#initialReading1').val()-$('#finalReading1').val());
hsdDipReading=parseInt(totalVal) + parseInt(totalVal1);
$('#hsdDipReading').val(hsdDipReading*0.96);
}
}

else if($('#finalReading1').val()!=='' && $('#initialReading1').val()!=='')
{
if(parseInt($('#finalReading1').val())>parseInt($('#initialReading1').val()))
{
alert('Initial value can not be less then Final value');
}
else
{
$('#totalReading1').val($('#initialReading1').val()-$('#finalReading1').val());
$('#hsdDipReading').val(($('#initialReading1').val()-$('#finalReading1').val())*0.96);
}
}
});
});
</script>



<script type="text/javascript">
(function ($){
$(document).ready(function(){
$(".myTimePicker").timepicker({
});
});
})(jQuery);
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
	var l=$('#lastDate').val();
$(".datepicker1" ).datepicker({dateFormat : 'dd-M-yy',changeMonth: true,
changeYear: true, yearRange: '-99:+0', minDate: new Date(l), maxDate: '+0M +0D'});
});
</script>


<style type="text/css">
<!--
#formID .bkgColor table tr td label {
	
}

.scroll {
	height: 114px;
	overflow-x: hidden;
	overflow-y: scroll;
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

.fixmyheader-8 {
	float: left;
}
-->
</style>

<form:form name="input" id="formID" action="saveAnnealingOven"
	modelAttribute="annealingOvenForm">
	<form:hidden path="annealingOvenMasterDTO.ovenId" id="ovenId" />
	<form:hidden path="lastAnnealiingLastDate" id="lastDate" />
	<div class="panel-header">
		<div class="panel-title">AnnealingOven</div>

	</div>

	<div align="left" class="bkgColor">
		<table width="557" height="115" border="0" class="tableview"
			align="left" class="">
			<tr>
				<td width="37" align="left"><label>AnnealingOven Date<span
						style="color: #FF0000">* </span> </label></td>
				<td width="60"><form:input
						path="annealingOvenMasterDTO.ovenDate" data-maxsize="11"
						class="validate[required] text-input datepicker1" readonly="true"
						style="width:60%" size="17" id="date" /></td>

				<td width="40" height="24">Shift <span style="color: #FF0000">*
				</span>
				</td>
				<td width="80"><form:select
						path="annealingOvenMasterDTO.shiftMasterDTO.mastersId"
						items="${shiftMastersList}" itemLabel="name" itemValue="mastersId"
						class="validate[required] text-input"
						style="height: 21px; width: 52%;" id="mastersId" />
				</td>

				<td width="70" height="24" align="left"><label>L P G
						Reading</label></td>

				<td width="70"><form:input
						path="annealingOvenMasterDTO.lpgReading"
						class="quantity validate[custom[number]]"
						onkeypress="return check(event)" style="width: 52%;"
						data-maxsize="65" size="8" id="shiftEnginner" />
			</tr>

			<tr>
				<td width="100" height="24">Shift Engineer Name</td>
				<td colspan="4"><form:input
						path="annealingOvenMasterDTO.shiftEngineerName"
						style="width: 57%;" onkeypress="return check(event)"
						data-maxsize="65" id="shiftEnginner" />
				</td>
			</tr>

		</table>

		<h4 style="margin-left: 6px; margin-bottom: 0px; width: 524px;">
			Add Records&nbsp;
			<c:if test="${opr!='R'}">
				<input class="newRow" id="L" title="Add"
					style="font-size: 11px; font-weight: bold; width: 23px;"
					type="button" value=" " />
			</c:if>
		</h4>

		<div class="gridheadingdiv">
			<table width="668" id="tbrollL" class="fixmyheader-8">
				<thead>
					<tr>
						<td width="20"><div align="center">
								<strong>S No.</strong>
							</div></td>
						<td width="51"><div align="center">
								<strong>Time</strong>
							</div></td>
						<td width="59"><div align="center">
								<strong>Zone 1</strong>
							</div></td>
						<td width="63"><div align="center">
								<strong>Zone 2</strong>
							</div></td>
						<td width="63"><div align="center">
								<strong>Zone 3</strong>
							</div></td>
						<td width="61"><div align="center">
								<strong>Zone 4</strong>
							</div></td>
						<td width="68"><div align="center">
								<strong>Hsd Dip Redng(MM)</strong>
							</div></td>
						<td width="100"><div align="center">
								<strong>Remarks</strong>
							</div></td>
						<td width="22"><div align="center">
								<strong>Action</strong>
							</div></td>
					</tr>
				</thead>
				<c:forEach
					items="${annealingOvenForm.annealingOvenMasterDTO.annealingOvenDetailDTOList}"
					var="bpd" varStatus="s">
					<tbody>

						<tr id="${s.index}">
							<td style="" width="11"><input type="hidden" name=""
								value="${annealingOvenForm.annealingOvenMasterDTO.annealingOvenDetailDTOList.size()}"
								id="listSize"> <input type="hidden" name="snoid"
								value="${s.count}" id="snoid"> <label>${s.count
									}</label></td>

							<td width="41"><form:input
									path="annealingOvenMasterDTO.annealingOvenDetailDTOList[${s.index}].ovenTime"
									style="border:1px solid #7f9db9; width: 100%" class="timePick"
									onclick="timePick(${s.index})" data-maxsize="11" size="8"
									id="ovenTime${s.index}" />
							</td>

							<td width="49"><form:input
									path="annealingOvenMasterDTO.annealingOvenDetailDTOList[${s.index}].ovenTempZone1"
									style="border:1px solid #7f9db9; width: 100%" class="quantity"
									data-maxsize="15" size="8" id="ovenTempZone1" />
							</td>

							<td width="53"><form:input
									path="annealingOvenMasterDTO.annealingOvenDetailDTOList[${s.index}].ovenTempZone2"
									style="border:1px solid #7f9db9; width: 100%" class="quantity"
									data-maxsize="15" size="8" id="ovenTempZone2" />
							</td>

							<td width="53"><form:input
									path="annealingOvenMasterDTO.annealingOvenDetailDTOList[${s.index}].ovenTempZone3"
									style="border:1px solid #7f9db9; width: 100%" class="quantity"
									data-maxsize="15" size="8" id="ovenTempZone3" />
							</td>

							<td width="51"><form:input
									path="annealingOvenMasterDTO.annealingOvenDetailDTOList[${s.index}].ovenTempZone4"
									style="border:1px solid #7f9db9; width: 100%" class="quantity"
									data-maxsize="15" size="8" id="ovenTempZone4" />
							</td>

							<td width="58"><form:input
									path="annealingOvenMasterDTO.annealingOvenDetailDTOList[${s.index}].hsdDipReading"
									style="border:1px solid #7f9db9; width: 100%" class="quantity"
									data-maxsize="15" size="8" id="density" />
							</td>


							<td width="90"><form:input
									path="annealingOvenMasterDTO.annealingOvenDetailDTOList[${s.index}].ovenRemark"
									style="width: 100%;border:1px solid #7f9db9;"
									data-maxsize="250" size="8" id="remarks2" /></td>

							<td width="12" style="text-align: center;"><c:if
									test="${opr!='R'}">
									<input class="remRow" type="image" src="static/images/drop.png"
										style="width: 12px; height: 12px;" id="${s.index}" )"/>

								</c:if> <c:if test="${opr=='E'}">
									<form:hidden
										path="annealingOvenMasterDTO.annealingOvenDetailDTOList[${s.index}].ovenId" />
									<form:hidden
										path="annealingOvenMasterDTO.annealingOvenDetailDTOList[${s.index}].sno" />
								</c:if>
							</td>
						</tr>
					</tbody>
				</c:forEach>

			</table>

		</div>
		<div>
			<table width="557" height="115" border="0" class="tableview"
				align="left" class="">
				<tr>
					<td width="100" height="24" align="left"><label>Initial
							Reading</label></td>
					<td width="100"><form:input
							path="annealingOvenMasterDTO.initialReading"
							class="quantity validate[custom[number]]" data-maxsize="15"
							style="width: 65%;" size="20" id="initialReading" /></td>


					<td width="108" height="24">Final Reading</td>
					<td width="100"><form:input
							path="annealingOvenMasterDTO.finalReading"
							class="quantity validate[custom[number]]"
							onkeypress="return check(event)" data-maxsize="15"
							style="width: 65%;" size="20" id="finalReading" /></td>


					<td width="80" height="24">Total</td>
					<td width="100"><form:input
							path="annealingOvenMasterDTO.totalReading"
							class="quantity validate[custom[number]]"
							onkeypress="return check(event)" data-maxsize="15"
							readonly="true" style="width: 65%;" size="20" id="totalReading" />
					</td>
				</tr>
				<tr>
					<td width="80" height="24">Reffiling</td>
					<td width="100"><form:input
							path="annealingOvenMasterDTO.refilling"
							class="quantity validate[custom[number]]"
							onkeypress="return check(event)" data-maxsize="15"
							style="width: 65%;" size="20" id="refilling" /></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td width="100" height="24" align="left"><label>HSD
							Dip Reading</label></td>
					<td width="100" align="left"><form:input
							path="annealingOvenMasterDTO.hsdDipReading" style="width: 90%x;"
							onkeypress="return check(event)" readonly="true"
							class="quantity digitOnly" data-maxsize="65" size="8"
							id="hsdDipReading" /></td>
				</tr>
				<tr>
					<td width="100" height="24" align="left"><label>Initial
							Reading</label></td>
					<td width="100"><form:input
							path="annealingOvenMasterDTO.initialReading1"
							class="quantity validate[custom[number]]"
							onkeypress="return check(event)" data-maxsize="15"
							style="width: 65%;" size="20" id="initialReading1" /></td>

					<td width="100" height="24">Final Reading</td>
					<td width="100"><form:input
							path="annealingOvenMasterDTO.finalReading1"
							class="quantity validate[custom[number]]"
							onkeypress="return check(event)" data-maxsize="15"
							style="width: 65%;" size="20" id="finalReading1" /></td>

					<td width="80" height="24">Total</td>
					<td width="100"><form:input
							path="annealingOvenMasterDTO.totalReading"
							class="quantity validate[custom[number]]"
							onkeypress="return check(event)" data-maxsize="15"
							readonly="true" style="width: 65%;" size="20" id="totalReading1" />
					</td>
				</tr>


			</table>
		</div>

		<div class="btn"
			style="margin: 12px 0 0 0px; float: left; margin-left: 6px;">
			<div class="savbtn">

				<c:if test="${opr=='V'}">
					<c:forEach items="${roleAndRightsDTOList}" var="roleAndRights">
						<c:if test="${roleAndRights.menuId==sessionScope.menuId}">
							<c:if test="${roleAndRights.editFlag=='true'}">
								<input class="edit_btn" type="button" onclick="editMethod();"
									value="" />
								<a href="get_annealingOven_list" class="cancelbtn"></a>
							</c:if>
							<c:if test="${roleAndRights.editFlag=='false'}">
								<input class="edit_btn" type="button" onclick="checkEdit();"
									value="" />
								<a href="get_annealingOven_list" class="cancelbtn"></a>
							</c:if>

						</c:if>
					</c:forEach>

				</c:if>

				<c:if
					test="${annealingOvenForm.annealingOvenMasterDTO.ovenId!=null && opr!='V'&& opr!='R' }">
					<input class="updatebtn" onclick="return onSave();" type="submit"
						value=" " />
					<a href="get_annealingOven_list" class="cancelbtn"></a>
				</c:if>
				<c:if
					test="${opr!='R' && opr!='E' && opr!='V' && annealingOvenForm.annealingOvenMasterDTO.ovenId==null}">
					<input class="submit" type="submit" value=" "
						onclick="return onSave();" />
					<a href="get_annealingOven_list" class="cancelbtn"></a>
				</c:if>

			</div>
			<div style="width: 906px; margin: 0 auto;">
				<span style="margin-left: 80px;" class="errmsg"></span>
			</div>
		</div>

	</div>
</form:form>
