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
		//System.out.println("PatientHealthRecordsDAOImpl.insertPatientRecord()::"+new java.sql.Timestamp(new java.util.Date().getTime()));
		java.sql.Timestamp stamp=new java.sql.Timestamp(new java.util.Date().getTime());

		return jdbcTemplate.update(QueryConstants.INSERT_PATIENT_HEALTH_RECORD_DETAILS_QUERY,
				patientHealthReport.getPhr_id(), patientHealthReport.getPatient_id(),
				patientHealthReport.getDoctor_id(),stamp,
				patientHealthReport.getPhr_type(), patientHealthReport.getPhr_uploaded_path_original(),
				patientHealthReport.getPhr_uploaded_path_pdf(), patientHealthReport.getPhr_description(),0,0);
	}

	@Override
	public int getPHRId() {
		return jdbcTemplate.queryForObject(QueryConstants.GET_PHR_ID_QUERY, Integer.class);
	}

	@Override
	public List<PatientHealthReportResp> getPHRInfo(int pid, String reportType) {
		return jdbcTemplate.query(QueryConstants.GET_PHR_DETAILS_BY_PATIENT_ID_AND_PHR_TYPE, new PhrDetailsExtractor(), pid, reportType);
	}

	@Override
	public List<DoctorReportResponse> getDoctorReport(Integer doctor_id) {
		return jdbcTemplate.query(QueryConstants.GET_DOCTOR_REPORT_BY_DOCTOR_ID, new DoctorReportExtractor(), doctor_id);
	}
	
	@Override
	public List<Doctor> getDoctorDetails() {
		return jdbcTemplate.query(QueryConstants.GET_DOCTORS_DETAILS, new AllDoctorDetailsExtractor());
	}
	
	@Override
	public List<PatientHealthReportResp> getAllPatientReportsById(int pid) {
		return jdbcTemplate.query(QueryConstants.GET_ALL_REPORTS_BY_PATIENT_ID, new PhrDetailsExtractor(), pid);
	}
	
	@Override
	public List<DoctorReportResponse> getAllPatientReportsByName(String name,String type, int did) {
		System.out.println("type::"+type);
		System.out.println(name.equals("")+" "+name.equals(null)+" "+type.equals("")+" "+ type.equals(null));
		if((name.equals("") || name.equals(null))&& (type.equals("") || type.equals(null))){
			//System.out.println("if1");
			return getDoctorReport(did);
		}else if(!(name.equals("") || name.equals(null)) && (type.equals("") || type.equals(null))){
			//System.out.println("if2");
			//return jdbcTemplate.query(QueryConstants.GET_REPORTS_BY_PATIENT_NAME_AND_DOCTOR_ID, new DoctorReportExtractor(),did, name);
			return jdbcTemplate.query("select ph.PHR_ID, ph.doctor_id, p.patient_id, p.patient_name, p.patient_age, p.patient_sex, to_char(TO_TIMESTAMP(ph.PHR_UPLOADED_DATE),'YYYY-MM-DD HH:MI:SS AM'), ph.phr_type, ph.phr_uploaded_path_original, ph.phr_uploaded_path_pdf, ph.phr_description from phr_tab ph inner join patient_tab p on ph.patient_id = p.patient_id where ph.doctor_id=? and p.patient_name like '%"+name+"%' order by ph.PHR_ID desc", new DoctorReportExtractor(),did);
		}else if(!(type.equals("") || type.equals(null)) && (name.equals("") || name.equals(null))){
			//System.out.println("if3");
			String type1=type.toUpperCase();
			System.out.println(type1);
			return jdbcTemplate.query("select ph.PHR_ID, ph.doctor_id, p.patient_id, p.patient_name, p.patient_age, p.patient_sex, to_char(TO_TIMESTAMP(ph.PHR_UPLOADED_DATE),'YYYY-MM-DD HH:MI:SS AM'), ph.phr_type, ph.phr_uploaded_path_original, ph.phr_uploaded_path_pdf, ph.phr_description from phr_tab ph inner join patient_tab p on ph.patient_id = p.patient_id where ph.doctor_id=? and ph.PHR_TYPE like '%"+type1+"%' order by ph.PHR_ID desc", new DoctorReportExtractor(),did);
		}else{
			//System.out.println("if4");
			String type1=type.toUpperCase();
			return jdbcTemplate.query("select ph.PHR_ID, ph.doctor_id, p.patient_id, p.patient_name, p.patient_age, p.patient_sex, to_char(TO_TIMESTAMP(ph.PHR_UPLOADED_DATE),'YYYY-MM-DD HH:MI:SS AM'), ph.phr_type, ph.phr_uploaded_path_original, ph.phr_uploaded_path_pdf, ph.phr_description from phr_tab ph inner join patient_tab p on ph.patient_id = p.patient_id where ph.doctor_id=? and ph.PHR_TYPE like '%"+type1+"%'and p.patient_name like '%"+name+"%' order by ph.PHR_ID desc", new DoctorReportExtractor(),did);
		}
			
	}
	
	@Override
	public int selectPharmacyIdByPatientId(int patientId) {
		return jdbcTemplate.queryForObject(QueryConstants.GET_PHARMACYID_BY_PATIENTID, Integer.class,patientId);
	}
	
	@Override
	public int updatePrescriptionDetailsByPhrId(String prescription,int phrId) {
		
		return jdbcTemplate.update(QueryConstants.UPDATE_PRESCRIPTION_DETAILS_BY_PHR_ID, prescription, phrId);
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
			patient.setPatient_pharmacy_id(rs.getInt(10));
			patient.setPatient_pharmacy_name(rs.getString(11));

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
		//public static final SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		@Override
		public List<PatientHealthReportResp> extractData(ResultSet rs) throws SQLException, DataAccessException {
			PatientHealthReportResp patientHealthReport = null;
			//Timestamp stamp = null;

			List<PatientHealthReportResp> listOfPHRs = new ArrayList<>();
			while (rs.next()) {
				patientHealthReport = new PatientHealthReportResp();

				patientHealthReport.setPhr_id(rs.getInt(1));
				patientHealthReport.setPatient_id(rs.getInt(2));
				patientHealthReport.setDoctor_id(rs.getInt(3));
				//stamp = new Timestamp(rs.getTimestamp(4).getTime());
				//patientHealthReport.setPhr_uploaded_date(formate.format(stamp));
				patientHealthReport.setPhr_uploaded_date(rs.getString(4));
				patientHealthReport.setPhr_type(rs.getString(5));
				patientHealthReport.setPhr_uploaded_path_original(rs.getString(6));
				patientHealthReport.setPhr_uploaded_path_pdf(rs.getString(7));
				patientHealthReport.setPhr_description(rs.getString(8));
				patientHealthReport.setDoctor_name(rs.getString(9));
				patientHealthReport.setDoctor_specialization(rs.getString(10));
				patientHealthReport.setPatient_prescription(rs.getString(11));
				patientHealthReport.setDeliveryStatus(rs.getInt(12));
				patientHealthReport.setPaymentStatus(rs.getInt(13));

				listOfPHRs.add(patientHealthReport);
			}
			return listOfPHRs;
		}

	}

	private static class DoctorReportExtractor implements ResultSetExtractor<List<DoctorReportResponse>> {
		//public static final SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		@Override
		public List<DoctorReportResponse> extractData(ResultSet rs) throws SQLException, DataAccessException {

			DoctorReportResponse drr = null;
			//Timestamp stamp = null;

			List<DoctorReportResponse> listOfDtr = new ArrayList<>();
			while (rs.next()) {
				drr = new DoctorReportResponse();
				
				drr.setPhr_id(rs.getInt(1));
				drr.setDoctor_id(rs.getInt(2));
				drr.setPatient_id(rs.getInt(3));
				drr.setPatient_name(rs.getString(4));
				drr.setPatient_age(rs.getInt(5));
				drr.setPatient_sex(rs.getString(6));
				//stamp = new Timestamp(rs.getTimestamp(7).getTime());
				//drr.setPhr_uploaded_date(formate.format(stamp));
				drr.setPhr_uploaded_date(rs.getString(7));
				drr.setPhr_type(rs.getString(8));
				drr.setPhr_uploaded_path_original(rs.getString(9));
				drr.setPhr_uploaded_path_pdf(rs.getString(10));
				drr.setPhr_description(rs.getString(11));

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

	@Override
	public int updateDeliveryStatusByPhrId(int phrId) {
		return jdbcTemplate.update(QueryConstants.UPDATE_DELIVERY_STATUS_BY_PHR_ID, 1, phrId);
	}

	@Override
	public int updatePaymentStatusByPhrId( int phrId) {
		return jdbcTemplate.update(QueryConstants.UPDATE_PAYMENT_STATUS_BY_PHR_ID, 1, phrId);
	}

	@Override
	public int getPHRIdByOrderId(int orderId) {
		
		return jdbcTemplate.queryForObject(QueryConstants.SELECT_PHRID_BY_ORDER_ID, Integer.class, orderId);
	}

	@Override
	public int updateDeliveryStatusOfOrderTabByPhrId(int phrId) {
		return jdbcTemplate.update(QueryConstants.UPDATE_ORDER_PAYMENT_STATUS_BY_PHRID,
				new java.sql.Timestamp(new java.util.Date().getTime()), 1, phrId);
	}
	
	/*
	 PHR_ID                                    NOT NULL NUMBER(6)
	 PATIENT_ID                                NOT NULL NUMBER(6)
	 DOCTOR_ID                                 NOT NULL NUMBER(6)
	 PHR_UPLOADED_DATE                         NOT NULL TIMESTAMP(3)
	 PHR_TYPE                                  NOT NULL VARCHAR2(20)
	 PHR_UPLOADED_PATH_ORIGINAL                NOT NULL VARCHAR2(150)
	 PHR_UPLOADED_PATH_PDF                     NOT NULL VARCHAR2(150)
	 PHR_DESCRIPTION                           NOT NULL VARCHAR2(155)
	 PATIENT_PRESCRIPTION                               VARCHAR2(150)
	 PAYMENT_STATUS                                     NUMBER(2)
	 IS_DELIVERED                                       NUMBER(2)
	*/
	
	/*
	 DOCTOR_ID                                 NOT NULL NUMBER(6)
	 DOCTOR_TITLE                              NOT NULL VARCHAR2(5)
	 DOCTOR_NAME                               NOT NULL VARCHAR2(40)
	 DOCTOR_SPECIALIZATION                     NOT NULL VARCHAR2(30)
	 DOCTOR_MOBILENO                           NOT NULL VARCHAR2(15)
	 DOCTOR_EMAILID                            NOT NULL VARCHAR2(30)
	 HOSPITAL                                  NOT NULL VARCHAR2(30)
	 COUNTRY                                   NOT NULL VARCHAR2(30)
	 STATE                                     NOT NULL VARCHAR2(30)
	 CITY                                      NOT NULL VARCHAR2(30)
	 DOCTOR_IMAGE_PATH                         NOT NULL VARCHAR2(30)
	*/
	
	@Override
	public List<PatientHealthReportResp> getReportsForAdvSearch(int patientId, String name, String speciality,
			String type, String dstatus, String pstatus) {
		System.out.println("In DAO==>name::"+name+" speciality::"+speciality+" type::"+type+" dstatus::"+dstatus+" pstatus::"+pstatus);
		StringBuffer advSearchQuery=new StringBuffer("select p.PHR_ID,PATIENT_ID,p.DOCTOR_ID,to_char(TO_TIMESTAMP(p.PHR_UPLOADED_DATE),'YYYY-MM-DD HH:MI:SS AM'), p.PHR_TYPE,p.PHR_UPLOADED_PATH_ORIGINAL,p.PHR_UPLOADED_PATH_PDF,p.PHR_DESCRIPTION,d.DOCTOR_NAME,d.DOCTOR_SPECIALIZATION,p.PATIENT_PRESCRIPTION,IS_DELIVERED,payment_status from phr_tab p, doctors_tab d ");
		if((name.equals("") || name.equals(null)) && (speciality.equals("") || speciality.equals(null)) 
				&& (type.equals("-1")) && (dstatus.equals("-1")) && (pstatus.equals("-1"))){
			return getAllPatientReportsById(patientId);
		}else if(!(name.equals("") || name.equals(null)) && !(speciality.equals("") || speciality.equals(null)) 
				&& !(type.equals("-1")) && !(dstatus.equals("-1")) && !(pstatus.equals("-1"))){
			//System.out.println("else-if1");
			advSearchQuery.append("where p.doctor_id=d.doctor_id and d.DOCTOR_NAME like '%"+name+"%' and d.DOCTOR_SPECIALIZATION like '%"+speciality+"%' and p.patient_id=? "
					+ "and p.PHR_TYPE=? and p.IS_DELIVERED=? and p.PAYMENT_STATUS=? and p.PATIENT_PRESCRIPTION is not null order by p.PHR_ID desc");
			if(dstatus.equals("1") && pstatus.equals("1")){
				//System.out.println(new String(advSearchQuery));
				return jdbcTemplate.query(new String(advSearchQuery), new PhrDetailsExtractor(), patientId, type, 0, 0 );
			}else if(dstatus.equals("1") && pstatus.equals("2")){
				return null;
			}else if(dstatus.equals("1") && pstatus.equals("3")){
				return null;
			}else if(dstatus.equals("2") && pstatus.equals("1")){
				return null;
			}else if(dstatus.equals("2") && pstatus.equals("2")){
				return jdbcTemplate.query(new String(advSearchQuery), new PhrDetailsExtractor(), patientId, type, 1, 0 );
			}else if(dstatus.equals("2") && pstatus.equals("3")){
				return null;
			}else if(dstatus.equals("3") && pstatus.equals("1")){
				return null;
			}else if(dstatus.equals("3") && pstatus.equals("2")){
				return null;
			}else {
				return jdbcTemplate.query(new String(advSearchQuery), new PhrDetailsExtractor(), patientId, type, 1, 1 );
			}
			
		}else  if(!(name.equals("") || name.equals(null)) && (speciality.equals("") || speciality.equals(null)) 
				&& (type.equals("-1")) && (dstatus.equals("-1")) && (pstatus.equals("-1"))){
			advSearchQuery.append("where p.doctor_id=d.doctor_id and p.patient_id=? and d.DOCTOR_NAME like '%"+name+"%' order by p.PHR_ID desc");
			return jdbcTemplate.query(new String(advSearchQuery), new PhrDetailsExtractor(), patientId );
		}else if((name.equals("") || name.equals(null)) && !(speciality.equals("") || speciality.equals(null)) 
				&& (type.equals("-1")) && (dstatus.equals("-1")) && (pstatus.equals("-1"))){
			advSearchQuery.append("where p.doctor_id=d.doctor_id and p.patient_id=? and d.DOCTOR_SPECIALIZATION like '%"+speciality+"%' order by p.PHR_ID desc");
			return jdbcTemplate.query(new String(advSearchQuery), new PhrDetailsExtractor(), patientId);
		}else  if((name.equals("") || name.equals(null)) && (speciality.equals("") || speciality.equals(null)) 
				&& !(type.equals("-1")) && (dstatus.equals("-1")) && (pstatus.equals("-1"))){
			advSearchQuery.append("where p.doctor_id=d.doctor_id and p.patient_id=? and p.PHR_TYPE=? order by p.PHR_ID desc");
			return jdbcTemplate.query(new String(advSearchQuery), new PhrDetailsExtractor(), patientId, type );
		}else  if((name.equals("") || name.equals(null)) && (speciality.equals("") || speciality.equals(null)) 
				&& (type.equals("-1")) && !(dstatus.equals("-1")) && (pstatus.equals("-1"))){
			advSearchQuery.append("where p.doctor_id=d.doctor_id and p.patient_id=? and p.IS_DELIVERED=? and p.PAYMENT_STATUS=? and p.PATIENT_PRESCRIPTION is not null order by p.PHR_ID desc");
			
			if(dstatus.equals("1")){
				return jdbcTemplate.query(new String(advSearchQuery), new PhrDetailsExtractor(), patientId, 0, 0 );
			}else if(dstatus.equals("2")){
				return jdbcTemplate.query(new String(advSearchQuery), new PhrDetailsExtractor(), patientId, 1, 0 );
			}else{
				return jdbcTemplate.query(new String(advSearchQuery), new PhrDetailsExtractor(), patientId, 1, 1 );
			}
			
		}else if((name.equals("") || name.equals(null)) && (speciality.equals("") || speciality.equals(null)) 
				&& (type.equals("-1")) && (dstatus.equals("-1")) && !(pstatus.equals("-1"))){
			advSearchQuery.append("where p.doctor_id=d.doctor_id and p.patient_id=? and p.IS_DELIVERED=? and p.PAYMENT_STATUS=? and p.PATIENT_PRESCRIPTION is not null order by p.PHR_ID desc");
			
			if(pstatus.equals("1")){
				return jdbcTemplate.query(new String(advSearchQuery), new PhrDetailsExtractor(), patientId, 0, 0 );
			}else if(pstatus.equals("2")){
				return jdbcTemplate.query(new String(advSearchQuery), new PhrDetailsExtractor(), patientId, 1, 0 );
			}else{
				return jdbcTemplate.query(new String(advSearchQuery), new PhrDetailsExtractor(), patientId, 1, 1 );
			}
		}else if(!(name.equals("") || name.equals(null)) && !(speciality.equals("") || speciality.equals(null)) 
				&& (type.equals("-1")) && (dstatus.equals("-1")) && (pstatus.equals("-1"))){
			advSearchQuery.append("where p.doctor_id=d.doctor_id and p.patient_id=? and d.DOCTOR_NAME like '%"+name+"%' and d.DOCTOR_SPECIALIZATION like '%"+speciality+"%' order by p.PHR_ID desc");
			return jdbcTemplate.query(new String(advSearchQuery), new PhrDetailsExtractor(), patientId );
		}else if(!(name.equals("") || name.equals(null)) && (speciality.equals("") || speciality.equals(null)) 
				&& !(type.equals("-1")) && (dstatus.equals("-1")) && (pstatus.equals("-1"))){
			advSearchQuery.append("where p.doctor_id=d.doctor_id and p.patient_id=? and p.PHR_TYPE=? and d.DOCTOR_NAME like '%"+name+"%' order by p.PHR_ID desc");
			return jdbcTemplate.query(new String(advSearchQuery), new PhrDetailsExtractor(), patientId, type );
		}else if(!(name.equals("") || name.equals(null)) && !(speciality.equals("") || speciality.equals(null)) 
				&& !(type.equals("-1")) && (dstatus.equals("-1")) && (pstatus.equals("-1"))){
			advSearchQuery.append("where p.doctor_id=d.doctor_id and p.patient_id=? and p.PHR_TYPE=? and d.DOCTOR_NAME like '%"+name+"%' and d.DOCTOR_SPECIALIZATION like '%"+speciality+"%' order by p.PHR_ID desc");
			return jdbcTemplate.query(new String(advSearchQuery), new PhrDetailsExtractor(), patientId, type );
		}else {
			return null;
		}
	}	

}
