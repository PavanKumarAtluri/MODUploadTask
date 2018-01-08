package com.mod.healthrecords.beans.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PrescriptionSMSBean {
	@JsonProperty("From")
	private String From;
	@JsonProperty("To")
	private String To;
	@JsonProperty("TemplateName")
	private String TemplateName;
	@JsonProperty("VAR1")
	private String VAR1;
	@JsonProperty("VAR2")
	private String VAR2;
	@JsonProperty("VAR3")
	private String VAR3;
	@JsonProperty("VAR4")
	private String VAR4;
	
	public String getFrom() {
		return From;
	}
	public void setFrom(String from) {
		From = from;
	}
	public String getTo() {
		return To;
	}
	public void setTo(String to) {
		To = to;
	}
	public String getTemplateName() {
		return TemplateName;
	}
	public void setTemplateName(String templateName) {
		TemplateName = templateName;
	}
	public String getVAR1() {
		return VAR1;
	}
	public void setVAR1(String vAR1) {
		VAR1 = vAR1;
	}
	public String getVAR2() {
		return VAR2;
	}
	public void setVAR2(String vAR2) {
		VAR2 = vAR2;
	}
	public String getVAR3() {
		return VAR3;
	}
	public void setVAR3(String vAR3) {
		VAR3 = vAR3;
	}
	public String getVAR4() {
		return VAR4;
	}
	public void setVAR4(String vAR4) {
		VAR4 = vAR4;
	}
}
