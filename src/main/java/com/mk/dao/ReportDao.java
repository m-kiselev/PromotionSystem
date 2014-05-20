package com.mk.dao;

import java.util.Date;
import java.util.List;

import com.mk.view.ContractReportRecord;
import com.mk.view.SalaryReportRecord;

public interface ReportDao {

	List<SalaryReportRecord> getSalaryReportRecords(Date date, Integer from, Integer count);

	List<ContractReportRecord> getContractReportRecords(Date date, Integer from, Integer count);
	
}
