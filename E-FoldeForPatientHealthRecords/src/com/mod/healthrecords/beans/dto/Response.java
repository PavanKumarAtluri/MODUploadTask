package com.mod.healthrecords.beans.dto;

public class Response {
	private byte status;
	private String msg;
	
	public byte getStatus() {
		return status;
	}
	public void setStatus(byte status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	@Override
	public String toString() {
		return "Response [status=" + status + ", msg=" + msg + "]";
	}
	
	
}
