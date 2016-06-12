package com.bnc.model;

import java.sql.SQLException;
import java.util.List;

import com.bnc.community.model.NoticeDTO;
import com.bnc.rentcar.model.CarInformationDTO;
import com.bnc.reserve.model.RentDTO;
import com.bnc.sqlmap.SqlMapConfig;
import com.bnc.travel.model.TravelDTO;

public class MainDAO
{
	public List<NoticeDTO> executeNoticeList() throws SQLException
	{
		return SqlMapConfig.getSqlMapClient().queryForList( "main.noticelist" );
	}
	
	public CarInformationDTO executeBestCar( int seq ) throws SQLException
	{
		return ( CarInformationDTO )SqlMapConfig.getSqlMapClient().queryForObject( "main.bestcar", seq );
	}
	
	public TravelDTO executeBestTravel( int seq ) throws SQLException
	{
		return ( TravelDTO )SqlMapConfig.getSqlMapClient().queryForObject( "main.besttravel", seq );
	}
	
	public List<CarInformationDTO> executeCarList() throws SQLException
	{
		return SqlMapConfig.getSqlMapClient().queryForList( "main.carlist" );
	}

	public List<TravelDTO> executeTravelList() throws SQLException
	{
		return SqlMapConfig.getSqlMapClient().queryForList( "main.travellist" );
	}

}
