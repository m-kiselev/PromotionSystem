package com.mk.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mk.dao.AbstractDao;
import com.mk.dao.ContractDao;
import com.mk.model.Contract;

@Repository
@Transactional
public class ContractDaoImpl extends AbstractDao<Contract, Long> implements ContractDao  {

    @Autowired
    private SessionFactory sessionFactory;

	@Override
	public List<Contract> listContracts(Long managerId, Date startDate, Date endDate, Integer from, Integer count) {

		String hql = "SELECT c FROM Contract c ";
//		if (managerId != null || startDate != null || endDate != null) {
//			hql = hql + "WHERE ";
//		}
//		if (managerId != null) {
//			hql = hql + "c.managerId = :managerId";
//		}
//		if (startDate != null) {
//			hql = hql + "c.approvedDate >= :startDate";
//		}
//		if (endDate != null) {
//			hql = hql + "c.approvedDate <= :endDate";
//		}
		Query query = sessionFactory
				.getCurrentSession()
				.createQuery(hql);
//				.setParameter("managerId", managerId)
//				.setParameter("startDate", startDate)
//				.setParameter("endDate", endDate);
		setLimits(query, from, count);
		return query.list();
	}

	@Override
	public Long countContracts(Long managerId, Date startDate, Date endDate) {
		
		String hql = "SELECT count(c) FROM Contract c ";
		Query query = sessionFactory
				.getCurrentSession()
				.createQuery(hql);
//				.setParameter("managerId", managerId)
//				.setParameter("startDate", startDate)
//				.setParameter("endDate", endDate);
		return ((Number) query.uniqueResult()).longValue();
	}

}
