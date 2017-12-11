package com.mod.healthrecords.constants;

public class QueryConstants {
	public static final String GET_PHR_ID_QUERY="select PHR_ID_SEQ.nextval from dual";
	public static final String GET_PATIENT_DETAILS_QUERY="select * from patient_tab where patient_id=?";
	public static final String GET_DOCTOR_DETAILS_QUERY="select * from doctors_tb where doctor_id=?";
	public static final String INSERT_PATIENT_HEALTH_RECORD_DETAILS_QUERY="insert into phr_tab values(?,?,?,?,?,?,?,?)";
	public static final String GET_PHR_DETAILS="select p.PHR_ID,PATIENT_ID,p.DOCTOR_ID,to_char(p.PHR_UPLOADED_DATE,'YYYY-MM-DD HH:mm:SS'), p.PHR_TYPE,p.PHR_UPLOADED_PATH_ORIGINAL,p.PHR_UPLOADED_PATH_PDF,p.PHR_DESCRIPTION,d.DOCTOR_NAME,d.DOCTOR_SPECIALIZATION from phr_tab p, doctors_tab d where p.doctor_id=d.doctor_id and p.patient_id=? and p.phr_type=?";
	public static final String GET_DOCTOR_REPORT="select ph.doctor_id, p.patient_id, p.patient_name, p.patient_age, p.patient_sex, ph.phr_uploaded_date, ph.phr_type, ph.phr_uploaded_path_original, ph.phr_uploaded_path_pdf, ph.phr_description from phr_tab ph inner join patient_tab p on ph.patient_id = p.patient_id where ph.doctor_id=? order by ph.phr_uploaded_date desc";
	public static final String GET_DOCTORS_DETAILS="select DOCTOR_ID,DOCTOR_NAME,DOCTOR_SPECIALIZATION from doctors_tab";
}
