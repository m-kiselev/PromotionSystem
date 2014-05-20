package com.mk.view;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Subselect;

@Entity
@Subselect(" ")
public class ContractReportRecord {
	
	@Id
	private String number;
	private String  fio;
	private boolean isApproved;
	private String comment;
	private Integer summ;

	public String getFio() {
		return fio;
	}
	public void setFio(String fio) {
		this.fio = fio;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Integer getSumm() {
		return summ;
	}
	public void setSumm(Integer summ) {
		this.summ = summ;
	}
	public boolean isApproved() {
		return isApproved;
	}
	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}
}

