package com.mod.healthrecords.remoteservice.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mod.healthrecords.constants.PhrmacyServiceURIConstants;

@Service("smsServiceClientImpl")
public class SMSServiceClientImpl implements SMSServiceClient {
	
	@Autowired
	private RestTemplate template;

	@Override
	public String sendPrescriptionSMS(String jsonPrescriptionSMSBean) {
		HttpHeaders headers = null;
		HttpEntity<String> entity = null;
		String jsonSMSResponse = null;

		headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		System.out.println("SMSServiceClientImpl.sendPrescriptionSMS().jsonPrescriptionSMSBean::"+jsonPrescriptionSMSBean);
		entity = new HttpEntity<String>(jsonPrescriptionSMSBean, headers);
		jsonSMSResponse = template.postForObject(PhrmacyServiceURIConstants.SEND_SMS_PRESCRIPTION_URL, entity,
				String.class);
		System.out.println("SMSServiceClientImpl.sendPrescriptionSMS().jsonSMSResponse:: "+jsonSMSResponse);
		return jsonSMSResponse;
	}

}
