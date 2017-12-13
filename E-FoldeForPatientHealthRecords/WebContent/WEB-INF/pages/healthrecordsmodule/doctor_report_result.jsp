<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="${pageContext.request.contextPath}/asserts/theme1/css/style.css" type="text/css" rel="stylesheet" media="all" />


<title>Result Page</title>
<!-- <style>
table, th, td {
    border: 1px solid black;
    border-collapse: collapse;
    text-align: center;
}
</style> -->
<style>
body{
	margin:0px;
	font-family: Arial,Monteserrat-Light;
    font-size: 16px;
    line-height: 1.42857143;
    color: #333333;
    background-color: #ffffff;
	
}
.header{
	background-color:#70a1ff; height:75px;
	}
	
	
	.dataTable {
    min-height: 310px;
    background-color: #ffffff;
    margin-top: 50px;
    margin-bottom: 20px;
    padding: 20px 15px 30px 15px;
    box-shadow: 15px 15px 20px rgba(0, 0, 0, 0.15);
    border: 1px solid #ddd;
    z-index: 1;
	margin:5% 5%;
	font-family: arial;
    font-size: 17px;    color: rgba(0, 0, 0, 0.6); 
}
	.dataTable .patient-label {
    font-size: 17px;
    margin-top: 10px;
	}
	
	
	.dataTable table tr th{
	
    height: 40px;
    line-height: 20px;
    border-bottom: 5px solid #70a1ff;
    background-color: rgba(0, 0, 0, 0.2);
    text-align: left; text-align:Center;}
	.dataTable table{border-right:1px solid #f5f5f5;}
	
	.dataTable table tr td{border-bottom:1px solid #ddd; padding:10px; border-left:1px solid #f5f5f5;}
	
	.dataTable .title {
    width: 100%;
    color: rgba(0, 0, 0, 0.5);
    padding-left: 23px;
    font-size: 22px;
    margin-bottom: 35px;
	display:inline-block;
    line-height: 25px;}
       .logoutbtn{background-image:url(${pageContext.request.contextPath}/asserts/theme1/images/logout.png); background-repeat:no-repeat; background-position:left; padding-left:30px; float:right; color:#fff; background-size:24px; height:60px; margin-right:30px; line-height:60px;text-decoration:none;
    
</style>

</head>
<body>
<div class="header">
	<img src="${pageContext.request.contextPath}/asserts/theme1/images/logo.png" height="75" />
	<a class="logoutbtn" href="${pageContext.request.contextPath}/phr/logout.htm">Logout</a>
</div>
	<p style="text-align: center"><a href="${pageContext.request.contextPath}/phr/doctor_details.htm">Home</a></p>

<div class="dataTable">
<label class="title">View Reports</label>
<!-- <h1 style="color: red;text-align: center;">Doctor Report Details</h1> -->
	<c:choose>
		<c:when test="${!empty resultList}">
		
			<table align="center">
				<tr>
					<!-- <th>Doctor ID</th> -->
					<!-- <th>Patient ID</th> -->
					<th>Name of the Patient</th>
					<th>Age</th>
					<th>Gender</th>
					<th>Report Type</th>
					<th>Uploaded Date</th>
					<th>Notes From Patient</th>
					<th>Original Report</th>
					<th>PDF Report</th>
					
				</tr>
				<c:forEach var="result" items="${resultList }">
					<tr>
						<%-- <td><c:out value="${result.doctor_id}" /></td> --%>
						<%-- <td><c:out value="${result.patient_id }" /></td> --%>
						<td><c:out value="${result.patient_name }" /></td>
						<td><c:out value="${result.patient_age }" /></td>
						<td><c:out value="${result.patient_sex }" /></td>
						<td><c:out value="${result.phr_type}" /></td>
						<td><c:out value="${result.phr_uploaded_date}" /></td>
						<td><c:out value="${result.phr_description}" /></td>
						<td><a href="${pageContext.request.contextPath}/phrDetailsDisplay/phrDownload/phrDownloadHandler.htm?path=${result.phr_uploaded_path_original}" target="_blank">Download</a></td>
						<td><a href="${pageContext.request.contextPath}/phrDetailsDisplay/phrDownload/phrDownloadHandler.htm?path=${result.phr_uploaded_path_pdf}" target="_blank">Download</a></td>
					</tr>
				</c:forEach>
			</table>
			
		</c:when>
		<c:otherwise>
			<h2 style="color: green; text-align: center;">Reports Not Found</h2>
		</c:otherwise>
	</c:choose>
	</div>
	<br>
</body>
</html>