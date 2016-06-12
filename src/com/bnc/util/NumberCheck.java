package com.bnc.util;

public class NumberCheck 
{
	/**
	 * null, "", ???????? ??? 0 ????
	 */
	public static int nullToZero( String tmp )
	{
		int num = 0;
		if ( tmp != null && tmp.trim().length() != 0 )
		{
			int cnt = 0;
			int len = tmp.length();
			for ( int i=0; i<len; i++ )
			{
				int c = tmp.charAt( i );		// 0: 48, 9: 57
				if ( c < 48 || c > 57 ) 
				{
					cnt++;
					break;
				}
			}
			num = cnt == 0 ? Integer.parseInt( tmp ) : 0;
		}
		
		return num;
	}
	
	/**
	 * null, "", ???????? ??? 1 ????
	 */
	public static int nullToOne( String tmp )
	{
		int num = 1;
		if ( tmp != null && tmp.trim().length() != 0 )
		{
			int cnt = 0;
			int len = tmp.length();
			for ( int i=0; i<len; i++ )
			{
				int c = tmp.charAt( i );		// 0: 48, 9: 57
				if ( c < 48 || c > 57 ) 
				{
					cnt++;
					break;
				}
			}
			num = cnt == 0 ? Integer.parseInt( tmp ) : 1;
		}
		
		return num;
	}
}