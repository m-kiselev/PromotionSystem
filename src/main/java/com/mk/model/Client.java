package com.mk.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Client implements Identifiable<Long> {

	@Id
    @GeneratedValue
	private long id;
	private String fio;
	private String phone;
	private String adress;
	
	public Client() {
	}

	public Client(String fio) {
		this.fio = fio;
	}
	
	public Client(String fio, String phone, String adress) {
		this.fio = fio;
		this.phone = phone;
		this.setAdress(adress);
	}
	
	/* true -физ, false - юр. лицо */
	private boolean isIndivid;

	@Override
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getFio() {
		return fio;
	}

	public void setFio(String fio) {
		this.fio = fio;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isIndivid() {
		return isIndivid;
	}

	public void setIndivid(boolean isIndivid) {
		this.isIndivid = isIndivid;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}
}
