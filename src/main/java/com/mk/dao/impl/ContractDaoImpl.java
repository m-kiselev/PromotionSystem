package com.mk.dao.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

//    SELECT * FROM contract
//    where  date(SUBSTR(approvedDate, 1,10), 'unixepoch') >= date('2014-05-13')
	@Override
	public List<Contract> listContracts(Integer managerId, Date startDate, Date endDate, Integer from, Integer count) {

		
		System.out.println("===========");
		System.out.println(startDate);
		System.out.println(endDate);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String sDate = "'" + dateFormat.format(startDate) + "'";
		String eDate = "'" + dateFormat.format(endDate) + "'";
		String hql = String.format("SELECT c FROM Contract c WHERE date(SUBSTR(c.approvedDate, 1,10), 'unixepoch') >= %s "
				+ "AND date(SUBSTR(c.approvedDate, 1,10), 'unixepoch') <= %s",
				sDate,
				eDate);

		if (managerId != null) {
			hql = hql + " AND c.managerId = :managerId";
		}

		
		Query query = sessionFactory
				.getCurrentSession()
				.createQuery(hql);
//				.setParameter("startDate", sDate)
//				.setParameter("endDate",   eDate);
		
		System.out.println(sDate);
		System.out.println(eDate);
		System.out.println(query.toString());
		System.out.println(hql);

		if (managerId != null) {
			query.setParameter("managerId", managerId);
		}

		setLimits(query, from, count);
		return query.list();
	}

	@Override
	public Long countContracts(Integer managerId, Date startDate, Date endDate) {
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String sDate = "'" + dateFormat.format(startDate) + "'";
		String eDate = "'" + dateFormat.format(endDate) + "'";
		String hql = String.format("SELECT count(c) FROM Contract c WHERE date(SUBSTR(c.approvedDate, 1,10), 'unixepoch') >= %s "
				+ "AND date(SUBSTR(c.approvedDate, 1,10), 'unixepoch') <= %s",
				sDate,
				eDate);

		if (managerId != null) {
			hql = hql + " AND c.managerId = :managerId";
		}

		Query query = sessionFactory
				.getCurrentSession()
				.createQuery(hql);
//				.setParameter("startDate", sDate)
//				.setParameter("endDate",   eDate);

		if (managerId != null) {
			query.setParameter("managerId", managerId);
		}

		return ((Number) query.uniqueResult()).longValue();
	}

}
