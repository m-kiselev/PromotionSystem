package com.mk.service;

import java.util.Date;
import java.util.List;

import com.mk.view.SalaryReportRecord;

public interface ReportService {
	
	public List<SalaryReportRecord> getSalaryReportRecords(Date curDate, Integer from, Integer count);
}
