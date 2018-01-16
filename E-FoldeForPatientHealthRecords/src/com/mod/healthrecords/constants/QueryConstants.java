package com.mod.healthrecords.constants;

public class QueryConstants {
	public static final String GET_PHR_ID_QUERY="select PHR_ID_SEQ.nextval from dual";
	public static final String GET_PATIENT_DETAILS_QUERY="select * from patient_tab where patient_id=?";
	public static final String GET_DOCTOR_DETAILS_QUERY="select * from doctors_tab where doctor_id=?";
	public static final String INSERT_PATIENT_HEALTH_RECORD_DETAILS_QUERY="insert into phr_tab(PHR_ID,PATIENT_ID,DOCTOR_ID,PHR_UPLOADED_DATE,PHR_TYPE,PHR_UPLOADED_PATH_ORIGINAL,PHR_UPLOADED_PATH_PDF,PHR_DESCRIPTION,IS_DELIVERED,payment_status) values(?,?,?,?,?,?,?,?,?,?)";
	//public static final String GET_PHR_DETAILS="select p.PHR_ID,PATIENT_ID,p.DOCTOR_ID,to_char(p.PHR_UPLOADED_DATE,'YYYY-MM-DD HH:mm:SS'), p.PHR_TYPE,p.PHR_UPLOADED_PATH_ORIGINAL,p.PHR_UPLOADED_PATH_PDF,p.PHR_DESCRIPTION,d.DOCTOR_NAME,d.DOCTOR_SPECIALIZATION from phr_tab p, doctors_tab d where p.doctor_id=d.doctor_id and p.patient_id=? and p.phr_type=?";
	//public static final String GET_DOCTOR_REPORT="select ph.doctor_id, p.patient_id, p.patient_name, p.patient_age, p.patient_sex, ph.phr_uploaded_date, ph.phr_type, ph.phr_uploaded_path_original, ph.phr_uploaded_path_pdf, ph.phr_description from phr_tab ph inner join patient_tab p on ph.patient_id = p.patient_id where ph.doctor_id=? order by ph.phr_uploaded_date desc";
	
	public static final String GET_PHR_DETAILS_BY_PATIENT_ID_AND_PHR_TYPE="select p.PHR_ID,PATIENT_ID,p.DOCTOR_ID,to_char(TO_TIMESTAMP(p.PHR_UPLOADED_DATE),'YYYY-MM-DD HH:MI:SS AM'), p.PHR_TYPE,p.PHR_UPLOADED_PATH_ORIGINAL,p.PHR_UPLOADED_PATH_PDF,p.PHR_DESCRIPTION,d.DOCTOR_NAME,d.DOCTOR_SPECIALIZATION,p.PATIENT_PRESCRIPTION,IS_DELIVERED,payment_status from phr_tab p, doctors_tab d where p.doctor_id=d.doctor_id and p.patient_id=? and p.phr_type=? order by p.PHR_ID desc";
	public static final String GET_DOCTOR_REPORT_BY_DOCTOR_ID="select ph.PHR_ID,ph.doctor_id, p.patient_id, p.patient_name, p.patient_age, p.patient_sex, to_char(TO_TIMESTAMP(ph.PHR_UPLOADED_DATE),'YYYY-MM-DD HH:MI:SS AM'), ph.phr_type, ph.phr_uploaded_path_original, ph.phr_uploaded_path_pdf, ph.phr_description from phr_tab ph inner join patient_tab p on ph.patient_id = p.patient_id where ph.doctor_id=? order by ph.PHR_ID desc";
	public static final String GET_DOCTORS_DETAILS="select DOCTOR_ID,DOCTOR_NAME,DOCTOR_SPECIALIZATION from doctors_tab";
	public static final String GET_ALL_REPORTS_BY_PATIENT_ID="select p.PHR_ID,PATIENT_ID,p.DOCTOR_ID,to_char(TO_TIMESTAMP(p.PHR_UPLOADED_DATE),'YYYY-MM-DD HH:MI:SS AM'), p.PHR_TYPE,p.PHR_UPLOADED_PATH_ORIGINAL,p.PHR_UPLOADED_PATH_PDF,p.PHR_DESCRIPTION,d.DOCTOR_NAME,d.DOCTOR_SPECIALIZATION,p.PATIENT_PRESCRIPTION,IS_DELIVERED,payment_status from phr_tab p, doctors_tab d where p.doctor_id=d.doctor_id and p.patient_id=? order by p.PHR_ID desc";
	public static final String GET_REPORTS_BY_PATIENT_NAME_AND_DOCTOR_ID="select ph.PHR_ID, ph.doctor_id, p.patient_id, p.patient_name, p.patient_age, p.patient_sex, to_char(TO_TIMESTAMP(ph.PHR_UPLOADED_DATE),'YYYY-MM-DD HH:MI:SS AM'), ph.phr_type, ph.phr_uploaded_path_original, ph.phr_uploaded_path_pdf, ph.phr_description from phr_tab ph inner join patient_tab p on ph.patient_id = p.patient_id where ph.doctor_id=? and p.patient_name=? order by ph.PHR_ID desc";
	public static final String GET_PHARMACYID_BY_PATIENTID="select PHARMACYID from patient_tab where patient_id=?";
	public static final String UPDATE_PRESCRIPTION_DETAILS_BY_PHR_ID="update phr_tab set PATIENT_PRESCRIPTION=? where PHR_ID=?";
	public static final String UPDATE_DELIVERY_STATUS_BY_PHR_ID="update phr_tab set IS_DELIVERED=? where PHR_ID=?";
	public static final String UPDATE_PAYMENT_STATUS_BY_PHR_ID="update phr_tab set  PAYMENT_STATUS=? where PHR_ID=?";
	public static final String SELECT_PHRID_BY_ORDER_ID="SELECT PHRID FROM pharmacy_orders_tab WHERE ORDERID=?";
	public static final String UPDATE_ORDER_PAYMENT_STATUS_BY_PHRID="update pharmacy_orders_tab set DELIVERED_DATE=?,PAYMENT_STATUS=? where PHRID=?";
}
