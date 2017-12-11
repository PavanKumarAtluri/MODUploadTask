<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- <style type="text/css">
table, th, td {
	border: 1px solid black;
	border-collapse: collapse;
	text-align: center;
} 
</style>-->
<style>
body {
    background-image: url(/E-FoldeForPatientHealthRecords/asserts/theme1/images/bg-login.png);
    background-repeat: no-repeat;
    background-size: 100%;
    margin: 0px auto;
    font-family: Arial;
}

table, th, td {
    border: #fff;
     text-align: center;
}

table tr td {
    padding: 15px;
    width: 50%;
    /* border: none!important; */
    border-bottom: 5px solid #70a1ff ;
    background-color: rgba(0, 0, 0, 0.2);
    color: #fff;
}

table tr td a {
    color: #fff;
    text-decoration: none;
}
h1{
margin-top:5%; margin:bottom:5%;
}
</style>
<style>
body {
    background-image: url("${pageContext.request.contextPath}/asserts/theme1/images/bg-login.png");
}
.header{
    background-color:#70a1ff ; height:75px;
    }
    .containerLogin{
    background-image:url(${pageContext.request.contextPath}/asserts/theme1/images/bg-login.png); background-repeat:no-repeat;
    }
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome</title>
</head>
<body>
<div class="header">
    <img src="${pageContext.request.contextPath}/asserts/theme1/images/logo.png" height="75" />
</div>
<h1 style="color: blue; text-align: center">Welcome To Medic On Demand</h1>

<!-- <table align="center" bgcolor="#D6DBDF"> -->
<%-- <tr><td><a href="${pageContext.request.contextPath}/phr/phr_form_page.htm">Upload Your Reports</a></td></tr> --%>
<%-- <tr><td><a href="${pageContext.request.contextPath}/phrDetailsDisplay/phr_detals_form.htm">Download Your Reports</a></td></tr> --%>
<!-- </table> -->

<table align="center" bgcolor="#D6DBDF">
<tr><td><a href="${pageContext.request.contextPath}/phr/doctor_login.htm">Doctor Login</a></td></tr>
<tr><td><a href="${pageContext.request.contextPath}/phr/patient_login.htm">Patient Login</a></td></tr>
</table>

</body>
</html>