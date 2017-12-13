<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
table, th, td {
	border: 1px solid black;
	border-collapse: collapse;
	text-align: center;
}

.rq {
	color: #FF0000;
	font-size: 9pt;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Form Page</title>

</head>
<body onload="myRemoveSessionMsg()">
<a style="text-align: right;" href="${pageContext.request.contextPath}/phr/logout.htm">Logout</a>
	<h1 style="color: blue; text-align: center">Upload Your Report</h1>
	<div id="myDIV">
		<h1 style="color: red; text-align: center;">${message}</h1>
		<h1 style="color: green; text-align: center;">${message1}</h1>
	</div>
	<table align="center" border="1" bgcolor="#D6DBDF">
		<f:form commandName="patientHealthReportCommand" method="POST"
			enctype="multipart/form-data">
			<tr>
				<td><label for="patient_id">Enter Patient Id</label></td>
				<td><f:input path="patient_id" id="patient_id"
						onfocus="myHide()" /></td>
			</tr>
			<tr></tr>
			<tr></tr>
			<tr>
				<td><label for="doctor_id">Enter Doctor Id</label></td>
				<td><f:input path="doctor_id" id="doctor_id" onfocus="myHide()" /></td>
			</tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr>
				<td><label for="phr_type">Please select report type</label></td>
				<td><select name="phr_type" id="phr_type" onfocus="myHide()">
						<option value="-1">Select Report Type</option>
						<option value="scan">scan</option>
						<option value="phr">phr</option>
						<option value="pmr">pmr</option>
						<option value="ecg">ecg</option>
				</select></td>
			</tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr>
				<td><label for="phr_uploaded_file" >Upload
						Your Record</label></td>
				<td><input type="file" name="phr_uploaded_file"
					id="phr_uploaded_file" onfocus="myHide()"></td>
			</tr>

			<tr>
				<td></td>
				<td><p class="rq">(Format type: .pdf, .jpg,
						.png, .tif, .docx&nbsp;&nbsp;&nbsp;Max size: 2MB)</p></td>
			</tr>
			<tr>
				<td><label for="phr_description"  >Description</label></td>
				<td><textarea rows="5" cols="25" name="phr_description"
						id="phr_description" maxlength='150' onkeyup="checkLength()" onblur="removeMsg()" onfocus="myHide()"></textarea></td>
			</tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr>
				<td><input type="submit" value="Submit"
					onclick="return ValidationEvent()" /></td>
				<td><input type="reset" value="Cancel" /></td>
			</tr>
		</f:form>
	</table>
	<p style="text-align: center" id="text_area_msg" class="rq"></p>
	<p style="text-align: center">
		<a href="${pageContext.request.contextPath}/phr/patient_details.htm">Home</a>
	</p>
	<script type="text/javascript">
		function ValidationEvent() {
			var patient_id1 = document.getElementById("patient_id").value;
			var doctor_id1 = document.getElementById("doctor_id").value;
			var phr_type1 = document.getElementById("phr_type");
			var phr_description1 = document.getElementById("phr_description").value;

			if (patient_id1 == "") {
				alert("Patient Id must be filled out");
				return false;
			} else if (doctor_id1 == "") {
				alert("Doctor Id must be filled out");
				return false;
			} else if (phr_type1.selectedIndex == "") {
				alert("Please select PHR Type");
				return false;
			} else if (phr_description1 == "") {
				alert("Please Provide Description");
				return false;
			}
		}

		function checkLength() {
			var textLength = document.getElementById("phr_description").value;
			var length = textLength.length;
			var diff = 150 - length;
			var KeyID = event.keyCode;
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

		function myRemoveSessionMsg() {
	<%session.removeAttribute("message");
			session.removeAttribute("message1");%>
		}
	</script>
</body>
</html> --%>

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
	
<!-- <head>
<style type="text/css">
table, th, td {
	border: 1px solid black;
	border-collapse: collapse;
	text-align: center;
}

.rq {
	color: #FF0000;
	font-size: 9pt;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Form Page</title>
</head>
 -->
<!-- <body onload="myRemoveSessionMsg()"> -->
	<!-- <h1 style="color: blue; text-align: center">Upload Your Report</h1> -->
	<%-- <div id="myDIV">
		<h1 style="color: red; text-align: center;">${message}</h1>
		<h1 style="color: green; text-align: center;">${message1}</h1>
	</div> --%>
	<%-- 	<table align="center" border="1" bgcolor="#D6DBDF">
		<f:form commandName="patientHealthReportCommand" method="POST"
			enctype="multipart/form-data">
			<tr>
				<td><label for="patient_id">Enter Patient Id</label></td>
				<td><f:input path="patient_id" id="patient_id"
						onfocus="myHide()" /></td>
			</tr>
			<tr></tr>
			<tr></tr>
			<tr>
				<td><label for="doctor_id">Enter Doctor Id</label></td>
				<td><f:input path="doctor_id" id="doctor_id" onfocus="myHide()" /></td>
			</tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr>
				<td><label for="phr_type">Please select report type</label></td>
				<td><select name="phr_type" id="phr_type" onfocus="myHide()">
						<option value="-1">Select Report Type</option>
						<option value="scan">scan</option>
						<option value="phr">phr</option>
						<option value="pmr">pmr</option>
						<option value="ecg">ecg</option>
				</select></td>
			</tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr>
				<td><label for="phr_uploaded_file" >Upload
						Your Record</label></td>
				<td><input type="file" name="phr_uploaded_file"
					id="phr_uploaded_file" onfocus="myHide()"></td>
			</tr>

			<tr>
				<td></td>
				<td><p class="rq">(Format type: .pdf, .jpg,
						.png&nbsp;&nbsp;&nbsp;Max size: 2MB)</p></td>
			</tr>
			<tr>
				<td><label for="phr_description"  >Description</label></td>
				<td><textarea rows="5" cols="25" name="phr_description"
						id="phr_description" maxlength='150' onkeyup="checkLength()" onblur="removeMsg()" onfocus="myHide()"></textarea></td>
			</tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
<%-- 			<tr>
				<td><input type="submit" value="Submit"
					onclick="return ValidationEvent()" /></td>
				<td><input type="reset" value="Cancel" /></td>
			</tr>
		</f:form> 
	</table>--%>
	<!-- <p style="text-align: center" id="text_area_msg" class="rq"></p> -->
	<%-- <p style="text-align: center">
		<a href="${pageContext.request.contextPath}/phr/home.htm">Home</a>
	</p> --%>
	<%-- <script type="text/javascript">
		function ValidationEvent() {
			var patient_id1 = document.getElementById("patient_id").value;
			var doctor_id1 = document.getElementById("doctor_id").value;
			var phr_type1 = document.getElementById("phr_type");
			var phr_description1 = document.getElementById("phr_description").value;

			if (patient_id1 == "") {
				alert("Patient Id must be filled out");
				return false;
			} else if (doctor_id1 == "") {
				alert("Doctor Id must be filled out");
				return false;
			} else if (phr_type1.selectedIndex == "") {
				alert("Please select PHR Type");
				return false;
			} else if (phr_description1 == "") {
				alert("Please Provide Description");
				return false;
			}
		}

		function checkLength() {
			var textLength = document.getElementById("phr_description").value;
			var length = textLength.length;
			var diff = 150 - length;
			var KeyID = event.keyCode;
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

		function myRemoveSessionMsg() {
	<%session.removeAttribute("message");
			session.removeAttribute("message1");%>
		}
	</script>

</body>
</html>
 
<html>
--%>
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
		<a href="${pageContext.request.contextPath}/phr/patient_details.htm">Home</a>
	</p>
	<div id="myDIV">
		<h1 style="color: red; text-align: center;">${message}</h1>
		<h1 style="color: green; text-align: center;">${message1}</h1>
	</div>
	
	<div class="col-xs-12 col-md-8 col-md-offset-2">
	<div class="main-box">
	
		<label class="title">Upload New Report</label>
		<table width="100%">
			<f:form commandName="patientHealthReportCommand" method="POST"
				enctype="multipart/form-data" id="myForm">
				<!-- <tr>
					<td><label for="patient_id">Enter Patient Id</label></td>
				</tr> -->
				<%-- <tr>

					<td><f:input path="patient_id" id="patient_id"
							onfocus="myHide();myRemoveSessionMsg();" readonly="true"/></td>
				</tr> --%>

				<tr>
					<td><label for="doctor_id">Name of the Doctor</label></td>
				</tr>
				<tr>
					<td><f:select path="doctor_id" id="doctor_id" onfocus="myHide();myRemoveSessionMsg();">
							<option value="">Select</option>
							<f:options items="${doctorsList}"/>
						</f:select></td>
					<%-- <td><f:input path="doctor_id" id="doctor_id"
							onfocus="myHide();myRemoveSessionMsg();" /></td> --%>
				</tr>


				<tr>
					<td><label for="phr_type">Report Type</label></td>

				</tr>
				<tr>

					<td><select name="phr_type" id="phr_type" onfocus="myHide();myRemoveSessionMsg();">
							<option value="">Select</option>
							<option value="scan">scan</option>
							<option value="phr">phr</option>
							<option value="pmr">pmr</option>
							<option value="ecg">ecg</option>
					</select></td>
				</tr>

				<tr>
					<td><label for="phr_uploaded_file">Upload Report</label></td>
				</tr>
				<tr>


					<td><input type="file" name="phr_uploaded_file"
						id="phr_uploaded_file" onfocus="myHide();myRemoveSessionMsg();"/></td>
				</tr>
				<tr>

					<td><p class="rq">(Format type: .pdf, .jpg,
						.png, .tif, .docx&nbsp;&nbsp;&nbsp;Max size: 2MB)</p></td>
				</tr>


				<tr>
					<td><label for="phr_description">Notes to Doctor</label></td>

				</tr>
				<tr>
					<td><textarea name="phr_description"
							placeholder="Below 150 Characters..." id="phr_description"
							maxlength='150'  onblur="removeMsg()" onkeyup="checkLength()"
							onfocus="myHide();myRemoveSessionMsg();"></textarea></td>
				</tr>

<tr>
			<td align="right"><input type="submit" class="btn-save" value="Submit" onclick="return ValidationEvent()" /><input type="reset" class="btn-save" value="Cancel" />
			</td>
		</tr>

				<!-- <tr>
					<td align="right"><input type="submit" value="Submit"
						onclick="return ValidationEvent()" /></td>
					<td><input type="reset" value="Cancel" /></td>
				</tr> -->


			</f:form>
		</table>
		<p style="color: red; text-align: center" id="text_area_msg" class="rq"></p>
	</div>
	</div>
	
	
	<script type="text/javascript">
		function ValidationEvent() {
			var doctor_id1 = document.getElementById("doctor_id");
			var phr_type1 = document.getElementById("phr_type");
			var phr_description1 = document.getElementById("phr_description").value;
			
			//alert("doctor_id1.selectedIndex ::"+doctor_id1.selectedIndex);
			//alert("phr_type1.selectedIndex ::"+phr_type1.selectedIndex);
			
			if (doctor_id1.selectedIndex == "") {
				alert("Please select Doctor Name");
				 
				return false;
			} else if (phr_type1.selectedIndex == "") {
				alert("Please select Report Type");
				 
				return false;
			} else if (phr_description1 == "") {
				alert("Please Provide Notes For Doctor");
				
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

		function myRemoveSessionMsg() {
	<%session.removeAttribute("message");
			session.removeAttribute("message1");%>
		}
		
		function cleanForm(){
			document.getElementById("doctor_id").value="";
		}
		
	</script>
	
	<!-- <script type="text/javascript">
	$(document).ready(function(){
	    $("#phr_description").contents().keyup(function(evnt) {
	    	alert("hi");
	    	var textLength = document.getElementById("phr_description").value;
			var length = textLength.length;
			var diff = 150 - length;
			var KeyID = event.keyCode;
			if (diff > 0) {
				document.getElementById("text_area_msg").innerHTML = diff
						+ ' Characters Remaining';
			} else {
				document.getElementById("text_area_msg").innerHTML = 'Text box will not allow more than 150 characters';
			}
	    });
	});
	</script> -->
</body>
</html>
