package com.mk.dao;

import java.util.List;

import com.mk.model.Contact;

public interface TestDao {
	
	void saveOrUpdate(Contact contact);
	List<Contact> getContacts();
}
