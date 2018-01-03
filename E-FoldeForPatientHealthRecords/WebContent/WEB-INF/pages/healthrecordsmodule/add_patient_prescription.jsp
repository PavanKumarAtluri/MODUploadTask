<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<!doctype html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Welcome</title>
<link href="${pageContext.request.contextPath}/asserts/theme1/css/style.css" type="text/css" rel="stylesheet" media="all" />
<head>
<meta charset="utf-8">
<title>Upload Your Report</title>
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

.main-box {
	min-height: 310px;
	background-color: #ffffff;
	margin-top: 50px;
	margin-bottom: 20px;
	padding: 20px 15px 30px 15px;
	box-shadow: 15px 15px 20px rgba(0, 0, 0, 0.15);
	border: 1px solid #ddd;
	z-index: 1;
	margin: 5% 25%;
	font-family: arial;
	font-size: 17px;
	color: rgba(0, 0, 0, 0.6);
}

.main-box .patient-label {
	font-size: 17px;
	margin-top: 10px;
}

input, textarea {
	border: 1px solid;
	border-color: #d9d9d9 #ccc #b3b3b3;
	-webkit-border-radius: 4px;
	border-radius: 4px;
	font-size: 1em;
	font-size: 15px;
	color: rgba(0, 0, 0, 0.6);
	padding: 10px;
	width: 96%;
	margin-bottom: 15px;
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

.btn-save {
	background-color: #70a1ff;
	padding: 5px 15px;
	border-radius: 5px;
	height: 40px;
	margin-bottom: 10px;
	color: white;
	box-shadow: 3px 3px 6px rgba(0, 0, 0, 0.5);
	min-width: 140px;
	text-transform: uppercase;
	font-size: 13px;
	font-weight: bold;
	font-style: normal;
	-webkit-transition: background-color 0.3s ease;
	transition: background-color 0.3s ease;
	width: 100px;
	margin: 5px;
}

.main-box .title {
	width: 100%;
	color: rgba(0, 0, 0, 0.5);
	padding-left: 23px;
	font-size: 22px;
	margin-bottom: 35px;
	display: inline-block;
	line-height: 25px;
}
   .logoutbtn{background-image:url(${pageContext.request.contextPath}/asserts/theme1/images/logout.png); background-repeat:no-repeat; background-position:left; padding-left:30px; float:right; color:#fff; background-size:24px; height:60px; margin-right:30px; line-height:60px;text-decoration:none;

</style>
<style>
#myDIV {
    font-size: 8px;
    background-color: #f6f6f6 ;
    line-height: 44px;
    }
 </style>
</head>

<body onload="cleanForm()">
	<%-- <script type="text/javascript" src="${pageContext.request.contextPath }/asserts/theme1/jquery/jquery-3.2.1.min.js"></script> --%>
	<div class="header">
	<img src="${pageContext.request.contextPath}/asserts/theme1/images/logo.png" height="75" />
		<a class="logoutbtn" href="${pageContext.request.contextPath}/phr/logout.htm">Logout</a>
	
	</div>
	<p style="text-align: center">
		<a href="${pageContext.request.contextPath}/doctorReportDisplay/doctor_report_result.htm?id=${doctorId}">Back</a>
	</p>
	<div id="myDIV">
		<h1 style="color: green; text-align: center;">${resultMsg}</h1>
	</div>
	
	<div class="col-xs-12 col-md-8 col-md-offset-2">
	<div class="main-box">
	
		<label class="title">Add Prescription</label>
		<table width="100%">
			<f:form method="POST" id="myForm">
				<!-- <tr>
					<td><label for="phr_description">Prescription:</label></td>

				</tr> -->
				<tr><td><input type="hidden" id="phr_id" name="phr_id" value="${phr_id}"></td>
				<td><input type="hidden" id="doctor_id" name="doctor_id" value="${doctor_id}"></td>
				<td><input type="hidden" id="patient_id" name="patient_id" value="${patient_id}"></td></tr>
				<tr>
					<td><textarea name="phr_description"
							placeholder="Below 150 Characters..." id="phr_description"
							maxlength='150'  onblur="removeMsg()" onkeyup="checkLength()"
							onfocus="myHide();"></textarea></td>
				</tr>

<tr>
			<td align="right"><input type="submit" class="btn-save" value="Submit" onclick="return ValidationEvent()" /><input type="reset" class="btn-save" value="Cancel" />
			</td>
		</tr>
			</f:form>
		</table>
		<p style="color: red; text-align: center" id="text_area_msg" class="rq"></p>
	</div>
	</div>
	
	
	<script type="text/javascript">
		function ValidationEvent() {
			
			var phr_description1 = document.getElementById("phr_description").value;
			
			
			if (phr_description1 == "") {
				alert("Please add prescription");
				
				return false;
			}
			 
		}

		function checkLength() {
			var textLength = document.getElementById("phr_description").value;
			var length = textLength.length;
			var diff = 150 - length;
			//var KeyID = event.keyCode;
			if (diff > 0) {
				document.getElementById("text_area_msg").innerHTML = diff
						+ ' Characters Remaining';
			} else {
				document.getElementById("text_area_msg").innerHTML = 'Text box will not allow more than 150 characters';
			}
		}

		function removeMsg() {
			document.getElementById("text_area_msg").innerHTML = '';
		}

		function myHide() {
			document.getElementById("myDIV").style.display = "none";
		}
	</script>
</body>
</html>