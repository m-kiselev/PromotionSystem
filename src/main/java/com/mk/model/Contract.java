package com.mk.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "contract")
public class Contract {
	
	@Id
    @GeneratedValue
	private Integer id;
	private String number;
	private boolean isApproved = false;
	private Date approvedDate;
	private String comment;
	private Integer managerId;
	private Integer clientId;
	private EnumContractStatus status;

	public Contract() {
	}

	public Contract(String number, String comment, Integer managerId) {
		this.number = number;
		this.comment = comment;
		this.managerId = managerId;
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

	public boolean isApproved() {
		return isApproved;
	}

	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}

	public Date getApprovedDate() {
		return approvedDate;
	}

	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getManagerId() {
		return managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}
	
	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public EnumContractStatus getStatus() {
		return status;
	}

	public void setStatus(EnumContractStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return String.format("Contract: [id = %s, number = %s, isApproved = %s, approvedDate = %s, comment = %s, status = %s]",
				id, number, isApproved, approvedDate, comment, status);
	}
}
