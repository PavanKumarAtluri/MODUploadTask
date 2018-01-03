package com.mod.healthrecords.beans.dto;

public class Order {
	private int orderid;
	private int pharmacyid;
	private int phrid;
	private int doctorid;
	private int patientid;
	private String prescription;
	private String date_prescription;
	private int is_delivered;
	private String delivered_date;
	
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	public int getPharmacyid() {
		return pharmacyid;
	}
	public void setPharmacyid(int pharmacyid) {
		this.pharmacyid = pharmacyid;
	}
	public int getPhrid() {
		return phrid;
	}
	public void setPhrid(int phrid) {
		this.phrid = phrid;
	}
	public int getDoctorid() {
		return doctorid;
	}
	public void setDoctorid(int doctorid) {
		this.doctorid = doctorid;
	}
	public int getPatientid() {
		return patientid;
	}
	public void setPatientid(int patientid) {
		this.patientid = patientid;
	}
	public String getDate_prescription() {
		return date_prescription;
	}
	public void setDate_prescription(String date_prescription) {
		this.date_prescription = date_prescription;
	}
	public String getPrescription() {
		return prescription;
	}
	public void setPrescription(String prescription) {
		this.prescription = prescription;
	}
	public int getIs_delivered() {
		return is_delivered;
	}
	public void setIs_delivered(int is_delivered) {
		this.is_delivered = is_delivered;
	}
	public String getDelivered_date() {
		return delivered_date;
	}
	public void setDelivered_date(String delivered_date) {
		this.delivered_date = delivered_date;
	}
	
}
