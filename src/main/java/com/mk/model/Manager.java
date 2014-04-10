package com.mk.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "manager")
public class Manager {

	@Id
    @GeneratedValue
	private Integer id;
	private String number;
	private String fio;
	private String phone;
	private String passportData;
	private String adress;
	private Integer hourOfDay;
	private Integer salary;

	public Manager() {
	}

	public Manager(String number, String fio, Integer hourOfDay, Integer salary) {
		this.number = number;
		this.fio = fio;
		this.hourOfDay = hourOfDay;
		this.salary = salary;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
	public Integer getHourOfDay() {
		return hourOfDay;
	}
	public void setHourOfDay(Integer hourOfDay) {
		this.hourOfDay = hourOfDay;
	}
	public Integer getSalary() {
		return salary;
	}
	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return String.format("Contract: [id = %s, number = %s, fio = %s, hourOfDay = %s, salary = %s]",
				id, number, fio, hourOfDay, salary);
	}
}
