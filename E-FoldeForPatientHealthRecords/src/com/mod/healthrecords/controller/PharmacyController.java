package com.mod.healthrecords.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mod.healthrecords.beans.dto.Order;
import com.mod.healthrecords.beans.dto.Resp;
import com.mod.healthrecords.service.PatientHealthRecordsServiceI;
import com.mod.healthrecords.utils.JsonUtil;

@Controller("pharmacyController")
@RequestMapping("/pharmacy")
public class PharmacyController {
	
	@Autowired
	private PatientHealthRecordsServiceI service;
	
	@RequestMapping(value = "/pharmacy_login.htm", method = RequestMethod.GET)
	public String getPharmacyLoginPage() {
		return "pharmacy_login";
	}
	
	@RequestMapping(value = "/pharmacy_dashboard.htm", method = RequestMethod.GET)
	public String getPharmacyHome() {
		return "pharmacy_dashboard";
	}
	
	@RequestMapping(value = "/pharmacy_login.htm", method = RequestMethod.POST)
	public String pharmacyLoginProcess(HttpServletRequest req, HttpSession session) {
		String pharmacyId = req.getParameter("pharmacyId");
		String password = req.getParameter("pwd");
		
		if(pharmacyId.equalsIgnoreCase("9") && password.equals("welcome@123")) {
			session.setAttribute("pharmacyId", pharmacyId);
			session.removeAttribute("errorMsg");
			return "pharmacy_dashboard";
		} else if(pharmacyId.equalsIgnoreCase("11") && password.equals("welcome@123")) {
			session.setAttribute("pharmacyId", pharmacyId);
			session.removeAttribute("errorMsg");
			return "pharmacy_dashboard";
		} else if(pharmacyId.equalsIgnoreCase("13") && password.equals("welcome@123")) {
			session.setAttribute("pharmacyId", pharmacyId);
			session.removeAttribute("errorMsg");
			return "pharmacy_dashboard";
		}
		else{
			session.setAttribute("errorMsg", "Invalid Credentials");
		}
		return "pharmacy_login";
	}
	
	
	@RequestMapping(value = "/logout.htm", method = RequestMethod.GET)
	public String logout(HttpServletRequest req, HttpSession session) {
		session.removeAttribute("pharmacyId");
		session.invalidate();
		return "home";
	}
	
	
	@RequestMapping(value="/pharmacy_orders_result.htm",method = RequestMethod.GET)
	public String getAllOrdersOfAPharmacy(@RequestParam("id") Integer pharmacyId,Model model,@RequestParam(required=false,value="statusMsg") String statusMsg,@RequestParam(required=false,value="statusMsg1") String statusMsg1){
		
		List<Order> list=service.getAllOrdersByPharmacyId(pharmacyId);
		
		model.addAttribute("listOfOrders", list);
		model.addAttribute("statusMsg", statusMsg);
		model.addAttribute("statusMsg1", statusMsg1);
		return "pharmacy_orders_result";
	}
	
	
	@RequestMapping(value="/get_order_by_order_id.htm",method = RequestMethod.GET)
	@ResponseBody
	public String getOrderbyOrderId(@RequestParam("orderId") Integer orderId,HttpSession session){
		List<Order> list=null;
		
		String pharmacyId = (String) session.getAttribute("pharmacyId");
		Integer pharmacyId1 = Integer.valueOf(pharmacyId);
		
		if(orderId==null){
			list=service.getOrderDetailsByOrderId(pharmacyId1,0);
		}else{
			list=service.getOrderDetailsByOrderId(pharmacyId1,orderId);
		}
			
		
			
		
		
		return JsonUtil.javaToJson(list);
		
	}
	
	@RequestMapping(value="/get_orders_by_patient_id.htm",method = RequestMethod.GET)
	@ResponseBody
	public String getOrdersbyPatientId(@RequestParam("id") Integer patientId,HttpSession session){
		
		String pharmacyId = (String) session.getAttribute("pharmacyId");
		Integer pharmacyId1 = Integer.valueOf(pharmacyId);
		List<Order> list=service.getOrderDetailsByPatientIdAndPharmacyId(pharmacyId1, patientId);
		//System.out.println(list.get(0).getIs_delivered());
		
		return JsonUtil.javaToJson(list);
		
	}
	
	@RequestMapping(value="/changeDeliveryStatus.htm",method=RequestMethod.GET)
	public String changeDeliveryStatus(@RequestParam("orderid") Integer orderId,@RequestParam("patientid") Integer patientid,HttpSession session){
		String pharmacyId = (String) session.getAttribute("pharmacyId");
		Integer pharmacyId1 = Integer.valueOf(pharmacyId);
		
		Resp resp=service.changeDeliveryStatus(orderId,patientid);
		
		
		
		return "redirect:pharmacy_orders_result.htm?id="+pharmacyId1+"&statusMsg="+resp.getMsg();
		
	}
	
	@RequestMapping(value="/changePaymentStatus.htm",method=RequestMethod.GET)
	public String changePaymentStatus(@RequestParam("orderid") Integer orderId,@RequestParam("patientid") Integer patientid,HttpSession session){
		String pharmacyId = (String) session.getAttribute("pharmacyId");
		Integer pharmacyId1 = Integer.valueOf(pharmacyId);
		
		Resp resp=service.changePaymentStatus(orderId,patientid);
		
		
		
		return "redirect:pharmacy_orders_result.htm?id="+pharmacyId1+"&statusMsg1="+resp.getMsg();
		
	}

}
