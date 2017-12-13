<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<!doctype html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>PHR Display Form</title>
<link href="${pageContext.request.contextPath}/asserts/theme1/css/style.css" type="text/css" rel="stylesheet" media="all" />
	
<head>
<!-- <style type="text/css">
table, th, td {
	border: 1px solid black;
	border-collapse: collapse;
	text-align: center;
}
</style> -->
<style>
.btn-save {
	background-color: #70a1ff;
	padding: 5px 15px;
	border-radius: 5px;
	height: 30px;
	margin-bottom: 10px;
	color: white;
	box-shadow: 3px 3px 6px rgba(0, 0, 0, 0.5);
	min-width: 60px;
	text-transform: uppercase;
	font-size: 12px;
	font-weight: bold;
	font-style: normal;
	-webkit-transition: background-color 0.3s ease;
	transition: background-color 0.3s ease;
	width: 100px;
	margin: 5px;
}
body {
    background-image: url(/E-FoldeForPatientHealthRecords/asserts/theme1/images/bg-login.png);
    background-repeat: no-repeat;
    background-size: 100%;
    margin: 0px auto;
    font-family: Arial;
}

table, th, td {
    border: #fff;
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
           .logoutbtn{background-image:url(${pageContext.request.contextPath}/asserts/theme1/images/logout.png); background-repeat:no-repeat; background-position:left; padding-left:30px; float:right; color:#fff; background-size:24px; height:60px; margin-right:30px; line-height:60px;text-decoration:none;
    </style>

</head>
<body onload="preventBack()">
<div class="header">
    <img src="${pageContext.request.contextPath}/asserts/theme1/images/logo.png" height="75" />
	<a class="logoutbtn" href="${pageContext.request.contextPath}/phr/logout.htm">Logout</a>
</div>
	<p style="text-align: center"><a href="${pageContext.request.contextPath}/phr/patient_details.htm">Home</a></p>

	<h1 style="color: blue; text-align: center">Find Your Reports</h1>

	<table align="center" bgcolor="#D6DBDF">
		<f:form commandName="phrDisplayCommand" method="POST">
<!-- 			<tr> -->
<!-- 				<td><label for="patient_id">Enter Patient Id</label></td> -->
<%-- 				<td><f:input path="patient_id" id="patient_id" /></td> --%>
<!-- 			</tr> -->
			<tr>
				<td><label for="phr_type">Report Type</label></td>
				<td><select name="phr_type" id="phr_type">
						<option value="-1">Select</option>
						<option value="scan">scan</option>
						<option value="phr">phr</option>
						<option value="pmr">pmr</option>
						<option value="ecg">ecg</option>
				</select></td>
			</tr>
			<tr>
				<td align="center"><input class="btn-save" type="submit" value="Submit"
					onclick="return ValidationEvent()" /></td>
				<td align="center"><input class="btn-save" type="reset" value="Cancel" /></td>
			</tr>
		</f:form>
	</table>
	
	<script type="text/javascript">
		function ValidationEvent() {
			var patient_id1 = document.getElementById("patient_id").value;
			var phr_type1 = document.getElementById("phr_type");

			if (patient_id1 == "") {
				alert("Patient Id must be filled out");
				return false;
			} else if (phr_type1.selectedIndex == "") {
				alert("Please select PHR Type");
				return false;
			}
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