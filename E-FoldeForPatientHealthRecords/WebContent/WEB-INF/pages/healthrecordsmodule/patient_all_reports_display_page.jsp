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
						url : "${pageContext.request.contextPath}/phrDetailsDisplay/doctorNameCheck.htm",
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
	
	$(document).ready(function() {
		$(function() {
			$("#search1").autocomplete({
				source : function(request, response) {
					$.ajax({
						url : "${pageContext.request.contextPath}/phrDetailsDisplay/doctorSpecialityCheck.htm",
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
    	var bla2 = $("#search2").val();
    	var bla3 = $("#search4").val();
    	var bla4 = $("#search5").val();
    	  //alert( bla);
    	  //alert(bla1);
    	  //alert(bla+" "+bla1+" "+bla2+" "+bla3+" "+bla4);
    	  
		 /*  if(bla == ""){
			  alert("please provide patient name");
			  return false;
		  } */
		$.get('${pageContext.request.contextPath}/phrDetailsDisplay/get_reports_by_adv_search.htm?name='+bla+'&speciality='+bla1+'&type='+bla2+'&dstatus='+bla3+'&pstatus='+bla4,function(details) {
			document.getElementById('search2').getElementsByTagName('option')[0].selected = 'selected';
			document.getElementById('search4').getElementsByTagName('option')[0].selected = 'selected';
			document.getElementById('search5').getElementsByTagName('option')[0].selected = 'selected';
			document.getElementById("search").value = '';
			document.getElementById("search1").value = '';
			
			var tr = '<tr>' ;
			$("#t").empty();
			
			
			tr += '<th width="20%">' + "Doctor Name"  + '</th>';
			tr += '<th width="10%">' + "Speciality"  + '</th>';
			tr += '<th width="15%">' + "Report Type"  + '</th>';
			tr += '<th width="30%">' + "Uploaded Date"  + '</th>';
			tr += '<th >' + "Notes to Doctor"  + '</th>';
			/* tr += '<th>' + "Prescription"  + '</th>'; */
			tr += '<th width="15%">' + "Delivery Status"  + '</th>';
			tr += '<th width="25%">' + "Payment Status"  + '</th>';
			tr += '<th width="10%">' + "Download"  + '</th>';
			tr +='</tr>';
			
			
			var e = JSON.parse(details);
			$.each(e, function(k, v) {
	
				tr += '<td align="center" style="text-transform: capitalize;">' + v.doctor_name  + '</td>';
		        tr += '<td align="center" style="text-transform: capitalize;">' + v.doctor_specialization + '</td>';
		        tr += '<td align="center" style="text-transform: capitalize;">' + v.phr_type  + '</td>';
		        tr += '<td align="center">' + v.phr_uploaded_date + '</td>';
		        tr += '<td align="center">' + v.phr_description  + '</td>';
		        if(v.patient_prescription!=null){
		        	/* tr += '<td align="center">' + v.patient_prescription  + '</td>'; */
		        	if(v.deliveryStatus==0 && v.paymentStatus==0){
		        		tr += '<td align="center">' + "Order Placed"  + '</td>';
		        		tr += '<td align="center" style="color: red;">' + "Pending"  + '</td>';
		        	}else if(v.deliveryStatus==1 && v.paymentStatus==0){
		        		tr += '<td align="center">' + "Order Ready"  + '</td>';
		        		tr += '<td align="center" style="color: red;">' + '<a href='+'"'+'${pageContext.request.contextPath}/phrDetailsDisplay/changePaymentStatusByphrId.htm?phrId='+v.phr_id+'"'+'>Make Payment</a>'  + '</td>';
		        		
		        	}else if(v.deliveryStatus==1 && v.paymentStatus==1){
		        		tr += '<td align="center" style="color: green;">' + "Delivered"  + '</td>';
		        		tr += '<td align="center" style="color: green;">' + "Completed"  + '</td>';
		        		
		        	}
		        	
		        }else{
		        	/* tr += '<td align="center" style="color: red;">' + "Not Prescribed" + '</td>'; */
		        	tr += '<td align="center" >' + "N/A" + '</td>';
		        	tr += '<td align="center" >' + "N/A" + '</td>';
		        }
		        
		        
		        tr += '<td align="center">' + '<a href='+'"'+'phrDownload/phrDownloadHandler.htm?path='+v.phr_uploaded_path_original+'"'+'><img src="${pageContext.request.contextPath}/asserts/theme1/images/doc_download3.png" alt="x" title="Download Original" width="17" height="24"></a>'+'&nbsp;&nbsp;&nbsp;'+ '<a href='+'"'+'phrDownload/phrDownloadHandler.htm?path='+v.phr_uploaded_path_pdf+'"'+'><img src="${pageContext.request.contextPath}/asserts/theme1/images/pdf_download1.png" title="Download pdf" alt="x" width="20" height="24"></a>' + '</td>';
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
	<title>Patient Reports</title>

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
<%-- <script type="text/javascript"
		src="${pageContext.request.contextPath }/asserts/theme1/jquery/jquery-3.2.1.min.js"> 
</script> --%>
<!-- <h1 style="color: red;text-align: center;">All Reports Details</h1> -->
<div class="header">
	<img src="${pageContext.request.contextPath}/asserts/theme1/images/logo.png" height="75" />
	<a class="logoutbtn" href="${pageContext.request.contextPath}/phr/logout.htm">Logout</a>
</div>
 <span style="float: right;color: #9000A1;font-style: normal;font-size:smaller;">Welcome:&nbsp;${userId} (Patient)&nbsp;&nbsp;&nbsp;</span>
	<p style="text-align: center"><a href="${pageContext.request.contextPath}/phr/patient_details.htm">Home</a></p>

<div class="dataTable" id="div1">
<!-- <div style="float:left"><label class="title">View Reports</label></div> -->
<!-- <div> -->
<div class="search-container">
	<div class="row">
		<div class="col-sm-12">
			<h4 style="font-weight: bold;">View Reports</h4>
		</div>
	</div>
	<div class="row" style="float: right;">
		<div class="col-sm-12 col-md-2">
			<fieldset class="form-group">
				<label for="search" style="font-weight: normal;">Doctor Name:</label>
				<input type="text" id="search" class="form-control search" name="search" onfocus="removeMsg()">
			</fieldset>
		</div>
		<div class="col-sm-12 col-md-2">
			<fieldset class="form-group">
				<label for="search" style="font-weight: normal;">Speciality:</label>
				<input type="text" id="search1" class="form-control search" name="search1" onfocus="removeMsg1()">
			</fieldset>
		</div>
		<div class="col-sm-12 col-md-2">
			<fieldset class="form-group">
				<label for="search" style="font-weight: normal;">Report Type:</label>
				<select name="search2" id="search2" class="form-control search">
					<option value="-1">Select</option>
					<option value="SCAN">Scan</option>
					<option value="PHR">PHR</option>
					<option value="PMR">PMR</option>
					<option value="ECG">ECG</option>
				</select>
			</fieldset>
		</div>
		<div class="col-sm-12 col-md-2">
			<fieldset class="form-group">
				<label for="search" style="font-weight: normal;">Delivery Status:</label>
				<select name="search4" id="search4" class="form-control search">
					<option value="-1">Select</option>
					<option value="1">Order Placed</option>
					<option value="2">Order Ready</option>
					<option value="3">Delivered</option>
				</select>
			</fieldset>
		</div>
		<div class="col-sm-12 col-md-2">
			<fieldset class="form-group">
				<label for="search" style="font-weight: normal;">Payment Status:</label>
				 <select name="search5" id="search5" class="form-control search">
						<option value="-1">Select</option>
						<option value="1">Pending</option>
						<option value="2">Make Payment</option>
						<option value="3">Completed</option>
				</select>
			</fieldset>
		</div>
		<div class="col-sm-12 col-md-2" style="margin-top: 30px">
			<fieldset class="form-group">
				<!-- <!-- <label for=" "> </label> -->
				<!-- <input type="button" name="searchBtn" id="searchBtn" value="Search" class=" form-control searchbtn btn-info glyphicon glyphicon-search "> -->
				 <button type="button" class="btn btn-info" name="searchBtn" id="searchBtn">
      				<span class="glyphicon glyphicon-search" style="font-size: 16px;"></span> Search
    			</button>
			</fieldset>
		</div>
	</div>
		<!-- <div class="ui-widget">
			<span>Doctor Name:</span><span> <input type="text" id="search" name="search" class="search" onfocus="removeMsg()"/></span>
			<span>Speciality:</span><span> <input type="text" id="search1" name="search1" class="search" onfocus="removeMsg1()"/></span>
			<span>Report Type:</span><span><select name="search2" id="search2">
												<option value="-1">Select</option>
												<option value="SCAN">Scan</option>
												<option value="PHR">PHR</option>
												<option value="PMR">PMR</option>
												<option value="ECG">ECG</option>
											</select>
									</span>
			<span>Uploaded Date:</span><span> <input type="text" id="search3" name="search3" class="search" onfocus="removeMsg3()"/></span>
			<span>Delivery Status:</span><span><select name="search4" id="search4">
													<option value="-1">Select</option>
													<option value="1">Order Placed</option>
													<option value="2">Order Ready</option>
													<option value="3">Delivered</option>
												</select>
										</span>
			<span>Payment Status:</span><span> <select name="search5" id="search5">
													<option value="-1">Select</option>
													<option value="1">Pending</option>
													<option value="2">Make Payment</option>
													<option value="3">Completed</option>
												</select>
										</span>
			<span><input type="button" name="searchBtn" id="searchBtn" value="Search"></span>
		</div> -->
	</div>
	<!-- <select name="phr_type" id="phr_type">
						<option value="-1">Filter By Type</option>
						<option value="all">All</option>
						<option value="SCAN">Scan</option>
						<option value="PHR">PHR</option>
						<option value="PMR">PMR</option>
						<option value="ECG">ECG</option>
</select> -->
<!-- </div> -->
	<c:choose>
		<c:when test="${!empty allReports}">
			<table align="center" id="t" width="100%">
				<tr>
					<!-- <th>Patient ID</th> -->
					<!-- <th>Doctor ID</th> -->
					<th width="20%">Doctor Name</th>
					<th width="10%">Speciality</th>
					<th width="15%">Report Type</th>
					<th width="30%">Uploaded Date</th>
					<th>Notes to Doctor</th>
					<!-- <th>Prescription</th> -->
					<th width="15%">Delivery Status</th>
					<th width="25%">Payment Status</th>
					<th width="10">Download</th>
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
						<c:choose>
								<c:when test="${!empty result.patient_prescription}">
									<%-- <td align="center"><c:out value="${result.patient_prescription}"/></td> --%>
									<c:if test="${result.deliveryStatus==0 && result.paymentStatus==0}">
										<td align="center"><c:out value="Order Placed" /></td>
										<td align="center" style="color: red"><c:out value="Pending" /></td>
									</c:if>
									<c:if test="${result.deliveryStatus==1 && result.paymentStatus==0}">
										<td align="center"><c:out value="Order Ready" /></td>
										<td align="center" style="color: red"><a href="${pageContext.request.contextPath}/phrDetailsDisplay/changePaymentStatusByphrId.htm?phrId=${result.phr_id }">Make Payment</a></td>	
									</c:if>
									<c:if test="${result.deliveryStatus==1 && result.paymentStatus==1}">
										<td align="center" style="color: green"><c:out value="Delivered" /></td>
										<td align="center" style="color: green"><c:out value="Completed" /></td>
									</c:if>
								</c:when>
								<c:otherwise>
									<%-- <td align="center" style="color: red;"><c:out value="Not Prescribed" /></td> --%>
									<td align="center">N/A</td>
									<td align="center">N/A</td>
								</c:otherwise>
							</c:choose>
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
	
	
	<!-- <script type="text/javascript">
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
				tr += '<th width="30%">' + "Uploaded Date"  + '</th>';
				tr += '<th >' + "Notes to Doctor"  + '</th>';
				/* tr += '<th>' + "Prescription"  + '</th>'; */
				tr += '<th width="15%">' + "Delivery Status"  + '</th>';
				tr += '<th width="25%">' + "Payment Status"  + '</th>';
				tr += '<th width="10%">' + "Download"  + '</th>';
				tr +='</tr>';
				
				
				var e = JSON.parse(details);
				$.each(e, function(k, v) {
		
					tr += '<td align="center" style="text-transform: capitalize;">' + v.doctor_name  + '</td>';
			        tr += '<td align="center" style="text-transform: capitalize;">' + v.doctor_specialization + '</td>';
			        tr += '<td align="center" style="text-transform: capitalize;">' + v.phr_type  + '</td>';
			        tr += '<td align="center">' + v.phr_uploaded_date + '</td>';
			        tr += '<td align="center">' + v.phr_description  + '</td>';
			        if(v.patient_prescription!=null){
			        	/* tr += '<td align="center">' + v.patient_prescription  + '</td>'; */
			        	if(v.deliveryStatus==0 && v.paymentStatus==0){
			        		tr += '<td align="center">' + "Order Placed"  + '</td>';
			        		tr += '<td align="center" style="color: red;">' + "Pending"  + '</td>';
			        	}else if(v.deliveryStatus==1 && v.paymentStatus==0){
			        		tr += '<td align="center">' + "Order Ready"  + '</td>';
			        		tr += '<td align="center" style="color: red;">' + '<a href='+'"'+'${pageContext.request.contextPath}/phrDetailsDisplay/changePaymentStatusByphrId.htm?phrId='+v.phr_id+'"'+'>Make Payment</a>'  + '</td>';
			        		
			        	}else if(v.deliveryStatus==1 && v.paymentStatus==1){
			        		tr += '<td align="center" style="color: green;">' + "Delivered"  + '</td>';
			        		tr += '<td align="center" style="color: green;">' + "Completed"  + '</td>';
			        		
			        	}
			        	
			        }else{
			        	/* tr += '<td align="center" style="color: red;">' + "Not Prescribed" + '</td>'; */
			        	tr += '<td align="center" >' + "N/A" + '</td>';
			        	tr += '<td align="center" >' + "N/A" + '</td>';
			        }
			        
			        
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
	
	</script> -->
</body>
</html>