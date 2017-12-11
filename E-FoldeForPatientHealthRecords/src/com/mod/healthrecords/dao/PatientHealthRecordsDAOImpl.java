package com.mod.healthrecords.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.mod.healthrecords.beans.bo.Doctor;
import com.mod.healthrecords.beans.bo.DoctorReportResponse;
import com.mod.healthrecords.beans.bo.Patient;
import com.mod.healthrecords.beans.bo.PatientHealthReport;
import com.mod.healthrecords.beans.bo.PatientHealthReportResp;
import com.mod.healthrecords.constants.QueryConstants;

@Repository("patientHealthRecordsDAOImpl")
public class PatientHealthRecordsDAOImpl implements PatientHealthRecordsDAOI {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Patient getPatientDetails(int pid) {
		return jdbcTemplate.queryForObject(QueryConstants.GET_PATIENT_DETAILS_QUERY, new PatientDetailsRowMapper(),
				pid);
	}

	@Override
	public Doctor getDoctorDetails(int did) {

		return jdbcTemplate.queryForObject(QueryConstants.GET_DOCTOR_DETAILS_QUERY, new DoctorDetailsRowMapper(), did);
	}

	@Override
	public int insertPatientRecord(PatientHealthReport patientHealthReport) {
		System.out.println(new java.sql.Date(patientHealthReport.getPhr_uploaded_date().getTime()));
		System.out.println(patientHealthReport.getPhr_uploaded_date());

		return jdbcTemplate.update(QueryConstants.INSERT_PATIENT_HEALTH_RECORD_DETAILS_QUERY,
				patientHealthReport.getPhr_id(), patientHealthReport.getPatient_id(),
				patientHealthReport.getDoctor_id(), patientHealthReport.getPhr_uploaded_date(),
				patientHealthReport.getPhr_type(), patientHealthReport.getPhr_uploaded_path_original(),
				patientHealthReport.getPhr_uploaded_path_pdf(), patientHealthReport.getPhr_description());
	}

	@Override
	public int getPHRId() {
		return jdbcTemplate.queryForObject(QueryConstants.GET_PHR_ID_QUERY, Integer.class);
	}

	@Override
	public List<PatientHealthReportResp> getPHRInfo(int pid, String reportType) {
		return jdbcTemplate.query(QueryConstants.GET_PHR_DETAILS, new PhrDetailsExtractor(), pid, reportType);
	}

	@Override
	public List<DoctorReportResponse> getDoctorReport(Integer doctor_id) {
		return jdbcTemplate.query(QueryConstants.GET_DOCTOR_REPORT, new DoctorReportExtractor(), doctor_id);
	}
	
	@Override
	public List<Doctor> getDoctorDetails() {
		return jdbcTemplate.query(QueryConstants.GET_DOCTORS_DETAILS, new AllDoctorDetailsExtractor());
	}

	private static class PatientDetailsRowMapper implements RowMapper<Patient> {

		@Override
		public Patient mapRow(ResultSet rs, int index) throws SQLException {
			Patient patient = null;

			patient = new Patient();
			patient.setPatient_id(rs.getInt(1));
			patient.setPatient_title(rs.getString(2));
			patient.setPatient_name(rs.getString(3));
			patient.setPatient_age(rs.getInt(4));
			patient.setPatient_sex(rs.getString(5));
			patient.setPatient_mobileno(rs.getString(6));
			patient.setPatient_email_id(rs.getString(7));
			patient.setPatient_image_path(rs.getString(8));
			patient.setPatient_identity_path(rs.getString(9));

			return patient;
		}

	}

	private static class DoctorDetailsRowMapper implements RowMapper<Doctor> {

		@Override
		public Doctor mapRow(ResultSet rs, int index) throws SQLException {
			Doctor doctor = null;

			doctor = new Doctor();
			doctor.setDoctor_id(rs.getInt(1));
			doctor.setDoctor_title(rs.getString(2));
			doctor.setDoctor_name(rs.getString(3));
			doctor.setDoctor_specialization(rs.getString(4));
			doctor.setDoctor_mobileno(rs.getString(5));
			doctor.setDoctor_emailid(rs.getString(6));
			doctor.setHospital(rs.getString(7));
			doctor.setCountry(rs.getString(8));
			doctor.setState(rs.getString(9));
			doctor.setCity(rs.getString(10));
			doctor.setDoctor_image_path(rs.getString(11));

			return doctor;
		}

	}

	private static class PhrDetailsExtractor implements ResultSetExtractor<List<PatientHealthReportResp>> {
		public static final SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		@Override
		public List<PatientHealthReportResp> extractData(ResultSet rs) throws SQLException, DataAccessException {
			PatientHealthReportResp patientHealthReport = null;
			Timestamp stamp = null;

			List<PatientHealthReportResp> listOfPHRs = new ArrayList<>();
			while (rs.next()) {
				patientHealthReport = new PatientHealthReportResp();

				patientHealthReport.setPhr_id(rs.getInt(1));
				patientHealthReport.setPatient_id(rs.getInt(2));
				patientHealthReport.setDoctor_id(rs.getInt(3));
				System.out.println("rs.getTimestamp(4) :: " + rs.getTimestamp(4));
				stamp = new Timestamp(rs.getTimestamp(4).getTime());
				System.out.println("stamp :: " + stamp);
				System.out.println("Pavan ::" + formate.format(stamp));
				patientHealthReport.setPhr_uploaded_date(formate.format(stamp));
				patientHealthReport.setPhr_type(rs.getString(5));
				patientHealthReport.setPhr_uploaded_path_original(rs.getString(6));
				patientHealthReport.setPhr_uploaded_path_pdf(rs.getString(7));
				patientHealthReport.setPhr_description(rs.getString(8));
				patientHealthReport.setDoctor_name(rs.getString(9));
				patientHealthReport.setDoctor_specialization(rs.getString(10));;

				listOfPHRs.add(patientHealthReport);
			}
			return listOfPHRs;
		}

	}

	private static class DoctorReportExtractor implements ResultSetExtractor<List<DoctorReportResponse>> {

		@Override
		public List<DoctorReportResponse> extractData(ResultSet rs) throws SQLException, DataAccessException {

			DoctorReportResponse drr = null;

			List<DoctorReportResponse> listOfDtr = new ArrayList<>();
			while (rs.next()) {
				drr = new DoctorReportResponse();

				drr.setDoctor_id(rs.getInt(1));
				drr.setPatient_id(rs.getInt(2));
				drr.setPatient_name(rs.getString(3));
				drr.setPatient_age(rs.getInt(4));
				drr.setPatient_sex(rs.getString(5));
				drr.setPhr_uploaded_date(rs.getDate(6));
				drr.setPhr_type(rs.getString(7));
				drr.setPhr_uploaded_path_original(rs.getString(8));
				drr.setPhr_uploaded_path_pdf(rs.getString(9));
				drr.setPhr_description(rs.getString(10));

				listOfDtr.add(drr);
			}
			return listOfDtr;

		}

	}

	private static class AllDoctorDetailsExtractor implements ResultSetExtractor<List<Doctor>> {

		@Override
		public List<Doctor> extractData(ResultSet rs) throws SQLException, DataAccessException {
			Doctor doctor = null;
			
			List<Doctor> listOfDtrs = new ArrayList<>();
			while(rs.next()){
				doctor = new Doctor();
				doctor.setDoctor_id(rs.getInt(1));
				doctor.setDoctor_name(rs.getString(2));
				doctor.setDoctor_specialization(rs.getString(3));
				
				listOfDtrs.add(doctor);
			}
			

			return listOfDtrs;
		}

	}

}
