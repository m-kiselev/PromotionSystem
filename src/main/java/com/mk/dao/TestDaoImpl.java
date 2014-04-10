package com.mk.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mk.model.Contact;

@Repository
@Transactional
public class TestDaoImpl implements TestDao {

    @Autowired
    private SessionFactory sessionFactory;
    
    @Transactional
	public void saveOrUpdate(Contact contact) {
    	sessionFactory.getCurrentSession().saveOrUpdate(contact);
	}

    @Transactional
	@SuppressWarnings("unchecked")
	public List<Contact> getContacts() {
    	
		Contact myContact = new Contact("My Name", "my_email@email.com");
		Contact yourContact = new Contact("Your Name", "your_email@email.com");
		
		// Saving to the database
		saveOrUpdate(myContact);
		saveOrUpdate(yourContact);

		List<Contact> list = sessionFactory.getCurrentSession().createQuery("from Contact").list();
        if (list.isEmpty()) {
            System.out.println("=========Empty=========");
        }
        for (Contact contact : list) {
            System.out.println(contact.toString());
        }
        
        return list;
	}

}
