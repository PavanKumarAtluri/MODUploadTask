<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    

<!doctype html>
<html>
<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script> -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
<script>
	$(document).ready(function() {
		$(function() {
			$("#search").autocomplete({
				source : function(request, response) {
					$.ajax({
						url : "${pageContext.request.contextPath}/doctorReportDisplay/patientnameCheck.htm",
						type : "GET",
						data : {
							term : request.term
						},
						dataType : "json",
						success : function(data) {
							response(data);
						}
					});
				}
			});
		});
	});
	
</script>
<script>
$(document).ready(function(){
    $("#searchBtn").click(function(){
    	var bla = $("#search").val();
    	var bla1 = $("#search1").val();
    	  //alert( bla);
    	  //alert(bla1);
    	  
		 /*  if(bla == ""){
			  alert("please provide patient name");
			  return false;
		  } */
		$.get('${pageContext.request.contextPath}/doctorReportDisplay/get_reports_by_patient_name.htm?name='+bla+'&type='+bla1,function(details) {
			document.getElementById("search").value = '';
			document.getElementById("search1").value = '';
			var tr = '<tr>' ;
			$("#t").empty();
			
			
			tr += '<th width="20%">' + "Patient Name"  + '</th>';
			tr += '<th width="5%">' + "Age"  + '</th>';
			tr += '<th width="5%">' + "Gender"  + '</th>';
			tr += '<th width="15%">' + "Report Type"  + '</th>';
			tr += '<th width="22%">' + "Uploaded Date"  + '</th>';
			tr += '<th>' + "Notes from Patient"  + '</th>';
			tr += '<th width="10%">' + "Download"  + '</th>';
			tr += '<th width="10%">' + "Prescription"  + '</th>';
			
			tr +='</tr>';
			
			
			var e = JSON.parse(details);
			
			$.each(e, function(k, v) {
	
				tr += '<td align="center" style="text-transform: capitalize;">' + v.patient_name  + '</td>';
		        tr += '<td align="center">' + v.patient_age + '</td>';
		        tr += '<td align="center" style="text-transform: capitalize;">' + v.patient_sex  + '</td>';
		        tr += '<td align="center" style="text-transform: capitalize;">' + v.phr_type + '</td>';
		        tr += '<td align="center">' + v.phr_uploaded_date  + '</td>';
		        tr += '<td align="center">' + v.phr_description  + '</td>';
		        tr += '<td align="center">' + '<a href='+'"'+'${pageContext.request.contextPath}/phrDetailsDisplay/phrDownload/phrDownloadHandler.htm?path='+v.phr_uploaded_path_original+'"'+'><img src="${pageContext.request.contextPath}/asserts/theme1/images/doc_download3.png" alt="x" title="Download Original" width="17" height="24"></a>'+'&nbsp;&nbsp;&nbsp;' + '<a href='+'"'+'${pageContext.request.contextPath}/phrDetailsDisplay/phrDownload/phrDownloadHandler.htm?path='+v.phr_uploaded_path_pdf+'"'+'><img src="${pageContext.request.contextPath}/asserts/theme1/images/pdf_download1.png" alt="x" title="Download pdf" width="22" height="24"></a>' + '</td>';
		    	tr += '<td align="center">' + '<a href='+'"'+'${pageContext.request.contextPath}/addPrescription.htm?phr_id='+v.phr_id+'&doctor_id='+v.doctor_id+'&patient_id='+v.patient_id+'"'+'><span class="glyphicon glyphicon-plus" title="Add Prescription"></span></a>'  + '</td>';
		        tr +='</tr>';
		    	
		    	
			});
			
			if ($.trim(details)=='[]'){   
			    //alert("No report is found with this name"+details);
			    //document.getElementById("errMsg").innerHTML="Patient "+bla+" doesn't exist";
			    document.getElementById("errMsg").innerHTML="No reports were found that match the specified search criteria";
			}else
				document.getElementById("errMsg").innerHTML="";
			
			$("#t").append(tr);
		
		});
    });
});

function removeMsg() {
	document.getElementById("search").value = '';
}

function removeMsg1() {
	document.getElementById("search1").value = '';
}
</script>
<link href="${pageContext.request.contextPath}/asserts/theme1/css/style.css" type="text/css" rel="stylesheet" media="all" />


<title>Doctor Reports</title>
<!-- <style>
table, th, td {
    border: 1px solid black;
    border-collapse: collapse;
    text-align: center;
}
</style> -->
<style>
th {
    font-size: 16px;
    font-family: Arial;
}
td {
    font-size: 14px;
    font-family: Arial;
}

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
      #search {
	border: 1px solid;
	border-color: #d9d9d9 #ccc #b3b3b3;
	-webkit-border-radius: 4px;
	border-radius: 4px;
	font-size: 1em;
	font-size: 15px;
	color: rgba(0, 0, 0, 0.6);
	padding: 10px;
	margin-bottom: 15px;
	
</style>

</head>
<body>

<div class="header">
	<img src="${pageContext.request.contextPath}/asserts/theme1/images/logo.png" height="75" />
	<a class="logoutbtn" href="${pageContext.request.contextPath}/phr/logout.htm">Logout</a>
</div>
<span style="float: right;color: #9000A1;font-style: normal;font-size:smaller;">Welcome:&nbsp;${doctorId} (Doctor)&nbsp;&nbsp;&nbsp;</span>
	<p style="text-align: center"><a href="${pageContext.request.contextPath}/phr/doctor_details.htm">Home</a></p>
	

<div class="dataTable">
		<div style="float: left">
			<label class="title" style="font-size: 18px;">View Orders</label>
		</div>
<!-- <div style="float:right">

	<div class="search-container">
		<div class="ui-widget">
			<span>Patient Name:</span><span> <input type="text" id="search" name="search" class="search" onfocus="removeMsg()"/></span>
			<span>Report Type:</span><span> <input type="text" id="search1" name="search1" class="search" onfocus="removeMsg1()"/></span>
			<span><input type="button" name="searchBtn" id="searchBtn" value="Search"></span>
		</div>
	</div>
</div> -->

<div class="row" style="float: right;">
		<div class="col-sm-12 col-md-4">
			<fieldset class="form-group">
				<label for="search" style="font-weight: normal;">Patient Name:</label>
				<input type="text" id="search" class="form-control search" name="search" onfocus="removeMsg()">
			</fieldset>
		</div>
		<div class="col-sm-12 col-md-4">
			<fieldset class="form-group">
				<label for="search" style="font-weight: normal;">Report Type:</label>
				<input type="text" id="search1" class="form-control search" name="search1" onfocus="removeMsg1()">
			</fieldset>
		</div>
		<div class="col-sm-12 col-md-4" style="margin-top: 30px">
			<fieldset class="form-group">
				<!-- <!-- <label for=" "> </label> -->
				<!-- <input type="button" name="searchBtn" id="searchBtn" value="Search" class=" form-control searchbtn btn-info glyphicon glyphicon-search "> -->
				 <button type="button" class="btn btn-info" name="searchBtn" id="searchBtn">
      				<span class="glyphicon glyphicon-search" style="font-size: 15px;"></span> Search
    			</button>
			</fieldset>
		</div>
	</div>
<div style="clear:both"></div>
<!-- <h1 style="color: red;text-align: center;">Doctor Report Details</h1> -->
	<c:choose>
		<c:when test="${!empty resultList}">
		
			<table align="center" id="t">
				<tr>
					<!-- <th>Doctor ID</th> -->
					<!-- <th>Patient ID</th> -->
					<th width="20%">Patient Name</th>
					<th width="5%">Age</th>
					<th width="5%">Gender</th>
					<th width="15%">Report Type</th>
					<th width="22%">Uploaded Date</th>
					<th>Notes from Patient</th>
					<th width="10%">Download</th>
					<th width="10%">Prescription</th>
					
				</tr>
				<c:forEach var="result" items="${resultList }">
					<tr>
						<%-- <td><c:out value="${result.doctor_id}" /></td> --%>
						<%-- <td><c:out value="${result.patient_id }" /></td> --%>
						<td align="center" style="text-transform: capitalize;"><c:out value="${result.patient_name }" /></td>
						<td align="center"><c:out value="${result.patient_age }" /></td>
						<td align="center" style="text-transform: capitalize;"><c:out value="${result.patient_sex }" /></td>
						<td align="center" style="text-transform: capitalize;"><c:out value="${result.phr_type}" /></td>
						<td align="center"><c:out value="${result.phr_uploaded_date}" /></td>
						<td align="center"><c:out value="${result.phr_description}" /></td>
						<td align="center"><a href="${pageContext.request.contextPath}/phrDetailsDisplay/phrDownload/phrDownloadHandler.htm?path=${result.phr_uploaded_path_original}"><img src="${pageContext.request.contextPath}/asserts/theme1/images/doc_download3.png" alt="x" title="Download Original" width="17" height="24"></a>&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/phrDetailsDisplay/phrDownload/phrDownloadHandler.htm?path=${result.phr_uploaded_path_pdf}"><img src="${pageContext.request.contextPath}/asserts/theme1/images/pdf_download1.png" alt="x" title="Download pdf" width="22" height="24"></a></td>
						<td align="center"><a href="${pageContext.request.contextPath}/addPrescription.htm?phr_id=${result.phr_id}&doctor_id=${result.doctor_id}&patient_id=${result.patient_id}"><span class="glyphicon glyphicon-plus" title="Add Prescription"></span></a></td>
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
	
</body>
</html>