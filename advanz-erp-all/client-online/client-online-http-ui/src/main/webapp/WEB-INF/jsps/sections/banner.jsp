<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<c:url var="logoutUrl" value="j_spring_security_logout"/>
<div id="pagebanner">
	<a href="http://advanz101.com/" target="_blank" ><span class="banner-title"></span></a>
	<a class="logout" href="${logoutUrl}">Logout</a>
	<a  class="logout" href="/client-online-http-ui/welcome">Home</a>
</div>