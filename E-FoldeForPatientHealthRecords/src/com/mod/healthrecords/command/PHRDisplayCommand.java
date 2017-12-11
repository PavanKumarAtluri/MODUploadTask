package com.mod.healthrecords.command;

public class PHRDisplayCommand {
	private Integer patient_id;
	private String phr_type;

	public Integer getPatient_id() {
		return patient_id;
	}

	public void setPatient_id(Integer patient_id) {
		this.patient_id = patient_id;
	}

	public String getPhr_type() {
		return phr_type;
	}

	public void setPhr_type(String phr_type) {
		this.phr_type = phr_type;
	}

	@Override
	public String toString() {
		return "PHRDisplayCommand [patient_id=" + patient_id + ", phr_type=" + phr_type + "]";
	}

}
