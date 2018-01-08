package com.mod.healthrecords.remoteservice.client;

public interface SMSServiceClient {
	public String sendPrescriptionSMS(String jsonPrescriptionSMSBean);
}
