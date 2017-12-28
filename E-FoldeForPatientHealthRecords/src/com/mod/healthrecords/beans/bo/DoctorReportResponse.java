package com.mod.healthrecords.beans.bo;

import java.util.Date;

public class DoctorReportResponse {
	
	private int phr_id;
	private int doctor_id;
	private int patient_id;
	private String patient_name;
	private int patient_age;
	private String patient_sex;
	private String phr_type;
	private String phr_uploaded_date;
	private String phr_uploaded_path_original;
	private String phr_uploaded_path_pdf;
	private String phr_description;
	
	public int getPhr_id() {
		return phr_id;
	}
	public void setPhr_id(int phr_id) {
		this.phr_id = phr_id;
	}
	public int getDoctor_id() {
		return doctor_id;
	}
	public void setDoctor_id(int doctor_id) {
		this.doctor_id = doctor_id;
	}
	public int getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(int patient_id) {
		this.patient_id = patient_id;
	}
	public String getPatient_name() {
		return patient_name;
	}
	public void setPatient_name(String patient_name) {
		this.patient_name = patient_name;
	}
	public String getPhr_type() {
		return phr_type;
	}
	public void setPhr_type(String phr_type) {
		this.phr_type = phr_type;
	}
	public String getPhr_uploaded_date() {
		return phr_uploaded_date;
	}
	public void setPhr_uploaded_date(String date) {
		this.phr_uploaded_date = date;
	}
	public String getPhr_uploaded_path_original() {
		return phr_uploaded_path_original;
	}
	public void setPhr_uploaded_path_original(String phr_uploaded_path_original) {
		this.phr_uploaded_path_original = phr_uploaded_path_original;
	}
	public String getPhr_uploaded_path_pdf() {
		return phr_uploaded_path_pdf;
	}
	public void setPhr_uploaded_path_pdf(String phr_uploaded_path_pdf) {
		this.phr_uploaded_path_pdf = phr_uploaded_path_pdf;
	}
	public String getPhr_description() {
		return phr_description;
	}
	public void setPhr_description(String phr_description) {
		this.phr_description = phr_description;
	}
	public int getPatient_age() {
		return patient_age;
	}
	public void setPatient_age(int i) {
		this.patient_age = i;
	}
	public String getPatient_sex() {
		return patient_sex;
	}
	public void setPatient_sex(String patient_sex) {
		this.patient_sex = patient_sex;
	}
	
}
