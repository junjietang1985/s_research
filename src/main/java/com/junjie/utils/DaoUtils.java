package com.junjie.utils;

public class DaoUtils
{
	public static final String QUOTATION_MARK = "\'";

	public static String format(Object value, String delimiter)
	{
		return delimiter + value + delimiter;
	}

	public static String formatQuatation(Object value)
	{
		return format(value, QUOTATION_MARK);
	}
}
