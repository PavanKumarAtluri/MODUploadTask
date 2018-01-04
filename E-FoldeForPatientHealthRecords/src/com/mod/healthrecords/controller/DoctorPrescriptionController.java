package com.mod.healthrecords.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mod.healthrecords.beans.dto.DoctorPrescription;
import com.mod.healthrecords.beans.dto.Response;
import com.mod.healthrecords.service.PatientHealthRecordsServiceI;

@Controller
public class DoctorPrescriptionController {
	
	@Autowired
	private PatientHealthRecordsServiceI patientHealthRecordsService;
	
	@RequestMapping(value = "/addPrescription.htm", method = RequestMethod.GET)
    public String addPrescription(){
		return "add_patient_prescription";
	}
	
	@RequestMapping(value = "/addPrescription.htm", method = RequestMethod.POST)
    public String processPrescription(Map<String,Object> map,HttpServletRequest req,@RequestParam("phr_id") Integer phr_id,@RequestParam("doctor_id") Integer doctor_id,@RequestParam("patient_id") Integer patient_id){
		
		
		
		//get pharmacy Id
		int pharmacy_id=patientHealthRecordsService.getpharmacyIdByPatientId(patient_id);
		
		
		
		//call service method
		DoctorPrescription doctorPrescription=new DoctorPrescription();
		doctorPrescription.setDocId(doctor_id);
		doctorPrescription.setPatId(patient_id);
		doctorPrescription.setPhrId(phr_id);
		doctorPrescription.setPhrmacyId(pharmacy_id);
		doctorPrescription.setMedicalPrescrition(req.getParameter("phr_description"));
		Response response=patientHealthRecordsService.sendDoctorPrescriptionToRequestedPharmacy(doctorPrescription);
		map.put("resultMsg", response.getMsg());
		return "add_patient_prescription";
	}
}
