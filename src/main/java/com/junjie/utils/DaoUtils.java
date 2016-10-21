package com.junjie.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DaoUtils
{
	public static final String QUOTATION_MARK = "\'";
	
	public static final DateFormat FORMAT_YYYY_MM_DD = new SimpleDateFormat("yyyy-MM-dd");

	public static String format(Object value, String delimiter)
	{
		return delimiter + value + delimiter;
	}

	public static String formatQuatation(Object value)
	{
		return format(value, QUOTATION_MARK);
	}
	
	public static String formatForDB(Date date){
		return FORMAT_YYYY_MM_DD.format(date);
	}
}
