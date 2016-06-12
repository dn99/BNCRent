package com.bnc.util;

import com.bnc.amf.model.RemoteDAO;
import com.bnc.model.MainDAO;
import com.bnc.reserve.model.RentDAO;

public class DAOFactory
{
	private static MainDAO mainDAO;
	
	private static RemoteDAO remoteDAO;
	private static RentDAO rentDAO;
	
	public static MainDAO getMainDAO()
	{
		if ( mainDAO == null ) mainDAO = new MainDAO();
		return mainDAO;
	}
	
	public static RentDAO getRentDAO()
	{
		if ( rentDAO == null ) rentDAO = new RentDAO();
		return rentDAO;
	}
	
	public static RemoteDAO getRemoteDAO()
	{
		if ( remoteDAO == null ) remoteDAO = new RemoteDAO();
		return remoteDAO;
	}
}
