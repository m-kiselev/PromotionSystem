package com.mk.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;


@MappedSuperclass
public class Person implements Identifiable<Long> {

	@Id
    @GeneratedValue
	private long id;
	private String number;
	private String fio;
	private String phone;
	private String passportData;
	private String adress;
	
	public Person() {
	}

	public Person(String number, String fio) {
		this.number = number;
		this.fio = fio;
	}
	
	public Person(String number, String fio, String phone, String passportData) {
		this.number = number;
		this.fio = fio;
		this.phone = phone;
		this.passportData = passportData;
	}
	
	public Person(String number, String fio, String phone, String passportData, String adress) {
		this.number = number;
		this.fio = fio;
		this.phone = phone;
		this.passportData = passportData;
		this.adress = adress;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
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
	public String getPassportData() {
		return passportData;
	}
	public void setPassportData(String passportData) {
		this.passportData = passportData;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	
	@Override
	public String toString() {
		return String.format("Contract: [id = %s, number = %s, fio = %s]",
				id, number, fio);
	}
}
