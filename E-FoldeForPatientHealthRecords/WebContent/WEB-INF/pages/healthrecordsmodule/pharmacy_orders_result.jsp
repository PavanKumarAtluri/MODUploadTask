<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!doctype html>
<html>
<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script> -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">

<script>
$(document).ready(function(){
    $("#searchBtn").click(function(){
    	var id = $("#search").val();
    	 
		$.get('${pageContext.request.contextPath}/pharmacy/get_orders_by_patient_id.htm?id='+id,function(details) {
			
			var tr = '<tr>' ;
			$("#t").empty();
			
			tr += '<th width="10%">' + "Order ID"  + '</th>';
			tr += '<th width="30%">' + "Prescription"  + '</th>';
			tr += '<th width="15%">' + "Prescribed Date"  + '</th>';
			tr += '<th width="15%">' + "Delivery Status"  + '</th>';
			tr += '<th width="15%">' + "Payment Status"  + '</th>';
			tr += '<th width="15%">' + "Delivered Date"  + '</th>';
			
			
			tr +='</tr>';
			
			
			var e = JSON.parse(details);
			
			if ($.trim(details)=='[]'){   
			    document.getElementById("errMsg").innerHTML="Patient "+id+" not exist";
			    return false;
			}else
				document.getElementById("errMsg").innerHTML="";
			
			$.each(e, function(k, v) {
	
				tr += '<td align="center">' + v.orderid  + '</td>';
		        tr += '<td align="center">' + v.prescription + '</td>';
		        tr += '<td align="center">' + v.date_prescription  + '</td>';
		        
		 	
		        if(v.is_delivered==0 && v.payment_status==0){
		        	tr += '<td align="center" style="color: red;">' + '<a href='+'"'+'${pageContext.request.contextPath}pharmacy/changeDeliveryStatus.htm?orderid='+v.phr_id+'&patientid='+v.patientid+'"'+'>Make ready to deliver</a>'  + '</td>';
					tr += '<td align="center" style="color: red;">' + "PENDING" + '</td>';
					tr += '<td align="center">' + "N/A"  + '</td>';
		        }else if(v.is_delivered==1 && v.payment_status==0){
		        	tr += '<td align="center" >' + "Ready to deliver" + '</td>';
		        	tr += '<td align="center" style="color: red;">' + "PENDING" + '</td>';
					tr += '<td align="center">' + "N/A"  + '</td>';
				}else if(v.is_delivered==1 && v.payment_status==1){
					tr += '<td align="center" style="color: green;">' + "Delivered" + '</td>';
					tr += '<td align="center" style="color: green;">' + "Completed" + '</td>';
					tr += '<td align="center">' + v.delivered_date  + '</td>';
				}
		       	tr +='</tr>';
		    	
			});
			
			
			
			$("#t").append(tr);
		
		});
    });
});

	function removeMsg() {
		document.getElementById("search").value = '';
	}
</script>
<link
	href="${pageContext.request.contextPath}/asserts/theme1/css/style.css"
	type="text/css" rel="stylesheet" media="all" />


<title>Result Page</title>
<!-- <style>
table, th, td {
    border: 1px solid black;
    border-collapse: collapse;
    text-align: center;
}
</style> -->
<style>
body {
	margin: 0px;
	font-family: Arial, Monteserrat-Light;
	font-size: 16px;
	line-height: 1.42857143;
	color: #333333;
	background-color: #ffffff;
}

.header {
	background-color: #70a1ff;
	height: 75px;
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
	margin: 5% 5%;
	font-family: arial;
	font-size: 17px;
	color: rgba(0, 0, 0, 0.6);
}

.dataTable .patient-label {
	font-size: 17px;
	margin-top: 10px;
}

.dataTable table tr th {
	height: 40px;
	line-height: 20px;
	border-bottom: 5px solid #70a1ff;
	background-color: rgba(0, 0, 0, 0.2);
	text-align: left;
	text-align: Center;
}

.dataTable table {
	border-right: 1px solid #f5f5f5;
}

.dataTable table tr td {
	border-bottom: 1px solid #ddd;
	padding: 10px;
	border-left: 1px solid #f5f5f5;
}

.dataTable .title {
	width: 100%;
	color: rgba(0, 0, 0, 0.5);
	padding-left: 23px;
	font-size: 22px;
	margin-bottom: 35px;
	display: inline-block;
	line-height: 25px;
}

.logoutbtn {
	background-image:
		url(${pageContext.request.contextPath}/asserts/theme1/images/logout.png);
	background-repeat: no-repeat;
	background-position: left;
	padding-left: 30px;
	float: right;
	color: #fff;
	background-size: 24px;
	height: 60px;
	margin-right: 30px;
	line-height: 60px;
	text-decoration: none;
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
}
</style>

</head>
<body onclick="hideStatusMsg();" onload="hideStatusMsg();">

	<div class="header">
		<img
			src="${pageContext.request.contextPath}/asserts/theme1/images/logo.png"
			height="75" /> <a class="logoutbtn"
			href="${pageContext.request.contextPath}/pharmacy/logout.htm">Logout</a>
	</div>
	<span style="float: right;color: #9000A1;font-style: normal;font-size:smaller;">Welcome:&nbsp;${pharmacyId} (Pharmacist)&nbsp;&nbsp;&nbsp;</span>
	<p style="text-align: center">
		<a href="${pageContext.request.contextPath}/pharmacy/pharmacy_dashboard.htm">Home</a>
	</p>

	<div id="statusdiv">
		<h3 style="color: green; text-align: center;">${statusMsg}</h3>
	</div>
	<div id="statusdiv1">
		<h3 style="color: green; text-align: center;">${statusMsg1}</h3>
	</div>
	
	<div class="dataTable">
		<div style="float: left">
			<label class="title">View Orders</label>
		</div>
		<div style="float: right">

			<div class="search-container">
				<div class="ui-widget">
					<span>Patient ID:</span><span> <input type="text"
						id="search" name="search" class="search" onfocus="removeMsg()" /></span>
					<span><input type="button" name="searchBtn" id="searchBtn"
						value="Search"></span>
				</div>
			</div>
		</div>
		<div style="clear: both"></div>
		<!-- <h1 style="color: red;text-align: center;">Doctor Report Details</h1> -->
		<c:choose>
			<c:when test="${!empty listOfOrders}">

				<table align="center" id="t">
					<tr>

						<th width="10%">Order ID</th>
						<th width="30%">Prescription</th>
						<th width="15%">Prescribed Date</th>
						<th width="15%">Delivery Status</th>
						<th width="15%">Payment Status</th>
						<th width="15%">Delivered Date</th>

					</tr>
					<c:forEach var="result" items="${listOfOrders }">
						<tr>

							<td align="center"><c:out value="${result.orderid }" /></td>
							<td align="center"><c:out value="${result.prescription }" /></td>
							<td align="center"><c:out value="${result.date_prescription }" /></td>
							
							<c:if test="${result.is_delivered==0 && result.payment_status==0}">
								<td align="center"><a href="${pageContext.request.contextPath}/pharmacy/changeDeliveryStatus.htm?orderid=${result.orderid }&patientid=${result.patientid }">Make ready to deliver</a></td>
								<td align="center" style="color: red"><c:out value="PENDING" /></td>
								<td align="center"><c:out value="N/A" /></td>
							</c:if>
							<c:if test="${result.is_delivered==1 && result.payment_status==0}">
								<td align="center"><c:out value="Ready to deliver" /></td>
								<td align="center" style="color: red"><c:out value="PENDING" /></td>
								<td align="center"><c:out value="N/A" /></td>
							</c:if>
							<c:if test="${result.is_delivered==1 && result.payment_status==1}">
								<td align="center" style="color: green"><c:out value="Delivered" /></td>
								<td align="center" style="color: green"><c:out value="Completed" /></td>
								<td align="center"><c:out value="${result.delivered_date }" /></td>
							</c:if>
						</tr>
					</c:forEach>
				</table>

			</c:when>
			<c:otherwise>
				<h2 style="color: green; text-align: center;">Orders Not Found</h2>
			</c:otherwise>
		</c:choose>
		<p id="errMsg" style="color: red; text-align: center;"></p>
	</div>

	<br>
	<script type="text/javascript">
		function hideStatusMsg() {
			//alert("hi")
			document.getElementById('statusdiv').style.display = 'none';
			document.getElementById('statusdiv1').style.display = 'none';
		}
	</script>
</body>
</html>