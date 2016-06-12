package com.bnc.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.bnc.props.AppProperties;

public class Encoder 
{
	public static String urlSimpleEncode( String tmp )
	{
		return URLEncoder.encode( tmp );
	}
	
	public static String urlEncode( String tmp )
	{
		String url = null;
		try
		{
			url = URLEncoder.encode( tmp, AppProperties.CHAR_SET );
		} 
		catch ( UnsupportedEncodingException e )
		{
			e.printStackTrace();
		}
		
		return url;
	}
	
	public static String urlUTFEncode( String tmp )
	{
		String url = null;
		try
		{
			url = URLEncoder.encode( tmp, "UTF-8" );
		} 
		catch ( UnsupportedEncodingException e )
		{
			e.printStackTrace();
		}
		
		return url;
	}
	
	public static String isoToEuc( String tmp )
	{
		String euc = null;
		try
		{
			euc = new String( tmp.getBytes( "ISO-8859-1" ), AppProperties.CHAR_SET );
		} 
		catch ( UnsupportedEncodingException e )
		{
			e.printStackTrace();
		}
		
		return euc;
	}
	
	public static String isoToUTF( String tmp )
	{
		String euc = null;
		try
		{
			euc = new String( tmp.getBytes( "ISO-8859-1" ), "UTF-8" );
		} 
		catch ( UnsupportedEncodingException e )
		{
			e.printStackTrace();
		}
		
		return euc;
	}
}