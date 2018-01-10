package com.mod.healthrecords.beans.dto;

public class OrderStatus {
	public String prescription;
	public String prescribedDate;
	public int deliveryStatus;
	public String deliveredDate;
	public int paymentStatus;
	
	public String getPrescription() {
		return prescription;
	}
	public void setPrescription(String prescription) {
		this.prescription = prescription;
	}
	public String getPrescribedDate() {
		return prescribedDate;
	}
	public void setPrescribedDate(String prescribedDate) {
		this.prescribedDate = prescribedDate;
	}
	public int getDeliveryStatus() {
		return deliveryStatus;
	}
	public void setDeliveryStatus(int deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}
	public String getDeliveredDate() {
		return deliveredDate;
	}
	public void setDeliveredDate(String deliveredDate) {
		this.deliveredDate = deliveredDate;
	}
	public int getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(int paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	
	
	

}
