package com.mk.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mk.dao.ReportDao;
import com.mk.service.ReportService;
import com.mk.view.SalaryReportRecord;

@Service("reportService")
public class ReportServiceImpl implements ReportService {
	
	@Autowired
	ReportDao	reportDao;
	
	/* should be moved to the configuration file or DB */
	private static final Integer vipCoast      = 500;
	private static final Integer standartCoast = 300;
	
	public List<SalaryReportRecord> getSalaryReportRecords(Date curDate, Integer from, Integer count) {
		
		List<SalaryReportRecord> list  = reportDao.getSalaryReportRecords(curDate, from, count);
		populateSalaryReportRecords(list);
		return list;
	}

	private void populateSalaryReportRecords(List<SalaryReportRecord> records) {
		for (SalaryReportRecord rec : records) {
			Integer hourCost = (int) Math.ceil(rec.getSalary() / ( 22 * 8 ));
			rec.setSalaryOfMonth(hourCost * rec.getHourOfDay());
			rec.setBonus(rec.getStandardCount() * standartCoast + rec.getVipCount() * vipCoast);
			rec.setSumm(rec.getSalaryOfMonth() + rec.getBonus());
		}
	}
}
