package com.mk.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "service")
public class Service {

	@Id
    @GeneratedValue
	private Integer id;
	private String name;
	private String timeInterval;

	public Service() {
	}

	public Service(String name, String timeInterval) {
		this.name = name;
		this.timeInterval = timeInterval;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTimeInterval() {
		return timeInterval;
	}
	public void setTimeInterval(String timeInterval) {
		this.timeInterval = timeInterval;
	}
	
	@Override
	public String toString() {
		return String.format("Contract: [id = %s, name = %s, timeInterval = %s]",
				id, name, timeInterval);
	}
}
