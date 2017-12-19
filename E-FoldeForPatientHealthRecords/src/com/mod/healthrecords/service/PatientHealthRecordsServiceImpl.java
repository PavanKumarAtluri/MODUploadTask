package com.mod.healthrecords.service;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.DocumentException;
import com.mod.healthrecords.beans.bo.Doctor;
import com.mod.healthrecords.beans.bo.DoctorReportResponse;
import com.mod.healthrecords.beans.bo.Patient;
import com.mod.healthrecords.beans.bo.PatientHealthReport;
import com.mod.healthrecords.beans.bo.PatientHealthReportResp;
import com.mod.healthrecords.beans.dto.PatientHealthReportDTO;
import com.mod.healthrecords.constants.FileConstants;
import com.mod.healthrecords.dao.PatientHealthRecordsDAOI;
import com.mod.healthrecords.exceptions.PHRException;
import com.mod.healthrecords.utils.ImageToPdfUtiil;

@Service("patientHealthRecordsServiceImpl")
public class PatientHealthRecordsServiceImpl implements PatientHealthRecordsServiceI {

	@Autowired
	private PatientHealthRecordsDAOI patientHealthRecordsDAO;

	@Override
	public Patient getPatientDetails(int pid) {

		return patientHealthRecordsDAO.getPatientDetails(pid);
	}

	@Override
	public Doctor getDoctorDetails(int did) {
		return patientHealthRecordsDAO.getDoctorDetails(did);
	}

	@Override
	public int insertPatientRecord(PatientHealthReportDTO patientHealthReport, int id) throws PHRException {
		PatientHealthReport phr = new PatientHealthReport();
		boolean flag = false;
		String pdf_path = null;
		
		String file_loc1 = FileConstants.ORIGINAL_FILE__DEST_LOC + "/"
				+ String.valueOf(getPatientDetails(id).getPatient_id()) + "_"
				+ new SimpleDateFormat("yyyy_MM_dd HH_mm_SS").format(new Date()) + "_"
				+ patientHealthReport.getPhr_uploaded_file().getOriginalFilename();
		// System.out.println(file_loc1);

		String file_loc2 = FileConstants.PDF_FILE_DEST_LOC;
		// System.out.println(file_loc2);

		File file1 = new File(file_loc1);
		if (!file1.exists()) {
			file1.mkdirs();
		}

		File file2 = new File(file_loc2);
		if (!file2.exists()) {
			file2.mkdirs();
		}

		try {
			patientHealthReport.getPhr_uploaded_file().transferTo(file1);
			file_loc2 = file_loc2 + "/"
					+ String.valueOf(getPatientDetails(id).getPatient_id()) + "_"
					+ new SimpleDateFormat("yyyy_MM_dd HH_mm_SS").format(new Date()) + "_"
					+ patientHealthReport.getPhr_uploaded_file().getOriginalFilename();
			String ext = FilenameUtils.getExtension(file_loc2);
			if (!("pdf".equalsIgnoreCase(ext)))
				file_loc2 = file_loc2.replaceAll(file_loc2.substring(file_loc2.lastIndexOf('.'), file_loc2.length()),
						".pdf");

			try {
				System.out.println("Inside Service pdf_path::" + file_loc2);
				flag = ImageToPdfUtiil.convertAndSaveImageAsPdf(file_loc1.toString(), file_loc2, patientHealthReport);

			} catch (DocumentException e) {
				if (flag == false)
					file1.delete();
				e.printStackTrace();
			}
		} catch (IllegalStateException | IOException e) {
			file1.delete();
			e.printStackTrace();
			throw new PHRException("Getting Problem While Uploading Doc, Please Verify Details You Submitted");

		}

		phr.setDoctor_id(patientHealthReport.getDoctor_id());
		phr.setPatient_id(id);
		int a = patientHealthRecordsDAO.getPHRId();
		// System.out.println(a);
		phr.setPhr_id(a);
		phr.setPhr_type(patientHealthReport.getPhr_type());
		phr.setPhr_uploaded_date(new Date());
		phr.setPhr_uploaded_path_original(file_loc1);
		phr.setPhr_uploaded_path_pdf(file_loc2);
		phr.setPhr_description(patientHealthReport.getPhr_description());

		int count = patientHealthRecordsDAO.insertPatientRecord(phr);

		return count;
	}

	@Override
	public List<PatientHealthReportResp> getPHRInfo(int pid, String reportType) {

		return patientHealthRecordsDAO.getPHRInfo(pid, reportType);
	}

	@Override
	public List<DoctorReportResponse> getDoctorReport(Integer doctor_id) {

		return patientHealthRecordsDAO.getDoctorReport(doctor_id);
	}

	@Override
	public List<Doctor> getDoctorDetails() {
		return patientHealthRecordsDAO.getDoctorDetails();
	}

	@Override
	public List<PatientHealthReportResp> getAllPatientReportsById(int pid) {
		return patientHealthRecordsDAO.getAllPatientReportsById(pid);
	}

	@Override
	public ArrayList<String> getAllPatients(String str) {
		ArrayList<String> list = new ArrayList<String>();
		PreparedStatement ps = null;
		Connection con = null;
		String data;
		try {
			String output = str.toLowerCase();
			String ch = "%"+ output + "%";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
			ps = con.prepareStatement("SELECT PATIENT_NAME FROM PATIENT_TAB WHERE PATIENT_NAME  LIKE '" + ch + "'");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				data = rs.getString(1);
				System.out.println(data);
				list.add(data);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<DoctorReportResponse> getRecordsByPatientname(String name,int did) {
		
		return patientHealthRecordsDAO.getAllPatientReportsByName(name, did);
	}

}
