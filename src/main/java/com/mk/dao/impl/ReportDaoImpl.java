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

import com.mk.Utils;
import com.mk.dao.AbstractDao;
import com.mk.dao.ReportDao;
import com.mk.model.Contract;
import com.mk.view.ContractReportRecord;
import com.mk.view.SalaryReportRecord;

@Repository
@Transactional
public class ReportDaoImpl extends AbstractDao<Contract, Long> implements ReportDao {
	
    @Autowired
    private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<SalaryReportRecord> getSalaryReportRecords(Date date, Integer from, Integer count) {
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String sDate = "'" + dateFormat.format(Utils.firstDayOfMonth(date)) + "'";
		String eDate = "'" + dateFormat.format(Utils.lastDayOfMonth(date)) + "'";
		String interval = String.format("date(SUBSTR(b.approvedDate, 1,10), 'unixepoch') >= %s "
				+ "AND date(SUBSTR(b.approvedDate, 1,10), 'unixepoch') <= %s ",
				sDate,
				eDate);
		
		StringBuilder sb = new StringBuilder();
		sb.append("select a.fio, a.salary, a.hourOfDay, ");
		sb.append("(SELECT count(c.id) FROM contract c WHERE a.id = c.managerId and ");
		sb.append(interval);
		sb.append("and c.isApproved = 1 and c.status = 0) vipCount, ");
		sb.append("(SELECT count(d.id) FROM contract d WHERE a.id = d.managerId and ");
		sb.append(interval);
		sb.append("and d.isApproved = 1 and d.status = 1) standardCount ");
		sb.append("FROM manager a, contract b WHERE a.id = b.managerId and ");
		sb.append(interval);
		sb.append("and b.isApproved = 1 ");
		sb.append("group by a.fio, a.salary, a.hourOfDay");
		
		Query query =  sessionFactory.getCurrentSession()
				.createSQLQuery(sb.toString())
				.addEntity(SalaryReportRecord.class);
		setLimits(query, from, count);

		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ContractReportRecord> getContractReportRecords(Date date, Integer from, Integer count) {

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String sDate = "'" + dateFormat.format(Utils.firstDayOfMonth(date)) + "'";
		String eDate = "'" + dateFormat.format(Utils.lastDayOfMonth(date)) + "'";
		String hql = String.format("SELECT a.fio, b.number, b.isApproved, b.comment, b.summ "
				+ "FROM manager a, contract b "
				+ "WHERE a.id = b.managerId "
				+ "AND date(SUBSTR(b.approvedDate, 1,10), 'unixepoch') >= %s "
				+ "AND date(SUBSTR(b.approvedDate, 1,10), 'unixepoch') <= %s",
				sDate,
				eDate);

		Query query =  sessionFactory.getCurrentSession()
				.createSQLQuery(hql)
				.addEntity(ContractReportRecord.class);
		setLimits(query, from, count);
		return query.list();
	}
	
}
