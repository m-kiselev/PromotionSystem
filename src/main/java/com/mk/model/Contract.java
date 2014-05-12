package com.mk.model;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.time.DateUtils;

@Entity
@Table(name = "contract")
public class Contract implements Identifiable<Long> {
	
	@Id
    @GeneratedValue
	private long id;
	private String number;
	private boolean isApproved = false;
	
	@Temporal(TemporalType.DATE)
	private Date approvedDate;
	private String comment;
	
	/* manager data */
	private Integer managerId;
	private String  managerName;
	
	/* manager data */
	private Integer clientId;
	private boolean clientIsIndivid = true;
	private String  clientName;
	
	private EnumContractStatus status;

	public Contract() {
	}

	public Contract(String number, String comment, Integer managerId) {
		this.number = number;
		this.comment = comment;
		this.managerId = managerId;
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
		this.approvedDate = approvedDate == null
				? null
				: DateUtils.truncate(approvedDate, Calendar.DATE);
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

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public boolean isClientIsIndivid() {
		return clientIsIndivid;
	}

	public void setClientIsIndivid(boolean clientIsIndivid) {
		this.clientIsIndivid = clientIsIndivid;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	
	@Override
	public String toString() {
		return String.format("Contract: [id = %s, number = %s, isApproved = %s, approvedDate = %s, comment = %s, status = %s]",
				id, number, isApproved, approvedDate, comment, status);
	}


}
