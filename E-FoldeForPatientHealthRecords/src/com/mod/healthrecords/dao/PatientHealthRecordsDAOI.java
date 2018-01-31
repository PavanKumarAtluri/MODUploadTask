package com.mod.healthrecords.dao;

import java.util.ArrayList;
import java.util.List;

import com.mod.healthrecords.beans.bo.Doctor;
import com.mod.healthrecords.beans.bo.DoctorReportResponse;
import com.mod.healthrecords.beans.bo.Patient;
import com.mod.healthrecords.beans.bo.PatientHealthReport;
import com.mod.healthrecords.beans.bo.PatientHealthReportResp;

public interface PatientHealthRecordsDAOI {
	public Patient getPatientDetails(int pid);

	public Doctor getDoctorDetails(int did);

	public int insertPatientRecord(PatientHealthReport patientHealthReport);
	
	public int getPHRId();
	
	public List<PatientHealthReportResp> getPHRInfo(int pid ,String reportType);
	
	public List<DoctorReportResponse> getDoctorReport(Integer doctor_id);
	
	public List<Doctor> getDoctorDetails();
	
	public List<PatientHealthReportResp> getAllPatientReportsById(int pid);

	public List<DoctorReportResponse> getAllPatientReportsByName(String name,String type, int did);
	
	public int selectPharmacyIdByPatientId(int patientId);
	
	public int updatePrescriptionDetailsByPhrId(String prescription,int phrId);
	
	public int updateDeliveryStatusByPhrId(int phrId);
	
	public int updatePaymentStatusByPhrId(int phrId);
	
	public int getPHRIdByOrderId(int orderId);
	
	public int updateDeliveryStatusOfOrderTabByPhrId(int phrId);
	
	public List<PatientHealthReportResp> getReportsForAdvSearch(int patientId, String name,String speciality,String type,String dstatus,String pstatus);
}
