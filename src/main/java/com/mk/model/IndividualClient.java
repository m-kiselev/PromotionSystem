package com.mk.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "individ_client")
public class IndividualClient extends Client {

	private String passportData;
	private String additionInfo;
	
	public IndividualClient() {
	}

	public IndividualClient(String fio, String phone, String adress, String passportData, String additionInfo) {
		super(fio, phone, adress);
		this.setPassportData(passportData);
		this.setAdditionInfo(additionInfo);
	}
	
	public String getPassportData() {
		return passportData;
	}
	public void setPassportData(String passportData) {
		this.passportData = passportData;
	}
	public String getAdditionInfo() {
		return additionInfo;
	}
	public void setAdditionInfo(String additionInfo) {
		this.additionInfo = additionInfo;
	}
}
