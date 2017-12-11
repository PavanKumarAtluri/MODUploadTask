package com.mod.healthrecords.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mod.healthrecords.beans.bo.DoctorReportResponse;
import com.mod.healthrecords.service.PatientHealthRecordsServiceI;

@Controller("doctorReportController")
@RequestMapping("/doctorReportDisplay")
public class DoctorReportController {
	//url?id=1001
	@Autowired
	private PatientHealthRecordsServiceI patientHealthRecordsService;
	
	@RequestMapping(value="/doctor_report_result.htm",method=RequestMethod.GET)
	public String getPhrDetailsForDoctor(@RequestParam("id") int doctor_id,Map<String,Object> map){
		List<DoctorReportResponse> list=patientHealthRecordsService.getDoctorReport(doctor_id);
		map.put("resultList", list);
		return "doctor_report_result";
	}
	

}
