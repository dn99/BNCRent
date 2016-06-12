package com.bnc.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.bnc.common.model.CommonDAO;

public class DateUtil
{
	private static CommonDAO dao;
	
	public static String FORMAT_DATE_DAY = "yyyy.MM.dd";
	public static String FORMAT_DATE_TIME = "HH24:MI:SS";
	
	/**
	 * ���� ��¥/�ð� ���� ��ȯ
	 * @return
	 */
	public static String getCurrentDate( String formatDate )
	{
		if ( dao == null ) dao = new CommonDAO();
		return dao.getCurrentDate( formatDate ).trim();
	}
	
	/**
	 * ��¥ ������ ���Ͽ� Date�� ��ȯ
	 * @param dayStr
	 * @return
	 */
	public static Date getDayStrToDate( String dayStr )
	{
		DateFormat dateFormat = new SimpleDateFormat( FORMAT_DATE_DAY );
		Date date = null;
		try
		{
			date = dateFormat.parse( dayStr.trim() );
		} 
		catch ( ParseException e )
		{
			e.printStackTrace();
		}
		
		return date;
	}
}
