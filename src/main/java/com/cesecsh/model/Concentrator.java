package com.cesecsh.model;

public class Concentrator {

	private String conId;
	private String conName;	
	private String conCode;
	private String conIp;
	private String conPath;
	private ConcentratorType concentratorType;
	public String getConId() {
		return conId;
	}
	public void setConId(String conId) {
		this.conId = conId;
	}
	public String getConName() {
		return conName;
	}
	public void setConName(String conName) {
		this.conName = conName;
	}
	public String getConCode() {
		return conCode;
	}
	public void setConCode(String conCode) {
		this.conCode = conCode;
	}
	public String getConIp() {
		return conIp;
	}
	public void setConIp(String conIp) {
		this.conIp = conIp;
	}
	
	public String getConPath() {
		return conPath;
	}
	public void setConPath(String conPath) {
		this.conPath = conPath;
	}
	public ConcentratorType getConcentratorType() {
		return concentratorType;
	}
	public void setConcentratorType(ConcentratorType concentratorType) {
		this.concentratorType = concentratorType;
	}
	
}