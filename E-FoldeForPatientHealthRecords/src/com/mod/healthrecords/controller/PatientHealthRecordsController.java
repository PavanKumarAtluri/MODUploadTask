package com.mod.healthrecords.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.print.Doc;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mod.healthrecords.beans.bo.Doctor;
import com.mod.healthrecords.beans.dto.PatientHealthReportDTO;
import com.mod.healthrecords.command.PatientHealthReportCommand;
import com.mod.healthrecords.exceptions.PHRException;
import com.mod.healthrecords.service.PatientHealthRecordsServiceI;

@Controller("patientHealthRecordsController")
@RequestMapping(value = "/phr")
public class PatientHealthRecordsController {
	
	/*
	
	@Autowired
	private PatientHealthRecordsServiceI patientHealthRecordsService;

	@RequestMapping(value = "/home.htm", method = RequestMethod.GET)
	public String getHomePage() {
		return "home";
	}

	@RequestMapping(value = "/phr_form_page.htm", method = RequestMethod.GET)
	public String getPhrformPage(Map<String, Object> map) {
		PatientHealthReportCommand patientHealthReportCommand=new PatientHealthReportCommand();
		map.put("patientHealthReportCommand", patientHealthReportCommand);
		return "phr_form_page";
	}

	@RequestMapping(value = "/phr_form_page.htm", method = RequestMethod.POST)
	public String processPhrformPage(@ModelAttribute("patientHealthReportCommand") PatientHealthReportCommand cmd,
			Map<String, Object> map,HttpSession session) throws PHRException {

		if (cmd.getPhr_uploaded_file().isEmpty()) {
			session.setAttribute("message", "Please select a file to upload");
			//redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
			//return "redirect:phr/uploadStatus.htm";
			//return "uploadStatus";
			return "phr_form_page";
			
		} else if ((cmd.getPhr_uploaded_file().getSize() >= 2097152)) {
			session.setAttribute("message", "Please select a file of size below 2MB");
			//redirectAttributes.addFlashAttribute("message", "Please select a file of size belo 15kb");
			//return "redirect:phr/uploadStatus.htm/";
			//return "uploadStatus";
			return "phr_form_page";
		}

		try {

			PatientHealthReportDTO patientHealthReport = new PatientHealthReportDTO();
			BeanUtils.copyProperties(cmd, patientHealthReport);

			int status = patientHealthRecordsService.insertPatientRecord(patientHealthReport);

			if (status == 1){
				session.setAttribute("message1","Your file has been successfully uploaded");
				//session.setAttribute("message1","You successfully uploaded '" + cmd.getPhr_uploaded_file().getOriginalFilename() + "'");
				//redirectAttributes.addFlashAttribute("message","You successfully uploaded '" + cmd.getPhr_uploaded_file().getOriginalFilename() + "'");
			}			

		} catch (PHRException e) {
			e.printStackTrace();
			throw new PHRException("Internal Problem Try Again..");
		}

		//return "redirect:phr/uploadStatus.htm/";
		//return "uploadStatus";
		return "phr_form_page";
	}

	@RequestMapping(value="/uploadStatus.htm",method=RequestMethod.GET)
	public String uploadStatus() {
		return "uploadStatus";
	}

	@ExceptionHandler(PHRException.class)
	public String handleError1(PHRException e,HttpSession session) {
		session.setAttribute("message", e.getCause().getMessage());
		//redirectAttributes.addFlashAttribute("message", e.getCause().getMessage());
		//return "redirect:phr/uploadStatus.htm/";
		//return "uploadStatus";
		return "phr_form_page";
	}
	
	
	*/
	
	@Autowired
	private PatientHealthRecordsServiceI patientHealthRecordsService;

	@RequestMapping(value = "/home.htm", method = RequestMethod.GET)
	public String getHomePage() {
		return "home";
	}

	@RequestMapping(value = "/phr_form_page.htm", method = RequestMethod.GET)
	public String getPhrformPage(Map<String, Object> map,HttpSession session) {
		PatientHealthReportCommand patientHealthReportCommand=new PatientHealthReportCommand();
		//patientHealthReportCommand.setPatient_id(Integer.parseInt((String)session.getAttribute("userId")));
		map.put("patientHealthReportCommand", patientHealthReportCommand);
		return "phr_form_page";
	}
	
	@ModelAttribute("doctorsList")
	public Map<Integer,String> populateDoctorsDetails(){
		
		List<Doctor> list=patientHealthRecordsService.getDoctorDetails();
		Map<Integer,String> doctorsList = new LinkedHashMap<Integer,String>();
		/*doctorsList.put(-1, "Select");*/
		for(Doctor d:list){
			doctorsList.put(d.getDoctor_id(), d.getDoctor_name()+"-"+d.getDoctor_specialization());
		}
		
		return doctorsList;
		
	}
	
	@RequestMapping(value = "/patient_details.htm", method = RequestMethod.POST)
	public String getPatientDetails(HttpServletRequest req, HttpSession session) {
		String userId = req.getParameter("uname");
		String password = req.getParameter("pwd");
		
		if(userId.equalsIgnoreCase("1001") && password.equals("welcome@123")) {
			session.setAttribute("userId", userId);
			session.removeAttribute("errorMsg");
			return "patient_details";
		} else if(userId.equalsIgnoreCase("1002") && password.equals("welcome@123")) {
			session.setAttribute("userId", userId);
			session.removeAttribute("errorMsg");
			return "patient_details";
		} else if(userId.equalsIgnoreCase("1003") && password.equals("welcome@123")) {
			session.setAttribute("userId", userId);
			session.removeAttribute("errorMsg");
			return "patient_details";
		}
		else{
			session.setAttribute("errorMsg", "Invalid Credentials");
		}
		return "patient_login_form";
	}
	
	@RequestMapping(value = "/patient_details.htm", method = RequestMethod.GET)
	public String getPatientDetails1(HttpServletRequest req, HttpSession session) {
		return "patient_details";
	}
	
	@RequestMapping(value = "/doctor_details.htm", method = RequestMethod.POST)
	public String getDoctorDetails(HttpServletRequest req, HttpSession session) {
		String doctorId = req.getParameter("docname");
		String password = req.getParameter("docpwd");
		
		if(doctorId.equalsIgnoreCase("10001") && password.equals("welcome@123")) {
			session.setAttribute("doctorId", doctorId);
			session.removeAttribute("docerrorMsg");
			return "doctor_details";
		} else if(doctorId.equalsIgnoreCase("10002") && password.equals("welcome@123")) {
			session.setAttribute("doctorId", doctorId);
			session.removeAttribute("docerrorMsg");
			return "doctor_details";
		} else if(doctorId.equalsIgnoreCase("10003") && password.equals("welcome@123")) {
			session.setAttribute("doctorId", doctorId);
			session.removeAttribute("docerrorMsg");
			return "doctor_details";
		}
		else{
			session.setAttribute("docerrorMsg", "Invalid Credentials");
		}
		return "doctor_login_form";
	}
	
	@RequestMapping(value = "/doctor_details.htm", method = RequestMethod.GET)
	public String getDoctorDetails1(HttpServletRequest req, HttpSession session) {
	
		return "doctor_details";
		
	}
	
	@RequestMapping(value = "/logout.htm", method = RequestMethod.GET)
	public String logout(HttpServletRequest req, HttpSession session) {
		session.removeAttribute("userId");
		session.invalidate();
		return "home";
	}
	
	@RequestMapping(value = "/patient_login.htm", method = RequestMethod.GET)
	public String getPatientLoginPage(){
		return "patient_login_form";
	}
	
	@RequestMapping(value = "/doctor_login.htm", method = RequestMethod.GET)
	public String getDoctorLoginPage(){
		return "doctor_login_form";
	}

	@RequestMapping(value = "/phr_form_page.htm", method = RequestMethod.POST)
	public String processPhrformPage(@ModelAttribute("patientHealthReportCommand") PatientHealthReportCommand cmd,
			Map<String, Object> map,HttpSession session) throws PHRException {

		if (cmd.getPhr_uploaded_file().isEmpty()) {
			session.setAttribute("message", "Please select a file to upload");
			//redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
			//return "redirect:phr/uploadStatus.htm";
			//return "uploadStatus";
			return "phr_form_page";
			
		} else if ((cmd.getPhr_uploaded_file().getSize() >= 2097152)) {
			session.setAttribute("message", "Please select a file of size below 2MB");
			//redirectAttributes.addFlashAttribute("message", "Please select a file of size belo 15kb");
			//return "redirect:phr/uploadStatus.htm/";
			//return "uploadStatus";
			return "phr_form_page";
		}

		try {

			PatientHealthReportDTO patientHealthReport = new PatientHealthReportDTO();
			
			BeanUtils.copyProperties(cmd, patientHealthReport);
			
			int id=Integer.parseInt((String)session.getAttribute("userId"));
			
			int status = patientHealthRecordsService.insertPatientRecord(patientHealthReport,id);

			if (status == 1){
				System.out.println("if successs");
				session.setAttribute("message1","Your file has been successfully uploaded");
				//session.setAttribute("message1","You successfully uploaded '" + cmd.getPhr_uploaded_file().getOriginalFilename() + "'");
				//redirectAttributes.addFlashAttribute("message","You successfully uploaded '" + cmd.getPhr_uploaded_file().getOriginalFilename() + "'");
			}			

		} catch (PHRException e) {
			e.printStackTrace();
			throw new PHRException("Internal Problem Try Again..");
		}

		//return "redirect:phr/uploadStatus.htm/";
		//return "uploadStatus";
		return "phr_form_page";
	}

	/*@RequestMapping(value="/uploadStatus.htm",method=RequestMethod.GET)
	public String uploadStatus() {
		return "uploadStatus";
	}*/

	@ExceptionHandler(PHRException.class)
	public String handleError1(PHRException e,HttpSession session) {
		session.setAttribute("message", e.getCause().getMessage());
		//redirectAttributes.addFlashAttribute("message", e.getCause().getMessage());
		//return "redirect:phr/uploadStatus.htm/";
		//return "uploadStatus";
		return "phr_form_page";
	}
}
