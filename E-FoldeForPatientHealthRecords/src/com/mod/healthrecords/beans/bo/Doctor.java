package com.mod.healthrecords.beans.bo;

public class Doctor {
	private int doctor_id;
	private String doctor_title;
	private String doctor_name;
	private String doctor_specialization;
	private String doctor_mobileno;
	private String doctor_emailid;
	private String hospital;
	private String country;
	private String state;
	private String city;
	private String doctor_image_path;

	public int getDoctor_id() {
		return doctor_id;
	}

	public void setDoctor_id(int doctor_id) {
		this.doctor_id = doctor_id;
	}

	public String getDoctor_title() {
		return doctor_title;
	}

	public void setDoctor_title(String doctor_title) {
		this.doctor_title = doctor_title;
	}

	public String getDoctor_name() {
		return doctor_name;
	}

	public void setDoctor_name(String doctor_name) {
		this.doctor_name = doctor_name;
	}

	public String getDoctor_specialization() {
		return doctor_specialization;
	}

	public void setDoctor_specialization(String doctor_specialization) {
		this.doctor_specialization = doctor_specialization;
	}

	public String getDoctor_mobileno() {
		return doctor_mobileno;
	}

	public void setDoctor_mobileno(String doctor_mobileno) {
		this.doctor_mobileno = doctor_mobileno;
	}

	public String getDoctor_emailid() {
		return doctor_emailid;
	}

	public void setDoctor_emailid(String doctor_emailid) {
		this.doctor_emailid = doctor_emailid;
	}

	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public String getDoctor_image_path() {
		return doctor_image_path;
	}

	public void setDoctor_image_path(String doctor_image_path) {
		this.doctor_image_path = doctor_image_path;
	}

	@Override
	public String toString() {
		return "Doctor [doctor_id=" + doctor_id + ", doctor_title=" + doctor_title + ", doctor_name=" + doctor_name
				+ ", doctor_specialization=" + doctor_specialization + ", doctor_mobileno=" + doctor_mobileno
				+ ", doctor_emailid=" + doctor_emailid + ", hospital=" + hospital + ", country=" + country + ", state="
				+ state + ", city=" + city + "]";
	}
}
