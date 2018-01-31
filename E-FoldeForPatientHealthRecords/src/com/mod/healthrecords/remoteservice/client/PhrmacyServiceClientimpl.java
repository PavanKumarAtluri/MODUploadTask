package com.mod.healthrecords.remoteservice.client;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mod.healthrecords.constants.PhrmacyServiceURIConstants;

@Service("phrmacyServiceClientimpl")
public class PhrmacyServiceClientimpl implements PhrmacyServiceClient {

	@Autowired
	private RestTemplate template;

	@Override
	public String sendDoctorPrescriptionToRequestedPharmacy(String jsonDoctorPrescription) {
		HttpHeaders headers = null;
		HttpEntity<String> entity = null;
		String jsonResponse = null;

		headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		entity = new HttpEntity<String>(jsonDoctorPrescription, headers);
		jsonResponse = template.postForObject(PhrmacyServiceURIConstants.SEND_DOCTOR_PRESCRIPTION_URL, entity,
				String.class);
		return jsonResponse;
	}

	@Override
	public String getOrderDetailsByOrderId(int pharmacyId,int orderId) {
		/*Map<String, Object> map = null;
		String jsondResp = null;

		map = new HashMap<>();
		map.put("orderId", orderId);
		jsondResp = template.getForObject(PhrmacyServiceURIConstants.GET_ORDER_BY_ORDERID_URL, String.class, map);
		return jsondResp;*/
		
		HttpEntity<String> entity=null;

		
		
		 HttpHeaders headers = new HttpHeaders();
		 headers.add("Content-Type", "application/json");
		entity=new HttpEntity<String>(headers);
		ResponseEntity<String> resp=template.exchange(PhrmacyServiceURIConstants.GET_ORDER_BY_ORDERID_URL, HttpMethod.GET, entity, String.class,pharmacyId,orderId);
		System.out.println(resp.getBody());
		return resp.getBody();
	}

	@Override
	public String getOrderDetailsByPatientIdAndPharmacyId(int pharmacyId, int patientId) {
		/*Map<String, Object> map = null;
		String jsondResp = null;

		map = new HashMap<>();
		map.put("pharmacyId", pharmacyId);
		map.put("patientId", patientId);
		jsondResp = template.getForObject(PhrmacyServiceURIConstants.GET_ORDERS_OF_PARTICULAR_PATIENT_URL, String.class,
				map);
		return jsondResp;*/
		
		//Map<String, Object> map = null;
		//String jsondResp = null;
		HttpEntity<String> entity=null;

		
		
		 HttpHeaders headers = new HttpHeaders();
		 headers.add("Content-Type", "application/json");
		entity=new HttpEntity<String>(headers);
		ResponseEntity<String> resp=template.exchange(PhrmacyServiceURIConstants.GET_ORDERS_OF_PARTICULAR_PATIENT_URL, HttpMethod.GET, entity, String.class, pharmacyId,patientId);
		System.out.println(resp.getBody());
		return resp.getBody();
	}

	@Override
	public String getAllOrdersByPharmacyId(int pharmacyId) {
		//String jsondResp = null;
		HttpEntity<String> entity=null;

		//jsondResp = template.getForObject(PhrmacyServiceURIConstants.GET_ALL_ORDERS_OF_PHARMACY_URL, String.class,
				//pharmacyId);
		 HttpHeaders headers = new HttpHeaders();
		 headers.add("Content-Type", "application/json");
		entity=new HttpEntity<String>(headers);
		ResponseEntity<String> resp=template.exchange(PhrmacyServiceURIConstants.GET_ALL_ORDERS_OF_PHARMACY_URL, HttpMethod.GET, entity, String.class, pharmacyId);
		System.out.println(resp.getBody());
		return resp.getBody();
	}

	@Override
	public String changeDeliveryStatus(int orderId) {
		//String jsondResp = null;
		HttpEntity<String> entity=null;

		//jsondResp = template.getForObject(PhrmacyServiceURIConstants.GET_ALL_ORDERS_OF_PHARMACY_URL, String.class,orderId);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		entity=new HttpEntity<String>(headers);
		ResponseEntity<String> resp=template.exchange(PhrmacyServiceURIConstants.CHANGE_DELIVERY_STATUS, HttpMethod.GET, entity, String.class, orderId);
		System.out.println(resp.getBody());
		return resp.getBody();
	}

	@Override
	public String changePaymentStatus(int orderId) {
		//String jsondResp = null;
		HttpEntity<String> entity=null;

		//jsondResp = template.getForObject(PhrmacyServiceURIConstants.GET_ALL_ORDERS_OF_PHARMACY_URL, String.class,orderId);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		entity=new HttpEntity<String>(headers);
		ResponseEntity<String> resp=template.exchange(PhrmacyServiceURIConstants.CHANGE_PAYMENT_STATUS, HttpMethod.GET, entity, String.class, orderId);
		System.out.println(resp.getBody());
		return resp.getBody();
	}
	
	@Override
	public String getOrderStatusByphrId(int phrId) {
		/*Map<String, Object> map = null;
		String jsondResp = null;

		map = new HashMap<>();
		map.put("orderId", orderId);
		jsondResp = template.getForObject(PhrmacyServiceURIConstants.GET_ORDER_BY_ORDERID_URL, String.class, map);
		return jsondResp;*/
		
		HttpEntity<String> entity=null;

		
		
		 HttpHeaders headers = new HttpHeaders();
		 headers.add("Content-Type", "application/json");
		entity=new HttpEntity<String>(headers);
		ResponseEntity<String> resp=template.exchange(PhrmacyServiceURIConstants.GET_ORDERSTATUS_BYPHRID, HttpMethod.GET, entity, String.class, phrId);
		System.out.println(resp.getBody());
		return resp.getBody();
	}
}
