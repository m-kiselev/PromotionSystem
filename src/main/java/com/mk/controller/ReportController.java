package com.mk.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mk.dao.ReportDao;
import com.mk.service.ReportService;
import com.mk.view.ContractReportRecord;
import com.mk.view.SalaryReportRecord;
import com.mk.web.JsonModelAndView;

@Controller
@RequestMapping("/report")
public class ReportController extends AbstractController {
	
	@Autowired
	ReportDao		reportDao;
	
	@Autowired
	ReportService	reportService;
	
	@ResponseBody
	@Transactional(readOnly = true)
	@RequestMapping(method = RequestMethod.GET, value = "/salary", produces = "application/json")
	public Object listSalaryReportRecords(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
			@RequestParam(value = "limit", required = false, defaultValue = "20") Integer count,
			@RequestParam(required = false) String date) throws IOException, ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Date curDate = sdf.parse(date);

		Integer from = (page == null) || (count == null) ? null : (page - 1) * count;
		List<SalaryReportRecord> list  = reportService.getSalaryReportRecords(curDate, from, count);
		Long total = (long) list.size();

		JsonModelAndView mav = new JsonModelAndView(jsonViews.getListSalaryReportRecordView());
		
		return makeListResponse(mav, list, count, page, total);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/salary/export")
	public ModelAndView excelSalaryReportRecords(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
			@RequestParam(value = "limit", required = false, defaultValue = "20") Integer count,
			@RequestParam(required = false) String date) throws IOException, ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Date curDate = sdf.parse(date);

		Integer from = (page == null) || (count == null) ? null : (page - 1) * count;
		List<SalaryReportRecord> list  = reportService.getSalaryReportRecords(curDate, from, count);
		
		return new ModelAndView("ExcelSalaryReport", "list", list);
	}

	@ResponseBody
	@Transactional(readOnly = true)
	@RequestMapping(method = RequestMethod.GET, value = "/contracts", produces = "application/json")
	public Object listContractReportRecords(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
			@RequestParam(value = "limit", required = false, defaultValue = "20") Integer count,
			@RequestParam(required = false) String date,
			@RequestParam(required = false) Integer managerId) throws IOException, ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Date curDate = sdf.parse(date);

		Integer from = (page == null) || (count == null) ? null : (page - 1) * count;
		List<ContractReportRecord> list  = reportDao.getContractReportRecords(curDate, from, count);
		Long total = (long) list.size();

		JsonModelAndView mav = new JsonModelAndView(jsonViews.getListContractReportRecordView());
		
		return makeListResponse(mav, list, count, page, total);
	}

}
