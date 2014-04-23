package com.mk.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "legal_client")
public class LegalClient extends Client {

	private String details;
	private String name;
	
	public LegalClient() {
	}

	public LegalClient(String fio, String phone, String adress, String details, String name) {
		super(fio, phone, adress);
		this.setDetails(details);
		this.setName(name);
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
