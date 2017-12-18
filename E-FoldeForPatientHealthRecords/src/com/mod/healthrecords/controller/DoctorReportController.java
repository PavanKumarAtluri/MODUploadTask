package com.mod.healthrecords.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.mod.healthrecords.beans.bo.DoctorReportResponse;
import com.mod.healthrecords.beans.bo.PatientHealthReportResp;
import com.mod.healthrecords.service.PatientHealthRecordsServiceI;
import com.mod.healthrecords.utils.JsonUtil;

@Controller("doctorReportController")
@RequestMapping("/doctorReportDisplay")
public class DoctorReportController {
	
	@Autowired
	private PatientHealthRecordsServiceI patientHealthRecordsService;

	@RequestMapping(value = "/doctor_report_result.htm", method = RequestMethod.GET)
	public String getPhrDetailsForDoctor(@RequestParam("id") int doctor_id, Map<String, Object> map) {
		List<DoctorReportResponse> list = patientHealthRecordsService.getDoctorReport(doctor_id);
		map.put("resultList", list);
		return "doctor_report_result";
	}

	@RequestMapping(value = "/patientnameCheck.htm", method = RequestMethod.GET)
	@ResponseBody
	public String getpatientName(@RequestParam("term") String str) {
		System.out.println("Data from ajax call " + str);
		ArrayList<String> list = patientHealthRecordsService.getAllPatients(str);
		System.out.println(list);
		String json = new Gson().toJson(list);
		return json;
	}

	@RequestMapping(value = "/get_reports_by_patient_name.htm", method = RequestMethod.GET)
	@ResponseBody
	public String getReportsbyPatientNameWithajax(@RequestParam("name") String name, Map<String, Object> map,
			HttpSession session) {
		System.out.println("DoctorReportController.getReportsbyPatientNameWithajax().name::"+name);
		List<DoctorReportResponse> list = null;
		String did = (String) session.getAttribute("doctorId");
		Integer doctorIdInt = Integer.valueOf(did);
		list = patientHealthRecordsService.getRecordsByPatientname(name, doctorIdInt);
		
		return JsonUtil.javaToJson(list);
	}
}
