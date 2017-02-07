<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%><%@taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="jquery/jquery.autocomplete.css" />
<script type="text/javascript" src="jquery/lib/jquery.js"></script>
<script type="text/javascript" src="jquery/jquery.autocomplete.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Attandance list</title>
<script type="text/javascript">

$(document).ready(function ()
  { 
   $("#year").click(function()
	{ 
	for(i =new Date().getFullYear();i >=1990;i--)
	{
	if($("#year").val()!=i)
	{$('#year').append($('<option/>"').val(i).html(i));}
	}
  })
 });
</script>
</head>
<style>
#tableID {
	border: solid 1px;
	border-collapse: collapse;
	width: 100%;
}

#tableID th {
	font-size: 15px;
	border: solid 1px;
	border-collapse: collapse;
	 width: 20%; 
}

#tableID td {
	border: solid 1px;
	vertical-align: middle;
	width: 10px;
}
</style>
<script type="text/javascript">
$(document).ready(	function() {
	$("#tablDiv").hide();
	$(".btn").hide();
	});
</script>
<c:if test="${attandanceMasterForm.succ=='show'}">
 <!--   on load it will execute -->
    <script type="text/javascript">
    $(document).ready(	function() 
    		{
    		$("#tablDiv").show();
       		$(".btn").show();
       		var index1=document.getElementById("listSize").value;
       		for (i = 0;i < index1;i++) 
       		{
            if(($("#attendanceFlagId"+i).val()=='WeakOff') ||($("#attendanceFlagId"+i).val()=='Holiday'))
       		{
       		  $('#dayStatus'+i).val("Full Day");
       		  $("#dayStatus"+i+" option[value='Half Day']").hide();
       		}
       		}
       	  });
    </script>
</c:if>
<script>
	function changeFunc() 
	{	
		var a= $('#month').val();
		var b= $('#year').val();
		var e= $('#employee').val();
	   
		if($('#month').val()=='')
		 {
			alert("Please select Month");
		 }
		else if( $('#year').val()=="")
		{
		    alert("Please select Year");
		}
	   else if($('#employee').val()=="")
		{
			alert("Please Select Employee");
		}
		else
		{	
		 var delUrl='get_Day?month='+a+'&Year='+b+'&employee='+e;
	 	 window.self.location = delUrl;
	 	}
	}
	 function changeAttendanceValueOfCombo(valueOfIndex,index)
      {
		var v=valueOfIndex;
        var index=index;
        if((v=="ABSENT")||(v=="WeakOff")||(v=="Holiday"))
         {
         $("#dayStatus"+index+" option[value='Half Day']").hide();
         $('#dayStatus'+index).val("Full Day");
         var k= document.getElementById("dayOfDate");
         }
   	    else
   	      {
   	      $("#dayStatus"+index+" option[value='Half Day']").show();
   	      }
   	     }
</script>
<body>
<form:form action="save_attancedance_date" name="input" method="post" modelAttribute="attandanceMasterForm">
	<form:hidden path="attandanceMasterDTO.employeeName"/>
	<form:hidden path="attandanceMasterDTO.employeeCode"/>
	<div class="header">Attandance Form</div>
		<div class="headingdiv">
			<table>
				<tr>
	 				<td width="20"><font color="#349DF0"><strong>Year</strong></font><span style="color: #FF0000">*</span></td>
					<td>
				<form:select path="attandanceMasterDTO.year" id="year" onchange="changeFunc()">
                <form:option value="${attandanceMasterForm.attandanceMasterDTO.year}"></form:option>
                </form:select></td>
				
				<td width="20" class="ename"><font color="#349DF0"><strong>Month</strong></font><span style="color: #FF0000">*</span></td>
					<td><form:select path="attandanceMasterDTO.month" id="month" onchange="changeFunc()"
							name="month">
							<form:option value="0">January</form:option>
							<form:option value="1">February</form:option>
							<form:option value="2">March</form:option>
							<form:option value="3">April</form:option>
							<form:option value="4">May</form:option>
							<form:option value="5">June</form:option>
							<form:option value="6">July</form:option>
							<form:option value="7">August</form:option>
							<form:option value="8">September</form:option>
							<form:option value="9">October</form:option>
							<form:option value="10">November</form:option>
							<form:option value="11">December</form:option>
						</form:select></td>
					
					<td width="30" ><font color="#349DF0"><strong>Employee Name</strong></font><span style="color:#FF0000">*</span></td>
                 	<td><form:select path="attandanceMasterDTO.employeeId"  id="employee" onchange="changeFunc()">
							<form:option value="">---Select Name---</form:option>
							<c:forEach var="list" items="${employeeList}">
							<form:option value="${list.employeeId}" >
							<c:out value="${list.employeeFullName} ${list.employeeCode}" />
							</form:option>
							</c:forEach>
	                   </form:select></td>
        </tr>
		</table>
		</div>
	    <div id="tablDiv">
		<table width="150" id="tableID">
		 <thead>
				 <tr>
					<td width="50px" style="background-color:#4E8CCF"><center><strong><font color="#FFFFFF">Date</font></strong></center></td>
					<td width="50px" style="background-color:#4E8CCF"><center><strong><font color="#FFFFFF">Day Status</font></strong></center></td>
					<td width="50px" style="background-color:#4E8CCF"><center><strong><font color="#FFFFFF">Attendance Flag</font></strong></center></td>
				</tr>
 			</thead>
			<tbody>
			 <c:forEach  items="${attandanceMasterForm.attandanceMasterList}" var="att" varStatus="status">
			 <tr>
				<td style="background-color:#DAE7FD"><center><fmt:formatDate value="${att.date}" pattern="dd-MMM-yyyy"/></center>
				<form:hidden path="attandanceMasterList[${status.count - 1}].date"/>
                <form:hidden path="attandanceMasterList[${status.count - 1}].dayOfDate"/>
			   
 			</td>
			<td style="background-color:#DAE7FD"><center>
			<c:if test="${att.weakOff==null}">			
		    <form:select path="attandanceMasterList[${status.count - 1}].attandanceFlag" id="attendanceFlagId${status.index}" onchange="changeAttendanceValueOfCombo(this.value,${status.index})">
			<form:option value="">----Select----</form:option>
			<form:option value="ABSENT">A</form:option>
			<form:option value="EXTRA">E</form:option>
			<form:option value="Holiday">H</form:option>
			<form:option value="PRESENT">P</form:option>
			<form:option value="WeakOff">W</form:option>
			</form:select></center></td>
             </c:if>  	
           
			<c:if test="${att.weakOff!=null}">	
			<div id="attandaceId${status.index}"><center>	
		    <form:select path="attandanceMasterList[${status.count - 1}].attandanceFlag" id="attendanceFlagId${status.index}" onchange="changeAttendanceValueOfCombo(this.value,${status.index})">
			<form:option value="">----Select----</form:option>
			<form:option value="ABSENT">A</form:option>
			<form:option value="EXTRA">E</form:option>
			<form:option value="Holiday">H</form:option>
			<form:option value="PRESENT">P</form:option>
			<form:option value="WeakOff">W</form:option>
			 </form:select></center>
			 </td></div></c:if>
                    <td style="background-color:#DAE7FD"><center>
				    <form:select path="attandanceMasterList[${status.count - 1}].dayStatus" id="dayStatus${status.index}">
					<form:option value="">----Select---- </form:option>
					<form:option value="Half Day">Half Day</form:option>
					<form:option value="Full Day">Full Day</form:option>
					</form:select></center>
					</td>
					</tr>
					</c:forEach>
		   </tbody>
		  </table>
		      </div>
			<div id="wrapper"></div>
				<div class="btn">
					<div>
						<input class="submit"
							style="font-size: 11px; font-weight: bold; padding: 0 0 0 30px;"
							type="submit" value="Save" name="operation"/>
					 </div>
					<div>
						<a href="weicome" class="cancelbtn"></a>
					</div>
				</div>
	</form:form>
</body>
</html>