package com.mod.healthrecords.beans.dto;

import org.springframework.web.multipart.MultipartFile;

public class PatientHealthReportDTO {
	private Integer doctor_id;
	private String phr_type;
	private MultipartFile phr_uploaded_file;
	private String phr_description;
	
	public Integer getDoctor_id() {
		return doctor_id;
	}
	public void setDoctor_id(Integer doctor_id) {
		this.doctor_id = doctor_id;
	}
	public String getPhr_type() {
		return phr_type;
	}
	public void setPhr_type(String phr_type) {
		this.phr_type = phr_type;
	}
	public MultipartFile getPhr_uploaded_file() {
		return phr_uploaded_file;
	}
	public void setPhr_uploaded_file(MultipartFile phr_uploaded_file) {
		this.phr_uploaded_file = phr_uploaded_file;
	}
	public String getPhr_description() {
		return phr_description;
	}
	public void setPhr_description(String phr_description) {
		this.phr_description = phr_description;
	}
	@Override
	public String toString() {
		return "PatientHealthReportCommand [doctor_id=" + doctor_id + ", phr_type=" + phr_type + ", phr_uploaded_file="
				+ phr_uploaded_file + ", phr_description=" + phr_description + "]";
	}

}
