package com.mod.healthrecords.remoteservice.client;

public interface PhrmacyServiceClient {
	public String sendDoctorPrescriptionToRequestedPharmacy(String jsonDoctorPrescription);
	public String getOrderDetailsByOrderId(int orderId);
	public String getOrderDetailsByPatientIdAndPharmacyId(int pharmacyId,int patientId);
	public String getAllOrdersByPharmacyId(int pharmacyId);
	public String changeDeliveryStatus(int orderId);
}
