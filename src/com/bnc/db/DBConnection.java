package com.bnc.db;

import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBConnection 
{
	private static DBConnection dbc;
	
	private DBConnection() {}
	
	public static DBConnection getInstance()
	{
		if ( dbc == null ) dbc = new DBConnection();
		
		return dbc;
	}
	
	public Connection getConnection()
	{
		Connection conn = null;
		try 
		{
			Context ictx = new InitialContext();
			Context ctx = ( Context )ictx.lookup( "java:comp/env" );
			DataSource ds = ( DataSource )ctx.lookup( "jdbc/bnc" );
			conn = ds.getConnection();
		} 
		catch ( SQLException e ) 
		{
			e.printStackTrace();
		} 
		catch ( NamingException e )
		{
			e.printStackTrace();
		}
		
		return conn;
	}
}