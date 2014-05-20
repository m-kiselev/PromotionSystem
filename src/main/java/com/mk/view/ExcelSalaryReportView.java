package com.mk.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

public class ExcelSalaryReportView extends AbstractExcelView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

//		Map<String,String> revenueData = (Map<String,String>) model.get("revenueData");
//		@SuppressWarnings("unchecked")
		List<SalaryReportRecord> list = (List<SalaryReportRecord>) model.get("list");
		//create a wordsheet
		HSSFSheet sheet = workbook.createSheet("Отчет по ЗП");
 
		HSSFRow header = sheet.createRow(0);
		header.createCell(0).setCellValue("ФИО менеджера");
		header.createCell(1).setCellValue("Зарплата");
		header.createCell(2).setCellValue("Кол-во раб. часов");
		header.createCell(3).setCellValue("Зарплата за месяц");
		header.createCell(4).setCellValue("Премия");
		header.createCell(5).setCellValue("Итого");
 
		int rowNum = 1;
		for (SalaryReportRecord rec : list) {
			//create the row data
			HSSFRow row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(rec.getFio());
			row.createCell(1).setCellValue(rec.getSalary());
			row.createCell(2).setCellValue(rec.getHourOfDay());
			row.createCell(3).setCellValue(rec.getSalaryOfMonth());
			row.createCell(4).setCellValue(rec.getBonus());
			row.createCell(5).setCellValue(rec.getSumm());
         }
	}

	
}
