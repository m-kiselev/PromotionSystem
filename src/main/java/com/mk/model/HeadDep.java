package com.mk.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "headDep")
public class HeadDep extends Person {
	

	private Long		monthPlanId;
	private String		monthPlanNumber;
	
	public HeadDep() {
	}

	public HeadDep(String number, String fio, Long monthPlanId) {
		super(number, fio);
		this.setMonthPlanId(monthPlanId);
	}
	
	public HeadDep(String number, String fio, String phone, String passportData, Long monthPlanId) {
		super(number, fio, phone, passportData);
		this.setMonthPlanId(monthPlanId);
	}
	
	public HeadDep(String number, String fio, String phone, String passportData, String adress, Long monthPlanId) {
		super(number, fio, phone, passportData, adress);
		this.setMonthPlanId(monthPlanId);
	}

	public Long getMonthPlanId() {
		return monthPlanId;
	}

	public void setMonthPlanId(Long monthPlanId) {
		this.monthPlanId = monthPlanId;
	}

	public String getMonthPlanNumber() {
		return monthPlanNumber;
	}

	public void setMonthPlanNumber(String monthPlanNumber) {
		this.monthPlanNumber = monthPlanNumber;
	}
}
