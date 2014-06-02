package com.mk.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "service_contract")
public class ServiceContract implements Identifiable<Long> {
	
	@Id
    @GeneratedValue
	private long id;
	private String number;
	
	@Temporal(TemporalType.DATE)
	private Date   approvedDate;
	private String complexName;
	private EnumServiceType serviceType;
	private Integer serviceCount;
	private Integer usedServiceCount;
	private Integer coast;
	private boolean status = false;
	private String managerName;
	
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
	public Date getApprovedDate() {
		return approvedDate;
	}
	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}
	public String getComplexName() {
		return complexName;
	}
	public void setComplexName(String complexName) {
		this.complexName = complexName;
	}
	public EnumServiceType getServiceType() {
		return serviceType;
	}
	public void setServiceType(EnumServiceType serviceType) {
		this.serviceType = serviceType;
	}
	public Integer getServiceCount() {
		return serviceCount;
	}
	public void setServiceCount(Integer serviceCount) {
		this.serviceCount = serviceCount;
	}
	public Integer getUsedServiceCount() {
		return usedServiceCount;
	}
	public void setUsedServiceCount(Integer usedServiceCount) {
		this.usedServiceCount = usedServiceCount;
	}
	public Integer getCoast() {
		return coast;
	}
	public void setCoast(Integer coast) {
		this.coast = coast;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
}
