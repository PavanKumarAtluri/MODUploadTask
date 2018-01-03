package com.mod.healthrecords.service;

import java.util.ArrayList;
import java.util.List;

import com.mod.healthrecords.beans.bo.Doctor;
import com.mod.healthrecords.beans.bo.DoctorReportResponse;
import com.mod.healthrecords.beans.bo.Patient;
import com.mod.healthrecords.beans.bo.PatientHealthReportResp;
import com.mod.healthrecords.beans.dto.DoctorPrescription;
import com.mod.healthrecords.beans.dto.Order;
import com.mod.healthrecords.beans.dto.PatientHealthReportDTO;
import com.mod.healthrecords.beans.dto.Resp;
import com.mod.healthrecords.beans.dto.Response;
import com.mod.healthrecords.exceptions.PHRException;

public interface PatientHealthRecordsServiceI {
	public Patient getPatientDetails(int pid);

	public Doctor getDoctorDetails(int did);

	public int insertPatientRecord(PatientHealthReportDTO patientHealthReport, int id) throws PHRException;

	public List<PatientHealthReportResp> getPHRInfo(int pid, String reportType);

	public List<DoctorReportResponse> getDoctorReport(Integer doctor_id);

	public List<Doctor> getDoctorDetails();

	public List<PatientHealthReportResp> getAllPatientReportsById(int pid);

	public ArrayList<String> getAllPatients(String str);

	public List<DoctorReportResponse> getRecordsByPatientname(String name, int did);

	public Response sendDoctorPrescriptionToRequestedPharmacy(DoctorPrescription doctorPrescription);

	public int getpharmacyIdByPatientId(int patientId);

	public Resp getOrderDetailsByOrderId(int orderId);

	public List<Order> getOrderDetailsByPatientIdAndPharmacyId(int pharmacyId, int patientId);

	public List<Order> getAllOrdersByPharmacyId(int pharmacyId);

	public Resp changeDeliveryStatus(int orderId);
}
