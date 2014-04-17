package com.mk.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mk.dao.ContractDao;
import com.mk.model.Contract;

@Repository
@Transactional
public class ContractDaoImpl implements ContractDao {

    @Autowired
    private SessionFactory sessionFactory;
    
	@Override
	public void saveOrUpdate(Contract contract) {
	  	sessionFactory.getCurrentSession().saveOrUpdate(contract);
	}

    @Transactional
	@SuppressWarnings("unchecked")
	public List<Contract> getContracts() {
		List<Contract> list = sessionFactory.getCurrentSession().createQuery("from Contract").list();
        return list;
	}

	@Override
	public void delete(Contract contract) {
		 sessionFactory.getCurrentSession().delete(contract);
	}

}
