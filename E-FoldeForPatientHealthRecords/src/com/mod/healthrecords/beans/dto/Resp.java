package com.mod.healthrecords.beans.dto;

public class Resp {
	private byte status;
	private String msg;
	private String data;
	
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
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		return "Resp [status=" + status + ", msg=" + msg + ", data=" + data + "]";
	}
	
	
}
