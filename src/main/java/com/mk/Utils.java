package com.mk;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Utils {
	/**
	 * Дата соответствующая началу последнего дня месяца указанной даты 
	 * @param date
	 * @return
	 */
	public static Date lastDayOfMonth(Date date) {

		Calendar c = new GregorianCalendar();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		return c.getTime();
	}

	/**
	 * Дата соответствующая началу первого дня месяца указанной даты
	 * 
	 * @param date
	 * @return
	 */
	public static Date firstDayOfMonth(Date date) {

		Calendar c = new GregorianCalendar();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
		return c.getTime();
	}
}
