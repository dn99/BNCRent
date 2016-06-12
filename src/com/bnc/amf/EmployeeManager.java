package com.bnc.amf;

import org.apache.log4j.Logger;

import java.util.ArrayList;

public class EmployeeManager
{
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger( EmployeeManager.class );

	public Object[] getList( String deptId )
	{
		System.out.println( "DEPT ID : " + deptId );

		if ( logger.isDebugEnabled() )
		{
			logger.debug( "getList(String) - start" ); //$NON-NLS-1$
		}

		ArrayList list = new ArrayList();
		if ( deptId.equals( "ENG" ) )
		{
			list.add( new Employee( "Christina Coenreaets", "555-219-270", "ccoenraets@fictitious.com" ) );
			list.add( new Employee( "Louis Freligh", "555-219-2270", "lfrelight@fictitious.com" ) );
		} 
		else if ( deptId.equals( "PM" ) )
		{
			list.add( new Employee( "Ronnie Hodgman", "555-219-2270", "ccoenraets@fictitious.com" ) );
			list.add( new Employee( "Joanne Wall", "555-219-2270", "lfrelight@fictitious.com" ) );
		} 
		else if ( deptId.equals( "MKT" ) )
		{
			list.add( new Employee( "Maurice Smith", "555-219-2270", "ccoenraets@fictitious.com" ) );
			list.add( new Employee( "Mary Jones", "555-219-2270", "lfrelight@fictitious.com" ) );
		}
		Object[] returnObjectArray = list.toArray();

		if ( logger.isDebugEnabled() )
		{
			logger.debug( "getList(String) - end" ); //$NON-NLS-1$
		}

		return returnObjectArray;
	}
}