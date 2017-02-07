<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ page isELIgnored="false"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">


<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	String securePath = "https://" + request.getServerName() + path
			+ "/";
	if (request.getServerName().equalsIgnoreCase("localhost")) {
		securePath = basePath;
	}

				String key = "user-agent";
				String userAgent = request.getHeader(key);

if(userAgent.contains("Chrome") || userAgent.contains("Safari") || userAgent.contains("MSIE"))
	{
	System.out.println("Browser name is :"+userAgent );
%>



<script type="text/javascript">

function checkBrowser(){
	alert("Sorry, currently we are supporting only Mozila firefox browser, so please use Mozila to access the application. Thanks");
window.self.location = 'http://www.afterdawn.com/software/network/browsers/firefox.cfm';
return false;
}

  var delUrl='j_spring_security_logout?Logout';
  window.onload = load();

  function load() {
		alert("Sorry, currently we are supporting only Mozila firefox browser, so please use Mozila to access the application. Thanks");
		window.self.location = 'http://www.afterdawn.com/software/network/browsers/firefox.cfm';
		// window.self.location = delUrl;
  }
 	</script>

  <%

				
}
%>


<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Login Form</title>
<script src="http://code.jquery.com/jquery-1.8.2.js"></script>
<script src="http://code.jquery.com/ui/1.9.1/jquery-ui.js"></script>
</head>
<input type="hidden" id="invalidateId"  value="${error}"> 

<script type="text/javascript" charset="utf-8">
    $(document).ready(function() {
if($("#invalidateId").val()!=null){
	 $('#errorMsg').text($("#invalidateId").val());
}
});
</script>
<style>
body {
	background: #fff;
	font-family: Arial, Helvetica, sans-serif;
	color: #3889df;
}

.main {
	width: 460px;
	height: 274px;
	margin: auto;
	border: solid 2px #81c6ff;
	background: #FFFFFF;
}

.header {
	background: #dcefff;
	width: 460px;
	height: 68px;
}

.logo {
	width: 114px;
	height: 55px;
	margin-left: 10px;
	padding-top: 10px;
}

.inputbtn {
	height: 27px;
	margin-left: 10px;
	width: 72%;
	border: 1px solid #a2cef3;
}

table {
	margin-left: 12px;
}

.man #login table tr td {
	font-weight: bold;
}

.loginbtn {
	margin: 15px auto;
	width: 174px;
	padding-left: 40	px;
	
}

.footer {
	width: 460px;
	background-color: #89c8fb;
	float: left;
	height: 30px;
}
.login {
 background: url("static/images/login_btn.png") no-repeat scroll 0 0 transparent;   
    height: 34px;
    width:81px;
    cursor:pointer;
    font-size: 0px;
    border: none;
}
.reset{
 background: url("static/images/reset.png") no-repeat scroll 0 0 transparent;   
    height: 34px;
    width:81px;
    cursor:pointer;
     font-size: 0px;
     border: none;
}

.chgpasswd {
	color: #020006;
	float: right;
	font-size: 14px;
	font-weight: normal;
	margin-top: 7px;
	text-decoration: underline;
	width: 156px;
}
.errorDiv{
color: red;
}
</style>
<body>
	<div class="main">
		<div class="header">
			<div class="logo">
				<img src="static/images/logo.png" />
			</div>
		</div>
		<form id="login" method="post" action="j_spring_security_check">
			<table width="437" height="108" border="0" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="139" height="81">User Name:</td>
					<td width="292"><input type="text" name="j_username"
						id="j_username" value="" class="inputbtn" /></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type="password" name="j_password" id="j_password"
						value="" class="inputbtn" /></td>
				</tr>
			</table>
			<div id="errorMsg" class="errorDiv"></div>
		 
			
			<div class="loginbtn">
				<input name="submit" class="login" type="submit" onclick="return checkBrowser();" /> 		<input name="reset" class="reset" type="reset" />
			</div>
			<div class="footer">
				<div class="chgpasswd">
					<img src="static/images/arrow.jpg "
						style="float: left; margin-right: 7px;" />change Password
				</div>
			</div>
		</form>
	</div>
</body>
</html>
