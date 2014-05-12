package com.mk.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "month_plan")
public class MonthPlan  implements Identifiable<Long> {

	@Id
    @GeneratedValue
	private long id;
	private String number;
	private Integer sumVIP;
	private Integer sumStandart;
	private Integer countVIP;
	private Integer countStandart;

	public MonthPlan() {
	}

	public MonthPlan(String number, Integer sumVIP, Integer sumStandart, Integer countVIP, Integer countStandart) {
		this.number = number;
		this.sumVIP = sumVIP;
		this.sumStandart = sumStandart;
		this.countVIP = countVIP;
		this.countStandart = countStandart;
	}
	
	@Override
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
	public Integer getSumVIP() {
		return sumVIP;
	}
	public void setSumVIP(Integer sumVIP) {
		this.sumVIP = sumVIP;
	}
	public Integer getSumStandart() {
		return sumStandart;
	}
	public void setSumStandart(Integer sumStandart) {
		this.sumStandart = sumStandart;
	}
	public Integer getCountVIP() {
		return countVIP;
	}
	public void setCountVIP(Integer countVIP) {
		this.countVIP = countVIP;
	}
	public Integer getCountStandart() {
		return countStandart;
	}
	public void setCountStandart(Integer countStandart) {
		this.countStandart = countStandart;
	}
	
	@Override
	public String toString() {
		return String.format("Contract: [id = %s, number = %s, sumVIP = %s, sumStandart = %s, countVIP = %s, countStandart = %s]",
				id, number, sumVIP, sumStandart, countVIP, countStandart);
	}
}
