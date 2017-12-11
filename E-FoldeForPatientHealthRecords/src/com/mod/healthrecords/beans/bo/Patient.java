package com.mod.healthrecords.beans.bo;

public class Patient {
	private int patient_id;
	private String patient_title;
	private String patient_name;
	private int patient_age;
	private String patient_sex;
	private String patient_mobileno;
	private String patient_email_id;
	private String patient_image_path;
	private String patient_identity_path;

	public int getPatient_id() {
		return patient_id;
	}

	public void setPatient_id(int patient_id) {
		this.patient_id = patient_id;
	}

	public String getPatient_title() {
		return patient_title;
	}

	public void setPatient_title(String patient_title) {
		this.patient_title = patient_title;
	}

	public String getPatient_name() {
		return patient_name;
	}

	public void setPatient_name(String patient_name) {
		this.patient_name = patient_name;
	}

	public int getPatient_age() {
		return patient_age;
	}

	public void setPatient_age(int patient_age) {
		this.patient_age = patient_age;
	}

	public String getPatient_sex() {
		return patient_sex;
	}

	public void setPatient_sex(String patient_sex) {
		this.patient_sex = patient_sex;
	}

	public String getPatient_mobileno() {
		return patient_mobileno;
	}

	public void setPatient_mobileno(String patient_mobileno) {
		this.patient_mobileno = patient_mobileno;
	}

	public String getPatient_email_id() {
		return patient_email_id;
	}

	public void setPatient_email_id(String patient_email_id) {
		this.patient_email_id = patient_email_id;
	}

	public String getPatient_image_path() {
		return patient_image_path;
	}

	public void setPatient_image_path(String patient_image_path) {
		this.patient_image_path = patient_image_path;
	}

	public String getPatient_identity_path() {
		return patient_identity_path;
	}

	public void setPatient_identity_path(String patient_identity_path) {
		this.patient_identity_path = patient_identity_path;
	}

	@Override
	public String toString() {
		return "Patient [patient_id=" + patient_id + ", patient_title=" + patient_title + ", patient_name="
				+ patient_name + ", patient_age=" + patient_age + ", patient_sex=" + patient_sex + ", patient_mobileno="
				+ patient_mobileno + ", patient_email_id=" + patient_email_id + ", patient_image_path="
				+ patient_image_path + ", patient_identity_path=" + patient_identity_path + "]";
	}

}
