package com.bnc.sqlmap;

import java.io.IOException;
import java.io.Reader;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class SqlMapConfig
{
	private static SqlMapClient sqlmap;
	
	static
	{
		try
		{
			Reader reader = Resources.getResourceAsReader( "com/bnc/sqlmap/SqlMapConfig.xml" );
			sqlmap = SqlMapClientBuilder.buildSqlMapClient( reader );
			reader.close();
		} 
		catch ( IOException e )
		{
			e.printStackTrace();
		}
	}
	
	public static SqlMapClient getSqlMapClient()
	{
		return sqlmap;
	}
}
