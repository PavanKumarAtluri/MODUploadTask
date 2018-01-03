package com.mod.healthrecords.constants;

public class PhrmacyServiceURIConstants {
	public static final String SEND_DOCTOR_PRESCRIPTION_URL="http://localhost:8085/ParmacyService/phrmacyProvider/submitDoctorPrescription";
	public static final String GET_ORDERS_OF_PARTICULAR_PATIENT_URL="http://localhost:8085/ParmacyService/phrmacyProvider/getOrderOfParticularPatient?pharmacyId={pharmacyId}&patientId={patientId}";
	public static final String GET_ORDER_BY_ORDERID_URL="http://localhost:8085/ParmacyService/phrmacyProvider/getOrderByOrderId/{orderId}";
	public static final String GET_ALL_ORDERS_OF_PHARMACY_URL="http://localhost:8085/ParmacyService/phrmacyProvider/getAllOrdersByPharmacyId/{pharmacyId}";
	public static final String CHANGE_DELIVERY_STATUS="http://localhost:8085/ParmacyService/phrmacyProvider/changeDeliveryStatusByDeliveryId/{deliveryId}";
}
