<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>

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
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PHR Display Form</title>
</head>
<body>
	<h1 style="color: blue; text-align: center">Find Your Reports</h1>
	<table align="center" bgcolor="#D6DBDF">
		<f:form commandName="phrDisplayCommand" method="POST">
			<tr>
				<td><label for="patient_id">Enter Patient Id</label></td>
				<td><f:input path="patient_id" id="patient_id" /></td>
			</tr>
			<tr>
				<td><label for="phr_type">Please select report type</label></td>
				<td><select name="phr_type" id="phr_type">
						<option value="-1">Select Report Type</option>
						<option value="scan">scan</option>
						<option value="phr">phr</option>
						<option value="pmr">pmr</option>
						<option value="ecg">ecg</option>
				</select></td>
			</tr>
			<tr>
				<td><input type="submit" value="Submit"
					onclick="return ValidationEvent()" /></td>
				<td><input type="reset" value="Cancel" /></td>
			</tr>
		</f:form>
	</table>
	<p style="text-align: center"><a href="${pageContext.request.contextPath}/phr/home.htm">Home</a></p>
	<script type="text/javascript">
		function ValidationEvent() {
			var patient_id1 = document.getElementById("patient_id").value;
			var phr_type1 = document.getElementById("phr_type");

			if (patient_id1 == "") {
				alert("Patient Id must be filled out");
				return false;
			} else if (phr_type1.selectedIndex == "") {
				alert("Please select PHR Type");
				return false;
			}
		}
	</script>
</body>
</html>