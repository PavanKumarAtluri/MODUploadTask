<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Result Page</title>
<style>
table, th, td {
    border: 1px solid black;
    border-collapse: collapse;
    text-align: center;
}
</style>
</head>
<body>
<h1 style="color: red;text-align: center;">All Reports Details</h1>
	<c:choose>
		<c:when test="${!empty resultList}">
			<table align="center">
				<tr>
					<th>PATIENT ID</th>
					<th>DOCTOR ID</th>
					<th>TYPE OF REPORT</th>
					<th>UPLOADED DATE</th>
					<th>DOWNLOAD ORIGINAL REPORT</th>
					<th>DOWNLOAD REPORT AS PDF</th>
				</tr>
				<c:forEach var="result" items="${resultList }">
					<tr>
						<td><c:out value="${result.patient_id }" /></td>
						<td><c:out value="${result.doctor_id}" /></td>
						<td><c:out value="${result.phr_type }" /></td>
						<td><c:out value="${ result.phr_uploaded_date}" /></td>
						<td><a href="phrDownload/phrDownloadHandler.htm?path=${result.phr_uploaded_path_original}">Download</a></td>
						<td><a href="phrDownload/phrDownloadHandler.htm?path=${result.phr_uploaded_path_pdf}">Download</a></td>
					</tr>
				</c:forEach>
			</table>
		</c:when>
		<c:otherwise>
			<h2 style="color: green; text-align: center;">Reports Not Found</h2>
		</c:otherwise>
	</c:choose>
	<br>
	<p style="text-align: center"><a href="${pageContext.request.contextPath}/phr/home.htm">Home</a></p>
</body>
</html>