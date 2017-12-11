<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
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
		<a href="${pageContext.request.contextPath}/phr/home.htm">Home</a>
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
</html>