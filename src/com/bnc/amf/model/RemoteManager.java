package com.bnc.amf.model;

import java.sql.SQLException;
import java.util.List;

import com.bnc.rentcar.model.CarInformationDTO;
import com.bnc.util.DAOFactory;

public class RemoteManager
{
	public List<CarInformationDTO> getRentCarList()
	{
		List<CarInformationDTO> list = null;
		try
		{
			list = DAOFactory.getRemoteDAO().getRentCarList();
		} 
		catch ( SQLException e )
		{
			e.printStackTrace();
		}
		return list;
	}
}
