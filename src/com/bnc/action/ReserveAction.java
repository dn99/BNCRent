package com.bnc.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bnc.props.CodeProperties;
import com.bnc.reserve.model.RentDTO;
import com.bnc.util.DAOFactory;
import com.bnc.util.PageNavi;
import com.bnc.util.PageSize;
import com.opensymphony.xwork2.Action;

public class ReserveAction implements Action
{
	private PageNavi navigator;
	private List<RentDTO> rentList;
	private List<String> rlocationList;
	private List<String> rofficeList;
	private List<String> retlocationList;
	private List<String> retofficeList;
	private List<String> insuranceList;
	private List<Integer> insurancePriceList;
	private int pg = 1;
	
	public PageNavi getNavigator()
	{
		return navigator;
	}
	public List<RentDTO> getRentList()
	{
		return rentList;
	}
	public List<String> getRlocationList()
	{
		return rlocationList;
	}
	public List<String> getRofficeList()
	{
		return rofficeList;
	}
	public List<String> getRetlocationList()
	{
		return retlocationList;
	}
	public List<String> getRetofficeList()
	{
		return retofficeList;
	}
	public List<String> getInsuranceList()
	{
		return insuranceList;
	}
	public List<Integer> getInsurancePriceList()
	{
		return insurancePriceList;
	}
	public int getPg()
	{
		return pg;
	}
	public void setPg( int pg )
	{
		this.pg = pg;
	}
	
	@Override
	public String execute() throws Exception
	{
		int end = pg * PageSize.LIST_SIZE_RESERVE;
		int start = end - PageSize.LIST_SIZE_RESERVE;
		
		rentList = DAOFactory.getRentDAO().getRentList( end, start );
		setPageNavi();
		setCodeNameList();
		
		System.out.println( "RENT LIST SIZE : " + rentList.size() );
		
		return rentList == null ? ERROR : SUCCESS;
	}
	
	private void setPageNavi()
	{
		navigator = new PageNavi();
		navigator.setCallFunction( "hs_goRentList" );
		navigator.setPageSize( PageSize.PAGE_SIZE_RESERVE );
		
		int totalArticle = 0;
		try
		{
			totalArticle = DAOFactory.getRentDAO().getTotalRentCount();
		} 
		catch ( NumberFormatException e )
		{
			e.printStackTrace();
		} 
		catch ( SQLException e )
		{
			e.printStackTrace();
		}
		navigator.setTotalArticle( totalArticle );
		int totalPage = ( totalArticle + ( PageSize.LIST_SIZE_RESERVE - 1 ) ) / PageSize.LIST_SIZE_RESERVE;
		navigator.setTotalPage( totalPage );
		navigator.setPageNo( pg );
		boolean nowFirst = pg <= PageSize.LIST_SIZE_RESERVE ?  true : false;
		navigator.setNowFirst( nowFirst );
		navigator.setNowEnd( isEnd( pg, totalPage, PageSize.PAGE_SIZE_RESERVE ) );
	}
	
	private boolean isEnd( int pg, int totalPage, int pageSize ) 
	{
		if( ( totalPage - 1 ) / pageSize * pageSize < pg )
			return true;
		return false;
	}
	
	private void setCodeNameList()
	{
		rlocationList = new ArrayList<String>();
		rofficeList = new ArrayList<String>();
		retlocationList = new ArrayList<String>();
		retofficeList = new ArrayList<String>();
		insuranceList = new ArrayList<String>();
		insurancePriceList = new ArrayList<Integer>();
		
		String rlocation = "";
		String roffice = "";
		String retlocation = "";
		String retoffice = "";
		String insurance = "";
		int insurancePrice = 0;
		
		for ( RentDTO dto : rentList )
		{
			rlocation = CodeProperties.getCodeName( CodeProperties.CAR_LO, dto.getRent_rlocation() );
			rlocationList.add( rlocation );
			roffice = CodeProperties.getCodeName( CodeProperties.CAR_OFFICE, dto.getRent_roffice() );
			rofficeList.add( roffice );
			retlocation = CodeProperties.getCodeName( CodeProperties.CAR_LO, dto.getRent_retlocation() );
			retlocationList.add( retlocation );
			retoffice = CodeProperties.getCodeName( CodeProperties.CAR_OFFICE, dto.getRent_retoffice() );
			retofficeList.add( retoffice );
			insurance = CodeProperties.getTypeInsurance( dto.getRent_insurance_seq() );
			insuranceList.add( insurance );
			insurancePrice = CodeProperties.getPriceInsurance( dto.getRent_insurance_seq() );
			insurancePriceList.add( insurancePrice );
		}
	}
}
