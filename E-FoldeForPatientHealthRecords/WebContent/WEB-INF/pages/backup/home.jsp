<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
table, th, td {
	border: 1px solid black;
	border-collapse: collapse;
	text-align: center;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home Page</title>
</head>
<body>
<h1 style="color: blue; text-align: center">Home</h1>
<table align="center" bgcolor="#D6DBDF">
<tr><td><a href="${pageContext.request.contextPath}/phr/phr_form_page.htm">Upload Your Reports</a></td></tr>
<tr><td><a href="${pageContext.request.contextPath}/phrDetailsDisplay/phr_detals_form.htm">Download Your Reports</a></td></tr>
</table>

</body>
</html>