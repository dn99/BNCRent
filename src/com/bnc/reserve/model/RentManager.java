package com.bnc.reserve.model;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bnc.rentcar.model.CarInformationDTO;
import com.bnc.travel.model.TRentDTO;
import com.bnc.util.PageNavi;
import com.bnc.util.PageSize;

public class RentManager 
{
	RentDAO dao;
	
	public RentManager()
	{
		dao = new RentDAO();
	}
	
	public List<RentDTO> getRentList( int pg ) 
	{
		int end = pg * PageSize.LIST_SIZE_RESERVE;
		int start = end - PageSize.LIST_SIZE_RESERVE;
		
		return dao.getRentList( end, start );
	}
	
//	public List<CarInformationDTO> getRentCarList( int pg, int roffice, int carGrade ) throws SQLException
	public List<CarInformationDTO> getRentCarList( int pg, int roffice ) throws SQLException
	{
		int end = pg * PageSize.LIST_SIZE_RESERVE_CAR;
		int start = end - PageSize.LIST_SIZE_RESERVE_CAR;
		
		Map<String, Integer> carListInfo = new HashMap<String, Integer>();
		carListInfo.put( "start", start );
		carListInfo.put( "end", end );
		carListInfo.put( "roffice", roffice );
//		carListInfo.put( "carGrade", carGrade );
		
		System.out.println( "CAR INFO : " + start + "\t" + end + "\t" + roffice  );
		
		return dao.getRentCarList( carListInfo );
	}
	
	public PageNavi getPageNavi( int pg, int roffice ) 
	{
		PageNavi navigator = new PageNavi();
		navigator.setCallFunction( "hs_goCarList" );
		navigator.setPageSize( PageSize.PAGE_SIZE_RESERVE_CAR );
		
		int totalCarNum = 0;
		try
		{
			totalCarNum = dao.getTotalCarCount( roffice );
			System.out.println( "totalCarNum : " + totalCarNum );
		} 
		catch ( NumberFormatException e )
		{
			e.printStackTrace();
		} 
		catch ( SQLException e )
		{
			e.printStackTrace();
		}
		
		navigator.setTotalArticle( totalCarNum );
		int totalPage = ( totalCarNum + ( PageSize.LIST_SIZE_RESERVE_CAR - 1 ) ) / PageSize.LIST_SIZE_RESERVE_CAR;
		navigator.setTotalPage( totalPage );
		navigator.setPageNo( pg );
		boolean nowFirst = pg <= PageSize.PAGE_SIZE_RESERVE_CAR ?  true : false;
		navigator.setNowFirst( nowFirst );
		navigator.setNowEnd( isEnd( pg, totalPage, PageSize.PAGE_SIZE_RESERVE_CAR ) );
		
		return navigator;
	}

	private boolean isEnd( int pg, int totalPage, int pageSize ) 
	{
		if( ( totalPage - 1 ) / pageSize * pageSize < pg )
			return true;
		return false;
	}
	
	public List<InsuranceDTO> getInsuranceList() throws SQLException
	{
		return dao.getInsuranceList();
	}
	
	public boolean insertRent( RentDTO dto )
	{
		return dao.insertRent( dto );
	}

	/**
	 * TODO 렌트카 예약 이후 해당 차량 예약체크 시켜주기
	 * @param carSeq
	 */
	public void updateRentCheck( int carSeq )
	{
		// TODO Auto-generated method stub
		
	}

	public int getRentSeq()
	{
		int rentSeq = 0;
		try
		{
			rentSeq = dao.getRentSeq();
		} 
		catch ( NumberFormatException e )
		{
			e.printStackTrace();
		} 
		catch ( SQLException e )
		{
			e.printStackTrace();
		}
		
		return rentSeq;
	}

	public int insertTour( TRentDTO trentDTO )
	{
		int cnt = 0;
		try
		{
			cnt = dao.insertTour( trentDTO ); 
		} 
		catch ( SQLException e )
		{
			e.printStackTrace();
		}
		
		return cnt;
	}
}
