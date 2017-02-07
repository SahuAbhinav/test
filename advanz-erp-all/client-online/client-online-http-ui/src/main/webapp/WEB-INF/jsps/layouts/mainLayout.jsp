<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="keywords" content="ERP,Sales,manufacturing">
<%
Integer userId=(Integer)session.getAttribute("userId");
if(userId==null){
%>
<script>
var delUrl='j_spring_security_logout?Logout';
window.self.location = delUrl;
</script>

<%} %>

<script type="text/javascript" src="static/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="static/js/jquery.easyui.min.js"></script>

<!-- Comment 14/02/1213 -->
<!-- <script src="static/js/jquery-ui.min.js"></script> -->
<script src="<c:url value="/static/js/jquery-ui.min.js"/>" type="text/javascript"
	charset="utf-8"></script>
	<script src="<c:url value="/static/js/jquery.dataTables.js"/>" type="text/javascript"
	charset="utf-8"></script>
	<script src="<c:url value="/static/js/jquery.js"/>" type="text/javascript"
	charset="utf-8"></script>
	
<script src="<c:url value="/static/js/flexdropdown.js"/>" type="text/javascript"
	charset="utf-8"></script>	
	<%-- <script src="<c:url value="/static/js/myjavascript.js"/>" type="text/javascript"
	charset="utf-8"></script> --%>


<script src="<c:url value="/static/js/jquery-1.8.3.js"/>" type="text/javascript"
	charset="utf-8"></script>
<script src="<c:url value="/static/js/languages/jquery.validationEngine-en.js"/>"
	type="text/javascript" charset="utf-8"></script>
<script src="<c:url value="/static/js/jquery.validationEngine.js"/>" type="text/javascript"
	charset="utf-8"></script>
	
<script src="<c:url value="/static/js/jquery.dataTables.nightly.js"/>" type="text/javascript"
	charset="utf-8"></script>	
	

<script src="<c:url value="/static/js/ui/1.9.0/jquery-ui.js"/>" type="text/javascript"></script>
<script type="text/javascript" src="<c:url value="/static/js/maxlength.js"/>"></script>
<script type="text/javascript" src="static/js/jquery-ui-timepicker-addon.js"></script>
<script type="text/javascript" src="<c:url value="/static/js/dateformate.js"/>"></script>
<link rel="stylesheet" type="text/css"
	href="static/css/easyui.css">

<link rel="stylesheet" type="text/css"
	href="static/css/flexdropdown.css">	
<link rel="stylesheet" type="text/css"
	href="static/css/icon.css">
 <link rel="stylesheet" type="text/css"
	href="static/css/demo_page.css">
	<link rel="stylesheet" type="text/css"
	href="static/css/demo_table.css">	
<link rel="stylesheet" type="text/css"
	href="static/css/demo.css">
<link href="static/css/jquery-ui.css"
	rel="stylesheet" type="text/css" />	 
	
<link rel="stylesheet" href="<c:url value="/static/css/template.css"/>" type="text/css" />
<link href="<c:url value="/static/css/style.css"/>" rel="stylesheet" type="text/css" />

<link rel="stylesheet" href="static/css/validationEngine.jquery.css" type="text/css"/>
<link rel="stylesheet" href="static/css/template.css" type="text/css"/>



<link rel="stylesheet" href="static/css/jquery-ui-1.8.4.custom.css" type="text/css"/>
<link rel="stylesheet" href="static/css/base.css" type="text/css"/>
<script src="<c:url value="/static/js/jquery.fixheadertable.js"/>" type="text/javascript"
	charset="utf-8"></script>



<!-- ...end script....... -->




<title><tiles:getAsString name="title-content" ignore="true" /></title>

</head>

<body>
<tiles:insertAttribute name="banner-content" />
	<div id="container">
		<!-- Bannar -->
		
		<div id="content-container">
			<!-- Menu -->
			<tiles:insertAttribute name="menu-content" />
			<div></div>
			<div id="aside">
				
				<!-- Primary Content -->
				<div style="margin-top: 0px; margin-left: 0px;">
					<tiles:insertAttribute name="primary-content" />
				</div>
			</div>
			<!-- Footer -->
			
		</div>
		</div>
		<tiles:insertAttribute name="footer-content" />
	
</body>
</html>
