package com.mod.healthrecords.remoteservice.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mod.healthrecords.constants.PhrmacyServiceURIConstants;

@Service("phrmacyServiceClientimpl")
public class PhrmacyServiceClientimpl implements PhrmacyServiceClient {
	
	@Autowired
	private RestTemplate rt;
	
	@Override
	public String sendDoctorPrescriptionToRequestedPharmacy(String jsonDoctorPrescription) {
		HttpHeaders headers=null;
		HttpEntity<String> entity=null;
		String jsonBookResponse=null;
		
		headers=new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		entity=new HttpEntity<String>(jsonDoctorPrescription, headers);
		jsonBookResponse=rt.postForObject(PhrmacyServiceURIConstants.SEND_DOCTOR_PRESCRIPTION, entity,String.class);
		return jsonBookResponse;
	}

}
