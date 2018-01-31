package com.mod.healthrecords.remoteservice.client;

public interface PhrmacyServiceClient {
	public String sendDoctorPrescriptionToRequestedPharmacy(String jsonDoctorPrescription);
	public String getOrderDetailsByOrderId(int pharmacyId,int orderId);
	public String getOrderDetailsByPatientIdAndPharmacyId(int pharmacyId,int patientId);
	public String getAllOrdersByPharmacyId(int pharmacyId);
	public String changeDeliveryStatus(int orderId);
	public String changePaymentStatus(int orderId);
	public String getOrderStatusByphrId(int phrId);
}
