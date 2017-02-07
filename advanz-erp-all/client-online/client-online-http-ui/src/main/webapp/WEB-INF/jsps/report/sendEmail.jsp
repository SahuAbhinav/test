<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
	<head>
	<script type="text/javascript">
	function sendMail() {
		if($('#date').val()!=''){
			var url = "send_email?date="+$('#date').val();
			var child = window.open(url);
		}else{
			alert("Please select date");
		}
	}
	</script>
	<input type="hidden" name="asd" id="infoId" value="${info}"/>
	 <c:if test="${info!=null}">
	 <script type="text/javascript">
	$(function() {
		alert($("#infoId").val());
	});
		 </script>
	 </c:if>
	<script type="text/javascript">
	$(function() {
		/* $("#fromDate").datepicker({
			dateFormat : 'dd-M-yy',
			autoSize : true,
			changeMonth : true,
			changeYear : true,
			dateFormat : 'dd-M-yy',changeMonth: true,
	        changeYear: true, yearRange: '-99:+0', maxDate: '+0M +0D'
		});
		var date = new Date();
		var firstDay = new Date(date.getFullYear(), date.getMonth(), 1);
		$('#fromDate').datepicker().datepicker('setDate',firstDay); */
		 
		$("#date").datepicker({
			dateFormat : 'dd-M-yy',
			autoSize : true,
			changeMonth : true,
			changeYear : true,maxDate: 0
			
	        
		});
		$('#date').datepicker().datepicker('setDate',new Date());
		
	});
	
	
	</script>
	</head>
<body>
	<div class="header">Send Email</div>

<div align="center" class="null">
	
	<p><b>Email Date:</b><input name="date"  id="date" />   <b>Sned Email:      </b><img onclick="sendMail();" src="..../../static/images/emailSend.jpg" alt="Email Send" width="80" height="80"  id="sendEmail" align="middle"></p>
	
	</div>	
	
	
</body>
