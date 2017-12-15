package com.mod.healthrecords.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mod.healthrecords.beans.bo.PatientHealthReportResp;
import com.mod.healthrecords.command.PHRDisplayCommand;
import com.mod.healthrecords.service.PatientHealthRecordsServiceI;
import com.mod.healthrecords.utils.JsonUtil;

@Controller("phrDetailsDisplayController")
@RequestMapping("/phrDetailsDisplay")
public class PHRDetailsDisplayController {
	
	/*
	
	@Autowired
	private PatientHealthRecordsServiceI patientHealthRecordsService;
	
	@RequestMapping(value="/phr_detals_form.htm" , method=RequestMethod.GET)
	public String phrDisplayHome(Map<String,Object> map){
		PHRDisplayCommand phrDisplayCommand=new PHRDisplayCommand();
		map.put("phrDisplayCommand", phrDisplayCommand);
		return "phr_detals_form";
	}
	
	@RequestMapping(value="/phr_detals_form.htm" , method=RequestMethod.POST)
	public String phrDisplayProcess(@ModelAttribute("phrDisplayCommand") PHRDisplayCommand cmd,Map<String,Object> map){
		
		
		List<PatientHealthReportResp> list=patientHealthRecordsService.getPHRInfo(cmd.getPatient_id(), cmd.getPhr_type());
		
		map.put("resultList", list);
		return "phr_detals_result";
	}
	
	 */
	
	@Autowired
	private PatientHealthRecordsServiceI patientHealthRecordsService;
	
	@RequestMapping(value="/phr_detals_form.htm" , method=RequestMethod.GET)
	public String phrDisplayHome(Map<String,Object> map){
		PHRDisplayCommand phrDisplayCommand=new PHRDisplayCommand();
		map.put("phrDisplayCommand", phrDisplayCommand);
		return "phr_detals_form";
	}
	
	@RequestMapping(value="/phr_detals_form.htm" , method=RequestMethod.POST)
	public String phrDisplayProcess(@ModelAttribute("phrDisplayCommand") PHRDisplayCommand cmd,Map<String,Object> map, HttpSession session){
		String userId = (String) session.getAttribute("userId");
		Integer userIdInt = Integer.valueOf(userId);
		List<PatientHealthReportResp> list=patientHealthRecordsService.getPHRInfo(userIdInt, cmd.getPhr_type());
		map.put("resultList", list);
		return "phr_detals_result";
	}
	
	@RequestMapping(value="/get_reports_by_type.htm" , method=RequestMethod.GET)
	@ResponseBody
	public String getReportswithajax(@RequestParam("type") String reportType,Map<String,Object> map, HttpSession session){
		List<PatientHealthReportResp> list=null;
		String userId = (String) session.getAttribute("userId");
		Integer userIdInt = Integer.valueOf(userId);
		
		if(reportType.equalsIgnoreCase("all"))
			list=patientHealthRecordsService.getAllPatientReportsById(userIdInt);
		else
			list=patientHealthRecordsService.getPHRInfo(userIdInt, reportType);
		
		
		return JsonUtil.javaToJson(list);
	}
	
	@RequestMapping(value="/patient_all_reports_display.htm" , method=RequestMethod.GET)
	public String getAllReportsbyId(Map<String,Object> map, HttpSession session){
		String userId = (String) session.getAttribute("userId");
		Integer userIdInt = Integer.valueOf(userId);
		List<PatientHealthReportResp> list=patientHealthRecordsService.getAllPatientReportsById(userIdInt);
		map.put("allReports", list);
		return "patient_all_reports_display_page";
	}
	
	
}
