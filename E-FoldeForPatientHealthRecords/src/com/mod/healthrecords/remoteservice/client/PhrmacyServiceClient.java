package com.mod.healthrecords.remoteservice.client;

public interface PhrmacyServiceClient {
	public String sendDoctorPrescriptionToRequestedPharmacy(String jsonDoctorPrescription);
}
