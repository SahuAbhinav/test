<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:if test="${not empty(errorList)}">
 <input type="hidden" id="errorId" value="${errorList.errorMsg}">
 <script type="text/javascript">
 		$(document).ready(function() {
 		var errorId=document.getElementById('errorId');
		alert(errorId.value);
 		});
 	</script>
 </c:if>

 <c:if test="${opr=='R'}">
 <script type="text/javascript">
		var redrctUrl='role_list';
				
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
 		 var frank_param = getParam('roleId');
 		 //	confirm('Are you sure you want to delete?');
		 var delUrl='remove_role?roleId='+frank_param;
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
 		 var frank_param = $('#roleId').val();
 		 //	confirm('Are you sure you want to delete?');
		 var delUrl='get_role?roleId='+frank_param+'&opr=E';
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
		$("input:checkbox").attr("disabled", true);
			
		});
</script>
</c:if>
 	

<c:if test="${opr=='R' || opr=='E'}">
<script type="text/Javascript">
 $(document).ready(function() {
  var i;
  var menuRole;
  var addId; 
  var editId;
  var deleteId;
  for(i=0;i<200;i++)
   {
	menuRole="menuRole"+i;
	addId = "addId" + i;
	editId = "editId" + i;
	deleteId = "deleteId"+i;
	
	if(document.getElementById(menuRole).value=='true')
	 {
		document.getElementById(addId).disabled = false;
		document.getElementById(editId).disabled = false;
		document.getElementById(deleteId).disabled = false;
	 }
   }
 });
 </script>
 </c:if>
 
 <script type="text/javascript">
 
 var index;
 function changeScore(index)
 {
	var addId = "addId" + index;
	var editId = "editId" + index;
	var deleteId = "deleteId" + index;
	var name1="menuRole"+index;
	
	if(document.getElementById(name1).value=='false') {
		document.getElementById(addId).disabled = true;
		document.getElementById(editId).disabled = true;
		document.getElementById(deleteId).disabled = true;
		
		document.getElementById(addId).value ='false';
		document.getElementById(editId).value ='false';
		document.getElementById(deleteId).value ='false';
	  } 
	else{
		document.getElementById(addId).disabled = false;
		document.getElementById(editId).disabled = false;
		document.getElementById(deleteId).disabled = false;
		
	} 
  }
</script>


<script type="text/javascript" charset="utf-8">
                                  
                $(document).ready(function() {
                    /* Initialise datatables */
                    var oTable = $('#example').dataTable();
                    
                } );
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
		
		$(".cancelbtn").click(function() {
				window.self.location = "role_list";
		});

		
		<c:if test="${opr=='R'}">
		// in case of remove, set the select tag to readonly
			$("select").each(function() {
	                $(this).attr("disabled", true);
	        });
			
			$("#roleName").attr("disabled", true);
		</c:if>
	});
</script>


<script type="text/javascript">
			
			$(document).ready(function() {  	
				 
				
				$('.fixmyheader-8').fixheadertable({
					caption		: 'My header is fixed !',
					height		:317,
					addTitles	: true,
					colratio	: ['10%', '10%', '8%', '50px', 'auto', 'auto', '30%', 'auto']
				});
			});
		</script>

<style type="text/css">
th {
	font-size: 10px;
}

td {
	font-size: 11px;
}
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
		.ui-widget-content {
overflow-x: hidden !important;
 
}
		.mn{width:158px !important; border:none !important;}
	.sb{width:158px !important; border:none !important;}
	.menuname{width:158px !important; border:none !important;}
	.visible{width:80px !important; border:none !important;}
	.add{width:80px !important; border:none !important;}
	.edit{width:80px !important; border:none !important;}
	.del{width:70px !important; border:none !important;}
 

.info, .success, .warning, .error, .validation {
    border: 1px solid;
    margin: 10px 0px;
    padding:15px 10px 15px 50px;
    background-repeat: no-repeat;
    background-position: 10px center;
}
.info {
    color: #00529B;
    background-color: #BDE5F8;
    background-image: url('info.png');
}
.success {
    color: #4F8A10;
    background-color: #DFF2BF;
    background-image:url('success.png');
}
.dataTables_length {
width: 966px !important;
}
.warning {
    color: #9F6000;
    background-color: #FEEFB3;
    background-image: url('warning.png');
}
.error {
    color: #D8000C;
    background-color: #FFBABA;
    background-image: url('error.png');
}
</style>

<form:form id="formID" action="edit_role" method="post" modelAttribute="roleForm"> 
    
	<div class="panel-header">
		<div class="panel-title">Role Master</div>
		<div class="panel-tool"></div>
	</div>
	<div align="left" class="bkgColor">
		<table class="" width="405" height="30" border="0" align="center">
			<tr>
				<td width="45" height="30" align="left"><label> Role Name<span style="color: #FF0000">*</span>
				</label></td>
				<td width="150">
				<form:input type="text" onkeypress="return check(event)"   data-maxsize="25"
					class="validate[required] text-input" 
					path="role.roleName" size="20" id="roleName" /></td>
				
					<td width="45" height="24" align="left"><label>Hot Key Activated<span style="color: #FF0000">*</span>
				</label></td>
				<td width="150">
				
				<form:select style="width:113px; height:21px"
							path="role.hotKeyActive" id="hotKeyActive">
							<form:option value="0">Deactivated</form:option>
							<form:option value="1">Activated</form:option>
						</form:select>
				</td>
					<form:input type="hidden" path="role.roleId" size="20" id="roleId" />
				
				
			</tr>
		</table>
		<div class="gridheadingdiv">
			 <table width="972" class="display fixmyheader-8" id="example">
				<thead>
 
				<tr>
					<td class="mn"><div align="center">Module Name</div></td>
					<td class="sb"><div align="center">Sub Module</div></td>
					<td class="menuname"><div align="center">Menu Name</div></td>
					<td class="visible"><div align="center">Visible</div></td>
					<td class="add"><div align="center">Add</div></td>
					<td class="edit"><div align="center">Edit</div></td>
					<td class="del"><div align="center">Delete</div></td>
			</tr>
			</thead>
			<tbody>
				<c:forEach items="${roleForm.moduleMenuMasterDTOList}" var="menuRole" varStatus="status">
					<tr>
						<td width="148"><c:out value="${menuRole.moduleName}"/>
						<form:hidden path="moduleMenuMasterDTOList[${status.count - 1}].moduleName" /></td>
						<td width="148"><c:out value="${menuRole.subModuleName}"/>
						<form:hidden path="moduleMenuMasterDTOList[${status.count - 1}].subModuleName"/></td>
						<td width="148"><c:out value="${menuRole.menuName}"/>
						<form:hidden path="moduleMenuMasterDTOList[${status.count - 1}].menuName"/></td>
						<td width="70">
							<form:hidden  style="width:100%" path="userRoleAndRightsDTOList[${status.count - 1}].sno"/>
								<form:select id="menuRole${status.count-1}" onchange="changeScore(${status.count-1})" style="width:100%" path="userRoleAndRightsDTOList[${status.count - 1}].visibleFlag">
								
										<form:option value="false" >False</form:option>
										<form:option value="true" >True</form:option>
								</form:select>
						</td>
						<td width="70">				
								<form:select  id="addId${status.count-1}" style="width:100%" path="userRoleAndRightsDTOList[${status.count - 1}].addFlag" disabled="true">
										<form:option value="false" >False</form:option>
										<form:option value="true" >True</form:option>
								</form:select>
						</td>
						<td width="70">		
								 <form:select  id="editId${status.count - 1}" style="width:100%" path="userRoleAndRightsDTOList[${status.count - 1}].editFlag" disabled="true">
										<form:option value="false" >False</form:option>
										<form:option value="true" >True</form:option>
								</form:select>
						<td width="70">
							<form:select id="deleteId${status.count - 1}" style="width:73%" path="userRoleAndRightsDTOList[${status.count - 1}].deleteFlag" disabled="true">
										<form:option value="false" >False</form:option>
										<form:option value="true" >True</form:option>
							</form:select>
						</td>
						<td style="display: none;"><form:hidden path="moduleMenuMasterDTOList[${status.count - 1}].menuId"/></td>	
					</tr>
					 
				</c:forEach>
		</tbody>
		</table>
</div>
		<p>&nbsp;</p>
		<div class="btn">
			<c:if test="${opr=='E'}">
				<div class="savbtn">
				
					<input class="updatebtn"	type="submit" value="Edit" />
				</div>
					<div>
				<a href="role_list" class="cancelbtn"></a>
			</div>
			</c:if>	
			
		 <c:if test="${opr=='V'}">
		 <c:forEach items="${roleAndRightsDTOList}" var="roleAndRights">
		   <c:if test="${roleAndRights.menuId==sessionScope.menuId}">
    	  	 <c:if test="${roleAndRights.editFlag=='true'}">
		  	 <input class="edit_btn"  type="button" onclick="editMethod();" value=""/>
    	      <a href="role_list"  class="cancelbtn" ></a>  
		     </c:if>
		      <c:if test="${roleAndRights.editFlag=='false'}">
		      <input class="edit_btn"  type="button" onclick="checkEdit();" value=""/>
    	      <a href="role_list"  class="cancelbtn" ></a>  
		      </c:if>
		    
          </c:if>
          </c:forEach>	
   		   
    	</c:if>
        
			
			<c:if test="${opr=='R'}">
				<c:url value="remove_role" var="remove_url">
					<c:param name="roleId" value="${roleForm.role.roleId}"></c:param>
				</c:url>
			</c:if>
			</div>
			</div>
	<form:hidden path="role.roleId" />
</form:form>
