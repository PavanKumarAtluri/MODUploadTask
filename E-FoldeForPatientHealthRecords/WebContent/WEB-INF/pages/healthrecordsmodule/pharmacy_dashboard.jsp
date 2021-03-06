<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!doctype html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="${pageContext.request.contextPath}/asserts/theme1/css/style.css" type="text/css" rel="stylesheet" media="all" />
<title>Pharmacy DashBoard</title>
<head>
<style>
body {
    background-image: url(/E-FoldeForPatientHealthRecords/asserts/theme1/images/bg-login.png);
    background-repeat: no-repeat;
    background-size: 100% auto;
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
    background-image:url(${pageContext.request.contextPath}/asserts/theme1/images/bg-login.png); background-repeat:no-repeat;
}
.header{
    background-color:#70a1ff ; height:75px;
    }
       .logoutbtn{background-image:url(${pageContext.request.contextPath}/asserts/theme1/images/logout.png); background-repeat:no-repeat; background-position:left; padding-left:30px; float:right; color:#fff; background-size:24px; height:60px; margin-right:30px; line-height:60px;text-decoration:none;
    
   
</style>


</head>
<body onload="preventBack()">
<div class="header">

    <img src="${pageContext.request.contextPath}/asserts/theme1/images/logo.png" height="75" />
   <a class="logoutbtn" href="${pageContext.request.contextPath}/pharmacy/logout.htm">Logout</a>
    </div>
    <span style="float: right;color: #9000A1;font-style: normal;font-size:smaller;">Welcome:&nbsp;${pharmacyId} (Pharmacist)&nbsp;&nbsp;&nbsp;</span>
<h1 style="color: blue; text-align: center">Pharmacy Dashboard</h1>

<div class="user-dashboard">
	<div class="welcome-section">
		<a href="${pageContext.request.contextPath}/pharmacy/pharmacy_orders_result.htm?id=${pharmacyId}"><button class="contact-doc-btn">ORDERS</button></a>
	</div>
</div>

<script type = "text/javascript" >
    function preventBack() { 
    window.history.forward(); }
    setTimeout("preventBack()", 0);
    window.onunload = function () { null };
</script>
</body >
</html>