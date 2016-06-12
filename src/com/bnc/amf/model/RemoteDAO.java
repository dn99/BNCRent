package com.bnc.amf.model;

import java.sql.SQLException;
import java.util.List;

import com.bnc.rentcar.model.CarInformationDTO;
import com.bnc.sqlmap.SqlMapConfig;

public class RemoteDAO
{

	public List<CarInformationDTO> getRentCarList() throws SQLException
	{
		return ( List<CarInformationDTO> )SqlMapConfig.getSqlMapClient().queryForList( "remote.getCarList" );
	}

}
