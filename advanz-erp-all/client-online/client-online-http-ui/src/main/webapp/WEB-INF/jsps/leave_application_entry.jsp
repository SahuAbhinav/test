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


  <script>
	$(document).ready(function() {
		$(".cancelbtn").click(function() {
			window.self.location = "show_leave_application_list";
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
	$(document).ready(function() {
		$("button").button();
		//$('input:text, input:password').button()   
		$(".datepicker").datepicker();
		//      $(":submit").button()
	});
</script>

<script type="text/javascript">
	$(document).ready(function() {

		
		$(".fromDate").datepicker({
			changeMonth : true,
			changeYear : true,
			minDate: 0,
			yearRange : '-99:+10',
			dateFormat : 'dd-M-yy'
		});
		$(".toDate").datepicker({
			changeMonth : true,
			changeYear : true,
			yearRange : '-99:+10',
			dateFormat : 'dd-M-yy'
		});
		//     
	});
</script>
<c:if test="${operation=='view' }">
	<script type="text/javascript">
		$(document).ready(function() {
		$('input').attr('readonly','readonly');
		$('select').attr('disabled','disabled');
		$('textarea').attr('readonly','readonly');
		$('.datepicker').attr('disabled','disabled');
		$('input:radio').attr('disabled',true);
		$("input:checkbox").attr("disabled", true);
		$(".fromDate").attr("disabled", true);
		$('.toDate').attr('disabled','disabled');
		});
	</script>
 </c:if>
 <script type="text/javascript">
 function checkApproved(){
		alert("You can not edit this record as it is already approved.");
 }
 function checkEdit()
	{
	alert('Login User Not Permit to Edit Record !!!!!!');
	}
	
	//	$(document).ready(function() {
 	function editMethod()
 	 { 
 	 var frank_param = $('#sno').val();
 	 //	confirm('Are you sure you want to delete?');
	 var delUrl='show_leave_application?sno='+frank_param+'&operation=update';
 	 window.self.location = delUrl;  
	}
 	</script>
 	 <c:if test="${operation=='Delete'}">
 	<script>
 	
 	$(document).ready(function() {
 		
 		var redrctUrl='show_leave_application_list';
 		 var frank_param = $('#sno').val();
		 //	confirm('Are you sure you want to delete?');
		 var delUrl='show_leave_application_list?sno='+frank_param+"&operation=Delete";
		 if(confirm('Are you sure you want to delete?')) 
		   {
			window.self.location = delUrl;
		 	}
		 else{
			 window.self.location = redrctUrl;
 			}
		});
 	</script></c:if>
	<form:form name="input" id="formID" action="save_leave_application"
		method="post" modelAttribute="leaveApplicationForm" commandName="leaveApplicationForm">
		
		<form:errors path="*" cssClass="errorblock" element="div" />
	<form:hidden path="applicationDTO.sno"  id="sno"/>
		<div class="panel-header">
			<div class="panel-title">Leave Application Entry Form</div>
			<div class="panel-tool"></div>
		</div>

		<div align="left" class="bkgColor">
			<table class="" width="743" height="102" border="0" align="left">
				<tr>
					<td width="101" height="30" align="left"><label>
							Employee Name<span style="color: #FF0000">*</span>
					</label>
					</td>
					<td width="216"><form:select id="employeeId"
							class="validate[required] text-input"
							style="width:250px; height:20px;"
							path="applicationDTO.employeeDTO.employeeId" items="${employeeList}"
							itemLabel="employeeNameCode" itemValue="employeeId">
					</form:select>
					</td>
					<td width="52" height="30"><label>Leave Type<span
							style="color: #FF0000">*</span>
					</label>
					</td>
					<td width="259"><form:select id="leaveId"
							class="validate[required] text-input"
							style="width:250px; height:20px;"
							path="applicationDTO.leaveTypeMastDTO.leaveId" items="${eaveTypeList}"
							itemLabel="leaveName" itemValue="leaveId">
					</form:select>
					</td>
				</tr>
				<tr>
					<td height="31" width="101" align="left"><label>From Date<span
							style="color: #FF0000">*</span>
					</label>
					</td>
					<td height="31" colspan="1">
					<form:input path="applicationDTO.fromDate" class="validate[required] text-input fromDate" size="11" /></td>
				
					<td width="101" height="26" align="left"><label>
							To Date<span style="color: #FF0000">*</span></label>
					</td>
					<td height="26" colspan="3"><form:input path="applicationDTO.toDate" class="validate[required] text-input toDate" size="11" />
					</td>
				</tr>
				<tr>
					<td height="31" width="101" align="left"><label>Leave Description
					</label>
					</td>
					<td height="31" colspan="1">
					<form:textarea onkeyup="valid(this)" onblur="valid(this)" data-maxsize="225"
							path="applicationDTO.remark" cols="35" rows="2"/></td>
				
					<td width="101" height="26" align="left"><label>
							Approved&nbsp;</label>
					</td>
					<td height="26" colspan="3"><form:checkbox onkeyup="valid(this)" onblur="valid(this)" data-maxsize="225"
							path="applicationDTO.approveFlag" value="1" />
					</td>
				</tr>
			</table>
			 <div class="btn">
   <div class="savbtn">
   <c:if test="${leaveApplicationForm.applicationDTO.sno>0}">
   <c:if test="${operation=='view' }">
   <c:forEach items="${roleAndRightsDTOList}" var="roleAndRights">
		   <c:if test="${roleAndRights.menuId==sessionScope.menuId}">
    	  	 <c:if test="${roleAndRights.editFlag=='true'}">
    	  	  <c:choose>
												<c:when test="${leaveApplicationForm.applicationDTO.approveFlag=='true'}">
													<img onclick="checkApproved();"
														class="edit_btn" title="Edit Record"
														alt="" />
			</c:when>
		  	 <c:otherwise>
		  	 <input class="edit_btn"  type="button" onclick="editMethod();" value=""/>
		  	 </c:otherwise></c:choose>
		     </c:if>
		      <c:if test="${roleAndRights.editFlag=='false'}">
		      <input class="edit_btn"  type="button" onclick="checkEdit();" value=""/>
		      </c:if>
		    
          </c:if>
          </c:forEach>	
   </c:if> <c:if test="${operation!='view' }">
   <input class="updatebtn"  type="submit" name="operation"
								value="" onclick="this.value='Save';"/>
								</c:if>
								
								</c:if>
								<c:if test="${leaveApplicationForm.applicationDTO.sno<0 || leaveApplicationForm.applicationDTO.sno==null}">
     <input class="submit"  type="submit" name="operation"
								value="" onclick="this.value='Save';"/>
								</c:if>
   </div>
   
   <div >
     <a href="#"><input  class="cancelbtn" 
     type="reset" value=""/></a>
   </div>
    </div>

		</div>



	</form:form>