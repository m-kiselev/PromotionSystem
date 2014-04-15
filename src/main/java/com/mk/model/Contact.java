package com.mk.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the contact database table.
 * 
 */
@Entity
@Table(name = "contact")
public class Contact {
	
	@Id
    @GeneratedValue
	private Integer id;
	private String name;
	private String email;

	public Contact() {
	}

	public Contact(String name, String email) {
		this.name = name;
		this.email = email;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return String.format("Contact: [id = %s, name = %s, email = %s]",
				id, name, email);
	}
}