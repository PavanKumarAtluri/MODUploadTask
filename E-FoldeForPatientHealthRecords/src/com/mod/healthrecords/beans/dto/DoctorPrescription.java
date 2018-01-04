package com.mod.healthrecords.beans.dto;

public class DoctorPrescription {
	private int phrId;
	private int docId;
	private int patId;
	private int phrmacyId;
	private String medicalPrescrition;
	
	public int getPhrId() {
		return phrId;
	}

	public void setPhrId(int phrId) {
		this.phrId = phrId;
	}
	
	public int getDocId() {
		return docId;
	}

	public void setDocId(int docId) {
		this.docId = docId;
	}

	public int getPatId() {
		return patId;
	}

	public void setPatId(int patId) {
		this.patId = patId;
	}

	public int getPhrmacyId() {
		return phrmacyId;
	}

	public void setPhrmacyId(int phrmacyId) {
		this.phrmacyId = phrmacyId;
	}

	public String getMedicalPrescrition() {
		return medicalPrescrition;
	}

	public void setMedicalPrescrition(String medicalPrescrition) {
		this.medicalPrescrition = medicalPrescrition;
	}

	@Override
	public String toString() {
		return "DoctorPrescription [phrId=" + phrId + ", docId=" + docId + ", patId=" + patId + ", phrmacyId="
				+ phrmacyId + ", medicalPrescrition=" + medicalPrescrition + "]";
	}

	

}
