package com.mk.view;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.annotations.Subselect;

@Entity
@Subselect(" ")
public class SalaryReportRecord {
	
	@Id
	private String  fio;
	private Integer salary;
	private Integer hourOfDay;
	private Integer standardCount;
	private Integer vipCount;
	
	/* populated properties */
	@Transient
	private Integer salaryOfMonth;
	@Transient
	private Integer bonus;
	@Transient
	private Integer summ;

	public String getFio() {
		return fio;
	}
	public void setFio(String fio) {
		this.fio = fio;
	}
	public Integer getSalary() {
		return salary;
	}
	public void setSalary(Integer salary) {
		this.salary = salary;
	}
	public Integer getHourOfDay() {
		return hourOfDay;
	}
	public void setHourOfDay(Integer hourOfDay) {
		this.hourOfDay = hourOfDay;
	}
	public Integer getSalaryOfMonth() {
		return salaryOfMonth;
	}
	public void setSalaryOfMonth(Integer salaryOfMonth) {
		this.salaryOfMonth = salaryOfMonth;
	}
	public Integer getBonus() {
		return bonus;
	}
	public void setBonus(Integer bonus) {
		this.bonus = bonus;
	}
	public Integer getSumm() {
		return summ;
	}
	public void setSumm(Integer summ) {
		this.summ = summ;
	}
	public Integer getStandardCount() {
		return standardCount;
	}
	public void setStandardCount(Integer standardCount) {
		this.standardCount = standardCount;
	}
	public Integer getVipCount() {
		return vipCount;
	}
	public void setVipCount(Integer vipCount) {
		this.vipCount = vipCount;
	}
	
}
