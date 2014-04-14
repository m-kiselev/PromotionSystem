package com.mk.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mk.dao.ManagerDao;
import com.mk.model.Contact;
import com.mk.model.Manager;

@Repository
@Transactional
public class ManagerDaoImpl implements ManagerDao {

    @Autowired
    private SessionFactory sessionFactory;

	@Transactional
	public void saveOrUpdate(Manager manager) {
	  	sessionFactory.getCurrentSession().saveOrUpdate(manager);
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public List<Manager> getManagers() {
		List<Manager> list = sessionFactory.getCurrentSession().createQuery("from Manager").list();
        return list;
	}

	@Transactional
	public void delete(Manager manager) {
		 sessionFactory.getCurrentSession().delete(manager);
	}

}
