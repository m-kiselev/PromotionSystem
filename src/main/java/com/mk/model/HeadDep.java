package com.mk.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

@Entity
@Table(name = "headDep")
public class HeadDep extends Person {
	
	@ManyToOne(fetch = FetchType.LAZY)
	@LazyToOne(LazyToOneOption.PROXY)
	@JoinColumn(name = "mplan_id")
	private MonthPlan	monthPlan;

	@Column(name = "mplan_id", updatable = false, insertable = false)
	private Long		monthPlanId;
	
	public HeadDep() {
	}

	public HeadDep(String number, String fio, MonthPlan monthPlan) {
		super(number, fio);
		this.setMonthPlan(monthPlan);
	}
	
	public HeadDep(String number, String fio, String phone, String passportData, MonthPlan monthPlan) {
		super(number, fio, phone, passportData);
		this.setMonthPlan(monthPlan);
	}
	
	public HeadDep(String number, String fio, String phone, String passportData, String adress, MonthPlan monthPlan) {
		super(number, fio, phone, passportData, adress);
		this.setMonthPlan(monthPlan);
	}

	public MonthPlan getMonthPlan() {
		return monthPlan;
	}

	public void setMonthPlan(MonthPlan monthPlan) {
		this.monthPlan = monthPlan;
	}

	public Long getMonthPlanId() {
		return monthPlanId;
	}

	public void setMonthPlanId(Long monthPlanId) {
		this.monthPlanId = monthPlanId;
	}
}
