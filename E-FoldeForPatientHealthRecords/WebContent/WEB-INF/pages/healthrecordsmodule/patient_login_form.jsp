<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
</head>
<body onload="myRemoveSessionMsg(); preventBack();">
<h1 style="color: green;text-align: center">Patient Login Page</h1>
<div id="myDIV2">
<p style="color: red;text-align: center">${errorMsg}</p>
</div>

<form name='f' action='${pageContext.request.contextPath}/phr/patient_details.htm' method='POST'>
 <table align="center">
    <tr><td>Patient Id:</td><td><input type='text' name='uname' onfocus="myHide()"/></td></tr>
    <tr><td>Password:</td><td><input type='password' name='pwd' onfocus="myHide()"/></td></tr>
    <tr><td colspan='2'><input name="submit" type="submit" value="Login"/></td></tr>
  </table>
</form>

<script type="text/javascript">

		function myHide() {
			document.getElementById("myDIV2").style.display = "none";
		}

		function myRemoveSessionMsg() {
	<%session.removeAttribute("errorMsg");%>
		}
	</script>
	<script type = "text/javascript" >
    function preventBack() { 
    window.history.forward(); }
    setTimeout("preventBack()", 0);
    window.onunload = function () { null };
</script>
</body>
</html> --%>

<!doctype html>
<html>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<head>
<meta charset="utf-8">
<title>Patient Login</title>
<link href="${pageContext.request.contextPath}/asserts/theme1/css/style.css" type="text/css" rel="stylesheet" media="all" />
<link href="${pageContext.request.contextPath}/asserts/theme1/css/display.css" type="text/css" rel="stylesheet" media="all" />

<style>

.header{
    background-color:#70a1ff ; height:75px;
    }
    .containerLogin{
    background-image:url(${pageContext.request.contextPath}/asserts/theme1/images/bg-login.png); background-repeat:no-repeat;
    }
</style>

</head>

<body onload="myRemoveSessionMsg();preventBack();">
<div class="header">
    <img src="${pageContext.request.contextPath}/asserts/theme1/images/logo.png" height="75" />
    <p style="float:right; margin-right:20px; color:#fff; line-height:50px; padding-top:10px; font-family:Arial">
		<a style="color:#fff!important" href="${pageContext.request.contextPath}/phr/home.htm">Back</a>
	</p>
</div>


<div class="containerLogin">
<div class="card-panel">
<div id="myDIV2" style="background-color:#fff; text-align:center">
<span style="color: red;text-align: center">${errorMsg}</span>
</div>
<form id="signInFormm" action='${pageContext.request.contextPath}/phr/patient_details.htm' method="post">
<div class="form-group">
<div class="col-xs-12 margin-bottom-35 panel-top">
<span class="register-form-label">PATIENT LOGIN</span></div>
<div class="row"><div class="col-xs-12 text-center"><input type="text" class="register-form-input" name="uname" placeholder="Login ID" onfocus="myHide()" ><i class="fa fa-user input-icon" aria-hidden="true"></i></div><div class="col-xs-12 text-center"><input type="password" class="register-form-input" name="pwd" placeholder="Password" onfocus="myHide()"><i class="fa fa-lock input-icon" aria-hidden="true"></i></div></div>
<div id="communique" class="communique"><span class=""></span></div>

<div class="row"><div class="col-xs-12 text-center"><a href="patient-screen.html" ><input type="submit" id="signInBtn" value="Login" class="btn-login" /></a></div></div>
<!--
<div class="row registerAndforgot"><div class="col-xs-6 forgotPasswordDiv text-center"><a id="forgotPasswordBtn" class="forgotPasswordBtn" href="/restore_password">Forgotten password</a></div><div class="col-xs-6 registerDiv text-center"><a id="registerUserBtn" class="registerBtn" href="/register_first_step">Register</a></div></div>-->

</div></form></div><!-- react-text: 31 --><!-- /react-text --></div>
</div>

<script type="text/javascript">

		function myHide() {
			document.getElementById("myDIV2").style.display = "none";
		}

		function myRemoveSessionMsg() {
	<%session.removeAttribute("errorMsg");%>
		}
	</script>
	<script type = "text/javascript" >
    function preventBack() { 
    window.history.forward(); }
    setTimeout("preventBack()", 0);
    window.onunload = function () { null };
</script>
</body>
</html>

