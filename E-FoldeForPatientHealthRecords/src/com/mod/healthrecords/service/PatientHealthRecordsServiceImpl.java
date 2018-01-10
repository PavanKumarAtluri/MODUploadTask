package com.mod.healthrecords.service;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.DocumentException;
import com.mod.healthrecords.beans.bo.Doctor;
import com.mod.healthrecords.beans.bo.DoctorReportResponse;
import com.mod.healthrecords.beans.bo.Patient;
import com.mod.healthrecords.beans.bo.PatientHealthReport;
import com.mod.healthrecords.beans.bo.PatientHealthReportResp;
import com.mod.healthrecords.beans.dto.DoctorPrescription;
import com.mod.healthrecords.beans.dto.Order;
import com.mod.healthrecords.beans.dto.OrderStatus;
import com.mod.healthrecords.beans.dto.PatientHealthReportDTO;
import com.mod.healthrecords.beans.dto.PatientHealthReportRespWithOrderSatusDetails;
import com.mod.healthrecords.beans.dto.PrescriptionSMSBean;
import com.mod.healthrecords.beans.dto.Resp;
import com.mod.healthrecords.beans.dto.Response;
import com.mod.healthrecords.beans.dto.SMSResp;
import com.mod.healthrecords.constants.FileConstants;
import com.mod.healthrecords.dao.PatientHealthRecordsDAOI;
import com.mod.healthrecords.exceptions.PHRException;
import com.mod.healthrecords.remoteservice.client.PhrmacyServiceClient;
import com.mod.healthrecords.remoteservice.client.SMSServiceClient;
import com.mod.healthrecords.utils.ImageToPdfUtiil;
import com.mod.healthrecords.utils.JsonUtil;

import oracle.net.aso.p;

@Service("patientHealthRecordsServiceImpl")
public class PatientHealthRecordsServiceImpl implements PatientHealthRecordsServiceI {

	@Autowired
	private PatientHealthRecordsDAOI patientHealthRecordsDAO;
	
	@Autowired
	private PhrmacyServiceClient phrmacyServiceClient;
	
	@Autowired
	private SMSServiceClient smsServiceClient;
	
	@Override
	public Patient getPatientDetails(int pid) {

		return patientHealthRecordsDAO.getPatientDetails(pid);
	}

	@Override
	public Doctor getDoctorDetails(int did) {
		return patientHealthRecordsDAO.getDoctorDetails(did);
	}

	@Override
	public int insertPatientRecord(PatientHealthReportDTO patientHealthReport, int id) throws PHRException {
		PatientHealthReport phr = new PatientHealthReport();
		boolean flag = false;
		String pdf_path = null;
		
		String file_loc1 = FileConstants.ORIGINAL_FILE__DEST_LOC + "/"
				+ String.valueOf(getPatientDetails(id).getPatient_id()) + "_"
				+ new SimpleDateFormat("yyyy_MM_dd HH_mm_SS").format(new Date()) + "_"
				+ patientHealthReport.getPhr_uploaded_file().getOriginalFilename();
		// System.out.println(file_loc1);

		String file_loc2 = FileConstants.PDF_FILE_DEST_LOC;
		// System.out.println(file_loc2);

		File file1 = new File(file_loc1);
		if (!file1.exists()) {
			file1.mkdirs();
		}

		File file2 = new File(file_loc2);
		if (!file2.exists()) {
			file2.mkdirs();
		}

		try {
			patientHealthReport.getPhr_uploaded_file().transferTo(file1);
			file_loc2 = file_loc2 + "/"
					+ String.valueOf(getPatientDetails(id).getPatient_id()) + "_"
					+ new SimpleDateFormat("yyyy_MM_dd HH_mm_SS").format(new Date()) + "_"
					+ patientHealthReport.getPhr_uploaded_file().getOriginalFilename();
			String ext = FilenameUtils.getExtension(file_loc2);
			if (!("pdf".equalsIgnoreCase(ext)))
				file_loc2 = file_loc2.replaceAll(file_loc2.substring(file_loc2.lastIndexOf('.'), file_loc2.length()),
						".pdf");

			try {
				System.out.println("Inside Service pdf_path::" + file_loc2);
				flag = ImageToPdfUtiil.convertAndSaveImageAsPdf(file_loc1.toString(), file_loc2, patientHealthReport);

			} catch (DocumentException e) {
				if (flag == false)
					file1.delete();
				e.printStackTrace();
			}
		} catch (IllegalStateException | IOException e) {
			file1.delete();
			e.printStackTrace();
			throw new PHRException("Getting Problem While Uploading Doc, Please Verify Details You Submitted");

		}

		phr.setDoctor_id(patientHealthReport.getDoctor_id());
		phr.setPatient_id(id);
		int a = patientHealthRecordsDAO.getPHRId();
		// System.out.println(a);
		phr.setPhr_id(a);
		phr.setPhr_type(patientHealthReport.getPhr_type());
		phr.setPhr_uploaded_date(new Date());
		phr.setPhr_uploaded_path_original(file_loc1);
		phr.setPhr_uploaded_path_pdf(file_loc2);
		phr.setPhr_description(patientHealthReport.getPhr_description());

		int count = patientHealthRecordsDAO.insertPatientRecord(phr);

		return count;
	}

	@Override
	public List<PatientHealthReportResp> getPHRInfo(int pid, String reportType) {

		return patientHealthRecordsDAO.getPHRInfo(pid, reportType);
	}

	@Override
	public List<DoctorReportResponse> getDoctorReport(Integer doctor_id) {

		return patientHealthRecordsDAO.getDoctorReport(doctor_id);
	}

	@Override
	public List<Doctor> getDoctorDetails() {
		return patientHealthRecordsDAO.getDoctorDetails();
	}
	
	@Override
	public List<PatientHealthReportResp> getAllPatientReportsById(int pid) {
		return patientHealthRecordsDAO.getAllPatientReportsById(pid);
	}
	
	
	/*
	@Override
	public List<PatientHealthReportRespWithOrderSatusDetails> getAllPatientReportsById(int pid) {
		List<PatientHealthReportResp> list=patientHealthRecordsDAO.getAllPatientReportsById(pid);
		PatientHealthReportRespWithOrderSatusDetails phros=null; 
		
		
		List<PatientHealthReportRespWithOrderSatusDetails> listwithOrderStatus=new ArrayList<>();
		for(PatientHealthReportResp resp:list){
			phros=new PatientHealthReportRespWithOrderSatusDetails();
			
			if(resp.getPatient_prescription()!=null){
				String jsonOrderStatus=phrmacyServiceClient.getOrderStatusByphrId(resp.getPhr_id());
				OrderStatus orderStatus=JsonUtil.jsonToJava(jsonOrderStatus, OrderStatus.class);
				phros.setPatient_prescription(orderStatus.getPrescription());
				phros.setPrescribedDate(orderStatus.getPrescribedDate());
				phros.setDeliveredDate(orderStatus.getDeliveredDate());
				phros.setDeliveryStatus(orderStatus.getDeliveryStatus());
				phros.setPaymentStatus(orderStatus.getPaymentStatus());
			}
			
			private int phr_id;
			private int patient_id;
			private int doctor_id;
			private String phr_uploaded_date;
			private String phr_type;
			private String phr_uploaded_path_original;
			private String phr_uploaded_path_pdf;
			private String phr_description;
			private String doctor_name;
			private String doctor_specialization;
			 
			phros.setPhr_id(resp.getPhr_id());
			phros.setPatient_id(resp.getPatient_id());
			phros.setDoctor_id(resp.getDoctor_id());
			phros.setPhr_uploaded_date(resp.getPhr_uploaded_date());
			phros.setPhr_type(resp.getPhr_type());
			phros.setPhr_uploaded_path_original(resp.getPhr_uploaded_path_original());
			phros.setPhr_uploaded_path_pdf(resp.getPhr_uploaded_path_pdf());
			phros.setPhr_description(resp.getPhr_description());
			phros.setDoctor_name(resp.getDoctor_name());
			phros.setDoctor_specialization(resp.getDoctor_specialization());
			
			listwithOrderStatus.add(phros);
			
		}
		return listwithOrderStatus;
	}
	 */
	@Override
	public ArrayList<String> getAllPatients(String str) {
		ArrayList<String> list = new ArrayList<String>();
		PreparedStatement ps = null;
		Connection con = null;
		String data;
		try {
			String output = str.toLowerCase();
			String ch = "%"+ output + "%";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "Ad3!nAd3!n");
			ps = con.prepareStatement("SELECT PATIENT_NAME FROM PATIENT_TAB WHERE PATIENT_NAME  LIKE '" + ch + "'");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				data = rs.getString(1);
				System.out.println(data);
				list.add(data);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<DoctorReportResponse> getRecordsByPatientname(String name,int did) {
		
		return patientHealthRecordsDAO.getAllPatientReportsByName(name, did);
	}

	@Override
	public Response sendDoctorPrescriptionToRequestedPharmacy(DoctorPrescription doctorPrescription) {
		String jsonDoctorPrescription = null;
		String jsonResponse = null;
		Response resp = null;
		
		// convert BookDTO to json
		jsonDoctorPrescription = JsonUtil.javaToJson(doctorPrescription);

		// call client method to register book details
		jsonResponse = phrmacyServiceClient.sendDoctorPrescriptionToRequestedPharmacy(jsonDoctorPrescription);

		// convert json to java
		resp = new Response();
		resp = JsonUtil.jsonToJava(jsonResponse, Response.class);
		
		if(resp.getStatus()==(byte)1){
			try{
				patientHealthRecordsDAO.updatePrescriptionDetailsByPhrId(doctorPrescription.getMedicalPrescrition(),doctorPrescription.getPhrId());
				Patient p=patientHealthRecordsDAO.getPatientDetails(doctorPrescription.getPatId());
				Doctor d=patientHealthRecordsDAO.getDoctorDetails(doctorPrescription.getDocId());
				
				PrescriptionSMSBean smsBean=new PrescriptionSMSBean();
				smsBean.setFrom("MODMSG");
				smsBean.setTo(p.getPatient_mobileno());
				smsBean.setTemplateName("MOD_MESSAGE_TEMPLATE1");
				smsBean.setVAR1(p.getPatient_name());
				smsBean.setVAR2(p.getPatient_pharmacy_name());
				smsBean.setVAR3(d.getDoctor_name());
				smsBean.setVAR4("http://dev.magicurehealthcare.com:8091/E-FoldeForPatientHealthRecords_10thJan18_19s/phr/patient_login.htm");
				
				String jsonPrescriptionSMSBean=JsonUtil.javaToJson(smsBean);
				System.out.println(jsonPrescriptionSMSBean);
				
				try{
					String jsonSMSResponse=smsServiceClient.sendPrescriptionSMS(jsonPrescriptionSMSBean);
					
					SMSResp smsResp=JsonUtil.jsonToJava(jsonSMSResponse, SMSResp.class);
					
					System.out.println("ststus::"+smsResp.getStatus()+" "+" Details::"+smsResp.getDetails());
					
					if(smsResp.getStatus().equals("Success"))
						System.out.println("SMS Sended");
					else
						System.out.println("SMS Not Sended");
				}catch(Exception e){
					System.out.println("getting error while sending message");
					e.printStackTrace();
				}
				
			}catch(Exception e){
				System.out.println("getting error while setting props to sending message");
				e.printStackTrace();
			}
			
		}
		
		return resp;
	}

	@Override
	public int getpharmacyIdByPatientId(int patientId) {
		
		return patientHealthRecordsDAO.selectPharmacyIdByPatientId(patientId);
	}

	@Override
	public Order getOrderDetailsByOrderId(int orderId) {
		String jsonResp = null;
		Resp resp = null;
		Order order=new Order();

		// call client method to delete the book
		jsonResp = phrmacyServiceClient.getOrderDetailsByOrderId(orderId);

		// convert jsonResponse to java Object
		resp = JsonUtil.jsonToJava(jsonResp, Resp.class);
		
		if(resp.getStatus()==(byte)1){
			order=JsonUtil.jsonToJava(resp.getData(), Order.class);
		}

		return order;
	}

	@Override
	public List<Order> getOrderDetailsByPatientIdAndPharmacyId(int pharmacyId, int patientId) {
		
		String jsonResp = null;
		Resp resp = null;
		List<Order> list=new ArrayList<>();

		// call client method to delete the book
		jsonResp = phrmacyServiceClient.getOrderDetailsByPatientIdAndPharmacyId(pharmacyId, patientId);

		// convert jsonResponse to java Object
		resp = JsonUtil.jsonToJava(jsonResp, Resp.class);
		
		if(resp.getStatus()==(byte)1){
			list=JsonUtil.jsonToJava(resp.getData(), List.class);
		}

		return list;
	}

	@Override
	public List<Order> getAllOrdersByPharmacyId(int pharmacyId) {
		String jsonResp = null;
		Resp resp = null;
		List<Order> list=new ArrayList<>();

		// call client method to delete the book
		jsonResp = phrmacyServiceClient.getAllOrdersByPharmacyId(pharmacyId);

		// convert jsonResponse to java Object
		resp = JsonUtil.jsonToJava(jsonResp, Resp.class);
		
		if(resp.getStatus()==(byte)1){
			list=JsonUtil.jsonToJava(resp.getData(), List.class);
		}
		

		return list;
	}

	@Override
	public Resp changeDeliveryStatus(int orderId, int patientid) {
		String jsonResp = null;
		Resp resp = null;

		// call client method to delete the book
		jsonResp = phrmacyServiceClient.changeDeliveryStatus(orderId);

		// convert jsonResponse to java Object
		resp = JsonUtil.jsonToJava(jsonResp, Resp.class);
		
		if(resp.getStatus()==(byte)1){
			try{
				patientHealthRecordsDAO.updateDeliveryStatusByPhrId(patientHealthRecordsDAO.getPHRIdByOrderId(orderId));
				Patient p=patientHealthRecordsDAO.getPatientDetails(patientid);
				
				
				PrescriptionSMSBean smsBean=new PrescriptionSMSBean();
				smsBean.setFrom("MODSMS");
				smsBean.setTo(p.getPatient_mobileno());
				smsBean.setTemplateName("MOD_ORDER_MSG_TEMPLATE");
				smsBean.setVAR1(p.getPatient_name());
				smsBean.setVAR2(String.valueOf(orderId));
				smsBean.setVAR3(p.getPatient_pharmacy_name());
				smsBean.setVAR4("http://dev.magicurehealthcare.com:8091/E-FoldeForPatientHealthRecords_10thJan18_19s/phr/patient_login.htm");
				
				String jsonPrescriptionSMSBean=JsonUtil.javaToJson(smsBean);
				System.out.println(jsonPrescriptionSMSBean);
				
				try{
					String jsonSMSResponse=smsServiceClient.sendPrescriptionSMS(jsonPrescriptionSMSBean);
					
					SMSResp smsResp=JsonUtil.jsonToJava(jsonSMSResponse, SMSResp.class);
					
					System.out.println("ststus::"+smsResp.getStatus()+" "+" Details::"+smsResp.getDetails());
					
					if(smsResp.getStatus().equals("Success"))
						System.out.println("SMS Sended");
					else
						System.out.println("SMS Not Sended");
				}catch(Exception e){
					System.out.println("getting error while sending message");
					e.printStackTrace();
				}
				
			}catch(Exception e){
				System.out.println("getting error while setting props to sending message");
				e.printStackTrace();
			}
		}

		return resp;
	}

	@Override
	public Resp changePaymentStatus(int orderId, int patientid) {
		String jsonResp = null;
		Resp resp = null;

		// call client method to delete the book
		jsonResp = phrmacyServiceClient.changePaymentStatus(orderId);

		// convert jsonResponse to java Object
		resp = JsonUtil.jsonToJava(jsonResp, Resp.class);
		
		return resp;
	}

	@Override
	public int changePaymentStatusByPhrId(int phrId) {
		int cnt=0,cnt1=0;
		
		
		cnt=patientHealthRecordsDAO.updatePaymentStatusByPhrId(phrId);
		
		if(cnt!=0)
			cnt1=patientHealthRecordsDAO.updateDeliveryStatusOfOrderTabByPhrId(phrId);
		
		
		
		return cnt1;
	}

}
