<%@page import="java.util.List"%>
<%@page import="com.mod.healthrecords.utils.JsonUtil"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!doctype html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
 }
    select {
	border: 1px solid;
	border-color: #d9d9d9 #ccc #b3b3b3;
	-webkit-border-radius: 4px;
	border-radius: 4px;
	font-size: 1em;
	font-size: 15px;
	color: rgba(0, 0, 0, 0.6);
	padding: 10px;
	margin-bottom: 15px;
	width: 100%
}
</style>
</head>
<body>
<script type="text/javascript"
		src="${pageContext.request.contextPath }/asserts/theme1/jquery/jquery-3.2.1.min.js">
</script>
<!-- <h1 style="color: red;text-align: center;">All Reports Details</h1> -->
<div class="header">
	<img src="${pageContext.request.contextPath}/asserts/theme1/images/logo.png" height="75" />
	<a class="logoutbtn" href="${pageContext.request.contextPath}/phr/logout.htm">Logout</a>
</div>
 <span style="float: right;color: #9000A1;font-style: normal;font-size:smaller;">Welcome:&nbsp;${userId} (Patient)&nbsp;&nbsp;&nbsp;</span>
	<p style="text-align: center"><a href="${pageContext.request.contextPath}/phr/patient_details.htm">Home</a></p>

<div class="dataTable" id="div1">
<div style="float:left"><label class="title">View Reports</label></div><div style="float:right">
<select name="phr_type" id="phr_type">
						<option value="-1">Filter By Type</option>
						<option value="all">All</option>
						<option value="scan">Scan</option>
						<option value="PHR">PHR</option>
						<option value="PMR">PMR</option>
						<option value="ECG">ECG</option>
</select>
</div>
	<c:choose>
		<c:when test="${!empty allReports}">
			<table align="center" id="t" width="100%">
				<tr>
					<!-- <th>Patient ID</th> -->
					<!-- <th>Doctor ID</th> -->
					<th width="20%">Doctor Name</th>
					<th width="10%">Speciality</th>
					<th width="15%">Report Type</th>
					<th width="22%">Uploaded Date</th>
					<th>Notes to Doctor</th>
					<th width="10%">Download</th>
				</tr>
				<c:forEach var="result" items="${allReports }">
					<tr>
						<%-- <td><c:out value="${result.patient_id }" /></td> --%>
						<%-- <td><c:out value="${result.doctor_id}" /></td> --%>
						<td align="center" style="text-transform: capitalize;"><c:out value="${result.doctor_name }" /></td>
						<td align="center" style="text-transform: capitalize;"><c:out value="${result.doctor_specialization }" /></td>
						<td align="center" style="text-transform: capitalize;"><c:out value="${result.phr_type }" /></td>
						<td align="center"><c:out value="${ result.phr_uploaded_date}" /></td>
						<td align="center"><c:out value="${result.phr_description}"/></td>
						<td align="center"><a href="phrDownload/phrDownloadHandler.htm?path=${result.phr_uploaded_path_original}" ><img src="${pageContext.request.contextPath}/asserts/theme1/images/doc_download3.png" alt="x" title="Download Original" width="17" height="24"></a>&nbsp;&nbsp;&nbsp;<a href="phrDownload/phrDownloadHandler.htm?path=${result.phr_uploaded_path_pdf}" ><img src="${pageContext.request.contextPath}/asserts/theme1/images/pdf_download1.png" alt="x" title="Download pdf" width="18" height="24"></a></td>
					</tr>
				</c:forEach>
			</table>
		</c:when>
		<c:otherwise>
			<h2 style="color: green; text-align: center;">Reports Not Found</h2>
		</c:otherwise>
	</c:choose>
	<p id="errMsg" style="color: red;text-align: center;"></p>
	</div>
	<br>
	
	
	<script type="text/javascript">
	$(document).ready(function() {
		$('select').on('change', function() {
			  //alert( this.value );
			  if(this.value == -1){
				  return false;
			  }
			$.get('${pageContext.request.contextPath}/phrDetailsDisplay/get_reports_by_type.htm?type='+this.value,function(details) {
				
				var tr = '<tr>' ;
				$("#t").empty();
				
				
				tr += '<th width="20%">' + "Doctor Name"  + '</th>';
				tr += '<th width="10%">' + "Speciality"  + '</th>';
				tr += '<th width="15%">' + "Report Type"  + '</th>';
				tr += '<th width="22%">' + "Uploaded Date"  + '</th>';
				tr += '<th>' + "Notes to Doctor"  + '</th>';
				tr += '<th width="10%">' + "Download"  + '</th>';
				tr +='</tr>';
				
				
				var e = JSON.parse(details);
				$.each(e, function(k, v) {
		
					tr += '<td align="center" style="text-transform: capitalize;">' + v.doctor_name  + '</td>';
			        tr += '<td align="center" style="text-transform: capitalize;">' + v.doctor_specialization + '</td>';
			        tr += '<td align="center" style="text-transform: capitalize;">' + v.phr_type  + '</td>';
			        tr += '<td align="center">' + v.phr_uploaded_date + '</td>';
			        tr += '<td align="center">' + v.phr_description  + '</td>';
			        tr += '<td align="center">' + '<a href='+'"'+'phrDownload/phrDownloadHandler.htm?path='+v.phr_uploaded_path_original+'"'+'><img src="${pageContext.request.contextPath}/asserts/theme1/images/doc_download3.png" alt="x" title="Download Original" width="17" height="24"></a>'+'&nbsp;&nbsp;&nbsp;'+ '<a href='+'"'+'phrDownload/phrDownloadHandler.htm?path='+v.phr_uploaded_path_pdf+'"'+'><img src="${pageContext.request.contextPath}/asserts/theme1/images/pdf_download1.png" title="Download pdf" alt="x" width="20" height="24"></a>' + '</td>';
			    	tr +='</tr>';  	
			    	
				});
				
				if ($.trim(details)=='[]'){   
				    //alert("No report is found with this name"+details);
				    document.getElementById("errMsg").innerHTML="No report found with "+$("#phr_type").val()+" type";
				}else
					document.getElementById("errMsg").innerHTML="";
				
				$("#t").append(tr);
			
			});
		})
	});
	
	</script>
</body>
</html>