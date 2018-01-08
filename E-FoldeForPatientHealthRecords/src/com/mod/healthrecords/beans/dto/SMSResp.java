package com.mod.healthrecords.beans.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SMSResp {
	@JsonProperty("Status")
	private String Status;
	@JsonProperty("Details")
	private String Details;
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getDetails() {
		return Details;
	}
	public void setDetails(String details) {
		Details = details;
	}
}
