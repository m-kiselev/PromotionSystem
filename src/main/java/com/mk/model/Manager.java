package com.mk.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "manager")
public class Manager extends Person {

	private Integer hourOfDay;
	private Integer salary;

	public Manager() {
	}

	public Manager(String number, String fio, Integer hourOfDay, Integer salary) {
		super(number, fio);
		this.hourOfDay = hourOfDay;
		this.salary = salary;
	}
	
	public Manager(String number, String fio, String phone, String passportData, Integer hourOfDay, Integer salary) {
		super(number, fio, phone, passportData);
		this.hourOfDay = hourOfDay;
		this.salary = salary;
	}
	
	public Manager(String number, String fio, String phone, String passportData, String adress, Integer hourOfDay, Integer salary) {
		super(number, fio, phone, passportData, adress);
		this.hourOfDay = hourOfDay;
		this.salary = salary;
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
		return String.format("Contract: [hourOfDay = %s, salary = %s]",
				hourOfDay, salary);
	}
}
